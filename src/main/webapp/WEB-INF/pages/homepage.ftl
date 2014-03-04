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
  <#--  <textarea readonly rows="10" cols="5">
        Днепропетро́вск (укр. Дніпропетро́вськ); первоначальное название Екатеринослав (1776—1797 гг.; 1802—1926 гг.; в 1797—1802 годах
        назывался Новороссийском); современное имя состоит из названия реки, на которой стоит город, и фамилии советского партийного и государственного
        деятеля.Днепропетровск — город, областной центр Днепропетровской области Украины, центр Днепропетровской агломерации.
        Четвёртый город по численности населения на Украине после Киева, Харькова и Одессы.
        Днепропетровск — один из крупнейших промышленных, экономических и транспортных центров, центр металлургии и космическая столица Украины.
        Особенно развита чёрная металлургия, металлообработка, машиностроение и другие тяжёлые отрасли промышленности.
        На 1 июля 2013 года по данным облстата наличное население составило 1 000 111 человек. Код КОАТУУ — 1210100000.
    </textarea>-->
    <br>

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
