<style>
    label {
        float: left;
        width: 13em;
        margin-right: 1em;
        text-align: right;
        font-size: 10pt;
        margin-bottom: 10px;
    }
</style>
<h3 class="page-header"><@spring.message "user.details"/></h3>

<div class="row">
    <div class="col-md-6">
        <label><@spring.message "user.email"/></label>

        <p>${user.email!" "}</p>
        <label><@spring.message "user.role"/></label>

        <p>
        <#list user.authorities as role>
        ${role}<#if role_has_next>,</#if>
        </#list>
        </p>
    </div>
</div>

<hr>

<div class="row">
    <div class="col-md-6">
        <label><@spring.message "user.gender"/></label><p>${user.gender!" "}</p>
    </div>
</div>

<div class="row">
    <div class="col-md-6">
        <label><@spring.message "user.surname"/></label>

        <p>${user.surname!" "}</p>
        <label><@spring.message "user.middleName"/></label>

        <p>${user.middleName!" "}</p>
    </div>
    <div class="col-md-6">
        <label><@spring.message "user.name"/></label>

        <p>${user.name!" "}</p>
        <label><@spring.message "user.birthDate"/></label>

        <p><#if user.birthDate??>${user.birthDate ? datetime ? string("yyyy-MM-dd")}</#if></p>
    </div>
</div>

<hr>
<div class="row">
    <div class="col-md-6">
        <label><@spring.message "user.city"/></label>

        <p>${user.city!" "}</p>
    </div>
    <div class="col-md-6">
        <label><@spring.message "user.phone"/></label>

        <p>${user.phone!" "}</p>
    </div>
</div>

<hr>
<div class="row">
    <div class="col-md-6"
    ">
    <label><@spring.message "user.university"/></label>

    <p>${user.university!" "}</p><br/>
    <label><@spring.message "user.faculty"/></label>

    <p>${user.faculty!" "}</p>
</div>
<div class="col-md-6">
    <label><@spring.message "user.speciality"/></label>

    <p>${user.speciality!" "}</p>
</div>
</div>

<hr>
<div class="row">
    <div class="col-md-6">
        <label><@spring.message "user.programmingLanguages"/></label>

        <p><#list user.programmingLanguages as progLang>
        ${progLang}<#if progLang_has_next>,</#if>
        </#list></p>
    </div>
    <div class="col-md-6">
        <label><@spring.message "user.english"/></label>

        <p>${user.english!" "}</p>
    </div>
</div>

<hr>
<div class="row">
    <div class="col-md-6">
        <label><@spring.message "user.note"/></label>

        <p>${user.note!" "}</p>
    </div>
</div>

<hr>
<div class="row">
    <div class="span12"  style="margin:10px 0px 80px 975px; ">
        <a href="<@spring.url "/admin/user/userPage/editPage/${user.code}" />">
            <button class="btn"><@spring.message "coursesPage.action.edit"/></button>
        </a> or
        <a href="<@spring.url "/admin/user/viewAll" />"><@spring.message "coursesPage.action.cancel"/></a>
    </div>
</div>
</div>
