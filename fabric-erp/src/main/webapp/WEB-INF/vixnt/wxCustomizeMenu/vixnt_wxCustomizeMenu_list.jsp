<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-list-alt fa-fw "></i> 微信管理 <span>> 自定义菜单</span>
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12 col-md-12">
			<div class="well">
				<div class="row">
					<div class="col-xs-4 col-sm-4">
						<div class="menu_preview_area">
							<div class="mobile_menu_preview">
								<div class="mobile_hd tc">中盛盈创</div>
								<div class="mobile_bd">
									<ul class="pre_menu_list grid_line ui-sortable ui-sortable-disabled" id="menuList">
										<c:choose>
											<c:when test="${firstWxpMenuList != null && fn:length(firstWxpMenuList) > 0}">
												<c:forEach items="${firstWxpMenuList}" var="firstMenu" varStatus="s">
													<li	<c:if test="${fn:length(firstWxpMenuList) == 3}">class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of3"</c:if> 
														<c:if test="${fn:length(firstWxpMenuList) == 2}">class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of3"</c:if>
													 	<c:if test="${fn:length(firstWxpMenuList) == 1}">class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of2"</c:if>>
														<a href="javascript:void(0);" class="pre_menu_link" draggable="false" onclick="wxpMenuListContent('${firstMenu.id}','first',this,null)"> 
															<!-- <i class="icon_menu_dot js_icon_menu_dot dn"></i> 
															<i class="icon20_common sort_gray"></i> --> 
															<span class="js_l1Title" id="name_${firstMenu.id }">${firstMenu.title}</span>
														</a>
														<div class="sub_pre_menu_box js_l2TitleBox">
															<ul class="sub_pre_menu_list">
																<c:if test="${firstMenu.subMenus != null && fn:length(firstMenu.subMenus) > 0}">
																	<c:forEach items="${firstMenu.subMenus}" var="secondMenu" varStatus="ss">
																		<li class="jslevel2" onclick="wxpMenuListContent('${secondMenu.id }','second',this,'${firstMenu.id}');">
																			<a href="javascript:void(0);" class="jsSubView" draggable="false">
																				<span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon20_common sort_gray"></i><span class="js_l2Title">${secondMenu.title}</span></span>
																			</a>
																		</li>
																	</c:forEach>
																</c:if>
																<c:if test="${fn:length(firstMenu.subMenus) != 5}">
																	<li class="js_addMenuBox" data="${fn:length(firstMenu.subMenus)}" onclick="addNewWxpMenu('second','${firstMenu.menuKey}',this,'${firstMenu.id}')"><a href="javascript:void(0);" class="jsSubView js_addL2Btn" title="最多添加5个子菜单" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon14_menu_add"></i></span></a></li>
																</c:if>
															</ul>
															<i class="arrow arrow_out"></i> <i class="arrow arrow_in"></i>
														</div>
													</li>
												</c:forEach>
												<c:if test="${fn:length(firstWxpMenuList) != 3}">
						                        	<li <c:if test="${fn:length(firstWxpMenuList) == 2}">class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of3"</c:if>
					                        			<c:if test="${fn:length(firstWxpMenuList) == 1}">class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of2"</c:if>
					                        			onclick="addNewWxpMenu('first',null,this,null)" data-count="${fn:length(firstWxpMenuList)}">
														<a href="javascript:void(0);" class="pre_menu_link" draggable="false">
								                           	<i class="icon14_menu_add"></i>
								                        </a>
							                        </li>
							                    </c:if>
											</c:when>
											<c:otherwise>
												<li class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled current" style="width: 100%;" id="empty_menu">
													<a href="javascript:void(0);" class="pre_menu_link" draggable="false">
							                           	<i class="icon14_menu_add"></i>
							                            <span class="js_l1Title">添加菜单</span>
							                        </a>
						                        </li>
						                        <li class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of2 current" id="menu_1" style="display: none;">
							                        <a href="javascript:void(0);" class="pre_menu_link" draggable="false" onclick="wxpMenuListContent('','first',null,this,null)">
							                            <span class="js_l1Title">菜单名称</span>
							                        </a>
							                        <div class="sub_pre_menu_box js_l2TitleBox" style="display:block;">
							                            <ul class="sub_pre_menu_list">
							                                <li class="js_addMenuBox" data="0" onclick="addNewWxpMenuNotice()">
							                                	<a href="javascript:void(0);" class="jsSubView js_addL2Btn" title="最多添加5个子菜单" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon14_menu_add"></i></span></a>
							                                </li>
							                            </ul>
							                            <i class="arrow arrow_out"></i>
							                            <i class="arrow arrow_in"></i>
							                        </div>
							                    </li>
						                        <li class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of2" id="menu_add" style="display: none;"
						                        	onclick="addNewWxpMenuNotice()" data-count="1">
													<a href="javascript:void(0);" class="pre_menu_link" draggable="false">
							                           	<i class="icon14_menu_add"></i>
							                        </a>
						                        </li>
											</c:otherwise>
										</c:choose>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-8 col-sm-8" id="menu_row_div"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
    $(function(){
    	$('#menuList .jsMenu:first').children('a').click();
    	$("#empty_menu").click(function(){
    		$(this).hide();
    		$("#menu_add").show();
    		$("#menu_1").show();
    		wxpMenuListContent('','first',$("#menu_1"),null);
    	});
    });
    function wxpMenuListContent(id,type,obj,parentId){
    	$.ajax({
    		url : "${nvix}/nvixnt/wx/wxCustomizeMenuAction!wxpMenuListContent.action",
    		data : {
    			"type" : type,
    			"id" : id,
    			"parentId" : parentId
    		},
    		cache : false,
    		success : function(html){
    			$("#menu_row_div").html(html);
    			if(typeof obj === 'object'){
        			obj = $(obj);
        		}
        		jsMenu(obj,type);
    		}
    	})
    }
    
    function addNewWxpMenuNotice(){
    	layer.alert('您已有一个编辑中的菜单，请先完善');
    }
    function addNewWxpMenu(type,menuKey,obj,parentId){
    	if(type == 'first'){
    		$(obj).siblings(".jsMenu").removeClass("current");
    		$(obj).siblings(".jsMenu").removeClass("size1of2").addClass("size1of3");
    		$(obj).siblings(".jsMenu").children(".sub_pre_menu_box").hide();
    		if($(obj).attr("data-count") == 2){
    			$(obj).hide();
    		}else{
    			$(obj).removeClass("size1of2").addClass("size1of3");
    			$(obj).attr("data-count",2)
    			$(obj).attr("onclick","addNewWxpMenuNotice()");
    		}
    		var l = $('<li class="jsMenu pre_menu_item grid_item jslevel1 ui-sortable ui-sortable-disabled size1of3 current"></li>');
    		var a =  $('<a href="javascript:void(0);" class="pre_menu_link" draggable="false"  onclick="wxpMenuListContent(\'\',\'first\',this,null)"></a>');
    		var span = '<span class="js_l1Title">菜单名称</span>';
    		var div =  $('<div class="sub_pre_menu_box js_l2TitleBox" style="display: block;"></div>');
    		var ul = $('<ul class="sub_pre_menu_list"></ul>');
    		var li = '<li class="js_addMenuBox" data="0" onclick="addNewWxpMenuNotice()"><a href="javascript:void(0);" class="jsSubView js_addL2Btn" title="最多添加5个子菜单" draggable="false"><span class="sub_pre_menu_inner js_sub_pre_menu_inner"><i class="icon14_menu_add"></i></span></a></li>';
    		var i = '<i class="arrow arrow_out"></i><i class="arrow arrow_in"></i>';
    		ul.append(li);
    		div.append(ul).append(i);
    		a.append(span);
    		l.append(a);
    		l.append(div);
    		l.insertBefore($(obj));
    		wxpMenuListContent('',type,l,parentId);
    	}else if(menuKey == ''){
    		var li = $('<li class="jslevel2"  onclick="wxpMenuListContent(\'\',\'second\',this,\''+parentId+'\')"></li>');
    		var a =  $('<a href="javascript:void(0);" class="jsSubView" draggable="false"></a>');
    		var span =  $('<span class="sub_pre_menu_inner js_sub_pre_menu_inner"></span>');
    		var content = '<i class="icon20_common sort_gray"></i><span class="js_l2Title">子菜单名称</span>';
    		span.append(content);
    		a.append(span);
    		li.append(a);
    		li.insertBefore($(obj));
    		var data = $(obj).attr("data");
    		data = parseInt(data) + 1;
    		if(parseInt(data) > 4){
    			$(obj).hide();
    		}
    		$(obj).attr("data",data)
    		wxpMenuListContent('',type,li,parentId);
    	}else{
    		layer.alert('操作提示','清除一级菜单的内容，才可添加子菜单');
    	}
    }
    
    function jsMenu(obj,type){
    	if(!obj.hasClass("current")){
    		if(type == 'first'){
    			obj = obj.parent('.jsMenu')
    			obj.addClass("current").siblings(".jsMenu").removeClass("current");
    			obj.children(".jslevel2").removeClass("current");
    			obj.children(".sub_pre_menu_box").show();
    			obj.siblings(".jsMenu").children(".sub_pre_menu_box").hide();
    			$(".jslevel2").each(function(){
    				$(this).removeClass("current");
    			});
    		}else{
    			obj.addClass("current").siblings(".jslevel2").removeClass("current");
    			if(obj.parents(".jsMenu").hasClass("current")){
    				obj.parents(".jsMenu").removeClass("current");
    			}
    		}
    	}
    }
</script>
	