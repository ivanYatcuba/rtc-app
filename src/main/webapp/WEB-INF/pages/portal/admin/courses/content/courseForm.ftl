<style>
    hr {
        float:left;
        width:91%;
        margin-bottom: 20px;
    }
</style>

<div class="row">
    <div class="col-md-6">
        <@formMacro.rtcFormTextInput "course.name" "course.name" "required" 'maxlength="50"'/>
        <@formMacro.rtcFormMultiSelect "course.category" "course.types" categories/>
        <@formMacro.rtcFormTextInput "course.capacity" "course.capacity"  "input-small required" 'style="width:50px;" onkeydown="return isNumber(event);" size="2" maxlength="2"' />
    </div>
    <div class="col-md-6">
        <#--<@formMacro.rtcFormTextInput "course.startDate" "course.startDate" "input-medium required" />-->
        <#--<@formMacro.rtcFormTextInput "course.endDate" "course.endDate" "input-medium required" />-->
        <@spring.bind "course.startDate"/>
            <div class="form-group">
                <label class="control-label col-md-3">
                <@spring.message "course.startDate"/>
                </label>
                <div class="col-md-5">
                <@spring.formInput "course.startDate" "input-medium required"/>
                </div>
            </div>
        <@spring.bind "course.endDate"/>
            <div class="form-group">
                <label class="control-label col-md-3">
                <@spring.message "course.endDate"/>
                </label>
                <div class="col-md-5">
                <@spring.formInput "course.endDate" "input-medium required"/>
                </div>
            </div>
        <@formMacro.rtcFormTagsInput "course.tags" "course.tags"/>
    </div>
</div>
<hr>
<div class="row">
    <div class="col-md-6">
        <#--<@expertMultiSelect/>-->
        <@formMacro.rtcFormMultiSelect "course.experts" "course.experts" experts 'style="width:100%;"'/>
    </div>
</div>
<hr>

<div class="row">
    <div class="col-md-6">
       <@formMacro.rtcFormTextarea "course.description" "course.description" "required" 'style="width:425%;" rows="3" maxlength="255"'/>
    </div>
</div>
<#if !course.isPublished()>
    <hr>
    <div class="row">
        <div class="col-md-10">
            <div class="form-group checkbox">
                <label class="control-label col-md-2"></label>
                <label class = "col-md-10 style="padding-top: 7px">
                    <input type="checkbox">Publish as news
                </label>
            </div>
        </div>
    </div>
</#if>

<#--<#macro expertMultiSelect>-->
    <#--<#assign exp = []>-->
    <#--<#if experts??>-->
        <#--<#list experts as expert>-->
            <#--<#if !expert.isForRemoval()>-->
                <#--<#assign exp = exp + [ expert ] />-->
            <#--</#if>-->
        <#--</#list>-->
        <#--<@formMacro.rtcFormMultiSelect "filterCourse.experts" "filterCourse.experts" exp 'style="width:100%;"'/>-->
    <#--</#if>-->
<#--</#macro>-->
<hr>
&nbsp;
<#--<@formMacro.rtcFormValidation formName="courseForm" jsonRules="${validationRules}" />-->
<@formMacro.rtcFormValidation formName="courseForm" jsonRules="${validationRules}" />
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
