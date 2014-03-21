<html>
<#import "../spring_addition.ftl" as spring/>

<head>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.css'/>" rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"/>
<@spring.includeHeader />
</head>

<body>
<style type="text/css">
    html, body {
        height: 100%;
        width: 100%;
        margin: 0px;
        padding: 0px;
    }

    .container-fluid {
        min-height: 100%;
        height: auto !important;
        height: 100%;
    }

    .container {
        padding-bottom: 100px;
    }

    .footer {
        height: 100px;
        margin-top: -100px;
        text-align: center;
        background: #999;
    }
</style>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2">

            <div class="jumbotron">
                <h1>
                    there was a menu
                </h1>
            </div>
        </div>
        <p>

        <form name="course" id="course" action="<@spring.url "/admin/courses/update" />" method="post">

            <div class="container">
                <@spring.formHiddenInput "course.code" />
                <h3><@spring.message "update.message"/></h3>
                <hr width="100%">
                <#include "courseForm.ftl" />

                <!--Create & Cancel-->
                <div class="row-fluid span12">
                    <div class="container">
                        <div class="span5">
                        </div>

                        <div class="span6" style="text-align: right">

                            <input type="submit" value="Update"/> or <a
                                href="<@spring.url "/admin/courses/"${course.code} />">Cancel</a>

                        </div>
                    </div>
                </div>

            </div>
        </form>
        </p>
    </div>
</div>

<div class="footer">
    Copyright 2014 Exigen Services, Ltd. All rights reserved E-mail: ukraine@exigenservices.com
</div>
</body>
</html>


