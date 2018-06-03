<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<li>
	<a id="mid_document" href="javascript:void(0);"><i class="fa fa-lg fa-fw fa-file-text-o"></i> <span class="menu-item-parent">公文管理</span></a>
	<ul>
		<li>
			<a href="javascript:void(0);"><i class="fa fa-fw icon-iconfont-enumeration"></i>字典管理</a>
			<ul>
				<li><a id="mid_sendType" href="javascript:void(0);" onclick="loadContent('mid_sendType','${nvix}/nvixnt/document/nvixSendTypeAction!goList.action');"><i class="fa fa-reply"></i>发文类型</a></li>
				<li><a id="mid_receiveType" href="javascript:void(0);" onclick="loadContent('mid_receiveType','${nvix}/nvixnt/document/nvixReceiveTypeAction!goList.action');"><i class="fa fa-share"></i>收文类型</a></li>
				<li><a id="mid_secretGrade" href="javascript:void(0);" onclick="loadContent('mid_secretGrade','${nvix}/nvixnt/document/nvixSecretGradeAction!goList.action');"><i class="fa fa-sort-numeric-asc"></i>秘密等级</a></li>
				<li><a id="mid_urgentDegree" href="javascript:void(0);" onclick="loadContent('mid_urgentDegree','${nvix}/nvixnt/document/nvixUrgentDegreeAction!goList.action');"><i class="fa fa-sort-numeric-desc"></i>紧急程度</a></li>
			</ul>
		</li>
		<li>
			<a href="javascript:void(0);"><i class="fa fa-reply"></i>发文管理</a>
			<ul>
				<li><a id="mid_sendDocument" href="javascript:void(0);" onclick="loadContent('mid_sendDocument','${nvix}/nvixnt/document/nvixSendDocumentAction!goList.action');"><i class="fa fa-file"></i>发文拟稿</a></li>
			</ul>
		</li>
		<li>
			<a href="javascript:void(0);"><i class="fa fa-share"></i>收文管理</a>
			<ul>
				<li><a id="mid_receiveDocument" href="javascript:void(0);" onclick="loadContent('mid_receiveDocument','${nvix}/nvixnt/document/nvixReceiveDocumentAction!goList.action');"><i class="fa fa-file-text"></i>收文登记</a></li> 
				<li><a id="mid_receiveDocumentCheck" href="javascript:void(0);" onclick="loadContent('mid_receiveDocumentCheck','${nvix}/nvixnt/document/nvixReceiveDocumentAction!goCheckList.action');"><i class="fa fa-file-text-o"></i>收文审核</a></li> 
			</ul>
		</li>
	</ul>
</li>