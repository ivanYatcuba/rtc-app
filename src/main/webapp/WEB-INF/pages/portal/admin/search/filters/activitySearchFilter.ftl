<#import "../../../../fieldMacro.ftl" as formMacro/>
<h4><strong><@spring.message "activity.criteria"/></strong></h4>
<div class="form-horizontal">
    <div class="row">
        <div class="col-md-6">
            <@formMacro.rtcFormTextInput "activity.filter.entity.someEntity" "activityFilter.object"/>
            <@formMacro.rtcFormMultiSelect "activity.filter.entity" "activityFilter.entity", ["Course", "User", "News"], "" "height: 65;" "" "entityOnClick()"/>
        </div>
        <div class="col-md-6">
            <@formMacro.formDateSearch  "activityFilter.dateMoreLessEq" "activityFilter.date"/>
            <@formMacro.rtcFormMultiSelect "activity.filter.action" "activityFilter.action", ["Saved", "Updated", "Removed"], "" "height: 65;"/>
        </div>
    </div>
    <hr style="height: 1px; margin-top: 5px; margin-bottom: 10px; border-top: 1px solid #ddd;"/>
</div>
<br>

<script type="text/javascript">
    function entityOnClick() {
        var entityOptions = $('#entity')[0].options;
        var count = 0;
        var label = '';
        if (entityOptions[0].selected) {
            ++count;
            label = label + 'Course, ';
        }
        if (entityOptions[1].selected) {
            ++count;
            label = label + 'User, ';
        }
        if (entityOptions[2].selected) {
            ++count;
            label = label + 'News, ';
        }
        if (count == 0) $('label[for=object]').html('Some Entity:');
        else $('label[for=object]').html(label.substring(0, label.length-2)+':');
    }
</script>

