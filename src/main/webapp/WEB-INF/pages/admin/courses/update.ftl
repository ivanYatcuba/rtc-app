<html>
<#import "../spring_addition.ftl" as spring/>

<head>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.css'/>" rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"/>
<@spring.includeHeader />
</head>

<body>
<#include "style.ftl">
<#include "header.ftl">
<#include "menu.ftl">

        <form name="course" id="course" action="<@spring.url "/admin/courses/update" />" method="post">

            <div class="container">
                <@spring.formHiddenInput "course.code" />
                <h3><@spring.message "update.message"/></h3>
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

                            <input type="submit" class="btn" value="Update"/> or <a
                                href="<@spring.url "/admin/courses/${course.code}" />">Cancel</a>

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


