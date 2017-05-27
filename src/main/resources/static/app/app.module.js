var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider, $locationProvider){
    $locationProvider.html5Mode(true);
    $routeProvider
        .when('/',{
            templateUrl: '/views/album-list.html'

        })
        .when('/new-album',{
            templateUrl: '/views/edit-album.html',
            controller: 'EditController as editctrl'
        })
        .when('/edit-album/:id',{
            templateUrl: '/views/edit-album.html',
            controller: 'EditController as editctrl'
        })
        .when('/album-detail/:id',{
            templateUrl: '/views/album-detail.html',
            controller: 'DetailController as detailctrl'
        })
        .when('/album-detail/:id/new-song',{
            templateUrl: '/views/edit-song.html',
            controller: 'EditController as editctrl'
        })
        .when('/album-detail/:id/new-song/:song_id',{
            templateUrl: '/views/edit-song.html',
            controller: 'EditController as editctrl'
        })

        .otherwise(
            { redirectTo: '/'}
        );
});

