<div class="container">
<form name="course" id="course" action="<@spring.url "/admin/courses/save" />" method="post">
        <h3><@spring.message "create.message"/></h3>
        <hr width="100%">
        <div class="panel panel-default">
            <div class="panel-body">

            <#include "courseForm.ftl" />

                <!--Create & Cancel-->
                <div class="row-fluid span12">
                        <div class="span6">
                        </div>

                        <div class="span5" style="text-align: right">

                            <input type="submit" class="btn" value="Create"/> or <a
                                href="<@spring.url "/admin/courses" />">Cancel</a>

                        </div>
                </div>
            </div>
        </div>
</form>
</div>