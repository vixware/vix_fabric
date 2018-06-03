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
	
	/** 任务反馈*/
	function saveOrUpdateOrder(){
		
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/task/taskDefinition/feedbackAction!saveOrUpdate.action',
				{'executionFeedback.id':$("#id").val(),
				'executionFeedback.taskDefinition.id':$("#taskId").val(),
				 'executionFeedback.executionFeedback':executionFeedbacks.html(),
				 'executionFeedback.feedbackTime':$("#feedbackTime").val(),
				 'executionFeedback.name':$("#name").val(),
				 'executionFeedback.taskDefinition.schedule':$("#schedule").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/oa/task/taskDefinition/myTasksAction!goList.action?menuId=menuContract');
				} 
			);
		}else {
			return false;
		}
	}
	
	 $("#orderForm").validationEngine();

	
	/** 任务反馈*/
	function saveOrUpdateOrder1(){
		
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/task/taskDefinition/feedbackAction!saveOrUpdate.action',
				{'executionFeedback.id':$("#id").val(),
				'executionFeedback.taskDefinition.id':$("#taskId").val(),
				 'executionFeedback.executionFeedback':executionFeedbacks.html(),
				 'executionFeedback.feedbackTime':$("#feedbackTime").val(),
				 'executionFeedback.name':$("#name").val(),
				 'executionFeedback.taskDefinition.schedule':$("#schedule").val()
				},
				function(result){
				} 
			);
		}
	}
	
	$(function() {
	    //加载更新时间(新增)
	    if (document.getElementById("feedbackTime").value == "") {
		    var myDate = new Date();
		    $("#feedbackTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
	    }
	});
	
	
	function editEntity(id,executionFeedbackId){
		saveOrUpdateOrder1();
		$.ajax({
			  url:"${vix}/oa/feedbackAction!eqSbwdEdit.action?id="+id+"&executionFeedbackId="+executionFeedbackId,
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
									     url: "${vix}/oa/feedbackAction!saveEqSbwd.action",
									     dataType: "text",
									     success: function(result){
										loadContent ('${vix}/oa/feedbackAction!goSaveOrUpdate.action?id='+result);
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
				<li><a href="#">任务管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/myTasksAction!goList.action?pageNo=${pageNo}');">我的任务 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/myTasksAction!goList.action?pageNo=${pageNo}');">执行反馈</a></li>
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
						onclick="loadContent('${vix}/oa/task/taskDefinition/myTasksAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="反馈内容" /></b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<input type="hidden" id="taskId" name="taskId" value="${executionFeedback.taskDefinition.id}" />
										<input type="hidden" id="id" name="id" value="${executionFeedback.id}" />
										<tr>
											<th align="right">反馈时间：</th>
											<td><input id="feedbackTime" name="executionFeedback.feedbackTime" value="${executionFeedback.feedbackTime}" onchange="salesOrderFieldChanged(this);" type="text" class="validate[required] text-input" /> <img onclick="showTime('feedbackTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td align="right">进度情况:</td>
											<td><input id="name" name="name" value="${executionFeedback.name}" type="text" size="16" class="validate[required] text-input" />% <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th align="right">反馈内容：</th>
											<th colspan="3"><textarea id="executionFeedback" name="executionFeedback">${executionFeedback.executionFeedback}</textarea> <script type="text/javascript">
													 var executionFeedbacks = KindEditor.create('textarea[name="executionFeedback"]',
													{basePath:'${vix}/plugin/KindEditor/',
														width:796,
														height:245,
														cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
														uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
														fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
														allowFileManager : true 
													}
												 );
											  </script></th>
										</tr>
										<tr>
											<th align="right">提醒：</th>
											<td><label><input name="" type="checkbox" value="" class="checkbox" /> 发送事务提醒消息</label></td>
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
														<td><span style="color: gray;"><s:date name="#entity.uploadTime" format="yyyy-MM-dd" /></td>
														<td><span style="color: gray;">${entity.title}</span></td>
														<td style="padding-top: 2px;"><a href="javascript:void(0);" onclick="addBeijian('${entity.id}');" title="<s:text name='cmn_update'/>"> <img src="${vix}/common/img/icon_edit.png" />
														</a> <a href="javascript:void(0);" onclick="editEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_12.png" />
														</a>
															<div class="untitled" style="position: static; display: inline;">
																<span><img alt="" src="img/icon_untitled.png"> </span>
																<div class="popup" style="display: none; top: -7px;">
																	<strong> <b>${entity.title}</b>
																	</strong>
																	<p>${entity.note}</p>
																</div>
															</div></td>
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







