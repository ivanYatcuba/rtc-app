<table width="100%" border="1px">
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
                <a href="<@spring.url"/admin/user/userPage/${user.code}"/>">  ${user.surname + " " + user.name } </a></td>
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

        <td><a href="<@spring.url "/admin/user/delete/${user.code}" />">Remove</a></td>

    </tr>
</#list>
</table>

<br><br>
<div align = "right">
    <form name="createUser" action="createUser" method="get">
    <button type="submit">Create</button>
</div>

