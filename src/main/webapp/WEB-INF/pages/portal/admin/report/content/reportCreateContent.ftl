<form name="report" id="report" action="<@spring.url "/admin/export/reportCreationAction" />" method="get"
      xmlns="http://www.w3.org/1999/html">
    <h3 class="page-header"><@spring.message "report.create"/></h3>
    <#include "reportForm.ftl" />

    <!--Create & Cancel-->
    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6">
        </div>
        <div class="span5" style="text-align: right">
            <button name="action"  type="submit" class="btn" value="create">Create</button> or
            <a href="<@spring.url "/admin/export/viewAll" />">Cancel</a>
        </div>
    </div>
</form>