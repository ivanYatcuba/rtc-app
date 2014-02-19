<html>
<#include "macro.ftl">
<head>

    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

    <link href="runnable.css" rel="stylesheet" />

    <!-- Load jQuery and the validate plugin -->
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

    <!-- jQuery Form Validation code -->
    <script>
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
    </script>
</head>

<body>
<h1>Register here</h1>

<!--  The form that will be parsed by jQuery before submit  -->
<form action="" method="post" id="view-form" novalidate=" novalidate">


</form>

</body>
</html>