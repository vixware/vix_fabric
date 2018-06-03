<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function chooseObjectExpand(){
	$.ajax({
		  url:'${vix}/system/objectExpandAction!goChooseObjectExpand.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 780,
					height : 500,
					title:"选择对象类型",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#objectExpandId").val(result[0]);
								$("#objectExpandName").html(result[1]);
							}else{
								$("#objectExpandId").val("");
								$("#objectExpandName").html("");
								asyncbox.success("<s:text name='pleaseChooseCategory'/>","<s:text name='vix_message'/>");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
/** 更新规格信息  */
function saveOrUpdateInner(){
	if($('#specificationForm').validationEngine('validate')){
		$.post('${vix}/system/specificationAction!saveOrUpdateInner.action',
			{'specification.id':$("#id").val(),
			  'specification.code':$("#code").val(),
			  'specification.name':$("#name").val(),
			  'specification.orderBy':$("#orderBy").val(),
			  'specification.memo':$("#memo").val(),
			  'specification.objectExpand.id':$("#objectExpandId").val()
			},
			function(result){
				$("#id").val(Number(result));
				$("#specificationSave").attr('style','display:none');
				$("#specificationItem").attr('style','display:');
			}
		);
	}
};
/** 添加规格明细 */
function saveOrUpdateDetail(id){
	$.ajax({
		  url:'${vix}/system/specificationDetailAction!goSaveOrUpdate.action?id='+id,
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 540,
					height : 180,
					title:"规格明细",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#specificationDetailForm').validationEngine('validate')){
								$.post('${vix}/system/specificationDetailAction!saveOrUpdate.action',
									{'specificationDetail.id':$("#detailId").val(),
									 'specificationDetail.specification.id':$("#id").val(),
									 'specificationDetail.name':$("#detailName").val(),
									 'specificationDetail.code':$("#detailCode").val(),
									 'specificationDetail.memo':$("#detailMemo").val()
									},
									function(result){
										asyncbox.success(result,"<s:text name='vix_message'/>",function(action){
											pager("start","${vix}/system/specificationDetailAction!goSingleList.action?id="+$("#id").val(),'specificationDetail');
										});
									}
								 );
							}else{
								return false;
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
};
/** 删除规格明细 */
function deleteSpecificationDetail(row,id){
	asyncbox.confirm('确定要删除该规格明细么?','<s:text name='vix_message'/>',function(action){
		if(action == 'ok'){
			$(row).parent().parent().remove();
			$.ajax({
				  url:'${vix}/system/specificationDetailAction!deleteById.action?id='+id,
				  cache: false,
				  success: function(html){
					  asyncbox.success(html,"<s:text name='vix_message'/>");
				  }
			});
		}
	});
}
pager("start","${vix}/system/specificationDetailAction!goSingleList.action?id="+$("#id").val(),'specificationDetail');
$("#specificationForm").validationEngine();
if($("#id").val() != ""){
	$("#specificationSave").attr('style','display:none');
	$("#specificationItem").attr('style','display:');
}
</script>
<div class="content" style="margin-top: 5px; overflow: hidden">
	<form id="specificationForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<s:hidden id="id" name="specification.id" value="%{specification.id}" theme="simple" />
				<s:hidden id="objectExpandId" name="objectExpandId" value="%{specification.objectExpand.id}" theme="simple" />
				<input id="code" name="specification.code" value="${specification.code}" type="hidden" />
				<tr height="30">
					<td align="right">对象类型:&nbsp;</td>
					<td><span id="objectExpandName"><s:property value="specification.objectExpand.name" /></span> <a href="#" class="abtn" onclick="chooseObjectExpand();"><span>选择</span> </a></td>
					<td align="right">名称:&nbsp;</td>
					<td><input id="name" name="specification.name" value="${specification.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">顺序:&nbsp;</td>
					<td><input id="orderBy" name="specification.orderBy" value="${specification.orderBy}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">备注:&nbsp;</td>
					<td><input id="memo" name="specification.memo" value="${specification.memo}" /></td>
				</tr>
				<tr id="specificationSave" height="30">
					<td colspan="4" align="center"><a href="###" class="abtn" onclick="saveOrUpdateInner();"><span>保存</span></a></td>
				</tr>
			</table>
			<div class="table" style="margin: 5px 0;">
				<div class="pagelist drop" style="height: 30px;">
					<span style="color: black;">明细列表</span> <a id="specificationItem" class="abtn" style="display: none;" href="#" onclick="saveOrUpdateDetail();"><span>添加</span></a>
				</div>
				<div id="specificationDetail"></div>
			</div>
		</div>
	</form>
</div>