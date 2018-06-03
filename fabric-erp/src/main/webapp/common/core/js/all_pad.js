//add by wulei 2014-05-16
var _pad_container_page_url = "content_url";
var _pad_page_refresh_target_class = "refresh_target";
//数据列表检索排序字符串保存的键值(值以$.data的方式保存在_current_tab_page_id对应的对象上)
var _pad_adv_filter_id = "_pad_adv_filter_id";
//数据列表检索条件数据
var _pad_search_params_id = "_pad_search_params_id";
//页面初次加载时保存参数
var _pad_page_base_params_id = "_pad_page_base_params_id";
var _pad_grid_page_size = "gridPageSize";
var _pad_grid_page_no = "pageNo";


var _pad_page_array = null;
var _pad_page_refresh_target = null;
var _pad_page_refresh_main_content = false;
var _pad_page_main_content_refresh_url = null;
var _pad_page_main_content_refresh_time = 0;


/**
*@param {string} url 完整的URL地址
*@returns {object} 自定义的对象
*@description 用法示例：
*	var myURL = parseURL('http://abc.com:8080/dir/index.html?id=255&m=hello#top');
*	myURL.file='index.html'
*	myURL.hash= 'top'
*	myURL.host= 'abc.com'
*	myURL.query= '?id=255&m=hello'
*	myURL.params= Object = { id: 255, m: hello }
*	myURL.path= '/dir/index.html'
*	myURL.segments= Array = ['dir', 'index.html']
*	myURL.port= '8080'
*	yURL.protocol= 'http'
*	myURL.source= 'http://abc.com:8080/dir/index.html?id=255&m=hello#top'
*/
function _pad_all_parseURL(url) {
	var a =  document.createElement('a');
	a.href = url;
	return {
		  source: url,
		protocol: a.protocol.replace(':',''),
		    host: a.hostname,
		    port: a.port,
		   query: a.search,
		  params: (function(){
		    var ret = {},
		        seg = a.search.replace(/^\?/,'').split('&'),
		        len = seg.length, i = 0, s;
		    for (;i<len;i++) {
		        if (!seg[i]) { continue; }
		        s = seg[i].split('=');
		        ret[s[0]] = s[1];
		    }
		    return ret;
		  })(),
		  	file: (a.pathname.match(/\/([^\/?#]+)$/i) || [,''])[1],
		    hash: a.hash.replace('#',''),
		    path: a.pathname.replace(/^([^\/])/,'/$1'),
		relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [,''])[1],
		segments: a.pathname.replace(/^\//,'').split('/')
	};
}

function _p_load_grid_page(url,pageContainerId,pageSize){
	if(pageContainerId.substring(0,1)=='#')
		pageContainerId = pageContainerId.substring(1);
	
	if(pageSize && pageSize>0)
		$('#' + pageContainerId).attr(_pad_grid_page_size, pageSize);
	
	_pad_all_loadPage(url,pageContainerId);
}

//给指定元素加载内容
function _p_a_load(url,pageContainerId,keepParams,data,callBack){
	_pad_all_loadPage(url,pageContainerId,keepParams,data,callBack);
}
function _pad_all_loadPage(url,pageContainerId,keepParams,data,callBack){
	if(pageContainerId.substring(0,1)=='#')
		pageContainerId = pageContainerId.substring(1);
	
	var containerObj = $('#' + pageContainerId);

	var initPageSize = containerObj.attr(_pad_grid_page_size);
	if(initPageSize && initPageSize>0){
		//有初始化containerObj 中grid的pageSize
		if(!data || data.length==0){
			data = 'gridPageSize='+initPageSize;
		}else{
			if(typeof(data)=='string'){
				data = data + '&gridPageSize='+initPageSize;
			}else{
				data.gridPageSize = initPageSize;
			}
		}
	}
	
	if(!keepParams)
		_pad_clear_container_old_data(pageContainerId);

	var pageBaseParams = containerObj.data(_pad_page_base_params_id);
	if(!pageBaseParams && data){
		containerObj.data(_pad_page_base_params_id, data);
	}
	
	$.ajax({
		url:url ,
		type: "post",
		data: data,
		cache:false,
		success:function(html){
				
			_pad_add_pageInfo_to_loadPageHtml(html, pageContainerId, url);

			//find any grid and add containerId
			var tempIdx1 = html.indexOf('<table');
			if(tempIdx1!=-1){
				tempIdx1 = tempIdx1 + 6;
				var tempIdx2 = html.indexOf('>',tempIdx1);
				var tempIdx3 = html.indexOf('table',tempIdx1);
				if(tempIdx3!=-1 && tempIdx3< tempIdx2){
					//尝试准确的定位"table.table:first"的table
					html = html.substring(0,tempIdx1) + ' containerId="#'+pageContainerId+'"' + html.substring(tempIdx1);
				}
			}

			containerObj.html(html);
			
			containerObj.trigger('new_content_load');
			if(tempIdx1!=-1){
				//containerObj.trigger('grid_content_load');
				var anyGrid = _pad_findGridByContainerId(pageContainerId);
				_grid_init_table_list(anyGrid);
			}
								
			
			if(callBack){
				callBack(pageContainerId);
			}
		}
	});
}

//处理编辑页面的按键
function _pad_check_update_page_btns(containerObj){
	var editPageBtnContainer = containerObj.find('#orderTitleFixd');
	if(editPageBtnContainer.length>0){
		editPageBtnContainer.find('span.no_line').children().each(function(){
			if($(this).is('a.f_btn')){
				if(_pad_is_link_good($(this))){
					if($(this).is('.edit_back')){
						//如果没有上一级，不显示返回
						if(_pad_page_array && _pad_page_array.length>0)
							$(this).show();
					}else{
						$(this).show();
					}
				}
			}else if($(this).is('div.ms.f_btn')){
				//下拉菜单
				//弹出子链接
				var sublinks = $(this).find('ul a');
				var goodCount = 0;
				sublinks.each(function(){
					if(!_pad_is_link_good($(this)))
						$(this).parent().hide();
					else
						goodCount ++;
				});

				//if(goodCount==0)
				//	$(this).find('ul:first').hide();
				
				//自身链接
				var mylink = $(this).find('a:first');
				if(_pad_is_link_good(mylink) || goodCount>0)
					$(this).addClass('show');
			}
		});
	}
}

function _pad_is_link_good(link){
	var linkAttr = link.attr('href');
	if(!linkAttr || linkAttr=='' || linkAttr=='#' || linkAttr.indexOf('void')!=-1){
		linkAttr = link.attr('onclick');
		if(!linkAttr || linkAttr=='')
			return false;
	}
	return true;
}

function _pad_clear_container_old_data(containerId){
	if(containerId.substring(0,1)!='#')
		containerId = '#' + containerId;
	
	var container = $(containerId);

	container.attr(_pad_container_page_url,'');
	//container.removeAttr(_pad_grid_page_size);
	container.removeAttr(_pad_grid_page_no);

	container.removeData(_pad_adv_filter_id);
	container.removeData(_pad_search_params_id);
	container.removeData(_pad_page_base_params_id);
	
	try{
		_all_gridSearchClear(containerId);
	}catch(e){
		alert(e);
	}
}

function _pad_findGridByContainerId(containerId){
	if(containerId.substring(0,1)!='#')
		containerId = '#' + containerId;
	var containerObj = $(containerId);
	var anyGrid = containerObj.find('table.table:first');
	return anyGrid;
}

function _pad_add_pageInfo_to_loadPageHtml(jqHtml, pageContainerId, url){
	var container = $('#' + pageContainerId);
	container.attr(_pad_container_page_url,url);
}

function _pad_reload_my_container(contentId){
	var container = $('#'+contentId).parent();
	var containerId = container.attr('id');
	var url = container.attr(_pad_container_page_url);

	_pad_all_loadPage(url,containerId,true);
}

function _pad_reload_content_page(){
	var container = $('#'+resource_page_content_container_id);
	var containerId = container.attr('id');
	var url = container.attr(_pad_container_page_url);

	_pad_all_loadPage(url,containerId,true);
}


function _pad_pager_goPage(containerId, pageNo){
	if(containerId.substring(0,1)!='#')
		containerId = '#' + containerId;
	//将新页码添加到信息中
	$(containerId).attr(_pad_grid_page_no,pageNo);
	
	_pad_grid_loadPage(containerId);
	
	//下面注释掉的已经不适用了
	//var reloadUrl = $('#'+containerId).attr(_pad_container_page_url);
	//var url = reloadUrl + '?page=' + pageNo;
	//_pad_all_loadPage(url,containerId);
}

function _pad_grid_search(containerId,params){
	if(!params)
		params = {};
	_pad_grid_loadPage(containerId,params);
}

function _pad_grid_loadPage(containerId,params)
{
	if(containerId.substring(0,1)!='#')
		containerId = '#' + containerId;
	
	//params 是列表上方检索条件的值
	//如果params有效，则判断为点击搜做按键，pageNo条件清空,从新记录检索条件
	if(!params){
		var savedParam = $(containerId).data(_pad_search_params_id);
		if(!savedParam)
			params = {};
		else
			params = savedParam;
	}else{
		//保存检索条件
		$(containerId).data(_pad_search_params_id, params);
		//kill pageNo attribute
		$(containerId).attr(_pad_grid_page_no,'1');
	}
	
	//处理高级搜索、排序（导出）处产生的设定
	var gridAdvFilterStr = $(containerId).data(_pad_adv_filter_id);
	if(gridAdvFilterStr && gridAdvFilterStr.length>0)
	{
		//将params清空，即如果有高级搜索条件，忽略普通搜索
		//vix中不用清空，检索条件都是使用advFilterStr进行的检索
		//params = {};
		
		params['advFilterStr'] = gridAdvFilterStr;
	}
	
	//处理页面加载原始参数，其实可以与search_params合并处理
	var page_base_params = $(containerId).data(_pad_page_base_params_id);

	if(page_base_params){
		params = _pad_mergeJsonObject(page_base_params, params);
	}

	//处理当前pageNo
	var reloadUrl = $(containerId).attr(_pad_container_page_url);
	var pageNo = $(containerId).attr(_pad_grid_page_no);
	var gridPageSize = $(containerId).attr(_pad_grid_page_size);

	if(!pageNo)
		pageNo = 1;
	
	//set pageNo to post attribute 'page'
	params.page=pageNo;
	if(gridPageSize && gridPageSize>0)
		params.gridPageSize = gridPageSize;
	
	_pad_all_loadPage(reloadUrl,containerId,true,params);
}

function _pad_mergeJsonObject(baseData, newData){
	if(!baseData)
		return newData;
	if(!newData)
		return baseData;
	
    var resultJsonObject={};  
    for(var attr in baseData){  
        resultJsonObject[attr]=baseData[attr];  
    }  
    for(var attr in newData){  
        resultJsonObject[attr]=newData[attr];  
    }  

    return resultJsonObject; 
}


//左侧菜单点击处理
function _sidebar_menu_activeMenu(sidebar_menu_activedObj){
	if(sidebar_menu_activedObj.length>0){
		$('#sidebar ul.nav-list').find('li.active').each(function(){
			if(!$(this).hasClass('open'))
				$(this).removeClass('active');
		});
		
		var sidebar_menu_activedParentLi = sidebar_menu_activedObj.parent();
		var sidebar_menu_activedParentUl = sidebar_menu_activedParentLi.parent();
		
		var sidebar_menu_actived_text = sidebar_menu_activedObj.text();
		var sidebar_menu_actived_url = sidebar_menu_activedObj.attr('page');
		
		if(sidebar_menu_activedParentUl.hasClass('submenu')){
			//点击的是二级菜单
			var sidebar_menu_activedUpLevelLi = sidebar_menu_activedParentUl.parent();
			if(!sidebar_menu_activedUpLevelLi.hasClass('open')){
				sidebar_menu_activedParentUl.siblings('a.dropdown-toggle:first').trigger('click');
				sidebar_menu_activedUpLevelLi.addClass('open active');
			}else{
				sidebar_menu_activedUpLevelLi.addClass('active');
			}
			sidebar_menu_activedUpLevelLi.siblings('li.open').each(function(){
				$(this).children('a.dropdown-toggle:first').trigger('click');
				$(this).removeClass('active');
			});
			
			var sidebar_menu_actived_parent_text = sidebar_menu_activedUpLevelLi.find('.menu-text:first').text();

			_breadcrumbs_clear();
			_breadcrumbs_setText(sidebar_menu_actived_parent_text);
			_breadcrumbs_setText(sidebar_menu_actived_text,sidebar_menu_actived_url);
			
		}else{
			//点击的是一级菜单
			sidebar_menu_activedParentLi.siblings('li.open').each(function(){
				$(this).children('a.dropdown-toggle:first').trigger('click');
				$(this).removeClass('active');
			});

			_breadcrumbs_clear();
			_breadcrumbs_setText(resource_text_index_page);
			_breadcrumbs_setText(sidebar_menu_actived_text,sidebar_menu_actived_url);
		}
		sidebar_menu_activedParentLi.addClass('active');

		_breadcrumbs_bindEvent();

		var sidebar_menu_pageUrl = sidebar_menu_activedObj.attr('page');
		if(sidebar_menu_pageUrl!='' && sidebar_menu_pageUrl!='#'){
			_pad_all_loadPage(sidebar_menu_pageUrl,resource_page_content_container_id,false,null,function(pageContainerId){
			});
		}
	}
}

//清楚面包屑内容
function _breadcrumbs_clear(){
	$('#breadcrumbUL').html('');
}

//设定面包屑，根据调用顺序累加名称
function _breadcrumbs_setText(text,textUrl){
	var _breadcrumbs_container = $('#breadcrumbUL');
	var _breadcrumbs_text_li_list = _breadcrumbs_container.children('li');
	var nowIndex = _breadcrumbs_text_li_list.length;

	var _breadcrumbs_html = '<li>';
	
	if(nowIndex==0){
		_breadcrumbs_html += '<i class="icon-home home-icon"></i>';
	}else{
		_breadcrumbs_text_li_list.last().append('<span class="divider"><i class="icon-angle-right arrow-icon"></i></span>');
	}
	
	if(textUrl && textUrl.length>0){
		_breadcrumbs_html += '<a ';
		if(nowIndex>0)
			_breadcrumbs_html += 'class="bc_s_link" ';
		_breadcrumbs_html += ' href="javascript:void(0);" page="' + textUrl + '" >' + text + '</a>';
	}else{
		_breadcrumbs_html += text;
	}

	_breadcrumbs_html += '</li>';
	
	_breadcrumbs_container.append(_breadcrumbs_html);
}

function _breadcrumbs_bindEvent(){
	$('#breadcrumbs a.bc_s_link').bind('click',function(){
		var bc_url = $(this).attr('page');
		_pad_all_loadPage(bc_url,resource_page_content_container_id);
	});
}

function _show_page_notice(title,text,time){
	if(!time)
		time="1500";
	$.gritter.add({
		title: title,
		text: text,
//		image: $path_assets+'/avatars/avatar.png',
		sticky: false,
		time: time,
		class_name: 'gritter-light'
	});
}

function _show_page_error(title,text,time){
	if(!time)
		time="4000";
	$.gritter.add({
		title: title,
		text: text,
//		image: $path_assets+'/avatars/avatar.png',
		sticky: false,
		time: time,
		class_name: 'gritter-light gritter-error'
	});
}


//添加input事件，只允许输入整数和小数
function _pad_addInputCheckNumEvent(objId){
	if(objId.substring(0,1)!='#')
		objId = '#' + objId;
	$(objId).keydown(function(event) {
      var keyCode = event.which;
      var oldVal = $(this).val();
      if(oldVal.indexOf('.')>0 && keyCode == 190)
      	return false;
      if(oldVal=='' && keyCode == 190)
      	return false;
      if (keyCode == 46 || keyCode == 8 || keyCode == 190 || (keyCode >= 48 && keyCode <= 57) || (keyCode >= 96 && keyCode <= 105) || keyCode == 110) {
          return true;
      }else {
			return false;
		}
  }).focus(function() {
      this.style.imeMode = 'disabled';
  });
}

//使用nestable样式添加tep_book_leibie
function genNestableEle(jsonList,idStr){
	var listObj = $('<ol class="dd-list"></ol>');
	for(var i=0;i<jsonList.length;i++){
		var data = jsonList[i];
		
		var itemLi = $('<li class="dd-item"></li>');
		itemLi.attr('data-id',data.id);
		
		var item = $('<div class="dd2-content"></div>');
		var myId = 'id' + idMark;
		item.attr('id',myId);
		var myIdLevel = idStr + myId;
		idMark ++;
		item.attr('idLevel',myIdLevel);
		item.text(data.title);
		
		itemLi.append(item);
		if(data.children && data.children.length>0){
			itemLi.append(genNestableEle(data.children, myIdLevel + '-'));
		}
		
		listObj.append(itemLi);
	}

	return listObj;
}


function switchFormToTextView(containerSelector){
	_pad_switchFormToTextView(containerSelector);
}

function _pad_switchFormToTextView(containerSelector){	
	$(containerSelector).find("label.control-label").each(function(){
		var textView = $('<span class="view_text">：</span>');;
		$(this).append(textView);
	});
	
	$(containerSelector).find("input[type='text']").each(function(){
		var parent = $(this).parent();
		var textView = $('<span class="view_text"></span>');
		textView.html($(this).val());
		parent.append(textView);
	});
	
	$(containerSelector).find("input[type='radio']").each(function(){
		if($(this).attr('checked')&&$(this).attr('checked')=='checked'){
			var parent = $(this).parent();
			var textView = $('<span class="view_text"></span>');
			textView.html($(this).val());
			parent.append(textView);
		}
	});


	$(containerSelector).find("select option:selected").each(function(){
		var parent = $(this).parent().parent();
		var textView = $('<span class="view_text"></span>');
		textView.html($(this).html());
		parent.append(textView);
	});

	$(containerSelector).find("div.wysiwyg-editor").each(function(){
		var parent = $(this).parent();
		var textView = $('<span class="view_text"></span>');
		textView.html($(this).html());
		parent.append(textView);
	});
	
	
}

function openBlank(action,data){
    var form = $("<form/>").attr('action',action).attr('method','post');
    form.attr('target','_blank');
    var input = '';
    $.each(data, function(i,n){
        input += '<input type="hidden" name="'+ i +'" value="'+ n +'" />';
    });
    form.append(input).appendTo("body").css('display','none').submit();
    form.remove();
}





function _pad_viewSchool(id,name,type){
	var title = name;
	if(!type)
		type =0;
	if(type==1)
		title = name + ' 学校基本信息';
	else if(type==12)
		title = name + ' 院系信息';
	else if(type==2)
		title = name + ' 专业及招生信息';
	else if(type==3)
		title = name + ' 课程与教材信息';
	else if(type==4)
		title = name + ' 教师信息';
	else
		title = '院校信息';
	_tabShow(title,resource_padall_tep_url_base+'/tep/tepSchoolAction!yxSchoolInfo.action?schoolId='+id+'&infoType='+type,'p_view_sc_tab'+id);
}

function _pad_viewTeacher(id,name,type,schoolId){
	if(!type)
		type = 0;
	var sturl = resource_padall_tep_url_base+'/tep/tepTeacherAction!jsTeacherMgrAdd.action?teacherId='+id+'&infoType='+type;
	if(schoolId && schoolId>0)
		sturl = sturl + '&schoolId='+schoolId;
	_tabShow(name,sturl,'p_tab_add_new_teacher');
}

//查看工作日志记录
function _pad_viewVisitRecord(targetId, targetType, name){
	var title = '记录';
	if(targetType==1)
		title = '老师拜访记录 - '+name;
	else if(targetType==2)
		title = '卖场商合作记录 - '+name;
	else if(targetType==3)
		title = '经销商合作记录 - '+name;
	else if(targetType==4)
		title = '旅游局合作记录 - '+name;
	
	_tabShow(title,'${vix}/tep/tepWorkAction!viewDailyVisitListContent.action?targetType='+targetType+'&targetId='+targetId,'p_visit_workdaily');
}
//查看工作日志详细信息
function _pad_viewWorkDailyRecord(id){
	_tabShow('工作日报','${vix}/tep/tepWorkAction!rbWorkDailyAdd.action?dailyId='+id,'p_add_new_tab');
}


function exportGridData(containerId, exportType){
	if(containerId.substring(0,1)!='#')
		containerId = '#' +containerId;
	var advFilterStr = $(containerId).data(_pad_adv_filter_id);

	var url = resource_padall_tep_url_base + '/tep/tepWorkAction!exoprtExcelData.action';

	openBlank(url,{advFilterStr:advFilterStr,exportType:exportType});
}



function _pad_loadSubSchoolForTeacher(selectObj,relSchoolId){
	selectObj.html('');
	var url = resource_padall_tep_url_base + '/tep/tepSchoolAction!loadSubSchoolData.action?schoolId='+relSchoolId;
	$.ajax({
		url:url ,
		type: "post",
		data: 'json',
		cache:false,
		success:function(json){
			var dataList = json.rows;
			if(dataList && dataList.length>0){
				$.each(dataList,function(idx,item){
					var option = $('<option></option>');
					option.attr('value',item.id);
					option.text(item.mingcheng);
					selectObj.append(option);
				});
			}
		}
	});
}

function _pad_loadSubKd(parentSel, subSel, subVal, callBackFunc)
{
	if(parentSel.substring(0,1)!='#')
		parentSel = '#'+parentSel;
	if(subSel.substring(0,1)!='#')
		subSel = '#'+subSel;
	
	var parentObj = $(parentSel);
	var subObj = $(subSel);
	
	subObj.html('');
	var pid = parentObj.find('option:selected').attr('kdId');
	if(!pid || pid=='')
		return;
	
	var url = resource_padall_tep_url_base + '/tep/tepWorkAction!getSubKeyDataList.action?kdPid='+pid;
	$.ajax({
		url:url ,
		type: "post",
		data: 'json',
		cache:false,
		success:function(json){
			var dataList = json;

			if(dataList && dataList.length>0){
				if(subObj.is('.default_empty')){
					var option = $('<option>&nbsp;</option>');
					option.attr('value','');
					subObj.append(option);
				}
				$.each(dataList,function(idx,item){
					var option = $('<option></option>');
					option.attr('value',item.dataValue);
					option.text(item.dataTitle);
					option.attr('kdId',item.id);
					subObj.append(option);
				});
				if(subVal && subVal.length>0){
					subObj.val(subVal);
				}else{
					var initVal = subObj.attr('initVal');
					if(initVal && initVal.length>0){
						subObj.val(initVal);
					}
				}
			}
			subObj.attr('initVal','');
			subObj.trigger('change');
			
			if(callBackFunc)
				callBackFunc(subObj.val());
		}
	});
}



/**
 * 将txtObj内容同步到hiddenObj
 * 目前只测试了div（contentEditable=true）
 * 可设置绑定字数限制
 */
function _pad_bind_div_text_limit_event(txtObj, hiddenObj, lengthLimit, callBack){
	if(!txtObj || txtObj.length==0){
		return;
	}
	
	if(lengthLimit && lengthLimit>0)
		txtObj.attr('maxlength',lengthLimit);
	else{
		var tempLimit = txtObj.attr('maxlength');
		if(!tempLimit || tempLimit=='')
			txtObj.attr('maxlength',0);
	}
	
	if(!hiddenObj || hiddenObj.length==0 || !hiddenObj.is('input[type="hidden"]')){
		var hiddenPartner = $('<input type="hidden" id="_txt_limit_content_partner" />');
		txtObj.attr('hidden-obj-id','_txt_limit_content_partner');
		txtObj.parent().append(hiddenPartner);
	}else{
		if(hiddenObj.attr('id')=='')
			hiddenObj.attr('id','_txt_limit_content_partner')
		txtObj.attr('hidden-obj-id',hiddenObj.attr('id'));
	}
	
	txtObj.bind('DOMSubtreeModified',function(){
		//DOMNodeInserted DOMSubtreeModified DOMNodeRemoved
		var textRemain = _pad_div_text_limit_check($(this));
		
		if(callBack)
			callBack(textRemain)
	});
	
	_pad_div_text_limit_check(txtObj);
}
function _pad_div_text_limit_check(txtObj){
	var maxLimit = txtObj.attr('maxlength');
	var hiddenObjId = txtObj.attr('hidden-obj-id');
	var hiddenObj = $('#'+hiddenObjId);
	
	if(maxLimit && maxLimit>0){
		var textCount = _pad_div_text_limit_text_count(txtObj);
		var textRemain = maxLimit - textCount;
		//alert(maxLimit+'   '+textCount)
		if(textRemain<0){
			var hiddenContent = hiddenObj.val();
			var hiddenTextCount = _pad_div_text_limit_text_count($('<div>'+hiddenContent+'</div>'));
			
			var oriOverflow = txtObj.attr('oriOverflow');
			//如果初始值已经超出范围
			if(oriOverflow && oriOverflow=='1'){
				if(textCount <= hiddenTextCount){	
					hiddenObj.val(txtObj.html());
				}else{
					txtObj.html(hiddenContent);
					textCount = _pad_div_text_limit_text_count(txtObj);
				}
			}else{
				//隐藏值（初始值）已经是超出内容长度限制
				if(hiddenTextCount>maxLimit){
					//本意是不截取，扔设定为超长内容，然后可以删，不能加，但是有光标定位问题，无法正常删除，暂时使用截取
					hiddenContent = hiddenContent.substring(0,maxLimit);
					//txtObj.attr('oriOverflow','1');
				}
				txtObj.html(hiddenContent);
				textCount = _pad_div_text_limit_text_count(txtObj);
			}
			
			textRemain = maxLimit - textCount;
			
			if(textRemain<0)
				textRemain = 0;
		}else{
			txtObj.attr('oriOverflow','0');
			hiddenObj.val(txtObj.html());
		}
		
		return textRemain;
	}else{
		hiddenObj.val(txtObj.html());
		return 0;
	}
}
function _pad_div_text_limit_text_count(obj){
	var imgCount = obj.find('img').length;
	var textCount = obj.text().length;
	return imgCount + textCount;
}


function _pad_refreshTreeSelectedNoteDelay(treeDomId){
	setTimeout(function(){
		_pad_refreshTreeSelectedNote(treeDomId);
	},700);
}

function _pad_refreshTreeSelectedNote(treeDomId){
	if(!treeDomId)
		treeDomId = "tree_root";
	var myTree = $.fn.zTree.getZTreeObj(treeDomId);
	if(!myTree)
		return;
	var selectedNode = myTree.getNodeByTId($("#selectIdTreeId").val());
	if(!selectedNode){
		myTree.reAsyncChildNodes(null,"refresh");
	}else{
		if(selectedNode.isParent){
			myTree.reAsyncChildNodes(selectedNode,"refresh");
		}else{
			var parentTreeId = selectedNode.parentTId;
			if(parentTreeId){
				selectedNode = myTree.getNodeByTId(parentTreeId);
				myTree.reAsyncChildNodes(selectedNode,"refresh");
			}else{
				myTree.reAsyncChildNodes(null,"refresh");
			}
		}
	}
}


function _pad_page_view_push(url,data,callback){
	//暂时只用一级，js无法取出，仍在页面中
	if(_pad_page_array==null)
		_pad_page_array = new Array();
	
	var old = $('#mainContent');
	var idx = _pad_page_array.length + 1;
	var newId ='page_content_' + idx;
	old.hide();
	old.attr('id',newId);
	old.addClass('old_main_content');
	_pad_page_array.push(newId);
	
	var breadCrumb = old.find('#breadCrumb');
	var bcHtml = breadCrumb.html();
	
	var newContent = $('<div id="mainContent"></div>');
	old.before(newContent);

	_pad_all_loadPage(url,'mainContent',true,data,function(){
		var newBreadCrumb = $('#mainContent #breadCrumb');
		newBreadCrumb.html(bcHtml);
		if(callback){
			callback('mainContent');
		}
	});
}

function _pad_page_view_back(){
	if(_pad_page_array==null)
		return;
	var oldId = _pad_page_array.pop();
	
	if(oldId && oldId.length>0){
		$('#mainContent').remove();
		var oldContent = $('#'+oldId);
		oldContent.attr('id','mainContent');
		oldContent.removeClass('old_main_content');
		
		//暂时每次都刷新grid
		var anyGrid = _pad_findGridByContainerId('mainContent');
		if(anyGrid && anyGrid.length>0){
			var gridContainerId = anyGrid.attr('containerId');
			if(gridContainerId && gridContainerId.length>0){

				setTimeout('_pad_grid_loadPage("'+gridContainerId+'")', 1000);
				//_pad_grid_loadPage(gridContainerId);
			}
		}
		
		oldContent.show();
	}
}

function _pad_page_view_clear(){
	if(_pad_page_array!=null){
		_pad_page_array = null;
	}
	_pad_page_refresh_target = null;
	_pad_page_main_content_refresh_url = null;
	_pad_page_refresh_main_content = false;
	$('.old_main_content').remove();
}

function _pad_handle_document_key_event(keyCode, event){
	//block F5 to page target
    if (keyCode == 116) {
    	//refresh all for ctrl+F5
    	if(event.ctrlKey)
    		return;
    	//refresh all for press twice in 5s
    	if(_pad_page_main_content_refresh_time && _pad_page_main_content_refresh_time!=0){
        	var timeNow = new Date();
        	var refreshDiff = timeNow.getTime() - _pad_page_main_content_refresh_time;
        	if(refreshDiff<5000){
        		return;
        	}
    	}
    	
    	var blockRefresh = false;
    	if(_pad_page_main_content_refresh_url && _pad_page_main_content_refresh_url.length>0){
    		loadContent(_pad_page_main_content_refresh_url);
        	var timeNow = new Date();
    		_pad_page_main_content_refresh_time = timeNow.getTime();
    		blockRefresh = true;
    		$('.asyncbox_normal').each(function(){
    			$.close($(this).attr('id'));
    		});
    	}else if(_pad_page_refresh_target!=null){
    		if($('#'+_pad_page_refresh_target).length>0){
        		if($('#'+_pad_page_refresh_target).attr(_pad_container_page_url)!=null){
            		blockRefresh = true;
        			_pad_grid_loadPage(_pad_page_refresh_target);
        		}
    		}
    	}
    	
    	if(blockRefresh){
    		//以下是禁止f5
            if(event.preventDefault) {
            	event.preventDefault();
            } else {
            	event.keyCode = 0;
            	event.returnValue = false;
            }
    	}
    }
}

function _pad_check_page_refresh_target(container){	
	if(container && container.length>0){
		var refreshTarget = container.find('.'+_pad_page_refresh_target_class+':first');
		if(refreshTarget.length>0){
			var rtId = refreshTarget.attr('id');
			_pad_page_refresh_target = rtId;
		}
	}
}




var _pad_callback_after_save = null;
function _pad_check_entity_saved_id(saveFunc, callBack, idColumn){
	if(!idColumn || idColumn=='')
		idColumn = 'id';
	var baseId = $('#'+idColumn).val();
	if(!baseId || baseId==''){
		_pad_callback_after_save = callBack;
		
		_pad_execute_function(saveFunc);
		
		return false;
	}
	return true;
}

function _pad_execute_after_save(){
	var ret = _pad_execute_function(_pad_callback_after_save);
	_pad_callback_after_save = null;
	return ret;
}

function _pad_execute_function(func){
	if(typeof func == "function"){
		func();
		return true;
	}else if(typeof func == "string"){
		eval(func);
		return true;
	}
	return false;
}
