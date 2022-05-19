var baseUrl = "http://localhost:8080/supermarket_war/";

function cashierPost(url, data, someFunction) {
    // $.ajax({
    //     url:baseUrl + url,
    //     type:"POST",
    //     data:JSON.stringify(data),
    //     contentType:"application/json; charset=utf-8",
    //     dataType:"json",
    //     success: function(result){
    //         if (result != null && result.code == 200) {
    //             someFunction(result.data);
    //         } else if (result != null && result.code == 401) {
    //             window.location.href = "home.html";
    //         } else {
    //             $.message({
    //                 message: result.msg,    //提示信息
    //                 duration: 3000,
    //                 type: 'warning',           //显示类型，包括4种：success.error,info,warning 默认info
    //                 showClose: false       //显示关闭按钮（默认：否）
    //             });
    //         }
    //     }});
    $.post(baseUrl + url, data, function (result) {
        if (result != null && result.code == 200) {
            someFunction(result.data);
        } else if (result != null && result.code == 401) {
            window.location.href = "home.html";
        } else {
            $.message({
                message: result.msg,    //提示信息
                duration: 3000,
                type: 'warning',           //显示类型，包括4种：success.error,info,warning 默认info
                showClose: false       //显示关闭按钮（默认：否）
            });
        }
    }, "json");
}

/**
 *判断是否是数字
 *
 **/

function isNumber(val) {
    // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除，
    if (val === "" || val == null) {
        return false;
    }
    if (!isNaN(val)) {
        //对于空数组和只有一个数值成员的数组或全是数字组成的字符串，isNaN返回false，例如：'123'、[]、[2]、['123'],isNaN返回false,
        //所以如果不需要val包含这些特殊情况，则这个判断改写为if(!isNaN(val) && typeof val === 'number' )
        return true;
    } else {
        return false;
    }
}

/**
 * 校验正负整数就返回true
 **/

function isIntNum(num) {
    if (!isNaN(num) && num % 1 === 0) {
        return true;
    } else {
        return false;
    }
}

function dateFtt(fmt, date) { //author: meizz
    let o = {
        "M+": date.getMonth() + 1,     //月份
        "d+": date.getDate(),     //日
        "h+": date.getHours(),     //小时
        "m+": date.getMinutes(),     //分
        "s+": date.getSeconds(),     //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds()    //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//获取地址栏里（URL）传递的参数
function getRequest(localUrl) {
    console.log(localUrl);
    //url例子：XXX.aspx?ID=" + ID + "&Name=" + Name；
    let url = localUrl; //获取url中"?"符以及其后的字串
    let addressParameter = {};
    if (url.indexOf("?") != -1) {//url中存在问号，也就说有参数。
        let str;
        if (url.indexOf("=") != -1) {
            str = url.substr(1, url.indexOf("=") - 1);
        } else {
            str = url.substr(1);
        }
        console.log(str);
        //地址栏参数解密
        let addressUrl = decodeURIComponent(atob(str));
        let addressData = addressUrl.split('&');
        for (let i = 0; i < addressData.length; i++) {
            addressParameter[addressData[i].split("=")[0]] = addressData[i].split("=")[1];
        }
    }
    return addressParameter;
}

//获取地址栏里（URL）传递的参数
function getRequestId(localUrl) {
    console.log(localUrl);
    //url例子：XXX.aspx?ID=" + ID + "&Name=" + Name；
    let url = localUrl; //获取url中"?"符以及其后的字串
    let str = {};
    if (url.indexOf("?") != -1) {//url中存在问号，也就说有参数。

        if (url.indexOf("=") != -1) {
            str.id = url.substr(4);
        }
    }
    return str;
}

//四舍五入保留2位小数（若第二位小数为0，则保留一位小数）
function keepTwoDecimal(num) {
    let result = parseFloat(num);
    if (isNaN(result)) {
        alert('传递参数错误，请检查！');
        return false;
    }
    result = Math.floor(num * 100) / 100;
    return result;
}

function dufDay() {
    let ddd = new Date();
    let day =ddd.getDate();
    let month = "";
    if(ddd.getMonth()<10){
        month = "0"+(ddd.getMonth()+1);
    }
    if(ddd.getDate()<10){
        day = "0"+ddd.getDate();
    }
    let datew = ddd.getFullYear()+"-"+month+"-"+day;
    return datew;
}

function savenDay() {
    let curDate = new Date();
    let ddd = new Date(curDate.getTime() - 24*60*60*1000 * 7); //前一天
    let day =ddd.getDate();
    let month = "";
    if(ddd.getMonth()<10){
        month = "0"+(ddd.getMonth()+1);
    }
    if(ddd.getDate()<10){
        day = "0"+ddd.getDate();
    }
    let datew = ddd.getFullYear()+"-"+month+"-"+day;
    return datew;
}
