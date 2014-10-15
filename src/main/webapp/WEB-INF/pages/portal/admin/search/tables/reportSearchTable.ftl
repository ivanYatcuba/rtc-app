<script src="<@spring.url'/resources/Bootstrap/js/bootstrap-dropdown.js'/>"></script>
<h3 class="page-header"><@spring.message "report.list"/></h3><br/>

<@rtcmacros.addPagination "/admin/export/viewAll/" />
<table id="data" width="100%" class="table-bordered table">
    <tr bgcolor="#d3d3d3" style="font-weight:bold">
        <td>Name</td>
        <td>Report Type</td>
        <td>Export Format</td>
        <td>Created Date</td>
        <td>&nbsp;</td>
    </tr>
<#list reports as report>
    <tr>
        <td>
            <a href="<@spring.url "/admin/export/${report.code}" />">${report.name}</a>
        </td>
        <td>${report.exportClass.simpleName}</td>
        <td>${report.exportFormat}</td>
        <td>${report.createdDate?datetime?string("dd-MM-yyyy")}</td>
        <td>
            <ul class="nav" role="navigation">
                <li class="dropdown">
                    <a href="#" class="btn dropdown-toggle"
                       data-toggle="dropdown">Action <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu"
                        aria-labelledby="drop1">
                        <li role="presentation"><a role="menuitem" tabindex="-1"
                                                   href="<@spring.url "/admin/export/download/${report.code}"/>">Download!</a>
                        </li>
                        <li role="presentation"><a role="menuitem" tabindex="-1"
                                                   href="<@spring.url "/admin/export/delete/${report.code}"/>">Delete</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </td>
    </tr>
</#list>
</table>

<br/>
<a style="float: right" href="<@spring.url "/admin/export/create" />">
    <button class="btn">Create New</button>
</a>