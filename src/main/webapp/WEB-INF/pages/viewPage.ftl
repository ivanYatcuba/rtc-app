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

    <form name="goHome" action="goHome" method="get" >
        <div style="margin-left:560px">
            <button class="btn btn-warning pull-right" type="submit"><@spring.message "btn.home"/></button>
        </div>

        <br>

    <h2><label> <@spring.message "reg.firstName"/>: </label>
<#if (userForm.firstname)??>
${userForm.firstname} 
<#else>
 <br>
</#if>
</h2>
    <h2><label> <@spring.message "reg.lastName"/>:</label> 
<#if (userForm.lastname)??>
${userForm.lastname} 
<#else>
</#if>
<br></h2>

    <h2><label> <@spring.message "reg.birthDate"/>: </label>
<#if (userForm.birthYear)??>
 ${userForm.birthYear}
<#else>
</#if>
 <br></h2>
    <h2><label> <@spring.message "reg.email"/>: </label> 
<#if (userForm.email)??>
${userForm.email} 
<#else>
</#if>
<br></h2>

</div>

</form>
<form action="edit" method="post" id="view-form" >
</form>
</div>
<form name="user" action="edit" method="post" id="view-form" novalidate=" novalidate">
    <button class="btn btn-warning"> <@spring.message "page.edit"/> </button><br>
</form>
</div>
<#include "down.ftl">
</body>
</html>
