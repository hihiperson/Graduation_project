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