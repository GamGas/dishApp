<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
         
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/css/main.css'/>"/>
    <script src="<@spring.url '/script/main.js'/>"></script>
    <title>Angular UserItems Management</title>
</head>
<body ng-app="UserItemManagement" ng-controller="UserItemController">
<h3>CRUD Spring boot, Angular</h3>
<form ng-submit="submitUserItem()">
    <table border="0">
        <tr>
            <td>UserItem Id</td>
            <td>{{useritemForm.id}}</td>
        </tr>
        <tr>
            <td>UserItem username</td>
            <td><input type="text" ng-model="useritemForm.username"/></td>
        </tr>
        <tr>
            <td>UserItem password</td>
            <td><input type="text" ng-model="useritemForm.password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" class="blue-button"/>
            </td>
        </tr>
    </table>
</form>

<br/>
      <a class="create-button" ng-click="createUserItem()">Create UserItem</a>
      
<table border="1">
             
    <tr>
                    
        <th>UserItem id</th>
                    
        <th>UserItem Username</th>
                    
        <th>UserItem Password</th>
                    
        <th>Edit</th>
                    
        <th>Delete</th>
                 
    </tr>
             <!-- $scope.employees -->
             
    <tr ng-repeat="userItem in userItems">
                    
        <td> {{ userItem.id }}</td>
                    
        <td> {{ userItem.username }}</td>
                    
        <td>{{ userItem.password }}</td>
                    
        <td>
            <a ng-click="editUserItem(userItem)" class="edit-button">Edit</a>
                        
        </td>
               
        <td><a ng-click="deleteUserItem(userItem)" class="delete-button">Delete</a>
        </td>
                 
    </tr>
          
</table>
</body>
</html>