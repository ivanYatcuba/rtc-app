<#import "../../../../../datatables.ftl" as datatables/>
<#import "../../../../../fieldMacro.ftl" as formMacro/>
<h4><strong><@spring.message "news.search.result.page.header"/></strong></h4>
<div>
    <table width="100%" class="table" style="margin-bottom: 5px" id="NewsTable">
        <thead>
        <tr>
            <th><@spring.message "news.search.result.header.title"/></th>
            <th><@spring.message "news.search.result.header.author"/></th>
            <th><@spring.message "news.search.result.header.created.date"/></th>
            <th><@spring.message "news.search.result.header.status"/></th>
            <th></th>
        </tr>
        </thead>
        <#if news??>
        <#list news as news>
            <tr style="vertical-align: middle">
                <td style="vertical-align: middle">
                <a href="<@spring.url "/admin/news/${news.code}" />">${news.title}</a>
            </td>
            <#if (news.author)??>
                <td style="vertical-align: middle">
                    <p>${news.author.name!" "} ${news.author.surname!" "}</p>
                </td>
            <#else>
                <td style="vertical-align: middle"></td>
            </#if>
                <td style="vertical-align: middle">
                <p>${news.createDate!" "}</p>
            </td>
            <#if (news.status)??>
                <#if (news.status)=="DRAFT">
                    <td style="vertical-align: middle">
                        <@formMacro.rtcColorLabel "Draft" "label-warning"/>
                    </td>
                <#else>
                    <td style="vertical-align: middle">
                        <@formMacro.rtcColorLabel "Published" "label-success"/>
                    </td>
                </#if>
            <#else>
                <td style="vertical-align: middle"></td>
            </#if>

                <td style="vertical-align: middle">
                <div class="btn-group">
                    <button class="btn btn-default" type="button" style="width: 100px" id="dropdownMenu1"
                            data-toggle="dropdown">
                        Action</button>
                    <button type="button" class="btn btn-default dropdown-toggle" style="height: 34px" data-toggle="dropdown">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                    </button>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                        <#if (news.status??) && (news.status == "DRAFT") >
                            <li id="publicationLi" role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url'/admin/news/publish/${news.code}'/>">Publish</a></li>
                        </#if>
                        <li id="deleteLi" role="presentation"><a role="menuitem" tabindex="-1" href="<@spring.url'/admin/news/delete/${news.code}'/>">Remove</a></li>
                    </ul>
                </div>
            </td>
        </tr>
        </#list>
        </#if>
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

