<#--Macro to create custom input
* lableText - text near input
* lableClass - text of the lable near input
* lableOnTheNewLine set the lable in above input
* type - type of selected input
* class - input's class
* id - input's id
* name - input's name
* style - input's style
* value - input's value
* textOnTheLeftSide - is true display input label at the left side
* Example:
* <@input lableText = ["Enter first name", "Enter last name"]
*                         type = "text"
*                         textOnTheLeftSide="false"
*                         lableOnTheNewLine="true"
*                         id = ["1"]/>
-->

<#macro input
        lableText = [""]
        lableClass = ""
        lableOnTheNewLine = "true"
        type = "text"
        textOnTheLeftSide = "true"
        class = ""
        id = [""]
        name = [""]
        style = ""
        value = [""]>
    <#list lableText as l_text >
        <#if textOnTheLeftSide == "true" && lableOnTheNewLine != "true"><div class="${lableClass}">${l_text}</#if>
        <#if textOnTheLeftSide == "true" && lableOnTheNewLine == "true"><div class="${lableClass}">${l_text}</div></#if>
        <#if textOnTheLeftSide != "true" && lableOnTheNewLine == "true"><div class="${lableClass}">${l_text}</div></#if>
        <#if textOnTheLeftSide != "true" && lableOnTheNewLine != "true"><div class="${lableClass}"></#if>
        <input  type = ${type}
                    class = "${class}"
                    <#if id[l_text_index]?exists>id = "${id[l_text_index]}"<#else>id = ""</#if>
                    <#if name[l_text_index]?exists>name = "${name[l_text_index]}"<#else>name = ""</#if>
                    <#if value[l_text_index]?exists>value = "${value[l_text_index]}"<#else>value= ""</#if>>
        <#if textOnTheLeftSide != "true" && lableOnTheNewLine != "true">${l_text}</div></#if>
        <#if textOnTheLeftSide == "true" && lableOnTheNewLine != "true"></div></#if>  <br />
    </#list>
</#macro>


<#--Macro to create custom select
* options - options in select
* class - select's class
* id - select's id
* name - select's name
* style - select's style
* value - values of options
* Example:   <@customSelect options = ["winter", "spring", "summer", "autmn"]/>
-->
<#macro customSelect options
                     class = ""
                     id = ""
                     name = ""
                     style = ""
                     value = [""]>
    <p><select size="x_size"
               class = "${class}"
               id = "${id}"
               name = "${name}"
               style = "${style}">
    <#list options as x>
        <option value=${x}  <#if value[x_index]?exists>value = "${value[x_index]}"<#else>value= ""</#if>>
        ${x}
        </option>
    </#list>
    </select></p>
</#macro>

<#--Macro to create text area
* lableText - text of the lable near text area
* lableClass - class of the lable near text area
* class - class of text are
* id - id of text area
* name - name of text area
* style - style of text area
* rows - number of rows in text area
* cols number of cols in text area
-->
<#macro textArea
        lableText = ""
        lableClass = "none"
        class = ""
        id = ""
        name = ""
        style = ""
        rows = "10"
        cols = "45">
    <div class="${lableClass}">${lableText}</div><br />
    <textarea class="${class}" id="${id}" name="${name}" style="${style}" rows="${rows}" cols="${cols}" ></textarea>
</#macro>


<#--Macro to validate form
* form - name of the form that needs validation
* class - json class
-->
<#macro validate
        form = ""
        class = "">
<script>
$(function() {
    $("#${form}").validate({

    ${class}

        submitHandler: function(form) {
            form.submit();
        }
    });

});
</script>
</#macro>




<#macro datepicker
id ="datepicker"
name="datepicker"
>

<link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/themes/ui-darkness/jquery-ui.css" rel="stylesheet">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
<input id="${id}" type="text">
<script type="text/javascript">
    $(function() {
        $("#${id}").datepicker({changeYear:true});
        $("#${id}").datepicker();
        $("#${id}").datepicker("setDate", new Date);
    });
</script>

</#macro>
