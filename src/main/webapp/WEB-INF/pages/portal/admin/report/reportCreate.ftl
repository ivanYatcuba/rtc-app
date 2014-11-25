<#import "../layout/layout.ftl" as layout/>
<#import "../../../fieldMacro.ftl" as formMacro />

<@layout.layout>

<@formMacro.rtcForm "reportForm" "/admin/export/insertReport" "report.create.page.header" "${validationRules}">
    <#include "reportForm.ftl" />
    <@formMacro.rtcSubmit "Create" "Cancel" "/admin/search"/>
</@formMacro.rtcForm>

</@layout.layout>

<script type="text/javascript">
    function CheckNullReportFields(userCode) {
        $("#userCode").val(userCode);
        $('#removeUserModal').modal('show')
    }
</script>
