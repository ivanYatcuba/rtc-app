<html>

<head>

    <title>Edit User</title>

</head>

<body>
<h3>Create User</h3>

<div class="row-fluid">
      <div class="span6">
           <form class="form-horizontal">
                <div class="control-group">
                     <label class="control-label" for="inputEmail">*Email:</label>
                     <div class="controls">
                      <input type="email" id="inputEmail" placeholder="Email" required>
                      </div>
                      </div>
                      <label class="control-label" for=inputRole">*Role:</label>
                      <div class="controls">
                      <select>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                      </select>
                      </div>
                      
                      
             </form>
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
<hr>
<div class="row-fluid">
      <div class="span6">
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
       <div>
</div>
<div class="row-fluid">
      <div class="span6">
            <form class="form-horizontal">
                 <div class="control-group">
                          <label class="control-label">*Last name:</label> 
                          <div class="controls">
                          <input type="text" id="inputLastName" placeholder="Last name" required>
                         </div>
                 </div>

                 <div class="control-group">
                          <label class="control-label">Middle name:</label> 
                          <div class="controls">
                          <input type="text" id="inputMidleName" placeholder="Middle name" required>
                         </div>
                 </div>
                 </form>
                </div>
             
             
      <div class="span6">
             <form class="form-horizontal">
               
                 
               
                <div class="control-group">
                         <label class="control-label">*First name:</label> 
                          <div class="controls">
                          <input type="text" id="inputFirstName" placeholder="First name:" required>
                         </div>
                 </div>
                 <div class="control-group">
                         <label class="control-label">*Birth date:</label> 
                          <div class="controls">
                          <input type="text" id="inputBirth date" placeholder="Birth date:" required>
                         </div>
                 </div>
             </form>

      </div>

</div>






</body>

</html>
