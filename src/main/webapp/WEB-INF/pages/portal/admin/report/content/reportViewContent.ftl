<h3 class="page-header"><@spring.message "report.details"/></h3>
<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span6" >
        <label><@spring.message "report.name"/></label>&nbsp${report.name}</p>
        <label><@spring.message "report.exportClass"/></label>&nbsp${report.exportClass.simpleName}</p>
    </div>
    <div class="span5">
        <label><@spring.message "report.exportFormat"/></label>&nbsp${report.exportFormat}</p>
    </div>
</div>
&NonBreakingSpace;
<hr>
<div class="span6" >
    <p><@spring.message "report.fields"/></p>
    <#list report.fields as field>
        <label></label><p>${field}</p>
    </#list>
</div>


<div class = "row-fluid span12">
    <hr>
    <div class="span6" align="right">
        <a href="<@spring.url "/admin/export/update/${report.code}" />"><button class="btn"><@spring.message "action.edit"/></button></a> or
        <a href="<@spring.url "/admin/export/viewAll" />"><@spring.message "action.cancel"/></a>
    </div>
</div>
