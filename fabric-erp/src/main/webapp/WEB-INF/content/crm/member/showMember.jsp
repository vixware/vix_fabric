<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript">
<!--
	$(".addtable .addtable_t").click(function(){
		$(this).toggleClass("addtable_td");
		$(this).next(".addtable_c").toggle();
	});
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />供应链</a></li>
				<li><a href="#">会员交互管理</a></li>
				<li><a href="#">会员管理</a></li>
				<li><a href="#">忠诚度管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/member/customerBlackListAction!goList.action?source=member');">黑名单管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="order">
		<dl>
			<dt>
				<span class="no_line"> <a href="#" onclick="loadContent('${vix}/crm/member/customerBlackListAction!goList.action?source=member');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
				</span> <strong> <b> ${customerAccount.name} </b> <i></i>
				</strong>
			</dt>
			<dd class="clearfix">
				<div class="order_table">
					<div class="voucher newvoucher">
						<dl>
							<dt>
								<b></b> <strong>基本信息</strong>
							</dt>
							<dd style="display: block;">
								<table class="addtable_c">
									<tr>
										<td align="right" width="15%">客户分类：</td>
										<td colspan="3" width="85%">${customerAccount.customerAccountCategory.name}</td>
									</tr>
									<tr>
										<td align="right" width="15%">姓名：</td>
										<td width="35%">${customerAccount.name}</td>
										<td align="right" width="10%">手机：</td>
										<td width="40%">${contactPerson.directPhone}</td>
									</tr>
									<tr>
										<td align="right">标识：</td>
										<td>${contactPerson.tag}</td>
										<td align="right">标题：</td>
										<td>${contactPerson.title}</td>
									</tr>
									<tr>
										<td align="right">外部编号：</td>
										<td>${contactPerson.outNumber}</td>
										<td align="right">积分密码：</td>
										<td></td>
									</tr>
									<tr>
										<td align="right">热点客户:</td>
										<td><s:if test="customerAccount.isHotCustomer == 0">否</s:if> <s:elseif test="customerAccount.isHotCustomer == 1">是</s:elseif></td>
										<td align="right">热点程度:</td>
										<td>${customerAccount.hotLevel.name}</td>
									</tr>
									<tr>
										<td align="right">性别：</td>
										<td><s:if test="contactPerson.sex == 1">男</s:if> <s:elseif test="contactPerson.sex == 0">女</s:elseif></td>
										<td align="right">客户种类：</td>
										<td>${customerAccount.customerType.name}</td>
									</tr>
									<tr>
										<td align="right">阶段：</td>
										<td>${customerAccount.customerStage.name}</td>
										<td align="right">生日：</td>
										<td>${contactPerson.birthday}</td>
									</tr>
									<tr>
										<td align="right">等级标识：</td>
										<td>${customerAccount.levelId}</td>
										<td align="right">积分历史：</td>
										<td>${customerAccount.pointHistory}</td>
									</tr>
									<tr>
										<td align="right">冻结积分：</td>
										<td>${customerAccount.levelId}</td>
										<td align="right">可用积分：</td>
										<td>${customerAccount.point}</td>
									</tr>
									<tr>
										<td align="right">是否已婚：</td>
										<td><s:if test="contactPerson.isMarriage == 1">是</s:if> <s:if test="contactPerson.isMarriage == 0">否</s:if></td>
										<td align="right">是否允许联系：</td>
										<td><s:if test="contactPerson.isAllowConnect == 1">是</s:if> <s:if test="contactPerson.isAllowConnect == 0">否</s:if></td>
									</tr>
									<tr>
										<td align="right">教育程度：</td>
										<td>${contactPerson.education}</td>
										<td align="right">收入水平：</td>
										<td>${contactPerson.incomeLevel}</td>
									</tr>
									<tr>
										<td align="right">客户身份：</td>
										<td colspan="3">${contactPerson.identity}</td>
									</tr>
									<tr>
										<td colspan="4">联系方式</td>
									</tr>
									<tr>
										<td align="right">邮箱：</td>
										<td>${contactPerson.email}</td>
										<td align="right">工作电话：</td>
										<td>${contactPerson.mobile}</td>
									</tr>
									<tr>
										<td align="right">QQ：</td>
										<td>${contactPerson.qqAccount}</td>
										<td align="right">MSN：</td>
										<td>${contactPerson.msnAccount}</td>
									</tr>
									<tr>
										<td align="right">淘宝旺旺：</td>
										<td>${contactPerson.wangAccount}</td>
										<td align="right">SKYPE：</td>
										<td>${contactPerson.skypeAccount}</td>
									</tr>
									<tr>
										<td colspan="4">商务信息</td>
									</tr>
									<tr>
										<td align="right">来源：</td>
										<td>${customerAccount.customerSource.name}</td>
										<td align="right">关系等级：</td>
										<td>${customerAccount.relationshipClass.name}</td>
									</tr>
									<tr>
										<td align="right">价值评估：</td>
										<td><s:if test="customerAccount.valueAssessment == 1">高</s:if> <s:elseif test="customerAccount.valueAssessment == 2">中</s:elseif> <s:elseif test="customerAccount.valueAssessment == 3">低</s:elseif></td>
										<td align="right">信用等级：</td>
										<td><s:if test="customerAccount.creditLevel == 1">高</s:if> <s:elseif test="customerAccount.creditLevel == 2">中</s:elseif> <s:elseif test="customerAccount.creditLevel == 3">低</s:elseif></td>
									</tr>
									<tr>
										<td align="right">联系人类型：</td>
										<td colspan="3">${contactPerson.contactPersonType.name}</td>
									</tr>
									<tr>
										<td align="right">证件类型：</td>
										<td>${contactPerson.credentialType.name}</td>
										<td align="right">证件号码：</td>
										<td>${contactPerson.credentialCode}</td>
									</tr>
									<tr>
										<td align="right">备注：</td>
										<td colspan="3">${customerAccount.memo}</td>
									</tr>
								</table>
							</dd>
						</dl>
					</div>
				</div>
			</dd>
		</dl>
	</div>
</div>
