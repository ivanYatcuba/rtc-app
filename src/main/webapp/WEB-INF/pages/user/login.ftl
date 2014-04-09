
<#import "/spring.ftl" as spring/>
<div class="col-md-5 col-md-offset-5" style="margin-top: 10%">
    <form name="f" action= <@spring.url "/j_spring_security_check"/> method="POST">
 <script type="text/javascript" src="/resources/css/js/jquery.js"></script>


    <div>
        <span class="message"><b>Please sign in</b></span>
    </div>

    <div>
        <p><input id="username" name="j_username" style="width:250px;height:35px" value="Email"  type="text"> </p>

        <p><input id="password" name="j_password" style="width:250px;height:35px" value="Password" title="password"   type="password"> </p>
    </div>
    <div>
        <p> <input style="width:250px;height:35px" id="signin_submit" value="Sign in" type="submit" name="submit"></p>

        <p><td colspan="2"><input style="width:250px;height:35px" name="reset" type="reset" /></p>

        <p class="remember"> <input type="checkbox" id="chbxce">show password?
            <script language="JavaScript" type="text/javascript">
                $("#chbxce").change(function(){
                    if($(this).attr("checked")){


                        pas.type="text"
                    }else{
                        pas.type="password"

                    }});
            </script>
            <input type="checkbox" name="_spring_security_remember_me">remember me
        </p>
    </div>

    <#if error??>
        <div class="errorblock">
            Your login attempt was not successful, try again.<br /> Caused :
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </#if>
</div>

</form>
</div>
