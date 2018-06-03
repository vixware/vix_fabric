<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />Print</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />Help</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<s:if test="source =='mdm' ">
					<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />主数据管理</a></li>
					<li><a href="#">主数据维护</a></li>
					<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=${source}');">客户</a></li>
				</s:if>
				<s:else>
					<li><a href="#"><img src="${vix}/common/img/crm/customer.png" alt="" />供应链</a></li>
					<li><a href="#">客户关系管理</a></li>
					<li><a href="#" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=${source}');">客户</a></li>
				</s:else>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="npcontent clearfix">
		<div class="np_right">
			<div class="np_right_box">
				<div class="nprb_title">
					相关摘要(自动合计) <a title="注意：若有退货发生时，相应的产品数量/金额等是由发货明细/交付记录-退货明细自动计算得出。" style="cursor: help;"><img src="img/infild.png" width="16" height="16" style="vertical-align: middle;" />提示</a>
				</div>
				<div class="ld">
					<table border="0" width="100%;">
						<tr>
							<td>合同：</td>
							<td align="right">￥0.00</td>
						</tr>
						<tr>
							<td>回款计划：</td>
							<td align="right">￥0.00</td>
						</tr>
						<tr>
							<td>已回款：</td>
							<td align="right">￥0.00</td>
						</tr>
						<tr>
							<td>已发货/交付产品：</td>
							<td align="right">￥0.00</td>
						</tr>
						<tr>
							<td>已开票金额：</td>
							<td align="right">￥0.00</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="np_right_box">
				<div class="nprb_title">往来对账</div>
				<p align="right">
					<a href="#" title="交付/回款 月度对帐">交付/回款 月度对帐>></a>
				</p>
			</div>
			<div class="np_right_box">
				<div class="nprb_title">
					费用<a href="#">(新建/明细/审批)</a>
				</div>
				<div class="ld">
					<table border="0" width="100%;">
						<tr>
							<td>已审批费用：</td>
							<td align="right">￥0.00</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="np_left clearfix" id="common_table">
			<div class="np_left_title">
				<h2 style="float: none;">客户信息</h2>
				<div>
					<p>
						<a href="###" class="abtn"><span>编辑</span></a> <a href="###" class="abtn"><span>删除</span></a> <a href="###" class="abtn" onclick="loadContent('${vix}/mdm/crm/customerAccountAction!goList.action?source=${source}');"><span>返回</span></a>
					</p>
					数据所有人：boss <a href="#"><img src="img/address_book.png" width="16" height="16" />共享</a> <a href="#"><img src="img/address_book.png" width="16" height="16" />转移</a> <a href="#"><img src="img/address_book.png" width="16" height="16" />数据日志</a>
				</div>
			</div>
			<s:hidden id="id" name="customerAccount.id" value="%{customerAccount.id}" theme="simple" />
			<div class="task_list">
				<table width="100%">
					<tr>
						<th colspan="4">客户名称：<b>${customerAccount.name}</b></th>
					</tr>
					<tr>
						<td class="ar">热点客户：</td>
						<td><s:if test="customerAccount.isHotCustomer == '1'">是</s:if> <s:else>否</s:else></td>
						<td class="ar">热度：</td>
						<td>${customerAccount.hotLevel.name}</td>
					</tr>
					<tr>
						<td class="ar">客户种类：</td>
						<td>${customerAccount.customerType.name }</td>
						<td>行业：</td>
						<td>${customerAccount.industry.name }</td>
					</tr>
					<tr>
						<td colspan="4"><strong>基本信息</strong></td>
					</tr>
					<tr>
						<td>助记简称：</td>
						<td>${customerAccount.shorterName }</td>
						<td>价值评估：</td>
						<td><s:if test="customerAccount.valueAssessment == 1">高</s:if> <s:elseif test="customerAccount.valueAssessment == 2">中</s:elseif> <s:elseif test="customerAccount.valueAssessment == 3">低</s:elseif> <s:else>中</s:else></td>
					</tr>
					<tr>
						<td>编码：</td>
						<td>${customerAccount.code}</td>
						<td>信用等级：</td>
						<td><s:if test="customerAccount.valueAssessment == 1">高</s:if> <s:elseif test="customerAccount.valueAssessment == 2">中</s:elseif> <s:elseif test="customerAccount.valueAssessment == 3">低</s:elseif> <s:else>中</s:else></td>
					</tr>
					<tr>
						<td>关系等级：</td>
						<td>${customerAccount.relationshipClass.name }</td>
						<td>人员规模：</td>
						<td>${customerAccount.staffScale.name }</td>
					</tr>
					<tr>
						<td>来源：</td>
						<td>${customerAccount.customerSource.name }</td>
						<td>阶段：</td>
						<td>${customerAccount.customerStage.name }</td>
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
						<td>国家或地区：</td>
						<td colspan="3">${customerAccount.shippingAddressCountry}</td>
					</tr>
					<tr>
						<td>邮编：</td>
						<td>${customerAccount.shippingAddressPostalcode}</td>
						<td>电话：</td>
						<td>${customerAccount.phoneOffice}</td>
					</tr>
					<tr>
						<td>传真：</td>
						<td>${customerAccount.phoneFax}</td>
						<td>网址：</td>
						<td><a href="${customerAccount.website}" target="_blank">${customerAccount.website}</a></td>
					</tr>
					<tr>
						<td colspan="4"><strong>备注</strong></td>
					</tr>
					<tr>
						<td>备注：</td>
						<td colspan="3">${customerAccount.memo}</td>
					</tr>
					<tr>
						<td>创建日期：</td>
						<td><s:property value="formatDateToString(customerAccount.createTime)" /></td>
						<td>更新日期：</td>
						<td><s:property value="formatDateToString(customerAccount.updateTime)" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="atable">
			<div class="task_list">
				<table width="100%" class="newtab">
					<tr>
						<th colspan="3" style="text-align: center">客户跟踪</th>
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td class="np_border">
							<table width="100%">
								<tbody>
									<script type="text/javascript">
		                    			function saveOrUpdateSchedule(id){
		                    				$.ajax({
		                    					  url:'${vix}/crm/customer/customerAccountScheduleAction!goSaveOrUpdate.action?id='+id,cache: false,
		                    					  success: function(html){
		                    						  asyncbox.open({
		                    							 	modal:true,width : 680,height : 360,title:"日程",html:html,
		                    								callback : function(action){
		                    									if(action == 'ok'){
		                    										if($('#scheduleForm').validationEngine('validate')){
		                    											$.post('${vix}/crm/customer/customerAccountScheduleAction!saveOrUpdate.action',
		                    												 {'schedule.id':$("#scheduleId").val(),
		                    												  'schedule.subject':$("#subject").val(),
		                    												  'schedule.customerAccount.id':$("#id").val(),
		                    												  'schedule.time':$("#time").val(),
		                    												  'schedule.content':$("#content").val()
		                    												},
		                    												function(result){
		                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
		                    														loadSchedule();
		                    													});
		                    												}
		                    											 ); 
		                    						  				}else{
		                    						  					return false;
		                    						  				}
		                    									}
		                    								},
		                    								btnsbar : $.btn.OKCANCEL
		                    							});
		                    					  }
		                    				});
		                    			}
		                    			function loadSchedule(){
		                    				$.ajax({
		                    					url:'${vix}/crm/customer/customerAccountScheduleAction!goListContent.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#schedule").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadSchedule();
		                    		</script>
									<tr class="alt">
										<th>日程</th>
										<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateSchedule(0);"><span>新建</span></a></th>
									</tr>
									<tr class="alt">
										<td id="schedule" colspan="2"></td>
									</tr>
								</tbody>
							</table>
						</td>
						<td></td>
						<td class="np_border">
							<table width="100%">
								<tbody>
									<script type="text/javascript">
		                    			function saveOrUpdateCustomerAccountTask(id){
		                    				$.ajax({
		                    					  url:'${vix}/crm/customer/customerAccountTaskAction!goSaveOrUpdate.action?id='+id,cache: false,
		                    					  success: function(html){
		                    						  asyncbox.open({
		                    							 	modal:true,width : 680,height : 320,title:"待办任务",html:html,
		                    								callback : function(action){
		                    									if(action == 'ok'){
		                    										if($('#taskForm').validationEngine('validate')){
		                    											$.post('${vix}/crm/customer/customerAccountTaskAction!saveOrUpdate.action',
		                    												 {'task.id':$("#taskId").val(),
		                    												  'task.customerAccount.id':$("#id").val(),
		                    												  'task.subject':$("#subject").val(),
		                    												  'task.startTime':$("#startTime").val(),
		                    												  'task.endTime':$("#endTime").val(),
		                    												  'task.content':$("#taskContent").val()
		                    												},
		                    												function(result){
		                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
		                    														loadCustomerAccountTask();
		                    													});
		                    												}
		                    											 ); 
		                    						  				}else{
		                    						  					return false;
		                    						  				}
		                    									}
		                    								},
		                    								btnsbar : $.btn.OKCANCEL
		                    							});
		                    					  }
		                    				});
		                    			}
		                    			function loadCustomerAccountTask(){
		                    				$.ajax({
		                    					url:'${vix}/crm/customer/customerAccountTaskAction!goListContent.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#customerAccountTask").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadCustomerAccountTask();
		                    		</script>
									<tr>
										<th>待办任务</th>
										<th style="text-align: right;"><a class="abtn" href="###" onclick="saveOrUpdateCustomerAccountTask(0);"><span>新建</span></a></th>
									</tr>
									<tr class="alt">
										<td id="customerAccountTask" colspan="2"></td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" class="newtab">
					<tbody>
						<script type="text/javascript">
                   			function saveOrUpdateActionHistory(id){
                   				$.ajax({
                   					  url:'${vix}/crm/customer/customerAccountActionHistoryAction!goSaveOrUpdate.action?id='+id,cache: false,
                   					  success: function(html){
                   						  asyncbox.open({
                   							 	modal:true,width : 680,height : 320,title:"行动历史",html:html,
                   								callback : function(action){
                   									if(action == 'ok'){
                   										if($('#actionHistoryForm').validationEngine('validate')){
                   											$.post('${vix}/crm/customer/customerAccountActionHistoryAction!saveOrUpdate.action',
                   												 {'actionHistory.id':$("#actionHistoryId").val(),
                   												  'actionHistory.customerAccount.id':$("#id").val(),
                   												  'actionHistory.subject':$("#subject").val(),
                   												  'actionHistory.actionDate':$("#actionDate").val(),
                   												  'actionHistory.description':$("#description").val()
                   												},
                   												function(result){
                   													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
                   														loadActionHistory();
                   													});
                   												}
                   											 ); 
                   						  				}else{
                   						  					return false;
                   						  				}
                   									}
                   								},
                   								btnsbar : $.btn.OKCANCEL
                   							});
                   					  }
                   				});
                   			}
                   			function loadActionHistory(){
                   				$.ajax({
                   					url:'${vix}/crm/customer/customerAccountActionHistoryAction!goListContent.action?id='+$("#id").val(),
                   					cache:false,
                   					success:function(html){
                   						$("#actionHistory").html(html);
                   					}
                   				});
                   			}
                   			loadActionHistory();
                   		</script>
						<tr>
							<th>行动历史</th>
							<th style="text-align: right;"><a class="abtn" href="###" onclick="saveOrUpdateActionHistory(0);"><span>新建行动历史</span></a></th>
						</tr>
						<tr class="alt">
							<td id="actionHistory" colspan="2"></td>
						</tr>
					</tbody>
				</table>
				<table width="100%" class="newtab newtab_red">
					<tr>
						<th colspan="3" style="text-align: center">售 前</th>
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td class="np_border">
							<table width="100%">
								<tbody>
									<script type="text/javascript">
		                    			function saveOrUpdateSaleChance(id){
		                    				$.ajax({
		                    					  url:'${vix}/crm/salechance/saleChanceAction!goSaveOrUpdateForCustomerAccount.action?id='+id,cache: false,
		                    					  success: function(html){
		                    						  asyncbox.open({
		                    							 	modal:true,width : 680,height : 360,title:"销售机会",html:html,
		                    								callback : function(action){
		                    									if(action == 'ok'){
		                    										if($('#saleChanceForm').validationEngine('validate')){
		                    											$.post('${vix}/crm/salechance/saleChanceAction!saveOrUpdateForCustomerAccount.action',
		                    												 {'saleChance.id':$("#saleChanceId").val(),
		                    												  'saleChance.subject':$("#subject").val(),
		                    												  'saleChance.customerName':$("#customerName").val(),
		                    												  'saleChance.customerAccount.id':$("#id").val(),
		                    												  'saleChance.type':$("#type").val(),
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
		                    												  'saleChance.memo':$("#memo").val()
		                    												},
		                    												function(result){
		                    													var r = result.split(",");
		                    													asyncbox.success(r[1],"<s:text name='vix_message'/>",function(action){
		                    														loadSaleChance();
		                    													});
		                    												}
		                    											 ); 
		                    						  				}else{
		                    						  					return false;
		                    						  				}
		                    									}
		                    								},
		                    								btnsbar : $.btn.OKCANCEL
		                    							});
		                    					  }
		                    				});
		                    			}
		                    			function loadSaleChance(){
		                    				$.ajax({
		                    					url:'${vix}/crm/salechance/saleChanceAction!goListContentForCustomerAccount.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#saleChance").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadSaleChance();
		                    		</script>
									<tr class="alt">
										<th>销售机会</th>
										<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateSaleChance(0);"><span>新建</span></a></th>
									</tr>
									<tr class="alt">
										<td id="saleChance" colspan="2"></td>
									</tr>
								</tbody>
							</table>
						</td>
						<td></td>
						<td class="np_border">
							<table width="100%">
								<tbody>
									<script type="text/javascript">
		                    			function saveOrUpdateSalesQuotation(id){
		                    				$.ajax({
		                    					  url:'${vix}/sales/quotes/salesQuotationAction!goSaveOrUpdateForCustomerAccount.action?id='+id,cache: false,
		                    					  success: function(html){
		                    						  asyncbox.open({
		                    							 	modal:true,width : 920,height : 440,title:"销售报价单",html:html,
		                    								callback : function(action){
		                    									if(action == 'ok'){
		                    										if($('#salesQuotationForm').validationEngine('validate')){
		                    											$.post('${vix}/sales/quotes/salesQuotationAction!saveOrUpdate.action',
		                    												{'salesQuotation.id':$("#sqId").val(),
		                    												  'salesQuotation.billDate':$("#billDate").val(),
		                    												  'salesQuotation.customerAccount.id':$("#id").val(),
		                    												  'salesQuotation.regional.id':$("#regionalId").val(),
		                    												  'salesQuotation.code':$("#code").val(),
		                    												  'salesQuotation.groupCode':$("#groupCode").val(),
		                    												  'salesQuotation.quoteSubject':$("#quoteSubject").val(),
		                    												  'salesQuotation.dept.id':$("#sqOrganizationUnitId").val(),
		                    												  'salesQuotation.type':$("#typeId").val(),
		                    												  'salesQuotation.amount':$("#amount").val(),
		                    												  'salesQuotation.bizType':$("#bizType").val(),
		                    												  'salesQuotation.memo':$("#memo").val(),
		                    												  'salesQuotation.salesOrg.id':$("#saleOrgId").val(),
		                    												  'salesQuotation.salesMan.id':$("#salesManId").val(),
		                    												  'salesQuotation.tax':$("#tax").val(),
		                    												  'salesQuotation.dilveryDate':$("#dilveryDate").val(),
		                    												  'salesQuotation.city':$("#city").val(),
		                    												  'salesQuotation.isDeleted':$("#isDeleted").val(),
		                    												  'salesQuotation.validQuotationFrom':$("#validQuotationFrom").val(),
		                    												  'salesQuotation.validQuotationTo':$("#validQuotationTo").val(),
		                    												  'billGroupCode.id':$("#billGroupCodeId").val()
		                    												},
		                    												function(result){
		                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
		                    														loadSalesQuotation();
		                    													});
		                    												}
		                    											);
		                    										}else{
		                    						  					return false;
		                    						  				}
		                    									}
		                    								},
		                    								btnsbar : $.btn.OKCANCEL
		                    							});
		                    					  }
		                    				});
		                    			}
		                    			function loadSalesQuotation(){
		                    				$.ajax({
		                    					url:'${vix}/sales/quotes/salesQuotationAction!goListContentForCustomerAccount.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#salesQuotation").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadSalesQuotation();
		                    		</script>
									<tr class="alt">
										<th>报价单(历史报价)</th>
										<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateSalesQuotation(0);"><span>新建</span></a></th>
									</tr>
									<tr>
										<td id="salesQuotation" colspan="2"></td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" class="newtab  newtab_blue">
					<tr>
						<th colspan="3" style="text-align: center">售 中</th>
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td class="np_border">
							<table width="100%">
								<tbody>
									<script type="text/javascript">
									/* 采购合同 */
									function saveOrUpdatePurchase(id){
	                    				$.ajax({
		                   					  url:'${vix}/contract/contractFastAction!goSaveOrUpdatePurchase.action?contractType='+1,cache: false,
		                   					  success: function(html){
		                   						  asyncbox.open({
		                   							 	modal:true,width : 920,height : 440,title:"合同",html:html,
		                   								callback : function(action){
		                   									if(action == 'ok'){
		                   										if($('#backSectionPlanForm').validationEngine('validate')){
		                   											$.post('${vix}/contract/contractFastAction!saveOrUpdatePurchase.action',
		                    												 {'contract.id':$("#id").val(),
		                    												  'contract.contractCode':$("#contractCode").val(),
		                    												  'contract.contractName':$("#contractName").val(),
		                    												  'contract.firstPartyId':$("#firstPartyId").val(),
		                    												  'contract.secondParty':$("#secondParty").val(),
		                    												  'contract.contractType':$("#contractType").val(),
		                    												  'contract.secondParty':$("#secondParty").val()
		                    												},
		                    												function(result){
		                    													asyncbox.success(result,"<s:text name='采购合同'/>",function(action){
		                    														loadContract();
		                    													});
		                    												}
		                    											 ); 
		                   						  				}else{
		                   						  					return false;
		                   						  				}
		                   									}
		                   								},
		                   								btnsbar : $.btn.OKCANCEL
		                   							});
		                   						}
		                    				});
		                    			}

										/* 销售合同 */
		                    			function saveOrUpdateContract(id){
		                    				$.ajax({
		                    					  url:'${vix}/contract/contractFastAction!goSaveOrUpdateContractFast.action?contractType='+3,cache: false,
		                    					  success: function(html){
		                    						  asyncbox.open({
		                    							 	modal:true,width : 920,height : 440,title:"销售合同",html:html,
		                    								callback : function(action){
		                    									if(action == 'ok'){
		                    										if($('#backSectionPlanForm').validationEngine('validate')){
		                    											$.post('${vix}/contract/contractFastAction!saveOrUpdateContractFast.action',
		                    												 {'contract.id':$("#id").val(),
		                    												  'contract.contractCode':$("#contractCode").val(),
		                    												  'contract.contractName':$("#contractName").val(),
		                    												  'contract.firstParty':$("#firstParty").val(),
		                    												  'contract.secondPartyId':$("#secondPartyId").val(),
		                    												  'contract.contractType':$("#contractType").val(),
		                    												  'contract.secondParty':$("#secondParty").val()
		                    												},
		                    												function(result){
		                    													asyncbox.success(result,"<s:text name='销售合同'/>",function(action){
		                    														loadContract();
		                    													});
		                    												}
		                    											 ); 
		                    						  				}else{
		                    						  					return false;
		                    						  				}
		                    									}
		                    								},
		                    								btnsbar : $.btn.OKCANCEL
		                    							});
		                    					  }
		                    				});
		                    			}
		                    			function loadContract(){
		                    				$.ajax({
		                    					url:'${vix}/contract/contractFastAction!goSingleList.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#contract").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadContract();
		                    		</script>
									<tr class="alt">
										<th>合同</th>
										<th style="text-align: right;" class="drop"><a href="###" class="abtn" onclick="saveOrUpdatePurchase(0);"><span>采购合同</span></a> <a href="###" class="abtn" onclick="saveOrUpdateContract(0);"><span>销售合同</span></a></th>
									</tr>
									<tr>
										<td id="contract" colspan="2"></td>
									</tr>
								</tbody>
							</table>
							<table width="100%">
								<tbody>
									<script type="text/javascript">
		                    			function saveOrUpdateBackSectionRecord(id){
		                    				$.ajax({
		                    					  url:'${vix}/crm/salechance/backSectionRecordAction!goSaveOrUpdateForCustomerAccount.action?id='+id,cache: false,
		                    					  success: function(html){
		                    						  asyncbox.open({
		                    							 	modal:true,width : 920,height : 440,title:"回款记录",html:html,
		                    								callback : function(action){
		                    									if(action == 'ok'){
		                    										if($('#backSectionRecordForm').validationEngine('validate')){
		                    											$.post('${vix}/crm/salechance/backSectionRecordAction!saveOrUpdateForCustomerAccount.action',
		                    												 {'backSectionRecord.id':$("#bsrId").val(),
		                    												  'backSectionRecord.backSectionRecordDate':$("#backSectionRecordDate").val(),
		                    												  'backSectionRecord.stageNumber':$("#stageNumber").val(),
		                    												  'backSectionRecord.customerAccount.id':$("#id").val(),
		                    												  'backSectionRecord.amount':$("#amount").val(),
		                    												  'backSectionRecord.foreignCurrencyMemo':$("#foreignCurrencyMemo").val(),
		                    												  'backSectionRecord.backSectionRecordStatus':$(":radio[name=backSectionRecordStatus][checked]").val(),
		                    												  'backSectionRecord.owner.id':$("#ownerId").val(),
		                    												  'backSectionRecord.charger.id':$("#chargerId").val(),
		                    												  'backSectionRecord.memo':$("#memo").val()
		                    												},
		                    												function(result){
		                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
		                    														loadBackSectionRecord();
		                    													});
		                    												}
		                    											 ); 
		                    						  				}else{
		                    						  					return false;
		                    						  				}
		                    									}
		                    								},
		                    								btnsbar : $.btn.OKCANCEL
		                    							});
		                    					  }
		                    				});
		                    			}
		                    			function loadBackSectionRecord(){
		                    				$.ajax({
		                    					url:'${vix}/crm/salechance/backSectionRecordAction!goListContentForCustomerAccount.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#backSectionRecord").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadBackSectionRecord();
		                    		</script>
									<tr class="alt">
										<th>回款记录</th>
										<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateBackSectionRecord(0);"><span>新建</span></a></th>
									</tr>
									<tr>
										<td id="backSectionRecord" colspan="2"></td>
									</tr>
								</tbody>
							</table>
							<table width="100%">
								<tbody>
									<script type="text/javascript">
		                    			function saveOrUpdateBackSectionPlan(id){
		                    				$.ajax({
		                    					  url:'${vix}/crm/salechance/backSectionPlanAction!goSaveOrUpdateForCustomerAccount.action?id='+id,cache: false,
		                    					  success: function(html){
		                    						  asyncbox.open({
		                    							 	modal:true,width : 920,height : 440,title:"回款计划",html:html,
		                    								callback : function(action){
		                    									if(action == 'ok'){
		                    										if($('#backSectionPlanForm').validationEngine('validate')){
		                    											$.post('${vix}/crm/salechance/backSectionPlanAction!saveOrUpdateForCustomerAccount.action',
		                    												 {'backSectionPlan.id':$("#bspId").val(),
		                    												  'backSectionPlan.backSectionPlanDate':$("#backSectionPlanDate").val(),
		                    												  'backSectionPlan.stageNumber':$("#stageNumber").val(),
		                    												  'backSectionPlan.customerAccount.id':$("#id").val(),
		                    												  'backSectionPlan.amount':$("#amount").val(),
		                    												  'backSectionPlan.foreignCurrencyMemo':$("#foreignCurrencyMemo").val(),
		                    												  'backSectionPlan.backSectionPlanStatus':$(":radio[name=backSectionPlanStatus][checked]").val(),
		                    												  'backSectionPlan.owner.id':$("#ownerId").val(),
		                    												  'backSectionPlan.charger.id':$("#chargerId").val(),
		                    												  'backSectionPlan.memo':$("#memo").val()
		                    												},
		                    												function(result){
		                    													var r = result.split(",");
		                    													asyncbox.success(r[1],"<s:text name='vix_message'/>",function(action){
		                    														loadBackSectionPlan();
		                    													});
		                    												}
		                    											 ); 
		                    						  				}else{
		                    						  					return false;
		                    						  				}
		                    									}
		                    								},
		                    								btnsbar : $.btn.OKCANCEL
		                    							});
		                    					  }
		                    				});
		                    			}
		                    			function loadBackSectionPlan(){
		                    				$.ajax({
		                    					url:'${vix}/crm/salechance/backSectionPlanAction!goListContentForCustomerAccount.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#backSectionPlan").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadBackSectionPlan();
		                    		</script>
									<tr class="alt">
										<th>回款计划</th>
										<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateBackSectionPlan(0);"><span>新建</span></a></th>
									</tr>
									<tr>
										<td id="backSectionPlan" colspan="2"></td>
									</tr>
								</tbody>
							</table>
						</td>
						<td></td>
						<td class="np_border">
							<table width="100%">
								<tr>
									<th>合同交付计划</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">2006-06-30</a> [1期]投影仪3M/3M X80C[台] 1(1)<br /> ■ 2006-06-30 [1期]投影仪3M/3M X80C[台] 1(1)<br />
									</td>
								</tr>
							</table>
							<table width="100%">
								<tr>
									<th>合同交付记录/明细</th>
									<th style="text-align: right;"><a class="abtn" href="#"><span>新建</span></a></th>
								</tr>
								<tr>
									<td colspan="2">■ <a href="#">2006-06-30</a>T35型电源模块/9000[个] <b>2</b> 1,800.00 <a href="#">关联</a><br /> ■ <a href="#">2006-06-30</a>T35型电源模块/9000[个] <b>2</b> 1,800.00 <a href="#">关联</a><br />
									</td>
								</tr>
							</table>
							<table width="100%">
								<tbody>
									<script type="text/javascript">
		                    			function saveOrUpdateSalesInvoice(id){
		                    				$.ajax({
		                    					  url:'${vix}/sales/invoice/salesInvoiceAction!goSaveOrUpdateForCustomerAccount.action?id='+id,cache: false,
		                    					  success: function(html){
		                    						  asyncbox.open({
		                    							 	modal:true,width : 920,height : 440,title:"发票",html:html,
		                    								callback : function(action){
		                    									if(action == 'ok'){
		                    										if($('#salesInvoiceForm').validationEngine('validate')){
		                    											$.post('${vix}/sales/invoice/salesInvoiceAction!saveOrUpdateForCustomerAccount.action',
		                    												 {'salesInvoice.id':$("#siId").val(),
		                    												'salesInvoice.code':$("#code").val(),
		                    												'salesInvoice.name':$("#name").val(),
		                    												'salesInvoice.formType':$("#formType").val(),
		                    												'salesInvoice.bizType':$("#bizType").val(),
		                    												'salesInvoice.invoiceType':$("#invoiceType").val(),
		                    												'salesInvoice.customerName':$("#customerName").val(),
		                    												'salesInvoice.customerAccount.id':$("#id").val(),
		                    												'salesInvoice.payCondition':$("#payCondition").val(),
		                    												'salesInvoice.amount':$("#amount").val(),
		                    												'salesInvoice.tax':$("#tax").val(),
		                    												'salesInvoice.deliveryTime':$("#deliveryTime").val(),
		                    												'salesInvoice.createTime':$("#createTime").val()
		                    												},
		                    												function(result){
		                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
		                    														loadSalesInvoice();
		                    													});
		                    												}
		                    											 ); 
		                    						  				}else{
		                    						  					return false;
		                    						  				}
		                    									}
		                    								},
		                    								btnsbar : $.btn.OKCANCEL
		                    							});
		                    					  }
		                    				});
		                    			}
		                    			function loadSalesInvoice(){
		                    				$.ajax({
		                    					url:'${vix}/sales/invoice/salesInvoiceAction!goListContentForCustomerAccount.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#salesInvoice").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadSalesInvoice();
		                    		</script>
									<tr class="alt">
										<th>发票</th>
										<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateSalesInvoice(0);"><span>新建</span></a></th>
									</tr>
									<tr>
										<td id="salesInvoice" colspan="2"></td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
				<table width="100%" class="newtab  newtab_yellow">
					<tr>
						<th colspan="3" style="text-align: center">售 后</th>
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td class="np_border">
							<table width="100%">
								<tbody>
									<script type="text/javascript">
		                    			function saveOrUpdateCustomerService(id){
		                    				$.ajax({
		                    					  url:'${vix}/crm/service/customerServiceAction!goSaveOrUpdateForCustomerAccount.action?id='+id,cache: false,
		                    					  success: function(html){
		                    						  asyncbox.open({
		                    							 	modal:true,width : 920,height : 440,title:"客户服务",html:html,
		                    								callback : function(action){
		                    									if(action == 'ok'){
		                    										if($('#customerServiceForm').validationEngine('validate')){
		                    											$.post('${vix}/crm/service/customerServiceAction!saveOrUpdateForCustomerAccount.action',
		                    												 {'customerService.id':$("#customerServiceId").val(),
		                    												  'customerService.subject':$("#subject").val(),
		                    												  'customerService.startDate':$("#startDate").val(),
		                    												  'customerService.startTimeStr':$("#startTimeStr").val(),
		                    												  'customerService.consumeTime.id':$("#consumeTimeId").val(),
		                    												  'customerService.serviceContent':$("#serviceContent").val(),
		                    												  'customerService.customerFeedback':$("#customerFeedback").val(),
		                    												  'customerService.memo':$("#memo").val(),
		                    												  'customerService.serviceMode.id':$("#serviceModeId").val(),
		                    												  'customerService.serviceType.id':$("#serviceTypeId").val(),
		                    												  'customerService.customerAccount.id':$("#id").val(),
		                    												  'customerService.employee.id':$("#employeeId").val(),
		                    												  'customerService.customerServiceStatus.id':$("#customerServiceStatusId").val(),
		                    												  'customerService.customerFeedback':$("#customerFeedback").val(),
		                    												  'customerService.serviceContent':$("#serviceContent").val()
		                    												},
		                    												function(result){
		                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
		                    														loadCustomerService();
		                    													});
		                    												}
		                    											 ); 
		                    						  				}else{
		                    						  					return false;
		                    						  				}
		                    									}
		                    								},
		                    								btnsbar : $.btn.OKCANCEL
		                    							});
		                    					  }
		                    				});
		                    			}
		                    			function loadCustomerService(){
		                    				$.ajax({
		                    					url:'${vix}/crm/service/customerServiceAction!goListContentForCustomerAccount.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#customerService").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadCustomerService();
		                    		</script>
									<tr class="alt">
										<th>客户服务</th>
										<th style="text-align: right;"><a onclick="saveOrUpdateCustomerService(0);" href="###" class="abtn"><span>新建</span></a></th>
									</tr>
									<tr>
										<td id="customerService" colspan="2"></td>
									</tr>
								</tbody>
							</table>
							<table width="100%">
								<tbody>
									<script type="text/javascript">
		                    			function saveOrUpdateCustomerCare(id){
		                    				$.ajax({
		                    					  url:'${vix}/crm/service/customerCareAction!goSaveOrUpdateForCustomerAccount.action?id='+id,cache: false,
		                    					  success: function(html){
		                    						  asyncbox.open({
		                    							 	modal:true,width : 920,height : 340,title:"客户关怀",html:html,
		                    								callback : function(action){
		                    									if(action == 'ok'){
		                    										if($('#customerCareForm').validationEngine('validate')){
		                    											$.post('${vix}/crm/service/customerCareAction!saveOrUpdateForCustomerAccount.action',
		                    												 {'customerCare.id':$("#customerCareId").val(),
		                    												  'customerCare.subject':$("#subject").val(),
		                    												  'customerCare.careContent':$("#careContent").val(),
		                    												  'customerCare.customerFeedback':$("#customerFeedback").val(),
		                    												  'customerCare.memo':$("#memo").val(),
		                    												  'customerCare.customerAccount.id':$("#id").val(),
		                    												  'customerCare.employee.id':$("#employeeId").val()
		                    												},
		                    												function(result){
		                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
		                    														loadCustomerCare();
		                    													});
		                    												}
		                    											 ); 
		                    						  				}else{
		                    						  					return false;
		                    						  				}
		                    									}
		                    								},
		                    								btnsbar : $.btn.OKCANCEL
		                    							});
		                    					  }
		                    				});
		                    			}
		                    			function loadCustomerCare(){
		                    				$.ajax({
		                    					url:'${vix}/crm/service/customerCareAction!goListContentForCustomerAccount.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#customerCare").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadCustomerCare();
		                    		</script>
									<tr class="alt">
										<th>客户关怀</th>
										<th style="text-align: right;"><a onclick="saveOrUpdateCustomerCare(0);" href="###" class="abtn"><span>新建</span></a></th>
									</tr>
									<tr>
										<td id="customerCare" colspan="2"></td>
									</tr>
								</tbody>
							</table>
						</td>
						<td></td>
						<td class="np_border">
							<table width="100%">
								<tbody>
									<script type="text/javascript">
		                    			function saveOrUpdateCustomerComplaint(id){
		                    				$.ajax({
		                    					  url:'${vix}/crm/service/customerComplaintAction!goSaveOrUpdateForCustomerAccount.action?id='+id,cache: false,
		                    					  success: function(html){
		                    						  asyncbox.open({
		                    							 	modal:true,width : 920,height : 440,title:"客户投诉",html:html,
		                    								callback : function(action){
		                    									if(action == 'ok'){
		                    										if($('#customerComplaintForm').validationEngine('validate')){
		                    											$.post('${vix}/crm/service/customerComplaintAction!saveOrUpdateForCustomerAccount.action',
		                    												 {'customerComplaint.id':$("#customerComplaintId").val(),
		                    												  'customerComplaint.subject':$("#subject").val(),
		                    												  'customerComplaint.description':$("#description").val(),
		                    												  'customerComplaint.startDate':$("#startDate").val(),
		                    												  'customerComplaint.startTimeStr':$("#startTimeStr").val(),
		                    												  'customerComplaint.customerAccount.id':$("#id").val(),
		                    												  'customerComplaint.contactPerson.id':$("#contactPersonId").val(),
		                    												  'customerComplaint.employee.id':$("#employeeId").val(),
		                    												  'customerComplaint.complaintType.id':$("#complaintTypeId").val(),
		                    												  'customerComplaint.dealResult.id':$("#dealResultId").val(),
		                    												  'customerComplaint.consumeTime.id':$("#consumeTimeId").val(),
		                    												  'customerComplaint.emergencyLevelType.id':$("#emergencyLevelTypeId").val(),
		                    												  'customerComplaint.dealProcess':$("#dealProcess").val(),
		                    												  'customerComplaint.customerFeedback':$("#customerFeedback").val(),
		                    												  'customerComplaint.visitConfirmed':$("#visitConfirmed").val(),
		                    												  'customerComplaint.memo':$("#memo").val()
		                    												},
		                    												function(result){
		                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
		                    														loadCustomerComplaint();
		                    													});
		                    												}
		                    											 ); 
		                    						  				}else{
		                    						  					return false;
		                    						  				}
		                    									}
		                    								},
		                    								btnsbar : $.btn.OKCANCEL
		                    							});
		                    					  }
		                    				});
		                    			}
		                    			function loadCustomerComplaint(){
		                    				$.ajax({
		                    					url:'${vix}/crm/service/customerComplaintAction!goListContentForCustomerAccount.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#customerComplaint").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadCustomerComplaint();
		                    		</script>
									<tr class="alt">
										<th>客户投诉</th>
										<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateCustomerComplaint(0);"><span>新建</span></a></th>
									</tr>
									<tr>
										<td id="customerComplaint" colspan="2"></td>
									</tr>
								</tbody>
							</table>
							<table width="100%">
								<tbody>
									<script type="text/javascript">
		                    			function saveOrUpdateCustomerServiceNotepad(id){
		                    				$.ajax({
		                    					  url:'${vix}/crm/service/customerServiceNotepadAction!goSaveOrUpdateForCustomerAccount.action?id='+id,cache: false,
		                    					  success: function(html){
		                    						  asyncbox.open({
		                    							 	modal:true,width :820,height : 240,title:"客户记事",html:html,
		                    								callback : function(action){
		                    									if(action == 'ok'){
		                    										if($('#customerServiceNotepadForm').validationEngine('validate')){
		                    											$.post('${vix}/crm/service/customerServiceNotepadAction!saveOrUpdateForCustomerAccount.action',
		                    												 {'customerServiceNotepad.id':$("#customerServiceNotepadId").val(),
		                    												  'customerServiceNotepad.customerAccount.id':$("#id").val(),
		                    												  'customerServiceNotepad.noteDate':$("#noteDate").val(),
		                    												  'customerServiceNotepad.content':$("#csnContent").val()
		                    												},
		                    												function(result){
		                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
		                    														loadCustomerServiceNotepad();
		                    													});
		                    												}
		                    											 ); 
		                    						  				}else{
		                    						  					return false;
		                    						  				}
		                    									}
		                    								},
		                    								btnsbar : $.btn.OKCANCEL
		                    							});
		                    					  }
		                    				});
		                    			}
		                    			function loadCustomerServiceNotepad(){
		                    				$.ajax({
		                    					url:'${vix}/crm/service/customerServiceNotepadAction!goListContentForCustomerAccount.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#customerServiceNotepad").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadCustomerServiceNotepad();
		                    		</script>
									<tr class="alt">
										<th>客服记事</th>
										<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateCustomerServiceNotepad(0);"><span>新建</span></a></th>
									</tr>
									<tr>
										<td id="customerServiceNotepad" colspan="2"></td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>