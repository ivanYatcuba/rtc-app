<form name="searchFilter" id="searchFilter" action="<@spring.url "/admin/course/filter"/>" method="gets">
    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span6">
        <@spring.formItem "searchFilter.title"/>
        <@spring.bind "categories" />
        <@spring.bind "searchFilter.categories" />
        <@spring.message "searchFilter.categories"/><br/>
        <#--<@spring.formSingleSelect "searchFilter.categories"/>-->
            <select name="categories" >
                <option/>
            <#list categories as cat>
                <#assign isSelected = false>
                <#if searchFilter.categories??>
                    <#list searchFilter.categories as ct>
                        <#if ct == cat>
                            <#assign isSelected = true>
                        </#if>
                    </#list>
                </#if>
                <option value="${cat}"  ${isSelected ?string("selected", "")} />${cat}<br>
            </#list>
            </select>
        <@spring.formItem "searchFilter.author" />
        </div>

        <div class="span5">
        <@spring.formItem "searchFilter.startDate" "datepiker" />
        <@spring.bind "statuses" />
        <@spring.bind "searchFilter.status" />
        <@spring.message "searchFilter.status"/><br/>
            <select name="status">
            <option value="">ALL</option>
            <#list statuses as stat>
                <#assign isSelected = false>
                <#if searchFilter.status??>
                    <#if searchFilter.status == stat>
                        <#assign isSelected = true>
                    </#if>
                </#if>
                <option value="${stat}" ${isSelected ?string("selected", "")}/>${stat}<br>
            </#list>
            </select>
        <@spring.formItem "searchFilter.tags" "tag" />
        </div>
    </div>

    <div class="span10" style="text-align: right">
        <br>
        <hr>
        <br>
        <input type="submit" class="btn" value="Search"/> or <a
            href="<@spring.url "/admin/course" />">Reset</a>
    </div>

</form>