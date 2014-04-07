<html>
<#import "/spring.ftl" as spring/>

<head>
    <#include "layout/link.ftl">

</head>

<body>

    <#include "layout/header.ftl">

    <div class="container-fluid" style="margin-bottom: 60px">
        <#include "${content}.ftl">
    </div>

    <#include "layout/footer.ftl">

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.min.js'/>"></script>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.js'/>"></script>
</html>




