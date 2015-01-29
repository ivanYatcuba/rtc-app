<#macro layout>
<html>
<head>
    <link href="<@spring.url'/resources/css/bootstrap.min.css'/>"  rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/application.css'/>"  rel="stylesheet"/>
</head>

<body>
    <div id="wrap">
    <#include "header.ftl" />
        <div class="container">
            <#nested>
        </div>
    </div>
    <#include "footer.ftl" />
</body>

<script src="<@spring.url'/resources/js/jquery/jquery-1.11.1.min.js'/>"></script>
<script src="<@spring.url'/resources/js/bootstrap.min.js'/>"></script>
</html>
</#macro>




