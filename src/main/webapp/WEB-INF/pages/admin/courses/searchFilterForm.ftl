

<form name="searchFilter" id="searchFilter" action="<@spring.url "/admin/courses/filter"/>" method="gets">
    <@spring.formItem "searchFilter.title"/>
    <@spring.formItem "searchFilter.startDate" "datepiker" />
    <@spring.bind "categories" />
    <@spring.bind "searchFilter.categories" />
    <#list categories as cat>
        <#assign isSelected = false>
        <#if searchFilter.categories??>
            <#list searchFilter.categories as ct>
                <#if ct == cat>
                    <#assign isSelected = true>
                </#if>
            </#list>
        </#if>
        <input type="checkbox" value="${cat}" name="categories" <#if isSelected> checked="checked"</#if>/>${cat}<br>
    </#list>
    <@spring.formItem "searchFilter.tags" "tags" />
    <button type="submit">Search</button>
</form>