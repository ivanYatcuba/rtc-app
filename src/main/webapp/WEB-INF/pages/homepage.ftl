<html>

<head>

    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>

    <link href="runnable.css" rel="stylesheet" />

    <!-- Load jQuery and the validate plugin -->
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>

    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">

</head>
<title> Home page </title>
<!--<body style= " background-image : url(back.jpeg)"  > -->

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
<#include "header.ftl">    
<form action="start input" method="get">

  <center>  <button class="btn btn-primary"> Enter Page </button></center>

</form>

<form action="registration" method="post" >

  <center>  <button class="btn btn-primary"> Register Page </button></center>

</form>
<#include "down.ftl">
</body>
</html>
