<ul class="nav nav-sidebar">
    <li id="course"><a href="<@spring.url'/admin/course/'/>">Courses</a></li>
    <li id="user"><a href="<@spring.url'/admin/user/viewAll'/>">Users</a></li>
    <li id="export"><a href="<@spring.url'/admin/export/viewAll'/>">Reports</a></li>
</ul>
 <script type="text/javascript">
     $(function() {
         var pathname = window.location.pathname;
         if(pathname.contains("course")){
             $( "#course" ).addClass( "active" );
         }
         if(pathname.contains("user")){
             $( "#user" ).addClass( "active" );
         }
         if(pathname.contains("export")){
             $( "#export" ).addClass( "active" );
         }
     });
 </script>
