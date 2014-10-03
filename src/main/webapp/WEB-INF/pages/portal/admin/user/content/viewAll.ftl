<h4><@spring.message "user.search.result.page.header.criteria"/></h4>
<form class="form-horizontal" name="userFilter" role="form">
      <#--action="<@spring.url "/admin/user/filter"/>" method="get">-->
<div class="row">
    <div class="col-md-6">
    </div>
    <div class="col-md-6">
    </div>
</div>
</form>

<div class="row">
    <div class="col-md-6">
    </div>
    <div class="col-md-6" style="text-align: right">
        <input type="submit" class="btn btn-primary" style="width: 90px" value="Search"/>
        <#--<a href="<@spring.url "/admin/course" />">Reset</a>-->
        <input type="button" class="btn btn-default" style="width: 90px" onClick="<@spring.url "/admin/course" />" value='Reset'>
    </div>
</div>
<hr style="height: 6px; border-top: 2px solid #ddd;">


<h4><@spring.message "user.search.result.page.header.search"/></h4>
<table width="100%" class="table" style="margin-bottom: 5px" id="UserTable">
        <thead>
        <tr>
            <th><@spring.message "user.search.result.header.user"/></th>
            <th><@spring.message "user.search.result.header.email"/></th>
            <th><@spring.message "user.search.result.header.created.date"/></th>
            <th><@spring.message "user.search.result.header.status"/></th>
            <th></th>
        </tr>
        </thead>

<#list users as user>
    <tr style="vertical-align: middle">
        <#if (user.name) ?? && (user.surname) ?? >
            <td style="width: 30%; vertical-align: middle">
                <div class="row">
                   <div class="col-md-2">
                       <img src = "<@spring.url'/resources/images/errorCat.jpg'/>" alt="..." class="avatar">
                   </div>
                   <div class="col-md-10">
                       <a href="<@spring.url"/admin/user/userPage/${user.code}"/>">  ${user.surname + " " + user.name } </a>
                       <br>User
                    </div>
                </div>
            </td>
        <#else>
            <td style="vertical-align: middle">None</td>
        </#if>

        <#if (user.email)??>
        <div class="row">
           <div class="col-md-6">
                <td style="vertical-align: middle">${user.email}</td>
           </div>
        </div>
        <#else>
            <td style="vertical-align: middle">None</td>
        </#if>

        <#if (user.registerDate)??>
            <td style="vertical-align: middle">${user.registerDate?datetime?string("dd-MM-yyyy")}</td>
        <#else>
            <td style="vertical-align: middle">None</td>
        </#if>

        <#if (user.status)??>
            <td style="vertical-align: middle">
                <#if (user.status)=="ACTIVE">
                    <span class="label label-success" style="width: 90px; height: 34px">Active</span>
                <#else>
                    <span class="label label-danger" style="width: 90px; height: 34px">Deleted</span>
                </#if>
            </td>
        <#else>
            <td style="vertical-align: middle">
                <span class="label label-default" style="width: 90px; height: 34px">None</span>
            </td>
        </#if>
        <td style="width: 15%; vertical-align: middle">
            <#if user.isForRemoval() >
                <form style="margin: 0 0 0" name="deleteUser" action="<@spring.url"/admin/user/restore/"/>" method="post">
                    <input type="hidden" name="userCode" value="${user.code}"/>
                    <button class="btn btn-default" style="width: 112px " type="submit">Restore</button>
                </form>
            <#else>
                <div class="btn-group">
                    <button type="button" class="btn btn-default">Inactivate</button>
                    <button type="button" class="btn btn-default dropdown-toggle" style="height: 34px" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#" onclick="javascript:PopUpShow('${user.code}')">Remove</a></li>
                     </ul>
                </div>
                <#--<button class="btn btn-default" onclick="javascript:PopUpShow('${user.code}')">Remove</button>-->
            </#if>
                <#--<div class="btn-group">-->
                    <#--<button type="button" class="btn btn-default">Action</button>-->
                    <#--<button type="button" class="btn btn-default dropdown-toggle dropdown-button" data-toggle="dropdown">-->
                        <#--<span class="caret"></span>-->
                        <#--<span class="sr-only">Toggle Dropdown</span>-->
                    <#--</button>-->
                    <#--<ul class="dropdown-menu" role="menu">-->
                        <#--<li><a href="#">Action</a></li>-->
                        <#--<li><a href="#">Another action</a></li>-->
                        <#--<li><a href="#">Something else here</a></li>-->
                        <#--<li class="divider"></li>-->
                        <#--<li><a href="#">Separated link</a></li>-->
                    <#--</ul>-->
                <#--</div>-->
        </td>
    </tr>
</#list>
</table>
<hr style="height: 1px; margin-top: 5px; border-top: 1px solid #ddd;">
<div class="row">
    <div class="col-md-6">
    <form  class="inline-box" style="margin: 0px"  name="createUser" action="<@spring.url"/admin/user/createUser"/>"method="get">
        <button  class="btn btn-primary" type="submit">Create New</button>
    </form>
    </div>
    <div class="col-md-6" style="text-align: right">
        <@spring.addPagination "/admin/user/viewAll"/>
    </div>
</div>

<!-- Modal -->
<div class="modal" style="top: 15%; left: 1%" id="removeUserModal" tabindex="-1" role="dialog" aria-labelledby="removeUserModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="removeUserModalLabel">Remove User</h4>
            </div>
            <div class="modal-body">
                Are you sure you want to remove User?
            </div>
            <div class="modal-footer">
                <form name="deleteUser" action="<@spring.url"/admin/user/remove/"/>" method="post">
                    <input type="hidden" id="userCode" name="userCode"/>
                    <button type="button" class="btn btn-default"  data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary" onClick="javascript:PopUpHide()">Remove</button>
                </form>
            </div>
        </div>
    </div>
</div>




<script type="text/javascript">
    function PopUpShow(userCode) {
        $("#userCode").val(userCode);
        $('#removeUserModal').modal('show')
    }
    function PopUpHide() {
        $('#removeUserModal').modal('hide');
    }
</script>
