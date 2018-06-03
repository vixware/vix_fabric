<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
/**申请人*/
function chooseEmployees(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 600,
					title:"选择申请人",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = "";
								var selectNames = "";
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										
										selectIds += "," + v[0];
										selectNames = v[1];
									}
								}
								$("#approver").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

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
		<form id="brandForm">
			<input type="hidden" id="" name="" value="" />
			<table width="100%">
				<tr>
					<th class="tr" width="150">部门：</th>
					<td><input id="" name="" value="" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr">日期：</th>
					<td><input id="" type="text" name="" value="" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'blue'})"></td>
				</tr>
				<tr>
					<th class="tr">申请人：</th>
					<td><input id="" name="" value="" type="text" style="width: 200px; height: 22px;" validate[required] text-input" /> <input type="hidden" id="" name="" value="" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /></td>
				</tr>
				<tr>
					<th class="tr" width="150">单位：</th>
					<td><input id="" name="" value="" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr" width="150">主要货物品称：</th>
					<td><input id="" name="" value="" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr" width="150">种类：</th>
					<td><input id="" name="" value="" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr" width="150">货物总数量：</th>
					<td><input id="" name="" value="" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr">要求送达日期：</th>
					<td><input id="" type="text" name="" value="" class="time_aoto_input ipt" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'blue'})"></td>
				</tr>
				<tr>
					<th class="tr">其它说明：</th>
					<td colspan="3"><textarea id="" class="example" rows="1" style="width: 508px; height: 75px;">  </textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>