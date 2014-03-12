<html>
    <#import "/spring.ftl" as spring/>
<head>
        <#include "link.ftl">
 <script src="/templates/bimall/js/bootstrap-scrollspy.js"></script>
</head>
<title> 
    <@spring.message "title.view"/>
 </title>

<body>
<div id="content">
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span6">
        <center>
          <h2>
              <p class="muted"><@spring.message "viewPage.information"/></p>
           </h2>
        </center>
         <div style="margin-left:15px;">Иванов Иван</div>
            <div class="row-fluid">
             <div class="span2">
        <img src="<@spring.url'/resources/images/profile.jpg'/>" width="150" height="200">
                </div>
                 <div class="span10">
                        <div class="row-fluid">
                                <div class="span4">
                                 <p class="muted"> <@spring.message "viewPage.dateOfBirth"/></p>
                                   </div>
                                   <div class = "span8">
                                    10 Февраля 1990
                                   </div>
                        </div>
                        <div class="row-fluid">
                                <div class="span4">
                                 <p class="muted"> <@spring.message "viewPage.cityOfResidence"/></p>
                                   </div>
                                   <div class = "span8">
                                    Днепропетровск
                                   </div>
                        </div>
                        <div class="row-fluid">
                                <div class="span4">
                                 <p class="muted"> <@spring.message "viewPage.placeOfStudy"/></p>
                                   </div>
                                   <div class = "span8">
                                    ДНУ им. О. Гончара
                                   </div>
                        </div>
                        <div class="row-fluid">
                                <div class="span4">
                                  <p class="muted"><@spring.message "viewPage.specialization"/></p>
                                   </div>
                                   <div class = "span8">
                                    Какаято специализация
                                   </div>
                        </div>                                 
                 </div>
                 </div>
    </div>
    <div class="span6">
        <center>
            <h2>
               <p class="muted"> <@spring.message "viewPage.skills"/></p>
            </h2>
        </center>
            <div class="row-fluid">
                                <div class="span2">
                                  <@spring.message "viewPage.english"/>
                                   </div>
                                   <div class = "span8">
                                     <div class="progress">
                                        <div class="bar bar-success" style="width: 35%;"></div>
                                     </div>
                                   </div>
            </div>
            <div class="row-fluid">
                                <div class="span2">
                                  <@spring.message "viewPage.direction"/> 1
                                   </div>
                                   <div class = "span8">
                                     <div class="progress">
                                        <div class="bar bar-success" style="width: 45%;"></div>
                                     </div>
                                   </div>
            </div>
             <div class="row-fluid">
                                <div class="span2">
                                  <@spring.message "viewPage.direction"/> 2
                                   </div>
                                   <div class = "span8">
                                     <div class="progress">
                                        <div class="bar bar-success" style="width: 65%;"></div>
                                     </div>
                                   </div>
            </div>
            <div class="row-fluid">
                                <div class="span2">
                                  <@spring.message "viewPage.direction"/> 3
                                   </div>
                                   <div class = "span8">
                                     <div class="progress">
                                        <div class="bar bar-success" style="width: 15%;"></div>
                                     </div>
                                   </div>
            </div>
    </div>
  </div>
</div>

<div class="row-fluid">
    <div class="span3">
        <center>
            <a href="#"><@spring.message "viewPage.message"/>(1)</a>
        </center>
     </div>
    <div class="span2">
        <center>
            <a href="#"><@spring.message "viewPage.questions"/>(3)</a>
        </center>
     </div>
    <div class="span2">
        <center>
            <a href="#"><@spring.message "viewPage.library"/></a>
        </center>
     </div>
    <div class="span2">
        <center>
            <a href="#"><@spring.message "viewPage.testing"/></a>
        </center>
     </div>
    <div class="span3">
        <center>
            <a href="#"><@spring.message "viewPage.recommendations"/></a>
        </center>
     </div>
</div>
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span4" >
        <center><h3><@spring.message "viewPage.projects"/></h3></center>
        <div data-spy="scroll" data-target="#navbar-example" data-offset="0" 
                style="height:200px;overflow:auto; position: relative;">
                 <#list project as x>
                    <hr>
                    <img src="<@spring.url'/resources/images/project.jpg'/>" width="100" height="150">
                      Project name: ${x.name} <br>                      
                        </#list>
        </div>
<br>
<hr>
                <center><h3><@spring.message "viewPage.friends"/></h3></center>
                   <div data-spy="scroll" data-target="#navbar-example" data-offset="0" 
                    style="height:200px;overflow:auto; position: relative;">
                     <#list user as x>
                             <hr>
                        <img src="<@spring.url'/resources/images/profile.jpg'/>" width="100" height="150">
                         Login: ${x.login}, password: ${x.password} <br>                      
                        </#list>
             </div>
    </div>
    <div class="span8">
      <!--Body content-->
        
    </div>
  </div>
</div>




</div>
<script type="text/javascript">
$('body').scrollspy({ target: '.navbar-example' })

</script>
<#include "down.ftl">
</body>
</html>
