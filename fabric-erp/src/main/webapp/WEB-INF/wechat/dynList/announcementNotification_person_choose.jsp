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
	var ids = "";
	$(function() {
		$(".selCon_list1 ul").find("li").each(function() {
			$(this).click(function() {
				ids = ids + "," + $(this).attr("value");
			});
		})
	});
	function goSaveOrUpdateAnnouncementNotification(id) {
		$.ajax({
		type : "post",
		url : "",
		data : {
		"ids" : ids,
		"id" : id
		},
		dataType : "Text",
		success : function(data) {
			location.href = '${vix}/wechat/weChatDynListAction!goSaveOrUpdateAnnouncementNotification.action?ids=' + ids + "&id=" + id;
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
			<span>全选</span>
			<!-- <a href="#" class="btn">按组织选人</a> -->
		</div>
		<div class="selCon_list1">
			<h2>公共群组(${wxpQyContactsNum })</h2>
			<ul>
				<s:if test="wxpQyContactsList.size > 0">
					<s:iterator value="wxpQyContactsList" var="entity" status="s">
						<li id="wxpQyContacts_${entity.id}" value="${entity.id}"><img src="${entity.headImgUrl }">${entity.name }</li>
					</s:iterator>
				</s:if>
			</ul>
		</div>
		<div style="height: 60px; clear: both;"></div>
		<div class="selCon_btn">
			<h2>
				<span><a href="#" class="btn">取消</a> </span><span><a href="#" class="btn" onclick="goSaveOrUpdateAnnouncementNotification('${announcementNotification.id}');">确定</a> </span>
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
	})
</script>
