<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function chosedatabydate() {
		loadContent('${vix}/crm/business/purchasingBehaviorAction!goList.action?date=' + $('#date').val() + "&dateType=" + $(":radio[name=datetype][checked]").val() + "&channelDistributorId=" + $('#channelDistributorId').val() + "&weekday=" + $('#weekday').val() + "&monthdate=" + $('#monthdate').val() + "&startdate=" + $('#startdate').val() + "&enddate=" + $('#enddate').val());
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
		url : '${vix}/crm/business/purchasingBehaviorAction!goSearch.action',
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
					loadContent('${vix}/crm/business/purchasingBehaviorAction!goList.action?date=' + $('#date').val() + "&dateType=" + $(":radio[name=datetype][checked]").val() + "&channelDistributorId=" + $('#channelDistributorId').val() + "&weekday=" + $('#weekday').val() + "&monthdate=" + $('#monthdate').val() + "&startdate=" + $('#startdate').val() + "&enddate=" + $('#enddate').val());
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
</script>
<style>
.order_t {
	text-align: center;
	padding: 15px;
}

.order_t table {
	width: 100%;
}

.order_t strong {
	font-weight: bold;
}

.order_t strong.fb {
	font-size: 1.4em;
}

.dateimg {
	cursor: pointer;
}

.box {
	clear: both;
	background: #FAFAFA;
	_height: 100%;
	margin: 0 7px;
	overflow: hidden;
	position: relative;
	z-index: 7;
}

.list_center, .list_center th, .list_center td {
	text-align: center;
	border: #DDD solid 1px;
}

.list_center .list_cash, .list_center th.list_cash, .list_center td.list_cash
	{
	width: 10px;
	border-color: #06F;
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
				<li><a href="#">购买行为分析</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>搜索条件: ${searchCriteria }</label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div class="order_t">
			<table>
				<tr>
					<td colspan="6"><strong class="yellow">总成交金额<br />${totalAmount }
					</strong></td>
				</tr>
				<tr>
					<td rowspan="2"><img src="img/shop_1.png" /><br /> <br /> <span class="gray">成交金额</span><br /> <strong class="green fb">&yen;${firstOrderAmount }</strong></td>
					<td><span class="gray">订单数</span><br /> <strong class="green">${firstOrderNum }</strong></td>
					<td><span class="gray">订单数</span><br /> <strong class="blue">${buyBackOrderNum }</strong></td>
					<td rowspan="2"><img src="img/shop_2.png" /><br /> <br /> <span class="gray">成交金额</span><br /> <strong class="blue fb">&yen;${buyBackOrderAmount }</strong></td>
				</tr>
				<tr>
					<td><span class="gray">订单均价</span><br /> <strong class="green">${firstOrderPrice }</strong></td>
					<td><span class="gray">订单均价</span><br /> <strong class="blue">${buyBackOrderPrice }</strong></td>
				</tr>
			</table>
		</div>
		<div class="pagelist drop clearfix">
			<strong>明细数据</strong>
			<div>
				<span><a href="javascript:;"><img src="img/web_icon_b.gif" style="vertical-align: middle;" /> 导出</a></span>
			</div>
		</div>
		<!-- left -->
		<div class="table">
			<table class="list list_center">
				<tr>
					<th></th>
					<th>总计</th>
					<th colspan="3">其中：首次购买订单</th>
					<th colspan="3">其中：回头购买订单</th>
				</tr>
				<tr>
					<th>购买日期</th>
					<th>成交金额</th>
					<th>成交金额</th>
					<th>订单数</th>
					<th>订单均价</th>
					<th>成交金额</th>
					<th>订单数</th>
					<th>订单均价</th>
				</tr>
				<s:iterator value="orderSalesInformationList" var="entity" status="s">
					<tr>
						<td><fmt:formatDate value="${entity.buyDate }" pattern="yyyy-MM-dd" /></td>
						<td>${entity.totalAmount }</td>
						<td>${entity.firstOrderAmount }</td>
						<td>${entity.firstOrderNum }</td>
						<td>${entity.firstOrderPrice }</td>
						<td>${entity.buyBackOrderAmount }</td>
						<td>${entity.buyBackOrderNum }</td>
						<td>${entity.buyBackOrderPrice }</td>
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