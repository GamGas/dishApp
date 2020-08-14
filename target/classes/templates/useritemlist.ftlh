<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>UserItem List</title>
</head>
<body>
<h3>Person List</h3>
<#--<a href="addPerson">Add Person</a>-->
<#--<br><br>-->
<div>

    <table border="1">
        <tr>
            <th>Username</th>
            <th>Password</th>
            <th>LocalDate</th>
        </tr>
        <#list useritems as useritem>
            <tr>
                <td>${useritem.getUsername()}</td>
                <td>${useritem.getPassword()}</td>
                <td>${useritem.getLocalDate()}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>