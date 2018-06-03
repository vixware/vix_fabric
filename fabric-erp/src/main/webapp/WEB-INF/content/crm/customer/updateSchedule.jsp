<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 5px;">
	<form id="scheduleForm">
		<div class="order">
			<dl>
				<dt>
					<strong> <b> <s:if test="schedule.id > 0">
								${schedule.subject}
							</s:if> <s:else>
								新增日程
							</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<s:hidden id="scheduleId" name="schedule.id" value="%{schedule.id}" theme="simple" />
						<table>
							<tr height="30">
								<td align="right">主题:&nbsp;</td>
								<td><input type="text" id="subject" name="schedule.subject" value="${schedule.subject}" /></td>
								<td>日期</td>
								<td><input type="text" id="time" name="schedule.time" value="${schedule.time}" /> <img onclick="showTime('time','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
							</tr>
							<tr height="30">
								<td align="right">内容:&nbsp;</td>
								<td colspan="3"><textarea id="content" rows="3" cols="40" style="font-size: 12px;">${schedule.content}</textarea></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
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
	$("#scheduleForm").validationEngine();
</script>