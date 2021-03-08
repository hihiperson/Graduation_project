// 弹窗
function showWindow() {
    $('#showdiv').show();  //显示弹窗
    $('#cover').css('display', 'block'); //显示遮罩层

}
// 关闭弹窗
function closeWindow() {
    $('#showdiv').hide();  //隐藏弹窗
    $('#cover').css('display', 'none');   //显示遮罩层
}

/*文件（照片）上传*/
function showWindow_file() {
    $('#showdiv_file').show();  //显示弹窗
    $('#showfile').css('display', 'block'); //显示遮罩层

}
// 关闭弹窗
function closeWindow_file() {
    $('#showdiv_file').hide();  //隐藏弹窗
    $('#showfile').css('display', 'none');   //显示遮罩层
}