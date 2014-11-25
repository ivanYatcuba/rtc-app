<#import "/spring.ftl" as spring/>
<div style="margin-top: 10%" align="center">
<#if error??>
    <div class="errorblock" style="color: red;border: 1px solid;">
        Your login attempt was not successful, try again.<br/> Caused :
    ${Session["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
</#if>
    <form name="f" action= <@spring.url "/j_spring_security_check"/> method= "POST">

    <div>
        <div class="message" style="margin-right: 140px"><h4>Please sign in</h4></div>
        <input class="form-control" id="username" name="j_username"
                  style="width:250px;height:35px" placeholder="E-mail"
                  type="text"></p>

        <p><input class="form-control" id="password" name="j_password"
                  style="width:250px;height:35px" placeholder="Password"
                  id="password"
                  type="password"></p>

    </div>
    <div>

        <p>
        <td colspan="2">
        </p>

        <p class="remember"><input id="showPassword"
                                   onchange="if ($('#password').get(0).type=='password') $('#password').get(0).type='text'; else $('#password').get(0).type='password';"
                                   name="showPassword" type="checkbox" value="false"
                                   style="margin: 0px 0px 0px;">  <@spring.message "user.showPassword" />
            <input type="checkbox" name="_spring_security_remember_me" style="vertical-align: top">Remember me
        </p>

        <input class="btn btn-primary" style="width:250px;height:35px" id="signin_submit"
                  value="Sign in" type="submit" name="submit">
    </div>
</div>

</form>
</div>
