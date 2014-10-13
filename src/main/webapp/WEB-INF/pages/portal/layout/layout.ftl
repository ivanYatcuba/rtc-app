<#macro layout menu content header>
<html>

    <#import "/spring.ftl" as spring/>
    <#import "../../rtcmacroses.ftl" as rtcmacros/>
<head>

    <@rtcmacros.includeLink />
    <@rtcmacros.includeScript/>
</head>

<body>
<div id="header" class="navbar navbar-inverse navbar-fixed-top"
     role="navigation">
    <#include "${header}" />
</div>

<div class="container-fluid" style="margin-bottom: 60px">
    <div class="row-fluid">
        <#if menu?length!=0>
            <div id="menu" class="col-sm-3 col-md-2 sidebar">
                <#include "${menu}" />
            </div>
            <div id="content" class="col-sm-offset-3 col-md-offset-2 main">
                <#include "${content}">
            </div>
        <#else>
            <div id="content" class="col-md-12 main">
                <#include "${content}">
            </div>
        </#if>
    </div>
</div>

<div id="footer" class="container">
    <#include "footer.ftl" />
</div>
</body>

</html>
</#macro>

