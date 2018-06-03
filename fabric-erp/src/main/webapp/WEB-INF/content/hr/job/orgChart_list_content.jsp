<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/hr/orgTree/css/css.css" rel="stylesheet" type="text/css" />
<body>
	<!-- top bar -->
	<!-- head -->
	<!-- sub menu -->
	<div class="content">
		<div class="box">
			<div class="treebase">
				</span></a>
				<center>
					<div id="treeBase" class="tree_base"></div>
				</center>
			</div>
		</div>
		<div class="c_foot">
			<span class="left_bg"></span> <span class="right_bg"></span>
		</div>
	</div>

	<script src="js/menu.js" type="text/javascript"></script>
	<script src="js/loadtree.js" type="text/javascript"></script>
	<script>
$(function(){
	$('.tc_texticon').live('click',function(){
		if($.trim($(this).text()) == '+'){
			$(this).closest('td').append('<div class="tc_copay">'+$(this).closest('.tc_copay').html()+'</div>');
			$('.tc_texticon',$(this).closest('td')).text('-');
			$('.tc_texticon:last',$(this).closest('td')).text('+');
		}else if($.trim($(this).text()) == '-'){
			$(this).closest('.tc_copay').remove();
		}
	});
});
</script>
	<script type="text/javascript">
$(function(){
	$(".tree_base .n_info").each(function(i){
		$(this).bind('click',addSubTreeNode);
	})
	
addTreeRootNode('<s:property value="org.id"/>','<s:property value="org.orgName"/>','treeBase')
	
<s:iterator value="org.subOrganizations.iterator"  var="entity" >
	addSubTreeNode('${entity.id}', '${entity.orgName}', '${entity.parentId}');
</s:iterator>
	
})

var tree_node_html = '<dl class="t_node"><dt class="n_head"><div class="n_info">info</div></dt><dd class="n_sub"></dd></dl>';

function genNodeObj(orgId, orgName, parentId){
	if(!orgName)
		orgName = 'no name';
	var infoNode = $(tree_node_html);
	
	infoNode.attr('id',orgId);
	infoNode.attr('org_name',orgName);
	infoNode.attr('parent_id',parentId)
	infoNode.find('.n_info').text(orgName);
	return infoNode;
}

function addTreeRootNode(orgId, orgName, parentId){
	var tree_node_obj = genNodeObj(orgId, orgName,parentId) ;
	tree_node_obj.addClass('t_root');
	$('#'+parentId).append(tree_node_obj);
	tree_node_obj.animate({opacity:1},300);
}

function addSubTreeNode(orgId, orgName, parentId){
	var parentObj = $('#'+parentId);
	
	var parentHead = parentObj.find('.n_head');
	if(!parentHead.hasClass('l_down'))
		parentHead.addClass('l_down');

	var tree_node_obj = genNodeObj(orgId, orgName, parentId) ;
	tree_node_obj.prepend('<div class="n_line l no"></div><div class="n_line r no"></div>');
	
	var tree_node_sub = parentObj.find('.n_sub:first');

	tree_node_sub.append(tree_node_obj);
	
	addSubNodeBg(tree_node_sub);
	if(!tree_node_obj.hasClass('l_up_mid'))
		tree_node_obj.addClass('l_up_mid');
}

function addSubNodeBg(subNodesParent){
	var subCount = subNodesParent.attr('subCount');
	if(subCount>0){
		subNodesParent.attr('subCount',subCount+1);
		subNodesParent.children('.t_node').each(function(i){
			if($(this).is(':first-child')){
				$(this).addClass('l_up_left');
				$(this).find('.n_line.r').removeClass('no');
			}else if($(this).is(':last-child')){
				$(this).addClass('l_up_right');
				$(this).find('.n_line.l').removeClass('no');
			}else{
				$(this).removeClass('l_up_left').removeClass('l_up_right');
				$(this).find('.n_line').removeClass('no');
			}
		});
		if(subCount==1)
			subNodesParent.addClass('l_mid');
		subNodesParent.attr('subCount',subCount+1);
	}else{
		subNodesParent.attr('subCount',1);
	}
}
</script>
</body>