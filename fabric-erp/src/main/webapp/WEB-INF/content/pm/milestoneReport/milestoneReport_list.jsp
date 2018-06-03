<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link href="css/newservice.css" type="text/css" rel="stylesheet">
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/WdatePicker.js" type="text/javascript"></script>
<script src="js/jquery.jBreadCrumb.1.1.js" type="text/javascript" language="JavaScript"></script>
<script src="js/jquery.jcarousel.min.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript" language="JavaScript"></script>
<script src="js/bar.js" type="text/javascript"></script>
<script src="js/jquery.ui.core.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.widget.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.mouse.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.resizable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.draggable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.position.min.js" type="text/javascript"></script>
<script src="js/newservice.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.scrollfollow.js"></script>
<script type="text/javascript">
	function addNewTaskBug() {
		$.post('${vix}/pm/milestoneReportAction!saveOrUpdate.action', {
		'milestoneReport.id' : $("#id").val(),
		'milestoneReport.name' : $("#name").val(),
		'milestoneReport.projectMembers' : $("#projectMembers").val(),
		'milestoneReport.priority' : $("#priority").val(),
		'milestoneReport.startTime' : $("#startTime").val(),
		'milestoneReport.endTime' : $("#endTime").val(),
		'milestoneReport.status' : $("#status").val()
		}, function(result) {
			showMessage(result);
			setTimeout("", 1000);
			loadContent('${vix}/pm/milestoneReportAction!goList.action');
		});
	}
</script>
<!-- head -->
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pm_task.png" alt="" />项目管理</a></li>
				<li><a href="#">追踪管理</a></li>
				<li><a href="#">里程碑报告</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
<div id="addtodo">
	<form onsubmit="$('#addtodoid').hide();return false;" method="POST" class="formParam">
		<input type="hidden" id="id" name="id" value="${milestoneReport.id}" />
		<div id="addtodoid" onmouseup="sE(event);" onclick="sE(event);" class="txtSmall qcdiv w320">
			<div class="zt">
				<img border="0" class="zprarrow" src="img/spacer.gif">
			</div>
			<table width="100%" class="qulbl">
				<tbody>
					<tr>
						<td valign="top" class="pL10 pb1" colspan="2"><div class="fl pt5 requiredField">里程碑报告</div></td>
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
						<td valign="top" class="pL10 pt10 pb1" colspan="2">成员</td>
					</tr>
					<tr>
						<td style="width: 290px;" class="pl10 pb5" colspan="2"><div style="width: 290px;" id="assignless">
								<select id="projectMembers" name="projectMembers" style="visibility: visible;" class="logtimeaddselect h23 w120">
									<optgroup label="项目成员">
										<option title="ritchiechu (tingzhuqi@hotmail.com) " value="张三">张三</option>
									</optgroup>
								</select> <span class="pL10"><a href="javascript:;" class="optionsLink">分配多个</a> </span>
							</div></td>
					</tr>
					<tr>
						<td width="150" valign="top" class="pL10 pt10 pb1" id="stlbl">开始日期</td>
						<td width="150" valign="top" class="pT10 pb1"><span id="dueid">结束日期</span><span class="hide" id="durid">持续时间</span></td>
					</tr>
					<tr>
						<td valign="top" class="pL10 pb5"><input id="startTime" name="startTime" value="${milestoneReport.startTime}" type="text" class="logtimeaddtext w115 h17" style="font-size: 11px; width: 116px;"> <img width="18" height="18" border="0" align="absmiddle" class="zpdateIcon" src="img/spacer.gif"
							style="position: relative; right: 22px; bottom: 0px;" title="点击选择开始日期" id="task_st_cal" onclick="showTime('startTime','yyyy-MM-dd');"></td>
						<td valign="top" class="pb5"><div id="taskenddateopt">
								<table width="100%" cellspacing="0" cellpadding="0" border="0">
									<tbody>
										<tr>
											<td><input type="text" class="logtimeaddtext w115 h17" style="font-size: 11px; width: 116px;" readonly="" id="endTime"> <img width="18" height="18" border="0" align="absmiddle" class="zpdateIcon" src="img/spacer.gif" style="position: relative; right: 22px; bottom: 0px;" title="点击选择结束日期" id="task_end_cal"
												onclick="showTime('endTime','yyyy-MM-dd');"></td>
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
<div class="content content_np" onclick="hideMPMenu(event);" onmouseup="hideSubMenu(event);" id="layoutdiv">
	<table width="100%">
		<tr>
			<td valign="bottom" colspan="3"><div id="projectcontent">
					<div style="position: absolute; z-index: 1;" id="updatetaskdiv"></div>
					<div id="task_contpage">
						<table width="100%" cellspacing="0" cellpadding="0" border="0">
							<tbody>
								<tr>
									<td valign="top" class="tmp25 pb45">
										<!-- TaskList Display -->
										<div class="np_left clearfix" id="common_table" style="margin: 0; padding: 10px 10px 10px 20px;">
											<div class="np_left_title">
												<h2>里程碑报告</h2>
												<p>
													<!-- <a href="javascript:;" onclick="javascript:reorderTaskList();">Reorder Task List</a> -->
													<a id="cclmnid" href="javascript:;" onclick="setPosDiv('cclmnid','customcolumn');">自定义列</a><span class="morearrow">▼</span>
												</p>
												<div id="customcolumn" onmouseup="sE(event);" onclick="sE(event);" style="position: absolute; z-index: 200001; text-align: left; display: none;">
													<form>
														<div style="position: relative; top: 5px; left: 170px;">
															<img border="0" class="zptarrow" src="img/spacer.gif">
														</div>
														<div class="txtSmall qcdiv w250">
															<div class="txtSmall lh20 pl5 pb5">任务列表包含以下字段，选定字段将显示在任务列表视图中。</div>
															<ul class="ulstyle">
																<li class="pt5 pb5 pl5 taskDescRow bdrbtm4"><input type="checkbox" class="ml0 mr5" id="5" onclick="fieldToggle(this.id);" value="" checked=""> 优先级</li>
																<li class="pt5 pb5 pl5 taskDescRowHover bdrbtm4"><input type="checkbox" class="ml0 mr5" checked="" id="6" onclick="fieldToggle(this.id);" value=""> 开始时间</li>
																<li class="pt5 pb5 pl5 taskDescRow bdrbtm4"><input type="checkbox" class="ml0 mr5" checked="" id="7" onclick="fieldToggle(this.id);" value=""> 结束时间</li>
																<li class="pt5 pb5 pl5 taskDescRowHover bdrbtm4"><input type="checkbox" class="ml0 mr5" checked="" id="8" onclick="fieldToggle(this.id);" value=""> 持续时间</li>
																<li class="pt5 pb5 pl5 taskDescRowHover bdrbtm4"><input type="checkbox" class="ml0 mr5" checked="" id="9" onclick="fieldToggle(this.id);" value=""> 状态</li>
																<li class="pt5 pb5 pl5 taskDescRow bdrbtm4"><input type="checkbox" class="ml0 mr5" checked="" id="10" onclick="fieldToggle(this.id);" value=""> 创建者</li>
																<li class="pt5 pb5 pl5 taskDescRowHover bdrbtm4"><input type="checkbox" class="ml0 mr5" checked="" id="11" onclick="fieldToggle(this.id);" value=""> 百分比（%）</li>
															</ul>
														</div>
													</form>
												</div>
											</div>
											<dl class="task_list">
												<dt>
													<div class="np_dt_t">即将到来里程碑</div>
													<!-- <p>in <span class="red">Accounts Receivabies</span> on Tue,31 May.2001 for Jasmine Frank</p> -->
													<b></b>
												</dt>
												<dd>
													<table width="100%">
														<tr>
															<th width="14">&nbsp;</th>
															<th width="20">&nbsp;</th>
															<th>标题</th>
															<th>全部成员</th>
															<th width="70">优先级</th>
															<th width="90">开始时间</th>
															<th width="90">结束时间</th>
															<th width="60">持续时间</th>
															<th width="70">状态</th>
															<th width="90">创建者</th>
															<th width="90">%</th>
														</tr>
														<tbody id="list_body">
															<s:if test="milestoneReportList.size > 0">
																<s:iterator value="milestoneReportList" var="entity" status="s">
																	<tr>
																		<td><img src="img/list_icon_08.gif" /></td>
																		<td><input name="" type="checkbox" value="" /></td>
																		<td><img src="img/list_icon_24.gif" />${entity.name }</td>
																		<td id="owner-link_19491001"><a href="javascript:;" class="hn" id="owner_span_19491001" onclick="openOwnerOptions('19491001','owner','townerdiv','owner-link_');">${entity.projectMembers
																				} <span class="morearrow" style="visibility: hidden;">▼</span>
																		</a></td>
																		<td id="priority-link_19491001"><a href="javascript:;" class="hn" id="priority_span_19491001" onclick="openOptions('19491001','priority','prioritydiv','priority-link_');">${entity.priority
																				}&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span>
																		</a></td>
																		<td id="sdatecal_19491001" onclick="showTime('sdate_19491001','dd/MM/yy');" class="cursor"><fmt:formatDate value="${entity.startTime
																			}" pattern="yyyy-MM-dd" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
																		<td id="edatecal_19491001" onclick="showTime('edate_19491001','dd/MM/yy');"><fmt:formatDate value="${entity.endTime }" pattern="yyyy-MM-dd" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
																		<td id="dur-link_19491001" onclick="openOptions('19491001','duration','durdiv','dur-link_');" class="cursor">${entity.durationDate }<span class="morearrow" style="visibility: hidden;">▼</span></td>
																		<td id="status-link_19491001"><a href="javascript:;" class="hn" id="status_span_19491001" onclick="openOptions('19491001','status','statusdiv','status-link_');">${entity.status } &nbsp;<span class="morearrow" style="visibility: hidden;">▼</span>
																		</a></td>
																		<td>${entity.creator }</td>
																		<td id="percomp-link_19491001" onclick="openOptions('19491001','percomp','percompdiv','percomp-link_');">
																			<div id="perdisp_19491001" class="w60px cursor" title="10% 完成">
																				<div class="w50px fl">
																					<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																					<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																				</div>
																				<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																			</div>
																		</td>
																	</tr>
																</s:iterator>
															</s:if>
														</tbody>
														<tr>
															<td colspan="2"></td>
															<td colspan="9" class="lastLine"><p class="npb_link">
																	<a href="javascript:;" onclick="javascript:showAddToDoId(this);scrollInViewPart('addtodo');">添加任务</a>&nbsp;&nbsp;&nbsp;<a href="#">重新排序</a>
																</p></td>
														</tr>
													</table>
												</dd>
											</dl>
											<dl class="task_list">
												<dt>
													<div class="np_dt_t">完成里程碑</div>
													<!-- <p>in <span class="red">Accounts Receivabies</span> on Tue,31 May.2001 for Jasmine Frank</p> -->
													<b></b>
												</dt>
												<dd>
													<table width="100%">
														<tr>
															<th width="14">&nbsp;</th>
															<th width="20">&nbsp;</th>
															<th>标题</th>
															<th>全部成员</th>
															<th width="70">优先级</th>
															<th width="90">开始时间</th>
															<th width="90">结束时间</th>
															<th width="60">持续时间</th>
															<th width="70">状态</th>
															<th width="90">创建者</th>
															<th width="90">%</th>
														</tr>
														<tbody id="list_body">
															<s:if test="milestoneReportList.size > 0">
																<s:iterator value="milestoneReportList" var="entity" status="s">
																	<tr>
																		<td><img src="img/list_icon_08.gif" /></td>
																		<td><input name="" type="checkbox" value="" /></td>
																		<td><img src="img/list_icon_24.gif" />${entity.name }</td>
																		<td id="owner-link_19491001"><a href="javascript:;" class="hn" id="owner_span_19491001" onclick="openOwnerOptions('19491001','owner','townerdiv','owner-link_');">${entity.projectMembers
																				} <span class="morearrow" style="visibility: hidden;">▼</span>
																		</a></td>
																		<td id="priority-link_19491001"><a href="javascript:;" class="hn" id="priority_span_19491001" onclick="openOptions('19491001','priority','prioritydiv','priority-link_');">${entity.priority
																				}&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span>
																		</a></td>
																		<td id="sdatecal_19491001" onclick="showTime('sdate_19491001','dd/MM/yy');" class="cursor"><fmt:formatDate value="${entity.startTime
																			}" pattern="yyyy-MM-dd" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
																		<td id="edatecal_19491001" onclick="showTime('edate_19491001','dd/MM/yy');"><fmt:formatDate value="${entity.endTime }" pattern="yyyy-MM-dd" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
																		<td id="dur-link_19491001" onclick="openOptions('19491001','duration','durdiv','dur-link_');" class="cursor">${entity.durationDate }<span class="morearrow" style="visibility: hidden;">▼</span></td>
																		<td id="status-link_19491001"><a href="javascript:;" class="hn" id="status_span_19491001" onclick="openOptions('19491001','status','statusdiv','status-link_');">${entity.status } &nbsp;<span class="morearrow" style="visibility: hidden;">▼</span>
																		</a></td>
																		<td>${entity.creator }</td>
																		<td id="percomp-link_19491001" onclick="openOptions('19491001','percomp','percompdiv','percomp-link_');">
																			<div id="perdisp_19491001" class="w60px cursor" title="10% 完成">
																				<div class="w50px fl">
																					<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																					<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																				</div>
																				<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																			</div>
																		</td>
																	</tr>
																</s:iterator>
															</s:if>
														</tbody>
														<tr>
															<td colspan="2"></td>
															<td colspan="9" class="lastLine"><p class="npb_link">
																	<a href="javascript:;" onclick="javascript:showAddToDoId(this);scrollInViewPart('addtodo');">添加任务</a>&nbsp;&nbsp;&nbsp;<a href="#">重新排序</a>
																</p></td>
														</tr>
													</table>
												</dd>
											</dl>
											<dl class="task_list">
												<dt>
													<div class="np_dt_t">未完成里程碑</div>
													<!-- <p>in <span class="red">Accounts Receivabies</span> on Tue,31 May.2001 for Jasmine Frank</p> -->
													<b></b>
												</dt>
												<dd>
													<table width="100%">
														<tr>
															<th width="14">&nbsp;</th>
															<th width="20">&nbsp;</th>
															<th>标题</th>
															<th>全部成员</th>
															<th width="70">优先级</th>
															<th width="90">开始时间</th>
															<th width="90">结束时间</th>
															<th width="60">持续时间</th>
															<th width="70">状态</th>
															<th width="90">创建者</th>
															<th width="90">%</th>
														</tr>
														<tbody id="list_body">
															<s:if test="milestoneReportList.size > 0">
																<s:iterator value="milestoneReportList" var="entity" status="s">
																	<tr>
																		<td><img src="img/list_icon_08.gif" /></td>
																		<td><input name="" type="checkbox" value="" /></td>
																		<td><img src="img/list_icon_24.gif" />${entity.name }</td>
																		<td id="owner-link_19491001"><a href="javascript:;" class="hn" id="owner_span_19491001" onclick="openOwnerOptions('19491001','owner','townerdiv','owner-link_');">${entity.projectMembers
																				} <span class="morearrow" style="visibility: hidden;">▼</span>
																		</a></td>
																		<td id="priority-link_19491001"><a href="javascript:;" class="hn" id="priority_span_19491001" onclick="openOptions('19491001','priority','prioritydiv','priority-link_');">${entity.priority
																				}&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span>
																		</a></td>
																		<td id="sdatecal_19491001" onclick="showTime('sdate_19491001','dd/MM/yy');" class="cursor"><fmt:formatDate value="${entity.startTime
																			}" pattern="yyyy-MM-dd" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
																		<td id="edatecal_19491001" onclick="showTime('edate_19491001','dd/MM/yy');"><fmt:formatDate value="${entity.endTime }" pattern="yyyy-MM-dd" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
																		<td id="dur-link_19491001" onclick="openOptions('19491001','duration','durdiv','dur-link_');" class="cursor">${entity.durationDate }<span class="morearrow" style="visibility: hidden;">▼</span></td>
																		<td id="status-link_19491001"><a href="javascript:;" class="hn" id="status_span_19491001" onclick="openOptions('19491001','status','statusdiv','status-link_');">${entity.status } &nbsp;<span class="morearrow" style="visibility: hidden;">▼</span>
																		</a></td>
																		<td>${entity.creator }</td>
																		<td id="percomp-link_19491001" onclick="openOptions('19491001','percomp','percompdiv','percomp-link_');">
																			<div id="perdisp_19491001" class="w60px cursor" title="10% 完成">
																				<div class="w50px fl">
																					<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																					<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																				</div>
																				<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																			</div>
																		</td>
																	</tr>
																</s:iterator>
															</s:if>
														</tbody>
														<tr>
															<td colspan="2"></td>
															<td colspan="9" class="lastLine"><p class="npb_link">
																	<a href="javascript:;" onclick="javascript:showAddToDoId(this);scrollInViewPart('addtodo');">添加任务</a>&nbsp;&nbsp;&nbsp;<a href="#">重新排序</a>
																</p></td>
														</tr>
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
																<td nowrap="nowrap" height="23" class="userminmax"><input type="checkbox" value="李子树" class="chkbox"> <span>李子树</span> &nbsp;&nbsp;<input type="checkbox" value="李四" class="chkbox"> <span>李四</span></td>
															</tr>
															<tr>
																<td height="5" colspan="2"></td>
															</tr>
														</tbody>
													</table>
												</div>
												<div class="pl3 pt10 pb10 clearLeft fl">
													<input type="button" class="buttonNew h21 w60px" onclick="javascript:selectMember();" value="确定"> &nbsp;&nbsp;<a onclick="javascript:jQuery('#townerdiv').hide();" class="optionsLink" href="javascript:;">取消</a>
												</div>
											</div>
											<div onmouseup="sE(event);" onclick="sE(event);" id="prioritydiv" class="txtSmall qcdiv w100px">
												<div class="zt">
													<img border="0" class="zptarrow" src="img/spacer.gif">
												</div>
												<ul class="ulstyle">
													<li onclick="updateTextValues('priority-link_','Null','prioritydiv');hideCommonList('prioritydiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">空</li>
													<li onclick="updateTextValues('priority-link_','低','prioritydiv');hideCommonList('prioritydiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">低</li>
													<li onclick="updateTextValues('priority-link_','中','prioritydiv');hideCommonList('prioritydiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">中</li>
													<li onclick="updateTextValues('priority-link_','高','prioritydiv');hideCommonList('prioritydiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">高</li>
												</ul>
											</div>
											<div class="txtSmallBlack qcdiv w150 p10" id="durdiv" onclick="sE(event);" onmouseup="sE(event);">
												<div class="zt">
													<img border="0" src="img/spacer.gif" class="zptarrow">
												</div>
												<div class="pt5 h50">
													<div class="fl w80">
														<input type="text" size="42" id="durtext" class="logtimeaddtext w80 h23 tar">
													</div>
													<span class="txtSmallgray fl pl10 pt5">天</span>
												</div>
												<div align="left" style="clear: both;" class="pt8 btdot">
													<input type="submit" value="Save" class="buttonNew" onclick="updateDateValues('dur-link_','durdiv');hideCommonList('durdiv'); return false;"> &nbsp; <a href="javascript:;" class="optionsLink" onclick="javascript:jQuery('#durdiv').hide();">取消</a>
												</div>
											</div>
											<div onmouseup="sE(event);" onclick="sE(event);" id="statusdiv" class="txtSmall qcdiv w100px">
												<div class="zt">
													<img border="0" class="zptarrow" src="img/spacer.gif">
												</div>
												<ul class="ulstyle">
													<li onclick="updateTextValues('status-link_','开始','statusdiv');hideCommonList('statusdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">开始</li>
													<li onclick="updateTextValues('status-link_','结束','statusdiv');hideCommonList('statusdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">结束</li>
												</ul>
											</div>
											<div onmouseup="sE(event);" onclick="sE(event);" style="z-index: 10; position: absolute; right: 20px; display: none;" id="percompdiv" class="txtSmall qcdiv w100px">
												<div style="position: relative; top: -16px; left: 35px; width: 20px; height: 0;">
													<img border="0" class="zptarrow" src="img/spacer.gif">
												</div>
												<ul class="ulstyle">
													<li onclick="updateValues('perdisp_',0);hideCommonList('percompdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">0&nbsp;<span class="txtDisabled">%</span></li>
													<li onclick="updateValues('perdisp_',10);hideCommonList('percompdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">10&nbsp;<span class="txtDisabled">%</span></li>
													<li onclick="updateValues('perdisp_',20);hideCommonList('percompdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">20&nbsp;<span class="txtDisabled">%</span></li>
													<li onclick="updateValues('perdisp_',30);hideCommonList('percompdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">30&nbsp;<span class="txtDisabled">%</span></li>
													<li onclick="updateValues('perdisp_',40);hideCommonList('percompdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">40&nbsp;<span class="txtDisabled">%</span></li>
													<li onclick="updateValues('perdisp_',50);hideCommonList('percompdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">50&nbsp;<span class="txtDisabled">%</span></li>
													<li onclick="updateValues('perdisp_',60);hideCommonList('percompdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">60&nbsp;<span class="txtDisabled">%</span></li>
													<li onclick="updateValues('perdisp_',70);hideCommonList('percompdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">70&nbsp;<span class="txtDisabled">%</span></li>
													<li onclick="updateValues('perdisp_',80);hideCommonList('percompdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">80&nbsp;<span class="txtDisabled">%</span></li>
													<li onclick="updateValues('perdisp_',90);hideCommonList('percompdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">90&nbsp;<span class="txtDisabled">%</span></li>
													<li onclick="updateValues('perdisp_',100);hideCommonList('percompdiv');" class="pt5 pb5 pl5 taskDescRow bdrbtm4">100&nbsp;<span class="txtDisabled">%</span></li>
												</ul>
											</div>
										</div>
										<div id="addtodolist">
											<div onmouseup="sE(event);" onclick="sE(event);" id="addtlistid" class="txtSmall qcdiv w250">
												<div class="zt">
													<img border="0" class="zprarrow" src="img/spacer.gif">
												</div>
												<form onsubmit="javascript:$('#addtlistid').hide();" method="POST" class="formparam">
													<table width="100%" cellspacing="0" cellpadding="0" border="0" class="qulbl">
														<tbody>
															<tr>
																<td valign="top" class="pL10" colspan="2"><div class="requiredField">任务列表</div></td>
															</tr>
															<tr>
																<td class="pL10 pB10" colspan="2">
																	<div id="mile_templates_separator_0">
																		<input type="text" class="logtimeaddtext w200px h18" id="toptodolist" size="42">
																	</div>
																</td>
															</tr>
															<tr>
																<td align="left" class="pt5 pb5 btdot pL10" colspan="2"><input type="submit" class="buttonNew" value="添加任务列表"> &nbsp;&nbsp; <a onclick="$('#addtlistid').hide();" class="optionsLink" href="javascript:;">取消</a> <span class="hide" id="zoho_add_tlist_busy_0"><img border="0" align="absmiddle" src="img/zoho-busy.gif">
																</span></td>
															</tr>
														</tbody>
													</table>
												</form>
											</div>
										</div>
										<div id="todoexport">
											<div onmouseup="sE(event);" onclick="sE(event);" id="todoexpid" class="txtSmall qcdiv w250">
												<div class="zt">
													<img border="0" class="zprarrow" src="img/spacer.gif">
												</div>
												<form onsubmit="javascript:$('#todoexpid').hide();" method="POST">
													<table width="100%" cellspacing="0" cellpadding="0" border="0" class="qulbl">
														<tbody>
															<tr>
																<td colspan="2"><div id="ttask_export_status" class="block"></div></td>
															</tr>
															<tr>
																<td valign="top" class="pL10" colspan="2">登录名</td>
															</tr>
															<tr>
																<td class="pL10" colspan="2"><select class="logtimeaddselect w175" id="exportusername" style="visibility: visible;">
																		<option value="全部任务">全部成员</option>
																		<option value="AnyUser">任意成员</option>
																		<option value="21628287">张三</option>
																</select></td>
															</tr>
															<tr>
																<td valign="top" class="pL10 pt15" colspan="2">状态</td>
															</tr>
															<tr>
																<td class="pL10" colspan="2"><select class="logtimeaddselect w175" id="mstat" style="visibility: visible;">
																		<option value="未决任务">未决任务</option>
																		<option value="关闭任务">关闭任务</option>
																		<option value="归档任务">归档任务</option>
																		<option value="全部任务">全部任务</option>
																</select></td>
															</tr>
															<tr>
																<td valign="top" class="pL10 pt15" colspan="2">格式</td>
															</tr>
															<tr>
																<td class="pL10 pB15" colspan="2"><select class="logtimeaddselect w100px" id="exportType" style="visibility: visible;">
																		<option value="xls">xls</option>
																		<option value="csv">csv</option>
																		<!--option value="tsv">tsv</option-->
																</select></td>
															</tr>
															<tr>
																<td align="left" class="pt5 pb5 btdot pL10" colspan="2"><input type="submit" value="导出" class="buttonNew"> &nbsp;&nbsp;<a onclick="jQuery('#todoexpid').hide();" class="optionsLink" href="javascript:;">取消</a> &nbsp;&nbsp;<span class="hide" id="zoho_export_ttask_busy"><img border="0" align="absmiddle"
																		src="img/zoho-busy.gif"> </span></td>
															</tr>
														</tbody>
													</table>
												</form>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div></td>
		</tr>
	</table>
</div>