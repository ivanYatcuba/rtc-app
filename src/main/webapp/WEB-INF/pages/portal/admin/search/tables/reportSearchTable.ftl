<#import "../../../../datatables.ftl" as datatables/>
<script src="<@spring.url'/resources/Bootstrap/js/bootstrap-dropdown.js'/>"></script>
<h4><strong><@spring.message "reportFilter.search.result.page.header"/></strong></h4>
<div>
    <table width="100%" class="table" style="margin-bottom: 5px" id="ReportTable">
        <thead>
        <tr>
            <th><@spring.message "reportFilter.search.result.header.name"/></th>
            <th><@spring.message "reportFilter.search.result.header.reportType"/></th>
            <th><@spring.message "reportFilter.search.result.header.exportFormat"/></th>
            <th><@spring.message "reportFilter.search.result.header.createDate"/></th>
            <th></th>
        </tr>
        </thead>
    <#if reports??>
        <#list reports as report>
            <tr style="vertical-align: middle">
                <td style="vertical-align: middle; width: 25%">
                    <a href="<@spring.url "/admin/export/${report.code}" />">${report.name}</a>
                </td>
                <td style="vertical-align: middle">${report.exportClass.simpleName}</td>
                <td style="vertical-align: middle">${report.exportFormat}</td>
                <td style="vertical-align: middle">${report.createdDate?datetime?string("dd-MM-yyyy")}</td>
                <td style="vertical-align: middle">
                    <div class="btn-group">
                        <button class="btn btn-default" type="button" style="width: 100px" id="dropdownMenuReport" data-toggle="dropdown">Action</button>
                        <button type="button" class="btn btn-default dropdown-toggle" style="height: 34px" data-toggle="dropdown">
                            <span class="caret"></span>
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                            <li id="downloadLi" role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url'/admin/export/download/${report.code}'/>">Download</a></li>
                            <li id="removeLi" role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url'/admin/export/delete/${report.code}'/>">Remove</a></li>
                        </ul>
                    </div>

                </td>
            </tr>
        </#list>
    </#if>
    </table>
</div>
<hr>
<div class="row">
    <div class="col-md-6">
        <form  class="inline-box" style="margin: 0px"  name="createNews" action="<@spring.url"/admin/export/create"/>"method="get">
            <button  class="btn btn-primary" type="submit">Create New</button>
        </form>
    </div>
    <div class="col-md-6" style="text-align: right">
    <#--<@datatables.addPagination/>-->
    </div>
</div>