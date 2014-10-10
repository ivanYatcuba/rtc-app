<div class="row">
    <div class="col-md-6">
    <@spring.formItem "course.name", 'class="required" maxlength="50"'/>
        <label for="types"><@spring.message "course.category" /></label>
    <@spring.formMultiSelect "course.types", categories/>
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
        <div class="form-group">
            <label class="control-label col-md-2"
                   for="addExpertH">*<@spring.message "filterCourse.experts"/></label>

            <div class="col-md-6">
                <div id="experts"></div>
                <a id="addExpertH" href="#" onclick="addExpert()">Add Expert</a>
            </div>
        </div>
    </div>
</div>
<hr>
<div class="row">
    <div class="col-md-12">
        <div class="form-group">
            <label class="control-label col-md-2"
                   for="description"><@spring.message "course.description"/></label>

            <div class="col-md-9"><@spring.formTextarea "course.description"
            'style="width:100%;" rows="3" maxlength="255" class=\"required\"'/> </div>
        </div>
    </div>
</div>
<hr>
<div class="row">
    <div class="col-md-12">
        <div class="form-group">
            <label class="control-label col-md-2"><@spring.message "course.status"/></label>
            <label style="padding-top: 5px">&nbsp;${course.status}</label>
        </div>
    </div>
</div>
<@spring.formValidation formName="courseForm" jsonRules="${validationRules}"/>
<script type="text/javascript"
        src="<@spring.url'/resources/js/pages/courseForm.js'/>"></script>
<script>
    $(function () {
        prepareCourseFormPage("<@spring.url "/admin/user/expertUsers"/>");
    <#if course.experts??>
        <#assign i = 0>
        <#list course.experts as f>
            addExpert();
            setFieldSelection(${i}, "${f.name}" + " " + "${f.surname}" + " " + "${f.email}");
            <#assign i = i+1>
        </#list>
    </#if>
    });
</script>

