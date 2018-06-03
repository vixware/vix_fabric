<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
 
$("#saleChanceForm").validationEngine();
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
function saveOrUpdateSaleChance(){
	if($('#saleChanceForm').validationEngine('validate')){
		$.post('${vix}/crm/salechance/saleChanceAction!saveOrUpdate.action',
			{'saleChance.id':$("#id").val(),
			  'saleChance.subject':$("#subject").val(),
			  'saleChance.customerName':$("#customerName").val(),
			  'saleChance.customerAccount.id':$("#customerAccountId").val(),
			  'saleChance.contactPerson.id':$("#contactPersonId").val(),
			  'saleChance.saleType.id':$("#saleTypeId").val(),
			  'saleChance.findDate':$("#findDate").val(),
			  'saleChance.source':$("#source").val(),
			  'saleChance.sourcePerson':$("#sourcePerson").val(),
			  'saleChance.requirement':$("#requirement").val(),
			  'saleChance.preOrderDate':$("#preOrderDate").val(),
			  'saleChance.charger':$("#charger").val(),
			  'saleChance.expectedValue':$("#expectedValue").val(),
			  'saleChance.possibility':$("#possibility").val(),
			  'saleChance.compaignProbability':$("#compaignProbability").val(),
			  'saleChance.phaseStay':$("#phaseStay").val(),
			  'saleChance.phase':$("#phase").val(),
			  'saleChance.cleanDate':$("#cleanDate").val(),
			  'saleChance.dateEntered':$("#dateEntered").val(),
			  'saleChance.findOutTime':$("#findOutTime").val(),
			  'saleChance.leadSource':$("#leadSource").val(),
			  'saleChance.createdBy':$("#createdBy").val(),
			  'saleChance.amount':$("#amount").val(),
			  'saleChance.count':$("#count").val(),
			  'saleChance.currencyType.id':$("#currencyTypeId").val(),
			  'saleChance.dateClosed':$("#dateClosed").val(),
			  'saleChance.nextStepPlan':$("#nextStepPlan").val(),
			  'saleChance.saleStage.id':$("#saleStageId").val(),
			  'saleChance.probability':$("#probability").val(),
			  'saleChance.precastCleanDate':$("#precastCleanDate").val(),
			  'saleChance.precastCloseDate':$("#precastCloseDate").val(),
			  'saleChance.estimatedCost':$("#estimatedCost").val(),
			  'saleChance.acturalCost':$("#acturalCost").val(),
			  'saleChance.potentialAmount':$("#potentialAmount").val(),
			  'saleChance.weightedAmount':$("#weightedAmount").val(),
			  'saleChance.grossMargin':$("#grossMargin").val(),
			  'saleChance.saleChanceStatus.id':$("#saleChanceStatusId").val(),
			  'saleChance.grossTotalMargin':$("#grossTotalMargin").val(),
			  'saleChance.crmProject.id':$("#crmProjectId").val(),
			  'saleChance.isDeleted':$("#isDeleted").val(),
			  'saleChance.memo':$("#memo").val()
			},
			function(result){
				showMessage(result);
				loadContent('${vix}/crm/salechance/saleChanceAction!goList.action');
			}
		);
	}
}

function chooseCustomerAccount(){
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!goChooseCustomerAccount.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#customerAccountId").val(result[0]);
								$("#customerName").val(result[1]);
								$("#customerCode").val(result[2]);
								loadContactPerson();
							}else{
								$("#customerAccountId").val("");
								$("#customerName").val("");
								$("#customerCode").val("");
								asyncbox.success("<s:text name='pleaseChooseCustomerAccount'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function loadContactPerson(){
	$.ajax({
		  url:"${vix}/sales/order/salesOrderAction!loadCustomerContactPerson.action?id="+$("#id").val()+"&customerAccountId="+$("#customerAccountId").val(),
		  cache: false,
		  success: function(html){
			  $("#contactPersonId").html(html);
		  }
	});
}
loadContactPerson();
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
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
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/crm/saleChance.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/salechance/saleChanceAction!goList.action');">销售机会</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${saleChance.id}" />
<input type="hidden" id="isDeleted" name="isDeleted" value="${saleChance.isDeleted}" />
<input type="hidden" id="crmProjectId" name="crmProjectId" value="${saleChance.crmProject.id}" />
<div class="content">
	<form id="saleChanceForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateSaleChance();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/salechance/saleChanceAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="saleChance.id > 0">
							${saleChance.subject}
						</s:if> <s:else>
							新增机会
						</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>机会信息</strong>
								</dt>
								<dd style="display: block;">
									<table class="addtable_c">
										<tr>
											<td width="15%" align="right">机会主题:</td>
											<td width="35%"><input id="subject" name="saleChance.subject" value="${saleChance.subject}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td width="10%" align="right">销售机会类型:</td>
											<td><s:select id="saleTypeId" headerKey="" headerValue="--请选择--" list="%{saleTypeList}" listValue="name" listKey="id" value="%{saleChance.saleType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">客户名称:</td>
											<td><input id="customerName" name="saleChance.customerAccount.name" value="${saleChance.customerAccount.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId" value="${saleChance.customerAccount.id}" /> <a
												class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></td>
											<td align="right">联系人:</td>
											<td><select id="contactPersonId"></select></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>基本情况</strong>
								</dt>
								<dd style="display: block;">
									<table class="addtable_c">
										<tr>
											<td width="15%" align="right">发现时间:</td>
											<td width="35%"><input id="findDate" name="saleChance.findDate" value="<s:property value='formatDateToString(saleChance.findDate)'/>" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('findDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
											<td width="10%" align="right">来源:</td>
											<td width="40%"><input id="source" name="saleChance.source" value="${saleChance.source}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">负责人:</td>
											<td><input id="charger" name="saleChance.charger" value="${saleChance.charger}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">提供人:</td>
											<td><input id="sourcePerson" name="saleChance.sourcePerson" value="${saleChance.sourcePerson}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">客户需求:</td>
											<td><input id="requirement" name="saleChance.requirement" value="${saleChance.requirement}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">输入日期 :</td>
											<td><input id="dateEntered" name="saleChance.dateEntered" value="${saleChance.dateEntered}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('dateEntered','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">领导:</td>
											<td><input id="leadSource" name="saleChance.leadSource" value="${saleChance.leadSource}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">创建人:</td>
											<td><input id="createdBy" name="saleChance.createdBy" value="${saleChance.createdBy}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">销售机会状态:</td>
											<td><s:select id="saleChanceStatusId" headerKey="" headerValue="--请选择--" list="%{saleChanceStatusList}" listValue="name" listKey="id" value="%{saleChance.saleChanceStatus.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">备注:</td>
											<td><input id="memo" name="saleChance.memo" value="${saleChance.memo}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>预期</strong>
								</dt>
								<dd style="display: block;">
									<table class="addtable_c">
										<tr>
											<td width="15%" align="right">预计签单日期:</td>
											<td width="35%"><input id="preOrderDate" name="saleChance.preOrderDate" value="${saleChance.preOrderDate}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('preOrderDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td width="10%" align="right">预期金额:</td>
											<td width="40%"><input id="expectedValue" name="saleChance.expectedValue" value="${saleChance.expectedValue}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <script type="text/javascript">
												$(function(){
													$('#expectedValue').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>当前状态</strong>
								</dt>
								<dd style="display: block;">
									<table class="addtable_c">
										<tr>
											<td width="15%" align="right">阶段停留 :</td>
											<td width="35%"><input id="phaseStay" name="saleChance.phaseStay" value="${saleChance.phaseStay}" type="text" size="30" /></td>
											<td width="10%" align="right">阶段:</td>
											<td width="40%"><input id="phase" name="saleChance.phase" value="${saleChance.phase}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">可能性(百分比):</td>
											<td><input id="possibility" name="saleChance.possibility" value="${saleChance.possibility}" type="text" size="30" /> <script type="text/javascript">
												$(function(){
													$('#possibility').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
											<td align="right">竞争对手可能性(百分比):</td>
											<td><input id="compaignProbability" name="saleChance.compaignProbability" value="${saleChance.compaignProbability}" type="text" size="30" /> <script type="text/javascript">
												$(function(){
													$('#compaignProbability').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
										</tr>
										<tr>
											<td align="right">货币类型:</td>
											<td><s:select id="currencyTypeId" headerKey="" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{saleChance.currencyType.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">日期关闭 :</td>
											<td><input id="dateClosed" name="saleChance.dateClosed" value="${saleChance.dateClosed}" type="text" size="30" /> <img onclick="showTime('dateClosed','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">下一步计划:</td>
											<td><input id="nextStepPlan" name="saleChance.nextStepPlan" value="${saleChance.nextStepPlan}" type="text" size="30" /></td>
											<td align="right">销售阶段:</td>
											<td><s:select id="saleStageId" headerKey="" headerValue="--请选择--" list="%{saleStageList}" listValue="name" listKey="id" value="%{saleChance.saleStage.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">概率:</td>
											<td><input id="probability" name="saleChance.probability" value="${saleChance.probability}" type="text" size="30" />% 范围(1-100) <script type="text/javascript">
												$(function(){
													$('#probability').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
											<td align="right">预测结算日期:</td>
											<td><input id="precastCleanDate" name="saleChance.precastCleanDate" value="${saleChance.precastCleanDate}" type="text" size="30" /> <img onclick="showTime('precastCleanDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">预测结束日期:</td>
											<td><input id="precastCloseDate" name="saleChance.precastCloseDate" value="${saleChance.precastCloseDate}" type="text" size="30" /> <img onclick="showTime('precastCloseDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">预计费用:</td>
											<td><input id="estimatedCost" name="saleChance.estimatedCost" value="${saleChance.estimatedCost}" type="text" size="30" /> <script type="text/javascript">
												$(function(){
													$('#estimatedCost').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>费用信息</strong>
								</dt>
								<dd style="display: block;">
									<table class="addtable_c">
										<tr>
											<td width="15%" align="right">结算日期:</td>
											<td width="35%"><input id="cleanDate" name="saleChance.cleanDate" value="${saleChance.cleanDate}" type="text" size="30" /> <img onclick="showTime('cleanDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td width="10%" align="right">毛利总计:</td>
											<td width="40%"><input id="grossTotalMargin" name="saleChance.grossTotalMargin" value="${saleChance.grossTotalMargin}" type="text" size="30" /> <script type="text/javascript">
												$(function(){
													$('#grossTotalMargin').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
										</tr>
										<tr>
											<td align="right">金额:</td>
											<td><input id="amount" name="saleChance.amount" value="${saleChance.amount}" type="text" size="30" /> <script type="text/javascript">
												$(function(){
													$('#amount').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
											<td align="right">数量:</td>
											<td><input id="count" name="saleChance.count" value="${saleChance.count}" type="text" size="30" /> <script type="text/javascript">
												$(function(){
													$('#count').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
										</tr>

										<tr>
											<td align="right">实际费用:</td>
											<td><input id="acturalCost" name="saleChance.acturalCost" value="${saleChance.acturalCost}" type="text" size="30" /> <script type="text/javascript">
												$(function(){
													$('#acturalCost').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
											<td align="right">潜在金额:</td>
											<td><input id="potentialAmount" name="saleChance.potentialAmount" value="${saleChance.potentialAmount}" type="text" size="30" /> <script type="text/javascript">
												$(function(){
													$('#potentialAmount').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
										</tr>
										<tr>
											<td align="right">加权金额:</td>
											<td><input id="weightedAmount" name="saleChance.weightedAmount" value="${saleChance.weightedAmount}" type="text" size="30" /> <script type="text/javascript">
												$(function(){
													$('#weightedAmount').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
												});
											</script></td>
											<td align="right">毛利率:</td>
											<td><input id="grossMargin" name="saleChance.grossMargin" value="${saleChance.grossMargin}" type="text" size="30" />% 范围(1-100) <script type="text/javascript">
												$(function(){
													$('#grossMargin').priceFormat({
													    prefix: '',
													    centsLimit: 1
													});
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
	</form>
</div>
<!-- content -->
