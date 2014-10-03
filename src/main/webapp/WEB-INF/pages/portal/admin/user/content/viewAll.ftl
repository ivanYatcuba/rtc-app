<h4><@spring.message "user.search.result.page.header.criteria"/></h4>
<form class="form-horizontal" name="userFilter" role="form">
      <#--action="<@spring.url "/admin/user/filter"/>" method="get">-->
<div class="row">
    <div class="col-md-6">
        <#--<@spring.formItem "filterCourse.name"/>-->
        <#--<@spring.bind "filterCourse.name"/>-->
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
            <td style="vertical-align: middle">
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
            <td style="vertical-align: middle">${user.email}</td>
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
                    <span class="label label-success">Active</span>
                <#else>
                    <span class="label label-danger">Deleted</span>
                </#if>
            </td>
        <#else>
            <td style="vertical-align: middle">
                <span class="label label-default">None</span>
            </td>
        </#if>
        <td style="vertical-align: middle">
            <#if user.isForRemoval() >
                <form style="margin: 0 0 0" name="deleteUser" action="<@spring.url"/admin/user/restore/"/>" method="post">
                    <input type="hidden" name="userCode" value="${user.code}"/>
                    <button class="btn btn-default" type="submit">Restore</button>
                </form>
            <#else>
                <button class="btn btn-default" onclick="javascript:PopUpShow('${user.code}')">Remove</button>
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

<div class="popup " id="window-popup" style="display: none">
    <div class="popup-content">
        <center>
            <h2>Remove</h2>
        </center>
        <center>
            <lable>Do you realy want to remove specified user?</lable>
        </center>
        <br>
        <center>
            <form name="deleteUser" action="<@spring.url"/admin/user/remove/"/>" method="post">
                <input type="hidden" id="userCode" name="userCode"/>
                <button class="btn" type="submit">Ok</button>
                <button class="btn" type="button" onClick="javascript:PopUpHide()">Cancel</button>
            </form>
        </center>
    </div>
</div>

<br><br><br>



<script type="text/javascript">
//	$(document).ready(function () {
//		PopUpHide();
//	});
    function PopUpShow(userCode) {
        $("#userCode").val(userCode);
        $("#window-popup").show();
    }
    function PopUpHide() {
        $("#window-popup").hide();
    }
</script>
