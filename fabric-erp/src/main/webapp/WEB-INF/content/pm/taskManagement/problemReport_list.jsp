<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="css/newservice.css" type="text/css" rel="stylesheet">
<link href="/common/css/asyncBox/asyncbox.css" type="text/css" rel="stylesheet" />
<link href="/common/css/asyncBox/skins/default.css" type="text/css" rel="stylesheet" />
<script src="js/WdatePicker.js" type="text/javascript"></script>
<script src="js/jquery.jBreadCrumb.1.1.js" type="text/javascript" language="JavaScript"></script>
<script src="js/jquery.jcarousel.min.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript" language="JavaScript"></script>
<script src="js/bar.js" type="text/javascript"></script>
<script src="js/all.js" type="text/javascript"></script>
<script src="js/jquery.ui.core.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.widget.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.mouse.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.resizable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.draggable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.position.min.js" type="text/javascript"></script>
<script src="js/newservice.js" type="text/javascript"></script>
<script src="js/asyncbox.v1.5.beta.min.js" type="text/javascript"></script>

<script>
	function showPop(ele) {
		console.log(ele, ({
		left : $(ele).offset().left - $('#addtodo').width(),
		top : $(ele).offset().top - 36
		}));
		$('#addtodo').css({
		left : $(ele).offset().left - $('#addtodoid').outerWidth(),
		top : $(ele).offset().top - 50
		});
		$('#addtodoid').show();
	}

	var numSN = 20;
	function addNewTaskBug() {
		$.post('${vix}/pm/problemReportAction!saveOrUpdate.action', {
		'problemReport.id' : $("#id").val(),
		'problemReport.name' : $("#name").val(),
		'problemReport.designees' : $("#designees").val(),
		'problemReport.createTime' : $("#createTime").val(),
		'problemReport.errorDate' : $("#errorDate").val(),
		'problemReport.status' : $("#status").val()
		}, function(result) {
			showMessage(result);
			setTimeout("", 1000);
			loadContent('${vix}/pm/problemReportAction!goList.action');
		});
	}

	function saveOrUpdate() {
	};
</script>
<!-- head -->
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pm_task.png" alt="" /> 项目管理</a></li>
				<li><a href="#">追踪管理</a></li>
				<li><a href="#">问题报告</a></li>
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
				<div class="t_btn">
					<a href="javascript:;" onclick="javascript:showAddToDoId(this);scrollInViewPart('addtodo');"><img src="img/list_icon_22.gif" style="vertical-align: middle;" /> 提交错误</a>
				</div>
				<div class="npr_form">
					<div class="npr_form_title">过滤条件</div>
					<p>
						所有人<br /> <select name="">
							<option value="1">所有</option>
							<option value="2">打开</option>
							<option value="3">关闭</option>
							<option value="4">无</option>
						</select>
					</p>
					<p>
						状态<br /> <select name="">
							<option value="1">所有</option>
							<option value="2">打开</option>
							<option value="3">关闭</option>
							<option value="4">无</option>
						</select>
					</p>
					<p>
						优先<br /> <select name="">
							<option value="1">所有</option>
							<option value="2">打开</option>
							<option value="3">关闭</option>
							<option value="4">无</option>
						</select>
					</p>
					<p>
						时间<br /> <select name="">
							<option value="1">所有</option>
							<option value="2">打开</option>
							<option value="3">关闭</option>
							<option value="4">无</option>
						</select>
					</p>
				</div>
			</div>
			<div id="addtodo">
				<form onsubmit="$('#addtodoid').hide();return false;" method="POST" class="formParam">
					<input type="hidden" id="id" name="id" value="${problemReport.id}" />
					<div id="addtodoid" onmouseup="sE(event);" onclick="sE(event);" class="txtSmall qcdiv w320">
						<div class="zt">
							<img border="0" class="zprarrow" src="img/spacer.gif">
						</div>
						<table width="100%" class="qulbl">
							<tbody>
								<tr>
									<td valign="top" class="pL10 pb1" colspan="2"><div class="fl pt5 requiredField">问题报告</div></td>
								</tr>
								<tr>
									<td class="pL10 pb5" colspan="2"><textarea id="name" name="name" class="logtimeaddtext w265px h70" style="width: 282px;"></textarea></td>
								</tr>
								<tr>
									<td valign="top" class="pL10 pt10 pb1" colspan="2">项目列表</td>
								</tr>
								<tr>
									<td class="pl10 pb5" colspan="2"><div id="tlistdispid">
											<select id="projectName" name="projectName" class="logtimeaddselect w290">
												<option value="extgeneral">常规</option>
											</select>
										</div></td>
								</tr>
								<tr>
									<td valign="top" class="pL10 pt10 pb1" colspan="2">指定人</td>
								</tr>
								<tr>
									<td style="width: 290px;" class="pl10 pb5" colspan="2"><div style="width: 290px;" id="assignless">
											<select id="designees" name="designees" style="visibility: visible;" class="logtimeaddselect h23 w120">
												<optgroup label="项目成员">
													<option title="ritchiechu (tingzhuqi@hotmail.com) " value="张三">张三</option>
												</optgroup>
											</select> <span class="pL10"><a href="javascript:;" class="optionsLink">分配多个</a> </span>
										</div></td>
								</tr>
								<tr>
									<td width="150" valign="top" class="pL10 pt10 pb1" id="stlbl">创建日期</td>
									<td width="150" valign="top" class="pT10 pb1"><span id="dueid">报错日期</span><span class="hide" id="durid">持续时间</span></td>
								</tr>
								<tr>
									<td valign="top" class="pL10 pb5"><input id="createTime" name="createTime" value="${problemReport.createTime}" type="text" class="logtimeaddtext w115 h17" style="font-size: 11px; width: 116px;"> <img width="18" height="18" border="0" align="absmiddle" class="zpdateIcon" src="img/spacer.gif"
										style="position: relative; right: 22px; bottom: 0px;" title="点击选择开始日期" id="task_st_cal" onclick="showTime('createTime','yyyy-MM-dd');"></td>
									<td valign="top" class="pb5"><div id="taskenddateopt">
											<table width="100%" cellspacing="0" cellpadding="0" border="0">
												<tbody>
													<tr>
														<td><input type="text" class="logtimeaddtext w115 h17" style="font-size: 11px; width: 116px;" readonly="" id="errorDate"> <img width="18" height="18" border="0" align="absmiddle" class="zpdateIcon" src="img/spacer.gif" style="position: relative; right: 22px; bottom: 0px;" title="点击选择结束日期" id="task_end_cal"
															onclick="showTime('errorDate','yyyy-MM-dd');"></td>
													</tr>
												</tbody>
											</table>
											<div style="position: absolute;">
												<a href="javascript:;" class="qcsublink" onclick="$('#taskdurationopt').show();$('#taskenddateopt').hide();$('#dueid').text('持续时间');"> 输入周期 </a>
											</div>
										</div>
										<div id="taskdurationopt" class="hide">
											<table width="100%" cellspacing="0" cellpadding="0" border="0">
												<tbody>
													<tr>
														<td><input type="text" id="duration" size="5" class="logtimeaddtext w60px h17"> &nbsp;<span class="qcsubtxt">天 </span> <input type="hidden" value="days"></td>
													</tr>
												</tbody>
											</table>
											<div style="position: absolute;">
												<a href="javascript:;" class="qcsublink" onclick="$('#taskdurationopt').hide();$('#taskenddateopt').show();$('#dueid').text('结束日期');"> 选择结束日期 </a>
											</div>
										</div></td>
								</tr>
								<tr>
									<td valign="top" class="pL10 pt15 pb1">优先级</td>
									<td valign="top" class="pL10 pt15 pb1">状态</td>
								</tr>
								<tr>
									<td valign="top" class="pL10 pb5"><select style="visibility: visible; width: 121px;" id="priority" class="logtimeaddselect h23 w120">
											<option value="1级">1级</option>
											<option value="2级">2级</option>
											<option value="3级">3级</option>
									</select></td>
									<td valign="top" class="pL10 pb5"><select style="visibility: visible; width: 121px;" id="status" class="logtimeaddselect h23 w120">
											<option value="已处理">已处理</option>
											<option value="未处理" selected="selected">未处理</option>
									</select></td>
								</tr>
								<tr>
									<td align="left" class="pt5 pb5 btdot pl10" colspan="2"><div class="txtDisabled pb5">
											<input type="checkbox" onclick="" checked="" value="yes" id="notifyuser"> 发送邮件通知
										</div> <input type="submit" onclick="javascript:return addNewTaskBug()" class="buttonNew" value="添加任务"> &nbsp;&nbsp; <a href="javascript:;" class="optionsLink" onclick="$('#addtodoid').hide();return false;">取消</a> &nbsp;&nbsp;<span id="zoho_add_ttask_busy" class="hide"><img border="0" align="absmiddle" src="img/zoho-busy.gif">
									</span></td>
								</tr>
							</tbody>
						</table>
					</div>
				</form>
			</div>
		</div>
		<div class="np_left clearfix" id="common_table">
			<div class="np_left_title">
				<h2>问题报告</h2>
			</div>
			<dl class="task_list">
				<dt>
					<ul class="np_tab">
						<li class="current"><a onclick="javascript:tab(2,1,'np',event)" href="javascript:;">错误报告</a></li>
						<li><a onclick="javascript:tab(2,2,'np',event)" href="javascript:;">错误指定</a></li>
					</ul>
				</dt>
				<dd id="np1">
					<table width="100%">
						<thead>
							<tr>
								<th>&nbsp;</th>
								<th>&nbsp;</th>
								<th>错误标题</th>
								<th></th>
								<th>项目</th>
								<th>创建时间</th>
								<th>状态</th>
								<th>报错日期</th>
							</tr>
						</thead>
						<tbody id="list_body">
							<s:if test="problemReportList.size > 0">
								<s:iterator value="problemReportList" var="entity" status="s">
									<tr>
										<td>${entity.id }</td>
										<td><img align="absmiddle" src="img/spacer.gif" class="imgbottom_gray pointer" onclick="javascript:saveOrUpdate();" /></td>
										<td><a href="#">${entity.name }</a></td>
										<td><img align="absmiddle" src="img/spacer.gif" class="imgqbz pointer" onclick="javascript:saveOrUpdate();" /></td>
										<td>${entity.project.projectName }</td>
										<td class="cursor"><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd" /><span style="visibility: hidden;" class="morearrow">▼</span></td>
										<td id="status-link_19491001"><a onclick="openOptions('19491001','status','statusdiv','status-link_');" id="status_span_19491001" class="hn" href="javascript:;">${entity.status }&nbsp;<span style="visibility: hidden;" class="morearrow">▼</span>
										</a></td>
										<td><fmt:formatDate value="${entity.errorDate }" pattern="yyyy-MM-dd" /><span style="visibility: hidden;" class="morearrow">▼</span></td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<c:forEach begin="1" end="10">
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</c:forEach>
							</s:else>
						</tbody>
					</table>
				</dd>
				<dd id="np2" style="display: none;">
					<table width="100%">
						<tr>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<th>错误标题</th>
							<th></th>
							<th>项目</th>
							<th>创建时间</th>
							<th>状态</th>
							<th>指定人</th>
							<th>报错日期</th>
						</tr>
						<tbody id="list_body">
							<s:if test="problemReportList.size > 0">
								<s:iterator value="problemReportList" var="entity" status="s">
									<tr>
										<td>${entity.id }</td>
										<td><img align="absmiddle" src="img/spacer.gif" class="imgbottom_gray pointer" onclick="javascript:saveOrUpdate();" /></td>
										<td><a href="#">${entity.name }</a></td>
										<td><img align="absmiddle" src="img/spacer.gif" class="imgqbz pointer" onclick="javascript:saveOrUpdate();" /></td>
										<td>${entity.project.projectName }</td>
										<td class="cursor"><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd" /><span style="visibility: hidden;" class="morearrow">▼</span></td>
										<td id="status-link_19491001"><a onclick="openOptions('19491001','status','statusdiv','status-link_');" id="status_span_19491001" class="hn" href="javascript:;">${entity.status }&nbsp;<span style="visibility: hidden;" class="morearrow">▼</span>
										</a></td>
										<td id="owner-link_19491001"><a onclick="openOwnerOptions('19491001','owner','townerdiv','owner-link_');" id="owner_span_19491001" class="hn" href="javascript:;">${entity.designees }<span style="visibility: hidden;" class="morearrow">▼</span>
										</a></td>
										<td><fmt:formatDate value="${entity.errorDate }" pattern="yyyy-MM-dd" /><span style="visibility: hidden;" class="morearrow">▼</span></td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<c:forEach begin="1" end="10">
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</c:forEach>
							</s:else>
						</tbody>
					</table>
				</dd>
			</dl>
			<div onmouseup="sE(event);" onclick="sE(event);" id="townerdiv" class="txtSmall qcdiv w300">
				<div class="zt">
					<img border="0" class="zptarrow" src="img/spacer.gif">
				</div>
				<div style="width: 295px;" class="userslistdiv_new bgwhite pl3">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="txtSmall">
						<tbody>
							<tr>
								<td height="25" bgcolor="#f8f8f8" class="txtSmall pl5" colspan="2">项目成员</td>
							</tr>
							<tr>
								<td nowrap="nowrap" height="23" class="userminmax"><input type="checkbox" value="ritchiechu" class="chkbox"> <span>Tricis Boyic</span> &nbsp;&nbsp;<input type="checkbox" value="ritchiechu" class="chkbox"> <span>Charies Stone</span></td>
							</tr>
							<tr>
								<td height="5" colspan="2"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="pl3 pt10 pb10 clearLeft fl">
					<input type="button" class="buttonNew h21 w60px" onclick="javascript:jQuery('#townerdiv').hide();return false;" value="Submit"> &nbsp;&nbsp;<a onclick="javascript:jQuery('#townerdiv').hide();" class="optionsLink" href="javascript:;">Cancel</a>
				</div>
			</div>
			<div onmouseup="sE(event);" onclick="sE(event);" id="statusdiv" class="txtSmall qcdiv w100px">
				<div class="zt">
					<img border="0" class="zptarrow" src="img/spacer.gif">
				</div>
				<ul class="ulstyle">
					<li onclick="updateTextValues('status-link_','未处理','statusdiv');hideCommonList('statusdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">未处理</li>
					<li onclick="updateTextValues('status-link_','已处理','statusdiv');hideCommonList('statusdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">已处理</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>