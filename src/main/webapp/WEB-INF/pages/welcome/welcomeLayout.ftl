<html>
<#import "../customSpring.ftl" as spring/>
<head>
    <#include "layout/link.ftl">
    <#include "layout/header.ftl" />
</head>

<body>
    <div id="wrap" class="container">
        <#include "${content}.ftl" />
    </div>
    <div id="push"></div>
    <footer>
        <#include "layout/footer.ftl" />
    </footer>
</body>

<script src="<@spring.url'/resources/js/jquery-1.11.1.min.js'/>"></script>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.min.js'/>"></script>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.js'/>"></script>
</html>




