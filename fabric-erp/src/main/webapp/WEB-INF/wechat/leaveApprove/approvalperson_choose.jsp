<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/css/date.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	function chooseAll(chk) {
		if ($(chk).hasClass("check")) {
			$.each($("li[name='chkId']"), function(i, n) {
				$(n).removeAttr("class");
			});
		} else {
			$.each($("li[name='chkId']"), function(i, n) {
				$(n).attr("class", "check");
			});
		}
	}
	function goLeaveApprove(id) {
		var ids = "";
		$.each($("li[name='chkId']"), function(i, n) {
			if ($(n).hasClass("check")) {
				ids = ids + "," + $(n).attr("value");
			}
		});
		$.ajax({
		type : "post",
		dataType : "Text",
		success : function(data) {
			location.href = '${vix}/wechat/weChatLeaveApproveAction!goSaveOrUpdate.action?ids=' + ids + "&id=" + id;
		}
		});
	};
</script>
</head>
<body>
	<section class="selCon">
		<div class="search">
			<p>
				<img src="${vix}/wechatcommon/images/search_icon.png" /><input type="text" placeholder="输入姓名，拼音，搜索">
			</p>
		</div>
		<div class="selCon_list2">
			<span onclick="chooseAll(this);">全选</span>
		</div>
		<div class="selCon_list1">
			<h2>公共群组(${employeeNum })</h2>
			<ul>
				<s:if test="employeeList.size > 0">
					<s:iterator value="employeeList" var="entity" status="s">
						<li id="wxpQyContacts_${entity.id}" value="${entity.id}" name='chkId'><img src="${entity.headImgUrl }">${entity.name }</li>
					</s:iterator>
				</s:if>
			</ul>
		</div>
		<div style="height: 60px; clear: both;"></div>
		<div class="selCon_btn">
			<h2>
				<span><a href="#" class="btn">取消</a> </span><span><a href="#" class="btn" onclick="goLeaveApprove('${tripRecord.id}');">确定</a> </span>
			</h2>
		</div>
	</section>
</body>
</html>
<script type="text/javascript">
	$(function() {
		$('.selCon_list1 h2').click(function() {
			$(this).toggleClass('icon');
			$(this).nextAll().slideToggle();
		});
	});
	$('.selCon_list2 span,.selCon_list1 ul li').click(function() {
		if ($(this).hasClass('check')) {
			$(this).removeClass('check');
		} else {
			$(this).addClass('check');
		}
	});
</script>