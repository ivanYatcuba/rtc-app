<#import "../../../fieldMacro.ftl" as formMacro />
<h4><strong><@spring.message "message.criteria"/></strong></h4>
<div class="form-horizontal">
    <@spring.formHiddenInput "messageFilter.userCode" />
    <div class="row">
        <div class="col-md-6">
        <@formMacro.rtcFormSingleSelect "messageFilter.status" "messageFilter.status" messageStatus, "", "", "message.status.", {"" : "All"}/>
        </div>
        <div class="col-md-6">
                <@formMacro.formDateSearch "messageFilter.dateMoreLessEq", "messageFilter.receivedDate"/>
        </div>

    </div>
    <hr style="height: 1px; margin-top: 5px; margin-bottom: 10px; border-top: 1px solid #ddd;"/>
</div>
<br>