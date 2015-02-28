<#import "layout/layout.ftl" as layout/>
<@layout.layout>
<div style="margin-top: 10%">
    <#if error??>
        <div class="row">
            <div class="alert alert-danger col-md-6 col-md-offset-3" role="alert">
                Your login attempt was not successful, try again.<br/>
                Caused :${Session["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </div>
    </#if>
    <div class="row  col-md-6 col-md-offset-4">
    <form name="loginForm" action="<@spring.url "/j_spring_security_check"/>" method= "POST" style="margin-left: 30px">
        <div class="row">
            <div class="col-md-4"><h3><@spring.message "login.signIn" /></h3></div>
            <div class="col-md-3" style="text-align: right; margin-top: 20px">
                <h5><a href="<@spring.url "/register" />" style="text-decoration: underline;"><@spring.message "login.signUp" /></a></h5></div>
        </div>
        <div class="row"><div class="col-md-7" style="margin-bottom: 10px;">
            <input class="col-md-3 form-control" id="username" name="j_username"
                   placeholder="<@spring.message "login.email" />" type="text" style="height: 38px;">
        </div></div>
        <div class="row"><div class="col-md-7" style="margin-bottom: 10px;">
            <input class="col-md-3 form-control" id="password" name="j_password"
                   placeholder="<@spring.message "login.password" />" type="password" style="height: 38px;">
        </div></div>
        <div class="row" style="margin-bottom: 5px">
            <div class="col-md-4">
            <input type="checkbox" name="showPassword" onchange="showPasswordLogin();">
                <@spring.message "login.showPassword" /></div>
            <div class="col-md-4">
            <input type="checkbox" name="_spring_security_remember_me">
                <@spring.message "login.rememberMe" /></div>
        </div>
        <div class="row"><div class="col-md-7">
            <input class="btn btn-primary" style="width: 100%; height: 40px" value="<@spring.message "login.signIn" />" type="submit"></div></div>
    </form>
    </div>
</div>
<script>
    function showPasswordLogin() {
        if ($('#password').get(0).type =='password') {
            $('#password').get(0).type='text';
        } else {
            $('#password').get(0).type='password';
        }
    }
</script>
</@layout.layout>
