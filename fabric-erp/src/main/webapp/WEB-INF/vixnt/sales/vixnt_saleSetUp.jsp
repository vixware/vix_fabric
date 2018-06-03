<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-shopping-cart"></i> 销售管理<span>> 销售设置 </span>
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="tab_menu">
				<ul>
					<li class="selected" onclick="">业务控制</li>
					<li onclick="">其他控制</li>
					<li onclick="">信用控制</li>
					<li onclick="">可用量控制</li>
					<li onclick="">价格管理</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="tab-cons">
				<div class="common">
					<form method="get">
						<fieldset>
							<legend>基础选项</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="OrdinaryBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否有零售日报业务</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否有销售调拨业务</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="ConsignmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否有委托代销业务</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="EnableConsignment" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否有分期收款业务</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否有直运销售业务</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraPlannedOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否有超订量发货控制</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="EnableConsignment" data-prompt-position="topLeft" type="checkbox"/>
									<label>允许超发货量开票</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" data-prompt-position="topLeft" type="checkbox"/>
									<label>订单变更保存历史记录</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraPlannedOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>近期失效存货检查</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="EnableConsignment" data-prompt-position="topLeft" type="checkbox"/>
									<label>折扣小数位数</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" data-prompt-position="topLeft" type="checkbox"/>
									<label>红字单据允许红蓝混录</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-8">
									<input id="identityId" name="WhetherToAllowUltraPlannedOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>普通销售必有订单、委托代销必有订单、分期收款必有订单、直运销售必有订单</label>
								</div>
							</div>
						</fieldset>
					</form>
				</div>			
				<div class="common">
					<form method="get">
						<fieldset>
							<legend>基础选项</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="OrdinaryBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>新增发货默认</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>新增退货单默认</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="ConsignmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>新增发票默认</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="OrdinaryBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>追踪型存货参照对应入库单是否显示单价</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>订单批量生成发货时表体记录可生成的发货单数量</label>
								</div>
							</div>
						</fieldset>	
					</form>		
				</div>
				<div class="common">
					<form method="get">
						<fieldset>
							<legend>基础选项</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否需要信用审批</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="ConsignmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>信用控制范围</label>
								</div>
							
								<div class="col-md-4">
									<input id="identityId" name="OrdinaryBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>信用检查点</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>控制信用的单据</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>额度检查公式</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-8">
									<input id="identityId" name="OrdinaryBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否有客户信用控制、是否有部门信用控制、是否有业务员信用控制</label>
								</div>
							</div>
						</fieldset>	
					</form>		
				</div>
				<div class="common">
					<form method="get">
						<fieldset>
							<legend>基础选项</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="OrdinaryBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否允许非批次存货超可用量发货</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否允许批次存货超可用量发货</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="ConsignmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>发货单/发票非追踪型存货可用量控制公式</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="OrdinaryBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>发货单/发票非追踪型存货可用量检查公式</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>订单追踪型存货可用量检查公式</label>
								</div>
							</div>
						</fieldset>	
					</form>
				</div>
				<div class="common">
					<form method="get">
						<fieldset>
							<legend>基础选项</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="OrdinaryBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>报价参照设置</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>价格参照过滤设置</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="ConsignmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否有最低售价控制</label>
								</div>
							</div>
						</fieldset>	
					</form>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	var tab_menu_li = $('.tab_menu li');
	$('.tab-cons .common:gt(0)').hide();
	
	tab_menu_li.click(function(){
	    var index = $(this).index()+1;
	    $(this).addClass('selected')
	            .siblings().removeClass('selected');
	    var tab_menu_li_index = tab_menu_li.index(this);
	    $('.tab-cons .common').eq(tab_menu_li_index).show()
	            .siblings().hide();
	})
</script>