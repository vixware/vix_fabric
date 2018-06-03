<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var code = "";
var name = "";
function loadConditions(){
	code = $('#code').val();
	code=Url.encode(code);
	code=Url.encode(code);
	name = $('#name').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
loadConditions();
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>编码 : <input type="text" name="code" id="code" class="int" />
				</td>
				<td>名称 : <input type="text" name="name" id="name" class="int" />
				</td>
			</tr>

		</table>
	</div>
</div>