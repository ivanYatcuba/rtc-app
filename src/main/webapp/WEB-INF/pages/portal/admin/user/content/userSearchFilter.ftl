<#import "../../../../fieldMacro.ftl" as formMacro />
<h4><strong><@spring.message "userFilter.criteria"/></strong></h4>
<div class="form-horizontal">
<div class="row">
    <div class="col-md-6">
        <@formMacro.rtcFormTextInput "userFilter.surname" "userFilter.surname"/>
    </div>
    <div class="col-md-6">
        <@formMacro.formDateSearch "userFilter.dateMoreLessEq", "userFilter.registerDate"/>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <div>
        <@formMacro.rtcFormSingleSelect "userFilter.authorities", "userFilter.authorities", authorities, "", "", "", {"" : "All"}/>
        </div>
    </div>

    <div class="col-md-6">
        <@formMacro.rtcFormSingleSelect "userFilter.status", "userFilter.status", statuses, "", "", "", {"" : "All"}/>
    </div>
</div>
</div>
<hr style="height: 1px; margin-top: 5px; margin-bottom: 10px; border-top: 1px solid #ddd;"/>
        <div class="row" style="text-align: right">
            <div class="col-md-5">
            </div>
            <div class="col-md-5" style="text-align: right"> <input type="submit" id="searchButton" class="btn btn-primary" value="Search"/>
              <a class="btn btn-default" href="<@spring.url "/admin/user/viewAll" />">Reset</a>
            </div>
        </div>
