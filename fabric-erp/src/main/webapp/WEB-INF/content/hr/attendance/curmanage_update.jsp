<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
$("#brandForm").validationEngine();
//-->
</script>
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="brandForm">
		<s:hidden id="id" name="brand.id" value="%{brand.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">名称:&nbsp;</th>
					<td><input id="name" name="brand.name" value="${brand.name}" data-text-tooltip="Tip tip tip tip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" class="validate[required] text-input" /></td>
					<th align="right">备注:&nbsp;</th>
					<td><s:textfield id="memo" name="brand.memo" value="%{brand.memo}" theme="simple" /></td>
				</tr>
				<tr>
					<th>radio:&nbsp;</th>
					<td><input name="a" type="radio" value="" />男&nbsp;&nbsp;<input name="a" type="radio" value="" />女</td>
					<th>checkbox :&nbsp;</th>
					<td><input name="" type="checkbox" value="" />多选1&nbsp;&nbsp;<input name="" type="checkbox" value="" />多选2&nbsp;&nbsp;<input name="" type="checkbox" value="" />多选3&nbsp;&nbsp;<input name="" type="checkbox" value="" />多选4&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<th>button:&nbsp;</th>
					<td><input type="button" value="button" class="btn"></td>
					<th>file:&nbsp;</th>
					<td><input name="" type="file" value="" /></td>
				</tr>
				<tr>
					<th>时间:&nbsp;</th>
					<td><input id="time-form2" data-text-tooltip="时间格式 yyyy-MM-dd HH:mm" name="" type="text" /> <img onclick="showTime('time-form2','yyyy-MM-dd HH:mm')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th>时间:&nbsp;</th>
					<td><input id="time-form3" data-text-tooltip="时间格式 yyyy-MM-dd" name="" type="text" /> <img onclick="showTime('time-form3','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr>
					<th>索引:&nbsp;</th>
					<td colspan="3"><input id="demo-input-facebook-theme-form" name="" type="text" /> <script type="text/javascript">
							$(function() {
								$("#demo-input-facebook-theme-form").tokenInput("http://shell.loopj.com/tokeninput/tvshows.php", {theme: "facebook"});
							});
						</script></td>
				</tr>
				<tr>
					<th>下拉:&nbsp;</th>
					<td><div class="no-border">
							<div id="combo-form1" class="combo"></div>
						</div> <script type="text/javascript">
							$(function(){
								var dd = [];
								for(var i=1;i<=5; i++)
									dd.push({ code: i + '', name: 'Employee ' + i });
								var cfg = {
									keyField: 'code',
									displayField: 'name',
									multiSelect: false,
									width: 200,
									boxWidth: 200,
									cols : [{
										field: 'code', width: '28%'
									},{
										field: 'name', width: '70%'
									}],
									data: dd
								};
								var cfg1 = $.extend({}, cfg);
								var cb1 = $('#combo-form1').mac('combo', cfg1);
								$('#get1').click(function(){
									alert(cb1.selected);
								});
								$('#set1').click(function(){
									cb1.select(2);
								});
								var cfg2 = $.extend({}, cfg);
								cfg2.multiSelect = true;
								var cb2 = $('#combo-form2').mac('combo', cfg2);
								$('#get2').click(function(){
									alert(cb2.selected.join(','));
								});
								$('#set2').click(function(){
									cb2.select([2,4]);
								});
							});
						</script></td>
					<th>下拉多选:&nbsp;</th>
					<td><div class="no-border">
							<div id="combo-form2" class="combo"></div>
						</div></td>
				</tr>
				<tr>
					<th>select:&nbsp;</th>
					<td><select name="" class="select-form" style="height: 24px; line-height: 24px;">
							<option>select</option>
							<option>select</option>
							<option>select</option>
							<option>select</option>
					</select></td>
					<th>select:&nbsp;</th>
					<td><select name="" size="3" multiple="multiple" class="select-form">
							<option>select</option>
							<option>select</option>
							<option>select</option>
							<option>select</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="4">
						<div class="areabox">
							<textarea name="" id="area" cols="" rows="" style="width: 670px; height: 200px;"></textarea>
						</div> <script>
					$(function(){
						new nicEditor().panelInstance('area');
						 
					});
					</script>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>