<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function chosedatabydate() {
		loadContent('${vix}/crm/business/categoryAnalysisAction!goList.action?date=' + $('#date').val() + "&dateType=" + $(":radio[name=datetype][checked]").val() + "&channelDistributorId=" + $('#channelDistributorId').val() + "&weekday=" + $('#weekday').val() + "&monthdate=" + $('#monthdate').val() + "&startdate=" + $('#startdate').val() + "&enddate=" + $('#enddate').val());
	}

	function funccc() {
		$dp.$('weekday').value = $dp.cal.getP('y') + '-' + $dp.cal.getP('W', 'WW');
	}

	function chooseChannelDistributor() {
		$.ajax({
		url : '${vix}/crm/business/purchasingBehaviorAction!goChooseChannelDistributor.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 550,
			height : 350,
			title : "选择网店",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$('#channelDistributorId').val(result[0]);
						$('#channelDistributorName').val(result[1]);
					} else {
						asyncbox.success("请选择网店信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};

	function goSearch() {
		$.ajax({
		url : '${vix}/crm/business/categoryAnalysisAction!goSearch.action',
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
					loadContent('${vix}/crm/business/categoryAnalysisAction!goList.action?date=' + $('#date').val() + "&dateType=" + $(":radio[name=datetype][checked]").val() + "&channelDistributorId=" + $('#channelDistributorId').val() + "&weekday=" + $('#weekday').val() + "&monthdate=" + $('#monthdate').val() + "&startdate=" + $('#startdate').val() + "&enddate=" + $('#enddate').val());
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
</script>
<style>
.list_center, .list_center th, .list_center td {
	text-align: center;
	border: #DDD solid 1px;
}

.list_center .list_cash, .list_center th.list_cash, .list_center td.list_cash
	{
	width: 10px;
	border-color: #06F;
}

.goods_info {
	padding: 10px;
	width: 284px;
}

.goods_info dt {
	float: left;
	padding-right: 10px;
}

.goods_info dd {
	overflow: hidden;
	line-height: 18px;
	text-align: left;
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
				<li><a href="#">商业智能与决策</a></li>
				<li><a href="#">品类分析</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>根据条件搜索:</label> <label><label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div class="pagelist drop clearfix">
			<strong>明细数据</strong>
			<div>
				<span><a href="javascript:;"><img src="img/web_icon_b.gif" style="vertical-align: middle;" /> 导出</a></span>
			</div>
		</div>
		<div class="table">
			<table class="list list_center">
				<tr>
					<th style="text-align: left;">
						<!-- <input type="text" class="ipt" style="width: 200px;" /> <input type="button" class="btn" value="搜索" /> -->
					</th>
					<th colspan="3">首次购买订单</th>
					<th colspan="3">${selectDate }订单</th>
					<th colspan="3">环比变化</th>
				</tr>
				<tr>
					<th>宝贝</th>
					<th>成交金额（元）</th>
					<th>成交件数</th>
					<th>件单价（元）</th>
					<th>成交金额（元）</th>
					<th>成交件数</th>
					<th>件单价（元）</th>
					<th>成交金额</th>
					<th>成交件数</th>
					<th>件单价</th>
				</tr>
				<s:iterator value="goodsSaleInformationList" var="entity" status="s">
					<tr>
						<td><dl class="goods_info">
								<dt>
									<img src="http://qiniuphotos.qiniudn.com/gogopher.jpg?imageView2/1/w/50/h/50" />
								</dt>
								<dd>
									<p>${entity.goodsName }</p>
									<a href="#" class="pull-right"><img src="img/list_icon_24.gif" /></a><span class="gray">类目：帽子</span>
								</dd>
							</dl></td>
						<td>${entity.amount }</td>
						<td>${entity.quantity }</td>
						<td>${entity.unitPrice }</td>
						<td>${entity.amount }</td>
						<td>${entity.quantity }</td>
						<td>${entity.unitPrice }</td>
						<td><span class="green">${entity.amount }</span><img src="img/web_icon_t.gif" style="vertical-align: middle;" /></td>
						<td><span class="green">${entity.quantity }</span><img src="img/web_icon_t.gif" style="vertical-align: middle;" /></td>
						<td><span class="red">${entity.unitPrice }</span><img src="img/web_icon_t.gif" style="vertical-align: middle;" /></td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>