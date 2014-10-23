<#import "../../../../datatables.ftl" as datatables/>
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
                <p>${news.title!" "}</p>
            </td>
            <#if (news.authorCode)??>
                <td>
                    <p>${news.authorCode.name!" "} ${news.authorCode.surname!" "}</p>
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
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu"
                            data-toggle="dropdown">Action<span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                        <#if (news.status) == "DRAFT">
                            <li id = "" role="presentation"><a role="menuitem" tabindex="-1" href="#">Publish</a></li>
                        </#if>
                        <li id = "" role="presentation"><a role="menuitem" tabindex="-1" href="#">Delete</a></li>
                    </ul>
                </div>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
<hr>
<@datatables.addPagination/>