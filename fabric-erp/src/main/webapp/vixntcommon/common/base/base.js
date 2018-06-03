/** 
 * 下列列表树接口
 * @param inputParentDivId      输入框外层div的id
 * @param url                   树形数据的url
 * @param dataId                选择树节点数据的id
 * @param dataName              选择树节点数据的name
 * @param treeId                ztree的id
 * @param menuContentId         展示树形结构的div的id
 * @param height                展示树形结构的div的高度，单位像素
 * @param chooseFinishFunc      回调方法，选中树后执行
 * @param getAsyncUrl           回调方法，获取下拉树url，用于下拉树的url变化的情况
 * @param isRefreshTreeOnShow   回调方法，获取是否每次显示都刷新树
 * @returns {Function}          返回显示下来列表树的方法
 */
function initDropListTree(inputParentDivId,url,dataId,dataName,treeId,menuContentId,height,chooseFinishFunc,getAsyncUrl,isRefreshTreeOnShow){
	/** 校验数据 */
	if(height == undefined || height == null || height == ''){
		height = 260;
	}
	
	/** 动态url通过回调方法获取 */
	if(url == '' && typeof getAsyncUrl == 'function'){
		url = getAsyncUrl;
	}
	
	var setting = {
		view: {
			dblClickExpand: false
		},
		async: {
			enable: true,
			url:url,
			autoParam:["id", "name=n", "level=lv","treeType"]
		},
		callback: {
			onClick: onClick
		}
	};
	
	var zTree = $.fn.zTree.init($("#"+treeId), setting);
	
	function onClick(e, treeId, treeNode) {
		if(treeNode.id != $("#id").val()){
			if(dataId != ''){$("#"+dataId).val(treeNode.id);}
			if(dataName != ''){$("#"+dataName).val(treeNode.name);}
			if(typeof chooseFinishFunc == 'function'){
				chooseFinishFunc(treeNode);
	    	}
		}else{
			layer.alert("分类不允许引用自身为父分类!", function(index){
				layer.close(index);
			});    
		}
		hideMenu();
	}

	function showMenu() {
		if(typeof isRefreshTreeOnShow == 'function'){
			if(isRefreshTreeOnShow()){
				/** 刷新获取动态url失败，销毁数重建 */
				$.fn.zTree.destroy(treeId);
				zTree = $.fn.zTree.init($("#"+treeId), setting);
			}
		}
		var width = $('#'+inputParentDivId).width();
		var treeCss = "margin-top:0;overflow-y:scroll;overflow-x:scroll;z-index:9999; height:" + height + "px; width:";
		treeCss += width + "px";
		$("#"+treeId).attr('style',treeCss);
		var top = $('#'+inputParentDivId).height();
		$("#"+menuContentId).css({left:"0px", top:top + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#"+menuContentId).fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == menuContentId || $(event.target).parents("#"+menuContentId).length>0)) {
			hideMenu();
		}
	}

	return showMenu;
}

/**
 * 显示延迟消失的提示信息
 * @param html       信息内容
 * @param colorType  颜色类型，success:成功，info:信息，warn：警告，error:错误
 * @param title      提示框标题
 * @param time       延迟时间，单位毫秒
 */
function showMessage(html,colorType,title,time){
	if(title == undefined || title == null){
		title = "default";
	}
	
	var color = '';
	if(colorType != undefined && colorType == 'success'){
		if(title == 'default'){title = '操作成功';}
		color = "#8ac38b";
	}
	if(colorType != undefined && colorType == 'info'){
		color = "#296191";
	}
	if(colorType != undefined && colorType == 'warn'){
		if(title == 'default'){title = '警告';}
		color = "#efe1b3";
	}
	if(colorType != undefined && colorType == 'error'){
		if(title == 'default'){title = '错误';}
		color = "#c26565";
	}
	
	/** 设置默认标题 */
	if(title == 'default'){
		title = "提示信息";
	}
	/** 默认提示颜色为info */
	if(color == ''){
		color = "#296191";
	}
	if(time == undefined || time == null){
		time = 4000;
	}
	$.smallBox({
		title : title,
		content : html,
		color : color,
		iconSmall : "fa fa-thumbs-up bounce animated",
		timeout : time
	});
}

/***
 * 选中树节点
 * @param url        json url 
 * @param title      窗口标题
 * @param entityName 选中接收的标签名称
 * @param func       选中后的回调函数
 */
function chooseTreeData(url,title,entityName,yesFuncCallBack,cancelFuncCallBack){
	if(title == undefined || title == null || title == ''){
		title = '选择数据';
	}
	if(entityName == undefined || entityName == null || entityName == ''){
		layer.alert("数据选择缺少参数!", function(index){
			layer.close(index);
		});
		return;
	}
	$.ajax({
		url:url,
		cache: false,
		success: function(html){
			layer.open({
			    type: 1,
			    title: title,
			    area: ['720px', '420px'],
			    closeBtn: 1,
			    content: html,
			    btn: ['确定', '取消']
		    ,yes: function(index, layero){
		    	if(typeof yesFuncCallBack == 'function'){
		    		yesFuncCallBack();
		    	}
		    	layer.close(index);
		    },cancel: function(index, layero){
		    	$("#"+entityName+"Id").val("");
		    	if(typeof cancelFuncCallBack == 'function'){
		    		cancelFuncCallBack();
		    	}
		    	layer.close(index);
			}});
		},error: function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("系统错误，请联系管理员");
		}
	});
}

/** 
 * 接口中会将选中的数据的id和name设置到调用方配置的标签中，标签id为：单选：entityName + Id,entityName + Name，多选：entityName + Ids
 * @param url         数据选择链接
 * @param chooseType  选择方式 ，multi:多选 ，single:单选
 * @param title       弹框标题
 * @param entityName  数据名称
 * @param func        选择商品后需要执行的方法
 * 选择数据接口的url列表：
 * 已发布商品：${snow}/ec/product/ecProductAction!goChooseEcProduct.action?status=1
 * 客户：${snow}/ec/member/ecCustomerAction!goChooseEcCustomer.action
 * 品牌：${snow}/ec/brand/productBrandAction!goChooseProductBrand.action
 * 公告：${snow}/ec/info/bulletinAction!goChooseBulletin.action 
 * 帮助：${snow}/ec/info/helpAction!goChooseHelp.action 
 * 帮助分类：${snow}/ec/info/helpCategoryAction!goChooseHelpCategory.action?chooseType=multi
 * 店铺：${snow}/ec/dianpu/microShopAction!goChooseMicroShop.action?chooseType=single
 * 字典：'${snow}/system/enumerationAction!goChooseEnumeration.action'
 * PC端活动：
 * 移动端活动：
 */
var openChooseListDataWindowId = "";
function chooseListData(url,chooseType,title,entityName,func,width,height){
	/** 设置弹出窗口id，窗口只允许打开一次，不允许重复打开 */
	if(openChooseListDataWindowId != ""){
		return;
	}else{
		openChooseListDataWindowId = entityName;
	}
	if(chooseType == undefined || chooseType == null || chooseType == ''){
		chooseType = 'single';
	}
	if(title == undefined || title == null || title == ''){
		title = '选择数据';
	}
	if(width == undefined || width == null || width == ''){
		width = '720';
	}
	if(height == undefined || height == null || height == ''){
		height = '520';
	}
	if(entityName == undefined || entityName == null || entityName == ''){
		if(chooseType == 'single'){
			layer.alert("数据选择缺少参数!", function(index){
				/** 清空id,允许再次打开窗口 */
				openChooseListDataWindowId = "";
				layer.close(index);
			});    
			return;
		}
	}
	if(url.indexOf('?') > 0){
		url += '&chooseType='+chooseType + "&entityName="+entityName;
	}else{
		url += '?chooseType='+chooseType + "&entityName="+entityName;
	}
	$.ajax({
		url:url,
		cache: false,
		success: function(html){
			layer.open({
			    type: 1,
			    title: title,
			    area: [width+'px', height+'px'],
			    closeBtn: 1,
			    content: html,
			    btn: ['确定', '取消']
		    ,yes: function(index, layero){
		    	/** 清空id,允许再次打开窗口 */
				openChooseListDataWindowId = "";
		    	if(typeof func == 'function'){
		    		func();
		    	}
		    	layer.close(index);
		    },cancel: function(index, layero){
		    	/** 清空id,允许再次打开窗口 */
				openChooseListDataWindowId = "";
				chooseMap.clear();
				if(chooseType == 'multi'){
					$("#"+entityName+"Ids").val("");
				}
				if(chooseType == 'single'){
					$("#"+entityName+"Id").val("");
					$("#"+entityName+"Name").val("");
				}
		    	layer.close(index);
			}});
		},error: function(XMLHttpRequest, textStatus, errorThrown) {
			/** 清空id,允许再次打开窗口 */
			openChooseListDataWindowId = "";
			layer.alert("系统错误，请联系管理员");
		}
	});
};

/**
 * 选择数据中的单选操作
 * @param entityName   实体描述名称，例如实体EcProduct，则entityName 为： ecProduct
 * @param id           实体id
 * @param name         实体名称
 */
function radioChange(entityName,id,name){
	parent.$('#' + entityName + 'Id').val(id);
	parent.$('#' + entityName + 'Name').val(name);
}

/** 定义全局数据选中map */
var chooseMap = getMap();
chooseMap.setColumnName("");
/**
 * 初始化数据的DataTable
 * 注：禁用列排序，例如："columnDefs" : [{"orderable" : false,"targets" : 0}],
 * @param tableId                DataTable的id
 * @param url                    DataTable的url
 * @param columns                DataTable的数据列
 * @param func                   DataTable回调方法
 * @param pageLength             每页数据量,默认10
 * @param isGenerateSelectStyle  是否生成列表选中效果, 0：不启用选中效果，1:单选 2：多选,默认1
 * @param isGenerateIndex        是否生成首列索引, 1:是 , 0：否,默认1
 * @param loadSelectIdsFunc      回调方法，返回选中的id,多选需要此回调方法
 * @param sDom                   sDom
 * @returns                      DataTable实例
 */
function initDataTable(tableId,url,columns,func,pageLength,isGenerateSelectStyle,isGenerateIndex,sDom){
	/** 校验数据 */
	if(tableId == undefined || tableId == null || tableId == ''){
		return null;
	}
	if(url == undefined || url == null || url == ''){
		return null;
	}
	if(columns == undefined || columns == null || columns == ''){
		return null;
	}
	if(func == undefined || typeof func != 'function'){
		return null;
	}
	if(sDom == undefined || sDom == null || sDom == ''){
		sDom = "<'dt-toolbar no-padding'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"+
				"t"+
				"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>";
	}
	if(pageLength == undefined || pageLength == null || pageLength == ''){
		pageLength = 10;
	}
	/** 默认不生成选中效果 */
	if(isGenerateSelectStyle == undefined || isGenerateSelectStyle == null || isGenerateSelectStyle == ''){
		isGenerateSelectStyle = '1';
	}
	/** 默认生成索引列 */
	if(isGenerateIndex == undefined || isGenerateIndex == null || isGenerateIndex == ''){
		isGenerateIndex = '1';
	}
	
	var tablePage = 1;
	var tablePageSize = 10;
	var responsiveHelper_dt_basic = undefined;
	var responsiveHelper_datatable_fixed_column = undefined;
	var responsiveHelper_datatable_col_reorder = undefined;
	var responsiveHelper_datatable_tabletools = undefined;
	
	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};
	var table = $('#'+tableId).DataTable({
		"sDom":sDom,
		"autoWidth" : false,
		"ordering" : true,// 排序
		"order":[[ 1,"asc"]],//默认排序列
		"info" : true,// 左下显示条数/总数
		"paging" : true, // 是否显示分页器
		"filter" : false, // 是否启用客户端过滤器
		"pageLength" : pageLength,// 每页显示的数量
		"lengthChange" : false, // 是否显示每页大小的下拉框
		'language': {  
        	'emptyTable': '没有数据',  
            'loadingRecords': '加载中...',  
            'zeroRecords': '没有数据',  
            'paginate': {'first':'第一页','last':'最后一页','next':'','previous':''},  
            'info': '共有 _MAX_ 条数据 , 第 _PAGE_ 页 / 共 _PAGES_ 页',  
            'infoEmpty': '没有数据',  
            'infoFiltered': '(过滤总件数 _MAX_ 条)'  
        },
		"columns" : columns,
		"serverSide" : true,
		"preDrawCallback" : function() {
			// Initialize the responsive datatables helper once.
			if (!responsiveHelper_datatable_fixed_column) {
				responsiveHelper_datatable_fixed_column = new ResponsiveDatatablesHelper($('#'+tableId), breakpointDefinition);
			}
		},
		"rowCallback" : function(nRow) {
			responsiveHelper_datatable_fixed_column.createExpandIcon(nRow);
		},
		"drawCallback" : function(oSettings) {
			responsiveHelper_datatable_fixed_column.respond();
		},
		"ajax" : {
			"url" : url,
			"data" : function(data) {
				var page = data.start/data.length + 1;
				var pageSize = data.length;
				tablePage = page;
				tablePageSize = pageSize;
				
				var orderField = "";
				if(data.order.length > 0){
					orderField = data.columns[data.order[0].column].name;
				}
				var orderBy = data.order[0].dir;
				return func(page,pageSize,orderField,orderBy);
			},"error":function(){
				layer.alert("系统错误，请联系管理员");
			}
		}
	});
	
	if(isGenerateIndex == '1'){
		/** 生成索引 */
		table.on('draw', function () {
			/** 计算最后一页的数据量 */
			var allCount = table.ajax.json().recordsFiltered;
			var lastPageCount = allCount - ((tablePage-1) * tablePageSize);
			
			table.column(0).nodes().each(function (cell, i) {
				if(i < lastPageCount){
					cell.innerHTML = i+1 + (table.page() * table.page.len());
				}
		    });
		});
	}
	
	/** 行选中效果(单选) */
	if(isGenerateSelectStyle == '1'){
		 $('#'+tableId+' tbody').on( 'click', 'tr', function () {
	    	if ($(this).hasClass('selected') ) {
	        	$(this).removeClass('selected');
	        }else {
	        	table.$('tr.selected').removeClass('selected');
	        	$(this).addClass('selected');
	        	/** 选中行时，选中radio */
	        	$(this).find("input").trigger("change");
	        	$(this).find("input").click();
	        }
	    });
	}
	/** 行选中效果(多选) */
	if(isGenerateSelectStyle == '2'){
		$('#'+tableId+' tbody').on( 'click', 'tr', function () {
			$(this).toggleClass('selected');
			/** 获取选中id */
			var ids = "";
			var names = "";
			var column = chooseMap.getColumnName();
			var data = table.rows(".selected").data();
			for(var i=0;i<data.length;i++){
				ids += data[i].id + ",";
				if(column != ""){
					names += data[i][column] + ",";
				}
			}
			chooseMap.put(table.page(),ids + ":" + names);
	    });
		
		/** 选中项恢复 */
		table.on('draw.dt', function () {
   			var ids = chooseMap.get(table.page());
   	   		if(ids != null && ids != undefined && ids != ''){
   	   			var idArray = chooseMap.valueIdSetInner().split(',');
   		   		for(var i= 0; i < idArray.length; i++){
   		   			$("#" + idArray[i]).addClass("selected");
   		   		}
   			}
   		});
	}
	
	$('#'+tableId+' thead th input[type=text]').on('keyup change', function() {
		table.column($(this).parent().index() + ':visible').search(this.value).draw();
	});
	return table;
}

/**
 * 数据表格的多选效果控制
 * @param tableId  表id
 * @param tag      操作标识，全选，反选，取消
 * @param func     回调方式，用于处理列表中不能被选中的数据
 */
function changeDataTableSelect(tableId,tag,func){
	if(tag == 'all'){
		$('#'+tableId+' tbody tr').each(function() {
			$(this).addClass("selected");
	    });
	}
	if(tag == 'reverse'){
		$('#'+tableId+' tbody tr').each(function() {
			if($(this).hasClass("selected")){
				$(this).removeClass("selected");
			}else{
				$(this).addClass("selected");
			}
	    });
	}
	if(tag == 'cancle'){
		$('#'+tableId+' tbody tr').each(function() {
			$(this).removeClass("selected");
	    });
	}
	var table = $("#"+tableId).DataTable();
	/** 获取选中id与name */
	var ids = "";
	var names = "";
	var column = chooseMap.getColumnName();
	var data = table.rows(".selected").data();
	for(var i=0;i<data.length;i++){
		ids += data[i].id + ",";
		if(column != ""){
			names += data[i][column] + ",";
		}
	}
	chooseMap.put(table.page(),ids + ":" + names);

	if(typeof func == 'function'){
		func();
	}
}

/**
 * 初始化数据的DataTable
 * 注：禁用列排序，例如："columnDefs" : [{"orderable" : false,"targets" : 0}],
 * @param tableId                DataTable的id
 * @param url                    DataTable的url
 * @param columns                DataTable的数据列
 * @param func                   DataTable回调方法
 * @param isGenerateSelectStyle  是否生成列表选中效果, 0：不启用选中效果，1:单选 2：多选
 * @param isGenerateIndex        是否生成首列索引, 1:是 , 0：否
 * @returns                      DataTable实例
 */
function initDataTableNoPaging(tableId,url,columns,func,isGenerateSelectStyle,isGenerateIndex){
	/** 校验数据 */
	if(tableId == undefined || tableId == null || tableId == ''){
		return null;
	}
	if(url == undefined || url == null || url == ''){
		return null;
	}
	if(columns == undefined || columns == null || columns == ''){
		return null;
	}
	/** 默认不生成选中效果 */
	if(isGenerateSelectStyle == undefined || isGenerateSelectStyle == null || isGenerateSelectStyle == ''){
		isGenerateSelectStyle = '1';
	}
	/** 默认生成索引列 */
	if(isGenerateIndex == undefined || isGenerateIndex == null || isGenerateIndex == ''){
		isGenerateIndex = '1';
	}

	var table = $('#'+tableId).DataTable({
		"autoWidth" : true,
		"ordering" : false,// 排序
		"order":[[ 1,"desc"]],//默认排序列
		"info" : false,// 左下显示条数/总数
		"paging" : false, // 是否显示分页器
		"filter" : false, // 是否启用客户端过滤器
		"lengthChange" : false, // 是否显示每页大小的下拉框
		'language': {  
        	'emptyTable': '没有数据',  
            'loadingRecords': '加载中...',  
            'processing': '查询中...',  
            'zeroRecords': '没有数据',  
            'paginate': {'first':'第一页','last':'最后一页','next':'','previous':''},  
            'info': '共有 _MAX_ 条数据 , 第 _PAGE_ 页 / 总 _PAGES_ 页',  
            'infoEmpty': '没有数据',  
            'infoFiltered': '(过滤总件数 _MAX_ 条)'  
        },
		"columns" : columns,
		"serverSide" : true,
		"ajax" : {
			"url" : url, 
			"data" : function(data) {
				if(func != undefined && typeof func == 'function'){
					var orderField = "id";
					var orderBy = "asc";
					if(data.order.length > 0){
						orderField = data.columns[data.order[0].column].name;
						orderBy = data.order[0].dir;
					}
					return func(orderField,orderBy);
				}
			},error:function(){
				layer.alert("系统错误，请联系管理员");
			}
		}
	});
	
	if(isGenerateIndex == '1'){
		/** 生成索引 */
		table.on('draw', function () {
			table.column(0).nodes().each(function (cell, i) {
				cell.innerHTML = i+1;
		    });
		});
	}
	
	/** 行选中效果(单选) */
	if(isGenerateSelectStyle == '1'){
		 $('#'+tableId+' tbody').on( 'click', 'tr', function () {
	    	if ($(this).hasClass('selected') ) {
	        	$(this).removeClass('selected');
	        }else {
	        	table.$('tr.selected').removeClass('selected');
	        	$(this).addClass('selected');
	        	/** 选中行时，选中radio */
	        	$(this).find("input").click();
	        }
	    });
	}
	/** 行选中效果(多选) */
	if(isGenerateSelectStyle == '2'){
		 $('#'+tableId+' tbody').on( 'click', 'tr', function () {
			 $(this).toggleClass('selected');
	    });
	}
	return table;
}

/***
 * 带form提交的弹出窗口
 * @param url             弹出窗口url
 * @param formId          窗口中表单id
 * @param title           弹出窗口标题
 * @param width           窗口宽度
 * @param height          窗口高度
 * @param table           需要刷新的table
 * @param beforeFormFunc  回调方法,表单提交前调用
 * @param afterFormfunc   回调方法，表单提交后调用
 */
var openSaveOrUpdateWindowId = "";
function openSaveOrUpdateWindow(url,formId,title,width,height,table,beforeFormFunc,afterFormfunc){
	/** 设置弹出窗口id，窗口只允许打开一次，不允许重复打开 */
	if(openSaveOrUpdateWindowId != "" && openSaveOrUpdateWindowId == formId){
		return;
	}else{
		openSaveOrUpdateWindowId = formId;
	}
	/** 校验数据 */
	if(url == undefined || url == null || url == ''){
		return null;
	}
	if(formId == undefined || formId == null || formId == ''){
		return null;
	}
	if(title == undefined || title == null || title == ''){
		title = "弹出窗口";
	}
	if(width == undefined || width == null || width == ''){
		width = 720;
	}
	if(height == undefined || height == null || height == ''){
		height = 320;
	}
	
	$.ajax({
		url:url,
		cache: false,
		success: function(html){
			layer.open({
			    type: 1,
			    title: title,
			    area: [width+'px', height+'px'],
			    closeBtn: 1,
			    content: html,
			    btn: ['确定', '取消']
		    ,yes: function(index, layero){
		    	/** 清空id,允许再次打开窗口 */
		    	openSaveOrUpdateWindowId = "";
		    	//表单验证
				if(!$("#"+formId).validationEngine('validate')){
					return false;
				}

				/** 打开遮盖层 */
				var loadIndex = layer.load(2);
				if(typeof beforeFormFunc == 'function'){
					var result = beforeFormFunc();
					if(result == false){
						/** 关闭遮盖层 */
						layer.close(loadIndex);
						return false;
					}
		    	}
		    	$("#"+formId).ajaxSubmit({
		    		type: "post",
		    		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					success : function(html) {
						/** 关闭遮盖层 */
						layer.close(loadIndex);
						/** 关闭窗口遮盖层 */
						layer.close(index);
						var result = html.split(":");
						if(result.length > 1){
							showMessage(result[1],'success');
						}else{
							showMessage(html,'success');
						}
						if(typeof afterFormfunc == 'function'){
							afterFormfunc(html);
				    	}
						if(table != undefined && table != null){
							table.ajax.reload();
						}
					},error: function(XMLHttpRequest, textStatus, errorThrown) {
						/** 关闭遮盖层 */
						layer.close(loadIndex);
						layer.alert("系统错误，请联系管理员");
					}
				});
			},cancel: function(index, layero){
		    	/** 清空id,允许再次打开窗口 */
				openSaveOrUpdateWindowId = "";
			}});
		},error: function(XMLHttpRequest, textStatus, errorThrown) {
			/** 清空id,允许再次打开窗口 */
			openSaveOrUpdateWindowId = "";
			layer.alert("系统错误，请联系管理员");
		}
	});
}

/***
 * 不带form提交的弹出窗口
 * @param url             弹出窗口url
 * @param title           弹出窗口标题
 * @param width           窗口宽度
 * @param height          窗口高度
 * @param func            回调方法
 */
function openWindowForShow(url,title,width,height,func){
	/** 校验数据 */
	if(url == undefined || url == null || url == ''){
		return null;
	}
	if(title == undefined || title == null || title == ''){
		title = "弹出窗口";
	}
	if(width == undefined || width == null || width == ''){
		width = 720;
	}
	if(height == undefined || height == null || height == ''){
		height = 320;
	}
	
	$.ajax({
		url:url,
		cache: false,
		success: function(html){
			layer.open({
			    type: 1,
			    title: title,
			    area: [width+'px', height+'px'],
			    closeBtn: 1,
			    content: html,
			    btn: ['关闭']
		    ,yes: function(index, layero){
		    	/** 关闭窗口遮盖层 */
				layer.close(index);
				if(typeof func == 'function'){
					func();
		    	}
			}});
		},error: function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("系统错误，请联系管理员");
		}
	});
}

/**
 * 跳转到修改内容页面，切换右侧内容区
 * @param url  修改页面url
 */
function goSaveOrUpdateEntity(url){
	$.ajax({
		url:url,
		cache: false,
		success: function(html){
			$("#content").html(html); 
		},error: function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("系统异常，请联系管理员");
		}
	});
}

/**
 * 保存数据
 * @param formId          表单id
 * @param beforeFormFunc  表单提交前回调方法
 * @param afterFormfunc   表单提交后回调方法
 */
function saveOrUpdateEntity(formId,beforeFormFunc,afterFormfunc){
	/** 校验数据 */
	if(formId == undefined || formId == null || formId == ''){
		return null;
	}
	
	//表单验证
	if(!$("#"+formId).validationEngine('validate')){
		return false;
	}
	
	/** 打开遮盖层 */
	var loadIndex = layer.load(2);
	
	if(typeof beforeFormFunc == 'function'){
		var result = beforeFormFunc();
		if(result == false){
			/** 关闭遮盖层 */
			layer.close(loadIndex);
			return false;
		}
	}
	$("#"+formId).ajaxSubmit({
		type: "post",
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(html) {
			/** 关闭遮盖层 */
			layer.close(loadIndex);
			var result = html.split(":");
			if(result.length > 1){
				showMessage(result[1],'success');
			}else{
				showMessage(html,'success');
			}
			if(typeof afterFormfunc == 'function'){
				afterFormfunc(html);
	    	}
		},error: function(XMLHttpRequest, textStatus, errorThrown) {
			/** 关闭遮盖层 */
			layer.close(loadIndex);
			layer.alert("系统错误，请联系管理员");
		}
	});
}

/**
 * 弹出询问是否更新数据弹出框
 * @param url             数据更新url
 * @param confirmContent  询问信息
 * @param table           需要刷新的table
 * @param title           弹出框标题
 * @param func            回调方法，数据更新后调用
 */
function updateEntityByConfirm(url,confirmContent,table,title,func){
	/** 校验数据 */
	if(url == undefined || url == null || url == ''){
		return null;
	}
	if(confirmContent == undefined || confirmContent == null || confirmContent == ''){
		confirmContent = "?";
	}
	if(title == undefined || title == null || title == ''){
		title = "提示信息";
	}
	
	layer.confirm(confirmContent, {title:title}, function(index){
		/** 打开遮盖层 */
		var loadIndex = layer.load(2);
		$.ajax({
			url:url,
			cache: false,
			success: function(html){
				/** 关闭遮盖层 */
				layer.close(loadIndex);
				showMessage(html,'success');
				if(typeof func == 'function'){
		    		func();
		    	}
				if(table != undefined && table != null){
					table.ajax.reload();
				}
			},error: function(XMLHttpRequest, textStatus, errorThrown) {
				/** 关闭遮盖层 */
				layer.close(loadIndex);
				layer.alert("系统错误，请联系管理员");
			}
		});
	    layer.close(index);
	});
}

/**
 * 弹出询问是否删除数据弹出框
 * @param url             数据删除url
 * @param confirmContent  询问信息
 * @param table           需要刷新的table
 * @param title           弹出框标题
 * @param func            回调方法，数据删除后调用
 */
function deleteEntityByConfirm(url,confirmContent,table,title,func){
	/** 校验数据 */
	if(url == undefined || url == null || url == ''){
		return null;
	}
	if(confirmContent == undefined || confirmContent == null || confirmContent == ''){
		confirmContent = "?";
	}
	if(title == undefined || title == null || title == ''){
		title = "提示";
	}
	
	layer.confirm(confirmContent, {title:title}, function(index){
		/** 打开遮盖层 */
		var loadIndex = layer.load(2);
		$.ajax({
			url:url,
			cache: false,
			success: function(html){
				/** 关闭遮盖层 */
				layer.close(loadIndex);
				if(html.indexOf(':') > 0){
					layer.alert(html.split(":")[1]);
				}else{
					showMessage(html,'success');
				}
				if(typeof func == 'function'){
		    		func();
		    	}
				if(table != undefined && table != null){
					table.ajax.reload();
				}
			},error: function(XMLHttpRequest, textStatus, errorThrown) {
				/** 关闭遮盖层 */
				layer.close(loadIndex);
				layer.alert("系统错误，请联系管理员");
			}
		});
	    layer.close(index);
	});
}

/**
 * 数据导入弹出窗口
 * @param url      数据导入链接
 * @param formId   数据导入表单id
 * @param title    窗口标题
 * @param width    窗口宽度
 * @param height   窗口高度
 * @param table    需要刷新的table 
 * @param func     回调方法，数据导入完成后调用
 */
function importEntity(url,formId,title,width,height,table,func){
	/** 校验数据 */
	if(url == undefined || url == null || url == ''){
		return null;
	}
	if(formId == undefined || formId == null || formId == ''){
		return null;
	}
	if(title == undefined || title == null || title == ''){
		title = "导入数据";
	}
	if(width == undefined || width == null || width == ''){
		width = 480;
	}
	if(height == undefined || height == null || height == ''){
		height = 240;
	}
	
	$.ajax({
		  url:url,
		  cache: false,
		  success: function(html){
			  layer.open({
				    type: 1,
					title:title,
				    area: [width + 'px', height + 'px'],
				    closeBtn: 1,
				    shadeClose: true,
				    content: html,
				    btn: ['导入', '取消']
			    ,yes: function(index, layero){
			    	/** 打开遮盖层 */
					var loadIndex = layer.load(2);
					$("#"+formId).ajaxSubmit({
					    dataType: "text",
					    success: function(result){
					    	/** 关闭遮盖层 */
							layer.close(loadIndex);
					    	showMessage(result,'success');
					    	if(typeof func == 'function'){
					    		func();
					    	}
					    	if(table != undefined && table != null){
								table.ajax.reload();
							}
					    },error: function(XMLHttpRequest, textStatus, errorThrown) {
							/** 关闭遮盖层 */
							layer.close(loadIndex);
							layer.alert("系统错误，请联系管理员");
						}
					});
					layer.close(index);
				}
			});
		},error: function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("系统错误，请联系管理员");
		}
	});
}

/** 
 * 搜索框清空
 * @param parameters 参数id列表，逗号分割
 */
function clearSearchCondition(parameters,table){
	if(parameters != undefined && parameters != null && parameters != ''){
		var paraNames = parameters.split(",");
		for (var i=0 ; i < paraNames.length ; i++){
			$("#"+paraNames[i]).val('');
		}
	}
	if(table != undefined && table != ''){
		table.ajax.reload();
	}
	/** 从新过滤情况选中项目 */
	chooseMap.clear();
}

/**
 * 数据表动态隐藏或显示列
 * @param table    DataTable引用
 * @param indexs   需要操作的列索引，DataTable索引从0开始，参数接收的索引使用逗号分隔
 * @param visible  取值：true（显示列） 或 false（隐藏列）,默认隐藏
 */
function hideDataTableColumnByIndex(table,indexs,visible){
	if(null == table || undefined == table || table == ''){
		layer.alert("需要动态显示或隐藏列的table未获取到!");
	}
	if(null == indexs || undefined == indexs || indexs == ''){
		layer.alert("需要动态显示或隐藏列的索引列表未获取到!");
	}
	if(null == visible || undefined == visible || visible == ''){
		visible = false;
	}
	
	var indexArray = indexs.split(",");
	if(indexArray.length <= 0){
		layer.alert("需要动态显示或隐藏列的索引列表未获取到!");
	}
	
	 for(var i=0; i<indexArray.length;i++){
		 if(!isNaN(indexArray[i])){
			 table.column(Number(indexArray[i])).visible(visible);
		 }
	 }
}
/**
 * 上传文件
 * @param formId       文件上传form的id
 * @param formUrl      文件上传form的url
 * @param fileId       文件id
 * @param fileLength   文件长度，单位kb
 * @param fileType     文件类型 file:文件，image:图片
 * @param func         文件上传的回调方法,处理上传文件后的返回值
 */
function uploadFileOrImage(formId,formUrl,fileId,fileType,func,fileLength) {
	/** 校验数据 */
	if(fileId == undefined || fileId == null){
		showMessage("文件未获取到!",'error');
		return;
	}
	if(fileLength == undefined || fileLength == null){
		/** 文件默认最大500kb */
		fileLength = 500000000;
	}
	
	var fileInput = document.getElementById(fileId);
	var filename = fileInput.files[0].name;
	byteSize  = fileInput.files[0].size;
	var fileKb =  Math.ceil(byteSize / 1024);
	var fileExt=(/[.]/.exec(filename)) ? /[^.]+$/.exec(filename.toLowerCase()) : '';
   	if(fileKb > fileLength){
   		showMessage("文件大小超过限制!",'error');
		return;
   	}
   	if(fileType == 'image' && fileExt != "jpg" && fileExt != "jpeg" && fileExt != "JPG" && fileExt != "JPEG" && fileExt != "gif" && fileExt != "GIF" && fileExt != "png" && fileExt != "PNG"){  
   		showMessage("文件格式不对!",'error');
		return;
   	}

   	/** 构建form */
   	var formString = "<form id='" + formId + "' action='" + formUrl + "' method='post' enctype='multipart/form-data'></form>";
   	
   	$("#"+fileId).wrap(formString);
   	
   	/** 打开遮盖层 */
	var loadIndex = layer.load(2);
	
	$("#"+formId).ajaxSubmit({
		dataType:  'text',
   		uploadProgress: function(event, position, total, percentComplete) {},
		success: function(data) {
			/** 关闭遮盖层 */
			layer.close(loadIndex);
			$("#"+fileId).val('');
			if(typeof func == 'function'){
	    		func(data);
	    	}
			return;
		},
		error:function(xhr){
			/** 关闭遮盖层 */
			layer.close(loadIndex);
			layer.alert("文件上传失败！");
			return;
		}
	});
	if($("#"+formId) != undefined){
		$("#"+fileId).unwrap();
	}
}

/** 时间区间比较，时间格式yyyy-MM-dd HH:mm:ss */
function dateTimeRange(startTime,endTime){
	/** 数据校验 */
	if(startTime == endTime){return false;}
	if(startTime == null || startTime == '' || startTime == undefined){return false;}
	if(endTime == null || endTime == '' || endTime == undefined){return false;}
	if(startTime.length != 19 || endTime.length != 19){return false;}
	
	var startTimeFirstArr = startTime.split(" ");
	var endTimeFirstArr = endTime.split(" ");
	
	var startTimeSecondArr1 = startTimeFirstArr[0].split("-");
	var startTimeSecondArr2 = startTimeFirstArr[1].split(":");
	var endTimeSecondArr1 = endTimeFirstArr[0].split("-");
	var endTimeSecondArr2 = endTimeFirstArr[1].split(":");
	
	var start = new Date(parseInt(startTimeSecondArr1[0]),parseInt(startTimeSecondArr1[1]),parseInt(startTimeSecondArr1[2]),parseInt(startTimeSecondArr2[0]),parseInt(startTimeSecondArr2[1]),parseInt(startTimeSecondArr2[2]));
	var end = new Date(parseInt(endTimeSecondArr1[0]),parseInt(endTimeSecondArr1[1]),parseInt(endTimeSecondArr1[2]),parseInt(endTimeSecondArr2[0]),parseInt(endTimeSecondArr2[1]),parseInt(endTimeSecondArr2[2]));
	
	if(start.getTime() < end.getTime()){
		return true;
	}else{
		return false;
	}
}

/**
 * 初始化map,为系统内弹窗窗口跨页选中提供数据存取
 * columnName(字符串)        弹出窗口中需要选中数据的name
 * valueIdSet(方法)         获取选中的ids,并释放存储数据的内存
 * valueNameSet(方法)       获取选中的names,并释放存储数据的内存
 * valueSetWithClear(方法)  获取选中的ids与names，用：拼接，并释放存储数据的内存
 * clear                   清空数据释放内存
 */
function getMap() {
    var map_ = new Object();
    
    map_.setColumnName = function(name){
    	map_["columnName"] = name;
    }
    map_.getColumnName = function(name){
    	return map_["columnName"];
    }
    
	map_.put = function(key, value) {
	    map_[key+'_'] = value;
    };
    map_.get = function(key) {
    	return map_[key+'_'];
    };

    map_.remove = function(key) {
    	delete map_[key+'_'];
    };

    map_.keySet = function() {
	    var ret = "";
	    for(var p in map_) {
		    if(typeof p == 'string' && p.substring(p.length-1) == "_") {
			    ret += ",";
			    ret += p.substring(0,p.length-1);
		    }
	    }
	    return ret;
	};
	
	map_.valueIdSetInner = function() {
	    var ids = "";
	    var temp = "";
	    for(var p in map_) {
		    if(typeof p == 'string' && p.substring(p.length-1) == "_") {
		    	temp = map_[p].split(":");
		    	ids += ",";
		    	ids += temp[0];
		    }
	    }
	    return ids.substring(1,ids.length);
	};
	
	map_.valueIdSet = function() {
	    var ids = "";
	    var temp = "";
	    for(var p in map_) {
		    if(typeof p == 'string' && p.substring(p.length-1) == "_") {
		    	temp = map_[p].split(":");
		    	ids += ",";
		    	ids += temp[0];
		    	delete map_[p];
		    }
	    }
	    return ids.substring(1,ids.length);
	};
	
	map_.valueNameSet = function() {
	    var names = "";
	    var temp = "";
	    for(var p in map_) {
		    if(typeof p == 'string' && p.substring(p.length-1) == "_") {
		    	temp = map_[p].split(":");
		    	if(temp.length == 2){
		    		names += ",";
		    		names += temp[1];
		    		delete map_[p];
		    	}
		    }
	    }
	    return names.substring(1,names.length);
	};
	
	map_.valueSetWithClear = function() {
		var ids = "";
	    var names = "";
	    var temp = "";
	    for(var p in map_) {
		    if(typeof p == 'string' && p.substring(p.length-1) == "_") {
		    	temp = map_[p].split(":");
		    	ids += ",";
		    	ids += temp[0];
		    	if(temp.length == 2){
		    		names += ",";
		    		names += temp[1];
		    	}
		    	delete map_[p];
		    }
	    }
	    map_["columnName"] = "";
	    return ids.substring(1,ids.length) + ":" + names.substring(1,names.length);
	};
	
	map_.clear = function(){
		for(var p in map_) {
			if(typeof p == 'string' && p.substring(p.length-1) == "_") {
				delete map_[p];
		    }
	    }
		map_["columnName"] = "";
	}

    return map_;
}