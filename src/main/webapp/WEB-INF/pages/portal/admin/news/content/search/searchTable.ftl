<#import "../../../../../datatables.ftl" as datatables/>
<h3><@spring.message "news.search.result.page.header"/></h3>
<div>
    <table class="table" id="UserTable">
        <thead>
        <tr>
            <th><@spring.message "news.search.result.header.title"/></th>
            <th><@spring.message "news.search.result.header.author"/></th>
            <th><@spring.message "news.search.result.header.created.date"/></th>
            <th><@spring.message "news.search.result.header.status"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list news as news>
        <tr>
            <td>
                <a href="<@spring.url "/admin/news/${news.code}" />">${news.title}</a>
            </td>
            <#if (news.author)??>
                <td>
                    <p>${news.author.name!" "} ${news.author.surname!" "}</p>
                </td>
            <#else>
                <td></td>
            </#if>
            <td>
                <p>${news.createDate!" "}</p>
            </td>
            <#if (news.status)??>
                <#if (news.status)=="DRAFT">
                    <td style="vertical-align: middle">
                        <span class="label label-warning">Draft</span>
                    </td>
                <#else>
                    <td style="vertical-align: middle">
                        <span class="label label-success">Published</span>
                    </td>
                </#if>
            <#else>
                <td></td>
            </#if>

            <td>
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                            data-toggle="dropdown">
                        Action
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <#if news.status == "DRAFT" >
                            <li id="publicationLi" role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url'/admin/news/publish/${news.code}'/>">Publish</a></li>
                        </#if>
                        <li id="deleteLi" role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url'/admin/news/delete/${news.code}'/>">Delete</a></li>
                    </ul>
                </div>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
<hr>

<div class="row">
    <div class="col-md-6">
        <form  class="inline-box" style="margin: 0px"  name="createNews" action="<@spring.url"/admin/news/create"/>"method="get">
            <button  class="btn btn-primary" type="submit">Create New</button>
        </form>
    </div>
    <div class="col-md-6" style="text-align: right">
    <@datatables.addPagination/>
    </div>
</div>

