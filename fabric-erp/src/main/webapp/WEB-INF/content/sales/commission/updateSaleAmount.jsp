<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
 
$("#saleAmountForm").validationEngine();
function saveOrUpdateSaleAmount(){
	if($('#saleAmountForm').validationEngine('validate')){
		$.post('${vix}/sales/commission/saleAmountAction!saveOrUpdate.action',
			{'saleAmount.id':$("#id").val(),
			  'saleAmount.code':$("#code").val(),
			  'saleAmount.name':$("#name").val(),
			  'saleAmount.saleOrg.id':$("#saleOrgId").val(),
			  'saleAmount.saleOrg.fullName':$("#saleOrgName").val(),
			  'saleAmount.personnelCategory.id':$("#personnelCategoryId").val(),
			  'saleAmount.employee.id':$("#employeeId").val(),
			  'saleAmount.year':$("#year").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/commission/saleAmountAction!goList.action');
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
function choosePersonnelCategory(){
	$.ajax({
		  url:'${vix}/sales/commission/personnelCategoryAction!goChoosePersonnelCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 340,
					title:"选择父分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(typeof(returnValue) == undefined){
								$("#personnelCategoryId").val("");
								$("#personnelCategoryName").val("");
								asyncbox.success("<s:text name='pleaseChoosePersonnelCategory'/>","<s:text name='vix_message'/>");
							}else{
								var result = returnValue.split(",");
								$("#personnelCategoryId").val(result[0]);
								$("#personnelCategoryName").val(result[1]);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseEmployee(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择销售员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#employeeId").val(result[0].replace(",",""));
								$("#employeeName").val(result[1].replace(",",""));
							}else{
								$("#employeeId").val("");
								$("#employeeName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
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
				<li><a href="#"><img src="img/pur_plan.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">销售佣金管理</a></li>
				<li><a href="#">设置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/commission/saleAmountAction!goList.action');">销售定额</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${saleAmount.id}" />
<input type="hidden" id="billGroupCodeId" name="billGroupCodeId" value="${billGroupCode.id}" />
<div class="content">
	<form id="saleAmountForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateSaleAmount();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/commission/saleAmountAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="saleAmount.name != null ">
							${saleAmount.name}
						</s:if> <s:else>
							新增销售定额
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table>
										<tr>
											<td align="right" width="15%">编码:</td>
											<td width="35%"><input id="code" name="saleAmount.code" value="${saleAmount.code}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right" width="10%">主题:</td>
											<td width="40%"><input id="name" name="saleAmount.name" value="${saleAmount.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">销售组织:</td>
											<td><input id="saleOrgName" name="saleAmount.saleOrg.fullName" value="${saleAmount.saleOrg.fullName}" type="text" size="30" /> <input type="hidden" id="saleOrgId" name="saleOrgId" value="${saleAmount.saleOrg.id}" /> <span><a class="abtn" href="#" onclick="chooseSaleOrg();"><span>选择</span></a></span></td>
											<td align="right">销售员类别:</td>
											<td><input id="personnelCategoryName" name="saleAmount.personnelCategory.name" value="${saleAmount.personnelCategory.name}" type="text" size="30" /> <input type="hidden" id="personnelCategoryId" name="personnelCategoryId" value="${saleAmount.personnelCategory.id}" /> <span><a class="abtn" href="#"
													onclick="choosePersonnelCategory();"><span>选择</span></a></span></td>
										</tr>
										<tr>
											<td align="right">销售员:</td>
											<td><input id="employeeName" name="saleAmount.employee.name" value="${saleAmount.employee.name}" type="text" size="30" /> <input type="hidden" id="employeeId" name="employeeId" value="${saleAmount.employee.id}" /> <span><a class="abtn" href="#" onclick="chooseEmployee();"><span>选择</span></a></span></td>
											<td align="right">年度:</td>
											<td><input id="year" name="saleAmount.year" value="${saleAmount.year}" type="text" onClick="WdatePicker({dateFmt:'yyyy'})" size="30" class="validate[required] text-input Wdate" /><span style="color: red;">*</span></td>
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
							<li class="current"><img src="${vix}/common/img/mail.png" />定额明细</li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="saleAmountItem" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#saleAmountItemTb',url: '${vix}/sales/commission/saleAmountAction!getSaleAmountItemJson.action?id=${saleAmount.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'month',width:100,align:'center'">月份</th>
										<th data-options="field:'quarter',width:100,align:'center'">季度</th>
										<th data-options="field:'saleAmountQuota',width:120,align:'center'">期间金额</th>
										<th data-options="field:'saleAmountQuotaTotal',width:120,align:'center'">到当前期间的累计金额</th>
										<th data-options="field:'saleCountQuota',width:120,align:'center'">期间数量</th>
										<th data-options="field:'saleCountQuotaTotal',width:120,align:'center'">到当前期间的累计数量</th>
									</tr>
								</thead>
							</table>
							<div id="saleAmountItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addSaleAmountItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="updateSaleAmountItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true"
									onclick="removeSaleAmountItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#saleAmountItem').datagrid();
							function removeSaleAmountItem(){
								var row = $('#saleAmountItem').datagrid('getSelected');
								if(row){
									asyncbox.confirm('是否删除该销售定额明细?','提示信息',function(action){
										if(action == 'ok'){
											var index = $('#saleAmountItem').datagrid('getRowIndex',row);
											$('#saleAmountItem').datagrid('deleteRow', index);
											$.ajax({
												  url:'${vix}/sales/commission/saleAmountItemAction!deleteById.action?id='+row.id,
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
							
							function updateSaleAmountItem(){
								var row = $('#saleAmountItem').datagrid('getSelected');
								if(row){
									addSaleAmountItem(row.id);
								}else{
									showMessage("请选择一条数据!");
								}
							}
							function addSaleAmountItem(id){
								$.ajax({
									  url:'${vix}/sales/commission/saleAmountItemAction!goSaveOrUpdate.action?id='+id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 660,
												height : 240,
												title:"添加销售定额明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#saleAmountItemForm').validationEngine('validate')){
															$.post('${vix}/sales/commission/saleAmountItemAction!saveOrUpdate.action',
																	{'saleAmountItem.id':$("#saId").val(),
																	  'saleAmountItem.saleAmount.id':$("#id").val(),
																	  'saleAmountItem.month':$("#month").val(),
																	  'saleAmountItem.saleAmountQuota':$("#saleAmountQuota").val(),
																	   'saleAmountItem.saleCountQuota':$("#saleCountQuota").val()
																	},
																	function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#saleAmountItem').datagrid("reload");
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
	</form>
</div>
<!-- content -->
