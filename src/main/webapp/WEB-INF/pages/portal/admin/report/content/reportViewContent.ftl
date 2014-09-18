<h3 class="page-header"><@spring.message "report.details"/></h3><br>
<style>
    label {
        float: left;
        width: 13em;
        margin-right: 1em;
        text-align: right;
        font-size: 10pt;
        margin-bottom: 10px;
    }
</style>
<div class="row">
    <div class="col-md-6">
        <label><@spring.message "report.name"/></label>

        <p>&nbsp${report.name}</p>
        <label><@spring.message "report.exportClass"/></label>

        <p>&nbsp${report.exportClass.simpleName}</p>
    </div>
    <div class="col-md-6">
        <label><@spring.message "report.exportFormat"/></label>

        <p>&nbsp${report.exportFormat}</p>
    </div>
</div>
&NonBreakingSpace;
<hr>
<div class="row">
    <div class="col-md-6">
        <p style="margin-left: 6em"><@spring.message "report.fields"/></p>
    <#list report.fields as field>
        <label></label>

        <p>&nbsp${field}</p>
    </#list>
    </div>
</div>
<hr>
<div class="row">
    <div class="col-md-6"></div>
    <div class="col-md-5" style="text-align: right">
        <a href="<@spring.url "/admin/export/update/${report.code}" />">
            <button class="btn"><@spring.message "action.edit"/></button>
        </a> or
        <a href="<@spring.url "/admin/export/viewAll" />"><@spring.message "action.cancel"/></a>
    </div>
</div>
