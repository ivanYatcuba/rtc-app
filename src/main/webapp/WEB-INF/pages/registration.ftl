<html>
<#include "macro.ftl"/>
<head><title>Registration page</title>
<body>
<div id="header">

</div>

<div id="content">

    <fieldset>
        <legend>Personal data</legend>
        <form name="user" action="add.html" method="post">

        <@customInput inputLabel = ["Enter FIO"]
                                 inputType = "text"
                                 textOnTheLeftSide="true"/>
            <input type="submit" value="   Register  " />
        </form>
    </fieldset>
    <br/>


</div>
</body>
</html>