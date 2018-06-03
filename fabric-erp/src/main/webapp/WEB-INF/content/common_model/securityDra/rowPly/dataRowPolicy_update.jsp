<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--

function addDataRowPolicyObj(){
	$.ajax({
		  url:'${vix}/common/security/dataResRowPolicyAction!goChooseRowPolicy.action?tag=choose',//dataResRowPolicyAction
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 760,
					height : 520,
					title:"选择",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							var roleIds = "";
							var roleNames = "";
							var result = returnValue.split(",");
							for (var i=0; i<result.length; i++){
								if(result[i].length>1){
									var v = result[i].split(":");
									roleIds += "," + v[0];
									roleNames += "," + v[1];
								}
							}
							
							roleIds = roleIds.substring(1,roleIds.length);
							roleNames = roleNames.substring(1,roleNames.length);
							
							$("#addDataRowPolicyObjIds").val(roleIds);
							$("#addDataRowPolicyObjNames").html(roleNames);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function deleteDataRowPolicyObj(row,id){
	$(row).parent().parent().remove();
	$("#deleteDataRowPolicyObjIds").val($("#deleteDataRowPolicyObjIds").val()+","+id);
}

function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form action="" id="dataRowPolicyForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">策略名称:&nbsp;</td>
					<td><input type="text" id="policyName" name="entityForm.policyName" value="${entity.policyName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">策略状态:&nbsp;</td>
					<td><s:if test="entity.policyStatus == \"Y\"">
							<input type="radio" id="policyStatus1" name="entityForm.policyStatus" value="Y" checked="checked" />正常
						<input type="radio" id="policyStatus2" name="entityForm.policyStatus" value="N" />无效
					</s:if> <s:elseif test="entity.policyStatus == \"N\"">
							<input type="radio" id="policyStatus1" name="entityForm.policyStatus" value="Y" />正常
						<input type="radio" id="policyStatus2" name="entityForm.policyStatus" value="N" checked="checked" />无效
					</s:elseif> <s:else>
							<input type="radio" id="policyStatus1" name="entityForm.policyStatus" value="Y" />正常
						<input type="radio" id="policyStatus2" name="entityForm.policyStatus" value="N" />无效
					</s:else></td>
				</tr>
				<%-- <tr height="40">
				<td align="right">policyName:&nbsp;</td>
				<td><s:select list="#{'R':'角色'}" id="PolicyType" name="entityForm.PolicyType" value="%{entity.PolicyType}" theme="simple"></s:select></td>
				<td align="right">权限策略:&nbsp;</td>
				<td><s:select list="dataResRowPolicyList" listKey="id" listValue="policyName" id="dataResRowPolicyId" name="entityForm.dataResRowPolicy.id" value="%{entity.dataResRowPolicy.id}" theme="simple"></s:select></td>
			</tr> --%>
				<tr height="30">
					<td align="right">分配数据权限条件:</td>
					<td><a id="addDataRowPolicyObj" href="#" onclick="addDataRowPolicyObj();" style="color: #000000; text-decoration: none;">添加</a></td>
					<td align="right">权限条件:</td>
					<td colspan="3"><span id="addDataRowPolicyObjNames"></span></td>
				</tr>
			</table>
			<hr />
			<input type="hidden" id="addDataRowPolicyObjIds" name="addDataRowPolicyObjIds" value="" /> <input type="hidden" id="deleteDataRowPolicyObjIds" name="deleteDataRowPolicyObjIds" value="" />
			<div class="table" style="margin: 5px;">
				<p id="table_top" style="border: 1px solid #D6D6D6; color: #CDCDCD; padding: 5px;">
					<img src="${vix}/common/img/icon_22.gif" /><a id="bradn" href="#" onclick="brandList()" style="color: #000000; text-decoration: none;">权限条件列表</a>
				</p>
				<table id="brandSub" class="list">
					<tr>
						<th width="10%">编号</th>
						<th width="80%">名称</th>
						<th width="10%">操作</th>
					</tr>
					<s:iterator value="entity.dataResRowPolicyObjs" var="plyObj" status="s">
						<tr>
							<td align="right">${s.count}</td>
							<td align="right">${plyObj.metaDataViewName}</td>
							<td align="center"><a href="#" onclick="deleteDataRowPolicyObj(this,${plyObj.id});" title="删除"> <img src="${vix}/common/img/icon_12.png" />
							</a></td>
						</tr>
					</s:iterator>
				</table>
				<%-- <table id="dimensionSub" class="list" style="display:none;">
				<tr>
					<th width="10%">编号</th>
					<th width="80%">名称</th>
					<th width="10%">操作</th>
				</tr>
				<s:iterator value="category.dimensions" var="entity" status="s">
					<tr>
						<td>${s.count}</td>
						<td>${entity.name}</td>
						<td align="center">
							<a href="#" onclick="deleteUserAccountRoleDimension(this,${entity.id});" title="删除">
								<img src="${vix}/common/img/icon_12.png"/>
							</a>
						</td>
					</tr>
				</s:iterator>
			</table> --%>
			</div>
		</div>
	</form>
</div>
<script src="${vix}/common/js/jtip.js" type="text/javascript"></script>
<script type="text/javascript">
<!--
//提示
if($('input.sweet-tooltip').length){
	$('input.sweet-tooltip').focus(function() {
		tooltip				= $(this);
		tooltipText 		= tooltip.attr('data-text-tooltip');
		tooltipClassName	= 'tooltip';
		tooltipClass		= '.' + tooltipClassName;
		
		if(tooltip.hasClass('showed-tooltip')) return false;
		
		tooltip.addClass('showed-tooltip')
				 .after('<div class="'+tooltipClassName+'"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">'+tooltipText+'</div><div class="tooltip_b"></div></div>');
		tooltipPosTop 	= tooltip.position().top - $(tooltipClass).outerHeight() - 10;
		tooltipPosLeft = tooltip.position().left;
		tooltipPosLeft = tooltipPosLeft - (($(tooltipClass).outerWidth()/2) - tooltip.outerWidth()/2);
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop }).animate({'opacity':'1', 'marginTop':'0'}, 500);
	}).focusout(function() {
		$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
			$(this).remove();
			tooltip.removeClass('showed-tooltip');
				
		});
	});
}
//-->
</script>