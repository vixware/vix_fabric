<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//载入分页列表数据
	pager("start", "${vix}/crm/business/logisticsFollowUpAction!goSingleList.action?1=1", 'salesOrder');

	function currentPager(tag) {
		pager(tag, "${vix}/crm/business/logisticsFollowUpAction!goSingleList.action?1=1", 'salesOrder');
	}

	function saveOrUpdateExpediting(id) {
		$.ajax({
		url : '${vix}/crm/business/logisticsFollowUpAction!goSaveOrUpdate.action?shippingId=' + id,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 750,
			height : 355,
			title : "关怀",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#messageLogForm').validationEngine('validate')) {
						$.post('${vix}/crm/business/logisticsFollowUpAction!saveOrUpdateMessage.action', {
						'messageLog.id' : $("#messageLogId").val(),
						'messageLog.messageContent' : $("#messageContent").val(),
						'messageLog.mobilePhone' : $("#mobilePhone").val(),
						'shippingId' : $("#shippingId").val()
						}, function(result) {
							showMessage(result);
							setTimeout("", 1000);
							loadContent('${vix}/crm/business/logisticsFollowUpAction!goList.action');
						});
					} else {
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
	function saveOrUpdate(id) {
		$.ajax({
		url : '${vix}/crm/business/logisticsFollowUpAction!goSaveOrUpdateFollowing.action?shippingId=' + id,
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 750,
			height : 300,
			title : "延长订单",
			html : html,
			callback : function(action) {
				if (action == 'ok') {
					if ($('#messageLogForm').validationEngine('validate')) {
						$.post('${vix}/crm/business/logisticsFollowUpAction!saveOrUpdateShipping.action', {
						'shippingId' : $("#shippingId").val(),
						'prolongDays' : $("#prolongDays").val()
						}, function(result) {
							showMessage(result);
							setTimeout("", 1000);
							loadContent('${vix}/crm/business/logisticsFollowUpAction!goList.action');
						});
					} else {
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
	$("#myAffairsForm").validationEngine();
	$("#messageLogForm").validationEngine();
	bindSearch();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/remind.png" alt="" />供应链</a></li>
				<li><a href="#">会员交互管理</a></li>
				<li><a href="#">客服中心</a></li>
				<li><a href="#">事物跟进</a></li>
				<li><a href="#">物流跟进</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div id="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			店铺选择：
			<s:select id="channelDistributorId" headerKey="" headerValue="--请选择--" list="%{channelDistributorList}" listValue="name" listKey="id" value="" multiple="false" theme="simple">
			</s:select>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div class="order_table">
			<div>
				在途时长<input type="text" class="ipt" style="width: 50px;" />天&nbsp;&nbsp;&nbsp;&nbsp;是否关怀：<select><option value="">是</option></select>&nbsp;&nbsp;&nbsp;&nbsp;物流单号：<input type="text" class="ipt" style="width: 50px;" />&nbsp;&nbsp;&nbsp;&nbsp;快递公司：<select><option value="">不限</option></select>&nbsp;&nbsp;&nbsp;&nbsp;订单编号：<input type="text"
					class="ipt" style="width: 100px;" />&nbsp;&nbsp;&nbsp;&nbsp;客户昵称：<input type="text" class="ipt" style="width: 100px;" /> <input type="button" class="btn" value="筛选" /> <a href="#">重置</a>
			</div>
		</div>
		<div id="right">
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="salesOrderInfo"></b> <s:text name='cmn_to' /> <b class="salesOrderTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
				<div id="salesOrder" class="table"></div>
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
						<span><a class="start" onclick="currentPager('start');"></a> </span> <span><a class="previous" onclick="currentPager('previous');"></a> </span> <em>(<b class="salesOrderInfo"></b> <s:text name='cmn_to' /> <b class="salesOrderTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPager('next');"></a> </span> <span><a class="end" onclick="currentPager('end');"></a> </span>
					</div>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>