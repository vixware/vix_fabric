<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

pager("start","${vix}/crm/business/changePostAndPriceAction!goSingleList.action?1=1",'salesOrder');
//修改价格
function goUpdatePrice(id){
	$.ajax({
		  url:'${vix}/crm/business/changePostAndPriceAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 450,
					height : 200,
					title:"订单信息",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if ($('#salesOrderForm').validationEngine('validate')) {
								$.post('${vix}/crm/business/changePostAndPriceAction!updatePrice.action', {
								'salesOrder.id' : $("#salesOrderId").val(),
								'postFee' : $("#postFee").val()
								}, function(result) {
									showMessage(result);
									setTimeout("", 1000);
									loadContent('${vix}/crm/business/changePostAndPriceAction!goList.action');
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
//排序
function orderBy(orderField) {
	var orderBy = $("#salesOrderOrderBy").val();
	if (orderBy == 'desc') {
		orderBy = "asc";
	} else {
		orderBy = 'desc';
	}
	pager("start", "${vix}/crm/business/changePostAndPriceAction!goSingleList.action?orderField=" + orderField + "&orderBy=" + orderBy, 'salesOrder');
}
function currentPager(tag){
	pager(tag,"${vix}/crm/business/changePostAndPriceAction!goSingleList.action?1=1",'salesOrder');
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sale/saleOrder.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">会员交互管理</a></li>
				<li><a href="#">客服中心 </a></li>
				<li><a href="#">改邮费</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop"></div>
</div>
<div class="content">
	<div id="c_head" class=" drop">
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
		</ul>
		<div>
			<label>订单编码:<input type="text" class="int" id="orderCode"></label> <label>客户姓名:<input type="text" class="int" id="buyerNick"></label> <label>手机号码:<input type="text" class="int" id="receiverMobile"></label> <label><input id="simpleSearch" type="button" value="<s:text name='cmn_search'/>" class="btn"
				onclick="searchForContent();"> <input id="simpleReset" type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="resetForContent()"> </label> <label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<s:iterator value="salesOrderList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id});">${c.code}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div class="pagelist drop">
					<ul>
						<li class="tp"><a href="#"><s:text name='cmn_choose' /> </a>
							<ul>
								<li><a href="#"><s:text name='cmn_delete' /> </a></li>
								<li><a href="#"><s:text name='cmn_mail' /> </a></li>
								<li><a href="#"><s:text name="cmn_merger" /> </a></li>
								<li><a href="#"><s:text name="cmn_export" /> </a></li>
							</ul></li>
					</ul>
					<strong><s:text name="cmn_selected" />:<span id="selectCount1">0</span> </strong>
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
					<strong><s:text name="cmn_selected" />:<span id="selectCount2">0</span> </strong>
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