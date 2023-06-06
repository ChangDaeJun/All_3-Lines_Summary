<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" type="text/css" href="/resources/index.css">
    <title>All 3-line Summary</title>
</head>
<body>
<div id = "header">
    <div id="headerBox">
        <div id="logo"> All 3-Line </br>Summary</div>
        <div id="searchBox"></div>
    </div>
    <div id="menuBox">
        <ul id ="menuList">
            <li>News</li>
            <li>Post</li>
            <li>Ent</li>
            <li>Shopping</li>
            <li>Books</li>
        </ul>
    </div>
</div>
<div id = "body">
    <div id = "content">
        <div id ="today">2023.4.3. Today News</div>
    </div>
</div>
</body>
</html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    var total = 0;
    var cur = 0;
    $(document).ready(function() {
        $.ajax({
            url: '/news/list?page=0',
            method: 'GET',
            success: function(response) {
                total = response.total;
                cur += 1;
                var newsList = response.newsList;
                for (var i = 0; i < 10; i++) {
                    var body = newsList[i];
                    var images = newsList[i].images;
                    var title = body.title;
                    var URL = body.url;
                    var summary = body.summary;
                    var imageURL = "";
                    if(images != null){
                        imageURL = images[0].imageURL;
                    }

                    var newContent = $('<div class = "newsBox">' +
                        '<div class = "newsTitle">'+title+ '</div>' +
                        '<div class = "imageBox"><img src="'+imageURL+'"></div>' +
                        '<div class = "summary">'+summary+'</div>' +
                        '<div class = "AImodel">By NAVER CLOVA</div>' +
                        '</div>');
                    $('#content').append(newContent);
                }
            },
            error: function() {
                console.log('API 요청에 실패했습니다.');
            }
        });
    });

    $(document).ready(function() {
        $(window).scroll(function() {
            if ($(window).scrollTop() + $(window).height() >= $(document).height()) {
                loadMoreContent();
                cur += 1;
            }
        });

        function loadMoreContent() {
            $.ajax({
                url: '/news/list?page='+cur,
                method: 'GET',
                success: function(response) {
                    var newsList = response.newsList;
                    for (var i = 0; i < 10; i++) {
                        var body = newsList[i];
                        var images = newsList[i].images;
                        var title = body.title;
                        var URL = body.url;
                        var summary = body.summary;
                        var imageURL = "";
                        if(images != null){
                            imageURL = images[0].imageURL;
                        }

                        var newContent = $('<div class = "newsBox">' +
                            '<div class = "newsTitle">'+title+ '</div>' +
                            '<div class = "imageBox"><img src="'+imageURL+'"></div>' +
                            '<div class = "summary">'+summary+'</div>' +
                            '<div class = "AImodel">By NAVER CLOVA</div>' +
                            '</div>');
                        $('#content').append(newContent);
                    }
                },
                error: function() {
                    console.log('API 요청에 실패했습니다.');
                }
            });
        }
    });
</script>