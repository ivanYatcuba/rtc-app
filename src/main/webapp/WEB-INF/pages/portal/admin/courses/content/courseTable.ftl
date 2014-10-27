<#import "../../../../datatables.ftl" as datatables/>
<h4><b>Search Results</b></h4>
<div class="row">
    <div class="col-md-12">
        <table width="100%" class="table" style="margin-bottom: 5px">
            <tr>
                <td><b>NAME</b></td>
                <td><b>EXPERT</b></td>
                <td><b>TERM</b></td>
                <td><b>STATUS</b></td>
                <td><b>&nbsp</b></td>
            </tr>
        <#if courses??>
            <#list courses as course>
                <tr>
                    <td style="vertical-align: middle">
                        <a href="<@spring.url "/admin/course/view/${course.code}" />">${course.name}</a>
                        <p style="font-family: 'Times New Roman', Times, serif; font-style: italic">${course.types?join(", ")}</p>
                    </td>
                    <td style="vertical-align: middle">
                        <#list course.experts as expert>
                            <p>&nbsp${expert.name}&nbsp${expert.surname}
                                &nbsp${expert.email}</p>
                        </#list>
                    </td>
                    <td style="vertical-align: middle">${course.startDate?date?string("dd-MM-yyyy")}&nbsp;-&nbsp;${course.endDate?datetime?string("dd-MM-yyyy")}</td>
                    <td style="vertical-align: middle">
                        <#if "${course.status}" == "DRAFT"> <span class="label label-warning">${course.status}</span> </#if>
                        <#if "${course.status}" == "PUBLISHED"> <span class="label label-success">${course.status}</span> </#if>
                        <#if "${course.status}" == "ARCHIVED"> <span class="label label-default">${course.status}</span> </#if>
                    </td>
                    <td style="vertical-align: middle">
                        <ul class="nav" role="navigation">
                            <li class="dropdown">
                                <#if "${course.status}" == "DRAFT">
                                    <button class="btn btn-default dropdown-toggle"
                                       data-toggle="dropdown" style="width: 90px; height: 34px">Action
                                    </button>
                                    <ul class="dropdown-menu" role="menu"
                                        aria-labelledby="drop1">

                                        <li role="presentation"><a
                                                role="menuitem" tabindex="-1"
                                                href="<@spring.url "/admin/course/publish/${course.code}"/>">Publish!</a>
                                        </li>
                                        <li role="presentation"><a role="menuitem"
                                                                   tabindex="-1"
                                                                   href="<@spring.url "/admin/course/delete/${course.code}"/>">Delete</a>
                                        </li>


                                    </ul>
                                </#if>
                            </li>
                        </ul>
                    </td>
                </tr>
            </#list>
        </#if>
        </table>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <a  href="<@spring.url "/admin/course/create" />">
            <button class="btn btn-primary">Create New</button>
        </a>
    </div>
    <div class="col-md-6" style="text-align: right">
        <@datatables.addPagination/>
    </div>
</div>
