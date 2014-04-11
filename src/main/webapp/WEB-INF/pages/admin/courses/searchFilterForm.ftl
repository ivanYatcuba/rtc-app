
<form name="searchFilter" id="searchFilter" action="<@spring.url "/admin/course/filter"/>" method="gets">
    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6" >
            <@spring.formItem "searchFilter.title"/>
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
        </div>

        <div class="span5">
            <@spring.formItem "searchFilter.startDate" "datepiker" />
            <@spring.formItem "searchFilter.tag" "tag" />
        </div>
    </div>

    <a style="float: right" href="<@spring.url "/admin/course/create" />">
        <button type="submit" class="btn">Search</button>
    </a>
</form>