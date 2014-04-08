
<h2>Create User</h2>

 <div class="row-fluid span12" style="margin-left: 1px">

            <div class="span6" >
    <form class="form-horizontal">
                <@spring.formItem "user.email"/>
                </form>
                <form class="form-horizontal">
                <div><label  class="control-label" for="type"><@spring.message "user.role"/></label>
                <@spring.bind "roles" />
                <div class="controls">
                <@spring.formSingleSelect "user.authorities", roles, " "/></div></div></form>
                <@spring.formItem "user.birthDate" "datepiker" 'class="input-medium"'/>
               <hr align="center" width="800" size="1" />
            </div>

            <div class="span5">

                <form class="form-horizontal">
                     <div class="control-group">
                         <label class="control-label" for="inputPassword">*Password:</label>
                         <div class="controls">
                         <input id="pass" class="st-input" type="password" alt="" placeholder="" /> <br><br>
                         <input onchange="if ($('#pass').get(0).type=='password') $('#pass').get(0).type='text'; else $('#pass').get(0).type='password';" name="fff" type="checkbox" value="false">Show password.
                         </div>
                     </div>
                </form>
             <!--<div><label for="course.author.firstName">
                <@spring.message "courses.author"/> </label>
                <@spring.formInput "course.author.firstName" 'class="input-medium"'/>
                <@spring.formInput "course.author.lastName" 'class="input-medium"'/>
                <@spring.showErrors "<br>" /></div>
                <@spring.formItem "course.author.email"/>
                <@spring.formItem "course.tags" "tags"/>-->
            </div>

    </div>



</body>

</html>
