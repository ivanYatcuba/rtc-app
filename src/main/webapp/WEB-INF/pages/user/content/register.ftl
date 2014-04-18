<form name="user" id="user" action="<@spring.url "/user/save" />" method="post">
    <h3 class="page-header"><@spring.message "user.title"/></h3>
<#include "userForm.ftl" />

    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6">
        </div>

        <div class="span2" style="text-align: right">

            <input type="submit" class="btn" value="Register"/> or <a
                href="<@spring.url "/" />">Cancel</a>

        </div>
    </div>
</form>