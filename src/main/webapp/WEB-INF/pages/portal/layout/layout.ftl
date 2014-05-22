<#macro layout menu content>
<html>
    <#import "/spring.ftl" as spring/>

<head>
    <#include "link.ftl"/>

</head>
<body>

<div id="header" class="navbar navbar-inverse navbar-fixed-top" role="navigation" >
      <#include "header.ftl" />
</div>
/**
*footer for user and admin
*/
<div id="footer" class="container">
    <#include "footer.ftl" />
</div>



<div class="container-fluid" style="margin-bottom: 60px">
<div class="row">
    /**
    *menu
    */

    <div id="menu" class="col-sm-3 col-md-2 sidebar"  >
    <#include "${menu}" />
    </div>


    <div id="content" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <#include "${content}">
    </div>

</div>
</div>
</body>

</html>

</#macro>

