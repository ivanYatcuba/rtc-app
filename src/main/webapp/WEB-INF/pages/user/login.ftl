<#import "/spring.ftl" as spring/>
<form name="f" action= <@spring.url "/j_spring_security_check"/> method="POST">
 
  <table>
  <tr>
   <span class="message"><b>Please sign in</b></span>

  </tr>
   <tr>
    <p><input id="username" name="j_username" style="width:250px;height:35px" value="Email"  type="text"> </p>
   </tr>
   <tr>
   <p><input id="password" name="j_password" style="width:250px;height:35px" value="Password" title="password"   type="password"></p>  
   </tr>
   <tr>
   <input style="width:250px;height:35px" id="signin_submit" value="Sign in" type="submit" name="submit">
   </tr>
   <tr>
    <td colspan="2"><input style="width:250px;height:35px" name="reset" type="reset" />
    </td>
   </tr>
   <tr>
   <p class="remember"> <input type="checkbox">show password? 
   <input type="checkbox" name="_spring_security_remember_me">remember me
   </p>
  </tr>
  </table>
  
 <#if error??>
  <div class="errorblock">
   Your login attempt was not successful, try again.<br /> Caused :
   ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
  </div>
 </#if>
 
 </form>
