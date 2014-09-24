<html>
<#import "../spring.ftl" as spring/>
<head>
    <link href="<@spring.url'/resources/css/bootstrap.css'/>"
          rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/bootstrap.min.css'/>"
          rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/welcomePage.css'/>"
          rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/carousel.css'/>" rel="stylesheet"/>
</head>

<body>
<div id="wrap">
<#include "layout/header.ftl" />
    <div class="container">
    <#include "${content}.ftl" />
    </div>
</div>
<#include "layout/footer.ftl" />
</body>

<script src="<@spring.url'/resources/js/jquery/jquery-1.11.1.min.js'/>"></script>
<script src="<@spring.url'/resources/Bootstrap/js/bootstrap.min.js'/>"></script>
</html>




