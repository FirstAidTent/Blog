$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/blogposts"
    }).then(function(data) {
       $('.blog-id').append(data[0].id);
       $('.blog-name').append(data[0].name);
       $('.blog-comment').append(data[0].comment);
    });
});