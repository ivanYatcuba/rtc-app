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
                <a href="<@spring.url"/admin/user/userPage/${user.code}"/>">  ${user.name + " " + user.surname} </a></td>
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

        <#if (user.regDate)??>
            <td>${user.regDate}</td>
        <#else>
            <td>None</td>
        </#if>

        <#if (user.role)??>
            <td>${user.role}</td>
        <#else>
            <td>None</td>
        </#if>

        <td><a href="<@spring.url "/admin/user/delete/${user.code}" />">Remove</a></td>

    </tr>
</#list>
</table>

<br><br>
<div align = "right">
    <form name="createUser" action="createUser" method="get">
    <button type="submit">Create</button>
</div>

