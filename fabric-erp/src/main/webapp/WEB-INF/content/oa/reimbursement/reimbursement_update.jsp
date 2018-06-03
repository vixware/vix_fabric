<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
	/** input 获取焦点input效果绑定  */
	$ (".order_table input[type='text']").focusin (function (){
		$ (this).css ({
		'border' : '1px solid #f00' ,
		'background' : '#f5f5f5'
		});
	});
	$ (".order_table  input[type='text']").focusout (function (){
		$ (this).css ({
		'border' : '1px solid #ccc' ,
		'background' : '#fff'
		});
	});
	function saveOrUpdateChannelDistributor (){
		if ($ ('#channelDistributorForm').validationEngine ('validate')){
			$.post ('${vix}/drp/distributerManagementAction!saveOrUpdate.action' , {
			'channelDistributor.id' : $ ("#id").val () ,
			'organizationId' : $ ("#parentId").val () ,
			'channelDistributor.code' : $ ("#code").val () ,
			'channelDistributor.name' : $ ("#name").val () ,
			'channelDistributor.shortName' : $ ("#shortName").val () ,
			'channelDistributor.status' : $ ("#status").val () ,
			'channelDistributor.telephone' : $ ("#telephone").val () ,
			'channelDistributor.industry' : $ ("#industry").val () ,
			'channelDistributor.employeeCounts' : $ ("#employeeCounts").val () ,
			'channelDistributor.email' : $ ("#email").val () ,
			'channelDistributor.catalog' : $ ("#catalog").val () ,
			'channelDistributor.level' : $ ("#level").val () ,
			'channelDistributor.artificialPerson' : $ ("#artificialPerson").val () ,
			'channelDistributor.registeredCapital' : $ ("#registeredCapital").val () ,
			'channelDistributor.currency' : $ ("#currency").val () ,
			'channelDistributor.openingBank' : $ ("#openingBank").val () ,
			'channelDistributor.bankAccount' : $ ("#bankAccount").val () ,
			'channelDistributor.taxCode' : $ ("#taxCode").val () ,
			'channelDistributor.region' : $ ("#region").val () ,
			'channelDistributor.address' : $ ("#address").val () ,
			'channelDistributor.permit' : $ ("#permit").val () ,
			'channelDistributor.maplink' : $ ("#maplink").val () ,
			'channelDistributor.picture' : $ ("#picture").val () ,
			'channelDistributor.accessory' : $ ("#accessory").val ()
			} , function (result){
				showMessage (result);
				setTimeout ("" , 1000);
				loadContent ('${vix}/drp/distributerManagementAction!goList.action');
			});
		}else{
			return false;
		}
	}
	$ ("#channelDistributorForm").validationEngine ();
	if ($ (".ms").length > 0){
		$ (".ms").hover (function (){
			$ (">ul" , this).stop ().slideDown (100);
		} , function (){
			$ (">ul" , this).stop ().slideUp (100);
		});
		$ (".ms ul li").hover (function (){
			$ (">a" , this).addClass ("hover");
			$ (">ul" , this).stop ().slideDown (100);
		} , function (){
			$ (">a" , this).removeClass ("hover");
			$ (">ul" , this).stop ().slideUp (100);
		});
	}
	function chooseParentOrganization (){
		$.ajax ({
		url : '${vix}/drp/distributerManagementAction!goChooseOrganization.action' ,
		cache : false ,
		success : function (html){
			asyncbox.open ({
			modal : true ,
			width : 560 ,
			height : 340 ,
			title : "选择父公司" ,
			html : html ,
			callback : function (action , returnValue){
				if (action == 'ok'){
					if (returnValue != undefined){
						var result = returnValue.split (",");
						if (result [ 0 ] == $ ("#id").val ()){
							asyncbox.success ("不允许引用本公司为父公司!" , "提示信息");
						}else{
							$ ("#parentId").val (result [ 0 ]);
							$ ("#organizationName").val (result [ 1 ]);
						}
					}else{
						$ ("#parentId").val ("");
						$ ("#organizationName").val ("");
						asyncbox.success ("请选择公司信息!" , "提示信息");
					}
				}
			} ,
			btnsbar : $.btn.OKCANCEL
			});
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
				<li><a href="#"><img src="${vix}/common/img/oa_claims_management.png" alt="" /> 协同办公 </a></li>
				<li><a href="#">行政办公 </a></li>
				<li><a href="#">报销管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/reimbursementAction!goList.action');">项目费用报销 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="channelDistributor.id" value="${channelDistributor.id}" />
<div class="content">
	<form id="channelDistributorForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/oa/reimbursementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="项目费用报销 " /> </b><i></i> </strong>
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
											<td align="right">报销人：</td>
											<td><input id="name" name="name" value="${channelDistributor.name }" type="text" size="20" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<td align="right">报销日期：</td>
											<td><input id="createTime" name="createTime" value="${wimStockrecords.createTime}" type="text" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"> <span style="color: red;">*</span>
											</td>
										</tr>
										<tr>
											<td align="right">项目：</td>
											<td><input id="code" name="code" value="${channelDistributor.code }" type="text" size="30" /></td>
											<td align="right">部门：</td>
											<td><select id="industry" name="industry" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="财务" selected="selected">财务</option>
													<option value="销售">销售</option>
											</select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">报销说明：</th>
											<td colspan="3"><textarea id="description" name="description">${competingProducts.description }</textarea> <script type="text/javascript">
														var editor = KindEditor.create ('textarea[name="description"]' , {
														basePath : '${vix}/plugin/KindEditor/' ,
														width : 830 ,
														height : 200 ,
														cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css' ,
														uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp' ,
														fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp' ,
														allowFileManager : true
														});
													</script></td>
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
							<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(5,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />费用报销明细</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(5,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />未还清借款</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlAddressTb',url: '${vix}/inventory/allocateTransferAction!getWimTransvouchItemJson.action?id=${wimTransvouch.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'cinvcode',width:100,align:'center',editor:'text'">费用报销科目</th>
										<th data-options="field:'tvquantity',width:100,align:'center',editor:'text'">预算金额</th>
										<th data-options="field:'tvacost',width:100,align:'center',editor:'numberbox'">可用金额</th>
										<th data-options="field:'tvaprice',width:100,align:'center',editor:'numberbox'">报销金额</th>
										<th data-options="field:'tvpcost',width:100,align:'center',editor:'numberbox'">开支日期</th>
										<th data-options="field:'salecost',width:100,align:'center',editor:'numberbox'">发票张数</th>
										<th data-options="field:'salecost',width:100,align:'center',editor:'numberbox'">会计调整金额</th>
										<th data-options="field:'salecost',width:100,align:'center',editor:'numberbox'">调整后金额</th>
										<th data-options="field:'salecost',width:100,align:'center',editor:'numberbox'">备注</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span> </a>
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
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="vehicleTable" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#vehicleTb',url: '${vix}/inventory/allocateTransferAction!getWimTransvouchItemJson.action?id=${wimTransvouch.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'cinvcode',width:100,align:'center',editor:'text'">单号</th>
										<th data-options="field:'tvquantity',width:100,align:'center',editor:'text'">借款人</th>
										<th data-options="field:'tvacost',width:100,align:'center',editor:'numberbox'">借款金额</th>
										<th data-options="field:'tvaprice',width:100,align:'center',editor:'numberbox'">已还款金额</th>
										<th data-options="field:'tvpcost',width:100,align:'center',editor:'numberbox'">未还款金额</th>
										<th data-options="field:'salecost',width:100,align:'center',editor:'numberbox'">本次还款金额</th>
										<th data-options="field:'salecost',width:100,align:'center',editor:'numberbox'">状态</th>
										<th data-options="field:'salecost',width:100,align:'center',editor:'numberbox'">备注</th>
									</tr>
								</thead>
							</table>
							<div id="vehicleTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendVehicle()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeVehicle()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveVehicle()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span> </a>
							</div>
							<script type="text/javascript">
								$ ('#vehicleTable').datagrid ();
								var editIndexDlAddress = undefined;
								function endDlEditing (){
									if (editIndexDlAddress == undefined){
										return true;
									}
									if ($ ('#vehicleTable').datagrid ('validateRow' , editIndexDlAddress)){
										$ ('#vehicleTable').datagrid ('endEdit' , editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									}else{
										return false;
									}
								}
								function onDlClickRow (index){
									if (editIndexDlAddress != index){
										if (endDlEditing ()){
											$ ('#vehicleTable').datagrid ('selectRow' , index).datagrid ('beginEdit' , index);
											editIndexDlAddress = index;
										}else{
											$ ('#vehicleTable').datagrid ('selectRow' , editIndexDlAddress);
										}
									}
								}
								function appendVehicle (){
									if (endDlEditing ()){
										$ ('#vehicleTable').datagrid ('appendRow' , {
											status : 'P'
										});
										editIndexDlAddress = $ ('#vehicleTable').datagrid ('getRows').length - 1;
										$ ('#vehicleTable').datagrid ('selectRow' , editIndexDlAddress).datagrid ('beginEdit' , editIndexDlAddress);
									}
								}
								function removeVehicle (){
									if (editIndexDlAddress == undefined){
										return;
									}
									$ ('#vehicleTable').datagrid ('cancelEdit' , editIndexDlAddress).datagrid ('deleteRow' , editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveVehicle (){
									if (endDlEditing ()){
										$ ('#vehicleTable').datagrid ('acceptChanges');
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
<!-- content -->
<script type="text/javascript">
	$ (function (){
		$.fn.fix = function (options){
			var defaults = {
			'advance' : 0 ,
			'top' : 0
			};
			options = $.extend (defaults , options);
			return this.each (function (){
				var $_this = $ (this);
				$_this.wrap ('<div></div>');
				var wp = $_this.parent ('div');
				wp.height (wp.height ());
				wp.offset ();
				if ( ! $_this.is (':visible') && $ (window).scrollTop () + options.advance > $_this.offset ().top){
					$_this.css ({
					'position' : 'fixed' ,
					'z-index' : 9999 ,
					'top' : options.top ,
					'width' : $_this.width ()
					});
				}
				$ (window).scroll (function (){
					if ( ! $_this.is (':visible')){
						return;
					}
					if ($ (window).scrollTop () + options.advance > wp.offset ().top){
						$_this.css ({
						'position' : 'fixed' ,
						'z-index' : 9999 ,
						'top' : options.top ,
						'width' : $_this.width ()
						});
					}else{
						$_this.css ({
						'position' : 'static' ,
						'z-index' : 'auto' ,
						'top' : 'auto' ,
						'width' : 'auto'
						});
					}
				});
			});
		};
		$ ('#a1 .right_btn,#a2 .right_btn').fix ({
		'advance' : 38 ,
		'top' : 38
		});
	});
	function tabs (title , content , style){
		$ (title).click (function (){
			$ (title).removeClass (style);
			$ (this).addClass (style);
			$ (content).hide ();
			$ (content + ':eq(' + $ (title).index ($ (this)) + ')').show ();
		});
	}
</script>