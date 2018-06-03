<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
<script type="text/javascript">
	function chooseChkCustomer() {
		$
				.ajax({
					url : '${vix}/template/simpleGridAction!goChooseChkSimpleGrid.action',
					cache : false,
					success : function(html) {
						asyncbox.open({
							modal : true,
							width : 760,
							height : 440,
							title : "选择客户",
							html : html,
							callback : function(action, returnValue) {
								if (action == 'ok') {
									if (returnValue != undefined) {
										$("#customerCChk").html(returnValue);
									} else {
										$("#customerCChk").html("");
										asyncbox.success("请选择分类信息!", "提示信息");
									}
								}
							},
							btnsbar : $.btn.OKCANCEL
						});
					}
				});
	}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr>
				<td align="right"><s:text name="cmn_id" />:</td>
				<td><input id="name" name="BO_SR_CMNM_name" type="text" size="30" /></td>
				<td align="right"><s:text name="ctm_warning_time" />:</td>
				<td><input id="artificialPerson" name="BO_SR_CMNM_artificialPerson" type="text" size="30" /></td>
			</tr>
			<tr>
				<td align="right"><s:text name="ctm_warning_type" />:</td>
				<td><select name="BO_SR_CMNM_catalog" style="width: 50"><option>请选择</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="4"><s:text name="ctm_warning_information" />:
					<div class="areabox">
						<textarea name="" id="area" cols="" rows="" style="width: 670px; height: 200px;"></textarea>
					</div> <script>
						$(function() {
							//$('#area').width($('.areabox').width());
							new nicEditor().panelInstance('area');

						});
					</script></td>
			</tr>

		</table>
	</div>
</div>