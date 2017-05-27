app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);

        $http.post(uploadUrl, fd, {
            withCredentials: true,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        })
            .success(function(){

            })

            .error(function(){

            });
    }
}]);

app.factory('createLanguages', function () {
    return{
        getLanguages: function (albums) {
            var languages = [];
            albums.forEach(function (item) {
                if(languages.indexOf(item.language) === -1){
                    languages.push(item.language);
                }
            });
            return languages;
        }
    }
});


