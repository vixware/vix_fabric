<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function radioCheck(id,name){
	$.returnValue = id+":"+name;
}
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<div>
			<label>Name<input id="nameS" name="nameS" type="text" class="int" /></label> <label>My items<input name="" type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="Search" onclick="searchAccount();" /> </label> <strong id="lb_search_advanced"><s:text name="cmn_advance_search" /></strong>
		</div>
		<div class="search_advanced">
			<label>Name<input name="" type="text" class="int" /></label> <label>My items<input name="" type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="Search" /> </label> <label>Name<input name="" type="text" class="int" /></label> <label>My items<input name="" type="checkbox" value="" /></label> <label><input
				name="" type="button" class="btn" value="Search" /> </label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="right">
			<div class="right_content">
				<div id="crmProject" class="table">
					<table id="caTable" class="list">
						<tbody>
							<tr class="alt">
								<th>选择</th>
								<th width="15%">编号</th>
								<th width="20%">${vv:varView('vix_mdm_item')}</th>
								<th width="25%">数量</th>
							</tr>
							<s:iterator value="salesOrderItems" var="entity" status="s">
								<tr>
									<td><input id="chkAccountId" name="chkAccountId" value="${entity.id}" type="radio" onclick="radioCheck(${entity.id},'${entity.item.name}');" /></td>
									<td>${s.count + (pager.pageSize * (pager.pageNo -1))}</td>
									<td>${entity.item.name}</td>
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