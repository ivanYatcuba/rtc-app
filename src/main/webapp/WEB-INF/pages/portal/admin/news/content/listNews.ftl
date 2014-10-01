<#import "../../../../datatables.ftl" as datatables/>

<style>
    hr {
        border-top:2px solid #e1dede
    }
</style>
<h4><@spring.message "news.search.result.page.header"/></h4>

<@datatables.datatable "/portal/admin/news/content/search/filterForm.ftl" "/portal/admin/news/content/search/searchTable.ftl" "/admin/news/search"/>

<#--<table class="table" id="UserTable">
    <thead>
    <tr>
        <th><@spring.message "news.search.result.header.title"/></th>
        <th><@spring.message "news.search.result.header.author"/></th>
        <th><@spring.message "news.search.result.header.created.date"/></th>
        <th><@spring.message "news.search.result.header.status"/></th>
    </tr>
    </thead>
    <tbody>
<#list news as news>
        <tr>
            <td>
                <p>${news.title!" "}</p>
            </td>
                <td>
                    <p>${news.author.name + " " + news.author.surname!" "}</p>
                </td>
            <td>
                <p>${news.createDate!" "}</p>
            </td>
            <td style="vertical-align: middle">
                <#if (news.status)=="DRAFT">
                    <span class="label label-warning">Draft</span>
                <#else>
                    <span class="label label-success">Published</span>
                </#if>
            </td>
            <td>
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
                        Action
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Regular link</a></li>
                        <li role="presentation" class="disabled"><a role="menuitem" tabindex="-1" href="#">Disabled link</a></li>
                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">Another link</a></li>
                    </ul>
                </div>
            </td>
        </tr>
</#list>
    </tbody>
</table>
<hr>-->
<div class="row">
    <div class="col-md-6" >
        <form  class="inline-box" style="margin:20" name="createNews" action="<@spring.url"/admin/news/create"/>"method="get">
            <button  class="btn btn-primary" type="submit">Create New</button>
        </form>
    </div>
    <#--<div class="col-md-6" >
        <ul class="pagination">
            <li><a href="#">&laquo;</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>-->
</div>

