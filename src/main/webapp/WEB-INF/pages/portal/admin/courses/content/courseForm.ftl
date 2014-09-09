<div class="row">
    <div class="col-md-6">
        <@spring.formItem "course.name" "class=\"required\""/>
        <div>
            <label for="type"><@spring.message "course.category" /></label>
            <@spring.bind "categories" />
            <@spring.formSingleSelect "course.type", categories, "class=\"required\""/>
        </div>
        <@spring.formItem "course.capacity", 'onkeydown="return isNumber(event);" class="input-small required" size="2" maxlength="2" ' />
    </div>

    <div class="col-md-6">
        <@spring.formItem "course.startDate" 'class="input-medium required"' />
        <@spring.formItem "course.endDate" 'class="input-medium required"' />
        <@spring.formItem "course.tags" "" "tag"/>
    </div>
</div>
<hr>
<div class="row">
    <div class="col-md-6">
        <label>*<@spring.message "course.experts"/></label>
        <div id="experts"></div>
        <label for="addExpertH"></label><a id="addExpertH" href="#" onclick="addExpert()">Add Expert</a>
    </div>
</div>
<hr>
<div class="row">
    <div class="col-md-11">
        <label for="description"><@spring.message "course.description"/> </label>
        <@spring.formTextarea "course.description" 'style="width:79%;" rows="3" maxlength="255" id=\"description\" class=\"required\"'/>
    </div>
</div>
<hr>
<div class="row">
    <label><@spring.message "course.status"/></label>&nbsp;${course.status}
</div>

<@spring.formValidation formName="course" jsonRules="${validationRules}"/>
<script type="text/javascript"  src="<@spring.url'/resources/js/courseForm.js'/>"></script>
<script>
    $(function() {
        prepareCourseFormPage("<@spring.url "/admin/user/expertUsers"/>");
        <#if course.experts??>
        <#assign i = 0>
            <#list course.experts as f>
                addExpert();
                setFieldSelection(${i}, "${f.name}"+" "+"${f.surname}"+" "+"${f.email}");
                <#assign i = i+1>
            </#list>
        </#if>
    });
</script>




