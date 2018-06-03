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
			url:"${vix}/oa/bookEntryAction!findBookCategoryTree.action",
			autoParam:["id", "name=n", "level=lv"]
		},
		callback: {
			onClick: onClick
		}
	};
	function onClick(e, treeId, treeNode) {
		$("#bookCategoryId").attr("value", treeNode.id);
		$("#bookCategoryName").attr("value", treeNode.name);
		hideMenu();
		searchForContent();
	}

var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function chooseBook(id,bookCode,bookName,ISBN,bookType,author,press,address,amount){
	$("#bookEntryId").val(id);
	$("#code").val(bookCode);
	$("#bookName").val(bookName);
	$("#ISBN").val(ISBN);
	$("#bookType").val(bookType);
	$("#author").val(author);
	$("#press").val(press);
	$("#address").val(address);
	$("#amount").val(amount);
}
loadName();
//载入分页列表数据
pager("start","${vix}/oa/bookEntryAction!goListContent.action?name="+name,'bookEntry');
//排序 
function orderBy(orderField){
	loadName();
	var orderBy = $("#bookEntryOrderBy").val();
	if(orderBy == 'desc'){
		orderBy = "asc";
	}else{
		orderBy = 'desc';
	}
	pager("start","${vix}/oa/bookEntryAction!goListContent.action?orderField="+orderField+"&orderBy="+orderBy+"&name="+name+"&parentId="+$("#selectId").val(),'bookEntry');
}

function currentPager(tag){
	loadName();
	pager(tag,"${vix}/oa/bookEntryAction!goListContent.action?name="+name,'bookEntry');
}


function addBookBorrow(){
	if($('#saleBookBorrowForm').validationEngine('validate')){
		$.post('${vix}/oa/bookLibrarianAction!saveOrUpdateBookBorrow.action',
			{'bookBorrow.id':$("#bookBorrowId").val(),
			 'bookBorrow.bookEntry.id':$("#bookEntryId").val(),
			 'bookBorrow.bookRegister.id':$("#id").val(),
			 'bookBorrow.code':$("#code").val(),
			 'bookBorrow.bookName':$("#bookName").val(),
			 'bookBorrow.ISBN':$("#ISBN").val(),
			 'bookBorrow.bookType':$("#bookType").val(),
			 'bookBorrow.author':$("#author").val(),
			 'bookBorrow.press':$("#press").val(),
			 'bookBorrow.address':$("#address").val(),
			 'bookBorrow.amount':$("#amount").val(),
			 'bookBorrow.borrowNumber':$("#borrowNumber").val()
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
	$("#bookBorrowId").val("");
	$("#code").val("");
	$("#bookName").val("");
	$("#ISBN").val("");
	$("#bookType").val("");
	$("#author").val("");
	$("#press").val("");
	$("#address").val("");
	$("#amount").val("");
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
	var parentId = $("#bookCategoryId").val();
	pager("start","${vix}/oa/bookEntryAction!goListContent.action?bookName="+name+"&parentId="+parentId,'bookEntry');
}
function reset(){
	$("#bookCategoryId").val("");
	$("#bookCategoryName").val("");
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
			<label> 分类 <input id="bookCategoryId" type="hidden" value="" /> <input id="bookCategoryName" type="text" readonly="readonly" onfocus="showMenu(); return false;" class="int" value="" />
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
						<span><a class="start" onclick="bookEntryPager('start','bookEntry');"></a></span> <span><a class="previous" onclick="bookEntryPager('previous','bookEntry');"></a></span> <em>(<b class="bookEntryInfo"></b> <s:text name='cmn_to' /> <b class="bookEntryTotalCount"></b>)
						</em> <span><a class="next" onclick="bookEntryPager('next','bookEntry');"></a></span> <span><a class="end" onclick="bookEntryPager('end','bookEntry');"></a></span>
					</div>
				</div>
				<div id="bookEntry" class="table"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="bookEntryPager('start','bookEntry');"></a></span> <span><a class="previous" onclick="bookEntryPager('previous','bookEntry');"></a></span> <em>(<b class="bookEntryInfo"></b> <s:text name='cmn_to' /> <b class="bookEntryTotalCount"></b>)
						</em> <span><a class="next" onclick="bookEntryPager('next','bookEntry');"></a></span> <span><a class="end" onclick="bookEntryPager('end','bookEntry');"></a></span>
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
								<th align="right">图书编码:&nbsp;</th>
								<td><input id="code" name="bookBorrow.code" value="${bookBorrow.bookCode}" type="text" class="validate[required] text-input" /></td>
							</tr>
							<tr height="30">
								<th align="right">图书名称:&nbsp;</th>
								<td><input id="bookName" name="bookBorrow.bookName" value="${bookBorrow.bookName}" type="text" class="validate[required] text-input" /></td>
							</tr>
							<tr height="30">
								<th align="right">ISBN:&nbsp;</th>
								<td><input id="ISBN" name="bookBorrow.ISBN" value="${bookBorrow.ISBN}" type="text" class="validate[required] text-input" /></td>
							</tr>
							<tr height="30">
								<th align="right">类型:&nbsp;</th>
								<td><input id="bookType" name="bookBorrow.bookType" value="${bookBorrow.bookType}" type="text" /></td>
							</tr>
							<tr height="30">
								<th align="right">作者:&nbsp;</th>
								<td><input id="author" name="bookBorrow.author" value="${bookBorrow.author}" type="text" /></td>
							</tr>
							<tr height="30">
								<th align="right">出版社:&nbsp;</th>
								<td><input id="press" name="bookBorrow.press" value="${bookBorrow.press}" type="text" /></td>
							</tr>
							<tr height="30">
								<th align="right">存放地点:&nbsp;</th>
								<td><input id="address" name="bookBorrow.address" value="${bookBorrow.address}" type="text" /></td>
							</tr>
							<tr height="30">
								<th align="right">存放数量:&nbsp;</th>
								<td><input id="amount" name="bookBorrow.amount" value="${bookBorrow.amount}" type="text" /></td>
							</tr>
							<tr height="30">
								<th align="right">借用数量:&nbsp;</th>
								<td><s:hidden id="bookEntryId" name="bookEntryId" value="%{bookBorrow.bookEntry.id}" theme="simple" /> <input id="borrowNumber" name="bookBorrow.borrowNumber" value="${bookBorrow.borrowNumber}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
							</tr>
						</table>
						<input type="hidden" id="giftJson" value="" />
					</div>
					<div style="padding-left: 150px; padding-top: 15px;">
						<s:if test="BookBorrow.id > 0">
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