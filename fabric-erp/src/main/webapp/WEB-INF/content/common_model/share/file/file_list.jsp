<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<style type="text/css">
* {
	font-size: 12px;
}

#vixFileListInfo {
	background: #f0f0f0;
}

#vixFileListInfo table {
	border: 0px solid #69c;
}

#vixFileListInfo td {
	padding: 6px 8px;
	border-bottom: 1px solid #ddd;
	color: #000;
}

#vixFileListInfo .bg_c td {
	background: #f6f6f6;
}
</style>
<div id="vixFileListInfo">
	<s:if test="%{fileList!=null && fileList.size()>0}">
		<table width="100%" cellspacing="0" cellpadding="0">
			<s:iterator id="fileAtt" value="fileList" status="st">
				<tr <s:if test="%{#st.isOdd}">class="bg_c"</s:if>>
					<td><s:property value="%{#fileAtt.name}" /></td>
					<td><a href="javascript:void(0)" onclick="downloadVixFile('<s:property value="%{#fileAtt.id}"/>')">下载</a></td>
					<s:if test="%{isDetail!=1}">
						<td><a href="javascript:void(0)" onclick="deleteVixFile('<s:property value="%{#fileAtt.id}"/>','${divId}','<s:property value="%{#fileAtt.dataId}"/>','<s:property value="%{#fileAtt.businessTag}"/>','<s:property value="%{#fileAtt.attType}"/>')">删除</a></td>
					</s:if>
				</tr>
			</s:iterator>
		</table>
	</s:if>
	<%-- <s:else>
		没有附件!
	</s:else> --%>
</div>

