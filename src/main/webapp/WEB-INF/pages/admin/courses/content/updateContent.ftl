<div class="container">
<form name="course" id="course" action="<@spring.url "/admin/courses/update" />" method="post">

    <@spring.formHiddenInput "course.code" />
        <h3><@spring.message "update.message"/></h3>
        <hr width="100%">
        <div class="panel panel-default">
            <div class="panel-body">

            <#include "courseForm.ftl" />

                <!--Update & Cancel-->
                <div class="row-fluid span12">
                        <div class="span5">
                        </div>

                        <div class="span6" style="text-align: right">

                            <input type="submit" class="btn" value="Save"/> or <a
                                href="<@spring.url "/admin/courses/${course.code}" />">Cancel</a>

                        </div>
                </div>
            </div>
        </div>

</form>
</div>