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
        <div class="span6">
        </div>
        <div class="span5" style="text-align: right">
            <input type="submit" class="btn" value="Update">
            or
            <a href="<@spring.url "/admin/export/${report.code}" />">Cancel</a>
        </div>
    </div>
</form>
