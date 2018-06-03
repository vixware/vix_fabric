<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
//面包屑
if($('.sub_menu').length)
{
	$("#breadCrumb").jBreadCrumb();
}
function itemSave(tag){
	debugger;
	_head_loading(true);
	$(".jd li a").each(function(i){
		if($(this).attr("class") == 'current'){
			var id = $(this).attr('id');
			if(id == 'oneTag'){
				if($('#one').validationEngine('validate')){
					$(this).click();
					if(tag == '1'){
						saveOrUpdate(0,0)
					}else{
						loadContent('${vix}/mdm/item/itemAction!goList.action?menuId=menuItem');
					}
				}
			}
			if(id == 'twoTag'){
				if($('#two').validationEngine('validate')){
					$(this).click();
					if(tag == '1'){
						saveOrUpdate(0,0)
					}else{
						loadContent('${vix}/mdm/item/itemAction!goList.action?menuId=menuItem');
					}
				}
			}
			if(id == 'threeTag'){
				if($('#three').validationEngine('validate')){
					$(this).click();
					if(tag == '1'){
						saveOrUpdate(0,0)
					}else{
						loadContent('${vix}/mdm/item/itemAction!goList.action?menuId=menuItem');
					}
				}
			}
			if(id == 'fourTag'){
				if($('#four').validationEngine('validate')){
					$(this).click();
					if(tag == '1'){
						saveOrUpdate(0,0)
					}else{
						loadContent('${vix}/mdm/item/itemAction!goList.action?menuId=menuItem');
					}
				}
			}
		}
	});
	_head_loading(false);
}
loadItemContent('one');
function tabSwitch(href,tag){
	_head_loading(true);
	var currentTag = $("#currentTag").val();
	if(currentTag == 'one'){
		if($('#one').validationEngine('validate')){
			saveOrUpdateItemTabOne(function(){
				$(".jd li a").each(function(i){
					if($(this).attr("class") == 'current'){
						$(this).attr("class","");
					}
				});
				$(href).attr("class","current");
				$("#currentTag").val(tag);
				loadItemContent(tag);
			});
		}else{
			return false;
		}
	}
	if(currentTag == 'two'){
		if($("#id").val() > 0 && $('#two').validationEngine('validate')){
			saveOrUpdateItemTabTwo(function(){
				$(".jd li a").each(function(i){
					if($(this).attr("class") == 'current'){
						$(this).attr("class","");
					}
				});
				$(href).attr("class","current");
				$("#currentTag").val(tag);
				loadItemContent(tag);
			});
		}else{
			return false;
		}
	}
	if(currentTag == 'three'){
		if($("#id").val() > 0 && $('#three').validationEngine('validate')){
			saveOrUpdateItemTabThree(function(){
				$(".jd li a").each(function(i){
					if($(this).attr("class") == 'current'){
						$(this).attr("class","");
					}
				});
				$(href).attr("class","current");
				$("#currentTag").val(tag);
				loadItemContent(tag);
			});
		}else{
			return false;
		}
	}
	if(currentTag == 'four'){
		if($("#id").val() > 0 && $('#four').validationEngine('validate')){
			saveOrUpdateItemTabFour(function(){
				$(".jd li a").each(function(i){
					if($(this).attr("class") == 'current'){
						$(this).attr("class","");
					}
				});
				$(href).attr("class","current");
				$("#currentTag").val(tag);
				loadItemContent(tag);
			});
		}else{
			return false;
		}
	}
	_head_loading(false);
}

function loadItemContent(tag){
	var url = '${vix}/mdm/item/';
	if(tag == 'one'){
		url += 'itemAction!goSaveOrUpdateItemTabOne.action?id='+$("#id").val();
	}
	if(tag == 'two'){
		url += 'itemAction!goSaveOrUpdateItemTabTwo.action?id='+$("#id").val();
	}
	if(tag == 'three'){
		url += 'itemAction!goSaveOrUpdateItemTabThree.action?id='+$("#id").val();
	}
	if(tag == 'four'){
		url += 'itemAction!goSaveOrUpdateItemTabFour.action?id='+$("#id").val();
	}
	$.ajax({
		  url:url ,
		  cache: false,
		  success: function(html){
			  $("#itemContent").html(html);
		  }
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/mdm/item.png" alt="" />主数据</a></li>
				<li><a href="#">主数据管理</a></li>
				<li><a href="#">主数据维护</a></li>
				<li><a href="#" onclick="loadContent('${vix}/mdm/item/itemAction!goList.action');">${vv:varView('vix_mdm_item')}</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<span class="no_line"> <a onclick="itemSave('0');" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a onclick="itemSave('1');" href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
					onclick="loadContent('${vix}/mdm/item/itemAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
				</span> <strong> <b> <s:if test="item.name != null">${item.name}</s:if> <s:else>新增${vv:varView('vix_mdm_item')}</s:else>
				</b> <i></i>
				</strong>
			</dt>
			<dd class="clearfix">
				<input type="hidden" id="currentTag" name="currentTag" value="one">
				<ul class="clearfix jd jd6">
					<li style="width: 140px;"><a id="oneTag" href="#" onclick="tabSwitch(this,'one');" class="current">基本信息<span></span></a></li>
					<li style="width: 230px;"><a id="twoTag" href="#" onclick="tabSwitch(this,'two');">供应链(采购、销售、库存)<span></span></a></li>
					<li style="width: 120px;"><a id="threeTag" href="#" onclick="tabSwitch(this,'three');">财务<span></span></a></li>
					<li style="width: 230px;"><a id="fourTag" href="#" onclick="tabSwitch(this,'four');">生产(MRP, 计划、质量)<span></span></a></li>
				</ul>
				<input id="id" name="item.id" value="${item.id}" type="hidden" />
				<div id="itemContent"></div>
			</dd>
		</dl>
	</div>
</div>
