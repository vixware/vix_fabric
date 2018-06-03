<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//载入分页列表数据
	pager("start", "${vix}/crm/business/orderCareAction!goSingleList.action?1=1", 'order');

	function currentPager(tag) {
		pager(tag, "${vix}/crm/business/orderCareAction!goSingleList.action?1=1", 'order');
	}
	function saveOrUpdate() {
		if ($('#expeditingSetting').validationEngine('validate')) {
			$.post('${vix}/crm/business/orderCareAction!saveOrUpdate.action', {
			'expeditingSetting.id' : $("#expeditingSettingId").val(),
			'expeditingSetting.startType' : $("#startType").val(),
			'expeditingSetting.startDate' : $("#startDate").val(),
			'expeditingSetting.customerLevel' : $("#customerLevel").val(),
			'expeditingSetting.minAmount' : $("#minAmount").val(),
			'expeditingSetting.maxAmount' : $("#maxAmount").val(),
			'expeditingSetting.messageContent' : $("#messageContent").val(),
			'expeditingSetting.isExpediting' : $(":radio[name=isExpediting][checked]").val(),
			'expeditingSetting.timeOutIsExpediting' : $(":radio[name=timeOutIsExpediting][checked]").val(),
			'expeditingSetting.isSetTime' : $(":checkbox[name=isSetTime][checked]").val(),
			'expeditingSetting.isLimit' : $(":checkbox[name=isLimit][checked]").val(),
			'expeditingSetting.isJhs' : $(":checkbox[name=isJhs][checked]").val(),
			'expeditingSetting.mobilePhone' : $(":checkbox[name=mobilePhone][checked]").val(),
			'expeditingSetting.orderCode' : $(":checkbox[name=orderCode][checked]").val(),
			'expeditingSetting.customerName' : $(":checkbox[name=customerName][checked]").val(),
			'expeditingSetting.careNode' : $(":radio[name=careNode][checked]").val(),
			'expeditingSetting.goodsName' : $(":checkbox[name=goodsName][checked]").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/crm/business/orderCareAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#expeditingSetting").validationEngine();
	function change(a) {
		$('#messageContent').val(a.value);
	}
	/** 状态 */
	function listbystatus(status) {
		pager("start", "${vix}/crm/business/orderCareAction!goSingleList.action?status=" + status, 'order');
	}
	/* 最近使用 */
	function leastRecentlyUsed(days) {
		pager("start", "${vix}/crm/business/orderCareAction!goSingleList.action?days=" + days, 'order');
	}
	bindSearch();
	
	function goSearch() {
		$.ajax({
		url : '${vix}/crm/business/orderCareAction!goSearch.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 650,
			height : 300,
			title : "查询条件",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					pager("start", "${vix}/crm/business/orderCareAction!goSingleList.action?code=" + $('#code').val() + "&selectName=" + $('#selectName').val(), 'order');
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
</script>
<style>
.gray {
	color: #666;
}

.order_box h2 {
	line-height: 30px;
	margin-bottom: 15px;
}

.order_left {
	width: 500px;
	margin-right: 20px;
	float: left;
	display: inline;
	padding: 15px;
}

.order_right {
	overflow: hidden;
	padding: 15px;
}
</style>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/remind.png" alt="" />供应链</a></li>
				<li><a href="#">会员交互管理</a></li>
				<li><a href="#">订单中心</a></li>
				<li><a href="#">订单关怀</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>
<div class="content">
	<div class="drop" id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img alt="" src="img/icon_11.png"> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" /> <s:text name="cmn_index" /> </a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png"> <s:text name="cmn_mode" /> </a>
				<ul>
					<li><a href="#" onclick="listbystatus('0')"><img alt="" src="img/uncommitted.png"> 未提交 </a></li>
					<li><a href="#" onclick="listbystatus('1')"><img alt="" src="img/unaudited.png"> 待审批 </a></li>
					<li><a href="#" onclick="listbystatus('2')"><img alt="" src="img/verifying.png"> 审批中 </a></li>
					<li><a href="#" onclick="listbystatus('3')"><img alt="" src="img/approved.png"> 已完成 </a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png"> <s:text name="cmn_recently_used" /> </a>
				<ul>
					<li><a href="#" onclick="leastRecentlyUsed('THREEDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('SEVENDAYS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_seven_days" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('ONEMONTH');"><img alt="" src="img/time_go.png"> <s:text name="cmn_month" /> </a></li>
					<li><a href="#" onclick="leastRecentlyUsed('THREEMONTHS');"><img alt="" src="img/time_go.png"> <s:text name="cmn_three_months" /> </a></li>
				</ul></li>
		</ul>
		<div>
			<label>内容:<input type="text" class="int" id="nameS" placeholder="名称"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent(0);" /><input type="button" value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();" /></label> <label> <input
				type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<%-- <div class="search_advanced">
			<label>编码:<input type="text" class="int" name="" id="code" placeholder="编码"></label> <label>名称:<input type="text" class="int" name="" id="selectName" placeholder="名称"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" name="" onclick="searchForContent();"><input type="button"
				value="<s:text name='cmn_reset'/>" class="btn" name="" onclick="resetForContent();"></label>
		</div> --%>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="wimTransvouchList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.tvcode}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div class="order_box">
			<div class="order_left">
				<form id="expeditingSetting">
					<h2>
						订单关怀<span class="gray">店铺选择：<s:select id="channelDistributorId" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" value="" multiple="false" theme="simple">
							</s:select></span>
					</h2>
					<div class="order_table">
						<table>
							<tr>
								<th>启动类型:</th>
								<td><select id="startType">
										<option value="1">持续开启</option>
								</select></td>
							</tr>
							<tr>
								<th></th>
								<td><input name="isSetTime" type="checkbox" value="1" />启动时间:&nbsp;&nbsp;<input id="startDate" value="${expeditingSetting.startDate}" class="sweet-tooltip" name="startDate" type="text" /> <img onclick="showTime('startDate','HH:mm')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
							</tr>
							<tr>
								<th>关怀节点:</th>
								<td><input name="careNode" type="radio" value="" />下单后立即发送&nbsp;&nbsp;&nbsp;&nbsp;<input name="careNode" type="radio" value="" /> 付款后立即发送&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<th>会员等级:</th>
								<td><select id="customerLevel">
										<option value="0">不限</option>
										<option value="1">新用户</option>
										<option value="2">普通会员及以下</option>
										<option value="3">高级会员及以下</option>
										<option value="4">VIP及以下</option>
										<option value="5">至尊VIP及以下</option>
										<option value="6">至尊VIP及以下</option>
								</select></td>
							</tr>
							<tr>
								<th>订单金额:</th>
								<td><input name="isLimit" type="checkbox" value="0" /> 不限&nbsp;&nbsp;&nbsp;&nbsp;<input class="sweet-tooltip" id="minAmount" name="minAmount" type="text" value="${expeditingSetting.minAmount}" />-<input class="sweet-tooltip" name="maxAmount" id="maxAmount" type="text" value="${expeditingSetting.maxAmount}" />元</td>
							</tr>
							<tr>
								<th>聚划算:</th>
								<td><input name="isJhs" type="checkbox" value="1" /> 包含聚划算</td>
							</tr>
							<tr>
								<th>短信模板:</th>
								<td><s:select id="messageTemplateId" headerKey="" headerValue="--请选择--" list="%{messageTemplateList}" listValue="name" listKey="messageContent" value="" multiple="false" theme="simple" onchange="change(this);">
									</s:select></td>
							</tr>
							<tr>
								<th>短信预览:</th>
								<td><textarea id="messageContent" name="messageContent" style="width: 364px; height: 117px;">${expeditingSetting.messageContent}</textarea></td>
							</tr>
						</table>
						<table width="100%" cellspacing="0" cellpadding="0" border="0" class="msgtable">
							<tr>
								<td colspan="2" class="tc" style="padding: 10px 0;"><input name="" type="button" value="保存" class="btn" onclick="saveOrUpdate();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input name="" type="button" value="重置" class="btn" /></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
			<div id="order_right">
				<div class="right_content">
					<div class="pagelist drop">
						<ul>
							<li class="tp"><a href="#"><s:text name='cmn_operate' /> </a>
								<ul>
									<li><a href="#"><s:text name='cmn_add' /> </a></li>
									<li><a href="#"><s:text name='cmn_update' /> </a></li>
									<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								</ul></li>
						</ul>
						<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount1">0</span> </strong>
						<div>
							<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="orderInfo"></b> <s:text name='cmn_to' /> <b class="orderTotalCount"></b>)
							</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
						</div>
					</div>
					<div id="order" class="table"></div>
					<div class="pagelist drop">
						<ul>
							<li class="ed"><a href="#"><s:text name='cmn_choose' /> </a>
								<ul>
									<li><a href="#"><s:text name='cmn_delete' /> </a></li>
									<li><a href="#"><s:text name='cmn_mail' /> </a></li>
									<li><a href="#"><s:text name="cmn_merger" /> </a></li>
									<li><a href="#"><s:text name="cmn_export" /> </a></li>
								</ul></li>
						</ul>
						<strong><s:text name="cmn_selected" />:<span id="selectCategoryCount2">0</span> </strong>
						<div>
							<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="orderInfo"></b> <s:text name='cmn_to' /> <b class="orderTotalCount"></b>)
							</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>