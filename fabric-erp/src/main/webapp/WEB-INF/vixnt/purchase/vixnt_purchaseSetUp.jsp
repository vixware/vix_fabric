<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-shopping-cart"></i> 采购管理<span>> 采购设置 </span>
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="tab_menu">
				<ul>
					<li class="selected" onclick="">业务</li>
					<li onclick="">预警</li>
					<li onclick="">权限</li>
					<li onclick="">参照控制</li>
					<li onclick="">流程控制</li>
					<li onclick="">打印\打印控制</li>
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
									<label>普通业务必须要有订单</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>直运业务必须要有订单</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="ConsignmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>受托代销业务必须要有订单</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="EnableConsignment" data-prompt-position="topLeft" type="checkbox"/>
									<label>启用受托代销</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否允许超订单到货及入库</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraPlannedOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>是否允许超计划订货</label>
								</div>
							</div>
							<legend><input id="identityId" name="WhetherToAllowUltraPlannedOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>入库单是否自动带入单价</label></legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="EnableConsignment" data-prompt-position="topLeft" type="radio"/>
									<label>手工录入</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" data-prompt-position="topLeft" type="radio"/>
									<label>参考成本</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraPlannedOrders" data-prompt-position="topLeft" type="radio"/>
									<label>最新成本</label>
								</div>
							</div>
							<legend>订单\到货单\发票单价录入方式</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="MonovalentEntryWay" data-prompt-position="topLeft" type="radio"/>
									<label>手工录入</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="MonovalentEntryWay" data-prompt-position="topLeft" type="radio"/>
									<label>取自供应商存货价格表价格</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="MonovalentEntryWay" data-prompt-position="topLeft" type="radio" checked="checked"/>
									<label>最新价格(来源同历史交易价参照设置)</label>
								</div>
							</div>
							<legend>历史交易价参照设置</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="WhetherTheSuppliersTakePrice" data-prompt-position="topLeft" type="radio"/>
									<label>是否按供应商取价</label>
								</div>
								<div class="col-md-4">
									<label>来源</label>
									<select name="Source">
										<option value="0">订单</option>
									</select>
								</div>
								<div class="col-md-4">
									<label>显示最近<input type="text" name="TransactionHistory" />次历史交易记录</label>
								</div>
							</div>
							<legend>最高进阶控制口令</legend>
							<div class="form-group">
								<div class="col-md-6">
									<input type="text" name="txtCommand" class="form-control" value="">
								</div>
								<button onclick="orderTypeTable.ajax.reload();" type="button" class="btn btn-info">
									更改
								</button>
							</div>
							<legend>修改税额时是否改变税率</legend>
							<div class="form-group">
								<label class="col-md-1 control-label"> 单行容差:</label>
								<div class="col-md-4">
									<input type="text" name="txtCommand" class="form-control" value="">
								</div>
								<label class="col-md-1 control-label">合计容差:</label>
								<div class="col-md-4">
									<input type="text" name="txtCommand" class="form-control" value="">
								</div>
							</div>
							<legend>结算选项</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraPlannedOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>商业版费用是否分摊到入库成本</label>
								</div>
							</div>
						</fieldset>	
					</form>
				</div>
				<div class="common">
					<form method="get">
						<fieldset>
							<legend><input type="checkbox" name="ChangingTaxRates" /><label>预警和报警</label></legend>
							<div class="form-group">
								<label class="col-md-2 control-label"> 提前预警天数:</label>
								<div class="col-md-4">
									<input type="text" name="txtCommand" class="form-control" value="">
								</div>
								<label class="col-md-2"> 逾期报警天数:</label>
								<div class="col-md-4">
									<input type="text" name="txtCommand" class="form-control" value="">
								</div>
							</div>
						</fieldset>
					</form>		
				</div>
				<div class="common">
					<form method="get">
						<fieldset>
							<legend>基本权限控制</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="OrdinaryBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>检查存货权限</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>检查供应商权限</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="ConsignmentBizMustHaveOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>检查部门权限</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="EnableConsignment" data-prompt-position="topLeft" type="checkbox"/>
									<label>检查业务员权限</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" data-prompt-position="topLeft" type="checkbox"/>
									<label>检查操作员权限</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraPlannedOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>检查金额审核权限</label>
								</div>
							</div>
						</fieldset>
					</form>		
				</div>
				<div class="common">
					<form method="get">
						<fieldset>
							<legend>系统启用</legend>
							<div class="form-group">
								<label class="col-md-2 control-label"> 启用日期：</label>
								<div class="col-md-4">
									<label>2013-03-18</label>
								</div>
								<label class="col-md-2 control-label"> 本系统启用的会计月:</label>
								<div class="col-md-4">
									<select><option>2013</option></select>年 <select><option>5</option></select>月
								</div>
							</div>
							<legend>浮动换算率计算规则</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="EnableConsignment" data-prompt-position="topLeft" type="radio"/>
									<label>件数为准</label>
								</div>
								<div class="col-md-3">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" data-prompt-position="topLeft" type="radio"/>
									<label>数量为准</label>
								</div>
							</div>
							<legend>远程应用</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="EnableConsignment" data-prompt-position="topLeft" type="checkbox"/>
									<label>启用远程</label>
								</div>
								<label class="col-md-2 control-label"> 远程识别号:</label>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" class="form-control"  data-prompt-position="topLeft" type="text"/>
								</div>
							</div>
							<legend>发票默认率设置</legend>
							<div class="form-group">
								<label class="col-md-2 control-label"> 专用发票默认税率:</label>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" class="form-control"  data-prompt-position="topLeft" type="text"/>
								</div>
							</div>
							<legend>参照方式设置</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="OrdinaryBizMustHaveOrders" data-prompt-position="topLeft" type="radio"/>
									<label>基于基串精确匹配</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="radio"/>
									<label>基于基串向后匹配</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="ConsignmentBizMustHaveOrders" data-prompt-position="topLeft" type="radio"/>
									<label>基于基串向前匹配</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="EnableConsignment" data-prompt-position="topLeft" type="radio"/>
									<label>基于基串前后模糊匹配</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" data-prompt-position="topLeft" type="radio"/>
									<label>查询全部不做模糊匹配</label>
								</div>
							</div>
							<div class="form-group">	
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraPlannedOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>检查业务员权限</label>
								</div>
							</div>
						</fieldset>	
					</form>
				</div>
				<div class="common">
					<form method="get">
						<fieldset>
							<legend>流程控制</legend>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="OrdinaryBizMustHaveOrders" data-prompt-position="topLeft" type="radio"/>
									<label>基于基串精确匹配</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="DirectShipmentBizMustHaveOrders" data-prompt-position="topLeft" type="radio"/>
									<label>基于基串向后匹配</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="ConsignmentBizMustHaveOrders" data-prompt-position="topLeft" type="radio"/>
									<label>基于基串向前匹配</label>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4">
									<input id="identityId" name="EnableConsignment" data-prompt-position="topLeft" type="radio"/>
									<label>基于基串前后模糊匹配</label>
								</div>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" data-prompt-position="topLeft" type="radio"/>
									<label>查询全部不做模糊匹配</label>
								</div>
							</div>
							<div class="form-group">	
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraPlannedOrders" data-prompt-position="topLeft" type="checkbox"/>
									<label>检查业务员权限</label>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<div class="common">
					<form method="get">
						<fieldset>
							<legend>打印控制</legend>
							<div class="form-group">	
								<label class="col-md-2 control-label"> 打印次数最多:</label>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" class="form-control"  type="text"/>
								</div>
								<label class="col-md-2 control-label"> 打印预览格式:</label>	
								<div class="col-md-3">
									<select>
										<option value="0">A4</option>
										<option value="1">A5</option>
									</select>
								</div>
							</div>	
							<legend>导出控制</legend>
							<div class="form-group">	
								<label class="col-md-2 control-label"> 导出路径:</label>
								<div class="col-md-4">
									<input id="identityId" name="WhetherToAllowUltraOrderArrivalAndStorage" class="form-control"  data-prompt-position="topLeft" type="text"/>
								</div>
								<label class="col-md-2 control-label">导出格式:</label>	
								<div class="col-md-4">
									<select>
										<option value="0">Word</option>
										<option value="1">Excel</option>
									</select>
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