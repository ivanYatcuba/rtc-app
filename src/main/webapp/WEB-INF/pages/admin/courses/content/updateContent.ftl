<form name="course" id="course" action="<@spring.url "/admin/courses/update" />" method="post">

    <div class="container" style="margin-right: 5px;">
    <@spring.formHiddenInput "course.code" />
        <h3><@spring.message "update.message"/></h3>
        <hr width="100%">
        <div class="panel panel-default">
            <div class="panel-body">
            <#include "courseForm.ftl" />

                <!--Update & Cancel-->
                <div class="row-fluid span12">
                    <div class="container" style="padding-bottom: 10px;">
                        <div class="span5">
                        </div>

                        <div class="span6" style="text-align: right">

                            <input type="submit" class="btn" value="Update"/> or <a
                                href="<@spring.url "/admin/courses/${course.code}" />">Cancel</a>

                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</form>