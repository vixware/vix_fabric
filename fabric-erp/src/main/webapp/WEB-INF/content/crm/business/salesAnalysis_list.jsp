<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>


<link href="${vix}/common/js/vreport/vreport/css/vreport.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/js/vreport/jquery/css/jquery-ui-1.9.1.custom.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/vreport/vprint.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/jquery/jquery-1.8.2.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/jquery/jquery-ui-1.9.1.custom.min.js" type="text/javascript"></script>
<script src="${vix}/common/js/raphael-min.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/flowtask.js" type="text/javascript"></script>
<script src="${vix}/common/js/vreport/vreport.js" type="text/javascript"></script>
<script type="text/javascript">
	function chosedatabydate() {
		loadContent('${vix}/crm/business/salesAnalysisAction!goList.action?date=' + $('#date').val() + "&dateType=" + $(":radio[name=datetype][checked]").val() + "&channelDistributorId=" + $('#channelDistributorId').val() + "&weekday=" + $('#weekday').val() + "&monthdate=" + $('#monthdate').val() + "&startdate=" + $('#startdate').val() + "&enddate=" + $('#enddate').val());
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
		url : '${vix}/crm/business/salesAnalysisAction!goSearch.action',
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
					loadContent('${vix}/crm/business/salesAnalysisAction!goList.action?date=' + $('#date').val() + "&dateType=" + $(":radio[name=datetype][checked]").val() + "&channelDistributorId=" + $('#channelDistributorId').val() + "&weekday=" + $('#weekday').val() + "&monthdate=" + $('#monthdate').val() + "&startdate=" + $('#startdate').val() + "&enddate=" + $('#enddate').val());
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
</script>
<style>
<!--
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
-->
</style>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/Alert_11.png" alt="" />供应链 </a></li>
				<li><a href="#">会员交互管理</a></li>
				<li><a href="#">商业智能与决策 </a></li>
				<li><a href="#">会员结构分析 </a></li>
				<li><a href="#">销售分析 </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>根据条件搜索:</label> <label><label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div class="box">
		<div id="contentDiv"></div>
		<script>
			var report = new Rreport({
			contentDiv : 'contentDiv',
			isEdit : true,
			saveFun : function(data) {
				document.write(data);
			}
			});

			report.loadData({
			"viewType" : "h",
			"width" : "100%",
			"height" : "750",
			"link" : [ {
			"id" : "link1",
			"from" : "node1",
			"to" : [ "node2", "node3" ],
			"length" : 13
			}, {
			"id" : "link2",
			"from" : "node3",
			"to" : [ "node4", "node5" ],
			"length" : 24
			}, {
			"id" : "link3",
			"from" : "node2",
			"to" : [ "node7", "node8" ],
			"length" : 26
			} ],
			"node" : [ {
			"x" : 745,
			"y" : 25,
			"title" : "成交金额",
			"titleRemark" : "",
			"content" : "",
			"id" : "node1",
			"w" : 172,
			"h" : 100,
			"th" : 40,
			"url" : "/vixnt/crm/business/salesAnalysisAction!goShowData.action"
			}, {
			"x" : 1141,
			"y" : 234,
			"title" : "订单均价",
			"titleRemark" : "",
			"content" : "",
			"id" : "node2",
			"w" : 172,
			"h" : 100,
			"th" : 40,
			"url" : "/vixnt/crm/business/salesAnalysisAction!goOrderPriceData.action"
			}, {
			"x" : 380,
			"y" : 227,
			"title" : "成交订单数",
			"titleRemark" : "",
			"content" : "",
			"id" : "node3",
			"w" : 190,
			"h" : 100,
			"th" : 40,
			"url" : "/vixnt/crm/business/salesAnalysisAction!goBuyNumData.action"
			}, {
			"x" : 505,
			"y" : 460,
			"title" : "回头购买订单数",
			"titleRemark" : "",
			"content" : "",
			"id" : "node4",
			"w" : 226,
			"h" : 100,
			"th" : 40,
			"url" : "/vixnt/crm/business/salesAnalysisAction!goBuyMoreData.action"
			}, {
			"x" : 178,
			"y" : 460,
			"title" : "首次购买订单数",
			"titleRemark" : "",
			"content" : "",
			"id" : "node5",
			"w" : 226,
			"h" : 100,
			"th" : 40,
			"url" : "/vixnt/crm/business/salesAnalysisAction!goBuyOneData.action"
			}, {
			"x" : 1322,
			"y" : 460,
			"title" : "平均订单件数",
			"titleRemark" : "",
			"content" : "",
			"id" : "node7",
			"w" : 208,
			"h" : 100,
			"th" : 40,
			"url" : "/vixnt/crm/business/salesAnalysisAction!goOrderAvgNumData.action"
			}, {
			"x" : 948,
			"y" : 460,
			"title" : "关联销售订单占比",
			"titleRemark" : "",
			"content" : "",
			"id" : "node8",
			"w" : 244,
			"h" : 100,
			"th" : 40,
			"url" : "/vixnt/crm/business/salesAnalysisAction!goShowData.action"
			} ]
			});

			$('#vreporttoolbar').attr({
				style : 'display:none'
			});
		</script>

		<div class="table">
			<table class="list">
				<tr>
					<th width="50">
						<div class="list_check">
							<div class="drop">
								<label><input name="" type="checkbox" value="" /></label>
								<ul>
									<li><a href="#">所有</a></li>
									<li><a href="#">其他</a></li>
									<li><a href="#">式样</a></li>
									<li><a href="#">关闭</a></li>
								</ul>
							</div>
						</div>
					</th>
					<th>买了A商品（前项商品）<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
					<th>人数<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
					<th>还买了B商品（后项商品）<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
					<th>人数<a href="#"><img src="img/arrow.gif" alt="" /></a></th>
					<th>购买连带率</th>
					<th>可推荐人数</th>
					<th>预计成功数（人）</th>
				</tr>
				<s:iterator value="combinedSalesGoodsList" var="entity" status="s">
					<tr>
						<td><input name="" type="checkbox" value="" /></td>
						<td><dl class="goods_info">
								<dt>
									<img src="http://qiniuphotos.qiniudn.com/gogopher.jpg?imageView2/1/w/50/h/50">
								</dt>
								<dd>
									<p>${entity.agoodsName }</p>
									<a href="#" class="pull-right"><img src="img/list_icon_24.gif"></a>
								</dd>
							</dl></td>
						<td>${entity.apeoplenumber }</td>
						<td><dl class="goods_info">
								<dt>
									<img src="http://qiniuphotos.qiniudn.com/gogopher.jpg?imageView2/1/w/50/h/50">
								</dt>
								<dd>
									<p>${entity.bgoodsName }</p>
									<a href="#" class="pull-right"><img src="img/list_icon_24.gif"></a>
								</dd>
							</dl></td>
						<td>${entity.bpeoplenumber }</td>
						<td>${entity.purchaseJointRate }%</td>
						<td>${entity.apeoplenumber }<br /> <input type="button" class="btn" value="立即营销" /><br /></td>
						<td>${entity.bpeoplenumber }</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>