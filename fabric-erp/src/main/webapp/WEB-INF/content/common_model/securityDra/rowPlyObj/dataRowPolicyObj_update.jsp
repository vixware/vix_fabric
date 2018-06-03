<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--
function addDataRowMetaData(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectMetaDataAction!goChooseMetaData.action?tag=choose&chkStyle=radio',//dataResRowOwnerAction
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 860,
					height : 540,
					title:"选择元数据",
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
							
							$("#addDataRowMetaDataIds").val(roleIds);
							$("#addDataRowMetaDataNames").html(roleNames);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function deleteDataRowOwnerRole(row,id){
	$(row).parent().parent().remove();
	$("#deleteDataRowMetaDataIds").val($("#deleteDataRowMetaDataIds").val()+","+id);
}

function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form action="" id="dataRowPolicyObjForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">权限标识名称:&nbsp;</td>
					<td><input type="text" id="metaDataViewName" name="entityForm.metaDataViewName" value="${entity.metaDataViewName}" data-text-tooltip="必须填写！" class="sweet-tooltip" style="border: 1px solid rgb(204, 204, 204); background: none repeat scroll 0% 0% rgb(255, 255, 255);" /></td>
					<td align="right">优先级:&nbsp;</td>
					<td><input type="text" id="priory" name="entityForm.priory" value="${entity.priory}" /></td>
				</tr>
				<tr height="30">
					<td align="right">分配元数据:</td>
					<td><a id="addDataRowOwnerRole" href="#" onclick="addDataRowMetaData();" style="color: #000000; text-decoration: none;">选择</a></td>
					<td align="right">元数据:</td>
					<input type="hidden" id="metadataId" name="entityForm.baseMetaData.id" value="${entity.baseMetaData.id}" />
					<td colspan="3"><span id="addDataRowMetaDataNames">${entity.baseMetaData.metaDataDisplayName}</span></td>
				</tr>
			</table>
			<!-- <hr/> -->
			<input type="hidden" id="addDataRowMetaDataIds" name="addDataRowMetaDataIds" value="" /> <input type="hidden" id="deleteDataRowMetaDataIds" name="deleteDataRowMetaDataIds" value="" />
			<div class="table" style="margin: 5px;">
				<%-- <p id="table_top" style="border: 1px solid #D6D6D6;color: #CDCDCD;padding: 5px;">
				<img src="${vix}/common/img/icon_22.gif"/><a id="bradn" href="#" onclick="brandList()" style="color: #000000; text-decoration: none;">角色列表</a>
			</p>
			<table id="brandSub" class="list">
				<tr>
					<th width="10%">编号</th>
					<th width="80%">名称</th>
					<th width="10%">操作</th>
				</tr>
				<s:iterator value="entity.roles" var="role" status="s">
					<tr>
						<td align="right">${s.count}</td>
						<td align="right">${role.name}</td>
						<td align="center">
							<a href="#" onclick="deleteUserAccountRole(this,${role.id});" title="删除">
								<img src="${vix}/common/img/icon_12.png"/>
							</a>
						</td>
					</tr>
				</s:iterator>
			</table> --%>
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