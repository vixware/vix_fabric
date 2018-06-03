<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function setvalue(i) {
		$('#searchtext').val(i.value);
		function displayDiv() {
			var div = document.getElementById("popUpId");
			div.style.display = "none";
		}
	}
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>店铺选择 : <input type="hidden" id="channelDistributorId" name="channelDistributorId" /> <input type="text" name="channelDistributorName" id="channelDistributorName" class="int" /> <input class="btn" type="button" value="选择" onclick="chooseChannelDistributor();" /></td>
			</tr>
			<tr height="30">
				<td><label for="datetyped"><input id="datetyped" name="datetype" type="radio" value="d" />按日统计：<input id="date" type="text" class="int" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></label></td>
			</tr>
			<tr height="30">
				<td><label for="datetypew"><input id="datetypew" name="datetype" type="radio" value="w" />按周统计：<input id="weekday" class="int" type="text" onfocus="WdatePicker({isShowWeek:true,onpicked:funccc,errDealMode:3})" /></label></td>
			</tr>
			<tr height="30">
				<td><label for="datetypem"><input id="datetypem" name="datetype" type="radio" value="m" checked="checked" />按月统计：<input id="monthdate" class="int" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM'})" onchange="setvalue(this);displayDiv();" /></label></td>
			</tr>
			<tr height="30">
				<td><label for="datetypeb"><input id="datetypeb" name="datetype" type="radio" value="b" />按时间段：<input id="startdate" class="int" type="text" onFocus="var enddate=$dp.$('enddate');WdatePicker({onpicked:function(){enddate.focus();},maxDate:'#F{$dp.$D(\'enddate\')}'})" />至 <input id="enddate" class="int" type="text"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startdate\')}'})" /></label></td>
			</tr>
		</table>
	</div>
</div>