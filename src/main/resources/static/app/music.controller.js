(function () {
    'use strict';
    app.controller('MusicController',MusicController);

    MusicController.$inject = ['$http','createLanguages'];

    function MusicController($http, createLanguages) {
        var vm = this;
        vm.getAll = getAll;
        vm.getByLanguage = getByLanguage;
        vm.deleteAlbum = deleteAlbum;
        vm.refresh_album = refresh_album;
        vm.searchAlbum = searchAlbum;

        vm.albums = [];
        vm.songs = [];
        vm.all_albums = [];
        vm.languages = [];

        init();  //initialization
        function init() {
            getAll();
        }
        function getAll() {
            var url = '/albums';
            var albumPromise = $http.get(url);
            albumPromise.then(function (response) {
                vm.albums = vm.all_albums = response.data;
                vm.languages = createLanguages.getLanguages(vm.albums);
            });
        }
        function refresh_album() {
            vm.albums = vm.all_albums;
        }
        
        function getByLanguage(language) {
            if(language) {
                var album_list = vm.albums = vm.all_albums;
                vm.language_search = [];
                for(var i=0; i<vm.albums.length;i++){

                    if(album_list[i].language.localeCompare(language)===0){
                        vm.language_search.push(album_list[i]);
                    }
                }
                vm.albums = vm.language_search;

            }
        }
        function deleteAlbum(id) {
         var url = '/albums/'+id;
         $http.delete(url).then(function (response) {
             vm.albums = vm.all_albums = response.data;
             vm.languages = createLanguages.getLanguages(vm.albums);
         });
        }

        function searchAlbum(){
            vm.albums = vm.all_albums;
            var data = vm.searchData.toLowerCase();
            var search_res = [];9
            for(var i=0; i<vm.albums.length;i++){
                var str = vm.albums[i].title.toLowerCase();
                if(str.indexOf(data) > -1){
                    search_res.push(vm.albums[i]);
                }
            }
            vm.albums = search_res;
        }
    }

    
    app.controller('EditController',
        ['$http','fileUpload','$location','$scope','createLanguages','$routeParams',
            function($http, fileUpload,$location,$scope, createLanguages, $routeParams){
        var editctrl = this;

        editctrl.saveAlbum = saveAlbum;
        editctrl.saveSong = saveSong;
        editctrl.title = "";
        editctrl.logo = "";
        editctrl.language = "";
        editctrl.editMode = false;

        editctrl.song_title = "";
        editctrl.song_logo = "";
        editctrl.song_artist = "";
        editctrl.song_edit_mode = false;

        if($routeParams.id){
            editctrl.album_id = $routeParams.id;
            editctrl.editMode = true;
            var album_list = $scope.$parent.vm.albums;
            for(var i=0; i<album_list.length;i++){
                if(parseInt(album_list[i].id) === parseInt($routeParams.id)){
                    editctrl.title = album_list[i].title;
                    editctrl.language = album_list[i].language;
                    editctrl.current_logo = album_list[i].logo;
                    editctrl.myFile = album_list[i].logo;
                }
            }
        }

        function saveAlbum() {
            var data, url, albumPromise, logo;
            var file = editctrl.myFile;

            var uploadUrl = "/upload";
            fileUpload.uploadFileToUrl(file, uploadUrl);

            logo = file.name ? file.name : editctrl.current_logo;

            if(editctrl.editMode){
                data = {
                    "id":$routeParams.id,
                    "title": editctrl.title,
                    "logo":logo,
                    "language":editctrl.language
                };
                url = "/albums/"+$routeParams.id;
                albumPromise = $http.put(url, data);
            }else{
                data = {
                    "title": editctrl.title,
                    "logo":file.name,
                    "language":editctrl.language
                };
                url = "/albums/";
                albumPromise = $http.post(url, data);
            }
            albumPromise.then(function (response) {
                $scope.$parent.vm.albums =$scope.$parent.vm.all_albums = response.data;
                $scope.$parent.vm.languages = createLanguages.getLanguages(response.data);
                $location.path("/");
            });
        }

        if($routeParams.song_id){
            editctrl.song_edit_mode=true;
            var song_list = $scope.$parent.vm.songs;
            for(var i=0; i<song_list.length;i++){
                if(parseInt(song_list[i].id) === parseInt($routeParams.song_id)){
                    editctrl.song_title = song_list[i].title;
                    editctrl.song_artist = song_list[i].artist;
                    editctrl.current_logo = song_list[i].logo;
                    editctrl.song_logo = song_list[i].logo;
                }
            }
        }

        function saveSong() {
            var data, url, songPromise, logo;
            var file = editctrl.song_logo;

            var uploadUrl = "/upload";
            fileUpload.uploadFileToUrl(file, uploadUrl);
            logo = file.name ? file.name : editctrl.current_logo;

            if(editctrl.song_edit_mode){
                data = {
                    "id":$routeParams.song_id,
                    "title": editctrl.song_title,
                    "logo":logo,
                    "artist":editctrl.song_artist
                };
                url = "/albums/"+$routeParams.id+"/songs/"+$routeParams.song_id;
                songPromise = $http.put(url, data);
            }else{
                data = {
                    "title": editctrl.song_title,
                    "logo":file.name,
                    "artist":editctrl.song_artist
                };
                url = "/albums/"+$routeParams.id+"/songs";
                songPromise = $http.post(url, data);
            }
            songPromise.then(function (response) {
                $scope.$parent.vm.songs = response.data;

                $location.path("/album-detail/"+$routeParams.id);
            });
        }
    }]);

    app.controller('DetailController', DetailController);

    DetailController.$inject = ['$http','$routeParams','$scope'];
    function DetailController($http, $routeParams,$scope){
        var detailctrl = this;
        var albums = $scope.$parent.vm.all_albums;
        for(var i=0; i<albums.length;i++){
            if(parseInt(albums[i].id) === parseInt($routeParams.id)){
                detailctrl.id=albums[i].id;
                detailctrl.album_logo=albums[i].logo;
                detailctrl.album_title=albums[i].title;
                detailctrl.album_language=albums[i].language;
            }
        }

        detailctrl.getAllSongs = getAllSongs;
        detailctrl.deleteSong = deleteSong;
        getAllSongs($routeParams.id);

        function getAllSongs(album_id) {
            var url = '/albums/'+album_id;
            var albumPromise = $http.get(url);
            albumPromise.then(function (response) {
                detailctrl.songs = $scope.$parent.vm.songs = response.data;
            });

        }

        function deleteSong(song_id) {
            var album_id = $routeParams.id;
            var url = "/albums/"+album_id+"/songs/"+song_id;

            var albumPromise = $http.delete(url);
            albumPromise.then(function (response) {
                detailctrl.songs = $scope.$parent.vm.songs = response.data;
            })

        }

    }

    
})();
