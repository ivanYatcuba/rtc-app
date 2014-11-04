<div class="row">
    <div class="col-md-6">
        <@formMacro.rtcFormTextInput "report.name" "report.name" "required" ""/>
    </div>
    <div class="col-md-6" >
            <@formMacro.rtcFormSingleSelect "report.lable.exportFormate" "report.exportFormat" formats "required" "" "report.exportFormat." ""/>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <#--<@formMacro.rtcFormSingleSelect "report.lable.exportClass" "report.exportFormat" types "required" "" "report.exportClass." ""/>-->
    </div>


    <#--<div class="form-group">
        <label class="control-label col-md-2">*<@spring.message "report.exportClass"/></label>

        <div class="col-md-4">
        <@spring.bind "types" />
            <select id="selectedType" name="selectedType" class="required">
            <#list types as type>
                <option value="${type}"
                        <#if report.exportClass?? && type == report.exportClass.simpleName>selected</#if>>${type}</option>
            </#list>
            </select>
        </div>
    </div>-->
</div>
&NonBreakingSpace;
<hr>
<div class="row">
    <div class="col-md-12">
        <div class="form-group">
            <div class="col-md-4"
                 for="addFieldH"><@spring.message "report.fields"/></div>
            <div class="col-md-6" style="text-align: left; margin-left: -170px">
                <div id="fields"></div>
                <a id="addFieldH" href="#" onclick="addField()">Add Field</a>
            </div>
        </div>
    </div>
</div>

<input type="hidden" id="reportCount" name="reportCount" value="">
<script>
    var fields;
    var currentFieldId = 0;
    var fieldsCount = 0;
    function addField() {
        $("#fields").append(getFieldsSelect(fields));
        fieldsCount++;
    }

    function removeField(field) {
        $("#" + field).remove();
        fieldsCount--;
    }
    function setFieldSelection(field, selection) {
        $("#" + field + " select").val(selection);
    }

    function getFieldsSelect(list) {
        var fieldsSelect = "<div id=\"" + currentFieldId + "\"><label for=\"fieldsCount\"></label><select name=\"reportFields\">";
        for (var i = 0; i < list.length; i++) {
            fieldsSelect += "<option>" + list[i] + "</option>";
        }
        fieldsSelect += "</select>" +
                "<button onclick='removeField(" + currentFieldId + ")' >-</button>"
                + "<br/></div>";
        currentFieldId++;
        return fieldsSelect;
    }

    function getFields(clean) {
        var selectedType = $('#selectedType').val();
        var data = 'selectedType=' + encodeURIComponent(selectedType);
        $.ajax({
            url: '<@spring.url "/admin/export/getFields" />',
            data: data,
            type: "GET",
            success: function (response) {
                fields = response;
                if (clean == true) {
                    var div = $("#fields");
                    div.html("");
                } else {
                <#if report.fields??>
                    <#assign i = 0>
                    <#list report.fields as f>
                        addField();
                        setFieldSelection(${i}, "${f}");
                        <#assign i = i+1>
                    </#list>
                </#if>
                }

            },
            error: function (xhr, status, error) {
            }
        });
        return false;
    }

    $(document).ready(function () {
        getFields(false);
        $('#selectedType').change(function (event) {
            getFields(true);
        });

        $("form input[type=submit]").click(function(event){
            if(fieldsCount == 0){
                event.preventDefault();
                alert("Add fields!");
            }
        });

    });


</script>
