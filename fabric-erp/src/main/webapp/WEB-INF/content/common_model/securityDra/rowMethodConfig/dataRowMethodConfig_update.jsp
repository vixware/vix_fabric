<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
<!--

function addDataRowMetaData(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectMetaDataAction!goChooseMetaData2.action?tag=choose&chkStyle=radio',//dataResRowOwnerAction
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
									break;
								}
							}
							
							roleIds = roleIds.substring(1,roleIds.length);
							roleNames = roleNames.substring(1,roleNames.length);
							$("#baseMetaDataId").val(roleIds);
							$("#baseMetaDataName").html(roleNames);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

/**
 * 选择hqlProvider
 */
function chooseDataResRowMethod(){
	$.ajax({
		  url:'${vix}/common/security/dataResRowMethodAction!goChooseDataResRowMethod.action?tag=choose&chkStyle=radio',//dataResRowOwnerAction
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 860,
					height : 540,
					title:"选择方法配置项",
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
									break;
								}
							}
							
							roleIds = roleIds.substring(1,roleIds.length);
							roleNames = roleNames.substring(1,roleNames.length);
							$("#dataResRowMethodId").val(roleIds);
							$("#hqlProviderName").val(roleNames);
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
	<form action="" id="dataRowMethodConfigForm" name="dataRowMethodConfigForm">
		<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td width="20%" align="right">查询方法配置:&nbsp;</td>
					<td width="80%" colspan="3"><input type="hidden" id="dataResRowMethodId" name="entityForm.dataResRowMethod.id" value="${entity.dataResRowMethod.id}" /> <%-- <span id="parentTreeName"><s:property value="parentTreeName"/></span> --%> <input type="text" id="hqlProviderName" name="hqlProviderName" style="width: 80%;" readonly="readonly"
						class="validate[required] text-input" value="${entity.dataResRowMethod.hqlProvider}" /> <input class="btn" type="button" value="选择" onclick="chooseDataResRowMethod();" /></td>
				</tr>
				<tr height="40">
					<td align="right">分配元数据:</td>
					<td><a id="addDataRowOwnerRole" href="#" onclick="addDataRowMetaData();" style="color: #000000; text-decoration: none;">选择</a></td>
					<td align="left">元数据:</td>
					<td><span id="baseMetaDataName">${entity.baseMetaData.metaDataName}</span> <input id="baseMetaDataId" name="entityForm.baseMetaData.id" type="hidden" value="${entity.baseMetaData.id}" class="validate[required] text-input" /></td>
				</tr>
			</table>
			<!-- <hr/> -->
			<!-- <input type="hidden" id="addDataRowMetaDataIds" name="addDataRowMetaDataIds" value=""/>
		<input type="hidden" id="deleteDataRowMetaDataIds" name="deleteDataRowMetaDataIds" value=""/> -->
			<div class="table" style="margin: 5px;">
				<%-- <p id="table_top" style="border: 1px solid #D6D6D6;color: #CDCDCD;padding: 5px;">
				<img src="${vix}/common/img/icon_22.gif"/><a id="bradn" href="#" onclick="brandList()" style="color: #000000; text-decoration: none;">元数据列表</a>
			</p> --%>
				<%-- <table id="brandSub" class="list">
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
							<a href="#" onclick="deleteDataRowOwnerRole(this,${role.id});" title="删除">
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