<html>
<#import "/spring.ftl" as spring/>

<head>
<#include "layout/link.ftl">

</head>

<body>

    <#include "layout/header.ftl">

    <div class="container-fluid" style="margin-bottom: 60px">
        <div class="row">

                <#include "layout/menu.ftl">

            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <#include "content/${content}.ftl">
            </div>

        </div>
    </div>

    <#include "../admin/course/layout/footer.ftl">

</body>

</html>