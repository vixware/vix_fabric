<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" />供应链 </a></li>
				<li><a href="#">客户关系管理 </a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box">
		<div id="por_left" class="np_l_l mail_left">
			<div class="addtitle " style="border: 1px solid #B4B4B4; border-bottom: 0;">
				<span class="l"><img src="${vix}/common/img/inv_inbound.png" width="16" height="16" style="vertical-align: middle" />&nbsp;客户管理</span>
			</div>
			<ul class="datelist">
				<li id="crmS_btn1" class="clearfix on_lnpbtn" onclick="javascript:tabBig(4,1,'crmS','crmS_btn','on_lnpbtn')"><img src="img/system_basic_information.png" /> 快捷功能</li>
				<li id="crmS_btn2" class="clearfix" onclick="javascript:tabBig(4,2,'crmS','crmS_btn','on_lnpbtn')"><img src="img/personal_Settings_interface_settings.png" /> 模块</li>
				<li id="crmS_btn3" class="clearfix" onclick="javascript:tabBig(4,3,'crmS','crmS_btn','on_lnpbtn')"><img src="img/personal_accounts_and_security_settings.png" /> 参数设置</li>
			</ul>
		</div>
		<div id="por_right" class="np_l_r clearfix" style="position: relative;">
			<div id="crmS1">
				<div class="addbox wf">
					<div class="addtitle">
						<span class="l"><img src="${vix}/common/img/inv_inbound.png" width="16" height="16" style="vertical-align: middle" />&nbsp;快捷功能</span>
					</div>
					<div class="fn_table">
						<table>
							<tr>
								<td><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_02.png" /><span class="fn_table_num">5</span> </span>客户整合管理</a></td>
								<td><a href="#" onclick="loadContent('${vix}/crm/business/membershipSubdivisionAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_03.png" /><span class="fn_table_num">5</span> </span>客户细分管理</a></td>
								<td><a href="#" onclick="loadContent('${vix}/crm/customerTransactionalAction!goCustomerTransactional.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_01.png" /><span class="fn_table_num">5</span> </span>客户往来数据管理</a></td>
							</tr>
							<%-- 
							
							<tr>
								<td><a href="#" onclick="loadContent('${vix}/crm/business/purchasingBehaviorAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_04.png" /><span class="fn_table_num">5</span> </span>购买行为分析</a></td>
								<td><a href="#" onclick="loadContent('${vix}/crm/analyse/rfmAnalyseAction!showAnalyseData.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /><span class="fn_table_num">5</span> </span>RFM模型分析</a></td>
							</tr> 
								
								--%>
						</table>
					</div>
				</div>
				<div class="addbox wf" style="border: 0;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(3,1,'fn',event)"><img src="img/mail.png" alt="" />常见问题</a></li>
							<li><a onclick="javascript:tab(3,2,'fn',event)"><img src="img/mail.png" alt="" />视频演示</a></li>
						</ul>
					</div>
					<div id="fn1" class="fn_cont">
						<ul class="ullist">暂无
						</ul>
					</div>
					<div id="fn2" class="fn_cont" style="display: none;">
						<ul class="ullist">暂无
						</ul>
					</div>
				</div>
			</div>
			<div id="crmS2" style="display: none;">
				<div class="addbox wf">
					<div class="addtitle">
						<span class="l"><img src="${vix}/common/img/inv_inbound.png" width="16" height="16" style="vertical-align: middle" />&nbsp;模块</span>
					</div>
					<div class="fn_table">
						<table>
							<tr>
								<td><a href="#"><span class="fn_table_btn"><span class="fn_table_num">3</span> </span></a></td>
								<td><a href="#"><span class="fn_table_btn"><span class="fn_table_num"></span> </span></a></td>
								<td><a href="#"><span class="fn_table_btn"><span class="fn_table_num"></span> </span></a></td>
								<td><a href="#"><span class="fn_table_btn"><span class="fn_table_num"></span> </span></a></td>
							</tr>
							<tr>
								<td><a href="#"><span class="fn_table_btn"><span class="fn_table_num"></span> </span></a></td>
								<td><a href="#"><span class="fn_table_btn"><span class="fn_table_num"></span> </span></a></td>
							</tr>
							<%-- 
							
							<tr>
								<td><a href="#" onclick="loadContent('${vix}/crm/business/membershipIntegrationAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_02.png" /><span class="fn_table_num">5</span> </span>会员整合管理</a></td>
								<td><a href="#" onclick="loadContent('${vix}/crm/business/memberShipAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /><span class="fn_table_num">5</span> </span>会员管理</a></td>
								<td><a href="#" onclick="loadContent('${vix}/crm/business/membershipSubdivisionAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_03.png" /><span class="fn_table_num">5</span> </span>会员细分管理</a></td>
								<td><a href="#" onclick="loadContent('${vix}/crm/business/couponAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_01.png" /><span class="fn_table_num">5</span> </span>优惠券管理</a></td>
							</tr>
							<tr>
								<td><a href="#" onclick="loadContent('${vix}/crm/business/purchasingBehaviorAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_04.png" /><span class="fn_table_num">5</span> </span>购买行为分析</a></td>
								<td><a href="#" onclick="loadContent('${vix}/crm/analyse/rfmAnalyseAction!showAnalyseData.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /><span class="fn_table_num">5</span> </span>RFM模型分析</a></td>
							</tr> 
							
								--%>
						</table>
					</div>
				</div>
			</div>
			<div id="crmS3" style="display: none;">
				<div class="addbox wf">
					<div class="addtitle">
						<span class="l"><img src="${vix}/common/img/inv_inbound.png" width="16" height="16" style="vertical-align: middle" />&nbsp;参数设置</span>
					</div>
					<div id="crmS1">
						<div class="np_clist" id="np1">
							<div class="nt">
								<div class="nt_title">参数设置</div>
								<div class="nt_line"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>