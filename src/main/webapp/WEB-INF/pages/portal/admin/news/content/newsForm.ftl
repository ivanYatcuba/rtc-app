<div class="row">
    <div class="col-md-7">
    <@rtcmacros.formItem "news.title" 'id="title" class="required"' />
    </div>
    <div class="col-md-5">
    <@rtcmacros.formItem "news.tags" "" "tag" />
    </div>
</div>
<div class="row">
    <div class="col-md-10" >
        <div class="form-group">
            <label class="control-label col-md-2"
                   for="description"><@spring.message "news.description"/></label>
        <@rtcmacros.formTextarea "news.description"
        'rows="3" maxlength="255" class=\"required field col-md-9\"'/>
        </div>
        <div class="checkbox">
            <label><input id="publish" type="checkbox"><@spring.message "news.publish"/></label>
        </div>
    </div>
</div>

<hr/>