// 弹窗
function showWindow(cid, cname, userId, aid) {
    $("#cid").val(cid);
    $("#userId").val(userId);
    $("#cname").val(cname);
    $("#aid").val(aid);
    alert(aid);
    $('#showdiv').show();  //显示弹窗
    $('#cover').css('display', 'block'); //显示遮罩层
    // var createTime = $("createTime").val();
    // var message = $("message").val();
    // if (message == "" || message==null || message == undefined){ $("message").val('申请理由不能为空~');}
    // if (createTime == "" || createTime==null || createTime == undefined){ $("message").val('请填写日期~');}
    // else {
    //     $.ajax({
    //         url: "/laboratory/index2/cannotReturn?cid=" + cid + "&cname=" + cname + "&userId=" + userId + "&createTime=" +createTim + "&message="+message,
    //         type: "get",
    //         dataType: "json"
    //     })
    // }
}
// 关闭弹窗
function closeWindow() {
    $('#showdiv').hide();  //隐藏弹窗
    $('#cover').css('display', 'none');   //显示遮罩层
}
//弹窗中的表单信息~






/*文件（照片）上传*/
function showWindow_file() {
    $('#showdiv_file').show();  //显示弹窗
    $('#showfile').css('display', 'block'); //显示遮罩层

}
// 关闭文件上传弹窗
function closeWindow_file() {
    $('#showdiv_file').hide();  //隐藏弹窗
    $('#showfile').css('display', 'none');   //显示遮罩层
}