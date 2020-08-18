var app = angular.module("UserItemManagement", []);

//Controller part
app.controller("UserItemController", function ($scope, $http) {

    $scope.userItems = [];
    $scope.useritemForm = {
        id: 1,
        username: "",
        password: ""
    };
    //now load the data from server
    _refreshUserItemData();

    // HTTP POST/PUT methods for add/edit userItem
    // Call: http://localhost:8080/users
    $scope.submitUserItem = function () {

        var method = "";
        var url = "";

        if ($scope.useritemForm.id == -1) {
            method = "POST";
            url = '/users';
        } else {
            method = "PUT";
            url = '/users/' + $scope.useritemForm.id;
        }

        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.useritemForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);

    };

    $scope.createUserItem = function () {
        _clearFormData();
    }

    // HTTP DELETE- delete employee by Id
    // Call: http://localhost:8080/employee/{empId}
    $scope.deleteUserItem = function (userItem) {

        $http({
            method: 'DELETE',
            url: '/users/' + userItem.id
        }).then(_success, _error);
    };

    // In case of edit
    $scope.editUserItem = function (userItem) {
        $scope.useritemForm.id = userItem.id;
        $scope.useritemForm.username = userItem.username;
        $scope.useritemForm.password = userItem.password;
    };

    // Private Method
    // HTTP GET- get all employees collection
    // Call: http://localhost:8080/employees
    function _refreshUserItemData() {
        $http({
            method: 'GET',
            url: '/users'
        }).then(
            function (res) { // success
                $scope.userItems = res.data.userItems;
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        _refreshUserItemData();
        _clearFormData();
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

    // Clear the form
    function _clearFormData() {
        $scope.useritemForm.id = -1;
        $scope.useritemForm.username = "";
        $scope.useritemForm.password = "";
    };
});