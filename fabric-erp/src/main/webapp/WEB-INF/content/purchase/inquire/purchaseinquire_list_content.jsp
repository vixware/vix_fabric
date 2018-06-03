<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<!-- 列表区  -->
<table id="purchase_inquire_grid" class="list table" pager='<s:property value="pager.genPagerInfoJsonStr()" escape="false"/>' fillblank="1">
	<thead>
		<tr class="alt">
			<th width="5%">
				<div class="list_check">
					<div class="drop">
						<label><input type="checkbox" name="chkIds" onchange="chooseAll(this)"> </label>
						<ul>
							<li><a href="#" onclick="chooseAll();"><s:text name="cmn_all" /> </a></li>
							<li><a href="#"><s:text name="cmn_other" /> </a></li>
							<li><a href="#"><s:text name="cmn_close" /> </a></li>
						</ul>
					</div>
				</div>
			</th>
			<th width="15%" sColumn="code">编码</th>
			<th width="30%" sColumn="name"><s:text name="pur_theme" /></th>
			<th width="15%">金额</th>
			<th width="15%" sColumn="purchasePerson"><s:text name="pur_clerk" /></th>
			<th width="15%" sColumn="appDate"><s:text name="pur_application_time" /></th>
			<th width="5%"><s:text name="cmn_operate" /></th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="pager.resultList" var="entity" status="s">
			<tr id="purchaseinquire_${entity.id}">
				<td><input id="chkId" name="chkId" value="${entity.id}" type="checkbox" onchange="selectCount()" /></td>
				<td><span style="color: gray;">${entity.code }</span></td>
				<td><span style="color: gray;">${entity.name }</span></td>
				<td><span style="color: gray;">${entity.total }</span></td>
				<td><span style="color: gray;">${entity.purchasePerson }</span></td>
				<td><span style="color: gray;"><s:date name="#entity.appDate" format="yyyy-MM-dd" /></span></td>
				<td style="padding-top: 2px;">
					<div class="untitled" style="position: static; display: inline;">
						<span><img alt="" src="img/icon_untitled.png"> </span>
						<div class="popup" style="display: none; top: -7px;">
							<strong> <i><a href="#" onclick="deleteEntity('${entity.id}');" title="<s:text name='cmn_delete'/>"> <img src="${vix}/common/img/icon_delete.png" />
								</a></i> <i><a href="#" title="查看" onclick="showPurchaseInquire('${entity.id}');"><img src="${vix}/common/img/icon_show.gif" /></a> </i> <i><a href="#" onclick="saveOrUpdate('${entity.id}');" title="编辑"><img src="${vix}/common/img/icon_edit.png" /></a> </i> <b>${entity.name}</b>
							</strong>
							<p>${entity.memo}</p>
						</div>
					</div>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<script type="text/javascript">
	function deleteEntity(id) {
		asyncbox.confirm('确定要删除?', '提示信息', function(action) {
			if (action == 'ok') {
				$.ajax({
				url : "${vix}/purchase/purchaseInquireAction!deleteById.action",
				data : "id=" + id,
				dataType : "text",
				success : function(data) {
					if (data == 'success') {
						showMessage('操作完毕');
						$('#purchaseinquire_' + id).remove();
					} else {
						showErrorMessage('操作失败');
					}
				}
				});
			}
		});
	}
</script>