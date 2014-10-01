<#import "../../../../../datatables.ftl" as datatables/>
<div>
<table class="table" id="UserTable">
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
            <p>${news.author.name!" " + " " + news.author.surname!" "}</p>
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
</div>
<hr>
<@datatables.addPagination/>