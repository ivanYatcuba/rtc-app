<form name="course" id="course" action="<@spring.url "/admin/course/save" />" method="post">
<h3 class="page-header"><@spring.message "create.message"/></h3>
    <#include "courseForm.ftl" />
    <!--Create & Cancel-->
    <div class="row">
        <div class="col-md-6">
        </div>
        <div class="col-md-5" style="text-align: right">
            <input type="submit" class="btn" value="Create"/> or
            <a href="<@spring.url "/admin/course" />">Cancel</a>
        </div>
    </div>
</form>
