<#import "../../../fieldMacro.ftl" as formMacro />
<#import "../../../datatables.ftl" as datatables/>
<h4><strong><@spring.message "order.search.result.page.header"/></strong></h4>
<div>
<table width="100%" class="table" style="margin-bottom: 5px" id="ReportTable">
    <thead>
    <tr>
        <th><@spring.message "order.search.result.header.user"/></th>
        <th><@spring.message "order.search.result.header.orderDate"/></th>
        <th><@spring.message "order.search.result.header.course"/></th>
        <th><@spring.message "order.search.result.header.capacity"/></th>
        <th><@spring.message "order.search.result.header.status"/></th>
        <th></th>
    </tr>
    </thead>
<#if orders?has_content>
    <#list orders as order>
        <tr style="vertical-align: middle">
            <td style="vertical-align: middle; width: 25%">
                <#if order.userPhoto??>
                    <img id="img" src="<@spring.url '/image/${order.userPhoto}'/>"  class="avatar"/>
                <#else>
                    <img id="img" src = "<@spring.url '/resources/images/errorCat.jpg'/>"  class="avatar">
                </#if>
                <a href="<@spring.url "/user/expert/order/user/${order.userCode}"/>">  ${order.userName} </a>
            </td>
            <td style="vertical-align: middle">
                <#if order.orderDate??>
                ${order.orderDate?string('dd-MMM-yyyy')!" "}
                </#if>
            </td>
            <td style="vertical-align: middle">
                <a href="<@spring.url "/user/expert/order/course/${order.courseCode}"/>">  ${order.courseName} </a><br/>
                (${order.courseStartDate?string('dd-MMM-yyyy')!" "} - ${order.courseEndDate?string('dd-MMM-yyyy')!" "})
            </td>
            <td style="vertical-align: middle">
                <@formMacro.capacityIndicator order.courseAcceptedOrders order.courseCapacity />${order.courseAcceptedOrders} / ${order.courseCapacity}
            </td>
            <td style="vertical-align: middle">
                ${order.status}
            </td>
            <td style="vertical-align: middle">
                <div class="btn-group">
                    <button class="btn btn-default" type="button" style="width: 100px" id="dropdownMenuReport" data-toggle="dropdown">Action</button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="min-width: 100px;">
                        <li id="downloadLi" role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url'/admin/export/download/${report.code}'/>">Download</a></li>
                        <li id="removeLi" role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url'/admin/export/delete/${report.code}'/>">Remove</a></li>
                    </ul>
                </div>
            </td>
        </tr>
    </#list>
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
        <@datatables.addPagination/>
    </div>
</div>
<#else>
    <td>
        There are no reports. <a  href="<@spring.url "/admin/export/create"/>">Click here</a> to add.
    </td>
</#if>
