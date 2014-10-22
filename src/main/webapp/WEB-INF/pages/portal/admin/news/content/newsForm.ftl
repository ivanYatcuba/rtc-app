<style>
    .control-label {
        float: left;
        width: 13em;
        text-align: right;
        margin-bottom: 0px;
    }
</style>
<div class="row">
    <div class="col-md-6">
    <@rtcmacros.formItem "news.title" 'id="title" class="required"' />
    </div>
    <div class="col-md-6">
    <@rtcmacros.formItem "news.tags" "" "tag" />
    </div>
</div>
<div class="row">
    <div class="col-md-12" >
        <div class="form-group">
            <label class="control-label col-md-2"
                   for="description"><@spring.message "news.description"/></label>
        <@rtcmacros.formTextarea "news.description"
        'rows="3" maxlength="255" class=\"required field col-md-9\"'/>
        </div>
    </div>
    </div>
    <div class="row" style="padding-left: 13em">
    <#if news.status=='DRAFT'>
        <input id="publish" name="publish" type="checkbox"><@spring.message "news.publish"/>
    </#if>
    </div>


<hr/>
<@rtcmacros.formValidation formName="newsForm" jsonRules="${validationRules}"/>