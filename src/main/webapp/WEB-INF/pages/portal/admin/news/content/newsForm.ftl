<#import "../../../../fieldMacro.ftl" as formMacro />
<style>
    .control-label {
        float: left;
        width: 13em;
        text-align: right;
        margin-bottom: 0px;
    }
</style>
<div class="row">
    <div class="col-md-6">
        <@formMacro.rtcFormTextInput "news.title" "news.title" "required" "maxlength='255'"/>
    </div>
    <div class="col-md-6">
        <@formMacro.rtcFormTagsInput "news.tags" "news.tags" "" />
    </div>
</div>
<div class="row">
    <div class="col-md-12" >
        <@formMacro.rtcFormTextarea "news.description" "news.description" " required field col-md-9" "style='width:186%' rows='3' maxlength='255'"/>
    </div>
</div>
    <div class="row" style="padding-left: 13em">
    <#if news.status=='DRAFT'>
        <input id="publish" name="publish" type="checkbox"><@spring.message "news.publish"/>
    </#if>
    </div>
<hr/>
<@formMacro.rtcFormValidation "newsForm" "${validationRules}"/>