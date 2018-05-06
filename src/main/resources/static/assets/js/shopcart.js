/**
 * Created by macbookair on 2018/4/19.
 */


function del(scid) {
    var sum = ($(".sum").html());
    console.log("原来的总价："+sum);
    var c = "." + scid + "dcp";
    var discountprice = parseInt($(c).html());
    console.log("当前产品的折扣价："+discountprice);
    var newsum = Number(sum) - Number(discountprice);
    var sumcount = parseInt($(".sumcount").html());
    var newsumcount = sumcount - 1;
    console.log(scid);
    var b = "." + scid;
    var pnumb = $(b).html();
    console.log(pnumb);
    console.log("新的总价为:" + newsum);
    var newpnumb = pnumb - 1;

    if (newpnumb == 0) {
        var settings1 = {
            "async": true,
            "crossDomain": true,
            "url": "http://localhost:8080/user/delshopcartproduct?scid=" + scid,
            "method": "POST"
        };

        $.ajax(settings1).done(function (response) {
            console.log(scid + "商品删除");
            $(".sum").html(newsum);
            $(".sumcount").html(newsumcount);
            var a = "#" + scid;
            $(a).hide();
        });
    }
    else {
        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "http://localhost:8080/user/reducepnumb?scid=" + scid + "&newpnumb=" + newpnumb,
            "method": "POST"
        };

        $.ajax(settings).done(function (response) {
            console.log(scid + "商品数减1");
            $(".sum").html(newsum);
            $(".sumcount").html(newsumcount);
            $(b).html("");
            $(b).html(newpnumb);

        });
    }

}
function add(scid) {
    var sum = $(".sum").html();
    console.log("原来的总价："+sum);
    var c = "." + scid + "dcp";
    var discountprice = ($(c).html());
    console.log("当前产品的折扣价："+discountprice);
    var newsum = Number(sum) + Number(discountprice);
    var sumcount = parseInt($(".sumcount").html());
    var newsumcount = sumcount + 1;
    console.log(scid);
    var b = "." + scid;
    var pnumb = parseInt($(b).html());
    console.log("取到的数量" + pnumb);
    var newpnumb = pnumb + 1;
    console.log("加一后的数量" + newpnumb);
    console.log("新的总价为:" + newsum);
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8080/user/addpnumb?scid=" + scid + "&newpnumb=" + newpnumb,
        "method": "POST"
    };

    $.ajax(settings).done(function (response) {
        console.log(scid);
        $(".sum").html(newsum);
        $(".sumcount").html(newsumcount);
        $(b).html("");
        $(b).html(newpnumb);
    });

}


function delAll(scid){
    var sum = ($(".sum").html());
    console.log("原来的总价："+sum);
    var c = "." + scid + "dcp";
    var discountprice = parseInt($(c).html());
    console.log("当前产品的折扣价："+discountprice);
    var newsum = Number(sum) - Number(discountprice);
    var sumcount = parseInt($(".sumcount").html());
    var newsumcount = sumcount - 1;
    console.log(scid);
    var b = "." + scid;
    var pnumb = $(b).html();
    console.log(pnumb);
    console.log("新的总价为:" + newsum);
    var newpnumb = pnumb - 1;
    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8080/user/delshopcartproduct",
        "method": "POST",
        "headers": {
            "Content-Type": "application/x-www-form-urlencoded",
            "Cache-Control": "no-cache",
            "Postman-Token": "2b065643-b094-4f46-af8d-707e61baeea2"
        },
        "data": {
            "scid": scid;
        }
    };

    $.ajax(settings).done(function (response) {
        console.log(response);
        console.log(scid + "商品删除");
        $(".sum").html(newsum);
        $(".sumcount").html(newsumcount);
        var a = "#" + scid;
        $(a).hide();
    });
}

// $(function () {
//     $(".deleteicon").onmouseup(function () {
//         $(".deleteicon").attr("src","/assets/img/close-3.png");
//     })
// });