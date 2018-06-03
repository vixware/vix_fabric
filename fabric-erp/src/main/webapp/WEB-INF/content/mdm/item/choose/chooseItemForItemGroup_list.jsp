<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
var name = "";
function loadName(){
	name = $('#nameS').val();
	name=Url.encode(name);
	name=Url.encode(name);
}
function searchForRightContent(){
	loadName();
	pager("start","${vix}/mdm/item/itemChooseAction!goListContentForItemGroup.action?chooseType=${chooseType}&name="+name+"&categoryId="+$("#selectId").val(),'chooseForItemGroup');
}
loadName();
//载入分页列表数据
pager("start","${vix}/mdm/item/itemChooseAction!goListContentForItemGroup.action?chooseType=${chooseType}&name="+name+"&categoryId="+$("#selectId").val(),'chooseForItemGroup');
bindSwitch();
bindSearch();
$.returnValue = "";
var itemId = "";
function chkChange(chk,id,code,name,price){
	 if(chk.checked){
		 if($('#chooseType').val() == 'multi'){
			 $.returnValue = $.returnValue + "," + id;
		 }else{
		 	$.returnValue = id + "," + code+ "," + name+ ',' + price;
		 }
	 }else{
		 if($('#chooseType').val() == 'multi'){
			 $.returnValue = $.returnValue.replace(","+id,"");
		 }
	 }
}
function currentPagerForItemChoose(tag){
	loadName();
	pager(tag,"${vix}/mdm/item/itemChooseAction!goListContentForItemGroup.action?chooseType=${chooseType}&name="+name+"&categoryId="+$("#selectId").val(),'chooseForItemGroup');
}
</script>
<input type="hidden" id="chooseType" name="chooseType" value="${chooseType}" />
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="${vix}/common/img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<div>
			<label><s:text name="cmn_name" /><input type="text" class="int" id="nameS"></label> <label><input type="button" value="<s:text name='cmn_search'/>" class="btn" onclick="searchForRightContent();" /></label>
		</div>
	</div>
	<!-- c_head -->
	<div class="box" style="height: 455px;">
		<div id="left" class="ui-resizable" style="width: 180px; height: 400px;">
			<div class="left_content">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();">根目录</a>
				</div>
				<div id="chooseItemTree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTreeChooseItem;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/mdm/item/itemCatalogAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					pager("start","${vix}/mdm/item/itemChooseAction!goListContentForItemGroup.action?chooseType=${chooseType}&name="+name+"&categoryId="+$("#selectId").val(),'chooseForItemGroup');
				}
				zTreeChooseItem = $.fn.zTree.init($("#chooseItemTree_root"), setting);
				//-->
			</script>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
		<!-- left -->
		<div id="right">
			<div class="right_content" id="a1">
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPagerForItemChoose('start');"></a></span> <span><a class="previous" onclick="currentPagerForItemChoose('previous');"></a></span> <em>(<b class="chooseForItemGroupInfo"></b> <s:text name='cmn_to' /> <b class="chooseForItemGroupTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPagerForItemChoose('next');"></a></span> <span><a class="end" onclick="currentPagerForItemChoose('end');"></a></span>
					</div>
				</div>
				<div id="chooseForItemGroup" style="overflow-x: auto; overflow-y: hidden; width: 100%;"></div>
				<div class="pagelist drop">
					<div>
						<span><a class="start" onclick="currentPagerForItemChoose('start');"></a></span> <span><a class="previous" onclick="currentPagerForItemChoose('previous');"></a></span> <em>(<b class="chooseForItemGroupInfo"></b> <s:text name='cmn_to' /> <b class="chooseForItemGroupTotalCount"></b>)
						</em> <span><a class="next" onclick="currentPagerForItemChoose('next');"></a></span> <span><a class="end" onclick="currentPagerForItemChoose('end');"></a></span>
					</div>
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