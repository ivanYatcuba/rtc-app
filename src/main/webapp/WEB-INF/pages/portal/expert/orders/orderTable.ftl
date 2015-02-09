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
                <a style="text-decoration: underline;" href="<@spring.url "/user/expert/order/user/${order.userCode}"/>">  ${order.userName} </a>
            </td>
            <td style="vertical-align: middle">
                <#if order.orderDate??>
                ${order.orderDate?string('dd-MMM-yyyy')!" "}
                </#if>
            </td>
            <td style="vertical-align: middle">
                <a style="text-decoration: underline;" href="<@spring.url "/user/expert/order/course/${order.courseCode}"/>">  ${order.courseName} </a><br/>
                 <span style="font-style: italic;font-size: smaller;">(${order.courseStartDate?string('dd-MMM-yyyy')!" "}&nbsp;-&nbsp;${order.courseEndDate?string('dd-MMM-yyyy')!" "})</span>
            </td>
            <td style="vertical-align: middle">
                <@formMacro.capacityIndicator order.courseAcceptedOrders order.courseCapacity />${order.courseAcceptedOrders} / ${order.courseCapacity}
            </td>
            <td style="vertical-align: middle">
                <#if order.status == 'PENDING' >
                    <@formMacro.rtcColorLabel "PENDING" "label-default" "order.status."/>
                </#if>
                <#if order.status == 'ACCEPTED' >
                    <@formMacro.rtcColorLabel "ACCEPTED" "label-success" "order.status."/>
                </#if>
                <#if order.status == 'REJECTED' >
                    <@formMacro.rtcColorLabel "REJECTED" "label-danger" "order.status."/>
                </#if>
            </td>
            <td style="vertical-align: middle">
                <div class="btn-group">
                    <button class="btn btn-default" type="button" style="width: 100px" id="dropdownMenu1" data-toggle="dropdown">Action</button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="min-width: 100px;">
                        <#if order.status == 'PENDING' >
                            <li><a class="btn" onclick="doAjaxCall('<@spring.url'/user/expert/order/accept/${order.orderCode}'/>')">
                                    <@spring.message "order.action.accept"/></a></li>
                            <li><a class="btn" onclick="doAjaxCall('<@spring.url'/user/expert/order/decline/${order.orderCode}'/>')">
                                    <@spring.message "order.action.reject"/></a></li>
                        </#if>
                        <#if order.status == 'ACCEPTED' >
                            <li><a class="btn" onclick="doAjaxCall('<@spring.url'/user/expert/order/decline/${order.orderCode}'/>')">
                                <@spring.message "order.action.reject"/></a></li>
                        </#if>
                        <#if order.status == 'REJECTED' >
                            <li><a class="btn" onclick="doAjaxCall('<@spring.url'/user/expert/order/accept/${order.orderCode}'/>')">
                                <@spring.message "order.action.accept"/></a></li>
                        </#if>
                    </ul>
                </div>
            </td>
        </tr>
    </#list>
</table>
</div>
<hr>
<div class="row">
    <div class="col-md-12" style="text-align: right">
        <@datatables.addPagination/>
    </div>
</div>
<#else>
    <td>
        There are no orders yet.
    </td>
</#if>

<script>
    function doAjaxCall(url) {
        $.ajax({
            type: "GET",
            url: url,
            success: function (result) {
                search(${currentPage});
            }, error: function (xhr, status, error) {
            }
        });
    }
</script>
