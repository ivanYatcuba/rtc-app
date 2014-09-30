<h4><@spring.message "news.search.result.page.header"/></h4>

<table width="100%" class="table" id="UserTable">
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
                    <p>${news.author!" "}</p>
                </td>
            <td>
                <p>${news.createDate!" "}</p>
            </td>
            <td>
                <p>${news.status!" "}</p>
            </td>
            <td></td>
        </tr>
</#list>
    </tbody>
</table>
<div>
    <a href="<@spring.url "/admin/news/create" />">Create new</a>
</div>
