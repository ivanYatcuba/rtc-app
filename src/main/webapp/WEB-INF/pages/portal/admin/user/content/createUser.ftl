<div class="row">

    <!--Email-->
    <div class="col-md-6">
        <@spring.formItem  "user.email" "class=\"required\""/>
        <!--Authorities-->
        	<label   for="type"><@spring.message "user.role"/></label>
              <select id="selectedRole" name="selectedRole" class="required">
                    <#list roles as role>
                        <option value="${role}">${role}</option>
                    </#list>
              </select>
    </div>
 
    <!--Password-->
    <div class="col-md-6">
        <div class="controls">
            <label  for="password"><@spring.message "user.password"/></label>
            <@spring.formInput "user.password" "id=\"password\" class=\"required\"" "password"/>
        </div>
        <div class="controls">
            <label  for="check"></label>
            <input id="check" onchange="if ($('#password').get(0).type=='password') $('#password').get(0).type='text'; else $('#password').get(0).type='password';" name="fff" type="checkbox" value="false" style="margin: 0px 0px 0px;">  <@spring.message "user.showPassword"/>
        </div>
    </div>
</div>
    <hr />

<!--Gender-->
<div class="row">
    <div class="col-md-6">
            <label for="user.gender"><@spring.message "user.gender"/>:</label>
            <@spring.bind "user.gender"/>
             <input type="radio" name="gender" id="optionsRadios1" value="Male" checked
                    style="margin: -3px 0px 0px;"> <@spring.message "user.genderM"/>
             <input type="radio" name="gender" id="optionsRadios2" value="Female"
                    style="margin: -3px 0px 0px;"> <@spring.message "user.genderF"/>
    </div>
</div>

<div class="row">
    <div class="col-md-6">
        <!--Last Name-->
        <@spring.formItem  "user.surname" "class=\"required\""/>
        <!--Middle Name -->
        <@spring.formItem  "user.middleName" />
 	</div>

    <div class="col-md-6">
        <!--First Name-->
        <@spring.formItem  "user.name" "class=\"required\""/>
        <!--Birth Date-->
        <@spring.formItem "user.birthDate"  'class="input-medium required"' "datepiker"/>
    </div>
</div>

<hr />

<div class="row" >
    <div class="col-md-6">
        <!--City-->
        <@spring.formItem  "user.city" />
    </div>
    <div class="col-md-6">
        <!--Phone-->
        <@spring.formItem  "user.phone" "class=\"required\""/>
    </div>
</div>

<hr />

<div class="row">
     <div class="col-md-6">
        <!--University-->
        <@spring.formItem  "user.university" />
        <!--Speciality-->
        <@spring.formItem  "user.faculty" />

    </div>       

     <div class="col-md-6">
         <!--Faculty-->
     <@spring.formItem  "user.speciality" />
    </div> 
</div>

<hr/>

<div class="row" >
    <div class="col-md-6">
        <!--Languages-->
        <@spring.formItem "user.programmingLanguages" />
    </div>       

    <div class="col-md-6">
        <!--English-->
        <div><label for="user.english"><@spring.message "user.english"/></label>
        <@spring.formSingleSelect "user.english", ["Basic", "Intermediate", "Advanced"], 'style="background-color: #FFFACD;"'/>
        </div>
    </div>
</div>

<hr />

 <!--Note-->
 <div class="row">
     <div class="col-md-12">
         <label for="note"><@spring.message "user.note"/></label>
         <@spring.formTextarea "user.note" 'style="width:75%;" rows="3" maxlength="255" id=\"note\" class=\"required\"' />
     </div>
 </div>

 <@spring.formValidation formName="user" jsonRules="${validationRules}"/>
