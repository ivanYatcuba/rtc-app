<#include "macro.ftl">

<@textArea lableText="Enter some text" name="some" rows="5" cols="30"/>  <br/>  <br/>

<@input lableText = ["Enter first name", "Enter last name"]
type = "text"
textOnTheLeftSide="true"
lableOnTheNewLine="false"
id = ["1"]/>

<@input lableText = ["yellow", "red", "black", "I don`t know"]
type = "checkbox"
textOnTheLeftSide="false"
lableOnTheNewLine="false"/>

<@customSelect options = ["winter", "asprint", "summer", "autmn"] value = ["winter", "asprint"] id = "3"/>