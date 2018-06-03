$(function(){
	jQuery("#addtree").jqGrid({
		url: 'jqgrid_test_php/server.php?q=tree',
		treedatatype: "xml",
		mtype: "POST",
		colNames:["id","Account","Acc Num", "Debit", "Credit","Balance"],
		colModel:[
			{name:'id',index:'id', width:1,hidden:true,key:true, editable:true},
			{name:'name',index:'name', width:470, editable:true},
			{name:'num',index:'acc_num', width:210, align:"center",editable:true},
			{name:'debit',index:'debit', width:214, align:"right",editable:true},
			{name:'credit',index:'credit', width:214,align:"right",editable:true},
			{name:'balance',index:'balance', width:214,align:"right",editable:true}
		],
		height:'400',
		pager : "#paddtree",
		treeGrid: true,
		ExpandColumn : 'name',
		editurl:'jqgrid_test_php/server.php?q=dummy',
		caption: "Add Tree node example",
		width:'auto'
	});
	jQuery("#addtree").jqGrid('navGrid',"#paddtree");
	//aaa();
	//bbb();
});
function aaa(){
	if($('#gview_addtree').length >0)
	{
		$('#gview_addtree').removeAttr('style');
		$('div.addtree').parent('div').removeAttr('style');
	}
	else setTimeout("aaa()","100");
}
function bbb(){
	if($('.ui-jqgrid-bdiv').length >0)
	{
		$('.ui-jqgrid-bdiv > div').css('left',0);
	}
	else setTimeout("bbb()","100");
}		
