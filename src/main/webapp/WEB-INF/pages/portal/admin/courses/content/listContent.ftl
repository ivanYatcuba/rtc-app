Create New<script src="<@spring.url'/resources/Bootstrap/js/bootstrap-dropdown.js'/>"></script>

<#include "searchPanel.ftl">
<h3>Search Results</h3>

<div id="data" class="row">
    <div class="col-md-12">
        <table width="100%" class="table-bordered table">
            <tr bgcolor="#d3d3d3" style="font-weight:bold">
                <td>Name</td>
                <td>Category</td>
                <td>Dates</td>
                <td>Author</td>
                <td>Status</td>
                <td>&nbsp;</td>
            </tr>
        <#if courses??>
            <#list courses as course>
                <tr>
                    <td>
                        <a href="<@spring.url "/admin/course/view/${course.code}" />">${course.name}</a>
                    </td>
                    <td>${course.types?join(", ")}</td>

                    <td>${course.startDate?datetime?string("dd-MM-yyyy")}&nbsp;-&nbsp;${course.endDate?datetime?string("dd-MM-yyyy")}</td>
                    <td> <#list course.experts as expert>
                        <p>&nbsp${expert.name}&nbsp${expert.surname}
                            &nbsp${expert.email}</p>
                    </#list></td>
                    <td>${course.status}</td>
                    <td>
                        <ul class="nav" role="navigation">
                            <li class="dropdown">
                                <#if "${course.status}" == "DRAFT">
                                <a href="#" class="btn dropdown-toggle"
                                   data-toggle="dropdown">Action <span
                                        class="caret"></span></a>
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
        <@rtcmacros.addPagination "/admin/course/switch/" />
    </div>
</div>






