<html>
<#import "../customSpring.ftl" as spring/>
<head>
    <#include "layout/link.ftl">
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

<script src="<@spring.url'/resources/js/jquery-1.11.1.min.js'/>"></script>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.min.js'/>"></script>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.js'/>"></script>
</html>




