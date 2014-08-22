
<form name="report" id="report" action="<@spring.url "/admin/export/updateReport" />" method="POST"
      xmlns="http://www.w3.org/1999/html">
    <h3 class="page-header"><@spring.message "report.edit"/></h3>
    <@spring.formHiddenInput "report.id" />
    <@spring.formHiddenInput "report.code" />
    <@spring.formHiddenInput "report.createdDate" />
    <#include "reportForm.ftl" />

    <!--Create & Cancel-->
    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6">
        </div>
        <div class="span5" style="text-align: right">
            <button type="submit" class="btn" >Update</button> or
            <a href="<@spring.url "/admin/export/viewAll" />">Cancel</a>
        </div>
    </div>
</form>
