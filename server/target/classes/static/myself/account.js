//判断表单是否可以提交
var uname_isOk = false;
var upwd_isOk = false;
var upwd2_isOk = false;
var tel_isOk = false;
var msgCode_isOk = false;
var sendMsg = false;
function check_submit(){
    if (uname_isOk==true && upwd_isOk==true && upwd2_isOk==true && tel_isOk==true && msgCode_isOk==true){
        return true;
    }
    else {
        return false;
    }
}

//判断用户是否存在
//失去焦点时的操作
$("#uname").blur(function () {
    //弹出对话框，测试成功
    //alert("11111")
    var uname = $(this).val();
    $.ajax({
        url: "/laboratory/account/isExistsUname?uname=" + uname,
        type: "get",
        dataType: "json",
        success: function (hint) {
            //接收来自服务器的json字符串，并转换为json对象
            if (hint=="1"){ $("#hint_uname").html("该用户已存在！");  uname_isOk=false;}
            else{$("#hint_uname").html("");  uname_isOk=true;}
        }
    })
});

//判断密码不能为空
$("#upwd").blur(function () {
    var upwd = $(this).val();
    if (upwd == "" || upwd==null || upwd == undefined){ $("#hint_upwd").html("密码不能为空！");  upwd_isOk=false;}
    else{ $("#hint_upwd").html("");  upwd_isOk=true;}
});


//判断两次密码是否输入一致
$("#upwd2").blur(function () {
    var upwd = $("#upwd").val();
    var upwd2 = $(this).val();
    if (upwd != upwd2){ $("#hint_upwd2").html("两次密码不一致！");  upwd2_isOk=false;}
    else{$("#hint_upwd2").html("");  upwd2_isOk=true;}
});

//是否输入验证码
//判断验证码不能为空
$("#msgCode").blur(function () {
    var msgCode = $(this).val();
    var tel = $("#tel").val();
    if (msgCode == "" || msgCode==null || msgCode == undefined){$("#hint_msgCode").html("请输入验证码！"); msgCode_isOk=false;}
    else{
        $.ajax({
            url: "/laboratory/account/checkMsgCode?tel=" + tel +"&msgCode="+msgCode,
            type: "get",
            dataType: "json",
            success: function (isOk) {
                //接收来自服务器的json字符串，并转换为json对象
                if (isOk=="200"){ $("#hint_msgCode").html(""); msgCode_isOk=true;}
                else{$("#hint_msgCode").html("验证码错误，请重新输入！"); msgCode_isOk=false;}
            }
        })
    }
});


//是否输入手机号
$("#tel").blur(function () {
    var tel = $(this).val();
    if (tel == "" || tel==null || tel == undefined){ $("#hint_tel").html("请输入手机号！");  tel_isOk=false; sendMsg=false;}
    else if(tel.length!=11){
        $("#hint_tel").html("请输入正确的手机号！");  tel_isOk=false; sendMsg=false;
    }
    else {
        $.ajax({
            url: "/laboratory/account/isExistsPhone?phone=" + tel,
            type: "get",
            dataType: "json",
            success: function (hint) {
                //接收来自服务器的json字符串，并转换为json对象
                if (hint=="1"){ $("#hint_tel").html("该手机号已被注册！");  tel_isOk=false; sendMsg=false;}
                else{$("#hint_tel").html("");   tel_isOk=true; sendMsg=true;}
            }
        })
    }
});

//<!--获取验证码-->
function getMsgCode() {
    if (sendMsg==true && tel_isOk==true) {
        $("#getNum").removeAttr("onclick");
        var tel = $("#tel").val();
        var validCode = true;
        var time = 30;
        var code = $("#getNum");

        $.ajax({
            url: "/laboratory/msgCode/sendCode?phone=" + tel,
            type: "get",
            dataType: "json",
            success: function (msgCode) {
                alert("验证码是：" + msgCode);
            }
        })

        if (validCode) {
            validCode = false;
            code.addClass("msgs1");
            code.html(time + " (s)");
            var t = setInterval(function () {
                time--;
                code.html(time + " (s)");
                if (time == 0) {
                    clearInterval(t);
                    code.html("重新获取");
                    validCode = true;
                    code.removeClass("msgs1");
                    //设置30S后执行添加a标签点击事件
                    $('#getNum').attr('onclick', 'getMsgCode();')
                }
            }, 1000)
        }
    }
}


