<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#purchasePlanPackageForm").validationEngine();
	/**
	 * 变更选择发布类型
	 */
	function changePubType(pubTypeValue) {
		clearPubType();
	}

	/**
	 * 清空选择对象
	 */
	function clearPubType() {
		$("#organdempid").val("");
		$("#organdempname").val("");
	}

	/**
	 * 添加发布对象
	 */
	function addBulletinPubobject() {

		var pubTypeVal = $("input[name='packageType']:checked").val();
		//debugger;
		if (pubTypeVal == "O") {
			chooseBulletinOrgUnit($("#organdempid").val());
		} else if (pubTypeVal == "E") {
			chooseBulletinEmp($("#organdempid").val());
		}
	}/**
	 * 选择部门
	 */
	function chooseBulletinOrgUnit() {
		$.ajax({
		url : '${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
		data : {
		chkStyle : "checkbox",
		canCheckComp : 0
		},
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择部门",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						//alert(returnValue);
						var selectIds = "";
						var selectNames = "";

						var resObj = $.parseJSON(returnValue);

						for (var i = 0; i < resObj.length; i++) {
							selectIds += "," + resObj[i].treeId;
							selectNames += "," + resObj[i].name;
						}

						if (selectIds != '') {
							selectIds = selectIds.substring(1);
							selectNames = selectNames.substring(1);
							//alert(selectIds)
							$("#organdempid").val(selectIds);
							$("#organdempname").val(selectNames);
						}
					}

				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	/**
	 * 选择人员
	 */
	function chooseBulletinEmp(checkObjIds) {
		$.ajax({
		url : '${vix}/common/select/commonSelectEmpAction!goList.action',
		data : {
			chkStyle : "checkbox"
		},
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 660,
			height : 340,
			title : "选择人员",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						//alert(returnValue);
						var selectIds = "";
						var selectNames = "";

						var pubIdTmp = $("#organdempid").val();

						pubIdTmp = pubIdTmp + ",";

						/* if(resObj.length == 0 ){
							return;
						} */
						//debugger;
						var result = returnValue.split(",");
						for (var i = 0; i < result.length; i++) {
							if (result[i].length > 1) {
								var v = result[i].split(":");
								if (!pubIdTmp.contains(v[0] + ",")) {
									selectIds += "," + v[0];
									selectNames += "," + v[1];
								}
							}
						}

						selectIds = $("#organdempid").val() + selectIds;
						selectNames = $("#organdempname").val() + selectNames;

						$("#organdempid").val(selectIds);
						selectNames = selectNames.substring(1, selectNames.length);
						$("#organdempname").val(selectNames);
					}

				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
</script>
<input type="hidden" id="purchasePlanPackageId" name="purchasePlanPackageId" value="${purchasePlanPackage.id}" />
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="purchasePlanPackageForm">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">主题：</td>
					<td><input id="name" name="name" value="${purchasePlanPackage.name }" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<th class="tr">上报：</th>
					<td colspan="3"><s:radio id="packageType" list="#{'O':'部门','E':'人员'}" name="packageType" value="%{purchasePlanPackage.packageType}" onchange="changePubType(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBulletinPubobject()"><img src="img/icon_25.gif" />新增</a>&nbsp;&nbsp; <a href="#"
						onclick="clearPubType()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="3" cols="3" id="organdempname" name="organdempname" style="width: 450px; height: 85px;"></textarea> <input type="hidden" id="organdempid" name="organdempid" value="" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>