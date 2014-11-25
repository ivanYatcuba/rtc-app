<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>
<style>
    label {
        float: left;
        width: 13em;
        text-align: right;
        margin-bottom: 0px;
    }
</style>
<div style="width: 85%">
<div>
    <h4 class="page-header"><@spring.message "news.details"/></h4>
</div>
<div class="row">
    <div class="col-md-6">
        <@formMacro.rtcFormLabelOut "news.title" "${news.title}"/>
    </div>
    <div class="col-md-6">
        <label><@spring.message "news.tags"/></label>
        <p>&nbsp${news.tags?join(", ")}</p>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <@formMacro.rtcFormLabelOut "news.description" "${news.description}"/>
    </div>
</div>
<hr/>
<div class="row">
<div class="col-md-12">
    <div>
        <label><@spring.message "news.status"/></label>&nbsp
        <#if news.status=='DRAFT'>
            <@formMacro.rtcColorLabel "Draft" "label-warning"/>
        <#else>
            <@formMacro.rtcColorLabel "Published" "label-success"/>
        </#if>
    </div>
    <div>
        <@formMacro.rtcFormLabelOut "news.creationDate" "${news.createDate?string('dd-MMM-yyyy')}"/>
    </div>
</div>
</div>
<hr/>
<@formMacro.rtcSubmitDoOrCancel "action.edit" "/admin/news/${news.code}/edit" "Cancel" "/admin/search"/>
</div>
</@layout.layout>
