<#--<#import "../../../../fieldMacro.ftl" as formMacro/>-->
<form class="form-horizontal" id="newsForm" name="news"
    action="<@spring.url '/admin/news/save'/>" method="post">
    <div style="width: 85%">
    <div>
        <h4 class="page-header"><@spring.message "news.create"/></h4>
    </div>
<#include "newsForm.ftl" />
    <@formMacro.rtcSubmit "Create" "Cancel" "/admin/news"/>
    </div>
</form>