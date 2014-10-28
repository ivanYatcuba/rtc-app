<#import "../../../../fieldMacro.ftl" as formMacro />
<form class="form-horizontal" name="course" id="courseForm"
      action="<@spring.url "/admin/course/update" />" method="POST">
<@spring.formHiddenInput "course.id" />
<@spring.formHiddenInput "course.code" />
<@spring.formHiddenInput "course.status" />
<@spring.formHiddenInput "course.publishDate" />
    <h4 class="page-header"><@spring.message "update.message"/></h4><br/>
<#include "courseForm.ftl" />
    <div class="row">
        <div class="col-md-6">
        </div>
        <div class="col-md-5" style="text-align: right">
            <input type="submit" class="btn btn-primary" value="Save"/>
            <input type="button" onclick="window.location.href = '/admin/course/view/${course.code}';" class="btn btn-default" value="Cancel"/>
        </div>

        <#--<div class="col-md-offset-6" style="text-align: right">-->
            <#--<input type="submit" class="btn" value="Save"/> or <a-->
                <#--href="<@spring.url "/admin/course/view/${course.code}" />">Cancel</a>-->
    </div>
</form>
