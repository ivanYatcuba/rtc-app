<#import "../../../fieldMacro.ftl" as formMacro/>
<form class="form-horizontal" name="courseFilter" role="form"
      action="<@spring.url "/admin/course/filter"/>" method="get">
    <div class="row">
        <div class="col-md-6">
        <@formMacro.rtcFormTextInput "filterCourse.name" "filterCourse.name"/>
        <@formMacro.rtcFormMultiSelect "filterCourse.types" "filterCourse.types" categories/>
        </div>
        <div class="col-md-6">
        <div class="form-group">
            <label class="control-label col-md-3"><@spring.message "filterCourse.startDate"/></label>
            <@rtcmacros.formSingleSelect "filterCourse.dateMoreLessEq", ["=", "<", ">"]/>
            <@rtcmacros.formDatepicker "filterCourse.startDate"/>
            </div>
            <@formMacro.rtcFormSingleSelect "filterCourse.status" "filterCourse.status" statuses/>
             <div class="form-group">
                <label class="control-label col-md-3" for="addExpertH">
                <@spring.message "filterCourse.experts"/></label>
                <div class="col-md-8">
                    <div id="experts">
                    </div>
                   <a id="addExpertH" href="#" onclick="addExpert()">Add Expert</a>
                </div>
            </div>
    </div>
    <div class="row">
        <div class="col-md-6">
        </div>
        <div class="col-md-5" style="text-align: right">
            <input type="submit" class="btn btn-primary" value="Search"/>
            <a href="<@spring.url "/admin/course" />" class="btn btn-default">Reset</a>
        </div>
    </div>
</form>

<script src="<@spring.url'/resources/js/pages/courseForm.js'/>"></script>
<script src="<@spring.url'/resources/js/jquery/jquery.validate.min.js'/>"></script>
<script src="<@spring.url'/resources/js/jquery/jquery-validate.bootstrap-tooltip.min.js'/>"></script>
<script>
    $(function () {
        $("#courseFilter").validate();
        prepareCourseFormPage("<@spring.url "/admin/user/expertUsers"/>");
    <#if filterCourse.experts??>
        <#assign i = 0>
        <#list  filterCourse.experts as f>
            addExpert();
            setFieldSelection(${i}, "${f.name}" + " " + "${f.surname}" + " " + "${f.email}");
            #assign i = i+1>
        </#list>
    </#if>
    });
</script>
