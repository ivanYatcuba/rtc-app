<div class="row-fluid span12" style="margin-left: 1px">
    <div><h3>User details</h3></div>
    <div class="span6">
        <div class="row">
            <div class="col-md-3">
                <div align="right"><@spring.message "user.email"/>:</div>
            </div>
            <div class="col-md-9">
                <div align="left">${user.email}</div>
            </div>
        </div>
        <hr width="100%">
        <div class="row">
            <div class="col-md-3">
                <div align="right"><@spring.message "user.gender"/>:</div>
                <div align="right"><@spring.message "user.surname"/>:</div>
                <div align="right"><@spring.message "user.middleName"/>:</div>
            </div>
            <div class="col-md-3">
                <div align="left">${user.gender}</div>
                <div align="left">${user.surname}</div>
                <div align="left">${user.middleName}</div>
            </div>
            <div class="col-md-3">
                <br>
                <div align="right"><@spring.message "user.name"/>:</div>
                <div align="right"><@spring.message "user.birthDate"/>:</div>
            </div>
            <div class="col-md-3">
                <br>
                <div align="left">${user.name}</div>
                <div align="left">${user.birthDate?date}</div>
            </div>
        </div>
        <hr width="100%">
        <div class="row">
            <div class="col-md-3">
                <div align="right"><@spring.message "user.city"/>:</div>
            </div>
            <div class="col-md-3">
                <div align="left">${user.city}</div>
            </div>
            <div class="col-md-3">
                <div align="right"><@spring.message "user.phone"/>:</div>
            </div>
            <div class="col-md-3">
                <div align="left">${user.phone}</div>
            </div>
        </div>
        <hr width="100%">
        <div class="row">
            <div class="col-md-3">
                <div align="right"><@spring.message "user.university"/>:</div>
                <div align="right"><@spring.message "user.faculty"/>:</div>
            </div>
            <div class="col-md-3">
                <div align="left">${user.university}</div>
                <div align="left">${user.faculty}</div>
            </div>
            <div class="col-md-3">
                <div align="right"><@spring.message "user.speciality"/>:</div>
            </div>
            <div class="col-md-3">
                <div align="left">${user.speciality}</div>
            </div>
        </div>
        <hr width="100%">
        <div class="row">
            <div class="col-md-3">
                <div align="right"><@spring.message "user.progLanguages"/>:</div>
            </div>
            <div class="col-md-3">
                <div align="left">${user.progLanguages}</div>
            </div>
            <div class="col-md-3">
                <div align="right"><@spring.message "user.english"/>:</div>
            </div>
            <div class="col-md-3">
                <div align="left">${user.english}</div>
            </div>
        </div>
        <hr width="100%">
        <div class="row">
            <div class="col-md-3">
                <div align="right"><@spring.message "user.note"/>:</div>
            </div>
            <div class="col-md-9">
                <div style="width:100%; word-wrap: break-word;" align="left">${user.note}</div>
            </div>
        </div>
        <hr width="100%">
    </div>
    <div class="row-fluid span12" style="margin-left: 1px">
        <div class="span5" style="text-align: right">

            <a href="<@spring.url "/user/edit/${user.id}"/>">
                <button class="btn">Edit</button>
            </a> or <a href="/user/view/${user.id}">Cancel</a>

        </div>
    </div>
</div>