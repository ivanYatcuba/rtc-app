<h3 class="page-header"><@spring.message "course.details"/></h3>

<div class="row">
    <div class="col-md-6">
        <label>Email:</label><p>${user.email!" "}</p>
        <label>Role:</label>
        <p>
            <#list user.authorities as role>
            ${role}<#if role_has_next>,</#if>
            </#list>
        </p>
    </div>
</div>

<hr>

<div class="row">
    <label>Gender:</label><p>${user.gender!" "}</p>
</div>

<div class="row">
    <div class="col-md-6">
        <label>Last Name:</label><p>${user.surname!" "}</p>
        <label>Middle Name:</label><p>${user.middleName!" "}</p>
    </div>
    <div class="col-md-6">
        <label>First Name:</label><p>${user.name!" "}</p>
        <label>BirthDate:</label><p><#if user.birthDate??>${user.birthDate ? datetime ? string("yyyy-MM-dd")}</#if></p>
    </div>
</div>

<hr>
<div class="row">
    <div class="col-md-6">
        <label>City:</label><p>${user.city!" "}</p>
    </div>
    <div class="col-md-6">
        <label>Phone:</label><p>${user.phone!" "}</p>
    </div>
</div>

<hr>
<div class="row">
    <div class="col-md-6"">
        <label>Univercity:</label><p>${user.university!" "}</p>
        <label>Faculty:</label><p>${user.faculty!" "}</p>
    </div>
    <div class="col-md-6">
        <label>Speciality:</label><p>${user.speciality!" "}</p>
    </div>
</div>

<hr>
<div class="row">
    <div class="col-md-6">
        <label>Programming languages:</label>
        <p><#list user.programmingLanguages as progLang>
                ${progLang}<#if progLang_has_next>,</#if>
            </#list></p>
    </div>
    <div class="col-md-6">
        <label>English:</label><p>${user.english!" "}</p>
    </div>
</div>

<hr>
<div class="row">
    <div class="col-md-6">
        <p>Why do you want to join us: ${user.note!" "}</p>
    </div>
</div>

<hr>
<div class = "row">
    <div class="span12" align="right">
        <a href="<@spring.url "editPage/${user.code}" />"><button class="btn"><@spring.message "coursesPage.action.edit"/></button></a> or
        <a href="<@spring.url "/admin/user/viewAll" />"><@spring.message "coursesPage.action.cancel"/></a>
    </div>
</div>
</div>
