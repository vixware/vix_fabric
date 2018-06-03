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
 
$("#crmProjectForm").validationEngine();
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
function saveOrUpdateCrmProject(){
	if($('#crmProjectForm').validationEngine('validate')){
		$.post('${vix}/crm/project/crmProjectAction!saveOrUpdate.action',
			{'crmProject.id':$("#id").val(),
			  'crmProject.subject':$("#subject").val(),
			  'crmProject.projectStageMemo':$("#projectStageMemo").val(),
			  'crmProject.customerAccount.id':$("#customerAccountId").val(),
			  'crmProject.projectStatus.id':$("#projectStatusId").val(),
			  'crmProject.projectStage.id':$("#projectStageId").val(),
			  'crmProject.personInCharge.id':$("#employeeId").val(),
			  'crmProject.projectSalePreviousStage.id':$("#projectSalePreviousStageId").val(),
			  'crmProject.projectEstablishDate':$("#projectEstablishDate").val(),
			  'crmProject.projectSummary':$("#projectSummary").val(),
			  'crmProject.forecastSignDate':$("#forecastSignDate").val(),
			  'crmProject.forecastMoney':$("#forecastMoney").val(),
			  'crmProject.possibility':$("#possibility").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/crm/project/crmProjectAction!goList.action');
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
								$("#customerAccountName").html(result[1]);
								$("#customerCode").val(result[2]);
							}else{
								$("#customerAccountId").val("");
								$("#customerAccountName").html("");
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
function chooseEmployee(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择职员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#employeeId").val(result[0]);
								$("#employeeName").val(result[1]);
							}else{
								$("#employeeId").val("");
								$("#employeeName").val("");
								asyncbox.success("<s:text name='pleaseChooseEmployee'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
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
				<li><a href="#"><img src="${vix}/common/img/crm/project.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">客户关系管理</a></li>
				<li><a href="#">项目管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/crm/project/crmProjectAction!goList.action');">项目管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${crmProject.id}" />
<input type="hidden" id="employeeId" value="${crmProject.personInCharge.id}" />
<input type="hidden" id="customerAccountId" value="${crmProject.customerAccount.id}" />
<div class="content">
	<form id="crmProjectForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCrmProject();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/crm/project/crmProjectAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b>${crmProject.subject}</b> <i></i>
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
											<td width="15%" align="right">项目主题:</td>
											<td width="35%">${crmProject.subject}</td>
											<td width="10%" align="right">客户:</td>
											<td width="40%">${crmProject.customerAccount.name}</td>
										</tr>
										<tr>
											<td align="right">项目状态:</td>
											<td>${crmProject.projectStatus.name}</td>
											<td align="right">项目阶段:</td>
											<td>${crmProject.projectStage.name}</td>
										</tr>
										<tr>
											<td align="right">项目阶段备注:</td>
											<td>${crmProject.projectStageMemo}</td>
											<td align="right">售前阶段:</td>
											<td>${crmProject.projectSalePreviousStage.name}</td>
										</tr>
										<tr>
											<td align="right">负责人:</td>
											<td>${crmProject.personInCharge.name}</td>
											<td align="right">立项时间:</td>
											<td><s:property value="formatDateToString(crmProject.projectEstablishDate)" /></td>
										</tr>
										<tr>
											<td align="right">项目概要:</td>
											<td>${crmProject.projectSummary}</td>
											<td align="right">预计签单日期:</td>
											<td><s:property value="formatDateToString(crmProject.forecastSignDate)" /></td>
										</tr>
										<tr>
											<td align="right">预计金额:</td>
											<td>${crmProject.forecastMoney}</td>
											<td align="right">可能性:</td>
											<td>${crmProject.possibility}</td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
					<div class="atable">
						<div class="task_list">
							<table width="100%" class="newtab">
								<tbody>
									<tr class="alt">
										<th style="text-align: center" colspan="3">项目跟踪</th>
									</tr>
									<tr class="">
										<td colspan="3"></td>
									</tr>
									<tr class="alt">
										<td class="np_border">
											<table width="100%">
												<tbody>
													<script type="text/javascript">
				                    			function saveOrUpdateProjectCollaborator(id){
				                    				$.ajax({
				                    					  url:'${vix}/crm/project/projectCollaboratorAction!goSaveOrUpdate.action?id='+id,cache: false,
				                    					  success: function(html){
				                    						  asyncbox.open({
				                    							 	modal:true,width : 420,height : 280,title:"项目协作方",html:html,
				                    								callback : function(action){
				                    									if(action == 'ok'){
				                    										if($('#projectCollaboratorForm').validationEngine('validate')){
				                    											$.post('${vix}/crm/project/projectCollaboratorAction!saveOrUpdate.action',
				                    												 {'projectCollaborator.id':$("#projectCollaboratorId").val(),
				                    												  'projectCollaborator.crmProject.id':$("#id").val(),
				                    												  'projectCollaborator.customerAccount.id':$("#customerAccountId").val(),
				                    												  'projectCollaborator.collaboratorType.id':$("#collaboratorTypeId").val(),
				                    												  'projectCollaborator.memo':$("#memo").val()
				                    												},
				                    												function(result){
				                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
				                    														loadProjectCollaborator();
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
				                    			function loadProjectCollaborator(){
				                    				$.ajax({
				                    					url:'${vix}/crm/project/projectCollaboratorAction!goListContent.action?id='+$("#id").val(),
				                    					cache:false,
				                    					success:function(html){
				                    						$("#projectCollaborator").html(html);
				                    					}
				                    				});
				                    			}
				                    			loadProjectCollaborator();
				                    		</script>
													<tr>
														<th>项目协作方</th>
														<th style="text-align: right;"><a class="abtn" href="###" onclick="saveOrUpdateProjectCollaborator(0);"><span>新建</span></a></th>
													</tr>
													<tr class="alt">
														<td id="projectCollaborator" colspan="2"></td>
													</tr>
												</tbody>
											</table>
										</td>
										<td></td>
										<td class="np_border">
											<table width="100%">
												<tbody>
													<script type="text/javascript">
				                    			function saveOrUpdateProjectTask(id){
				                    				$.ajax({
				                    					  url:'${vix}/crm/project/projectTaskAction!goSaveOrUpdate.action?id='+id,cache: false,
				                    					  success: function(html){
				                    						  asyncbox.open({
				                    							 	modal:true,width : 680,height : 320,title:"待办任务",html:html,
				                    								callback : function(action){
				                    									if(action == 'ok'){
				                    										if($('#taskForm').validationEngine('validate')){
				                    											$.post('${vix}/crm/project/projectTaskAction!saveOrUpdate.action',
				                    												 {'task.id':$("#taskId").val(),
				                    												  'task.crmProject.id':$("#id").val(),
				                    												  'task.customerAccount.id':$("#customerAccountId").val(),
				                    												  'task.subject':$("#subject").val(),
				                    												  'task.startTime':$("#startTime").val(),
				                    												  'task.endTime':$("#endTime").val(),
				                    												  'task.content':$("#taskContent").val()
				                    												},
				                    												function(result){
				                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
				                    														loadProjectTask();
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
				                    			function loadProjectTask(){
				                    				$.ajax({
				                    					url:'${vix}/crm/project/projectTaskAction!goListContent.action?id='+$("#id").val(),
				                    					cache:false,
				                    					success:function(html){
				                    						$("#projectTask").html(html);
				                    					}
				                    				});
				                    			}
				                    			loadProjectTask();
				                    		</script>
													<tr>
														<th>待办任务</th>
														<th style="text-align: right;"><a class="abtn" href="###" onclick="saveOrUpdateProjectTask(0);"><span>新建</span></a></th>
													</tr>
													<tr class="alt">
														<td id="projectTask" colspan="2"></td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
									<tr class="alt">
										<td class="np_border">
											<table width="100%">
												<tbody>
													<script type="text/javascript">
				                    			function saveOrUpdateSchedule(id){
				                    				$.ajax({
				                    					  url:'${vix}/crm/project/scheduleAction!goSaveOrUpdate.action?id='+id,cache: false,
				                    					  success: function(html){
				                    						  asyncbox.open({
				                    							 	modal:true,width : 680,height : 360,title:"日程",html:html,
				                    								callback : function(action){
				                    									if(action == 'ok'){
				                    										if($('#scheduleForm').validationEngine('validate')){
				                    											$.post('${vix}/crm/project/scheduleAction!saveOrUpdate.action',
				                    												 {'schedule.id':$("#scheduleId").val(),
				                    												  'schedule.subject':$("#subject").val(),
				                    												  'schedule.crmProject.id':$("#id").val(),
				                    												  'schedule.customerAccount.id':$("#customerAccountId").val(),
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
				                    					url:'${vix}/crm/project/scheduleAction!goListContent.action?id='+$("#id").val(),
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
									</tr>
								</tbody>
							</table>
							<table width="100%" class="newtab">
								<tbody>
									<script type="text/javascript">
	                    			function saveOrUpdateActionHistory(id){
	                    				$.ajax({
	                    					  url:'${vix}/crm/project/actionHistoryAction!goSaveOrUpdate.action?id='+id,cache: false,
	                    					  success: function(html){
	                    						  asyncbox.open({
	                    							 	modal:true,width : 680,height : 320,title:"行动历史",html:html,
	                    								callback : function(action){
	                    									if(action == 'ok'){
	                    										if($('#actionHistoryForm').validationEngine('validate')){
	                    											$.post('${vix}/crm/project/actionHistoryAction!saveOrUpdate.action',
	                    												 {'actionHistory.id':$("#actionHistoryId").val(),
	                    												  'actionHistory.crmProject.id':$("#id").val(),
	                    												  'actionHistory.customerAccount.id':$("#customerAccountId").val(),
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
	                    					url:'${vix}/crm/project/actionHistoryAction!goListContent.action?id='+$("#id").val(),
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
								<tbody>
									<tr>
										<th style="text-align: center" colspan="3">售 前</th>
									</tr>
									<tr class="alt">
										<td colspan="3"></td>
									</tr>
									<tr class="">
										<td class="np_border">
											<table width="100%">
												<tbody>
													<script type="text/javascript">
				                    			function saveOrUpdateProjectRequirement(id){
				                    				$.ajax({
				                    					  url:'${vix}/crm/project/projectRequirementAction!goSaveOrUpdate.action?id='+id,cache: false,
				                    					  success: function(html){
				                    						  asyncbox.open({
				                    							 	modal:true,width : 680,height : 360,title:"项目需求",html:html,
				                    								callback : function(action){
				                    									if(action == 'ok'){
				                    										if($('#projectRequirementForm').validationEngine('validate')){
				                    											$.post('${vix}/crm/project/projectRequirementAction!saveOrUpdate.action',
				                    												 {'projectRequirement.id':$("#projectRequirementId").val(),
				                    												  'projectRequirement.subject':$("#subject").val(),
				                    												  'projectRequirement.provider.id':$("#providerId").val(),
				                    												  'projectRequirement.crmProject.id':$("#id").val(),
				                    												  'projectRequirement.customerAccount.id':$("#customerAccountId").val(),
				                    												  'projectRequirement.recordDate':$("#recordDate").val(),
				                    												  'projectRequirement.description':$("#description").val()
				                    												},
				                    												function(result){
				                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
				                    														loadProjectRequirement();
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
				                    			function loadProjectRequirement(){
				                    				$.ajax({
				                    					url:'${vix}/crm/project/projectRequirementAction!goListContentForProject.action?id='+$("#id").val(),
				                    					cache:false,
				                    					success:function(html){
				                    						$("#projectRequirement").html(html);
				                    					}
				                    				});
				                    			}
				                    			loadProjectRequirement();
				                    		</script>
													<tr class="alt">
														<th>项目需求</th>
														<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateProjectRequirement(0);"><span>新建</span></a></th>
													</tr>
													<tr class="alt">
														<td id="projectRequirement" colspan="2"></td>
													</tr>
												</tbody>
											</table>
										</td>
										<td></td>
										<td class="np_border">
											<table width="100%">
												<tbody>
													<script type="text/javascript">
				                    			function saveOrUpdateProjectSolution(id){
				                    				$.ajax({
				                    					  url:'${vix}/crm/project/projectSolutionAction!goSaveOrUpdate.action?id='+id,cache: false,
				                    					  success: function(html){
				                    						  asyncbox.open({
				                    							 	modal:true,width : 680,height : 360,title:"解决方案",html:html,
				                    								callback : function(action){
				                    									if(action == 'ok'){
				                    										if($('#projectSolutionForm').validationEngine('validate')){
				                    											$.post('${vix}/crm/project/projectSolutionAction!saveOrUpdate.action',
				                    												 {'projectSolution.id':$("#projectSolutionId").val(),
				                    												  'projectSolution.subject':$("#subject").val(),
				                    												  'projectSolution.submitDate':$("#submitDate").val(),
				                    												  'projectSolution.crmProject.id':$("#id").val(),
				                    												  'projectSolution.customerAccount.id':$("#customerAccountId").val(),
				                    												  'projectSolution.solutionContent':$("#solutionContent").val(),
				                    												  'projectSolution.customerFeedback':$("#customerFeedback").val()
				                    												},
				                    												function(result){
				                    													asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
				                    														loadProjectSolution();
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
				                    			function loadProjectSolution(){
				                    				$.ajax({
				                    					url:'${vix}/crm/project/projectSolutionAction!goListContentForCrmProject.action?id='+$("#id").val(),
				                    					cache:false,
				                    					success:function(html){
				                    						$("#projectSolution").html(html);
				                    					}
				                    				});
				                    			}
				                    			loadProjectSolution();
				                    		</script>
													<tr class="alt">
														<th>解决方案</th>
														<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateProjectSolution(0);"><span>新建</span></a></th>
													</tr>
													<tr>
														<td id="projectSolution" colspan="2"></td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
							<table width="100%" class="newtab  newtab_blue">
								<tbody>
									<tr class="alt">
										<th style="text-align: center" colspan="3">售 中</th>
									</tr>
									<tr>
										<td colspan="3"></td>
									</tr>
									<tr class="alt">
										<td class="np_border">
											<table width="100%">
												<tbody>
													<tr>
														<th>合同</th>
														<th style="text-align: right;"><a onclick="javascript:addContract();" href="javascript:;" class="abtn"><span>新建</span></a></th>
													</tr>
													<tr class="alt">
														<td colspan="2">■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br>
														</td>
													</tr>
												</tbody>
											</table>
											<table width="100%">
												<tbody>
													<script type="text/javascript">
				                    			function saveOrUpdateBackSectionPlan(id){
				                    				$.ajax({
				                    					  url:'${vix}/crm/salechance/backSectionPlanAction!goSaveOrUpdateForCrmProject.action?id='+id,cache: false,
				                    					  success: function(html){
				                    						  asyncbox.open({
				                    							 	modal:true,width : 920,height : 440,title:"回款计划",html:html,
				                    								callback : function(action){
				                    									if(action == 'ok'){
				                    										if($('#backSectionPlanForm').validationEngine('validate')){
				                    											$.post('${vix}/crm/salechance/backSectionPlanAction!saveOrUpdateForCrmProject.action',
				                    												 {'backSectionPlan.id':$("#bspId").val(),
				                    												  'backSectionPlan.backSectionPlanDate':$("#backSectionPlanDate").val(),
				                    												  'backSectionPlan.stageNumber':$("#stageNumber").val(),
				                    												  'backSectionPlan.crmProject.id':$("#id").val(),
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
				                    					url:'${vix}/crm/salechance/backSectionPlanAction!goListContentForCrmProject.action?id='+$("#id").val(),
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
											<table width="100%">
												<tbody>
													<script type="text/javascript">
				                    			function saveOrUpdateBackSectionRecord(id){
				                    				$.ajax({
				                    					  url:'${vix}/crm/salechance/backSectionRecordAction!goSaveOrUpdateForCrmProject.action?id='+id,cache: false,
				                    					  success: function(html){
				                    						  asyncbox.open({
				                    							 	modal:true,width : 920,height : 440,title:"回款记录",html:html,
				                    								callback : function(action){
				                    									if(action == 'ok'){
				                    										if($('#backSectionRecordForm').validationEngine('validate')){
				                    											$.post('${vix}/crm/salechance/backSectionRecordAction!saveOrUpdateForCrmProject.action',
				                    												 {'backSectionRecord.id':$("#bspId").val(),
				                    												  'backSectionRecord.crmProject.id':$("#id").val(),
				                    												  'backSectionRecord.backSectionDate':$("#backSectionDate").val(),
				                    												  'backSectionRecord.findDate':$("#findDate").val(),
				                    												  'backSectionRecord.amount':$("#amount").val(),
				                    												  'backSectionRecord.stageNumber':$("#stageNumber").val(),
				                    												  'backSectionRecord.foreignCurrencyMemo':$("#foreignCurrencyMemo").val(),
				                    												  'backSectionRecord.isBilling':$(":radio[name=isBilling][checked]").val(),
				                    												  'backSectionRecord.owner.id':$("#ownerId").val(),
				                    												  'backSectionRecord.paymentType.id':$("#paymentTypeId").val(),
				                    												  'backSectionRecord.paymentCategory.id':$("#paymentCategoryId").val(),
				                    												  'backSectionRecord.memo':$("#memo").val()
				                    												},
				                    												function(result){
				                    													var r = result.split(",");
				                    													asyncbox.success(r[1],"<s:text name='vix_message'/>",function(action){
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
				                    					url:'${vix}/crm/salechance/backSectionRecordAction!goListContentForCrmProject.action?id='+$("#id").val(),
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
										</td>
										<td></td>
										<td class="np_border">
											<table width="100%">
												<tbody>
													<tr>
														<th>合同交付计划</th>
														<th style="text-align: right;"><a href="#" class="abtn"><span>新建</span></a></th>
													</tr>
													<tr class="alt">
														<td colspan="2">■ <a href="#">2006-06-30</a> [1期]投影仪3M/3M X80C[台] 1(1)<br> ■ 2006-06-30 [1期]投影仪3M/3M X80C[台] 1(1)<br>
														</td>
													</tr>
												</tbody>
											</table>
											<table width="100%">
												<tbody>
													<tr>
														<th>合同交付记录/明细</th>
														<th style="text-align: right;"><a href="#" class="abtn"><span>新建</span></a></th>
													</tr>
													<tr class="alt">
														<td colspan="2">■ <a href="#">2006-06-30</a>T35型电源模块/9000[个] <b>2</b> 1,800.00 <a href="#">关联</a><br> ■ <a href="#">2006-06-30</a>T35型电源模块/9000[个] <b>2</b> 1,800.00 <a href="#">关联</a><br>
														</td>
													</tr>
												</tbody>
											</table>
											<table width="100%">
												<tbody>
													<tr>
														<th>发票</th>
														<th style="text-align: right;"><a href="#" class="abtn"><span>新建</span></a></th>
													</tr>
													<tr class="alt">
														<td colspan="2">■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br> ■ <a href="#">KP502：30台+安控综合方案</a>2007-03-10 ￥78,000.00/￥7,800.00<br>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
							<table width="100%" class="newtab  newtab_yellow">
								<tbody>
									<tr>
										<th style="text-align: center" colspan="3">售 后</th>
									</tr>
									<tr class="alt">
										<td colspan="3"></td>
									</tr>
									<tr>
										<td class="np_border">
											<table width="100%">
												<tbody>
													<script type="text/javascript">
				                    			function saveOrUpdateCustomerService(id){
				                    				$.ajax({
				                    					  url:'${vix}/crm/service/customerServiceAction!goSaveOrUpdate.action?from=project&id='+id,cache: false,
				                    					  success: function(html){
				                    						  asyncbox.open({
				                    							 	modal:true,width : 920,height : 440,title:"客户服务",html:html,
				                    								callback : function(action){
				                    									if(action == 'ok'){
				                    										if($('#customerServiceForm').validationEngine('validate')){
				                    											$.post('${vix}/crm/service/customerServiceAction!saveOrUpdate.action',
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
				                    												  'customerService.customerAccount.id':$("#customerAccountId").val(),
				                    												  'customerService.employee.id':$("#employeeId").val(),
				                    												  'customerService.crmProject.id':$("#id").val(),
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
				                    					url:'${vix}/crm/service/customerServiceAction!goListContentForCrmProject.action?id='+$("#id").val(),
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
				                    					  url:'${vix}/crm/service/customerCareAction!goSaveOrUpdateForCrmProject.action?id='+id,cache: false,
				                    					  success: function(html){
				                    						  asyncbox.open({
				                    							 	modal:true,width : 920,height : 340,title:"客户关怀",html:html,
				                    								callback : function(action){
				                    									if(action == 'ok'){
				                    										if($('#customerCareForm').validationEngine('validate')){
				                    											$.post('${vix}/crm/service/customerCareAction!saveOrUpdate.action',
				                    												 {'customerCare.id':$("#customerCareId").val(),
				                    											      'customerCare.subject':$("#subject").val(),
				                    												  'customerCare.careContent':$("#careContent").val(),
				                    												  'customerCare.customerFeedback':$("#customerFeedback").val(),
				                    												  'customerCare.employee.id':$("#employeeId").val(),
				                    												  'customerCare.customerAccount.id':$("#customerAccountFPId").val(),
				                    												  'customerCare.contactPerson.id':$("#contactPersonId").val(),
				                    												  'customerCare.crmProject.id':$("#id").val(),
				                    												  'customerCare.memo':$("#memo").val()
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
				                    					url:'${vix}/crm/service/customerCareAction!goListContentForCrmProject.action?id='+$("#id").val(),
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
														<th style="text-align: right;"><a href="###" class="abtn" onclick="saveOrUpdateCustomerCare(0);"><span>新建</span></a></th>
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
				                    					  url:'${vix}/crm/service/customerComplaintAction!goSaveOrUpdate.action?from=project&id='+id,cache: false,
				                    					  success: function(html){
				                    						  asyncbox.open({
				                    							 	modal:true,width : 920,height : 440,title:"客户投诉",html:html,
				                    								callback : function(action){
				                    									if(action == 'ok'){
				                    										if($('#customerComplaintForm').validationEngine('validate')){
				                    											$.post('${vix}/crm/service/customerComplaintAction!saveOrUpdate.action',
				                    												 {'customerComplaint.id':$("#customerComplaintId").val(),
				                    											      'customerComplaint.subject':$("#subject").val(),
				                    												  'customerComplaint.description':$("#description").val(),
				                    												  'customerComplaint.startDate':$("#startDate").val(),
				                    												  'customerComplaint.startTimeStr':$("#startTimeStr").val(),
				                    												  'customerComplaint.customerAccount.id':$("#customerAccountId").val(),
				                    												  'customerComplaint.contactPerson.id':$("#contactPersonId").val(),
				                    												  'customerComplaint.employee.id':$("#employeeId").val(),
				                    												  'customerComplaint.crmProject.id':$("#id").val(),
				                    												  'customerComplaint.complaintType.id':$("#complaintTypeId").val(),
				                    												  'customerComplaint.dealResult.id':$("#dealResultId").val(),
				                    												  'customerComplaint.consumeTime.id':$("#consumeTimeId").val(),
				                    												  'customerComplaint.customerAccount.id':$("#customerAccountId").val(),
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
				                    					url:'${vix}/crm/service/customerComplaintAction!goListContentForCrmProject.action?id='+$("#id").val(),
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
				                    					  url:'${vix}/crm/service/customerServiceNotepadAction!goSaveOrUpdate.action?id='+id,cache: false,
				                    					  success: function(html){
				                    						  asyncbox.open({
				                    							 	modal:true,width : 620,height : 240,title:"客服记事",html:html,
				                    								callback : function(action){
				                    									if(action == 'ok'){
				                    										if($('#customerServiceNotepadForm').validationEngine('validate')){
				                    											$.post('${vix}/crm/service/customerServiceNotepadAction!saveOrUpdate.action',
				                    												 {'customerServiceNotepad.id':$("#customerServiceNotepadId").val(),
				                    											      'customerServiceNotepad.noteDate':$("#noteDate").val(),
				                    												  'customerServiceNotepad.customerAccount.id':$("#customerAccountId").val(),
				                    												  'customerServiceNotepad.crmProject.id':$("#id").val(),
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
				                    					url:'${vix}/crm/service/customerServiceNotepadAction!goListContent.action?id='+$("#id").val(),
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
								</tbody>
							</table>
							<table width="100%" class="newtab">
								<tbody>
									<tr class="alt">
										<th style="text-align: center">所有客户联系人</th>
									</tr>
									<tr>
										<td class="np_border"><script type="text/javascript">
		                    			function loadProjectContactPerson(){
		                    				$.ajax({
		                    					url:'${vix}/crm/project/crmProjectAction!goContactPersonListContent.action?id='+$("#id").val(),
		                    					cache:false,
		                    					success:function(html){
		                    						$("#contactPersons").html(html);
		                    					}
		                    				});
		                    			}
		                    			loadProjectContactPerson();
		                    		</script>
											<table width="100%">
												<tbody>
													<tr class="alt" id="contactPersons"></tr>
												</tbody>
											</table></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--oder-->
	</form>
</div>
<!-- content -->
