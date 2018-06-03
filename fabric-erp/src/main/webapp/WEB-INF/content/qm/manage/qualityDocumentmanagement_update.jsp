<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$("#treeSingleGridForm").validationEngine();
function chooseParentCategory(){
	$.ajax({
		  url:'${vix}/qm/qualityDocumentmanagementAction!goChooseCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择父分类",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								if(result[0] == $("#id").val()){
									asyncbox.success("不允许引用自身为父分类!","提示信息");
								}else{
									$("#parentId").val(result[0]);
									$("#categoryName").html(result[1]);
								}
							}else{
								$("#parentId").val("");
								$("#categoryName").html("");
								asyncbox.success("请选择分类信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

/** 选择部门*/
function chooseParentOrganization(){
	 $.ajax({
		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
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
								var pubIdTmp = $("#pubIds").val();
								
								pubIdTmp = pubIdTmp + ",";
								
								/* if(resObj.length == 0 ){
									return;
								} */
								//debugger;
								for(var i=0;i<resObj.length;i++){
									//alert(resObj[i].treeId+"--"+resObj[i].name);
									if(resObj[i].treeType!="O"){
										asyncbox.alert("只能选择部门信息！","提示");
										return;
									} 
									if(!pubIdTmp.contains(resObj[i].treeId+",")){
										selectIds += "," + resObj[i].treeId;
										selectNames += "," + resObj[i].name;
									}
								}
								selectIds = $("#pubIds").val()+selectIds;
								selectNames = $("#despatchDepartment").val()+selectNames;
								
								$("#pubIds").val(selectIds);
								selectNames = selectNames.substring(1,selectNames.length);
								$("#despatchDepartment").val(selectNames);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<div class="tcbox">
	<form id="brandForm">
		<input type="hidden" id="id" name="id" value="${qualityDocumentmanagement.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">父分类:&nbsp;</td>
					<td><input type="hidden" id="parentId" name="parentId" value="${qualityDocumentmanagement.parentCategory.id}" /> <span id="categoryName"><s:property value="qualityDocumentmanagement.parentCategory.documentName" /></span> <span class="btn"><a href="#" onclick="chooseParentCategory();">选择</a></span></td>
					<td align="right">文档名称:&nbsp;</td>
					<td><input type="text" size="28" id="documentName" name="qualityDocumentmanagement.documentName" value="${qualityDocumentmanagement.documentName}" /></td>
				</tr>
				<tr height="30">
					<td align="right">是否禁用:&nbsp;</td>
					<td><s:if test="category.status == 0">
							<input type="radio" id="status1" name="status" value="1" />是
							<input type="radio" id="status2" name="status" value="0" checked="checked" />否
						</s:if> <s:elseif test="category.status == 1">
							<input type="radio" id="status1" name="status" value="1" checked="checked" />是
							<input type="radio" id="status2" name="status" value="0" />否
						</s:elseif> <s:else>
							<input type="radio" id="status1" name="status" value="1" />是
							<input type="radio" id="status2" name="status" value="0" checked="checked" />否
						</s:else></td>
					<td align="right">备注:&nbsp;</td>
					<td><s:textfield id="memo" size="28" name="qualityDocumentmanagement.memo" value="%{qualityDocumentmanagement.memo}" theme="simple" /></td>
				</tr>
				<tr height="30">
					<td align="right">文档序号：</td>
					<td><input id="serialNumber" name="serialNumber" value="${qualityDocumentmanagement.serialNumber}" type="text" size="28" class="validate[required] text-input" /></td>
					<td align="right">文档编码：</td>
					<td><input id="documentCode" name="documentCode" value="${qualityDocumentmanagement.documentCode}" type="text" size="28" class="validate[required] text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">文档作者：</td>
					<td><input id="documentAuthor" name="documentAuthor" value="${qualityDocumentmanagement.documentAuthor}" type="text" size="28" class="validate[required] text-input" /></td>
					<td align="right">文档类别：</td>
					<td><input id="documentCategory" name="documentCategory" value="${qualityDocumentmanagement.documentCategory}" type="text" size="28" class="validate[required] text-input" /></td>
				</tr>
				<tr height="30">
					<td align="right">批准日期：</td>
					<td><input id="approvalDate" data-text-tooltip="时间格式 yyyy-MM-dd" name="approvalDate" onclick="showTime('approvalDate','yyyy-MM-dd')" value="<fmt:formatDate value='${qualityDocumentmanagement.approvalDate}' type='both' pattern='yyyy-MM-dd' />" type="text" size="28" /> <img onclick="showTime('approvalDate','yyyy-MM-dd')"
						src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">发布日期：</td>
					<td><input id="issueDate" data-text-tooltip="时间格式 yyyy-MM-dd" name="issueDate" onclick="showTime('issueDate','yyyy-MM-dd')" value="<fmt:formatDate value='${qualityDocumentmanagement.issueDate}' type='both' pattern='yyyy-MM-dd' />" type="text" size="28" /> <img onclick="showTime('issueDate','yyyy-MM-dd')"
						src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="30">
					<td align="right">发送部门：</td>
					<td><input type="hidden" id="pubIds" name="pubIds" value="${pubIds}" /> <input type="hidden" id="treeType" name="treeType" value="${treeType}" /> <input type="text" id="despatchDepartment" name="despatchDepartment" readonly="readonly" value="${qualityDocumentmanagement.despatchDepartment}" /> <input class="btn" type="button" value="选择"
						onclick="chooseParentOrganization();" /></td>
				</tr>
			</table>
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