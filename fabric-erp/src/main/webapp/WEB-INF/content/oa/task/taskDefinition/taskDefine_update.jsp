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
	function deleteEntity(id){
		asyncbox.confirm('确定要删除该分类么?','提示信息',function(action){
			if(action == 'ok'){
				deleteById(id);
			}
		});
	}

	$(function() {
	    //加载更新时间(新增)
	    if (document.getElementById("updateTime").value == "") {
		    var myDate = new Date();
		    $("#updateTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours()+ ":" + myDate.getMinutes()+ ":" + myDate.getSeconds());
	    }
	  //默认选择部门
	    if($('input:radio[name=pubType]:checked').length==0){
	    	$('input:radio[name=pubType]:first').trigger('click');
	    }
	  //默认选择部门
	    if($('input:radio[name=pubType1]:checked').length==0){
	    	$('input:radio[name=pubType1]:first').trigger('click');
	    }
	  //默认选择部门
	    if($('input:radio[name=pubType2]:checked').length==0){
	    	$('input:radio[name=pubType2]:first').trigger('click');
	    }
	  //默认选择下达
	    if($('input:radio[name=isPublish]:checked').length==0){
	    	$('input:radio[name=isPublish]:first').trigger('click');
	    }
	});

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
			$.post('${vix}/oa/task/taskDefinition/taskDefineAction!saveOrUpdate.action',
				{
				'taskDefinition.id' : $("#id").val(),
				'taskDefinition.questName' : $("#questName").val(),
				'taskDefinition.validity' : $("#validity").val(),
				'taskDefinition.taskWeights' : $("#taskWeights").val(),
				'taskDefinition.executiveAgency' : $("#executiveAgency").val(),
				'taskDefinition.assessDepartment' : $("#assessDepartment").val(),
				'taskDefinition.reviewDivision' : $("#reviewDivision").val(),
				'taskDefinition.transactor' : $("#transactor").val(),
				'taskDefinition.schedule' : $("#schedule").val(),
				'taskDefinition.appraisalPeople' : $("#appraisalPeople").val(),
				'taskDefinition.reviewer' : $("#reviewer").val(),
				'taskDefinition.taskDescription' : taskDefinitions.html(),
				'taskDefinition.updateTime' : $("#updateTime").val(),
				'taskDefinition.complete' : $("#complete").val(),
				'taskDefinition.endTime' : $("#endTime").val(),
				'taskDefinition.startTime' : $("#startTime").val(),
				'taskDefinition.taskStartTime' : $("#taskStartTime").val(),
				'taskDefinition.taskEndTime' : $("#taskEndTime").val(),
				'taskDefinition.taskSourceType.id':$("#taskSourceTypeId").val(),
				'taskDefinition.taskType.id':$("#taskTypeId").val(),
				'taskDefinition.difficultyCoefficient.id':$("#difficultyCoefficientId").val(),
				'taskDefinition.isPublish':$('input:radio[name=isPublish]:checked').val(),
				'taskDefinition.taskLevel.id':$("#taskLevelId").val(),
				'taskDefinition.pubType':$('input:radio[name=pubType]:checked').val(),
				'taskDefinition.pubType1':$('input:radio[name=pubType1]:checked').val(),
				'taskDefinition.pubType2':$('input:radio[name=pubType2]:checked').val(),
				'taskDefinition.pubIds' : $("#pubIds").val()+',',
				'taskDefinition.pubIds1' : $("#pubIds1").val()+',',
				'taskDefinition.pubIds2' : $("#pubIds2").val()+',',
				'taskDefinition.parentVixTask.id':$("#parentId").val(),
				'taskDefinition.completionMark.id':$("#completionMarkId").val()
				},
				function(result){
					showMessage(result);
					 setTimeout("",1000);
					loadContent('${vix}/oa/task/taskDefinition/taskDefineAction!goList.action?menuId=menuContract'); 
				} 
			);
		}else {
			return false;
		}
	}
	
	 $("#orderForm").validationEngine();

	function saveOrUpdateOrder1(){
		
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/task/taskDefinition/taskDefineAction!saveOrUpdate.action',
				{
				'taskDefinition.id' : $("#id").val(),
				'taskDefinition.questName' : $("#questName").val(),
				'taskDefinition.validity' : $("#validity").val(),
				'taskDefinition.taskWeights' : $("#taskWeights").val(),
				'taskDefinition.executiveAgency' : $("#executiveAgency").val(),
				'taskDefinition.assessDepartment' : $("#assessDepartment").val(),
				'taskDefinition.reviewDivision' : $("#reviewDivision").val(),
				'taskDefinition.transactor' : $("#transactor").val(),
				'taskDefinition.schedule' : $("#schedule").val(),
				'taskDefinition.appraisalPeople' : $("#appraisalPeople").val(),
				'taskDefinition.reviewer' : $("#reviewer").val(),
				'taskDefinition.taskDescription' : taskDefinitions.html(),
				'taskDefinition.updateTime' : $("#updateTime").val(),
				'taskDefinition.taskStartTime' : $("#taskStartTime").val(),
				'taskDefinition.taskEndTime' : $("#taskEndTime").val(),
				'taskDefinition.complete' : $("#complete").val(),
				'taskDefinition.endTime' : $("#endTime").val(),
				'taskDefinition.startTime' : $("#startTime").val(),
				'taskDefinition.taskSourceType.id':$("#taskSourceTypeId").val(),
				'taskDefinition.taskType.id':$("#taskTypeId").val(),
				'taskDefinition.difficultyCoefficient.id':$("#difficultyCoefficientId").val(),
				'taskDefinition.isPublish':$('input:radio[name=isPublish]:checked').val(),
				'taskDefinition.taskLevel.id':$("#taskLevelId").val(),
				'taskDefinition.pubType':$('input:radio[name=pubType]:checked').val(),
				'taskDefinition.pubType1':$('input:radio[name=pubType1]:checked').val(),
				'taskDefinition.pubType2':$('input:radio[name=pubType2]:checked').val(),
				'taskDefinition.pubIds' : $("#pubIds").val()+',',
				'taskDefinition.pubIds1' : $("#pubIds1").val()+',',
				'taskDefinition.pubIds2' : $("#pubIds2").val()+',',
				'taskDefinition.parentVixTask.id':$("#parentId").val(),
				'taskDefinition.completionMark.id':$("#completionMarkId").val()
				},
				function(result){
				} 
			);
		}
	}
	
	/**
	 * 变更选择发布类型
	 */
	function changePubType(pubTypeValue){
		clearPubType();
	}

	/**
	 * 清空选择对象
	 */
	function clearPubType(){
		$("#pubIds").val("");
		$("#executiveAgency").val("");
	}

	/**
	 * 添加发布对象
	 */
	function addBulletinPubobject(){
		
		var pubTypeVal = $("input[name='pubType']:checked").val();
		//debugger;
		if(pubTypeVal=="O"){
			chooseBulletinOrgUnit($("#pubIds").val());
		}else if(pubTypeVal=="E"){
			chooseBulletinEmp($("#pubIds").val());
		}
	}

	/**
	 * 选择部门
	 */
	 function chooseBulletinOrgUnit(){
			$.ajax({
				  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
				  data:{chkStyle:"checkbox",canCheckComp:0},
				  cache: false,
				  success: function(html){
					  asyncbox.open({
						 	modal:true,
							width : 560,
							height : 340,
							title:"选择部门",
							html:html,
							callback : function(action,returnValue){
								if(action == 'ok'){
									if(returnValue != undefined){
										//alert(returnValue);
										var selectIds = "";
										var selectNames = "";
									
										var resObj = $.parseJSON(returnValue);
										
										for(var i=0;i<resObj.length;i++){
											selectIds += "," + resObj[i].treeId;
											selectNames += "," + resObj[i].name;
										}

										if(selectIds!=''){
											selectIds = selectIds.substring(1);
											selectNames = selectNames.substring(1);
											//alert(selectIds)
											$("#pubIds").val(selectIds);
											$("#executiveAgency").val(selectNames);
										}
									}
									
								}
							},
							btnsbar : $.btn.OKCANCEL
						});
				  }
			});
		}
	 
	 
	 
	
	
	/**
	 * 选择人员
	 */
	function chooseBulletinEmp(checkObjIds){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 660,
						height : 340,
						title:"选择人员",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var pubIdTmp = $("#pubIds").val();
									
									pubIdTmp = pubIdTmp + ",";
									
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											if(!pubIdTmp.contains(v[0]+",")){
												selectIds += "," + v[0];
												selectNames += "," + v[1];
											}
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
	
	/**
	 * 变更选择发布类型
	 */
	function changePubType1(pubTypeValue){
		clearPubType1();
	}

	/**
	 * 清空选择对象
	 */
	function clearPubType1(){
		$("#pubIds1").val("");
		$("#assessDepartment").val("");
	}
	
	/**
	 * 添加发布对象
	 */
	function addBulletinPubobject1(){
		
		var pubTypeVal = $("input[name='pubType1']:checked").val();
		//debugger;
		if(pubTypeVal=="O"){
			chooseBulletinOrgUnit1($("#pubIds1").val());
		}else if(pubTypeVal=="E"){
			chooseBulletinEmp1($("#pubIds1").val());
		}
	}
	
	
	/**
	 * 选择部门
	 */
	 function chooseBulletinOrgUnit1(){
			$.ajax({
				  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
				  data:{chkStyle:"checkbox",canCheckComp:0},
				  cache: false,
				  success: function(html){
					  asyncbox.open({
						 	modal:true,
							width : 560,
							height : 340,
							title:"选择部门",
							html:html,
							callback : function(action,returnValue){
								if(action == 'ok'){
									if(returnValue != undefined){
										//alert(returnValue);
										var selectIds = "";
										var selectNames = "";
									
										var resObj = $.parseJSON(returnValue);
										
										for(var i=0;i<resObj.length;i++){
											selectIds += "," + resObj[i].treeId;
											selectNames += "," + resObj[i].name;
										}

										if(selectIds!=''){
											selectIds = selectIds.substring(1);
											selectNames = selectNames.substring(1);
											//alert(selectIds)
											$("#pubIds1").val(selectIds);
											$("#assessDepartment").val(selectNames);
										}
									}
									
								}
							},
							btnsbar : $.btn.OKCANCEL
						});
				  }
			});
		}
	
	
	/**
	 * 选择人员
	 */
	function chooseBulletinEmp1(checkObjIds1){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 660,
						height : 340,
						title:"选择人员",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var pubIdTmp = $("#pubIds1").val();
									
									pubIdTmp = pubIdTmp + ",";
									
									/* if(resObj.length == 0 ){
										return;
									} */
									//debugger;
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											if(!pubIdTmp.contains(v[0]+",")){
												selectIds += "," + v[0];
												selectNames += "," + v[1];
											}
										}
									}
									
									selectIds = $("#pubIds1").val()+selectIds;
									selectNames = $("#assessDepartment").val()+selectNames;
									
									$("#pubIds1").val(selectIds);
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
	
	
	/**
	 * 变更选择发布类型
	 */
	function changePubType2(pubTypeValue){
		clearPubType2();
	}

	/**
	 * 清空选择对象
	 */
	function clearPubType2(){
		$("#pubIds2").val("");
		$("#reviewDivision").val("");
	}

	/**
	 * 添加发布对象
	 */
	function addBulletinPubobject2(){
		
		var pubTypeVal = $("input[name='pubType2']:checked").val();
		//debugger;
		if(pubTypeVal=="O"){
			chooseBulletinOrgUnit2($("#pubIds2").val());
		}else if(pubTypeVal=="E"){
			chooseBulletinEmp2($("#pubIds2").val());
		}
	}
	
	
	/**
	 * 选择部门
	 */
	 function chooseBulletinOrgUnit2(){
			$.ajax({
				  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
				  data:{chkStyle:"checkbox",canCheckComp:0},
				  cache: false,
				  success: function(html){
					  asyncbox.open({
						 	modal:true,
							width : 560,
							height : 340,
							title:"选择部门",
							html:html,
							callback : function(action,returnValue){
								if(action == 'ok'){
									if(returnValue != undefined){
										//alert(returnValue);
										var selectIds = "";
										var selectNames = "";
									
										var resObj = $.parseJSON(returnValue);
										
										for(var i=0;i<resObj.length;i++){
											selectIds += "," + resObj[i].treeId;
											selectNames += "," + resObj[i].name;
										}

										if(selectIds!=''){
											selectIds = selectIds.substring(1);
											selectNames = selectNames.substring(1);
											//alert(selectIds)
											$("#pubIds2").val(selectIds);
											$("#reviewDivision").val(selectNames);
										}
									}
									
								}
							},
							btnsbar : $.btn.OKCANCEL
						});
				  }
			});
		} 
	 
	/**
	 * 选择人员
	 */
	function chooseBulletinEmp2(checkObjIds){
		$.ajax({
			  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
			  data:{chkStyle:"checkbox"},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 660,
						height : 340,
						title:"选择人员",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var pubIdTmp = $("#pubIds2").val();
									
									pubIdTmp = pubIdTmp + ",";
									
									/* if(resObj.length == 0 ){
										return;
									} */
									//debugger;
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											if(!pubIdTmp.contains(v[0]+",")){
												selectIds += "," + v[0];
												selectNames += "," + v[1];
											}
										}
									}
									
									selectIds = $("#pubIds2").val()+selectIds;
									selectNames = $("#reviewDivision").val()+selectNames;
									
									$("#pubIds2").val(selectIds);
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
	
	
	function chooseParentCategory(){
		$.ajax({
			  url:'${vix}/oa/taskDefineAction!goChooseCategory.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择父分类",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var result = returnValue.split(",");
									if(result[0] == $("#id").val()){
										asyncbox.success("不允许引用自身为父分类!","提示信息");
									}else{
										$("#parentId").val(result[0]);
										$("#categoryName").html(result[1]);
									}
								}else{
									$("#parentId").val("");
									$("#categoryName").html("");
									asyncbox.success("请选择分类信息!","提示信息");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
	
	function editEntity(id,taskDefineId){
		saveOrUpdateOrder1();
		$.ajax({
			  url:"${vix}/oa/taskDefineAction!eqSbwdEdit.action?id="+id+"&taskDefineId="+taskDefineId,
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 780,
						height : 280,
						title:"上传附件",
						html:html,
						callback : function(action){
							if(action == 'ok'){
									$("#brandForm").ajaxSubmit({
									     type: "post",
									     url: "${vix}/oa/taskDefineAction!saveEqSbwd.action",
									     dataType: "text",
									     success: function(result){
										loadContent ('${vix}/oa/taskDefineAction!goSaveOrUpdate.action?id='+result);
									     }
									 });
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	};
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
				<li><a href="#" onclick="loadContent('${vix}/oa/taskDefineAction!goList.action?pageNo=${pageNo}');">任务管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/taskDefineAction!goList.action?pageNo=${pageNo}');">新增任务 </a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div class="content">
	<form id="orderForm">
		<input type="hidden" id="complete" name="complete" value="1" /> <input type="hidden" id="schedule" name="schedule" value="0" />
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/oa/task/taskDefinition/taskDefineAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="定义任务" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">部门:&nbsp;</td>
											<td colspan="3"><input type="hidden" id="parentId" name="parentId" value="${taskDefinition.parentVixTask.id}" /> <span id="categoryName"><s:property value="taskDefinition.parentVixTask.code" /></span> <span class="btn"><a href="#" onclick="chooseParentCategory();">选择</a></span></td>
										</tr>
										<tr>
											<th align="right">任务名称：</th>
											<td><input id="questName" name="questName" value="${taskDefinition.questName}" type="text" size="20" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
											<th align="right">任务来源：</th>
											<td><s:select id="taskSourceTypeId" headerKey="-1" headerValue="--请选择--" list="%{taskSourceTypeList}" listValue="name" listKey="id" value="%{taskDefinition.taskSourceType.id}" multiple="false" theme="simple">
												</s:select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">任务类型：</th>
											<td><s:select id="taskTypeId" headerKey="-1" headerValue="--请选择--" list="%{taskTypeList}" listValue="name" listKey="id" value="%{taskDefinition.taskType.id}" multiple="false" theme="simple">
												</s:select> <span style="color: red;">*</span></td>
											<th align="right">难度系数：</th>
											<td><s:select id="difficultyCoefficientId" headerKey="-1" headerValue="--请选择--" list="%{difficultyCoefficientList}" listValue="name" listKey="id" value="%{taskDefinition.difficultyCoefficient.id}" multiple="false" theme="simple">
												</s:select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">任务层级：</th>
											<td><s:select id="taskLevelId" headerKey="-1" headerValue="--请选择--" list="%{taskLevelList}" listValue="name" listKey="id" value="%{taskDefinition.taskLevel.id}" multiple="false" theme="simple">
												</s:select> <span style="color: red;">*</span></td>
											<th align="right">完成标志：</th>
											<td><s:select id="completionMarkId" headerKey="-1" headerValue="--请选择--" list="%{completionMarkList}" listValue="name" listKey="id" value="%{taskDefinition.completionMark.id}" multiple="false" theme="simple">
												</s:select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">开始时间：</th>
											<td><input id="taskStartTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="taskStartTime" onclick="showTime('taskStartTime','yyyy-MM-dd')" value="<fmt:formatDate value='${taskDefinition.taskStartTime}' type='both' pattern='yyyy-MM-dd' />" type="text" size="16" /> <img
												onclick="showTime('taskStartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th align="right">结束时间：</th>
											<td><input id="taskEndTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="taskEndTime" onclick="showTime('taskEndTime','yyyy-MM-dd')" value="<fmt:formatDate value='${taskDefinition.taskEndTime}' type='both' pattern='yyyy-MM-dd' />" type="text" size="16" /> <img onclick="showTime('taskEndTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">完成时间：</th>
											<td><input id="endTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="endTime" onclick="showTime('endTime','yyyy-MM-dd')" value="<fmt:formatDate value='${taskDefinition.endTime}' type='both' pattern='yyyy-MM-dd' />" type="text" size="16" /> <img onclick="showTime('endTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th align="right">超期时间：</th>
											<td><input id="startTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="startTime" onclick="showTime('startTime','yyyy-MM-dd')" value="<fmt:formatDate value='${taskDefinition.startTime}' type='both' pattern='yyyy-MM-dd' />" type="text" size="16" /> <img onclick="showTime('startTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">有效期：</th>
											<td><input id="validity" name="validity" value="${taskDefinition.validity}" type="text" size="16" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
											<th align="right">任务权重：</th>
											<td><input id="taskWeights" name="taskWeights" value="${taskDefinition.taskWeights}" type="text" size="16" /></td>
										</tr>
										<tr>
											<th align="right">执行：</th>
											<td><input type="hidden" id="id" name="id" value="${taskDefinition.id}" /> <s:radio id="pubType" list="#{'O':'执行部门','E':'执行人'}" name="pubType" value="%{taskDefinition.pubType}" onchange="changePubType(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBulletinPubobject()"><img
													src="img/icon_25.gif" />新增</a>&nbsp;&nbsp; <a href="#" onclick="clearPubType()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="executiveAgency" name="executiveAgency" style="width: 796px; height: 103px;">${taskDefinition.executiveAgency}</textarea> <input type="hidden" id="pubIds" name="taskDefinition.pubIds"
												value="${taskDefinition.pubIds}" /></td>
										</tr>
										<tr>
											<th align="right">考核：</th>
											<td><input type="hidden" id="id" name="id" value="${taskDefinition.id}" /> <s:radio id="pubType1" list="#{'O':'考核部门','E':'考核人'}" name="pubType1" value="%{taskDefinition.pubType1}" onchange="changePubType1(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBulletinPubobject1()"> <img
													src="img/icon_25.gif" />新增
											</a>&nbsp;&nbsp; <a href="#" onclick="clearPubType1()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="assessDepartment" name="assessDepartment" style="width: 796px; height: 103px;">${taskDefinition.assessDepartment}</textarea> <input type="hidden" id="pubIds1" name="taskDefinition.pubIds1"
												value="${taskDefinition.pubIds1}" /></td>
										</tr>
										<tr>
											<th align="right">审核：</th>
											<td><input type="hidden" id="id" name="id" value="${taskDefinition.id}" /> <s:radio id="pubType2" list="#{'O':'审核部门','E':'审核人'}" name="pubType2" value="%{taskDefinition.pubType2}" onchange="changePubType2(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBulletinPubobject2()"><img
													src="img/icon_25.gif" />新增</a>&nbsp;&nbsp; <a href="#" onclick="clearPubType2()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="reviewDivision" name="reviewDivision" style="width: 796px; height: 103px;">${taskDefinition.reviewDivision}</textarea> <input type="hidden" id="pubIds2" name="taskDefinition.pubIds2"
												value="${taskDefinition.pubIds2}" /></td>
										</tr>
										<tr>
											<th align="right">更新时间：</th>
											<td><input id="updateTime" name="taskDefinition.updateTime" value="${taskDefinition.updateTime}" onchange="salesOrderFieldChanged(this);" type="text" class="validate[required] text-input" /> <img onclick="showTime('updateTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle">
											</td>
										</tr>
										<tr>
											<th align="right">状态：</th>
											<td><s:radio list="#{'0':'下达','1':'终止'}" name="isPublish" value="%{taskDefinition.isPublish}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<th align="right">任务描述：</th>
											<td colspan="3"><textarea id="taskDescription" name="taskDescription">${taskDefinition.taskDescription}</textarea> <script type="text/javascript">
													 var taskDefinitions = KindEditor.create('textarea[name="taskDescription"]',
														{basePath:'${vix}/plugin/KindEditor/',
															width:796,
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
									<tr>
										<td>
											<p>
												<a href="#" onclick="editEntity(0,$('#id').val());"><img src="img/icon_25.gif" /><span><s:text name='添加附件' /></span></a>
											</p>
										</td>
									</tr>
									<div>
										<table id="eq_sbwd_grid_table" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>'>
											<thead>
												<tr class="alt">
													<td>编号</td>
													<td>文档名称</td>
													<td>文档类型</td>
													<td>上传时间</td>
													<td>上传者</td>
													<td><s:text name="cmn_operate" /></td>
												</tr>
											</thead>
											<tbody>
												<% int count =0; %>
												<s:iterator value="pager.resultList" var="entity" status="s">
													<% count++; %>
													<tr>
														<td><a href="${vix}/oa/taskDefineAction!downloadEqDocument.action?id=${entity.id}">${entity.id}</a></td>
														<td><span style="color: gray;">${entity.title}</span></td>
														<td><span style="color: gray;">${entity.fileType}</span></td>
														<td><span style="color: gray;"><s:date name="#entity.uploadTime" format="yyyy-MM-dd HH:mm:ss" /></td>
														<td><span style="color: gray;">${entity.title}</span></td>
														<td>
															<div class="untitled" style="position: static; display: inline;">
																<a href="${vix}/oa/taskDefineAction!downloadEqDocument.action?id=${entity.id}">下载</a>
															</div>
														</td>
													</tr>
												</s:iterator>
												<%	/** 用于页面数据行数显示不全时，输出空行，使页面保存美观! */
													com.vix.core.web.Pager pager = (com.vix.core.web.Pager)request.getAttribute("pager");
													count = pager.getPageSize() - count;
													request.setAttribute("count",count);
												%>
												<c:forEach begin="1" end="${count}">
													<tr>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</dd>
							</dl>
						</div>
					</div>
			</dl>
		</div>
	</form>
</div>







