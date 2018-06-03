// JavaScript Document
var time = null,
	autoBg = true;


function loginPageBgEvent(noneLoop){
	if(noneLoop && noneLoop==1){
		bodyBg(1);
		$('.top_bar .top').hide();
		return;
	}

	$('#bg li').each(function(i){
		if(i>0){
			$(this).addClass($(this).attr('id'));
		}
	});
	
	$('#bg li').css({"opacity":0});
	$('.bgbtn li').click(function(){
		if(autoBg){
			clearInterval(time);
			time=null;
			autoRun();
		}
		bodyBg($(this).attr('rel'));
		setCookie4Bg($(this).attr('rel'),autoBg);
	});
	
	$("#autoBtn").click(function(){
		if($(this).attr("class") == "play"){
			autoRun();
			$(this).attr("class","pause");
			autoBg = true;
		}else if($(this).attr("class") == "pause"){
			clearInterval(time);
			time=null;
			$(this).attr("class","play");
			autoBg = false;
		}
		setCookie4Bg($('.bgbtn li').index($('.bgbtn li.onthis'))+1,autoBg);
	});
	
	
	$('.loginbtn').hover(function(){
		$(this).addClass("loginbtn_hover");
	},function(){
		$(this).removeClass("loginbtn_hover");
	});
	getCookie4Bg();
	
}

window.onload = function(){
	$('.formbg').css('margin-top',-130).animate({'margin-top': -80,'opacity':'show'}, "slow",function(){$('.loginbox').animate({'opacity':'show'}, "slow");});
}



function bodyBg(num){
	
	var n = $('.bgbtn li').index($('.bgbtn li.onthis'))+2;
	if(n>$('.bgbtn li').length){
		n=1;
	}
	num = typeof num == "undefined" ? n : num;
	$('.bgbtn li').removeClass('onthis');
	$('.bgbtn li.bgbtn'+num).addClass('onthis');
	$('#bg li').animate({"opacity":0},500);
	$('#bg'+num).stop().animate({"opacity":1},500);
	setCookie4Bg($('.bgbtn li').index($('.bgbtn li.onthis'))+1,autoBg);
}
function autoRun(){
	time = setInterval(function(){
		bodyBg();
	},5500);
}


function getCookie4Bg()
{
	var bgName = "bgNum=";
	var isAutoRun = "auto=";
	if (document.cookie.length>0){
		var autoRunStart=document.cookie.indexOf("auto=");
		if(autoRunStart != -1){
			autoRunStart=autoRunStart + isAutoRun.length; 
			var isAutoEnd=document.cookie.indexOf(";",autoRunStart);
			if (isAutoEnd==-1){
				isAutoEnd=document.cookie.length;
			}
			if(document.cookie.substring(autoRunStart,isAutoEnd)=="true"){
				autoBg = true;
				$("#autoBtn").attr("class","pause");
				autoRun();
			}else{
				autoBg = false;
				$("#autoBtn").attr("class","play");
			}
		}

		bgStart=document.cookie.indexOf("bgNum=");
		if (bgStart!=-1){
			bgStart=bgStart + bgName.length; 
			c_end=document.cookie.indexOf(";",bgStart);
			if (c_end==-1) c_end=document.cookie.length;
			bodyBg(document.cookie.substring(bgStart,c_end));
		}else{
			bodyBg(1);
			if(autoBg){
				autoRun();
			}
		}
	}else{
		bodyBg(1);
		if(autoBg){
			autoRun();
		}
	}
}

function setCookie4Bg(bgNum,auto)
{
	bgNum = typeof bgNum == "undefined" ? 1 : bgNum; 
	document.cookie="bgNum="+escape(bgNum);
	document.cookie="auto="+escape(auto);
}

