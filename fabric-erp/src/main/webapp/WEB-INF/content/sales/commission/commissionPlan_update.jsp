<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript"> 
<!-- 
function saveOrUpdateCommissionPlan(){
	if($('#commissionPlanForm').validationEngine('validate')){
		$.post('${vix}/sales/commission/commissionPlanAction!saveOrUpdate.action',
				{
					'commissionPlan.id':$("#id").val(),
					'commissionPlan.saleOrg.id':$("#saleOrgId").val(),
					'commissionPlan.cpCode':$("#cpCode").val(),
					'commissionPlan.cpName':$("#cpName").val(),
					'commissionPlan.memo':$("#memo").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/sales/commission/commissionPlanAction!goList.action?temp=1');
				}
			 );
	}
}

function chooseSaleOrg(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action?canCheckComp=0',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择销售组织",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var resObj = $.parseJSON(returnValue);
								var uId = resObj[0].realId;
								$("#saleOrgId").val(uId);
								$("#saleOrgName").val(resObj[0].name);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
$("#commissionPlanForm").validationEngine();
if($(".ms").length>0){
	$(".ms").hover(function(){
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">ul",this).stop().slideUp(100);
	});
	$(".ms ul li").hover(function(){
		$(">a",this).addClass("hover");
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">a",this).removeClass("hover");
		$(">ul",this).stop().slideUp(100);
	});
}
$(window).scroll(function(){
	if($('#orderTitleFixd').parent('dl').offset().top - 43 < $(window).scrollTop()){
		if(!$('#orderTitleFixd').hasClass('fixed')){
			$('#orderTitleFixd').addClass('fixed');
			$('#orderTitleFixd').parent('dl').css('padding-top',30);
		}
	}else{
		$('#orderTitleFixd').removeClass('fixed');
		$('#orderTitleFixd').parent('dl').css('padding-top',0);
	}
});
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_inquire.png" alt="" /> <s:text name="supplyChain" /></a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">销售佣金管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/commission/commissionPlanAction!goList.action');">佣金方案</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${commissionPlan.id }" />
<div class="content">
	<form id="commissionPlanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCommissionPlan();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/commission/commissionPlanAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b>新增佣金计划</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">销售组织：</td>
											<td><input type="hidden" id="saleOrgId" value="${commissionPlan.saleOrg.id}" /> <input id="saleOrgName" name="commissionPlan.saleOrg.fullName" value="${commissionPlan.saleOrg.fullName}" type="text" size="30" class="validate[required] text-input" /> <a class="abtn" href="#" onclick="chooseSaleOrg();"><span>选择</span></a></td>
											<td align="right">计划编码：</td>
											<td><input id="cpCode" name="commissionPlan.cpCode" value="${commissionPlan.cpCode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">计划名称：</td>
											<td><input id="cpName" name="commissionPlan.cpName" value="${commissionPlan.cpName}" type="text" size="30" /></td>
											<td align="right">备注：</td>
											<td><input id="memo" name="commissionPlan.memo" value="${commissionPlan.memo}" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="item" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#itemTb',url: '${vix}/sales/commission/commissionPlanAction!getCommissionPlanItemJson.action?id=${commissionPlan.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'cpiCode',width:80,align:'center'">条款编码</th>
										<th data-options="field:'name',width:120,align:'center'">名称</th>
										<th data-options="field:'bizType',width:100,align:'center'">业务类别</th>
										<th data-options="field:'itemType',width:80,align:'center'">${vv:varView('vix_mdm_item')}类别</th>
										<th data-options="field:'computeBaseTarget',width:60,align:'center'">计算基础</th>
										<th data-options="field:'performanceEvaluationMethod',formatter:function(value,row){return row.performanceEvaluationMethod.name;},width:60,align:'center'">业绩考评方式</th>
										<th data-options="field:'adjustCoefficient',width:60,align:'center'">业务调整系数</th>
										<th data-options="field:'computeStyle',width:60,align:'center'">计算方式</th>
										<th data-options="field:'isGrandTotal',width:60,align:'center'">是否考虑累计业绩</th>
										<th data-options="field:'fixedCommissionTerm',width:60,align:'center'">固定佣金比率</th>
										<th data-options="field:'fixedCommission',width:60,align:'center'">固定佣金</th>
									</tr>
								</thead>
							</table>
							<div id="itemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-add',plain:true" onclick="updateItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#item').datagrid();
								function removeItem(){
									var row = $('#item').datagrid('getSelected');
									if(row){
										asyncbox.confirm('是否删除该计划明细?','提示信息',function(action){
											if(action == 'ok'){
												var index = $('#item').datagrid('getRowIndex',row);
												$('#item').datagrid('deleteRow', index);
												$.ajax({
													  url:'${vix}/sales/commission/commissionPlanItemAction!deleteById.action?id='+row.id,
													  cache: false,
													  success: function(html){
														  showMessage(html);
													  }
												});
											}
										});
									}else{
										showMessage("请选择一条数据!");
									}
								}
								 
								function updateItem(){
									var row = $('#item').datagrid('getSelected');
									if(row){
										addItem(row.id);
									}else{
										showMessage("请选择一条数据!");
									}
								}
								function addItem(id){
									$.ajax({
										  url:'${vix}/sales/commission/commissionPlanItemAction!goSaveOrUpdate.action?id='+id,
										  cache: false,
										  success: function(html){
											  asyncbox.open({
												 	modal:true,
													width : 760,
													height : 360,
													title:"计划明细",
													html:html,
													callback : function(action){
														if(action == 'ok'){
															if($('#commissionPlanItemForm').validationEngine('validate')){
																$.post('${vix}/sales/commission/commissionPlanItemAction!saveOrUpdate.action',
																		{'commissionPlanItem.id':$("#cpiId").val(),
																		  'commissionPlanItem.cpiCode':$("#cpiCode").val(),
																		  'commissionPlanItem.name':$("#name").val(),
																		  'commissionPlanItem.bizType':$("#bizType").val(),
																		  'commissionPlanItem.itemType':$("#itemType").val(),
																		  'commissionPlanItem.computeBaseTarget':$("#computeBaseTarget").val(),
																		  'commissionPlanItem.performanceEvaluationMethod.id':$("#salesPerformanceEvaluationMethodId").val(),
																		  'commissionPlanItem.adjustCoefficient':$("#adjustCoefficient").val(),
																		  'commissionPlanItem.computeStyle':$("#computeStyle").val(),
																		  'commissionPlanItem.isGrandTotal':$("#isGrandTotal").val(),
																		  'commissionPlanItem.fixedCommissionTerm':$("#fixedCommissionTerm").val(),
																		  'commissionPlanItem.fixedCommission':$("#fixedCommission").val(),
																		  'commissionPlanItem.commissionPlan.id':$("#id").val()
																		},
																		function(result){
																			showMessage(result);
																			setTimeout("",1000);
																			$('#item').datagrid("reload");
																		}
																	);
															}else{
											  					return false;
											  				}
														}
													},
													btnsbar : $.btn.OKCANCEL
												});
										  }
									});
								}
						</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--oder-->
		<div class="sub_menu sub_menu_bot" style="display: none;">
			<div class="drop">
				<p>
					<a href="#"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/template/orderAction!goList.action');"><span>返回</span></a> <a href="#"><span>编辑</span></a> <a href="#"><span>复制</span></a> <a href="#"><span>删除</span></a> <a href="#"><span>关闭并新建</span></a> <a href="#"><span>关闭</span></a> <a href="#" id="text"><span>弹出窗口测试</span></a>
				</p>
				<ul>
					<li><a href="#"><span>帮助</span></a>
						<ul>
							<li><a href="#">帮助</a></li>
							<li><a href="#">帮助</a></li>
							<li><a href="#">帮助</a></li>
							<li><a href="#">帮助</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<!--submenu-->
	</form>
</div>
<!-- content -->