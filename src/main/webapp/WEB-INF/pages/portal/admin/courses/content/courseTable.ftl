<#import "../../../../fieldMacro.ftl" as formMacro />
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
                    <td style="vertical-align: middle; width: 25%">
                        <a href="<@spring.url "/admin/course/view/${course.code}" />">${course.name}</a>
                        <p style="font-family: 'Times New Roman', Times, serif; font-style: italic">${course.types?join(", ")}</p>
                    </td>
                    <td style="vertical-align: middle;">
                        <#list course.experts as expert>
                            <p>&nbsp${expert.name}&nbsp${expert.surname}
                                &nbsp${expert.email}</p>
                        </#list>
                    </td>
                    <td style="vertical-align: middle; width: 25%">${course.startDate?date?string("dd-MM-yyyy")}&nbsp;-&nbsp;${course.endDate?datetime?string("dd-MM-yyyy")}</td>
                    <td style="vertical-align: middle; width: 15%">
                        <#if "${course.status}" == "DRAFT"> <@formMacro.rtcColorLabel "${course.status}" "label-warning" "course.status."/></#if>
                        <#if "${course.status}" == "PUBLISHED"><@formMacro.rtcColorLabel "${course.status}" "label-success" "course.status."/> </#if>
                        <#if "${course.status}" == "ARCHIVED"> <@formMacro.rtcColorLabel "${course.status}" "label-default" "course.status."/> </#if>
                    </td>

                    <#if (course.status??) && (course.status != "PUBLISHED") >
                    <td style="width: 15%;vertical-align: middle">
                        <#if (course.status??) && (course.status == "DRAFT") >
                        <div class="btn-group">
                            <button class="btn btn-default" type="button" style="width: 100px" id="dropdownMenu1"
                                    data-toggle="dropdown">
                                Action</button>
                            <button type="button" class="btn btn-default dropdown-toggle" style="height: 34px" data-toggle="dropdown">
                                <span class="caret"></span>
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                                <li id="publicationLi" role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url "/admin/course/publish/${course.code}"/>">Publish</a></li>
                                <li id="deleteLi" role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url "/admin/course/delete/${course.code}"/>">Remove</a></li>
                            </ul>
                        </div>
                        </#if>
                    </td>
                    </#if>
                </tr>
            </#list>
        </#if>
        </table>
    <#--</div>-->
</div>
<hr style="height: 1px; margin-top: 5px; border-top: 1px solid #ddd;">
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
