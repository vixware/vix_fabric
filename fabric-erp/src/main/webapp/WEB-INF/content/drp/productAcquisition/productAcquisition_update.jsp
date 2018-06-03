<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
	function saveOrUpdateChannelDistributor (){
		if ($ ('#channelDistributorForm').validationEngine ('validate')){
			$.post ('${vix}/drp/productAcquisitionAction!saveOrUpdate.action' , {
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
				loadContent ('${vix}/drp/productAcquisitionAction!goList.action');
			});
		}else{
			return false;
		}
	}
	$ ("#channelDistributorForm").validationEngine ();
	function chooseParentOrganization (){
		$.ajax ({
		url : '${vix}/drp/productAcquisitionAction!goChooseOrganization.action' ,
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
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">门店管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/productAcquisitionAction!goList.action');">本品竞品采集 </a></li>
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
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/drp/productAcquisitionAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增 </b></strong>
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
											<th>产品编码：</th>
											<td><input id="code" name="code" value="${competingProducts.code }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>${vv:varView("vix_mdm_item")}名称：</th>
											<td><input id="name" name="name" value="${competingProducts.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>通用名称：</th>
											<td><input id="gernalName" name="gernalName" value="${competingProducts.gernalName}" type="text" size="30" /></td>
											<th>英文名：</th>
											<td><input id="engishName" name="engishName" value="${competingProducts.engishName}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>规格型号：</th>
											<td><input id="specification" name="specification" value="${competingProducts.specification}" type="text" size="30" /></td>
											<th>价格：</th>
											<td><input id="price" name="price" value="${competingProducts.price}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>EAN/UPC：</th>
											<td><input id="eanupc" name="eanupc" value="${competingProducts.eanupc }" type="text" size="30"></td>
											<th>${vv:varView("vix_mdm_item")}代码：</th>
											<td><input id="xcode" name="xcode" value="${competingProducts.xcode}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>${vv:varView("vix_mdm_item")}类型：</th>
											<td><select id="itemType" name="itemType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="类型1" selected="selected">类型1</option>
													<option value="类型2">类型2</option>
											</select><span style="color: red;">*</span></td>
											<th>${vv:varView("vix_mdm_item")}分类：</th>
											<td><select id="catalog" name="catalog" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="类型1" selected="selected">类型1</option>
													<option value="类型2">类型2</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>行业领域：</th>
											<td><input id="industry" name="industry" value="${competingProducts.industry}" type="text" size="30" /></td>
											<th>主计量单位：</th>
											<td><input id="masterUnit" name="masterUnit" value="${competingProducts.masterUnit}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>销售税率：</th>
											<td><input id="saleTax" name="saleTax" value="${competingProducts.saleTax}" type="text" size="30" /></td>
											<th>生产国别：</th>
											<td><input id="productCountry" name="productCountry" value="${competingProducts.productCountry}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th align="right">产品描述：</th>
											<td colspan="3"><textarea id="description" name="description">${competingProducts.description }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
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
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>