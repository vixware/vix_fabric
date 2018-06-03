<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/LodopFuncs.js" type="text/javascript"></script>
<script type="text/javascript">
_pad_page_refresh_main_content = true;

$(function(){
	//载入tab数据
	_load_tab_page_content();

	loadMenuContent('${vix}/purchase/purchaseMainAction!goMenuContent.action');
});

function saveOrUpdate(id,parentId){
	var title = '新增采购申请';
	var url = '${vix}/purchase/purchaseApplyAction!goSaveOrUpdate.action';
	var data = {};
	if(id != null){
		title = '编辑采购申请';
		data.id=id;
	}
	if(parentId!=null){
		data.parentId=parentId;
	}
	
	_pad_page_view_push(url,data,function(containerId){
		if(containerId.substring(0,1)!='#')
			containerId = '#' + containerId;
		
		var saveNewBtn = $(containerId).find('a.f_btn.edit_savenew');
		if(saveNewBtn.length>0){
			saveNewBtn.show();
			saveNewBtn.click(function(){
				_pad_callback_after_save = saveOrUpdate;
				saveOrUpdateApply();
			});
		}
	});
}

function goShowPurchaseApply(id) {
	$.ajax({
	url : '${vix}/purchase/purchaseApplyAction!goShowPurchaseApply.action?id=' + id,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}
function goPrintPurchaseApply(id) {
	$.ajax({
	url : '${vix}/purchase/purchaseApplyAction!goPrintPurchaseApply.action?id=' + id,
	cache : false,
	success : function(html) {
		LODOP = getLodop();
		LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
		LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
		LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);// 打印后自动关闭预览窗口
		LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
		// LODOP.SET_PRINT_PAGESIZE(3,"240mm","45mm","");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
		LODOP.SET_PREVIEW_WINDOW(1, 0, 0, 1024, 550, ""); // 2上下打印工具条都显示
		/* LODOP.PRINT(); */
		LODOP.PREVIEW();
	}
	});
};
function goSearch() {
	$.ajax({
	url : '${vix}/purchase/purchaseApplyAction!goSearch.action',
	cache : false,
	success : function(html) {
		asyncbox.open({
		modal : true,
		width : 650,
		height : 300,
		title : "查询条件",
		html : html,
		callback : function(action) {
			if (action == 'ok') {
				pager("start","${vix}/purchase/purchaseApplyAction!goSingleList.action?code="+$('#code').val()+"&name="+$('#name').val()+"&purchasePerson="+$('#purchasePerson').val()+"&createTime="+$('#createTime').val()+"&status="+$('#status').val(),'srm_supplier_grid');
			}
		},
		btnsbar : $.btn.OKCANCEL
		});
	}
	});
};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#" class="nav_print_btn"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#" class="nav_help_btn"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module"></div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<ul>
			<li><a href="#" id="numBtn" class="num_btn"><img src="img/icon_10.png" alt="" /> <s:text name="cmn_index" /></a></li>
		</ul>
		<div search_page_id="tab_home" class="grid_search search_simple">
			<label><input type="text" name="code" class="int more" placeholder="编码"></label> <input type="button" class="btn search" value="<s:text name='cmn_search'/>" /><label> <input type="button" value="高级搜索" class="btn" onclick="goSearch();" /></label>
		</div>
		<%-- <div class="grid_search search_advanced">
			<label> 编码 <input name="purchaseName" type="text" class="int" />
			</label> <label> 主题 <input name="purchasePerson" type="text" class="int" />
			</label> <label> <input type="button" class="btn search" value="<s:text name='cmn_search'/>" /> <input type="button" class="btn reset" value="<s:text name='cmn_reset'/>" />
			</label>
		</div> --%>
	</div>
	<div id="number" class="quick_index">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<s:iterator value="indexEntityList" var="index">
				<li><a href="#" onclick="saveOrUpdate(${index.id});"><span style="display: none;">${index.chineseCharacter}</span>${index.name}</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->

	<div class="box">
		<div id="right">
			<div id="tab_content" class="tabbable addable_tab">
				<ul id="myTab" class="right_menu nav nav-tabs">
					<li pageId="tab_home" class="current"><a href="#tab_home" page="${vix}/purchase/purchaseApplyAction!goSingleList.action"> <img src="img/mail.png" alt="" /> 采购申请单
					</a></li>
				</ul>
				<div id="tab_content_page" class="right_content">
					<div id="tab_home" class="table"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>
<script type="text/javascript"> 
</script>