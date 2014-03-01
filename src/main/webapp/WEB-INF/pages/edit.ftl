<html>
<#import "/spring.ftl" as spring/>
<#include "macro.ftl" >
<head>

    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

    <link href="runnable.css" rel="stylesheet" />

    <!-- Load jQuery and the validate plugin -->
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

</head>
<title> Edit page </title>
<!--<body style= " background-image : url(back.jpeg)"  > -->

<body>
<form name="goHome" action="goHome" method="get" >
    <div style="margin-left:560px">
        <button class="btn btn-warning pull-right" type="submit">Home page</button>
    </div>
    </form>
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

    <form name="user" action="viewPage" method="post" id="register-form" novalidate="novalidate">

        <div class="label"><@spring.message "reg.firstName"/></div><br><input type="text" id="firstname" name="firstname" value="${user.firstname}"/><br />

        <div class="label"><@spring.message "reg.lastName"/></div><br><input type="text" id="lastname" name="lastname" value="${user.lastname}"/><br />

        <div class="label"><@spring.message "reg.birthDate"/></div><br><input type="text" id="birthYear" name="birthYear" value="${user.birthYear}" /><br />

        <div class="label"><@spring.message "reg.email"/></div><br><input type="text" id="email" name="email" value="${user.email}"/><br />

        <div style="margin-left:140px;"><input class="btn btn-warning" type="submit" value="Save" /></div>
    </form>
<#include "down.ftl">
</body>
</html>
