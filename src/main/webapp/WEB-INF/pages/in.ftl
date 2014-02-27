<html>
<#import "/spring.ftl" as spring/>

<head>
<title>Login page</title>
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
 <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
  <link href="runnable.css" rel="stylesheet" />

</head>
<body>
<div id="content">
    <style>
        body
        {
            background-image:url(" <@spring.url '/resources/images/back.jpeg'/> ");
            background-repeat: no-repeat;
            background-attachment:fixed;
            background-position:center;
            background-size: cover;
        }
        #content
        {
            background-color: rgba(255,255,255,0.7);
        }
    </style>
<center>
<h1><@spring.message "enter"/> </h1>
</center>
<form name="goHome" action="goHome" method="get" >
<div style="margin-left:560px"> 
    <button class="btn btn-primary" type="submit"><-</button>
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
