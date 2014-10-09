<form class="form-horizontal" id="course" name="course"
      action="<@spring.url "/admin/course/save" />" method="POST">
    <h3 class="page-header"><@spring.message "create.message"/></h3><br/>
<#include "courseForm.ftl" />
    <div class="row">
        <div class="col-md-6">
        </div>
        <div class="col-md-5" style="text-align: right">
            <input type="submit" name="subBtn" onsubmit="document.subBtn.disabled = true;" class="btn" value="Create"/> or
            <a href="<@spring.url "/admin/course" />">Cancel</a>
        </div>
    </div>
</form>
