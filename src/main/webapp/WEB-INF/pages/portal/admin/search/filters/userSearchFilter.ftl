<div class="row" style="width: 100%">
 <div class="col-md-6">
    <@rtcmacros.formItem "userFilter.surname"/>
 </div>
 <div class="col-md-6 form-group">
     <label class="control-label col-md-2"><@spring.message "userFilter.registerDate"/></label>
     <@rtcmacros.formSingleSelect "userFilter.dateMoreLessEq", ["=", "<", ">"], 'style="background-color: #FFFACD;"'/>
     <@rtcmacros.formDatepicker "userFilter.registerDate"/>
 </div>
</div>

<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label class="control-label col-md-2"><@spring.message "news.filterNews.message.created"/></label>
        <@rtcmacros.formSingleSelect "newsFilter.dateMoreLessEq",["=", "<", ">"]/>
        <@rtcmacros.formDatepicker "newsFilter.createDate"/>
        <#--</div>-->
        <label class="control-label col-md-2"><@spring.message "userFilter.authorities"/></label>
        <#--<@rtcmacros.formSingleSelect "userFilter.authorities", ["ALL", "ROLE_USER", "ROLE_ADMIN", "ROLE_EXPERT"], 'style="background-color: #FFFACD;"'/>-->
         </div>
    </div>

    <div class="col-md-6">
    <@rtcmacros.formItem "userFilter.status" 'class="input-medium"' "singleSelect" userStatuses "userStatus."/>
    </div>
</div>

