<html>
<#import "/spring.ftl" as spring/>

<head>
<title><@spring.message "title.in"/></title>
<link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap-combined.no-icons.min.css'/>" rel="stylesheet"/>
    <script src="/<@spring.url'/resources/css/Bootstrap/js/bootstrap.min.js'/>"></script>

  <link href="runnable.css" rel="stylesheet" />
<link href="<@spring.url'/resources/css/style.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="content">
    
<center>
<h1><@spring.message "enter"/> </h1>
</center>
<form name="goHome" action="goHome" method="get" >
<div style="margin-left:560px"> 
    <button class="btn btn-warning pull-right" type="submit"><@spring.message "btn.home"/></button>
 </div>
</form>
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
    <input type="checkbox">Запомнить меня</label>
    <br>
 <a href="#">Забыли пароль?</a>
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
<#include "down.ftl">
</body>
</html>
