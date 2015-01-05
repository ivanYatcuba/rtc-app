<#import "../../../../datatables.ftl" as datatables/>
<#import "../../../../fieldMacro.ftl" as formMacro/>
<h4><strong><@spring.message "activity.result"/></strong></h4>
<div id="data">
    <table width="100%" class="table" style="margin-bottom: 5px" id="ActivityTable">
        <thead>
        <tr>
            <th><@spring.message "activity.table.details"/></th>
            <th><@spring.message "activity.table.action"/></th>
            <th><@spring.message "activity.table.user"/></th>
            <th><@spring.message "activity.table.time"/></th>
        </tr>
        </thead>
        <tr style="vertical-align: middle">
            <td style="vertical-align: middle">
            </td>
            <td style="vertical-align: middle">
            </td>
            <td style="vertical-align: middle">
            </td>
            <td style="vertical-align: middle">
            </td>
        </tr>
    </table>
</div>
<hr style="height: 1px; margin-top: 5px; border-top: 1px solid #ddd;">
<div class="row">
    <div class="col-md-6" style="text-align: right">
        <@datatables.addPagination/>
    </div>
</div>
