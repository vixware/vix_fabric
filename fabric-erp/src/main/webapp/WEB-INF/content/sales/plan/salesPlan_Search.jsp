<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
var fs = "";
var salesMan = "";
var endTime = "";
function loadConditions(){
	name = $('#name').val();
	name=Url.encode(name);
	name=Url.encode(name);
	fs = $('#fs').val();
	fs=Url.encode(fs);
	fs=Url.encode(fs);
	salesMan = $("#salesMan").val();
	salesMan=Url.encode(salesMan);
	salesMan=Url.encode(salesMan);
}
loadConditions();
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<th>
				<td>名称 : <input type="text" name="name" id="name" class="int" />
				</td>
				<td>部门 : <input type="text" name="fs" id="fs" class="int" />
				</td>
				<td>计划人: <input type="text" name="salesMan" id="salesMan" class="int" />
				</td>
				</th>
			</tr>
		</table>
	</div>
</div>