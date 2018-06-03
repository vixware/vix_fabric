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
					<th align="right">部门名称:&nbsp;</th>
					<td><input id="name" name="brand.name" value="${brand.name}" data-text-tooltip="Tip tip tip tip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" class="validate[required] text-input" /></td>
					<th align="right">联系电话:&nbsp;</th>
					<td><s:textfield id="memo" name="brand.memo" value="%{brand.memo}" theme="simple" /></td>
				</tr>
				<tr>
					<th>创建时间:&nbsp;</th>
					<td><input id="chuangjiantime" data-text-tooltip="时间格式 yyyy-MM-dd" name="" type="text" /> <img onclick="showTime('chuangjiantime','yyyy-MM-dd')" src="img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th>传真 :&nbsp;</th>
					<td><s:textfield id="chuanzhen" name="brand.chuanzhen" value="%{brand.chuanzhen}" theme="simple" /></td>
				</tr>
				<tr>
					<th>Email:&nbsp;</th>
					<td><s:textfield id="email" name="brand.email" value="%{brand.email}" theme="simple" /></td>
					<th>部门助记码:&nbsp;</th>
					<td><s:textfield id="bumen" name="brand.bumen" value="%{brand.bumen}" theme="simple" /></td>
				</tr>
				<tr>
					<th>组织架构:&nbsp;</th>
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
					<th>组织级别:&nbsp;</th>
					<td><div class="no-border">
							<div id="combo-form2" class="combo"></div>
						</div></td>
				</tr>
				<tr>
					<th>部门职责:&nbsp;</th>
					<td colspan="4">
						<div class="areabox">
							<textarea name="" cols="" rows="" style="width: 220px; height: 50px;"></textarea>
						</div>
				</tr>
				<tr>
					<th>备注:&nbsp;</th>
					<td colspan="4">
						<div class="areabox">
							<textarea name="" cols="" rows="" style="width: 220px; height: 50px;"></textarea>
						</div>
				</tr>
			</table>
		</div>
	</form>
</div>