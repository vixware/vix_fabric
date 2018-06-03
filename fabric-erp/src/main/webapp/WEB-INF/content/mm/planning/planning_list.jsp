<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/core/css/css.css" rel="stylesheet">
<script src="${vix}/common/js/newservice.js" type="text/javascript"></script>
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
		url : '${vix}/mm/planningAction!goSaveOrUpdate.action?id=' + id ,
		cache : false ,
		success : function (html){
			$ ("#mainContent").html (html);
		}
		});
	};
	function deleteById (id){
		$.ajax ({
		url : '${vix}/mm/planningAction!deleteById.action?id=' + id ,
		cache : false ,
		success : function (html){
			asyncbox.success (html , "<s:text name='cmn_message'/>" , function (action){
				var treeNode = zTree.getNodeByTId ($ ("#selectIdTreeId").val ());
				if (null != treeNode){
					treeNode.isParent = true;
				}
				zTree.reAsyncChildNodes (treeNode , "refresh");
				pager ("start" , "${vix}/mm/planningAction!goListContent.action?name=" + name , 'category');
			});
		}
		});
	}
	function searchForRightContent (){
		loadName ();
		pager ("start" , "${vix}/mm/planningAction!goListContent.action?name=" + name , 'category');
	}
	loadName ();
	//载入分页列表数据
	pager ("start" , "${vix}/mm/planningAction!goListContent.action?name=" + name , 'category');
	function loadRoot (){
		$ ('#name').val ("");
		$ ('#selectId').val ("");
		pager ("start" , "${vix}/mm/planningAction!goListContent.action?name=" , 'category');
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
		pager ("start" , "${vix}/mm/planningAction!goListContent.action?orderField=" + orderField + "&orderBy=" + orderBy + "&name=" + name + "&parentId=" + $ ("#selectId")
				.val () , 'category');
	}
	function categoryPager (tag , entity){
		loadName ();
		if (entity == 'category'){
			pager (tag , "${vix}/mm/planningAction!goListContent.action?name=" + name + '&parentId=' + $ ('#selectId').val () , entity);
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
				<li><a href="#"><img src="img/mm_planning.png" alt="" />生产管理</a></li>
				<li><a href="#">生产规划</a></li>
				<li><a href="#">生产规划编制</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a> <a href="#"><span>审批</span></a> <a href="#"><span>下达</span></a>
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
		<div id="left" class="ui-resizable switch" style="height: 500px; width: 7px;">
			<div id="switch_box" class="switch_btn off"></div>
			<div class="left_content current" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				var zTree;
				var setting = {
				async : {
				enable : true ,
				url : "${vix}/mm/planningAction!findTreeToJson.action" ,
				autoParam : [
				"id" , "name=n" , "level=lv"
				]
				} ,
				callback : {
					onClick : onClick
				}
				};
				function onClick (event , treeId , treeNode , clickFlag){
					$ ("#selectId").val (treeNode.id);
					$ ("#selectIdTreeId").val (treeNode.tId);
					pager ("start" , "${vix}/mm/planningAction!goListContent.action?parentId=" + treeNode.id , "category");
				}
				zTree = $.fn.zTree.init ($ ("#tree_root") , setting);
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<!-- left -->
		<div id="right">
			<div class="right_content">
				<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
					<div style="padding: 8px;">
						<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/common/json_tests/data.json',onClickRow: onDlClickRow1">
							<thead>
								<tr>
									<th data-options="field:'code',width:80,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}编码</th>
									<th data-options="field:'name',width:80,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}名称</th>
									<th data-options="field:'col1',width:80,align:'center',editor:'text'">规格型号</th>
									<th data-options="field:'col4',width:80,align:'center',editor:'text'">主计量单位</th>
									<th data-options="field:'col5',width:80,align:'center',editor:'text'">一月</th>
									<th data-options="field:'col5',width:80,align:'center',editor:'text'">二月</th>
									<th data-options="field:'col5',width:80,align:'center',editor:'text'">三月</th>
									<th data-options="field:'col5',width:80,align:'center',editor:'text'">四月</th>
									<th data-options="field:'col5',width:80,align:'center',editor:'text'">五月</th>
									<th data-options="field:'col5',width:80,align:'center',editor:'text'">六月</th>
									<th data-options="field:'col5',width:80,align:'center',editor:'text'">七月</th>
									<th data-options="field:'col5',width:80,align:'center',editor:'text'">八月</th>
									<th data-options="field:'col5',width:80,align:'center',editor:'text'">九月</th>
									<th data-options="field:'col5',width:80,align:'center',editor:'text'">十月</th>
									<th data-options="field:'col5',width:80,align:'center',editor:'text'">十一月</th>
									<th data-options="field:'col5',width:80,align:'center',editor:'text'">十二月</th>
								</tr>
							</thead>
						</table>
						<script type="text/javascript">
							$('#dlLineItem').datagrid();
							var editIndexDlLineItem = undefined;
							function endDlEditing1(){
								if (editIndexDlLineItem == undefined){return true;}
								if ($('#dlLineItem').datagrid('validateRow', editIndexDlLineItem)){
									$('#dlLineItem').datagrid('endEdit', editIndexDlLineItem);
									editIndexDlLineItem = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow1(index){
								if (editIndexDlLineItem != index){
									if (endDlEditing1()){
										$('#dlLineItem').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlLineItem = index;
									} else {
										$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem);
									}
								}
							}
							function appendDlLineItem(){
								if (endDlEditing1()){
									$('#dlLineItem').datagrid('appendRow',{status:'P'});
									editIndexDlLineItem = $('#dlLineItem').datagrid('getRows').length-1;
									$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem).datagrid('beginEdit', editIndexDlLineItem);
								}
							}
							function removeDlLineItem(){
								if (editIndexDlLineItem == undefined){return;}
								$('#dlLineItem').datagrid('cancelEdit', editIndexDlLineItem)
										.datagrid('deleteRow', editIndexDlLineItem);
								editIndexDlLineItem = undefined;
							}
							function saveDlLineItem(){
								if (endDlEditing1()){
									$('#dlLineItem').datagrid('acceptChanges');
								}
							}
					</script>
					</div>
				</div>
				<!-- <script type="text/javascript">
					$ ('#test').datagrid ({
					url : '${vix}/common/json_tests/data.json' ,
					width : 1400 ,
					height : 500 ,
					title : "生产规划" ,
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
					fitColumns : false ,
					idField : 'id' ,
					frozenColumns : [
						[{
						field : 'code' ,
						title : '${vv:varView("vix_mdm_item")}编码' ,
						width : 100 ,
						align : 'center'
						} , {
						field : 'name' ,
						title : '${vv:varView("vix_mdm_item")}名称' ,
						width : 100 ,
						align : 'center'
						}
						]
					] ,
					columns : [
						[
						{
						field : 'col1' ,
						title : '规格型号' ,
						width : 100 ,
						align : 'center'
						} , {
						field : 'col4' ,
						title : '主计量单位' ,
						width : 100 ,
						align : 'center'
						} , {
						field : 'col5' ,
						title : '一月' ,
						width : 50 ,
						align : 'right' ,
						editor : 'numberbox' ,
						required : 'true'
						} , {
						field : 'col5' ,
						title : '二月' ,
						width : 50 ,
						align : 'right' ,
						editor : 'numberbox' ,
						required : 'true'
						} , {
						field : 'col5' ,
						title : '三月' ,
						width : 50 ,
						align : 'right' ,
						editor : 'numberbox' ,
						required : 'true'
						} , {
						field : 'col5' ,
						title : '四月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'col5' ,
						title : '五月' ,
						width : 50 ,
						editor : 'numberbox' ,
						align : 'center'
						} , {
						field : 'col5' ,
						title : '六月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'col5' ,
						title : '七月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'col5' ,
						title : '八月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'col5' ,
						title : '九月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'col5' ,
						title : '十月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'col5' ,
						title : '十一月' ,
						width : 50 ,
						align : 'center'
						} , {
						field : 'col5' ,
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
				</script> -->

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