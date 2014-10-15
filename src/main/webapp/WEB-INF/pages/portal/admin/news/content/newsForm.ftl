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
        <#if news.status=='DRAFT'>
            <input id="publish" name="publish" onclick="published()" type="checkbox"><@spring.message "news.publish"/>
        </#if>
    </div>
</div>

<hr/>
<@rtcmacros.formValidation formName="newsForm" jsonRules="${validationRules}"/>
<script>
    function published(){
        if(document.getElementById("publish").checked == true) {
            document.getElementById("publish").value = true;
        }else{
            document.getElementById("publish").value = false;
        }
    }
</script>