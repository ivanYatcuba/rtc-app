<#import "layout/layout.ftl" as layout/>
<@layout.layout>
<div style="margin-top: 10%" align="center">
    <#if error??>
        <div class="row">
            <div class="alert alert-danger col-md-6 col-md-offset-3" role="alert">
                Your login attempt was not successful, try again.<br/> Caused :
            ${Session["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </div>
    </#if>
    <form name="f" action="<@spring.url "/j_spring_security_check"/>" method= "POST">
        <div>
            <div class="message" style="margin-right: 140px"><h4>Please sign in</h4></div>
            <div>
                <input class="form-control" id="username" name="j_username" style="width:250px;height:35px;margin-bottom: 5px" placeholder="E-mail" type="text">
            </div>
            <div style="margin-bottom: 10px">
                <input class="form-control" id="password" name="j_password" style="width:250px;height:35px" placeholder="Password" id="password" type="password">
            </div>
        </div>
        <div>
            <div class="remember" style="margin-bottom: 10px"><input id="showPassword"
                                       onchange="if ($('#password').get(0).type=='password') $('#password').get(0).type='text'; else $('#password').get(0).type='password';"
                                       name="showPassword" type="checkbox" value="false"
                                       style="margin: 0px 0px 0px;">  <@spring.message "user.showPassword" />
                <input type="checkbox" name="_spring_security_remember_me" style="vertical-align: top">Remember me
            </div>
            <input class="btn btn-primary" style="width:250px;height:35px" id="signin_submit" value="Sign in" type="submit" name="submit">
        </div>
    </form>
</div>
</@layout.layout>
