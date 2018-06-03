<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateOrder() {
		$.post('${vix}/template/orderAction!saveOrUpdate.action', {
		'order.id' : $("#id").val(),
		'order.code' : $("#code").val(),
		'order.memo' : $("#memo").val()
		}, function(result) {
			asyncbox.success(result, "提示信息", function(action) {
				loadContent('${vix}/template/orderAction!goList.action');
			});
		});
	}
	$("#order").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
	</h2>
	<div id="breadCrumb" class="breadCrumb module">
		<ul>
			<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
			<li><a href="#"><s:text name="ldm_dtbcenter_management" /> </a></li>
			<li><a href="#"><s:text name="ldm_distribution_management" /> </a></li>
			<a href="#"><s:text name="ldm_distribution" /> </a>
		</ul>
	</div>
</div>
<input type="hidden" id="id" name="id" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCustomer()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/pickupBusinessAnalysisAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>提货业务分拆 </b> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong><s:text name="inventory_basicinfo" /> </strong>
								</dt>
								<dd style="display: block;"></dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--oder-->
	</form>
</div>