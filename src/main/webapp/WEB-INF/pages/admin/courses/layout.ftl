<html>
<#import "/spring.ftl" as spring/>

<head>
    <#include "layout/link.ftl">

</head>

<body>

    <#include "layout/header.ftl">

    <div class="container-fluid">
        <div class="row">

            <div class="col-sm-3 col-md-2 sidebar">
                <#include "layout/menu.ftl">
            </div>

            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <#include "content/${content}.ftl">
            </div>
            
        </div>
    </div>
    <#--<#include "layout/footer.ftl">-->
</body>

</html>




