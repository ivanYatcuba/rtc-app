<form class="form-horizontal" name="report" id="report" action="<@spring.url "/admin/export/insertReport" />" method="POST">
    <h3 class="page-header"><@spring.message "report.create"/></h3><br/>
    <#include "reportForm.ftl" />
    <!--Create & Cancel-->
    <div id="createAndCancel" class="row" style="margin-left: 1px">
        <div class="col-md-6">
        </div>
        <div class="col-md-5" style="text-align: right">
            <button type="submit" class="btn" >Create</button> or
            <a href="<@spring.url "/admin/export/viewAll" />">Cancel</a>
        </div>
    </div>
</form>