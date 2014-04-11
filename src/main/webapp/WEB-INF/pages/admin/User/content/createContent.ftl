<h2><@spring.message "user.createUser"/></h2>
    <form class="form-horizontal" name="user" id="user" action="<@spring.url '/admin/user/save'/>" method="post">
        <br>
            <#include "createUser.ftl" />
     <!--Create & Cancel-->
                <div class="row-fluid">
                        <div class="span6">
                        </div>
                        <div class="span6" style="text-align: right">
                        <br>
                            <input type="submit" class="btn" value="Create"/> or
                            <a href="<@spring.url "/admin/user/viewAll" />">Cancel</a>
                        </div>
                </div>
    </form>