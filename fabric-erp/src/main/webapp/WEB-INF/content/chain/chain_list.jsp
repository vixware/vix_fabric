<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" />供应链 </a></li>
				<li><a href="#">连锁经营管理 </a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="mail_left">
				<div class="addbox wf" style="border: 0;">
					<div class="addtitle" style="border: 1px solid #B4B4B4; border-bottom: 0;">
						<span class="l"><img src="${vix}/common/img/inv_inbound.png" width="16" height="16" style="vertical-align: middle" />&nbsp;连锁经营管理 </span>
					</div>
					<ul class="menu_list">
						<li><a href="#">1.快捷功能</a></li>
						<li><a href="#">2.模块</a></li>
						<li><a href="#">3.参数设置</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="right">
			<div class="right_content" style="border: 0;">
				<div class="addbox wf">
					<div class="addtitle">
						<span class="l"><img src="${vix}/common/img/inv_inbound.png" width="16" height="16" style="vertical-align: middle" />&nbsp;连锁经营管理 </span>
					</div>
					<div class="fn_table">
						<table>
							<tr>
								<td><a href="#" onclick="loadContent('${vix}/drp/distributionSystemRelationShipAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_02.png" /><span class="fn_table_num">5</span> </span>分销体系管理</a></td>
								<td><a href="#" onclick="loadContent('${vix}/drp/drpWarehouseAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /><span class="fn_table_num">5</span> </span>分销仓库管理</a></td>
								<td><a href="#" onclick="loadContent('${vix}/drp/salwableProductAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_03.png" /><span class="fn_table_num">5</span> </span>分销产品管理</a></td>
								<td><a href="#" onclick="loadContent('${vix}/drp/employeeManagementAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_01.png" /><span class="fn_table_num">5</span> </span>分销员工管理</a></td>
							</tr>
							<tr>
								<td><a href="#" onclick="loadContent('${vix}/drp/accountManagementAction!goList.action?source=drp','bg_02');"><span class="fn_table_btn"><img src="img/fn_04.png" /><span class="fn_table_num">5</span> </span>分销账号管理</a></td>
								<td><a href="#" onclick="loadContent('${vix}/drp/channelPriceAction!goList.action','bg_02');"><span class="fn_table_btn"><img src="img/fn_05.png" /><span class="fn_table_num">5</span> </span>分销价格管理</a></td>
							</tr>
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
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>