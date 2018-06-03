<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function chooseParentBusinessOrg(){
	$.ajax({
		  url:'${vix}/common/org/businessOrgAction!goChooseBusinessOrg.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择上级",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								if(result[0] == $("#id").val()){
									//asyncbox.success("不允许引用本组织为父组织!","提示信息");
									showMessage("不允许引用本组织为父组织!");
									setTimeout("", 1000);
								}else{
									$("#parentId").val(result[0]);
									$("#parentTreeName").val(result[1]);
								}
							}else{
								$("#parentId").val("");
								$("#parentTreeName").val("");
								showMessage("请选择业务组织信息!");
								setTimeout("", 1000);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}


function addBizOrgObject(){
	
	var pubTypeVal = $("input[name=bizOrgType]:checked").val();
	if(pubTypeVal=="O"){
		chooseBizOrgUnit($("#bizOrgIds").val());
	}else if(pubTypeVal=="R"){
		chooseBizRole($("#bizOrgIds").val());
	}else if(pubTypeVal=="E"){
		chooseBizEmp($("#bizOrgIds").val());
	}
}

function clearBizOrgType(){
	$("#bizOrgIds").val("");
	$("#bizOrgNames").val("");
}

function changeBizOrgType(typeValue){
	clearBizOrgType();
}

/**
 * 选择部门
 */
function chooseBizOrgUnit(checkObjIds){
	$.ajax({
		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
		  data:{checkedObjIds:checkObjIds,chkStyle:"radio"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择部门",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";
								var resObj = $.parseJSON(returnValue);
								
								for(var i=0;i<resObj.length;i++){
									//alert(resObj[i].treeId+"--"+resObj[i].name);
									/* if(resObj[i].treeType!="O"){
										asyncbox.alert("只能选择部门信息！","提示");
										return;
									} */
									//if(!pubIdTmp.contains(resObj[i].treeId+",")){
									
									if(resObj[i].treeId.contains("C")){
										//asyncbox.alert("不能选择公司作为业务组织！","提示");
										showMessage("不能选择公司作为业务组织！");
										setTimeout("", 1000);
										return;
									}
									selectIds = resObj[i].treeId;
									selectNames = resObj[i].name;
									break;
									//}
									
								}
								
								$("#bizOrgIds").val(selectIds);
								//selectNames = selectNames.substring(1,selectNames.length);
								$("#bizOrgNames").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}


/**
 * 选择角色
 */
function chooseBizRole(checkObjIds){
	$.ajax({
		  url:'${vix}/common/select/commonSelectRoleAction!goList.action',
		  data:{chkStyle:"radio"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 760,
					height : 520,
					title:"选择角色",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";

								/* if(resObj.length == 0 ){
									return;
								} */
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										selectIds = v[0];
										selectNames = v[1];
									}
								}
								
								
								$("#bizOrgIds").val(selectIds);
								//selectNames = selectNames.substring(1,selectNames.length);
								$("#bizOrgNames").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}



/**
 * 选择人员
 */
function chooseBizEmp(checkObjIds){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"radio"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 440,
					title:"选择人员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";
							
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										selectIds = v[0];
										selectNames = v[1];
									}
								}
								
								$("#bizOrgIds").val(selectIds);
								$("#bizOrgNames").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}


/**
 * 负责人选择
 */
function chooseManager(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"radio"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 340,
					title:"选择负责人",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = "";
								var selectNames = "";
								var result = returnValue.split(",");
								var resultLength = result.length;
								if(result[resultLength-1].length>1){
									var v = result[resultLength-1].split(":");
									selectIds += v[0];
									selectNames += v[1];
								}
								$("#managerEmpId").val(selectIds);
								$("#managerEmpName").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}


function brandList(){
	$("#brandSub").css({"display":""});
	$("#dimensionSub").css({"display":"none"});
}


$(function(){
	$("#businessOrgForm").validationEngine();
});

</script>
<div class="content" style="margin-top: 15px; overflow: hidden">

	<div class="box order_table" style="line-height: normal;">
		<form action="" id="businessOrgForm" name="businessOrgForm">
			<s:hidden id="id" name="entityForm.id" value="%{entity.id}" theme="simple" />
			<table>
				<tr>
					<td align="right">上级业务组织:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="parentId" name="parentIdStr" value="${parentIdStr}" /> <input type="hidden" id="parentTreeType" name="parentTreeType" value="${parentTreeType}" /> <input type="text" id="parentTreeName" name="parentTreeName" class="validate[required] text-input" readonly="readonly" value="${parentTreeName}" /> <input
						class="btn" type="button" value="选择" onclick="chooseParentBusinessOrg();" /></td>
				</tr>
				<tr height="40">
					<td align="right">名称:&nbsp;</td>
					<td><input type="text" id="businessOrgName" name="entityForm.businessOrgName" value="${entity.businessOrgName}" class="validate[required] text-input" /></td>
					<td align="right">&nbsp;</td>
					<td align="right">&nbsp;</td>
					<%-- <td align="right">编码:&nbsp;</td>
				<td>&nbsp;${entity.businessOrgCode}</td> --%>
				</tr>

				<tr height="40">
					<td align="right">是否虚拟节点:&nbsp;</td>
					<td colspan="3"><s:radio list="#{'1':'是','0':'否'}" name="entityForm.isVirtualUnitNode" value="%{entity.isVirtualUnitNode}" theme="simple"></s:radio></td>
				</tr>

				<tr height="40">
					<td align="right">负责人:&nbsp;</td>
					<td colspan="3"><input type="hidden" id="managerEmpId" name="entityForm.manager.id" value="${entity.manager.id}" /> <input type="text" id="managerEmpName" name="managerEmpName" readonly="readonly" value="${entity.manager.name}" /> <input class="btn" type="button" value="选择" onclick="chooseManager();" /></td>
				</tr>


				<%-- 
			<tr height="40">
				<td align="right">组织类型:&nbsp;</td>
				<td colspan="3">
					<s:radio id="bizOrgType" list="#{\"O\":\"部门\",\"R\":\"角色\",\"E\":\"人员\"}" name="bizOrgType" value="%{entity.bizOrgType}" onchange="changeBizOrgType(this.value);" theme="simple"></s:radio>
				</td>
			</tr>
			<tr>
				<td align="right">业务组织:&nbsp;</td>
				<td colspan="3">
					<input type="hidden" id="bizOrgIds" name="bizOrgIds" value="${bizOrgIds}" />
					<input type="hidden" id="parentTreeType" name="parentTreeType" value="${parentTreeType}"/>
					<input type="text" id="bizOrgNames" name="bizOrgNames" readonly="readonly" value="${bizOrgNames}"/>
					<input class="btn" type="button" value="选择" onclick="addBizOrgObject();"/>
					<input class="btn" type="button" value="清空" onclick="clearBizOrgType();"/>
				</td>
			</tr>
			 --%>

				<!-- <tr height="30">
				<td align="right">新增品牌:</td>
				<td><a id="addCategoryBrand" href="#" onclick="addCategoryBrand();" style="color: #000000; text-decoration: none;">添加</a></td>
				<td align="right">品牌:</td>
				<td colspan="3"><span id="addCategoryBrandNames"></span></td>
			</tr> -->
			</table>
			<!-- <hr/> -->
			<!-- <input type="hidden" id="addCategoryBrandIds" name="addCategoryBrandIds" value=""/>
		<input type="hidden" id="deleteCategoryBrandIds" name="deleteCategoryBrandIds" value=""/> -->

		</form>
		<%-- 
		<div class="table" style="margin: 5px;">
			<p id="table_top" style="border: 1px solid #D6D6D6;color: #CDCDCD;padding: 5px;">
				<img src="${vix}/common/img/icon_22.gif"/><a id="bradn" href="#" onclick="brandList()" style="color: #000000; text-decoration: none;">品牌列表</a>
			</p>
			<table id="brandSub" class="list">
				<tr>
					<th width="10%">编号</th>
					<th width="80%">名称</th>
					<th width="10%">操作</th>
				</tr>
				<s:iterator value="category.brands" var="entity" status="s">
					<tr>
						<td>${s.count}</td>
						<td>${entity.name}</td>
						<td align="center">
							<a href="#" onclick="deleteCategoryBrand(this,${entity.id});" title="删除">
								<img src="${vix}/common/img/icon_12.png"/>
							</a>
						</td>
					</tr>
				</s:iterator>
			</table>
			<table id="dimensionSub" class="list" style="display:none;">
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
							<a href="#" onclick="deleteCategoryDimension(this,${entity.id});" title="删除">
								<img src="${vix}/common/img/icon_12.png"/>
							</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div> --%>
	</div>
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