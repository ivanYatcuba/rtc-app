
<form name="searchFilter" id="searchFilter" action="<@spring.url "/admin/course/filter"/>" method="gets">
    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6" >
            <@spring.formItem "searchFilter.title"/>
            <@spring.bind "categories" />
            <@spring.bind "searchFilter.categories" />
            <@spring.message "searchFilter.categories"/><br/>
            <select>
                <#list categories as cat>
                    <#assign isSelected = false>
                    <#if searchFilter.categories??>
                        <#list searchFilter.categories as ct>
                            <#if ct == cat>
                                <#assign isSelected = true>
                            </#if>
                        </#list>
                    </#if>
                    <option value="${cat}" name="categories"/>${cat}<br>
                </#list>
            </select>
            <@spring.formItem "searchFilter.author" />
        </div>

        <div class="span5">
            <@spring.formItem "searchFilter.startDate" "datepiker" />
            <@spring.bind "status" />
            <@spring.bind "searchFilter.status" />
            <@spring.message "searchFilter.status"/><br/>
                <select>
                <#list status as stat>
                    <#assign isSelected = false>
                    <#if searchFilter.status??>
                        <#list searchFilter.status as st>
                            <#if st == stat>
                                <#assign isSelected = true>
                            </#if>
                        </#list>
                    </#if>
                    <option value="${stat}" name="status"/>${stat}<br>
                </#list>
                </select>
            <@spring.formItem "searchFilter.tags" "tag" />
        </div>
    </div>

    <div class="span10" style="text-align: right">
        <input type="submit" class="btn" value="Search"/> or <a
            href="<@spring.url "/admin/course" />">Reset</a>
    </div>

</form>