<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>
<h3 class="page-header"><@spring.message "report.details"/></h3><br>
<style>
    label {
        float: left;
        width: 13em;
        margin-right: 1em;
        text-align: right;
        font-size: 10pt;
        margin-bottom: 10px;
    }
</style>
<div class="row">
    <div class="col-md-6">
        <div class="col-md-12">
            <@formMacro.rtcFormLabelOut "report.lable.name" "${report.name}"/>
        </div>
        <div class="col-md-12">
            <@formMacro.rtcFormLabelOut "report.lable.exportClass" "${report.exportClass}"/>
        </div>
    </div>
    <div class="col-md-6">
        <div class="col-md-12">
            <@formMacro.rtcFormLabelOut "report.lable.exportFormat" "${report.exportFormat}"/>
        </div>
    </div>
</div>
&NonBreakingSpace;
<hr>
<div class="row">
    <div class="col-md-6">
        <p style="margin-left: 6em"><b><@spring.message "report.lable.fields"/></b></p>
        <#if report.fields??>
            <#list report.fields as field>
                <label></label>

                <p>&nbsp${field}</p>
            </#list>
        </#if>
    </div>
</div>
<hr>
<div class="row">
<@formMacro.rtcSubmitDoOrCancel "action.edit" "/admin/export/update/${report.code}" "action.cancel" "/admin/search/${menuItem}"/>
</div>
</@layout.layout>
