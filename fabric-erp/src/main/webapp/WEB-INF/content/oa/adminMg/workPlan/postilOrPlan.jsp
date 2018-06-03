<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.mousewheel.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.ui.ipad.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.global.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="projectForm">
		<input type="hidden" id="id" name="id" value="${postil.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr>
					<td><textarea id="planContent" name="planContent">${postil.planContent}</textarea> <script type="text/javascript">
							 var planContents = KindEditor.create('textarea[name="planContent"]',
							{basePath:'${vix}/plugin/KindEditor/',
								width:670,
								height:245,
								cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
								uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
								fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
								allowFileManager : true 
						}
					 );
					 </script></td>
				</tr>
			</table>
		</div>
	</form>
</div>