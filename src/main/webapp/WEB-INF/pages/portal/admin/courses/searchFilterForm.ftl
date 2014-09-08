<form name="course" id="course" action="<@spring.url "/admin/course/filter"/>" method="get">
    <div class="row">
        <div class="col-md-6">
        <@spring.formItem "course.name"/>
        <@spring.bind "course.type" />
        <label for="categories"><@spring.message "course.category"/></label>
        <#--<@spring.formSingleSelect "searchFilter.categories"/>-->
            <select id="categories" name="categories" >
            <option/>
            <#list categories as cat>
                <option value="${cat}"  />${cat}<br>
            </#list>
            </select>
            <label>*<@spring.message "course.experts"/></label>
            <div id="experts"></div>
            <label for="addExpertH"></label><a id="addExpertH" href="#" onclick="addExpert()">Add Expert</a>
        </div>

        <div class="col-md-6">
        <@spring.formItem "course.startDate" "" "datepiker" />
        <@spring.bind "course.status" />
        <label for="status"><@spring.message "searchFilter.status"/></label>
            <select id="status" name="status">
            <option value="">ALL</option>
            <#list statuses as stat>
                <option value="${stat}" />${stat}<br>
            </#list>
            </select>
        <@spring.formItem "course.tags" "" "tag" />
        </div>
    </div>

    <div class="col-md-10">
        <br>
        <hr>
        <br>
        <input type="submit" class="btn" value="Search"/> or <a
            href="<@spring.url "/admin/course" />">Reset</a>
    </div>

</form>

<script type="text/javascript"  src="<@spring.url'/resources/js/courseForm.js'/>"></script>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.min.js'/>"></script>
<script src="<@spring.url'/resources/css/js/jquery.validate.min.js'/>"></script>
<script src="<@spring.url'/resources/css/js/jquery-validate.bootstrap-tooltip.min.js'/>"></script>


<script>

    $(function() {
        $("#course").validate();
        prepareCourseFormPage("<@spring.url "/admin/user/expertUsers"/>");
    <#if course.experts??>
        <#assign i = 0>
        <#list  course.experts as f>
            addExpert();
            setFieldSelection(${i}, "${f.name}"+" "+"${f.surname}"+" "+"${f.email}");
            <#assign i = i+1>
        </#list>
    </#if>
    });
</script>