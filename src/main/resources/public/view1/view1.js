'use strict';

angular
  .module('myApp.view1', ['ngRoute'])

  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view1', {
      templateUrl: 'view1/view1.html',
      controller: 'View1Ctrl'
    });
  }])

  .service('appService', appServiceInit)

  .controller('View1Ctrl', view1Controller);

function appServiceInit($http) {
  this.getBasicAreaChart = function () {
    return $http({
      "method": "get",
      "url": '/estimationHighChart'
    });
  };
}

function view1Controller(appService, $scope, $log) {
  $scope.chartObj; // this will contain a reference to the highcharts' chart object
  appService.getBasicAreaChart()
    .then(function (data) {
      $scope.basicAreaChart = data.data;
    }, function (error) {
      // pass the error the the error service
      var result = error.data;
      filter(result);
      $scope.basicAreaChart = result;
    });
}

function filter(obj) {
  $.each(obj, function(key, value) {
    if (key == 'type' && value !== null)
      obj[key] = value.toLowerCase();
    if (value === "" || value === null)
      delete obj[key];
    else if (typeof value !== 'undefined' && typeof value === 'object')
      filter(value);
    else if (typeof value !== 'undefined' && $.isArray(value))
      value.forEach(function (el){filter(el)});
  });
}