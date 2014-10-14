<form class="form-horizontal" id="news" name="news"
    action="<@spring.url '/admin/news/save'/>" method="post">
    <div>
        <h4 class="page-header"><@spring.message "news.create"/></h4>
    </div>
<#include "newsForm.ftl" />
    <div class="row">
        <div class="col-md-12" style="text-align: right">
            <input type="submit" class="btn btn-primary" value="Create" />
            <button type="button" onclick="location.href='<@spring.url '/admin/news/list'/>'" class="btn btn-default">Cancel</button>
        </div>
    </div>
</form>
