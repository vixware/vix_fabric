<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateOrder() {
		//saveDlAddress();
		//var dlData = $("#dlAddress").datagrid("getRows");
		//var dlJson = JSON.stringify(dlData);
		if ($('#exportMD').validationEngine('validate')) {
			$.post('${vix}/inventory/takeStockAction!saveOrUpdate.action', {
			'stockTaking.id' : $("#id").val(),
			'stockTaking.code' : $("#code").val(),
			'stockTaking.name' : $("#name").val(),
			'stockTaking.invWarehouse.id' : $("#warehouseId").val(),
			'stockTaking.createTime' : $("#createTime").val(),
			'stockTaking.sttype' : $("#sttype").val(),
			'stockTaking.accountdate' : $("#accountdate").val(),
			'stockTaking.maker' : $("#maker").val(),
			'updateField' : updateField
			//'dlJson' : dlJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/takeStockAction!goList.action');
			});
		}
	}
	$("#exportMD").validationEngine();
	function initdate() {
		//加载创建时间(新增)
		if (document.getElementById("createTime").value == "") {
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds());
		}
		if (document.getElementById("sttype").value == "") {
			$("#sttype").val('${stockTaking.sttype}');
		}
	}

	function choosewarehouse() {
		$.ajax({
		url : '${vix}/inventory/warehouseAction!goChooseWarehouse.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择仓库",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#warehouseId").val(result[0]);
						$("#warehouseName").val(result[1]);
					} else {
						asyncbox.success("请选择仓库信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}

	function importXlsFile() {
		$.ajaxFileUpload({
		url : '${vix}/inventory/takeStockAction!importFile.action?id=' + $('#id').val(),//用于文件上传的服务器端请求地址
		secureuri : true,//是否安全提交,设置为true;设置为false，则出现乱码
		fileElementId : 'fileToUpload',//文件上传空间的id属性  <input type="file" id="file" name="file" />
		dataType : 'text',//返回值类型 ,可以使xml、text、json、html
		success : function(data, status) { //服务器成功响应处理函数
			var data = $.parseJSON(data);
			if (data.success == '1') {
				asyncbox.success(data.msg, "提示");
				searchForContent();
			} else {
				if (typeof (data.error) != 'undefined') {
					if (data.error != '') {
						asyncbox.alert(data.error, "上传错误");
					} else {
						alert(data.msg);
					}
				}
			}
		},
		error : function(data, status, e) {//服务器响应失败处理函数
			asyncbox.success("上传错误!", "提示");
		}
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_takestock.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/takeStockAction!goList.action?pageNo=${pageNo}');"><s:text name="inventory_takeStock_business" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">

	<div class="order">
		<form action="${vix}/inventory/takeStockAction!exportTakeStockExcel.action" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0">
			<input type="hidden" id="id" name="id" value="${stockTaking.id}" /> <input type="file" id="fileToUpload" name="fileToUpload" onchange="importXlsFile();" style="display: none;" />
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#" onclick="$('#fileToUpload').click()"><img width="22"
							height="22" title="导入" src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/inventory/takeStockAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="inventory_takeStock" /> </b> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>盘点单号：</th>
											<td><input id="code" name="code" value="${stockTaking.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>主题：</th>
											<td><input id="name" name="name" value="${stockTaking.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>仓库：</th>
											<td><input type="hidden" id="warehouseId" name="warehouseId" value="${stockTaking.invWarehouse.id }" onchange="fieldChanged(this);" /><input type="text" name="warehouseName" id="warehouseName" value="${stockTaking.invWarehouse.name }" size="20" class="validate[required] text-input" /><input class="btn" type="button" value="选择"
												onclick="choosewarehouse();" /><span style="color: red;">*</span></td>
											<th>盘点日期：</th>
											<td><input id="createTime" name="createTime" value="<s:date name='%{stockTaking.createTime}' format='yyyy-MM-dd HH:mm:ss'/>" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif"
												width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>盘点类型：</th>
											<td><select id="sttype" name="sttype" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1" selected="selected">盘盈盘亏</option>
													<option value="2">委托代销商品出库</option>
													<option value="3">商品报损出库</option>
											</select><span style="color: red;">*</span></td>
											<th>制单人：</th>
											<td><input id="maker" name="maker" value="${stockTaking.maker }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />商品表</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<div>
							<table id="dlAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers : true,fitColumns : true,url: '${vix}/inventory/takeStockAction!getStockTakingItemJson.action?id=${stockTaking.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'itemcode',width:100,align:'center',required:'true'">商品编码</th>
										<th data-options="field:'itemname',width:100,align:'center'">商品名称</th>
										<th data-options="field:'skuCode',width:100,align:'center',required:'true'">SKU编码</th>
										<th data-options="field:'specification',width:100,align:'center'">规格型号</th>
										<th data-options="field:'cvquantity',width:80,align:'center',required:'true'">账面数量</th>
										<th data-options="field:'cvcquantity',width:80,align:'center',editor :'numberbox',required:'true'">盘点数量</th>
									</tr>
								</thead>
							</table>
							<script type="text/javascript">
								$('#dlAddress').datagrid();
								var editIndexDlAddress = undefined;
								function endDlEditing() {
									if (editIndexDlAddress == undefined) {
										return true;
									}
									if ($('#dlAddress').datagrid('validateRow', editIndexDlAddress)) {
										$('#dlAddress').datagrid('endEdit', editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlAddress != index) {
										if (endDlEditing()) {
											$('#dlAddress').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlAddress = index;
										} else {
											$('#dlAddress').datagrid('selectRow', editIndexDlAddress);
										}
									}
								}
								function appendDlAddress() {
									if (endDlEditing()) {
										$('#dlAddress').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlAddress = $('#dlAddress').datagrid('getRows').length - 1;
										$('#dlAddress').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
									}
								}
								function removeDlAddress() {
									if (editIndexDlAddress == undefined) {
										return;
									}
									$('#dlAddress').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress() {
									if (endDlEditing()) {
										$('#dlAddress').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
				</div>
			</dl>
		</form>
	</div>
</div>