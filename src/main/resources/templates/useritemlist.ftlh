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
            <th>Id</th>
            <th>Username</th>
            <th>Password</th>
            <th>LocalDate</th>
            <th>Dishes</th>
        </tr>
        <#list useritems as useritem>
            <tr>
                <td>${useritem.getId()}</td>
                <td>${useritem.getUsername()}</td>
                <td>${useritem.getPassword()}</td>
                <td>${useritem.getLocalDate()}</td>
                <#if useritem.getDishes()?has_content>
                <td>true</td>
                </#if>
            </tr>
        </#list>
    </table>

    <a href="adduser">Add new user</a>
</div>
</body>
</html>