<!-- FreeMarker Macros -->
<#import "/spring.ftl" as spring/>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.gitcdn.link/cdn/angular/bower-material/v1.0.7/angular-material.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular-animate.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular-route.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular-aria.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.7/angular-messages.min.js"></script>
    <script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/t-114/svg-assets-cache.js"></script>
    <script src="https://cdn.gitcdn.link/cdn/angular/bower-material/v1.0.7/angular-material.js"></script>

    <link rel="stylesheet" type="text/css" href="<@spring.url '/css/main.css'/>"/>
    <script src="<@spring.url '/script/main.js'/>"></script>
    <title>Angular UserItems Management</title>
</head>

<body ng-app="UserItemManagement"
      ng-controller="UserItemController as ctrl">

<section>

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
</section>
<section>
    <div class="example-chip-list" layout="column">
        <md-content class="md-padding" layout="column">
            <h2 class="md-title">Use <code>md-autocomplete</code> to build an ordered set of chips</h2>
            <md-chips ng-model="ctrl.selectedProducts"
                      md-autocomplete-snap=""
                      md-transform-chip="ctrl.transformChip($chip)"
                      md-require-match="ctrl.autocompleteRequireMatch">
                <md-autocomplete md-selected-item="ctrl.selectedItem"
                                 md-search-text="ctrl.searchText"
                                 md-items="item in ctrl.querySearch(ctrl.searchText)"
                                 md-item-text="item.title"
                                 placeholder="Search for a product">
                    <span md-highlight-text="ctrl.searchText">
                        {{item.title}}::{{item.id}}
                    </span>
                    <md-not-found>
                        <div class="chip-not-found"
                             ng-click="ctrl.notFound(ctrl.searchText)">
                            Not found. Click here to add new.
                        </div>
                    </md-not-found>
                </md-autocomplete>
                <md-chip-template>
                    <span>
                        <strong>{{$chip.title}}</strong>
                        <em>({{$chip.id}})</em>
                    </span>
                </md-chip-template>
            </md-chips>
        </md-content>
    </div>

</section>

</body>
</html>