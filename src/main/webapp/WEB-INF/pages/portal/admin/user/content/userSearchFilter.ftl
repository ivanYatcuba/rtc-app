<h4><strong><@spring.message "userFilter.criteria"/></strong></h4>
<div class="row">
    <div class="col-md-6">
    <@rtcmacros.formItem "userFilter.surname"/>
    </div>
    <div class="col-md-6">
        <div class="form-group">
            <label class="control-label col-md-4"><@spring.message "userFilter.registerDate"/></label>
        <@rtcmacros.formSingleSelect "userFilter.dateMoreLessEq", ["=", "<", ">"], ''/>
        <@rtcmacros.formDatepicker "userFilter.registerDate", ''/>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <div >
            <label class="control-label col-md-4"><@spring.message "userFilter.authorities"/></label>
        <@spring.formSingleSelect "userFilter.authorities", ["ALL", "User", "Administrator", "Expert"], ''/>
        </div>
    </div>

    <div class="col-md-6">
        <label class="control-label col-md-4"><@spring.message "userFilter.status"/></label>
    <@spring.formSingleSelect "userFilter.status", ["ALL", "ACTIVE", "INACTIVE", "FOR_REMOVAL"], ''/>
    </div>
</div>
        <div class="row" style="text-align: right">
            <input type="submit" id="searchButton" class="btn btn-primary" value="Search"/> or <a class="btn-default"
         href="<@spring.url "/admin/user/viewAll" />">Reset</a>
        </div>