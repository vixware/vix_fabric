<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="addReasonForm">
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="30">
					<td align="right">原因:&nbsp;</td>
					<td width="85%"><s:if test="type == 'add'">
							<textarea id="reason" style="width: 406px; height: 34px;">${contactPerson.blackReason}</textarea>
						</s:if> <s:if test="type == 'remove'">
							<textarea id="reason" style="width: 406px; height: 34px;">${contactPerson.removeBlackReason}</textarea>
						</s:if></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
	$("#addReasonForm").validationEngine();
</script>