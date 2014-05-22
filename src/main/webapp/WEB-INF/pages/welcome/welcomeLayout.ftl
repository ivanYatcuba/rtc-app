<html>
<#import "/spring.ftl" as spring/>

<head>
    <#include "layout/link.ftl">

</head>

<body>

<div id="header" class="navbar navbar-inverse navbar-fixed-top" role="navigation" >
<#include "layout/header.ftl" />
</div>

    <div class="container-fluid" style="margin-bottom: 60px">
        <#include "${content}.ftl">
    </div>

<div id="footer" class="container">
<#include "layout/footer.ftl" />
</div>


</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.min.js'/>"></script>
<script src="<@spring.url'/resources/css/Bootstrap/js/bootstrap.js'/>"></script>
</html>




