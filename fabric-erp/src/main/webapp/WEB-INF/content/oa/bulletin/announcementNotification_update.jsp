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
    if (document.getElementById("activeStartDate").value == "") {
	    var myDate = new Date();
	    $("#activeStartDate").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours()+ ":" + myDate.getMinutes()+ ":" + myDate.getSeconds());
    }
    
    
  //默认选择部门
    if($('input:radio[name=pubType]:checked').length==0){
    	$('input:radio[name=pubType]:first').trigger('click');
    }
  //默认选择发布
    if($('input:radio[name=isPublish]:checked').length==0){
    	$('input:radio[name=isPublish]:first').trigger('click');
    }
  //默认选择全体公告
    if($('input:radio[name=bulletinType]:checked').length==0){
    	$('input:radio[name=bulletinType]:first').trigger('click');
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
	
	/** 公告通知 */
	function saveOrUpdateOrder(){
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/announcementNotificationAction!saveOrUpdate.action',
				{
				'announcementNotification.id' : $("#id").val(),
				'announcementNotification.title':$("#title").val(),	
				'announcementNotification.pubNames':$("#pubNames").val(),	
				'announcementNotification.pubIds':$("#pubIds").val(),	
				'announcementNotification.bulletinType':$('input:radio[name=bulletinType]:checked').val(),		
				'announcementNotification.updateTime':$("#updateTime").val(),	
				'announcementNotification.activeStartDate':$("#activeStartDate").val(),	
				'announcementNotification.activeEndDate':$("#activeEndDate").val(),	
				'announcementNotification.plotSummary':$("#plotSummary").val(),
				'announcementNotification.isPublish':$('input:radio[name=isPublish]:checked').val(),
				'announcementNotification.pubType':$('input:radio[name=pubType]:checked').val(),
				'announcementNotification.content' : contents.html(),
				'announcementNotification.keywords':$("#keywords").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/oa/announcementNotificationAction!goList.action?menuId=menuContract');
				} 
			);
		}else {
			return false;
		}
	}
	
	/** 保存并新增培训计划 */
	function saveOrAdd(){
		$.post('${vix}/oa/announcementNotificationAction!saveOrUpdate.action',
		   {
			'announcementNotification.id' : $("#id").val(),
			'announcementNotification.title':$("#title").val(),	
			'announcementNotification.pubNames':$("#pubNames").val(),	
			'announcementNotification.pubIds':$("#pubIds").val(),	
			'announcementNotification.bulletinType':$('input:radio[name=bulletinType]:checked').val(),		
			'announcementNotification.updateTime':$("#updateTime").val(),	
			'announcementNotification.activeStartDate':$("#activeStartDate").val(),	
			'announcementNotification.activeEndDate':$("#activeEndDate").val(),	
			'announcementNotification.plotSummary':$("#plotSummary").val(),
			'announcementNotification.isPublish':$('input:radio[name=isPublish]:checked').val(),
			'announcementNotification.pubType':$('input:radio[name=pubType]:checked').val(),
			'announcementNotification.content' : contents.html(),
			'announcementNotification.keywords':$("#keywords").val()
		   },
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/oa/announcementNotificationAction!goSaveOrUpdate.action');
			} 
		);
	}
	
    $("#orderForm").validationEngine();
	
	function saveOrUpdateOrder1(){
			if($('#orderForm').validationEngine('validate')){
				$.post('${vix}/oa/announcementNotificationAction!saveOrUpdate.action',
					{
					'announcementNotification.id' : $("#id").val(),
					'announcementNotification.title':$("#title").val(),	
					'announcementNotification.pubIds':$("#pubIds").val(),	
					'announcementNotification.pubNames':$("#pubNames").val(),	
					'announcementNotification.bulletinType':$('input:radio[name=bulletinType]:checked').val(),		
					'announcementNotification.updateTime':$("#updateTime").val(),	
					'announcementNotification.activeStartDate':$("#activeStartDate").val(),	
					'announcementNotification.activeEndDate':$("#activeEndDate").val(),	
					'announcementNotification.plotSummary':$("#plotSummary").val(),
					'announcementNotification.isPublish':$('input:radio[name=isPublish]:checked').val(),
					'announcementNotification.pubType':$('input:radio[name=pubType]:checked').val(),
					'announcementNotification.content' : contents.html(),
					'announcementNotification.keywords':$("#keywords").val()
					},
					function(result){
					} 
				);
			}
		}
	
	
		/**
		 * 变更选择发布类型
		 */
		function changeBizOrgType(typeValue){
			clearPubType();
		}

		/**
		 * 清空选择对象
		 */
		function clearPubType(){
			$("#pubIds").val("");
			$("#pubNames").val("");
		}
	
		/**
		 * 添加发布对象
		 */
		function addBulletinPubobject(){
			
			var pubTypeVal = $("input[name=announcementNotification.pubType]:checked").val();
			//debugger;
			if(pubTypeVal=="O"){
				chooseBulletinOrgUnit($("#pubIds").val());
			}else if(pubTypeVal=="D"){
				addBoOrganizationUnit($("#pubIds").val());
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
							title:"选择分管部门",
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
											$("#pubNames").val(selectNames);
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
		 * 选择分销门店
		 */	
		  function addBoOrganizationUnit(){
				$.ajax({
					  url:'${vix}/common/select/commonSelectEmpDrpAction!goList.action',
					  data:{chkStyle:"checkbox"},
					  cache: false,
					  success: function(html){
						  asyncbox.open({
							 	modal:true,
								width : 760,
								height : 440,
								title:"选择分销门店人员",
								html:html,
								callback : function(action,returnValue){
									if(action == 'ok'){
										if(returnValue != undefined){
											//alert(returnValue);
											var selectIds = "";
											var selectNames = "";
											var result = returnValue.split(",");
											for (var i=0; i<result.length; i++){
												if(result[i].length>1){
													var v = result[i].split(":");
													selectIds +=  "," + v[0];
													selectNames +=  "," + v[1];
												}
											}
											if(selectIds!=''){
												$("#pubIds").val(selectIds+",");
												$("#pubNames").val(selectNames);
												
												$("#pubIds").val(selectIds+",");
												selectNames = selectNames.substring(1,selectNames.length);
												$("#pubNames").val(selectNames);
											}
											
										}
										
									}
								},
								btnsbar : $.btn.OKCANCEL
							});
					  }
				});
			} 
	
	
	
	
	/*
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
									
									/* if(resObj.length == 0 ){
										return;
									} */
									//debugger;
									var result = returnValue.split(",");
									for (var i=0; i<result.length; i++){
										if(result[i].length>1){
											var v = result[i].split(":");
											if(!pubIdTmp.contains(v[0]+",")){
												selectIds += v[0]+",";
												selectNames += "," + v[1];
											}
										}
									}
									
									selectIds = $("#pubIds").val()+ "," + selectIds;
									selectNames = $("#pubNames").val()+selectNames;
									
									$("#pubIds").val(selectIds);
									selectNames = selectNames.substring(1,selectNames.length);
									$("#pubNames").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	
/**
 * 上传附件
 */
function editEntity(id,announcementNotificationId){
	//alert(announcementNotificationId)
		saveOrUpdateOrder1();
		$.ajax({
			  url:"${vix}/oa/announcementNotificationAction!eqSbwdEdit.action?id="+id+"&announcementNotificationId="+announcementNotificationId,
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
									     url: "${vix}/oa/announcementNotificationAction!saveEqSbwd.action",
									     dataType: "text",
									     success: function(result){
										loadContent ('${vix}/oa/announcementNotificationAction!goSaveOrUpdate.action?id='+result);
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
				<li><a href="#"><img src="${vix}/common/img/oa/oa_notice.png" alt="" />
					<s:text name="supplyChain" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/announcementNotificationAction!goList.action?pageNo=${pageNo}');"><s:text name="oa_xtbg" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/announcementNotificationAction!goList.action?pageNo=${pageNo}');"><s:text name="行政办公" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/announcementNotificationAction!goList.action?pageNo=${pageNo}');"><s:text name="oa_announcement_notification" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/announcementNotificationAction!goList.action?pageNo=${pageNo}');"><s:text name="新增公告通知" /></a></li>
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
						onclick="loadContent('${vix}/oa/announcementNotificationAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="编辑公告通知" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<input type="hidden" id="announcementNotificationId" name="announcementNotificationId" value="${noticeUploader.announcementNotification.id}" />
										<input type="hidden" id="id" name="id" value="${announcementNotification.id}" />
										<tr>
											<th align="right">发布时间：</th>
											<td><input id="activeStartDate" name="announcementNotification.activeStartDate" value="${announcementNotification.activeStartDate}" onchange="salesOrderFieldChanged(this);" type="text" size="26" class="validate[required] text-input" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">主题：</th>
											<td><input id="title" name="title" value="${announcementNotification.title}" type="text" size="26" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td class="right">发布对象：</td>
											<td><input type="hidden" name="id" value="${announcementNotification.id}" /> <s:radio id="pubType" list="#{'O':'部门','D':'分支结构','E':'人员'}" name="pubType" value="%{announcementNotification.pubType}" onchange="changeBizOrgType(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBulletinPubobject()"><img
													src="img/icon_25.gif" />新增</a>&nbsp;&nbsp; <a href="#" onclick="clearPubType()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="pubNames" name="pubNames" style="width: 931px; height: 114px;">${announcementNotification.pubNames}</textarea> <span style="color: red;">*</span> <input type="hidden" id="pubIds"
												name="pubIds" value="${announcementNotification.pubIds}" /></td>
										</tr>
										<tr>
											<th align="right">类型：</th>
											<td><s:radio list="#{'0':'全体公告','1':'部门公告','2':'行政公告','3':'通知'}" id="bulletinType" name="bulletinType" value="%{announcementNotification.bulletinType}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<th align="right">结束时间：</th>
											<td><input id="activeEndDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="activeEndDate" onclick="showTime('activeEndDate','yyyy-MM-dd')" value="<fmt:formatDate value='${announcementNotification.activeEndDate}' type='both' pattern='yyyy-MM-dd' />" type="text" size="26" /> <span style="color: red;">*</span> <img
												onclick="showTime('activeEndDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">提醒：</th>
											<td><label><input name="" type="checkbox" value="" class="checkbox" /> 发送事务提醒消息</label></td>
										</tr>
										<tr>
											<th align="right">内容简介：</th>
											<td colspan="3"><textarea maxlength="30" id="plotSummary" name="plotSummary" class="example" rows="2" style="width: 931px; height: 123px;">${announcementNotification.plotSummary }</textarea>（最多输入30个字）</td>
										</tr>
										<tr>
											<th align="right">状态：</th>
											<td><s:radio list="#{'0':'终止','1':'发布'}" name="isPublish" value="%{announcementNotification.isPublish}" theme="simple"></s:radio></td>
										</tr>
										<tr>
											<th align="right">公告内容：</th>
											<td colspan="3"><textarea id="content" name="content">${announcementNotification.content}</textarea> <script type="text/javascript">
													 var contents = KindEditor.create('textarea[name="content"]',
														{basePath:'${vix}/plugin/KindEditor/',
															width:931,
															height:281,
															cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
															uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
															fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
															allowFileManager : true 
														}
													 );
												 </script></td>
										</tr>
										<tr>
											<th align="right">关键字：</th>
											<td colspan="3"><textarea id="keywords" name="keywords" class="example" rows="2" style="width: 931px; height: 74px;">${announcementNotification.keywords }</textarea></td>
										</tr>
									</table>
									<tr>
										<td>
											<p>
												<a href="#" onclick="editEntity(0,$('#id').val());"><img src="img/icon_25.gif" /><span><s:text name='添加附件' /></span></a>
											</p>
										</td>
									</tr>
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
													<td><a href="${vix}/oa/announcementNotificationAction!downloadEqDocument.action?id=${entity.id}">${entity.title}</a></td>
													<td><span style="color: gray;">${entity.title}</span></td>
													<td><span style="color: gray;">${entity.fileType}</span></td>
													<td><span style="color: gray;"><s:date name="#entity.uploadTime" format="yyyy-MM-dd" /></td>
													<td><span style="color: gray;">${entity.uploadPersonName}</span></td>
													<td>
														<div class="untitled" style="position: static; display: inline;">
															<a href="${vix}/oa/announcementNotificationAction!downloadEqDocument.action?id=${entity.id}">下载</a>
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
								</dd>
							</dl>
						</div>
					</div>
			</dl>
		</div>
	</form>
</div>







