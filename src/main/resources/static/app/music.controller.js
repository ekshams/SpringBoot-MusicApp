/**
 * Created by ekshams on 23/5/17.
 */
(function () {
    'use strict';
    angular.module('app').controller('MusicController',MusicController);

    MusicController.$inject = ['$http'];

    function MusicController($http) {
        var vm = this;
        vm.getAll = getAll;
        vm.getByLanguage = getByLanguage;
        vm.deleteAlbum = deleteAlbum;

        vm.albums = [];
        vm.all_albums = [];

        init();
        function init() {
            getAll();
        }
        function getAll() {
            var url = '/albums';
            var albumPromise = $http.get(url);
            albumPromise.then(function (response) {
                vm.albums = response.data;
                vm.all_albums = response.data;
            });
        }
        function getByLanguage(language) {
            if(language) {
                var url = '/albums/' + language;
                var albumPromise = $http.get(url);
                albumPromise.then(function (response) {
                    vm.albums = response.data;
                    console.log(response.data);
                });
            }
        }
        function deleteAlbum(id) {
         var url = '/albums/'+id;
         $http.delete(url).then(function (response) {
             vm.albums = response.data;
         });
        }
    }
    
})();
