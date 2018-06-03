<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
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
 
$("#customerServiceNotepadForm").validationEngine();
//-->
</script>
<input type="hidden" id="customerServiceNotepadId" name="customerServiceNotepadId" value="${customerServiceNotepad.id}" />
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="customerServiceNotepadForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">记录时间:</td>
					<td><input id="noteDate" name="customerServiceNotepad.noteDate" value="${customerServiceNotepad.noteDate}" type="text" /> <img onclick="showTime('noteDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="30">
					<td align="right">内容:</td>
					<td><textarea id="csnContent" rows="3" cols="60" style="font-size: 12px;">${customerServiceNotepad.content}</textarea></td>
				</tr>
			</table>
		</div>
		<!--oder-->
	</form>
</div>
<!-- content -->
