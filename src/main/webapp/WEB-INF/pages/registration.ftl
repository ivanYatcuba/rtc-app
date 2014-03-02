<html>
<#include "macro.ftl">
<#import "/spring.ftl" as spring/>
<head>

    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

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
        #homeBtn{

        }
        #content
        {
            background-color: rgba(255,255,255,0.7);
            margin-left: 40px;
            margin-right: 40px;
            margin-bottom: 5%;
            margin-top: 5%;
            height: 90%;
            width: auto;
        }
        #reg{
            text-align: center;
        }
    </style>
<h1 id="reg"><@spring.message "page.register"/></h1>
    <form name="goHome" action="goHome" method="get" >
        <div id="homeBtn">
            <button class="btn btn-primary" type="submit"><-</button>
        </div>
    </form>
<!--  The form that will be parsed by jQuery before submit  -->
<form name="userForm" action="viewPage" method="post" id="register-form" novalidate="novalidate">

    <div class="label"><@spring.message "reg.firstName"/></div> <input thype="text" id="firstname" name="firstname" /><br />

    <div class="label"><@spring.message "reg.lastName"/></div><input type="text" id="lastname" name="lastname" /><br />

    <div class="label"><@spring.message "reg.birthDate"/></div><input type="text" id="birthYear" name="birthYear" /><br />

    <div class="label"><@spring.message "reg.email"/></div><input type="text" id="email" name="email" /><br />

    <div class="label"><@spring.message "reg.password"/></div><input type="password" id="password" name="password" /><br />
  
    <div style="margin-left:140px;"><input class ="btn btn-warning" type="submit" name="submit" value=<@spring.message "reg.register"/> /></div>
</form>
<#include "down.ftl">
</body>
</html>
