<#macro layout>
<html>
    <#import "/spring.ftl" as spring/>
    <#import "../../fieldMacro.ftl" as formMacro />
<head>
    <@formMacro.rtcIncludeLink />
    <@formMacro.rtcIncludeScript/>
</head>

<body>
    <div id="header" class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <#include "header.ftl" />
    </div>
    <div class="container-fluid" style="margin-bottom: 60px">
        <div class="row-fluid">
                <div id="content" class="main">
                    <#nested>
                </div>
        </div>
    </div>
    <div id="footer" class="container">
        <#include "footer.ftl" />
    </div>
</body>

</html>
</#macro>

