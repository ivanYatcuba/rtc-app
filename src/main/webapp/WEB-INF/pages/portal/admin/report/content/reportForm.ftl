<div class="row">
    <div class="col-md-6">
        <!--Report Name-->
    <@rtcmacros.formItem "report.name" "class=\"required\""/>
        <!--Report Class-->
        <div class="form-group">
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
        </div>

    </div>
    <div class="span5">
        <!--Report Format-->
        <div class="form-group">
            <label class="control-label col-md-2">*<@spring.message "report.exportFormat"/></label>

            <div class="col-md-4">
            <@spring.bind "formats" />
                <@spring.formSingleSelect "report.exportFormat", formats, "class=\"required\""/>
            </div>
        </div>
    </div>
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

<script>
    var fields;
    var fieldsCount = 0;
    function addField() {
        var div = $("#fields");
        div.append(getFieldsSelect(fields));
    }

    function removeField(field) {
        $("#" + field).remove();
    }
    function setFieldSelection(field, selection) {
        $("#" + field + " select").val(selection);
    }

    function getFieldsSelect(list) {
        var fieldsSelect = "<div id=\"" + fieldsCount + "\"><label for=\"fieldsCount\"></label><select name=\"reportFields\">";
        for (var i = 0; i < list.length; i++) {
            fieldsSelect += "<option>" + list[i] + "</option>";
        }
        fieldsSelect += "</select>" +
                "<button onclick='removeField(" + fieldsCount + ")' >-</button>"
                + "<br/></div>";
        fieldsCount++;
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
    });
</script>
<@rtcmacros.formValidation formName="report" jsonRules="${validationRules}"/>
