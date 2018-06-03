<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="${vix}/common/css/aristo/jquery-ui-1.8.5.custom.css" type="text/css" media="screen" title="no title" charset="utf-8">
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script src="${vix}/common/js/underscore-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/backbone-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.tmpl.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-debug.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-tinyPubSub.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.mousewheel.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.ui.ipad.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.global.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
function tabs(title,content,style){
		$(title).click(function(){
			$(title).removeClass(style);
			$(this).addClass(style);
			$(content).hide();
			$(content+':eq('+$(title).index($(this))+')').show();
		});
	}
	
	
	
	
	/** 保存任务定义 */
	function saveOrUpdateOrder(){
		
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/taskDefineAction!saveOrUpdate.action',
				{
				'taskDefinition.id' : $("#id").val(),
				'taskDefinition.questName' : $("#questName").val(),
				'taskDefinition.validity' : $("#validity").val(),
				'taskDefinition.taskWeights' : $("#taskWeights").val(),
				'taskDefinition.executiveAgency' : $("#executiveAgency").val(),
				'taskDefinition.assessDepartment' : $("#assessDepartment").val(),
				'taskDefinition.reviewDivision' : $("#reviewDivision").val(),
				'taskDefinition.transactor' : $("#transactor").val(),
				'taskDefinition.appraisalPeople' : $("#appraisalPeople").val(),
				'taskDefinition.reviewer' : $("#reviewer").val(),
				'taskDefinition.taskDescription' : taskDefinitions.html(),
				'taskDefinition.updateTime' : $("#updateTime").val(),
				'taskDefinition.endTime' : $("#endTime").val(),
				'taskDefinition.startTime' : $("#startTime").val(),
				'taskDefinition.pubIds' : $("#pubIds").val()+',',
				'taskDefinition.taskSourceType.id':$("#taskSourceTypeId").val(),
				'taskDefinition.taskType.id':$("#taskTypeId").val(),
				'taskDefinition.difficultyCoefficient.id':$("#difficultyCoefficientId").val(),
				'taskDefinition.taskLevel.id':$("#taskLevelId").val(),
				'taskDefinition.completionMark.id':$("#completionMarkId").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/oa/taskDefineAction!goList.action?menuId=menuContract');
				} 
			);
		}
	}
	
	/** 执行部门*/
	 function chooseParentOrganization(){
		 $.ajax({
			  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择执行部门",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var resObj = $.parseJSON(returnValue);
									var pubIdTmp = $("#pubIds").val();
									
									pubIdTmp = pubIdTmp + ",";
									
									/* if(resObj.length == 0 ){
										return;
									} */
									//debugger;
									for(var i=0;i<resObj.length;i++){
										//alert(resObj[i].treeId+"--"+resObj[i].name);
										if(resObj[i].treeType!="O"){
											asyncbox.alert("只能选择部门信息！","提示");
											return;
										} 
										if(!pubIdTmp.contains(resObj[i].treeId+",")){
											selectIds += "," + resObj[i].treeId;
											selectNames += "," + resObj[i].name;
										}
									}
									selectIds = $("#pubIds").val()+selectIds;
									selectNames = $("#executiveAgency").val()+selectNames;
									
									$("#pubIds").val(selectIds);
									selectNames = selectNames.substring(1,selectNames.length);
									$("#executiveAgency").val(selectNames);
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	
	 /** 考核部门*/
	 function chooseParentOrganization1(){
		 $.ajax({
			  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择考核部门",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var resObj = $.parseJSON(returnValue);
									var pubIdTmp = $("#pubIds").val();
									
									pubIdTmp = pubIdTmp + ",";
									
									/* if(resObj.length == 0 ){
										return;
									} */
									//debugger;
									for(var i=0;i<resObj.length;i++){
										//alert(resObj[i].treeId+"--"+resObj[i].name);
										if(resObj[i].treeType!="O"){
											asyncbox.alert("只能选择部门信息！","提示");
											return;
										} 
										if(!pubIdTmp.contains(resObj[i].treeId+",")){
											selectIds += "," + resObj[i].treeId;
											selectNames += "," + resObj[i].name;
										}
									}
									selectIds = $("#pubIds").val()+selectIds;
									selectNames = $("#assessDepartment").val()+selectNames;
									
									$("#pubIds").val(selectIds);
									selectNames = selectNames.substring(1,selectNames.length);
									$("#assessDepartment").val(selectNames);
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	 
	 
	 
	 /** 审核部门*/
	 function chooseParentOrganization2(){
		 $.ajax({
			  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择审核部门",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var resObj = $.parseJSON(returnValue);
									var pubIdTmp = $("#pubIds").val();
									
									pubIdTmp = pubIdTmp + ",";
									
									/* if(resObj.length == 0 ){
										return;
									} */
									//debugger;
									for(var i=0;i<resObj.length;i++){
										//alert(resObj[i].treeId+"--"+resObj[i].name);
										if(resObj[i].treeType!="O"){
											asyncbox.alert("只能选择部门信息！","提示");
											return;
										} 
										if(!pubIdTmp.contains(resObj[i].treeId+",")){
											selectIds += "," + resObj[i].treeId;
											selectNames += "," + resObj[i].name;
										}
									}
									selectIds = $("#pubIds").val()+selectIds;
									selectNames = $("#reviewDivision").val()+selectNames;
									
									$("#pubIds").val(selectIds);
									selectNames = selectNames.substring(1,selectNames.length);
									$("#reviewDivision").val(selectNames);
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	/**执行人*/
	function chooseEmployees(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 800,
						height : 600,
						title:"选择执行人",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var selectIds = "";
									var selectNames = "";
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											
											selectIds += "," + v[0];
											selectNames = v[1];
										}
									}
									$("#transactor").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	/**考核人*/
	function chooseEmployees1(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 800,
						height : 600,
						title:"选择考核人",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var selectIds = "";
									var selectNames = "";
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											
											selectIds += "," + v[0];
											selectNames = v[1];
										}
									}
									$("#appraisalPeople").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	/**审核人*/
	function chooseEmployees2(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 800,
						height : 600,
						title:"选择审核人",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var selectIds = "";
									var selectNames = "";
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											
											selectIds += "," + v[0];
											selectNames = v[1];
										}
									}
									$("#reviewer").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="${vix}/common/img/icon_14.gif" alt="" />
			<s:text name="print" /></a> <a href="#" id="help"><img src="${vix}/common/img/icon_15.gif" alt="" />
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/oa_task.png" alt="" /> 协同办公</a></li>
				<li><a href="#">任务管理</a></li>
				<li><a href="#">新增任务 </a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<form id="orderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/oa/taskDefineAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="定义任务" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">任务名称：</th>
											<td><input id="questName" name="questName" value="${taskDefinition.questName}" type="text" size="26" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<th align="right">任务来源：</th>
											<td><s:select id="taskSourceTypeId" headerKey="-1" headerValue="--请选择--" list="%{taskSourceTypeList}" listValue="name" listKey="id" value="%{taskDefinition.taskSourceType.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
										<tr>
											<th align="right">任务类型：</th>
											<td><s:select id="taskTypeId" headerKey="-1" headerValue="--请选择--" list="%{taskTypeList}" listValue="name" listKey="id" value="%{taskDefinition.taskType.id}" multiple="false" theme="simple">
												</s:select></td>
											<th align="right">难度系数：</th>
											<td><s:select id="difficultyCoefficientId" headerKey="-1" headerValue="--请选择--" list="%{difficultyCoefficientList}" listValue="name" listKey="id" value="%{taskDefinition.difficultyCoefficient.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
										<tr>
											<th align="right">任务层级：</th>
											<td><s:select id="taskLevelId" headerKey="-1" headerValue="--请选择--" list="%{taskLevelList}" listValue="name" listKey="id" value="%{taskDefinition.taskLevel.id}" multiple="false" theme="simple">
												</s:select></td>
											<th align="right">完成标志：</th>
											<td><s:select id="completionMarkId" headerKey="-1" headerValue="--请选择--" list="%{completionMarkList}" listValue="name" listKey="id" value="%{taskDefinition.completionMark.id}" multiple="false" theme="simple">
												</s:select></td>
										</tr>
										<tr>
											<th align="right">开始时间：</th>
											<td><input id="startTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="startTime" value="<fmt:formatDate value='${taskDefinition.startTime}' type='both' pattern='yyyy-MM-dd' />" type="text" size="26" /> <img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<th align="right">完成时间：</th>
											<td><input id="endTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="endTime" value="<fmt:formatDate value='${taskDefinition.endTime}' type='both' pattern='yyyy-MM-dd' />" type="text" size="26" /> <img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">有效期：</th>
											<td><input id="validity" name="validity" value="${taskDefinition.validity}" type="text" size="26" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<th align="right">任务权重：</th>
											<td><input id="taskWeights" name="taskWeights" value="${taskDefinition.taskWeights}" type="text" size="26" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">执行部门：</th>
											<td><input type="hidden" id="pubIds" name="pubIds" value="${pubIds}" /> <input type="hidden" id="treeType" name="treeType" value="${treeType}" /> <input type="text" id="executiveAgency" name="executiveAgency" readonly="readonly" value="${taskDefinition.executiveAgency}" /> <input class="btn" type="button" value="选择"
												onclick="chooseParentOrganization();" /></td>
											<th align="right">执行人：</th>
											<td><input id="transactor" name="parentId" value="${taskDefinition.transactor}" type="text" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /></td>
										</tr>
										<tr>
											<th align="right">考核部门：</th>
											<td><input type="hidden" id="pubIds" name="pubIds" value="${pubIds}" /> <input type="hidden" id="treeType" name="treeType" value="${treeType}" /> <input type="text" id="assessDepartment" name="assessDepartment" readonly="readonly" value="${taskDefinition.assessDepartment}" /> <input class="btn" type="button" value="选择"
												onclick="chooseParentOrganization1();" /></td>
											<th align="right">考核人：</th>
											<td><input id="appraisalPeople" name="parentId" value="${taskDefinition.appraisalPeople}" type="text" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees1();" /></td>
										</tr>
										<tr>
											<th align="right">审核部门：</th>
											<td><input type="hidden" id="pubIds" name="pubIds" value="${pubIds}" /> <input type="hidden" id="treeType" name="treeType" value="${treeType}" /> <input type="text" id="reviewDivision" name="reviewDivision" readonly="readonly" value="${taskDefinition.reviewDivision}" /> <input class="btn" type="button" value="选择"
												onclick="chooseParentOrganization2();" /></td>
											<th align="right">审核人：</th>
											<td><input id="reviewer" name="parentId" value="${taskDefinition.reviewer}" type="text" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees2();" /></td>
										</tr>
										<tr>
											<th align="right">更新时间：</th>
											<td><input id="updateTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="updateTime" value="<fmt:formatDate value='${taskDefinition.updateTime}' type='both' pattern='yyyy-MM-dd' />" type="text" size="26" /> <img onclick="showTime('updateTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">任务描述：</th>
											<td colspan="3"><textarea id="taskDescription" name="taskDescription">${taskDefinition.taskDescription}</textarea> <script type="text/javascript">
													 var taskDefinitions = KindEditor.create('textarea[name="taskDescription"]',
													{basePath:'${vix}/plugin/KindEditor/',
														width:735,
														height:300,
														cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
														uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
														fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
														allowFileManager : true 
														}
													 );
									 			</script></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
			</dl>
		</div>
	</form>
</div>







