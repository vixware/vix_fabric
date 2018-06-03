<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
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

//-->
</script>
<input type="hidden" id="id" name="id" value="${saleChance.id}" />
<div class="content">
	<form id="saleChanceForm">
		<div class="order">
			<dl>
				<dt>
					<strong> <b> <s:if test="saleChance.id > 0">
								${saleChance.subject}
							</s:if> <s:else>
								新增销售机会
							</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">机会主题:</td>
								<td width="35%"><input id="subject" name="saleChance.subject" value="${saleChance.subject}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td width="10%" align="right">发现时间:</td>
								<td width="40%"><input id="findDate" name="saleChance.findDate" value="${saleChance.findDate}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('findDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
							</tr>
							<tr>
								<td align="right">来源:</td>
								<td><input id="source" name="saleChance.source" value="${saleChance.source}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">提供人:</td>
								<td><input id="sourcePerson" name="saleChance.sourcePerson" value="${saleChance.sourcePerson}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">客户需求:</td>
								<td><input id="requirement" name="saleChance.requirement" value="${saleChance.requirement}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">预计签单日期:</td>
								<td><input id="preOrderDate" name="saleChance.preOrderDate" value="${saleChance.preOrderDate}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('preOrderDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
							</tr>
							<tr>
								<td align="right">负责人:</td>
								<td><input id="charger" name="saleChance.charger" value="${saleChance.charger}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">预期金额:</td>
								<td><input id="expectedValue" name="saleChance.expectedValue" value="${saleChance.expectedValue}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <script type="text/javascript">
										$(function(){
											$('#expectedValue').priceFormat({
											    prefix: '',
											    centsLimit: 3
											});
										});
									</script></td>
							</tr>
							<tr>
								<td align="right">可能性(百分比):</td>
								<td><input id="possibility" name="saleChance.possibility" value="${saleChance.possibility}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">竞争对手可能性(百分比):</td>
								<td><input id="compaignProbability" name="saleChance.compaignProbability" value="${saleChance.compaignProbability}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">阶段停留 :</td>
								<td><input id="phaseStay" name="saleChance.phaseStay" value="${saleChance.phaseStay}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">阶段:</td>
								<td><input id="phase" name="saleChance.phase" value="${saleChance.phase}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">结算日期:</td>
								<td><input id="cleanDate" name="saleChance.cleanDate" value="${saleChance.cleanDate}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('cleanDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								<td align="right">输入日期 :</td>
								<td><input id="dateEntered" name="saleChance.dateEntered" value="${saleChance.dateEntered}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('dateEntered','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
							</tr>
							<tr>
								<td align="right">领导:</td>
								<td><input id="leadSource" name="saleChance.leadSource" value="${saleChance.leadSource}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">创建人:</td>
								<td><input id="createdBy" name="saleChance.createdBy" value="${saleChance.createdBy}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">金额:</td>
								<td><input id="amount" name="saleChance.amount" value="${saleChance.amount}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <script type="text/javascript">
										$(function(){
											$('#amount').priceFormat({
											    prefix: '',
											    centsLimit: 3
											});
										});
									</script></td>
								<td align="right">数量:</td>
								<td><input id="count" name="saleChance.count" value="${saleChance.count}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">货币类型:</td>
								<td><s:select id="currencyTypeId" headerKey="-1" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{saleChance.currencyType.id}" multiple="false" theme="simple"></s:select></td>
								<td align="right">日期关闭 :</td>
								<td><input id="dateClosed" name="saleChance.dateClosed" value="${saleChance.dateClosed}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('dateClosed','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
							</tr>
							<tr>
								<td align="right">下一步计划:</td>
								<td><input id="nextStepPlan" name="saleChance.nextStepPlan" value="${saleChance.nextStepPlan}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">销售阶段:</td>
								<td><s:select id="saleStageId" headerKey="-1" headerValue="--请选择--" list="%{saleStageList}" listValue="name" listKey="id" value="%{saleChance.saleStage.id}" multiple="false" theme="simple"></s:select></td>
							</tr>
							<tr>
								<td align="right">概率:</td>
								<td><input id="probability" name="saleChance.probability" value="${saleChance.probability}" type="text" class="validate[required] text-input" />% 范围(1-100)<span style="color: red;">*</span></td>
								<td align="right">预测结算日期:</td>
								<td><input id="precastCleanDate" name="saleChance.precastCleanDate" value="${saleChance.precastCleanDate}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('precastCleanDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
								</td>
							</tr>
							<tr>
								<td align="right">预测结束日期:</td>
								<td><input id="precastCloseDate" name="saleChance.precastCloseDate" value="${saleChance.precastCloseDate}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('precastCleanDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
								</td>
								<td align="right">预计费用:</td>
								<td><input id="estimatedCost" name="saleChance.estimatedCost" value="${saleChance.estimatedCost}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <script type="text/javascript">
										$(function(){
											$('#estimatedCost').priceFormat({
											    prefix: '',
											    centsLimit: 3
											});
										});
									</script></td>
							</tr>
							<tr>
								<td align="right">实际费用:</td>
								<td><input id="acturalCost" name="saleChance.acturalCost" value="${saleChance.acturalCost}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <script type="text/javascript">
										$(function(){
											$('#acturalCost').priceFormat({
											    prefix: '',
											    centsLimit: 3
											});
										});
									</script></td>
								<td align="right">潜在金额:</td>
								<td><input id="potentialAmount" name="saleChance.potentialAmount" value="${saleChance.potentialAmount}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <script type="text/javascript">
										$(function(){
											$('#potentialAmount').priceFormat({
											    prefix: '',
											    centsLimit: 3
											});
										});
									</script></td>
							</tr>
							<tr>
								<td align="right">加权金额:</td>
								<td><input id="weightedAmount" name="saleChance.weightedAmount" value="${saleChance.weightedAmount}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <script type="text/javascript">
										$(function(){
											$('#weightedAmount').priceFormat({
											    prefix: '',
											    centsLimit: 3
											});
										});
									</script></td>
								<td align="right">毛利率:</td>
								<td><input id="grossMargin" name="saleChance.grossMargin" value="${saleChance.grossMargin}" type="text" class="validate[required] text-input" />% 范围(1-100)<span style="color: red;">*</span></td>
							</tr>
							<tr>
								<td align="right">销售机会状态:</td>
								<td><s:select id="saleChanceStatusId" headerKey="-1" headerValue="--请选择--" list="%{saleChanceStatusList}" listValue="name" listKey="id" value="%{saleChance.saleChanceStatus.id}" multiple="false" theme="simple"></s:select></td>
								<td align="right">毛利总计:</td>
								<td><input id="grossTotalMargin" name="saleChance.grossTotalMargin" value="${saleChance.grossTotalMargin}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <script type="text/javascript">
										$(function(){
											$('#grossTotalMargin').priceFormat({
											    prefix: '',
											    centsLimit: 3
											});
										});
									</script></td>
							</tr>
							<tr>
								<td align="right">类型:</td>
								<td><input id="type" name="saleChance.type" value="${saleChance.type}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
								<td align="right">备注:</td>
								<td><input id="memo" name="saleChance.memo" value="${saleChance.memo}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
