<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="taskEmployeeForm" class="form-horizontal" style="padding: 20px 15px;" action="">
	<input type="hidden" id="employeeIds" name="employeeIds" value="" /> <input type="hidden" id="vixTaskId" name="vixTask.id" value="${vixTask.id }" />
	<div class="col-sm-12">
		<h3>分配</h3>
		<div class="row">
			<s:if test="empList.size > 0">
				<s:iterator value="empList" var="entity" status="s">
					<div class="col-sm-3 col-md-3 col-lg-3 text-center app-del" onclick="deleteTaskEmployee('${entity.id }','${vixTask.id }');">
						<span class="glyphicon glyphicon-minus"></span> <a data-toggle="modal" href="#"><img src="${entity.headImgUrl }" class="btn-circle no-padding btn-xl"></a>
					</div>
				</s:iterator>
			</s:if>
			<div class="col-sm-3 col-md-3 col-lg-3 text-center app-del">
				<a data-toggle="modal" id="assignId" onclick="chooseEmployee();" style="cursor: pointer;"><img src="${nvix}/vixntcommon/base/img/add.png" class="btn-circle no-padding btn-xl"></a>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
	$('.app-del').hover(function() {
		$(this).children('span').css('display', 'block');
	}, function() {
		$(this).children('span').css('display', 'none');
	});
</script>