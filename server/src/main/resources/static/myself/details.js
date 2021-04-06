// 弹窗
function showWindow1() {
    $('#showdiv1').show();  //显示弹窗
    $('#cover').css('display', 'block'); //显示遮罩层
    $('#cover').css('height', document.body.clientHeight + 'px'); //设置遮罩层的高度为当前页面高度
}
function showWindow2() {
    $('#showdiv2').show();  //显示弹窗
    $('#cover').css('display', 'block'); //显示遮罩层
    $('#cover').css('height', document.body.clientHeight + 'px'); //设置遮罩层的高度为当前页面高度
}
// 关闭弹窗
function closeWindow() {
    $('#showdiv1').hide();  //隐藏弹窗
    $('#showdiv2').hide();  //隐藏弹窗
    $('#cover').css('display', 'none');   //显示遮罩层
}

applyNum_isok = false;
message_isOk = false;
applyDay_isOk = false;
applyTime_isOk = false;
function check_submit(){
    if (applyNum_isok==true && message_isOk==true && applyDay_isOk==true && applyTime_isOk==true){
        return true;
    }
    else {
        return false;
    }
}



//判断申请数量不能大于库存
$("#applyNum").blur(function () {
    var num = $("#num").val();
    var applyNum = $(this).val();
    if (applyNum>num){
        $("#hint_num").html("申请数量不能大于库存！");
        applyNum_isok = false;
    }else {
        $("#hint_num").html("");
        applyNum_isok = true;
    }
});
//判断预约时间不能为空
$("#applyTime").blur(function () {
    var applyTime = $(this).val();
    if (applyTime == "" || applyTime==null || applyTime == undefined ){ $("#hint_applyTime").html("预约日期不能为空！");  applyTime_isOk=false;}
    else{ $("#hint_applyTime").html(""); applyTime_isOk=true;}
});

//判断申请理由不能为空
$("#applyReson").blur(function () {
    var applyReson = $(this).val();
    if (applyReson == "" || applyReson==null || applyReson == undefined){ $("#hint_message").html("申请原因不能为空！");  message_isOk=false;}
    else{ $("#hint_message").html("");  message_isOk=true;}
});

//判断申请天数
$("#applyDay").blur(function () {
    var applyDay = $(this).val();
    if(applyDay == "" || isNaN(applyDay)){
        $("#hint_day").html("请规范填写数字~");  applyDay_isOk=false;
    }else {
        if (0<=applyDay && applyDay <= 30){
            $("#hint_day").html("");  applyDay_isOk=true;
        }else{
            $("#hint_day").html("请填写：0-30");  applyDay_isOk=false;
        }
    }
});