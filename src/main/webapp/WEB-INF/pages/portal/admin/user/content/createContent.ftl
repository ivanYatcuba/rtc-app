<form  name="user" id="user" action="<@spring.url '/admin/user/save'/>" method="post">
    <h3 class="page-header"><@spring.message "user.createUser"/></h3>
    <#include "createUser.ftl" />
    <!--Create & Cancel-->
    <div class="row">
        <div class="col-md-6">
        </div>
        <div class="col-md-5" style="text-align: right">
            <input type="submit" class="btn" value="Create"/> or
            <a href="<@spring.url "/admin/user/viewAll" />">Cancel</a>
        </div>
    </div>
</form>