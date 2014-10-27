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
        <div class="form-group">
            <label class="control-label" style="padding-top: 5px"
                   for="user.gender"><@spring.message "user.gender"/></label>
            <div  style="padding-top: 5px">
                <@spring.bind "user.gender"/>
                <input type="radio" name="gender" id="optionsRadios1"
                       value="Male" <#if user.gender == springMacroRequestContext.getMessage('user.genderM')>checked</#if>
                       style="margin: -3px 0px 0px;" disabled> <@spring.message "user.genderM"/>
                <input type="radio" name="gender" id="optionsRadios2"
                       value="Female" <#if user.gender == springMacroRequestContext.getMessage('user.genderF')>checked</#if>
                       style="margin: -3px 0px 0px;" disabled> <@spring.message "user.genderF"/>
            </div>
        </div>


    <div class="form-group">
        <label><@spring.message "user.surname"/></label>
        <div>
            <p class="form-control-static">
            <#if user.surname??>${user.surname}<#else>&nbsp</#if></p>
        </div>
    </div>

    <div class="form-group">
        <label><@spring.message "user.name"/></label>
        <div>
            <p class="form-control-static">
            <#if user.name??>${user.name}<#else>&nbsp</#if></p>
        </div>
    </div>

    <div class="form-group">
        <label><@spring.message "user.middleName"/></label>
        <div>
            <p class="form-control-static">
                <#if user.middleName??>${user.middleName}<#else>&nbsp</#if></p>
        </div>
    </div>

    <div class="form-group">
        <label><@spring.message "user.birthDate"/></label>
        <div>
            <p class="form-control-static">
                <#if user.birthDate??>${user.birthDate ? date ? string("dd-MMM-yyyy")}</#if></p>
        </div>
    </div>
    </div>

    <div class="col-md-6" style="padding-top: 10px">

        <div>
        <#if user.photo??>
            <img id="Img" src="/PathToPhotos/${user.photo}"  class="round-image"/>
        <#else>
            <img src = "<@spring.url '/resources/images/errorCat.jpg'/>"  class="round-image">
        </#if>
        </div>

    </div>
</div>

<hr>

<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label><@spring.message "user.email"/></label>
            <div>
                <p class="form-control-static">
                    <#if user.email??>${user.email}<#else>&nbsp</#if></p>
            </div>
        </div>

        <div class="form-group">
            <label><@spring.message "user.role"/></label>
            <div>
                <p class="form-control-static">
                <#list user.authorities as role>
                <@spring.message "${role}"/><#if role_has_next>,</#if>
                </#list>
            </div>
        </div>
    </div>
</div>

<hr>

<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label><@spring.message "user.city"/></label>
            <div>
                <p class="form-control-static">
                <#if user.city??>${user.city}<#else>&nbsp</#if></p>
            </div>
        </div>
    </div>

    <div class="col-md-6">
        <div class="form-group">
            <label><@spring.message "user.phone"/></label>
            <div>
                <p class="form-control-static">
                <#if user.phone??>${user.phone}<#else>&nbsp</#if></p>
            </div>
        </div>
    </div>
</div>

<hr>

<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label><@spring.message "user.university"/></label>
            <div>
                <p class="form-control-static">
                <#if user.university??>${user.university}<#else>&nbsp</#if></p>
            </div>
        </div>

        <div class="form-group">
            <label><@spring.message "user.faculty"/></label>
            <div>
                <p class="form-control-static">
                <#if user.faculty??>${user.faculty}<#else>&nbsp</#if></p>
            </div>
        </div>
    </div>

    <div class="col-md-6">
        <div class="form-group">
            <label><@spring.message "user.speciality"/></label>
            <div>
                <p class="form-control-static">
                <#if user.speciality??>${user.speciality}<#else>&nbsp</#if></p>
            </div>
        </div>
    </div>
</div>

<hr>

<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label><@spring.message "user.programmingLanguages"/></label>
            <div>
                <p class="form-control-static">
                <#list user.programmingLanguages as progLang>
                ${progLang}<#if progLang_has_next>,</#if>
                </#list>
            </div>
        </div>
    </div>

    <div class="col-md-6">
        <div class="form-group">
            <label><@spring.message "user.english"/></label>
            <div>
                <p class="form-control-static">
                <#if user.english??>${user.english}<#else>&nbsp</#if></p>
            </div>
        </div>
    </div>
</div>

<hr>

<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label><@spring.message "user.note"/></label>
            <div>
                <p class="form-control-static">
                <#if user.note??>${user.note}<#else>&nbsp</#if></p>
            </div>
        </div>
    </div>
</div>

<hr>

<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label><@spring.message "user.status"/></label>
            <div>
                <p class="form-control-static">
                <#if user.status=="ACTIVE">
                    <span class="label label-success">${user.status}</span>
                <#elseif user.status=="FOR_REMOVAL">
                    <span class="label label-danger">${user.status}</span>
                <#elseif user.status=="INACTIVE">
                    <span class="label label-warning">${user.status}</span>
                </#if>
                </p>
            </div>
        </div>

        <div class="form-group">
            <label><@spring.message "user.registerDate"/></label>
            <div>
                <p class="form-control-static">
                <#if user.registerDate??>${user.registerDate ? date ? string("dd-MMM-yyyy")}</#if></p>
            </div>
        </div>
    </div>
</div>

<hr>

<div class="row">
    <div class="span11"  align="right" style="margin:10px 0px 0px 0px; ">
        <a href="<@spring.url "/admin/user/userPage/editPage/${user.code}" />">
            <button class="btn"><@spring.message "coursesPage.action.edit"/></button>
        </a> or
        <a href="<@spring.url "/admin/user/viewAll" />"><@spring.message "coursesPage.action.cancel"/></a>
    </div>
</div>
