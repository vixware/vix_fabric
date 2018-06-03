<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<style>
.content {
	margin-bottom: 0;
}

.cardTable {
	padding: 0 10px;
}

.cardTable table th, .cardTable table td {
	padding: 5px;
	vertical-align: top;
	border: #CCC solid 1px;
}

.cardTable table th {
	background: #DCE7F1;
}

.cardTable table .tr {
	text-align: right;
}

.cardTable table .tr1 {
	text-align: center;
}

.cardTable .popupArea {
	height: 300px;
}

.cardTable .checkbox {
	vertical-align: middle;
}

.cardTable label {
	margin-right: 10px;
}
</style>
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box cardTable">
		<form id="brandForm">
			<input type="hidden" id="id" name="id" value="${progressLog.id}" />
			<table width="100%">
				<tbody>
					<tr>
						<td class="Big"><img width="22" align="absmiddle" height="18" src="${vix}/common/img/drp_channel.png"> <span class="big3"> 进度日志详情(新员工培训 2013年12月18日 - 2013年12月29日)</span></td>
					</tr>
				</tbody>
			</table>
			<table width="100%">
				<tr>
					<th class="tr1" align=center><b>作者：</b></th>
					<th class="tr1" align=center><b>内容：</b></th>
					<th class="tr1" align=center><b>附件：</b></th>
					<th class="tr1" align=center><b>日志时间：</b></th>
					<th class="tr1" align=center><b>进度百分比：</b></th>
					<th class="tr1" align=center><b>操作：</b></th>
				</tr>
				<tr>
					<td align=center>${projectManagement.plantype}</td>
					<td align=center>${progressLog.schedule}</td>
					<td align=center>${progressLog.accessory}</td>
					<td align=center>${progressLog.currentTime}</td>
					<td align=center>${progressLog.percent}</td>
					<td align=center><a href=""> 修改</a> <a href=""> 删除</a></td>
				</tr>
			</table>
			<table width="100%">
				<tbody>
					<tr>
						<td><img width="22" align="absmiddle" height="20" src="${vix}/common/img/drp_channel.png"> <span class="big3"> 添加进度日志</span></td>
					</tr>
				</tbody>
			</table>
			<table width="100%">
				<tr>
					<th class="tr">当前时间：</th>
					<td><input id="currentTime" type="text" style="width: 200px;" name="currentTime" value="${progressLog.currentTime}" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})"></td>
				</tr>
				<tr>
					<th class="tr" width="150">完成百分比：</th>
					<td><input id="percent" name="workPlan.percent" value="${progressLog.percent}" type="text" style="width: 30px;" class="ipt" /> % 上次进度值：100 （注：估计完成量与总量的百分比）</td>
				</tr>
				<tr>
					<th class="tr">进度详情：</th>
					<td colspan="3"><textarea id="schedule" class="example" rows="1" style="width: 500px; height: 97px;">${progressLog.schedule}</textarea></td>
				</tr>
				<tr>
					<th class="tr">附件：</th>
					<td><input id="accessory" name="progressLog.accessory" value="${progressLog.accessory}" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr" style="width: 200px; height: 22px;">附件选择：</th>
					<td colspan="2"><a href="#"><img src="img/icon_25.gif" />添加附件</a> <a href="#"><img src="img/icon_25.gif" />从文件柜和网络硬盘选择附件</a></td>
				</tr>
				<tr>
					<th class="tr" width="150" style="width: 200px; height: 22px;">事务提醒：</th>
					<td><label> <input name="workPlan.projectName" type="checkbox" value="true" <s:if test="%{entity.sendMsg}">checked="checked"</s:if> class="checkbox" /> 插入事务提醒消息
					</label></td>
				</tr>
				<tr>
					<th class="tr" width="150" style="width: 200px; height: 22px;">是否写入工作日志：</th>
					<td><label> <input name="workPlan.projectName" type="checkbox" value="true" <s:if test="%{entity.sendMsg}">checked="checked"</s:if> class="checkbox" /> （注意：勾选会将进度详情写入工作日志）
					</label></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>