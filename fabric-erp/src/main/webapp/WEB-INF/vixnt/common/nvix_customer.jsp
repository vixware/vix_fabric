<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li><a id="" href="#" onclick="loadContent('mid_member','${nvix}/nvixnt/vixntMemberManageDataAction!goMember.action');"><i class="fa fa-lg fa-fw fa-user"></i><span class="menu-item-parent">会员管理</span></a>
	<ul>
		<li><a id="" href="#"></i><i class="fa fa-cogs"></i>基础设置</a>
			<ul>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntMemberLevelManagementAction!goList.action');"></i><i class="fa fa-fw icon-iconfont-ecCustomerLevel"></i>等级管理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixCustomerTagAction!goList.action');"></i><i class="fa fa-fw icon-iconfont-ecCustomerTag"></i>标签管理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntIntegralRulesSetAction!goList.action');"></i><i class="fa fa-fw icon-iconfont-integralRule"></i>积分规则设置</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/vixntActivityAction!goList.action');"></i><i class="fa fa-fw icon-iconfont-integralRule"></i>积分活动管理</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/storedValueRuleSetAction!goList.action');"></i><i class="fa fa-fw icon-iconfont-integralRule"></i>存值规则设置</a></li>
				<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/cardAction!goList.action');"></i><i class="fa fa-credit-card"></i>会员卡定义</a></li>
			</ul></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixCustomerAccountAction!goList.action');"><i class="fa fa-fw icon-iconfont-ecCustomer"></i>会员列表</a></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixCustomerAccountClipAction!goList.action');"><i class="fa fa-credit-card"></i>会员卡管理</a></li>
	</ul></li>
<li><a href="#"><i class="fa fa-lg fa-fw fa-dollar"></i><span class="menu-item-parent"></i>会员消费管理</span></a>
	<ul>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixExpenseRecordAction!goList.action');"></i><i class="fa fa-paypal"></i>消费记录</a></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/integralManagementsubsidiaryAction!goList.action');"></i><i class="fa fa-cog"></i>积分记录</a></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixIntegraChangeHistory!goList.action');"></i><i class="fa fa-cog"></i>兑换历史</a></li>
		<li><a href="#" id="" onclick="loadContent('','${nvix}/nvixnt/nvixCustomerSalesReturnAction!goList.action');"></i><i class="fa fa-cog"></i>会员退货</a></li>
	</ul></li>