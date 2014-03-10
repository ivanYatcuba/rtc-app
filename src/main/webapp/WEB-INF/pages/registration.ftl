<html>
<#include "macro.ftl">
<#import "/spring.ftl" as spring/>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

     <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap-combined.no-icons.min.css'/>" rel="stylesheet"/>
    <script src="/<@spring.url'/resources/css/Bootstrap/js/bootstrap.min.js'/>"></script>

    <link href="runnable.css" rel="stylesheet" />

    <!-- Load jQuery and the validate plugin -->
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="<@spring.url'/resources/css/js/JavaScript.js'/>"> </script>
  <link href="<@spring.url'/resources/css/style.css'/>" rel="stylesheet" type="text/css" />

<link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
  <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap-responsive.css'/>" rel="stylesheet">

  

  <script src="<@spring.url'/resources/css/js/jquery.js'/>"></script>  
 <script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap-transition.js'/>"></script>
  <script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap-modal.js'/>"></script>
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

    <div class="label"><@spring.message "reg.lastName"/></div> <br>
    <input type="text" id="lastname" name="lastname" /><br />

    <div class="label"><@spring.message "reg.birthDate"/></div><br>
    <input type="text" id="birthYear" name="birthYear" /><br />

    <div class="label"><@spring.message "reg.email"/></div><br>
    <input type="text" id="email" name="email" /><br />

    <div class="label"><@spring.message "reg.password"/></div><br>
    <input type="password" id="password" name="password" /><br />
  
    <div style="margin-left:140px;"><input class ="btn btn-warning" type="submit" name="submit" value=<@spring.message "reg.register"/> /></div>
</form>

</body>
</html>
