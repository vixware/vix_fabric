<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$(".list tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$(".list tr:even").addClass("alt");
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div>
			<label>客户名称:<input id="nameS" name="nameS" type="text" class="int" value="${customerAccount.name}" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div class="right_content" style="min-height: 100px;">
				<div id="customerAccount" class="table">
					<table class="list">
						<tbody>
							<tr class="alt">
								<th width="20%">期次</th>
								<th width="20%">日期&nbsp;</th>
								<th width="30">客户</th>
								<th width="30%">回款金额</th>
							</tr>
							<s:iterator value="pager.resultList" var="entity" status="s">
								<tr>
									<td>${entity.stageNumber}</td>
									<td><s:property value="formatDateToString(#entity.backSectionDate)" /></td>
									<td>${entity.customerAccount.name}</td>
									<td>${entity.amount}</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>