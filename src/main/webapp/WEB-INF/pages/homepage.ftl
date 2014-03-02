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
    <link href="<@spring.url'/resources/css/js/style.css'/>" rel="stylesheet"  type="text/css">
</head>
<title> <@spring.message "title.home"/> </title>

<body>
<div id="content">
<#include "header.ftl">

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
       
<form action="start input" method="get">

  <button class="btn btn-warning"> <@spring.message "page.enter"/> </button>
  <br>

</form>

<form action="registration" method="get" >

   <button class="btn btn-warning"> <@spring.message "page.register"/> </button>

</form>

</h1>
</center>


<#include "down.ftl">
</div>
</body>
</html>
