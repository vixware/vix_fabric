<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<html>
<head>
<link href="${vix}/wechatcommon/css/result.css" rel="stylesheet" type="text/css">
<link href="${vix}/wechatcommon/css/date.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${vix}/wechatcommon/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${vix}/wechatcommon/js/js.js"></script>
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width" name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1" media="(device-height: 568px)" name="viewport">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增计划</title>
</head>
<script type="text/javascript">
	function saveOrUpdate() {
        window.location.href="${vix}/wechat/weChatTaskPlanAction!planList.action";
	}  
	
	/* function saveOrUpdate() {
		var a="xC6IWvNK0pCUjq7rylG9mmhdw42hlVSQ6N5FTpR-v0TDezbmf1mbUdxqIzNmo8gfE2pX2hn2mmSv5WpRkDjHcw";
		var id="2";
        window.location.href="${vix}/wechat/wxpQyContactsAction!contactGetByDepartmentId.action?wxpQyContacts.departmentId=2&acessToken="+a;
	
	} */
</script>
<body>
	<section class="newMeet">
		<div class="newMeet_list1">
			<h5>
				<strong>
					<div class="select_box">
						<span class="select_txt">请选择计划类型</span><a class="selet_open"></a>
						<div class="option">
							<a>工作计划</a> <a>旅游计划</a> <a>公司业务发展计划</a>
						</div>
						<input type="hidden" class="select_value" />
					</div>
				</strong>
			</h5>
			<h2>
				<input type="text" placeholder="请输入计划标题">
			</h2>
			<h3>
				<textarea placeholder="请输入计划内容"></textarea>
			</h3>
			<h4>
				<a href="#"><img src="${vix}/wechatcommon/images/newMeet_icon1.png" /></a><a href="#"><img src="${vix}/wechatcommon/images/newMeet_icon2.png" /></a>
			</h4>
		</div>
		<div class="taskDetail_list2">
		<h2>
			<a href="#" onclick="saveOrUpdate()"><span class="btn">保存</a> </span> <span class="btn"><a href="#">重置</a> </span>
		</h2>
	</div>
	</section>
</body>
</html>
