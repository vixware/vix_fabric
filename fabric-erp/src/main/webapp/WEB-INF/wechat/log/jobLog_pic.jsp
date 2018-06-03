<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/wechatcommon/page/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="con">
	<div class="newMeet_list1">
		<h4 id="imgId">
			<input type="hidden" id="pictureTag" value="1" />
			<s:if test="wxpQyPictureList.size > 0">
				<s:iterator value="wxpQyPictureList" var="entity" status="s">
					<a href="#" class="imgtag" onclick="removeImgId(this);deletepic('${entity.id}');"><img src="${vix}${entity.pictureUrl }" class="upload_image" /></a>
				</s:iterator>
			</s:if>
			<a id="btn_addPic1" class="btn_addPic1"><img src="${vix}/wechatcommon/images/newMeet_icon1.png" /> <input class=filePrew id="fileToUpload" title=支持jpg、jpeg、gif、png格式，文件小于5M tabIndex=3 type=file size=3 name="fileToUpload"></a> <a href="#" onclick="removeTag();"><img src="${vix}/wechatcommon/images/newMeet_icon2.png" /></a>
		</h4>
	</div>
</div>