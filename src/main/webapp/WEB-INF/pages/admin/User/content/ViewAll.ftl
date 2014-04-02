<html>
<head>
  <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">
</head>
<body>
  <table id="example">
    <thead>
  <tr>
      <th>FIO</th>
       <th>Email</th>
        <th>Phone</th>
         <th>Register Date</th>
          <th>Role</th>
           <th>Action</th>
      </tr>
    </thead>
    <tbody>
      <#list users as x>
<tr>
      <#if (x.fio)??>
        <td>${x.fio}</td>
     <#else>
        <td>None</td>
    </#if>

    <#if (x.email)??>
        <td>${x.email}</td>
    <#else>
        <td>None</td>
    </#if>

    <#if (x.phone)??>
        <td>${x.phone}</td>
    <#else>
        <td>None</td>
    </#if>

    <#if (x.regDate)??>
        <td>${x.regDate}</td>
    <#else>
        <td>None</td>
    </#if>

    <#if (x.role)??>
        <td>${x.role}</td>
    <#else>
        <td>None</td>
    </#if>

    <td><a href="<@spring.url "/admin/User/delete/${x.id}" />">Remove</a></td>




</tr>
    </#list>
    </tbody>

  </table>
  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
  <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
  <script>
  $(function(){
    $("#example").dataTable();
  })
  </script>
</body>
</html>

