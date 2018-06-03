<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="newMeet_list1 newCla_list2">
	<h1>
		<span>附件（${docNum }）</span><a class="btn_addPic" href="javascript:void(0);">+上传 <input class="filePrew" tabIndex="3" type="file" size="3" id="docToUpload" name="docToUpload" onchange="uploaddoc();"></a>
	</h1>
</div>
<!----- 已有的附件--->
<div class="attLis" id="uploaderId">
	<s:if test="uploaderList.size > 0">
		<s:iterator value="uploaderList" var="entity" status="s">
			<dl>
				<dt>
					<span><img src="${vix}/vixntcommon/base/images/icon-08.png"></span> <strong><b>${entity.fileName }</b>${entity.filesize }</strong>
				</dt>
				<a onclick="godeletedoc('${entity.id }');"><dd></dd></a>
			</dl>
		</s:iterator>
	</s:if>
</div>