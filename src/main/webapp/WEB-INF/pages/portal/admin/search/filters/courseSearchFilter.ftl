<div class="row">
    <div class="col-md-6">
    <@rtcmacros.formItem "courseFilter.name"/>

    <@spring.bind "courseFilter.types"/>
        <div class="form-group">
            <label class="control-label col-md-2"
                   for="types"><@spring.message "courseFilter.types"/></label>

            <div class="col-md-4">
            <@rtcmacros.formMultiSelect "courseFilter.types", categories/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-2"
                   for="addExpertH"><@spring.message "courseFilter.experts"/></label>

            <div class="col-md-8">
                <div id="experts"></div>
                <a id="addExpertH" href="#" onclick="addExpert()">Add
                    Expert</a>
            </div>
        </div>
    </div>
    <div class="col-md-6">
    <@rtcmacros.formItem "courseFilter.startDate" 'class="input-small"' "datepiker" />
            <@rtcmacros.formItem "courseFilter.status" 'class="input-medium"' "singleSelect" courseStatuses/>
            <@rtcmacros.formItem "courseFilter.tags" "" "tag" />
    </div>
</div>
