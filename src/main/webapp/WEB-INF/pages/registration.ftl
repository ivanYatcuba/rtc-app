<html>
<#include "macro.ftl">
<#import "/spring.ftl" as spring/>
<head>


    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

<!-- add Bootstrap-->
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap-combined.no-icons.min.css'/>" rel="stylesheet"/>
    <script src="/<@spring.url'/resources/css/Bootstrap/js/bootstrap.min.js'/>"></script>


    <link href="runnable.css" rel="stylesheet" />
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="<@spring.url'/resources/css/style.css'/>" rel="stylesheet" type="text/css" />
</head>

<title><@spring.message "register"/></title>

<body>
<div id="content">
 

<h1><@spring.message "register"/></h1><br>

    <form name="goHome" action="goHome" method="get" >
        <div style="margin-left:560px">
            <button class="btn btn-warning pull-right" type="submit"><@spring.message "btn.home"/></button>
        </div>
        </form>
<!--  The form that will be parsed by jQuery before submit  -->
<form name="userForm" action="viewPage" method="post" id="register-form" novalidate="novalidate">

    <div class="label"><@spring.message "reg.firstName"/></div><br>
<input type="text" id="firstname" name="firstname" /><br />

    <div class="label"><@spring.message "reg.lastName"/></div><input type="text" id="lastname" name="lastname" /><br />

    <div class="label"><@spring.message "reg.birthDate"/></div><input type="text" id="birthYear" name="birthYear" /><br />

    <div class="label"><@spring.message "reg.email"/></div><input type="text" id="email" name="email" /><br />

    <div class="label"><@spring.message "reg.password"/></div><input type="password" id="password" name="password" /><br />
  
    <div style="margin-left:140px;"><input class ="btn btn-warning" type="submit" name="submit" value=<@spring.message "reg.register"/> /></div>
</form>
<#include "down.ftl">
</body>
</html>
