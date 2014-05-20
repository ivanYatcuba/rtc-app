<#macro layout head menu link content>
<html>
    <#import "/spring.ftl" as spring/>

<head>
    <#include "${link}"/>

</head>

<body>
    <#include "${head}" />

/**
*futer for user and admin
*/
    <div id="footer" class="container">
         <nav class="navbar navbar-default navbar-fixed-bottom">
               <div class="navbar-inner navbar-content-center" align="center">
              <p class="text-muted credit">Copyright 2014 Exigen Services, Ltd. All rights reserved E-mail: ukraine@exigenservices.com</p>
            </div>
     </nav>
    </div>

<div class="container-fluid" style="margin-bottom: 60px">
<div class="row">

    <#include "${menu}" />


    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <#include "${content}">
    </div>

</div>
</div>
</body>

</html>

</#macro>

