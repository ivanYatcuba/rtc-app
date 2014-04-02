<html>
<head>
  <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">
</head>
<body>
  <table id="example">
    <thead>
  <tr><th>FIO</th>
 <th>Phone</th>
 <th>Email</th>
 <th>City</th>
 <th>University</th>
 <th>Faculty</th>
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
    <#if (x.phone)??>
        <td>${x.phone}</td>
    <#else>
        <td>None</td>
    </#if>
     <#if (x.email)??>
        <td>${x.email}</td>
    <#else>
         <td>None</td>
    </#if>
    <#if (x.city)??>
        <td>${x.city}</td>
    <#else>
        <td>None</td>
     </#if>
      <#if (x.university)??>
          <td>${x.university}</td>
      <#else>
          <td>None</td>
      </#if>
    <#if (x.faculty)??>
        <td>${x.faculty}</td>
    <#else>
        <td>None</td>
    </#if>
</tr>
    </#list>
    </tbody>
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

