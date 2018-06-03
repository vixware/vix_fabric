<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var code = "";
	function loadcode() {
		code = $('#code').val();
		code = Url.encode(code);
		code = Url.encode(code);
	}
	var selectname = "";
	function loadselectname() {
		selectname = $('#selectname').val();
		selectname = Url.encode(selectname);
		selectname = Url.encode(selectname);
	}
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<th>编码：</th>
				<td><input id="code" name="code" type="text" /></td>
				<th>主题：</th>
				<td><input id="selectname" name="selectname" type="text"></td>
			</tr>
		</table>
	</div>
</div>