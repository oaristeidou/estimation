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
      "url": 'data/basicAreaChart.json'
    });
  };
}

function view1Controller(appService, $scope) {
  $scope.chartObj; // this will contain a reference to the highcharts' chart object
  appService.getBasicAreaChart()
    .then(function (chart) {
      $scope.basicAreaChart = chart.data;
    });
}