<html>
<#include "macro.ftl">
<#import "/spring.ftl" as spring/>
<head>

    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

    <link href="runnable.css" rel="stylesheet" />

    <!-- Load jQuery and the validate plugin -->
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

    <!-- jQuery Form Validation code -->
   <#-- <script>
        <@validate form = "#register-form"
                    JSON = " rules: {
            firstname: \"required\",
            lastname: \"required\",
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 5
            },
            agree: \"required\"
        },

        messages: {
            firstname: \"Please enter your first name\",
            lastname: \"Please enter your last name\",
            password: {
                required: \"Please provide a password\",
                minlength: \"Your password must be at least 5 characters long\"
            },
            email: \"Please enter a valid email address\",
            agree: \"Please accept our policy\"
        },"
        />
    </script>-->
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
<h1><@spring.message "register"/></h1>

<!--  The form that will be parsed by jQuery before submit  -->
<form action="viewPage" method="post" id="register-form" novalidate="novalidate">

    <div class="label">First Name</div><input type="text" id="firstname" name="firstname" /><br />

    <div class="label">Last Name</div><input type="text" id="lastname" name="lastname" /><br />

    <div class="label">Email</div><input type="text" id="email" name="email" /><br />

    <div class="label">Password</div><input type="password" id="password" name="password" /><br />

    <div style="margin-left:140px;"><input type="submit" name="submit" value="Submit" /></div>
</form>
<#include "down.ftl">
</body>
</html>
