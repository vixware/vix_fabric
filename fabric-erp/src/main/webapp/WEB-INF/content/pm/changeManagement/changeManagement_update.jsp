<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
    function saveOrUpdateChangeRecord() {
	    if ($('#order').validationEngine('validate')) {
		    $.post('${vix}/pm/changeManageAction!saveOrUpdate.action', {
		    'changeRecord.id' : $("#id").val(),
		    'changeRecord.code' : $("#code").val(),
		    'changeRecord.name' : $("#name").val(),
		    'changeRecord.content' : $("#changeRecordContent").val(),
		    'changeRecord.modifier' : $("#modifier").val(),
		    'changeRecord.checker' : $("#checker").val(),
		    'changeRecord.approver' : $("#approver").val(),
		    'changeRecord.modifyTime' : $("#modifyTime").val()
		    }, function(result) {
			    showMessage(result);
			    setTimeout("", 1000);
			    loadContent('${vix}/pm/changeManageAction!goList.action');
		    });
	    }
    }
    $("#order").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" /> 供应链 </a></li>
				<li><a href="#">项目管理 </a></li>
				<li><a href="#">追踪管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/pm/changeManageAction!goList.action?pageNo=${pageNo}');">变更管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${changeRecord.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateChangeRecord()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#"><img width="22" height="22" title="取消" src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#" onclick="loadContent('${vix}/pm/changeManageAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>变更管理
					</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>编号：</th>
											<td><input id=code name="code" value="${changeRecord.code }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>主题：</th>
											<td><input id="name" name="name" value="${changeRecord.name }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>变更内容：</th>
											<td><input id="changeRecordContent" name="changeRecordContent" value="${changeRecord.content}" type="text" size="30" /></td>
											<th>负责人：</th>
											<td><input id="modifier" name="modifier" value="${changeRecord.modifier}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>审核人：</th>
											<td><input id="checker" name="checker" value="${changeRecord.checker}" type="text" size="30" /></td>
											<th>批准人：</th>
											<td><input id="approver" name="approver" value="${changeRecord.approver}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>修改时间：</th>
											<td><input id="modifyTime" name="modifyTime" value="${changeRecord.modifyTime}" type="text" /><img onclick="showTime('modifyTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>