var app = angular
    .module("UserItemManagement",
        ['ngMaterial',
            'ngMessages',
            'material.svgAssetsCache'])
    .directive('mdChips', function ($timeout) {
        return {
            restrict: 'E',
            require: 'mdChips',
            link: function (scope, element, attributes, mdChipsCtrl) {
                var mouseUpActions = [];
                mdChipsCtrl.onInputBlur = function (event) {
                    this.inputHasFocus = false;
                    mouseUpActions.push((function () {
                        var chipBuffer = this.getChipBuffer();
                        if (chipBuffer !== "") {
                            this.appendChip(chipBuffer);
                            this.resetChipBuffer();
                        }
                    }).bind(this));
                };
                window.addEventListener('click', function (event) {
                    while (mouseUpActions.length > 0) {
                        var action = mouseUpActions.splice(0, 1)[0];
                        $timeout(function () {
                            $timeout(action);
                        });
                    }
                }, false);
            }
        }
    });

//Controller part
app.controller("UserItemController", function ($scope, $http) {
    var self = this;
    self.readonly = false;
    self.selectedItem = null;
    self.searchText = null;
    self.querySearch = querySearch;
    self.productsList = [];
    self.selectedProducts = [];
    self.numberChips = [];
    self.numberChips2 = [];
    self.numberBuffer = '';
    self.autocompleteRequireMatch = false;
    self.transformChip = transformChip;
    _loadProductList()
    self.notFound = function (key) {
        transformChip(key);
    }

    function transformChip(chip) {
        if (angular.isObject(chip)) {
            return chip;
        }
        return {
            id: 1,
            title: chip
        }
    }

    function querySearch(query) {
        return query
            ? self.productsList.filter(createFilterFor(query))
            : [];
    }

    function createFilterFor(query) {
        var lowercaseQuery = angular.lowercase(query);
        return function filterFn(product) {
            return (product._lowertitle.indexOf(lowercaseQuery) === 0);
        }
    }

    function _loadProductList() {
        $http({
            method: 'GET',
            url: '/products'
        }).then(
            function (res) { // success
                self.productsList = res.data.productsList;
                self.productsList.map(function (prod) {

                    prod._lowertitle = prod.title.toLowerCase();

                    return prod;
                });
            },
            function (res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }


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

