<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

</script>
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
		<table width="100%">
			<tr>
				<th class="tr" width="150">任务标题：</th>
				<td><input name="" type="text" style="width: 200px;" class="ipt" /></td>
			</tr>
			<tr>
				<th class="tr">日期：</th>
				<td colspan="2"><input type="text" name="" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})">
			</tr>
			<tr>
				<th class="tr">事务内容：</th>
				<td><select name="" size="3" multiple="multiple" style="width: 200px;">
						<option></option>
						<option></option>
						<option></option>
						<option></option>
				</select>&nbsp;<a href="javascript:;"><img src="img/icon_25.gif" style="vertical-align: middle" /> 添加</a>&nbsp;<a href="javascript:;"><img src="img/delete.gif" style="vertical-align: middle" /> 清空</a></td>
			</tr>
			<tr>
				<th class="tr">事务类型：</th>
				<td colspan="2"><select name="">
						<option>工作事务</option>
						<option>工作事务</option>
				</select></td>
			</tr>
			<tr>
				<th class="tr">提醒类型：</th>
				<td colspan="2"><select name="">
						<option>按日提醒</option>
						<option>按日提醒</option>
				</select></td>
			</tr>
			<tr>
				<th class="tr">提醒时间：</th>
				<td><input type="text" name="" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})">为空为当前时间
			</tr>
			<tr>
				<th class="tr">参与者：</th>
				<td><select name="" size="3" multiple="multiple" style="width: 200px;">
						<option></option>
						<option></option>
						<option></option>
						<option></option>
				</select>&nbsp;<a href="javascript:;"><img src="img/icon_25.gif" style="vertical-align: middle" /> 添加</a>&nbsp;<a href="javascript:;"><img src="img/delete.gif" style="vertical-align: middle" /> 清空</a></td>
			</tr>
			<tr>
				<th class="tr">日期：</th>
				<td><input type="text" name="" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})">至 <input type="text" name="" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})"></td>
			</tr>
			<tr>
				<th class="tr">说明：</th>
				<td><select name="" size="3" multiple="multiple" style="width: 200px;">
						<option></option>
						<option></option>
						<option></option>
						<option></option>
				</select>&nbsp;<a href="javascript:;"><img src="img/icon_25.gif" style="vertical-align: middle" /> 添加</a>&nbsp;<a href="javascript:;"><img src="img/delete.gif" style="vertical-align: middle" /> 清空</a></td>
			</tr>
			<tr>
				<th class="tr">置顶：</th>
				<td colspan="2"><label> <input class="checkbox" type="checkbox" value="" name=""> 使投票置顶，显示为重要
				</label></td>
			</tr>
			<tr>
				<th class="tr">事务提醒：</th>
				<td colspan="2"><label> <input class="checkbox" type="checkbox" value="" name=""> 发送事务提醒消息
				</label></td>
			</tr>
			<tr>
				<th class="tr">附件文档：</th>
				<td colspan="2"><input name="" type="file" /></td>
			</tr>
			<tr>
				<th class="tr">附件选择：</th>
				<td colspan="2"><a href="#"><img src="img/icon_25.gif" />添加附件</a> <a href="#"><img src="img/icon_25.gif" />从文件柜和网络硬盘选择附件</a></td>
			</tr>
			<tr>
				<th class="tr">备注：</th>
				<td colspan="2"><textarea name="" cols="" rows="" style="width: 400px;"></textarea></td>
			</tr>
		</table>

	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>