<h3><@spring.message "news.filter.form.header"/></h3>
<div class="row">
    <div class="col-md-6 form-group">
    <@rtcmacros.formItem "newsFilter.title"/>
    </div>
    <div class="col-md-6">
        <div class="form-group">
        <label class="control-label col-md-2"><@spring.message "news.filterNews.message.created"/></label>
        <@rtcmacros.formSingleSelect "newsFilter.dateMoreLessEq",["=", "<", ">"]/>
        <@rtcmacros.formDatepicker "newsFilter.createDate"/>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-6">
    <@rtcmacros.formItem "newsFilter.author"/>
    </div>
    <div class="col-md-6">
    <@rtcmacros.formItem "newsFilter.status" 'class="input-medium"' "singleSelect" newsStatuses "NewsStatus."/>
    </div>
</div>
