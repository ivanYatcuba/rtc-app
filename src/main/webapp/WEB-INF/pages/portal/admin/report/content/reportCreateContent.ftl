<#import "../../../../fieldMacro.ftl" as formMacro />

<@formMacro.rtcForm "reportForm" "/admin/export/insertReport" "create.message" "${validationRules}">
    <#include "reportForm.ftl" />
    <@formMacro.rtcSubmit "Create" "Cancel" "/admin/export/viewAll"/>
</@formMacro.rtcForm>
<#--<form class="form-horizontal" name="report" id="report"
      action="<@spring.url "/admin/export/insertReport" />" method="POST">
    <h3 class="page-header"><@spring.message "report.create"/></h3><br/>
<#include "reportForm.ftl" />
    <!--Create & Cancel&ndash;&gt;
    <div id="createAndCancel" class="row" style="margin-left: 1px">
        <div class="col-md-6">
        </div>
        <div class="col-md-5" style="text-align: right">
            <input type="submit" class="btn" value="Create">
            or
            <a href="<@spring.url "/admin/export/viewAll" />">Cancel</a>
        </div>
    </div>
</form>


<script type="text/javascript">
    function CheckNullReportFields(userCode) {
        $("#userCode").val(userCode);
        $('#removeUserModal').modal('show')
    }
</script-->
