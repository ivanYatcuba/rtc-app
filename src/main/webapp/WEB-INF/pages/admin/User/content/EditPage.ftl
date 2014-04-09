<h2><@spring.message "user.title"/></h2>
<form class="form-horizontal" name="user" id="user" action="<@spring.url '/admin/user/update/4'/>" method="post">
<#include "CreateUser.ftl"/>
    <div class="row-fluid">
        <div class="span6">
        </div>
        <div class="span6" style="text-align: right">
            <br>
            <input type="submit" class="btn" value="Update"/> or
            <a href="<@spring.url "/admin/user/" />">Cancel</a>
        </div>
    </div>
</form>