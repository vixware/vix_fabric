<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$.returnValue = "";
	function chkChange(chk, id, name) {
		if (chk.checked) {
			$.returnValue = $.returnValue + "," + id + ":" + name;
		}
	}
</script>

<div class="content" style="margin-top: 15px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table>
			<s:iterator var="ef" value="memberTagList" status="s">
				<s:if test="#s.count%4 == 1 ">
					<tr height="40">
				</s:if>
				<td align="left"><input id="chk_${ef.id }" name="chk_${ef.id }" value="${ef.id}" type="checkbox" onclick="chkChange(this,${ef.id},'${ef.name}')" />${ef.name}</td>
				<s:if test="#s.count%4 == 0 ">
					</tr>
				</s:if>
			</s:iterator>
		</table>
	</div>
</div>