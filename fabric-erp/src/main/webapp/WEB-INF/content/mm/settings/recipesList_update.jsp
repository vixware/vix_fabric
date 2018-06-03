<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/base/jquery.ui.all.css" rel="stylesheet">
<script src="${vix}/common/js/jquery.tree.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/date.js" type="text/javascript"></script>
<script>
	/*$ (function (){
		$ ('#follow').scrollFollow ({
		offset : 50 ,
		container : 'followBox'
		});
	});*/
	function showPop (ele){
		console.log (ele , ({
		left : $ (ele).offset ().left - $ ('#addtodo').width () ,
		top : $ (ele).offset ().top - 36
		}));
		$ ('#addtodo').css ({
		left : $ (ele).offset ().left - $ ('#addtodoid').outerWidth () ,
		top : $ (ele).offset ().top - 50
		});
		$ ('#addtodoid').show ();
	}
</script>
<!-- head -->
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img src="img/icon_14.gif" alt="" />Print</a> <a href="#" id="help"><img src="img/icon_15.gif" alt="" />Help</a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/mm_pfgl.png" alt="" />生产管理</a></li>
				<li><a href="#">配方管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mm/recipesListAction!goList.action');">配方清单</a></li>
			</ul>
		</div>
	</h2>
</div>
<!-- sub menu -->
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
			<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
			<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
			<li class="fly"><a href="#"><img src="img/icon_10.png" alt="" />information</a>
				<ul>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
					<li><a href="#"><img src="img/icon_10.png" alt="" />information</a></li>
				</ul></li>
		</ul>
		<div>
			<label>Name<input name="" type="text" class="int" /></label> <label>My items<input name="" type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="Search" /><input name="" type="button" class="btn" value="Clear" /></label> <strong id="search_advanced">Advanced Search</strong>
		</div>
		<div class="search_advanced">
			<label>Name<input name="" type="text" class="int" /></label> <label>My items<input name="" type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="Search" /><input name="" type="button" class="btn" value="Clear" /></label> <label>Name<input name="" type="text" class="int" /></label> <label>My items<input
				name="" type="checkbox" value="" /></label> <label><input name="" type="button" class="btn" value="Search" /><input name="" type="button" class="btn" value="Clear" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left">
			<div class="switch_btn" id="switch_box"></div>
			<div class="left_content" id="tree_00"></div>
		</div>
		<!-- left -->
		<div id="right" style="overflow: hidden;">
			<div style="overflow: hidden;">
				<div class="tg_w_auto">
					<div class="right_btn clearfix" style="width: auto; border: 0; margin: 0;">
						<span><a href="javascript:void(0);" onclick="editNode('tt')" title="编辑"><img alt="" src="img/user.png"></a></span> <span><a href="javascript:void(0);" onclick="saveNode('tt')" title="保存"><img alt="" src="img/wrench_screwdriver.png"></a></span> <span><a href="javascript:void(0);" onclick="cancelNode('tt')" title="取消"><img
								alt="" src="img/address_book.png"></a></span>
					</div>
					<table id="tt" title="TreeGrid" class="easyui-treegrid" style="width: 1380px; height: 460px" url="json_tests/treegrid_data3.json" idField="id" treeField="code" pagination="true" fitColumns="true">
						<thead>
							<tr>
								<th field="code" width="150" editor="text">Code</th>
								<th field="name" width="200" editor="text">Name</th>
								<th field="addr" width="200" editor="text">Addr</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div style="overflow: hidden; margin-top: 15px">
				<div class="tg_w_auto">
					<div class="right_btn clearfix" style="width: auto; border: 0; margin: 0;">
						<span><a href="javascript:void(0);" onclick="editNode('tt')" title="编辑"><img alt="" src="img/user.png"></a></span> <span><a href="javascript:void(0);" onclick="saveNode('tt')" title="保存"><img alt="" src="img/wrench_screwdriver.png"></a></span> <span><a href="javascript:void(0);" onclick="cancelNode('tt')" title="取消"><img
								alt="" src="img/address_book.png"></a></span>
					</div>
					<table id="tt2" title="TreeGrid" class="easyui-treegrid" style="width: 1380px; height: 460px" url="json_tests/treegrid_data3.json" idField="id" treeField="code" pagination="true" fitColumns="true">
						<thead>
							<tr>
								<th field="code" width="150" editor="text">Code</th>
								<th field="name" width="200" editor="text">Name</th>
								<th field="addr" width="200" editor="text">Addr</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>

<script src="${vix}/common/js/loadtree.js" type="text/javascript"></script>
<script type="text/javascript">
	$ (document).ready (function (){
		// 左右拖动
		if ($ ("#left").length){
			$ ("#left").resizable ({
			maxHeight : 680 ,
			minHeight : 680 ,
			maxWidth : 400 ,
			minWidth : 200 ,
			handles : 'e'
			});
		}
		
		
		// 弹出层
		$.fx.speeds._default = 1000;
		$ (function (){
			$ ("#dialog").dialog ({
			autoOpen : false ,
			modal : true
			});
			$ ("#text").click (function (){
				$ ("#dialog").dialog ("open");
				return false;
			});
		});
		
		//渲染表格
		$('#tt').datagrid();
	});
</script>