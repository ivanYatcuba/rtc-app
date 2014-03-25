<html>
<#import "/spring.ftl" as spring/>
<head>
    
<#include "link.ftl">

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
</td>
<td>
    <center>
    <a data-toggle="modal" data-target="#modalIn" class="btn btn-warning"><@spring.message "btn.enter"></@></a>
    |
    <a data-toggle="modal" data-target="#modalReg" class="btn btn-warning"> <@spring.message "page.register"/></a>
    </center>
</td>
<td>
        </td>
            </tr>
            </table>


</div>


<#include "down.ftl">
</div>

<div class="container">


<div id="modalIn" class="modal hide fade" tabindex="-1" role="dialog">
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
    <button class="btn" data-dismiss="modal"><@spring.message "btn.close"/></button>
      </div>
</div>


<div class="container">

<div id="modalReg" class="modal hide fade" tabindex="-1" role="dialog">
  <div class="modal-header">

    <button type="button" class="close" data-dismiss="modal">X</button>

    <#-- <h1><@spring.message "register"/></h1>-->
  </div>

  <div class="modal-body">
<center>
    <#include "registration.ftl">
</center>
  </div>
  <div class="modal-footer">
    <button class="btn " data-dismiss="modal"><@spring.message "btn.close"/></button>
      </div>
</div>

<#--<form name="w" action="w" method="post" >
        <div style="margin-left:560px">
            <button class="btn btn-warning pull-right" type="submit"><@spring.message "btn.home"/></button>
        </div>
    </form>-->
</body>

</html>
