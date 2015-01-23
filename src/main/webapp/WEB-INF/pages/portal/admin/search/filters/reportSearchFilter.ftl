<#import "../../../../fieldMacro.ftl" as formMacro />
<h4><strong><@spring.message "report.search.result.page.criteria"/></strong></h4>
<div class="form-horizontal">
    <div class="row">
        <div class="col-md-6">
            <@formMacro.rtcFormTextInput "reportFilter.name" "reportFilter.name"/>
        </div>
        <div class="col-md-6">
            <@formMacro.rtcFormSingleSelect "report.lable.exportClass" "reportFilter.exportClass" reportTypes "" "" "report.exportClass." {"" : "All"}/>
        </div>
    </div>
    <hr style="height: 1px; margin-top: 5px; margin-bottom: 10px; border-top: 1px solid #ddd;"/>
</div>
<br>