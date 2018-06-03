<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/toolbar.css" rel="stylesheet" type="text/css" />
<link href="css/tree.css" rel="stylesheet" type="text/css" />
<link href="css/base/jquery.ui.all.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="css/easyui/themes/icon.css">

<link href="css/skin_01.css" type="text/css" id="skin" rel="stylesheet">
<link href="css/font_02.css" type="text/css" id="font" rel="stylesheet">
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.jBreadCrumb.1.1.js" type="text/javascript"></script>
<script src="js/jquery.jcarousel.min.js" type="text/javascript"></script>
<script src="js/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="js/bar.js" type="text/javascript"></script>
<script src="js/all.js" type="text/javascript"></script>
<script src="js/jquery.tree.js" type="text/javascript"></script>
<script src="js/date.js" type="text/javascript"></script>
<script src="js/jquery.ui.core.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.widget.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.mouse.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.resizable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.draggable.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.dialog.min.js" type="text/javascript"></script>
<script src="js/jquery.ui.position.min.js" type="text/javascript"></script>
<script src="js/page_02.js" type="text/javascript"></script>
<script src="js/jquery.easyui.min.js" type="text/javascript"></script>
<script src="js/datagrid.js" type="text/javascript"></script>
<script type="text/javascript">
	var name = "";
	function loadName (){
		name = $ ('#nameS').val ();
		name = Url.encode (name);
		name = Url.encode (name);
	}
	$ ('#numBtn').click (function (){
		$ ('#numBtn').parent ("li").toggleClass ("current");
		$ ('#number').stop ().animate ({
		height : 'toggle' ,
		opacity : 'toggle'
		} , 500 , function (){
			$ ('#number').css ('overflow' , 'visible');
		});
		return false;
	});
	$ (function (){
		if ($ ('#numBox').length)
			$ ('#numBox').listmenu ({
			menuWidth : '100%' ,
			cols : {
			count : 6 ,
			gutter : 0
			}
			});
	});
	$ ('input.btn[type="button"],input.btn[type="submit"]').hover (function (){
		$ (this).addClass ("btnhover");
	} , function (){
		$ (this).removeClass ("btnhover");
	});
	function saveOrUpdate (id){
		$.ajax ({
		url : '${vix}/mm/planGenerateAction!goSaveOrUpdate.action?id=' + id ,
		cache : false ,
		success : function (html){
			$ ("#mainContent").html (html);
		}
		});
	};
	function deleteById (id){
		$.ajax ({
		url : '${vix}/mm/planGenerateAction!deleteById.action?id=' + id ,
		cache : false ,
		success : function (html){
			asyncbox.success (html , "<s:text name='cmn_message'/>" , function (action){
				var treeNode = zTree.getNodeByTId ($ ("#selectIdTreeId").val ());
				if (null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes (treeNode , "refresh");
				pager ("start" , "${vix}/mm/planGenerateAction!goListContent.action?name=" + name , 'category');
			});
		}
		});
	}
	function searchForRightContent (){
		loadName ();
		pager ("start" , "${vix}/mm/planGenerateAction!goListContent.action?name=" + name , 'category');
	}
	loadName ();
	//载入分页列表数据
	pager ("start" , "${vix}/mm/planGenerateAction!goListContent.action?name=" + name , 'category');
	function loadRoot (){
		$ ('#name').val ("");
		$ ('#selectId').val ("");
		pager ("start" , "${vix}/mm/planGenerateAction!goListContent.action?name=" , 'category');
	}
	//排序 
	function orderBy (orderField){
		loadName ();
		var orderBy = $ ("#categoryOrderBy").val ();
		if (orderBy == 'desc'){
			orderBy = "asc";
		}else{
			orderBy = 'desc';
		}
		pager ("start" , "${vix}/mm/planGenerateAction!goListContent.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name + "&parentId=" + $ ("#selectId")
				.val () , 'category');
	}
	function categoryPager (tag , entity){
		loadName ();
		if (entity == 'category'){
			pager (tag , "${vix}/mm/planGenerateAction!goListContent.action?name=" + name + '&parentId=' + $ ('#selectId').val () , entity);
		}
	}
	bindSearch ();
	bindSwitch ();
	function categoryTab (num , befor , id , e , entity){
		var el = e.target ? e.target.parentNode : e.srcElement.parentNode;
		var pa = el.parentNode.getElementsByTagName ("li");
		for ( var i = 0;i < pa.length;i ++ ){
			pa [ i ].className = "";
		}
		el.className = "current";
		for (i = 1;i <= num;i ++ ){
			try{
				if (i == befor){
					document.getElementById (id + i).style.display = "block";
				}else{
					document.getElementById (id + i).style.display = "none";
				}
			}catch (e){
			}
		}
		if (entity != undefined){
			categoryPager ('start' , entity);
		}
	}
	$ (".drop>ul>li").bind ('mouseover' , function (){
		$ (this).children ('ul').delay (400).slideDown ('fast');
	}).bind ('mouseleave' , function (){
		$ (this).children ('ul').slideDown ('fast').stop (true , true);
		$ (this).children ('ul').slideUp ('fast');
	});
	//面包屑
	if ($ ('.sub_menu').length){
		$ ("#breadCrumb").jBreadCrumb ();
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/drp_channel.png" alt="" />生产管理</a></li>
				<li><a href="#">主生产计划</a></li>
				<li><a href="#">MPS计划管理</a></li>
				<li><a href="#">MPS计划生成</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a>
		</p>
	</div>
</div>

<div class="content">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#"><img src="img/icon_10.png" alt="" />索引</a></li>
			<li><a href="#"><img src="img/icon_10.png" alt="" />数据分类</a></li>
			<li class="fly"><a href="#"><img src="img/icon_10.png" alt="" />热点分类</a>
				<ul>
					<li><a href="#"><img src="img/icon_10.png" alt="" />信息</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />信息</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />信息</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />信息</a></li>
				</ul></li>
		</ul>
		<div>
			<label>内容<input id="nameS" name="" type="text" class="int" /></label> <label><input name="" type="button" class="btn" value="搜索" /><input name="" type="button" class="btn" value="重置" /> <strong id="search_advanced">高级搜索</strong>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="switch_btn" id="switch_box"></div>
			<div class="left_content" id="tree_00"></div>
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<table id="test"></table>
				<script type="text/javascript">
					$ ('#test').datagrid ({
					url : '${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=${wimStockrecords.id}' ,
					width : "100%" ,
					height : 450 ,
					title : "MPS计划" ,
					nowrap : true ,
					autoRowHeight : false ,
					striped : true ,
					collapsible : true ,
					sortName : 'id' ,
					sortOrder : 'desc' ,
					remoteSort : true ,
					pagination : true ,
					rownumbers : true ,
					showFooter : true ,
					idField : 'id' ,
					frozenColumns : [
						[
						{
						field : 'id' ,
						title : '编号' ,
						width : 60 ,
						hidden : true ,
						align : 'center'
						} , {
						field : 'itemname' ,
						title : '${vv:varView("vix_mdm_item")}名称' ,
						width : 100 ,
						align : 'center'
						} , {
						field : 'itemcode' ,
						title : '${vv:varView("vix_mdm_item")}编码' ,
						width : 100 ,
						align : 'center'
						}
						]
					] ,
					columns : [
						[
						{
						field : 'specification' ,
						title : '规格型号' ,
						width : 100 ,
						align : 'center'
						} , {
						field : 'unit' ,
						title : '主计量单位' ,
						width : 100 ,
						align : 'center'
						} , {
						field : 'unitcost' ,
						title : '一月' ,
						width : 50 ,
						align : 'right' ,
						editor : 'numberbox' ,
						required : 'true'
						} , {
						field : 'quantity' ,
						title : '二月' ,
						width : 50 ,
						align : 'right' ,
						editor : 'numberbox' ,
						required : 'true'
						} , {
						field : 'price' ,
						title : '三月' ,
						width : 50 ,
						align : 'right' ,
						editor : 'numberbox' ,
						required : 'true'
						} , {
						field : 'suppliercode' ,
						title : '四月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'producedate' ,
						title : '五月' ,
						width : 50 ,
						editor : 'datebox' ,
						align : 'center' ,
						formatter : function (val , rec){
							if (val != null && val != ""){
								var d = new Date (val);
								return format (d);
							}else
								return "";
						}
						} , {
						field : 'massdate' ,
						title : '六月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'poCode' ,
						title : '七月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'poCode' ,
						title : '八月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'poCode' ,
						title : '九月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'poCode' ,
						title : '十月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'poCode' ,
						title : '十一月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'poCode' ,
						title : '十二月' ,
						width : 50 ,
						align : 'center'
						}
						]
					] ,
					toolbar : [
					{
					id : 'da2Btnadd' ,
					text : '添加' ,
					iconCls : 'icon-add' ,
					handler : function (){
						$ ('#btnsave').linkbutton ('enable');
						saveDeliveryAddress ('${vix}/inventory/inboundWarehouseAction!goAddSaleOrderItem.action?=${wimStockrecords.id}');
					}
					} , '-' , {
					id : 'btnedit' ,
					text : '修改' ,
					iconCls : 'icon-edit' ,
					handler : function (){
						var row = $ ('#test').datagrid ("getSelected"); //获取你选择的所有行
						if (row){
							saveDeliveryAddress ('${vix}/inventory/inboundWarehouseAction!getOrderItemJson.action?id=' + row.id);
						}
					}
					} , '-' , {
					text : '删除' ,
					iconCls : 'icon-remove' ,
					handler : function (){
						var rows = $ ('#test').datagrid ("getSelections"); //获取你选择的所有行	
						//循环所选的行
						for ( var i = 0;i < rows.length;i ++ ){
							var index = $ ('#test').datagrid ('getRowIndex' , rows [ i ]);//获取某行的行号
							$ ('#test').datagrid ('deleteRow' , index); //通过行号移除该行
							$.ajax ({
							url : '${vix}/inventory/inboundWarehouseAction!deleteWimStockrecordlinesById.action?id=' + rows [ i ].id ,
							cache : false
							});
						}
					}
					}
					]
					});
					function saveDeliveryAddress (url){
						$.ajax ({
						url : url ,
						cache : false ,
						success : function (html){
							asyncbox.open ({
							title : '${vv:varView("vix_mdm_item")}明细' ,
							modal : true ,
							width : 724 ,
							height : 400 ,
							html : html ,
							callback : function (action , returnValue){
								if (action == 'ok'){
									if ($ ('#orderItemForm').validationEngine ('validate')){
										$.post ('${vix}/inventory/inboundWarehouseAction!saveOrUpdateWimStockRecordLines.action' , {
										'id' : $ ("#id").val () ,
										'wimStockrecordlines.id' : $ ("#oiId").val () ,
										'wimStockrecordlines.itemcode' : $ ("#itemcode").val () ,
										'wimStockrecordlines.itemname' : $ ("#itemname").val () ,
										'wimStockrecordlines.specification' : $ ("#specification").val () ,
										'wimStockrecordlines.unitcost' : $ ("#unitcost").val () ,
										'wimStockrecordlines.quantity' : $ ("#quantity").val () ,
										'wimStockrecordlines.suppliercode' : $ ("#suppliercode").val () ,
										'wimStockrecordlines.pocode' : $ ("#pocode").val () ,
										'wimStockrecordlines.unit' : $ ("#unit").val () ,
										'wimStockrecordlines.producedate' : $ ("#producedate").val () ,
										'wimStockrecordlines.massdate' : $ ("#massdate").val () ,
										'wimStockrecordlines.price' : $ ("#price").val ()
										} , function (result){
											showMessage (result);
											setTimeout ("" , 1000);
											$ ('#dlAddress2').datagrid ("reload");
										});
									}else{
										return false;
									}
								}
							} ,
							btnsbar : $.btn.OKCANCEL
							});
						}
						});
					}
				</script>

			</div>
		</div>
		<!-- right -->
	</div>
	<script src="${vix}/common/js/loadtree.js" type="text/javascript"></script>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>