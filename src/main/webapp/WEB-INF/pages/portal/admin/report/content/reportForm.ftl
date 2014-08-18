
<div class="row-fluid span12" style="margin-left: 1px" >
    <div class="span6" >
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
<div class="span6" >
    <!--Report Fields-->
    <@spring.message "report.fields"/>
    <div id="fields">
    <#if report.exportClass??>
        <#assign i = 0>
        <#if report.fields??>
            <#list report.fields as f>
                <#assign i = i+1>
                <@spring.bind "fieldsStr" />
                <select id="reportField_${i}" name="reportField_${i}">
                    <#list fieldsStr as field>
                            <option value="${field}" <#if f?? && field == f>selected</#if>>${field}</option>
                    </#list>
                </select>&nbsp;<button class="btn" type="submit" name="action" value="removeField_${i}" >-</button><br/>
            </#list>
        </#if>
        <#if addFieldTrue??>
            <#assign i = i+1>
            <select id="reportField_${i}" name="reportField_${i}">
                <#list fieldsStr as field>
                    <option value="${field}">${field}</option>
                </#list>
            </select>&nbsp;<button class="btn" type="submit" name="action" value="removeField_${i}" >-</button><br/>
        </#if>
    </#if>
    </div>
    <button type="submit" name="action" value="addField"
            style="background:none; border-width:0px; color:blue; text-decoration:underline;" >
        Add Field</button>
</div>
<#if report.fields??>
    <input type="hidden" name="fieldCount" value="${report.fields?size+1}">
<#else >
    <input type="hidden" name="fieldCount" value="0">
</#if>

<script>
    $(document).ready(function() {
        $('#selectedType').change(function() {
            var input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "action");
            input.setAttribute("value", "changeType");
            document.getElementById("report").appendChild(input);
            $("#report").submit();
        });
    });
</script>

<@spring.formValidation formName="report" jsonRules="${validationRules}"/>
