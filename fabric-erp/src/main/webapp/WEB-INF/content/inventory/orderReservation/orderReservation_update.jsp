<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script type="text/javascript">
	function saveOrUpdateOrder (){
		var dlData = $ ("#dlAddress").datagrid ("getRows");
		var dlJson = JSON.stringify (dlData);
		if ($ ('#order').validationEngine ('validate')){
			$.post ('${vix}/inventory/orderReservationAction!saveOrUpdate.action' , {
			'wimTransvouch.id' : $ ("#id").val () ,
			'wimTransvouch.tvcode' : $ ("#tvcode").val () ,
			'wimTransvouch.outdepartmentCode' : $ ("#outdepartmentCode").val () ,
			'wimTransvouch.tvdate' : $ ("#tvdate").val () ,
			'wimTransvouch.indepartmentCode' : $ ("#indepartmentCode").val () ,
			'wimTransvouch.outwarehousecode' : $ ("#outwarehousecode").val () ,
			'wimTransvouch.inwarehousecode' : $ ("#inwarehousecode").val () ,
			'wimTransvouch.outstockcatalogcode' : $ ("#outstockcatalogcode").val () ,
			'wimTransvouch.instockcatalogcode' : $ ("#instockcatalogcode").val () ,
			'dlJson' : dlJson
			} , function (result){
				showMessage (result);
				setTimeout ("" , 1000);
				loadContent ('${vix}/inventory/orderReservationAction!goList.action?menuId=menuTransfer');
			});
		}
	}
	$ ("#order").validationEngine ();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_transfer.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">库存管理 </a></li>
				<li><a href="#">其他业务处理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/orderReservationAction!goList.action?pageNo=${pageNo}');">订单预留</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${wimTransvouch.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#"><img width="22" height="22" title="取消" src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#" onclick="loadContent('${vix}/inventory/orderReservationAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>订单信息</b> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>订单编码：</th>
											<td><input id="tvcode" name="wimTransvouch.tvcode" value="${wimTransvouch.tvcode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>主题：</th>
											<td><input id="tvcode" name="wimTransvouch.tvcode" value="${wimTransvouch.tvcode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>客户：</th>
											<td><input id="outdepartmentCode" name="wimTransvouch.outdepartmentCode" value="${wimTransvouch.outdepartmentCode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>客户名称：</th>
											<td><input id="indepartmentCode" name="wimTransvouch.indepartmentCode" value="${wimTransvouch.indepartmentCode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>订单类型：</th>
											<td><select id="outstockcatalogcode" name="wimTransvouch.outstockcatalogcode">
													<option value="">请选择</option>
													<option value="销售出库">销售订单</option>
													<option value="生产出库">生产订单</option>
													<option value="其他出库">其他订单</option>
											</select></td>
											<th>金额：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" /></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />订单明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlAddressTb',url: '${vix}/inventory/orderReservationAction!getWimTransvouchItemJson.action?id=${wimTransvouch.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'id',width:60,align:'center'">编号</th>
										<th data-options="field:'cinvcode',width:100,align:'center',editor:'text'">商品编码</th>
										<th data-options="field:'cinvcode',width:100,align:'center',editor:'text'">商品名称</th>
										<th data-options="field:'cinvcode',width:100,align:'center',editor:'text'">规格型号</th>
										<th data-options="field:'cinvcode',width:100,align:'center',editor:'text'">计量单位</th>
										<th data-options="field:'tvquantity',width:100,editor:'numberbox'">数量</th>
										<th data-options="field:'tvacost',width:100,align:'center',editor:'numberbox'">单价</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /> </span> </span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /> </span> </span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /> </span> </span> </a>
							</div>
							<script type="text/javascript">
								$ ('#dlAddress').datagrid ();
								var editIndexDlAddress = undefined;
								function endDlEditing (){
									if (editIndexDlAddress == undefined){
										return true;
									}
									if ($ ('#dlAddress').datagrid ('validateRow' , editIndexDlAddress)){
										$ ('#dlAddress').datagrid ('endEdit' , editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									}else{
										return false;
									}
								}
								function onDlClickRow (index){
									if (editIndexDlAddress != index){
										if (endDlEditing ()){
											$ ('#dlAddress').datagrid ('selectRow' , index).datagrid ('beginEdit' , index);
											editIndexDlAddress = index;
										}else{
											$ ('#dlAddress').datagrid ('selectRow' , editIndexDlAddress);
										}
									}
								}
								function appendDlAddress (){
									if (endDlEditing ()){
										$ ('#dlAddress').datagrid ('appendRow' , {
											status : 'P'
										});
										editIndexDlAddress = $ ('#dlAddress').datagrid ('getRows').length - 1;
										$ ('#dlAddress').datagrid ('selectRow' , editIndexDlAddress).datagrid ('beginEdit' , editIndexDlAddress);
									}
								}
								function removeDlAddress (){
									if (editIndexDlAddress == undefined){
										return;
									}
									$ ('#dlAddress').datagrid ('cancelEdit' , editIndexDlAddress).datagrid ('deleteRow' , editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress (){
									if (endDlEditing ()){
										$ ('#dlAddress').datagrid ('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>