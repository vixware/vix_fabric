<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#saleBookBorrowForm").validationEngine();
var itemCategorySetting = {
		view: {
			dblClickExpand: false
		},
		async: {
			enable: true,
			url:"${vix}/oa/officeSuppliesAction!findBookCategoryTree.action",
			autoParam:["id", "name=n", "level=lv"]
		},
		callback: {
			onClick: onClick
		}
	};
	function onClick(e, treeId, treeNode) {
		$("#officeCategoryId").attr("value", treeNode.id);
		$("#officeCategoryName").attr("value", treeNode.name);
		hideMenu();
		searchForContent();
	}

var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function chooseBook(id,model,officeSuppliesName,prickle,supplier,lowestVigilance,maximumVigilance,currentInventory){
	$("#officeSuppliesId").val(id);
	$("#model").val(model);
	$("#officeName").val(officeSuppliesName);
	$("#prickle").val(prickle);
	$("#supplier").val(supplier);
	$("#lowestVigilance").val(lowestVigilance);
	$("#maximumVigilance").val(maximumVigilance);
	$("#numberOfRecipients").val(currentInventory);
}
loadName();
//载入分页列表数据
pager("start","${vix}/oa/officeSuppliesAction!goListContent.action?name="+name,'outBound');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#outBoundOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/officeSuppliesAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'outBound');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/officeSuppliesAction!goListContent.action?name="+name,'outBound');
}


function addBookBorrow(){
	if($('#saleBookBorrowForm').validationEngine('validate')){
		$.post('${vix}/oa/officeSuppliesRegisterAction!saveOrUpdateOSB.action',
			{'officeSuppliesBorrow.id':$("#officeSuppliesBorrowId").val(),
			 'officeSuppliesBorrow.officeSupplies.id':$("#officeSuppliesId").val(),
			 'officeSuppliesBorrow.officeSuppliesRegister.id':$("#id").val(),
			 'officeSuppliesBorrow.model':$("#model").val(),
			 'officeSuppliesBorrow.officeSuppliesCode':$("#officeSuppliesCode").val(),
			 'officeSuppliesBorrow.officeName':$("#officeName").val(),
			 'officeSuppliesBorrow.prickle':$("#prickle").val(),
			 'officeSuppliesBorrow.supplier':$("#supplier").val(),
			 'officeSuppliesBorrow.lowestVigilance':$("#lowestVigilance").val(),
			 'officeSuppliesBorrow.lowestVigilance':$("#lowestVigilance").val(),
			 'officeSuppliesBorrow.numberOfRecipients':$("#numberOfRecipients").val(),
			 'officeSuppliesBorrow.borrowNumber':$("#borrowNumber").val()
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				clearBookBorrowContent();
			}
		);
	}
}
function clearBookBorrowContent(){
	$("#officeSuppliesId").val("");
	$("#model").val("");
	$("#officeSuppliesName").val("");
	$("#prickle").val("");
	$("#supplier").val("");
	$("#lowestVigilance").val("");
	$("#maximumVigilance").val("");
	$("#currentInventory").val("");
}
function chooseSpecification(code){
	var spec = "";
	$("input[type=radio][id^='"+code+"_']:checked").each(function(){
		spec += $(this).val();
	});
	$("#specification").val(spec);
}
function searchForContent(){
	loadName();
	var parentId = $("#officeCategoryId").val();
	pager("start","${vix}/oa/officeSuppliesAction!goListContent.action?officeSuppliesName="+name+"&parentId="+parentId,'outBound');
}
function reset(){
	$("#officeCategoryId").val("");
	$("#officeCategoryName").val("");
	$("#nameS").val("");
}
function showMenu() {
	$("#itemMenuContent").css({left:70 + "px", top:76 + "px"}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#itemMenuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "itemMenuContent" || $(event.target).parents("#itemMenuContent").length>0)) {
		hideMenu();
	}
}
$.fn.zTree.init($("#itemCategoryTree"), itemCategorySetting);
$('input.btn[type="button"]').hover(function(){$(this).addClass("btnhover");},function(){$(this).removeClass("btnhover");});
</script>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<!-- 页面左上角按钮部分 -->
		<div>
			<label> 分类 <input id="officeCategoryId" type="hidden" value="" /> <input id="officeCategoryName" type="text" readonly="readonly" onfocus="showMenu(); return false;" class="int" value="" />
			</label> <label>名称<input type="text" class="int" id="nameS" onchange="searchForContent();"></label> <label> <input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForContent();" /> <input type="button" value="<s:text name='cmn_reset'/>" class="btn" onclick="reset();" />
			</label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" style="height: 390px; width: 600px;">
			<div class="left_content">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="outBoundPager('start','outBound');"></a></span> <span><a class="previous" onclick="outBoundPager('previous','outBound');"></a></span> <em>(<b class="outBoundInfo"></b> <s:text name='cmn_to' /> <b class="outBoundTotalCount"></b>)
						</em> <span><a class="next" onclick="outBoundPager('next','outBound');"></a></span> <span><a class="end" onclick="outBoundPager('end','outBound');"></a></span>
					</div>
				</div>
				<div id="outBound" class="table"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="outBoundPager('start','outBound');"></a></span> <span><a class="previous" onclick="outBoundPager('previous','outBound');"></a></span> <em>(<b class="outBoundInfo"></b> <s:text name='cmn_to' /> <b class="outBoundTotalCount"></b>)
						</em> <span><a class="next" onclick="outBoundPager('next','outBound');"></a></span> <span><a class="end" onclick="outBoundPager('end','outBound');"></a></span>
					</div>
				</div>
			</div>
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_content" style="height: 390px;">
				<form id="saleBookBorrowForm">
					<div class="box order_table" style="line-height: normal;">
						<table class="table-padding020">
							<tr height="30">
								<th align="right">办公用品编码:&nbsp;</th>
								<td><input id="model" name="officeSuppliesBorrow.model" value="${officeSuppliesBorrow.model}" type="text" class="validate[required] text-input" /></td>
							</tr>
							<tr height="30">
								<th align="right">办公用品名称:&nbsp;</th>
								<td><input id="officeName" name="officeSuppliesBorrow.officeName" value="${officeSuppliesBorrow.officeName}" type="text" class="validate[required] text-input" /></td>
							</tr>
							<tr height="30">
								<th align="right">计量单位:&nbsp;</th>
								<td><input id="prickle" name="officeSuppliesBorrow.prickle" value="${officeSuppliesBorrow.prickle}" type="text" class="validate[required] text-input" /></td>
							</tr>
							<tr height="30">
								<th align="right">供应商:&nbsp;</th>
								<td><input id="supplier" name="officeSuppliesBorrow.supplier" value="${officeSuppliesBorrow.supplier}" type="text" /></td>
							</tr>
							<tr height="30">
								<th align="right">最低警戒库存:&nbsp;</th>
								<td><input id="lowestVigilance" name="officeSuppliesBorrow.lowestVigilance" value="${officeSuppliesBorrow.lowestVigilance}" type="text" /></td>
							</tr>
							<tr height="30">
								<th align="right">最高警戒库存:&nbsp;</th>
								<td><input id="maximumVigilance" name="officeSuppliesBorrow.maximumVigilance" value="${officeSuppliesBorrow.maximumVigilance}" type="text" /></td>
							</tr>
							<tr height="30">
								<th align="right">当前库存:&nbsp;</th>
								<td><input id="numberOfRecipients" name="officeSuppliesBorrow.numberOfRecipients" value="${officeSuppliesBorrow.numberOfRecipients}" type="text" /></td>
							</tr>
							<tr height="30">
								<th align="right">借用数量:&nbsp;</th>
								<td><s:hidden id="officeSuppliesId" name="officeSuppliesId" value="%{officeSuppliesBorrow.officeSupplies.id}" theme="simple" /> <input id="borrowNumber" name="officeSuppliesBorrow.borrowNumber" value="${officeSuppliesBorrow.borrowNumber}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
						</table>
						<input type="hidden" id="giftJson" value="" />
					</div>
					<div style="padding-left: 150px; padding-top: 15px;">
						<s:if test="OfficeSuppliesBorrow.id > 0">
							<span class="abtn" style="cursor: pointer;" onclick="addBookBorrow();"><span>修改</span></span>
						</s:if>
						<s:else>
							<span class="abtn" style="cursor: pointer;" onclick="addBookBorrow();"><span>确定</span></span>
						</s:else>
						<span class="abtn" style="cursor: pointer;" onclick="clearBookBorrowContent();"><span>清空</span></span>
					</div>
				</form>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
<div id="itemMenuContent" class="itemMenuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf;">
	<ul id="itemCategoryTree" class="ztree" style="margin-top: 0; width: 155px;"></ul>
</div>