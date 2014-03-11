<html>
<#import "/spring.ftl" as spring/>
<#include "macro.ftl" >
<head>
<#include "link.ftl">
</head>
<title> <@spring.message "editPage"/> </title>


<body>

<div id="content">
    
    <h1> <@spring.message "editPage"/> </h1><br>

    <form name="goHome" action="goHome" method="get" >
        <div style="margin-left:560px">
            <button class="btn btn-warning pull-right" type="submit"><@spring.message "btn.home"/></button>
        </div>
    </form>

    <form name="user" action="viewPage" method="post" id="register-form" novalidate="novalidate">

        <div class="label"><@spring.message "reg.firstName"/></div><br>
<#if !(userForm.firstname)??>
<input type="text" id="firstname" name="firstname" value=""/><br />
<#else>
<input type="text" id="firstname" name="firstname" value="${user.firstname}"/><br />
</#if>
        <div class="label"><@spring.message "reg.lastName"/></div><br>
<#if !(user.lastname)??>
<input type="text" id="lastname" name="lastname" value=""/><br />
<#else>
<input type="text" id="lastname" name="lastname" value="${user.lastname}"/><br />
</#if>
        <div class="label"><@spring.message "reg.birthDate"/></div><br>
<#if !(user.birthYear)??>
<input type="text" id="birthYear" name="birthYear" value="" /><br />
<#else>
<input type="text" id="birthYear" name="birthYear" value="${user.birthYear}" /><br />
</#if>

        <div class="label"><@spring.message "reg.email"/></div><br>
<#if !(user.email)??>
<input type="text" id="email" name="email" value=""/><br />
<#else>
<input type="text" id="email" name="email" value="${user.email}"/><br />
</#if>
        <div style="margin-left:140px;"><input class="btn btn-warning" type="submit" value="Save" /></div>
    </form>
<#include "down.ftl">
</body>
</html>
