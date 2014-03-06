<div id="content">
<form name="goHome" action="goHome" method="get" >
<div style="margin-left"> 
    <button class="btn btn-warning pull-right" type="submit"><@spring.message "btn.home"/></button>
 </div>
</form>

<center>
<br>
<form name="Logins" action="input"  method="post">

 <div class="label"><@spring.message "in.login"/></div> <br>
<INPUT type="text" name="login" maxlenght="35" SIZE="20" > <br>
 <div class="label"><@spring.message "in.password"/></div><br>
<INPUT type="PASSWORD" name="password" maxlength="35" size="20"> <br>
<INPUT class="btn btn-warning" type="submit" value=" <@spring.message "btn.enter"/> ">

</FORM>

<form action="registration" enctype="text/plain" method="get" >
 </form>

<form class="form-inline">

<label class="checkbox">
    <input type="checkbox">Запомнить меня<</label>
</form>
    
 <a href="#">Забыли пароль??</a>


</center>
</div>




