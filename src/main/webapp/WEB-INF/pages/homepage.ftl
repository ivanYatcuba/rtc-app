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

<link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">
  <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap-responsive.css'/>" rel="stylesheet">

  

  <script src="<@spring.url'/resources/css/js/jquery.js'/>"></script>  
 <script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap-transition.js'/>"></script>
  <script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap-modal.js'/>"></script>

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
<br>
    <div>
        <table>
            <tr>
            <td>
            <div align = "right">
<a data-toggle="modal" data-target="#mayoOknoIn" class="btn btn-primary">Вход</a>
</div>
</td>
<td>|
<a data-toggle="modal" data-target="#mayoOknoRegister" class="btn btn-primary"> <@spring.message "page.register"/></a>
        </td>
            </tr>
        </table>
    </div>

<#include "down.ftl">
</div>

<div class="container">


<div id="mayoOknoIn" class="modal hide fade" tabindex="-1" role="dialog">
  <div class="modal-header">

    <button type="button" class="close" data-dismiss="modal">X</button>

    <h1><@spring.message "enter"/> </h1>
  </div>

  <div class="modal-body">
<center>
    <#include "in.ftl">
</center>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal">Закрыть</button>
      </div>
</div>


<div class="container">

<div id="mayoOknoRegister" class="modal hide fade" tabindex="-1" role="dialog">
  <div class="modal-header">

    <button type="button" class="close" data-dismiss="modal">X</button>

    <h1><@spring.message "register"/></h1>
  </div>

  <div class="modal-body">
<center>
    <#include "registration.ftl">
</center>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal">Закрыть</button>
      </div>
</div>

</body>
</html>
