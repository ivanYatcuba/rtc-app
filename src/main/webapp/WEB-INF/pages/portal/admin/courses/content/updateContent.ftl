<form class="form-horizontal" name="course" id="course" action="<@spring.url "/admin/course/update" />" method="post">
    <@spring.formHiddenInput "course.code" />
    <@spring.formHiddenInput "course.status" />
    <@spring.formHiddenInput "course.publishDate" />
    <h3 class="page-header"><@spring.message "update.message"/></h3><br/>
    <#include "courseForm.ftl" />
    <div class="row">
        <div class="col-md-offset-6" style="text-align: right">
            <input type="submit" class="btn" value="Save"/> or <a
                href="<@spring.url "/admin/course/view/${course.code}" />">Cancel</a>
        </div>
    </div>
</form>
