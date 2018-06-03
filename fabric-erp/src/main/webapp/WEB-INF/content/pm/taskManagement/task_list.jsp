<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="css/service.css" type="text/css" rel="stylesheet">
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery-ui.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/underscore-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/backbone-min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.tmpl.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-debug.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/ba-tinyPubSub.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.mousewheel.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.ui.ipad.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.global.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript" charset="utf-8"></script>
<script src="js/service.js" type="text/javascript"></script>
<script src="js/WdatePicker.js" type="text/javascript"></script>
<script src="js/newservice.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.scrollfollow.js"></script>
<script type="text/javascript">
$(function(){
	$('#follow').scrollFollow({
		offset:50,
	   container: 'followBox'
	  });
	  
});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pm_task.png" alt="" /> 项目管理</a></li>
				<li><a href="#">任务管理</a></li>
				<li><a href="#">任务</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
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
												<h2>任务</h2>
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
													<div class="np_dt_t">新建任务</div>
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
															<th width="50">状态</th>
															<th width="90">创建者</th>
															<th width="90">%</th>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491001"><a href="javascript:;" class="hn" id="owner_span_19491001" onclick="openOwnerOptions('19491001','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491001"><a href="javascript:;" class="hn" id="priority_span_19491001" onclick="openOptions('19491001','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491001" onclick="showTime('sdate_19491001','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491001" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491001" onclick="showTime('edate_19491001','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491001" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491001" onclick="openOptions('19491001','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491001"><a href="javascript:;" class="hn" id="status_span_19491001" onclick="openOptions('19491001','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
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
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491002"><a href="javascript:;" class="hn" id="owner_span_19491002" onclick="openOwnerOptions('19491002','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491002"><a href="javascript:;" class="hn" id="priority_span_19491002" onclick="openOptions('19491002','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491002" onclick="showTime('sdate_19491002','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491002" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491002" onclick="showTime('edate_19491002','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491002" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491002" onclick="openOptions('19491002','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491002"><a href="javascript:;" class="hn" id="status_span_19491002" onclick="openOptions('19491002','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491002" onclick="openOptions('19491002','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491002" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491003"><a href="javascript:;" class="hn" id="owner_span_19491003" onclick="openOwnerOptions('19491003','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491003"><a href="javascript:;" class="hn" id="priority_span_19491003" onclick="openOptions('19491003','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491003" onclick="showTime('sdate_19491003','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491003" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491003" onclick="showTime('edate_19491003','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491003" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491003" onclick="openOptions('19491003','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491003"><a href="javascript:;" class="hn" id="status_span_19491003" onclick="openOptions('19491003','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491003" onclick="openOptions('19491003','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491003" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491004"><a href="javascript:;" class="hn" id="owner_span_19491004" onclick="openOwnerOptions('19491004','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491004"><a href="javascript:;" class="hn" id="priority_span_19491004" onclick="openOptions('19491004','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491004" onclick="showTime('sdate_19491004','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491004" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491004" onclick="showTime('edate_19491004','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491004" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491004" onclick="openOptions('19491004','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491004"><a href="javascript:;" class="hn" id="status_span_19491004" onclick="openOptions('19491004','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491004" onclick="openOptions('19491004','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491004" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491005"><a href="javascript:;" class="hn" id="owner_span_19491005" onclick="openOwnerOptions('19491005','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491005"><a href="javascript:;" class="hn" id="priority_span_19491005" onclick="openOptions('19491005','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491005" onclick="showTime('sdate_19491005','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491005" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491005" onclick="showTime('edate_19491005','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491005" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491005" onclick="openOptions('19491005','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491005"><a href="javascript:;" class="hn" id="status_span_19491005" onclick="openOptions('19491005','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491005" onclick="openOptions('19491005','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491005" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491006"><a href="javascript:;" class="hn" id="owner_span_19491006" onclick="openOwnerOptions('19491006','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491006"><a href="javascript:;" class="hn" id="priority_span_19491006" onclick="openOptions('19491006','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491006" onclick="showTime('sdate_19491006','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491006" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491006" onclick="showTime('edate_19491006','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491006" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491006" onclick="openOptions('19491006','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491006"><a href="javascript:;" class="hn" id="status_span_19491006" onclick="openOptions('19491006','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491006" onclick="openOptions('19491006','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491006" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491007"><a href="javascript:;" class="hn" id="owner_span_19491007" onclick="openOwnerOptions('19491007','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491007"><a href="javascript:;" class="hn" id="priority_span_19491007" onclick="openOptions('19491007','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491007" onclick="showTime('sdate_19491007','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491007" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491007" onclick="showTime('edate_19491007','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491007" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491007" onclick="openOptions('19491007','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491007"><a href="javascript:;" class="hn" id="status_span_19491007" onclick="openOptions('19491007','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491007" onclick="openOptions('19491007','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491007" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491008"><a href="javascript:;" class="hn" id="owner_span_19491008" onclick="openOwnerOptions('19491008','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491008"><a href="javascript:;" class="hn" id="priority_span_19491008" onclick="openOptions('19491008','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491008" onclick="showTime('sdate_19491008','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491008" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491008" onclick="showTime('edate_19491008','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491008" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491008" onclick="openOptions('19491008','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491008"><a href="javascript:;" class="hn" id="status_span_19491008" onclick="openOptions('19491008','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491008" onclick="openOptions('19491008','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491008" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491009"><a href="javascript:;" class="hn" id="owner_span_19491009" onclick="openOwnerOptions('19491009','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491009"><a href="javascript:;" class="hn" id="priority_span_19491009" onclick="openOptions('19491009','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491009" onclick="showTime('sdate_19491009','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491009" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491009" onclick="showTime('edate_19491009','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491009" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491009" onclick="openOptions('19491009','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491009"><a href="javascript:;" class="hn" id="status_span_19491009" onclick="openOptions('19491009','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491009" onclick="openOptions('19491009','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491009" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491010"><a href="javascript:;" class="hn" id="owner_span_19491010" onclick="openOwnerOptions('19491010','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491010"><a href="javascript:;" class="hn" id="priority_span_19491010" onclick="openOptions('19491010','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491010" onclick="showTime('sdate_19491010','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491010" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491010" onclick="showTime('edate_19491010','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491010" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491010" onclick="openOptions('19491010','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491010"><a href="javascript:;" class="hn" id="status_span_19491010" onclick="openOptions('19491010','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491010" onclick="openOptions('19491010','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491010" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
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
													<div class="np_dt_t">完成任务</div>
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
															<th width="50">状态</th>
															<th width="90">创建者</th>
															<th width="90">%</th>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491101"><a href="javascript:;" class="hn" id="owner_span_19491101" onclick="openOwnerOptions('19491101','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491101"><a href="javascript:;" class="hn" id="priority_span_19491101" onclick="openOptions('19491101','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491101" onclick="showTime('sdate_19491101','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491101" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491101" onclick="showTime('edate_19491101','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491101" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491101" onclick="openOptions('19491101','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491101"><a href="javascript:;" class="hn" id="status_span_19491101" onclick="openOptions('19491101','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491101" onclick="openOptions('19491101','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491101" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491102"><a href="javascript:;" class="hn" id="owner_span_19491102" onclick="openOwnerOptions('19491102','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491102"><a href="javascript:;" class="hn" id="priority_span_19491102" onclick="openOptions('19491102','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491102" onclick="showTime('sdate_19491102','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491102" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491102" onclick="showTime('edate_19491102','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491102" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491102" onclick="openOptions('19491102','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491102"><a href="javascript:;" class="hn" id="status_span_19491102" onclick="openOptions('19491102','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491102" onclick="openOptions('19491102','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491102" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491103"><a href="javascript:;" class="hn" id="owner_span_19491103" onclick="openOwnerOptions('19491103','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491103"><a href="javascript:;" class="hn" id="priority_span_19491103" onclick="openOptions('19491103','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491103" onclick="showTime('sdate_19491103','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491103" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491103" onclick="showTime('edate_19491103','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491103" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491103" onclick="openOptions('19491103','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491103"><a href="javascript:;" class="hn" id="status_span_19491103" onclick="openOptions('19491103','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491103" onclick="openOptions('19491103','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491103" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491104"><a href="javascript:;" class="hn" id="owner_span_19491104" onclick="openOwnerOptions('19491104','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491104"><a href="javascript:;" class="hn" id="priority_span_19491104" onclick="openOptions('19491104','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491104" onclick="showTime('sdate_19491104','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491104" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491104" onclick="showTime('edate_19491104','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491104" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491104" onclick="openOptions('19491104','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491104"><a href="javascript:;" class="hn" id="status_span_19491104" onclick="openOptions('19491104','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491104" onclick="openOptions('19491104','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491104" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491105"><a href="javascript:;" class="hn" id="owner_span_19491105" onclick="openOwnerOptions('19491105','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491105"><a href="javascript:;" class="hn" id="priority_span_19491105" onclick="openOptions('19491105','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491105" onclick="showTime('sdate_19491105','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491105" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491105" onclick="showTime('edate_19491105','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491105" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491105" onclick="openOptions('19491105','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491105"><a href="javascript:;" class="hn" id="status_span_19491105" onclick="openOptions('19491105','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491105" onclick="openOptions('19491105','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491105" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491106"><a href="javascript:;" class="hn" id="owner_span_19491106" onclick="openOwnerOptions('19491106','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491106"><a href="javascript:;" class="hn" id="priority_span_19491106" onclick="openOptions('19491106','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491106" onclick="showTime('sdate_19491106','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491106" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491106" onclick="showTime('edate_19491106','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491106" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491106" onclick="openOptions('19491106','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491106"><a href="javascript:;" class="hn" id="status_span_19491106" onclick="openOptions('19491106','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491106" onclick="openOptions('19491106','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491106" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491107"><a href="javascript:;" class="hn" id="owner_span_19491107" onclick="openOwnerOptions('19491107','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491107"><a href="javascript:;" class="hn" id="priority_span_19491107" onclick="openOptions('19491107','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491107" onclick="showTime('sdate_19491107','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491107" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491107" onclick="showTime('edate_19491107','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491107" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491107" onclick="openOptions('19491107','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491107"><a href="javascript:;" class="hn" id="status_span_19491107" onclick="openOptions('19491107','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491107" onclick="openOptions('19491107','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491107" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491108"><a href="javascript:;" class="hn" id="owner_span_19491108" onclick="openOwnerOptions('19491108','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491108"><a href="javascript:;" class="hn" id="priority_span_19491108" onclick="openOptions('19491108','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491108" onclick="showTime('sdate_19491108','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491108" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491108" onclick="showTime('edate_19491108','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491108" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491108" onclick="openOptions('19491108','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491108"><a href="javascript:;" class="hn" id="status_span_19491108" onclick="openOptions('19491108','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491108" onclick="openOptions('19491108','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491108" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491109"><a href="javascript:;" class="hn" id="owner_span_19491109" onclick="openOwnerOptions('19491109','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491109"><a href="javascript:;" class="hn" id="priority_span_19491109" onclick="openOptions('19491109','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491109" onclick="showTime('sdate_19491109','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491109" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491109" onclick="showTime('edate_19491109','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491109" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491109" onclick="openOptions('19491109','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491109"><a href="javascript:;" class="hn" id="status_span_19491109" onclick="openOptions('19491109','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491109" onclick="openOptions('19491109','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491109" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
														<tr>
															<td><img src="img/list_icon_08.gif" /></td>
															<td><input name="" type="checkbox" value="" /></td>
															<td><img src="img/list_icon_24.gif" /> FRAC项目</td>
															<td id="owner-link_19491110"><a href="javascript:;" class="hn" id="owner_span_19491110" onclick="openOwnerOptions('19491110','owner','townerdiv','owner-link_');">任意成员 <span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="priority-link_19491110"><a href="javascript:;" class="hn" id="priority_span_19491110" onclick="openOptions('19491110','priority','prioritydiv','priority-link_');">中&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td id="sdatecal_19491110" onclick="showTime('sdate_19491110','dd/MM/yy');" class="cursor"><input type="text" class="setDate" id="sdate_19491110" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="edatecal_19491110" onclick="showTime('edate_19491110','dd/MM/yy');"><input type="text" class="setDate" id="edate_19491110" value="12/09/12" readonly="readonly" /><span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="dur-link_19491110" onclick="openOptions('19491110','duration','durdiv','dur-link_');" class="cursor">1 天 <span class="morearrow" style="visibility: hidden;">▼</span></td>
															<td id="status-link_19491110"><a href="javascript:;" class="hn" id="status_span_19491110" onclick="openOptions('19491110','status','statusdiv','status-link_');">开始&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span></a></td>
															<td>张三</td>
															<td id="percomp-link_19491110" onclick="openOptions('19491110','percomp','percompdiv','percomp-link_');">
																<div id="perdisp_19491110" class="w60px cursor" title="10% 完成">
																	<div class="w50px fl">
																		<div class="greenbar fl" style="width: 5px; height: 10px;"></div>
																		<div class="redbar fl" style="width: 45px; height: 10px;"></div>
																	</div>
																	<span class="morearrow fl pl3" style="visibility: hidden;">▼</span>
																</div>
															</td>
														</tr>
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
																<td nowrap="nowrap" height="23" class="userminmax"><input type="checkbox" value="张三" class="chkbox"> <span>李子树</span> &nbsp;&nbsp;<input type="checkbox" value="张三" class="chkbox"> <span>李四</span></td>
															</tr>
															<tr>
																<td height="5" colspan="2"></td>
															</tr>
														</tbody>
													</table>
												</div>
												<div class="pl3 pt10 pb10 clearLeft fl">
													<input type="button" class="buttonNew h21 w60px" onclick="javascript:jQuery('#townerdiv').hide();" value="确定"> &nbsp;&nbsp;<a onclick="javascript:jQuery('#townerdiv').hide();" class="optionsLink" href="javascript:;">取消</a>
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
									</td>
									<td valign="top" class="rhsdivbg" width="292" style="width: 256px;">
										<div class="np_right" id="followBox" style="position: relative; height: 1940px;">
											<div class="np_right_box" id="follow" style="position: absolute;">
												<div class="t_btn">
													<a href="javascript:;" onclick="javascript:showAddToDoId(this);scrollInViewPart('addtodo');"><img src="img/list_icon_22.gif" style="vertical-align: middle;" />新建任务</a>
												</div>
												<ul class="npr_list">
													<li><a href="javascript:;" onclick="javascript:$('#addtlistid').show();scrollInViewPart('addtlistid');">新任务列表</a></li>
													<li><a href="javascript:;" onclick="javascript:$('#todoexpid').show();">导出</a></li>
												</ul>
												<div class="npr_form">
													<div class="npr_form_title">过滤标准</div>
													<p>
														所有<br />
														<select name="">
															<option value="1">全部记录</option>
															<option value="2">打开</option>
															<option value="3">关闭</option>
														</select>
													</p>
													<p>
														状态<br />
														<select name="">
															<option value="1">所有</option>
															<option value="2">打开</option>
															<option value="3">关闭</option>
														</select>
													</p>
													<p>
														优先级<br />
														<select name="">
															<option value="1">所有</option>
															<option value="2">打开</option>
															<option value="3">关闭</option>
														</select>
													</p>
													<p>
														时间<br />
														<select name="">
															<option value="1">所有</option>
															<option value="2">打开</option>
															<option value="3">关闭</option>
														</select>
													</p>
												</div>
											</div>
										</div>
										<div id="addtodo">
											<form onsubmit="$('#addtodoid').hide();" method="POST" class="formParam">
												<div id="addtodoid" onmouseup="sE(event);" onclick="sE(event);" class="txtSmall qcdiv w320">
													<div class="zt">
														<img border="0" class="zprarrow" src="img/spacer.gif">
													</div>
													<table width="100%" class="qulbl">
														<tbody>
															<tr>
																<td valign="top" class="pL10 pb1" colspan="2"><div class="fl pt5 requiredField">任务</div></td>
															</tr>
															<tr>
																<td class="pL10 pb5" colspan="2"><textarea id="todotask" class="logtimeaddtext w265px h70" style="width: 282px;"></textarea></td>
															</tr>
															<tr>
																<td valign="top" class="pL10 pt10 pb1" colspan="2">任务列表</td>
															</tr>
															<tr>
																<td class="pl10 pb5" colspan="2"><div id="tlistdispid">
																		<select class="logtimeaddselect w290">
																			<optgroup label="内部的">
																				<option ''="" value="492772000000015007">adf&nbsp;(空)</option>
																				<option value="intgeneral">常规</option>
																			</optgroup>
																			<optgroup label="外部的">
																				<option value="extgeneral">常规</option>
																			</optgroup>
																		</select> <br> <a class="qcsublink" href="javascript:;">从完成的任务列表中选择</a>
																	</div></td>
															</tr>
															<tr>
																<td valign="top" class="pL10 pt10 pb1" colspan="2">谁是负责人？</td>
															</tr>
															<tr>
																<td style="width: 290px;" class="pl10 pb5" colspan="2"><div style="width: 290px;" id="assignless">
																		<select style="visibility: visible;" class="logtimeaddselect h23 w120">
																			<option value="AnyUser">任意成员</option>
																			<optgroup label="项目成员">
																				<option title="张三 (tingzhuqi@hotmail.com) " value="21628287">我</option>
																			</optgroup>
																		</select> <span class="pL10"><a href="javascript:;" class="optionsLink">分配多个</a></span>
																	</div></td>
															</tr>
															<tr>
																<td width="150" valign="top" class="pL10 pt10 pb1" id="stlbl">开始日期</td>
																<td width="150" valign="top" class="pT10 pb1"><span id="dueid">结束日期</span><span class="hide" id="durid">持续时间</span></td>
															</tr>
															<tr>
																<td valign="top" class="pL10 pb5"><input type="text" value="开始时间" class="logtimeaddtext w115 h17" style="font-size: 11px; width: 116px;" id="ttaskdate"> <img width="18" height="18" border="0" align="absmiddle" class="zpdateIcon" src="img/spacer.gif" style="position: relative; right: 22px; bottom: 0px;" title="点击选择开始日期"
																	id="task_st_cal" onclick="showTime('ttaskdate','dd/MM/yy');"></td>
																<td valign="top" class="pb5"><div id="taskenddateopt">
																		<table width="100%" cellspacing="0" cellpadding="0" border="0">
																			<tbody>
																				<tr>
																					<td><input type="text" value="结束时间" class="logtimeaddtext w115 h17" style="font-size: 11px; width: 116px;" readonly="" id="taskenddate"> <img width="18" height="18" border="0" align="absmiddle" class="zpdateIcon" src="img/spacer.gif" style="position: relative; right: 22px; bottom: 0px;" title="点击选择结束日期"
																						id="task_end_cal" onclick="showTime('taskenddate','dd/MM/yy');"></td>
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
																					<td><input type="text" id="duration" size="5" class="logtimeaddtext w60px h17"> &nbsp;<span class="qcsubtxt">天 </span> <input type="hidden" value="天"></td>
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
															</tr>
															<tr>
																<td valign="top" class="pL10 pb5"><select style="visibility: visible; width: 121px;" id="priority" class="logtimeaddselect h23 w120">
																		<option value="空">空</option>
																		<option value="低">低</option>
																		<option value="中">中</option>
																		<option value="高">高</option>
																</select></td>
															</tr>
															<tr>
																<td align="left" class="pt5 pb5 btdot pl10" colspan="2"><div class="txtDisabled pb5">
																		<input type="checkbox" onclick="" checked="" value="yes" id="notifyuser"> 发送邮件通知
																	</div> <input type="submit" class="buttonNew" value="添加任务"> &nbsp;&nbsp; <a href="javascript:;" class="optionsLink" onclick="$('#addtodoid').hide();return false;">取消</a> &nbsp;&nbsp;<span id="zoho_add_ttask_busy" class="hide"><img border="0" align="absmiddle" src="img/zoho-busy.gif"></span></td>
															</tr>
														</tbody>
													</table>
												</div>
											</form>
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
																<td align="left" class="pt5 pb5 btdot pL10" colspan="2"><input type="submit" class="buttonNew" value="添加任务列表"> &nbsp;&nbsp; <a onclick="$('#addtlistid').hide();" class="optionsLink" href="javascript:;">取消</a> <span class="hide" id="zoho_add_tlist_busy_0"><img border="0" align="absmiddle" src="img/zoho-busy.gif"></span></td>
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
																		src="img/zoho-busy.gif"></span></td>
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
<!-- content -->
<div id="footpanel">
	<ul id="mainpanel">
		<li><a href="http://www.designbombs.com" class="home">Inspiration <small>Design </small></a></li>
		<li><a href="http://www.designbombs.com" class="profile">View Profile <small>View Profile</small></a></li>
		<li><a href="http://www.designbombs.com" class="editprofile">Edit Profile <small>Edit Profile</small></a></li>
		<li><a href="http://www.designbombs.com" class="contacts">Contacts <small>Contacts</small></a></li>
		<li><a href="http://www.designbombs.com" class="messages">Messages (10) <small>Messages</small></a></li>
		<li><a href="http://www.designbombs.com" class="playlist">Play List <small>Play List</small></a></li>
		<li><a href="http://www.designbombs.com" class="videos">Videos <small>Videos</small></a></li>
		<li id="alertpanel"><a class="alerts" id="alerts_off">Alerts</a></li>
		<li id="chatpanel"><a href="#" class="chat">Friends (<strong>18</strong>)
		</a>
			<div class="subpanel">
				<h3>
					<span> &ndash; </span>Friends Online
				</h3>
				<ul>
					<li><span>Family Members</span></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><span>Other Friends</span></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
					<li><a href="#"><img src="img/chat-thumb.gif" alt="" /> Your Friend</a></li>
				</ul>
			</div></li>
	</ul>
</div>
<div id="footpanel_switch">
	<ul id="mainpanel">
		<li id="alertpanel"><a class="alerts" id="alerts_on">Alerts</a></li>
	</ul>
</div>
<!-- foot bar -->
<div class="pos_menu">
	<em id="show_menu"></em>
	<div id="pos_menu">
		<strong><a id="hidd_menu" href="###"></a>SHORTCHUTS</strong>
		<ul>
			<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/balloon.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/balloon.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/balloon.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/wrench_screwdriver.png" alt="" />demo</a></li>
			<li><a href="#"><img src="img/balloon.png" alt="" />demo</a></li>
		</ul>
	</div>
</div>
<!-- pos_menu -->

<script src="js/menu.js" type="text/javascript"></script>
<div id="helpContent" title="Viewing and using brush files">
	<p>&nbsp;&nbsp;You can view brush files in the Browser just like fonts or other files. This means that you can assign ratings or organize them just like any other file in ACDSee. Because many .abr files are actually groups of images in one file, you need to open them in the Viewer to see the individual images.</p>
	<p>
		<img src="img/address_book.png">This icon indicates an .abr brush file in ACDSee.
	</p>
	<p>
		<strong>To view brush files:</strong>
	</p>
	<p>&nbsp;&nbsp;1. In the Browser, navigate to the folder containing your brush files.</p>
	<p>&nbsp;&nbsp;2. To see just the top image in any .abr file, hover over the thumbnail to activate the pop-up, or click on it to see that image in the Preview pane.</p>
	<p>&nbsp;&nbsp;3. To view the other images in the .abr file, double-click on it to open it in the Viewer.</p>
	<p>&nbsp;&nbsp;The file opens in the Viewer showing the individual images in a pane on the left-hand side.</p>
	<p>&nbsp;&nbsp;4. To see the number of images, and select them by number, click the down-arrow at the top of the sidebar, and then select the number of the image.</p>
	<p>&nbsp;&nbsp;5. To scroll through the images, click the right and left arrows at the top of the sidebar, or on each image.</p>
	<p>
		<strong>To use brush files in Adobe Photoshop:</strong>
	</p>
	<p>&nbsp;&nbsp;With both Adobe Photoshop and ACDSee open, drag the file from the File List (in the Browser) onto the Photoshop window.</p>
	<p>&nbsp;&nbsp;Even though nothing appears to happen, the brush is loaded into the Photoshop brush library. To view the new brushes, open the library and scroll to the bottom of the pane.</p>
	<p class="helpTd">To make it even easier to use brushes in Photo Shop, you can configure it to be your default editor. Then you can use Ctrl +E to open Photoshop and use the brush right away.</p>
</div>