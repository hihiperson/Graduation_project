var uname_isOk = false;
var upwd_isOk = false;
var upwd2_isOk = false;
function check_submit(){
    if (uname_isOk==true && upwd_isOk==true && upwd2_isOk==true){
        return true;
    }
    else {
        return false;
    }
}

//判断账号不能为空
$("#uname").blur(function () {
    var uname = $(this).val();
    if (uname == "" || uname==null || uname == undefined){ $("#hint_uname").html("账号不能为空！");  uname_isOk=false;}
    else{ $("#hint_uname").html("");  uname_isOk=true;}
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