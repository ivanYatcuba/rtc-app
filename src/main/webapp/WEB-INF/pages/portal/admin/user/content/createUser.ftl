 <div class="row-fluid">
    <div class="span6" >
        <!--Email-->
        <div class="span6">
            <div><label for="user.email">* <@spring.message "user.email"/>: </label>
            <@spring.formInput "user.email" 'style="background-color: #FFFACD;"' /></div>
        </div>
        <!--Authorities-->
        <div><label  class="control-label" for="type"><@spring.message "user.role"/></label>
            <div class="controls">
                <select id="selectedRole" name="selectedRole">
                    <#list roles as role>
                        <option value="${role}">${role}</option>
                    </#list>
                </select>
            </div>

        </div>
    </div>

     <!--Password-->
     <div class="span6">
         <div>
             <div><label for="pass"><@spring.message "user.password"/>: </label>
             <@spring.formInput "user.password" 'id="password" name="password" class="st-input" style="background-color: #FFFACD;"' 'password'/><br/>
                 <input onchange="if ($('#password').get(0).type=='password') $('#password').get(0).type='text'; else $('#password').get(0).type='password';" name="fff" type="checkbox" value="false" style="margin: 0px 0px 0px;">  <@spring.message "user.showPassword"/>
             </div>
         </div>
     </div>
</div>
<hr/>

 <!--Gender-->
 <div class="row-fluid span12" style="margin-left: 1px">
     <div class="span6">
         <div style="margin-bottom: 10px;">
             <label for="user.gender"><@spring.message "user.gender"/>:</label>
         <@spring.bind "user.gender"/>
             <input type="radio" name="gender" id="optionsRadios1" value="Male" checked
                    style="margin: -3px 0px 0px;"> <@spring.message "user.genderM"/>
             <input type="radio" name="gender" id="optionsRadios2" value="Female"
                    style="margin: -3px 0px 0px;"> <@spring.message "user.genderF"/>

         </div>
     </div>
 </div>

<div class="row-fluid span12" style="margin-left: 1px">
    <div class="span6">
        <!--Last Name-->
        <div><label for="user.surname">* <@spring.message "user.surname"/>: </label>
        <@spring.formInput "user.surname" 'style="background-color: #FFFACD;"'/></div>
        <!--Middle Name -->
        <@spring.formItem "user.middleName"/>
        </div>

        <div class="span6">
        <!--First Name-->
        <div><label for="user.name">* <@spring.message "user.name"/>: </label>
        <@spring.formInput "user.name" 'style="background-color: #FFFACD;"'/></div>

        <!--Birth Date-->
        <div><label for="user.birthDate">* <@spring.message "user.birthDate"/>: </label>
        <@spring.formDatepicker "user.birthDate" 'style="background-color: #FFFACD;"'/></div>
    </div>
</div>

<hr/>

<div class="row-fluid span12" style="margin-left: 1px">
    <!--City-->
    <div class="span6"><@spring.formItem "user.city"/></div>

    <!--Phone-->
    <div class="span6">
        <div><label for="user.phone">* <@spring.message "user.phone"/>: </label>
        <@spring.formInput "user.phone" 'style="background-color: #FFFACD;"'/></div>
    </div>
</div>

<hr/>

<div class="row-fluid span12" style="margin-left: 1px">
     <!--Univercity-->
     <div class="span6"><@spring.formItem "user.university"/>
     <!--Faculty-->
     <@spring.formItem "user.faculty"/></div>
     <!--Speciality-->
     <div class="span6"><@spring.formItem "user.speciality"/></div>
</div>

<hr/>

<div class="row-fluid span12" style="margin-left: 1px">
    <!--Languages-->
    <div class="span6"><@spring.formItem "user.programmingLanguages"/></div>

    <!--English-->
    <div class="span6">
        <div><label for="user.english">* <@spring.message "user.english"/>: </label>
        <@spring.formSingleSelect "user.english", ["Basic", "Intermediate", "Advanced"], 'style="background-color: #FFFACD;"'/>
        </div>
    </div>
</div>

<hr/>

 <!--Note-->
 <div class="row-fluid span12" style="margin-left: 1px">
     <div class="span12">
         <div><label for="user.note">* <@spring.message "user.note"/>: </label>
         <@spring.formTextarea "user.note" 'style="width:700px; background-color: #FFFACD;"' />
         </div>
     </div>
 </div>


 <@spring.formValidation formName="user" jsonRules="${validationRules}"/>