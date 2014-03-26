<form name="course" id="course" action="<@spring.url "/admin/courses/update" />" method="post">

    <@spring.formHiddenInput "course.code" />
    <h3 class="page-header"><@spring.message "update.message"/></h3>

            <#include "courseForm.ftl" />

                <!--Save & Cancel-->
                <div class="row-fluid span12">
                        <div class="span6">
                        </div>

                        <div class="span5" style="text-align: right">

                            <input type="submit" class="btn" value="Save"/> or <a
                                href="<@spring.url "/admin/courses/${course.code}" />">Cancel</a>

                        </div>
                </div>
</form>
