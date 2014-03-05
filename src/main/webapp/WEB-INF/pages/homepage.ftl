<html>
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
</head>
<title> Home Page </title>

<body>
<div id="content">
 <#include "header.ftl">

  <center> <h2> <@spring.message "homepage.mid"/> </h2></center>
<div>
    <center>
        <table>
            <tr>
                <td>
                    <center>
                        <a href="" class="rollover" > 
                        </a>
                    </center>
                </td>
                <td>
                        <a href="" class="rollover1">
                        </a>
                </td>
                <td>
                        <a href="" class="rollover2">
                        </a>
                </td>
           </tr>
        </table>
    </center>
</div>
<center>
<h1>

    <div>
        <table>
            <tr>
<form action="start input" method="get">
  <button class="btn btn-warning"> <@spring.message "page.enter"/> </button>
</form>
    |
<form action="registration" method="get" >
   <button class="btn btn-warning"> <@spring.message "page.register"/> </button>
</form>
            </tr>
        </table>
    </div>
</h1>
</center>

<#include "down.ftl">
</div>
</body>
</html>
