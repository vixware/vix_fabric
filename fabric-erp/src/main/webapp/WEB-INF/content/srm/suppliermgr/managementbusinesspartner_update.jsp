<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<script type="text/javascript">
	/**页面加载时执行*/
	$(function() {
		//设置类型选中
		$("#type").val('${supplier.type}');
		//设置分类选中
		$("#catalogs").val('${supplier.catalogs}');
		//设置所属行业选中
		$("#industry").val('${supplier.industry}');
		//设置所属地区选中
		$("#region").val('${supplier.region}');
		//设置币种选中
		$("#currency").val('${supplier.currency}');
		//设置等级选中
		$("#classIfy").val('${supplier.classIfy}');
		//设置ABC等级选中
		$("#abcClassIfy").val('${supplier.abcClassIfy}');
		//设置状态选中

		$("#supplierForm").validationEngine();
		loadMenuContent('${vix}/srm/supplierMainAction!goMenuContent.action');

		_pad_addInputCheckNumEvent('employeeCounts');
		_pad_addInputCheckNumEvent('exceedAmount');
		_pad_addInputCheckNumEvent('exceedPercent');

		var parentId = $('#selectId').val();
		var parentName = $('#selectName').val();
		if (parentId && parentId > 0 && parentName && parentName != '') {
			$('#parentId').val(parentId);
			$('#catalog').val(parentName);
		}
	});

	/** 保存供应商 */
	function saveOrUpdateOrder() {
		var orderItemStr = "";
		if ($('#supplierForm').validationEngine('validate')) {
			$.post('${vix}/srm/${usedAction}!saveOrUpdate.action?parentId=' + $("#parentId").val(), {
			'supplier.id' : $("#id").val(),
			'supplier.code' : $("#code").val(),
			'supplier.name' : $("#name").val(),
			'supplier.type' : $("#type").val(),
			'supplier.contacts' : $("#contacts").val(),
			'supplier.telephone' : $("#telephone").val(),
			'supplier.artificialPerson' : $("#artificialPerson").val(),
			'supplier.pinYin' : $("#pinYin").val(),
			'supplier.catalog' : $("#catalog").val(),
			'supplier.catalogs' : $("#catalogs").val(),
			'supplier.industry' : $("#industry").val(),
			'supplier.employeeCounts' : $("#employeeCounts").val(),
			'supplier.parentCompanyCode' : $("#parentCompanyCode").val(),
			'supplier.parentCompanyName' : $("#parentCompanyName").val(),
			'supplier.region' : $("#region").val(),
			'supplier.currency' : $("#currency").val(),
			'supplier.cellphone' : $("#cellphone").val(),
			'supplier.registeredCapital' : $("#registeredCapital").val(),
			'supplier.portraiture' : $("#portraiture").val(),
			'supplier.classIfy' : $("#classIfy").val(),
			'supplier.abcClassIfy' : $("#abcClassIfy").val(),
			'supplier.catalog' : $("#catalog").val(),
			'supplier.status' : $("#status").val(),
			'orderItemStr' : orderItemStr,

			'supplier.purchaserCode' : $("#purchaserCode").val(),
			'supplier.purchaser' : $("#purchaser").val(),
			'supplier.costCenterCode' : $("#costCenterCode").val(),
			'supplier.costCenter' : $("#costCenter").val(),
			'supplier.outSourcingWarehouse' : $("#outSourcingWarehouse").val(),
			'supplier.outSourcingPositon' : $("#outSourcingPositon").val(),
			'supplier.isOutSourcing' : $("#isOutSourcing").val(),
			'supplier.isServiceProvider' : $("#isServiceProvider").val(),
			'supplier.isForeignSupplier' : $("#isForeignSupplier").val(),
			'supplier.isDirectltProvideProduct' : $("#isDirectltProvideProduct").val(),
			'supplier.isTotalBatchRecieve' : $("#isTotalBatchRecieve").val(),
			'supplier.isNeedUnitPrice' : $("#isNeedUnitPrice").val(),
			'supplier.isNeedIQC' : $("#isNeedIQC").val(),
			'supplier.exceedDealType' : $("#exceedDealType").val(),
			'supplier.exceedPercent' : $("#exceedPercent").val(),
			'supplier.exceedAmount' : $("#exceedAmount").val(),
			'supplier.isPayByExceed' : $("#isPayByExceed").val()
			}, function(result) {
				if (result != null) {
					//_tab_close_current_and_reload_opener_grid();
					var subEntityDiv = $('#supplier_sub_entity');
					if (subEntityDiv.is(':hidden')) {
						//新建
						$('#id').val(result);
						subEntityDiv.show();
						showMessage('信息保存成功，可以继续添加相关信息');
					} else {
						//update
						showMessage('信息保存成功');
						_pad_page_view_back();
					}
				} else {
					showErrorMessage('信息保存失败');
				}
			});
		}
	}

	//选择分类
	function chooseSupplierCategory() {
		$.ajax({
		url : '${vix}/srm/${usedAction}!goChooseSupplierCategory.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择所属分类",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#parentId").val(result[0]);
						$("#catalog").val(result[1]);
					} else {
						$("#parentId").val("");
						$("#catalog").val("");
						asyncbox.success("请选择所属分类信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
</script>

<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module"></div>
	</h2>
</div>

<div class="content" style="margin-top: 8px;">
	<form id="supplierForm">
		<input type="hidden" id="id" name="id" value="${supplier.id}" />
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a class="f_btn edit_save" href="javascript:saveOrUpdateOrder();"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a class="f_btn edit_savenew" href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a class="f_btn edit_cancel" href="#"><img
							width="22" height="22" alt="取消" src="${vix}/common/img/dt_cancelback.png" /></a> <a class="f_btn edit_disable" href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a class="f_btn edit_delete" href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png" /></a> <a
						class="f_btn edit_aprlpass" href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png" /></a> <a class="f_btn edit_aprlreject" href="#"><img width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a> <a class="f_btn edit_prv" href="#"><img width="22" height="22" alt="上一条"
							src="${vix}/common/img/dt_before.png"></a> <a class="f_btn edit_next" href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"></a> <a class="f_btn edit_print" href="#"><img width="22" height="22" alt="打印" src="${vix}/common/img/dt_print.png"></a>
						<div class="f_btn edit_relat ms" style="float: none;">
							<a href="#"><img width="22" height="22" alt="报表" src="${vix}/common/img/dt_report.png"></a>
							<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
							</ul>
						</div> <a class="f_btn edit_export" href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a> <a class="f_btn edit_back" href="javascript:_pad_page_view_back();"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>供应商信息</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">编码：</td>
											<td><input id="code" name="code" value="${supplier.code }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">名称：</td>
											<td><input id="name" name="name" value="${supplier.name }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">法人：</td>
											<td><input id="artificialPerson" name="artificialPerson" value="${supplier.artificialPerson }" type="text" size="30" /></td>
											<td align="right">类型：</td>
											<td><select id="type" name="type" style="width: 50" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="GENERAL">普通供应商</option>
													<option value="AGREEMENT">协议供应商</option>
													<option value="OUTSOURCING">委外供应商</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">拼音：</td>
											<td><input id="pinYin" name="pinYin" value="${supplier.pinYin }" type="text" size="30" /></td>
											<td align="right">所属分类：</td>
											<td><input type="hidden" id="parentId" name="parentId" value="${supplier.supplierCategory.id }" /> <input id="catalog" name="catalog" value="${supplier.catalog }" type="text" size="30" readonly="readonly" /> <input class="btn" type="button" value="选择" onclick="chooseSupplierCategory();" /></td>
										</tr>
										<tr>
											<td align="right">分类：</td>
											<td><select id="catalogs" name="catalogs" style="width: 50">
													<option value="">请选择</option>
													<option value="1">分类1</option>
													<option value="2">分类2</option>
											</select></td>
											<td align="right">所属行业：</td>
											<td><select id="industry" name="industry" style="width: 50">
													<option value="">请选择</option>
													<option value="1">行业1</option>
													<option value="2">行业2</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">员工人数：</td>
											<td><input id="employeeCounts" name="employeeCounts" value="${supplier.employeeCounts }" type="text" size="30" /></td>
											<td align="right">母公司代码：</td>
											<td><input id="parentCompanyCode" name="parentCompanyCode" value="${supplier.parentCompanyCode }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">母公司：</td>
											<td><input id="parentCompanyName" name="parentCompanyName" value="${supplier.parentCompanyName }" type="text" size="30" /></td>
											<td align="right">所属地区：</td>
											<td><select id="region" name="region" style="width: 50">
													<option value="">请选择</option>
													<option value="1">地区1</option>
													<option value="2">地区2</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">币种：</td>
											<td><select id="currency" name="currency" style="width: 50">
													<option value="">请选择</option>
													<option value="1">人民币</option>
													<option value="2">美元</option>
											</select></td>
											<td align="right">注册资金：</td>
											<td><input id="registeredCapital" name="registeredCapital" value="${supplier.registeredCapital }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">联系人：</td>
											<td><input id="contacts" name="contacts" value="${supplier.contacts }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">手机：</td>
											<td><input id="cellphone" name="cellphone" value="${supplier.cellphone }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">电话：</td>
											<td><input id="telephone" name="telephone" value="${supplier.telephone }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">传真：</td>
											<td><input id="portraiture" name="portraiture" value="${supplier.portraiture }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">等级：</td>
											<td><select id="classIfy" name="classIfy" style="width: 50">
													<option value="">请选择</option>
													<option value="1">等级1</option>
													<option value="2">等级2</option>
											</select></td>
											<td align="right">ABC等级：</td>
											<td><select id="abcClassIfy" name="abcClassIfy" style="width: 50">
													<option value="">请选择</option>
													<option value="1">等级A</option>
													<option value="2">等级B</option>
													<option value="3">等级C</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">状态：</td>
											<td><select id="status" name="status" style="width: 50" class="validate[required,custom[integer]] text-input">
													<option value="">请选择</option>
													<option value="1">寻源</option>
													<option value="2">待评估</option>
													<option value="3">正式</option>
													<option value="4">无效</option>
											</select><span style="color: red;">*</span></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>其他信息</strong>
								</dt>

								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">负责采购员编码：</td>
											<td><input id="purchaserCode" value="${supplier.purchaserCode}" name="BO_SPR_CMNM_purchaserCode" type="text" size="30" /></td>
											<td align="right">采购员：</td>
											<td><input id="purchaser" value="${supplier.purchaser}" name="BO_SPR_CMNM_purchaser" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">成本中心编码：</td>
											<td><input id="costCenterCode" value="${supplier.costCenterCode}" name="BO_SPR_CMNM_costCenterCode" type="text" size="30" /></td>
											<td align="right">成本中心：</td>
											<td><input id="costCenter" value="${supplier.costCenter}" name="BO_SPR_CMNM_costCenter" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">委外仓库：</td>
											<td><input id="outSourcingWarehouse" value="${supplier.outSourcingWarehouse}" name="BO_SPR_CMNM_outSourcingWarehouse" type="text" size="30" /></td>
											<td align="right">委外货位：</td>
											<td><input id="outSourcingPositon" value="${supplier.outSourcingPositon}" name="BO_SPR_CMNM_outSourcingPositon" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否为委外供应：</td>
											<td><select id="isOutSourcing" name="BO_SPR_CMNM_isOutSourcing" style="width: 50">
													<option value="0" <s:if test="supplier.isOutSourcing==0">selected</s:if>>否</option>
													<option value="1" <s:if test="supplier.isOutSourcing==1">selected</s:if>>是</option>
											</select></td>
											<td align="right">是否为服务提供商：</td>
											<td><select id="isServiceProvider" name="BO_SPR_CMNM_isServiceProvider" style="width: 50">
													<option value="0" <s:if test="supplier.isServiceProvider==0">selected</s:if>>否</option>
													<option value="1" <s:if test="supplier.isServiceProvider==1">selected</s:if>>是</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">是否为国外供应商：</td>
											<td><select id="isForeignSupplier" name="BO_SPR_CMNM_isForeignSupplier" style="width: 50">
													<option value="0" <s:if test="supplier.isForeignSupplier==0">selected</s:if>>否</option>
													<option value="1" <s:if test="supplier.isForeignSupplier==1">selected</s:if>>是</option>
											</select></td>
											<td align="right">是否直接提供产品：</td>
											<td><select id="isDirectltProvideProduct" style="width: 50">
													<option value="0" <s:if test="supplier.isDirectltProvideProduct==0">selected</s:if>>否</option>
													<option value="1" <s:if test="supplier.isDirectltProvideProduct==1">selected</s:if>>是</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">是否齐批接收：</td>
											<td><select id="isTotalBatchRecieve" style="width: 50">
													<option value="0" <s:if test="supplier.isTotalBatchRecieve==0">selected</s:if>>否</option>
													<option value="1" <s:if test="supplier.isTotalBatchRecieve==1">selected</s:if>>是</option>
											</select></td>
											<td align="right">是否单价接收：</td>
											<td><select id="isNeedUnitPrice" style="width: 50">
													<option value="0" <s:if test="supplier.isNeedUnitPrice==0">selected</s:if>>否</option>
													<option value="1" <s:if test="supplier.isNeedUnitPrice==1">selected</s:if>>是</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">是否进行IQC检验：</td>
											<td><select id="isNeedIQC" style="width: 50">
													<option value="0" <s:if test="supplier.isNeedIQC==0">selected</s:if>>否</option>
													<option value="1" <s:if test="supplier.isNeedIQC==1">selected</s:if>>是</option>
											</select></td>
											<td align="right">超交处理方式：</td>
											<td><select id="exceedDealType" style="width: 50">
													<option value="0" <s:if test="supplier.exceedDealType==0">selected</s:if>>否</option>
													<option value="1" <s:if test="supplier.exceedDealType==1">selected</s:if>>是</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">超交百分比：</td>
											<td><input id="exceedPercent" value="${supplier.exceedPercent}" name="BO_SPR_CMNM_exceedPercent" type="text" size="30" /></td>
											<td align="right">超交数量：</td>
											<td><input id="exceedAmount" value="${supplier.exceedAmount}" name="BO_SPR_CMNM_exceedAmount" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">超交是否付款：</td>
											<td><select id="isPayByExceed" style="width: 50">
													<option value="0" <s:if test="supplier.isPayByExceed==0">selected</s:if>>否</option>
													<option value="1" <s:if test="supplier.isPayByExceed==1">selected</s:if>>是</option>
											</select></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div id="supplier_sub_entity" class="clearfix" style="background:#FFF;position:relative;<s:if test="id==null || id==0">display:none;</s:if>">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(7,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />资质</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(7,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />通信地址</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(7,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />银行</a></li>
							<li><a onclick="javascript:$('#a4').attr('style','');tab(7,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />财务</a></li>
							<li><a onclick="javascript:$('#a5').attr('style','');tab(7,5,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />信用</a></li>
							<li><a onclick="javascript:$('#a6').attr('style','');tab(7,6,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />指标</a></li>
							<li><a onclick="javascript:$('#a7').attr('style','');tab(7,7,'a',event)"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>

					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlAptitudeInfo" class="easyui-datagrid" style="height: 350px; margin: 6px;" data-options="singleSelect: true,toolbar: '#dlAptitudeInfoTb',url: '${vix}/srm/${usedAction}!getSupplierAptitudeInfoJson.action'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60,formatter:showOpt1">操作</th>
										<th data-options="field:'name',width:200,align:'center',editor:'text'">资质名称</th>
										<th data-options="field:'description',width:300,align:'center',editor:'text'">资质描述</th>
										<th data-options="field:'files',width:200,align:'center',editor:'text'">资质文件</th>
										<th data-options="field:'validPeriod',width:120,align:'center',editor:'text'">资质有效期</th>
									</tr>
								</thead>
							</table>
							<div id="dlAptitudeInfoTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="editItem1(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid1() {
									$('#dlAptitudeInfo').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
								}
								reloadDataGrid1();

								function editItem1(id) {
									$.ajax({
									url : '${vix}/srm/${usedAction}!supplierAptitudeInfoEdit.action?supplierId=' + $('#id').val() + '&id=' + id,
									cache : false,
									success : function(html) {
										asyncbox.open({
										id : 'popModalWin',
										modal : true,
										width : 750,
										height : 210,
										title : "资质信息",
										html : html,
										callback : function(action) {
											if (action == 'close' || action == 'cancel') {
												return true;
											} else if (action == 'submit') {
												submitAptitudeInfoForm();
											}
											return false;
										},
										btnsbar : [ {
										text : '提交',
										action : 'submit'
										}, {
										text : '关闭',
										action : 'cancel'
										} ]
										});
									}
									});
								}
								function deleteItem1(id) {
									asyncbox.confirm('确定要删除?', '提示信息', function(action) {
										if (action == 'ok') {
											$.ajax({
											url : "${vix}/srm/${usedAction}!supplierAptitudeInfoDelete.action",
											data : "id=" + id,
											dataType : "text",
											success : function(data) {
												if (data == 'success') {
													showMessage('操作完毕');
													$("#dlAptitudeInfo").datagrid('reload');
												} else {
													showErrorMessage('操作失败');
												}
											}
											});
										}
									});
								}
								function showOpt1(value, row, index) {
									var edit = "<a href='javascript:editItem1(" + row.id + ")'>编辑</a>&nbsp;";
									var del = "<a href='javascript:deleteItem1(" + row.id + ")'>删除</a>&nbsp;";
									return edit + del;
								}
							</script>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlAddress" class="easyui-datagrid" style="height: 350px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlAddressTb',url: '${vix}/srm/${usedAction}!getAddressJson.action'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60,formatter:showOpt2">操作</th>
										<th data-options="field:'country',width:200,align:'center',editor:'text'">国家</th>
										<th data-options="field:'province',width:200,align:'center',editor:'text'">省</th>
										<th data-options="field:'city',width:200,align:'center',editor:'text'">市</th>
										<th data-options="field:'street',width:200,align:'center',editor:'text'">街道</th>
										<th data-options="field:'telephone',width:200,align:'center',editor:'text'">电话</th>
										<th data-options="field:'postMail',width:200,align:'center',editor:'text'">邮政信箱</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="editItem2(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid2() {
									$('#dlAddress').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
								}
								reloadDataGrid2();

								function editItem2(id) {
									$.ajax({
									url : '${vix}/srm/${usedAction}!supplierAddressEdit.action?supplierId=' + $('#id').val() + '&id=' + id,
									cache : false,
									success : function(html) {
										asyncbox.open({
										id : 'popModalWin',
										modal : true,
										width : 750,
										height : 270,
										title : "通信地址",
										html : html,
										callback : function(action) {
											if (action == 'close' || action == 'cancel') {
												return true;
											} else if (action == 'submit') {
												submitAddressForm();
											}
											return false;
										},
										btnsbar : [ {
										text : '提交',
										action : 'submit'
										}, {
										text : '关闭',
										action : 'cancel'
										} ]
										});
									}
									});
								}
								function deleteItem2(id) {
									asyncbox.confirm('确定要删除?', '提示信息', function(action) {
										if (action == 'ok') {
											$.ajax({
											url : "${vix}/srm/${usedAction}!supplierAddressDelete.action",
											data : "id=" + id,
											dataType : "text",
											success : function(data) {
												if (data == 'success') {
													showMessage('操作完毕');
													$("#dlAddress").datagrid('reload');
												} else {
													showErrorMessage('操作失败');
												}
											}
											});
										}
									});
								}
								function showOpt2(value, row, index) {
									var edit = "<a href='javascript:editItem2(" + row.id + ")'>编辑</a>&nbsp;";
									var del = "<a href='javascript:deleteItem2(" + row.id + ")'>删除</a>&nbsp;";
									return edit + del;
								}
							</script>
						</div>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlBankInfo" class="easyui-datagrid" style="height: 350px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlBankInfoTb',url: '${vix}/srm/${usedAction}!getBankInfoJson.action'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60,formatter:showOpt3">操作</th>
										<th data-options="field:'bankName',width:200,align:'center',editor:'text'">开户银行</th>
										<th data-options="field:'bankAccount',width:200,align:'center',editor:'text'">银行帐号</th>
										<th data-options="field:'bankCode',width:200,align:'center',editor:'text'">银行代码</th>
									</tr>
								</thead>
							</table>
							<div id="dlBankInfoTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="editItem3(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid3() {
									$('#dlBankInfo').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
								}
								reloadDataGrid3();

								function editItem3(id) {
									$.ajax({
									url : '${vix}/srm/${usedAction}!supplierBankInfoEdit.action?supplierId=' + $('#id').val() + '&id=' + id,
									cache : false,
									success : function(html) {
										asyncbox.open({
										id : 'popModalWin',
										modal : true,
										width : 750,
										height : 210,
										title : "银行信息",
										html : html,
										callback : function(action) {
											if (action == 'close' || action == 'cancel') {
												return true;
											} else if (action == 'submit') {
												submitBankInfoForm();
											}
											return false;
										},
										btnsbar : [ {
										text : '提交',
										action : 'submit'
										}, {
										text : '关闭',
										action : 'cancel'
										} ]
										});
									}
									});
								}
								function deleteItem3(id) {
									asyncbox.confirm('确定要删除?', '提示信息', function(action) {
										if (action == 'ok') {
											$.ajax({
											url : "${vix}/srm/${usedAction}!supplierBankInfoDelete.action",
											data : "id=" + id,
											dataType : "text",
											success : function(data) {
												if (data == 'success') {
													showMessage('操作完毕');
													$("#dlBankInfo").datagrid('reload');
												} else {
													showErrorMessage('操作失败');
												}
											}
											});
										}
									});
								}
								function showOpt3(value, row, index) {
									var edit = "<a href='javascript:editItem3(" + row.id + ")'>编辑</a>&nbsp;";
									var del = "<a href='javascript:deleteItem3(" + row.id + ")'>删除</a>&nbsp;";
									return edit + del;
								}
							</script>
						</div>
					</div>
					<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlAccountingInfo" class="easyui-datagrid" style="height: 350px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlAccountingInfoTb',url: '${vix}/srm/${usedAction}!getAccountingInfoJson.action'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60,formatter:showOpt4">操作</th>
										<th data-options="field:'generalAccountCatalog',width:200,align:'center',editor:'text'">总帐分类</th>
										<th data-options="field:'payCondition',width:200,align:'center',editor:'text'">付款条件</th>
										<th data-options="field:'payType',width:200,align:'center',editor:'text'">付款类型</th>
										<th data-options="field:'payStyle',width:200,align:'center',editor:'text'">付款方式</th>
										<th data-options="field:'payTarget',width:200,align:'center',editor:'text'">付款对象</th>
										<th data-options="field:'limited',width:200,align:'center',editor:'numberbox'">付款期限</th>
										<th data-options="field:'invoiceHeader',width:200,align:'center',editor:'text'">发票抬头</th>
										<th data-options="field:'vaBank',width:200,align:'center',editor:'text'">增值税开户银行</th>
										<th data-options="field:'checked',width:200,align:'center',editor:'text'">审核人</th>
									</tr>
								</thead>
							</table>
							<div id="dlAccountingInfoTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="editItem4(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid4() {
									$('#dlAccountingInfo').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
								}
								reloadDataGrid4();

								function editItem4(id) {
									$.ajax({
									url : '${vix}/srm/${usedAction}!supplierAccountingInfoEdit.action?supplierId=' + $('#id').val() + '&id=' + id,
									cache : false,
									success : function(html) {
										asyncbox.open({
										id : 'popModalWin',
										modal : true,
										width : 750,
										height : 330,
										title : "银行信息",
										html : html,
										callback : function(action) {
											if (action == 'close' || action == 'cancel') {
												return true;
											} else if (action == 'submit') {
												submitAccountingInfoForm();
											}
											return false;
										},
										btnsbar : [ {
										text : '提交',
										action : 'submit'
										}, {
										text : '关闭',
										action : 'cancel'
										} ]
										});
									}
									});
								}
								function deleteItem4(id) {
									asyncbox.confirm('确定要删除?', '提示信息', function(action) {
										if (action == 'ok') {
											$.ajax({
											url : "${vix}/srm/${usedAction}!supplierAccountingInfoDelete.action",
											data : "id=" + id,
											dataType : "text",
											success : function(data) {
												if (data == 'success') {
													showMessage('操作完毕');
													$("#dlAccountingInfo").datagrid('reload');
												} else {
													showErrorMessage('操作失败');
												}
											}
											});
										}
									});
								}
								function showOpt4(value, row, index) {
									var edit = "<a href='javascript:editItem4(" + row.id + ")'>编辑</a>&nbsp;";
									var del = "<a href='javascript:deleteItem4(" + row.id + ")'>删除</a>&nbsp;";
									return edit + del;
								}
							</script>
						</div>
					</div>
					<div id="a5" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlCreditInfo" class="easyui-datagrid" style="height: 350px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlCreditInfoTb',url: '${vix}/srm/${usedAction}!getCreditInfoJson.action'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60,formatter:showOpt5">操作</th>
										<th data-options="field:'supplierName',width:300,align:'center',editor:'text'">供应商名称</th>
										<th data-options="field:'creditLevel',width:100,align:'center',editor:'text'">信用等级</th>
										<th data-options="field:'creditAmount',width:100,align:'center',editor:'numberbox'">信用额度</th>
										<th data-options="field:'discount',width:100,align:'center',editor:'numberbox'">折扣率</th>
										<th data-options="field:'payCondition',width:280,align:'center',editor:'text'">付款条件</th>
										<th data-options="field:'lastDealAmount',width:100,align:'center',editor:'numberbox'">最后交易金额</th>
										<th data-options="field:'lastDealTime',width:100,align:'center',editor:'datebox'">最后交易日期</th>
									</tr>
								</thead>
							</table>
							<div id="dlCreditInfoTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="editItem5(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid5() {
									$('#dlCreditInfo').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
								}
								reloadDataGrid5();

								function editItem5(id) {
									$.ajax({
									url : '${vix}/srm/${usedAction}!supplierCreditInfoEdit.action?supplierId=' + $('#id').val() + '&id=' + id,
									cache : false,
									success : function(html) {
										asyncbox.open({
										id : 'popModalWin',
										modal : true,
										width : 750,
										height : 230,
										title : "信用信息",
										html : html,
										callback : function(action) {
											if (action == 'close' || action == 'cancel') {
												return true;
											} else if (action == 'submit') {
												submitCreditInfoForm();
											}
											return false;
										},
										btnsbar : [ {
										text : '提交',
										action : 'submit'
										}, {
										text : '关闭',
										action : 'cancel'
										} ]
										});
									}
									});
								}
								function deleteItem5(id) {
									asyncbox.confirm('确定要删除?', '提示信息', function(action) {
										if (action == 'ok') {
											$.ajax({
											url : "${vix}/srm/${usedAction}!supplierCreditInfoDelete.action",
											data : "id=" + id,
											dataType : "text",
											success : function(data) {
												if (data == 'success') {
													showMessage('操作完毕');
													$("#dlCreditInfo").datagrid('reload');
												} else {
													showErrorMessage('操作失败');
												}
											}
											});
										}
									});
								}
								function showOpt5(value, row, index) {
									var edit = "<a href='javascript:editItem5(" + row.id + ")'>编辑</a>&nbsp;";
									var del = "<a href='javascript:deleteItem5(" + row.id + ")'>删除</a>&nbsp;";
									return edit + del;
								}
							</script>
						</div>
					</div>
					<div id="a6" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlIndicators" class="easyui-datagrid" style="height: 350px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlIndicatorsTb',url: '${vix}/srm/${usedAction}!getIndicatorsJson.action'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60,formatter:showOpt6">操作</th>
										<th data-options="field:'itemName',width:200,align:'center',editor:'text'">指标名称</th>
										<th data-options="field:'weights',width:200,align:'center',editor:'text'">权重</th>
										<th data-options="field:'quota',width:200,align:'center',editor:'numberbox'">配额数量</th>
										<th data-options="field:'priceLimit',width:200,align:'center',editor:'numberbox'">金额限度</th>
									</tr>
								</thead>
							</table>
							<div id="dlIndicatorsTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="editItem6(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid6() {
									$('#dlIndicators').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
								}
								reloadDataGrid6();

								function editItem6(id) {
									$.ajax({
									url : '${vix}/srm/${usedAction}!supplierIndicatorsEdit.action?supplierId=' + $('#id').val() + '&id=' + id,
									cache : false,
									success : function(html) {
										asyncbox.open({
										id : 'popModalWin',
										modal : true,
										width : 750,
										height : 230,
										title : "指标信息",
										html : html,
										callback : function(action) {
											if (action == 'close' || action == 'cancel') {
												return true;
											} else if (action == 'submit') {
												submitsIndicatorsForm();
											}
											return false;
										},
										btnsbar : [ {
										text : '提交',
										action : 'submit'
										}, {
										text : '关闭',
										action : 'cancel'
										} ]
										});
									}
									});
								}
								function deleteItem6(id) {
									asyncbox.confirm('确定要删除?', '提示信息', function(action) {
										if (action == 'ok') {
											$.ajax({
											url : "${vix}/srm/${usedAction}!supplierIndicatorsDelete.action",
											data : "id=" + id,
											dataType : "text",
											success : function(data) {
												if (data == 'success') {
													showMessage('操作完毕');
													$("#dlIndicators").datagrid('reload');
												} else {
													showErrorMessage('操作失败');
												}
											}
											});
										}
									});
								}
								function showOpt6(value, row, index) {
									var edit = "<a href='javascript:editItem6(" + row.id + ")'>编辑</a>&nbsp;";
									var del = "<a href='javascript:deleteItem6(" + row.id + ")'>删除</a>&nbsp;";
									return edit + del;
								}
							</script>
						</div>
					</div>
					<div id="a7" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							$('#soAttach').datagrid({
							url : '${vix}/srm/${usedAction}!getAttachmentsJson.action',
							queryParams : {
								id : $('#id').val()
							},
							title : '附件',
							width : 900,
							height : '450',
							fitColumns : true,
							columns : [ [ {
							field : 'id',
							title : '操作',
							width : 80,
							formatter : showOpt7
							}, {
							field : 'name',
							title : '名称',
							width : 180
							}, ] ],
							toolbar : [ {
							id : 'saBtnadd',
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								$.ajax({
								url : '${vix}/srm/${usedAction}!addAttachments.action?supplierId=' + $('#id').val(),
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 364,
									height : 160,
									title : "上传附件",
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											submitsAttachForm();
										}
									},
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							}
							} ]
							});
							function reloadDataGrid7() {
								$('#soAttach').datagrid({
									queryParams : {
										id : $('#id').val()
									}
								});
							}
							reloadDataGrid7();
							function deleteItem7(id) {
								asyncbox.confirm('确定要删除?', '提示信息', function(action) {
									if (action == 'ok') {
										$.ajax({
										url : "${vix}/srm/${usedAction}!deleteAttachment.action",
										data : "id=" + id,
										dataType : "text",
										success : function(data) {
											if (data == 'success') {
												showMessage('操作完毕');
												$("#soAttach").datagrid('reload');
											} else {
												showErrorMessage('操作失败');
											}
										}
										});
									}
								});
							}
							function downloadItem7(id) {
								window.open('${vix}/srm/${usedAction}!downloadSupplierAttachment.action?id=' + id)
							}
							function showOpt7(value, row, index) {
								var edit = "<a href='javascript:downloadItem7(" + row.id + ")'>下载</a>&nbsp;";
								var del = "<a href='javascript:deleteItem7(" + row.id + ")'>删除</a>&nbsp;";
								return edit + del;
							}
						</script>
						<div style="padding: 8px;">
							<table id="soAttach"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>