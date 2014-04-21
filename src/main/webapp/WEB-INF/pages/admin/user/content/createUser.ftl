 <div class="row-fluid">
            <div class="span6" >
                <div class="control-group">
                    <@spring.formItem "user.email"/>
                </div>
                <div><label  class="control-label" for="type"><@spring.message "user.role"/></label>
                    <@spring.bind "roles" />
                        <div class="controls">
                            <@spring.formSingleSelect "user.authorities", roles, " "/>
                        </div>
                </div>
           </div>
           <div class="span6">
                <div class="control-group">
                         <label class="control-label" for="inputPassword">*Password:</label>
                         <div class="controls">
               <input id="pass" name="password" class="st-input" type="password" alt="" placeholder="password" /> <br><br>
                         <input  onchange="if ($('#pass').get(0).type=='password') $('#pass').get(0).type='text'; else $('#pass').get(0).type='password';" name="password" type="checkbox">Show password.
                       
</div>
                     </div>
                </div>
    </div>
<hr/>
<div class="row-fluid">
            <div class="span6" >
                <div class="control-group">
                         <label class="control-label">*Gender:</label>
                         <div class="controls">
                            <label class="radio inline">
                            <input type="radio" name="gender" id="optionsRadios1" value="Male" checked>Male</label>
                            <label class="radio inline">
                           <input type="radio" name="gender" id="optionsRadios2" value="Female">Famale</label>
                           </div>
                 </div>
             </div>
              <div class="span6">
              </div>
</div>
<div class="row-fluid">
            <div class="span6" >
                 <@spring.formItem "user.surname"/>
                 <br>
               <@spring.formItem "user.middleName"/>
            </div>
            <div class="span6">
                 <@spring.formItem "user.name"/>
                 <br>
               <@spring.formItem "user.birthDate" "datepiker" 'class="input-medium"'/>
            </div>
    </div>
<hr/>

<div class="row-fluid">
            <div class="span6" >
                 <@spring.formItem "user.city"/>
            </div>
            <div class="span6">
                 <@spring.formItem "user.phone"/>
            </div>
    </div>
<hr/>

<div class="row-fluid">
            <div class="span6" >
                 <@spring.formItem "user.university"/>
                 <br>
               <@spring.formItem "user.faculty"/>
            </div>
            <div class="span6">
                 <@spring.formItem "user.speciality"/>
            </div>
    </div>
<hr/>
<div class="row-fluid">
            <div class="span6" >
                 <@spring.formItem "user.progLanguages"/>
            </div>
            <div class="span6">
                <div><label  class="control-label" for="type"><@spring.message "user.english"/></label>
                <@spring.bind "english" />
                <div class="controls">
                <@spring.formSingleSelect "user.english", english, " "/></div></div>
            </div>
    </div>
<hr/>
<div>
<label  class="control-label" for="type"><@spring.message "user.note"/></label>
 <@spring.formTagsInput "user.note"/>