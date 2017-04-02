
// This function is run when you click one of the buttons.
//It will also run a "GET" request when you load the homepage
function httpRequest(method, postId) {
    // Creates an http request.
    var xmlHttp = new XMLHttpRequest();

    // Depending on the request method, different links are used.
    if (method == "GET") {
        xmlHttp.open("GET", "http://localhost:8080/blogposts/", true);
    } else if (method == "POST") {
        xmlHttp.open("POST", "http://localhost:8080/blogposts?title=" + document.getElementById('blog_title').value +
            "&comment=" + document.getElementById('blog_text').value, true);
    } else if (method == "PUT") {

    } else if (method == "DELETE") {
        xmlHttp.open("DELETE", "http://localhost:8080/blogposts/" + postId, true);
    } else {
        return
    }

    xmlHttp.onload = function () {
        console.log('DONE', xmlHttp.readyState);
        console.log('STATUS', xmlHttp.status);
    };

    // When the http request is done and a response has been received, the blog post will be created below the text area.
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            var parsed = JSON.parse(this.responseText);

            var out = "";
            var i;
            for(i = parsed.length - 1; i >= 0; i--) {
                // The title and text of every post will be shown along with and edit and delete button.
                out += "<div class=\"blog-post\">" +  parsed[i].title + "<br><br>" + parsed[i].comment +
                        "<br><br><button type=\"button\" class=\"btn btn-default\" onclick=\"\">Edit</button>" +
                        "<button type=\"button\" class=\"btn btn-default\" onclick=\"httpRequest('DELETE', this.id)\" id=\"" +
                        parsed[i].id + "\">Delete</button><br></div>";

                // Adding a line under every post to separate them, unless it is the last one.
                if (i != 0) {
                    out += "<hr class=\"line\">";
                } else {
                    out += "<br>";
                }
            }
            document.getElementById('posts').innerHTML = out;
        }
    }

    xmlHttp.send(null)
}