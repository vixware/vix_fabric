<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="${vix}/common/css/aristo/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" title="no title" charset="utf-8">
<script type="text/javascript">
$('#data-list-fn .data-list-btn').click(function(){
	$('#data-list-fn .data-list-btn').removeClass('data-list-ck');
	$(this).addClass('data-list-ck');
});
$('.datalist .datalist-check').each(function(index, element) {
    if($(element).is(':checked')){
		$(element).parent('.datalist-item').addClass($(element).attr('rel'));
	};
});
$('.datalist .datalist-item').click(function(){
//$('.datalist-check',this).attr('rel')
if($('.datalist-check',this).is(':checked')){
	$('.datalist-check',this).attr('checked',false);
	$(this).removeClass("data-list-orange data-list-orange data-list-pink data-list-red data-list-blue data-list-green");
}else{
	$('.datalist-check',this).attr('checked','checked');
	$(this).addClass($('#data-list-fn .data-list-ck .data-list-ico').attr('rel'));
}
});

$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
   
function chooseAll(chk){
	if(null == chk){
		$("input[name='chkCategoryId']").attr("checked", true);
	}else{
		if($(chk).attr('checked')){
			$("input[name='chkCategoryId']").attr("checked", true);
		}else{
			$("input[name='chkCategoryId']").attr("checked", false);
		}
	}
	selectCount();
}

function selectCount(){
	var selectCount = 0;
	$.each($("input[name='chkCategoryId']"), function(i, n){
		if($(n).attr('checked')){
			selectCount++;
		}
	});
	$("#selectCategoryCount1").html(selectCount);
	$("#selectCategoryCount2").html(selectCount);
	if(selectCount == 0){
		$("input[name='chkCategoryIds']").attr("checked", false);
	}else{
		$("input[name='chkCategoryIds']").attr("checked", true);
	}
}

$(".untitled").hover(
	function () {
		$(this).css({ "position": "relative"});
		$(this).children('.popup').css({ "display": "block"});
		var bh = $("body").height(); 
		var pt = $(this).offset();
		if(( bh - pt.top) < 165){$(this).children('.popup').css({ "bottom": "0"});}else{$(this).children('.popup').css({ "top": "-7px"});};
  	},
	function () {
		$(this).css({ "position": "static"});
		$(this).children('.popup').css({ "display": "none"});
 	}
);
$(".close").click(
	function () {
		$(this).parent().parent().css({ "display": "none"});
	}
);
</script>
<input type="hidden" id="categoryTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="categoryPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="categoryTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="categoryOrderField" value="${pager.orderField}" />
<input type="hidden" id="categoryOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="categoryInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />

<div class="box">
	<div class="ct_title">
		<strong>车辆信息查询管理：<span class="blue">公务车奥迪A8</span>
		</strong>
	</div>
	<p style="padding: 10px;" class="gray" id="data-list-fn">
		<b>图例说明：</b> &nbsp; <span class="data-list-btn data-list-ck"> <span class="data-list-ico data-list-orange" rel="data-list-orange"></span>申请
		</span> &nbsp;<span class="data-list-btn"><span class="data-list-ico data-list-pink" rel="data-list-pink"></span>撤销</span> &nbsp;<span class="data-list-btn"><span class="data-list-ico data-list-red" rel="data-list-red"></span>他人已申请</span> &nbsp;<span class="data-list-btn"><span class="data-list-ico data-list-blue" rel="data-list-blue"></span>本人已申请</span>
		&nbsp;<span class="data-list-btn"><span class="data-list-ico data-list-green" rel="data-list-green"></span>管理员周期安排</span>
	</p>
	<form action="" method="get">
		<table class="borderlist rp datalist">
			<tr>
				<th><span>日期</span></th>
				<th><span>09:00<br />-<br />09:30
				</span></th>
				<th><span>09:30<br />-<br />10:00
				</span></th>
				<th><span>10:00<br />-<br />10:30
				</span></th>
				<th><span>10:30<br />-<br />11:00
				</span></th>
				<th><span>11:00<br />-<br />11:30
				</span></th>
				<th><span>11:30<br />-<br />12:00
				</span></th>
				<th><span>12:00<br />-<br />12:30
				</span></th>
				<th><span>12:30<br />-<br />13:00
				</span></th>
				<th><span>13:00<br />-<br />13:30
				</span></th>
				<th><span>13:30<br />-<br />14:00
				</span></th>
				<th><span>14:00<br />-<br />14:30
				</span></th>
				<th><span>14:30<br />-<br />15:00
				</span></th>
				<th><span>15:00<br />-<br />15:30
				</span></th>
				<th><span>15:30<br />-<br />16:00
				</span></th>
				<th><span>16:00<br />-<br />16:30
				</span></th>
				<th><span>16:30<br />-<br />17:00
				</span></th>
				<th><span>17:00<br />-<br />17:30
				</span></th>
				<th><span>17:30<br />-<br />18:00
				</span></th>
				<th><span>18:00<br />-<br />21:00
				</span></th>
			</tr>
			<tr>
				<th><span>06-08<br />星期五
				</span></th>
				<td><span class="datalist-item">09:00<br />-<br />09:30<input class="datalist-check" name="" type="checkbox" value="09:00\-09:30" rel="data-list-orange" checked="checked" /></span></td>
				<td><span class="datalist-item">09:30<br />-<br />10:00<input class="datalist-check" name="" type="checkbox" value="09:30\-10:00" rel="data-list-pink" checked="checked" /></span></td>
				<td><span class="datalist-item">10:00<br />-<br />10:30<input class="datalist-check" name="" type="checkbox" value="10:00\-10:30" rel="data-list-red" checked="checked" /></span></td>
				<td><span class="datalist-item">10:30<br />-<br />11:00<input class="datalist-check" name="" type="checkbox" value="10:30\-11:00" rel="data-list-blue" checked="checked" /></span></td>
				<td><span class="datalist-item">11:00<br />-<br />11:30<input class="datalist-check" name="" type="checkbox" value="11:00\-11:30" rel="data-list-green" checked="checked" /></span></td>
				<td><span class="datalist-item">11:30<br />-<br />12:00<input class="datalist-check" name="" type="checkbox" value="11:30\-12:00" />
				</span></td>
				<td><span class="datalist-item">12:00<br />-<br />12:30<input class="datalist-check" name="" type="checkbox" value="12:00\-12:30" />
				</span></td>
				<td><span class="datalist-item">12:30<br />-<br />13:00<input class="datalist-check" name="" type="checkbox" value="12:30\-13:00" />
				</span></td>
				<td><span class="datalist-item">13:00<br />-<br />13:30<input class="datalist-check" name="" type="checkbox" value="13:00\-13:30" />
				</span></td>
				<td><span class="datalist-item">13:30<br />-<br />14:00<input class="datalist-check" name="" type="checkbox" value="13:30\-14:00" />
				</span></td>
				<td><span class="datalist-item">14:00<br />-<br />14:30<input class="datalist-check" name="" type="checkbox" value="14:00\-14:30" />
				</span></td>
				<td><span class="datalist-item">14:30<br />-<br />15:00<input class="datalist-check" name="" type="checkbox" value="14:30\-15:00" />
				</span></td>
				<td><span class="datalist-item">15:00<br />-<br />15:30<input class="datalist-check" name="" type="checkbox" value="15:00\-15:30" />
				</span></td>
				<td><span class="datalist-item">15:30<br />-<br />16:00<input class="datalist-check" name="" type="checkbox" value="15:30\-16:00" />
				</span></td>
				<td><span class="datalist-item">16:00<br />-<br />16:30<input class="datalist-check" name="" type="checkbox" value="16:00\-16:30" />
				</span></td>
				<td><span class="datalist-item">16:30<br />-<br />17:00<input class="datalist-check" name="" type="checkbox" value="16:30\-17:00" />
				</span></td>
				<td><span class="datalist-item">17:00<br />-<br />17:30<input class="datalist-check" name="" type="checkbox" value="17:00\-17:30" />
				</span></td>
				<td><span class="datalist-item">17:30<br />-<br />18:00<input class="datalist-check" name="" type="checkbox" value="17:30\-18:00" />
				</span></td>
				<td><span class="datalist-item">18:00<br />-<br />21:00<input class="datalist-check" name="" type="checkbox" value="18:00\-21:00" />
				</span></td>
			</tr>
			<tr>
				<th><span>06-11<br />星期一
				</span></th>
				<td><span class="datalist-item">09:00<br />-<br />09:30<input class="datalist-check" name="" type="checkbox" value="09:00\-09:30" />
				</span></td>
				<td><span class="datalist-item">09:30<br />-<br />10:00<input class="datalist-check" name="" type="checkbox" value="09:30\-10:00" />
				</span></td>
				<td><span class="datalist-item">10:00<br />-<br />10:30<input class="datalist-check" name="" type="checkbox" value="10:00\-10:30" />
				</span></td>
				<td><span class="datalist-item">10:30<br />-<br />11:00<input class="datalist-check" name="" type="checkbox" value="10:30\-11:00" />
				</span></td>
				<td><span class="datalist-item">11:00<br />-<br />11:30<input class="datalist-check" name="" type="checkbox" value="11:00\-11:30" />
				</span></td>
				<td><span class="datalist-item">11:30<br />-<br />12:00<input class="datalist-check" name="" type="checkbox" value="11:30\-12:00" />
				</span></td>
				<td><span class="datalist-item">12:00<br />-<br />12:30<input class="datalist-check" name="" type="checkbox" value="12:00\-12:30" />
				</span></td>
				<td><span class="datalist-item">12:30<br />-<br />13:00<input class="datalist-check" name="" type="checkbox" value="12:30\-13:00" />
				</span></td>
				<td><span class="datalist-item">13:00<br />-<br />13:30<input class="datalist-check" name="" type="checkbox" value="13:00\-13:30" />
				</span></td>
				<td><span class="datalist-item">13:30<br />-<br />14:00<input class="datalist-check" name="" type="checkbox" value="13:30\-14:00" />
				</span></td>
				<td><span class="datalist-item">14:00<br />-<br />14:30<input class="datalist-check" name="" type="checkbox" value="14:00\-14:30" />
				</span></td>
				<td><span class="datalist-item">14:30<br />-<br />15:00<input class="datalist-check" name="" type="checkbox" value="14:30\-15:00" />
				</span></td>
				<td><span class="datalist-item">15:00<br />-<br />15:30<input class="datalist-check" name="" type="checkbox" value="15:00\-15:30" />
				</span></td>
				<td><span class="datalist-item">15:30<br />-<br />16:00<input class="datalist-check" name="" type="checkbox" value="15:30\-16:00" />
				</span></td>
				<td><span class="datalist-item">16:00<br />-<br />16:30<input class="datalist-check" name="" type="checkbox" value="16:00\-16:30" />
				</span></td>
				<td><span class="datalist-item">16:30<br />-<br />17:00<input class="datalist-check" name="" type="checkbox" value="16:30\-17:00" />
				</span></td>
				<td><span class="datalist-item">17:00<br />-<br />17:30<input class="datalist-check" name="" type="checkbox" value="17:00\-17:30" />
				</span></td>
				<td><span class="datalist-item">17:30<br />-<br />18:00<input class="datalist-check" name="" type="checkbox" value="17:30\-18:00" />
				</span></td>
				<td><span class="datalist-item">18:00<br />-<br />21:00<input class="datalist-check" name="" type="checkbox" value="18:00\-21:00" />
				</span></td>
			</tr>
			<tr>
				<th><span>06-12<br />星期二
				</span></th>
				<td><span class="datalist-item">09:00<br />-<br />09:30<input class="datalist-check" name="" type="checkbox" value="09:00\-09:30" />
				</span></td>
				<td><span class="datalist-item">09:30<br />-<br />10:00<input class="datalist-check" name="" type="checkbox" value="09:30\-10:00" />
				</span></td>
				<td><span class="datalist-item">10:00<br />-<br />10:30<input class="datalist-check" name="" type="checkbox" value="10:00\-10:30" />
				</span></td>
				<td><span class="datalist-item">10:30<br />-<br />11:00<input class="datalist-check" name="" type="checkbox" value="10:30\-11:00" />
				</span></td>
				<td><span class="datalist-item">11:00<br />-<br />11:30<input class="datalist-check" name="" type="checkbox" value="11:00\-11:30" />
				</span></td>
				<td><span class="datalist-item">11:30<br />-<br />12:00<input class="datalist-check" name="" type="checkbox" value="11:30\-12:00" />
				</span></td>
				<td><span class="datalist-item">12:00<br />-<br />12:30<input class="datalist-check" name="" type="checkbox" value="12:00\-12:30" />
				</span></td>
				<td><span class="datalist-item">12:30<br />-<br />13:00<input class="datalist-check" name="" type="checkbox" value="12:30\-13:00" />
				</span></td>
				<td><span class="datalist-item">13:00<br />-<br />13:30<input class="datalist-check" name="" type="checkbox" value="13:00\-13:30" />
				</span></td>
				<td><span class="datalist-item">13:30<br />-<br />14:00<input class="datalist-check" name="" type="checkbox" value="13:30\-14:00" />
				</span></td>
				<td><span class="datalist-item">14:00<br />-<br />14:30<input class="datalist-check" name="" type="checkbox" value="14:00\-14:30" />
				</span></td>
				<td><span class="datalist-item">14:30<br />-<br />15:00<input class="datalist-check" name="" type="checkbox" value="14:30\-15:00" />
				</span></td>
				<td><span class="datalist-item">15:00<br />-<br />15:30<input class="datalist-check" name="" type="checkbox" value="15:00\-15:30" />
				</span></td>
				<td><span class="datalist-item">15:30<br />-<br />16:00<input class="datalist-check" name="" type="checkbox" value="15:30\-16:00" />
				</span></td>
				<td><span class="datalist-item">16:00<br />-<br />16:30<input class="datalist-check" name="" type="checkbox" value="16:00\-16:30" />
				</span></td>
				<td><span class="datalist-item">16:30<br />-<br />17:00<input class="datalist-check" name="" type="checkbox" value="16:30\-17:00" />
				</span></td>
				<td><span class="datalist-item">17:00<br />-<br />17:30<input class="datalist-check" name="" type="checkbox" value="17:00\-17:30" />
				</span></td>
				<td><span class="datalist-item">17:30<br />-<br />18:00<input class="datalist-check" name="" type="checkbox" value="17:30\-18:00" />
				</span></td>
				<td><span class="datalist-item">18:00<br />-<br />21:00<input class="datalist-check" name="" type="checkbox" value="18:00\-21:00" />
				</span></td>
			</tr>
			<tr>
				<th><span>06-13<br />星期三
				</span></th>
				<td><span class="datalist-item">09:00<br />-<br />09:30<input class="datalist-check" name="" type="checkbox" value="09:00\-09:30" />
				</span></td>
				<td><span class="datalist-item">09:30<br />-<br />10:00<input class="datalist-check" name="" type="checkbox" value="09:30\-10:00" />
				</span></td>
				<td><span class="datalist-item">10:00<br />-<br />10:30<input class="datalist-check" name="" type="checkbox" value="10:00\-10:30" />
				</span></td>
				<td><span class="datalist-item">10:30<br />-<br />11:00<input class="datalist-check" name="" type="checkbox" value="10:30\-11:00" />
				</span></td>
				<td><span class="datalist-item">11:00<br />-<br />11:30<input class="datalist-check" name="" type="checkbox" value="11:00\-11:30" />
				</span></td>
				<td><span class="datalist-item">11:30<br />-<br />12:00<input class="datalist-check" name="" type="checkbox" value="11:30\-12:00" />
				</span></td>
				<td><span class="datalist-item">12:00<br />-<br />12:30<input class="datalist-check" name="" type="checkbox" value="12:00\-12:30" />
				</span></td>
				<td><span class="datalist-item">12:30<br />-<br />13:00<input class="datalist-check" name="" type="checkbox" value="12:30\-13:00" />
				</span></td>
				<td><span class="datalist-item">13:00<br />-<br />13:30<input class="datalist-check" name="" type="checkbox" value="13:00\-13:30" />
				</span></td>
				<td><span class="datalist-item">13:30<br />-<br />14:00<input class="datalist-check" name="" type="checkbox" value="13:30\-14:00" />
				</span></td>
				<td><span class="datalist-item">14:00<br />-<br />14:30<input class="datalist-check" name="" type="checkbox" value="14:00\-14:30" />
				</span></td>
				<td><span class="datalist-item">14:30<br />-<br />15:00<input class="datalist-check" name="" type="checkbox" value="14:30\-15:00" />
				</span></td>
				<td><span class="datalist-item">15:00<br />-<br />15:30<input class="datalist-check" name="" type="checkbox" value="15:00\-15:30" />
				</span></td>
				<td><span class="datalist-item">15:30<br />-<br />16:00<input class="datalist-check" name="" type="checkbox" value="15:30\-16:00" />
				</span></td>
				<td><span class="datalist-item">16:00<br />-<br />16:30<input class="datalist-check" name="" type="checkbox" value="16:00\-16:30" />
				</span></td>
				<td><span class="datalist-item">16:30<br />-<br />17:00<input class="datalist-check" name="" type="checkbox" value="16:30\-17:00" />
				</span></td>
				<td><span class="datalist-item">17:00<br />-<br />17:30<input class="datalist-check" name="" type="checkbox" value="17:00\-17:30" />
				</span></td>
				<td><span class="datalist-item">17:30<br />-<br />18:00<input class="datalist-check" name="" type="checkbox" value="17:30\-18:00" />
				</span></td>
				<td><span class="datalist-item">18:00<br />-<br />21:00<input class="datalist-check" name="" type="checkbox" value="18:00\-21:00" />
				</span></td>
			</tr>
			<tr>
				<th><span>06-14<br />星期四
				</span></th>
				<td><span class="datalist-item">09:00<br />-<br />09:30<input class="datalist-check" name="" type="checkbox" value="09:00\-09:30" />
				</span></td>
				<td><span class="datalist-item">09:30<br />-<br />10:00<input class="datalist-check" name="" type="checkbox" value="09:30\-10:00" />
				</span></td>
				<td><span class="datalist-item">10:00<br />-<br />10:30<input class="datalist-check" name="" type="checkbox" value="10:00\-10:30" />
				</span></td>
				<td><span class="datalist-item">10:30<br />-<br />11:00<input class="datalist-check" name="" type="checkbox" value="10:30\-11:00" />
				</span></td>
				<td><span class="datalist-item">11:00<br />-<br />11:30<input class="datalist-check" name="" type="checkbox" value="11:00\-11:30" />
				</span></td>
				<td><span class="datalist-item">11:30<br />-<br />12:00<input class="datalist-check" name="" type="checkbox" value="11:30\-12:00" />
				</span></td>
				<td><span class="datalist-item">12:00<br />-<br />12:30<input class="datalist-check" name="" type="checkbox" value="12:00\-12:30" />
				</span></td>
				<td><span class="datalist-item">12:30<br />-<br />13:00<input class="datalist-check" name="" type="checkbox" value="12:30\-13:00" />
				</span></td>
				<td><span class="datalist-item">13:00<br />-<br />13:30<input class="datalist-check" name="" type="checkbox" value="13:00\-13:30" />
				</span></td>
				<td><span class="datalist-item">13:30<br />-<br />14:00<input class="datalist-check" name="" type="checkbox" value="13:30\-14:00" />
				</span></td>
				<td><span class="datalist-item">14:00<br />-<br />14:30<input class="datalist-check" name="" type="checkbox" value="14:00\-14:30" />
				</span></td>
				<td><span class="datalist-item">14:30<br />-<br />15:00<input class="datalist-check" name="" type="checkbox" value="14:30\-15:00" />
				</span></td>
				<td><span class="datalist-item">15:00<br />-<br />15:30<input class="datalist-check" name="" type="checkbox" value="15:00\-15:30" />
				</span></td>
				<td><span class="datalist-item">15:30<br />-<br />16:00<input class="datalist-check" name="" type="checkbox" value="15:30\-16:00" />
				</span></td>
				<td><span class="datalist-item">16:00<br />-<br />16:30<input class="datalist-check" name="" type="checkbox" value="16:00\-16:30" />
				</span></td>
				<td><span class="datalist-item">16:30<br />-<br />17:00<input class="datalist-check" name="" type="checkbox" value="16:30\-17:00" />
				</span></td>
				<td><span class="datalist-item">17:00<br />-<br />17:30<input class="datalist-check" name="" type="checkbox" value="17:00\-17:30" />
				</span></td>
				<td><span class="datalist-item">17:30<br />-<br />18:00<input class="datalist-check" name="" type="checkbox" value="17:30\-18:00" />
				</span></td>
				<td><span class="datalist-item">18:00<br />-<br />21:00<input class="datalist-check" name="" type="checkbox" value="18:00\-21:00" />
				</span></td>
			</tr>
			<tr>
				<th><span>06-15<br />星期五
				</span></th>
				<td><span class="datalist-item">09:00<br />-<br />09:30<input class="datalist-check" name="" type="checkbox" value="09:00\-09:30" />
				</span></td>
				<td><span class="datalist-item">09:30<br />-<br />10:00<input class="datalist-check" name="" type="checkbox" value="09:30\-10:00" />
				</span></td>
				<td><span class="datalist-item">10:00<br />-<br />10:30<input class="datalist-check" name="" type="checkbox" value="10:00\-10:30" />
				</span></td>
				<td><span class="datalist-item">10:30<br />-<br />11:00<input class="datalist-check" name="" type="checkbox" value="10:30\-11:00" />
				</span></td>
				<td><span class="datalist-item">11:00<br />-<br />11:30<input class="datalist-check" name="" type="checkbox" value="11:00\-11:30" />
				</span></td>
				<td><span class="datalist-item">11:30<br />-<br />12:00<input class="datalist-check" name="" type="checkbox" value="11:30\-12:00" />
				</span></td>
				<td><span class="datalist-item">12:00<br />-<br />12:30<input class="datalist-check" name="" type="checkbox" value="12:00\-12:30" />
				</span></td>
				<td><span class="datalist-item">12:30<br />-<br />13:00<input class="datalist-check" name="" type="checkbox" value="12:30\-13:00" />
				</span></td>
				<td><span class="datalist-item">13:00<br />-<br />13:30<input class="datalist-check" name="" type="checkbox" value="13:00\-13:30" />
				</span></td>
				<td><span class="datalist-item">13:30<br />-<br />14:00<input class="datalist-check" name="" type="checkbox" value="13:30\-14:00" />
				</span></td>
				<td><span class="datalist-item">14:00<br />-<br />14:30<input class="datalist-check" name="" type="checkbox" value="14:00\-14:30" />
				</span></td>
				<td><span class="datalist-item">14:30<br />-<br />15:00<input class="datalist-check" name="" type="checkbox" value="14:30\-15:00" />
				</span></td>
				<td><span class="datalist-item">15:00<br />-<br />15:30<input class="datalist-check" name="" type="checkbox" value="15:00\-15:30" />
				</span></td>
				<td><span class="datalist-item">15:30<br />-<br />16:00<input class="datalist-check" name="" type="checkbox" value="15:30\-16:00" />
				</span></td>
				<td><span class="datalist-item">16:00<br />-<br />16:30<input class="datalist-check" name="" type="checkbox" value="16:00\-16:30" />
				</span></td>
				<td><span class="datalist-item">16:30<br />-<br />17:00<input class="datalist-check" name="" type="checkbox" value="16:30\-17:00" />
				</span></td>
				<td><span class="datalist-item">17:00<br />-<br />17:30<input class="datalist-check" name="" type="checkbox" value="17:00\-17:30" />
				</span></td>
				<td><span class="datalist-item">17:30<br />-<br />18:00<input class="datalist-check" name="" type="checkbox" value="17:30\-18:00" />
				</span></td>
				<td><span class="datalist-item">18:00<br />-<br />21:00<input class="datalist-check" name="" type="checkbox" value="18:00\-21:00" />
				</span></td>
			</tr>
			<tr>
				<th><span>06-18<br />星期一
				</span></th>
				<td><span class="datalist-item">09:00<br />-<br />09:30<input class="datalist-check" name="" type="checkbox" value="09:00\-09:30" />
				</span></td>
				<td><span class="datalist-item">09:30<br />-<br />10:00<input class="datalist-check" name="" type="checkbox" value="09:30\-10:00" />
				</span></td>
				<td><span class="datalist-item">10:00<br />-<br />10:30<input class="datalist-check" name="" type="checkbox" value="10:00\-10:30" />
				</span></td>
				<td><span class="datalist-item">10:30<br />-<br />11:00<input class="datalist-check" name="" type="checkbox" value="10:30\-11:00" />
				</span></td>
				<td><span class="datalist-item">11:00<br />-<br />11:30<input class="datalist-check" name="" type="checkbox" value="11:00\-11:30" />
				</span></td>
				<td><span class="datalist-item">11:30<br />-<br />12:00<input class="datalist-check" name="" type="checkbox" value="11:30\-12:00" />
				</span></td>
				<td><span class="datalist-item">12:00<br />-<br />12:30<input class="datalist-check" name="" type="checkbox" value="12:00\-12:30" />
				</span></td>
				<td><span class="datalist-item">12:30<br />-<br />13:00<input class="datalist-check" name="" type="checkbox" value="12:30\-13:00" />
				</span></td>
				<td><span class="datalist-item">13:00<br />-<br />13:30<input class="datalist-check" name="" type="checkbox" value="13:00\-13:30" />
				</span></td>
				<td><span class="datalist-item">13:30<br />-<br />14:00<input class="datalist-check" name="" type="checkbox" value="13:30\-14:00" />
				</span></td>
				<td><span class="datalist-item">14:00<br />-<br />14:30<input class="datalist-check" name="" type="checkbox" value="14:00\-14:30" />
				</span></td>
				<td><span class="datalist-item">14:30<br />-<br />15:00<input class="datalist-check" name="" type="checkbox" value="14:30\-15:00" />
				</span></td>
				<td><span class="datalist-item">15:00<br />-<br />15:30<input class="datalist-check" name="" type="checkbox" value="15:00\-15:30" />
				</span></td>
				<td><span class="datalist-item">15:30<br />-<br />16:00<input class="datalist-check" name="" type="checkbox" value="15:30\-16:00" />
				</span></td>
				<td><span class="datalist-item">16:00<br />-<br />16:30<input class="datalist-check" name="" type="checkbox" value="16:00\-16:30" />
				</span></td>
				<td><span class="datalist-item">16:30<br />-<br />17:00<input class="datalist-check" name="" type="checkbox" value="16:30\-17:00" />
				</span></td>
				<td><span class="datalist-item">17:00<br />-<br />17:30<input class="datalist-check" name="" type="checkbox" value="17:00\-17:30" />
				</span></td>
				<td><span class="datalist-item">17:30<br />-<br />18:00<input class="datalist-check" name="" type="checkbox" value="17:30\-18:00" />
				</span></td>
				<td><span class="datalist-item">18:00<br />-<br />21:00<input class="datalist-check" name="" type="checkbox" value="18:00\-21:00" />
				</span></td>
			</tr>
		</table>
		<div class="add_stable" style="text-align: center;">
			<input type="submit" value="提交" name="" class="sbtn">&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="关闭" class="sbtn" name="">
		</div>
	</form>
</div>