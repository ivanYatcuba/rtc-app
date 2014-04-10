<#import "/spring.ftl" as spring/>
<div class="col-md-5 col-md-offset-5" style="margin-top: 10%">
<#if error??>
    <div class="errorblock" style="color: red;border: 1px solid;">
        Your login attempt was not successful, try again.<br/> Caused :
    ${Session["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
</#if>
    <form name="f" action= <@spring.url "/j_spring_security_check"/> method=
    "POST">
    <scripttype="text/javascript" src="/resources/css/js/jquery.js"></script>
      <script src="//code.jquery.com/jquery-1.9.1.js"></script>

    <div>
        <span class="message"><b>Please sign in</b></span>
    </div>

    <div>
        <p><input id="username" name="j_username" style="width:250px;height:35px" value="Email" type="text"></p>

        <p><input id="password" name="j_password" style="width:250px;height:35px" value="Password" title="password"
                  type="password"></p>
    </div>
    <div>
        <p><input style="width:250px;height:35px" id="signin_submit" value="Sign in" type="submit" name="submit"></p>

        <p>
        <td colspan="2"><input style="width:250px;height:35px" name="reset" type="reset"/>
        </p>

                   <p class="remember"><input type="checkbox" id="ch">show password?
                       <script language="JavaScript" type="text/javascript">
                           $("#ch").change(function(){
                           
                               if (ch.checked) {
                                  password.type = "text"
                               } else {
                                   password.type = "password"

                               }});
                       </script>
            <input type="checkbox" name="_spring_security_remember_me">remember me
        </p>
    </div>
</div>

</form>
</div>
