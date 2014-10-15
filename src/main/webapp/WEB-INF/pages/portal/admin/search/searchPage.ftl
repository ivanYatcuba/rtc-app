    <#import "../../../rtcmacroses.ftl" as rtcmacros/>
<div id="newsFilter" style="width: 100%; float: left; display: ">
    <#include "filters/newsSearchFilter.ftl"/>
</div>

<div id="userFilter" style="width: 100%; float: left; display: ">
<#include "filters/userSearchFilter.ftl"/>
</div>

<div id="courseFilter" style="display: ">
<#include "filters/courseSearchFilter.ftl"/>
</div>

<div id="newsFilter" style="display: ">
<#include "filters/reportSearchFilter.ftl"/>
</div>
    <div class="row" style="text-align: right">
        <input type="submit" class="btn btn-primary" value="Search"/> or <a class="btn-default"
                                                                            href="<@spring.url "/admin/user/viewAll" />">Reset</a>
    </div>
<div id="searchTable">

</div>

