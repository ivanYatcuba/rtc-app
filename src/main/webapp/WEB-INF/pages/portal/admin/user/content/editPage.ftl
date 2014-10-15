<form class="form-horizontal" name="user" id="user"
      action="<@spring.url '/admin/user/update/${user.code}'/>" method="post" enctype="multipart/form-data">
    <h3 class="page-header"><@spring.message "user.editUser"/></h3><br>
<@spring.formHiddenInput "user.registerDate" />
<#include "createUser.ftl"/>
    <div class="row">
        <div class="col-md-6">
        </div>
        <div class="col-md-4" style="text-align: right">
            <br>
            <input type="submit" class="btn" value="Save"/> or
            <a href="<@spring.url "/admin/user/userPage/${user.code}" />">Cancel</a>
        </div>
    </div>
</form>
