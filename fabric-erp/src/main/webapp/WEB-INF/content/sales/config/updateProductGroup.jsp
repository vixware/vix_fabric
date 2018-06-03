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
 
$("#productGroupForm").validationEngine();
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
function saveOrUpdateProductGroup(){
	if($('#productGroupForm').validationEngine('validate')){
		$.post('${vix}/sales/config/productGroupAction!saveOrUpdate.action',
			{'productGroup.id':$("#id").val(),
			  'productGroup.name':$("#name").val(),
			  'productGroup.code':$("#code").val(),
			  'productGroup.enable':$(":radio[name=enable][checked]").val(),
			  'productGroup.salesOrg.id':$("#saleOrgId").val(),
			  'productGroup.memo':$("#memo").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/config/productGroupAction!goList.action');
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
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
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
				<li><a href="#"><img src="${vix}/common/img/crm/site.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">供应链</a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#">配置</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/config/productGroupAction!goList.action');">产品组</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${productGroup.id}" />
<div class="content">
	<form id="productGroupForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateProductGroup();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/config/productGroupAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="productGroup.id > 0">
							${productGroup.name}
						</s:if> <s:else>
							新增产品组
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">编码:</td>
								<td width="35%"><input id="code" name="productGroup.code" value="${productGroup.code}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td width="10%" align="right">名称:</td>
								<td width="40%"><input id="name" name="productGroup.name" value="${productGroup.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">状态:</td>
								<td><s:if test="productGroup.enable == 1">
										<input type="radio" id="enable1" name="enable" value="1" checked="checked" />启用
									<input type="radio" id="enable1" name="enable" value="0" />禁用
								</s:if> <s:elseif test="productGroup.enable == 0">
										<input type="radio" id="enable1" name="enable" value="1" />启用
									<input type="radio" id="enable1" name="enable" value="0" checked="checked" />禁用
								</s:elseif> <s:else>
										<input type="radio" id="enable1" name="enable" value="1" checked="checked" />启用
									<input type="radio" id="enable1" name="enable" value="0" />禁用
								</s:else></td>
								<td align="right">销售组织:</td>
								<td><input type="hidden" id="saleOrgId" value="${productGroup.salesOrg.id}" /> <input id="saleOrgName" name="salesOrder.saleOrg.fullName" value="${productGroup.salesOrg.fullName}" type="text" size="30" /> <a class="abtn" href="#" onclick="chooseSaleOrg();"><span>选择</span></a></td>
							</tr>
							<tr>
								<td align="right">备注:</td>
								<td colspan="3"><input id="memo" name="productGroup.memo" value="${productGroup.memo}" type="text" size="30" /></td>
							</tr>
						</table>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(3,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />${vv:varView('vix_mdm_item')}</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(3,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />人员</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(3,3,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />销售区域</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="item" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#itemTb',url: '${vix}/sales/config/productGroupAction!getItemJson.action?id=${productGroup.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'code',width:80,align:'center'">编码</th>
										<th data-options="field:'name',width:120,align:'center'">名称</th>
										<th data-options="field:'specification',width:100,align:'center'">规格型号</th>
										<th data-options="field:'price',width:60,align:'center'">金额</th>
										<th data-options="field:'memo',width:120,align:'center'">备注</th>
									</tr>
								</thead>
							</table>
							<div id="itemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
						$('#item').datagrid();
						function removeItem(){
							var row = $('#item').datagrid('getSelected');
							if(row){
								asyncbox.confirm('是否删除该${vv:varView('vix_mdm_item')}?','提示信息',function(action){
									if(action == 'ok'){
										var index = $('#item').datagrid('getRowIndex',row);
										$('#item').datagrid('deleteRow', index);
										$.ajax({
											  url:'${vix}/sales/config/productGroupAction!deleteItemById.action?id=' + $("#id").val() + "&itemId=" + row.id,
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
						function addItem(id){
							$.ajax({
								  url:'${vix}/mdm/item/itemAction!goChooseItem.action',
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										 	modal:true,
											width : 960,
											height : 580,
											title:"选择${vv:varView('vix_mdm_item')}",
											html:html,
											callback : function(action,returnValue){
												if(action == 'ok'){
													if(returnValue != ''){
														var i = returnValue.split(",");
														$.ajax({
															  url:'${vix}/sales/config/productGroupAction!addItemById.action?id=' + $("#id").val() + "&itemId=" + i[0],
															  cache: false,
															  success: function(html){
																  showMessage(html);
																  $('#item').datagrid("reload");
															  }
														});
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
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="employee" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#employeeTb',url: '${vix}/sales/config/productGroupAction!getEmployeeJson.action?id=${productGroup.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',width:60">编号</th>
										<th data-options="field:'code',width:80,align:'center',editor:'text'">编码</th>
										<th data-options="field:'organizationUnit.fullName',width:100,align:'center',editor:'text'">部门</th>
										<th data-options="field:'name',width:90,align:'center',editor:'text'">姓名</th>
										<th data-options="field:'subordinatePosition',width:120,align:'center',editor:'text'">职务</th>
										<th data-options="field:'email',width:140,align:'center',editor:'text'">邮箱</th>
									</tr>
								</thead>
							</table>

							<div id="employeeTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addEmployee(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeEmployee()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#employee').datagrid();
							function removeEmployee(){
								var row = $('#employee').datagrid('getSelected');
								if(row){
									asyncbox.confirm('是否删除该人员?','提示信息',function(action){
										if(action == 'ok'){
											var index = $('#employee').datagrid('getRowIndex',row);
											$('#employee').datagrid('deleteRow', index);
											$.ajax({
												  url:'${vix}/sales/config/productGroupAction!deleteEmployeeById.action?id=' + $("#id").val() + "&empId=" + row.id,
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
							function addEmployee(id){
								$.ajax({
									  url:'${vix}/common/select/commonSelectEmpAction!goList.action?chkStyle=radio',
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 960,
												height : 500,
												title:"选择职员",
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														if(returnValue != undefined){
															var result = returnValue.split(":");
															$.ajax({
																  url:'${vix}/sales/config/productGroupAction!addEmployeeById.action?id=' + $("#id").val() + "&empIds=" + result[0],
																  cache: false,
																  success: function(html){
																	  showMessage(html);
																	  $('#employee').datagrid("reload");
																  }
															});
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
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="regional" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#regionalTb',url: '${vix}/sales/config/productGroupAction!getRegionalJson.action?id=${productGroup.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',width:60">编号</th>
										<th data-options="field:'code',width:80,align:'center',editor:'text'">编码</th>
										<th data-options="field:'name',width:100,align:'center',editor:'text'">名称</th>
										<th data-options="field:'memo',width:220,align:'center',editor:'text'">备注</th>
									</tr>
								</thead>
							</table>
							<div id="regionalTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addRegional(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeRegional()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#regional').datagrid();
							function removeRegional(){
								var row = $('#regional').datagrid('getSelected');
								if(row){
									asyncbox.confirm('是否删除该销售区域?','提示信息',function(action){
										if(action == 'ok'){
											var index = $('#regional').datagrid('getRowIndex',row);
											$('#regional').datagrid('deleteRow', index);
											$.ajax({
												  url:'${vix}/sales/config/productGroupAction!deleteRegionalById.action?id=' + $("#id").val() + "&regionalId=" + row.id,
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
							function addRegional(id){
								$.ajax({
									  url:'${vix}/common/regionalAction!goChooseRegional.action',
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 960,
												height : 500,
												title:"选择销售区域",
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														if(returnValue != undefined){
															var result = returnValue.split(":");
															$.ajax({
																url:'${vix}/sales/config/productGroupAction!addRegionalById.action?id=' + $("#id").val() + "&regionalIds=" + result[0],
																cache: false,
																success: function(html){
																	showMessage(html);
																	$('#regional').datagrid("reload");
																}
															});
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
