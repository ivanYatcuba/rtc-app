<form class="form-horizontal" name="news" id="news"
      action="<@spring.url '/admin/news/edit' />" method="post">
<@spring.formHiddenInput "news.code" />
<@spring.formHiddenInput "news.id" />
    <div>
        <h4 class="page-header"><@spring.message "news.edit"/></h4>
    </div><br/>
<#include "newsForm.ftl" />
    <div class="row">
        <div class="col-md-12" style="text-align: right">
            <input type="submit" class="btn btn-primary" value="Save" />
            <button type="button" onclick="location.href='/admin/news/list'" class="btn btn-default">Cancel</button>
        </div>
    </div>
</form>
