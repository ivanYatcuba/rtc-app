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
<style>
   table {
   
   
    width: 100%; /* Ширина таблицы */ 
    border-spacing: 7px 11px; /* Расстояние между ячейками */ 
   }
   </style>
 <style>
   a.rollover {
    background: url("<@spring.url '/resources/images/one.jpg'/>"); /* Путь к файлу с исходным рисунком  */
    display: block; /*  Рисунок как блочный элемент */
    width: 300px; /* Ширина рисунка */
    height: 225px; /*  Высота рисунка */

   }
   a.rollover:hover {
    background: url("<@spring.url '/resources/images/one2.jpg'/>"); /* Путь к файлу с заменяемым рисунком  */
   }
 
   a.rollover1 {
    background: url("<@spring.url '/resources/images/two.jpg'/>"); /* Путь к файлу с исходным рисунком  */
    display: block; /*  Рисунок как блочный элемент */
    width: 300px; /* Ширина рисунка */
    height: 225px; /*  Высота рисунка */
   }
   a.rollover1:hover {
    background: url("<@spring.url '/resources/images/two2.jpg'/>"); /* Путь к файлу с заменяемым рисунком  */
   }
  
   a.rollover2 {
    background: url("<@spring.url '/resources/images/three.jpg'/>"); /* Путь к файлу с исходным рисунком  */
    display: block; /*  Рисунок как блочный элемент */
    width: 300px; /* Ширина рисунка */
    height: 225px; /*  Высота рисунка */
   }
   a.rollover2:hover {
    background: url("<@spring.url '/resources/images/three2.jpg'/>"); /* Путь к файлу с заменяемым рисунком  */
   }
  </style>
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
<form action="start input" method="get">

  <center>  <button class="btn btn-warning"> <@spring.message "page.enter"/> </button></center>

</form>

<form action="registration" method="get" >

  <center>  <button class="btn btn-warning"> <@spring.message "page.register"/> </button></center>

</form>
<#include "down.ftl">
</body>
</html>
