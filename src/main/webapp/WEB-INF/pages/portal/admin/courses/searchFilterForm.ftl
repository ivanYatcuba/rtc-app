<form name="searchFilter" id="searchFilter" action="<@spring.url "/admin/course/filter"/>" method="gets">
    <div class="row">
        <div class="col-md-6">
        <@spring.formItem "searchFilter.title"/>
        <@spring.bind "searchFilter.categories" />
        <label for="categories"><@spring.message "searchFilter.categories"/></label>
        <#--<@spring.formSingleSelect "searchFilter.categories"/>-->
            <select id="categories" name="categories" >
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

        <div class="col-md-6">
        <@spring.formItem "searchFilter.startDate" "" "datepiker" />
        <@spring.bind "searchFilter.status" />
        <label for="status"><@spring.message "searchFilter.status"/></label>
            <select id="status" name="status">
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
        <@spring.formItem "searchFilter.tags" "" "tag" />
        </div>
    </div>

    <div class="col-md-10">
        <br>
        <hr>
        <br>
        <input type="submit" class="btn" value="Search"/> or <a
            href="<@spring.url "/admin/course" />">Reset</a>
    </div>

</form>