<h2><@spring.message "user.title"/></h2>
<form class="form-horizontal" name="user" id="user" action="<@spring.url '/admin/user/update/${user.id}'/>" method="post">
<#include "createUser.ftl"/>
    <div class="row-fluid">
        <div class="span6">
        </div>
        <div class="span6" style="text-align: right">
            <br>
            <input type="submit" class="btn" value="Update"/> or
            <a href="<@spring.url "/admin/user/userPage/${user.id}" />">Cancel</a>
        </div>
    </div>
</form>