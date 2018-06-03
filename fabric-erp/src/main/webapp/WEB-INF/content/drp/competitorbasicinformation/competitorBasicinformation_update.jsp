<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
	function saveOrUpdateCompetingChannelInfo (){
		if ($ ('#competingChannelInfoForm').validationEngine ('validate')){
			$.post ('${vix}/drp/competitorBasicinformationAction!saveOrUpdate.action' , {
			'competingChannelInfo.id' : $ ("#id").val () ,
			'competingChannelInfo.name' : $ ("#name").val () ,
			'competingChannelInfo.shortName' : $ ("#shortName").val () ,
			'competingChannelInfo.englishName' : $ ("#englishName").val () ,
			'competingChannelInfo.telephone' : $ ("#telephone").val () ,
			'competingChannelInfo.saleOrg' : $ ("#saleOrg").val () ,
			'competingChannelInfo.indetity' : $ ("#indetity").val () ,
			'competingChannelInfo.telefax' : $ ("#telefax").val () ,
			'competingChannelInfo.catalog' : $ ("#catalog").val () ,
			'competingChannelInfo.type' : $ ("#type").val () ,
			'competingChannelInfo.region' : $ ("#region").val () ,
			'competingChannelInfo.employeeCounts' : $ ("#employeeCounts").val () ,
			'competingChannelInfo.parentCompanyCode' : $ ("#parentCompanyCode").val () ,
			'competingChannelInfo.parentCompanyName' : $ ("#parentCompanyName").val () ,
			'competingChannelInfo.url' : $ ("#url").val () ,
			'competingChannelInfo.email' : $ ("#email").val () ,
			'competingChannelInfo.industry' : $ ("#industry").val () ,
			'competingChannelInfo.currency' : $ ("#currency").val () ,
			'competingChannelInfo.catalogs' : $ ("#catalogs").val () ,
			'competingChannelInfo.registeredCapital' : $ ("#registeredCapital").val () ,
			'competingChannelInfo.openingBank' : $ ("#openingBank").val () ,
			'competingChannelInfo.bankAccount' : $ ("#bankAccount").val () ,
			'competingChannelInfo.taxCode' : $ ("#taxCode").val () ,
			'competingChannelInfo.artificialPerson' : $ ("#artificialPerson").val () ,
			'competingChannelInfo.description' : editor.html () ,
			'competingChannelInfo.classes' : $ ("#classes").val ()
			} , function (result){
				showMessage (result);
				setTimeout ("" , 1000);
				loadContent ('${vix}/drp/competitorBasicinformationAction!goList.action');
			});
		}else{
			return false;
		}
	}
	$ ("#competingChannelInfoForm").validationEngine ();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_competitor.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_market_research_management" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/competitorBasicinformationAction!goList.action');">竞争基础信息 </a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="competingChannelInfoForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCompetingChannelInfo()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/drp/competitorBasicinformationAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b><s:text name="drp_competitor_information" /></b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<input type="hidden" id="id" name="competingChannelInfo.id" value="${competingChannelInfo.id}" />
									<table style="border: none;">
										<tr>
											<td align="right">编码：</td>
											<td><input id="name" name="name" value="${competingChannelInfo.name }" type="text" size="20" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<td align="right">主题：</td>
											<td><input id="shortName" name="shortName" value="${competingChannelInfo.shortName }" type="text" size="20" /></td>
										</tr>
										<tr>
											<td align="right">竞争者名称：</td>
											<td><input id="englishName" name="englishName" value="${competingChannelInfo.englishName }" type="text" size="30" /></td>
											<td align="right">调查类型：</td>
											<td><select id="type" name="type">
													<option value="">请选择</option>
													<option value="A级" selected="selected">A类</option>
													<option value="B级">B类</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">状态：</td>
											<td><select id="type" name="type">
													<option value="">请选择</option>
													<option value="A级" selected="selected">A类</option>
													<option value="B级">B类</option>
											</select></td>
											<td align="right">调查人：</td>
											<td><input id="registeredCapital" name="registeredCapital" value="${competingChannelInfo.registeredCapital }" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>