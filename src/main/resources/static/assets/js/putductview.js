/**
 * Created by macbookair on 2018/4/12.
 */

$(function () {
    $(".insertshopcartbtn").on("click", function () {
        var pid= [[${message}]]
        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "http://localhost:8080/user/insertshopcart",
            "method": "POST"

        };

        $.ajax(settings).done(function (response) {
            console.log("");
        });
    })

}