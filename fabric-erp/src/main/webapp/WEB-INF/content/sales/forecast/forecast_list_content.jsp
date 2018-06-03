<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
//list_check
$(".list_check").bind('mouseover',function(){
	$(".list_check").addClass('posr');
	$(".list_check ul").css('display','block');
}).bind('mouseleave',function(){
	$(".list_check").removeClass('posr');
	$(".list_check ul").css('display','none');
});
$(".untitled").hover(
	function () {
	$(this).css({ "position": "relative"});
	$(this).children('.popup').css({ "display": "block"});
	var bh = $("body").height(); 
	var pt = $(this).offset();
	if(( bh - pt.top) < 165){$(this).children('.popup').css({ "bottom": "0"});}else{$(this).children('.popup').css({ "top": "-7px"});};
  },
	function () {
	$(this).css({ "position": "static"});
	$(this).children('.popup').css({ "display": "none"});
  }
);
$(".close").click(
	function () {
		$(this).parent().parent().css({ "display": "none"});
	}
);
</script>
<input type="hidden" id="forecastTotalCountHidden" value="${pager.totalCount}" />
<input type="hidden" id="forecastPageNoHidden" value="${pager.pageNo}" />
<input type="hidden" id="forecastTotalPageHidden" value="${pager.totalPage}" />
<input type="hidden" id="forecastOrderField" value="${pager.orderField}" />
<input type="hidden" id="forecastOrderBy" value="${pager.orderBy}" />
<input type="hidden" id="forecastInfoHidden" value="${(pager.pageNo-1)*pager.pageSize+1}-${(pager.pageNo-1)*pager.pageSize+pager.pageSize}" />
<div style="padding: 8px;">
	<table id="dlLineItem" class="easyui-datagrid" style="height: 490px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '',onClickRow: onDlClickRow1,fitColumns:false">
		<thead>
			<tr>
				<th data-options="field:'month1',width:50,align:'center',editor:'text'">编码</th>
				<th data-options="field:'month2',width:150,align:'center',editor:'text'">名称</th>
				<th data-options="field:'month3',width:100,align:'center',editor:'text'">规格型号</th>
				<th data-options="field:'month4',width:100,align:'center',editor:'text'">计量单位</th>
				<th data-options="field:'month5',width:90,align:'center',editor:'text'">年度</th>
				<th data-options="field:'month6',width:110,align:'center',editor:'text'">${vv:varView('vix_mdm_item')}</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">单位</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">1月</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">2月</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">3月</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">4月</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">5月</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">6月</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">7月</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">8月</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">9月</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">10月</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">11月</th>
				<th data-options="field:'month6',width:60,align:'center',editor:'text'">12月</th>
				<th data-options="field:'month6',width:100,align:'center',editor:'text'">状态</th>
				<th data-options="field:'month7',width:100,align:'center',editor:'text'">时间</th>
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