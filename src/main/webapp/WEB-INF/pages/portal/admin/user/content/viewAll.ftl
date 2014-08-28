<h3 class="page-header"><@spring.message "user.list"/></h3>

<div class="row">
    <div class="col-md-12" align="right">
        Pages:
        <#if numberOfPage-1&gt;1>
            <a href="<@spring.url"/admin/user/viewAll/1"/>">&nbsp;&lt;&lt;</a>
            <a href="<@spring.url"/admin/user/viewAll/${numberOfPage+1}"/>">&nbsp;&lt;</a>
        </#if>
    <#list 1..(pages) as index>
        <a href="<@spring.url"/admin/user/viewAll/${index}"/>">  ${index}</a>
    </#list>
    <#if numberOfPage+1<pages?length>
        <a href="<@spring.url"/admin/user/viewAll/${numberOfPage+1}"/>">&nbsp;&gt;</a>
        <a href="<@spring.url"/admin/user/viewAll/${pages?length}"/>">&nbsp;&gt;&gt;</a>
    </#if>
    </div>
</div>
<table width="100%" class="table-bordered table">
    <tr bgcolor="#d3d3d3" style="font-weight:bold">
        <td>Name</td>
        <td>Email</td>
        <td>Phone</td>
        <td>Register Date</td>
        <td>Role</td>
        <td>Action</td>

    </tr>
<#list users as user>
    <tr>
        <#if (user.name) ?? && (user.surname) ?? >
            <td>
                <a href="<@spring.url"/admin/user/userPage/${user.code}"/>">  ${user.surname + " " + user.name } </a>
            </td>
        <#else>
            <td>None</td>
        </#if>

        <#if (user.email)??>
            <td>${user.email}</td>
        <#else>
            <td>None</td>
        </#if>

        <#if (user.phone)??>
            <td>${user.phone}</td>
        <#else>
            <td>None</td>
        </#if>

        <#if (user.registerDate)??>
            <td>${user.registerDate?datetime?string("dd-MM-yyyy")}</td>
        <#else>
            <td>None</td>
        </#if>

        <td>
            <#list user.authorities as role>
            ${role}<#if role_has_next>,</#if>
            </#list>
        </td>

        <td>
            <button class="btn" onclick="javascript:PopUpShow('${user.code}')">Remove</button>
        </td>
    </tr>
</#list>
</table>

<div class="popup" id="window-popup">
    <div class="popup-content">
        <center>
            <h2>Remove</h2>
        </center>
        <center>
            <lable>Do you realy want to remove specified user?</lable>
        </center>
        <br>
        <center>
            <form name="deleteUser" action="<@spring.url"/admin/user/delete/"/>" method="post">
                <input type="hidden" id="userCode" name="userCode"/>
                <button class="btn" type="submit">Ok</button>
                <button class="btn" type="button" onClick="javascript:PopUpHide()">Cancel</button>
            </form>
        </center>
    </div>
</div>


<br>


<br><br>
<div align="right">
    <form name="createUser" action="<@spring.url"/admin/user/createUser"/>" method="get">
        <button class="btn" type="submit">Create New</button>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        PopUpHide();
    });
    function PopUpShow(userCode) {
        $("#userCode").val(userCode);
        $("#window-popup").show();
    }
    function PopUpHide() {
        $("#window-popup").hide();
    }
</script>



