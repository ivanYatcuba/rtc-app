<html>
<#import "/spring.ftl" as spring/>

<head>
    <#include "layout/link.ftl">

</head>

<body>
    <#include "layout/header.ftl">
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span2">
                <#include "layout/menu.ftl">
            </div>
            <div class="span10">
                <#include "content/${content}.ftl">
            </div>
        </div>
    </div>
    <#include "layout/footer.ftl">
</body>

</html>




