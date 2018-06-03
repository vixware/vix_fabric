<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function loadCurrentExpenseDetail() {
		$.ajax({
		url : '${vix}/inventory/outBoundAction!goListSalesOrderContent.action?id=' + $("#id").val(),
		cache : false,
		success : function(html) {
			$("#currentExpenseDetailTable").html(html);
		}
		});
	};
	loadCurrentExpenseDetail();
</script>
<input type="hidden" id="id" name="id" value="${stockRecords.id}" />
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action?pageNo=${pageNo}');"><s:text name="inventory_inbound_business" /> </a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action?pageNo=${pageNo}');"><span>返回</span> </a>
	</div>
</div>
<div class="content" id="ordercontent">
	<form id="order">
		<div class="order">
			<dl>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(2,1,'a',event);loadCurrentExpenseDetail();"><img alt="" src="img/mail.png">引用单据</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="display: block;">
						<div class="list_table" style="margin: 0; width: 100%"></div>
						<div style="padding: 8px;">
							<div id="currentExpenseDetailTable" class="list_table" style="margin: 0; width: 100%"></div>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>