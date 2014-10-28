<#import "../../../../fieldMacro.ftl" as formMacro />
<form class="form-horizontal" id="courseForm" name="course"
      action="<@spring.url "/admin/course/save" />" method="POST">
    <h4 class="page-header"><@spring.message "create.message"/></h4><br/>
<#include "courseForm.ftl" />
    <div class="row">
        <div class="col-md-6">
        </div>
        <div class="col-md-5" style="text-align: right">
            <input type="submit" class="btn btn-primary" value="Create"/>
            <!--<a href="<@spring.url "/admin/course" />">Cancel</a>-->
            <input type="button" onclick="window.location.href = '/admin/course';" class="btn btn-default" value="Cancel"/>
            <#--<@formMacro.rtcSubmit "Create" "Cancel" "/admin/user/viewAll"/>-->
        </div>
    </div>
</form>

