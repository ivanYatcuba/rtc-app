<form class="form-horizontal" name="report" id="report"
      action="<@spring.url "/admin/export/updateReport" />" method="POST"
      xmlns="http://www.w3.org/1999/html">
    <h3 class="page-header"><@spring.message "report.edit"/></h3><br/>
<@spring.formHiddenInput "report.id" />
<@spring.formHiddenInput "report.code" />
<@spring.formHiddenInput "report.createdDate" />
<#include "reportForm.ftl" />

    <!--Create & Cancel-->
    <div class="row-fluid span12" style="margin-left: 1px">
    <@formMacro.rtcSubmitDoOrCancel "action.update" "/admin/export/update/${report.code}" "action.cancel"
    "/admin/search"/>
    </div>
</form>
