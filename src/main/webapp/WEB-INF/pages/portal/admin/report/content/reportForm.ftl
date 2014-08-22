
<div class="row" style="margin-left: 1px" >
    <div class="col-md-6" >
        <!--Report Name-->
        <@spring.formItem "report.name"/>
        <!--Report Class-->
        <label><@spring.message "report.exportClass"/></label>
        <@spring.bind "types" />
        <select id="selectedType" name="selectedType">
            <#list types as type>
                <option value="${type}" <#if report.exportClass?? && type == report.exportClass.simpleName>selected</#if>>${type}</option>
            </#list>
        </select>
    </div>
    <div class="span5">
        <!--Report Format-->
        <label><@spring.message "report.exportFormat"/></label>
        <@spring.bind "formats" />
        <@spring.formSingleSelect "report.exportFormat", formats, " "/>
    </div>
</div>
&NonBreakingSpace;
<hr>
<div class="row">
    <div  class="col-md-6" >
        <!--Report Fields-->
        <@spring.message "report.fields"/>
        <div id="fields">
        </div>
        <label for="addFieldH"></label><a id="addFieldH" href="#" onclick="addField()">Add Field</a>
    </div>
</div>

<script>
    var fields;
    var fieldsCount = 0;
    function addField(){
        var div = $("#fields");
        div.append(getFieldsSelect(fields));
    }

    function removeField(field){
        $("#"+field).remove();
    }
    function setFieldSelection(field, selection){
        $("#"+field+" select").val(selection);
    }

    function getFieldsSelect(list){
        var fieldsSelect = "<label for=\"fieldsCount\"></label>"
        fieldsSelect += "<div id=\""+fieldsCount+"\"><select name=\"reportFields\">";
        for(var i=0; i<list.length; i++){
            fieldsSelect+="<option>"+list[i]+"</option>";
        }
        fieldsSelect+="</select>"+
                "<button onclick='removeField("+fieldsCount+")' >-</button>"
                +"<br/></div>";
        fieldsCount++;
        return fieldsSelect;
    }

    function getFields(clean){
        var selectedType = $('#selectedType').val();
        var data = 'selectedType=' + encodeURIComponent(selectedType);
        $.ajax({
            url : '<@spring.url "/admin/export/getFields" />',
            data : data,
            type : "GET",
            success : function(response) {
                fields = response;
                if(clean == true){
                    var div = $("#fields");
                    div.html("");
                }else{
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
            error : function(xhr, status, error) {
            }
        });
        return false;
    }

    $(document).ready(function() {
        getFields(false);
        $('#selectedType').change(function(event) {
            getFields(true);
        });
    });
</script>
<@spring.formValidation formName="report" jsonRules="${validationRules}"/>
