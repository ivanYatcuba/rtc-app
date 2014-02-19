

<#--Macro to create custom input
* inputLabel - just input label
* inputType - type of selected input
* class - input's class
* id - input's id
* name - input's name
* style - input's style
* value - input's value
* textOnTheLeftSide - is true display input label at the left side
* Example:  <@customInput inputLabel = ["Enter first name", "Enter last name"]
*                         inputType = "text"
*                         textOnTheLeftSide="true"/>
-->
<#macro customInput inputLabel = ""
                    inputType = "text"
                    textOnTheLeftSide = "true"
                    class = ""
                    id = ""
                    name = ""
                    style = ""
                    values = "">
    <#list inputLabel as x >
        <p><#if textOnTheLeftSide == "true">${x}</#if>
            <input  type = ${inputType}
                    class = ${class}
                    id = ${id}
                    name = ${name}
                    style = ${style}
                    value = ${values[x_index]}>
        <#if textOnTheLeftSide != "true">${x}</#if></p>
    </#list>
</#macro>


<#--Macro to create custom select
* options - options in select
* class - select's class
* id - select's id
* name - select's name
* style - select's style
* Example:   <@customSelect options = ["winter", "asprint", "summer", "autmn"]/>
-->
<#macro customSelect options
                     class = ""
                     id = ""
                     name = ""
                     style = "">
    <p><select size="x_size"
               class = ${class}
               id = ${id}
               name = ${name}
               style = ${style}>
    <#list options as x>
        <option value=${x}>${x}</option>
    </#list>
    </select></p>
</#macro>