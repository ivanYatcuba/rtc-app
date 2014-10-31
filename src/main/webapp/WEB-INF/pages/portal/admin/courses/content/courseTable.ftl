<#import "../../../../datatables.ftl" as datatables/>
<h4><strong><@spring.message "course.search.result.page.search"/></strong></h4>
<div id="data">
    <#--<div class="col-md-12">-->
<table width="100%" class="table" style="margin-bottom: 5px" id="CourseTable">
            <thead>
            <tr>
                <th><@spring.message "course.search.result.table.name"/></th>
                <th><@spring.message "course.search.result.table.expert"/></th>
                <th><@spring.message "course.search.result.table.term"/></th>
                <th><@spring.message "course.search.result.table.status"/></th>
                <th></th>
            </tr>
            </thead>
        <#if courses??>
            <#list courses as course>
                <tr style="vertical-align: middle">
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
                        <#if "${course.status}" == "DRAFT"> <span style="display: block" class="label label-warning">${course.status}</span> </#if>
                        <#if "${course.status}" == "PUBLISHED"> <span style="display: block" class="label label-success">${course.status}</span> </#if>
                        <#if "${course.status}" == "ARCHIVED"> <span style="display: block" class="label label-default">${course.status}</span> </#if>
                    </td>
                    <td style="vertical-align: middle">
                       <div class="btn-group">
                         <#if "${course.status}" == "DRAFT">
                         <button class="btn btn-default" style="width: 100px" type="button" data-toggle="dropdown">
                                Action
                          </button>
                             <button type="button" class="btn btn-default dropdown-toggle" style="height: 34px" data-toggle="dropdown">
                                 <span class="caret"></span>
                                 <span class="sr-only">Toggle Dropdown</span>
                             </button>
                        <ul class="dropdown-menu" style="width: 112px" role="menu">
                            <li class="dropdown">
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
                        </div>
                        </div>
                    </td>
                </tr>
            </#list>
        </#if>
        </table>
    <#--</div>-->
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
