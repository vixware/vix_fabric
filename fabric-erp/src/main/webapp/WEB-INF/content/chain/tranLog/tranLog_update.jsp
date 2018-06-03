<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
	function saveOrUpdateChannelDistributor (){
		if ($ ('#channelDistributorForm').validationEngine ('validate')){
			$.post ('${vix}/drp/storesIntegralAction!saveOrUpdate.action' , {
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
				loadContent ('${vix}/drp/storesIntegralAction!goList.action');
			});
		}else{
			return false;
		}
	}
	$ ("#channelDistributorForm").validationEngine ();
	function chooseParentOrganization (){
		$.ajax ({
		url : '${vix}/drp/storesIntegralAction!goChooseOrganization.action' ,
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
				<li><a href="#" onclick="loadContent('${vix}/drp/storesIntegralAction!goList.action');">门店积分管理 </a></li>
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
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/drp/storesIntegralAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增</b></strong>
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
											<td align="right">编码：</td>
											<td><input id="name" name="name" value="${channelDistributor.name }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">主题：</td>
											<td><input id="shortName" name="shortName" value="${channelDistributor.shortName }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">联系人：</td>
											<td><input id="code" name="code" value="${channelDistributor.code }" type="text" size="35" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">电话：</td>
											<td><input id="telephone" name="telephone" value="${channelDistributor.telephone }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">积分：</td>
											<td><input id="email" name="email" value="${channelDistributor.email }" type="text" size="30" /></td>
											<td align="right">状态：</td>
											<td><select id="catalog" name="catalog" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="启用" selected="selected">启用</option>
													<option value="禁用">禁用</option>
											</select></td>
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