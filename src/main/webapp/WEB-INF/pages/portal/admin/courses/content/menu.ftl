<ul class="nav nav-sidebar">
    <li id="news"><a href="<@spring.url'/admin/news'/>">News</a></li>
    <li id="course"><a href="<@spring.url'/admin/course/'/>">Courses</a></li>
    <li id="user"><a href="<@spring.url'/admin/user/viewAll'/>">Users</a></li>
    <li id="export"><a href="<@spring.url'/admin/export/viewAll'/>">Reports</a></li>
</ul>
 <script type="text/javascript">
     $(function() {
         var pathname = window.location.pathname;
         if(pathname.indexOf("course") > -1){
             $("#course" ).addClass( "active" );
         }
         if(pathname.indexOf("user") > -1){
             $( "#user" ).addClass( "active" );
         }
         if(pathname.indexOf("export") > -1){
             $( "#export" ).addClass( "active" );
         }
         if(pathname.indexOf("news") > -1){
             $( "#news" ).addClass( "active" );
         }
     });
 </script>
