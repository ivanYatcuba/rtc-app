<html>
<#import "/spring.ftl" as spring/>
<head>
<title>Login page</title>
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
 <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

    <link href="runnable.css" rel="stylesheet" />
</head>
<body>
<center>
<h1><@spring.message "enter"> </h1>

<form name="Logins" action="input" method="post">

<form name="Logins" action="input"  method="post">

 <div class="label">Login</div> <br>
<INPUT type="text" name="login" maxlenght="35" SIZE="20" > <br>
 <div class="label">Password</div><br>
<INPUT type="PASSWORD" name="password" maxlength="35" size="20"> <br>
<INPUT type="submit" value=" In ">

</FORM>
<form action="registration" enctype="text/plain" method="post" >
   
    <input type="submit" value="registration"/>

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
