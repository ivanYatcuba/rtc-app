
<h2>Create User</h2>

 <div class="row-fluid">

            <div class="span6" >
             <form class="form-horizontal">
                <@spring.formItem "user.email"/>
                </form>
                <form class="form-horizontal">
                <div><label  class="control-label" for="type"><@spring.message "user.role"/></label>
                <@spring.bind "roles" />
                <div class="controls">
                <@spring.formSingleSelect "user.authorities", roles, " "/></div></div></form>
                
            </div>

            <div class="span6">

                <form class="form-horizontal">
                     <div class="control-group">
                         <label class="control-label" for="inputPassword">*Password:</label>
                         <div class="controls">
                         <input id="pass" class="st-input" type="password" alt="" placeholder="" /> <br><br>
                         <input onchange="if ($('#pass').get(0).type=='password') $('#pass').get(0).type='text'; else $('#pass').get(0).type='password';" name="fff" type="checkbox" value="false">Show password.
                         </div>
                     </div>
                </form>
           
            </div>

    </div>

<hr/>
<div class="row-fluid">

            <div class="span6" >
             <form class="form-horizontal">
                <div class="control-group">
                         <label class="control-label">*Gender:</label>
                         <div class="controls">
                            <label class="radio inline">
                            <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>Male</label>
                            <label class="radio inline">
                           <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">Famale</label>
                           </div>
                 </div>
            </form>
             </div>
              <div class="span6">
              </div>

</div>

<div class="row-fluid">

            <div class="span6" >
             <form class="form-horizontal">
                 <@spring.formItem "user.lastName"/>
                 <br>
               <@spring.formItem "user.middleName"/>
                </form>
               
                
            </div>

            <div class="span6">

                <form class="form-horizontal">
                 <@spring.formItem "user.surname"/>
                 <br>
               <@spring.formItem "user.birthDate" "datepiker" 'class="input-medium"'/>
                </form>
           
            </div>

    </div>
<hr/>

<div class="row-fluid">

            <div class="span6" >
             <form class="form-horizontal">
                 <@spring.formItem "user.university"/>
                 <br>
               <@spring.formItem "user.faculty"/>
                </form>
               
                
            </div>

            <div class="span6">

                <form class="form-horizontal">
                 <@spring.formItem "user.speciality"/>
    
             
                </form>
           
            </div>

    </div>
<hr/>
<div class="row-fluid">

            <div class="span6" >
             <form class="form-horizontal">
                 <@spring.formItem "user.progLanguages"/>
               </form>
               
                
            </div>

            <div class="span6">
                <form class="form-horizontal">
                <div><label  class="control-label" for="type"><@spring.message "user.role"/></label>
                <@spring.bind "roles" />
                <div class="controls">
                <@spring.formSingleSelect "user.authorities", roles, " "/></div></div>
    
             
                </form>
           
            </div>

    </div>
<hr/>
 <form class="form-horizontal">
<div><label  class="control-label" for="type"><@spring.message "user.note"/></label>
 <@spring.formTagsInput "user.note"/></form>