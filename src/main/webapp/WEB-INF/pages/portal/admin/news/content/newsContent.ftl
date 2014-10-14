<div>
    <h4 class="page-header"><@spring.message "news.details"/></h4>
</div>
<div>
    <div class="col-md-6">
        <label><@spring.message "news.title"/></label>&nbsp${news.title}
    </div>
    <div class="col-md-6">
        <label><@spring.message "news.tags"/></label>&nbsp${news.tags?join(", ")}
    </div>
</div>
<div>
    <label><@spring.message "news.description"/></label>&nbsp${news.description}
</div>
<hr/>
<div>
    <#if news.status=='DRAFT'>
        <label><@spring.message "news.status"/></label><span class="label label-warning">Draft</span><br>
        <label><@spring.message "news.creationDate"/></label>&nbsp${news.createDate}
    <#else>
        <label><@spring.message "news.status"/></label><span class="label label-success">Published</span><br>
        <label><@spring.message "news.publishDate"/></label>&nbsp${news.createDate}
    </#if>
</div>
<hr/>
<div class="row">
    <div class="col-md-12" style="text-align: right">
        <a href="<@spring.url "/admin/news/${news.code}/edit" />">
            <button class="btn btn-primary"><@spring.message "action.edit"/></button>
        </a>
        <button type="button" onclick="location.href='<@spring.url '/admin/news/list'/>'" class="btn btn-default">Cancel</button>
    </div>
</div>