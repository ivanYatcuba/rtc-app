<style>
    label {
        float: left;
        width: 13em;
        text-align: right;
        margin-bottom: 0px;
    }
</style>
<div style="width: 85%">
<div>
    <h4 class="page-header"><@spring.message "news.details"/></h4>
</div>
<div class="row">
    <div class="col-md-6">
        <label><@spring.message "news.title"/></label>
        &nbsp${news.title}
    </div>
    <div class="col-md-6">
        <label><@spring.message "news.tags"/></label>
        <p>&nbsp${news.tags?join(", ")}</p>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <label><@spring.message "news.description"/></label>
        &nbsp${news.description}
    </div>
</div>
<hr/>
<div class="row">
<#if news.status=='DRAFT'>
<div class="col-md-12">
        <label><@spring.message "news.status"/></label>
        <span class="label label-warning">Draft</span>
    <br>
        <label><@spring.message "news.creationDate"/></label>${news.createDate}
</div>
    <#else>
    <div class="col-md-12">
            <label><@spring.message "news.status"/></label>
            <span class="label label-success">Published</span>
        <br>
            <label><@spring.message "news.publishDate"/></label>
            &nbsp${news.createDate}
    </div>
    </#if>
</div>
<hr/>
<div class="row">
    <div class="col-md-12" style="text-align: right">
        <a href="<@spring.url "/admin/news/${news.code}/edit" />">
            <button class="btn btn-primary"><@spring.message "action.edit"/></button>
        </a>
        <button type="button" onclick="location.href='<@spring.url '/admin/news'/>'" class="btn btn-default">Cancel</button>
    </div>
</div>
</div>