<html>

<head>

    <meta charset="utf-8">
  <title>User Page</title>
  <style>
    .block1 {
    width: 940px;
    background: #fff;
    padding: 20px;
    padding-right: 0px;
    border: solid 1px black;
    float: top;
   }

   </style>

</head>

<!------------>
<body>
    <div class="block1">

        <b><p1>User Details</p1>
        <br>
        <br>
        <br>
        <label>Email: </label><label>ivanov@xyz.net</label>
        <br>
        <label>Role: </label><label>User</label>

        <hr>
        </b>
        <table width="940">
            <tr>
                <th><label>Gender: </label><label>Male</label></th>
                <th></th>
            </tr>
            <br>
            <tr></tr>

            <tr>
                <th>
                    <label>Last name: </label><label>Ivanov</label>
                </th>
                <th>
                    <label>First name: </label><label>Ivan</label>
                </th>
            </tr>

            <tr></tr>

            <tr>
                 <th>
                    <label>Middle name: </label><label>Ivanovich</label>
                 </th>
                 <th>
                    <label>Birthday: </label><label>10-10-1990</label>
                 </th>
            </tr>
        </table>

        <hr>

        <table width="940">
            <tr>
                <th>
                    <label>City: </label><label>Dnipropetrovsk</label>
                </th>
                <th>
                    <label>Phone: </label><label>06354747474747</label>
                </th>
            </tr>
        </table>

        <hr>

        <table width="940">
              <tr>
                 <th>
                     <label>University: </label><label>DNU</label>
                 </th>
                 <th>
                    <label>Spesiality: </label><label>Biologist</label>
                 </th>
              </tr>
              <tr>
                 <th>
                     <label>Faculty: </label><label></label>
                 </th>
                 <th>

                 </th>
              </tr>
        </table>
        <hr>
        <table width="940">
              <tr>
                  <th>
                       <label>Programming language: </label><label>Java, PHP</label>
                  </th>
                  <th>
                       <label>English: </label><label>Basic</label>
                  </th>
              </tr>

        </table>
        <hr>
        <table width="940">
              <tr>
                  <th>
                       <label>Why do you want to join us?: </label><label>Bla-bla</label>
                  </th>

                  <th>

                  </th>
              </tr>
              <tr>

              </tr>
        </table>
        <hr>
        <table width="940">
              <th width="900">
              </th>
              <tr>
                  <th width="900">
                  </th>
                  <th>
                       <button>Edit</button>
                  </th>
                  <th>
                       or
                  </th>
                  <th>
                       <link>Cancel</link>
                  </th>
              </tr>

        </table>
    </div>


    <script>
        $(function(){
            $("#example").dataTable();
        })
    </script>
</body>

</html>