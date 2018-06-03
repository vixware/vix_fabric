var agt = navigator.userAgent.toLowerCase();
var is_ie = ((agt.indexOf("msie") != -1) && (agt.indexOf("opera") == -1));
var deptextboxid = "";
var Utils={}
Utils.taskinhr = "false";
var menudiv_id = "";

stopEvents = function(ev) {
	try {
		if (!ev) {
			ev = window.event;
		}
		if (ev.stopPropagation) {
			ev.preventDefault();
			ev.stopPropagation();
		} else {
			ev.cancelBubble = true;
			ev.returnValue = false;
		}
	} catch(e) {}
};

//弹窗scroll位置
function scrollInViewPart(_10a7) {
	var elem = jQuery("#" + _10a7);
	var _10a9 = jQuery(window).scrollTop();
	var _10aa = _10a9 + jQuery(window).height();
	var _10ab = jQuery(elem).offset().top;
	var _10ac = _10ab + jQuery(elem).height();
	if ((_10ac > _10aa) && (_10ab < _10aa)) {
		jQuery("#" + _10a7)[0].scrollIntoView(false);
	} else {
		if (_10ab < _10a9) {
			jQuery("#" + _10a7)[0].scrollIntoView(true);
		}
	}
}
//自定义列弹窗
function setPosDiv(divId, _10b7) {
	var pos = jQuery("#" + divId).offset();
	var _10b9 = pos.top;
	if ("customcolumn" == _10b7) {
		var _10ba = pos.left - 146;
		jQuery("#" + _10b7).css({
			"top": (_10b9-170.5) + "px",
			"left": _10ba + "px"
		});
	} else {
		jQuery("#" + _10b7).css({
			"top": _10b9 + "px"
		});
	}
	jQuery("#" + _10b7).show();
	scrollInViewPart(_10b7);
}
function sE(e) {
	if (!e) {
		var e = window.event;
	}
	e.cancelBubble = true;
	if (e.stopPropagation) {
		e.stopPropagation();
	}
	iscalvisible = false;
}

function hideMPMenu(ev) {
	var qobj = document.getElementById("mpMenu");
	if (!qobj) {
		return;
	}
	if (is_ie) {
		var eobj = window.event.srcElement;
	} else {
		var eobj = ev.target;
	}
	if (eobj.id != "myportalid" && eobj.id != "myportalid" && eobj.id != "myportalid" && eobj.id != "mpMenu") {
		if (qobj.style.display == "block") {
			qobj.style.display = "none";
		}
	}
	if (is_ie && !is_opera) {
		iframeIEHack = document.getElementById("iframeIEHack");
		if (iframeIEHack) {
			document.body.removeChild(iframeIEHack);
			iframeIEHack = null;
		}
	}
}

function hideSubMenu(ev) {
	if (is_ie) {
		var eobj = window.event.srcElement;
	} else {
		var eobj = ev.target;
	}
	jQuery("#selperiod").attr("class", "tsactionmenunor1");
	jQuery("#periods").hide();
	if (jQuery("#errorMsgDiv").is(":visible")) {
		jQuery("#errorMsgDiv").hide();
	}
	if (document.getElementById("customcolumn") != null) {
		if (eobj.id != "customcolumn") {
			jQuery("#customcolumn").hide();
		}
	}
	if (document.getElementById("addtasklog") != null) {
		if (eobj.id != "addtasklog") {
			jQuery("#addtasklog").hide();
		}
	}
	if (document.getElementById("muluploaddocs") != null) {
		if (eobj.id != "muluploaddocs") {
			jQuery("#muluploaddocs").hide();
		}
	}
	if (document.getElementById("updassign") != null) {
		if (eobj.id != "updassign") {
			jQuery("#updassign").hide();
		}
	}
	if (document.getElementById("movetaskid") != null) {
		if (eobj.id != "movetaskid") {
			jQuery("#movetaskid").hide();
		}
	}
	
	//所有者弹窗 隐藏
	if (document.getElementById("townerdiv") != null) {
		if (eobj.id != "townerdiv") {
			jQuery("#townerdiv").hide();
		}
	}
	if (document.getElementById("assoforumid") != null) {
		if (eobj.id != "assoforumid") {
			jQuery("#assoforumid").hide();
		}
	}
	if (document.getElementById("assodocid") != null) {
		if (eobj.id != "assodocid") {
			jQuery("#assodocid").hide();
		}
	}
	if (document.getElementById("addtodoid") != null) {
		if (eobj.id != "addtodoid") {
			jQuery("#addtodoid").hide();
		}
	}
	if (document.getElementById("updtodoid") != null) {
		if (eobj.id != "updtodoid") {
			jQuery("#updtodoid").hide();
		}
	}
	if (document.getElementById("copyid") != null) {
		if (eobj.id != "copyid") {
			jQuery("#copyid").hide();
		}
	}
	if (document.getElementById("updtlistid") != null) {
		if (eobj.id != "updtlistid") {
			jQuery("#updtlistid").hide();
		}
	}
	if (document.getElementById("addtlistid") != null) {
		if (eobj.id != "addtlistid") {
			jQuery("#addtlistid").hide();
		}
	}
	if (document.getElementById("todoexpid") != null) {
		if (eobj.id != "todoexpid") {
			jQuery("#todoexpid").hide();
		}
	}
	if (document.getElementById("customcolumn") != null) {
		if (eobj.id != "customcolumn") {
			jQuery("#customcolumn").hide();
		}
	}
	if (document.getElementById("assoforumid") != null) {
		if (eobj.id != "assoforumid") {
			jQuery("#assoforumid").hide();
		}
	}
	if (document.getElementById("assodocid") != null) {
		if (eobj.id != "assodocid") {
			jQuery("#assodocid").hide();
		}
	}
	if (document.getElementById("addtodoid") != null) {
		if (eobj.id != "addtodoid") {
			jQuery("#addtodoid").hide();
		}
	}
	if (document.getElementById("updtodoid") != null) {
		if (eobj.id != "updtodoid") {
			jQuery("#updtodoid").hide();
		}
	}
	if (document.getElementById("copyid") != null) {
		if (eobj.id != "copyid") {
			jQuery("#copyid").hide();
		}
	}
	if (document.getElementById("updtlistid") != null) {
		if (eobj.id != "updtlistid") {
			jQuery("#updtlistid").hide();
		}
	}
	if (document.getElementById("addtlistid") != null) {
		if (eobj.id != "addtlistid") {
			jQuery("#addtlistid").hide();
		}
	}
	if (document.getElementById("todoexpid") != null) {
		if (eobj.id != "todoexpid") {
			jQuery("#todoexpid").hide();
		}
	}
	if (document.getElementById("ganttprint") != null) {
		if (eobj.id != "ganttprint") {
			jQuery("#ganttprint").attr("class", "tsactionmenunor1");
			jQuery("#printpopup").hide();
		}
	}
	if (document.getElementById("quickActionListView") != null) {
		if (eobj.id != "quickActionListView") {
			jQuery("[name=depActionDiv]").hide();
		}
	}
	if (document.getElementById(deptextboxid) != null) {
		if (eobj.id != deptextboxid) {
			if (deptextboxid == "deppredtext") {
				depFormHide("depsafdisp_", "deppreddiv", "pred_", "predid_", "pt3 pL3 pointer txtSmallBlack dpczpbdrbr", "pt3 pL3 pointer txtSmallBlack dark");
			} else {
				if (deptextboxid == "enddatetb") {
					var _4d9 = true;
					if (is_ie) {
						_4d9 = checkDepDiv(eobj, _4d9);
					}
					if (_4d9 == true) {
						depFormHide("dependisp_", "dependdatediv", "enddate_", "enddateid_", "pt3 pL3 pointer taskdeptxtSmall dpczpbdrbr", "pt3 pL3 pointer taskdeptxtSmall dark");
					}
				} else {
					if (deptextboxid == "startdatetext") {
						var _4d9 = true;
						if (is_ie) {
							_4d9 = checkDepDiv(eobj, _4d9);
						}
						if (_4d9 == true) {
							depFormHide("depstdisp_", "depstartdatediv", "stdate_", "stdateid_", "pt3 pL3 pointer taskdeptxtSmall dpczpbdrbr", "pt3 pL3 pointer taskdeptxtSmall dark");
						}
					} else {
						if (deptextboxid == "depdurtext") {
							depFormHide("depdurdisp_", "depdurdiv", "durationtd_", "duration_", "pt3 pL3 pointer txtSmallBlack dpczpbdrbr", "pt3 pL3 pointer txtSmallBlack dark");
						} else {
							if (deptextboxid == "deptitletext") {

								depFormHide("taskdisp_", "deptitlediv", "taskname_", "taskid_", "pr5 pL3 dpczpbdrbr", "pr5 pL3 dark");
							}
						}
					}
				}
			}
		}
	}
	if (document.getElementById("tlactiondiv") != null) {
		if (eobj.id != "tlactiondiv") {
			jQuery("#tlactiondiv").hide();
		}
	}
	jQuery("[nameattr=taskactiondiv]").hide();
	changeStyleByName("taskactiondiv", "div", "none");
	changeStyleByName("mileactiondiv", "div", "none");
	changeStyleByName("docactiondiv", "div", "none");
	changeStyleByName("temptlaction", "div", "none");
	changeStyleByName("clactiondiv", "div", "none");
	changeTaskStyleByName("addownerdiv", "div", "none", eobj.id);
	if (document.getElementById("export") != null) {
		if (eobj.id != "export") {
			jQuery("#export").remove();
		}
	}
	if (document.getElementById("logtaskbug") != null) {
		if (eobj.id != "logtaskbug") {
			jQuery("#logtaskbug").remove();
		}
	}
	changeStyleByName("timesheetactiondiv", "div", "none");
	if (eobj.id != "tsshownotes") {
		changeStyleByName("tsnotesdiv", "div", "none");
	}
	if (document.getElementById("percompdiv") != null) {
		if (eobj.id != "percompdiv") {
			jQuery("#percompdiv").hide();
		}
	}
	if (document.getElementById("statusdiv") != null) {
		if (eobj.id != "statusdiv") {
			jQuery("#statusdiv").hide();
		}
	}
	if (document.getElementById("durdiv") != null) {
		if (eobj.id != "durdiv") {
			jQuery("#durdiv").hide();
		}
	}
	if (document.getElementById("prioritydiv") != null) {
		if (eobj.id != "prioritydiv") {
			jQuery("#prioritydiv").hide();
		}
	}
	if (document.getElementById("movetlid") != null) {
		if (eobj.id != "movetlid") {
			jQuery("#movetlid").hide();
		}
	}
	if (document.getElementById("tlbulkView") != null) {
		if (eobj.id != "tlbulkView") {
			jQuery("#tlbulkView").hide();
		}
	}
	if (document.getElementById("taskstatusView") != null) {
		if (eobj.id != "taskstatusView") {
			showHideTaskPropertyList("taskstatusView", false);
		}
	}
	if (document.getElementById("taskpriorityView") != null) {
		if (eobj.id != "taskpriorityView") {
			showHideTaskPropertyList("taskpriorityView", false);
		}
	}
	if (document.getElementById("taskownerView") != null) {
		if (eobj.id != "taskownerView") {
			showHideTaskPropertyList("taskownerView", false);
		}
	}
	if (document.getElementById("searchtd") != null) {
		if (eobj.id != "searchtd") {
			Hide("tablist");
		}
	}
	if (document.getElementById("mileuser") != null) {
		if (eobj.id != "mileuser") {
			jQuery("#mileuser").attr("class", "tsactionmenunor1");
			jQuery("#mileuserview").hide();
		}
	}
	if (document.getElementById("projlisttr") != null) {
		if (eobj.id != "projlisttr") {
			ShowHideInline("switchproj", "projlist");
		}
	}
	if (document.getElementById("portallisttr") != null) {
		if (eobj.id != "portallisttr") {
			ShowHideInline("switchportal", "pmenu");
		}
	}
	if (document.getElementById("homefiltertr") != null) {
		if (eobj.id != "homefiltertr") {
			ShowHideInline("fname", "fdiv");
		}
	}

	if (document.getElementById("moreopttr") != null) {
		if (eobj.id != "moreopttr") {
			ShowHideInline("moreoptname", "moreopt");
		}
	}
	if (document.getElementById("switch") != null) {
		if (eobj.id != "switch") {
			document.getElementById("switchto").style.display = "none";
		}
	}
	if (document.getElementById("tp_helpdiv") != null) {
		if (eobj.id != "tp_helpdiv") {
			ShowHideInline("listhelp", "tp_helpdiv");
		}
	}
	if (document.getElementById("tp_settingdiv") != null) {
		if (eobj.id != "tp_settingdiv") {
			ShowHideInline("listsetting", "tp_settingdiv");
		}
	}
	if (document.getElementById("selmthdiv") != null) {
		if (eobj.id != "selmthdiv") {
			ShowHideInline("selmthname", "selmthdiv");
		}
	}
	if (document.getElementById("seldatetr") != null) {
		if (eobj.id != "seldatetr") {
			ShowHideInline("seldatename", "seldatediv");
		}
	}
	if (document.getElementById("taskreport") != null) {
		if (eobj.id != "taskreport") {
			jQuery("#taskreport").attr("class", "tsactionmenunor1");
			jQuery("#taskreportView").hide();
		}
	}
	if (document.getElementById("dateview") != null) {
		if (eobj.id != "dateview") {
			jQuery("#dateview").attr("class", "tsactionmenunor1");
			jQuery("#gantView1").hide();
		}
	}
	if (document.getElementById("gant") != null) {
		if (eobj.id != "gant") {
			jQuery("#gant").attr("class", "tsactionmenunor1");
			jQuery("#gantView").hide();
		}
	}
	if (document.getElementById("types") != null) {
		if (eobj.id != "types") {
			jQuery("#types").attr("class", "tsactionmenunor1");
			jQuery("#typelistView").hide();
		}
	}
	if (document.getElementById("projectlist") != null) {
		if (eobj.id != "projectlist") {
			jQuery("#projectlist").attr("class", "tsactionmenunor1");
			jQuery("#projectlistView").hide();
		}
	}
	if (document.getElementById("severityView") != null) {
		if (eobj.id != "severityView") {
			showHideBugPropertyList("severityView", "severity", false);
		}
	}
	if (document.getElementById("priorityView") != null) {
		if (eobj.id != "priorityView") {
			showHideBugPropertyList("priorityView", "priority", false);
		}
	}
	if (document.getElementById("typeView") != null) {
		if (eobj.id != "typeView") {
			showHideBugPropertyList("typeView", "type", false);
		}
	}
	if (document.getElementById("ownerView") != null) {
		if (eobj.id != "ownerView") {
			showHideBugPropertyList("ownerView", "owner", false);
		}
	}
	if (document.getElementById("moduleView") != null) {
		if (eobj.id != "moduleView") {
			showHideBugPropertyList("moduleView", "module", false);
		}
	}
	if (document.getElementById("flagView") != null) {
		if (eobj.id != "flagView") {
			showHideBugPropertyList("flagView", "flag", false);
		}
	}
	if (document.getElementById("statusFilter") != null) {
		if (eobj.id != "statusFilter") {
			showHideCommonList("statusFilterView", "statusFilter", false);
		}
	}
	if (document.getElementById("bugUsersList") != null) {
		if (eobj.id != "bugUsersList") {
			jQuery("#bugUsersList").hide();
		}
	}
	if (document.getElementById("bugAssigntoList") != null) {
		if (eobj.id != "bugAssigntoList") {
			jQuery("#bugAssigntoList").hide();
		}
	}
	if (document.getElementById("bugSeverityList") != null) {
		if (eobj.id != "bugSeverityList") {
			jQuery("#bugSeverityList").hide();
		}
	}
	if (document.getElementById("bugClassifyList") != null) {
		if (eobj.id != "bugClassifyList") {
			jQuery("#bugClassifyList").hide();
		}
	}
	if (document.getElementById("bugModuleList") != null) {
		if (eobj.id != "bugModuleList") {
			jQuery("#bugModuleList").hide();
		}
	}
	if (document.getElementById("bugFlagList") != null) {
		if (eobj.id != "bugFlagList") {
			jQuery("#bugFlagList").hide();
		}
	}
	if (document.getElementById("bugStatusList") != null) {
		if (eobj.id != "bugStatusList") {
			jQuery("#bugStatusList").hide();
		}
	}
	if (document.getElementById("bugMileList") != null) {
		if (eobj.id != "bugMileList") {
			jQuery("#bugMileList").hide();
		}
	}
	if (document.getElementById("singleStatusUpdate") != null) {
		if (eobj.id != "singleStatusUpdate") {
			jQuery("#singleStatusUpdate").hide();
		}
	}
	if (document.getElementById("viewbyFilter") != null) {
		if (eobj.id != "viewbyFilter") {
			showHideCommonList("viewbyFilterView", "viewbyFilter", false);
		}
	}
	if (document.getElementById("bugactdiv_" + menudiv_id) != null) {
		if (eobj.id != "bugactdiv_" + menudiv_id) {
			hideCommonList("bugActionListview");
			ChangeClass("bugactdiv_" + menudiv_id, "indIcon");
		}
	}
	if (document.getElementById("formlist_" + menudiv_id) != null) {
		if (eobj.id != "formlist_" + menudiv_id) {
			hideCommonList("bugActionListview" + menudiv_id);
			ChangeClass("formlist_" + menudiv_id, "indIcon");
		}
	}
	if (document.getElementById("projectFilter") != null) {
		if (eobj.id != "projectFilter") {
			showHideCommonList("projectFilterView", "projectFilter", false);
		}
	}
	if (document.getElementById("userFilter") != null) {
		if (eobj.id != "userFilter") {
			showHideCommonList("userFilterView", "userFilter", false);
		}
	}
	if (document.getElementById("bugisitreprodeditdiv") != null) {
		if (eobj.id != "duedateinput" && eobj.id != "modulelist" && eobj.id != "bugtypelist" && eobj.id != "milestonelist" && eobj.id != "bugisitreprodlist" && eobj.id != "flaglist" && eobj.id != "duedateanchor" && eobj.id != "moduleanchor" && eobj.id != "bugtypeanchor" && eobj.id != "milestoneanchor" && eobj.id != "bugisitreprodanchor" && eobj.id != "flaganchor" && eobj.id != "duedateimganchor" && eobj.id.indexOf("UDF_CHAR") == -1 && eobj.id.indexOf("UDF_LONG") == -1 && eobj.id.indexOf("UDF_DATE") == -1 && eobj.id.indexOf("saveicon") == -1 && eobj.id != "duedateTD" && eobj.id != "subjectlist" && eobj.id != "descriptionlist" && eobj.id != "descriptionimganchor" && eobj.id != "subjectimganchor") {
			HideAllBugEditOptions();
		}
	}
	var _4da = document.getElementsByName("issue_with_duedate");
	if (_4da != null) {
		for (var i = 0; i < _4da.length; i++) {
			var elmt = _4da[i];
			var _4dd = new String(_4da[i].value);
			var _4de = eobj.id;
			if (_4de.indexOf(_4dd + "_duedate") < 0) {
				ShowHideInline(_4dd + "_duedatevaluediv", _4dd + "_duedateeditdiv");
			}
		}
	}
	if (document.getElementById("userslist") != null) {
		if (eobj.id != "userslist") {
			jQuery("#userslist").attr("class", "tsactionmenunor1");
			jQuery("#clientlist").hide();
		}
	}
	if (document.getElementById("bylist") != null) {
		if (eobj.id != "bylist") {
			jQuery("#bylist").attr("class", "tsactionmenunor1");
			jQuery("#lists").hide();
		}
	}
	if (document.getElementById("addcaltask") != null) {
		if (eobj.id != "addcaltask") {
			showHideTaskPropertyList("addcaltask", false);
		}
	}
	if (document.getElementById("addcalmilestone") != null) {
		if (eobj.id != "addcalmilestone") {
			showHideTaskPropertyList("addcalmilestone", false);
		}
	}
	if (document.getElementById("resourceTaskDiv") != null) {
		if (eobj.id != "resourceTaskDiv") {
			showHideTaskPropertyList("resourceTaskDiv", false);
		}
	}
	if (document.getElementById("addcalmeeting") != null) {
		if (eobj.id != "addcalmeeting") {
			showHideTaskPropertyList("addcalmeeting", false);
		}
	}
	if (document.getElementById("updatemeeting") != null) {
		if (eobj.id != "updatemeeting") {
			showHideTaskPropertyList("updatemeeting", false);
		}
	}
	if (document.getElementById("updatemile") != null) {
		if (eobj.id != "updatemile") {
			showHideTaskPropertyList("updatemile", false);
		}
	}
	if (document.getElementById("addtop_mile") != null) {
		if (eobj.id != "addtop_mile") {
			showHideTaskPropertyList("addtop_mile", false);
		}
	}
	if (document.getElementById("addquickmeeting") != null) {
		if (eobj.id != "addquickmeeting") {
			showHideTaskPropertyList("addquickmeeting", false);
		}
	}
	if (document.getElementById("ZB_click") != null) {
		if (eobj.id != "ZB_click") {
			jQuery("#ZB_click").hide();
		}
	}
	if (document.getElementById("bugaction-link_" + menudiv_id) != null) {
		if (eobj.id != "bugaction-link_" + menudiv_id) {
			hideCommonList("bugactiondiv_" + menudiv_id);
			ChangeClass("bugaction-link_" + menudiv_id, "indIcon");
		}
	}
	if (document.getElementById("edittemptask") != null) {
		if (eobj.id != "edittemptask") {
			Hide("edittemptask");
		}
	}
	if (document.getElementById("showtempaddtask") != null) {
		if (eobj.id != "showtempaddtask") {
			Hide("showtempaddtask");
		}
	}
	if (document.getElementById("addtemptodolist") != null) {
		if (eobj.id != "addtemptodolist") {
			Hide("addtemptodolist");
		}
	}
	if (document.getElementById("tlistedit") != null) {
		if (eobj.id != "tlistedit") {
			Hide("tlistedit");
		}
	}
	if (document.getElementById("filemove") != null) {
		if (eobj.id != "filemove") {
			showHideTaskPropertyList("filemove", false);
		}
	}
	if (document.getElementById("exportbugdiv") != null) {
		if (eobj.id != "exportbugdiv") {
			jQuery("#exportbugdiv").hide();
		}
	}
	if (document.getElementById("modulePage") != null) {
		if (eobj.id != "modulePage") {
			jQuery("#modulePage").hide();
		}
	}
	if (document.getElementById("bugresolve") != null) {
		if (eobj.id != "bugresolve") {
			jQuery("#bugresolve").hide();
		}
	}
	if (document.getElementById("editresolutiondiv") != null) {
		if (eobj.id != "editresolutiondiv") {
			jQuery("#editresolutiondiv").hide();
		}
	}
	if (document.getElementById("addbuglog") != null) {
		if (eobj.id != "addbuglog") {
			jQuery("#addbuglog").hide();
		}
	}
	if (document.getElementById("editbuglog") != null) {
		if (eobj.id != "editbuglog") {
			jQuery("#editbuglog").hide();
		}
	}
	if (document.getElementById("bugform") != null) {
		if (eobj.id != "bugform") {
			showHideTaskPropertyList("bugform", false);
		}
	}
	if (document.getElementById("bugcustomcolumn") != null) {
		if (eobj.id != "bugcustomcolumn") {
			jQuery("#bugcustomcolumn").hide();
		}
	}
	if (document.getElementById("subjecteditdiv") != null) {
		if (eobj.id != "subjecteditdiv") {
			jQuery("#subjecteditdiv").hide();
		}
	}
	if (document.getElementById("duedateeditdiv") != null) {
		if (eobj.id != "duedateeditdiv") {
			EmptyDivContent("duedateeditdiv");
			jQuery("#duedateeditdiv").hide();
		}
	}
	if (document.getElementById("showmeetnotes") != null) {
		if (eobj.id != "showmeetnotes") {
			showHideTaskPropertyList("showmeetnotes", false);
		}
	}
	if (document.getElementById("addmeetdoc") != null) {
		if (eobj.id != "addmeetdoc") {
			showHideTaskPropertyList("addmeetdoc", false);
		}
	}
	if (document.getElementById("attachcommentdetail") != null) {
		if (eobj.id != "attachcommentdetail") {
			jQuery("#attachcommentdetail").hide();
		}
	}
	iscalvisible = false;
}
function hideCommonList(id){
	$("#"+id).hide();
}

function changeStyleByName(_fe1, _fe2, _fe3) {
	var _fe4 = document.getElementsByName(_fe1);
	if (is_ie) {
		var elem = document.getElementsByTagName(_fe2);
		for (i = 0; i < elem.length; i++) {
			att = elem[i].getAttribute("name");
			if (att == _fe1) {
				elem[i].style.display = _fe3;
			}
		}
	} else {
		for (var a = 0; a < _fe4.length; a++) {
			_fe4[a].style.display = _fe3;
		}
	}
}
function changeTaskStyleByName(_ff8, _ff9, _ffa, _ffb) {
	if (is_ie) {
		var elem = document.getElementsByTagName(_ff9);
		if (_ffb && _ffb.indexOf("_") != -1) {
			if (_ffb.split("_")[1]) {
				_ffb = _ff8 + "_" + _ffb.split("_")[1];
			}
		}
		for (i = 0; i < elem.length; i++) {
			var att = elem[i].getAttribute("name");
			if (att == _ff8) {
				var ids = elem[i].getAttribute("id");
				if (ids != _ffb) {
					hideCommonList(ids);
				}
			}
		}
	} else {
		var _fff = document.getElementsByName(_ff8);
		for (var a = 0; a < _fff.length; a++) {
			_fff[a].style.display = _ffa;
		}
	}
}
function changeAnchorClass(_1001, _1002) {
	if (is_ie) {
		var elem = document.getElementsByTagName("a");
		for (i = 0; i < elem.length; i++) {
			att = elem[i].getAttribute("name");
			if (att == _1001) {
				elem[i].className = _1002;
			}
		}
	} else {
		var elem = document.getElementsByName(_1001);
		for (var i = 0,
		l = elem.length; i < l; i++) {
			elem[i].className = _1002;
		}
	}
}
//openOwnerOptions('492772000000013031','owner','townerdiv','owner-link_','ul_ttask_492772000000013027','AnyUser');
function openOwnerOptions(id, _f41, _f42, _f43, _f44, _f45) {
	taskbulk = "";
	taskdiv_id = id;
	taskdiv_operation = _f41;
	var pos = jQuery("#" + _f43 + id).offset();
	var _f47 = Math.round(eval(pos.top)-151);
	var _f48 = Math.round(eval(pos.left)-19);
	jQuery("#" + _f42).css({
		"top": _f47 + "px",
		"left": _f48 + "px"
	});
	jQuery("#" + _f42).show();
	scrollInViewPart(_f42);
}
//自定义列
function fieldToggle(_109d, pid, csrf) {
	$("#common_table tbody tr td:nth-child(" + _109d + ")").toggle();
}
$(function(){
	$("#customcolumn ul li input[type='checkbox']").each(function(index, element) {
		var $_this = $(this);
		if($_this.attr("checked")!="checked"){
			$("#common_table tbody tr td:nth-child(" + $_this.attr('id') + ")").hide();
		}
	});;
});

//弹窗
//openOptions('492772000000013031','percomp','percompdiv','percomp-link_','ul_ttask_492772000000013027');
function openOptions(id, _f37, _f38, _f39) {
	taskbulk = "";
	taskdiv_id = id;
	taskdiv_operation = _f37;
	var pos = jQuery("#" + _f39 + id).offset();
	var _f3e = Math.round(eval(pos.top) - 175);
	var _f3f = Math.round(eval(pos.left));
	jQuery("#" + _f38).css({
		"top": (_f3e + 20) + "px",
		"left": (_f3f - 30) + "px"
	});
	
	//如果是百分比
	if(_f38 == "percompdiv" || _f38 == "prioritydiv" || _f38 == "statusdiv" || _f38 == "durdiv"){
		$("#"+_f38).attr("rel",id);
	}
	
	jQuery("#" + _f38).show();
	scrollInViewPart(_f38);
}

//updateValues('perdisp_',0,492772000000013031);hideCommonList('percompdiv');
//updateValues('/portal/ritchiechu/updateaction.do?projid=492772000000013019&percomp=0&zpcp=b5478fbb-5538-4935-839e-e2a72f69d151','popup','percomp');hideCommonList('percompdiv');
function updateValues (idName,num){
	var id = $("#percompdiv").attr("rel");
	if(num == 0){
		$("#"+idName+id+" .greenbar").css("width",0).attr("title","0% 完成");
		$("#"+idName+id+" .redbar").css("width",50);
	}
	else{
		$("#"+idName+id+" .greenbar").css("width",num/10*5).attr("title",num+"% 完成");
		$("#"+idName+id+" .redbar").css("width",50-num/10*5);
	}
}
//updateTextValues('priority-link_','空','492772000000013031');hideCommonList('prioritydiv');
function updateTextValues(idName,text,div){
	//alert(div);
	//alert("#"+idName+$("#"+div).attr("rel")+" a");
	$("#"+idName+$("#"+div).attr("rel")+" a").html(text+'&nbsp;<span class="morearrow" style="visibility: hidden;">▼</span>');
}
//updateDateValues('dur-link_''492772000000013031');hideCommonList('durdiv');
function updateDateValues(idName,div){
	$("#"+idName+$("#"+div).attr("rel")).html($("#durtext").val()+'&nbsp;days<span class="morearrow" style="visibility: hidden;">▼</span>');
}

