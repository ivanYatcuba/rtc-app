<html>
<#import "/spring.ftl" as spring/>
<head>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.css'/>" rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/Bootstrap/css/bootstrap.min.css'/>" rel="stylesheet"/>
    <link href="<@spring.url'/resources/css/grid.css'/>" rel="stylesheet"/>
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
    .header {
        height: 40px;
        text-align: center;
        background: #999;
    }
</style>
<#include "header.ftl">
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span2">

            <div class="jumbotron">
                <h1>
                       <#include "menu.ftl">
                </h1>
            </div>
        </div>
        <p>

        <div class="container">
            <h3>Courses List</h3>
            <hr width="100%">

            <div class="head-2"><strong>Category</strong></div>
            <div class="head-3"><strong>Name</strong></div>
            <div class="head-3"><strong>Date</strong></div>
            <div class="head-2"><strong>Author</strong></div>
            <div class="head-2"><strong>Action</strong></div>
        <#list courses as course>
            <div class="col-md-2">${course.type}</div>
            <div class="col-md-3"><a href="<@spring.url "/admin/courses/${course.code}" />">${course.name}</a></div>
            <div class="col-md-3">${course.startDate?date}&nbsp;-&nbsp;${course.endDate?date}</div>
            <div class="col-md-2">${course.author.firstName}&nbsp;${course.author.lastName}</div>
            <div class="col-md-2"><a href="<@spring.url "/admin/courses/delete/${course.code}" />">delete</a></div>
        </#list>

            <a style="float: right" href="<@spring.url "/admin/courses/create" />">
                <button class="btn">Create New</button>
            </a>
        </div>
        </p>
    </div>
</div>

<#include "footer.ftl">
</body>
</html>
