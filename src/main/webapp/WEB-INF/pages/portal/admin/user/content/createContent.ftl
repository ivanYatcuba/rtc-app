<form class="form-horizontal" id="user" name="user"
      action="<@spring.url '/admin/user/save'/>" method="post" enctype="multipart/form-data">
    <h3 class="page-header"><@spring.message "user.createUser"/></h3><br/>
<#include "createUser.ftl" />
    <div class="row">
        <div class="col-md-6">

        </div>
        <div class="col-md-5" style="text-align: right">
            <input type="submit" class="btn" value="Create"/> or
            <a href="<@spring.url "/admin/user/viewAll" />">Cancel</a>
        </div>
    </div>
</form>

