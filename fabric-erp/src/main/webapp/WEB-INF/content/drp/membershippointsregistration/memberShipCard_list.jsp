<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	loadMenuContent('${vix}/drp/drpMainAction!goMenuContent.action');
	$.returnValue = "";
	function chkChange(chk, id, name, code) {
		if (chk.checked) {
			$.returnValue = id + "," + name + "," + code;
		}
	}
	pager("start", "${vix}/drp/membershipPointsregistrationAction!goMemberShipCardList.action?temp=1", "memberShipCard");
	//排序 
	function subOrderBy(orderField) {
		var orderBy = $("#subBrandOrderBy").val();
		if (orderBy == 'desc') {
			orderBy = "asc";
		} else {
			orderBy = 'desc';
		}
		pager("start", "${vix}/drp/membershipPointsregistrationAction!goMemberShipCardList.action?orderField=" + orderField + "&orderBy=" + orderBy, "memberShipCard");
	}
	function currentPager(tag) {
		pager(tag, "${vix}/drp/membershipPointsregistrationAction!goMemberShipCardList.action", 'memberShipCard');
	}
	var code = "";
	function loadCode() {
		code = $('#srm_code').val();
		code = Url.encode(code);
		code = Url.encode(code);
	}
	/*搜索*/
	function searchForContent(i) {
		loadCode();
		pager("start", "${vix}/drp/membershipPointsregistrationAction!goMemberShipCardList.action?searchContent=" + code, 'memberShipCard');
	}
	/*重置搜索*/
	function resetForContent() {
		$("#srm_code").val("");
	}
	//状态
	function srmStatus(status) {
		pager("start", "${vix}/drp/membershipPointsregistrationAction!goMemberShipCardList.action?status=" + status, 'memberShipCard');
	}
	//最近使用
	function srmRecent(rencentDate) {
		pager("start", "${vix}/drp/membershipPointsregistrationAction!goMemberShipCardList.action?updateTime=" + rencentDate, 'memberShipCard');
	}
</script>
<div class="content" style="background: #DCE7F1">
	<div id="c_head" class="drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /> </b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a> </i> <i><a href="#"></a> </i> <i><a href="#"></a> </i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<div>
			<label>姓名:<input id="srm_code" name="" type="text" class="int" />
			</label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent();" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent();" />
			</label>
		</div>
	</div>
	<div class="box">
		<div id="right">
			<div id="memberShipCard"></div>
		</div>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>