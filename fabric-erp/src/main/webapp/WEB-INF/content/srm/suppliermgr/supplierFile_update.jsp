<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>

<script type="text/javascript">
	/**页面加载时执行*/
	$(function(){
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
	});
	loadMenuContent('${vix}/srm/supplierMainAction!goMenuContent.action');
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
		   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
		});
		$(".order_table  input[type='text']").focusout( function() {
		   $(this).css({'border':'1px solid #ccc','background':'#fff'});
		});
	/** 保存供应商 */
	function saveOrUpdateOrder(){
		var orderItemStr = "";
		/** 资质信息 */
		var dlData = $("#dlAptitudeInfo").datagrid("getRows");
		var dlJson = JSON.stringify(dlData);
		/** 地址 */
		var sdData = $("#dlAddress").datagrid("getRows");
		var sdJson = JSON.stringify(sdData);
		/** 银行信息 */
		var sbData = $("#dlBankInfo").datagrid("getRows");
		var sbJson = JSON.stringify(sbData);
		/** 银行信息 */
		var saiData = $("#dlAccountingInfo").datagrid("getRows");
		var saiJson = JSON.stringify(saiData);
		/** 信用 */
		var scData = $("#dlCreditInfo").datagrid("getRows");
		var scJson = JSON.stringify(scData);
		/** 指标 */
		var siData = $("#dlIndicators").datagrid("getRows");
		var siJson = JSON.stringify(siData);
		if($('#supplierForm').validationEngine('validate')){
			$.post('${vix}/srm/supplierFileAction!saveOrUpdate.action?parentId='+$("#parentId").val(),
					{
						'supplier.id':$("#id").val(),
						'supplier.code':$("#code").val(),
						'supplier.name':$("#name").val(),
						'supplier.type':$("#type").val(),
						'supplier.contacts':$("#contacts").val(),
						'supplier.telephone':$("#telephone").val(),
						'supplier.artificialPerson':$("#artificialPerson").val(),
						'supplier.pinYin':$("#pinYin").val(),
						'supplier.catalog':$("#catalog").val(),
						'supplier.catalogs':$("#catalogs").val(),
						'supplier.industry':$("#industry").val(),
						'supplier.employeeCounts':$("#employeeCounts").val(),
						'supplier.parentCompanyCode':$("#parentCompanyCode").val(),
						'supplier.parentCompanyName':$("#parentCompanyName").val(),
						'supplier.region':$("#region").val(),
						'supplier.currency':$("#currency").val(),
						'supplier.cellphone':$("#cellphone").val(),
						'supplier.registeredCapital':$("#registeredCapital").val(),
						'supplier.portraiture':$("#portraiture").val(),
						'supplier.classIfy':$("#classIfy").val(),
						'supplier.abcClassIfy':$("#abcClassIfy").val(),
						'supplier.catalog':$("#catalog").val(),
						'orderItemStr':orderItemStr,
						'dlJson':dlJson,
						'sdJson':sdJson,
						'sbJson':sbJson,
						'saiJson':saiJson,
						'scJson':scJson,
						'siJson':siJson
					},
					function(result){
						showMessage(result);
						setTimeout("",1000);
						loadContent('${vix}/srm/supplierFileAction!goList.action?menuId=menuOrder');
					}
				 );
		}
	}
	$(".addtable .addtable_t").click(function(){
		$(this).toggleClass("addtable_td");
		$(this).next(".addtable_c").toggle();
	});
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	
	//选择分类
	function chooseSupplierCategory(){
		$.ajax({
			  url:'${vix}/srm/supplierFileAction!goChooseSupplierCategory.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择所属分类",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var result = returnValue.split(",");
										$("#parentId").val(result[0]); 
										$("#catalog").val(result[1]);
								}else{
									$("#parentId").val("");
									$("#catalog").val("");
									asyncbox.success("请选择所属分类信息!","提示信息");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
$("#supplierForm").validationEngine();
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/srm_supplier.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="srm_supp_rela_manage" /></a></li>
				<li><a href="#"><s:text name='srm_supp_manage' /></a></li>
				<a href="#" onclick="loadContent('${vix}/srm/supplierFileAction!goList.action');">供应商建档</a>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${supplier.id}" />
<div class="content">
	<form id="supplierForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" alt="取消"
							src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png" /></a> <a href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png" /></a> <a
						href="#"><img width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a> <a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"></a> <a href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"></a> <a href="#"><img width="22" height="22" alt="打印"
							src="${vix}/common/img/dt_print.png"></a>
						<div class="ms" style="float: none; display: inline;">
							<a href="#"><img width="22" height="22" alt="报表" src="${vix}/common/img/dt_report.png"></a>
							<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
							</ul>
						</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a> <a href="#" onclick="loadContent('${vix}/srm/supplierFileAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增供应商</b> <i></i>
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
											<td><input id="telephone" name="telephone" value="${supplier.telephone }" type="text" size="30" class="validate[required,custom[integer]] text-input" /><span style="color: red;">*</span></td>
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
											</select></td>
										</tr>
										<tr>
											<td align="right">状态：</td>
											<td><select id="status" name="status" style="width: 50">
													<option value="3" selected="selected">正式</option>
											</select></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>其他信息</strong>
								</dt>

								<dd style="display: none;">
									<table style="border: none;">
										<tr>
											<td align="right">负责采购员编码：</td>
											<td><input id="purchaserCode" name="BO_SPR_CMNM_purchaserCode" type="text" size="30" /></td>
											<td align="right">采购员：</td>
											<td><input id="purchaser" name="BO_SPR_CMNM_purchaser" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">成本中心编码：</td>
											<td><input id="costCenterCode" name="BO_SPR_CMNM_costCenterCode" type="text" size="30" /></td>
											<td align="right">成本中心：</td>
											<td><input id="costCenter" name="BO_SPR_CMNM_costCenter" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">委外仓库：</td>
											<td><input id="outSourcingWarehouse" name="BO_SPR_CMNM_outSourcingWarehouse" type="text" size="30" /></td>
											<td align="right">委外货位：</td>
											<td><input id="outSourcingPositon" name="BO_SPR_CMNM_outSourcingPositon" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">是否为委外供应：</td>
											<td><select name="BO_SPR_CMNM_isOutSourcing" style="width: 50"><option>请选择</option></select></td>
											<td align="right">是否为服务提供商：</td>
											<td><select name="BO_SPR_CMNM_isServiceProvider" style="width: 50"><option>请选择</option></select></td>
										</tr>
										<tr>
											<td align="right">是否为国外供应商：</td>
											<td><select name="BO_SPR_CMNM_isForeignSupplier" style="width: 50"><option>请选择</option></select></td>
											<td align="right">是否直接提供产品：</td>
											<td><select name="BO_SPR_CMNM_isDirectltProvideProduct" style="width: 50"><option>请选择</option></select></td>
										</tr>
										<tr>
											<td align="right">是否齐批接收：</td>
											<td><select name="BO_SPR_CMNM_isTotalBatchReciever" style="width: 50"><option>请选择</option></select></td>
											<td align="right">是否单价接收：</td>
											<td><select name="BO_SPR_CMNM_isNeedUnitPricer" style="width: 50"><option>请选择</option></select></td>
										</tr>
										<tr>
											<td align="right">是否进行IQC检验：</td>
											<td><select name="BO_SPR_CMNM_isNeedIQCr" style="width: 50"><option>请选择</option></select></td>
											<td align="right">超交处理方式：</td>
											<td><select style="width: 50"><option>请选择</option></select></td>
										</tr>
										<tr>
											<td align="right">超交百分比：</td>
											<td><input id="exceedPercent" name="BO_SPR_CMNM_exceedPercent" type="text" size="30" /></td>
											<td align="right">超交数量：</td>
											<td><input id="exceedAmount" name="BO_SPR_CMNM_exceedAmount" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">超交是否付款：</td>
											<td><select name="BO_SPR_CMNM_exceedAmount" style="width: 50"><option>请选择</option></select></td>
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
							<table id="dlAptitudeInfo" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlAptitudeInfoTb',url: '${vix}/srm/supplierFileAction!getSupplierAptitudeInfoJson.action?id=${supplier.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'name',width:200,align:'center',editor:'text'">资质名称</th>
										<th data-options="field:'description',width:300,align:'center',editor:'text'">资质描述</th>
										<th data-options="field:'files',width:200,align:'center',editor:'text'">资质文件</th>
										<th data-options="field:'validPeriod',width:120,align:'center',editor:'text'">资质有效期</th>
									</tr>
								</thead>
							</table>
							<div id="dlAptitudeInfoTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlAptitudeInfo()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)"
									class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeDlAptitudeInfo()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-save',plain:true" onclick="saveDlAptitudeInfo()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlAptitudeInfo').datagrid();
							var editIndexDlAptitudeInfo = undefined;
							function endDlEditing(){
								if (editIndexDlAptitudeInfo == undefined){return true;}
								if ($('#dlAptitudeInfo').datagrid('validateRow', editIndexDlAptitudeInfo)){
									$('#dlAptitudeInfo').datagrid('endEdit', editIndexDlAptitudeInfo);
									editIndexDlAptitudeInfo = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow(index){
								if (editIndexDlAptitudeInfo != index){
									if (endDlEditing()){
										$('#dlAptitudeInfo').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlAptitudeInfo = index;
									} else {
										$('#dlAptitudeInfo').datagrid('selectRow', editIndexDlAptitudeInfo);
									}
								}
							}
							function appendDlAptitudeInfo(){
								if (endDlEditing()){
									$('#dlAptitudeInfo').datagrid('appendRow',{status:'P'});
									editIndexDlAptitudeInfo = $('#dlAptitudeInfo').datagrid('getRows').length-1;
									$('#dlAptitudeInfo').datagrid('selectRow', editIndexDlAptitudeInfo).datagrid('beginEdit', editIndexDlAptitudeInfo);
								}
							}
							function removeDlAptitudeInfo(){
								if (editIndexDlAptitudeInfo == undefined){return;}
								$('#dlAptitudeInfo').datagrid('cancelEdit', editIndexDlAptitudeInfo)
										.datagrid('deleteRow', editIndexDlAptitudeInfo);
								editIndexDlAptitudeInfo = undefined;
							}
							function saveDlAptitudeInfo(){
								if (endDlEditing()){
									$('#dlAptitudeInfo').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlAddressTb',url: '${vix}/srm/supplierFileAction!getAddressJson.action?id=${supplier.id}',onClickRow: onDlClickRow2">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
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
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-save',plain:true"
									onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlAddress').datagrid();
							var editIndexDlAddress = undefined;
							function endDlEditing2(){
								if (editIndexDlAddress == undefined){return true;}
								if ($('#dlAddress').datagrid('validateRow', editIndexDlAddress)){
									$('#dlAddress').datagrid('endEdit', editIndexDlAddress);
									editIndexDlAddress = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow2(index){
								if (editIndexDlAddress != index){
									if (endDlEditing2()){
										$('#dlAddress').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlAddress = index;
									} else {
										$('#dlAddress').datagrid('selectRow', editIndexDlAddress);
									}
								}
							}
							function appendDlAddress(){
								if (endDlEditing2()){
									$('#dlAddress').datagrid('appendRow',{status:'P'});
									editIndexDlAddress = $('#dlAddress').datagrid('getRows').length-1;
									$('#dlAddress').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
								}
							}
							function removeDlAddress(){
								if (editIndexDlAddress == undefined){return;}
								$('#dlAddress').datagrid('cancelEdit', editIndexDlAddress)
										.datagrid('deleteRow', editIndexDlAddress);
								editIndexDlAddress = undefined;
							}
							function saveDlAddress(){
								if (endDlEditing2()){
									$('#dlAddress').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlBankInfo" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlBankInfoTb',url: '${vix}/srm/supplierFileAction!getBankInfoJson.action?id=${supplier.id}',onClickRow: onDlClickRow3">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'bankName',width:200,align:'center',editor:'text'">开户银行</th>
										<th data-options="field:'bankAccount',width:200,align:'center',editor:'text'">银行帐号</th>
									</tr>
								</thead>
							</table>
							<div id="dlBankInfoTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlBankInfo()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDlBankInfo()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-save',plain:true"
									onclick="saveDlBankInfo()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlBankInfo').datagrid();
							var editIndexDlBankInfo = undefined;
							function endDlEditing3(){
								if (editIndexDlBankInfo == undefined){return true;}
								if ($('#dlBankInfo').datagrid('validateRow', editIndexDlBankInfo)){
									$('#dlBankInfo').datagrid('endEdit', editIndexDlBankInfo);
									editIndexDlBankInfo = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow3(index){
								if (editIndexDlBankInfo != index){
									if (endDlEditing3()){
										$('#dlBankInfo').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlBankInfo = index;
									} else {
										$('#dlBankInfo').datagrid('selectRow', editIndexDlBankInfo);
									}
								}
							}
							function appendDlBankInfo(){
								if (endDlEditing3()){
									$('#dlBankInfo').datagrid('appendRow',{status:'P'});
									editIndexDlBankInfo = $('#dlBankInfo').datagrid('getRows').length-1;
									$('#dlBankInfo').datagrid('selectRow', editIndexDlBankInfo).datagrid('beginEdit', editIndexDlBankInfo);
								}
							}
							function removeDlBankInfo(){
								if (editIndexDlBankInfo == undefined){return;}
								$('#dlBankInfo').datagrid('cancelEdit', editIndexDlBankInfo)
										.datagrid('deleteRow', editIndexDlBankInfo);
								editIndexDlBankInfo = undefined;
							}
							function saveDlBankInfo(){
								if (endDlEditing3()){
									$('#dlBankInfo').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>
					<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlAccountingInfo" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlAccountingInfoTb',url: '${vix}/srm/supplierFileAction!getAccountingInfoJson.action?id=${supplier.id}',onClickRow: onDlClickRow4">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'generalAccountCatalog',width:200,align:'center',editor:'text'">总帐分类</th>
										<th data-options="field:'payCondition',width:200,align:'center',editor:'text'">付款条件</th>
										<th data-options="field:'payType',width:200,align:'center',editor:'text'">付款类型</th>
										<th data-options="field:'payStyle',width:200,align:'center',editor:'text'">付款方式</th>
										<th data-options="field:'payTarget',width:200,align:'center',editor:'text'">付款对象</th>
										<th data-options="field:'limited',width:200,align:'center',editor:'text'">付款期限</th>
										<th data-options="field:'invoiceHeader',width:200,align:'center',editor:'text'">发票抬头</th>
										<th data-options="field:'vaBank',width:200,align:'center',editor:'text'">增值税开户银行</th>
										<th data-options="field:'checked',width:200,align:'center',editor:'text'">审核人</th>
									</tr>
								</thead>
							</table>
							<div id="dlAccountingInfoTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlAccountingInfo()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)"
									class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeDlCreditInfo()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-save',plain:true" onclick="saveDlAccountingInfo()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlAccountingInfo').datagrid();
							var editIndexDlAccountingInfo = undefined;
							function endDlEditing4(){
								if (editIndexDlAccountingInfo == undefined){return true;}
								if ($('#dlAccountingInfo').datagrid('validateRow', editIndexDlAccountingInfo)){
									$('#dlAccountingInfo').datagrid('endEdit', editIndexDlAccountingInfo);
									editIndexDlAccountingInfo = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow4(index){
								if (editIndexDlAccountingInfo != index){
									if (endDlEditing4()){
										$('#dlAccountingInfo').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlAccountingInfo = index;
									} else {
										$('#dlAccountingInfo').datagrid('selectRow', editIndexDlAccountingInfo);
									}
								}
							}
							function appendDlAccountingInfo(){
								if (endDlEditing4()){
									$('#dlAccountingInfo').datagrid('appendRow',{status:'P'});
									editIndexDlAccountingInfo = $('#dlAccountingInfo').datagrid('getRows').length-1;
									$('#dlAccountingInfo').datagrid('selectRow', editIndexDlAccountingInfo).datagrid('beginEdit', editIndexDlAccountingInfo);
								}
							}
							function removeDlAccountingInfo(){
								if (editIndexDlAccountingInfo == undefined){return;}
								$('#dlAccountingInfo').datagrid('cancelEdit', editIndexDlAccountingInfo)
										.datagrid('deleteRow', editIndexDlAccountingInfo);
								editIndexDlAccountingInfo = undefined;
							}
							function saveDlAccountingInfo(){
								if (endDlEditing4()){
									$('#dlAccountingInfo').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>
					<div id="a5" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlCreditInfo" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlCreditInfoTb',url: '${vix}/srm/supplierFileAction!getCreditInfoJson.action?id=${supplier.id}',onClickRow: onDlClickRow5">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'supplierName',width:200,align:'center',editor:'text'">供应商名称</th>
										<th data-options="field:'creditLevel',width:200,align:'center',editor:'text'">信用等级</th>
										<th data-options="field:'creditAmount',width:200,align:'center',editor:'numberbox'">信用额度</th>
										<th data-options="field:'discount',width:200,align:'center',editor:'numberbox'">折扣率</th>
										<th data-options="field:'payCondition',width:200,align:'center',editor:'text'">付款条件</th>
										<th data-options="field:'lastDealAmount',width:200,align:'center',editor:'numberbox'">最后交易金额</th>
										<th data-options="field:'lastDealTime',width:200,align:'center',editor:'datebox'">最后交易日期</th>
									</tr>
								</thead>
							</table>
							<div id="dlCreditInfoTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlCreditInfo()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDlCreditInfo()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-save',plain:true"
									onclick="saveDlCreditInfo()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlCreditInfo').datagrid();
							var editIndexDlCreditInfo = undefined;
							function endDlEditing5(){
								if (editIndexDlCreditInfo == undefined){return true;}
								if ($('#dlCreditInfo').datagrid('validateRow', editIndexDlCreditInfo)){
									$('#dlCreditInfo').datagrid('endEdit', editIndexDlCreditInfo);
									editIndexDlCreditInfo = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow5(index){
								if (editIndexDlCreditInfo != index){
									if (endDlEditing5()){
										$('#dlCreditInfo').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlCreditInfo = index;
									} else {
										$('#dlCreditInfo').datagrid('selectRow', editIndexDlCreditInfo);
									}
								}
							}
							function appendDlCreditInfo(){
								if (endDlEditing5()){
									$('#dlCreditInfo').datagrid('appendRow',{status:'P'});
									editIndexDlCreditInfo = $('#dlCreditInfo').datagrid('getRows').length-1;
									$('#dlCreditInfo').datagrid('selectRow', editIndexDlCreditInfo).datagrid('beginEdit', editIndexDlCreditInfo);
								}
							}
							function removeDlCreditInfo(){
								if (editIndexDlCreditInfo == undefined){return;}
								$('#dlCreditInfo').datagrid('cancelEdit', editIndexDlCreditInfo)
										.datagrid('deleteRow', editIndexDlCreditInfo);
								editIndexDlCreditInfo = undefined;
							}
							function saveDlCreditInfo(){
								if (endDlEditing5()){
									$('#dlCreditInfo').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>
					<div id="a6" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlIndicators" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlIndicatorsTb',url: '${vix}/srm/supplierFileAction!getIndicatorsJson.action?id=${supplier.id}',onClickRow: onDlClickRow6">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'itemName',width:200,align:'center',editor:'text'">指标名称</th>
										<th data-options="field:'weights',width:200,align:'center',editor:'text'">权重</th>
										<th data-options="field:'quota',width:200,align:'center',editor:'numberbox'">配额数量</th>
										<th data-options="field:'priceLimit',width:200,align:'center',editor:'numberbox'">金额限度</th>
									</tr>
								</thead>
							</table>
							<div id="dlIndicatorsTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlIndicators()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDlIndicators()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-save',plain:true"
									onclick="saveDlIndicators()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlIndicators').datagrid();
							var editIndexDlIndicators = undefined;
							function endDlEditing6(){
								if (editIndexDlIndicators == undefined){return true;}
								if ($('#dlIndicators').datagrid('validateRow', editIndexDlIndicators)){
									$('#dlIndicators').datagrid('endEdit', editIndexDlIndicators);
									editIndexDlIndicators = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow6(index){
								if (editIndexDlIndicators != index){
									if (endDlEditing5()){
										$('#dlIndicators').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlIndicators = index;
									} else {
										$('#dlIndicators').datagrid('selectRow', editIndexDlIndicators);
									}
								}
							}
							function appendDlIndicators(){
								if (endDlEditing5()){
									$('#dlIndicators').datagrid('appendRow',{status:'P'});
									editIndexDlIndicators = $('#dlIndicators').datagrid('getRows').length-1;
									$('#dlIndicators').datagrid('selectRow', editIndexDlIndicators).datagrid('beginEdit', editIndexDlIndicators);
								}
							}
							function removeDlIndicators(){
								if (editIndexDlIndicators == undefined){return;}
								$('#dlIndicators').datagrid('cancelEdit', editIndexDlIndicators)
										.datagrid('deleteRow', editIndexDlIndicators);
								editIndexDlIndicators = undefined;
							}
							function saveDlIndicators(){
								if (endDlEditing6()){
									$('#dlIndicators').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>
					<div id="a7" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
					$('#soAttach').datagrid({
						url: '${vix}/srm/supplierFileAction!getAttachmentsJson.action?id=${supplier.id}',
						title: '附件',
						width: 900,
						height: '450',
						fitColumns: true,
						columns:[[
							{field:'id',title:'编号',width:80},
							{field:'name',title:'名称',width:180},
						]],
						toolbar:[{
							id:'saBtnadd',
							text:'添加',
							iconCls:'icon-add',
							handler:function(){
								$('#btnsave').linkbutton('enable');
								$.ajax({
									  url:'${vix}/srm/supplierFileAction!addAttachments.action',
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 364,
												height : 160,
												title:"上传附件",
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														uploadFile('${vix}/srm/supplierFileAction!uploadAttachments.action?id=${supplier.id}','fileToUpload');
														$('#soAttach').datagrid("reload");
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
						},'-',{
							text:'删除',
							iconCls:'icon-remove',
							handler:function(){
								var rows = $('#soAttach').datagrid("getSelections");	//获取你选择的所有行	
								//循环所选的行
								for(var i =0;i<rows.length;i++){
									var index = $('#soAttach').datagrid('getRowIndex',rows[i]);//获取某行的行号
									$('#soAttach').datagrid('deleteRow',index);	//通过行号移除该行
									$.ajax({url:'${vix}/srm/supplierFileAction!deleteAttachFile.action?afId='+rows[i].id,cache: false});
								}
							}
						}]
					});
				</script>
						<div style="padding: 8px;">
							<table id="soAttach"></table>
						</div>
					</div>
					<!-- 
				 <div id="a7" style="width:100%; visibility:hidden; position:absolute; top:-5000px;">
					<div class="right_btn nomargin">
						<span><a href="#" onclick="addOrderItem();"><img src="${vix}/common/img/dt_line_add.png" alt="" /></a></span>
						<span><a href="#" ><img src="${vix}/common/img/dt_line_edit.png" alt="" /></a></span>
						<span><a href="#"><img src="${vix}/common/img/dt_line_view.png" alt="" /></a></span>
						<span><a href="#"><img src="${vix}/common/img/dt_line_delete.png" alt="" /></a></span>
					</div>
					<table id="dgdA7"></table>
				</div>
				 -->
				</div>
			</dl>
		</div>
		<!--oder-->
		<div class="sub_menu sub_menu_bot" style="display: none;">
			<div class="drop">
				<p>
					<a href="#" onclick=""><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/template/orderAction!goList.action');"><span>返回</span></a> <a href="#"><span>编辑</span></a> <a href="#"><span>复制</span></a> <a href="#"><span>删除</span></a> <a href="#"><span>关闭并新建</span></a> <a href="#"><span>关闭</span></a> <a href="#" id="text"><span>弹出窗口测试</span></a>
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