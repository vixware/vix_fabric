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

$(function() {
    //加载发布时间(新增)
    if (document.getElementById("initiateTime").value == "") {
	    var myDate = new Date();
	    $("#initiateTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours()+ ":" + myDate.getMinutes()+ ":" + myDate.getSeconds());
    }
    //默认选择部门
    if($('input:radio[name=pubType]:checked').length==0){
    	$('input:radio[name=pubType]:first').trigger('click');
    }
  
    //默认选择计划类型
    if($('input:radio[name=workPlan]:checked').length==0){
    	$('input:radio[name=workPlan]:first').trigger('click');
    }
    //默认选择生效
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
	
	
	/** 工作计划 */
	function saveOrUpdateOrder(){
		
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/projectManagementAction!saveOrUpdate.action',
				{'projectManagement.id':$("#id").val(),
				 'projectManagement.proposalTitle':$("#proposalTitle").val(),
				 'projectManagement.workPlan':$('input:radio[name=workPlan]:checked').val(),
				 'projectManagement.pubType':$('input:radio[name=pubType]:checked').val(),
				 'projectManagement.pubIds' : $("#pubIds").val()+',',
				 'projectManagement.isPublish':$('input:radio[name=isPublish]:checked').val(),
				 'projectManagement.bizOrgNames':$("#bizOrgNames").val(),	
				 'projectManagement.bizOrgNames1':$("#bizOrgNames1").val(),	
				 'projectManagement.bizOrgNames2':$("#bizOrgNames2").val(),	
				 'projectManagement.enclosure':$("#enclosure").val(),	
				 'projectManagement.remark':$("#remark").val(),	
				 'projectManagement.initiateTime':$("#initiateTime").val(),	
				 'projectManagement.overTime':$("#overTime").val(),	
				 'projectManagement.planContent' : planContents.html()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/oa/projectManagementAction!goList.action?menuId=menuContract');
				} 
			);
		}else {
			return false;
		}
	}
	
	/** 保存并新增工作计划 */
	function saveOrAdd(){
		$.post('${vix}/oa/projectManagementAction!saveOrUpdate.action',
			{'projectManagement.id':$("#id").val(),
			 'projectManagement.proposalTitle':$("#proposalTitle").val(),
			 'projectManagement.workPlan':$('input:radio[name=workPlan]:checked').val(),
			 'projectManagement.pubType':$('input:radio[name=pubType]:checked').val(),
			 'projectManagement.pubIds' : $("#pubIds").val()+',',
			 'projectManagement.isPublish':$('input:radio[name=isPublish]:checked').val(),
			 'projectManagement.bizOrgNames':$("#bizOrgNames").val(),	
			 'projectManagement.bizOrgNames1':$("#bizOrgNames1").val(),	
			 'projectManagement.bizOrgNames2':$("#bizOrgNames2").val(),	
			 'projectManagement.enclosure':$("#enclosure").val(),	
			 'projectManagement.remark':$("#remark").val(),	
			 'projectManagement.initiateTime':$("#initiateTime").val(),	
			 'projectManagement.overTime':$("#overTime").val(),	
			 'projectManagement.planContent' : planContents.html()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/oa/projectManagementAction!goSaveOrUpdate.action');
			} 
		);
	}
	
	 $("#orderForm").validationEngine();
	
	/**
	 * 添加发布对象
	 */
	function addBizOrgObject(){
		
		var pubTypeVal = $("input[name=projectManagement.pubType]:checked").val();
		if(pubTypeVal=="O"){
			chooseBizOrgUnit($("#pubIds").val());
		}else if(pubTypeVal=="R"){
			chooseBizRole($("#pubIds").val());
		}else if(pubTypeVal=="E"){
			chooseBizEmp($("#pubIds").val());
		}
	}

	/**
	 * 清空选择对象
	 */
	function clearBizOrgType(){
		$("#pubIds").val('');
		$("#bizOrgNames").val('');
	}

	/**
	 * 变更选择发布类型
	 */
	function changeBizOrgType(typeValue){
		clearBizOrgType();
	}
	
	/* 参与人 */

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
					title:"参与人",
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
								$("#bizOrgNames1").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}


	
	/* 负责人 */
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
					title:"选择负责人",
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
								$("#bizOrgNames2").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
} 

	
	

	/**
	 * 选择部门
	 */
	 function chooseBizOrgUnit(){
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
											$("#bizOrgNames").val(selectNames);
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
	 * 选择角色
	 */
	 function chooseBizRole(checkObjIds){
			$.ajax({
				  url:'${vix}/common/select/commonSelectRoleAction!goList.action',
				  data:{chkStyle:"checkbox"},
				  cache: false,
				  success: function(html){
					  asyncbox.open({
						 	modal:true,
							width : 560,
							height : 340,
							title:"选择角色",
							html:html,
							callback : function(action,returnValue){
								if(action == 'ok'){
									if(returnValue != undefined){
										//alert(returnValue);
										var selectIds = "";
										var selectNames = "";

										var pubIdTmp = $("#pubIds").val();
										
										pubIdTmp = pubIdTmp + ",";
										
										/* if(resObj.length == 0 ){
											return;
										} */
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
										selectNames = $("#bizOrgNames").val()+selectNames;
										
										$("#pubIds").val(selectIds);
										selectNames = selectNames.substring(1,selectNames.length);
										$("#bizOrgNames").val(selectNames);
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
	 function chooseBizEmp(checkObjIds){
			$.ajax({
				  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
				  data:{chkStyle:"checkbox"},
				  cache: false,
				  success: function(html){
					  asyncbox.open({
						 	modal:true,
							width : 560,
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
										
										selectIds = $("#pubIds").val()+selectIds;
										selectNames = $("#bizOrgNames").val()+selectNames;
										
										$("#pubIds").val(selectIds);
										selectNames = selectNames.substring(1,selectNames.length);
										$("#bizOrgNames").val(selectNames);
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
				<li><a href="#"><img src="img/oa/oa_planned.png" alt="" /> 协同办公</a></li>
				<li><a href="#">行政办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/projectManagementAction!goList.action?pageNo=${pageNo}');">工作计划 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/projectManagementAction!goList.action?pageNo=${pageNo}');">工作计划管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/projectManagementAction!goList.action?pageNo=${pageNo}');">新增工作计划 </a></li>
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
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="javascript:void(0)" onclick="saveOrAdd()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/oa/projectManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新建工作计划" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">开始时间：</th>
											<td><input id="initiateTime" name="projectManagement.initiateTime" value="${projectManagement.initiateTime}" onchange="salesOrderFieldChanged(this);" type="text" class="validate[required] text-input" /> <img onclick="showTime('initiateTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">名称：</th>
											<td><input id="proposalTitle" name="proposalTitle" value="${projectManagement.proposalTitle}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th class="tr">发布范围：</th>
											<td colspan="3"><input type="hidden" id="id" name="id" value="${projectManagement.id}" /> <s:radio id="pubType" list="#{'O':'部门','R':'角色','E':'人员'}" name="pubType" value="%{projectManagement.pubType}" onchange="changeBizOrgType(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBizOrgObject()"><img
													src="img/icon_25.gif" />新增</a>&nbsp;&nbsp; <a href="#" onclick="clearBizOrgType()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="bizOrgNames" name="bizOrgNames" style="width: 820px; height: 65px;">${projectManagement.bizOrgNames}</textarea> <input type="hidden" id="pubIds" name="projectManagement.pubIds"
												value="${projectManagement.pubIds}" /></td>
										</tr>
										<tr>
											<th align="right">状态：</th>
											<td><s:radio list="#{'0':'生效','1':'终止'}" name="isPublish" value="%{projectManagement.isPublish}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<th align="right">备注：</th>
											<td colspan="3"><textarea maxlength="30" id="remark" name="remark" class="example" rows="2" style="width: 698px; height: 40px;">${projectManagement.remark }</textarea>（最多输入30个字）</td>
										</tr>
										<tr>
											<th align="right">计划类型：</th>
											<td><s:radio list="#{'0':'今日计划','1':'本周计划','2':'本月计划'}" name="workPlan" value="%{projectManagement.workPlan}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<th align="right">结束时间：</th>
											<td><input id="overTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm:ss" name="overTime" value="<fmt:formatDate value='${projectManagement.overTime}' type='both' pattern='yyyy-MM-dd HH:mm:ss' />" type="text" size="26" onclick="showTime('overTime','yyyy-MM-dd HH:mm:ss')" /> <img
												onclick="showTime('overTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">提醒：</th>
											<td><label> <input name="" type="checkbox" value="" class="checkbox" /> 发送事务提醒消息
											</label></td>
										</tr>
										<tr>
											<th align="right">计划内容：</th>
											<td colspan="3"><textarea id="planContent" name="planContent">${projectManagement.planContent}</textarea> <script type="text/javascript">
												 var planContents = KindEditor.create('textarea[name="planContent"]',
													{basePath:'${vix}/plugin/KindEditor/',
														width:825,
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







