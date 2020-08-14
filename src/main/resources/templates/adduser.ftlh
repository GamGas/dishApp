<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Add User</title>
<#--    <link rel="stylesheet"-->
<#--          type="text/css" href="<@spring.url '/css/style.css'/>"/>-->
</head>
<body>

<div>
    <fieldset>
        <legend>Add User</legend>
        <form name="useritem" action="" method="POST">
            <input name="username" type="text" class="text">
            <input name="password" type="text" class="text">
            <input type="submit" value="Create"/>
        </form>
    </fieldset>
</div>


</body>

</html>