<html>

<head>
<#--<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>-->
  <title>User Page</title>
 </head>

<body>

    <p1>User Details</p1>
        <br>
        <br>

   <div class="row-fluid 12">
        <div class="span3" style="margin-left: 97px">

            <label>Email:${user.email}</label>

        </div>
         <br>
          <br>
       <div class="span3" style="margin-left: 100px">

          <label>Role:ROLE MF</label>

       </div>

   </div>

        <hr>

   <div class="row-fluid 12">

        <div class="span12" style="margin-left: 80px">
            <label>Gender:GENDER MF</label>

            <br>

            <div class="span3" style="margin-left: -15px">
            <label>Last Name:${user.surname}</label>
            </div>
            <div class="span7" style="margin-left: 180px">
            <label>First Name:${user.name}</label>
            </div>

        </div>


       <div class="span12" style="margin-left: 80px">
    <br>
           <div class="span3" style="margin-left: -28px">
               <label>Middle Name:${user.middleName}</label>
           </div>
           <div class="span7" style="margin-left: 180px">
               <label>BirthDate:${user.birthDate ? datetime ? string("yyyy-MM-dd")}</label>
           </div>

       </div>

   </div>

        <hr>

            <div class="row-fluid 12">

                <div class="span12" style="margin-left: 80px">
                <div class="span3" style="margin-left: 5px">
                    <label>City:${user.city}</label>
                </div>
                <div class="span7" style="margin-left: 180px">
                    <label>Phone:${user.phone}</label>
                </div>
        </div>
            </div>

        <hr>

    <div class="row-fluid 12">

        <div class="span12" style="margin-left: 80px">
            <div class="span3" style="margin-left: 5px">
                <label>Univercity:${user.university}</label>
            </div>
            <div class="span7" style="margin-left: 180px">
                <label>Speciality:${user.speciality}</label>
            </div>

            <div class="span3" style="margin-left: 0px">
                <br>
                <label>Faculty:${user.faculty}</label>
            </div>
        </div>

    </div>

        <hr>


    <div class="row-fluid 12">

        <div class="span12" style="margin-left: 80px">
            <div class="span3" style="margin-left: -70px">
                <label>Programming languages:${user.progLanguages}</label>
            </div>
            <div class="span7" style="margin-left: 180px">
                <label>English:${user.english}</label>
            </div>
        </div>
    </div>

        <hr>

        <div class="row-fluid 12">

            <div class="span12" style="margin-left: 0px">

                <label>Why do you want to join us:${user.note}</label>

            </div>


        </div>

        <hr>
<div class="span12" style="text-align: right">
    <form name ="editPage" action="editPage/${user.id}" method="get">
    <button class="btn btn-inverse" type="submit">Edit</button>
    </form>
    or
    <form name ="editPage" action="rtc-app/admin/user/viewAll" method="get">
    <button class="btn btn-link">Cancel</button>
    </form>
</div>

</body>

</html>