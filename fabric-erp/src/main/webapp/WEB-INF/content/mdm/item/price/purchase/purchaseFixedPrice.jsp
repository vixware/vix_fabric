<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
//载入分页列表数据
pager("start","${vix}/mdm/item/purchaseItemPriceAction!fixedPrice.action?id=${id}&count=${count}&billCreateDate=${billCreateDate}&supplierId=${supplierId}&channelDistributorId=${channelDistributorId}&customerAccountId=${customerAccountId}&regionalId=${regionalId}",'fixedPrice');
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>${vv:varView('vix_mdm_item')}编码：${item.code} ${vv:varView('vix_mdm_item')}名称：${item.name}</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div class="right_content">
				<div id="fixedPrice" class="table"></div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>