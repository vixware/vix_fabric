<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_head -->
	<div class="box">
		<div class="share_box">
			<input type="hidden" id="customerAccountId">
			<div class="share_title">
				<img src="${vix}/common/img/address_book.png" /> <span id="customerAccountName">选择目标用户</span>&nbsp;&nbsp;
			</div>
			<div class="share_type">
				<h3>批量共享提示</h3>
				<p>
					<label><input type="checkbox" />:有权限（增加权限）</label><label><input type="checkbox" />:无权限（去掉权限）</label><label><input type="checkbox" />:不变（保持原来权限）</label>
				</p>
				<h3>批量转移提示</h3>
				<p>选中目标人，点击提交即可</p>
			</div>
			<div class="share_title">
				<input type="radio" id="publicTo" /> 共享给
			</div>
			<div style="padding: 10px;">
				<div id="tree">
					<div id="chooseEmpBizOrgZtree" class="ztree" style="padding: 0;"></div>
				</div>
				<script type="text/javascript">
					<!--
					var choosEmpBizOrgTree;			
					var empBizOrgTreeSetting = {
						view:{
							addDiyDom: addDiyDom
						},
						async: {
							enable: true,
							url:"${vix}/common/select/commonSelectEmpByBizOrgAction!findEmpBizOrgTreeToJson.action?bizViewCode=Sale",
							autoParam:["treeId"]
						}
					};
					choosEmpBizOrgTree = $.fn.zTree.init($("#chooseEmpBizOrgZtree"), empBizOrgTreeSetting);
					
					function addDiyDom(treeId, treeNode) {
						var aObj = $("#" + treeNode.tId + "_a");
						var editStr = "<input type='checkbox' id='diyChkRead_"+treeId+"' value='"+treeNode.empId+"'/>读&nbsp;&nbsp;<input type='checkbox' id='diyChkWrite_"+treeId+"' value='"+treeNode.empId+"'/>写";
						aObj.after(editStr);
					}
					//-->
					</script>
			</div>
			<input type="hidden" id="selectEmpIds" value="" />
			<s:if test="type == 'share'">
				<div class="share_inner">
					<h4>用户组：</h4>
					<ul class="share_list">
						<s:iterator value="customerAccountGroupList" var="cag" status="s">
							<li><img src="img/n2.png" /> <strong>${cag.name}</strong><input id="userGroupChkRead_${s.count}" type="checkbox" value="${cag.id}">读 <input id="userGroupChkRead_${s.count}" type="checkbox" value="${cag.id}">写</li>
						</s:iterator>
					</ul>
				</div>
			</s:if>
			<div class="share_title">
				<input type="radio" id="publicAll" /> 全部公开
			</div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>