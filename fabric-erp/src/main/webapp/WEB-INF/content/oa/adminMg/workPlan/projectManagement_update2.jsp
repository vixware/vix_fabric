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
	
	
	 /* if('${null != meetingRequest.department }'){
		var chkTime = '${meetingRequest.allowedTime }'.split(",");
		for (var i=0;i<chkTime.length-1;i++){
			if ($("#allowedTime"+chkTime[i]).val() == chkTime[i]) {  
	            $("#allowedTime"+chkTime[i]).attr("checked", "checked");  
	        }
		}
	}  */
	
	/** 批注*/
	function saveOrUpdateOrder(){
		
		if($('#orderForm').validationEngine('validate')){
			$.post('${vix}/oa/projectManagementAction!saveOrUpdate2.action',
				{'postil.id':$("#id").val(),
				 'postil.ratifyTime':$("#ratifyTime").val(),
				 'postil.planContent':$("#planContent").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/oa/projectManagementAction!goList.action?menuId=menuContract');
				} 
			);
		}
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
				<li><a href="#"><img src="img/pm_task.png" alt="" /> 协同办公</a></li>
				<li><a href="#">行政办公</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/projectManagementAction!goList.action?pageNo=${pageNo}');">工作计划 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/projectManagementAction!goList.action?pageNo=${pageNo}');">工作计划管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/oa/projectManagementAction!goList.action?pageNo=${pageNo}');">批注 </a></li>
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
						onclick="loadContent('${vix}/oa/projectManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="批注记录" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th align="right">反馈时间：</th>
											<td><input id="ratifyTime" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="ratifyTime" value="<fmt:formatDate value='${postil.ratifyTime}' type='both' pattern='yyyy-MM-dd' />" type="text" size="26" /> <img onclick="showTime('ratifyTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">进度详细情：</th>
											<td colspan="3"><textarea id="planContent" name="planContent" class="example" rows="2" style="width: 1024px; height: 110px;">${postil.planContent }</textarea></td>
										</tr>
										<!-- <tr>
  											<th align="right">附件选择：</th>
											<td><input id=""
												name=""
												value="" type="text"
												size="26" /><span><a href="#"><img src="img/icon_25.gif" />添加附件</a>
																  <a href="#"><img src="img/icon_25.gif" />从文件柜和网络硬盘选择附件</a>
															</span>
											</td>
											<th align="right">提醒：</th>
										    <td><label> 
										    		<input name="" type="checkbox" value="" class="checkbox" />	发送事务提醒消息
												</label>
											</td>
									    </tr> -->
									</table>
								</dd>
							</dl>
						</div>
					</div>
			</dl>
		</div>
	</form>
</div>







