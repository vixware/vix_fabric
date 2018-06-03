var winWidth = 0;
var winHeight = 0;
var extHeight = 258;

function findDimensions(){ // 函数：获取尺寸
	var boxHeight = 500;
	// 获取窗口宽度
	if (window.innerWidth){
		winWidth = window.innerWidth;
	}
	else if ((document.body) && (document.body.clientWidth)){
		winWidth = document.body.clientWidth;
	}

	// 获取窗口高度
	if (window.innerHeight){
		winHeight = window.innerHeight;
	}
	else if ((document.body) && (document.body.clientHeight)){
		winHeight = document.body.clientHeight;
	}
	
	//最小高度460px
	boxHeight = winHeight-extHeight>460?winHeight-extHeight:460;
	document.getElementById('onresizeBox').style.height=boxHeight+"px";
	
}
window.onload = function(){
	findDimensions();
	// 调用函数，改变大小
	window.onresize=findDimensions;
}


/* jquery 左右滚动，标签切换 */
$(function(){
    //***变量
    var $cur = 1;//初始化显示的版面
    var $i = 1;//每版显示数
    var $len = $('.showbox>ul>li').length;//计算列表总长度(个数)
    var $pages = Math.ceil($len / $i);//计算展示版面数量
    var $w = $('.ibox').width();//取得展示区外围宽度
    var $showbox = $('.showbox');
    var $num = $('div.num li')
    var $pre = $('span.pre')
    var $next = $('span.next');
 	//***向前滚动
    $pre.click(function(){
        if (!$showbox.is(':animated')) {  //判断展示区是否动画
            if ($cur == 1) {   //在第一个版面时,再向前滚动到最后一个版面
                $showbox.animate({
                    left: '-=' + $w * ($pages - 1)
                }, 500); //改变left值,切换显示版面,500(ms)为滚动时间,下同
                $cur = $pages; //初始化版面为最后一个版面
            }
            else { 
                $showbox.animate({
                    left: '+=' + $w
                }, 600); //改变left值,切换显示版面
                $cur--; //版面累减
            }
            $num.eq($cur - 1).addClass('numcur').siblings().removeClass('numcur'); //为对应的版面数字加上高亮样式,并移除同级元素的高亮样式
        }
    });
    //***向后滚动
    $next.click(function(){
        if (!$showbox.is(':animated')) { //判断展示区是否动画
            if ($cur == $pages) {  //在最后一个版面时,再向后滚动到第一个版面
                $showbox.animate({
                    left: 0
                }, 600); //改变left值,切换显示版面,500(ms)为滚动时间,下同
                $cur = 1; //初始化版面为第一个版面
            }
            else {
                $showbox.animate({
                    left: '-=' + $w
                }, 600);//改变left值,切换显示版面
                $cur++; //版面数累加
            }
            $num.eq($cur - 1).addClass('numcur').siblings().removeClass('numcur'); //为对应的版面数字加上高亮样式,并移除同级元素的高亮样式
        }
    });
    //***数字点击事件
    $num.click(function(){
        if (!$showbox.is(':animated')) { //判断展示区是否动画
            var $index = $num.index(this); //索引出当前点击在列表中的位置值
            $showbox.animate({
                left: '-' + ($w * $index) 
            }, 800); //改变left值,切换显示版面,500(ms)为滚动时间
            $cur = $index + 1; //初始化版面值,这一句可避免当滚动到第三版时,点击向后按钮,出面空白版.index()取值是从0开始的,故加1
            $(this).addClass('numcur').siblings().removeClass('numcur'); //为当前点击加上高亮样式,并移除同级元素的高亮样式
        }
    });
})
