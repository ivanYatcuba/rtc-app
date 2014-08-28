<form name="user" id="user" action="<@spring.url '/admin/user/update/${user.code}'/>" method="post">
<h3 class="page-header"><@spring.message "user.createUser"/></h3>
<@spring.formHiddenInput "user.registerDate" />
<#include "createUser.ftl"/>
    <div class="row">
        <div class="col-md-6">
        </div>
        <div class="col-md-5" style="text-align: right">
            <br>
            <input type="submit" class="btn" value="Save"/> or
            <a href="<@spring.url "/admin/user/userPage/${user.code}" />">Cancel</a>
        </div>
    </div>
</form>
