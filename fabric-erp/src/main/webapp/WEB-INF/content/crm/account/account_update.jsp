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
  function saveOrUpdateAccount(){
	$.post('${vix}/crm/accountAction!saveOrUpdate.action',
		{'account.id':$("#id").val(),
		  'account.code':$("#code").val(),
		  'account.name':$("#name").val()
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/crm/accountAction!goList.action');
		}
	 );
}
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
				<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />
					<s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="crm_customerrelationshipmanagement" /></a></li>
				<li><a href="#"><s:text name='crm_customermanagement' /></a></li>
				<a href="#"><s:text name='crm_crmlist' /></a>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${account.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateAccount();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/accountAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="account.id > 0">
							${account.code}
						</s:if> <s:else>
							新增客户列表
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">编码：</td>
											<td><input id="code" name="code" value="${account.code}" type="text" size="30" /></td>
											<td align="right">名称：</td>
											<td><input id="name" name="name" value="${account.name}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">英文名称：</td>
											<td><input id="englishName" name="englishName" value="${account.englishName}" type="text" size="30" /></td>
											<td align="right">简称：</td>
											<td><input id="shorterName" name="shorterName" value="${account.shorterName}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">检索词：</td>
											<td><input id="indexWord" name="indexWord" value="${account.indexWord}" type="text" size="30" /></td>
											<td align="right">商标：</td>
											<td><input id="trademark" name="trademark" value="${account.trademark}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">检索词：</td>
											<td><input id="isHotCustomer" name="isHotCustomer" value="${account.isHotCustomer}" type="text" size="30" /></td>
											<td align="right">商标：</td>
											<td><input id="trademark" name="trademark" value="${account.trademark}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">热点客户：</td>
											<td><input id="isHotCustomer" name="isHotCustomer" value="${account.isHotCustomer}" type="text" size="30" /></td>
											<td align="right">热点程度：</td>
											<td><input id="hotLevel" name="hotLevel" value="${account.hotLevel}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">价值评估：</td>
											<td><input id="valueAssessment" name="valueAssessment" value="${account.valueAssessment}" type="text" size="30" /></td>
											<td align="right">信用等级：</td>
											<td><input id="creditLevel" name="creditLevel" value="${account.creditLevel}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">客户种类：</td>
											<td><input id="classify" name="classify" value="${account.classify}" type="text" size="30" /></td>
											<td align="right">关系等级：</td>
											<td><input id="relationshipClass" name="relationshipClass" value="${account.relationshipClass}" type="text" size="30" /></td>
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
											<td align="right">账户类型：</td>
											<td><input id="accountType" name="accountType" value="${account.accountType}" type="text" size="30" />
											<td align="right">行业：</td>
											<td><input id="industry" name="industry" value="${account.industry}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">年收入：</td>
											<td><input id="annualRevenue" name="annualRevenue" value="${account.annualRevenue}" type="text" size="30" /></td>
											<td align="right">电话传真：</td>
											<td><input id="phoneFax" name="phoneFax" value="${account.phoneFax}" type="text" size="30" /></td>
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
<!-- content -->