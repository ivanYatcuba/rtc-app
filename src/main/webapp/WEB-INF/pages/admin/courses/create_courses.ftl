<html>
<#import "../spring_addition.ftl" as spring/>

<head>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.css'/>" rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="<@spring.url '/resources/css/admin.css'/>"/>
<@spring.includeHeader />
</head>

<body>
<#include "header.ftl">
<#include "menu.ftl">

        <form name="course" id="course" action="<@spring.url "/admin/courses/save" />" method="post">
            <div class="container">
                <h3><@spring.message "create.message"/></h3>
                <hr width="100%">
                <div class="panel panel-default">
                    <div class="panel-body">
                <#include "courseForm.ftl" />

                <!--Create & Cancel-->
                <div class="row-fluid span12">
                    <div class="container">
                        <div class="span5">
                        </div>

                        <div class="span6" style="text-align: right">

                            <input type="submit" class="btn" value="Create"/> or <a
                                href="<@spring.url "/admin/courses" />">Cancel</a>

                        </div>
                    </div>
                </div>
                        </div>
                    </div>

            </div>
        </form>

<#include "footer.ftl">
</body>
</html>
