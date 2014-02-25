<html>
<#import "/spring.ftl" as spring/>
<head>
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
 <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <link href="runnable.css" rel="stylesheet" />
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">



</head>
<title> Profile View </title>

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


<div class="Profile view">

    <center><h1> <@spring.message "welcomeView"/> </h1> </center>
    <h2><label> First name: </label>${userForm.firstname} <br></h2>
    <h2><label> Last name:</label> ${userForm.lastname} <br></h2>
    <h2><label> Birth Year: </label> ${userForm.birthYear} <br></h2>
    <h2><label> E-mail: </label> ${userForm.email} <br></h2>



</div>
<form name="user" action="edit" method="post" id="view-form" novalidate=" novalidate">
    <button class="btn btn-primary"> Edit Page </button><br>
</form>
</div>
<#include "down.ftl">
</body>
</html>
