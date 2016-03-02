//    弹层的js
//弹出隐藏层
function Show2Div(show_div,bg_div){
    document.getElementById(show_div).style.display='block';
    document.getElementById(bg_div).style.display='block' ;
    var bgdiv = document.getElementById(bg_div);
    bgdiv.style.width = document.body.scrollWidth;
// bgdiv.style.height = $(document).height();
    $("#"+bg_div).height($(document).height());
};
function ShowDiv(show_div){
    document.getElementById(show_div).style.display='block';
    var showdiv = document.getElementById(show_div);
// bgdiv.style.height = $(document).height();
    $("#"+show_div).height($(document).height());
};
//关闭弹出层
function Close2Div(show_div,bg_div)
{
    document.getElementById(show_div).style.display='none';
    document.getElementById(bg_div).style.display='none';
};
//关闭弹出层
function CloseDiv(show_div)
{
    document.getElementById(show_div).style.display='none';
};