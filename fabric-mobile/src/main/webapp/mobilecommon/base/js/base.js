
var url={
    domainhttp:"",
    domain:""

}

var B_mobile_comm=new Object();
var B_mobile_util=new Object();

B_mobile_comm.messager = function(){};

$('.b-header-more').click(function(){
    var B_msg=new B_mobile_comm.messager();
    B_msg.popnews();
    BNJ.news();
})

B_mobile_comm.messager.prototype.popnews=function(){
    var html='';
    html+='<div class="newspop-box">'+
        '<ul>'+
        '<li><a href="'+url.domain+'/user/news.html"><i class="b_icon b_news"></i>消息</a></li>'+
        '<li><a href="'+url.domain+'/../首页.html"><i class="b_icon b_home"></i>首页</a></li>'+
        '<li><a href="'+url.domain+'/market/search.html"><i class="b_icon b_search"></i>搜索</a></li>'+
        '</ul>'+
        '</div>';
    $('body').append(html)
}


