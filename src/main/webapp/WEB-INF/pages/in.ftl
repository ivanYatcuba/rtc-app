<html>
<#import "/spring.ftl" as spring/>

<head>
<#include "link.ftl">
</head>
<body>
<div id="content">

<br>
<center>
<form name="Logins" action="input"  method="post">

 <div class="label"><@spring.message "in.login"/></div> <br>
<INPUT type="text" name="login" maxlenght="35" SIZE="20" > <br>
 <div class="label"><@spring.message "in.password"/></div><br>
<INPUT type="PASSWORD" name="password" maxlength="35" size="20"> <br>
<INPUT class="btn btn-warning" type="submit" value=" <@spring.message "btn.enter"/> ">

</FORM>
<form action="registration" enctype="text/plain" method="get" >
 </form>
<form class="form-inline">
<label class="checkbox">
    <input type="checkbox"><@spring.message "password.save"/></label>
    <br>
 <a href="#"><@spring.message "password.forgot"/></a>
</form>

</center>

<#if login??>
 You'r login is ${login} <br>
</#if>
<#if password??>
You'r password id ${password}<br>
</#if>
<#if message??>
${message}
</#if>
<#if message??>
${message}
</#if>
</body>
</html>
