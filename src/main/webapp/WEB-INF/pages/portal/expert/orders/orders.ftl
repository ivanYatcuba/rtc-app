<#import "../layout/layout.ftl" as layout/>
<@layout.layout>
    Orders:
    <#list orders as order>
    <div class="row" style="border-color: #080808; border: 2px solid; padding: 5px">
        User: ${order.userCode}<br/>
        Course: ${order.courseCode}<br/>
        Position: ${order.position}<br/>
        Reason: ${order.reason}<br/>
        <#if order.status == "PENDING">
            <a href="<@spring.url "/user/expert/order/accept/${order.code}" />" class="btn btn-success">Accept</a>&nbsp;
            <a href="<@spring.url "/user/expert/order/decline/${order.code}" />" class="btn btn-danger">Decline</a>
        <#else>
            <span style="color: #843534">${order.status}</span>
        </#if>
    </div>
    </#list>
</@layout.layout>
