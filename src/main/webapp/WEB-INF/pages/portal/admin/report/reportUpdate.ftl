<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>
    <@formMacro.rtcForm "reportForm" "/admin/export/updateReport" "report.edit" "${validationRules}">
        <@spring.formHiddenInput "report.id" />
        <@spring.formHiddenInput "report.code" />
        <@spring.formHiddenInput "report.createdDate" />
        <#include "reportForm.ftl" />
        <div class="row-fluid span12" style="margin-left: 1px">
            <@formMacro.rtcSubmitDoOrCancel "action.update" "/admin/export/update/${report.code}" "action.cancel"
            "/admin/search/${menuItem}"/>
        </div>
    </@formMacro.rtcForm>
</@layout.layout>
