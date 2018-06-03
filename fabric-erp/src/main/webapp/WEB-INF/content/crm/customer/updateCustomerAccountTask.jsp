<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 5px;">
	<form id="taskForm">
		<div class="order">
			<dl>
				<dt>
					<strong> <b> <s:if test="task.id > 0">
								${task.customerAccount.name}
							</s:if> <s:else>
								新增代办任务
							</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<s:hidden id="taskId" name="task.id" value="%{task.id}" theme="simple" />
						<s:hidden id="customerAccountId" name="task.customerAccount.id" value="%{task.customerAccount.id}" theme="simple" />
						<table>
							<tr height="30">
								<td align="right">主题:&nbsp;</td>
								<td><input type="text" id="subject" name="task.subject" value="${task.subject}" /></td>
							</tr>
							<tr height="30">
								<td align="right">开始时间:&nbsp;</td>
								<td><input type="text" id="startTime" name="task.startTime" value="${task.startTime}" /> <img onclick="showTime('startTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
								<td align="right">结束时间:&nbsp;</td>
								<td><input type="text" id="endTime" name="task.endTime" value="${task.endTime}" /> <img onclick="showTime('endTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
							</tr>
							<tr height="30">
								<td align="right">内容:&nbsp;</td>
								<td colspan="3"><textarea id="taskContent" rows="4" cols="60" style="font-size: 12px;">${task.content}</textarea></td>
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
	$("#taskForm").validationEngine();
</script>