function getPosts() {
    // Creates an http request.
    var xmlHttp = new XMLHttpRequest();
    var scope = angular.element(document.body).scope();

    xmlHttp.open("GET", "http://localhost:8080/blogposts/", true);

    xmlHttp.onload = function () {
        console.log('DONE ', xmlHttp.readyState);
        console.log('STATUS', xmlHttp.status);
    };

    // When the http request is done and a response has been received, the blog post will be created below the text area.
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            scope.posts = JSON.parse(this.responseText);
            scope.$apply();
        }
    }

    xmlHttp.send(null)
}

function sendPost() {
    var xmlHttp = new XMLHttpRequest();
    var scope = angular.element(document.body).scope();
    var title = document.getElementById("blog_title");
    var text = document.getElementById("blog_text");

    xmlHttp.open("POST", "http://localhost:8080/blogposts?title=" + title.value + "&comment=" + text.value, true);

    xmlHttp.onload = function () {
        console.log('DONE ', xmlHttp.readyState);
        console.log('STATUS', xmlHttp.status);
    };

    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            scope.posts = JSON.parse(this.responseText);
            scope.$apply();
        }
    }

    xmlHttp.send(null)
}

function deletePost(postId) {
    // Creates an http request.
    var xmlHttp = new XMLHttpRequest();
    var scope = angular.element(document.body).scope();

    xmlHttp.open("DELETE", "http://localhost:8080/blogposts/" + postId, true);

    xmlHttp.onload = function() {
        console.log('DONE DELETE', xmlHttp.readyState);
        console.log('STATUS', xmlHttp.status);
    };

    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            scope.posts = JSON.parse(this.responseText);
            scope.$apply();
        }
    }

    xmlHttp.send(null)
}

function editPost() {

}

//myApp.animation('.fd', [function() {
//  return {
//    // make note that other events (like addClass/removeClass)
//    // have different function input parameters
//    enter: function(element, doneFn) {
//      jQuery(element).fadeIn(1000, doneFn);
//
//      // remember to call doneFn so that AngularJS
//      // knows that the animation has concluded
//    },
//
//    move: function(element, doneFn) {
//      jQuery(element).fadeIn(1000, doneFn);
//    },
//
//    leave: function(element, doneFn) {
//      jQuery(element).fadeOut(1000, doneFn);
//    }
//  }
//}]);