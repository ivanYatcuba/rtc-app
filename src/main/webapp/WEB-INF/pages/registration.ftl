<html>

<#include "macro.ftl">
<#import "/spring.ftl" as spring/>
<head>
<#include "link.ftl">

</head>

<title><@spring.message "register"/></title>

<body>

<div id="content">


    <h2><@spring.message "register"/></h2><br>

       <h4> <@spring.message "fields.enter"/></h4>

<hr/>
                <h4><@spring.message "personalData"/></h4>

    <!--  The form that will be parsed by jQuery before submit  -->


                <!--name & birth row-->
                <div class="row-fluid span12">

                    <div class="container">

                        <div class = "span6">

                            <label for="fio"><@spring.message "fio"/></label>
                            <input type="text" id="fio" name="fio"/>

                        </div>
                       <div class="span6">

                           <label for="birthYear"><@spring.message "reg.birthDate"/> </label>
                           <input type="text" id="birthYear" name="birthYear"/>

                       </div>
                  </div>
              </div>

                      <!--town/phone/email row-->
                <div class="row-fluid span12">

                    <div class="container">

                        <div class="span4">

                            <label for="town"> <@spring.message "town"/> </label>
                            <input type="text" id="town" name="town"/>

                        </div>

                        <div class="span4">

                            <label for="phone"> <@spring.message "phone"/> </label>
                            <input type="text" id="phone" name="phone"/>

                        </div>

                        <div class="span4">

                            <label for="email"> <@spring.message "reg.email"/> </label>
                            <input type="text" id="email" name="email"/>

                        </div>

                   </div>

                 </div>
   - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  <!--Sorryy for this :(-->
                                        <h4> <@spring.message "education"/> </h4>

                <!-- university/Faculty/Speciality -->


            <div class="row-fluid span12">

                <div class="container">

                    <div class="span4">

                        <label for="university"> <@spring.message "university"/> </label>
                        <input type="text" id="university" name="university"/>

                    </div>

                    <div class="span4">

                        <label for="faculty"> <@spring.message "faculty"/> </label>
                        <input type="text" id="faculty" name="faculty"/>

                    </div>

                    <div class="span4">

                        <label for="speciality"> <@spring.message "speciality"/> </label>
                        <input type="text" id="speciality" name="speciality"/>

                    </div>

                </div>

            </div>
    - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  <!--Sorryy for this :(-->
    <h4> <@spring.message "progLanguage"/> </h4>

    <#--language dropdown-->
        <br>

<div class="btn-group">

    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">

    <@spring.message "progLanguage"/>

        <span class="caret"></span>

    </a>
        <ul class="dropdown-menu">
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
        <li>5</li>
        <li>6</li>
        <li>7</li>
        <li>8</li>
        <li>9</li>
        <li>10</li>
</ul>

</div>
    <br>
    - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  <!--Sorryy for this :(-->
    <h4> <@spring.message "language"/> </h4> <br>
    <h4> <@spring.message "language.mark"/> </h4><br>


    <div class="row-fluid span12">

        <div class="container">

            <div class = "span6">

                <div class="btn-group">

                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">

                    <@spring.message "language.write"/>

                        <span class="caret"></span>

                    </a>
                    <ul class="dropdown-menu">
                        <li>1</li>
                        <li>2</li>
                        <li>3</li>
                        <li>4</li>
                        <li>5</li>
                        <li>6</li>
                        <li>7</li>
                        <li>8</li>
                        <li>9</li>
                        <li>10</li>
                    </ul>


                </div>

            </div>
            <div class="span6">

                <div class="btn-group">

                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">

                    <@spring.message "language.spoken"/>

                        <span class="caret"></span>

                    </a>
                    <ul class="dropdown-menu">
                        <li>1</li>
                        <li>2</li>
                        <li>3</li>
                        <li>4</li>
                        <li>5</li>
                        <li>6</li>
                        <li>7</li>
                        <li>8</li>
                        <li>9</li>
                        <li>10</li>
                    </ul>


                </div>

            </div>
        </div>
    </div>




    - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  <!--Sorryy for this :(-->
<br>
    <h4> <@spring.message "reg.reason"/> </h4> <br>

   <div id="content">
    <textarea >


    </textarea>

   </div>



    - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  <!--Sorryy for this :(-->
<br>


                <div class="label"><@spring.message "reg.password"/></div>
                <br>
                <input type="password" id="password" name="password"/><br/>

    <form name="userForm" action="viewPage" method="post" id="register-form" novalidate="novalidate">

                <div style="margin-left:140px;">
                    <input class="btn btn-warning" type="submit" name="submit"value=<@spring.message "reg.register"/>></div>

    </form>

</body>
</html>
