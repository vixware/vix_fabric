/*
 * vix grid页面 生成table上下的pager信息及grid
*/

var id_grid_row_selected_row_ids = 'row_selected_ids';//当前被选中的行id保存到container
var id_grid_container_saved_pager = 'id_grid_container_saved_pager';//grid的pager对象保存到container
var set_grid_save_row_selected_in_all_page = false;

function _grid_init_table_list(gridTable, pagerJson){
	if(!gridTable)
		return;
	if(gridTable instanceof jQuery){
	}else{
		if(gridTable.substring(0,1)!='#')
			gridTable = '#' + gridTable;
		gridTable = $(gridTable);
	}
	
	_grid_bind_grid_sort_event(gridTable);
	
	if(!pagerJson)
		pagerJson = gridTable.attr('pager');
	
	var pagerInfo = new _grid_init_grid_pager(gridTable, pagerJson);
	
	var fillblank = gridTable.attr('fillblank');

	if(fillblank && fillblank==1)
		_grid_fill_blank_rows(gridTable, pagerJson);
	
	return pagerInfo;
}

function _grid_fill_blank_rows(gridTable, pagerJson){
	var thead = gridTable.find('thead');
	var tbody = gridTable.find('tbody');
	
	if(tbody.length>0){
		var _pad_pager_json = eval('('+pagerJson+')');
		var colspan = thead.find('tr:first th').length;
		if(colspan==0)
			colspan = thead.find('tr:first td').length;
		var pageSize = _pad_pager_json.pageSize;
		var currentPageSize = _pad_pager_json.currentPageSize;

		var row = '<tr><td colspan="'+colspan+'">&nbsp;</td></tr>';
		
		var loop = pageSize - currentPageSize;
		for(var i=0;i<loop;i++){
			tbody.append(row);
		}
	}
}

function _grid_init_grid_pager(gridTable, pagerJson){
	if(!pagerJson)
		return;

	this.gridTable = gridTable;
	var _pad_grid_container_id = gridTable.attr('containerId');
	var container = $(_pad_grid_container_id);

	//在grid的container上记录grid pager对象
	var pagerInfo = this;
	container.data(id_grid_container_saved_pager, pagerInfo);
	
	var _pad_pager_json = eval('('+pagerJson+')');

	if(!set_grid_save_row_selected_in_all_page){
		//如果不是全局保存选中行id，清除缓存
		container.data(id_grid_row_selected_row_ids,'');
	}else{
		//否则初始化被选中行
		var selectedRowIds = container.data(id_grid_row_selected_row_ids);

		if(selectedRowIds && selectedRowIds.length>0){
			gridTable.find('tbody .grid_check').each(function(){
				var tempKey = ',' + $(this).val() + ',';
				if(selectedRowIds.indexOf(tempKey)!=-1){
					$(this).prop('checked',true);
				}
			});
		}
	}
	
	var pagerClass = gridTable.attr('id') + "_pager";

	//pager div
	var _pad_pager_div = $('<div class="pagelist drop '+pagerClass+'"></div>');
	//复选框下来操作
	var _pad_dropdown_ul = $('<ul class="dropdown" style="display:none;"></ul>');
	_pad_pager_div.append(_pad_dropdown_ul);
	var _pad_dropdown_li = $('<li class=""><a href="javascript:void(0)">操作</a></li>');
	_pad_dropdown_ul.html(_pad_dropdown_li);
	var _pad_dropdown_actions = $('<ul class="dropdown_action"></ul>');
	_pad_dropdown_li.append(_pad_dropdown_actions);

	var linkArr = gridTable.data('dropdown_actions');

	if(linkArr && linkArr.length>0){
		for(l = 0;l<linkArr.length;l++){
			var data = linkArr[l];
			_pad_dropdown_actions.append($('<li><a href="'+data.href+'">'+data.title+'</a></li>'));
		}
		_pad_dropdown_ul.show();
	}
	
	//复选框选中文字说明
	var _pad_selected_txt = $('<strong class="selected_txt"></strong>');
	_pad_pager_div.append(_pad_selected_txt);
	var _pad_table_checkboxs = gridTable.find('tbody .grid_check');
	_pad_selected_txt.html('选中:'+gridTable.find('tbody .grid_check:checked').length);
	if(_pad_table_checkboxs.length==0){
		_pad_selected_txt.hide();
		_pad_dropdown_ul.hide();
	}
	//全选事件
	gridTable.find('thead .grid_check_all').bind('click',function(){
		if($(this).prop('checked')){
			gridTable.find('tbody .grid_check').each(function(){
				$(this).prop('checked',true);
				pagerInfo.setSelectedRowId($(this).val(),true);
			});
			//gridTable.find('tbody .grid_check').prop('checked',true);
			$('.selected_txt').html('选中:'+gridTable.find('tbody .grid_check').length);
		}else{
			gridTable.find('tbody .grid_check').each(function(){
				$(this).prop('checked',false);
				pagerInfo.setSelectedRowId($(this).val(),false);
			});
			//gridTable.find('tbody .grid_check').prop('checked',false);
			$('.selected_txt').html('选中:0');
		}
	});
	
	//单选事件
	_pad_table_checkboxs.bind('click',function(){
		$('.selected_txt').html('选中:'+gridTable.find('tbody .grid_check:checked').length);
		if($(this).prop('checked')){
			pagerInfo.setSelectedRowId($(this).val(),true);
		}else{
			pagerInfo.setSelectedRowId($(this).val(),false);
		}
	});
	
	var _pad_pager_obj = $('<div></div>');
	_pad_pager_div.append(_pad_pager_obj);

	//翻页  ,之前是判断_pad_pager_json.pageBeginRow && _pad_pager_json.pageBeginRow>0
	if(_pad_pager_json){
		
		if(_pad_pager_json.pageSize && _pad_pager_json.pageSize>0)
			container.attr(_pad_grid_page_size, _pad_pager_json.pageSize);
		
		var _pad_p_start = $('<span><a class="start" onclick="javascript:_grid_goPage(\''+_pad_grid_container_id+'\',1)"></a></span>');
		var _pad_p_previous = $('<span><a class="previous" onclick="javascript:_grid_goPage(\''+_pad_grid_container_id+'\','+_pad_pager_json.prePage+')"></a></span>');
		var _pad_p_row_info = '';
		_pad_p_row_info = _pad_pager_json.pageBeginRow + ' - '+ _pad_pager_json.pageEndRow;
		var _pad_p_info_html = '<em>('+
								_pad_p_row_info+
								' 共 '+
								_pad_pager_json.totalCount+
								'<b class="categoryTotalCount"></b>)</em>';
		var _pad_p_info = $(_pad_p_info_html);
		var _pad_p_next = $('<span><a class="next" onclick="javascript:_grid_goPage(\''+_pad_grid_container_id+'\','+_pad_pager_json.nextPage+')"></a></span>');
		var _pad_p_end = $('<span><a class="end" onclick="javascript:_grid_goPage(\''+_pad_grid_container_id+'\','+_pad_pager_json.pageCount+')"></a></span>');
		_pad_pager_obj.html('');
		_pad_pager_obj.append(_pad_p_start);
		_pad_pager_obj.append(_pad_p_previous);
		_pad_pager_obj.append(_pad_p_info);
		_pad_pager_obj.append(_pad_p_next);
		_pad_pager_obj.append(_pad_p_end);
	}

	//在grid上下各添加一份pager
	var pager_container_class = 'grid_table_pager_container_'+gridTable.attr('id');
	var pagerUp = $('<div class="'+pager_container_class+'"></div>');
	var pagerDown = $('<div class="'+pager_container_class+'"></div>');
	$(pagerUp).insertBefore(gridTable);
	$(pagerDown).insertAfter(gridTable);

	_pad_pager_div.appendTo('.'+pager_container_class);

	pagerUp.find('ul.dropdown li:first').addClass('tp');
	pagerDown.find('ul.dropdown li:first').addClass('ed');
	
	//当前对象
	this.pagerDiv = _pad_pager_div;
	this.dropdownActions = _pad_dropdown_actions;
	this.selectedTxt = _pad_selected_txt;
	this.pagerObj = _pad_pager_obj;
	
	//添加grid上面的下来菜单操作
	this.addDropdownAction = function(title, href){
		$('div.'+pagerClass+' ul.dropdown ul.dropdown_action').each(function(){
			$(this).append($('<li><a href="'+href+'">'+title+'</a></li>'));
			var dropdown = $(this).parent().parent();
			var selectedTxt = dropdown.siblings('.selected_txt:first');
			if(dropdown && selectedTxt){
				if(dropdown.is(':hidden') && selectedTxt.is(':visible'))
					dropdown.show();
			}
		});
	};
	
	//添加行复选框选中后id存储
	this.setSelectedRowId = function(rowId,isSelected){
		var idContainer;
		var idContainerId = this.gridTable.attr('containerId');
		if(idContainerId && idContainerId.length>0){
			idContainer = $(idContainerId);
		}else{
			idContainer = this.gridTable.parent();
		}
		
		try{
			var idStr = idContainer.data(id_grid_row_selected_row_ids);
			if(!idStr)
				idStr = ',';

			var idMark = ',' + rowId + ',';
			if(isSelected){
				if(idStr.indexOf(idMark)==-1){
					idStr = idStr + rowId + ',';
				}
			}else{
				idStr = idStr.replace(idMark, ',');
			}
			idContainer.data(id_grid_row_selected_row_ids, idStr);
			return idStr;
		}catch(e){
			alert('操作异常,'+e);
			return null;
		}
	};
	
	//获取所有被选中的行id，包括已经翻页
	this.getSelectedRowIds = function (){
		
	};
}

function _grid_goPage(gridContainerId, pageNo){
	_pad_pager_goPage(gridContainerId, pageNo);
}


//处理排序
function _grid_bind_grid_sort_event(sortableTable){
	if(!sortableTable.is('table.table'))
		sortableTable = sortableTable.find('table.table:first');

	if(sortableTable.length==0)
		return;
	
	var containerId = sortableTable.attr('containerId');

	var gridAdvFilterStr = $(containerId).data(_pad_adv_filter_id);
	var lastColumn = '';
	var lastSort = '';
	if(gridAdvFilterStr && gridAdvFilterStr.length>0){
		var advStrs = gridAdvFilterStr.split(',');
		for(var ti = 0;ti<advStrs.length;ti++){
			var advStr = advStrs[ti];
			if(advStr.indexOf('__')==-1){
				var csf = advStr.split('_');
				lastColumn = csf[0];
				lastSort = csf[1];
				break;
			}
		}
	}
	
	sortableTable.each(function(){
		var sortTd = $(this).find('thead td[sColumn]');
		if(sortTd.length==0)
			sortTd = $(this).find('thead th[sColumn]');
		sortTd.each(function(){
			var title = $(this).text();
			var sortColumn = $(this).attr('sColumn');
			var sortLink = $('<a href="javascript:void(0);" class="sort-link"></a>');
			sortLink.attr('sColumn',sortColumn);
			sortLink.attr('sort','');
			sortLink.text(title);
			$(this).html(sortLink);
			
			if(sortColumn == lastColumn){
				sortLink.attr('sort',lastSort);
				_grid_add_grid_sort_icon(sortLink);
			}
			
			//set sort event
			sortLink.bind('click',function(){

				var sort = sortLink.attr('sort');
				if(sort==''){
					sort = "asc";
					sortLink.attr('sort',sort);
				}else if(sort=='asc'){
					sort = "desc";
					sortLink.attr('sort',sort);
				}else{
					sort = '';
					sortLink.attr('sort',sort);
				}
				
				_grid_add_grid_sort_icon($(this));
				
				_grid_set_sort_column_by_grid(containerId,$(this));
				
				_pad_pager_goPage(containerId, 1);
			});
		});
	});
}

function _grid_add_grid_sort_icon(sortLink){
	var sort = sortLink.attr('sort');
	if(sort=='asc'){
		sortLink.addClass('asc');
		sortLink.removeClass('desc');
	}else if(sort=='desc'){
		sortLink.addClass('desc');
		sortLink.removeClass('asc');
	}else{
		sortLink.removeClass('asc');
		sortLink.removeClass('desc');
	}
}

//使用高级搜索的逻辑处理用户点击排序
function _grid_set_sort_column_by_grid(containerId,sortColumn){
	var column = sortColumn.attr('sColumn');
	column = column.replace('_','[xiahuaxian]');
	var sort = sortColumn.attr('sort');
	
	var gridAdvFilterStr = $(containerId).data(_pad_adv_filter_id);
	if(gridAdvFilterStr && gridAdvFilterStr.length>0)
	{
		//忽略其他排序设置
		gridAdvFilterStr = gridAdvFilterStr.replace('_asc_','__');
		gridAdvFilterStr = gridAdvFilterStr.replace('_desc_','__');
		var temp = ',' + gridAdvFilterStr;
		var findKey = ','+column+'_';
		var idx = temp.indexOf(findKey);
		if(idx!=-1){
			var oldStr = ',' + column + '__';
			var newStr = ',' + column + '_' + sort + '_';
			temp = temp.replace(oldStr, newStr);
			gridAdvFilterStr = temp.substring(1);
		}else{
			gridAdvFilterStr = gridAdvFilterStr + ',' + column + '_' + sort + '_';
		}
	}else{
		gridAdvFilterStr = column+'_'+sort+'_';
	}
	$(containerId).data(_pad_adv_filter_id, gridAdvFilterStr);
}

function _grid_add_adv_filter_input(containerId, inputObj){
	var value = inputObj.val();
	var column = inputObj.attr('column');
	if(!column || column=='')
		column = inputObj.attr('name');
	if(!column || column==''){
		alert('搜索条件没有指定栏目名称');
		return;
	}
	
	value = value.replace(',','[douhao]');
	value = value.replace('_','[xiahuaxian]');

	//没有值也应该继续处理，相当于清楚之前的值
	//if((!value && isNaN(value)) || $.trim(value)=='')
	//	return;
	
	var orderBy = inputObj.attr('order_by');
	if(!orderBy)
		orderBy = '';
	var dataType = inputObj.attr('data_type');
	if(!dataType)
		dataType = '';
	var filterType = inputObj.attr('filter_type');
	if(!filterType)
		filterType = '';
	
	if(filterType=='bt'){
		//尝试设置value的正确格式abc!def
		if(value.indexOf(',')!=-1){
			var range = value.split(',');
		}else if(value.indexOf('-')!=-1){
			var range = value.split('-');
		}else{
			return;
		}
		value = $.trim(range[0]) + '!' + $.trim(range[1]);
	}
	
	var advFilterStr = $('#'+containerId).data(_pad_adv_filter_id);
	if(advFilterStr && advFilterStr.length>0){
		if(advFilterStr.substring(0,1)!=',')
			advFilterStr = ',' + advFilterStr;
		
		var columnMark = ',' + column + '_';
		var idx = advFilterStr.indexOf(columnMark);
		if(idx!=-1){
			var tempAFS = advFilterStr + ',';
			var idx2 = tempAFS.indexOf(',',idx+1);
			advFilterStr = advFilterStr.substring(0,idx) + advFilterStr.substring(idx2);
		}
	}else{
		advFilterStr = '';
	}

	var addFilter = ',' + column + '_'+orderBy+'_' + value;
	
	if(dataType!='' || filterType !=''){
		addFilter = addFilter + '_' + dataType + '_' + filterType;
	}
	
	advFilterStr = advFilterStr + addFilter;
	advFilterStr = advFilterStr.substring(1);
	$('#'+containerId).data(_pad_adv_filter_id, advFilterStr);
}



(function ($) {
	$.fn.addGridDropdownAction = function(title, href)
	{
		if(!$(this).is('table.table'))
			return;
		var pagerClass = $(this).attr('id') + "_pager";
		if($('div.'+pagerClass+' ul.dropdown ul.dropdown_action').length>0){
			$('div.'+pagerClass+' ul.dropdown ul.dropdown_action').each(function(){
				$(this).append($('<li><a href="'+href+'">'+title+'</a></li>'));
				var dropdown = $(this).parent().parent();
				var selectedTxt = dropdown.siblings('.selected_txt:first');
				if(dropdown && selectedTxt){
					if(dropdown.is(':hidden') && selectedTxt.is(':visible'))
						dropdown.show();
				}
			});
		}else{
			var linkArr = $(this).data('dropdown_actions');
			if(!linkArr)
				linkArr = new Array();
			linkArr.push({title:title, href:href});
			$(this).data('dropdown_actions',linkArr);
		}
	};
}(window.jQuery));


$(function(){

});
