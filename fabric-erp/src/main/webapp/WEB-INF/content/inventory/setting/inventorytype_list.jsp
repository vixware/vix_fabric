<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateContactPersonType() {
		var params = "";
		for (var i = 1; i < 9; i++) {
			var id = $("#customerSourceId_" + i).val();
			var radio = '0';
			if ($("#customerSourceRadio_" + i).attr('checked')) {
				radio = '1';
			}
			var checkbox = '0';
			if ($("#customerSourceCheckbox_" + i).attr('checked')) {
				checkbox = '1';
			}
			var name = $("#customerSourceName_" + i).val();
			var memo = $("#customerSourceMemo_" + i).val();
			if (!(id == '' && name == '' && memo == '' && radio == '0' && checkbox == '0')) {
				if (id == '') {
					id = "Placeholder";
				}
				if (name == '') {
					name = "Placeholder";
				}
				if (memo == '') {
					memo = "Placeholder";
				}
				params += id + ":" + radio + ":" + checkbox + ":" + name + ":" + memo + ",";
			}
		}
		$.post('${vix}/inventory/inventoryTypeAction!saveOrUpdate.action', {
			'data' : params
		}, function(result) {
			asyncbox.success(result, "提示");
		});
	};
	//载入分页列表数据
	pager("start", "${vix}/inventory/inventoryTypeAction!goListContent.action?name=" + name, 'customerSource');
</script>
<div class="sub_menu">
	<h2>入库类别</h2>
</div>
<div class="content">
	<div class="box">
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div id="customerSource" class="table"></div>
			</div>
			<div id="search_bar" style="margin-bottom: 100px;">
				<p style="text-align: center;">
					<label><input type="button" value="保存" class="btn" onclick="saveOrUpdateContactPersonType();" /></label>
				</p>
			</div>
		</div>
		<!-- right -->
	</div>
</div>