<div class="row-fluid span12" style="margin-left: 1px">

    <!--Email-->
    <div class="span6">
        <div><label for="user.email">* <@spring.message "user.email"/>: </label>
            <@spring.formInput "user.email" 'style="background-color: #FFFACD;"' />
        </div>
                
        <!--Authorities-->

       
        	<div style="margin-top: 10px;"><label   for="type"><@spring.message "user.role"/></label>
              <select id="selectedRole" name="selectedRole">
                    <#list roles as role>
                        <option value="${role}">${role}</option>
                    </#list>
                </select>
          	</div>
      
    </div>
 
    <!--Password-->
    <div class="span6">
        <div>
            <div><label for="pass"><@spring.message "user.password"/>: </label>
                    <@spring.formInput "user.password" 'id="password" name="password" class="st-input" style="background-color: #FFFACD;"' 'password'/>
<br>
                <input onchange="if ($('#password').get(0).type=='password') $('#password').get(0).type='text'; else $('#password').get(0).type='password';" name="fff" type="checkbox" value="false" style="margin: 0px 0px 0px;">  <@spring.message "user.showPassword"/>
            </div>
        </div>
  	</div>
 </div>
 
<div class="row-fluid span12" style="margin-left: 1px;"><hr width="72%"></div>

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
            <@spring.formInput "user.surname" 'style="background-color: #FFFACD;"'/>
        </div>
        
                            
        <!--Middle Name -->
        <div style="margin-top: 10px;"><label for="user.middleName"><@spring.message "user.middleName"/>: </label>
              <@spring.formInput "user.middleName" />
        </div>
 	</div>

    <div class="span6">
        <!--First Name-->
        <div><label for="user.name">* <@spring.message "user.name"/>: </label>
              <@spring.formInput "user.name" 'style="background-color: #FFFACD;"'/>
         </div>

        <!--Birth Date-->
        <div><label for="user.birthDate">* <@spring.message "user.birthDate"/>: </label>
            <@spring.formDatepicker "user.birthDate" 'style="background-color: #FFFACD;"'/>
        </div>
    </div>
</div>

<div class="row-fluid span12" style="margin-left: 1px;"><hr width="72%"></div>

<div class="row-fluid span12" style="margin-left: 1px">
    <!--City-->
    <div class="span6">
       <div><label for="user.city"><@spring.message "user.city"/>: </label>
              <@spring.formInput "user.city" />
       </div>
    </div>   

    <!--Phone-->
    <div class="span6">
        <div><label for="user.phone">* <@spring.message "user.phone"/>: </label>
        <@spring.formInput "user.phone" 'style="background-color: #FFFACD;"'/></div>
    </div>
</div>

<div class="row-fluid span12" style="margin-left: 1px;"><hr width="72%"></div>

<div class="row-fluid span12" style="margin-left: 1px">
     <!--University-->
     <div class="span6">
       <div><label for="user.university"><@spring.message "user.university"/>: </label>
              <@spring.formInput "user.university" />
       </div>
            <!--Speciality-->
       <div style="margin-top: 10px;"><label for="user.speciality"><@spring.message "user.speciality"/>: </label>
              <@spring.formInput "user.speciality" />
        </div>
     
    </div>       
     
     <!--Faculty-->
     <div class="span6">
       <div><label for="user.faculty"><@spring.message "user.faculty"/>: </label>
              <@spring.formInput "user.faculty" />
       </div>
    </div> 
</div>

<div class="row-fluid span12" style="margin-left: 1px;"><hr width="72%"></div>

<div class="row-fluid span12" style="margin-left: 1px">
    <!--Languages-->
    <div class="span6">
       <div><label for="user.progLanguages"><@spring.message "user.progLanguages"/>: </label>
              <@spring.formInput "user.programmingLanguages" />
       </div>
    </div>       

    <!--English-->
    <div class="span6">
        <div><label for="user.english">* <@spring.message "user.english"/>: </label>
        <@spring.formSingleSelect "user.english", ["Basic", "Intermediate", "Advanced"], 'style="background-color: #FFFACD;"'/>
        </div>
    </div>
</div>

<div class="row-fluid span12" style="margin-left: 1px;"><hr width="72%"></div>

 <!--Note-->
 <div class="row-fluid span12" style="margin-left: 1px">
     <div class="span12">
         <div><label for="user.note">* <@spring.message "user.note"/>: </label>
         <@spring.formTextarea "user.note" 'style="width:700px; background-color: #FFFACD;"' />
         </div>
     </div>
 </div>


 <@spring.formValidation formName="user" jsonRules="${validationRules}"/>
