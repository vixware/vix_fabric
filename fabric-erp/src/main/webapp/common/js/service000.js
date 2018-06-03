/*
* IMPORTANT NOTE: This compressed javascript includes thirdparty javascripts 
* listed at http://writer.zoho.com/public/zohoprojects/Thirdparty/noband
* The original javascript sources are available in the above location.
*/
Calendar = function(_1, _2, _3, _4) {
	this.activeDiv = null;
	this.currentDateEl = null;
	this.getDateStatus = null;
	this.timeout = null;
	this.onSelected = _3 || null;
	this.onClose = _4 || null;
	this.dragging = false;
	this.hidden = false;
	this.minYear = 1970;
	this.maxYear = 2050;
	//this.dateFormat = Calendar._TT["DEF_DATE_FORMAT"];
	//this.ttDateFormat = Calendar._TT["TT_DATE_FORMAT"];
	this.isPopup = true;
	this.weekNumbers = true;
	this.firstDayOfWeek = _1;
	this.showsOtherMonths = false;
	this.dateStr = _2;
	this.ar_days = null;
	this.showsTime = false;
	this.time24 = true;
	this.yearStep = 2;
	this.table = null;
	this.element = null;
	this.tbody = null;
	this.firstdayname = null;
	this.monthsCombo = null;
	this.yearsCombo = null;
	this.hilitedMonth = null;
	this.activeMonth = null;
	this.hilitedYear = null;
	this.activeYear = null;
	this.dateClicked = false;
	if (typeof Calendar._SDN == "undefined") {
		if (typeof Calendar._SDN_len == "undefined") {
			Calendar._SDN_len = 3;
		}
		var ar = new Array();
		for (var i = 8; i > 0;) {
			ar[--i] = Calendar._DN[i].substr(0, Calendar._SDN_len);
		}
		Calendar._SDN = ar;
		if (typeof Calendar._SMN_len == "undefined") {
			Calendar._SMN_len = 3;
		}
		ar = new Array();
		for (var i = 12; i > 0;) {
			ar[--i] = Calendar._MN[i].substr(0, Calendar._SMN_len);
		}
		Calendar._SMN = ar;
	}
};
Calendar._C = null;
Calendar.is_ie = (/msie/i.test(navigator.userAgent) && !/opera/i.test(navigator.userAgent));
Calendar.is_ie5 = (Calendar.is_ie && /msie 5\.0/i.test(navigator.userAgent));
Calendar.is_opera = /opera/i.test(navigator.userAgent);
Calendar.is_khtml = /Konqueror|Safari|KHTML/i.test(navigator.userAgent);
Calendar.getAbsolutePos = function(el) {
	var SL = 0,
	ST = 0;
	var _a = /^div$/i.test(el.tagName);
	if (_a && el.scrollLeft) {
		SL = el.scrollLeft;
	}
	if (_a && el.scrollTop) {
		ST = el.scrollTop;
	}
	var r = {
		x: el.offsetLeft - SL,
		y: el.offsetTop - ST
	};
	if (el.offsetParent) {
		var _c = this.getAbsolutePos(el.offsetParent);
		r.x += _c.x;
		r.y += _c.y;
	}
	return r;
};
Calendar.isRelated = function(el, _e) {
	var _f = _e.relatedTarget;
	if (!_f) {
		var _10 = _e.type;
		if (_10 == "mouseover") {
			_f = _e.fromElement;
		} else {
			if (_10 == "mouseout") {
				_f = _e.toElement;
			}
		}
	}
	while (_f) {
		if (_f == el) {
			return true;
		}
		_f = _f.parentNode;
	}
	return false;
};
Calendar.removeClass = function(el, _12) {
	if (! (el && el.className)) {
		return;
	}
	var cls = el.className.split(" ");
	var ar = new Array();
	for (var i = cls.length; i > 0;) {
		if (cls[--i] != _12) {
			ar[ar.length] = cls[i];
		}
	}
	el.className = ar.join(" ");
};
Calendar.addClass = function(el, _17) {
	Calendar.removeClass(el, _17);
	el.className += " " + _17;
};
Calendar.getElement = function(ev) {
	if (Calendar.is_ie) {
		return window.event.srcElement;
	} else {
		return ev.currentTarget;
	}
};
Calendar.getTargetElement = function(ev) {
	if (Calendar.is_ie) {
		return window.event.srcElement;
	} else {
		return ev.target;
	}
};
Calendar.stopEvent = function(ev) {
	ev || (ev = window.event);
	if (Calendar.is_ie) {
		ev.cancelBubble = true;
		ev.returnValue = false;
	} else {
		ev.preventDefault();
		ev.stopPropagation();
	}
	return false;
};
Calendar.addEvent = function(el, _1c, _1d) {
	if (el.attachEvent) {
		el.attachEvent("on" + _1c, _1d);
	} else {
		if (el.addEventListener) {
			el.addEventListener(_1c, _1d, true);
		} else {
			el["on" + _1c] = _1d;
		}
	}
};
Calendar.removeEvent = function(el, _1f, _20) {
	if (el.detachEvent) {
		el.detachEvent("on" + _1f, _20);
	} else {
		if (el.removeEventListener) {
			el.removeEventListener(_1f, _20, true);
		} else {
			el["on" + _1f] = null;
		}
	}
};
Calendar.createElement = function(_21, _22) {
	var el = null;
	if (document.createElementNS) {
		el = document.createElementNS("http://www.w3.org/1999/xhtml", _21);
	} else {
		el = document.createElement(_21);
	}
	if (typeof _22 != "undefined") {
		_22.appendChild(el);
	}
	return el;
};
Calendar._add_evs = function(el) {
	with(Calendar) {
		addEvent(el, "mouseover", dayMouseOver);
		addEvent(el, "mousedown", dayMouseDown);
		addEvent(el, "mouseout", dayMouseOut);
		if (is_ie) {
			addEvent(el, "dblclick", dayMouseDblClick);
			el.setAttribute("unselectable", true);
		}
	}
};
Calendar.findMonth = function(el) {
	if (typeof el.month != "undefined") {
		return el;
	} else {
		if (typeof el.parentNode.month != "undefined") {
			return el.parentNode;
		}
	}
	return null;
};
Calendar.findYear = function(el) {
	if (typeof el.year != "undefined") {
		return el;
	} else {
		if (typeof el.parentNode.year != "undefined") {
			return el.parentNode;
		}
	}
	return null;
};
Calendar.showMonthsCombo = function() {
	var cal = Calendar._C;
	if (!cal) {
		return false;
	}
	var cal = cal;
	var cd = cal.activeDiv;
	var mc = cal.monthsCombo;
	if (cal.hilitedMonth) {
		Calendar.removeClass(cal.hilitedMonth, "hilite");
	}
	if (cal.activeMonth) {
		Calendar.removeClass(cal.activeMonth, "active");
	}
	var mon = cal.monthsCombo.getElementsByTagName("div")[cal.date.getMonth()];
	Calendar.addClass(mon, "active");
	cal.activeMonth = mon;
	var s = mc.style;
	s.display = "block";
	if (cd.navtype < 0) {
		s.left = cd.offsetLeft + "px";
	} else {
		var mcw = mc.offsetWidth;
		if (typeof mcw == "undefined") {
			mcw = 50;
		}
		s.left = (cd.offsetLeft + cd.offsetWidth - mcw) + "px";
	}
	s.top = (cd.offsetTop + cd.offsetHeight) + "px";
};
Calendar.showYearsCombo = function(fwd) {
	var cal = Calendar._C;
	if (!cal) {
		return false;
	}
	var cal = cal;
	var cd = cal.activeDiv;
	var yc = cal.yearsCombo;
	if (cal.hilitedYear) {
		Calendar.removeClass(cal.hilitedYear, "hilite");
	}
	if (cal.activeYear) {
		Calendar.removeClass(cal.activeYear, "active");
	}
	cal.activeYear = null;
	var Y = cal.date.getFullYear() + (fwd ? 1 : -1);
	var yr = yc.firstChild;
	var _33 = false;
	for (var i = 12; i > 0; --i) {
		if (Y >= cal.minYear && Y <= cal.maxYear) {
			yr.firstChild.data = Y;
			yr.year = Y;
			yr.style.display = "block";
			_33 = true;
		} else {
			yr.style.display = "none";
		}
		yr = yr.nextSibling;
		Y += fwd ? cal.yearStep: -cal.yearStep;
	}
	if (_33) {
		var s = yc.style;
		s.display = "block";
		if (cd.navtype < 0) {
			s.left = cd.offsetLeft + "px";
		} else {
			var ycw = yc.offsetWidth;
			if (typeof ycw == "undefined") {
				ycw = 50;
			}
			s.left = (cd.offsetLeft + cd.offsetWidth - ycw) + "px";
		}
		s.top = (cd.offsetTop + cd.offsetHeight) + "px";
	}
};
Calendar.tableMouseUp = function(ev) {
	var cal = Calendar._C;
	if (!cal) {
		return false;
	}
	if (cal.timeout) {
		clearTimeout(cal.timeout);
	}
	var el = cal.activeDiv;
	if (!el) {
		return false;
	}
	var _3a = Calendar.getTargetElement(ev);
	ev || (ev = window.event);
	Calendar.removeClass(el, "active");
	if (_3a == el || _3a.parentNode == el) {
		Calendar.cellClick(el, ev);
	}
	var mon = Calendar.findMonth(_3a);
	var _3c = null;
	if (mon) {
		_3c = new Date(cal.date);
		if (mon.month != _3c.getMonth()) {
			_3c.setMonth(mon.month);
			cal.setDate(_3c);
			cal.dateClicked = false;
			cal.callHandler();
		}
	} else {
		var _3d = Calendar.findYear(_3a);
		if (_3d) {
			_3c = new Date(cal.date);
			if (_3d.year != _3c.getFullYear()) {
				_3c.setFullYear(_3d.year);
				cal.setDate(_3c);
				cal.dateClicked = false;
				cal.callHandler();
			}
		}
	}
	with(Calendar) {
		removeEvent(document, "mouseup", tableMouseUp);
		removeEvent(document, "mouseover", tableMouseOver);
		removeEvent(document, "mousemove", tableMouseOver);
		cal._hideCombos();
		_C = null;
		return stopEvent(ev);
	}
};
Calendar.tableMouseOver = function(ev) {
	var cal = Calendar._C;
	if (!cal) {
		return;
	}
	var el = cal.activeDiv;
	var _41 = Calendar.getTargetElement(ev);
	if (_41 == el || _41.parentNode == el) {
		Calendar.addClass(el, "hilite active");
		Calendar.addClass(el.parentNode, "rowhilite");
	} else {
		if (typeof el.navtype == "undefined" || (el.navtype != 50 && (el.navtype == 0 || Math.abs(el.navtype) > 2))) {
			Calendar.removeClass(el, "active");
		}
		Calendar.removeClass(el, "hilite");
		Calendar.removeClass(el.parentNode, "rowhilite");
	}
	ev || (ev = window.event);
	if (el.navtype == 50 && _41 != el) {
		var pos = Calendar.getAbsolutePos(el);
		var w = el.offsetWidth;
		var x = ev.clientX;
		var dx;
		var _46 = true;
		if (x > pos.x + w) {
			dx = x - pos.x - w;
			_46 = false;
		} else {
			dx = pos.x - x;
		}
		if (dx < 0) {
			dx = 0;
		}
		var _47 = el._range;
		var _48 = el._current;
		var _49 = Math.floor(dx / 10) % _47.length;
		for (var i = _47.length; --i >= 0;) {
			if (_47[i] == _48) {
				break;
			}
		}
		while (_49-->0) {
			if (_46) {
				if (--i < 0) {
					i = _47.length - 1;
				}
			} else {
				if (++i >= _47.length) {
					i = 0;
				}
			}
		}
		var _4b = _47[i];
		el.firstChild.data = _4b;
		cal.onUpdateTime();
	}
	var mon = Calendar.findMonth(_41);
	if (mon) {
		if (mon.month != cal.date.getMonth()) {
			if (cal.hilitedMonth) {
				Calendar.removeClass(cal.hilitedMonth, "hilite");
			}
			Calendar.addClass(mon, "hilite");
			cal.hilitedMonth = mon;
		} else {
			if (cal.hilitedMonth) {
				Calendar.removeClass(cal.hilitedMonth, "hilite");
			}
		}
	} else {
		if (cal.hilitedMonth) {
			Calendar.removeClass(cal.hilitedMonth, "hilite");
		}
		var _4d = Calendar.findYear(_41);
		if (_4d) {
			if (_4d.year != cal.date.getFullYear()) {
				if (cal.hilitedYear) {
					Calendar.removeClass(cal.hilitedYear, "hilite");
				}
				Calendar.addClass(_4d, "hilite");
				cal.hilitedYear = _4d;
			} else {
				if (cal.hilitedYear) {
					Calendar.removeClass(cal.hilitedYear, "hilite");
				}
			}
		} else {
			if (cal.hilitedYear) {
				Calendar.removeClass(cal.hilitedYear, "hilite");
			}
		}
	}
	return Calendar.stopEvent(ev);
};
Calendar.tableMouseDown = function(ev) {
	if (Calendar.getTargetElement(ev) == Calendar.getElement(ev)) {
		return Calendar.stopEvent(ev);
	}
};
Calendar.calIE = function(ev) {
	if (Calendar.is_ie) {
		Calendar.continuation_for_the_fucking_khtml_browser();
	}
};
Calendar.calDragIt = function(ev) {
	var cal = Calendar._C;
	if (! (cal && cal.dragging)) {
		return false;
	}
	var _52;
	var _53;
	if (Calendar.is_ie) {
		_53 = window.event.clientY + document.body.scrollTop;
		_52 = window.event.clientX + document.body.scrollLeft;
	} else {
		_52 = ev.pageX;
		_53 = ev.pageY;
	}
	cal.hideShowCovered();
	var st = cal.element.style;
	st.left = (_52 - cal.xOffs) + "px";
	st.top = (_53 - cal.yOffs) + "px";
	return Calendar.stopEvent(ev);
};
Calendar.calDragEnd = function(ev) {
	var cal = Calendar._C;
	if (!cal) {
		return false;
	}
	cal.dragging = false;
	with(Calendar) {
		removeEvent(document, "mousemove", calDragIt);
		removeEvent(document, "mouseup", calDragEnd);
		tableMouseUp(ev);
	}
	cal.hideShowCovered();
};
Calendar.dayMouseDown = function(ev) {
	var el = Calendar.getElement(ev);
	if (el.disabled) {
		return false;
	}
	var cal = el.calendar;
	cal.activeDiv = el;
	Calendar._C = cal;
	if (el.navtype != 300) {
		with(Calendar) {
			if (el.navtype == 50) {
				el._current = el.firstChild.data;
				addEvent(document, "mousemove", tableMouseOver);
			} else {
				addEvent(document, Calendar.is_ie5 ? "mousemove": "mouseover", tableMouseOver);
			}
			addClass(el, "hilite active");
			addEvent(document, "mouseup", tableMouseUp);
		}
	} else {
		if (cal.isPopup) {
			cal._dragStart(ev);
		}
	}
	if (el.navtype == -1 || el.navtype == 1) {
		if (cal.timeout) {
			clearTimeout(cal.timeout);
		}
		cal.timeout = setTimeout("Calendar.showMonthsCombo()", 250);
	} else {
		if (el.navtype == -2 || el.navtype == 2) {
			if (cal.timeout) {
				clearTimeout(cal.timeout);
			}
			cal.timeout = setTimeout((el.navtype > 0) ? "Calendar.showYearsCombo(true)": "Calendar.showYearsCombo(false)", 250);
		} else {
			cal.timeout = null;
		}
	}
	return Calendar.stopEvent(ev);
};
Calendar.dayMouseDblClick = function(ev) {
	Calendar.cellClick(Calendar.getElement(ev), ev || window.event);
	if (Calendar.is_ie) {
		document.selection.empty();
	}
};
Calendar.dayMouseOver = function(ev) {
	if (Calendar.is_ie) {
		Calendar.continuation_for_the_fucking_khtml_browser();
	}
	var el = Calendar.getElement(ev);
	if (Calendar.isRelated(el, ev) || Calendar._C || el.disabled) {
		return false;
	}
	if (el.ttip) {
		if (el.ttip.substr(0, 1) == "_") {
			el.ttip = el.caldate.print(el.calendar.ttDateFormat) + el.ttip.substr(1);
		}
		el.calendar.tooltips.firstChild.data = el.ttip;
	}
	if (el.navtype != 300) {
		Calendar.addClass(el, "hilite");
		if (el.caldate) {
			Calendar.addClass(el.parentNode, "rowhilite");
		}
	}
	return Calendar.stopEvent(ev);
};
Calendar.dayMouseOut = function(ev) {
	with(Calendar) {
		var el = getElement(ev);
		if (isRelated(el, ev) || _C || el.disabled) {
			return false;
		}
		removeClass(el, "hilite");
		if (el.caldate) {
			removeClass(el.parentNode, "rowhilite");
		}
		el.calendar.tooltips.firstChild.data = _TT["SEL_DATE"];
		return stopEvent(ev);
	}
};
Calendar.cellClick = function(el, ev) {
	var cal = el.calendar;
	var _62 = false;
	var _63 = false;
	var _64 = null;
	if (typeof el.navtype == "undefined") {
		Calendar.removeClass(cal.currentDateEl, "selected");
		Calendar.addClass(el, "selected");
		_62 = (cal.currentDateEl == el);
		if (!_62) {
			cal.currentDateEl = el;
		}
		cal.date = new Date(el.caldate);
		_64 = cal.date;
		_63 = true;
		if (! (cal.dateClicked = !el.otherMonth)) {
			cal._init(cal.firstDayOfWeek, _64);
		}
	} else {
		if (el.navtype == 200) {
			Calendar.removeClass(el, "hilite");
			cal.callCloseHandler();
			return;
		}
		_64 = (el.navtype == 0) ? new Date() : new Date(cal.date);
		cal.dateClicked = false;
		var _65 = _64.getFullYear();
		var mon = _64.getMonth();
		function setMonth(m) {
			var day = _64.getDate();
			var max = _64.getMonthDays(m);
			if (day > max) {
				_64.setDate(max);
			}
			_64.setMonth(m);
		}
		switch (el.navtype) {
		case - 2 : if (_65 > cal.minYear) {
				_64.setFullYear(_65 - 1);
			}
			break;
		case - 1 : if (mon > 0) {
				setMonth(mon - 1);
			} else {
				if (_65-->cal.minYear) {
					_64.setFullYear(_65);
					setMonth(11);
				}
			}
			break;
		case 1:
			if (mon < 11) {
				setMonth(mon + 1);
			} else {
				if (_65 < cal.maxYear) {
					_64.setFullYear(_65 + 1);
					setMonth(0);
				}
			}
			break;
		case 2:
			if (_65 < cal.maxYear) {
				_64.setFullYear(_65 + 1);
			}
			break;
		case 100:
			cal.setFirstDayOfWeek(el.fdow);
			return;
		case 50:
			var _6a = el._range;
			var _6b = el.firstChild.data;
			for (var i = _6a.length; --i >= 0;) {
				if (_6a[i] == _6b) {
					break;
				}
			}
			if (ev && ev.shiftKey) {
				if (--i < 0) {
					i = _6a.length - 1;
				}
			} else {
				if (++i >= _6a.length) {
					i = 0;
				}
			}
			var _6d = _6a[i];
			el.firstChild.data = _6d;
			cal.onUpdateTime();
			return;
		case 0:
			if ((typeof cal.getDateStatus == "function") && cal.getDateStatus(_64, _64.getFullYear(), _64.getMonth(), _64.getDate())) {
				return false;
			}
			break;
		}
		if (!_64.equalsTo(cal.date)) {
			cal.setDate(_64);
			_63 = true;
		}
	}
	if (_63) {
		cal.callHandler();
	}
	if (_62) {
		Calendar.removeClass(el, "hilite");
		cal.callCloseHandler();
	}
};
Calendar.prototype.create = function(_6e) {
	var _6f = null;
	if (!_6e) {
		_6f = document.getElementsByTagName("body")[0];
		this.isPopup = true;
	} else {
		_6f = _6e;
		this.isPopup = false;
	}
	this.date = this.dateStr ? new Date(this.dateStr) : new Date();
	var _70 = Calendar.createElement("table");
	this.table = _70;
	_70.cellSpacing = 0;
	_70.cellPadding = 0;
	_70.calendar = this;
	Calendar.addEvent(_70, "mousedown", Calendar.tableMouseDown);
	var div = Calendar.createElement("div");
	this.element = div;
	div.className = "calendar";
	if (this.isPopup) {
		div.style.position = "absolute";
		div.style.display = "none";
	}
	div.appendChild(_70);
	Calendar.addEvent(div, "mouseover", Calendar.calIE);
	var _72 = Calendar.createElement("thead", _70);
	var _73 = null;
	var row = null;
	var cal = this;
	var hh = function(_77, cs, _79) {
		_73 = Calendar.createElement("td", row);
		_73.colSpan = cs;
		_73.className = "calButton";
		if (_79 != 0 && Math.abs(_79) <= 2) {
			_73.className += " nav";
		}
		Calendar._add_evs(_73);
		_73.calendar = cal;
		_73.navtype = _79;
		if (_77.substr(0, 1) != "&") {
			_73.appendChild(document.createTextNode(_77));
		} else {
			_73.innerHTML = _77;
		}
		return _73;
	};
	row = Calendar.createElement("tr", _72);
	var _7a = 7; (this.isPopup) && --_7a; (this.weekNumbers) && ++_7a;
	this.title = hh("", _7a, 300);
	this.title.className = "title";
	if (this.isPopup) {
		this.title.ttip = Calendar._TT["DRAG_TO_MOVE"];
		this.title.style.cursor = "move";
		hh("&#x00d7;", 1, 200).ttip = Calendar._TT["CLOSE"];
	}
	row = Calendar.createElement("tr", _72);
	row.className = "headrow";
	this._nav_py = hh("&#x00ab;", 1, -2);
	this._nav_py.ttip = Calendar._TT["PREV_YEAR"];
	this._nav_pm = hh("&#x2039;", 1, -1);
	this._nav_pm.ttip = Calendar._TT["PREV_MONTH"];
	this._nav_now = hh(Calendar._TT["TODAY"], this.weekNumbers ? 4 : 3, 0);
	this._nav_now.ttip = Calendar._TT["GO_TODAY"];
	this._nav_nm = hh("&#x203a;", 1, 1);
	this._nav_nm.ttip = Calendar._TT["NEXT_MONTH"];
	this._nav_ny = hh("&#x00bb;", 1, 2);
	this._nav_ny.ttip = Calendar._TT["NEXT_YEAR"];
	row = Calendar.createElement("tr", _72);
	row.className = "daynames";
	if (this.weekNumbers) {
		_73 = Calendar.createElement("td", row);
		_73.className = "name wn";
		_73.appendChild(document.createTextNode(Calendar._TT["WK"]));
	}
	for (var i = 7; i > 0; --i) {
		_73 = Calendar.createElement("td", row);
		_73.appendChild(document.createTextNode(""));
		if (!i) {
			_73.navtype = 100;
			_73.calendar = this;
			Calendar._add_evs(_73);
		}
	}
	this.firstdayname = (this.weekNumbers) ? row.firstChild.nextSibling: row.firstChild;
	this._displayWeekdays();
	var _7c = Calendar.createElement("tbody", _70);
	this.tbody = _7c;
	for (i = 6; i > 0; --i) {
		row = Calendar.createElement("tr", _7c);
		if (this.weekNumbers) {
			_73 = Calendar.createElement("td", row);
			_73.appendChild(document.createTextNode(""));
		}
		for (var j = 7; j > 0; --j) {
			_73 = Calendar.createElement("td", row);
			_73.appendChild(document.createTextNode(""));
			_73.calendar = this;
			Calendar._add_evs(_73);
		}
	}
	if (this.showsTime) {
		row = Calendar.createElement("tr", _7c);
		row.className = "time";
		_73 = Calendar.createElement("td", row);
		_73.className = "time";
		_73.colSpan = 2;
		_73.innerHTML = Calendar._TT["TIME"] || "&nbsp;";
		_73 = Calendar.createElement("td", row);
		_73.className = "time";
		_73.colSpan = this.weekNumbers ? 4 : 3; (function() {
			function makeTimePart(_7e, _7f, _80, _81) {
				var _82 = Calendar.createElement("span", _73);
				_82.className = _7e;
				_82.appendChild(document.createTextNode(_7f));
				_82.calendar = cal;
				_82.ttip = Calendar._TT["TIME_PART"];
				_82.navtype = 50;
				_82._range = [];
				if (typeof _80 != "number") {
					_82._range = _80;
				} else {
					for (var i = _80; i <= _81; ++i) {
						var txt;
						if (i < 10 && _81 >= 10) {
							txt = "0" + i;
						} else {
							txt = "" + i;
						}
						_82._range[_82._range.length] = txt;
					}
				}
				Calendar._add_evs(_82);
				return _82;
			}
			var hrs = cal.date.getHours();
			var _86 = cal.date.getMinutes();
			var t12 = !cal.time24;
			var pm = (hrs > 12);
			if (t12 && pm) {
				hrs -= 12;
			}
			var H = makeTimePart("hour", hrs, t12 ? 1 : 0, t12 ? 12 : 23);
			var _8a = Calendar.createElement("span", _73);
			_8a.appendChild(document.createTextNode(":"));
			_8a.className = "colon";
			var M = makeTimePart("minute", _86, 0, 59);
			var AP = null;
			_73 = Calendar.createElement("td", row);
			_73.className = "time";
			_73.colSpan = 2;
			if (t12) {
				AP = makeTimePart("ampm", pm ? "pm": "am", ["am", "pm"]);
			} else {
				_73.innerHTML = "&nbsp;";
			}
			cal.onSetTime = function() {
				var hrs = this.date.getHours();
				var _8e = this.date.getMinutes();
				var pm = (hrs > 12);
				if (pm && t12) {
					hrs -= 12;
				}
				H.firstChild.data = (hrs < 10) ? ("0" + hrs) : hrs;
				M.firstChild.data = (_8e < 10) ? ("0" + _8e) : _8e;
				if (t12) {
					AP.firstChild.data = pm ? "pm": "am";
				}
			};
			cal.onUpdateTime = function() {
				var _90 = this.date;
				var h = parseInt(H.firstChild.data, 10);
				if (t12) {
					if (/pm/i.test(AP.firstChild.data) && h < 12) {
						h += 12;
					} else {
						if (/am/i.test(AP.firstChild.data) && h == 12) {
							h = 0;
						}
					}
				}
				var d = _90.getDate();
				var m = _90.getMonth();
				var y = _90.getFullYear();
				_90.setHours(h);
				_90.setMinutes(parseInt(M.firstChild.data, 10));
				_90.setFullYear(y);
				_90.setMonth(m);
				_90.setDate(d);
				this.dateClicked = false;
				this.callHandler();
			};
		})();
	} else {
		this.onSetTime = this.onUpdateTime = function() {};
	}
	var _95 = Calendar.createElement("tfoot", _70);
	row = Calendar.createElement("tr", _95);
	row.className = "footrow";
	_73 = hh(Calendar._TT["SEL_DATE"], this.weekNumbers ? 8 : 7, 300);
	_73.className = "ttip";
	if (this.isPopup) {
		_73.ttip = Calendar._TT["DRAG_TO_MOVE"];
		_73.style.cursor = "move";
	}
	this.tooltips = _73;
	div = Calendar.createElement("div", this.element);
	this.monthsCombo = div;
	div.className = "combo";
	for (i = 0; i < Calendar._MN.length; ++i) {
		var mn = Calendar.createElement("div");
		mn.className = Calendar.is_ie ? "label-IEfix": "label";
		mn.month = i;
		mn.appendChild(document.createTextNode(Calendar._SMN[i]));
		div.appendChild(mn);
	}
	div = Calendar.createElement("div", this.element);
	this.yearsCombo = div;
	div.className = "combo";
	for (i = 12; i > 0; --i) {
		var yr = Calendar.createElement("div");
		yr.className = Calendar.is_ie ? "label-IEfix": "label";
		yr.appendChild(document.createTextNode(""));
		div.appendChild(yr);
	}
	this._init(this.firstDayOfWeek, this.date);
	_6f.appendChild(this.element);
};
Calendar._keyEvent = function(ev) {
	if (!window.calendar) {
		return false;
	} (Calendar.is_ie) && (ev = window.event);
	var cal = window.calendar;
	var act = (Calendar.is_ie || ev.type == "keypress");
	if (ev.ctrlKey) {
		switch (ev.keyCode) {
		case 37:
			act && Calendar.cellClick(cal._nav_pm);
			break;
		case 38:
			act && Calendar.cellClick(cal._nav_py);
			break;
		case 39:
			act && Calendar.cellClick(cal._nav_nm);
			break;
		case 40:
			act && Calendar.cellClick(cal._nav_ny);
			break;
		default:
			return false;
		}
	} else {
		switch (ev.keyCode) {
		case 32:
			Calendar.cellClick(cal._nav_now);
			break;
		case 27:
			act && cal.callCloseHandler();
			break;
		case 37:
		case 38:
		case 39:
		case 40:
			if (act) {
				var _9b = cal.date.getDate() - 1;
				var el = cal.currentDateEl;
				var ne = null;
				var _9e = (ev.keyCode == 37) || (ev.keyCode == 38);
				switch (ev.keyCode) {
				case 37:
					(--_9b >= 0) && (ne = cal.ar_days[_9b]);
					break;
				case 38:
					_9b -= 7; (_9b >= 0) && (ne = cal.ar_days[_9b]);
					break;
				case 39:
					(++_9b < cal.ar_days.length) && (ne = cal.ar_days[_9b]);
					break;
				case 40:
					_9b += 7; (_9b < cal.ar_days.length) && (ne = cal.ar_days[_9b]);
					break;
				}
				if (!ne) {
					if (_9e) {
						Calendar.cellClick(cal._nav_pm);
					} else {
						Calendar.cellClick(cal._nav_nm);
					}
					_9b = (_9e) ? cal.date.getMonthDays() : 1;
					el = cal.currentDateEl;
					ne = cal.ar_days[_9b - 1];
				}
				Calendar.removeClass(el, "selected");
				Calendar.addClass(ne, "selected");
				cal.date = new Date(ne.caldate);
				cal.callHandler();
				cal.currentDateEl = ne;
			}
			break;
		case 13:
			if (act) {
				cal.callHandler();
				cal.hide();
			}
			break;
		default:
			return false;
		}
	}
	return Calendar.stopEvent(ev);
};
Calendar.prototype._init = function(_9f, _a0) {
	var _a1 = new Date();
	this.table.style.visibility = "hidden";
	var _a2 = _a0.getFullYear();
	if (_a2 < this.minYear) {
		_a2 = this.minYear;
		_a0.setFullYear(_a2);
	} else {
		if (_a2 > this.maxYear) {
			_a2 = this.maxYear;
			_a0.setFullYear(_a2);
		}
	}
	this.firstDayOfWeek = _9f;
	this.date = new Date(_a0);
	var _a3 = _a0.getMonth();
	var _a4 = _a0.getDate();
	var _a5 = _a0.getMonthDays();
	_a0.setDate(1);
	var _a6 = (_a0.getDay() - this.firstDayOfWeek) % 7;
	if (_a6 < 0) {
		_a6 += 7;
	}
	if (_a6 != 0) {
		_a6 = -_a6;
	}
	_a0.setDate(_a6);
	_a0.setDate(_a0.getDate() + 1);
	var row = this.tbody.firstChild;
	var MN = Calendar._SMN[_a3];
	var _a9 = new Array();
	var _aa = Calendar._TT["WEEKEND"];
	for (var i = 0; i < 6; ++i, row = row.nextSibling) {
		var _ac = row.firstChild;
		if (this.weekNumbers) {
			_ac.className = "day wn";
			_ac.firstChild.data = _a0.getWeekNumber();
			_ac = _ac.nextSibling;
		}
		row.className = "daysrow";
		var _ad = false;
		for (var j = 0; j < 7; ++j, _ac = _ac.nextSibling, _a0.setDate(_a0.getDate() + 1)) {
			var _af = _a0.getDate();
			var _b0 = _a0.getDay();
			_ac.className = "day";
			var _b1 = (_a0.getMonth() == _a3);
			if (!_b1) {
				if (this.showsOtherMonths) {
					_ac.className += " othermonth";
					_ac.otherMonth = true;
				} else {
					_ac.className = "emptycell";
					_ac.innerHTML = "&nbsp;";
					_ac.disabled = true;
					continue;
				}
			} else {
				_ac.otherMonth = false;
				_ad = true;
			}
			_ac.disabled = false;
			_ac.firstChild.data = _af;
			if (typeof this.getDateStatus == "function") {
				var _b2 = this.getDateStatus(_a0, _a2, _a3, _af);
				if (_b2 === true) {
					_ac.className += " disabled";
					_ac.disabled = true;
				} else {
					if (/disabled/i.test(_b2)) {
						_ac.disabled = true;
					}
					_ac.className += " " + _b2;
				}
			}
			try {
				if (eventdates && eventdates != null && eventdates.length) {
					for (eday = 0; eday < eventdates.length; eday++) {
						if (_a0.getFullYear() == eval(eventyear) && _a0.getMonth() == eval(eventmonth) - 1 && _af == eventdates[eday]) {
							_ac.className += " hasevent";
						}
					}
				}
			} catch(e) {}
			if (!_ac.disabled) {
				_a9[_a9.length] = _ac;
				_ac.caldate = new Date(_a0);
				_ac.ttip = "_";
				if (_b1 && _af == _a4) {
					_ac.className += " selected";
					this.currentDateEl = _ac;
				}
				if (_a0.getFullYear() == _a1.getFullYear() && _a0.getMonth() == _a1.getMonth() && _af == _a1.getDate()) {
					_ac.className += " today";
					_ac.ttip += Calendar._TT["PART_TODAY"];
				}
				if (_aa.indexOf(_b0.toString()) != -1) {
					_ac.className += _ac.otherMonth ? " oweekend": " weekend";
				}
			}
		}
		if (! (_ad || this.showsOtherMonths)) {
			row.className = "emptyrow";
		}
	}
	this.ar_days = _a9;
	this.title.firstChild.data = Calendar._MN[_a3] + ", " + _a2;
	this.onSetTime();
	this.table.style.visibility = "visible";
};
Calendar.prototype.setDate = function(_b3) {
	if (!_b3.equalsTo(this.date)) {
		this._init(this.firstDayOfWeek, _b3);
	}
};
Calendar.prototype.refresh = function() {
	this._init(this.firstDayOfWeek, this.date);
};
Calendar.prototype.setFirstDayOfWeek = function(_b4) {
	this._init(_b4, this.date);
	this._displayWeekdays();
};
Calendar.prototype.setDateStatusHandler = Calendar.prototype.setDisabledHandler = function(_b5) {
	this.getDateStatus = _b5;
};
Calendar.prototype.setRange = function(a, z) {
	this.minYear = a;
	this.maxYear = z;
};
Calendar.prototype.callHandler = function() {
	if (this.onSelected) {
		this.onSelected(this, this.date.print(this.dateFormat));
	}
};
Calendar.prototype.callCloseHandler = function() {
	if (this.onClose) {
		this.onClose(this);
	}
	this.hideShowCovered();
};
Calendar.prototype.destroy = function() {
	var el = this.element.parentNode;
	el.removeChild(this.element);
	Calendar._C = null;
	window.calendar = null;
};
Calendar.prototype.reparent = function(_b9) {
	var el = this.element;
	el.parentNode.removeChild(el);
	_b9.appendChild(el);
};
Calendar._checkCalendar = function(ev) {
	if (!window.calendar) {
		return false;
	}
	var el = Calendar.is_ie ? Calendar.getElement(ev) : Calendar.getTargetElement(ev);
	for (; el != null && el != calendar.element; el = el.parentNode) {}
	if (el == null) {
		window.calendar.callCloseHandler();
		return Calendar.stopEvent(ev);
	}
};
Calendar.prototype.show = function() {
	var _bd = this.table.getElementsByTagName("tr");
	for (var i = _bd.length; i > 0;) {
		var row = _bd[--i];
		Calendar.removeClass(row, "rowhilite");
		var _c0 = row.getElementsByTagName("td");
		for (var j = _c0.length; j > 0;) {
			var _c2 = _c0[--j];
			Calendar.removeClass(_c2, "hilite");
			Calendar.removeClass(_c2, "active");
		}
	}
	this.element.style.display = "block";
	this.element.style.zIndex = 2;
	this.hidden = false;
	if (this.isPopup) {
		window.calendar = this;
		Calendar.addEvent(document, "keydown", Calendar._keyEvent);
		Calendar.addEvent(document, "keypress", Calendar._keyEvent);
		Calendar.addEvent(document, "mousedown", Calendar._checkCalendar);
	}
	this.hideShowCovered();
};
Calendar.prototype.hide = function() {
	if (this.isPopup) {
		Calendar.removeEvent(document, "keydown", Calendar._keyEvent);
		Calendar.removeEvent(document, "keypress", Calendar._keyEvent);
		Calendar.removeEvent(document, "mousedown", Calendar._checkCalendar);
	}
	this.element.style.display = "none";
	this.hidden = true;
	this.hideShowCovered();
};
Calendar.prototype.showAt = function(x, y) {
	var s = this.element.style;
	s.left = x + "px";
	s.top = y + "px";
	this.show();
};
Calendar.prototype.showAtElement = function(el, _c7) {
	var _c8 = this;
	var p = Calendar.getAbsolutePos(el);
	if (!_c7 || typeof _c7 != "string") {
		this.showAt(p.x, p.y + el.offsetHeight);
		return true;
	}
	function fixPosition(box) {
		if (box.x < 0) {
			box.x = 0;
		}
		if (box.y < 0) {
			box.y = 0;
		}
		var cp = document.createElement("div");
		var s = cp.style;
		s.position = "absolute";
		s.right = s.bottom = s.width = s.height = "0px";
		document.body.appendChild(cp);
		var br = Calendar.getAbsolutePos(cp);
		document.body.removeChild(cp);
		if (Calendar.is_ie) {
			br.y += document.documentElement.scrollTop;
			br.x += document.documentElement.scrollLeft;
		} else {
			br.y += window.scrollY;
			br.x += window.scrollX;
		}
		var tmp = box.x + box.width - br.x;
		if (tmp > 0) {
			box.x -= tmp;
		}
		tmp = box.y + box.height - br.y;
		if (tmp > 0) {
			box.y -= tmp;
		}
	}
	this.element.style.display = "block";
	Calendar.continuation_for_the_fucking_khtml_browser = function() {
		var w = _c8.element.offsetWidth;
		var h = _c8.element.offsetHeight;
		_c8.element.style.display = "none";
		var _d1 = _c7.substr(0, 1);
		var _d2 = "l";
		if (_c7.length > 1) {
			_d2 = _c7.substr(1, 1);
		}
		switch (_d1) {
		case "T":
			p.y -= h;
			break;
		case "B":
			p.y += el.offsetHeight;
			break;
		case "C":
			p.y += (el.offsetHeight - h) / 2;
			break;
		case "t":
			p.y += el.offsetHeight - h;
			break;
		case "b":
			break;
		}
		switch (_d2) {
		case "L":
			p.x -= w;
			break;
		case "R":
			p.x += el.offsetWidth;
			break;
		case "C":
			p.x += (el.offsetWidth - w) / 2;
			break;
		case "r":
			p.x += el.offsetWidth - w;
			break;
		case "l":
			break;
		}
		p.width = w;
		p.height = h + 40;
		_c8.monthsCombo.style.display = "none";
		fixPosition(p);
		_c8.showAt(p.x, p.y);
	};
	if (Calendar.is_khtml) {
		setTimeout("Calendar.continuation_for_the_fucking_khtml_browser()", 10);
	} else {
		Calendar.continuation_for_the_fucking_khtml_browser();
	}
};
Calendar.prototype.setDateFormat = function(str) {
	this.dateFormat = str;
};
Calendar.prototype.setTtDateFormat = function(str) {
	this.ttDateFormat = str;
};
Calendar.prototype.parseDate = function(str, fmt) {
	var y = 0;
	var m = -1;
	var d = 0;
	var a = str.split(/\W+/);
	if (!fmt) {
		fmt = this.dateFormat;
	}
	var b = fmt.match(/%./g);
	var i = 0,
	j = 0;
	var hr = 0;
	var min = 0;
	for (i = 0; i < a.length; ++i) {
		if (!a[i]) {
			continue;
		}
		switch (b[i]) {
		case "%d":
		case "%e":
			d = parseInt(a[i], 10);
			break;
		case "%m":
			m = parseInt(a[i], 10) - 1;
			break;
		case "%Y":
		case "%y":
			y = parseInt(a[i], 10); (y < 100) && (y += (y > 29) ? 1900 : 2000);
			break;
		case "%b":
		case "%B":
			for (j = 0; j < 12; ++j) {
				if (Calendar._MN[j].substr(0, a[i].length).toLowerCase() == a[i].toLowerCase()) {
					m = j;
					break;
				}
			}
			break;
		case "%H":
		case "%I":
		case "%k":
		case "%l":
			hr = parseInt(a[i], 10);
			break;
		case "%P":
		case "%p":
			if (/pm/i.test(a[i]) && hr < 12) {
				hr += 12;
			}
			break;
		case "%M":
			min = parseInt(a[i], 10);
			break;
		}
	}
	if (y != 0 && m != -1 && d != 0) {
		this.setDate(new Date(y, m, d, hr, min, 0));
		return;
	}
	y = 0;
	m = -1;
	d = 0;
	for (i = 0; i < a.length; ++i) {
		if (a[i].search(/[a-zA-Z]+/) != -1) {
			var t = -1;
			for (j = 0; j < 12; ++j) {
				if (Calendar._MN[j].substr(0, a[i].length).toLowerCase() == a[i].toLowerCase()) {
					t = j;
					break;
				}
			}
			if (t != -1) {
				if (m != -1) {
					d = m + 1;
				}
				m = t;
			}
		} else {
			if (parseInt(a[i], 10) <= 12 && m == -1) {
				m = a[i] - 1;
			} else {
				if (parseInt(a[i], 10) > 31 && y == 0) {
					y = parseInt(a[i], 10); (y < 100) && (y += (y > 29) ? 1900 : 2000);
				} else {
					if (d == 0) {
						d = a[i];
					}
				}
			}
		}
	}
	if (y == 0) {
		var _e1 = new Date();
		y = _e1.getFullYear();
	}
	if (m != -1 && d != 0) {
		this.setDate(new Date(y, m, d, hr, min, 0));
	}
};
Calendar.prototype.hideShowCovered = function() {
	var _e2 = this;
	Calendar.continuation_for_the_fucking_khtml_browser = function() {
		function getVisib(obj) {
			var _e4 = obj.style.visibility;
			if (!_e4) {
				if (document.defaultView && typeof(document.defaultView.getComputedStyle) == "function") {
					if (!Calendar.is_khtml) {
						_e4 = document.defaultView.getComputedStyle(obj, "").getPropertyValue("visibility");
					} else {
						_e4 = "";
					}
				} else {
					if (obj.currentStyle) {
						_e4 = obj.currentStyle.visibility;
					} else {
						_e4 = "";
					}
				}
			}
			return _e4;
		}
		var _e5 = new Array("applet", "iframe", "select");
		var el = _e2.element;
		var p = Calendar.getAbsolutePos(el);
		var EX1 = p.x;
		var EX2 = el.offsetWidth + EX1;
		var EY1 = p.y;
		var EY2 = el.offsetHeight + EY1;
		for (var k = _e5.length; k > 0;) {
			var ar = document.getElementsByTagName(_e5[--k]);
			var cc = null;
			for (var i = ar.length; i > 0;) {
				cc = ar[--i];
				p = Calendar.getAbsolutePos(cc);
				var CX1 = p.x;
				var CX2 = cc.offsetWidth + CX1;
				var CY1 = p.y;
				var CY2 = cc.offsetHeight + CY1;
				if (_e2.hidden || (CX1 > EX2) || (CX2 < EX1) || (CY1 > EY2) || (CY2 < EY1)) {
					if (!cc.__msh_save_visibility) {
						cc.__msh_save_visibility = getVisib(cc);
					}
					cc.style.visibility = cc.__msh_save_visibility;
				} else {
					if (!cc.__msh_save_visibility) {
						cc.__msh_save_visibility = getVisib(cc);
					}
					cc.style.visibility = "hidden";
				}
			}
		}
	};
	if (Calendar.is_khtml) {
		setTimeout("Calendar.continuation_for_the_fucking_khtml_browser()", 10);
	} else {
		Calendar.continuation_for_the_fucking_khtml_browser();
	}
};
Calendar.prototype._displayWeekdays = function() {
	var _f4 = this.firstDayOfWeek;
	var _f5 = this.firstdayname;
	var _f6 = Calendar._TT["WEEKEND"];
	for (var i = 0; i < 7; ++i) {
		_f5.className = "day name";
		var _f8 = (i + _f4) % 7;
		if (i) {
			_f5.ttip = Calendar._TT["DAY_FIRST"].replace("%s", Calendar._DN[_f8]);
			_f5.navtype = 100;
			_f5.calendar = this;
			_f5.fdow = _f8;
			Calendar._add_evs(_f5);
		}
		if (_f6.indexOf(_f8.toString()) != -1) {
			Calendar.addClass(_f5, "weekend");
		}
		_f5.firstChild.data = Calendar._SDN[(i + _f4) % 7];
		_f5 = _f5.nextSibling;
	}
};
Calendar.prototype._hideCombos = function() {
	this.monthsCombo.style.display = "none";
	this.yearsCombo.style.display = "none";
};
Calendar.prototype._dragStart = function(ev) {
	if (this.dragging) {
		return;
	}
	this.dragging = true;
	var _fa;
	var _fb;
	if (Calendar.is_ie) {
		_fb = window.event.clientY + document.body.scrollTop;
		_fa = window.event.clientX + document.body.scrollLeft;
	} else {
		_fb = ev.clientY + window.scrollY;
		_fa = ev.clientX + window.scrollX;
	}
	var st = this.element.style;
	this.xOffs = _fa - parseInt(st.left);
	this.yOffs = _fb - parseInt(st.top);
	with(Calendar) {
		addEvent(document, "mousemove", calDragIt);
		addEvent(document, "mouseup", calDragEnd);
	}
};
Date._MD = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
Date.SECOND = 1000;
Date.MINUTE = 60 * Date.SECOND;
Date.HOUR = 60 * Date.MINUTE;
Date.DAY = 24 * Date.HOUR;
Date.WEEK = 7 * Date.DAY;
Date.prototype.getMonthDays = function(_fd) {
	var _fe = this.getFullYear();
	if (typeof _fd == "undefined") {
		_fd = this.getMonth();
	}
	if (((0 == (_fe % 4)) && ((0 != (_fe % 100)) || (0 == (_fe % 400)))) && _fd == 1) {
		return 29;
	} else {
		return Date._MD[_fd];
	}
};
Date.prototype.getDayOfYear = function() {
	var now = new Date(this.getFullYear(), this.getMonth(), this.getDate(), 0, 0, 0);
	var then = new Date(this.getFullYear(), 0, 0, 0, 0, 0);
	var time = now - then;
	return Math.floor(time / Date.DAY);
};
Date.prototype.getWeekNumber = function() {
	var d = new Date(this.getFullYear(), this.getMonth(), this.getDate(), 0, 0, 0);
	var DoW = d.getDay();
	d.setDate(d.getDate() - (DoW + 6) % 7 + 3);
	var ms = d.valueOf();
	d.setMonth(0);
	d.setDate(4);
	return Math.round((ms - d.valueOf()) / (7 * 86400000)) + 1;
};
Date.prototype.equalsTo = function(date) {
	return ((this.getFullYear() == date.getFullYear()) && (this.getMonth() == date.getMonth()) && (this.getDate() == date.getDate()) && (this.getHours() == date.getHours()) && (this.getMinutes() == date.getMinutes()));
};
Date.prototype.print = function(str) {
	var m = this.getMonth();
	var d = this.getDate();
	var y = this.getFullYear();
	var wn = this.getWeekNumber();
	var w = this.getDay();
	var s = {};
	var hr = this.getHours();
	var pm = (hr >= 12);
	var ir = (pm) ? (hr - 12) : hr;
	var dy = this.getDayOfYear();
	if (ir == 0) {
		ir = 12;
	}
	var min = this.getMinutes();
	var sec = this.getSeconds();
	s["%a"] = Calendar._SDN[w];
	s["%A"] = Calendar._DN[w];
	s["%b"] = Calendar._SMN[m];
	s["%B"] = Calendar._MN[m];
	s["%C"] = 1 + Math.floor(y / 100);
	s["%d"] = (d < 10) ? ("0" + d) : d;
	s["%e"] = d;
	s["%H"] = (hr < 10) ? ("0" + hr) : hr;
	s["%I"] = (ir < 10) ? ("0" + ir) : ir;
	s["%j"] = (dy < 100) ? ((dy < 10) ? ("00" + dy) : ("0" + dy)) : dy;
	s["%k"] = hr;
	s["%l"] = ir;
	s["%m"] = (m < 9) ? ("0" + (1 + m)) : (1 + m);
	s["%M"] = (min < 10) ? ("0" + min) : min;
	s["%n"] = "\n";
	s["%p"] = pm ? "PM": "AM";
	s["%P"] = pm ? "pm": "am";
	s["%s"] = Math.floor(this.getTime() / 1000);
	s["%S"] = (sec < 10) ? ("0" + sec) : sec;
	s["%t"] = "\t";
	s["%U"] = s["%W"] = s["%V"] = (wn < 10) ? ("0" + wn) : wn;
	s["%u"] = w + 1;
	s["%w"] = w;
	s["%y"] = ("" + y).substr(2, 2);
	s["%Y"] = y;
	s["%%"] = "%";
	var re = /%./g;
	if (!Calendar.is_ie5) {
		return str.replace(re,
		function(par) {
			return s[par] || par;
		});
	}
	var a = str.match(re);
	for (var i = 0; i < a.length; i++) {
		var tmp = s[a[i]];
		if (tmp) {
			re = new RegExp(a[i], "g");
			str = str.replace(re, tmp);
		}
	}
	return str;
};
Date.prototype.__msh_oldSetFullYear = Date.prototype.setFullYear;
Date.prototype.setFullYear = function(y) {
	var d = new Date(this);
	d.__msh_oldSetFullYear(y);
	if (d.getMonth() != this.getMonth()) {
		this.setDate(28);
	}
	this.__msh_oldSetFullYear(y);
};
window.calendar = null;
Calendar.setup = function(_11a) {
	function param_default(_11b, def) {
		if (typeof _11a[_11b] == "undefined") {
			_11a[_11b] = def;
		}
	}
	param_default("inputField", null);
	param_default("displayArea", null);
	param_default("button", null);
	param_default("eventName", "click");
	param_default("ifFormat", "%Y/%m/%d");
	param_default("daFormat", "%Y/%m/%d");
	param_default("singleClick", true);
	param_default("disableFunc", null);
	param_default("dateStatusFunc", _11a["disableFunc"]);
	param_default("firstDay", 0);
	param_default("align", "Br");
	param_default("range", [1900, 2999]);
	param_default("weekNumbers", true);
	param_default("flat", null);
	param_default("flatCallback", null);
	param_default("onSelect", null);
	param_default("onClose", null);
	param_default("onUpdate", null);
	param_default("date", null);
	param_default("showsTime", false);
	param_default("timeFormat", "24");
	param_default("electric", true);
	param_default("step", 2);
	param_default("position", null);
	param_default("cache", false);
	param_default("showOthers", false);
	var tmp = ["inputField", "displayArea", "button"];
	for (var i in tmp) {
		if (typeof _11a[tmp[i]] == "string") {
			_11a[tmp[i]] = document.getElementById(_11a[tmp[i]]);
		}
	}
	if (! (_11a.flat || _11a.inputField || _11a.displayArea || _11a.button)) {
		alert("Calendar.setup:\n  Nothing to setup (no fields found).  Please check your code");
		return false;
	}
	function onSelect(cal) {
		var p = cal.params;
		var _121 = (cal.dateClicked || p.electric);
		if (_121 && p.flat) {
			if (typeof p.flatCallback == "function") {
				p.flatCallback(cal);
			} else {
				alert("No flatCallback given -- doing nothing.");
			}
			return false;
		}
		if (_121 && p.inputField) {
			p.inputField.value = cal.date.print(p.ifFormat);
			if (typeof p.inputField.onchange == "function") {
				p.inputField.onchange();
			}
		}
		if (_121 && p.displayArea) {
			p.displayArea.innerHTML = cal.date.print(p.daFormat);
		}
		if (_121 && p.singleClick && cal.dateClicked) {
			cal.callCloseHandler();
		}
		if (_121 && typeof p.onUpdate == "function") {
			p.onUpdate(cal);
		}
	}
	if (_11a.flat != null) {
		if (typeof _11a.flat == "string") {
			_11a.flat = document.getElementById(_11a.flat);
		}
		if (!_11a.flat) {
			alert("Calendar.setup:\n  Flat specified but can't find parent.");
			return false;
		}
		var cal = new Calendar(_11a.firstDay, _11a.date, _11a.onSelect || onSelect);
		cal.showsTime = _11a.showsTime;
		cal.time24 = (_11a.timeFormat == "24");
		cal.params = _11a;
		cal.weekNumbers = _11a.weekNumbers;
		cal.setRange(_11a.range[0], _11a.range[1]);
		cal.setDateStatusHandler(_11a.dateStatusFunc);
		cal.create(_11a.flat);
		cal.show();
		return cal;
	}
	function showCalendar() {
		var _123 = _11a.inputField || _11a.displayArea;
		var _124 = _11a.inputField ? _11a.ifFormat: _11a.daFormat;
		var _125 = false;
		var cal = window.calendar;
		if (! (cal && _11a.cache)) {
			window.calendar = cal = new Calendar(_11a.firstDay, _11a.date, _11a.onSelect || onSelect, _11a.onClose ||
			function(cal) {
				cal.hide();
			});
			cal.showsTime = _11a.showsTime;
			cal.time24 = (_11a.timeFormat == "24");
			cal.weekNumbers = _11a.weekNumbers;
			_125 = true;
		} else {
			if (_11a.date) {
				cal.setDate(_11a.date);
			}
			cal.hide();
		}
		cal.showsOtherMonths = _11a.showOthers;
		cal.yearStep = _11a.step;
		cal.setRange(_11a.range[0], _11a.range[1]);
		cal.params = _11a;
		cal.setDateStatusHandler(_11a.dateStatusFunc);
		cal.setDateFormat(_124);
		if (_125) {
			cal.create();
		}
		cal.parseDate(_123.value || _123.innerHTML);
		cal.refresh();
		if (!_11a.position) {
			cal.showAtElement(_11a.button || _11a.displayArea || _11a.inputField, _11a.align);
		} else {
			cal.showAt(_11a.position[0], _11a.position[1]);
		}
		return false;
	}
	showCalendar();
};
function Util() {}
function Utils() {}
var agt = navigator.userAgent.toLowerCase();
var is_ie = ((agt.indexOf("msie") != -1) && (agt.indexOf("opera") == -1));
var is_opera = (agt.indexOf("opera") != -1);
var is_mac = (agt.indexOf("mac") != -1);
var is_mac_ie = (is_ie && is_mac);
var is_win_ie = (is_ie && !is_mac);
var is_gecko = (navigator.product == "Gecko");
var is_safari = (agt.indexOf("safari") != -1);
var is_ff = (agt.indexOf("firefox"));
var is_chrome = (agt.indexOf("chrome") != -1);
var selDocCheck = new Boolean();
var iscalvisible = false;
selDocCheck = false;
function getDim() {
	var wh;
	if (document.documentElement.clientHeight) {
		wh = document.documentElement.clientHeight;
	} else {
		if (document.body.clientHeight) {
			wh = document.body.clientHeight;
		} else {
			if (window.innerHeight) {
				wh = window.innerHeight;
			}
		}
	}
	var ww;
	if (document.documentElement.clientWidth) {
		ww = document.documentElement.clientWidth;
	} else {
		if (document.body.clientWidth) {
			ww = document.body.clientWidth;
		} else {
			if (window.innerWidth) {
				ww = window.innerWidth;
			}
		}
	}
	return {
		width: ww,
		height: wh
	};
}
function setDim(_12a) {
	var _12b = getDim();
	var ww = _12b.width;
	var wh = _12b.height;
	var fh = wh;
	if (wh > 600 && wh < 700) {
		fh = wh - 155;
	} else {
		if (wh > 700 && wh < 900) {
			fh = wh - 160;
		} else {
			if (wh > 900) {
				fh = wh - 210;
			}
		}
	}
	var _12f = document.getElementById("chatrhs");
	_12f.style.minHeight = fh + "px";
}
Util.projdivId = "projId_0";
Util.projId = "-1";
Utils.currentThemeSelect = "lightblue";
Utils.previousTheme = "lightblue";
Utils.compId = "-1";
Utils.forumAttachment = 0;
Utils.forumComAttachment = 0;
Utils.isAdvOptEnabled = false;
Utils.ctime = 0;
Utils.isTaskMailOpt = true;
Utils.isEditTaskMailOpt = true;
Utils.docFile = 0;
Utils.isCompTaskShow = false;
isWorking = false;
Utils.isMeetAdvOptEnabled = false;
Utils.zuid = "-1";
Utils.isDepOptEnabled = false;
Utils.ifformat = "%m-%d-%Y";
Utils.dateformat = "";
Utils.isAddDependMailOpt = true;
Utils.buildfor = "zoho";
Utils.chatId = "-1";
Utils.fontBold = "";
Utils.fontItalic = "";
Utils.fontUnderlined = "none";
Utils.fontColor = "#000000";
Utils.fontSize = "12";
Utils.fontName = "Arial";
Utils.isChatOwner = false;
Utils.lname = "-1";
Utils.isTaskNoteMailOpt = true;
Utils.isEditTaskNoteMailOpt = true;
Utils.isChatPopOut = false;
Utils.isRMenuCalled = false;
Utils.msgCount = "140";
Utils.annCount = "100";
Utils.newPlanName = "";
Utils._newArray;
Utils._customPortal = "";
Utils.editorObj = new Object();
Utils.origChatOwner = "-1";
Utils.isChatSoundEnabled = false;
Utils.taskinhr = "false";
function TaskMailSendStatus(Id, cobj) {
	var id = document.getElementById(Id);
	if (cobj.checked) {
		Utils.isTaskMailOpt = true;
		id.value = "yes";
	} else {
		Utils.isTaskMailOpt = false;
		id.value = "no";
	}
}
function UpdateTaskMailSendStatus(Id, cobj) {
	var id = document.getElementById(Id);
	if (cobj.checked) {
		Utils.isEditTaskMailOpt = true;
		id.value = "yes";
	} else {
		Utils.isEditTaskMailOpt = false;
		id.value = "no";
	}
}
function ShowHideBlock(_136, Id) {
	var id = document.getElementById(_136);
	if (id) {
		id.className = "block";
	}
	var id1 = document.getElementById(Id);
	if (id1) {
		id1.className = "hide";
	}
}
function TdHide(div1, div2, div3) {
	document.getElementById(div1).style.visibility = "hidden";
	document.getElementById(div2).style.visibility = "hidden";
	document.getElementById(div3).style.visibility = "hidden";
}
function TdVisible(div1, div2, div3) {
	document.getElementById(div1).style.visibility = "visible";
	document.getElementById(div2).style.visibility = "visible";
	document.getElementById(div3).style.visibility = "visible";
}
function TwoTdHide(div1, div2) {
	document.getElementById(div1).style.visibility = "hidden";
	document.getElementById(div2).style.visibility = "hidden";
}
function TwoTdVisible(div1, div2) {
	document.getElementById(div1).style.visibility = "visible";
	document.getElementById(div2).style.visibility = "visible";
}
function ShowHideInline(_144, _145) {
	var id = document.getElementById(_144);
	if (id) {
		id.className = "inline";
	}
	var id1 = document.getElementById(_145);
	if (id1) {
		id1.className = "hide";
	}
}
function ShowGenInline(_148) {
	var id = document.getElementById(_148);
	if (id) {
		id.className = "inline";
	}
}
function ShowThreeInline(_14a, _14b, _14c) {
	var id1 = document.getElementById(_14a);
	var id2 = document.getElementById(_14b);
	var id3 = document.getElementById(_14c);
	id1.className = "inline";
	id2.className = "inline";
	id3.className = "inline";
}
function ShowGenTwoBlock(_150, _151) {
	var id1 = document.getElementById(_150);
	var id2 = document.getElementById(_151);
	id1.className = "block";
	id2.className = "block";
}
function HideThree(_154, _155, _156) {
	var id1 = document.getElementById(_154);
	var id2 = document.getElementById(_155);
	var id3 = document.getElementById(_156);
	id1.className = "hide";
	id2.className = "hide";
	id3.className = "hide";
}
function ShowGenTwoInline(_15a, _15b) {
	var id1 = document.getElementById(_15a);
	var id2 = document.getElementById(_15b);
	id1.className = "inline";
	id2.className = "inline";
}
function SwapClass(_15e, _15f) {
	var id1 = document.getElementById(_15e);
	var id2 = document.getElementById(_15f);
	var _162 = id1.className;
	id1.className = id2.className;
	id2.className = _162;
}
function SwapTwoStyle(_163, _164, stat) {
	var id1 = document.getElementById(_163);
	var id2 = document.getElementById(_164);
	if (stat == "show") {
		id1.className = "";
		id2.className = "";
	} else {
		if (stat == "hide") {
			id1.className = "hide";
			id2.className = "hide";
		}
	}
}
function SwapStyle(_168, stat) {
	var id1 = document.getElementById(_168);
	if (stat == "show") {
		id1.className = "";
	} else {
		if (stat == "hide") {
			id1.className = "hide";
			Utils.forumAttachment = Utils.forumAttachment - 1;
			if (Utils.forumAttachment == 0) {
				ShowGenInline("attachlink_1");
				var sid = document.getElementById("forumtddiv");
				sid.className = "hide";
			}
		}
	}
}
function SwapClassStyle(id1, id2) {
	var _16e = document.getElementById(id1);
	var _16f = document.getElementById(id2);
	_16e.className = "";
	_16f.className = "hide";
}
function addNewClass(id1, id2) {
	var _172 = document.getElementById(id1);
	var _173 = document.getElementById(id2);
	_172.className = "qcdiv txtSmall block";
	_173.className = "hide";
}
function HideTwo(_174, _175) {
	var id1 = document.getElementById(_174);
	var id2 = document.getElementById(_175);
	id1.className = "hide";
	id2.className = "hide";
}
function ShowGenBlock(_178) {
	var id = document.getElementById(_178);
	if (id) {
		id.className = "block";
	}
}
function ShowTaskHideInline(_17a, Id, _17c) {
	var id = document.getElementById(_17a);
	id.className = "inline";
	id1 = document.getElementById(Id);
	id1.className = "hide";
	eval("document.addTodoTask_" + _17c + ".todotask.focus()");
	if (Utils.isAdvOptEnabled) {
		if (_17c != "0") {
			ShowHideBlock("adv_option_" + _17c, "adv_opt_link_" + _17c);
		} else {
			ShowHideBlock("adv_option", "adv_opt_link");
		}
	}
	if (Utils.isTaskMailOpt || Utils.isTaskMailOpt == "undefined") {
		if (_17c == "0") {
			document.addTodoTaskForm.notifyuser.checked = true;
		} else {
			eval("document.addTodoTask_" + _17c + ".notifyuser").checked = true;
		}
	} else {
		if (_17c == "0") {
			document.addTodoTaskForm.notifyuser.checked = false;
		} else {
			eval("document.addTodoTask_" + _17c + ".notifyuser").checked = false;
		}
	}
}
function ShowTaskHideBlock(_17e, Id, _180) {
	ShowGenBlock(_17e);
	if (Utils.isAdvOptEnabled) {
		SwapTwoStyle("advopt1li_" + _180, "advopt2li_" + _180, "show");
		ShowHideInline("hideadvoptionli_" + _180, "dispadvoptionli_" + _180);
	}
}
function ShowTaskBlock(_181, _182) {
	ShowGenBlock(_181);
	if (Utils.isAdvOptEnabled) {
		if (_182 != "0") {
			SwapTwoStyle("advopt1_" + _182, "advopt2_" + _182, "show");
			ShowHideInline("hideadvoption_" + _182, "dispadvoption_" + _182);
		} else {
			SwapTwoStyle("advopt1", "advopt2", "show");
			ShowHideInline("hideadvoption", "dispadvoption");
		}
	}
	if (Utils.isTaskMailOpt || Utils.isTaskMailOpt == "undefined") {
		if (_182 == "0") {
			document.addTodoTaskForm.notifyuser.checked = true;
		} else {
			eval("document.addTodoTask_" + _182 + ".notifyuser").checked = true;
		}
	} else {
		if (_182 == "0") {
			document.addTodoTaskForm.notifyuser.checked = false;
		} else {
			eval("document.addTodoTask_" + _182 + ".notifyuser").checked = false;
		}
	}
}
function Hide(_183) {
	var id = document.getElementById(_183);
	if (id) {
		id.className = "hide";
	}
}
var timeddiv;
var whereDiv;
function mainOver(m) {
	if (isMouseOutOfSameDiv(m)) {
		clearTimeout(timeddiv);
	}
	var _186 = document.getElementById(m);
	_186.style.visibility = "visible";
}
function isMouseOutOfSameDiv(_187) {
	if (whereDiv == _187) {
		return true;
	} else {
		return false;
	}
}
function mainOut(i) {
	timeddiv = setTimeout("delayhide('" + i + "')", 500);
	whereDiv = i;
}
function floatOut(i) {
	delayhide(i);
}
function delayhide(i) {
	var _18b = document.getElementById(i);
	if (_18b) {
		_18b.style.visibility = "hidden";
	}
}
function floatOver(i) {
	clearTimeout(timeddiv);
}
function validateProjectForm(stat) {
	if (stat == "add") {
		document.addProject.addprojsubmit.disabled = true;
		var _18e = document.getElementById("proj_add_status");
		if (trim(document.getElementById("newproject").value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsspecify", null,
			function(mesg) {
				i18n.getJSAlertValue(Utils.zuid, "zp.newproject.projectname", null,
				function(_190) {
					_18e.innerHTML = "<span class=\"error\">" + mesg + " " + _190 + "</span>";
				});
			});
			document.getElementById("newproject").focus();
			document.addProject.addprojsubmit.disabled = false;
			return false;
		} else {
			if (findScriptTags(trim(document.getElementById("newproject").value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsspecify", null,
				function(mesg) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valprojname", null,
					function(_192) {
						_18e.innerHTML = "<span class=\"error\">" + mesg + " " + _192 + "</span>";
					});
				});
				document.getElementById("newproject").focus();
				document.addProject.addprojsubmit.disabled = false;
				return false;
			} else {
				if (document.getElementById("shiftdays") && !isNumeric(trim(document.getElementById("shiftdays").value))) {
					i18n.getJSAlertValue(Utils.zuid, "Shift Date must be Numeric and Positive", null,
					function(mesg) {
						_18e.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.getElementById("shiftdays").focus();
					document.addProject.addprojsubmit.disabled = false;
					return false;
				} else {
					ShowGenInline("zoho_add_project_busy");
					return true;
				}
			}
		}
	} else {
		if (stat == "update") {
			var _18e = document.getElementById("settings_update_status");
			if (trim(document.updateProject.projectname.value).length == 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsspecify", null,
				function(mesg) {
					i18n.getJSAlertValue(Utils.zuid, "zp.newproject.projectname", null,
					function(_195) {
						_18e.innerHTML = "<span class=\"error\">" + mesg + " " + _195 + "</span>";
					});
				});
				document.updateProject.projectname.focus();
				document.updateProject.addprojsubmit.disabled = false;
				return false;
			} else {
				if (findScriptTags(trim(document.updateProject.projectname.value))) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsspecify", null,
					function(mesg) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valprojname", null,
						function(_197) {
							_18e.innerHTML = "<span class=\"error\">" + mesg + " " + _197 + "</span>";
						});
					});
					document.updateProject.projectname.focus();
					document.updateProject.addprojsubmit.disabled = false;
					return false;
				} else {
					ShowGenInline("zoho_proj_settings_busy");
					return true;
				}
			}
		}
	}
}
function validateTemplateForm(stat) {
	if (stat == "add") {
		document.addProject.addprojsubmit.disabled = true;
		var _199 = document.getElementById("proj_add_status");
		if (trim(document.getElementById("newproject").value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.projtemp.spcytempname", null,
			function(mesg) {
				_199.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.getElementById("newproject").focus();
			document.addProject.addprojsubmit.disabled = false;
			return false;
		} else {
			if (findScriptTags(trim(document.getElementById("newproject").value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.projtemp.valtempname", null,
				function(mesg) {
					_199.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.getElementById("newproject").focus();
				document.addProject.addprojsubmit.disabled = false;
				return false;
			} else {
				ShowGenInline("zoho_add_project_busy");
				return true;
			}
		}
	} else {
		if (stat == "update") {
			document.updateProject.addprojsubmit.disabled = true;
			var _199 = document.getElementById("settings_update_status");
			if (trim(document.updateProject.projectname.value).length == 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.projtemp.spcytempname", null,
				function(mesg) {
					_199.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.updateProject.projectname.focus();
				document.updateProject.addprojsubmit.disabled = false;
				return false;
			} else {
				if (findScriptTags(trim(document.updateProject.projectname.value))) {
					i18n.getJSAlertValue(Utils.zuid, "zp.projtemp.valtempname", null,
					function(mesg) {
						_199.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.updateProject.projectname.focus();
					document.updateProject.addprojsubmit.disabled = false;
					return false;
				} else {
					ShowGenInline("zoho_proj_settings_busy");
					return true;
				}
			}
		}
	}
}
function validateAnnouncementForm(stat) {
	if (stat == "add") {
		document.addprojannounce.announcesubmit.disabled = true;
		var _19f = document.getElementById("error");
		if (trim(document.getElementById("announceTitle").value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsspecify", null,
			function(mesg) {
				i18n.getJSAlertValue(Utils.zuid, "zp.fetchprojannounce.anntitle", null,
				function(_1a1) {
					_19f.innerHTML = "<span class=\"error\">" + mesg + " " + _1a1 + "</span>";
				});
			});
			document.getElementById("announceTitle").focus();
			document.addprojannounce.announcesubmit.disabled = false;
			return false;
		} else {
			if (trim(document.getElementById("announceTitle").value).length > 100) {
				i18n.getJSAlertValue(Utils.zuid, "zp.projannounce.titleminchar", null,
				function(mesg) {
					_19f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.getElementById("announceTitle").focus();
				document.addprojannounce.announcesubmit.disabled = false;
				return false;
			} else {
				if (findScriptTags(trim(document.getElementById("announceTitle").value))) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsspecify", null,
					function(mesg) {
						i18n.getJSAlertValue(Utils.zuid, "zp.fetchprojannounce.validanntitle", null,
						function(_1a4) {
							_19f.innerHTML = "<span class=\"error\">" + mesg + " " + _1a4 + "</span>";
						});
					});
					document.getElementById("announceTitle").focus();
					document.addprojannounce.announcesubmit.disabled = false;
					return false;
				} else {
					if (trim(document.getElementById("announceDesc").value).length == 0) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsspecify", null,
						function(mesg) {
							i18n.getJSAlertValue(Utils.zuid, "zp.fetchprojannounce.anndesc", null,
							function(_1a6) {
								_19f.innerHTML = "<span class=\"error\">" + mesg + " " + _1a6 + "</span>";
							});
						});
						document.getElementById("announceDesc").focus();
						document.addprojannounce.announcesubmit.disabled = false;
						return false;
					} else {
						ShowGenInline("zoho_add_project_busy");
						return true;
					}
				}
			}
		}
	} else {
		var _1a7 = "updateannounce_" + stat;
		var _1a8 = "updannouncesubmit_" + stat;
		var _1a9 = "updtitle_" + stat;
		eval("document." + _1a7 + "." + _1a8).disabled = true;
		var _19f = document.getElementById("error_" + stat);
		if (trim(eval("document." + _1a7 + "." + _1a9 + ".value")).length == 0) {
			i18n.getJSAlertValue(Utils.zustat, "zp.functionjs.plsspecify", null,
			function(mesg) {
				i18n.getJSAlertValue(Utils.zustat, "zp.fetchprojannounce.anntitle", null,
				function(_1ab) {
					_19f.innerHTML = "<span class=\"error\">" + mesg + " " + _1ab + "</span>";
				});
			});
			eval("document." + _1a7 + "." + _1a9).focus();
			eval("document." + _1a7 + "." + _1a8).disabled = false;
			return false;
		} else {
			if (trim(eval("document." + _1a7 + "." + _1a9 + ".value")).length > 100) {
				i18n.getJSAlertValue(Utils.zustat, "zp.projannounce.titleminchar", null,
				function(mesg) {
					_19f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _1a7 + "." + _1a9).focus();
				eval("document." + _1a7 + "." + _1a8).disabled = false;
				return false;
			} else {
				if (findScriptTags(trim(eval("document." + _1a7 + "." + _1a9 + ".value")))) {
					i18n.getJSAlertValue(Utils.zustat, "zp.functionjs.plsspecify", null,
					function(mesg) {
						i18n.getJSAlertValue(Utils.zustat, "zp.fetchprojannounce.validanntitle", null,
						function(_1ae) {
							_19f.innerHTML = "<span class=\"error\">" + mesg + " " + _1ae + "</span>";
						});
					});
					eval("document." + _1a7 + "." + _1a9).focus();
					eval("document." + _1a7 + "." + _1a8).disabled = false;
					return false;
				} else {
					if (trim(document.getElementById("upddesc_" + stat).value).length == 0) {
						i18n.getJSAlertValue(Utils.zustat, "zp.functionjs.plsspecify", null,
						function(mesg) {
							i18n.getJSAlertValue(Utils.zustat, "zp.fetchprojannounce.anndesc", null,
							function(_1b0) {
								_19f.innerHTML = "<span class=\"error\">" + mesg + " " + _1b0 + "</span>";
							});
						});
						eval(document.getElementById("upddesc_" + stat)).focus();
						eval("document." + _1a7 + "." + _1a8).disabled = false;
						return false;
					} else {
						ShowGenInline("zoho_add_project_busy");
						return true;
					}
				}
			}
		}
	}
}
function validateUserForm() {
	var doc = document;
	var _1b2 = doc.getElementById("newuser_status");
	var _1b3 = /^[a-z0-9]([a-z0-9_\-\.\+]*)@([a-z0-9_\-\.]*)(\.[a-z]{2,4}(\.[a-z]{2}){0,2})$/i;
	var _1b4 = doc.addUser.email;
	doc.addUser.email.value = trim(doc.addUser.email.value);
	if (trim(_1b4.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyemailid", null,
		function(mesg) {
			_1b2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		_1b4.focus();
		return false;
	} else {
		if (!_1b3.test(_1b4.value)) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valemailid", null,
			function(mesg) {
				_1b2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			_1b4.focus();
			return false;
		}
	}
	ShowGenInline("zoho_adduser_busy");
	return true;
}
function trim(str) {
	return str.replace(/^\s+|\s+$/g, "");
}
function MoveOption(_1b8, _1b9) {
	var _1ba = new Array();
	var x = 0;
	for (var i = 0; i < _1b8.length; i++) {
		if (_1b8.options[i].selected && !_1b8.options[i].disabled) {
			var _1bd = _1b9.length++;
			_1b9.options[_1bd].text = _1b8.options[i].text;
			_1b9.options[_1bd].value = _1b8.options[i].value;
		} else {
			var _1be = new Object();
			_1be.text = _1b8.options[i].text;
			_1be.value = _1b8.options[i].value;
			_1be.disabled = _1b8.options[i].disabled;
			_1ba[x] = _1be;
			x++;
		}
	}
	_1b8.length = _1ba.length;
	for (var i = 0; i < _1ba.length; i++) {
		_1b8.options[i].text = _1ba[i].text;
		_1b8.options[i].value = _1ba[i].value;
		_1b8.options[i].disabled = _1ba[i].disabled;
		_1b8.options[i].selected = false;
	}
}
function TaskMoveOption(_1bf, _1c0) {
	var _1c1 = new Array();
	var _1c2 = new Array();
	var x = 0;
	var y = 0;
	var _1c5 = new Array();
	var _1c6 = 0;
	for (var i = 0; i < _1c0.length; i++) {
		_1c5[_1c6] = _1c0.options[i].value;
		_1c6++;
	}
	var _1c8 = new Array();
	var _1c6 = 0;
	for (var i = 0; i < _1bf.length; i++) {
		if (_1bf.options[i].selected && !_1bf.options[i].disabled) {
			_1c8[_1c6] = _1bf.options[i].value;
			_1c6++;
		}
	}
	if (_1c8.length > 0 && ((_1c8.indexOf("AnyUser") == -1) || (_1c5.length == 0 && (_1c5.indexOf("AnyUser") == -1)))) {
		if (_1c8.length > 0) {
			if ((_1c5.indexOf("AnyUser") != -1)) {
				var _1c9 = new Object();
				_1c9.text = _1c0.options[_1c5.indexOf("AnyUser")].text;
				_1c9.value = _1c0.options[_1c5.indexOf("AnyUser")].value;
				_1c9.disabled = _1c0.options[_1c5.indexOf("AnyUser")].disabled;
				_1c1[x] = _1c9;
				x++;
			} else {
				for (var t = 0; t < _1c0.length; t++) {
					var _1c9 = new Object();
					_1c9.text = _1c0.options[t].text;
					_1c9.value = _1c0.options[t].value;
					_1c9.disabled = _1c0.options[t].disabled;
					_1c2[y] = _1c9;
					y++;
				}
			}
		}
		_1c0.length = _1c2.length;
		for (var i = 0; i < _1c2.length; i++) {
			_1c0.options[i].text = _1c2[i].text;
			_1c0.options[i].value = _1c2[i].value;
			_1c0.options[i].disabled = _1c2[i].disabled;
			_1c0.options[i].selected = false;
		}
		for (var i = 0; i < _1bf.length; i++) {
			if (_1bf.options[i].selected && !_1bf.options[i].disabled) {
				var _1cb = _1c0.length++;
				_1c0.options[_1cb].text = _1bf.options[i].text;
				_1c0.options[_1cb].value = _1bf.options[i].value;
			} else {
				var _1c9 = new Object();
				_1c9.text = _1bf.options[i].text;
				_1c9.value = _1bf.options[i].value;
				_1c9.disabled = _1bf.options[i].disabled;
				_1c1[x] = _1c9;
				x++;
			}
		}
		_1bf.length = _1c1.length;
		for (var i = 0; i < _1c1.length; i++) {
			_1bf.options[i].text = _1c1[i].text;
			_1bf.options[i].value = _1c1[i].value;
			_1bf.options[i].disabled = _1c1[i].disabled;
			_1bf.options[i].selected = false;
		}
		for (var t = 0; t < _1c0.length; t++) {
			var _1c9 = new Object();
			_1c9.text = _1c0.options[t].text;
			_1c9.value = _1c0.options[t].value;
			_1c9.disabled = _1c0.options[t].disabled;
			_1c2[y] = _1c9;
			y++;
		}
	}
}
function validateMcatForm() {
	var _1cc = document.getElementById("disc_add_status");
	if (trim(document.addDiscCategory.categoryname.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.disctopic", null,
		function(mesg) {
			_1cc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.addDiscCategory.categoryname.focus();
		return false;
	} else {
		if (findScriptTags(trim(document.addDiscCategory.categoryname.value))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valdisctopic", null,
			function(mesg) {
				_1cc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.addDiscCategory.categoryname.focus();
			return false;
		} else {
			ShowGenInline("zoho_add_chat_busy");
			return true;
		}
	}
}
function validateLinkTagSearchForm() {
	if (trim(document.searchLinkTagForm.tagname.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.attach.schlktagemp", null,
		function(mesg) {
			alert(mesg);
		});
		document.searchLinkTagForm.tagname.focus();
		return false;
	} else {
		if (findScriptTags(trim(document.searchLinkTagForm.tagname.value))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.attach.vallkschtag", null,
			function(mesg) {
				alert(mesg);
			});
			document.searchLinkTagForm.tagname.focus();
			return false;
		} else {
			if ((trim(document.searchLinkTagForm.tagname.value)).toLowerCase().indexOf("<iframe") != -1 || (trim(document.searchLinkTagForm.tagname.value)).toLowerCase().indexOf("<object") != -1) {
				i18n.getJSAlertValue(Utils.zuid, "zp.attach.vallkschtag", null,
				function(mesg) {
					alert(mesg);
				});
				document.searchLinkTagForm.tagname.focus();
				return (false);
			} else {
				ShowGenInline("zoho_tag_search_busy");
				return true;
			}
		}
	}
}
function validateTagSearchForm() {
	if (trim(document.searchFileTagForm.tagname.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.searchtagemp", null,
		function(mesg) {
			alert(mesg);
		});
		document.searchFileTagForm.tagname.focus();
		return false;
	} else {
		if (findScriptTags(trim(document.searchFileTagForm.tagname.value))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specvalsearchtag", null,
			function(mesg) {
				alert(mesg);
			});
			document.searchFileTagForm.tagname.focus();
			return false;
		} else {
			ShowGenInline("zoho_tag_search_busy");
			return true;
		}
	}
}
function validateDiscForm(stat) {
	if (stat == "add") {
		if (trim(document.getElementById("disctitle").value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.disctitcantempty", null,
			function(mesg) {
				alert(mesg);
			});
			document.postDiscussion.mesgtitle.focus();
			return false;
		} else {
			if (findScriptTags(trim(document.getElementById("disctitle").value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valdisctitle", null,
				function(mesg) {
					alert(mesg);
				});
				document.postDiscussion.mesgtitle.focus();
				return false;
			} else {
				if (trim(document.getElementById("discbody").value).length == 0) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.disccontemp", null,
					function(mesg) {
						alert(mesg);
					});
					document.postDiscussion.mesgbody.focus();
					return false;
				} else {
					if (findScriptTags(trim(document.getElementById("discbody").value))) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.validdisccont", null,
						function(mesg) {
							alert(mesg);
						});
						document.postDiscussion.mesgbody.focus();
						return false;
					} else {
						return true;
					}
				}
			}
		}
	} else {
		var dt = "disctitle" + stat;
		var db = "discbody" + stat;
		if (trim(document.getElementById(dt).value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.disctitcantempty", null,
			function(mesg) {
				alert(mesg);
			});
			document.getElementById(dt).focus();
			return false;
		} else {
			if (findScriptTags(trim(document.getElementById(dt).value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valdisctitle", null,
				function(mesg) {
					alert(mesg);
				});
				document.getElementById(dt).focus();
				return false;
			} else {
				if (trim(document.getElementById(db).value).length == 0) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.disccontemp", null,
					function(mesg) {
						alert(mesg);
					});
					document.getElementById(db).focus();
					return false;
				} else {
					if (findScriptTags(trim(document.getElementById(db).value))) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.validdisccont", null,
						function(mesg) {
							alert(mesg);
						});
						document.getElementById(db).focus();
						return false;
					} else {
						return true;
					}
				}
			}
		}
	}
}
function validateFileCommentForm(ver) {
	var _1e0 = "editFileCommentForm_" + ver;
	var _1e1 = "zoho_comment_update_busy_" + ver;
	var alId = "comment_update_status_" + ver;
	var _1e3 = document.getElementById(alId);
	if (trim(eval("document." + _1e0 + ".doccomment.value")).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.entercomment", null,
		function(mesg) {
			_1e3.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		eval("document." + _1e0 + ".doccomment.focus()");
		return (false);
	} else {
		ShowGenInline(_1e1);
		return true;
	}
}
function validateCommentForm(stat) {
	var _1e6 = (stat == "add") ? "commentForm": ("updateComment_" + stat);
	var elem, _1e8;
	var doc = document;
	var _1ea = doc.getElementById((stat == "add") ? "fcomment_add_status": ("fcomment_upd_status_" + stat));
	var elem = doc[_1e6].commbody;
	if (trim(elem.value).length == 0 || trim(elem.value) == "<br>") {
		_1e8 = "zp.functionjs.entercomment";
		scroll(0, 0);
	} else {
		if (findScriptTags(elem.value)) {
			_1e8 = "zp.functionjs.validcontent";
			scroll(0, 0);
		}
	}
	if (_1e8) {
		elem.focus();
	} else {
		if (isFilesSizeGreater("uploadfile", 125)) {
			_1e8 = "zp.docdetails.greatersize";
			scroll(0, 0);
		}
	}
	if (_1e8) {
		i18n.getJSAlertValue(Utils.zuid, _1e8, null,
		function(mesg) {
			_1ea.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return false;
	}
	ShowGenInline((stat == "add") ? "zoho_add_comment_busy": ("zoho_upd_comment_busy_" + stat));
	return true;
}
function validateFolderForm(stat) {
	if (stat == "add") {
		var _1ed = document.getElementById("folder_add_status");
		if (trim(document.getElementById("newfolder").value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyfoldname", null,
			function(mesg) {
				_1ed.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.addFolder.foldname.focus();
			return false;
		} else {
			if (findScriptTags(trim(document.getElementById("newfolder").value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.validfolname", null,
				function(mesg) {
					_1ed.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.addFolder.foldname.focus();
				return false;
			} else {
				return true;
			}
		}
	} else {
		return true;
	}
}
function validatePassForm() {
	var _1f0 = document.getElementById("pass_upd_status");
	if (trim(document.changePassForm.oldpassword.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.userprofile.plsspyoldpwd", null,
		function(mesg) {
			_1f0.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.changePassForm.oldpassword.focus();
		return (false);
	} else {
		if (trim(document.changePassForm.password.value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifypwd", null,
			function(mesg) {
				_1f0.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.changePassForm.password.focus();
			return (false);
		} else {
			if (trim(document.changePassForm.confpassword.value).length == 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.confirmpwd", null,
				function(mesg) {
					_1f0.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.changePassForm.confpassword.focus();
				return (false);
			} else {
				if (document.changePassForm.password.value != document.changePassForm.confpassword.value) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.pwdmismatch", null,
					function(mesg) {
						_1f0.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.changePassForm.password.focus();
					return (false);
				} else {
					ShowGenInline("zoho_upd_passwd_busy");
					disableButton("zoho_upd_passwd_submit");
					return true;
				}
			}
		}
	}
}
function validateCompanyLogoForm() {
	var _1f5 = document.getElementById("clogo_upload_status");
	var _1f6 = trim(document.companyLogoForm.uploadlogo.value).lastIndexOf("/");
	if (_1f6 == -1) {
		_1f6 = trim(document.companyLogoForm.uploadlogo.value).lastIndexOf("\\");
	}
	if (trim(document.companyLogoForm.uploadlogo.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsuplfile", null,
		function(mesg) {
			_1f5.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.companyLogoForm.uploadlogo.focus();
		return (false);
	} else {
		if (endsWith(trim(document.companyLogoForm.uploadlogo.value), "/")) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsuplfile", null,
			function(mesg) {
				_1f5.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.companyLogoForm.uploadlogo.focus();
			return (false);
		} else {
			if (trim(document.companyLogoForm.uploadlogo.value).length > _1f6 + 50) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.filnmtolng", null,
				function(mesg) {
					_1f5.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.companyLogoForm.uploadlogo.focus();
				return (false);
			} else {
				ShowGenInline("zoho_upd_clogo_busy");
				return true;
			}
		}
	}
}
function validatePowerLogoForm() {
	var _1fa = document.getElementById("plogo_upload_status");
	var _1fb = trim(document.powerLogoForm.puploadlogo.value).lastIndexOf("/");
	if (_1fb == -1) {
		_1fb = trim(document.powerLogoForm.puploadlogo.value).lastIndexOf("\\");
	}
	if (trim(document.powerLogoForm.puploadlogo.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsuplfile", null,
		function(mesg) {
			_1fa.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.powerLogoForm.puploadlogo.focus();
		return (false);
	} else {
		if (endsWith(trim(document.powerLogoForm.puploadlogo.value), "/")) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsuplfile", null,
			function(mesg) {
				_1fa.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.powerLogoForm.puploadlogo.focus();
			return (false);
		} else {
			if (trim(document.powerLogoForm.puploadlogo.value).length > _1fb + 50) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.filnmtolng", null,
				function(mesg) {
					_1fa.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.powerLogoForm.puploadlogo.focus();
				return (false);
			} else {
				ShowGenInline("zoho_upd_plogo_busy");
				return true;
			}
		}
	}
}
function validateUserPhotoForm() {
	var _1ff = document.getElementById("photo_upload_status");
	var _200 = trim(document.userPhotoForm.uploadphoto.value).lastIndexOf("/");
	if (_200 == -1) {
		_200 = trim(document.userPhotoForm.uploadphoto.value).lastIndexOf("\\");
	}
	if (trim(document.userPhotoForm.uploadphoto.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsuplfile", null,
		function(mesg) {
			_1ff.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.userPhotoForm.uploadphoto.focus();
		return (false);
	} else {
		if (endsWith(trim(document.userPhotoForm.uploadphoto.value), "/")) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsuplfile", null,
			function(mesg) {
				_1ff.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.userPhotoForm.uploadphoto.focus();
			return (false);
		} else {
			ShowGenInline("zoho_upd_photo_busy");
			return true;
		}
	}
}
function validateUploadForm() {
	var _203 = document.getElementById("doc_upload_status");
	var _204 = trim(document.addDocument.uploaddoc.value).lastIndexOf("/");
	if (_204 == -1) {
		_204 = trim(document.addDocument.uploaddoc.value).lastIndexOf("\\");
	}
	if (trim(document.addDocument.uploaddoc.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsuplfile", null,
		function(mesg) {
			_203.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.addDocument.uploaddoc.focus();
		scroll(0, 0);
		return (false);
	} else {
		if (endsWith(trim(document.addDocument.uploaddoc.value), "/")) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsuplfile", null,
			function(mesg) {
				_203.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			scroll(0, 0);
			return (false);
		} else {
			if (trim(document.addDocument.uploaddoc.value).length > _204 + 100) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.filnmtolng", null,
				function(mesg) {
					_203.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.addDocument.uploaddoc.focus();
				scroll(0, 0);
				return (false);
			} else {
				if (trim(document.addDocument.docname.value).length == 0) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyfilecmt", null,
					function(mesg) {
						_203.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.addDocument.docname.focus();
					scroll(0, 0);
					return (false);
				} else {
					if (findScriptTags(trim(document.addDocument.docname.value))) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valfilecomment", null,
						function(mesg) {
							_203.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						document.addDocument.docname.focus();
						scroll(0, 0);
						return (false);
					} else {
						if (findScriptTags(trim(document.addDocument.tags.value))) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valtags", null,
							function(mesg) {
								_203.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							document.addDocument.tags.focus();
							scroll(0, 0);
							return (false);
						} else {
							return true;
						}
					}
				}
			}
		}
	}
}
function validateAddLinkForm() {
	var _20b = document.getElementById("link_add_status");
	if (trim(document.addLinkForm.linkname.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifylinkname", null,
		function(mesg) {
			_20b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.addLinkForm.linkname.focus();
		return (false);
	} else {
		if (findScriptTags(trim(document.addLinkForm.linkname.value))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinkname", null,
			function(mesg) {
				_20b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.addLinkForm.linkname.focus();
			return (false);
		} else {
			if ((trim(document.addLinkForm.linkname.value)).toLowerCase().indexOf("<iframe") != -1 || (trim(document.addLinkForm.linkname.value)).toLowerCase().indexOf("<object") != -1) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinkname", null,
				function(mesg) {
					_20b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.addLinkForm.linkname.focus();
				return (false);
			} else {
				if (trim(document.addLinkForm.linkurl.value).length == 0) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifylinkurl", null,
					function(mesg) {
						_20b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.addLinkForm.linkurl.focus();
					return (false);
				} else {
					if (findScriptTags(trim(document.addLinkForm.linkurl.value))) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinkurl", null,
						function(mesg) {
							_20b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						document.addLinkForm.linkurl.focus();
						return (false);
					} else {
						if (findScriptTags(trim(document.addLinkForm.description.value))) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinkdescription", null,
							function(mesg) {
								_20b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							document.addLinkForm.description.focus();
							return (false);
						} else {
							if ((trim(document.addLinkForm.description.value)).toLowerCase().indexOf("<iframe") != -1 || (trim(document.addLinkForm.description.value)).toLowerCase().indexOf("<object") != -1) {
								i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinkdescription", null,
								function(mesg) {
									_20b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
								});
								document.addLinkForm.description.focus();
								return (false);
							} else {
								if (findScriptTags(trim(document.addLinkForm.linktags.value))) {
									i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinktags", null,
									function(mesg) {
										_20b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
									});
									document.addLinkForm.linktags.focus();
									return (false);
								} else {
									if ((trim(document.addLinkForm.linktags.value)).toLowerCase().indexOf("<iframe") != -1 || (trim(document.addLinkForm.linktags.value)).toLowerCase().indexOf("<object") != -1) {
										i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinktags", null,
										function(mesg) {
											_20b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
										});
										document.addLinkForm.linktags.focus();
										return (false);
									} else {
										ShowGenInline("zoho_add_link_busy");
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
function validateLinkEditForm(stat) {
	var _216 = "eLinkEdit_" + stat;
	var _217 = "zoho_update_link_busy_" + stat;
	var alId = "link_update_status_" + stat;
	var _219 = document.getElementById(alId);
	if (trim(eval("document." + _216 + ".linkname.value")).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifylinkname", null,
		function(mesg) {
			_219.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		eval("document." + _216 + ".linkname.focus()");
		return (false);
	} else {
		if (findScriptTags(trim(eval("document." + _216 + ".linkname.value")))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinkname", null,
			function(mesg) {
				_219.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			eval("document." + _216 + ".linkname.focus()");
			return (false);
		} else {
			if ((trim(eval("document." + _216 + ".linkname.value"))).toLowerCase().indexOf("<iframe") != -1 || (trim(eval("document." + _216 + ".linkname.value"))).toLowerCase().indexOf("<object") != -1) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinkname", null,
				function(mesg) {
					_219.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _216 + ".linkname.focus()");
				return (false);
			} else {
				if (trim(eval("document." + _216 + ".linkurl.value")).length == 0) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifylinkurl", null,
					function(mesg) {
						_219.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					eval("document." + _216 + ".linkurl.focus()");
					return (false);
				} else {
					if (findScriptTags(trim(eval("document." + _216 + ".linkurl.value")))) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinkurl", null,
						function(mesg) {
							_219.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						eval("document." + _216 + ".linkurl.focus()");
						return (false);
					} else {
						if (findScriptTags(trim(eval("document." + _216 + ".description.value")))) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinkdescription", null,
							function(mesg) {
								_219.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							eval("document." + _216 + ".description.focus()");
							return (false);
						} else {
							if ((trim(eval("document." + _216 + ".description.value"))).toLowerCase().indexOf("<iframe") != -1 || (trim(eval("document." + _216 + ".description.value"))).toLowerCase().indexOf("<object") != -1) {
								i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinkdescription", null,
								function(mesg) {
									_219.innerHTML = "<span class=\"error\">" + mesg + "</span>";
								});
								eval("document." + _216 + ".description.focus()");
								return (false);
							} else {
								if (findScriptTags(trim(eval("document." + _216 + ".linktags.value")))) {
									i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinktags", null,
									function(mesg) {
										_219.innerHTML = "<span class=\"error\">" + mesg + "</span>";
									});
									eval("document." + _216 + ".linktags.focus()");
									return (false);
								} else {
									if ((trim(eval("document." + _216 + ".linktags.value"))).toLowerCase().indexOf("<iframe") != -1 || (trim(eval("document." + _216 + ".linktags.value"))).toLowerCase().indexOf("<object") != -1) {
										i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.vallinktags", null,
										function(mesg) {
											_219.innerHTML = "<span class=\"error\">" + mesg + "</span>";
										});
										eval("document." + _216 + ".linktags.focus()");
										return (false);
									} else {
										ShowGenInline(_217);
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
function validateMSForm(stat) {
	if (stat == "add") {
		var _224 = document.getElementById("mstone_add_status");
		var _225 = document.addMilestone.mstartdate.value;
		var _226 = document.addMilestone.milestonedate.value;
		Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
		var _227 = compareDates(_225, Utils.dateformat, _226, Utils.dateformat);
		if (document.addMilestone.projId.value == "-1") {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsspecify", null,
			function(mesg) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valprojname", null,
				function(_229) {
					_224.innerHTML = "<span class=\"error\">" + mesg + " " + _229 + "</span>";
				});
			});
			return (false);
		} else {
			if (trim(document.addMilestone.mtitle.value).length == 0 || trim(document.addMilestone.mtitle.value).length == "null") {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.miletitle", null,
				function(mesg) {
					_224.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.addMilestone.mtitle.focus();
				return (false);
			} else {
				if (trim(document.addMilestone.mtitle.value).length > 99) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.validmilelen", null,
					function(mesg) {
						_224.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.addMilestone.mtitle.focus();
					return (false);
				} else {
					if (findScriptTags(trim(document.addMilestone.mtitle.value))) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valmiletitle", null,
						function(mesg) {
							_224.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						document.addMilestone.mtitle.focus();
						return (false);
					} else {
						if (_225 != "" && _226 != "" && _227 != 0) {
							i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
							function(mesg) {
								_224.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							document.addMilestone.milestonedate.focus();
							return false;
						} else {
							ShowGenInline("zoho_add_ms_busy");
							return true;
						}
					}
				}
			}
		}
	} else {
		if (stat.indexOf("add") != -1) {
			var um = "mtitle_" + stat;
			var _22f = "addMilestone_" + stat;
			var _230 = "zoho_add_ms_busy_" + stat;
			var alId = "mstone_add_status_" + stat;
			var _225 = eval("document." + _22f + ".mstartdate.value");
			var _226 = eval("document." + _22f + ".milestonedate.value");
			Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
			var _227 = compareDates(_225, Utils.dateformat, _226, Utils.dateformat);
			var _224 = document.getElementById(alId);
			if (trim(document.getElementById(um).value).length == 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.miletitle", null,
				function(mesg) {
					_224.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _22f + ".mtitle.focus()");
				return (false);
			} else {
				if (trim(document.getElementById(um).value).length > 99) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.validmilelen", null,
					function(mesg) {
						_224.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					eval("document." + _22f + ".mtitle.focus()");
					return (false);
				} else {
					if (findScriptTags(trim(document.getElementById(um).value))) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valmiletitle", null,
						function(mesg) {
							_224.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						eval("document." + _22f + ".mtitle.focus()");
						return (false);
					} else {
						if (_225 != "" && _226 != "" && _227 != 0) {
							i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
							function(mesg) {
								_224.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							eval("document." + _22f + ".milestonedate.focus()");
							return false;
						} else {
							ShowGenInline(_230);
							return true;
						}
					}
				}
			}
		} else {
			var um = "updms_" + stat;
			var _22f = "updateMilestone_" + stat;
			var _230 = "zoho_update_ms_busy" + stat;
			var alId = "mstone_upd_status_" + stat;
			var _225 = eval("document." + _22f + ".mstartdate.value");
			var _226 = eval("document." + _22f + ".milestonedate.value");
			Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
			var _227 = compareDates(_225, Utils.dateformat, _226, Utils.dateformat);
			var _224 = document.getElementById(alId);
			if (trim(document.getElementById(um).value).length == 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.miletitle", null,
				function(mesg) {
					_224.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _22f + ".mtitle.focus()");
				return (false);
			} else {
				if (trim(document.getElementById(um).value).length > 99) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.validmilelen", null,
					function(mesg) {
						_224.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					eval("document." + _22f + ".mtitle.focus()");
					return (false);
				} else {
					if (findScriptTags(trim(document.getElementById(um).value))) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valmiletitle", null,
						function(mesg) {
							_224.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						eval("document." + _22f + ".mtitle.focus()");
						return (false);
					} else {
						if (_225 != "" && _226 != "" && _227 != 0) {
							i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
							function(mesg) {
								_224.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							eval("document." + _22f + ".milestonedate.focus()");
							return false;
						} else {
							ShowGenInline(_230);
							return true;
						}
					}
				}
			}
		}
	}
}
function validateMeetingForm(stat) {
	if (stat == "add") {
		var _23b = new Array();
		var _23c = eval("document.addMeeting.participants");
		var _23d = 0;
		if (_23c.length) {
			for (var i = 0; i < _23c.length; i++) {
				if (_23c[i].checked) {
					_23b[_23d] = _23c[i].value;
					_23d++;
				}
			}
		} else {
			if (_23c.checked) {
				_23b[0] = _23c.value;
				_23d++;
			}
		}
		var _23f = document.getElementById("meet_add_status");
		if (document.addMeeting.projId.value == "-1") {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsspecify", null,
			function(mesg) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valprojname", null,
				function(_241) {
					_23f.innerHTML = "<span class=\"error\">" + mesg + " " + _241 + "</span>";
				});
			});
			document.addMeeting.projId.focus();
			scroll(0, 0);
			return (false);
		} else {
			if (trim(document.addMeeting.meettitle.value).length == 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.meettitle", null,
				function(mesg) {
					_23f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.addMeeting.meettitle.focus();
				scroll(0, 0);
				return (false);
			} else {
				if (findScriptTags(trim(document.addMeeting.meettitle.value))) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valmeettitle", null,
					function(mesg) {
						_23f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.addMeeting.meettitle.focus();
					scroll(0, 0);
					return (false);
				} else {
					if (document.addMeeting.schdate.value == "") {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.meetdate", null,
						function(mesg) {
							_23f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						document.addMeeting.schdate.focus();
						scroll(0, 0);
						return (false);
					} else {
						if (_23d <= 0) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.meetparty", null,
							function(mesg) {
								_23f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							scroll(0, 0);
							return (false);
						} else {
							return true;
						}
					}
				}
			}
		}
	} else {
		var _246 = "zoho_update_meeting_busy";
		var alId = "meet_upd_status";
		var _23b = new Array();
		var _23c = eval("document.updateMeeting.participants");
		var _23d = 0;
		if (_23c.length) {
			for (var j = 0; j < _23c.length; j++) {
				if (_23c[j].checked) {
					_23b[_23d] = _23c[j];
					_23d++;
				}
			}
		} else {
			if (_23c.checked) {
				_23b[0] = _23c.value;
				_23d++;
			}
		}
		var _23f = document.getElementById(alId);
		if (trim(document.updateMeeting.meettitle.value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.meettitle", null,
			function(mesg) {
				_23f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.updateMeeting.meettitle.focus();
			scroll(0, 0);
			return (false);
		} else {
			if (findScriptTags(trim(document.updateMeeting.meettitle.value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valmeettitle", null,
				function(mesg) {
					_23f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.updateMeeting.meettitle.focus();
				scroll(0, 0);
				return (false);
			} else {
				if (document.updateMeeting.schdate.value == "") {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.meetdate", null,
					function(mesg) {
						_23f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.updateMeeting.schdate.focus();
					scroll(0, 0);
					return (false);
				} else {
					if (_23d <= 0) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.meetparty", null,
						function(mesg) {
							_23f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						scroll(0, 0);
						return (false);
					} else {
						ShowGenInline(_246);
						return true;
					}
				}
			}
		}
	}
}
function validateDocNoteForm(stat, id) {
	if (stat == "add" || stat == "addother") {
		var _24f = "addDocNotes_" + id;
		if (stat == "addother") {
			_24f = "addOthDocNotes_" + id;
		}
		if (eval("document." + _24f + ".notes.value") == "") {
			i18n.getJSAlertValue(Utils.zuid, "zp.attachments.doccomcanbeempty", null,
			function(mesg) {
				alert(mesg);
			});
			eval("document." + _24f + ".notes.focus()");
			return (false);
		} else {
			if (trim(eval("document." + _24f + ".notes.value")).length == 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.doccomment.validdocnote", null,
				function(mesg) {
					alert(mesg);
				});
				eval("document." + _24f + ".notes.focus()");
				return (false);
			} else {
				ShowGenInline("zohobusy_add_note_" + id);
				return true;
			}
		}
	} else {
		if (stat == "update") {
			var _24f = "updateDocNotes_" + id;
			if (eval("document." + _24f + ".notes.value") == "") {
				i18n.getJSAlertValue(Utils.zuid, "zp.attachments.doccomcanbeempty", null,
				function(mesg) {
					alert(mesg);
				});
				eval("document." + _24f + ".notes.focus()");
				return (false);
			} else {
				if (trim(eval("document." + _24f + ".notes.value")).length == 0) {
					i18n.getJSAlertValue(Utils.zuid, "zp.doccomment.validdocnote", null,
					function(mesg) {
						alert(mesg);
					});
					eval("document." + _24f + ".notes.focus()");
					return (false);
				} else {
					ShowGenInline("zohobusy_upd_note_" + id);
					return true;
				}
			}
		}
	}
}
function validateTaskNoteForm(stat, id) {
	if (stat == "add") {
		var _256 = "addTaskNotes_" + id;
		if (document.getElementById("addtnote_" + id).value == "") {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.tasknotesemp", null,
			function(mesg) {
				alert(mesg);
			});
			document.getElementById("addtnote_" + id).focus();
			return (false);
		} else {
			ShowGenInline("zohobusy_add_task_note_" + id);
			return true;
		}
	} else {
		if (stat == "update") {
			var _256 = "updateTaskNotesForm_" + id;
			if (document.getElementById("edittnote_" + id).value == "") {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.tasknotesemp", null,
				function(mesg) {
					alert(mesg);
				});
				document.getElementById("edittnote_" + id).focus();
				return (false);
			} else {
				ShowGenInline("zohobusy_upd_task_note_" + id);
				return true;
			}
		}
	}
}
function validateTListForm(stat) {
	if (stat == "add") {
		var _25a = document.getElementById("tlist_top_add_status");
		var _25b = document.addTodoList.tcateg.value;
		if (_25b == "task") {
			if (trim(document.addTodoList.todotitle.value).length == 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.tasklisttitle", null,
				function(mesg) {
					_25a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.addTodoList.todotitle.focus();
				return (false);
			} else {
				if (findScriptTags(trim(document.addTodoList.todotitle.value))) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valtasklisttitle", null,
					function(mesg) {
						_25a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.addTodoList.todotitle.focus();
					return (false);
				} else {
					ShowGenInline("zoho_add_tlist_busy");
					return true;
				}
			}
		} else {
			ShowGenInline("zoho_add_tlist_busy");
			return true;
		}
	} else {
		var _25e = "editTodoList_" + stat;
		var _25f = "zoho_update_tlist_busy_" + stat;
		var alId = "tlist_upd_status_" + stat;
		var _25a = document.getElementById(alId);
		if (trim(eval("document." + _25e + ".todotitle.value")).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.todolisttitle", null,
			function(mesg) {
				_25a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			eval("document." + _25e + ".todotitle.focus()");
			return (false);
		} else {
			if (findScriptTags(trim(eval("document." + _25e + ".todotitle.value")))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valtodolisttitle", null,
				function(mesg) {
					_25a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _25e + ".todotitle.focus()");
				return (false);
			} else {
				ShowGenInline(_25f);
				return true;
			}
		}
	}
}
function validateTMListForm(stat, mid) {
	if (stat == "add") {
		var _265 = "addTodoList_" + mid;
		var _266 = document.getElementById("tlist_add_status_" + mid);
		var _267 = document.getElementById("listtype_" + mid).value;
		if (_267 == "task") {
			if (trim(eval("document." + _265 + ".todotitle.value")).length == 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.todolisttitle", null,
				function(mesg) {
					_266.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _265 + ".todotitle.focus()");
				return (false);
			} else {
				if (findScriptTags(trim(eval("document." + _265 + ".todotitle.value")))) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valtodolisttitle", null,
					function(mesg) {
						_266.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					eval("document." + _265 + ".todotitle.focus()");
					return (false);
				} else {
					ShowGenInline("zoho_add_tlist_busy_" + mid);
					return true;
				}
			}
		} else {
			ShowGenInline("zoho_add_tlist_busy_" + mid);
			return true;
		}
	} else {
		var _265 = "editTodoList_" + stat;
		var _26a = "zoho_update_tlist_busy_" + stat;
		var alId = "tlist_upd_status_" + stat;
		var _266 = document.getElementById(alId);
		if (trim(eval("document." + _265 + ".todotitle.value")).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.todolisttitle", null,
			function(mesg) {
				_266.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			eval("document." + _265 + ".todotitle.focus()");
			return (false);
		} else {
			if (findScriptTags(trim(eval("document." + _265 + ".todotitle.value")))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valtodolisttitle", null,
				function(mesg) {
					_266.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _265 + ".todotitle.focus()");
				return (false);
			} else {
				ShowGenInline(_26a);
				return true;
			}
		}
	}
}
function validateTLogForm(stat) {
	var _26f = /^\d*[\:\.]{1}\d+$|^\d+$/;
	if (stat == "add") {
		var _270 = trim(document.addLogHours.loghours.value);
		document.addLogHours.loghours.value = _270;
		var _271 = document.addLogHours.tsheettype.value;
		var _272 = 0;
		var _273 = document.addLogHours.addhourstype.value;
		var _274 = document.addLogHours.frompage.value;
		var _275 = document.getElementById("add_log_status");
		var _276 = document.addLogHours.logdate.value;
		document.addLogHours.logsubmit.disabled = true;
		_275.innerHTML = "";
		if (_271 == "task" || _271 == "issue") {
			if (document.addLogHours.projId.value == "-1") {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsspecify", null,
				function(mesg) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valprojname", null,
					function(_278) {
						_275.innerHTML = "<span class=\"error\">" + mesg + " " + _278 + "</span>";
					});
				});
				document.addLogHours.projId.focus();
				document.addLogHours.logsubmit.disabled = false;
				return (false);
			} else {
				if (_271 == "task" && (document.addLogHours.logtask.value == "-1" || trim(document.addLogHours.logtask.value) == "")) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valtodotask", null,
					function(mesg) {
						_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					if (_271 == "task") {
						document.addLogHours.taskval.focus();
					}
					document.addLogHours.logsubmit.disabled = false;
					return (false);
				} else {
					if (_271 == "issue" && (document.addLogHours.logissue.value == "-1" || document.addLogHours.logissue.value == "")) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvalidissuetitle", null,
						function(mesg) {
							mesg = mesg.replace("{0}", Utils.bugReplaceValue);
							_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						document.addLogHours.logissue.focus();
						document.addLogHours.logsubmit.disabled = false;
						return (false);
					} else {
						if (_273 != "fromto" && trim(_270).length == 0) {
							i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.invalidformat", null,
							function(mesg) {
								_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							document.addLogHours.loghours.focus();
							document.addLogHours.logsubmit.disabled = false;
							return (false);
						} else {
							if (_273 != "fromto" && !_26f.test(_270)) {
								i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.invalidformat", null,
								function(mesg) {
									_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
								});
								document.addLogHours.loghours.focus();
								document.addLogHours.logsubmit.disabled = false;
								return (false);
							} else {
								if (trim(_276).length == 0) {
									i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.validdate", null,
									function(mesg) {
										_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
									});
									document.addLogHours.logdate.focus();
									document.addLogHours.logsubmit.disabled = false;
									return (false);
								} else {
									if (_273 == "fromto") {
										document.addLogHours.loghours.value = "0:00";
									}
									ShowGenInline("zoho_add_loghours_busy");
									return true;
								}
							}
						}
					}
				}
			}
		}
		if (_271 == "general") {
			if (document.addLogHours.projId.value == "-1") {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsspecify", null,
				function(mesg) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valprojname", null,
					function(_27f) {
						_275.innerHTML = "<span class=\"error\">" + mesg + " " + _27f + "</span>";
					});
				});
				document.addLogHours.projId.focus();
				document.addLogHours.logsubmit.disabled = false;
				return (false);
			} else {
				if (document.addLogHours.loggentask.value == "" || trim(document.addLogHours.loggentask.value).length == 0) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvaltask", null,
					function(mesg) {
						_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					if (document.getElementById("addlogtask")) {
						if (document.getElementById("addlogtask").style.display != "none") {
							document.addLogHours.loggentask.focus();
						}
					}
					document.addLogHours.logsubmit.disabled = false;
					return (false);
				} else {
					if (_273 != "fromto" && trim(_270).length == 0) {
						i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.invalidformat", null,
						function(mesg) {
							_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						document.addLogHours.loghours.focus();
						document.addLogHours.logsubmit.disabled = false;
						return (false);
					} else {
						if (_273 != "fromto" && !_26f.test(_270)) {
							i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.invalidformat", null,
							function(mesg) {
								_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							document.addLogHours.loghours.focus();
							document.addLogHours.logsubmit.disabled = false;
							return (false);
						} else {
							if (trim(_276).length == 0) {
								i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.validdate", null,
								function(mesg) {
									_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
								});
								document.addLogHours.logdate.focus();
								document.addLogHours.logsubmit.disabled = false;
								return (false);
							} else {
								if (_273 == "fromto") {
									document.addLogHours.loghours.value = "0:00";
								}
								ShowGenInline("zoho_add_loghours_busy");
								return true;
							}
						}
					}
				}
			}
		}
	} else {
		var _270 = trim(document.getElementById("edithours_" + stat).value);
		document.getElementById("edithours_" + stat).value = _270;
		var _272 = 0;
		var _273 = document.getElementById("updhourstype_" + stat).value;
		var _275 = document.getElementById("edit_log_status_" + stat);
		var _276 = document.getElementById("elogdate").value;
		if (document.getElementById("logtask_" + stat)) {
			var _284 = document.getElementById("logtask_" + stat).value;
			if (trim(_284).length == 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvaltask", null,
				function(mesg) {
					_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.getElementById("logtask_" + stat).focus();
				document.addLogHours.logsubmit.disabled = false;
				return (false);
			}
		}
		if (trim(_270).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.invalidformat", null,
			function(mesg) {
				_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.getElementById("edithours_" + stat).focus();
			document.addLogHours.logsubmit.disabled = false;
			return (false);
		} else {
			if (_273 != "fromto" && !_26f.test(_270)) {
				i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.invalidformat", null,
				function(mesg) {
					_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.getElementById("edithours_" + stat).focus();
				document.addLogHours.logsubmit.disabled = false;
				return (false);
			} else {
				if (trim(_276).length == 0) {
					i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.validdate", null,
					function(mesg) {
						_275.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.addLogHours.logdate.focus();
					document.addLogHours.logsubmit.disabled = false;
					return (false);
				} else {
					ShowGenInline("zoho_upd_loghours_busy_" + stat);
					return true;
				}
			}
		}
	}
}
function validateWeeklyLog() {
	var _289 = /^\d*[\:\.]{1}\d+$|^\d+$/;
	var _28a = document.getElementsByName("loghours");
	var _28b = document.getElementsByName("optionid");
	var _28c = document.getElementsByName("loggentask");
	var _28d = document.getElementsByName("projId");
	document.addLogHours.logsubmit.disabled = true;
	document.addLogHours.saveandaddnew.disabled = true;
	var _28e = true;
	for (var i = 1; i < _28b.length; i++) {
		var _290 = false;
		for (var j = i * 7; j < ((i + 1) * 7); j++) {
			_28a[j].value = trim(_28a[j].value);
			if (!_289.test(_28a[j].value) && trim(_28a[j].value).length != 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.invalidformatweekly", null,
				function(mesg) {
					alert(mesg);
				});
				document.addLogHours.logsubmit.disabled = false;
				document.addLogHours.saveandaddnew.disabled = false;
				return false;
			}
			if (trim(_28a[j].value).length != 0) {
				_28e = false;
				_290 = true;
			}
		}
		if (_290 == true) {
			if (_28d[i].value == "0") {
				i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.selectvalidproject", null,
				function(mesg) {
					alert(mesg + " " + i);
				});
				document.addLogHours.logsubmit.disabled = false;
				document.addLogHours.saveandaddnew.disabled = false;
				return false;
			}
			if (_28b[i].value == "" && _28c[i].value == "") {
				i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.selectvalidtask", null,
				function(mesg) {
					alert(mesg + " " + i);
				});
				document.addLogHours.logsubmit.disabled = false;
				document.addLogHours.saveandaddnew.disabled = false;
				return false;
			}
		}
	}
	if (_28e == true) {
		i18n.getJSAlertValue(Utils.zuid, "zp.timesheet.invalidformatweekly", null,
		function(mesg) {
			alert(mesg);
		});
		document.addLogHours.logsubmit.disabled = false;
		document.addLogHours.saveandaddnew.disabled = false;
		return false;
	}
	return true;
}
function validateCompTListForm(stat) {
	var _297 = "editTodoList_" + stat;
	var _298 = "zoho_update_tlist_busy_" + stat;
	var alId = "tlist_compupd_status_" + stat;
	var _29a = document.getElementById(alId);
	if (!eval("document." + _297 + ".tlcomp.checked")) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsselchkto", null,
		function(mesg) {
			_29a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return (false);
	} else {
		ShowGenInline(_298);
		return true;
	}
}
function validateTodoChecked(stat) {
	var _29d = "editTodoList_" + stat;
	if (!eval("document." + _29d + ".tlcomp.checked")) {
		return true;
	} else {
		return false;
	}
}
function validateTTaskForm(id, stat) {
	if (stat == "add") {
		var _2a0 = "addTodoTask_" + id;
		var _2a1 = "zoho_add_ttask_busy_" + id;
		var alId = "ttask_add_status_" + id;
		var _2a3 = eval("document." + _2a0 + ".duroption.value");
		var _2a4 = eval("document." + _2a0 + ".taskdate.value");
		var _2a5 = eval("document." + _2a0 + ".taskenddate.value");
		Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
		var _2a6 = compareDates(_2a4, Utils.dateformat, _2a5, Utils.dateformat);
		var _2a7 = document.getElementById(alId);
		eval("document." + _2a0 + ".addtasksubmit.disabled=true");
		if (trim(eval("document." + _2a0 + ".todotask.value")).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.pletask", null,
			function(mesg) {
				_2a7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			eval("document." + _2a0 + ".todotask.focus()");
			eval("document." + _2a0 + ".addtasksubmit.disabled=false");
			return (false);
		} else {
			if (findScriptTags(trim(eval("document." + _2a0 + ".todotask.value")))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvaltask", null,
				function(mesg) {
					_2a7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _2a0 + ".todotask.focus()");
				eval("document." + _2a0 + ".addtasksubmit.disabled=false");
				return (false);
			} else {
				if (!isNumeric(eval("document." + _2a0 + ".duration.value"))) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.tododurmustbe", null,
					function(mesg) {
						_2a7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					eval("document." + _2a0 + ".duration.focus()");
					eval("document." + _2a0 + ".addtasksubmit.disabled=false");
					return false;
				} else {
					if (trim(eval("document." + _2a0 + ".taskdate.value")).length == 0 && (trim(eval("document." + _2a0 + ".duration.value")).length != 0 || trim(eval("document." + _2a0 + ".taskenddate.value")).length != 0)) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valstartdate", null,
						function(mesg) {
							_2a7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						eval("document." + _2a0 + ".taskdate.focus()");
						eval("document." + _2a0 + ".addtasksubmit.disabled=false");
						return false;
					} else {
						if (_2a3 == "enddate" && _2a4 != "" && _2a5 != "" && _2a6 != 0) {
							i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
							function(mesg) {
								_2a7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							eval("document." + _2a0 + ".taskenddate.focus()");
							eval("document." + _2a0 + ".addtasksubmit.disabled=false");
							return false;
						} else {
							if (document.getElementById("gcalevnt")) {
								if (eval("document." + _2a0 + ".gcalevnt.checked")) {
									if (trim(eval("document." + _2a0 + ".taskdate.value")).length == 0) {
										i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.gappsalert", null,
										function(mesg) {
											_2a7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
										});
										eval("document." + _2a0 + ".taskdate.focus()");
										eval("document." + _2a0 + ".addtasksubmit.disabled=false");
										return false;
									}
								}
								ShowGenInline(_2a1);
								return true;
							} else {
								ShowGenInline(_2a1);
								return true;
							}
						}
					}
				}
			}
		}
	} else {
		if (stat == "update") {
			var _2a0 = "updateTodoTask_" + id;
			var _2a1 = "zoho_update_ttask_busy_" + id;
			var alId = "ttask_update_status_" + id;
			var _2a3 = eval("document." + _2a0 + ".duroption.value");
			var _2a4 = eval("document." + _2a0 + ".taskdate.value");
			var _2a5 = eval("document." + _2a0 + ".taskenddate.value");
			Utils.timeformat = Utils.timeformat.replace("aaa", "a");
			Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
			var _2ae = Utils.dateformat;
			if (Utils.taskinhr == "true" || ((!isDIHrs(_2a4)) || (!isDIHrs(_2a5)))) {
				_2ae = Utils.dateformat + " " + Utils.timeformat;
			}
			var _2a6 = compareDates(_2a4, _2ae, _2a5, _2ae);
			var _2a7 = document.getElementById(alId);
			eval("document." + _2a0 + ".edittasksubmit.disabled=true");
			if (trim(eval("document." + _2a0 + ".todotask.value")).length == 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.pletask", null,
				function(mesg) {
					_2a7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _2a0 + ".todotask.focus()");
				eval("document." + _2a0 + ".edittasksubmit.disabled=false");
				return (false);
			} else {
				if (findScriptTags(trim(eval("document." + _2a0 + ".todotask.value")))) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvaltask", null,
					function(mesg) {
						_2a7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					eval("document." + _2a0 + ".todotask.focus()");
					eval("document." + _2a0 + ".edittasksubmit.disabled=false");
					return (false);
				} else {
					if (!isNumeric(eval("document." + _2a0 + ".duration.value")) && (eval("document." + _2a0 + ".durtype.value") == "days") && _2a3 != "enddate") {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.tododurmustbe", null,
						function(mesg) {
							_2a7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						eval("document." + _2a0 + ".duration.focus()");
						eval("document." + _2a0 + ".edittasksubmit.disabled=false");
						return false;
					} else {
						if (eval("document." + _2a0 + ".duration.value") != "" && !isValDur(eval("document." + _2a0 + ".duration.value")) && (eval("document." + _2a0 + ".durtype.value") == "hrs")) {
							i18n.getJSAlertValue(Utils.zuid, "zp.task.invdur", null,
							function(mesg) {
								_2a7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							eval("document." + _2a0 + ".duration.focus()");
							eval("document." + _2a0 + ".edittasksubmit.disabled=false");
							return false;
						} else {
							if (trim(eval("document." + _2a0 + ".taskdate.value")).length == 0 && (trim(eval("document." + _2a0 + ".duration.value")).length != 0 || trim(eval("document." + _2a0 + ".taskenddate.value")).length != 0)) {
								i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valstartdate", null,
								function(mesg) {
									_2a7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
								});
								eval("document." + _2a0 + ".taskdate.focus()");
								eval("document." + _2a0 + ".edittasksubmit.disabled=false");
								return false;
							} else {
								if (_2a3 == "enddate" && _2a4 != "" && _2a5 != "" && _2a6 != 0) {
									i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
									function(mesg) {
										_2a7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
									});
									eval("document." + _2a0 + ".taskenddate.focus()");
									eval("document." + _2a0 + ".edittasksubmit.disabled=false");
									return false;
								} else {
									ShowGenInline(_2a1);
									return true;
								}
							}
						}
					}
				}
			}
		}
	}
}
function validateGTTaskForm(stat) {
	if (stat == "add" || stat == "dep") {
		var _2b6 = "addTodoTaskForm";
		var _2b7 = "zoho_add_ttask_busy";
		var alId = "ttask_add_status";
		if (stat == "dep") {
			_2b6 = "addDepTodoTaskForm";
			_2b7 = "zoho_add_deptask_busy";
			alId = "deptask_add_status";
		}
		var _2b9 = eval("document." + _2b6 + ".duroption.value");
		var _2ba = eval("document." + _2b6 + ".taskdate.value");
		var _2bb = eval("document." + _2b6 + ".taskenddate.value");
		Utils.timeformat = Utils.timeformat.replace("aaa", "a");
		Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
		var _2bc = Utils.dateformat;
		if (Utils.taskinhr == "true") {
			_2bc = Utils.dateformat + " " + Utils.timeformat;
		}
		if (getDateFromFormat(_2ba, _2bc) == "0") {
			_2ba = "";
			document.addTodoTaskForm.taskdate.value = "";
		}
		if (getDateFromFormat(_2bb, _2bc) == "0") {
			_2bb = "";
			document.addTodoTaskForm.taskenddate.value = "";
		}
		var _2bd = compareDates(_2ba, _2bc, _2bb, _2bc);
		var _2be = document.getElementById(alId);
		eval("document." + _2b6 + ".addtoptasksubmit.disabled=true");
		if (trim(eval("document." + _2b6 + ".todotask.value")).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.todotask", null,
			function(mesg) {
				_2be.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			eval("document." + _2b6 + ".todotask.focus()");
			eval("document." + _2b6 + ".addtoptasksubmit.disabled=false");
			return (false);
		} else {
			if (findScriptTags(trim(eval("document." + _2b6 + ".todotask.value")))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvaltask", null,
				function(mesg) {
					_2be.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _2b6 + ".todotask.focus()");
				eval("document." + _2b6 + ".addtoptasksubmit.disabled=false");
				return (false);
			} else {
				if (!isNumeric(eval("document." + _2b6 + ".duration.value")) && (eval("document." + _2b6 + ".durtype.value") == "days")) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.tododurmustbe", null,
					function(mesg) {
						_2be.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					eval("document." + _2b6 + ".duration.focus()");
					eval("document." + _2b6 + ".addtoptasksubmit.disabled=false");
					return false;
				} else {
					if (eval("document." + _2b6 + ".duration.value") != "" && !isValDur(eval("document." + _2b6 + ".duration.value")) && (eval("document." + _2b6 + ".durtype.value") == "hrs")) {
						i18n.getJSAlertValue(Utils.zuid, "zp.task.invdur", null,
						function(mesg) {
							_2be.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						eval("document." + _2b6 + ".duration.focus()");
						eval("document." + _2b6 + ".addtoptasksubmit.disabled=false");
						return false;
					} else {
						if (trim(eval("document." + _2b6 + ".taskdate.value")).length == 0 && (trim(eval("document." + _2b6 + ".duration.value")).length != 0 || trim(eval("document." + _2b6 + ".taskenddate.value")).length != 0)) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valstartdate", null,
							function(mesg) {
								_2be.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							eval("document." + _2b6 + ".taskdate.focus()");
							eval("document." + _2b6 + ".addtoptasksubmit.disabled=false");
							return false;
						} else {
							if (_2b9 == "enddate" && _2ba != "" && _2bb != "" && _2bd != 0) {
								i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
								function(mesg) {
									_2be.innerHTML = "<span class=\"error\">" + mesg + "</span>";
								});
								eval("document." + _2b6 + ".taskenddate.focus()");
								eval("document." + _2b6 + ".addtoptasksubmit.disabled=false");
								return false;
							} else {
								if (document.getElementById("gcalevnt")) {
									if (eval("document." + _2b6 + ".gcalevnt.checked")) {
										if (trim(eval("document." + _2b6 + ".taskdate.value")).length == 0) {
											i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valstartdate", null,
											function(mesg) {
												_2be.innerHTML = "<span class=\"error\">" + mesg + "&nbsp;to add this event to GCal</span>";
											});
											eval("document." + _2b6 + ".taskdate.focus()");
											eval("document." + _2b6 + ".addtoptasksubmit.disabled=false");
											return false;
										}
									}
									ShowGenInline(_2b7);
									return true;
								} else {
									ShowGenInline(_2b7);
									return true;
								}
							}
						}
					}
				}
			}
		}
	}
}
function validateUploadNextForm() {
	var doc = document;
	var _2c7 = doc.getElementById("upload_next_version");
	if (trim(document.updateDocument.uploaddoc.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valfile", null,
		function(mesg) {
			_2c7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		doc.updateDocument.uploaddoc.focus();
		return false;
	} else {
		if (isFilesSizeGreater("uploaddoc", 125)) {
			i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.greatersize", null,
			function(mesg) {
				_2c7.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return false;
		}
	}
	ShowGenInline("zoho_update_doc_busy");
	return true;
}
function changePlanAsk(stat, plan, _2cc) {
	var _2cc = _2cc.replace("<br>", "\n");
	if (confirm(plan)) {
		if (stat == "downgrade") {
			if (confirm(_2cc)) {
				return (true);
			} else {
				return (false);
			}
		} else {
			return (true);
		}
	} else {
		return (false);
	}
}
function resetPassAsk() {
	if (confirm("Do you want to reset password for this user")) {
		return (true);
	} else {
		return (false);
	}
}
function askOnDelete(msg) {
	var msg = msg.replace("<br>", "\n");
	if (confirm(msg)) {
		return (true);
	} else {
		return (false);
	}
}
function tlistArchiveAsk() {
	if (confirm("Do you want to archive this todo list and related todo tasks. \n This will mark all the todo tasks as completed")) {
		return (true);
	} else {
		return (false);
	}
}
function newsDeleteAsk() {
	if (confirm("Do you want to remove this news")) {
		return (true);
	} else {
		return (false);
	}
}
function setBgColor(bg, _2cf, _2d0) {
	var sbg = "";
	if (_2d0 != null) {
		sbg = _2d0 + bg;
	} else {
		sbg = bg;
	}
	if (_2cf == "over") {
		document.getElementById(sbg).style.background = "#ffffcc";
	} else {
		if (_2cf == "out") {
			document.getElementById(sbg).style.background = "#ffffff";
		}
	}
}
function archiveDiscAsk() {
	if (confirm("Do you want to archive this discussion and upload it to documents.")) {
		return (true);
	} else {
		return (false);
	}
}
function logoutChat(url) {
	CustomChat.quit(Utils.chatId);
	location.href = url;
}
function validateChatTitleForm(stat) {
	var _2d4 = "updateChatTitleForm_" + stat;
	if (trim(eval("document." + _2d4 + ".ctitle.value")).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.disctopic", null,
		function(mesg) {
			alert(mesg);
		});
		eval("document." + _2d4 + ".ctitle.focus()");
		return (false);
	} else {
		if (findScriptTags(trim(eval("document." + _2d4 + ".ctitle.value")))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valdisctopic", null,
			function(mesg) {
				alert(mesg);
			});
			eval("document." + _2d4 + ".ctitle.focus()");
			return (false);
		} else {
			return true;
		}
	}
}
function validateFoldTitleForm(stat) {
	var _2d8 = "eFoldTitle_" + stat;
	var _2d9 = "zoho_update_folder_busy_" + stat;
	if (trim(eval("document." + _2d8 + ".foldname.value")).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyfoldname", null,
		function(mesg) {
			alert(mesg);
		});
		eval("document." + _2d8 + ".foldname.focus()");
		return (false);
	} else {
		if (findScriptTags(trim(eval("document." + _2d8 + ".foldname.value")))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.validfolname", null,
			function(mesg) {
				alert(mesg);
			});
			eval("document." + _2d8 + ".foldname.focus()");
			return (false);
		} else {
			return true;
		}
	}
}
function validateForumTitleForm(stat) {
	var _2dd = "eForumTitle_" + stat;
	var _2de = "zoho_update_mescat_busy_" + stat;
	if (trim(eval("document." + _2dd + ".forumname.value")).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.forumtitle", null,
		function(mesg) {
			alert(mesg);
		});
		eval("document." + _2dd + ".forumname.focus()");
		return (false);
	} else {
		ShowGenInline(_2de);
		return true;
	}
}
function validateRespTodoForm(_2e0) {
	var _2e1 = document.getElementById(_2e0);
	if (trim(document.respTodoForm.startfrom.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.startdateempty", null,
		function(mesg) {
			_2e1.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.respTodoForm.startfrom.focus();
		return (false);
	} else {
		ShowGenInline("zoho_task_reminder_busy");
		return true;
	}
}
function isNumeric(_2e3) {
	var _2e4 = "0123456789";
	var _2e5 = true;
	var Char;
	if (_2e3 != "") {
		for (var i = 0; i < _2e3.length && _2e5 == true; i++) {
			Char = _2e3.charAt(i);
			if (_2e4.indexOf(Char) == -1) {
				_2e5 = false;
			}
		}
		if (!_2e5) {
			return false;
		} else {
			return true;
		}
	} else {
		return true;
	}
}
function _show(_2e8) {
	var id = document.getElementById(_2e8);
	if (id.style.display == "none") {
		startSlider(id, true, false, "block");
	} else {
		startSlider(id, false, false, "none");
	}
}
function _hide(_2ea) {
	var id = document.getElementById(_2ea);
	startSlider(id, false, false, "none");
}
function setCompanyKey() {
	if (document.signupForm.orgname.value != "") {
		var pn = document.signupForm.orgname.value;
		var arr = pn.split(" ");
		var _2ee = arr[0].toLowerCase();
		document.signupForm.porturl.value = _2ee;
	}
}
function ValidateSignUp() {
	var _2ef = document.getElementById("signup_status");
	if (trim(document.signupForm.orgname.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyorgname", null,
		function(mesg) {
			_2ef.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.signupForm.orgname.focus();
		return false;
	} else {
		if (trim(document.signupForm.porturl.value).length == 0) {
			_2ef.innerHTML = "<span class=\"error\">Please specify <b>Portal URL</b></span>";
			document.signupForm.porturl.focus();
			return false;
		} else {
			if (trim(document.signupForm.porturl.value).length > 25) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.validporturl", null,
				function(mesg) {
					_2ef.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.signupForm.porturl.focus();
				return false;
			} else {
				if (trim(document.signupForm.adminname.value).length == 0) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyadminid", null,
					function(mesg) {
						_2ef.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.signupForm.adminname.focus();
					return false;
				} else {
					if (trim(document.signupForm.adminpassword.value).length == 0) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifypwd", null,
						function(mesg) {
							_2ef.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						document.signupForm.adminpassword.focus();
						return false;
					}
				}
			}
		}
	}
	var _2f4 = /^[a-z0-9 ]{0,100}$/i;
	var _2f5 = /^[a-z0-9]{0,100}$/i;
	var _2f6 = document.signupForm.orgname.value;
	var purl = document.signupForm.porturl.value;
	if (!_2f5.test(purl)) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.domainvalidation", null,
		function(mesg) {
			_2ef.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return false;
	}
	var _2f9 = /^[a-z0-9]([a-z0-9_\-\.\+]*)@([a-z0-9_\-\.]*)(\.[a-z]{2,4}(\.[a-z]{2}){0,2})$/i;
	var _2fa = document.signupForm.adminname.value;
	if (!_2f9.test(_2fa)) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valemailid", null,
		function(mesg) {
			_2ef.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return false;
	}
	return true;
}
function validateCompForm() {
	var _2fc = document.getElementById("comp_upd_status");
	if (trim(document.companyForm.companyname.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyorgname", null,
		function(mesg) {
			_2fc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.companyForm.companyname.focus();
		return false;
	} else {
		if (findScriptTags(trim(document.companyForm.webaddress.value))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valwebaddress", null,
			function(mesg) {
				_2fc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.companyForm.webaddress.focus();
			return false;
		} else {
			if (findScriptTags(trim(document.companyForm.street.value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.validaddress", null,
				function(mesg) {
					_2fc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.companyForm.street.focus();
				return false;
			} else {
				if (findScriptTags(trim(document.companyForm.city.value))) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valcity", null,
					function(mesg) {
						_2fc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.companyForm.city.focus();
					return false;
				} else {
					if (findScriptTags(trim(document.companyForm.state.value))) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valstate", null,
						function(mesg) {
							_2fc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						document.companyForm.state.focus();
						return false;
					} else {
						if (findScriptTags(trim(document.companyForm.country.value))) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valcountry", null,
							function(mesg) {
								_2fc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							document.companyForm.country.focus();
							return false;
						} else {
							if (findScriptTags(trim(document.companyForm.zipcode.value))) {
								i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valzipcode", null,
								function(mesg) {
									_2fc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
								});
								document.companyForm.zipcode.focus();
								return false;
							} else {
								ShowGenInline("zoho_upd_comp_prof_busy");
								disableButton("zoho_upd_comp_prof_submit");
								return true;
							}
						}
					}
				}
			}
		}
	}
}
function validateClientCompForm() {
	var _304 = document.getElementById("comp_upd_status");
	if (trim(document.clientCompanyForm.companyname.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifycliname", null,
		function(mesg) {
			_304.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.clientCompanyForm.companyname.focus();
		return false;
	} else {
		if (trim(document.clientCompanyForm.companyname.value).length > 100) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.ccnamelesschar", null,
			function(mesg) {
				_304.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.clientCompanyForm.companyname.focus();
			return false;
		} else {
			if (findScriptTags(trim(document.clientCompanyForm.webaddress.value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valwebaddress", null,
				function(mesg) {
					_304.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.clientCompanyForm.webaddress.focus();
				return false;
			} else {
				if (findScriptTags(trim(document.clientCompanyForm.firstaddress.value))) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.validaddress", null,
					function(mesg) {
						_304.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.clientCompanyForm.firstaddress.focus();
					return false;
				} else {
					if (findScriptTags(trim(document.clientCompanyForm.secondaddress.value))) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valsecaddress", null,
						function(mesg) {
							_304.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						document.clientCompanyForm.secondaddress.focus();
						return false;
					} else {
						if (findScriptTags(trim(document.clientCompanyForm.city.value))) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valcity", null,
							function(mesg) {
								_304.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							document.clientCompanyForm.city.focus();
							return false;
						} else {
							if (findScriptTags(trim(document.clientCompanyForm.state.value))) {
								i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valstate", null,
								function(mesg) {
									_304.innerHTML = "<span class=\"error\">" + mesg + "</span>";
								});
								document.clientCompanyForm.state.focus();
								return false;
							} else {
								if (findScriptTags(trim(document.clientCompanyForm.country.value))) {
									i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valcountry", null,
									function(mesg) {
										_304.innerHTML = "<span class=\"error\">" + mesg + "</span>";
									});
									document.clientCompanyForm.country.focus();
									return false;
								} else {
									if (findScriptTags(trim(document.clientCompanyForm.zipcode.value))) {
										i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valzipcode", null,
										function(mesg) {
											_304.innerHTML = "<span class=\"error\">" + mesg + "</span>";
										});
										document.clientCompanyForm.zipcode.focus();
										return false;
									} else {
										ShowGenInline("zoho_upd_comp_prof_busy");
										return true;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
function changeLogo() {
	ShowHideInline("hidlogoid", "showlogoid");
}
function changePowerLogo() {
	ShowHideInline("hidpowerlogoid", "showpowerlogoid");
}
function validatePartiInviteForm() {
	var _30e = document.getElementById("inv_parti_status");
	var _30f = document.getElementById("chatparticipants_inv");
	if (_30f.length < 1) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.selparticipants", null,
		function(mesg) {
			_30e.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.invPartiForm.addparticipants.focus();
		return (false);
	} else {
		ShowGenInline("zoho_inv_parti_chat_busy");
		return true;
	}
}
var startsWith = function(str, _312) {
	var _313 = _312.length;
	var _314 = str.length;
	if (_313 > str.length) {
		return false;
	}
	return (str.substr(0, _313) == _312);
};
var endsWith = function(str, _316) {
	var _317 = _316.length;
	var _318 = str.length;
	if (_317 > str.length) {
		return false;
	}
	return (str.substr(_318 - _317, _317) == _316);
};
var win = null;
function NewWindow(_319, _31a, w, h, _31d) {
	LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
	TopPosition = (screen.height) ? (screen.height - h) / 2 : 0;
	settings = "height=" + h + ",width=" + w + ",top=" + TopPosition + ",left=" + LeftPosition + ",scrollbars=" + _31d + "";
	win = window.open(_319, _31a, settings);
	win.focus();
}
function reOrder(_31e) {
	var _31f = "reorder_separator_" + _31e;
	var _320 = "additem_separator_" + _31e;
	var _31e = "ul_ttask_" + _31e;
	Sortable.create(_31e, {
		tag: "tr",
		constraint: false
	});
	ShowHideInline(_31f, _320);
	document.getElementById(_31e).className = "reorder ulstyle";
	var _321 = "anch_" + _31e;
	var _322 = "unanch_" + _31e;
	expandCollapseDiv(_321, "hide", "div");
	expandCollapseDiv(_322, "inline", "div");
}
function saveOrder(_323, url) {
	var _325 = "reorder_separator_" + _323;
	var _326 = "additem_separator_" + _323;
	var _327 = "ul_ttask_" + _323;
	ShowHideInline(_326, _325);
	url = url + "&sort_order=" + Sortable.serialize(_327);
	ajaxSaveOrder(url, _327);
	document.getElementById(_327).className = "ulstyle";
	Sortable.destroy(_327);
}
function saveTaskTemplatesOrder(_328, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var _32a = "reorder_separator_" + _328;
	var _32b = "additem_separator_" + _328;
	var _32c = "ul_ttask_" + _328;
	ShowHideInline(_32b, _32a);
	var url = Utils.contPath + "/savetasktemporder.do?ListId=" + _328 + "&sort_order=" + Sortable.serialize(_32c) + "&" + csrf;
	ajaxSaveOrder(url, _32c);
	document.getElementById(_32c).className = "ulstyle";
	Sortable.destroy(_32c);
}
function tListtemplatesReOrder(csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var url = Utils.contPath + "/reordertlisttemporder.do?" + csrf;
	ajaxShowPage(url, "projectcontent");
}
function tListtemplatesReorder1() {
	var _330 = "reorder_separator";
	var _331 = "additem_separator";
	var _332 = "ul_tlist_tlistid";
	var hide = document.getElementsByTagName("div");
	for (var i = 0; i < hide.length; i++) {
		if (hide[i].id == "hideListItems") {
			hide[i].style.display = "none";
		}
	}
	Sortable.create(_332);
	ShowHideInline(_330, _331);
	document.getElementById(_332).className = "reorder";
}
function tListtemplatesSaveOrder(csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var _336 = "reorder_separator";
	var _337 = "additem_separator";
	var _338 = "tlreorder_id";
	ShowHideInline(_337, _336);
	var url = Utils.contPath + "/savetasklisttemporder.do?sort_order=" + Sortable.serialize(_338) + "&" + csrf;
	ajaxShowPage(url, "projectcontent");
}
function saveProjOrder(_33a, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var url = Utils.contPath + "/saveprojorder.do?sort_order=" + Sortable.serialize(_33a) + "&" + csrf;
	ajaxSaveProjectOrder(url);
	Sortable.create(_33a);
	if (document.getElementById("seq_upd_response")) {
		i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.projordsave", null,
		function(mesg) {
			document.getElementById("seq_upd_response").innerHTML = "<span class=\"pop tabLoading\"><b>" + mesg + "</b></span>";
		});
	}
}
function saveAlpProjOrder(_33e, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var list = document.getElementById(_33e);
	var _341 = list.getElementsByTagName("li");
	var _342 = new Array();
	var _343 = "";
	for (var i = 0; i < _341.length; i++) {
		_342[i] = _341[i].innerHTML + "z-z-z" + ((_341[i].id).split("_"))[1];
	}
	function sortCaseInsensitive(a, b) {
		if (a.toLowerCase() < b.toLowerCase()) {
			return - 1;
		}
		if (a.toLowerCase() > b.toLowerCase()) {
			return 1;
		}
		return 0;
	}
	_342.sort(sortCaseInsensitive);
	for (var i = 0; i < _342.length; i++) {
		if (_343.length > 0) {
			_343 += ",";
		}
		_343 += (_342[i].split("z-z-z"))[1];
	}
	var url = Utils.contPath + "/saveprojorder.do?sort_order=" + _343 + "&" + csrf;
	ajaxSaveAlpProjectOrder(url, csrf);
	if (document.getElementById("seq_upd_response")) {
		i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.projordsave", null,
		function(mesg) {
			document.getElementById("seq_upd_response").innerHTML = "<span class=\"pop tabLoading\"><b>" + mesg + "</b></span>";
		});
	}
}
function reOrderLists(_349, mid) {
	var hide = document.getElementsByTagName("div");
	for (var i = 0; i < hide.length; i++) {
		if (hide[i].id == "hideListItems_" + mid) {
			hide[i].style.display = "none";
		}
	}
	Sortable.create(_349);
	ShowHideInline("saveorder_span_" + mid, "reorder_span_" + mid);
	document.getElementById(_349).className = "reorder ulstyle";
}
function saveListOrder(_34d, _34e, _34f, mid, _351, _352) {
	ShowHideInline("reorder_span_" + mid, "saveorder_span_" + mid);
	var url = Utils.contPath + "/savetlistorder.do?mid=" + mid + "&sort_order=" + Sortable.serialize(_34d) + "&" + _351 + "=" + encodeURIComponent(_352);
	ajaxSaveOrder(url);
	var hide = document.getElementsByTagName("div");
	for (var i = 0; i < hide.length; i++) {
		if (hide[i].id == "hideListItems_" + mid) {
			hide[i].style.display = "block";
		}
	}
	document.getElementById(_34d).className = "ulstyle";
	Sortable.destroy(_34d);
}
function saveMstoneOrder(_356, _357, _358, _359, _35a, _35b, _35c) {
	var _35d = Sortable.serialize(_356);
	if (_35d.indexOf(",") == 0) {
		_35d = _35d.substring(1, _35d.length);
	}
	var url = Utils.contPath + "/savemstoneorder.do?mstype=" + _358 + "&modtype=" + _35a + "&username=" + _359 + "&projId=" + _357 + "&sort_order=" + _35d + "&" + _35b + "=" + encodeURIComponent(_35c);
	ajaxSaveOrder(url, _356);
	document.getElementById(_356).className = "ulstyle";
	Sortable.destroy(_356);
}
function changeTabStyle(elem) {
	changeElementClass("hometab", "");
	changeElementClass("workcaltab", "");
	changeElementClass("logcaltab", "");
	changeElementClass("searchtab", "");
	changeElementClass("billingtab", "");
	changeElementClass("peopleviewtab", "");
	changeElementClass("settingstab", "");
	changeElementClass("reportviewtab", "");
	changeElementClass("mybugstab", "");
	changeElementClass(elem + "tab", "selectedTab");
	if (elem == "peopleview") {
		if (document.getElementById("allprojectstab")) {
			document.getElementById("allprojectstab").className = "";
			ShowHideInline("subPeopleLayer", "subTabLayer");
		}
	} else {
		if (document.getElementById("allprojectstab")) {
			ShowHideInline("subTabLayer", "subPeopleLayer");
		}
	}
}
function changeProjTabStyle(elem) {
	changeElementClass("dboardtab", "");
	changeElementClass("tomitab", "");
	changeElementClass("caltab", "");
	changeElementClass("meettab", "");
	changeElementClass("doctab", "");
	changeElementClass("tsheettab", "");
	changeElementClass("reporttab", "");
	changeElementClass("forumtab", "");
	changeElementClass("wikitab", "");
	changeElementClass("chattab", "");
	changeElementClass("userstab", "");
	changeElementClass("issuetab", "");
	changeElementClass("issuesettingstab", "selectedTab");
	changeElementClass("issuereportstab", "selectedTab");
	changeElementClass("issuemilestonetab", "selectedTab");
	changeElementClass(elem + "tab", "selectedTab");
	var i;
	if (is_ie) {
		showHideElements_iefix("div", "subTabLayer", "hide");
	} else {
		var _362 = document.getElementsByName("subTabLayer");
		for (i = 0; i < _362.length; i++) {
			_362[i].className = "hide";
		}
	}
	if (elem == "dboard") {
		ShowGenInline("orgSubLayer");
		changeSubLink("dashlink", "dashImg");
	}
	if (elem == "tomi") {
		ShowGenInline("taskSubLayer");
		changeSubLink("taskviewlink", "taskviewImg");
	} else {
		if (elem == "issue") {
			ShowGenInline("bugSubLayer");
			changeSubLink("bugviewlink", "bugviewImg");
		} else {
			if (elem == "issuesettings") {
				ShowGenInline("bugSubLayer");
				changeSubLink("bugSettings", "bugSettingsviewImg");
			} else {
				if (elem == "issuereports") {
					ShowGenInline("bugSubLayer");
					changeSubLink("bugreportsviewlink", "bugreportsviewImg");
				} else {
					if (elem == "issuemilestone") {
						ShowGenInline("bugSubLayer");
						changeSubLink("bugmileviewlink", "bugmileviewImg");
					} else {
						if (elem == "cal") {
							ShowGenInline("calSubLayer");
						} else {
							if (elem == "meet") {
								ShowGenInline("meetSubLayer");
								changeSubLink("uplink", "upImg");
							} else {
								if (elem == "doc") {
									ShowGenInline("docSubLayer");
								} else {
									if (elem == "tsheet") {
										ShowGenInline("tsheetSubLayer");
										changeSubLink("lvLink", "lvImg");
									} else {
										if (elem == "report") {
											ShowGenInline("reportSubLayer");
										} else {
											if (elem == "forum") {
												ShowGenInline("forumSubLayer");
											} else {
												if (elem == "wiki") {
													ShowGenInline("wikiSubLayer");
												} else {
													if (elem == "chat") {
														ShowGenInline("chatSubLayer");
													} else {
														if (elem == "users") {
															ShowGenInline("usersSubLayer");
															changeSubLink("usLink", "usImg");
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
function validateFcatForm() {
	var _363 = document.getElementById("forum_add_status");
	if (trim(document.addForumCategory.categoryname.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.cattitle", null,
		function(mesg) {
			_363.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.addForumCategory.categoryname.focus();
		return false;
	} else {
		if (findScriptTags(trim(document.addForumCategory.categoryname.value))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valcattitle", null,
			function(mesg) {
				_363.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.addForumCategory.categoryname.focus();
			return false;
		} else {
			ShowGenInline("zoho_add_forum_busy");
			return true;
		}
	}
}
function validateForumForm(stat) {
	var _367 = (stat == "add") ? "postDiscussion": ("updateDiscussion_" + stat);
	var elem, _369;
	var doc = document;
	var _36b = doc.getElementById((stat == "add") ? "forum_post_add_status": ("forum_post_upd_status_" + stat));
	elem = doc[_367].mesgtitle;
	if (trim(elem.value).length == 0) {
		_369 = "zp.functionjs.posttitle";
		scroll(0, 0);
	} else {
		if (elem.value.length > 99) {
			_369 = "zp.forum.title";
			scroll(0, 0);
		} else {
			if (findScriptTags(elem.value)) {
				_369 = "zp.functionjs.valposttitle";
				scroll(0, 0);
			}
		}
	}
	if (!_369) {
		elem = doc[_367].mesgbody;
		if (trim(elem.value).length == 0 || trim(elem.value) == "<br>") {
			_369 = "zp.functionjs.postcontent";
			scroll(0, 0);
		} else {
			if (findScriptTags(elem.value)) {
				_369 = "zp.functionjs.valpostcontent";
				scroll(0, 0);
			}
		}
	}
	if (_369) {
		elem.focus();
	} else {
		if (isFilesSizeGreater("uploadfile", 125)) {
			_369 = "zp.docdetails.greatersize";
			scroll(0, 0);
		}
	}
	if (_369) {
		i18n.getJSAlertValue(Utils.zuid, _369, null,
		function(mesg) {
			_36b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return false;
	}
	ShowGenInline((stat == "add") ? "zoho_add_post_busy": ("zoho_update_post_busy_" + stat));
	return true;
}
function EmptyDivContent(id) {
	var _36e = document.getElementById(id);
	if (_36e != null) {
		_36e.innerHTML = "";
	}
	ShowGenBlock(id);
}
function loadAjaxTab(str) {
	var _370 = document.getElementById("ajax_load_tab");
	ShowGenBlock("ajax_load_tab");
}
function loadAjaxContent(str, _372) {
	ShowGenBlock("ajax_load_tab");
}
function dispServerResponse(str) {
	ShowGenBlock("ajax_server_response");
	var _374 = document.getElementById("ajax_server_response");
	_374.innerHTML = str;
	setTimeout(function() {
		Hide("ajax_server_response");
	},
	2000);
}
function dispChatResponse(str) {
	ShowGenBlock("ajax_chat_response");
	var _376 = document.getElementById("ajax_chat_response");
	_376.innerHTML = str;
	setTimeout(function() {
		Hide("ajax_chat_response");
	},
	6000);
}
function validateChatUploadForm(stat) {
	var _378 = trim(document.uploadChatFileForm.chatfile.value).lastIndexOf("/");
	if (_378 == -1) {
		_378 = trim(document.uploadChatFileForm.chatfile.value).lastIndexOf("\\");
	}
	if (trim(document.uploadChatFileForm.chatfile.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.chosseuplfile", null,
		function(mesg) {
			alert(mesg);
		});
		document.uploadChatFileForm.chatfile.focus();
		return false;
	} else {
		if (endsWith(trim(document.uploadChatFileForm.chatfile.value), "/")) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.chosseuplfile", null,
			function(mesg) {
				alert(mesg);
			});
			document.uploadChatFileForm.chatfile.focus();
			return false;
		} else {
			return true;
		}
	}
}
function validateLSearchForm() {
	var _37b = document.getElementById("loginerror_status");
	if (trim(document.loginSearchForm.fpemail.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valemailid", null,
		function(mesg) {
			_37b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.loginSearchForm.fpemail.focus();
		return (false);
	} else {
		return true;
	}
}
function validateClientUserForm(stat) {
	var doc = document;
	var _37f = doc.getElementById("client_add_status_" + stat);
	var _380 = /^[a-z0-9]([a-z0-9_\-\.\+]*)@([a-z0-9_\-\.]*)(\.[a-z]{2,4}(\.[a-z]{2}){0,2})$/i;
	var _381 = "addClientUser_" + stat;
	var _382 = doc[_381].email;
	if (trim(_382.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyemailid", null,
		function(mesg) {
			_37f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		_382.focus();
		return false;
	} else {
		if (!_380.test(_382.value)) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valemailid", null,
			function(mesg) {
				_37f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			_382.focus();
			return false;
		}
	}
	ShowGenInline("zoho_addclient_user_busy_" + stat);
	return true;
}
function validateClientForm() {
	var _385 = document.getElementById("newclient_add_status");
	if (trim(document.addClient.clientname.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifycliname", null,
		function(mesg) {
			_385.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.addClient.clientname.focus();
		return false;
	} else {
		if (trim(document.addClient.clientname.value).length > 100) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.ccnamelesschar", null,
			function(mesg) {
				_385.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.addClient.clientname.focus();
			return false;
		} else {
			if (findScriptTags(trim(document.addClient.clientname.value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvalcliname", null,
				function(mesg) {
					_385.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.addClient.clientname.focus();
				return false;
			} else {
				return true;
			}
		}
	}
}
function addComponent(cnt, stat) {
	var ftd;
	if (stat == "add") {
		ftd = document.getElementById("forumtablediv");
	} else {
		ftd = document.getElementById("forumtablediv_" + stat);
	}
	if (!Utils.forumAttachment) {
		Utils.forumAttachment = 0;
	}
	Utils.forumAttachment = Utils.forumAttachment + 1;
	var sid;
	if (stat == "add") {
		sid = document.getElementById("forumtddiv");
		sid.className = "";
	} else {
		sid = document.getElementById("forumtddiv_" + stat);
		sid.className = "";
	}
	var tr = document.createElement("tr");
	if (stat == "add") {
		tr.id = "trid_" + cnt;
	} else {
		tr.id = "trid_" + stat + "_" + cnt;
	}
	var td = document.createElement("td");
	td.className = "pt3";
	var _38f = document.createElement("input");
	_38f.setAttribute("type", "file");
	_38f.setAttribute("name", "uploadfile");
	_38f.className = "attachbox";
	_38f.size = 40;
	var _390 = document.createElement("a");
	_390.setAttribute("href", "javascript:removeComponent(" + cnt + ",'" + stat + "');");
	_390.className = "optionsLink";
	i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.remove", null,
	function(mesg) {
		var _392 = document.createTextNode(mesg);
		_390.appendChild(_392);
	});
	td.appendChild(_38f);
	td.appendChild(document.createTextNode(" "));
	td.appendChild(_390);
	td.appendChild(document.createElement("br"));
	var kcnt = parseInt(cnt, 10) + 1;
	var _394 = document.createElement("span");
	_394.className = "";
	var _395 = document.createElement("a");
	_395.setAttribute("href", "javascript:addComponent(" + kcnt + ",'" + stat + "');");
	_395.className = "optionsLink";
	if (stat == "add") {
		_395.id = "attachlink_" + kcnt;
	} else {
		_395.id = "attachlink" + kcnt + "_" + stat;
	}
	i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.attachfile", null,
	function(mesg) {
		var _397 = document.createTextNode(mesg);
		_395.appendChild(_397);
	});
	_394.appendChild(_395);
	var btd;
	if (stat == "add") {
		btd = document.getElementById("forumtddiv");
	} else {
		btd = document.getElementById("forumtddiv_" + stat);
	}
	btd.innerHTML = "";
	btd.appendChild(_394);
	tr.appendChild(td);
	ftd.appendChild(tr);
	if (stat == "add") {
		Hide("attachlink_" + cnt);
	} else {
		Hide("attachlink" + cnt + "_" + stat);
	}
}
function removeComponent(cnt, stat) {
	Utils.forumAttachment = Utils.forumAttachment - 1;
	if (Utils.forumAttachment == 0) {
		if (stat == "add") {
			ShowGenInline("attachlink_1");
			var sid = document.getElementById("forumtddiv");
			sid.className = "hide";
		} else {
			ShowGenInline("attachlink1_" + stat);
			var sid = document.getElementById("forumtddiv_" + stat);
			sid.className = "hide";
		}
	}
	var d2;
	if (stat == "add") {
		d2 = document.getElementById("trid_" + cnt);
	} else {
		d2 = document.getElementById("trid_" + stat + "_" + cnt);
	}
	for (i = 0; i < d2.childNodes.length; i++) {
		d2.removeChild(d2.childNodes[i]);
	}
}
function addComComponent(cnt, stat) {
	var ftd;
	if (stat == "add") {
		ftd = document.getElementById("forumcomtablediv");
	} else {
		ftd = document.getElementById("forumcomtablediv_" + stat);
	}
	if (!Utils.forumComAttachment) {
		Utils.forumComAttachment = 0;
	}
	Utils.forumComAttachment = Utils.forumComAttachment + 1;
	var sid;
	if (stat == "add") {
		sid = document.getElementById("forumcomtddiv");
		sid.className = "";
	} else {
		sid = document.getElementById("forumcomtddiv_" + stat);
		sid.className = "";
	}
	var tr = document.createElement("tr");
	if (stat == "add") {
		tr.id = "trid_" + cnt;
	} else {
		tr.id = "trid_" + stat + "_" + cnt;
	}
	var td = document.createElement("td");
	td.className = "pt3";
	var _3a3 = document.createElement("input");
	_3a3.setAttribute("type", "file");
	_3a3.setAttribute("name", "uploadfile");
	_3a3.className = "attachbox";
	_3a3.size = 40;
	var _3a4 = document.createElement("a");
	_3a4.setAttribute("href", "javascript:removeComComponent(" + cnt + ",'" + stat + "');");
	_3a4.className = "optionsLink";
	i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.remove", null,
	function(mesg) {
		var _3a6 = document.createTextNode(mesg);
		_3a4.appendChild(_3a6);
	});
	td.appendChild(_3a3);
	td.appendChild(document.createTextNode(" "));
	td.appendChild(_3a4);
	td.appendChild(document.createElement("br"));
	var kcnt = parseInt(cnt, 10) + 1;
	var _3a8 = document.createElement("a");
	_3a8.setAttribute("href", "javascript:addComComponent(" + kcnt + ",'" + stat + "');");
	_3a8.className = "optionsLink";
	if (stat == "add") {
		_3a8.id = "attachcomlink_" + kcnt;
	} else {
		_3a8.id = "attachcomlink" + kcnt + "_" + stat;
	}
	i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.attachfile", null,
	function(mesg) {
		var _3aa = document.createTextNode(mesg);
		_3a8.appendChild(_3aa);
	});
	var btd;
	if (stat == "add") {
		btd = document.getElementById("forumcomtddiv");
	} else {
		btd = document.getElementById("forumcomtddiv_" + stat);
	}
	btd.innerHTML = "";
	btd.appendChild(_3a8);
	tr.appendChild(td);
	ftd.appendChild(tr);
	if (stat == "add") {
		Hide("attachcomlink_" + cnt);
	} else {
		Hide("attachcomlink" + cnt + "_" + stat);
	}
}
function removeComComponent(cnt, stat) {
	Utils.forumComAttachment = Utils.forumComAttachment - 1;
	if (Utils.forumComAttachment == 0) {
		if (stat == "add") {
			ShowGenInline("attachcomlink_1");
			var sid = document.getElementById("forumcomtddiv");
			sid.className = "hide";
		} else {
			ShowGenInline("attachcomlink1_" + stat);
			var sid = document.getElementById("forumcomtddiv_" + stat);
			sid.className = "hide";
		}
	}
	var d2;
	if (stat == "add") {
		d2 = document.getElementById("trid_" + cnt);
	} else {
		d2 = document.getElementById("trid_" + stat + "_" + cnt);
	}
	for (i = 0; i < d2.childNodes.length; i++) {
		d2.removeChild(d2.childNodes[i]);
	}
}
function closeLastChat() {}
function getCalPos(el) {
	var r = {
		offsetLeft: el.offsetLeft,
		offsetTop: el.offsetTop
	};
	if (el.offsetParent) {
		var tmp = getCalPos(el.offsetParent);
		r.offsetLeft += tmp.offsetLeft;
		r.offsetTop += tmp.offsetTop;
		r.offsetRight += tmp.offsetRight;
	}
	return r;
}
function ShowDayCube(_3b3, _3b4) {
	parDiv = document.getElementById(_3b4);
	chldDiv = document.getElementById(_3b3);
	chldDiv.className = "block posabs";
	if (is_safari) {
		chldDiv.style.left = getCalPos(parDiv).offsetLeft + 5 + "px";
		chldDiv.style.top = getCalPos(parDiv).offsetTop + 3 + "px";
	} else {
		chldDiv.style.left = getCalPos(parDiv).offsetLeft + 3 + "px";
		chldDiv.style.top = getCalPos(parDiv).offsetTop + 3 + "px";
	}
}
function CollapseMilestones() {
	var hide = document.getElementsByTagName("div");
	for (var i = 0; i < hide.length; i++) {
		if (hide[i].id == "hideLists") {
			hide[i].style.display = "none";
		}
	}
	ShowHideInline("expand_milestones", "collapse_milestones");
}
function ExpandMilestones() {
	var hide = document.getElementsByTagName("div");
	for (var i = 0; i < hide.length; i++) {
		if (hide[i].id == "hideLists") {
			hide[i].style.display = "block";
		}
	}
	ShowHideInline("collapse_milestones", "expand_milestones");
}
function CollapseIndMilestones(id) {
	var hide = document.getElementsByTagName("div");
	for (var i = 0; i < hide.length; i++) {
		if (hide[i].id == "hideLists_" + id) {
			hide[i].style.display = "none";
		}
	}
}
function ExpandIndMilestones(id) {
	var hide = document.getElementsByTagName("div");
	for (var i = 0; i < hide.length; i++) {
		if (hide[i].id == "hideLists_" + id) {
			hide[i].style.display = "block";
		}
	}
}
function CollapseLists() {
	expandCollapseDiv("rollcollapse_tlid", "hide", "span");
	expandCollapseDiv("rollexpand_tlid", "inline", "span");
	expandCollapseDiv("tasklist_div", "hide", "div");
	expandCollapseDiv("collapse_list", "hide", "div");
	ShowHideInline("expand_lists", "collapse_lists");
}
function ExpandLists() {
	expandCollapseDiv("rollcollapse_tlid", "inline", "span");
	expandCollapseDiv("rollexpand_tlid", "hide", "span");
	expandCollapseDiv("tasklist_div", "inline", "div");
	expandCollapseDiv("collapse_list", "inline", "div");
	ShowHideInline("collapse_lists", "expand_lists");
}
function divOnClick(_3bf, pid, _3c1, _3c2, mid, _3c4, _3c5, _3c6) {
	_addEvent(_3bf, "click",
	function() {
		i18n.getJSAlertValue(Utils.zuid, "zp.tomi.loadtomitab", null,
		function(mesg) {
			loadAjaxTab(mesg);
		});
		changeProjTabStyle("tomi");
		var csrf = _3c5 + "=" + _3c6;
		if (typeof(csrf) == "undefined") {} else {
			csrf = getCSRFEncode(csrf);
		}
		getSearchMiles(csrf, pid, "1242816182919", _3c4, mid);
	});
}
_addEvent = function(el, _3ca, func) {
	if (is_ie) {
		el.attachEvent("on" + _3ca, func);
	} else {
		el.addEventListener(_3ca, func, true);
	}
};
_removeEvent = function(el, _3cd, func) {
	if (is_ie) {
		el.detachEvent("on" + _3cd, func);
	} else {
		el.removeEventListener(_3cd, func, true);
	}
};
function setTaskDate(id, val) {
	document.getElementById(id).value = val;
}
function changeHomeTaskStyle(trid, stat) {
	var td = document.getElementById("hometd_" + trid);
	if (stat == "completed") {
		i18n.getJSAlertValue(Utils.zuid, "zp.projpage.completed", null,
		function(mesg) {
			td.innerHTML = mesg;
		});
	} else {
		if (stat == "today" || stat == "start today") {
			i18n.getJSAlertValue(Utils.zuid, "zp.hp.sttdy", null,
			function(mesg) {
				td.innerHTML = mesg;
			});
		} else {
			if (stat == "due today") {
				i18n.getJSAlertValue(Utils.zuid, "zp.caltask.duetoday", null,
				function(mesg) {
					td.innerHTML = mesg;
				});
			} else {
				if (stat == "upcoming") {
					i18n.getJSAlertValue(Utils.zuid, "zp.hp.upcoming", null,
					function(mesg) {
						td.innerHTML = mesg;
					});
				} else {
					if (stat == "ongoing") {
						i18n.getJSAlertValue(Utils.zuid, "zp.caltask.ongoing", null,
						function(mesg) {
							td.innerHTML = mesg;
						});
					} else {
						if (stat == "delayed") {
							i18n.getJSAlertValue(Utils.zuid, "zp.hp.delay", null,
							function(mesg) {
								td.innerHTML = mesg;
							});
						} else {
							td.innerHTML = "-";
						}
					}
				}
			}
		}
	}
}
function isDateCheck(_3da, _3db, cobj) {
	return true;
}
function isMileDateCheck(_3dd, _3de, cobj) {
	if (cobj.checked) {
		if (_3dd > _3de) {
			return true;
		} else {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.miledatebefstdate", null,
			function(mesg) {
				alert(mesg);
			});
			cobj.checked = false;
			return false;
		}
	} else {
		return true;
	}
}
function validateCCForm(_3e1) {
	var _3e2 = document.getElementById("signup_status");
	var _3e3 = /^[a-z0-9]([a-z0-9_\-\.\+]*)@([a-z0-9_\-\.]*)(\.[a-z]{2,4}(\.[a-z]{2}){0,2})$/i;
	var _3e4 = /^[0-9]{10,18}$/;
	var _3e5 = document.cvalidationForm.email.value;
	var cval = document.cvalidationForm.cardnum.value;
	var _3e7 = document.cvalidationForm.cardvernum.value;
	var _3e8 = document.getElementById("customloadingdiv");
	if (trim(document.cvalidationForm.email.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyemailid", null,
		function(mesg) {
			_3e2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.cvalidationForm.email.focus();
		return false;
	} else {
		if (trim(document.cvalidationForm.compname.value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyorgname", null,
			function(mesg) {
				_3e2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.cvalidationForm.compname.focus();
			return false;
		} else {
			if (!_3e3.test(_3e5)) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyorgname", null,
				function(mesg) {
					_3e2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.impropermailfmt", null,
				function(mesg) {
					_3e2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.cvalidationForm.email.focus();
				return false;
			} else {
				if (trim(document.cvalidationForm.streetaddress.value).length == 0) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyorgname", null,
					function(mesg) {
						_3e2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valstraddress", null,
					function(mesg) {
						_3e2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.cvalidationForm.streetaddress.focus();
					return false;
				} else {
					if (trim(document.cvalidationForm.zipcode.value).length == 0) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valzipcode", null,
						function(mesg) {
							_3e2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						document.cvalidationForm.zipcode.focus();
						return false;
					} else {
						if ((trim(document.cvalidationForm.cardnum.value).length == 0) || (!_3e4.test(document.cvalidationForm.cardnum.value))) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valcardno", null,
							function(mesg) {
								_3e2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							document.cvalidationForm.cardnum.focus();
							return false;
						} else {
							if (cval.indexOf(" ") != -1) {
								i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.nospacealwincardno", null,
								function(mesg) {
									_3e2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
								});
								document.cvalidationForm.cardnum.focus();
								return false;
							} else {
								if (trim(document.cvalidationForm.cardvernum.value).length == 0) {
									i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valcardverno", null,
									function(mesg) {
										_3e2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
									});
									document.cvalidationForm.cardvernum.focus();
									return false;
								} else {
									if (_3e7.indexOf(" ") != -1) {
										i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.nospacecardverno", null,
										function(mesg) {
											_3e2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
										});
										document.cvalidationForm.cardvernum.focus();
										return false;
									} else {
										if (!document.cvalidationForm.agree.checked) {
											_3e2.innerHTML = "<span class=\"error\">Please read and accept the Terms of Service and Privacy Policy</span>";
											return false;
										} else {
											i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.transactioninprc", null,
											function(mesg) {
												_3e8.innerHTML = "<span class=\"pop tabLoading\"><b>" + mesg + "</b></span>";
											});
											ShowGenBlock("customloadingdiv");
											if (_3e1) {
												disableButton(_3e1);
											}
											return true;
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
function callCalendar(_3f5, but) {
	Calendar.setup({
		animation: false,
		fdow: parseInt(Utils.weekFirstDay),
		inputField: _3f5,
		trigger: but,
		dateFormat: Utils.ifformat,
		onSelect: function(cal) {
			iscalvisible = true;
			cal.hide();
		}
	});
}
function callCalendars(_3f8, but, _3fa, but1) {
	var mcal = Calendar.setup({
		animation: false,
		fdow: parseInt(Utils.weekFirstDay),
		inputField: _3f8,
		trigger: but,
		dateFormat: Utils.ifformat,
		onSelect: function(cal) {
			iscalvisible = true;
			cal.hide();
		}
	});
	mcal.manageFields(but, _3f8, Utils.ifformat);
	mcal.manageFields(but1, _3fa, Utils.ifformat);
}
function callDetCalendars(_3fe, but, _400, but1, _402) {
	var _403 = false;
	var dFmt = Utils.ifformat;
	if (Utils.taskinhr == "true" || (!(isDIHrs(document.getElementById(_3fe).value)) || !(isDIHrs(document.getElementById(_400).value)))) {
		_403 = "12";
		dFmt = Utils.ifformat + " %I:%M %p";
		if ((Utils.timeformat).indexOf("HH") != -1) {
			_403 = true;
			dFmt = Utils.ifformat + " %H:%M";
		}
	}
	var dcal = Calendar.setup({
		animation: false,
		fdow: parseInt(Utils.weekFirstDay),
		showTime: _403,
		onSelect: function(cal) {
			iscalvisible = true;
			cal.hide();
			ShowGenInline(_402);
		},
		onTimeChange: function(cal) {
			cal.date.setHours(parseInt(cal.time / 100));
			cal.date.setMinutes(cal.time % 100);
			cal.inputField.value = Calendar.printDate(cal.date, cal.dateFormat);
		}
	});
	dcal.manageFields(but, _3fe, dFmt);
	dcal.manageFields(but1, _400, dFmt);
}
function callCal(_408, but, url) {
	var mcal = Calendar.setup({
		animation: false,
		fdow: parseInt(Utils.weekFirstDay),
		inputField: _408,
		trigger: but,
		dateFormat: Utils.ifformat,
		onSelect: function(cal) {
			iscalvisible = true;
			cal.hide();
			if (url != null && url.indexOf("manageresource.do") != -1) {
				ShowGenInline("ajax_load_tab");
				var _40d = document.getElementById("sdateval").value;
				ajaxShowPage(url + "&weekstart=" + _40d, "projectcontent");
			}
		}
	});
	mcal.manageFields(but, _408, Utils.ifformat);
}
function callTodoCalendar(_40e, but) {
	Calendar.setup({
		inputField: _40e,
		ifFormat: Utils.ifformat,
		button: but,
		firstDay: parseInt(Utils.weekFirstDay),
		align: "Tl",
		showsTime: Utils.taskinhr
	});
}
function callDateCalendar(_410, but) {
	Calendar.setup({
		inputField: _410,
		ifFormat: Utils.ifformat,
		button: but,
		firstDay: parseInt(Utils.weekFirstDay),
		align: "Tl",
		electric: false
	});
}
function callTaskCalendar(_412, but) {
	var _414 = false;
	var dFmt = Utils.ifformat;
	if (Utils.taskinhr == "true" || !(isDIHrs(document.getElementById(_412).value))) {
		_414 = "12";
		dFmt = Utils.ifformat + " %I:%M %p";
		if ((Utils.timeformat).indexOf("HH") != -1) {
			_414 = true;
			dFmt = Utils.ifformat + " %H:%M";
		}
	}
	Calendar.setup({
		animation: false,
		fdow: parseInt(Utils.weekFirstDay),
		inputField: _412,
		trigger: but,
		dateFormat: dFmt,
		showTime: _414,
		onSelect: function(cal) {
			iscalvisible = true;
			cal.hide();
			document.getElementById(_412).focus();
		}
	});
}
function callUpdTaskCalendar(_417, but, url, csrf, _41b, _41c) {
	var _41d = false;
	var dFmt = Utils.ifformat;
	if (Utils.taskinhr == "true" || !(isDIHrs(document.getElementById(_417).value))) {
		_41d = "12";
		dFmt = Utils.ifformat + " %I:%M %p";
		if ((Utils.timeformat).indexOf("HH") != -1) {
			_41d = true;
			dFmt = Utils.ifformat + " %H:%M";
		}
	}
	Calendar.setup({
		animation: false,
		fdow: parseInt(Utils.weekFirstDay),
		inputField: _417,
		trigger: but,
		dateFormat: dFmt,
		showTime: _41d,
		selectionType: Calendar.SEL_SINGLE,
		singleClick: true,
		onSelect: function(cal) {
			iscalvisible = true;
			cal.hide();
			if (_41b != "duedate") {
				if ((_417.indexOf("edate_") != -1) && valDateCheck("sdate_" + _41c.split("_")[1], "edate_" + _41c.split("_")[1])) {
					url = url + "&" + _41b + "=" + document.getElementById(_417).value + "&" + csrf;
					ajaxShowTab(url, _41c, csrf);
				} else {
					if (_417.indexOf("sdate_") != -1) {
						url = url + "&" + _41b + "=" + document.getElementById(_417).value + "&" + csrf;
						ajaxShowTab(url, _41c, csrf);
					}
				}
			} else {
				if (!document.getElementById("sdate_" + _41c.split("_")[1]) || document.getElementById("sdate_" + _41c.split("_")[1]).value == "" || document.getElementById("sdate_" + _41c.split("_")[1]).value == "-1") {
					url = url + "&" + _41b + "=" + document.getElementById(_417).value + "&" + csrf;
					ajaxShowTab(url, _41c, csrf);
				} else {
					if ((_417.indexOf("cdate_") != -1) && valDateCheck("sdate_" + _41c.split("_")[1], "cdate_" + _41c.split("_")[1], "comp")) {
						url = url + "&" + _41b + "=" + document.getElementById(_417).value + "&" + csrf;
						ajaxShowTab(url, _41c, csrf);
					}
				}
			}
		}
	});
}
function callDetTaskCal(_420, but, sbut) {
	var _423 = false;
	var dFmt = Utils.ifformat;
	if (Utils.taskinhr == "true" || !(isDIHrs(document.getElementById(_420).value))) {
		_423 = "12";
		dFmt = Utils.ifformat + " %I:%M %p";
		if ((Utils.timeformat).indexOf("HH") != -1) {
			_423 = true;
			dFmt = Utils.ifformat + " %H:%M";
		}
	}
	Calendar.setup({
		animation: false,
		fdow: parseInt(Utils.weekFirstDay),
		inputField: _420,
		trigger: but,
		dateFormat: dFmt,
		showTime: _423,
		selectionType: Calendar.SEL_SINGLE,
		singleClick: true,
		onSelect: function(cal) {
			iscalvisible = true;
			cal.hide();
			ShowGenInline(sbut);
		}
	});
}
function callProjCalTaskCalendar(_426, but, _428) {
	var _429 = document.getElementById(_428).style.top;
	var _42a = document.getElementById(_428).style.left;
	var left = parseInt(_42a.split("px")[0]) + 102;
	var top = parseInt(_429.split("px")[0]) + 20;
	var _42d = jQuery(document).height();
	var _42e = _42d - top;
	if (_42e < 500 && _42e > 0) {
		top = top - 20;
	}
	if (_426.indexOf("taskenddate") != -1) {
		left = left + 145;
	}
	Calendar.setup({
		inputField: _426,
		ifFormat: Utils.ifformat,
		button: but,
		firstDay: parseInt(Utils.weekFirstDay),
		align: "Tl",
		position: new Array(left, top)
	});
}
function callMeetCalendar(_42f, but, _431) {
	var _432 = document.getElementById(_431).style.top;
	var _433 = document.getElementById(_431).style.left;
	var left = parseInt(_433.split("px")[0]) + 102;
	var top = parseInt(_432.split("px")[0]) - 40;
	if (document.getElementById("logproject")) {
		top = top + 50;
	}
	var _436 = jQuery(document).height();
	var _437 = _436 - top;
	if (_437 < 500 && _437 > 0) {
		top = top - 20;
	}
	Calendar.setup({
		inputField: _42f,
		ifFormat: Utils.ifformat,
		button: but,
		firstDay: parseInt(Utils.weekFirstDay),
		align: "Tl",
		position: new Array(left, top)
	});
}
function callDepCalendar(_438, but, id, _43b) {
	if (is_opera) {
		Calendar.setup({
			inputField: _438,
			ifFormat: Utils.ifformat,
			button: but,
			align: "Tl",
			onUpdate: function() {
				focusDepBox(id, _43b);
			}
		});
	} else {
		Calendar.setup({
			inputField: _438,
			ifFormat: Utils.ifformat,
			button: but,
			align: "Tl"
		});
	}
}
function focusDepBox(id, _43d) {
	if (_43d == "start") {
		ShowHideInline("depstbox_" + id, "depstdisp_" + id);
		document.getElementById("st_date_dep_" + id).focus();
	} else {
		if (_43d == "end") {
			document.getElementById("en_date_dep_" + id).focus();
		}
	}
}
function hideDepDateBox(id, _43f) {
	if (_43f == "start") {
		ShowHideInline("depstdisp_" + id, "depstbox_" + id);
	} else {
		if (_43f == "end") {}
	}
}
function destroyCalendar(_440) {}
function setBrowserTitle(_441) {
	_441 = replaceSplChrs(_441);
	document.title = decodeURIComponent(_441);
}
function replaceSplChrs(text) {
	text = replace(text, "%26%2339%3B", "'");
	text = replace(text, "+", " ");
	text = replace(text, "%26lt%3B", "<");
	text = replace(text, "%26gt%3B", ">");
	text = replace(text, "%26quot%3B", "\"");
	text = replace(text, "%26amp%3B", "&");
	return text;
}
function replace(_443, text, by) {
	var _446 = _443.length,
	_447 = text.length;
	if ((_446 == 0) || (_447 == 0)) {
		return _443;
	}
	var i = _443.indexOf(text);
	if ((!i) && (text != _443.substring(0, _447))) {
		return _443;
	}
	if (i == -1) {
		return _443;
	}
	var _449 = _443.substring(0, i) + by;
	if (i + _447 < _446) {
		_449 += replace(_443.substring(i + _447, _446), text, by);
	}
	return _449;
}
function validateLicAgreeCheck() {
	if (document.cvalidationForm.agree.checked) {
		document.cvalidationForm.create.disabled = false;
	} else {
		document.cvalidationForm.create.disabled = true;
	}
}
function validateAddPortalCheck(stat) {
	if (stat == "disable") {
		document.addPortal.createportal.disabled = true;
	} else {
		if (stat == "enable") {
			document.addPortal.createportal.disabled = false;
		}
	}
}
function setTaskValues(_44b, _44c, dlen) {
	eval("document.updateTodoTask_" + _44b + ".todotask").value = replace(replace(_44c, "'", "'"), "\"", "\\\"");
	eval("document.updateTodoTask_" + _44b + ".duration").value = dlen;
}
function genPreview(e, div1, div2) {
	ShowGenBlock(div2);
	var _451;
	var _452;
	if (window.event) {
		_451 = e.keyCode;
	} else {
		if (e.which) {
			_451 = e.which;
		}
	}
	var _453 = document.getElementById(div1);
	var _454 = document.getElementById(div2);
	_452 = String.fromCharCode(_451);
	var _455 = _453.innerHTML;
	var len = _455.length;
	_454.innerHTML = replace(_453.value, "\n", "<br>");
}
function show(id) {
	switch (id) {
	case "InactiveProjects":
		var _458 = document.getElementById("MyDocsList");
		var _459 = document.getElementById("ProjectTemplates");
		if (_458 != null) {
			_458.style.display = "none";
		}
		if (_459 != null) {
			_459.style.display = "none";
		}
		break;
	case "ProjectTemplates":
		var _458 = document.getElementById("MyDocsList");
		var _459 = document.getElementById("InactiveProjects");
		if (_458 != null) {
			_458.style.display = "none";
		}
		if (_459 != null) {
			_459.style.display = "none";
		}
		break;
	}
	var _45a = document.getElementById(id);
	if (_45a != null) {
		_45a.style.display = "block";
	}
}
function showBefore(_45b, aid) {
	var i = eval("document." + _45b + ".schremindbefore.selectedIndex");
	var _45e = eval("document." + _45b + ".schremindbefore.options[i].value");
	var _45f;
	if (aid != null) {
		_45f = "before_" + aid;
	} else {
		_45f = "before";
	}
	if (_45e != "on time") {
		ShowGenInline(_45f);
	} else {
		Hide(_45f);
	}
}
function selProjMenuTab(_460) {
	var _461 = document.getElementById("MyHome");
	var _462 = document.getElementById("Timesheet");
	var _463 = document.getElementById(_460);
	if (_461 != null) {
		_461.className = "collapsebg";
	}
	if (_462 != null) {
		_462.className = "collapsebg";
	}
	if (_463 != null) {
		_463.className = "collapsebgsel";
	}
}
function enDisEAft(sel, id) {
	if (sel != "only once") {
		ShowGenInline("notimesdiv");
	} else {
		Hide("notimesdiv");
	}
}
function validateProfile() {
	var _466 = document.getElementById("prof_upd_status");
	if (trim(document.profileForm.fullname.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.userprofile.plsspyfullname", null,
		function(mesg) {
			_466.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.profileForm.fullname.focus();
		return false;
	} else {
		if (findScriptTags(trim(document.profileForm.fullname.value))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.userprofile.spyvalidfullname", null,
			function(mesg) {
				_466.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.profileForm.fullname.focus();
			return false;
		} else {
			if (findScriptTags(trim(document.profileForm.jtitle.value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyjobtitle", null,
				function(mesg) {
					_466.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.profileForm.jtitle.focus();
				return false;
			} else {
				if (findScriptTags(trim(document.profileForm.uofficenumber.value))) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valofficeno", null,
					function(mesg) {
						_466.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
					document.profileForm.uofficenumber.focus();
					return false;
				} else {
					if (findScriptTags(trim(document.profileForm.homenumber.value))) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valresidenceno", null,
						function(mesg) {
							_466.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						document.profileForm.homenumber.focus();
						return false;
					} else {
						if (findScriptTags(trim(document.profileForm.mobilenumber.value))) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valmobileno", null,
							function(mesg) {
								_466.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							document.profileForm.mobilenumber.focus();
							return false;
						} else {
							ShowGenInline("zoho_upd_profile_busy");
							disableButton("zoho_upd_profile_submit");
							return true;
						}
					}
				}
			}
		}
	}
}
function sendAction(url) {
	document.exportForm.exporttaskSubmit.disabled = true;
	document.exportForm.action = url;
	document.exportForm.submit();
	document.exportForm.exporttaskSubmit.disabled = false;
}
function sendTsAction(url) {
	var _46f = "exporttsForm";
	var _470 = document.getElementById("timesheet_export_status");
	_470.innerHTML = "";
	var _471 = eval("document." + _46f + ".etsstartdate.value");
	var _472 = eval("document." + _46f + ".etsenddate.value");
	Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
	var _473 = compareDates(_471, Utils.dateformat, _472, Utils.dateformat);
	if (trim(document.exporttsForm.etsstartdate.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.stdateemp", null,
		function(mesg) {
			_470.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return (false);
	} else {
		if (trim(document.exporttsForm.etsenddate.value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.enddateemp", null,
			function(mesg) {
				_470.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return (false);
		} else {
			if (_471 != "" && _472 != "" && _473 != 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
				function(mesg) {
					_470.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _46f + ".etsenddate.value=\"\"");
				return false;
			} else {
				ShowGenInline("zoho_export_timesheet_busy");
				document.exporttsForm.exporttsSubmit.disabled = true;
				document.exporttsForm.action = url;
				document.exporttsForm.submit();
				document.exporttsForm.exporttsSubmit.disabled = false;
				Hide("zoho_export_timesheet_busy");
			}
		}
	}
}
function changeYearOpt() {
	var _477 = document.exporttsForm.month.value;
	var year = document.getElementById("year");
	if (_477 != "all") {
		year.options[15] = null;
	} else {
		year.options[15] = new Option("All", "all");
	}
}
function validateTempTodoListForm(stat) {
	if (stat == "add") {
		var _47a = document.getElementById("tlist_temp_add_status");
		if (trim(document.addTempTodoList.temptodotitle.value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.tasklisttitle", null,
			function(mesg) {
				_47a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.addTempTodoList.temptodotitle.focus();
			return (false);
		} else {
			if (findScriptTags(trim(document.addTempTodoList.temptodotitle.value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valtasklisttitle", null,
				function(mesg) {
					_47a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.addTempTodoList.temptodotitle.focus();
				return (false);
			} else {
				return true;
			}
		}
	} else {
		var _47d = "editTempTodoList_" + stat;
		var _47e = "zoho_update_templist_busy_" + stat;
		var alId = "templist_upd_status_" + stat;
		var _47a = document.getElementById(alId);
		if (trim(eval("document." + _47d + ".temptodotitle.value")).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.templisttitle", null,
			function(mesg) {
				_47a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			eval("document." + _47d + ".temptodotitle.focus()");
			return (false);
		} else {
			if (findScriptTags(trim(eval("document." + _47d + ".temptodotitle.value")))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valtemplisttitle", null,
				function(mesg) {
					_47a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _47d + ".temptodotitle.focus()");
				return (false);
			} else {
				ShowGenInline(_47e);
				return true;
			}
		}
	}
}
function validateTempTaskForm(_482, _483, alId) {
	var _485 = document.getElementById(alId);
	if (trim(eval("document." + _482 + ".todotask.value")).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.temptask", null,
		function(mesg) {
			_485.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		eval("document." + _482 + ".todotask.focus()");
		return (false);
	} else {
		if (findScriptTags(trim(eval("document." + _482 + ".todotask.value")))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valtemptask", null,
			function(mesg) {
				_485.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			eval("document." + _482 + ".todotask.focus()");
			return (false);
		} else {
			ShowGenInline(_483);
			return true;
		}
	}
}
function validateCopyTaskForm(_488, _489, alId) {
	var _48b = document.getElementById(alId);
	if (trim(eval("document." + _488 + ".nooftimes.value")).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.nooftimes", null,
		function(mesg) {
			_48b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		eval("document." + _488 + ".nooftimes.focus()");
		return (false);
	} else {
		if (!isNumeric(eval("document." + _488 + ".nooftimes.value"))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valmustbe", null,
			function(mesg) {
				_48b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			eval("document." + _488 + ".nooftimes.focus()");
			return false;
		} else {
			if ((eval("document." + _488 + ".nooftimes.value") > 5)) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.lessthansix", null,
				function(mesg) {
					_48b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _488 + ".nooftimes.focus()");
				return false;
			} else {
				ShowGenInline(_489);
				return true;
			}
		}
	}
}
function ShowTempTaskHideInline(_48f, Id) {
	var id = document.getElementById(_48f);
	id1.className = "inline";
	id1 = document.getElementById(Id);
	id1.className = "hide";
}
function getTemplate(stat) {
	if (stat == "template") {
		ShowHideInline("task_separator", "templates_separator");
	} else {
		ShowHideInline("templates_separator", "task_separator");
	}
}
function getMileListTemplate(mid, stat) {
	frmName = "addTodoList_" + mid;
	document.getElementById("listtype").value = stat;
	if (stat == "template") {
		ShowHideInline("mile_task_separator", "mile_templates_separator");
	} else {
		ShowHideInline("mile_templates_separator", "mile_task_separator");
	}
}
function validateSendMsg(stat) {
	var _496 = eval(document.sendMessage.toaddr);
	var _497 = new Array();
	var _498 = 0;
	if (_496.length) {
		for (var j = 0; j < _496.length; j++) {
			if (_496[j].checked) {
				_497[_498] = _496[j];
				_498++;
			}
		}
	} else {
		if (_496.checked) {
			Hide("zoho_sendmsg_busy");
			_497[0] = _496.value;
			_498++;
		}
	}
	var _49a = document.getElementById("send_mail_status");
	if (stat == "add") {
		if (trim(document.sendMessage.subject.value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifysub", null,
			function(mesg) {
				_49a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.sendMessage.subject.focus();
			scroll(0, 0);
			Hide("zoho_sendmsg_busy");
			return (false);
		} else {
			if (findScriptTags(trim(document.sendMessage.subject.value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvalsub", null,
				function(mesg) {
					_49a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.sendMessage.subject.focus();
				scroll(0, 0);
				Hide("zoho_sendmsg_busy");
				return (false);
			}
		}
		if (trim(document.sendMessage.message.value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifymessage", null,
			function(mesg) {
				_49a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.sendMessage.message.focus();
			scroll(0, 0);
			Hide("zoho_sendmsg_busy");
			return (false);
		} else {
			if (findScriptTags(trim(document.sendMessage.message.value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvalmsg", null,
				function(mesg) {
					_49a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.sendMessage.message.focus();
				scroll(0, 0);
				Hide("zoho_sendmsg_busy");
				return (false);
			}
		}
		if (_498 <= 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.recipientid", null,
			function(mesg) {
				_49a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			scroll(0, 0);
			Hide("zoho_sendmsg_busy");
			return (false);
		}
		return true;
	} else {
		return true;
	}
}
getPos = function(el) {
	var r = {
		offsetLeft: el.offsetLeft,
		offsetTop: el.offsetTop
	};
	if (el.offsetParent) {
		var tmp = getPos(el.offsetParent);
		r.offsetLeft += tmp.offsetLeft;
		r.offsetTop += tmp.offsetTop;
		r.offsetRight += tmp.offsetRight;
	}
	return r;
};
function showHelp(_4a3, _4a4) {
	var ev = (is_ie) ? window.event: _4a3;
	var _4a6 = (is_ie) ? ev.srcElement: ev.target;
	var r = getPos(_4a6);
	var x = r.offsetLeft + 8;
	var y = r.offsetTop + _4a6.offsetHeight - 8;
	id = document.getElementById(_4a4);
	id.style.left = x + "px";
	id.style.top = y + "px";
	id.className = "inline";
}
function showRecMeetDetails(_4aa, _4ab) {
	var ev = (is_ie) ? window.event: _4aa;
	var _4ad = (is_ie) ? ev.srcElement: ev.target;
	var r = getPos(_4ad);
	var x = r.offsetLeft + 8;
	var y = r.offsetTop + _4ad.offsetHeight + 2;
	id = document.getElementById(_4ab);
	id.style.left = x + "px";
	id.style.top = y + "px";
	id.style.display = "block";
}
function showDepHelp(_4b1, _4b2, _4b3) {
	var ev = (is_ie) ? window.event: _4b1;
	var _4b5 = (is_ie) ? ev.srcElement: ev.target;
	var r = getPos(_4b5);
	var x = r.offsetLeft + 10 - _4b3;
	var y = r.offsetTop + _4b5.offsetHeight - 8;
	id = document.getElementById(_4b2);
	id.style.left = x + "px";
	id.style.top = y + "px";
	id.style.display = "block";
}
var x = 0;
var r = 255;
var g = 255;
var b = 255;
function findDocDim() {
	if (document.all) {
		return {
			width: document.body.offsetWidth + document.body.scrollLeft,
			height: document.body.offsetHeight + document.body.scrollTop
		};
	} else {
		return {
			width: window.innerWidth + document.body.scrollLeft,
			height: window.innerHeight + document.body.scrollTop
		};
	}
}
function displayFadeMsg(msg, _4ba) {
	var _4bb = document.getElementById("centerstatusmsg");
	if (_4bb) {
		_4bb.innerHTML = "<b>" + msg + "<blink>...</blink></b>";
		var sts = document.getElementById("centerstatus");
		if (sts) {
			sts.className = "block";
			var doc = findDocDim();
			var _4be = sts.offsetWidth;
			var _4bf = sts.offsetHeight;
			var left = (doc.width / 2) + document.body.scrollLeft - 50;
			var top = (doc.height / 2) + (document.body.scrollTop / 2) - (_4bf / 2);
			sts.style.left = parseInt(left) + "px";
			sts.style.top = parseInt(top) + "px";
			sts.style.padding = "4px 8px 4px 8px";
			if (!_4ba) {
				setTimeout(function() {
					Hide("centerstatus");
				},
				1500);
			}
		}
	}
}
function setProjDeactiveAnnouncementCookie() {
	var _4c2 = new Date();
	var _4c3;
	_4c2.setTime(_4c2.getTime() + (24 * 60 * 60 * 1000 * 1));
	document.cookie = "pdstatus=1;expires= " + ((_4c2).toGMTString());
}
function setFreeProjAnnouncementCookie() {
	var _4c4 = new Date();
	var _4c5;
	_4c4.setTime(_4c4.getTime() + (24 * 60 * 60 * 1000 * 1));
	document.cookie = "fpstatus=1;expires= " + ((_4c4).toGMTString());
}
function setAnnouncementCookie() {
	var _4c6 = new Date();
	var _4c7;
	_4c6.setTime(_4c6.getTime() + (24 * 60 * 60 * 1000 * 1));
	document.cookie = "msgstatus=1;expires= " + ((_4c6).toGMTString());
}
function setUpdateAnnouncementCookie() {
	var _4c8 = new Date();
	var _4c9;
	_4c8.setTime(_4c8.getTime() + (24 * 60 * 60 * 1000 * 1));
	document.cookie = "updstatus=1;expires= " + ((_4c8).toGMTString());
}
function setInfoAnnouncementCookie(_4ca) {
	var _4cb = new Date();
	var _4cc;
	_4cb.setTime(_4cb.getTime() + (24 * 60 * 60 * 1000 * 1));
	document.cookie = _4ca + "=1;expires= " + ((_4cb).toGMTString());
}
function setAffiliateCookie(_4cd) {
	var _4ce = new Date();
	var _4cf;
	_4ce.setTime(_4ce.getTime() + (24 * 60 * 60 * 1000 * 1));
	document.cookie = _4cd + "=1;path=/;domain=.zoho.com;expires= " + ((_4ce).toGMTString());
}
function showMPMenu() {
	if (is_ie && !is_opera) {
		iframeIEHack = document.createElement("IFRAME");
		iframeIEHack.id = "iframeIEHack";
		iframeIEHack.scrolling = "no";
		iframeIEHack.frameBorder = 0;
		iframeIEHack.style.position = "absolute";
		iframeIEHack.style.zIndex = "90";
		iframeIEHack.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)";
	}
	document.getElementById("mpMenu").style.display = "block";
	document.getElementById("mpMenu").style.zIndex = "98";
	var top = document.getElementById("mpMenu").style.top = findPosY(document.getElementById("myportalid")) + 20;
	var left = document.getElementById("mpMenu").style.left = findPosX(document.getElementById("myportalid")) + document.getElementById("myportalid").offsetWidth - document.getElementById("mpMenu").offsetWidth + 8;
	Dig = document.getElementById("mpMenu");
	if (is_ie && !is_opera) {
		iframeIEHack.style.width = Dig.offsetWidth + "px";
		iframeIEHack.style.height = Dig.offsetHeight + "px";
		iframeIEHack.style.top = top + "px";
		iframeIEHack.style.left = left + "px";
		document.body.appendChild(iframeIEHack);
	}
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
function showQCMenu() {
	if (is_ie && !is_opera) {
		iframeIEHack = document.createElement("IFRAME");
		iframeIEHack.id = "iframeIEHack";
		iframeIEHack.scrolling = "no";
		iframeIEHack.frameBorder = 0;
		iframeIEHack.style.position = "absolute";
		iframeIEHack.style.zIndex = "90";
		iframeIEHack.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)";
	}
	document.getElementById("qcMenu").style.display = "block";
	document.getElementById("qcMenu").style.zIndex = "98";
	var top = document.getElementById("qcMenu").style.top = findPosY(document.getElementById("qcreate_center")) + 25;
	var left = document.getElementById("qcMenu").style.left = findPosX(document.getElementById("qcreate_center")) + document.getElementById("qcreate_center").offsetWidth - document.getElementById("qcMenu").offsetWidth + 8;
	Dig = document.getElementById("qcMenu");
	if (is_ie && !is_opera) {
		iframeIEHack.style.width = Dig.offsetWidth + "px";
		iframeIEHack.style.height = Dig.offsetHeight + "px";
		iframeIEHack.style.top = top + "px";
		iframeIEHack.style.left = left + "px";
		document.body.appendChild(iframeIEHack);
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
		if (eobj.id != "addtasklog" && iscalvisible == false) {
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
		if (eobj.id != "addtodoid" && iscalvisible == false) {
			jQuery("#addtodoid").hide();
		}
	}
	if (document.getElementById("updtodoid") != null) {
		if (eobj.id != "updtodoid" && iscalvisible == false) {
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
		if (eobj.id != "addtodoid" && iscalvisible == false) {
			jQuery("#addtodoid").hide();
		}
	}
	if (document.getElementById("updtodoid") != null) {
		if (eobj.id != "updtodoid" && iscalvisible == false) {
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
		if (eobj.id != "todoexpid" && iscalvisible == false) {
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
				if (deptextboxid == "enddatetb" && iscalvisible == false) {
					var _4d9 = true;
					if (is_ie) {
						_4d9 = checkDepDiv(eobj, _4d9);
					}
					if (_4d9 == true) {
						depFormHide("dependisp_", "dependdatediv", "enddate_", "enddateid_", "pt3 pL3 pointer taskdeptxtSmall dpczpbdrbr", "pt3 pL3 pointer taskdeptxtSmall dark");
					}
				} else {
					if (deptextboxid == "startdatetext" && iscalvisible == false) {
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
	if (document.getElementById("export") != null && iscalvisible == false) {
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
		if (eobj.id != "addcaltask" && iscalvisible == false) {
			showHideTaskPropertyList("addcaltask", false);
		}
	}
	if (document.getElementById("addcalmilestone") != null) {
		if (eobj.id != "addcalmilestone" && iscalvisible == false) {
			showHideTaskPropertyList("addcalmilestone", false);
		}
	}
	if (document.getElementById("resourceTaskDiv") != null) {
		if (eobj.id != "resourceTaskDiv" && iscalvisible == false) {
			showHideTaskPropertyList("resourceTaskDiv", false);
		}
	}
	if (document.getElementById("addcalmeeting") != null) {
		if (eobj.id != "addcalmeeting" && iscalvisible == false) {
			showHideTaskPropertyList("addcalmeeting", false);
		}
	}
	if (document.getElementById("updatemeeting") != null) {
		if (eobj.id != "updatemeeting" && iscalvisible == false) {
			showHideTaskPropertyList("updatemeeting", false);
		}
	}
	if (document.getElementById("updatemile") != null) {
		if (eobj.id != "updatemile" && iscalvisible == false) {
			showHideTaskPropertyList("updatemile", false);
		}
	}
	if (document.getElementById("addtop_mile") != null) {
		if (eobj.id != "addtop_mile" && iscalvisible == false) {
			showHideTaskPropertyList("addtop_mile", false);
		}
	}
	if (document.getElementById("addquickmeeting") != null) {
		if (eobj.id != "addquickmeeting" && iscalvisible == false) {
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
		if (eobj.id != "bugform" && iscalvisible == false) {
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
function findPosX(xobj) {
	var _4e1 = 0;
	if (document.getElementById || document.all) {
		while (xobj.offsetParent) {
			_4e1 += xobj.offsetLeft;
			xobj = xobj.offsetParent;
		}
	} else {
		if (document.layers) {
			_4e1 += xobj.x;
		}
	}
	return _4e1;
}
function findPosY(yobj) {
	var _4e3 = 0;
	if (document.getElementById || document.all) {
		while (yobj.offsetParent) {
			_4e3 += yobj.offsetTop;
			yobj = yobj.offsetParent;
		}
	} else {
		if (document.layers) {
			_4e3 += yobj.y;
		}
	}
	return _4e3;
}
function findScriptTags(val) {
	var _4e5 = false;
	var _4e6 = false;
	if (val.indexOf("<script") >= 0 || val.indexOf("<SCRIPT") >= 0) {
		var div = document.createElement("div");
		div.innerHTML = val;
		var eles = div.getElementsByTagName("script");
		if (is_ie) {
			_4e6 = eles.length >= 0;
		} else {
			_4e6 = eles.length > 0;
		}
		if (_4e6) {
			_4e5 = true;
		} else {
			_4e5 = false;
		}
		if (!_4e5) {
			var eles = div.getElementsByTagName("SCRIPT");
			if (_4e6) {
				_4e5 = true;
			} else {
				_4e5 = false;
			}
		}
	}
	return _4e5;
}
Utils.prevTomiTab = "tabucomelist";
function changeTomiTabStyle(elem, _4ea, _4eb) {
	var tlid = document.getElementById(elem);
	var mlid = document.getElementById("tomitab");
	tlid.className = _4ea;
	mlid.className = _4eb;
	var _4ee = Utils.prevTomiTab;
	if (elem != _4ee && Utils.idPrevTomiTab) {
		var _4ef = document.getElementById(_4ee);
		if (_4ef) {
			_4ef.className = "dvITabUnSelected";
		}
		Utils.prevTomiTab = elem;
	}
}
function SwapOneStyle(_4f0, stat) {
	var id1 = document.getElementById(_4f0);
	if (stat == "show") {
		id1.className = "";
	} else {
		if (stat == "hide") {
			id1.className = "hide";
		}
	}
}
function validateExportTsLogForm() {
	var _4f3 = "exportTimeLog";
	var _4f4 = document.getElementById("tslog_export_status");
	var _4f5 = eval("document." + _4f3 + ".tsstartdate.value");
	var _4f6 = eval("document." + _4f3 + ".tsenddate.value");
	Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
	var _4f7 = compareDates(_4f5, Utils.dateformat, _4f6, Utils.dateformat);
	if (trim(document.exportTimeLog.tsstartdate.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.stdateemp", null,
		function(mesg) {
			_4f4.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return (false);
	} else {
		if (trim(document.exportTimeLog.tsenddate.value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.enddateemp", null,
			function(mesg) {
				_4f4.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return (false);
		} else {
			if (_4f5 != "" && _4f6 != "" && _4f7 != 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
				function(mesg) {
					_4f4.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _4f3 + ".tsenddate.value=\"\"");
				return false;
			} else {
				return true;
			}
		}
	}
}
function sendTimeLogAction(url) {
	if (validateExportTsLogForm()) {
		EmptyDivContent("tslog_export_status");
		document.exportTimeLog.exporttlogSubmit.disabled = true;
		document.exportTimeLog.action = url;
		document.exportTimeLog.submit();
		document.exportTimeLog.exporttlogSubmit.disabled = false;
	} else {
		return false;
	}
}
function validateNewSheetForm(msg) {
	var _4fd = document.getElementById("newsheet_create_status");
	var _4fe = "!@#$%^&*()+=-[]\\';,./{}|\":<>?";
	var _4ff = trim(document.newSheetOpenForm.filename.value);
	if (_4ff.length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.spysheetname", null,
		function(mesg) {
			_4fd.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.newSheetOpenForm.filename.focus();
		return false;
	}
	for (var i = 0; i < _4ff.length; i++) {
		if (_4fe.indexOf(_4ff.charAt(i)) != -1) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.sheetnamevalidation", null,
			function(mesg) {
				_4fd.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.newSheetOpenForm.filename.focus();
			return false;
		}
	}
	return true;
}
function validateNewPptForm(msg) {
	var _504 = document.getElementById("newppt_create_status");
	var _505 = "!@#$%^&*()+=-[]\\';,./{}|\":<>?";
	var _506 = trim(document.newPptOpenForm.filename.value);
	if (_506.length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.spypptname", null,
		function(mesg) {
			_504.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.newPptOpenForm.filename.focus();
		return false;
	}
	for (var i = 0; i < _506.length; i++) {
		if (_505.indexOf(_506.charAt(i)) != -1) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.pptnamevalidation", null,
			function(mesg) {
				_504.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.newPptOpenForm.filename.focus();
			return false;
		}
	}
	return true;
}
function validateNewDocForm(msg) {
	var _50b = document.getElementById("newdoc_create_status");
	var _50c = "!@#$%^&*()+=-[]\\';,./{}|\":<>?";
	var _50d = trim(document.newDocOpenForm.filename.value);
	if (_50d.length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.spydocname", null,
		function(mesg) {
			_50b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.newDocOpenForm.filename.focus();
		return false;
	}
	for (var i = 0; i < _50d.length; i++) {
		if (_50c.indexOf(_50d.charAt(i)) != -1) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.docnamevalidation", null,
			function(mesg) {
				_50b.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.newDocOpenForm.filename.focus();
			return false;
		}
	}
	return true;
}
function saveMiscListOrder(url, dId, sId, sId1, sId2, sId4, _517, _518) {
	saveListOrder(sId, sId1, sId2, sId4, _517, _518);
	ajaxShowPage(url, dId);
	ShowHideInline("mact_div", "msaveorder_div");
	return true;
}
function saveGenListOrder(url1, id, _51b, _51c, _51d, mid, _51f, _520) {
	ShowHideInline("reorder_span_" + mid, "saveorder_span_" + mid);
	var url = Utils.contPath + "/savetlistorder.do?mid=" + mid + "&sort_order=" + Sortable.serialize(_51b) + "&" + _51f + "=" + encodeURIComponent(_520);
	ajaxGenSaveOrder(url, url1, id);
	var hide = document.getElementsByTagName("div");
	for (var i = 0; i < hide.length; i++) {
		if (hide[i].id == "hideListItems_" + mid) {
			hide[i].style.display = "block";
		}
	}
	document.getElementById(_51b).className = "ulstyle";
	Sortable.destroy(_51b);
}
function showHideLogDetails(_524) {
	var _525 = document.getElementById("showHide_" + _524).value;
	if (_525 == "show") {
		ShowGenInline("displogdet_" + _524);
		document.getElementById("showHide_" + _524).value = "hide";
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.hidetaskdet", null,
		function(mesg) {
			document.getElementById("showhidedisp_" + _524).title = mesg;
		});
	} else {
		Hide("displogdet_" + _524);
		document.getElementById("showHide_" + _524).value = "show";
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.showtaskdet", null,
		function(mesg) {
			document.getElementById("showhidedisp_" + _524).title = mesg;
		});
	}
}
function disableEndDate(_528, Id, Id1) {
	eval("document." + _528 + ".taskenddate.value = \"\" ");
	eval("document." + _528 + ".duration.disabled = false");
	ShowHideInline(Id1, Id);
}
function disableDuration(_52b, Id, Id1) {
	eval("document." + _52b + ".duration.value = \"\" ");
	eval("document." + _52b + ".duration.disabled = true");
	ShowHideInline(Id, Id1);
}
function sendGlobalExportAction(url) {
	EmptyDivContent("global_export_status");
	document.exportWorkCal.exporttlogSubmit.disabled = true;
	document.exportWorkCal.action = url;
	document.exportWorkCal.submit();
	document.exportWorkCal.exporttlogSubmit.disabled = false;
}
function asgValTS(_52f) {
	document.FilterTSheetForm.billStsVal.value = _52f;
}
Utils.switchMenu = function(_530, len) {
	Utils._addEvent(document, "mousedown", Utils.documentClick);
	var _532 = document.getElementById("switchimgdiv");
	_532.innerHTML = "";
	var ev = (is_ie) ? window.event: _530;
	var _534 = (is_ie) ? ev.srcElement: ev.target;
	var r = getPos(_534);
	var x = r.offsetLeft;
	var y = r.offsetTop + _534.offsetHeight;
	var _538 = Utils.createElement("table", "border=0 width=150px");
	_538.style.backgroundColor = "#F7F7F7";
	_538.cellPadding = 0;
	_538.cellSpacing = 0;
	_538.setAttribute("width", "150px");
	var _539 = Utils.createElement("tbody");
	var text = new Array("Zoho Writer", "Zoho Sheet", "Zoho Show", "Zoho Wiki", "Zoho Notebook", "Zoho Creator", "Zoho Chat", "Zoho Meeting");
	var _53b = new Array("Zoho Writer", "Zoho Sheet", "Zoho Show", "Zoho Wiki", "Zoho Notebook", "Zoho Creator", "Zoho Chat", "Zoho Meeting");
	var img = "images/lightblue/spacer.gif";
	var _53d = "images/newwindow.gif";
	for (var i = 0; i < len; i++) {
		var tr = Utils.createElement("tr");
		var _540 = "";
		imgNodel = Utils.createElement("img", "src=" + img + " width=5 height=2");
		var _541 = Utils.createElement("td", null, imgNodel);
		tr.appendChild(_541);
		var _542 = document.createTextNode(text[i]);
		var temp = Utils.createElement("a", "href=javascript:; class=toplinks", _542);
		var _544 = Utils.createElement("td", null, temp);
		_544.height = "20px";
		var _545 = Utils.createElement("img", "src=" + _53d);
		_545.align = "absmiddle";
		_544.appendChild(_545);
		tr.appendChild(_544);
		var _546 = Utils.createElement("td");
		_546.style.width = "40px";
		tr.appendChild(_546);
		var _547 = Utils.createElement("td");
		tr.appendChild(_547);
		tr.onmouseover = function(_548) {
			this.className = "mdsort";
		};
		tr.onmouseout = function(_549) {
			this.className = "";
		};
		var tmp = _53b[i];
		switchOnClick(_544, text[i]);
		_539.appendChild(tr);
	}
	_538.appendChild(_539);
	_532.className = "tagmenu";
	_532.style.right = "";
	_532.style.left = x + "px";
	_532.style.top = y + 4 + "px";
	_532.style.position = "absolute";
	_532.style.display = "block";
	_532.style.padding = "0px ";
	_532.style.border = "solid";
	_532.style.borderColor = "#A2D2E8";
	_532.appendChild(_538);
};
Utils._addEvent = function(el, _54c, func) {
	if (is_ie) {
		el.attachEvent("on" + _54c, func);
	} else {
		el.addEventListener(_54c, func, true);
	}
};
Utils._removeEvent = function(el, _54f, func) {
	if (is_ie) {
		el.detachEvent("on" + _54f, func);
	} else {
		el.removeEventListener(_54f, func, true);
	}
};
Utils._stopEvent = function(ev) {
	if (is_ie) {
		ev.cancelBubble = true;
		ev.returnValue = false;
	} else {
		ev.preventDefault();
		ev.stopPropagation();
	}
};
Utils.documentClick1 = function(ev) {
	ev || (ev = window.event);
	var el = is_ie ? ev.srcElement: ev.target;
	var div = document.getElementById("page_sharing");
	for (; el != null && el != div; el = el.parentNode) {}
	if (el == null) {
		if (div) {
			div.style.display = "none";
		}
		Utils._removeEvent(document, "mousedown", Utils.documentClick1);
	}
};
Utils.documentClick = function(ev) {
	ev || (ev = window.event);
	var el = is_ie ? ev.srcElement: ev.target;
	var div = document.getElementById("firstdiv");
	for (; el != null && el != div; el = el.parentNode) {}
	if (el == null) {
		if (div) {
			div.style.display = "none";
		}
		Utils._removeEvent(document, "mousedown", Utils.documentClick);
	}
};
Utils.createElement = function(tag, _559, _55a) {
	var el = document.createElement(tag);
	if (_559) {
		var _55c = _559.split(" ");
		for (i = 0; i < _55c.length; i++) {
			var _55d = _55c[i];
			var _55e = _55d.split("=");
			var name = _55e[0];
			var _560 = _55e[1];
			if (name && _560) {
				el.setAttribute(name, _560);
				if (name == "class") {
					el.className = _560;
				}
			} else {
				if (name) {
					el.setAttribute(name, "");
				}
			}
		}
	}
	if (_55a) {
		var _561 = null;
		if (typeof _55a == "string") {
			_561 = document.createTextNode(_55a);
		} else {
			_561 = _55a;
		}
		el.appendChild(_561);
	}
	return el;
};
function switchOnClick(tr, _563) {
	tr.onclick = function() {
		_563 = _563.substring(_563.indexOf(" ") + 1, _563.length);
		var val = window.location.protocol + "://" + _563.toLowerCase() + ".zoho.com";
		window.open(val);
	};
}
function validatePortalUpdateForm() {
	var _565 = document.getElementById("portal_update_status");
	var purl = document.changePortalForm.toportal.value;
	var _567 = /^[a-z0-9]{0,80}$/;
	if (trim(purl).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyporturl", null,
		function(mesg) {
			_565.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.changePortalForm.toportal.focus();
		return false;
	} else {
		if (!_567.test(purl)) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.domainvalidation", null,
			function(mesg) {
				_565.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.changePortalForm.toportal.focus();
			return false;
		} else {
			ShowGenInline("zoho_upd_portal_busy");
			disableButton("zoho_upd_portal_submit");
			return true;
		}
	}
}
function validatePortalForm() {
	var _56a = document.getElementById("portal_add_status");
	var purl = document.getElementById("newportal").value;
	var _56c = /^[a-z0-9]{0,100}$/;
	if (trim(document.getElementById("newportal").value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyporturl", null,
		function(mesg) {
			_56a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		document.getElementById("newportal").focus();
		return false;
	} else {
		if (!_56c.test(purl)) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.domainvalidation", null,
			function(mesg) {
				_56a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return false;
		}
	}
	validateAddPortalCheck("disable");
	return true;
}
function showPortalWindow() {
	if (is_ie && !is_opera) {
		iframeIEHack = document.createElement("IFRAME");
		iframeIEHack.id = "iframeIEHack";
		iframeIEHack.scrolling = "no";
		iframeIEHack.frameBorder = 0;
		iframeIEHack.style.position = "absolute";
		iframeIEHack.style.zIndex = "90";
		iframeIEHack.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)";
	}
	document.getElementById("addownportaldiv").style.display = "block";
	document.getElementById("addownportaldiv").style.zIndex = "98";
	var top = document.getElementById("addownportaldiv").style.top = 20;
	var _570 = document.getElementById("addownportaldiv").style.right = 20;
	Dig = document.getElementById("addownportaldiv");
	if (is_ie && !is_opera) {
		iframeIEHack.style.width = Dig.offsetWidth + "px";
		iframeIEHack.style.height = Dig.offsetHeight + "px";
		iframeIEHack.style.top = top + "px";
		iframeIEHack.style.right = _570 + "px";
		document.body.appendChild(iframeIEHack);
	}
}
function validatePeopleUser(_571) {
	if (document.userDisplayForm.userlist.value == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.selectanyuser", null,
		function(mesg) {
			alert(mesg);
		});
		alert("select any value");
		return false;
	} else {
		return true;
	}
}
function validateWorkLogForm(stat) {
	var _574 = /^[0]{0,1}[0-9]\.[0-5][0-9]$/i;
	var _575 = /^[1]{0,1}[0-2]\.[0-5][0-9]$/i;
	var _576 = document.getElementById("addworkdate").value;
	var _577 = document.getElementById("logfromhr").value;
	var _578 = document.getElementById("logfrommins").value;
	var _579 = document.getElementById("logfromampm").value;
	var _57a = document.getElementById("logtohr").value;
	var _57b = document.getElementById("logtomins").value;
	var _57c = document.getElementById("logtoampm").value;
	if (_579 == "pm") {
		_577 = parseInt(_577, 10) + 12;
	}
	if (_57c == "pm") {
		_57a = parseInt(_57a, 10) + 12;
	}
	var _57d = _576 + " " + _577 + ":" + _578 + ":00";
	var _57e = _576 + " " + _57a + ":" + _57b + ":00";
	var _57f = new Date();
	var _580 = _57f.getHours();
	var _581 = _57f.getMinutes();
	Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
	var _582 = compareDates(_57d, Utils.dateformat + " HH:mm:ss", _57e, Utils.dateformat + " HH:mm:ss");
	if (stat == "add") {
		var _583 = document.LogListForm.tsheettype.value;
		var _584 = document.getElementById("tlistsheet_add_status");
		if (_583 == "task") {
			if (_576 != "" && _582 != 0) {
				_584.innerHTML = "<span class=\"error\">To time cannot be before From time</span>";
				return false;
			} else {
				if (document.LogListForm.logproject.value == "-1") {
					_584.innerHTML = "<span class=\"error\">Select Any Project</span>";
					document.LogListForm.logproject.focus();
					return (false);
				} else {
					if (document.LogListForm.logprojtask.value == "-1") {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valtodotask", null,
						function(mesg) {
							_584.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
						document.LogListForm.logprojtask.focus();
						return (false);
					} else {
						ShowGenInline("zoho_add_loglist_busy");
						return true;
					}
				}
			}
		}
		if (_583 == "general") {
			if (_576 != "" && retCurVal != 0) {
				_584.innerHTML = "<span class=\"error\">Elapsed Date & Time cannot be set</span>";
				return false;
			} else {
				if (_576 != "" && _582 != 0) {
					_584.innerHTML = "<span class=\"error\">To time cannot be before From time</span>";
					return false;
				} else {
					if (document.LogListForm.logproject.value == "-1") {
						_584.innerHTML = "<span class=\"error\">Select Any Project</span>";
						document.LogListForm.logproject.focus();
						return (false);
					} else {
						if (document.LogListForm.loggentask.value == "" || trim(document.LogListForm.loggentask.value).length == 0) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvaltask", null,
							function(mesg) {
								_584.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
							document.LogListForm.loggentask.focus();
							return (false);
						} else {
							ShowGenInline("zoho_add_loglist_busy");
							return true;
						}
					}
				}
			}
		}
	}
}
function validatePeopleHieForm(_587, uId) {
	var _589 = document.getElementById(_587);
	var _58a = eval(document.getElementById("peoplemode_" + uId).value);
	var _58b = eval(document.getElementById("peoplefac_" + uId).value);
	if (_58a == _58b) {
		_589.innerHTML = "<span class=\"error\">Invalid Hierarchy</span>";
		return (false);
	} else {
		return true;
	}
}
function sendListTimeLogAction(url) {
	document.LogListForm.action = url;
	document.LogListForm.submit();
}
function askOnEdit(msg) {
	var msg = msg.replace("<br>", "\n");
	var _58e = confirm(msg);
	if (_58e) {
		document.getElementById("edittype").value = "Yes";
		return (true);
	} else {
		document.getElementById("edittype").value = "No";
		return (false);
	}
}
function submitDependsTask(id, _590, _591, tid, tval) {
	if (tid == "000") {
		Hide("zoho_depend_submit_busy");
		var _594 = document.getElementById("dependstask_status");
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.selectdep", null,
		function(mesg) {
			_594.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return false;
	} else {
		eval("window.opener.document.getElementById('" + _590 + "').value = '" + tid + "'");
		eval("window.opener.document.getElementById('" + _591 + "').value = '" + tval + "'");
		window.close();
	}
}
function showDependsTask(_596, _597, _598) {
	if (_596 == "none") {
		Hide(_597);
		Hide(_598);
	} else {
		ShowGenInline(_597);
		ShowGenInline(_598);
	}
}
function addRowComponent(cnt, stat, _59b) {
	var ftd;
	if (stat == "add") {
		ftd = document.getElementById("adddependstaskdiv");
	} else {
		ftd = document.getElementById("adddependstaskdiv_" + stat);
	}
	if (!Utils.addDepends) {
		Utils.addDepends = 1;
	}
	Utils.addDepends = Utils.addDepends + 1;
	var tr = document.createElement("tr");
	if (stat == "add") {
		tr.id = "trid_" + cnt;
	} else {
		tr.id = "trid_" + stat + "_" + cnt;
	}
	var kcnt = parseInt(cnt, 10) + 1;
	var td = document.createElement("td");
	td.setAttribute("width", "91%");
	var ltd1 = document.createElement("td");
	var _5a1 = "";
	var _5a2 = "";
	var _5a3 = "";
	var _5a4 = "";
	if (stat == "add") {
		td.id = "dependstaskid_" + kcnt;
		_5a1 = "dependstaskid_" + kcnt;
		_5a2 = "deptask_" + kcnt;
		_5a3 = "dependency_" + kcnt;
		_5a4 = "deptaskid_" + kcnt;
	} else {
		td.id = "dependstaskid" + kcnt + "_" + stat;
		_5a1 = "dependstaskid" + kcnt + "_" + stat;
		_5a2 = "deptask" + kcnt + "_" + stat;
		_5a3 = "dependency" + kcnt + "_" + stat;
		_5a4 = "deptaskid" + kcnt + "_" + stat;
	}
	var ltd = document.createElement("td");
	ltd.setAttribute("width", "9%");
	var _5a6 = document.createElement("span");
	if (is_ie) {
		_5a6.setAttribute("className", "blackBigTags");
	} else {
		_5a6.setAttribute("class", "blackBigTags");
	}
	i18n.getJSAlertValue(Utils.zuid, "zp.adddepend.thistaskstarts", null,
	function(mesg) {
		var tele = document.createTextNode(mesg);
		_5a6.appendChild(tele);
	});
	td.appendChild(_5a6);
	td.appendChild(document.createTextNode(" "));
	var _5a9 = document.createElement("span");
	if (is_ie) {
		_5a9.setAttribute("className", "blackBigTags");
	} else {
		_5a9.setAttribute("class", "blackBigTags");
	}
	var bele = document.createElement("b");
	_5a9.appendChild(bele);
	i18n.getJSAlertValue(Utils.zuid, "zp.addtodotask.after", null,
	function(mesg) {
		bele.appendChild(document.createTextNode(mesg));
	});
	td.appendChild(_5a9);
	td.appendChild(document.createTextNode(" "));
	var _5ac = document.createElement("input");
	_5ac.setAttribute("type", "text");
	_5ac.setAttribute("id", _5a2);
	_5ac.setAttribute("name", "deptask");
	_5ac.setAttribute("readonly", "true");
	td.appendChild(_5ac);
	td.appendChild(document.createTextNode(" "));
	var _5ad = document.createElement("input");
	_5ad.setAttribute("type", "hidden");
	_5ad.setAttribute("id", _5a4);
	_5ad.setAttribute("name", "deptaskid");
	td.appendChild(_5ad);
	var _5ae = document.createElement("a");
	var _5af = Utils.contPath + "/selectdependtask.do?projId=" + _59b + "&prevtlId=" + stat + "&taskDispName=" + _5a2 + "&taskDispId=" + _5a4;
	var _5b0 = "TaskWin";
	_5ae.setAttribute("href", "javascript:openPopUp(" + "'TaskWin',this" + ",'" + _5af + "'," + "'SelectTask',500,200," + "'menubar=no,toolbar=no,location=no,status=no,scrollbars=yes,resizable=yes" + "')");
	_5ae.className = "navLink";
	i18n.getJSAlertValue(Utils.zuid, "zp.dependstask.selecttask", null,
	function(mesg) {
		_5ae.appendChild(document.createTextNode(mesg));
	});
	td.appendChild(_5ae);
	td.appendChild(document.createTextNode(" "));
	var btd = document.createElement("div");
	var _5b3 = document.createElement("a");
	var _5b4 = "javascript:removeRowComponent(" + cnt + ",'" + stat + "');";
	_5b3.setAttribute("href", _5b4);
	_5b3.className = "navLink";
	var _5b5 = document.createElement("img");
	_5b5.src = Utils.contPath + "/images/minus.gif";
	_5b5.setAttribute("border", "0");
	_5b5.setAttribute("align", "absmiddle");
	_5b3.appendChild(_5b5);
	i18n.getJSAlertValue(Utils.zuid, "zp.adddepend.rmvthisdepend", null,
	function(mesg) {
		_5b3.setAttribute("title", mesg);
	});
	btd.appendChild(_5b3);
	ltd.appendChild(btd);
	tr.appendChild(td);
	tr.appendChild(ltd);
	ftd.appendChild(tr);
	var _5b7 = document.createElement("a");
	var _5b8 = "javascript:addRowComponent(" + kcnt + ",'" + stat + "','" + _59b + "');";
	_5b7.setAttribute("href", _5b8);
	_5b7.className = "navLink";
	i18n.getJSAlertValue(Utils.zuid, "zp.adddepend.addmoredepend", null,
	function(mesg) {
		_5b7.appendChild(document.createTextNode(mesg));
	});
	var atr = document.createElement("tr");
	if (stat == "add") {
		atr.id = "addlink_" + cnt;
	} else {
		atr.id = "addlink_" + cnt + "_" + stat;
	}
	var atd = document.createElement("td");
	atd.appendChild(_5b7);
	atr.appendChild(atd);
	ftd.appendChild(atr);
	if (stat == "add") {
		Hide("addlink_" + (parseInt(cnt, 10) - 1));
	} else {
		Hide("addlink_" + (parseInt(cnt, 10) - 1) + "_" + stat);
	}
}
function removeRowComponent(cnt, stat) {
	Utils.addDepends = Utils.addDepends - 1;
	var ltd1 = document.createElement("td");
	var _5bf = "";
	var _5c0 = "";
	var _5c1 = "";
	var d2;
	if (stat == "add") {
		d2 = document.getElementById("trid_" + cnt);
	} else {
		d2 = document.getElementById("trid_" + stat + "_" + cnt);
	}
	var ftd;
	if (stat == "add") {
		ftd = document.getElementById("adddependstaskdiv");
	} else {
		ftd = document.getElementById("adddependstaskdiv_" + stat);
	}
	ftd.removeChild(d2);
}
var winObj;
function openPopUp(_5c4, _5c5, _5c6, _5c7, _5c8, _5c9, _5ca) {
	var left = parseInt(findPosX(_5c5));
	var top = parseInt(findPosY(_5c5));
	if (window.navigator.appName != "Opera") {
		top += parseInt(_5c5.offsetHeight);
	} else {
		top += (parseInt(_5c5.offsetHeight) * 2) + 10;
	}
	if (is_ie) {
		top += window.screenTop - document.body.scrollTop;
		left -= document.body.scrollLeft;
		if (top + _5c9 + 30 > window.screen.height) {
			top = findPosY(_5c5) + window.screenTop - _5c9 - 30;
		}
		if (left + _5c8 > window.screen.width) {
			left = findPosX(_5c5) + window.screenLeft - _5c8;
		}
	} else {
		top += (scrY - pgeY);
		left += (scrX - pgeX);
		if (top + _5c9 + 30 > window.screen.height) {
			top = findPosY(_5c5) + (scrY - pgeY) - _5c9 - 30;
		}
		if (left + _5c8 > window.screen.width) {
			left = findPosX(_5c5) + (scrX - pgeX) - _5c8;
		}
	}
	_5ca = "width=" + _5c8 + ",height=" + _5c9 + ",top=" + top + ",left=" + left + ";" + _5ca;
	winObj = eval(_5c4 + "=window.open(\"" + _5c6 + "\",\"" + _5c7 + "\",\"" + _5ca + "\")");
	winObj.focus();
}
var scrX = 0,
scrY = 0,
pgeX = 0,
pgeY = 0;
function getElem(id) {
	if (document.layers) {
		return document.layers[id];
	} else {
		if (document.all) {
			return document.all[id];
		} else {
			if (document.getElementById) {
				return document.getElementById(id);
			}
		}
	}
}
function swapFolder(img) {
	objImg = getElem(img);
	if (objImg.src.indexOf(Utils.contPath + "/images/plus.gif") > -1) {
		objImg.src = Utils.contPath + "/images/minus.gif";
	} else {
		objImg.src = Utils.contPath + "/images/plus.gif";
	}
	swapSub("s" + img);
}
function swapText(img) {
	objImg = getElem(img);
	if (objImg.innerText.indexOf("+") > -1) {
		objImg.innerText = "-";
	} else {
		objImg.innerText = "+";
	}
	swapSub("s" + img);
}
function swapSub(img) {
	elem = getElem(img);
	objImg = elem.style;
	if (objImg.display == "block") {
		objImg.display = "none";
	} else {
		objImg.display = "block";
	}
}
function SwapStyles(_5d1, _5d2, _5d3, stat, id) {
	var id0 = document.getElementById(_5d1);
	var id1 = document.getElementById(_5d2);
	var id2 = document.getElementById(_5d3);
	if (stat == "show") {
		var disp = document.getElementsByTagName("tr");
		for (i = 0; i < disp.length; i++) {
			if (disp[i].id == _5d1) {
				disp[i].className = "";
			}
			if (disp[i].id == "trid_") {
				disp[i].className = "";
			}
		}
		id1.className = "";
		id2.className = "";
	} else {
		if (stat == "hide") {
			var disp = document.getElementsByTagName("tr");
			for (i = 0; i < disp.length; i++) {
				if (disp[i].id == _5d1) {
					disp[i].className = "hide";
				}
			}
			id1.className = "hide";
			id2.className = "hide";
		}
	}
}
function validateMyProgressForm(_5da) {
	var _5db = document.getElementById("logdate_" + _5da).value;
	var _5dc = document.getElementById("logtodate_" + _5da).value;
	Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
	var _5dd = compareDates(_5db, Utils.dateformat, _5dc, Utils.dateformat);
	if (_5dd == 0) {
		return true;
	} else {
		alert("To date cannot be before From date");
		return false;
	}
}
MuliFileInit = function() {
	mf = new MultiFile(document.getElementById("draganddropfilelist"), document.getElementById("fileview"), "file", "fileEntryTemplate");
	mf.reinit();
	mf.addFile(document.getElementById("sfile"));
};
var files = new Array();
var countfiles = 0;
MultiFile = function(_5de, view, _5e0, _5e1) {
	this.count = 0;
	this.container = _5de;
	this.view = view;
	this.prefix = _5e0;
	this.template = _5e1;
	this.id = 0;
	window._mf = this;
	this.addFile = function(el) {
		if (el != null) {
			el.id = this.prefix + this.id++;
			el.name = "uploaddoc";
			el._selector = this;
			this.container.setAttribute("_current", el.id);
			el.onchange = function() {
				var flag = new Boolean();
				var bool = new Boolean(false);
				if (countfiles > 0) {
					for (var i = 0; i < countfiles; i++) {
						if (files[i] == el.value) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.filealreadychoosen", null,
							function(mesg) {
								alert(mesg);
							});
							bool = true;
						}
					}
				}
				var url = el.value;
				if (url.match(window.location.protocol)) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.urlnotsupported", null,
					function(mesg) {
						alert(mesg);
					});
					el.value = "";
				} else {
					if (bool != true || countfiles == 0) {
						flag = validateFormElement(el);
						if (flag == true) {
							this.style.display = "none";
							var nf = document.createElement("INPUT");
							nf.type = "file";
							nf.size = "25";
							this.parentNode.insertBefore(nf, this);
							var _5ea = document.getElementById("afteruploadelements");
							_5ea.appendChild(this);
							this._selector.addFile(nf);
							this._selector.addView(this.id);
							files[countfiles] = el.value;
							countfiles++;
							showOrHideFilesList();
						} else {
							el.value = "";
						}
					} else {
						el.value = "";
					}
				}
			};
		}
	};
	this.addView = function(elid) {
		var el = document.getElementById(elid);
		var _5ed = el.value;
		if (el.value == "") {
			if (el.iter) {
				el.iter++;
			} else {
				el.iter = 1;
			}
			if (el.iter < 6) {
				setTimeout("window._mf.addView('" + elid + "')", 100);
			}
			return;
		}
		var _5ee = _5ed.split("\\");
		var _5ef = _5ed.split("/");
		var name = _5ed;
		if (_5ee.length > 1) {
			name = _5ee[_5ee.length - 1];
		} else {
			if (_5ef.length > 1) {
				name = _5ef[_5ef.length - 1];
			}
		}
		var fv = jstParse(this.template, [{
			id: "fv" + el.id,
			filename: name,
			filesize: el.getAttribute("filesize")
		}]);
		this.view.appendChild(fv[0]);
	};
	this.deleteFile = function(vid) {
		var v = document.getElementById(vid);
		if (v) {
			v.parentNode.removeChild(v);
			var f = document.getElementById(vid.replace(/fv/, ""));
			clearList(f.value);
			f.parentNode.removeChild(f);
			this.count--;
			showOrHideFilesList();
		}
	};
	this.beforeSubmit = function() {
		var f = document.getElementById(this.container.getAttribute("_current"));
		var sp = document.createElement("span");
		sp.id = "_mfu";
		f.parentNode.insertBefore(sp, f);
		f.parentNode.removeChild(f);
	};
	this.reinit = function() {
		var c = this.count;
		for (var i = 0; i <= countfiles; i++) {
			this.deleteFile("fv" + this.prefix + i);
			files[i] = "";
		}
		var _5f9 = document.getElementById("fileview");
		var _5fa = _5f9.firstChild;
		while (_5fa != null) {
			var _5fb = _5fa.nextSibling;
			if (_5fa.tagName != null) {
				if (_5fa.tagName.toLowerCase() == "tr") {
					var _5fc = _5fa.id;
					if (_5fc.indexOf("dr") == 0) {
						deleteDocFile(_5fc);
					} else {
						if (_5fc.indexOf("gd") == 0) {
							deleteGDocFile(_5fc);
						}
					}
				}
			}
			_5fa = _5fb;
		}
		this.count = 0;
		this.id = 0;
		var mfu = document.getElementById("_mfu");
		if (mfu) {
			var nf = document.createElement("INPUT");
			nf.type = "file";
			mfu.parentNode.replaceChild(nf, mfu);
			this.addFile(nf);
		}
	};
};
function validateFormElement(_5ff) {
	var rows = document.getElementById("muluploaddisptable").rows.length;
	if (rows >= 10) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.atatimemorethan", null,
		function(mesg) {
			alert(mesg);
		});
		return (false);
	} else {
		var url = _5ff.value;
		var _603 = trim(url).lastIndexOf("/");
		if (_603 == -1) {
			_603 = trim(url).lastIndexOf("\\");
		}
		if (trim(url).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsuplfile", null,
			function(mesg) {
				alert(mesg);
			});
			_5ff.focus();
			return (false);
		} else {
			if (endsWith(trim(url), "/") || endsWith(trim(url), "\\")) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.plsuplfile", null,
				function(mesg) {
					alert(mesg);
				});
				_5ff.focus();
				return (false);
			} else {
				if (trim(url).length > _603 + 100) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.filnmtolng", null,
					function(mesg) {
						alert(mesg);
					});
					_5ff.focus();
					return (false);
				} else {
					return true;
				}
			}
		}
	}
}
function showOrHideFilesList() {
	var rows = document.getElementById("muluploaddisptable").rows.length;
	if (rows >= 1) {
		document.getElementById("selectedfiles").className = "show";
	} else {
		document.getElementById("selectedfiles").className = "hide";
	}
}
function clearList(val) {
	for (var i = 0; i < countfiles; i++) {
		if (files[i] == val) {
			files[i] = "";
		}
	}
}
jstEvalString = function(es) {
	es = es.replace(/_\{.*?}/g,
	function($1) {
		$1 = $1.substring(2, $1.length - 1);
		var as = $1.split(",");
		var ls = "";
		for (var it = 0; it < as.length; it++) {
			ls += (it != 0) ? "=": "";
			ls += "_$t." + as[it];
		}
		return ls;
	});
	es = es.replace(/@\{.*?}/g,
	function($1) {
		$1 = $1.substring(2, $1.length - 1);
		return "_$o." + $1;
	});
	return es;
};
jstDOMWalk = function(_$t, _$o) {
	if (_$t && _$t.tagName) {
		if (_$t.getAttribute("jstEval")) {
			eval(jstEvalString(_$t.getAttribute("jstEval")));
			_$t.removeAttribute("jstEval");
		}
	}
	for (var i = 0; i < _$t.childNodes.length; i++) {
		var _$c = _$t.childNodes[i];
		jstDOMWalk(_$c, _$o);
	}
};
jstParse = function(_614, _615) {
	var ra = new Array();
	var t = document.getElementById(_614);
	for (var i = 0; i < _615.length; i++) {
		var ti = t.cloneNode(true);
		ti.id = "jst" + (Math.random() * 11111111111111110000);
		jstDOMWalk(ti, _615[i]);
		ra[i] = ti;
	}
	return ra;
};
function validateMulUploadForm() {
	var _61a;
	var doc = document;
	if (trim(doc.getElementById("fileview").innerHTML) == "") {
		_61a = "zp.functionjs.nofilestoup";
		scroll(0, 0);
	} else {
		if (isFilesSizeGreater("uploaddoc", 125)) {
			_61a = "zp.docdetails.greatersize";
			scroll(0, 0);
		}
	}
	if (_61a) {
		i18n.getJSAlertValue(Utils.zuid, _61a, null,
		function(mesg) {
			doc.getElementById("muldoc_upload_status").innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return false;
	}
	ShowGenInline("zoho_add_docs_busy");
	return true;
}
function newFoldPopup() {
	var _61d = document.addDocuments.uploadedfolder;
	if (_61d.value == "addingfolder") {
		var _61e = "";
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.enterfolname", null,
		function(mesg) {
			_61e = prompt(mesg, "");
			if (_61e != "" && _61e != null) {
				var _620 = document.createElement("option");
				if (_620.textContent !== undefined) {
					_620.textContent = _61e;
				} else {
					_620.innerText = _61e;
				}
				_620.value = "012+z-z-z+" + _61e;
				_61d.appendChild(_620);
				_620.selected = true;
			} else {
				_61d.options[0].selected = true;
			}
		});
	}
}
function changeSubTabStyles(id1, id2, id3, id4, id5) {
	document.getElementById(id1).className = "dvITabUnSelected";
	document.getElementById(id2).className = "dvITabUnSelected";
	document.getElementById(id3).className = "dvITabUnSelected";
	document.getElementById(id4).className = "dvITabUnSelected";
	document.getElementById(id5).className = "dvIDepViewTabSelected";
	document.getElementById("tomitab").className = "dvIDepViewTBorder";
}
function validateDepForm(_626) {
	if (_626 == "tasktitle") {
		var _627 = "deptitleform";
		var _628 = "task";
		var _629 = eval("document.getElementById(\"deptaskdisp_" + depaction_id + "\")").value;
		if (trim(eval("document." + _627 + "." + _628 + ".value")) == trim(_629)) {
			return false;
		}
		if (trim(eval("document." + _627 + "." + _628 + ".value")).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valtaskname", null,
			function(mesg) {
				alert(mesg);
			});
			return false;
		}
		return true;
	} else {
		if (_626 == "duration") {
			var _627 = "depdurform";
			var _62b = "duration";
			var _629 = eval("document.getElementById(\"depdurspan_" + depaction_id + "\")").innerHTML;
			_629 = trim(_629);
			if (_629 == trim(eval("document." + _627 + "." + _62b + ".value"))) {
				return false;
			}
			if (trim(eval("document." + _627 + "." + _62b + ".value")).length == 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valduration", null,
				function(mesg) {
					alert(mesg);
				});
				return false;
			}
			var dt = "days";
			var d = document.getElementById("durtext").value;
			if (Utils.taskinhr == "true") {
				dt = jQuery("input:radio[name=popdurtype]:checked").val();
			}
			if (!isNumeric(d) && (dt == "days")) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.tododurmustbe", null,
				function(mesg) {
					alert(mesg);
				});
				return false;
			} else {
				if (d != "" && !isValDur(d) && dt == "hrs") {
					i18n.getJSAlertValue(Utils.zuid, "zp.task.invdur", null,
					function(mesg) {
						alert(mesg);
					});
					return false;
				}
			}
			return true;
		} else {
			if (_626 == "enddate") {
				var _627 = "";
				var _629 = "";
				var _631 = "";
				var _632 = "";
				if (_626 == "enddate") {
					_627 = "dependdateform";
					_629 = eval("document.getElementById(\"dependisp_" + depaction_id + "\")").innerHTML;
					_631 = eval("document.getElementById(\"depstdisp_" + depaction_id + "\")").innerHTML;
					_632 = eval("document." + _627 + ".enddate.value");
				} else {
					if (_626 == "startdate") {
						_627 = "depstartdateform";
						_629 = eval("document.getElementById(\"depstdisp_" + depaction_id + "\")").innerHTML;
						_631 = eval("document." + _627 + ".startdate.value");
						_632 = eval("document.getElementById(\"dependisp_" + depaction_id + "\")").innerHTML;
					}
				}
				_629 = trim(_629);
				_631 = trim(_631);
				_632 = trim(_632);
				if (_626 == "startdate" && _631 == _629) {
					return false;
				} else {
					if (_626 == "enddate" && _632 == _629) {
						return false;
					}
				}
				Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
				Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
				Utils.timeformat = Utils.timeformat.replace("aaa", "a");
				var _633 = Utils.dateformat;
				if (Utils.taskinhr == "true" || !(isDIHrs(_631))) {
					_633 = Utils.dateformat + " " + Utils.timeformat;
				}
				var _634 = compareDates(_631, _633, _632, _633);
				if (_631 != "" && _632 != "" && _634 != 0) {
					i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
					function(mesg) {
						alert(mesg);
					});
					return false;
				} else {
					return true;
				}
			} else {
				if (_626 == "dependency") {
					var _627 = "deppredform";
					var _636 = "dependency";
					var _637 = "taskcount";
					var _638 = trim(eval("document." + _627 + "." + _636 + ".value"));
					var _639 = trim(eval("document." + _627 + "." + _637 + ".value"));
					var _63a = document.getElementById("totaltasks").value;
					var _63b = parseInt(trim(_63a), 10);
					var _629 = eval("document.getElementById(\"depsafdisp_" + depaction_id + "\")").innerHTML;
					_629 = trim(_629);
					if (_638 == _629) {
						return false;
					}
					if (_638.length > 0) {
						var _63c = _638.split(",");
						var _63d = new Array;
						var _63e = "";
						for (var i = 0; i < _63c.length; i++) {
							var tSet = true;
							var _641 = _63c[i];
							if (!isNumeric((trim(_641)))) {
								i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valdepend", null,
								function(mesg) {
									alert(mesg);
								});
								return false;
							} else {
								if (parseInt(trim(_641), 10) == parseInt(trim(_639), 10) || parseInt(trim(_641), 10) == 0 || _641 == "" || parseInt(trim(_641), 10) > _63b) {
									i18n.getJSAlertValue(Utils.zuid, "zp.funcitonjs.entervaldep", null,
									function(mesg) {
										alert(mesg);
									});
									return false;
								} else {
									var _644 = _63c[i];
									for (var j = i + 1; j < _63c.length; j++) {
										if (_63c[j] == _644) {
											tSet = false;
										}
									}
									if (tSet == true) {
										_63d.push(_644);
										if (_63e != "") {
											_63e = trim(_63e) + trim(",") + trim(_644);
										} else {
											_63e = trim(_644);
										}
									}
								}
							}
						}
						document.deppredform.dependency.value = _63e;
						return true;
					} else {
						return true;
					}
				} else {
					return true;
				}
			}
		}
	}
}
function validateTaskUserForm(id) {
	var _647 = "addDepTodoUserForm_" + id;
	var _648 = "zoho_add_depuser_busy_" + id;
	var _649 = document.getElementById("depuser_add_status_" + id);
	var _64a = new Array();
	var _64b = eval("document." + _647 + ".personresponsible");
	var i;
	var _64d = 0;
	if (_64b.length) {
		for (i = 0; i < _64b.length; i++) {
			if (_64b[i].checked) {
				_64a[_64d] = _64b[i].value;
				_64d++;
			}
		}
	} else {
		if (_64b.checked) {
			_64a[0] = _64b.value;
			_64d++;
		}
	}
	if (_64a.length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.selectowner", null,
		function(mesg) {
			alert(mesg);
		});
		return (false);
	} else {
		if (_64a.length > 1 && (_64a.indexOf("AnyUser") != -1)) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.invalidowner", null,
			function(mesg) {
				_649.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return (false);
		} else {
			ShowGenInline(_648);
			return true;
		}
	}
}
function TaskDependencySendStatus(Id, cobj) {
	var id = document.getElementById(Id);
	if (cobj.checked) {
		Utils.isAddDependMailOpt = true;
		id.value = "yes";
	} else {
		Utils.isAddDependMailOpt = false;
		id.value = "no";
	}
}
function upgChatWikiCCMsg(msg) {
	document.getElementById("zpwikichatstatusmsg").innerHTML = msg;
	var sts = document.getElementById("zpwikichatstatus");
	sts.style.display = "block";
	var doc = findDocDim();
	var _656 = sts.offsetWidth;
	var _657 = sts.offsetHeight;
	var left = (doc.width / 2) - _657 - 90;
	var top = (doc.height / 2) + (document.body.scrollTop / 2) - (_657 / 2);
	sts.style.left = parseInt(left) + "px";
	sts.style.top = parseInt(top) + "px";
}
function displayCCMsg(msg, _65b) {
	document.getElementById("zpccstatusmsg").innerHTML = msg;
	var sts = document.getElementById("zpccstatus");
	sts.style.display = "block";
	var doc = findDocDim();
	var _65e = sts.offsetWidth;
	var _65f = sts.offsetHeight;
	var left = (doc.width / 2) - _65f - 90;
	var top = (doc.height / 2) + (document.body.scrollTop / 2) - (_65f / 2);
	sts.style.left = parseInt(left) + "px";
	sts.style.top = parseInt(top) + "px";
}
function validateTaskDocument(_662) {
	var sId = "existdoc_upload_status_" + _662;
	var fId = "folderid_" + _662;
	var dId = "taskdoc_" + _662;
	var _662 = document.getElementById(sId);
	if (document.getElementById(fId).value == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.meetdoc.selectfold", null,
		function(mesg) {
			_662.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return false;
	} else {
		if (document.getElementById(dId).value == 0 || trim(document.getElementById(dId).value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.taskdocs.selectdoc", null,
			function(mesg) {
				_662.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return false;
		}
	}
	return true;
}
function validateTaskForum(_668) {
	var sId = "existforum_status_" + _668;
	var cId = "categoryid_" + _668;
	var fId = "taskforum_" + _668;
	var _668 = document.getElementById(sId);
	if (document.getElementById(cId).value == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.taskforums.selectcat", null,
		function(mesg) {
			_668.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return false;
	} else {
		if (document.getElementById(fId).value == 0 || trim(document.getElementById(fId).value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.taskforums.selectfor", null,
			function(mesg) {
				_668.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return false;
		}
	}
	return true;
}
function validateAddProjectClient() {
	var _66e = document.projectClientUsers.projCUserType.value;
	var _66f = document.getElementById("projclientalert");
	if (_66e == "clientuser") {
		var list = eval("document.projectClientUsers.projclientusers");
		var _671 = list.options[list.selectedIndex].value;
		if (_671.length == 0 || _671 == "0") {
			i18n.getJSAlertValue(Utils.zuid, "zp.projuser.selectanyclientuser", null,
			function(mesg) {
				_66f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			Hide("zoho_upd_proj_cuser_busy");
			return false;
		} else {
			return true;
		}
	} else {
		var _673 = document.projectClientUsers.projclientname.value;
		var _674 = document.projectClientUsers.projclientuser.value;
		var _675 = /^[a-z0-9]([a-z0-9_\-\.\+]*)@([a-z0-9_\-\.]*)(\.[a-z]{2,4}(\.[a-z]{2}){0,2})$/i;
		if (_673.length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvalcliname", null,
			function(mesg) {
				_66f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.projectClientUsers.projclientname.focus();
			Hide("zoho_upd_proj_cuser_busy");
			return false;
		} else {
			if (_674.length == 0 || !_675.test(_674)) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valemailid", null,
				function(mesg) {
					_66f.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.projectClientUsers.projclientuser.focus();
				Hide("zoho_upd_proj_cuser_busy");
				return false;
			} else {
				return true;
			}
		}
	}
}
function validateMeetDocument(_678) {
	var sId = "existdoc_upload_status_" + _678;
	var fId = "folderid_" + _678;
	var dId = "meetdoc_" + _678;
	var _678 = document.getElementById(sId);
	if (document.getElementById(fId).value == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.meetdoc.selectfold", null,
		function(mesg) {
			_678.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return false;
	} else {
		if (trim(document.getElementById(dId).value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.taskdocs.selectdoc", null,
			function(mesg) {
				_678.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return false;
		}
	}
	return true;
}
function projectDivSts() {
	var _67e = document.addUser.urole.value;
	var id = document.getElementById("projectDivFrPep1");
	if (_67e == "jointadmin") {
		ShowHideBlock("jointAdminDivFrPep", "projectDivFrPep");
		id.style.display = "none";
	} else {
		ShowHideBlock("projectDivFrPep", "jointAdminDivFrPep");
		id.style.display = "block";
	}
}
function userProjDivSts() {
	var _680 = document.userprofileForm.comprole.value;
	if (_680 == "jointadmin") {
		ShowHideInline("userprofjasts", "userprofsts");
	} else {
		ShowHideInline("userprofsts", "userprofjasts");
	}
}
function downloadUrl(url) {
	location.href = url;
}
function openInNewTab(url) {
	window.open(url, "_blank");
}
function setShowHide(_683) {
	var _684 = document.getElementById(_683).className;
	if (_684 == "hide") {
		ShowGenBlock(_683);
	} else {
		Hide(_683);
	}
}
function searchByRec(_685, csrf) {
	var _687 = "all";
	document.sform.st.value = _685;
	document.sform.scope.value = "all";
	searchProj(csrf, _687);
}
function clearrecsearch() {
	ajaxShowPage(Utils.contPath + "clearrs.do", "recserid");
}
function searchAll(csrf) {
	document.sform.scope.value = "all";
	searchProj(csrf, "all");
}
function searchProj(csrf, _68a, _68b) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var st = document.sform.st.value;
	st = new String(st);
	st = st.replace(/(^\s*)|(\s*$)/gi, "");
	if (st == "") {
		if (_68b) {
			var _68d = document.getElementById("search_status");
			i18n.getJSAlertValue(Utils.zuid, "zp.search.empsearch", null,
			function(mesg) {
				_68d.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return false;
		}
		document.sform.scope.value = _68a;
		searchonload();
		return false;
	} else {
		if ((new String(st)).length == 1) {
			i18n.getJSAlertValue(Utils.zuid, "zp.search.plzenterwords", null,
			function(mesg) {
				alert(mesg);
			});
			return false;
		} else {
			ShowGenInline("ajax_load_tab");
			_68a = document.sform.scope.value;
			var _690 = "";
			if (document.sform.proj.options) {
				for (var s = 1; s < document.sform.proj.options.length; s++) {
					var _692 = document.sform.proj.options[s].value;
					if (_690 != "") {
						_690 = _690 + "," + _692;
					} else {
						_690 = _692;
					}
				}
			}
			if (document.sform.proj.value == null || document.sform.proj.value == "null") {
				document.sform.proj.value = "all";
			}
			if (_690 == "" && document.sform.proj.value == "all") {
				_690 = "all";
			}
			ajaxShowPage(Utils.contPath + "/setsearchrng.do?scope=" + _68a + "&st=" + encodeURIComponent(document.sform.st.value) + "&proj=" + document.sform.proj.value + "&plist=" + _690 + "&" + csrf, "projectcontent");
		}
	}
}
function searchNav(csrf, _694, _695, _696, _697) {
	ShowGenInline("ajax_load_tab");
	var _698 = "";
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	if (document.sform.proj.options) {
		for (var s = 1; s < document.sform.proj.options.length; s++) {
			var _69a = document.sform.proj.options[s].value;
			if (_698 != "") {
				_698 = _698 + "," + _69a;
			} else {
				_698 = _69a;
			}
		}
	}
	var url = Utils.contPath + "/search.do?scope=" + _694 + "&st=" + encodeURIComponent(document.sform.st.value) + "&proj=" + document.sform.proj.value + "&sindex=" + _696 + "&searchsize=" + _695 + "&plist=" + _698 + "&" + csrf;
	if (_697) {
		url = Utils.contPath + "/projsearch.do?scope=" + _694 + "&st=" + encodeURIComponent(document.sform.st.value) + "&proj=" + document.sform.proj.value + "&sindex=" + _696 + "&searchsize=" + _695 + "&" + csrf;
	}
	if (_694 == "todos" || _694 == "all" || _694 == "docs") {
		var page = _696 / 25;
		page = new String(page);
		if (page.indexOf(".") != -1) {
			var end = page.indexOf(".");
			page = page.substring(0, end);
		}
		if (document.sform.issuecount != null) {
			url = url + "&page=" + page + "&tcount=" + document.sform.tcount.value + "&tlcount=" + document.sform.tlcount.value + "&tncount=" + document.sform.tncount.value + "&mscount=" + document.sform.mscount.value + "&doccount=" + document.sform.doccount.value + "&mesgcount=" + document.sform.mesgcount.value + "&comcount=" + document.sform.comcount.value + "&doccomcount=" + document.sform.doccomcount.value + "&doctagcount=" + document.sform.doctagcount.value + "&issuecount=" + document.sform.issuecount.value + "&wikicount=" + document.sform.wikicount.value + "&meetcount=" + document.sform.meetcount.value + "&projdesccount=" + document.sform.projdesccount.value + "&announcecount=" + document.sform.announcecount.value + "&statuscount=" + document.sform.statuscount.value;
		} else {
			url = url + "&page=" + page + "&tcount=" + document.sform.tcount.value + "&tlcount=" + document.sform.tlcount.value + "&tncount=" + document.sform.tncount.value + "&mscount=" + document.sform.mscount.value + "&doccount=" + document.sform.doccount.value + "&mesgcount=" + document.sform.mesgcount.value + "&comcount=" + document.sform.comcount.value + "&doccomcount=" + document.sform.doccomcount.value + "&doctagcount=" + document.sform.doctagcount.value + "&wikicount=" + document.sform.wikicount.value + "&meetcount=" + document.sform.meetcount.value + "&projdesccount=" + document.sform.projdesccount.value + "&announcecount=" + document.sform.announcecount.value + "&statuscount=" + document.sform.statuscount.value;
		}
	}
	ajaxShowPage(url, "projectcontent");
}
function searchonload() {
	ShowGenInline("ajax_load_tab");
	var _69e;
	if (document.sform.scope) {
		_69e = document.sform.scope.value;
	} else {
		_69e = "all";
	}
	var aels = document.getElementsByName("serscopear");
	for (var a = 0; a < aels.length; a++) {
		aels[a].style.display = "inline";
	}
	var lels = document.getElementsByName("serscopelbl");
	for (var a = 0; a < lels.length; a++) {
		lels[a].style.display = "none";
	}
	if (_69e != "all") {
		if (is_ie) {
			document.getElementById("alllbl").style.display = "none";
		}
	}
	if (_69e == "all") {
		ael = "allach";
		lel = "alllbl";
	} else {
		if (_69e == "milestones") {
			ael = "mileach";
			lel = "milelbl";
		} else {
			if (_69e == "todos") {
				ael = "todoach";
				lel = "todolbl";
			} else {
				if (_69e == "docs") {
					ael = "fileach";
					lel = "filelbl";
				} else {
					if (_69e == "messages") {
						ael = "mesgach";
						lel = "mesglbl";
					} else {
						if (_69e == "comments") {
							ael = "comach";
							lel = "comlbl";
						} else {
							if (_69e == "meeting") {
								ael = "meetach";
								lel = "meetlbl";
							} else {
								if (_69e == "issue") {
									ael = "issueach";
									lel = "issuelbl";
								} else {
									if (_69e == "wiki") {
										ael = "wikiach";
										lel = "wikilbl";
									}
								}
							}
						}
					}
				}
			}
		}
	}
	document.getElementById(lel).style.display = "inline";
	document.getElementById(ael).style.display = "none";
}
function searchmeet(csrf) {
	document.sform.scope.value = "meeting";
	searchProj(csrf, "meeting");
}
function searchmile(csrf) {
	document.sform.scope.value = "milestones";
	searchProj(csrf, "milestones");
}
function searchtodo(csrf) {
	document.sform.scope.value = "todos";
	searchProj(csrf, "todos");
}
function searchmesg(csrf) {
	document.sform.scope.value = "messages";
	searchProj(csrf, "messages");
}
function searchcom(csrf) {
	document.sform.scope.value = "comments";
	searchProj(csrf, "comments");
}
function searchfile(csrf) {
	document.sform.scope.value = "docs";
	searchProj(csrf, "docs");
}
function validateMPP() {
	if (document.mppimport.mpp.value == "") {
		i18n.getJSAlertValue(Utils.zuid, "zp.impmpp.empty", null,
		function(mesg) {
			alert(mesg);
		});
		return false;
	} else {
		var _6a9 = document.mppimport.projId.selectedIndex;
		var _6aa = document.mppimport.projId.options[_6a9].text;
		document.mppimport.pname.value = _6aa;
		var _6ab = document.mppimport.mpp.value;
		if (!_6ab.match(/[.]mp[p|x]$/i)) {
			i18n.getJSAlertValue(Utils.zuid, "zp.impmpp.mppcrit", null,
			function(mesg) {
				alert(mesg);
			});
			return false;
		} else {
			document.getElementById("zoho_mpp_busy").className = "inline";
		}
	}
}
function mapattendees() {
	if (document.radform.r1[1].checked) {
		var elm = document.getElementById("butdiv");
		elm.className = "hide";
		var el = document.getElementById("usdiv");
		el.className = "inline";
	} else {
		var elm = document.getElementById("butdiv");
		elm.className = "inline";
		var el = document.getElementById("usdiv");
		el.className = "hide";
	}
}
function assigntoanyUser(csrf) {
	var e = document.getElementById("zoho_mpp_imp_busy");
	e.className = "inline";
	var ie = document.getElementById("contbut");
	ie.disabled = true;
	var ie = document.getElementById("bck");
	ie.disabled = true;
	document.mppimp.tskass.value = "AnyUser";
	var _6b2 = document.mppimp.ldate.value;
	var _6b3 = document.mppimp.mppname.value;
	var _6b4 = document.mppimp.blckid.value;
	var ct = document.mppimp.ctime.value;
	ShowGenBlock("mppimp_tab");
	ajaxAddMppTasks(document.mppimp.tskass.value, document.mppimp.projId.value, ct, _6b2, "mppres", _6b3, csrf, _6b4);
	return false;
}
function tobck() {
	document.getElementById("istep1").className = "inline";
	document.getElementById("istep2").className = "hide";
}
function checkAssigns(_6b6) {
	var eqls = "no";
	for (var a = 0; a < document.mppimp.mppuser.length; a++) {
		for (var b = a + 1; b < document.mppimp.mppuser.length; b++) {
			var ind1 = document.mppimp.zpusers[a].selectedIndex;
			var _6bb = document.mppimp.zpusers[a].options[ind1].value;
			var ind2 = document.mppimp.zpusers[b].selectedIndex;
			var _6bd = document.mppimp.zpusers[b].options[ind2].value;
			if (_6bb != "AnyUser") {
				if (_6bb == _6bd) {
					eqls = "yes";
					break;
				}
			}
		}
	}
	if (eqls == "yes") {
		return confirm(_6b6);
	} else {
		return true;
	}
}
function confirmassign(_6be) {
	var _6bf = _6be.replace("<br>", "\n");
	if (checkAssigns(_6bf)) {
		document.getElementById("istep1").className = "hide";
		if (document.mppimp.mppuser.length) {
			for (var a = 0; a < document.mppimp.mppuser.length; a++) {
				var _6c1 = document.mppimp.mppuser[a].value;
				var ind = document.mppimp.zpusers[a].selectedIndex;
				var _6c3 = document.mppimp.zpusers[a].options[ind].text;
				document.mppimp1.seluser[a].value = _6c3;
			}
		} else {
			var _6c1 = document.mppimp.mppuser.value;
			var ind = document.mppimp.zpusers.selectedIndex;
			var _6c3 = document.mppimp.zpusers.options[ind].text;
			document.mppimp1.seluser.value = _6c3;
		}
		document.getElementById("istep2").className = "inline";
	}
}
function assignUsers(_6c4, _6c5, _6c6, csrf) {
	var e = document.getElementById(_6c5);
	e.className = "inline";
	document.getElementById(_6c6).disabled = true;
	document.getElementById(_6c4).disabled = true;
	var _6c9 = new Array();
	if (document.mppimp.mppuser) {
		if (document.mppimp.mppuser.length) {
			for (var a = 0; a < document.mppimp.mppuser.length; a++) {
				muser = document.mppimp.mppuser[a].value;
				ind = document.mppimp.zpusers[a].selectedIndex;
				name1 = document.mppimp.zpusers[a].options[ind].value;
				task1 = new task(muser, name1);
				_6c9[a] = task1;
			}
		} else {
			muser = document.mppimp.mppuser.value;
			ind = document.mppimp.zpusers.selectedIndex;
			name1 = document.mppimp.zpusers.options[ind].value;
			task1 = new task(muser, name1);
			_6c9[0] = task1;
		}
		document.mppimp.tskass.value = encodeURIComponent(_6c9.toString());
	} else {
		document.mppimp.tskass.value = "AnyUser";
	}
	var _6cb = document.mppimp.ldate.value;
	var _6cc = document.mppimp.blckid.value;
	var _6cd = document.mppimp.mppname.value;
	var ct = document.mppimp.ctime.value;
	ShowGenBlock("mppimp_tab");
	ajaxAddMppTasks(document.mppimp.tskass.value, document.mppimp.projId.value, ct, _6cb, "mppres", _6cd, csrf, _6cc);
	return false;
}
function task(_6cf, _6d0) {
	this.rsrcname = _6cf;
	this.assname = _6d0;
	this.toString = toString;
}
function toString() {
	var res = "{taskrsrcname: " + this.rsrcname + ";taskAttendee: " + this.assname + "}";
	return res;
}
function setbTitle(_6d2, tab) {
	if (tab == "mile" || tab == "tl" || tab == "tk") {
		i18n.getJSAlertValue(Utils.zuid, "zp.tomi.tab", null,
		function(mesg) {
			setBrowserTitle(_6d2 + "- " + mesg);
		});
	} else {
		if (tab == "meet") {
			i18n.getJSAlertValue(Utils.zuid, "zp.meetings.tab", null,
			function(mesg) {
				setBrowserTitle(_6d2 + "- " + mesg);
			});
		} else {
			if (tab == "mesg") {
				i18n.getJSAlertValue(Utils.zuid, "zp.forums.tab", null,
				function(mesg) {
					setBrowserTitle(_6d2 + "- " + mesg);
				});
			} else {
				if (tab == "doc") {
					i18n.getJSAlertValue(Utils.zuid, "zp.doc.tab", null,
					function(mesg) {
						setBrowserTitle(_6d2 + "- " + mesg);
					});
				} else {
					if (tab == "wiki") {
						i18n.getJSAlertValue(Utils.zuid, "zp.wiki.tab", null,
						function(mesg) {
							setBrowserTitle(_6d2 + "- " + mesg);
						});
					} else {
						if (tab == "issue") {
							i18n.getJSAlertValue(Utils.zuid, "zp.bugs.tab", null,
							function(mesg) {
								setBrowserTitle(_6d2 + "- " + mesg);
							});
						}
					}
				}
			}
		}
	}
}
function getSearchMiles(csrf, _6db, _6dc, _6dd, _6de, val, tlid, _6e1) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	ShowGenInline("ajax_load_tab");
	var tgt1 = Utils.contPath + "/tasklistview.do?projId=" + _6db + "&username=all&istabenabled=yes&mileId=" + _6de + "&" + csrf;
	if (_6e1) {
		tgt1 = tgt1 + _6e1;
	}
	ajaxShowSearchPage(csrf, tgt1, "projectpagediv", val, _6db, tlid);
}
function getSearchTaskList(csrf, _6e4, _6e5, _6e6, _6e7, val, tlid, _6ea) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	ShowGenInline("ajax_load_tab");
	var tgt1 = Utils.contPath + "/fetchtasklist.do?projid=" + _6e4 + "&tlistid=" + tlid + "&" + csrf + "&username=all&istabenabled=yes&from=niceurl&frompage=arctlist&status=all";
	if (_6ea) {
		tgt1 = tgt1 + _6ea;
	}
	ajaxShowSearchPage(csrf, tgt1, "projectpagediv", val, _6e4, tlid);
}
function getSearchTask(csrf, _6ed, _6ee, tlid, _6f0) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	ShowGenInline("ajax_load_tab");
	var tgt1 = Utils.contPath + "/taskdetails.do?projId=" + _6ed + "&tlistId=" + tlid + "&taskid=" + _6f0 + "&fpriority=all&fusername=all&fviewBy=all&fstatus=" + _6ee + "&" + csrf;
	ajaxShowPage(tgt1, "projectcontent");
}
function getSearchMesgs(csrf, _6f3, _6f4, _6f5, _6f6, _6f7) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	ShowGenInline("ajax_load_tab");
	var tgt1 = Utils.contPath + "/fetchcomment.do?projId=" + _6f3 + "&mesgid=" + _6f4 + "&catid=" + _6f5 + "&authorName=" + _6f6 + "&istabenabled=yes&" + csrf;
	if (_6f7) {
		ajaxShowSearchPage(csrf, tgt1, "projectpagediv", "dispcomment_" + _6f7);
	} else {
		ajaxShowPage(tgt1, "projectpagediv");
	}
}
function getSearchDocs(csrf, pid, _6fb, val) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	ajaxConstructPage(Utils.contPath + "/fetchdocnotes.do?projId=" + pid + "&from=detailview&docid=" + _6fb + "&" + csrf, _6fb + ":" + val);
}
function getSearchDoc(csrf, _6fe, _6ff, _700) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	ShowGenInline("ajax_load_tab");
	modifyURL("documents/" + _6fe + "/" + _700);
	ajaxShowPage(Utils.contPath + "/fetchdocdetails.do?projId=" + _6fe + "&folderid=" + _6ff + "&docid=" + _700 + "&istabenabled=yes&" + csrf, "projectpagediv");
}
function getSearchMeets(csrf, _702, _703, val, _705, _706) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	ShowGenInline("ajax_load_tab");
	var tgt1 = Utils.contPath + "/showproject.do?projId=" + _702 + "&toview=meetings&projstat=" + _703 + "&istabenabled=yes&dispMeetType=" + _705 + "&" + csrf;
	if (_706) {
		tgt1 = tgt1 + _706;
	}
	ajaxShowSearchPage(csrf, tgt1, "projectpagediv", val);
}
function getSearchStatus(csrf, _709, _70a) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	ShowGenInline("ajax_load_tab");
	ajaxShowTab(Utils.contPath + "/showproject.do?projId=" + _709 + "&toview=overview&istabenabled=yes" + "&projstat=active&activity=status&actid=" + _70a + "&homepageact=yes&" + csrf, "projectpagediv", csrf);
}
function getSearchAnnouncement(csrf, _70c, _70d) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	ShowGenInline("ajax_load_tab");
	ajaxShowTab(Utils.contPath + "/showproject.do?projId=" + _70c + "&toview=overview&istabenabled=yes" + "&projstat=active&activity=announcement&actid=" + _70d + "&homepageact=yes&" + csrf, "projectpagediv", csrf);
}
function setActivityPage(_70e, prid, _710, _711, _712, _713, _714, _715, hp, _717, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	ShowGenInline("ajax_load_tab");
	var url = Utils.contPath + "/showactpage.do?projId=" + prid + "&act=" + _70e + "&actid=" + _711 + "&projstat=" + _712 + "&audTime=" + _715 + "&" + csrf;
	ajaxShowActivityPage(url, _70e, prid, _711, _712, _713, _714, hp, _717, csrf);
}
function statusMsgCounter() {
	var len = document.addMyStatus.mystatus.value.length;
	var val = parseInt(Utils.msgCount, 10) - parseInt(len, 10);
	var _71c = document.getElementById("msgcounter");
	if (parseInt(val, 10) < 0) {
		_71c.className = "fr pR25 dashcountred inline";
	} else {
		_71c.className = "fr pR25 dashcount inline";
	}
	_71c.innerHTML = val;
}
function askToChange(msg) {
	var msg = msg.replace("<br>", "\n");
	if (confirm(msg)) {
		return (true);
	} else {
		return (false);
	}
}
function validateBumpForm() {
	var _71e = document.getElementById("bump_status");
	var _71f = document.getElementById("proceed");
	if (document.getElementById("proceed").value == "no") {
		_71e.innerHTML = "<span class=\"error\">Please read and accept the import conditions</span>";
		return false;
	} else {
		if (document.bcimport.bcfile.value == "") {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.nofile", null,
			function(mesg) {
				_71e.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return false;
		} else {
			var _721 = document.bcimport.bcfile.value;
			if (!_721.match(/[.]xml$/i)) {
				i18n.getJSAlertValue(Utils.zuid, "zp.bump.bumpcrit", null,
				function(mesg) {
					_71e.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				return false;
			} else {
				ShowGenInline("zoho_bump_busy");
				return true;
			}
		}
	}
}
function annTitleCounter() {
	var len = document.addprojannounce.announceTitle.value.length;
	var val = parseInt(Utils.annCount, 10) - parseInt(len, 10);
	var _725 = document.getElementById("anncounter");
	if (parseInt(val, 10) < 0) {
		_725.className = "fr pR25 dashcountred inline";
	} else {
		_725.className = "fr pR25 dashcount inline";
	}
	_725.innerHTML = val;
}
function setTaskListVal(val) {
	if (val) {
		document.getElementById("tsheettype").value = "task";
		var tval = val.split("z_z_z");
		var tid = tval[0];
		var _729 = tval[1];
		if (tval[2]) {
			document.getElementById("logtask_" + tval[2]).value = tid;
		} else {
			document.getElementById("logtask").value = tid;
			document.getElementById("logissue").value = "";
		}
		if (document.getElementById("issueval")) {
			document.getElementById("issueval").setAttribute("name", "taskval");
			document.getElementById("issueval").setAttribute("id", "taskval");
		}
		document.getElementById("taskval").value = _729;
		jQuery("#logtaskbug").remove();
	}
}
function changeDisplay(_72a, _72b) {
	if (is_ie) {
		var elem = document.getElementsByTagName("div");
		for (i = 0; i < elem.length; i++) {
			att = elem[i].getAttribute("name");
			if (att == _72a) {
				elem[i].style.display = _72b;
			}
		}
	} else {
		jQuery("div [name='" + _72a + "']").css("display", _72b);
	}
}
function validateMeetNoteForm(stat, id) {
	if (stat != "add" && stat != "update") {
		return undefined;
	}
	var elem = document[stat + "MeetNotes_" + id].notes;
	if (trim(elem.value) == "") {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.meetnotesemp", null,
		function(mesg) {
			alert(mesg);
		});
		elem.focus();
		return false;
	} else {
		var _731 = (stat == "update") ? "zohobusy_upd_note_": "zohobusy_add_note_";
		ShowGenInline(_731 + id);
		return true;
	}
}
function bumpProceedStatus(Id, cobj) {
	var id = document.getElementById(Id);
	if (cobj.checked) {
		id.value = "yes";
		document.getElementById("bumpsubmit").disabled = false;
		document.getElementById("bumpsubmit").className = "buttonNew";
	} else {
		id.value = "no";
		document.getElementById("bumpsubmit").disabled = true;
		document.getElementById("bumpsubmit").className = "buttonCancel";
	}
}
function bumpNotifyStatus(Id, cobj) {
	var id = document.getElementById(Id);
	if (cobj.checked) {
		id.value = "yes";
	} else {
		id.value = "no";
	}
}
function validateCrmTicketEmailIdForm() {
	var _738 = /^[a-z0-9]([a-z0-9_\-\.\+]*)@([a-z0-9_\-\.]*)(\.[a-z]{2,4}(\.[a-z]{2}){0,2})$/i;
	var _739 = document.vaidateTicket.iscemailId;
	var _73a = document.getElementById("ticket_status");
	document.vaidateTicket.iscemailId.value = trim(document.vaidateTicket.iscemailId.value);
	if (trim(_739.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyemailid", null,
		function(mesg) {
			_73a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		ShowGenInline("ticket_status");
		_739.focus();
		return false;
	} else {
		if (!_738.test(_739.value)) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valemailid", null,
			function(mesg) {
				_73a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			ShowGenInline("ticket_status");
			_739.focus();
			return false;
		}
	}
	if (trim(document.vaidateTicket.useriscticket.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "Ticket is empty", null,
		function(mesg) {
			_73a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		ShowGenInline("ticket_status");
		document.vaidateTicket.useriscticket.focus();
		return false;
	}
	return true;
}
function addMeetingoldDate(_73e) {
	var _73f;
	var val = "";
	if (document.getElementById("schdate") && document.getElementById("ampm") && document.getElementById("schhour") && document.getElementById("schmin") && document.getElementById("schdate").value != "") {
		var _741 = document.getElementById("schdate").value;
		var _742 = document.getElementById("schhour").value;
		var _743 = document.getElementById("schmin").value;
		var amPm = document.getElementById("ampm").value;
		if (amPm == "am") {
			amPm = "AM";
		} else {
			if (amPm == "pm") {
				amPm = "PM";
			}
		}
		val = _741 + " " + _742 + ":" + _743 + " " + amPm;
	} else {
		if (document.getElementById("schdate") && document.getElementById("schhour") && document.getElementById("schmin")) {
			var _741 = document.getElementById("schdate").value;
			var _742 = document.getElementById("schhour").value;
			var _743 = document.getElementById("schmin").value;
			if (_742 == "0") {
				_743 = "01";
			}
			val = _741 + " " + _742 + ":" + _743;
		}
	}
	_73f = val;
	if (document.getElementById("schdate") && _73f != null && document.getElementById("ampm") && document.getElementById("schdate").value != "") {
		Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
		Utils.timeformat = Utils.timeformat.replace("aaa", "a");
		var _745 = compareDates(_73e, Utils.dateformat + " " + Utils.timeformat, _73f, Utils.dateformat + " " + Utils.timeformat);
		if (_745 != 0) {
			document.getElementById("recurring").style.display = "none";
			document.getElementById("schremindbefore").options[0].selected = "true";
			document.getElementById("meetalert").options[0].selected = "true";
		} else {
			document.getElementById("recurring").style.display = "inline";
		}
	} else {
		if (document.getElementById("schdate") && _73f != null && document.getElementById("schdate").value != "") {
			Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
			var _745 = compareDates(_73e, Utils.dateformat + " HH:mm", _73f, Utils.dateformat + " HH:mm");
			if (_745 != 0) {
				document.getElementById("recurring").style.display = "none";
				document.getElementById("schremindbefore").options[0].selected = "true";
				document.getElementById("meetalert").options[0].selected = "true";
			} else {
				document.getElementById("recurring").style.display = "inline";
			}
		}
	}
}
function updateMeetingoldDate(_746) {
	var _747 = document.getElementById("schdateupdate").value;
	var val;
	if (document.getElementById("schdateupdate") && document.getElementById("ampm") && document.getElementById("schhour") && document.getElementById("schmin") && document.getElementById("schdateupdate").value != "") {
		var _749 = document.getElementById("schdateupdate").value;
		var _74a = document.getElementById("schhour").value;
		var _74b = document.getElementById("schmin").value;
		var amPm = document.getElementById("ampm").value;
		if (amPm == "am") {
			amPm = "AM";
		} else {
			if (amPm == "pm") {
				amPm = "PM";
			}
		}
		val = _749 + " " + _74a + ":" + _74b + " " + amPm;
	} else {
		if (document.getElementById("schdateupdate") && document.getElementById("schhour") && document.getElementById("schmin")) {
			var _749 = document.getElementById("schdateupdate").value;
			var _74a = document.getElementById("schhour").value;
			var _74b = document.getElementById("schmin").value;
			if (_74a == "0") {
				_74b = "01";
			}
			val = _749 + " " + _74a + ":" + _74b;
		}
	}
	selDate = val;
	if (document.getElementById("schdateupdate") && selDate != null && document.getElementById("ampm") && document.getElementById("schdateupdate").value != "") {
		Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
		Utils.timeformat = Utils.timeformat.replace("aaa", "a");
		var _74d = compareDates(_746, Utils.dateformat + " " + Utils.timeformat, selDate, Utils.dateformat + " " + Utils.timeformat);
		if (_74d != 0) {
			document.getElementById("recurring").style.display = "none";
			document.getElementById("schremindbefore").options[0].selected = "true";
			document.getElementById("meetalert").options[0].selected = "true";
		} else {
			document.getElementById("recurring").style.display = "inline";
		}
	} else {
		if (document.getElementById("schdateupdate") && selDate != null && document.getElementById("schdateupdate").value != "") {
			Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
			var _74d = compareDates(_746, Utils.dateformat + " HH:mm", selDate, Utils.dateformat + " HH:mm");
			if (_74d != 0) {
				document.getElementById("recurring").style.display = "none";
				document.getElementById("schremindbefore").options[0].selected = "true";
				document.getElementById("meetalert").options[0].selected = "true";
			} else {
				document.getElementById("recurring").style.display = "inline";
			}
		}
	}
}
function getCSRFEncode(csrf) {
	if (typeof(csrf) == "undefined") {
		return false;
	} else {
		var _74f = csrf.split("=");
		return _74f[0] + "=" + encodeURIComponent(_74f[1]);
	}
}
function validateAddTaskForm(id, _751) {
	var _752 = document.getElementById("todotask_" + id);
	var _753 = document.getElementById("ttaskdate_" + id).value;
	var _754 = document.getElementById("taskenddate_" + id).value;
	Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
	var _755 = compareDates(_753, Utils.dateformat, _754, Utils.dateformat);
	if (trim(_752.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.pletask", null,
		function(mesg) {
			alert(mesg);
		});
		_752.focus();
		return (false);
	} else {
		if (findScriptTags(_752.value)) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvaltask", null,
			function(mesg) {
				alert(mesg);
			});
			_752.focus();
			return (false);
		} else {
			if (_753 != "" && _754 != "" && _755 != 0 && _755 != -1) {
				i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
				function(mesg) {
					alert(mesg);
				});
				document.getElementById("taskenddate_" + id).focus();
				return false;
			} else {
				if (document.getElementById("gcalevnt")) {
					if (document.getElementById("gcalevnt").checked) {
						if (_753 == "" || (getDateFromFormat(_753, Utils.dateformat) == "0")) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.gappsalert", null,
							function(mesg) {
								alert(mesg);
							});
							return false;
						} else {
							return true;
						}
					} else {
						return true;
					}
				} else {
					return true;
				}
			}
		}
	}
}
function changeSubLink(_75a, _75b) {
	if (document.getElementById(_75a) && document.getElementById(_75b)) {
		var i;
		var j;
		if (is_ie) {
			showHideElements_iefix("div", "subLinkName", "mR20");
		} else {
			var _75e = document.getElementsByName("subLinkName");
			for (i = 0; i < _75e.length; i++) {
				_75e[i].className = "mR20";
			}
		}
		document.getElementById(_75a).className = "mR20 bld";
		if (is_ie) {
			showHideElements_iefix("div", "subImgName", "hide");
		} else {
			var _75f = document.getElementsByName("subImgName");
			for (j = 0; j < _75f.length; j++) {
				_75f[j].className = "hide";
			}
		}
		document.getElementById(_75b).className = "block";
	}
}
function reOrderList(_760) {
	Sortable.create(_760, {
		tag: "tr",
		constraint: false
	});
	ShowHideInline("saveorder_span", "reorder_span");
	document.getElementById(_760).className = "reorder ulstyle";
	expandCollapseDiv("actnone", "inline", "div");
	expandCollapseDiv("actdiv", "hide", "div");
}
function reOrderMstone(_761) {
	Sortable.create(_761, {
		tag: "tr",
		constraint: false
	});
	document.getElementById(_761).className = "reorder ulstyle";
}
function saveTListOrder(_762, _763, mid, _765, _766) {
	ShowHideInline("reorder_span", "saveorder_span");
	var url = Utils.contPath + "/savetodolistorder.do?mid=" + mid + "&projId=" + _763 + "&sort_order=" + Sortable.serialize(_762) + "&" + _765 + "=" + encodeURIComponent(_766);
	ajaxSaveOrder(url);
	document.getElementById(_762).className = "ulstyle";
	Sortable.destroy(_762);
	expandCollapseDiv("actnone", "hide", "div");
	expandCollapseDiv("actdiv", "inline", "div");
}
function reOrderTemTask(_768) {
	var _769 = "reorder_separator_" + _768;
	var _76a = "additem_separator_" + _768;
	var _768 = "ul_ttask_" + _768;
	Sortable.create(_768, {
		tag: "tr",
		constraint: false
	});
	ShowHideInline(_769, _76a);
	document.getElementById(_768).className = "reorder ulstyle";
}
function showPortalWindowForBiz() {
	if (is_ie && !is_opera) {
		iframeIEHack = document.createElement("IFRAME");
		iframeIEHack.id = "iframeIEHack";
		iframeIEHack.scrolling = "no";
		iframeIEHack.frameBorder = 0;
		iframeIEHack.style.position = "absolute";
		iframeIEHack.style.zIndex = "90";
		iframeIEHack.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)";
	}
	document.getElementById("addownportaldiv").style.display = "block";
	document.getElementById("addownportaldiv").style.zIndex = "98";
	var top = document.getElementById("addownportaldiv").style.top = findPosY(document.getElementById("myportalid")) + 35;
	var left = document.getElementById("addownportaldiv").style.left = findPosX(document.getElementById("myportalid")) + document.getElementById("myportalid").offsetWidth - document.getElementById("addownportaldiv").offsetWidth + 600;
	Dig = document.getElementById("addownportaldiv");
	if (is_ie && !is_opera) {
		iframeIEHack.style.width = Dig.offsetWidth + "px";
		iframeIEHack.style.height = Dig.offsetHeight + "px";
		iframeIEHack.style.top = top + "px";
		iframeIEHack.style.left = left + "px";
		document.body.appendChild(iframeIEHack);
	}
}
function isValidURL(url) {
	var regX = /^(http|https)\:\/\/\w+([\.\-]\w+)*\.\w{2,4}(\:\d+)*([\/\.\-\?\&\%\#]\w+)*\/?$/;
	if (regX.test(url)) {
		return true;
	} else {
		return false;
	}
}
function validateExportGTask() {
	var _76f = false;
	var _770 = eval("document.addGtask.seltasklist");
	if (_770.length) {
		for (var i = 0; i < _770.length; i++) {
			if (_770[i].checked) {
				_76f = true;
			}
		}
	} else {
		if (_770.checked) {
			_76f = true;
		}
	}
	if (!_76f) {
		i18n.getJSAlertValue(Utils.zuid, "zp.task.specifytoexport", null,
		function(mesg) {
			alert(mesg);
		});
		return false;
	}
	return _76f;
}
function selecttoSync(_773) {
	var _774 = document.getElementById(_773);
	var _775 = document.getElementById("check_" + _773);
	if (_774) {
		if (_775.checked) {
			_774.className = "fl pt5 pb5 pl3 ctaskDescSmall";
		} else {
			_774.className = "fl pt5 pb5 pl3 txtSmall";
		}
	}
}
function validateSyncGTask() {
	var _776 = false;
	var _777 = eval("document.syncGtask.syncgtasks");
	if (_777.length) {
		for (var i = 0; i < _777.length; i++) {
			if (_777[i].checked) {
				_776 = true;
			}
		}
	} else {
		if (_777.checked) {
			_776 = true;
		}
	}
	if (!_776) {
		i18n.getJSAlertValue(Utils.zuid, "zp.task.specifytosync", null,
		function(mesg) {
			alert(mesg);
		});
		return false;
	}
	return _776;
}
function validateTaskDetForm(_77a) {
	if ("all" == _77a) {
		var _77b = "updateTodoTask";
		var _77c = "zoho_update_ttask_busy";
		var alId = "ttask_update_status";
		var _77e = eval("document." + _77b + ".duroption.value");
		var _77f = eval("document." + _77b + ".taskdate.value");
		var _780 = eval("document." + _77b + ".taskenddate.value");
		Utils.timeformat = Utils.timeformat.replace("aaa", "a");
		Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
		var _781 = Utils.dateformat;
		if (Utils.taskinhr == "true" || ((!isDIHrs(_77f)) || (!isDIHrs(_780)))) {
			_781 = Utils.dateformat + " " + Utils.timeformat;
		}
		if (getDateFromFormat(_77f, _781) == "0") {
			_77f = "";
			eval("document." + _77b + ".taskdate").value = "";
		}
		if (getDateFromFormat(_780, _781) == "0") {
			_780 = "";
			eval("document." + _77b + ".taskenddate").value = "";
		}
		var _782 = compareDates(_77f, _781, _780, _781);
		var _783 = document.getElementById(alId);
		eval("document." + _77b + ".edittasksubmit.disabled=true");
		if (trim(eval("document." + _77b + ".taskdate.value")).length == 0 && trim(eval("document." + _77b + ".taskenddate.value")).length != 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valstartdate", null,
			function(mesg) {
				_783.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			eval("document." + _77b + ".taskdate.focus()");
			eval("document." + _77b + ".edittasksubmit.disabled=false");
			return false;
		} else {
			if (_77e == "enddate" && _77f != "" && _780 != "" && _782 != 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
				function(mesg) {
					_783.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _77b + ".taskenddate.focus()");
				eval("document." + _77b + ".edittasksubmit.disabled=false");
				return false;
			} else {
				ShowGenInline(_77c);
				return true;
			}
		}
	}
}
String.prototype.replaceCall = function(_786, _787) {
	var temp = this;
	var _789 = temp.indexOf(_786);
	while (_789 != -1) {
		temp = temp.replace(_786, _787);
		_789 = temp.indexOf(_786);
	}
	return temp;
};
function modImgHeightWeight(id) {
	var _78b = Utils.editorObj[id];
	var _78c = _78b.doc.body;
	var _img = _78c.getElementsByTagName("img");
	var _78e = 678;
	var _78f = 400;
	var _790 = window.location.protocol + "//" + window.location.host;
	for (var a = 0; a < _img.length; a++) {
		if (_img[a].clientHeight > _78f) {
			_img[a].style.height = _78f;
		}
		if (_img[a].clientWidth > _78e) {
			_img[a].style.width = _78e;
		}
	}
	var _792 = _78b.getContent();
	_792 = _792.replaceCall("src=\"/viewInlineAttach", "src=\"" + _790 + "/viewInlineAttach");
	return _792;
}
function hideIfShow(_793) {
	var Obj = document.getElementById(_793);
	if (Obj) {
		Hide(_793);
	}
}
function validateRemoteFileForm(msg, _796) {
	var _797 = document.getElementById("remote_file_open_" + _796);
	var _798 = "!@#$%^&*()+=-[]\\';,./{}|\":<>?";
	var _799 = trim(document.newRemoteFileForm.filename.value);
	if (_799.length == 0) {
		if (_796 == "xls") {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.spysheetname", null,
			function(mesg) {
				_797.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
		} else {
			if (_796 == "ppt") {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.spypptname", null,
				function(mesg) {
					_797.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
			} else {
				if (_796 == "doc") {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.spydocname", null,
					function(mesg) {
						_797.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
				}
			}
		}
		document.newRemoteFileForm.filename.focus();
		return false;
	}
	for (var i = 0; i < _799.length; i++) {
		if (_798.indexOf(_799.charAt(i)) != -1) {
			if (_796 == "xls") {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.sheetnamevalidation", null,
				function(mesg) {
					_797.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
			} else {
				if (_796 == "ppt") {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.pptnamevalidation", null,
					function(mesg) {
						_797.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
				} else {
					if (_796 == "doc") {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.docnamevalidation", null,
						function(mesg) {
							_797.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
					}
				}
			}
			document.newRemoteFileForm.filename.focus();
			return false;
		}
	}
	return true;
}
function SetDivTopLeft(_7a1, _7a2, _7a3, _7a4) {
	var _7a5 = jQuery("#" + _7a2).offset();
	var _7a6 = document.getElementById(_7a1);
	var _7a7 = _7a5.left;
	var _7a8 = _7a5.top;
	_7a8 += _7a4;
	_7a7 += _7a3;
	_7a6.style.left = _7a7 + "px";
	_7a6.style.top = _7a8 + "px";
	_7a6.style.display = "block";
}
function selectDocs() {
	var _7a9 = document.getElementById("itemSelected");
	var _7aa = document.getElementById("header_checkbox");
	var _7ab = 0;
	var _7ac = new Array();
	var btn = "doccheckbox";
	var _7ae = document.getElementsByName("doccheckbox");
	var _7af = _7ae.length;
	for (var i = 0,
	j = 0; i < _7af; i++) {
		if (_7ae[i].checked) {
			if (!selDocCheck) {
				Effect.SlideDown("zdocactionslide");
			}
			selDocCheck = true;
			var _7b2 = new String(_7ae[i].id);
			var dd = _7b2.split("_");
			_7ac[j++] = dd[1];
		}
	}
	bulk_issue_id = _7ac;
	_7ab = _7ac.length;
	_7a9.innerHTML = _7ab;
	if (_7ab < _7af) {
		_7aa.checked = false;
	}
	if (_7ab == _7af) {
		_7aa.checked = true;
	}
	if (_7ab == 0) {
		selDocCheck = false;
		Effect.SlideUp("zdocactionslide");
	}
}
function selectAllDocs() {
	var _7b4 = document.getElementsByName("doccheckbox");
	var _7b5 = document.getElementById("header_checkbox");
	var _7b6 = document.getElementById("itemSelected");
	var _7b7 = 0;
	if (_7b5.checked == true) {
		for (var i = 0; i < _7b4.length; i++) {
			var div = document.getElementById(_7b4[i].id);
			if (!div.disabled) {
				div.checked = true;
				var _7ba = new String(_7b4[i].id);
				var dd = _7ba.split("_");
				bulk_issue_id[_7b7] = dd[1];
				_7b7 = _7b7 + 1;
			}
		}
		selDocCheck = true;
		_7b6.innerHTML = _7b7;
	} else {
		for (var i = 0; i < _7b4.length; i++) {
			var div = document.getElementById(_7b4[i].id);
			div.checked = false;
		}
		selDocCheck = false;
		_7b6.innerHTML = _7b7;
		Effect.SlideUp("zdocactionslide");
	}
}
function dispNone(_7bc) {
	var id = document.getElementById(_7bc);
	if (id) {
		id.style.display = "none";
	}
}
function dispInline(_7be) {
	var id = document.getElementById(_7be);
	if (id) {
		id.style.display = "inline";
	}
}
function dispBlock(_7c0) {
	var id = document.getElementById(_7c0);
	if (id) {
		id.style.display = "block";
	}
}
function isValDur(v) {
	v = v.replace(/(^\s*|\s*$)/g, "");
	var _7c3 = /^\d*[\:\.]{0,1}\d*$/;
	var _7c4 = /^[\:\.]$/;
	if (!_7c3.test(v) || _7c4.test(v)) {
		return false;
	}
	return true;
}
function isDIHrs(d) {
	if (d.indexOf(":") != -1) {
		return false;
	}
	return true;
}
function changeImgSrc(type) {
	var _7c7 = document.getElementById("headerImage");
	if (type == "acctsel") {
		_7c7.src = "/images/select-folder-blue.png";
	} else {
		if (type == "mappingdone") {
			_7c7.src = "/images/sync-projects-blue.png";
		} else {
			_7c7.src = "/images/select-folder-blue.png";
		}
	}
}
function changeIdClassName(_7c8, _7c9, id) {
	var _7cb = document.getElementsByClassName(_7c8);
	for (var i = 0; i < _7cb.length; i++) {
		document.getElementById(_7cb[i].id).className = _7c9;
	}
	document.getElementById(id).className = _7c8;
}
var bugreportviwrange = 25;
function getHTTPObject() {
	var _7cd;
	if (window.XMLHttpRequest) {
		_7cd = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			try {
				_7cd = new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e) {
				_7cd = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
	}
	return _7cd;
}
function fixForCachingInIe(_7ce) {
	return _7ce;
}
function getURLArgs(_7cf) {
	var _7d0 = _7cf.indexOf("?");
	var args;
	var url;
	if (_7d0 == "-1") {
		url = _7cf;
		args = "";
	} else {
		url = _7cf.substring(0, _7cf.indexOf("?"));
		args = _7cf.substring(_7cf.indexOf("?") + 1, _7cf.length);
	}
	var _7d3 = {
		url: url,
		args: args
	};
	return _7d3;
}
function ajaxRefreshMenu(url, _7d5) {
	var _7d6 = fixForCachingInIe(url);
	var _7d7 = getURLArgs(_7d6);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _7d7.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _7da = document.getElementById("activeprojdiv");
				_7da.innerHTML = text;
				ini = false;
			}
		};
		http.send(_7d7.args);
	}
}
function ajaxSaveOrder(url, _7dc) {
	var _7dd = fixForCachingInIe(url);
	var _7de = getURLArgs(_7dd);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _7de.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				if (url.indexOf("savebugpositionorder.do") != -1) {
					if (url.indexOf("value=severity") != -1) {
						var text = http.responseText;
						var _7e1 = document.getElementById("reorderseveritysucmsg");
						if (text.indexOf("||Error occurred") != -1) {
							var a = text.split("||");
							var _7e3 = document.getElementById("reorderseveritysucmsg");
							_7e3.innerHTML = "<span class=\"error\">" + a[2] + "</span>";
						} else {
							_7e1.innerHTML = text;
							pmevalScript(_7e1);
							i18n.getJSAlertValue(Utils.zuid, "zp.bugajaxjs.fieldsorderupdated", null,
							function(mesg) {
								mesg = mesg.replace("{0}", Utils.severityValue);
								displayBugMsg1(mesg, "reorderseveritysucmsg");
							});
						}
					} else {
						if (url.indexOf("value=module") != -1) {
							var text = http.responseText;
							var _7e1 = document.getElementById("reordermodulesucmsg");
							if (text.indexOf("||Error occurred") != -1) {
								var a = text.split("||");
								var _7e3 = document.getElementById("reordermodulesucmsg");
								_7e3.innerHTML = "<span class=\"error\">" + a[2] + "</span>";
							} else {
								_7e1.innerHTML = text;
								pmevalScript(_7e1);
								i18n.getJSAlertValue(Utils.zuid, "zp.bugajaxjs.fieldsorderupdated", null,
								function(mesg) {
									mesg = mesg.replace("{0}", Utils.moduleValue);
									displayBugMsg1(mesg, "reordermodulesucmsg");
								});
							}
						} else {
							if (url.indexOf("value=type") != -1) {
								var text = http.responseText;
								var _7e1 = document.getElementById("reorderclasssucmsg");
								if (text.indexOf("||Error occurred") != -1) {
									var a = text.split("||");
									var _7e3 = document.getElementById("reorderclasssucmsg");
									_7e3.innerHTML = "<span class=\"error\">" + a[2] + "</span>";
								} else {
									_7e1.innerHTML = text;
									pmevalScript(_7e1);
									i18n.getJSAlertValue(Utils.zuid, "zp.bugajaxjs.fieldsorderupdated", null,
									function(mesg) {
										mesg = mesg.replace("{0}", Utils.classificationValue);
										displayBugMsg1(mesg, "reorderclasssucmsg");
									});
								}
							} else {
								if (url.indexOf("value=priority") != -1) {
									var text = http.responseText;
									var _7e1 = document.getElementById("reorderisitsucmsg");
									if (text.indexOf("||Error occurred") != -1) {
										var a = text.split("||");
										var _7e3 = document.getElementById("reorderisitsucmsg");
										_7e3.innerHTML = "<span class=\"error\">" + a[2] + "</span>";
									} else {
										_7e1.innerHTML = text;
										pmevalScript(_7e1);
										i18n.getJSAlertValue(Utils.zuid, "zp.bugajaxjs.fieldsorderupdated", null,
										function(mesg) {
											mesg = mesg.replace("{0}", Utils.isItReproducibleValue);
											displayBugMsg1(mesg, "reorderisitsucmsg");
										});
									}
								}
							}
						}
					}
				}
				if (url.indexOf("savettaskorder.do") != -1) {
					var text = http.responseText;
					var _7e1 = document.getElementById(_7dc);
					if (is_ie) {
						jQuery("#" + _7dc).replaceWith("<tbody id=\"" + _7dc + "\">" + text + "</tbody>");
						bindMoT(_7dc);
					} else {
						_7e1.innerHTML = text;
					}
					new Effect.ScrollTo(_7dc, {
						duration: 0.1
					});
					ini = false;
					pmevalScript(_7e1);
				}
				if (url.indexOf("savetasktemporder.do") != -1) {
					var text = http.responseText;
					var _7e1 = document.getElementById(_7dc);
					if (is_ie) {
						jQuery("#" + _7dc).replaceWith("<tbody id=\"" + _7dc + "\">" + text + "</tbody>");
						bindMoT(_7dc);
					} else {
						_7e1.innerHTML = text;
					}
					ini = false;
					pmevalScript(_7e1);
				}
				if (url.indexOf("savemstoneorder.do") != -1) {
					if (_7dc.indexOf("upcoming") != -1) {
						Hide("upcoming");
					} else {
						if (_7dc.indexOf("delayed") != -1) {
							Hide("delayed");
						}
					}
					var text = http.responseText;
					var _7e1 = document.getElementById(_7dc);
					if (is_ie) {
						jQuery("#" + _7dc).replaceWith("<tbody id=\"" + _7dc + "\">" + text + "</tbody>");
						bindMoT(_7dc);
					} else {
						_7e1.innerHTML = text;
					}
					new Effect.ScrollTo(_7dc, {
						duration: 0.1
					});
					ini = false;
					pmevalScript(_7e1);
				}
			}
		};
		http.send(_7de.args);
	}
}
function ajaxGenSaveOrder(url, url1, id) {
	var _7eb = fixForCachingInIe(url);
	var _7ec = getURLArgs(_7eb);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _7ec.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				ajaxShowPage(url1, id);
				ShowHideInline("mact_div", "msaveorder_div");
			}
		};
		http.send(_7ec.args);
	}
}
function ajaxSaveProjectOrder(url) {
	var _7ef = fixForCachingInIe(url);
	var _7f0 = getURLArgs(_7ef);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _7f0.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				if (url.indexOf("saveprojorder.do") != -1) {
					Hide("ajax_load_tab");
					ShowGenInline("seq_upd_response");
					setTimeout(function() {
						Hide("seq_upd_response");
					},
					3000);
				}
			}
		};
		http.send(_7f0.args);
	}
}
function ajaxShowTab(url, _7f3, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	if (url.indexOf("showproject.do") != -1 && url.indexOf("toview=chat") != -1) {
		url = url + "&sid=" + WebMessanger.getSid();
	}
	if (url.indexOf("/compmilestone.do") != -1) {
		if (url.indexOf("status=1") != -1) {
			dispMesg = "zp.todoms.milereopen";
		} else {
			dispMesg = "zp.todoms.mileclosed";
		}
	}
	var _7f5 = fixForCachingInIe(url);
	var _7f6 = getURLArgs(_7f5);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _7f6.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _7f9 = document.getElementById(_7f3);
				if (url.indexOf("showproject.do") != -1 && url.indexOf("toview=users") != -1) {
					if (document.getElementById("gapps_autofill_user")) {
						ajaxAutoFill(Utils.contPath + "/autofilluser.do");
					}
				}
				if (url.indexOf("moreprojects.do") != -1) {
					jQuery("#projlisting").append("<tbody>" + text + "</tbody>");
					Hide("ajax_load_tab");
					return false;
				}
				if (url.indexOf("fetchreordermilestone.do") != -1) {
					_7f9.innerHTML = text;
					ini = false;
					pmevalScript(_7f9);
					reOrderMstone("msreorder_ids");
					ShowHideInline("msaveorder_div", "mact_div");
					Hide("ajax_load_tab");
					return false;
				}
				if (url.indexOf("addtopmilestone.do") != -1) {
					if (document.getElementById("todolist_div")) {
						ShowHideBlock(_7f3, "todolist_div");
					}
				}
				if (url.indexOf("nextcomptasklist.do") != -1) {
					_7f9.innerHTML = _7f9.innerHTML + text;
					ini = false;
					pmevalScript(_7f9);
				}
				if (url.indexOf("compmilestone.do") != -1) {
					if (text.indexOf("zzzmstypezzz||upcoming") != -1) {
						_7f3 = "ul_mile_upcoming";
						if (url.indexOf("status=1") != -1) {
							Hide("upcoming");
						} else {
							Hide("completed");
						}
					} else {
						if (url.indexOf("status=1") != -1) {
							Hide("delayed");
						} else {
							Hide("completed");
						}
					}
					_7f9 = document.getElementById(_7f3);
					if (text.indexOf("isNotValidCompleDate ||") != -1) {
						i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.duedatecantst", null,
						function(mesg) {
							alert(mesg);
						});
						return false;
					}
					jQuery("#" + _7f3).empty();
					if (is_ie) {
						jQuery("#" + _7f3).replaceWith("<tbody id=\"" + _7f3 + "\">" + text + "</tbody>");
					} else {
						if (_7f9) {
							_7f9.innerHTML = text;
						}
					}
					bindMoT(_7f3);
					ini = false;
					pmevalScript(_7f9);
					i18n.getJSAlertValue(Utils.zuid, dispMesg, null,
					function(mesg) {
						displayFadeMsg(mesg);
					});
					return false;
				}
				if (url.indexOf("archivemile.do") != -1) {
					if (url.indexOf("archflag=archive") != -1) {
						Hide("archived");
					} else {
						Hide("completed");
					}
					if (is_ie) {
						jQuery("#" + _7f3).replaceWith("<tbody id=\"" + _7f3 + "\">" + text + "</tbody>");
					} else {
						_7f9.innerHTML = text;
					}
					bindMoT(_7f3);
					ini = false;
					pmevalScript(_7f9);
					i18n.getJSAlertValue(Utils.zuid, "zp.todoms.milearchived", null,
					function(mesg) {
						displayFadeMsg(mesg);
					});
				}
				if (url.indexOf("fetchtasklist.do") != -1) {
					if (is_ie) {
						jQuery("#" + _7f3).replaceWith("<tbody id=\"" + _7f3 + "\">" + text + "</tbody>");
					} else {
						if (_7f9) {
							_7f9.innerHTML = text;
						}
					}
					ini = false;
					pmevalScript(_7f9);
				}
				if (url.indexOf("updateaction.do") != -1) {
					if (text.indexOf("isParentClosed ||") != -1) {
						var msg = text.split("||");
						var _7fe = msg[1];
						dependentAlertMsg(_7fe);
						return null;
					} else {
						if (text.indexOf("isChildOpened ||") != -1) {
							var msg = text.split("||");
							var _7fe = msg[1];
							dependentAlertMsg(_7fe);
							return null;
						} else {
							if (text.indexOf("subtask can not reopened ||") != -1) {
								i18n.getJSAlertValue(Utils.zuid, "zp.subtask.reopened", null,
								function(mesg) {
									displayFadeMsg(mesg);
								});
								return null;
							} else {
								if (text.indexOf("set valid Date") != -1) {
									var a = text.split("||");
									Hide("zoho_update_ttask_busy");
									alert(a[1]);
									return null;
								} else {
									var _801 = null;
									if (text.indexOf("zzzupdatetaskzzz||") != -1) {
										var id = text.split("||");
										var _803 = "task_" + id[1];
										jQuery("#taskaction_" + id[1]).remove();
										if (url.indexOf("operation=status") != -1 && url.indexOf("value=2") != -1) {
											replacePrevTask(id[1]);
										}
										if (url.indexOf("operation=status") != -1 && url.indexOf("value=1") != -1) {
											var _804 = id[3];
											if (_804 != null && _804 != "0" && _804 != "") {
												jQuery("#task_" + id[1]).remove();
												var _805 = jQuery("[task_" + _804 + "=" + _804 + "]");
												if (_805 != null && _805.length > 0) {
													var _806 = _805[_805.length - 1];
													jQuery(_806).after("<tr>" + text + "</tr>");
												} else {
													jQuery("#taskaction_" + _804).after("<tr>" + text + "</tr>");
												}
											}
										} else {
											jQuery("#" + _803).replaceWith("<tr>" + text + "</tr>");
										}
										_801 = _803;
										var _807 = document.getElementById(_803);
										pmevalScript(_807);
									} else {
										if (text.indexOf("zzzbulkupdatetaskzzz||") != -1) {
											if (is_ie) {
												jQuery("#" + _7f3).replaceWith("<tbody id=\"" + _7f3 + "\">" + text + "</tbody>");
												bindMoT(_7f3);
											} else {
												_7f9.innerHTML = text;
											}
											pmevalScript(_7f9);
										} else {
											if (text.indexOf("zzzrecurrencetaskzzz||") != -1) {
												var id = text.split("||");
												var _808 = "task_" + id[1];
												var _809 = "task_" + id[2];
												jQuery("#" + _808).remove();
												jQuery("#taskaction_" + _808).remove();
												jQuery("#" + _7f3).append("<tr>" + text + "</tr>");
												bindMoT(_809);
												_801 = _809;
											} else {
												if (is_ie) {
													jQuery("#" + _7f3).replaceWith("<tbody id=\"" + _7f3 + "\">" + " " + "</tbody>");
													bindMoT(_7f3);
												} else {
													_7f9.innerHTML = " ";
												}
											}
										}
									}
									if (_801) {
										if (document.getElementById(_801)) {
											document.getElementById(_801).style.backgroundColor = "#ffffcc";
											setTimeout(function() {
												document.getElementById(_801).style.backgroundColor = "#FFFFFF";
											},
											3000);
											new Effect.ScrollTo(_801, {
												duration: 0.1
											});
										}
									}
									i18n.getJSAlertValue(Utils.zuid, "zp.general.taskupdatedsuccessfully", null,
									function(mesg) {
										displayFadeMsg(mesg);
									});
									return false;
								}
							}
						}
					}
					ini = false;
					pmevalScript(_7f9);
					i18n.getJSAlertValue(Utils.zuid, "zp.general.taskupdatedsuccessfully", null,
					function(mesg) {
						displayFadeMsg(mesg);
					});
					if (document.getElementById("gTaskId") && document.getElementById("csrftoken")) {
						var _80c = document.getElementById("gTaskId").innerHTML;
						var _80d = document.getElementById("csrftoken").innerHTML;
						if (_80c != null) {
							var _80e = _80c.split("||");
							var _80f = _80e[1].split("#");
							ajaxSendRequest(Utils.contPath + "/gtaskaction.do?taskidlist=" + _80f[0] + "&taskaction=" + _80f[1] + "&tlistid=" + _80f[2] + "&" + _80d);
						}
					}
				}
				if (url.indexOf("addcalendartask.do") != -1) {
					if (is_ie) {
						jQuery("#" + _7f3).replaceWith("<tbody id=\"" + _7f3 + "\">" + text + "</tbody>");
					} else {
						_7f9.innerHTML = text;
					}
					ini = false;
					pmevalScript(_7f9);
					if (text.indexOf("zzztoptodozzz||") != -1) {
						var a = text.split("||");
						var hid = "task_" + a[1];
						if (document.getElementById(hid)) {
							document.getElementById(hid).style.backgroundColor = "#ffffcc";
							setTimeout(function() {
								document.getElementById(hid).style.backgroundColor = "#FFFFFF";
							},
							3000);
						} else {
							i18n.getJSAlertValue(Utils.zuid, "zp.projcal.taskaddsucc", null,
							function(mesg) {
								displayFadeMsg(mesg);
							});
						}
					} else {
						i18n.getJSAlertValue(Utils.zuid, "zp.projcal.taskaddsucc", null,
						function(mesg) {
							displayFadeMsg(mesg);
						});
					}
					var Id = _7f3.split("_")[2];
					if (document.getElementById("todotask_" + Id)) {
						document.getElementById("todotask_" + Id).value = "";
						addtaskowner = "";
						i18n.getJSAlertValue(Utils.zuid, "zp.general.anyuser", null,
						function(mesg) {
							document.getElementById("personres_" + Id).innerHTML = mesg;
						});
						document.getElementById("todotask_" + Id).focus();
						new Effect.ScrollTo("todotask_" + Id, {
							duration: 0.1
						});
					}
				}
				if (url.indexOf("showproject.do") != -1 && url.indexOf("istabenabled=yes") != -1 && url.indexOf("homepageact=yes") != -1) {
					_7f9.innerHTML = text;
					ini = false;
					pmevalScript(_7f9);
					var args = _7f6.args;
					var _816 = args.indexOf("projId=");
					var _817 = args.length;
					if (args.indexOf("&", _816) != -1) {
						_817 = args.indexOf("&", _816);
					}
					var prid = args.substring(_816 + 7, _817);
					_816 = args.indexOf("activity=");
					_817 = args.length;
					if (args.indexOf("&", _816) != -1) {
						_817 = args.indexOf("&", _816);
					}
					var _819 = args.substring(_816 + 9, _817);
					_816 = args.indexOf("actid=");
					_817 = args.length;
					if (args.indexOf("&", _816) != -1) {
						_817 = args.indexOf("&", _816);
					}
					var _81a = args.substring(_816 + 6, _817);
					if (_819 == "announcement") {
						ShowGenInline("zoho_activity_busy");
						ajaxShowPage(Utils.contPath + "/fetchprojannouncement.do?projId=" + prid + "&sindex=1&" + csrf, "projannouncement:" + _81a);
						return;
					}
					if (_819 == "status comment") {
						_81a = "sts_cmt_" + _81a;
					}
					Hide("ajax_load_tab");
					ShowHideBlock("userStat", "projAct");
					ajaxShowSearchPage(csrf, Utils.contPath + "/fetchuserstatus.do?projId=" + prid + "&sindex=1&" + csrf, "userstatus", _81a);
					return;
				}
				if (url.indexOf("updatetask.do") != -1) {
					if (text.indexOf("isParentClosed ||") != -1) {
						i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.closemsg", null,
						function(mesg) {
							alert(mesg);
						});
					} else {
						if (text.indexOf("isChildOpened ||") != -1) {
							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.openmsg", null,
							function(mesg) {
								alert(mesg);
							});
						} else {
							if (text.indexOf("isNotValidDueDate ||") != -1) {
								i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.duedatecantst", null,
								function(mesg) {
									alert(mesg);
								});
							} else {
								jQuery("#" + _7f3).replaceWith("<tr>" + text + "</tr>");
								ini = false;
								pmevalScript(_7f9);
								i18n.getJSAlertValue(Utils.zuid, "zp.general.taskupdatedsuccessfully", null,
								function(mesg) {
									displayFadeMsg(mesg);
								});
							}
						}
					}
					return;
				}
				if (url.indexOf("taskdetailsmaps.do") != -1 || url.indexOf("attachnewdoc.do") != -1) {
					var _81f = "docmaplink_" + _7f3.split("_")[1];
					if (url.indexOf("taskdetailsmaps.do?maptype=forum") != -1) {
						_81f = "forummaplink_" + _7f3.split("_")[1];
					}
					var pos = jQuery("#" + _81f).offset();
					var _821 = pos.left + 50;
					var _822 = pos.top - 30;
					if (url.indexOf("attachnewdoc.do") != -1) {
						_822 = pos.top - 70;
					}
					jQuery("#" + _7f3).css({
						"left": _821 + "px",
						"top": _822 + "px"
					});
					_7f9.innerHTML = text;
					scrollInViewPart(_7f3);
				}
				if (url.indexOf("deleteuser.do") != -1) {
					Hide("customloadingdiv");
				}
				if (url.indexOf("getprojname.do") != -1) {
					ShowHideInline("projlist", "switchproj");
					ShowHideInline("switchprojicon", "loadingprojlist");
				}
				if (url.indexOf("deleteproject.do") != -1) {
					Hide("customloadingdiv");
					Hide("ajax_load_tab");
				} else {
					Hide("ajax_load_tab");
				}
				if (url.indexOf("deleteproject.do") != -1 && text.indexOf("Permission Denied") != -1) {} else {
					if (url.indexOf("fetchreorderlist.do") != -1) {
						_7f9.innerHTML = text;
						ini = false;
						pmevalScript(_7f9);
						reOrderList("tlreorder_id");
						ShowHideInline("msaveorder_div", "mact_div");
					} else {
						if (url.indexOf("tasklistview.do?filterview=false") != -1) {
							Hide(_7f3);
							jQuery("#common_table").append("<tbody>" + text + "</tbody>");
						} else {
							if (url.indexOf("fetchprojectusers.do") != -1) {
								var user = jQuery("#" + _7f3);
								jQuery(user).replaceWith(text);
							} else {
								if (url.indexOf("dashboardcharts.do") != -1) {
									_7f9.style.height = "1000px";
									_7f9.innerHTML = text;
									setTimeout(function() {
										_7f9.style.height = "";
									},
									10);
								} else {
									_7f9.innerHTML = text;
									ini = false;
									pmevalScript(_7f9);
									if (url.indexOf("deletecomment.do") != -1) {
										loadZEditor("comeditordiv", "");
									} else {
										if (url.indexOf("addtaskaction.do") != -1) {
											var _824 = "addTaskNotes_" + _7f3.split("_")[1];
											eval("document." + _824 + ".notes.focus()");
										} else {
											if (url.indexOf("showchangeset.do") != -1) {
												window.scroll(0, 0);
											} else {
												if (url.indexOf("taskdetails.do") != -1 && url.indexOf("from=loghours") != -1) {
													new Effect.ScrollTo("taskloghrscontent", {
														duration: 0.1
													});
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if (url.indexOf("allbugreport.do") != -1) {
					if (url.indexOf("type=all") != -1) {
						ShowGenBlock("allbugreport");
						Hide("closedBug");
						Hide("notclosedbug");
						changeBugRepTabStyle("all");
					}
					if (url.indexOf("type=closed") != -1) {
						ShowGenBlock("closedBug");
						Hide("allbugreport");
						Hide("notclosedbug");
						changeBugRepTabStyle("closed");
					}
					if (url.indexOf("type=open") != -1) {
						ShowGenBlock("notclosedbug");
						Hide("allbugreport");
						Hide("closedBug");
						changeBugRepTabStyle("notclosed");
					}
				}
				if (url.indexOf("consaddbug.do") != -1) {
					callCalendar("dueDate", "f_trigger_c_dueDate");
				}
				initLightbox();
			}
		};
		http.send(_7f6.args);
	}
}
function ajaxShowFormTab(url, _826, _827) {
	var _828 = fixForCachingInIe(url);
	var _829 = getURLArgs(_828);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _829.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _82c = document.getElementById(_826);
				_82c.innerHTML = text;
				ini = false;
				pmevalScript(_82c);
				if (_827 == "task") {
					ShowGenBlock("addtodotask");
					document.addTodoTaskForm.todotask.focus();
					if (Utils.isTaskMailOpt || Utils.isTaskMailOpt == "undefined") {
						document.addTodoTaskForm.notifyuser.checked = true;
					} else {
						document.addTodoTaskForm.notifyuser.checked = false;
					}
				} else {
					if (_827 == "tasklist") {
						ShowGenBlock("addtoptodolist");
						document.addTodoList.todotitle.focus();
					} else {
						if (_827 == "milestone") {
							ShowGenBlock("addmilestone");
							document.addMilestone.mtitle.focus();
						} else {
							if (_827 == "meeting") {
								ShowGenBlock("addmeeting");
								document.addMeeting.meettitle.focus();
							} else {
								if (_827 == "user") {
									ShowGenBlock("addProjectUser");
									document.addProjectUser.email.focus();
								} else {
									if (_827 == "forum") {
										ShowGenBlock("newforum");
										document.postDiscussion.mesgtitle.focus();
									} else {
										if (_827 == "document") {
											ShowGenBlock("uploaddoc");
										}
									}
								}
							}
						}
					}
				}
				Hide("ajax_load_tab");
			}
		};
		http.send(_829.args);
	}
}
function ajaxShowEditorTab(url, _82e) {
	var _82f = fixForCachingInIe(url);
	var _830 = getURLArgs(_82f);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _830.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				if (url.indexOf("projectoverview.do") != -1) {
					Utils.editorObj["overview_editor"].setContent(text);
				} else {
					var _833 = document.getElementById(_82e);
					_833.innerHTML = text;
					ini = false;
					pmevalScript(_833);
				}
			}
		};
		http.send(_830.args);
	}
}
function ajaxFetchTodos(url, _835) {
	var _836 = fixForCachingInIe(url);
	var _837 = getURLArgs(_836);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _837.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _83a = document.getElementById(_835);
				_83a.innerHTML = text;
				ini = false;
				pmevalScript(_83a);
				Hide("zoho_todo_filter_busy");
			}
		};
		http.send(_837.args);
	}
}
function ajaxSendRequest(url, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var _83d = fixForCachingInIe(url);
	var arg = arguments;
	var _83f = getURLArgs(_83d);
	var http = getHTTPObject();
	var _841;
	if (url.indexOf("/completemilestone.do") != -1) {
		if (url.indexOf("status=1") != -1) {
			_841 = "zp.todoms.milereopen";
		} else {
			_841 = "zp.todoms.mileclosed";
		}
	}
	if (http) {
		http.open("POST", _83f.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				if (url.indexOf("/deletemeeting.do") != -1) {
					i18n.getJSAlertValue(Utils.zuid, "zp.meeting.delmeetsucc", null,
					function(mesg) {
						displayFadeMsg(mesg);
					});
				} else {
					if (url.indexOf("/changeplan.do") != -1) {
						window.location.reload(true);
					} else {
						if (url.indexOf("/completemilestone.do") != -1) {
							i18n.getJSAlertValue(Utils.zuid, _841, null,
							function(mesg) {
								displayFadeMsg(mesg);
							});
						} else {
							if (url.indexOf("/makeastemplate.do") != -1) {
								i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.tempaddsuc", null,
								function(mesg) {
									displayFadeMsg(mesg);
								});
							} else {
								if (url.indexOf("deleteproject.do") != -1) {
									Hide("ajax_load_tab");
								} else {
									if (url.indexOf("/savetaborder.do") != -1) {
										modifyURL("dashboard/" + arg[2]);
										loadDataFromURL(csrf);
									} else {
										if (url.indexOf("/remoteopen.do") != -1) {} else {
											if (url.indexOf("/fetchprice.do") != -1) {
												var a = text.split("||");
												var amt = a[1];
												var _848 = a[2];
												var _849 = a[3];
												var wAmt = a[4];
												var cAmt = a[5];
												var _84c = a[6];
												var _84d = a[7];
												var bAmt = a[8];
												if (_84d.indexOf("ValuePack") != -1) {
													var _84f = parseInt(amt, 10) + parseInt(_84c, 10);
													var _850 = "<span class='dolnew'>$</span><span class='prinew'>" + _84c + "</span> <span class='dolnew'> /" + _848 + "</span>";
													var _851 = "<span class='dolnew'>$</span><span class='prinew'>" + _84f + "</span> <span class='dolnew'> /" + _848 + "</span>";
													document.getElementById("wikichatamt").innerHTML = _850;
													document.getElementById("totamt").innerHTML = _851;
												} else {
													var _852 = "<span class='dolnew'>$</span><span class='prinew'>" + amt + "</span> <span class='dolnew'> /" + _848 + "</span>";
													if (_849 == "monthly") {
														var _853 = "<span class='dolnew'>$</span><span class='prinew'>" + cAmt + "</span> <span class='dolnew'> /" + _848 + "</span>";
														var _854 = "<span class='dolnew'>$</span><span class='prinew'>" + wAmt + "</span> <span class='dolnew'> /" + _848 + "</span>";
														var _855 = "<span class='dolnew'>$</span><span class='prinew'>" + bAmt + "</span> <span class='dolnew'> /" + _848 + "</span>";
														var _84f = parseInt(amt, 10) + parseInt(wAmt, 10) + parseInt(cAmt, 10) + parseInt(bAmt, 10);
														document.getElementById("wikiamt").innerHTML = _854;
														document.getElementById("chatamt").innerHTML = _853;
														document.getElementById("bugamt").innerHTML = _855;
													} else {
														if (_849 = "yearly") {
															var _850 = "<span class='dolnew'>$</span><span class='prinew'>" + _84c + "</span> <span class='dolnew'> /" + _848 + "</span>";
															var _855 = "<span class='dolnew'>$</span><span class='prinew'>" + bAmt + "</span> <span class='dolnew'> /" + _848 + "</span>";
															var _84f = parseInt(amt, 10) + parseInt(_84c, 10) + parseInt(bAmt, 10);
															document.getElementById("wikichatamt").innerHTML = _850;
															document.getElementById("ybugamt").innerHTML = _855;
														}
													}
													var _851 = "<span class='dolnew'>$</span><span class='prinew'>" + _84f + "</span> <span class='dolnew'> /" + _848 + "</span>";
													document.getElementById("chosenplan").innerHTML = _852;
													document.getElementById("modeValue").value = _849;
													document.getElementById("totamt").innerHTML = _851;
												}
											} else {
												if (url.indexOf("/archivemstone.do") != -1) {
													i18n.getJSAlertValue(Utils.zuid, "zp.todoms.milearchived", null,
													function(mesg) {
														displayFadeMsg(mesg);
													});
												} else {
													if (url.indexOf("/fetchchatdetails.do") != -1) {
														var a = text.split("||");
														var _857 = WindowHandler.getWinObjById(a[3]);
														if (_857 && a[1] != "null") {
															_857.updateUserStatus(0, a[1]);
															_857.setTitle(a[2]);
														}
													} else {
														if (url.indexOf("/resendconfirm.do") != -1) {
															i18n.getJSAlertValue(Utils.zuid, "zp.users.resendinvitationmail", null,
															function(mesg) {
																displayFadeMsg(mesg);
															});
														} else {
															if (url.indexOf("/deletetodotask.do") != -1) {
																if (text.indexOf("Deleted Task|") != -1) {
																	var tid = text.split("|")[1];
																	var _85a = text.split("|")[2];
																	var _85b = jQuery("#task_" + tid).attr("status");
																	if (_85b == "open") {
																		replacePrevTask(tid);
																	}
																	jQuery("#task_" + tid).remove();
																	jQuery("#taskaction_" + tid).remove();
																	jQuery("[task_" + tid + "=" + tid + "]").remove();
																	if (_85a != "undefined" && _85a != null && _85a != "") {
																		var _85c = jQuery("[task_" + _85a + "=" + _85a + "]");
																		if (_85c == null || _85c.length == 0) {
																			jQuery("#hidesubtask_" + _85a).hide();
																			jQuery("#hidesubtask_" + _85a).attr("isparent", "false");
																		}
																	}
																}
															} else {
																if (url.indexOf("/updatetask.do") != -1) {
																	if (text.indexOf("isParentClosed ||") != -1) {
																		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.closemsg", null,
																		function(mesg) {
																			alert(mesg);
																		});
																	} else {
																		if (text.indexOf("isChildOpened ||") != -1) {
																			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.openmsg", null,
																			function(mesg) {
																				alert(mesg);
																			});
																		} else {
																			if (text.indexOf("isNotValidDueDate ||") != -1) {
																				i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.duedatecantst", null,
																				function(mesg) {
																					alert(mesg);
																				});
																			} else {
																				if (document.getElementById("tcompdate") && document.getElementById("taskdate")) {
																					document.getElementById("tcompdate").innerHTML = document.getElementById("taskdate").value;
																				}
																				ShowHideInline("task_comp", "changecompdate");
																				i18n.getJSAlertValue(Utils.zuid, "zp.general.taskupdatedsuccessfully", null,
																				function(mesg) {
																					displayFadeMsg(mesg);
																				});
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if (url.indexOf("/zpdboxsync.do") != -1) {
					if (url.indexOf("syncdel") != -1) {
						var a = jQuery("#dblistid").find("tr[id]");
						console.log(a.length);
						if (a.length < 1) {
							ShowHideInline("emptydblistdiv", "dblistid");
						}
					}
					setTimeout(function() {
						Hide("customloadingdiv");
					},
					2000);
				}
			}
		};
		http.send(_83f.args);
	}
}
function ajaxHomeCheckRequest(url, id) {
	var _863 = fixForCachingInIe(url);
	var _864 = getURLArgs(_863);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _864.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				if (text.indexOf("isParentClosed ||") != -1) {
					document.getElementById("ttaskcbox").checked = false;
				} else {
					if (text.indexOf("isChildOpened ||") != -1) {
						document.getElementById("ttaskcbox").checked = true;
					} else {
						changeHomeTaskStyle(id, text);
					}
				}
			}
		};
		http.send(_864.args);
	}
}
function ajaxSendFormRequest(url, _868, id, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var _86b = url;
	var args = "?" + getFormValues(_868);
	_86b = fixForCachingInIe(url + args);
	var _86d = getURLArgs(_86b);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _86d.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _870;
				if (url.indexOf("/sendmessage.do") != -1) {
					document.sendMessage.reset();
					Hide("zoho_sendmsg_busy");
					Hide("sendMessagediv");
					Hide("pepdfcpy");
					ShowGenBlock("userlisting");
					if (document.getElementById("usertitle")) {
						ShowGenBlock("usertitle");
						document.getElementById("usertitle").className = "toptitle2 mB20";
					}
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.msgsendsuccess", null,
					function(mesg) {
						displayFadeMsg(mesg);
					});
				} else {
					if (url.indexOf("/addprimaryclientcomp.do") != -1) {
						Hide("addclicomp");
						Hide("ajax_load_tab");
					} else {
						if (url.indexOf("/updateproject.do") != -1) {
							if (text.indexOf("zzzprojectupderrorzzz||") != -1) {
								var a = text.split("||");
								var _873 = a[1];
								var _874 = document.getElementById("settings_update_status");
								_874.innerHTML = _873;
								Hide("zoho_proj_settings_busy");
							} else {
								if (text.indexOf("projstatus") != -1) {
									var a = text.split("||");
									if (document.getElementById("activeprojectdiv")) {
										ajaxShowPage(Utils.contPath + "/viewprimaryclient.do?projId=0&" + csrf, "clientprojlistdiv");
										ShowHideInline("activeprojectdiv", "editprojdiv");
										ShowGenInline("activeprojdiv");
									} else {
										ShowHideInline("activeprojdiv", "editprojdiv");
									}
									ShowGenInline("projlinkId");
								}
							}
						} else {
							if (url.indexOf("/addportal.do") != -1 || url.indexOf("/addexistportal.do") != -1 || url.indexOf("/addotherportal.do") != -1) {
								if (text.indexOf("zzzportaladderrorzzz") != -1) {
									var a = text.split("||");
									var perr = a[1];
									var bfor = a[2];
									var _877 = document.getElementById(id);
									if (bfor == "zoho") {
										_877.innerHTML = perr;
									} else {
										i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.portnotavail", null,
										function(mesg) {
											_877.innerHTML = "<span class=\"error\">" + mesg + "</span>";
										});
									}
									validateAddPortalCheck("enable");
								} else {
									if (text.indexOf("zzzportaladdsucceszzz") != -1) {
										var a = text.split("||");
										var psuc = a[1];
										var _87a = a[2];
										var _87b = a[3];
										var _87c = a[4];
										var _87d = a[5];
										var _87e = a[6];
										var _877 = document.getElementById(id);
										var _87f = document.addPortal.pageurl;
										if (url.indexOf("/addotherportal.do") != -1 && _87f) {
											window.open(_87f.value, "_parent", "");
										} else {
											if (_87e == "yes") {
												window.open(_87a + "/newlogin.do", "_parent", "");
											} else {
												window.open(_87a + "/newlogin.do", "_parent", "");
											}
										}
										return true;
									}
								}
							} else {
								if (url.indexOf("/bulkdeletepeople.do") != -1) {
									i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.delsuccess", null,
									function(mesg) {
										displayFadeMsg(mesg);
									});
								} else {
									if (url.indexOf("/schedulereminder.do") != -1) {
										ShowGenInline("zoho_sch_message");
										setTimeout(function() {
											Hide("zoho_sch_message");
										},
										2000);
										Hide("zoho_task_reminder_busy");
									} else {
										if (url.indexOf("/newremoteopen.do") != -1) {} else {
											if (url.indexOf("/updpeoplehierarchy.do") != -1) {
												_870 = document.getElementById(id);
												_870.innerHTML = text;
											} else {
												if (url.indexOf("/movetasklist.do") != -1) {
													Hide("ul_ttitle_" + id);
													Hide("ul_ttask_" + id);
													Hide("ul_tfoot_" + id);
													jQuery("#movetlid").hide();
												} else {
													if (id == "wothnks") {
														ShowGenInline(id);
														_870 = document.getElementById(id);
														document.feedbackmain.textarea.value = "";
														document.feedbackmain.emailid.value = "";
													} else {
														_870 = document.getElementById("uprole_" + id);
													}
													_870.innerHTML = text;
													if (id == "wothnks") {
														setTimeout(function() {
															Hide(id);
														},
														3000);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		};
		http.send(_86d.args);
	}
}
function ajaxUpdateLog(url, id, _883) {
	var _884 = document.getElementById("lognotes_" + id).value;
	var _885 = document.getElementById("edithours_" + id).value;
	var _886 = document.getElementById("logtask_" + id).value;
	var _887 = document.getElementById("billsts_" + id).value;
	var _888 = document.getElementById("elogdate").value;
	var _889 = document.getElementById("updhourstype_" + id).value;
	var _88a = fixForCachingInIe(url + "&logid=" + id + "&logdate=" + _888 + "&lognotes=" + encodeURIComponent(_884) + "&loghours=" + _885 + "&logtask=" + encodeURIComponent(_886) + "&billsts=" + _887 + "&updhourstype=" + _889);
	if (document.getElementById("logfromampm_" + id) && document.getElementById("logtoampm_" + id) && _889 == "fromto") {
		var _88b = document.getElementById("logfromhr_" + id).value;
		var _88c = document.getElementById("logfrommins_" + id).value;
		var _88d = document.getElementById("logtohr_" + id).value;
		var _88e = document.getElementById("logtomins_" + id).value;
		_88a = fixForCachingInIe(url + "&logid=" + id + "&logdate=" + _888 + "&lognotes=" + encodeURIComponent(_884) + "&logtask=" + encodeURIComponent(_886) + "&billsts=" + _887 + "&updhourstype=" + _889 + "&logfromhr=" + _88b + "&logfrommins=" + _88c + "&logtohr=" + _88d + "&logtomins=" + _88e + "&logfromampm=" + document.getElementById("logfromampm_" + id).value + "&logtoampm=" + document.getElementById("logtoampm_" + id).value);
	} else {
		if (document.getElementById("logfromhr_" + id) && _889 == "fromto") {
			var _88b = document.getElementById("logfromhr_" + id).value;
			var _88c = document.getElementById("logfrommins_" + id).value;
			var _88d = document.getElementById("logtohr_" + id).value;
			var _88e = document.getElementById("logtomins_" + id).value;
			_88a = fixForCachingInIe(url + "&logid=" + id + "&lognotes=" + encodeURIComponent(_884) + "&logdate=" + _888 + "&logtask=" + encodeURIComponent(_886) + "&billsts=" + _887 + "&updhourstype=" + _889 + "&logfromhr=" + _88b + "&logfrommins=" + _88c + "&logtohr=" + _88d + "&logtomins=" + _88e);
		}
	}
	var _88f = getURLArgs(_88a);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _88f.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				contentId = document.getElementById(_883);
				if (text.indexOf("zzzinvalidlogtimezzz||") != -1) {
					i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.fromtoval", null,
					function(mesg) {
						alert(mesg);
					});
					Hide("zoho_upd_loghours_busy_" + id);
				} else {
					contentId.innerHTML = text;
					pmevalScript(contentId);
				}
			}
		};
		http.send(_88f.args);
	}
}
function ajaxClockRequest(url, id, stat) {
	var _896 = fixForCachingInIe(url);
	var _897 = getURLArgs(_896);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _897.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				if (text.indexOf("todo timer start||") != -1) {
					var a = text.split("||");
					var _89b = a[1];
				}
				if (stat == "start") {
					ShowHideInline("stoptimer_" + id, "starttimer_" + id);
				} else {
					if (stat == "stop") {
						if (document.getElementById("starttimer_" + id) && document.getElementById("stoptimer_" + id)) {
							ShowHideInline("starttimer_" + id, "stoptimer_" + id);
						} else {
							if (document.getElementById("starttimer_" + id)) {
								ShowGenInline("starttimer_" + id);
							} else {
								if (document.getElementById("stoptimer_" + id)) {
									Hide("stoptimer_" + id);
								}
							}
						}
						var _89c = document.getElementById("hourval_" + id);
						if (_89c) {
							var b = (_89c.value).split(" : ");
							var _89e = "<span class='bugown3 pt3'>" + b[0] + ":" + b[1] + "</span>";
							_89c.innerHTML = _89e;
							ShowGenInline("hourval_" + id);
						}
						var _89f = text.split("||")[2];
						_89f = _89f.replace(".", ":");
						var _8a0 = document.getElementById("totallog_" + id).innerHTML;
						document.getElementById("totallog_" + id).innerHTML = addLogHours(_8a0.trim(), _89f);
						var _8a1 = document.getElementById("billable_" + id).innerHTML;
						document.getElementById("billable_" + id).innerHTML = addLogHours(_8a1.trim(), _89f);
					}
				}
			}
		};
		http.send(_897.args);
	}
}
function ajaxComplete(url, _8a3, _8a4, what, _8a6) {
	var _8a7;
	var _8a8;
	var _8a9;
	if (what == "todos") {
		_8a7 = document.getElementById("zoho_todo_busy" + _8a4);
		_8a8 = document.getElementById("ttaskcbox_" + _8a4);
		if (_8a6 == 1) {
			_8a9 = "zp.todoms.taskreopen";
		} else {
			_8a9 = "zp.todoms.taskclosed";
		}
	} else {
		if (what == "milestones") {
			_8a7 = document.getElementById("zoho_msus_busy" + _8a4);
			_8a8 = document.getElementById("mcomp_" + _8a4);
			if (_8a6 == 1) {
				_8a9 = "zp.todoms.milereopen";
			} else {
				_8a9 = "zp.todoms.mileclosed";
			}
		} else {
			if (what == "dependview") {
				_8a7 = document.getElementById("zoho_dep_busy" + _8a4);
				_8a8 = document.getElementById("deptaskcbox_" + _8a4);
				if (_8a6 == 1) {
					_8a9 = "zp.todoms.taskreopen";
				} else {
					_8a9 = "zp.todoms.taskclosed";
				}
			}
		}
	}
	if (_8a7 != null) {
		_8a7.style.display = "inline";
		_8a8.style.display = "none";
	}
	var _8aa = fixForCachingInIe(url);
	var _8ab = getURLArgs(_8aa);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _8ab.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				if (text.indexOf("isParentClosed ||") != -1) {
					var msg = text.split("||");
					var _8af = msg[1];
					dependentAlertMsg(_8af);
					if (_8a7 != null) {
						_8a7.style.display = "none";
						_8a8.style.display = "inline";
						_8a8.checked = false;
					}
					enableCheckBox();
				} else {
					if (text.indexOf("isChildOpened ||") != -1) {
						var msg = text.split("||");
						var _8af = msg[1];
						dependentAlertMsg(_8af);
						if (_8a7 != null) {
							_8a7.style.display = "none";
							_8a8.style.display = "inline";
							_8a8.checked = true;
						}
						enableCheckBox();
					} else {
						if (text.indexOf("subtask can not reopened ||") != -1) {
							i18n.getJSAlertValue(Utils.zuid, "zp.subtask.reopened", null,
							function(mesg) {
								displayFadeMsg(mesg);
							});
							if (_8a7 != null) {
								_8a7.style.display = "none";
								_8a8.style.display = "inline";
								_8a8.checked = true;
							}
							enableCheckBox();
						} else {
							var _8b1 = document.getElementById(_8a3);
							_8b1.innerHTML = text;
							ini = false;
							pmevalScript(_8b1);
							i18n.getJSAlertValue(Utils.zuid, _8a9, null,
							function(mesg) {
								displayFadeMsg(mesg);
							});
						}
					}
				}
			}
		};
		http.send(_8ab.args);
	}
}
function ajaxHomeClockRequest(url, id, stat) {
	var _8b6 = fixForCachingInIe(url);
	var _8b7 = getURLArgs(_8b6);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _8b7.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				if (text.indexOf("todo timer start||") != -1) {
					var a = text.split("||");
					var _8bb = a[1];
				}
				if (stat == "start") {
					ShowHideInline("stoptimer_" + id, "starttimer_" + id);
				} else {
					if (stat == "stop") {
						ShowHideInline("starttimer_" + id, "stoptimer_" + id);
					}
				}
			}
		};
		http.send(_8b7.args);
	}
}
function ajaxShowSearchPage(csrf, url, _8be, val, pid, tlid) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var _8c2 = getHTTPObject();
	var _8c3 = fixForCachingInIe(url);
	var _8c4 = getURLArgs(_8c3);
	if (_8c2) {
		_8c2.open("POST", _8c4.url, true);
		_8c2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		_8c2.onreadystatechange = function() {
			if (_8c2.readyState == 4) {
				var text = _8c2.responseText;
				var _8c6 = document.getElementById(_8be);
				_8c6.innerHTML = text;
				ini = false;
				pmevalScript(_8c6);
				if (url.indexOf("fetchcomment.do") != -1) {
					loadZEditor("comeditordiv", "");
				}
				if (val) {
					if (tlid) {
						if (document.getElementById("collapseList_" + tlid)) {
							ShowGenBlock("collapseList_" + tlid);
							ShowHideInline("rollcollapse_" + tlid, "rollexpand_" + tlid);
							ajaxSendRequest(Utils.contPath + "/expandcollapse.do?projId=" + pid + "&item=todolist&action=expand&listid=" + tlid + "&" + csrf);
						}
						if (document.getElementById("compcollapseList_" + tlid)) {
							ShowGenBlock("compcollapseList_" + tlid);
							ShowHideInline("completedcollapse_" + tlid, "completedexpand_" + tlid);
							ajaxSendRequest(Utils.contPath + "/expandcollapse.do?projId=" + pid + "&item=completed&action=expand&listid=" + tlid + "&" + csrf);
						}
					}
					if (url.indexOf("isnotes=yes") != -1) {
						var args = _8c4.args;
						var _8c8 = args.indexOf("projId=");
						var _8c9 = args.length;
						if (args.indexOf("&", _8c8) != -1) {
							_8c9 = args.indexOf("&", _8c8);
						}
						var prid = args.substring(_8c8 + 7, _8c9);
						var ind = val.indexOf("_");
						var tid = val.substring(ind + 1, val.length);
						ShowGenBlock("addtaskactions_" + tid);
						ajaxShowTab(Utils.contPath + "/addtaskaction.do?taskid=" + tid + "&action=expand&projId=" + pid + "&actype=editdel&taskmaptype=notes&" + csrf, "addtaskactions_" + tid);
					}
					if (url.indexOf("ismeetnotes=yes") != -1) {
						var args = _8c4.args;
						var _8c8 = args.indexOf("projId=");
						var _8c9 = args.length;
						if (args.indexOf("&", _8c8) != -1) {
							_8c9 = args.indexOf("&", _8c8);
						}
						var prid = args.substring(_8c8 + 7, _8c9);
						var ind = val.indexOf("_");
						var mid = val.substring(ind + 1, val.length);
						ajaxConstructPage(Utils.contPath + "/fetchmeetnotes.do?meetid=" + mid + "&projId=" + prid + "&" + csrf, mid);
					}
					if (url.indexOf("dispMeetType=closed") != -1) {
						changeSubLink("ellink", "elImg");
					}
					if (document.getElementById(val)) {
						new Effect.ScrollTo(val, {
							duration: 0.3
						});
						document.getElementById(val).style.background = "#FAFABB";
						setTimeout(function() {
							document.getElementById(val).style.background = "";
						},
						3000);
					}
				}
				Hide("ajax_load_tab");
			}
		};
		_8c2.send(_8c4.args);
	}
}
function ajaxShowPage(url, _8cf) {
	var _8d0 = getHTTPObject();
	var _8d1 = fixForCachingInIe(url);
	var _8d2 = getURLArgs(_8d1);
	if (url.indexOf("/compmilestone.do") != -1) {
		if (url.indexOf("status=1") != -1) {
			dispMesg = "zp.todoms.milereopen";
		} else {
			dispMesg = "zp.todoms.mileclosed";
		}
	}
	if (_8d0) {
		_8d0.open("POST", _8d2.url, true);
		_8d0.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		_8d0.onreadystatechange = function() {
			if (_8d0.readyState == 4) {
				var text = _8d0.responseText;
				var div = _8cf;
				var div1 = _8cf;
				if (div1.indexOf(":") != -1) {
					div = _8cf.split(":");
					_8cf = div[0];
				} else {
					if (url.indexOf("/dropboxlist.do") != -1) {
						document.getElementById(_8cf).innerHTML = text;
					} else {
						if (url.indexOf("/dbfolderlist.do") != -1) {
							var did = _8cf + "_dbf";
							document.getElementById(did).innerHTML = text;
							Hide("db_folfetch_busy_" + _8cf);
							changeImgSrc("acctsel");
							document.getElementById("backtodbconfig").className = "inline";
							return false;
						} else {
							if (url.indexOf("/compmilestone.do") != -1) {
								if (text.indexOf("zzzmstypezzz||upcoming") != -1) {
									_8cf = "ul_mile_upcoming";
								}
								if (text.indexOf("isNotValidCompleDate ||") != -1) {
									i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.duedatecantst", null,
									function(mesg) {
										alert(mesg);
									});
									return false;
								}
								if (is_ie) {
									jQuery("#" + _8cf).replaceWith("<tbody id=\"" + _8cf + "\">" + text + "</tbody>");
									bindMoT(_8cf);
								}
								i18n.getJSAlertValue(Utils.zuid, dispMesg, null,
								function(mesg) {
									displayFadeMsg(mesg);
								});
							}
						}
					}
				}
				if (url.indexOf("/morecrmclient.do") != -1) {
					if (text.indexOf("There is no data to show||") != -1) {
						ShowGenInline("errorDiv");
						Hide("ajax_load_tab");
					} else {
						document.getElementById("crmcllist").innerHTML += text;
						if (document.addClientCrm.count) {
							var _8d9 = document.addClientCrm.count.value;
							var _8da = document.getElementById("clicount").value;
							document.addClientCrm.count.value = parseInt(_8d9) + parseInt(_8da);
						}
						var _8db = document.getElementById("fIndex");
						if (_8db) {
							_8db.value = parseInt(_8db.value) + 200;
						}
						Hide("ajax_load_tab");
					}
					return false;
				}
				if (url.indexOf("/deletetodotask.do") != -1) {
					if (text.indexOf("No Tasks||") != -1) {
						_8cf = "emptymsg_div";
						text = "<div class='txtSmall' align='center'>" + text.split("||")[1] + "</div>";
					}
				}
				if (url.indexOf("/timesheetaction.do") != -1) {
					document.getElementById("tspopupoptions").innerHTML = text;
					var pos = jQuery("#" + _8cf).offset();
					var _8dd = Math.round(eval(pos.top));
					var _8de = Math.round(eval(pos.left));
					jQuery("#tspopupoptions").css("top", _8dd);
					jQuery("#tspopupoptions").css("left", _8de);
					return false;
				}
				if (url.indexOf("/logtasklist.do") != -1 || url.indexOf("/logissuelist.do") != -1) {
					if (_8cf == "selectlogitem") {
						jQuery("#logtaskbug").remove();
						jQuery("#" + _8cf).after(text);
						jQuery("#logtaskbug").css("position", "");
					} else {
						if (_8cf.indexOf("selectlogitem") != -1) {
							jQuery("#logtaskbug").remove();
							var pos = jQuery("#" + _8cf).offset();
							jQuery("#projectcontent").append(text);
							var _8dd = Math.round(eval(pos.top));
							var _8de = Math.round(eval(pos.left));
							jQuery("#logtaskbug").css("top", _8dd + 24);
							jQuery("#logtaskbug").css("left", _8de);
							scrollInViewPart("logtaskbug");
						} else {
							document.getElementById(_8cf).innerHTML = text;
						}
					}
					return false;
				}
				if (url.indexOf("/editlog.do") != -1) {
					var _8df = document.getElementById("addtasklog");
					_8df.innerHTML = text;
					if (_8cf.indexOf("timesheetaction") != -1) {
						var pos = jQuery("#" + _8cf).offset();
						var _8e0 = pos.top - 61;
						var _8e1 = pos.left + 88;
						jQuery("#addtasklog").css({
							"left": _8e1 + "px",
							"top": _8e0 + "px"
						});
						jQuery("#addtasklog").show();
					} else {
						var pos = jQuery("#" + _8cf).offset();
						var _8e0 = pos.top - 63;
						var _8e1 = pos.left + 29;
						jQuery("#addtasklog").css({
							"left": _8e1 + "px",
							"top": _8e0 + "px"
						});
						jQuery("#addtasklog").show();
					}
					scrollInViewPart("addtasklog");
					callCal("elogdate", "log_cal");
					Hide("ajax_load_tab");
					ini = false;
					return false;
				}
				if (url.indexOf("/weeklogdates.do") != -1) {
					var _8e2 = document.getElementById(_8cf);
					jQuery("#" + _8cf).replaceWith("<tbody id=\"datestbody\">" + text + "</tbody");
					Hide("ajax_load_tab");
					ini = false;
					pmevalScript(_8e2);
					return false;
				}
				var _8df = document.getElementById(_8cf);
				_8df.innerHTML = text;
				Hide("ajax_load_tab");
				ini = false;
				pmevalScript(_8df);
				if (url.indexOf("tasklistview.do?mileId") != -1) {
					changeSubLink("mileviewlink", "mileviewImg");
				}
				if (url.indexOf("viewprimaryclient.do?") != -1) {
					ShowHideInline("clientprojlistdiv", "projlistdiv");
				}
				if (url.indexOf("people.do") != -1 || url.indexOf("newuser.do") != -1 || (url.indexOf("showproject.do") != -1 && url.indexOf("toview=users") != -1) || url.indexOf("newproject.do") != -1) {
					if (document.getElementById("gapps_autofill_user")) {
						ajaxAutoFill(Utils.contPath + "/autofilluser.do");
					}
				}
				if (url.indexOf("updateaction.do") != -1) {
					i18n.getJSAlertValue(Utils.zuid, "zp.general.taskupdatedsuccessfully", null,
					function(mesg) {
						displayFadeMsg(mesg);
					});
				}
				if (url.indexOf("constupdmeeting.do") != -1) {
					ShowHideInline("updatemeeting", "displaymeeting");
					callCal("schdateupdate", "app_trigger_c_update");
				}
				if (url.indexOf("editlogdetails.do") != -1) {
					if (document.getElementById("edit_log_div") && document.getElementById("timesheet_div")) {
						new Effect.ScrollTo("gentimesheet_separator", {
							duration: 0.1
						});
						ShowHideInline("edit_log_div", "timesheet_div");
					}
				}
				if (url.indexOf("projectsearch.do") != -1) {
					ShowHideInline("searchdiv", "lvhide");
				}
				if (url.indexOf("fetchprojactivity.do") != -1) {
					if (document.getElementById("projAct") && document.getElementById("userStat")) {
						ShowGenBlock("projAct");
						Hide("userStat");
						Hide("projannouncementdiv");
					}
					Hide("zoho_activity_busy");
				}
				if (url.indexOf("fetchuserstatus.do") != -1) {
					ShowGenBlock("userStat");
					Hide("projAct");
					Hide("projannouncementdiv");
					Hide("zoho_projpage_busy");
				}
				if (url.indexOf("projannouncement.do") != -1) {
					ShowGenBlock("projannouncementdiv");
					Hide("projAct");
					Hide("userStat");
					Hide("zoho_projannounce_busy");
					if (div1.indexOf(":") != -1) {
						var val = "showannounce_" + div[1];
						new Effect.ScrollTo(val, {
							duration: 0.3
						});
						document.getElementById(val).style.background = "#FAFABB";
						setTimeout(function() {
							document.getElementById(val).style.background = "";
						},
						3000);
					}
				}
				if (url.indexOf("clearrs.do") != -1) {
					var _8e5 = document.getElementById("recserid");
					_8e5.innerHTML = " Recent Search Terms Cleared ";
				}
				if (url.indexOf("attachnewdoc.do") != -1) {
					ShowHideInline("newdocdiv", "taskdetailsdiv");
				}
				if (url.indexOf("fetchgentask.do?linkType=reordertl") != -1) {
					var _8e5 = document.getElementById("miscidval").value;
					reOrderLists("ul_tlist_" + _8e5, _8e5);
					Hide("misc_tl_order_busy");
				}
				if (url.indexOf("saveusertheme.do") != -1) {
					applyTheme();
				}
				if (url.indexOf("fetchmiletodos.do?ldisptype=all") != -1) {
					var _8e5 = document.getElementById("miscidval").value;
					reOrderLists("ul_tlist_" + _8e5, _8e5);
					ShowHideInline("msaveorder_div", "mact_div");
					Hide("misc_tl_order_busy");
				}
				if (url.indexOf("usersettings.do") != -1) {
					if (url.indexOf("toshow=profile") != -1) {
						ShowGeneralSettings("profile");
					} else {
						if (url.indexOf("toshow=language") != -1) {
							ShowGeneralSettings("locale");
						} else {
							if (url.indexOf("toshow=photo") != -1) {
								ShowGeneralSettings("photo");
							}
						}
					}
				}
				if (url.indexOf("newproject.do") != -1) {
					document.addProject.projectname.focus();
					loadZEditor("overview_editor", "");
				}
				if (url.indexOf("projmodsettings.do") != -1) {
					Hide("ajax_load_tab");
					if (document.getElementById("updateProject")) {
						document.updateProject.projectname.focus();
						loadZEditor("overview_editor", "");
						Utils.editorObj["overview_editor"].setContent(document.updateProject.pdescribe.value);
					}
				}
				if (url.indexOf("newuser.do") != -1) {
					if (document.getElementById("homediv")) {
						ShowHideBlock("adduser", "homediv");
					}
					document.addUser.email.focus();
				}
				if (url.indexOf("deletefile.do") != -1) {}
				if (url.indexOf("deletefolder.do") != -1) {}
				if (url.indexOf("deletelink.do") != -1) {}
				if (url.indexOf("deleteforumcategory.do") != -1) {}
				if (url.indexOf("deleteuser.do") != -1) {}
				if (url.indexOf("fetchcomment.do") != -1) {
					loadZEditor("comeditordiv", "");
					scroll(0, 0);
				}
				if (url.indexOf("attachnewdoc.do") != -1) {
					ShowGenInline("muluploaddoc");
				}
				if (url.indexOf("changeplan.do") != -1) {
					if (text.indexOf("zzzplanchange||") != -1) {
						var a = text.split("||");
						var cid = a[1];
					}
				}
				if (url.indexOf("editprojectdetails.do") != -1) {
					ShowHideInline("editprojdiv", "activeprojectdiv");
					ShowHideInline("editprojdiv", "activeprojdiv");
					loadZEditor("overview_editor", "");
					Utils.editorObj["overview_editor"].setContent(document.updateProject.pdescribe.value);
				}
				if (url.indexOf("ccinfo.do") != -1) {}
				if (url.indexOf("/archivemile.do") != -1) {
					i18n.getJSAlertValue(Utils.zuid, "zp.todoms.milearchived", null,
					function(mesg) {
						displayFadeMsg(mesg);
					});
				}
				if (url.indexOf("userprofile.do") != -1) {
					if (document.getElementById("compusersdisplay")) {
						ShowHideBlock("usereditprofile", "compusersdisplay");
					}
				}
				if (url.indexOf("reorderprojpage.do") != -1) {
					Sortable.create("projsequence");
					document.getElementById("projsequence").className = "reorder";
				}
				if (url.indexOf("showproject.do") != -1 || url.indexOf("dependview.do") != -1) {
					Hide("zoho_milestone_busy");
				}
				if (url.indexOf("savetasklisttemporder.do") != -1) {}
				if (url.indexOf("reordertlisttemporder.do") != -1) {
					var _8e9 = "reorder_separator";
					var _8ea = "additem_separator";
					var _8eb = "tlreorder_id";
					Sortable.create(_8eb, {
						tag: "tr",
						constraint: false
					});
					ShowHideInline(_8e9, _8ea);
					document.getElementById(_8eb).className = "reorder";
				}
				if (url.indexOf("alphasearch.do") != -1) {
					Hide("search_busy");
					Hide("ajax_load_tab");
				}
				if (url.indexOf("updatetask.do") != -1) {
					i18n.getJSAlertValue(Utils.zuid, "zp.general.taskupdatedsuccessfully", null,
					function(mesg) {
						displayFadeMsg(mesg);
					});
				}
				if (url.indexOf("addcalendartask.do") != -1) {
					if (text.indexOf("zzztoptodozzz||") != -1) {
						var a = text.split("||");
						new Effect.ScrollTo("task_" + a[1], {
							duration: 0.1
						});
						var hid = "task_" + a[1];
						if (document.getElementById(hid)) {
							document.getElementById(hid).style.backgroundColor = "#ffffcc";
							setTimeout(function() {
								document.getElementById(hid).style.backgroundColor = "#FFFFFF";
							},
							3000);
						} else {
							i18n.getJSAlertValue(Utils.zuid, "zp.projcal.taskaddsucc", null,
							function(mesg) {
								displayFadeMsg(mesg);
							});
						}
					} else {
						i18n.getJSAlertValue(Utils.zuid, "zp.projcal.taskaddsucc", null,
						function(mesg) {
							displayFadeMsg(mesg);
						});
					}
					var Id = _8cf.split("_")[2];
					if (document.getElementById("todotask_" + Id)) {
						document.getElementById("todotask_" + Id).value = "";
						addtaskowner = "";
						i18n.getJSAlertValue(Utils.zuid, "zp.general.anyuser", null,
						function(mesg) {
							document.getElementById("personres_" + Id).innerHTML = mesg;
						});
						document.getElementById("todotask_" + Id).focus();
					}
				}
				if (url.indexOf("manageresource.do") != -1) {
					var _8df = document.getElementById(_8cf);
					_8df.innerHTML = text;
					ini = false;
					pmevalScript(_8df);
					resourceLoader();
				}
				if (url.indexOf("movefile.do") != -1) {
					i18n.getJSAlertValue(Utils.zuid, "zp.doc.movefile", null,
					function(mesg) {
						displayFadeMsg(mesg);
					});
				}
				Hide("ajax_load_tab");
				Hide("mppimp_tab");
				if (url.indexOf("makedefaultportal.do") == -1) {
					initLightbox();
				}
				if (url.indexOf("/clientcompanyprofile.do") != -1) {
					focusDiv("mainCompName");
				}
			}
		};
		_8d0.send(_8d2.args);
	}
}
function manipulateCardPageIframe(_8f3, _8f4, _8f5, _8f6, _8f7, _8f8) {
	if (_8f8.wiki && _8f8.wiki.checked && _8f4 != "-1") {
		_8f3 = _8f3 + "&AddOnID=" + _8f4 + "&AddOnCount=1";
	}
	if (_8f8.chat && _8f8.chat.checked && _8f4 != "-1") {
		_8f3 = _8f3 + "&AddOnID=" + _8f5 + "&AddOnCount=1";
	}
	if ((_8f8.bug && _8f8.bug.checked && _8f6 != "-1") || (_8f8.ybug && _8f8.ybug.checked && _8f6 != "-1")) {
		_8f3 = _8f3 + "&AddOnID=" + _8f6 + "&AddOnCount=1";
	}
	if (_8f8.wikichat && _8f8.wikichat.checked && _8f7 != "-1") {
		_8f3 = _8f3 + "&AddOnID=" + _8f7 + "&AddOnCount=1";
	}
	var mode = _8f8.mode.value;
	var _8fa = "1";
	if (mode == "yearly") {
		_8fa = "4";
	}
	_8f3 = _8f3 + "&PAYPERIOD=" + _8fa;
	document.getElementById("storeFreetoPaidIframe").src = _8f3;
}
function ajaxShowImgPage(url, _8fc, _8fd, _8fe, _8ff, _900, _901, _902, _903, _904, _905, _906, mode) {
	if (url.indexOf("bugreportlist.do") != -1) {
		url = url + "&range=" + bugreportviwrange;
	}
	var _908 = url;
	if (_8fd != "" && _8fd != null) {
		var args = "?" + getFormValues(_8fd);
		_908 = url + args;
		var _90a = _8fd.name;
	} else {
		_908 = url;
	}
	var _908 = fixForCachingInIe(_908);
	var _90b = getURLArgs(_908);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _90b.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				if (url.indexOf("genreport.do") != -1 || url.indexOf("gendatereport.do") != -1 || url.indexOf("mytasksreport.do") != -1) {
					if (http.responseText.indexOf("##invalid duration") != -1) {
						alert(http.responseText.split("##")[0]);
						Hide("ajax_load_tab");
						return false;
					}
					if (_8fc != null) {
						var text = http.responseText;
						var _90e = document.getElementById(_8fc);
						_90e.innerHTML = text;
						ini = false;
						pmevalScript(_90e);
						Hide("ajax_load_tab");
						if (document.getElementById("taskganttchart")) {
							ganttChartLoader();
						}
					} else {
						var _90f = document.getElementById("printdetails").innerHTML;
						for (var j = 0; j < _8fe; j++) {
							_8ff.loadData(Utils.domainappender + "jsp/project/mainGanttLoader.jsp?projid=" + _900 + "&zoid=" + _901 + "&coid=" + _902 + "&username=" + _903 + "&statustype=" + _904 + "&filename=" + "print_" + j + "_" + _905 + "&viewtype=" + _906, true, true);
							jQuery("#divforchartprint").css("page-break-after", "always");
							_8ff.panelTime.style.overflow = "";
							_8ff.oData.style.overflow = "";
							_8ff.panelNames.style.overflow = "";
							document.getElementById("divforchartprint").style.height = parseInt(_8ff.oData.firstChild.style.height) + 50 + "px";
							_8ff.arrProjects[0].projectItem[0].style.display = "none";
							_8ff.arrProjects[0].projectNameItem.style.display = "none";
							if (_8ff.oData.firstChild.offsetHeight < _8ff.oData.offsetHeight) {
								_8ff.oData.firstChild.style.height = _8ff.oData.offsetHeight + "px";
							}
							_90f = _90f + document.getElementById("printdivid").innerHTML;
						}
						var _911 = "<head><style>  .taskPanelBorder{border-width: 2px 2px 2px 2px;border-style:solid;border-color: #737373;} .taskName{font-family: Tahoma, Arial; font-weight: bold;font-size: 11px;color: #FFFFFF;cursor: pointer;white-space: nowrap;} .moveInfo{font-family: Tahoma, Arial;font-size: 10px;color:#006600;white-space:nowrap;} .ganttdescTask {color:#333333;cursor:default;font-family:arial;font-size:11px;white-space:nowrap;} .descProject{font-family: Tahoma, Arial;font-size: 10px;color:#006600;cursor: default;white-space: nowrap;} .ganttdayNumber, .ganttmonthName {color:#333333;font-family:arial;font-size:10px;font-weight:bold;text-align:center;vertical-align:middle;} .ganttmonthName {border-top:1px solid #f1f3f1; border-bottom:1px solid #f1f3f1; border-left:1px solid #f1f3f1;text-align:left;padding-left:5px;} .poPupInfo{background: #FFFFFF;width  : 170px;border: 1px dotted #279e00;padding: 4px 6px 4px 6px;float: left;} .poPupTime{background:#FFFFFF;border: 1px dotted #279e00;height : 25px;width  : 70px;position: absolute;z-index:2;} .contextMenu{z-index:10;width:150px;cursor:pointer;font-family: Tahoma, Arial;font-size:12px;color:#7D7D7D;border: 1px solid #808080;} .gantttaskNameItem {color:#333333;font-family:arial;font-size:11px;font-weight:normal;} .panelErrors{;padding: 4px 6px 4px 6px;font-family: Tahoma, Arial;font-size: 12px;color: red;white-space: nowrap;} .st {font-family: Arial, Helvetica, Sans-serif; font-size: 10px; font-weight: normal; color: #688060;} .ut {font-family: Arial, Helvetica, Sans-serif; font-size: 11px; font-weight: normal; color: #323232;} .lt {font-family: Arial, Helvetica, Sans-serif; font-size: 11px; font-weight: normal; color: #323232; padding: 0px 0px 0px 14px; margin: 0px; display: block;} .popload { position: fixed; text-align: center; top: 50%; left: 44%; z-index:90; _filter: Alpha(Opacity=70); _position: absolute; } </style></head>";
						Utils.projbarwidth = null;
						_90f = "<html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><body>" + _911 + _90f + "</body></meta></html>";
						jQuery("#divforchartprint").remove();
						if (mode == "print") {
							document.getElementById("pdfinputservlet").value = _90f;
							Hide("ajax_load_tab");
							window.open(Utils.contPath + "/ganttprint.do");
						} else {
							if (mode == "pdf") {
								document.getElementById("pdfinputservlet").value = _90f;
								Hide("ajax_load_tab");
								sendActionPdf(Utils.contPath + "/genchartPdf");
							}
						}
					}
				} else {
					var text = http.responseText;
					var _90e = document.getElementById(_8fc);
					_90e.innerHTML = text;
					ini = false;
					pmevalScript(_90e);
					if (url.indexOf("manageresource.do") != -1) {
						resourceLoader();
					}
					Hide("ajax_load_tab");
				}
			}
		};
		http.send(_90b.args);
	}
}
function ajaxConstructPage(url, _913) {
	var _914 = fixForCachingInIe(url);
	var _915 = getURLArgs(_914);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _915.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				ini = false;
				if (url.indexOf("consteditlinkpage.do") != -1) {
					var _918 = document.getElementById("edit_link_div");
					_918.innerHTML = text;
					ShowGenBlock("edit_link_div");
				}
				if (url.indexOf("changemailaddress.do") != -1) {
					var _918 = document.getElementById("mailurl_" + _913);
					if (_918) {
						_918.innerHTML = text;
						_918.href = "mailto:" + text;
					}
					var _919 = document.getElementById("mailurl_" + _913 + "_1");
					if (_919) {
						_919.innerHTML = text;
						_919.href = "mailto:" + text;
					}
					var _91a = document.getElementById("mailurl_" + _913 + "_2");
					if (_91a) {
						_91a.innerHTML = text;
						_91a.href = "mailto:" + text;
					}
				}
				if (url.indexOf("makeastask.do") != -1) {
					var _918 = document.getElementById(_913);
					if (is_ie) {
						jQuery("#" + _913).replaceWith("<tbody id=\"" + _913 + "\">" + text + "</tbody>");
						bindMoT(_913);
					} else {
						if (_918) {
							_918.innerHTML = text;
						}
					}
					ini = false;
					pmevalScript(_918);
					return null;
				}
				if (url.indexOf("listofportals.do") != -1) {
					var _918 = document.getElementById(_913);
					if (_918) {
						_918.innerHTML = text;
					}
					showMPMenu();
					ShowGenInline("pmenu");
					ShowHideInline("switchportalicon", "loadingportallist");
					var _91b = jQuery("#myportalid").offset();
					var _91c = _91b.left - 60;
					var _91d = _91b.top - 5;
					document.getElementById("plist").style.cssText = "z-index: 800;position: absolute;top:" + _91d + "px;left:" + _91c + "px";
				}
				if (url.indexOf("addnewprojuser.do") != -1) {
					var doc = document;
					var stid = doc.getElementById("newuser_status");
					if (text == "User already added to this organization") {
						i18n.getJSAlertValue(Utils.zuid, "zp.newuser.useralreadyadded", null,
						function(mesg) {
							stid.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
					} else {
						if (text == "User already added as client to this organization") {
							i18n.getJSAlertValue(Utils.zuid, "zp.newuser.useraddasclient", null,
							function(mesg) {
								stid.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
						} else {
							if (text.indexOf("digest=") == 0) {
								var _922 = text.split("digest=")[1];
								i18n.getJSAlertValue(Utils.zuid, "zp.user.wrongcaptcha", null,
								function(mesg) {
									stid.innerHTML = "<span class=\"error\">" + mesg + "</span>";
								});
								doc.getElementById("addusercaptcha").value = "";
								doc.getElementById("adduserdigest").value = _922;
								doc.getElementById("addusercaptcha").focus();
								var src = doc.getElementById("captchaimage").src;
								doc.getElementById("captchaimage").src = src.split("=")[0] + "=" + _922;
							} else {
								document.getElementById(_913).innerHTML = text;
							}
						}
					}
				}
				if (url.indexOf("fetchownertaskcnt.do") != -1) {
					Hide("trid_" + _913);
					Hide("ul_ttask_" + _913);
					Hide("ul_tfoot_" + _913);
					var _918 = document.getElementById("chartview_" + _913);
					_918.innerHTML = text;
					document.getElementById("chartview_" + action_id).className = "pl36 pB10";
				}
				if (url.indexOf("constdocform.do") != -1) {
					ShowGenInline(_913);
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
				}
				if (url.indexOf("consttododepend.do") != -1) {
					Hide("otaskcbox_" + _913);
					ShowHideBlock("addtododepend_" + _913, "dispttask_" + _913);
					var _918 = document.getElementById("addtododepend_" + _913);
					_918.innerHTML = text;
					pmevalScript(_918);
				}
				if (url.indexOf("constupdtodotask.do") != -1) {
					if (document.getElementById("dispttask_" + _913)) {
						ShowHideBlock("editttask_" + _913, "dispttask_" + _913);
					} else {
						ShowGenInline("editttask_" + _913);
					}
					var _918 = document.getElementById("editttask_" + _913);
					_918.innerHTML = text;
					eval("document.updateTodoTask_" + _913 + ".todotask.focus()");
					pmevalScript(_918);
				}
				if (url.indexOf("userprofile.do") != -1) {
					var _918 = document.getElementById("editcompuser_" + _913);
					_918.innerHTML = text;
					ShowHideBlock("editcompuser_" + _913, "dispuser_" + _913);
				}
				if (url.indexOf("constupdmilestone.do") != -1) {
					ShowGenInline(_913);
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
					Hide("rhscommon");
					if (url.indexOf("constupdmile.do") != -1) {
						Hide("mile_display");
					}
				}
				if (url.indexOf("constupdlist.do") != -1) {
					ShowHideBlock("tlistedit_" + _913, "disptlist_" + _913);
					var _918 = document.getElementById("tlistedit_" + _913);
					_918.innerHTML = text;
					eval("document.editTodoList_" + _913 + ".todotitle.focus()");
				}
				if (url.indexOf("constmovetask.do") != -1) {
					ShowGenInline("showmovetask_" + _913);
					var _918 = document.getElementById("showmovetask_" + _913);
					_918.innerHTML = text;
				}
				if (url.indexOf("constcommentform.do") != -1) {
					ShowHideBlock("editcomment_" + _913, "dispcomment_" + _913);
					var _918 = document.getElementById("editcomment_" + _913);
					_918.innerHTML = text;
					pmevalScript(_918);
					eval("document.updateComment_" + _913 + ".commbody.focus()");
				}
				if (url.indexOf("constaddtasknotes.do") != -1) {
					ShowHideBlock("addtasknotesForm_" + _913, "tnoteaddaction_" + _913);
					var _918 = document.getElementById("addtasknotesForm_" + _913);
					_918.innerHTML = text;
					eval("document.addTaskNotes_" + _913 + ".notes.focus()");
				}
				if (url.indexOf("constexptsheet.do") != -1) {
					jQuery("#projectcontent").append(text);
					Hide("ajax_load_tab");
					callCalendars("etsstartdate", "estartdatecal", "etsenddate", "etenddatecal");
				}
				if (url.indexOf("constexptask.do") != -1) {
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
					var pos = jQuery("#txid").offset();
					jQuery("#" + _913).css({
						"top": (pos.top - 50) + "px",
						"left": (pos.left - 250) + "px"
					});
				}
				if (url.indexOf("constalltsexport.do") != -1 || url.indexOf("constglobalexport.do") != -1 || url.indexOf("constadduser.do") != -1) {
					if (document.getElementById("lvhide") != null) {
						ShowHideInline(_913, "lvhide");
					} else {
						ShowGenInline(_913);
					}
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
				}
				if (url.indexOf("consttempaddtask.do?tasktype=task") != -1) {
					ShowGenInline("showtempaddtask");
					var _918 = document.getElementById("showtempaddtask");
					if (_918) {
						var pos = jQuery("#addtasktemptask_" + _913).offset();
						var _926 = pos.left + 100;
						var _927 = pos.top - 65;
						_918.style.cssText = "position: absolute; left:" + _926 + "px; top:" + _927 + "px; z-index:1;";
					}
					_918.innerHTML = text;
					scrollInViewPart("showtempaddtask");
					document.getElementById("newtodotask").focus();
				}
				if (url.indexOf("consttempaddtask.do?tasktype=subtask") != -1) {
					ShowGenInline("showtempaddtask");
					var _918 = document.getElementById("showtempaddtask");
					if (_918) {
						var pos = jQuery("#disptemptask_" + _913).offset();
						var _926 = pos.left + 100;
						var _927 = pos.top - 65;
						_918.style.cssText = "position: absolute; left:" + _926 + "px; top:" + _927 + "px; z-index:1;";
					}
					_918.innerHTML = text;
					scrollInViewPart("showtempaddtask");
					document.getElementById("newtodotask").focus();
				}
				if (url.indexOf("consttempupdlist.do") != -1) {
					ShowGenInline("tlistedit");
					var _928 = document.getElementById("disptlist_" + _913);
					var _918 = document.getElementById("tlistedit");
					if (_918) {
						var pos = jQuery("#disptlist_" + _913).offset();
						var _926 = pos.left + 160;
						var _927 = pos.top - 70;
						_918.style.cssText = "position: absolute; left:" + _926 + "px; top:" + _927 + "px; z-index:1;";
					}
					_918.innerHTML = text;
					scrollInViewPart("tlistedit");
					eval("document.editTempTodoList_" + _913 + ".temptodotitle.focus()");
				}
				if (url.indexOf("consttempupdtask.do?type=update") != -1) {
					var _928 = document.getElementById("disptemptask_" + _913);
					ShowGenInline("edittemptask");
					var _918 = document.getElementById("edittemptask");
					if (_918) {
						var pos = jQuery("#disptemptask_" + _913).offset();
						var _926 = pos.left + 150;
						var _927 = pos.top - 70;
						_918.style.cssText = "position: absolute; left:" + _926 + "px; top:" + _927 + "px; z-index:1;";
					}
					_918.innerHTML = text;
					scrollInViewPart("edittemptask");
					eval("document.editTempTodoTask_" + _913 + ".todotask.focus()");
				}
				if (url.indexOf("consttempupdtask.do?type=move") != -1) {
					ShowGenBlock("movetemptask_" + _913);
					var _918 = document.getElementById("movetemptask_" + _913);
					_918.innerHTML = text;
				}
				if (url.indexOf("constcopytodotask.do") != -1) {
					ShowGenInline("showcopytask_" + _913);
					var _918 = document.getElementById("showcopytask_" + _913);
					_918.innerHTML = text;
				}
				if (url.indexOf("constworkdaylog.do") != -1) {
					ShowHideInline(_913, "export_timesheet");
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
				}
				if (url.indexOf("consworkprojtask.do") != -1) {
					var _918 = document.getElementById("workhidetask_" + _913);
					_918.innerHTML = text;
					pmevalScript(_918);
				}
				if (url.indexOf("constdeptodotask.do") != -1 || url.indexOf("constdeptodouser.do") != -1 || url.indexOf("loopcalendar.do") != -1 || url.indexOf("conslogtime.do") != -1 || url.indexOf("constaddmile.do") != -1 || url.indexOf("constclientcomp.do") != -1 || url.indexOf("weeklylogtime.do") != -1) {
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
					if (url.indexOf("weeklylogtime.do") != -1) {
						var _929 = document.getElementById("weeklogtr_0").innerHTML;
						for (var i = 1; i <= 5; i++) {
							AddWeeklyLogRow(_929, i);
						}
						jQuery("[class='weeklogtr']")[0].style.display = "none";
					}
					if (url.indexOf("constaddmile.do") != -1) {
						callCalendars("f_date_c_st", "f_trigger_c_st", "f_date_c", "f_trigger_c");
					}
					Hide("ajax_load_tab");
					pmevalScript(_918);
				}
				if (url.indexOf("constupdmile.do") != -1) {
					var _918 = document.getElementById("updatemile");
					_918.innerHTML = text;
					var pos = jQuery("#" + _913).offset();
					var _92b = pos.left + 150;
					var _92c = pos.top - 66;
					jQuery("#updatemile").css({
						"left": _92b + "px",
						"top": _92c + "px"
					});
					jQuery("#updatemile").show();
					scrollInViewPart("updatemile");
				}
				if (url.indexOf("conslog.do") != -1) {
					var _92b = 0;
					var _92c = 0;
					var pos = 0;
					var _918 = document.getElementById("addtasklog");
					_918.innerHTML = text;
					if (_913 == "addloglink") {
						pos = jQuery("#addloglink").offset();
						_92b = pos.left + 50;
						_92c = pos.top - 56;
						jQuery("#addtasklog").css({
							"left": _92b + "px",
							"top": _92c + "px"
						});
						jQuery("#addtasklog").show();
					} else {
						if (_913 == "logbutton") {
							pos = jQuery("#logbutton").offset();
							_92c = pos.top - 62;
							jQuery("#addtasklog").css({
								"left": "",
								"right": "24%",
								"top": _92c + "px"
							});
							jQuery("#addtasklog").show();
						} else {
							if (_913.indexOf("logcaltd") != -1) {
								pos = jQuery("#" + _913).offset();
								_92b = pos.left + 24;
								_92c = pos.top - 52;
								jQuery("#addtasklog").css({
									"left": _92b + "px",
									"top": _92c + "px"
								});
								jQuery("#addtasklog").show();
							}
						}
					}
					callCal("logdate", "log_cal");
					Hide("ajax_load_tab");
					scrollInViewPart("addtasklog");
					return false;
				}
				if (url.indexOf("addtopmilestone.do") != -1) {
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
				}
				if (url.indexOf("contoptodolist.do") != -1) {
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
					EmptyDivContent("tlist_top_add_status");
					document.addTodoList.todotitle.focus();
				}
				if (url.indexOf("addtoptodotask.do") != -1) {
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
					document.addTodoTaskForm.todotask.focus();
				}
				if (url.indexOf("consttaskdocmap.do") != -1) {
					ShowGenInline("addtaskdoc_" + _913);
					var _918 = document.getElementById("addtaskdoc_" + _913);
					_918.innerHTML = text;
				}
				if (url.indexOf("constmeetdocmap.do") != -1) {
					var _918 = document.getElementById("addmeetdoc");
					_918.innerHTML = text;
					pmevalScript(_918);
					var pos = jQuery("#mdocslink_" + _913).offset();
					var _92b = pos.left - 40;
					var _92c = pos.top + 16;
					jQuery("#addmeetdoc").css({
						"left": _92b + "px",
						"top": _92c + "px",
						"z-index": "1",
						"position": "absolute"
					});
					jQuery("#addmeetdoc").show();
					scrollInViewPart("addmeetdoc");
				}
				if (url.indexOf("consttaskforummap.do") != -1) {
					ShowGenInline("addtaskforum_" + _913);
					var _918 = document.getElementById("addtaskforum_" + _913);
					_918.innerHTML = text;
				}
				if (url.indexOf("fetchtasknotes.do") != -1) {
					var _918 = document.getElementById("notes_" + _913);
					_918.innerHTML = text;
					setShowHide("notes_" + _913);
				}
				if (url.indexOf("fetchmeetnotes.do") != -1) {
					var _918 = document.getElementById("showmeetnotes");
					_918.innerHTML = text;
					pmevalScript(_918);
					var pos = jQuery("#mnoteslink_" + _913).offset();
					var _92b = pos.left;
					var _92c = pos.top + 16;
					jQuery("#showmeetnotes").css({
						"left": _92b + "px",
						"top": _92c + "px",
						"z-index": "1",
						"position": "absolute"
					});
					jQuery("#showmeetnotes").show();
					scrollInViewPart("showmeetnotes");
				}
				if (url.indexOf("addtasklogs.do") != -1) {
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
				}
				if (url.indexOf("fetchdocdetails.do") != -1) {
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
				}
				if (url.indexOf("fetchdocnotes.do") != -1) {
					var res = _913;
					var res1 = _913;
					if (_913.indexOf("shownotes_mn_") != -1) {
						res1 = _913.split(":");
						res = res1[0];
					}
					var _918 = document.getElementById("shownotes_" + res);
					_918.innerHTML = text;
					pmevalScript(_918);
					ShowGenInline("shownotes_" + res);
					if (_913.indexOf("shownotes_mn_") != -1) {
						new Effect.ScrollTo(res1[1], {
							duration: 0.3
						});
						document.getElementById(res1[1]).style.background = "#FAFABB";
						setTimeout(function() {
							document.getElementById(res1[1]).style.background = "";
						},
						3000);
					}
				}
				if (url.indexOf("constpage.do") != -1) {
					if (document.getElementById("addmeet")) {
						Hide("maincontent");
					}
					if (document.getElementById("addtodotask")) {
						Hide("maincontent");
						Hide("selectedevents");
					}
					if (_913 == "addtaskdiv") {
						ShowHideInline("addtaskdiv", "todolist_div");
					}
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
					if (url.indexOf("constpage.do?frompage=addtoptodo") != -1) {
						spcal("task_st_cal", "ttaskdate", "task_end_cal", "taskenddate", "addtask");
					} else {
						callCal("schdate", "app_trigger_c");
					}
				}
				if (url.indexOf("constquickaddmeet.do") != -1) {
					if (url.indexOf("frompage=projcalmeet") != -1) {
						ShowGenInline("addcalmeeting");
						var _918 = document.getElementById("addcalmeeting");
						_918.innerHTML = text;
						if (_918) {
							var pos = jQuery("#" + _913).offset();
							var _926 = pos.left - 40;
							var _927 = pos.top + 2;
							var _92f = jQuery(document).height();
							var _930 = jQuery(document).width();
							var _931 = _92f - (pos.top);
							var _932 = _930 - (pos.left);
							if (_932 > 1000 && _931 > 650) {
								Hide("meetlarrowtop");
								_926 = _926 + 60;
								var _933 = jQuery("#meettarrowtop").offset();
								_933.left = _933.left + 15;
								jQuery("#meettarrowtop").css({
									"left": +_933.left + "px",
									"top": "5px"
								});
							}
							if (_931 < 550 && _931 > 0) {
								_927 = _927 - 524;
								_926 = _926 + 100;
								ShowHideInline("meetlarrowbottom", "meetlarrowtop");
								Hide("meettarrowtop");
							} else {
								ShowHideInline("meettarrowtop", "meetlarrowtop");
							}
							_918.style.cssText = "position: absolute; left:" + _926 + "px; top:" + _927 + "px; z-index:1;";
						}
						scrollInViewPart("addcalmeeting");
					}
					if (url.indexOf("frompage=addmeet") != -1) {
						ShowGenInline("addquickmeeting");
						var _918 = document.getElementById("addquickmeeting");
						if (_918) {
							var pos = jQuery("#addmeetId").offset();
							var _926 = pos.left - 310;
							var _927 = pos.top - 40;
							_918.style.cssText = "position: absolute; left:" + _926 + "px; top:" + _927 + "px; z-index:1;";
						}
						_918.innerHTML = text;
						scrollInViewPart("addquickmeeting");
					}
					document.getElementById("newmeeting").focus();
					callCal("schdate", "app_trigger_c");
				}
				if (url.indexOf("constcopytask.do") != -1 || url.indexOf("constupdtask.do") != -1 || url.indexOf("constedittask.do") != -1 || url.indexOf("constmovetlist.do") != -1) {
					if (document.getElementById("updatetaskdiv") && document.getElementById("task_contpage") && url.indexOf("constupdtask.do") != -1) {
						var _918 = document.getElementById(_913);
						_918.innerHTML = text;
						ShowHideInline("updatetaskdiv", "task_contpage");
					} else {
						if (document.getElementById("updatetaskdiv") && document.getElementById("task_contpage") && url.indexOf("constedittask.do") != -1) {
							var _918 = document.getElementById("updatetaskdiv");
							_918.innerHTML = text;
							var tlId = _913;
							var _935 = "anch_ul_ttask_" + tlId;
							var pos = jQuery("#" + _935).offset();
							var _92c = pos.top - 70;
							var _92b = pos.left + 38;
							var _92f = jQuery(document).height();
							var _931 = _92f - (pos.top);
							if (_931 < jQuery("#updatetaskdiv").height() && _931 > 0 && (pos.top > jQuery("#updatetaskdiv").height())) {
								ShowHideInline("larrowbottom", "larrowtop");
								_92c = pos.top - (jQuery("#updatetaskdiv").height() - 93);
							} else {
								ShowHideInline("larrowtop", "larrowbottom");
							}
							jQuery("#updatetaskdiv").css({
								"left": _92b + "px",
								"top": _92c + "px"
							});
							jQuery("#updatetaskdiv").show();
							scrollInViewPart("updatetaskdiv");
							pmevalScript(_918);
							spcal("totaskcal_" + tlId, "totaskdate_" + tlId, "task_end_cal_" + tlId, "taskenddate_" + tlId, "edittask");
						} else {
							if (document.getElementById("addtaskdiv") && document.getElementById("todolist_div") && (url.indexOf("constcopytask.do") != -1 || url.indexOf("constmovetlist.do") != -1)) {
								var _918 = document.getElementById("addtaskdiv");
								_918.innerHTML = text;
								var tlId = _913;
								var _935 = "tlistnameid_" + tlId;
								var pos = jQuery("#" + _935).offset();
								var _92c = pos.top + 5;
								var _92b = pos.left + 20;
								if (url.indexOf("constcopytask.do") != -1 || url.indexOf("constmovetlist.do") != -1) {
									_92c = pos.top + 15;
								}
								var _92f = jQuery(document).height();
								var _931 = _92f - (pos.top);
								jQuery("#addtaskdiv").css({
									"left": _92b + "px",
									"top": _92c + "px"
								});
								jQuery("#addtaskdiv").show();
								scrollInViewPart("addtaskdiv");
							}
						}
					}
				}
				if (url.indexOf("consteditlist.do") != -1) {
					var _918 = document.getElementById("addtaskdiv");
					_918.innerHTML = text;
					var tlId = _913;
					var _935 = "tlistnameid_" + tlId;
					var pos = jQuery("#" + _935).offset();
					var _92c = pos.top + 20;
					var _92b = pos.left + 20;
					var _92f = jQuery(document).height();
					var _931 = _92f - (pos.top);
					jQuery("#addtaskdiv").css({
						"left": _92b + "px",
						"top": _92c + "px"
					});
					jQuery("#addtaskdiv").show();
					scrollInViewPart("addtaskdiv");
					eval("document.editTodoList_" + _913 + ".todotitle.focus()");
				}
				if (url.indexOf("constmvtask.do") != -1) {
					if (url.indexOf("bulkmove") != -1) {
						var pos = jQuery("#bmove_link_" + _913).offset();
						var _927 = Math.round(eval(pos.top) - 4);
						var _926 = Math.round(eval(pos.left));
						_927 = _927 + 20;
						_926 = _926 - 45;
						jQuery("#addtaskdiv").css({
							"top": _927 + "px",
							"left": _926 + "px"
						});
						jQuery("#addtaskdiv").show();
						scrollInViewPart("addtaskdiv");
						var _918 = document.getElementById("addtaskdiv");
						_918.innerHTML = text;
					} else {
						var _918 = document.getElementById(_913);
						_918.innerHTML = text;
						ShowGenInline(_913);
					}
				}
				if (url.indexOf("constaddtlist.do") != -1 || url.indexOf("conaddtask.do") != -1 || url.indexOf("constaddlist.do") != -1 || url.indexOf("consttlist.do") != -1 || url.indexOf("conprojuser.do") != -1) {
					var _918 = document.getElementById(_913);
					if (url.indexOf("conaddtask.do") != -1) {
						_918 = document.getElementById("addtodo");
					}
					if (url.indexOf("frompage=projcaltask") != -1) {
						ShowGenInline("addcaltask");
						_918 = document.getElementById("addcaltask");
					}
					if (url.indexOf("conaddtask.do?frompage=resourcetask") != -1) {
						_918 = document.getElementById("resourceTaskDiv");
					}
					var _936 = jQuery("#addtodo").height();
					_918.innerHTML = text;
					if (url.indexOf("conprojuser.do") != -1) {
						var _937 = jQuery("#addtodo").height();
						if (document.getElementById("larid")) {
							var _938 = (document.getElementById("larid").style.top).split("px")[0];
							var _939 = _938 - (_937 - _936);
							jQuery("#larid").css({
								"top": _939 + "px"
							});
						}
						scrollInViewPart("addtodo");
					}
					if (url.indexOf("conaddtask.do?frompage=projcaltask") != -1) {
						var pos = jQuery("#" + _913).offset();
						var _926 = pos.left - 75;
						var _927 = pos.top;
						var _92f = jQuery(document).height();
						var _930 = jQuery(document).width();
						var _931 = _92f - (pos.top);
						var _932 = _930 - (pos.left);
						if (_932 > 1000 && _931 > 650) {
							_926 = _926 + 60;
							var _933 = jQuery("#tarrowtop").offset();
							_933.left = _933.left + 15;
							jQuery("#tarrowtop").css({
								"left": +_933.left + "px",
								"top": "5px"
							});
						}
						if (_931 < 550 && _931 > 0) {
							_927 = _927 - 370;
							_926 = _926 + 100;
							ShowHideInline("larrowbottom", "larrowtop");
							Hide("tarrowtop");
						} else {
							ShowHideInline("tarrowtop", "larrowtop");
						}
						_918.style.cssText = "position: absolute; left:" + _926 + "px; top:" + _927 + "px; z-index:1;";
						scrollInViewPart("addcaltask");
						document.getElementById("todotask").focus();
					}
					if (url.indexOf("conaddtask.do?frompage=resourcetask") != -1) {
						Hide("ajax_load_tab");
						var _93a = jQuery(document).width();
						var _927 = jQuery("#resourceDiv").offset().top + 40;
						var _926 = parseInt(_913.split("_")[0]);
						var _93b = parseInt(_913.split("_")[1]);
						var _93c = jQuery("#resourceTaskDiv").height();
						var _93d = parseInt(_913.split("_")[2]) - _927 - 10;
						if ((_93d + 20) > _93c) {
							_927 = _927 + (_93d + 20 - _93c);
							_93d = _93c - 20;
						}
						if (_926 < _93a / 2) {
							_926 = _926 + 24 - (_93b % 24);
							ShowGenInline("reslarrow");
							jQuery("#reslarrow").css("top", _93d);
						} else {
							ShowGenInline("resrarrow");
							jQuery("#resrarrow").css("top", _93d);
							var _93e = jQuery("#resourceTaskDiv").width();
							_926 = _926 - _93e - (_93b % 24);
						}
						_918.style.cssText = "position: absolute;left:+" + _926 + "px;" + "top:" + _927 + "px;z-index:4;";
						document.getElementById("todotask").focus();
					}
					if (url.indexOf("constaddlist.do") != -1) {
						var pos = jQuery("#ntlid").offset();
						jQuery("#" + _913).css({
							"top": (pos.top - 48) + "px",
							"left": (pos.left - 240) + "px"
						});
						jQuery("#" + _913).show();
					}
					if (url.indexOf("conaddtask.do?frompage=addtodo") != -1) {
						var tlId = _913.split("_")[1];
						var _935 = "beforeaddtodo_" + tlId;
						var pos = jQuery("#" + _935).offset();
						var _92c = pos.top - 70;
						var _92b = pos.left + 38;
						var _92f = jQuery(document).height();
						var _931 = _92f - (pos.top);
						if (_931 < jQuery("#addtodo").height() && _931 > 0) {
							ShowHideInline("larrowbottom", "larrowtop");
							_92c = pos.top - 300;
						} else {
							ShowHideInline("larrowtop", "larrowbottom");
						}
						jQuery("#addtodo").css({
							"left": _92b + "px",
							"top": _92c + "px"
						});
						jQuery("#addtodo").show();
						scrollInViewPart("addtodo");
						document.getElementById("todotask").focus();
					} else {
						if (url.indexOf("conaddtask.do?frompage=addtoptodo") != -1) {
							var pos = jQuery("#ntid").offset();
							var _92c = pos.top - 48;
							var _92b = pos.left - 300;
							jQuery("#addtodo").css({
								"top": _92c + "px",
								"left": _92b + "px"
							});
							jQuery("#addtodo").show();
							scrollInViewPart("addtodo");
							document.getElementById("todotask").focus();
						} else {
							if (url.indexOf("conaddtask.do?issubtask=yes") != -1) {
								var _93f = _913.split("_")[1];
								var _935 = "anch_ul_ttask_" + _93f;
								var pos = jQuery("#" + _935).offset();
								var _92b = pos.left + 20;
								var _92c = pos.top - 70;
								var _92f = jQuery(document).height();
								var _931 = _92f - (pos.top);
								if (_931 < 150 && _931 > 0) {
									ShowHideInline("larrowbottom", "larrowtop");
									_92c = pos.top - 300;
								} else {
									ShowHideInline("larrowtop", "larrowbottom");
								}
								jQuery("#addtodo").css({
									"left": _92b + "px",
									"top": _92c + "px"
								});
								jQuery("#addtodo").show();
								document.getElementById("todotask").focus();
							}
						}
					}
					if (url.indexOf("conaddtask.do") != -1) {
						spcal("task_st_cal", "ttaskdate", "task_end_cal", "taskenddate", "addtask");
					}
				}
				if (url.indexOf("constupdmeeting.do") != -1) {
					ShowGenInline("updatemeeting");
					var _918 = document.getElementById("updatemeeting");
					_918.innerHTML = text;
					if (_918) {
						var pos = jQuery("#updmeetlinkId_" + _913).offset();
						var _926 = pos.left + 70;
						var _927 = pos.top - 111;
						var _92f = jQuery(document).height();
						var _931 = _92f - (pos.top);
						if (_931 < 400 && _931 > 0) {
							_927 = _927 - 435;
							_926 = _926 + 60;
							ShowHideInline("larrowbottom", "larrowtop");
						}
						_918.style.cssText = "position: absolute; left:" + _926 + "px; top:" + _927 + "px; z-index:1;";
					}
					scrollInViewPart("updatemeeting");
					document.getElementById("newmeeting").focus();
					callCal("schdateupdate", "app_trigger_c_update");
				}
				if (url.indexOf("constquickaddmile.do") != -1) {
					var _940 = "";
					if (document.getElementById("addcalmilestone")) {
						_940 = "addcalmilestone";
					}
					if (document.getElementById("addtop_mile")) {
						_940 = "addtop_mile";
					}
					if (document.getElementById(_940)) {
						ShowGenInline(_940);
						var _918 = document.getElementById(_940);
						if (_918) {
							var pos = jQuery("#" + _913).offset();
							var _926 = 0;
							var _927 = 0;
							if (document.getElementById("addcalmilestone")) {
								_926 = pos.left - 53;
								_927 = pos.top + 2;
							}
							if (document.getElementById("addtop_mile")) {
								_926 = pos.left - 302;
								_927 = pos.top - 44;
							}
							_918.style.cssText = "position: absolute; left:" + _926 + "px; top:" + _927 + "px; z-index:1;";
						}
						_918.innerHTML = text;
						scrollInViewPart(_940);
						document.getElementById("mtitle").focus();
					}
					callCalendars("f_date_c_st", "f_trigger_c_st", "f_date_c", "f_trigger_c");
				}
				if (url.indexOf("bugcustomview.do") != -1 || url.indexOf("editcustomview.do") != -1 || url.indexOf("delcustomfilter.do") != -1) {
					var _918 = document.getElementById(_913);
					_918.innerHTML = text;
					pmevalScript(_918);
					Hide("ajax_load_tab");
				}
			}
		};
		http.send(_915.args);
	}
}
function ajaxShowTSImgPage(url, _942) {
	var _943 = fixForCachingInIe(url);
	var _944 = getURLArgs(_943);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _944.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				text = http.responseText;
				var _946 = document.getElementById(_942);
				_946.innerHTML = text;
				ini = false;
				pmevalScript(_946);
				if (url.indexOf("logevents.do?showts=day") != -1) {
					ShowGenInline("ts_thatday");
				} else {
					if (url.indexOf("logevents.do?showts=week") != -1 || url.indexOf("logevents.do?showts=month") != -1) {
						ShowGenInline("ts_thatweek");
					}
				}
			}
		};
		http.send(_944.args);
	}
}
function ajaxShowCalImgPage(url, _948) {
	var _949 = fixForCachingInIe(url);
	var _94a = getURLArgs(_949);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _94a.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _94d = document.getElementById(_948);
				_94d.innerHTML = text;
				ini = false;
				if (url.indexOf("myhome.do") != -1 || url.indexOf("mytodos.do") != -1 || url.indexOf("mylogtodos.do") != -1 || url.indexOf("myworkday.do") != -1) {
					Hide("zohobusy_homeload");
				}
				Hide("ajax_load_tab");
				pmevalScript(_94d);
				if (document.getElementById("addmilestone")) {
					Hide("addmilestone");
				}
			}
		};
		http.send(_94a.args);
	}
}
function ajaxShowBusyPage(url, _94f, _950) {
	ShowGenBlock(_950);
	var _951 = fixForCachingInIe(url);
	var _952 = getURLArgs(_951);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _952.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				if (validateRespMessage(text)) {
					var _955 = document.getElementById(_94f);
					_955.innerHTML = text;
					pmevalScript(_955);
					ini = false;
					Hide(_950);
				}
				initLightbox();
			}
		};
		http.send(_952.args);
	}
}
function ajaxSubmitPage(url, _957, _958) {
	var _959 = url;
	if (_957 != "") {
		var args = "?" + getFormValues(_957);
		_959 = url + args;
		var _95b = _957.name;
	} else {
		_959 = url;
	}
	_959 = fixForCachingInIe(_959);
	var _95c = getURLArgs(_959);
	var http = getHTTPObject();
	var _95e = document.getElementById("mainCompName");
	var _95f = document.getElementById("webaddr");
	var _960 = "general";
	var _961 = "hours";
	var _962 = "";
	if (url.indexOf("addloghours.do") != -1) {
		_960 = document.addLogHours.tsheettype.value;
		_961 = document.addLogHours.addhourstype.value;
		_962 = document.addLogHours.logdate.value;
	}
	if (http) {
		http.open("POST", _95c.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _964 = document.getElementById(_958);
				if (url.indexOf("/updatemyprofile.do") != -1) {
					if (text == "PROFILE UPDATED") {
						EmptyDivContent("prof_upd_status");
						ShowHideInline("zoho_upd_profile_message", "zoho_upd_profile_busy");
						var _965 = document.getElementById("chatsoundstatus");
						if (_965) {
							Utils.isChatSoundEnabled = _965.checked;
						}
						setTimeout(function() {
							Hide("zoho_upd_profile_message");
						},
						2000);
					} else {
						if (text == "PHOTO DELETED") {
							Hide("delphotolink");
							document.getElementById("userphotoimg").src = document.getElementById("userhiddenimg").src;
							ShowGenInline("zoho_del_photo_message");
							setTimeout(function() {
								Hide("zoho_del_photo_message");
							},
							2000);
						} else {
							_964.innerHTML = text;
						}
					}
				} else {
					if (url.indexOf("/adddbconfig.do") != -1) {
						if (text.indexOf("Error while configuration ||") != -1) {
							Hide("customloadingdiv");
							var _966 = document.getElementById("dropbox_config_status1");
							var _967 = text.split("||");
							_966.innerHTML = "<span class=\"error\">" + _967[1] + "</span>";
						} else {
							_964.innerHTML = text;
						}
						setTimeout(function() {
							Hide("customloadingdiv");
						},
						2000);
					} else {
						if (url.indexOf("/updateproject.do") != -1) {
							if (text == "zzzprojupdated||PROJECT DETAILS UPDATED") {
								ShowHideInline("zoho_upd_project_message", "zoho_proj_settings_busy");
								setTimeout(function() {
									Hide("zoho_upd_project_message");
								},
								2000);
								return false;
							} else {
								if (text == "zzzprojarchived||PROJECT ARCHIVED") {
									ShowHideInline("zoho_upd_project_archived", "zoho_proj_settings_busy");
									setTimeout(function() {
										Hide("zoho_upd_project_archived");
									},
									2000);
									return false;
								} else {
									if (text.indexOf("projstatus||") != -1) {
										var a = text.split("#");
										var pid = a[1];
										if (document.getElementById("ul_pId_" + pid)) {
											Hide("ul_pId_" + pid);
											ShowHideInline("activeprojdiv", "editprojdiv");
										}
										if (document.getElementById("clpId_" + pid)) {
											Hide("clpId_" + pid);
											ShowHideInline("activeprojectdiv", "editprojdiv");
										}
									} else {
										if (document.getElementById("activeprojdiv") && document.getElementById("editprojdiv")) {
											ShowHideInline("activeprojdiv", "editprojdiv");
										}
										if (document.getElementById("activeprojectdiv") && document.getElementById("editprojdiv")) {
											ShowHideInline("activeprojectdiv", "editprojdiv");
											document.getElementById("activeprojectdiv").focus();
										}
										ShowGenInline("projlinkId");
										_964.innerHTML = text;
									}
								}
							}
						} else {
							if (url.indexOf("addsubtask.do") != -1) {
								var _96a = "taskaction_" + _958;
								var id = text.split("||");
								var _96c = "task_" + id[1];
								var _96d = jQuery("table tr[task_" + _958 + "=" + _958 + "]");
								var _96e = jQuery("#taskaction_" + _958);
								if (_96d != null && _96d.length > 0) {
									var _96e = _96d[_96d.length - 1];
								}
								jQuery(_96e).after("<tr>" + text + "</tr>");
								jQuery("#taskaction_" + id[1]).insertAfter("#" + _96c);
								jQuery("#hidesubtask_" + _958).attr("isparent", "true");
								jQuery("#hidesubtask_" + _958).show();
								jQuery("#showsubtask_" + _958).attr("isparent", "true");
								var _96f = document.getElementById(_96c);
								pmevalScript(_96f);
								jQuery("#addtodo").hide();
								return null;
							} else {
								if (url.indexOf("updatetemptodotask.do") != -1) {
									jQuery("#" + _958).replaceWith("<tr>" + text + "</tr>");
									jQuery("#edittemptask").hide();
								} else {
									if (url.indexOf("addtemptodotask.do") != -1) {
										var _970 = _957.name;
										var _971 = eval("document." + _970 + ".tasktype.value");
										if (_971 == "subtask") {
											var _96a = "task_" + _958;
											jQuery("<tr>" + text + "</tr>").insertAfter("#" + _96a);
										}
										if (_971 == "task") {
											var _96a = "ul_ttask_" + _958;
											jQuery("#" + _96a).append("<tr>" + text + "</tr>");
										}
										jQuery("#showtempaddtask").hide();
									} else {
										if (endsWith(url, "updatepassword.do")) {
											EmptyDivContent("pass_upd_status");
											stid = document.getElementById("pass_upd_status");
											if (text == "INVALID PASSWORD") {
												Hide("zoho_upd_passwd_busy");
												i18n.getJSAlertValue(Utils.zuid, "zp.password.incorrectpwd", null,
												function(mesg) {
													stid.innerHTML = "<span class=\"error\">" + mesg + "</span>";
												});
											} else {
												if (text == "PASSWORD UPDATED") {
													ShowHideInline("zoho_upd_passwd_message", "zoho_upd_passwd_busy");
													document.changePassForm.reset();
													setTimeout(function() {
														Hide("zoho_upd_passwd_message");
													},
													2000);
												} else {
													_964.innerHTML = text;
												}
											}
										} else {
											if (url.indexOf("/saveusertheme.do") != -1) {
												_964.innerHTML = text;
												ShowGenInline("zoho_upd_theme_message");
												setTimeout(function() {
													Hide("zoho_upd_theme_message");
												},
												2000);
											} else {
												if (url.indexOf("/updatelanguage.do") != -1) {
													_964.innerHTML = text;
													ShowGenInline("zoho_upd_lang_message");
													setTimeout(function() {
														Hide("zoho_upd_lang_message");
													},
													2000);
												} else {
													if (url.indexOf("/addmystatus.do") != -1) {
														var _974 = document.addMyStatus.targetid.value;
														_964 = document.getElementById(_974);
														_964.innerHTML = text;
														if (document.getElementById("projAct") && document.getElementById("userStat")) {
															if (_974 == "latestactivity") {
																ShowHideBlock("projAct", "userStat");
															} else {
																ShowHideBlock("userStat", "projAct");
															}
														}
														Hide("ajax_load_tab");
													} else {
														if (url.indexOf("/updateuserlayout.do") != -1) {
															Hide("zoho_upd_layout_busy");
															var _975 = document.getElementById("maincontent");
															var _976 = _975.className;
															if (text == "LAYOUT CHANGED TO left") {
																_975.className = _976.replace("fl", "fr");
															} else {
																if (text == "LAYOUT CHANGED TO right") {
																	_975.className = _976.replace("fr", "fl");
																}
															}
															ShowGenInline("zoho_upd_layout_message");
															setTimeout(function() {
																Hide("zoho_upd_layout_message");
															},
															2000);
														} else {
															if (url.indexOf("/updatecompanyprofile.do") != -1) {
																if (text == "PROFILE UPDATED") {
																	EmptyDivContent("comp_upd_status");
																	var _977 = document.getElementById("footurl");
																	var _978 = document.getElementById("dataCompName");
																	var _979 = trim(_95f.value);
																	if (_95e != null && _978 != null) {
																		_978.innerHTML = _95e.value;
																	}
																	if (_95f != null && _977 != null) {
																		_977.href = _979;
																	}
																	if (_957) {
																		var _97a = _957.name;
																		if (eval("document." + _97a + ".taskdur")) {
																			var _97b = jQuery("input:radio[name=taskdur]:checked").val();
																			if ("hrs" == _97b) {
																				Utils.taskinhr = true;
																			} else {
																				Utils.taskinhr = false;
																			}
																		}
																	}
																	ShowHideInline("zoho_upd_comp_prof_message", "zoho_upd_comp_prof_busy");
																	setTimeout(function() {
																		Hide("zoho_upd_comp_prof_message");
																	},
																	2000);
																} else {
																	if (text == "DATE FORMAT CHANGED") {
																		ShowHideInline("zoho_upd_comp_dt_message", "zoho_upd_comp_dt_busy");
																		setTimeout(function() {
																			Hide("zoho_upd_comp_dt_message");
																		},
																		2000);
																	} else {
																		if (text == "LOGO DELETED") {
																			Hide("dellogolink");
																			document.getElementById("mainCompLogo").src = "/images/logo.gif";
																			document.getElementById("companyLogo").src = "/images/logo.gif";
																			ShowGenInline("zoho_del_logo_message");
																			setTimeout(function() {
																				Hide("zoho_del_logo_message");
																			},
																			2000);
																		} else {
																			if (text == "POWERLOGO DELETED") {
																				Hide("delplogolink");
																				document.getElementById("powerLogoImg").src = "/images/powerLogo.gif";
																				document.getElementById("footerLogo").src = "/images/powerLogo.gif";
																				ShowGenInline("zoho_del_plogo_message");
																				setTimeout(function() {
																					Hide("zoho_del_plogo_message");
																				},
																				2000);
																			} else {
																				if (text == "SAME PORTAL NAME") {
																					Hide("zoho_upd_portal_busy");
																				} else {
																					if (text == "NOT AUTHORISED TO CHANGE PORTAL") {
																						Hide("zoho_upd_portal_busy");
																						i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.perdenied", null,
																						function(mesg) {
																							document.getElementById("portal_update_status").innerHTML = "<span class=\"error\">" + mesg + "</span>";
																						});
																					} else {
																						if (text == "NOT VALID PORTAL") {
																							Hide("zoho_upd_portal_busy");
																							i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.domainvalidation", null,
																							function(mesg) {
																								document.getElementById("portal_update_status").innerHTML = "<span class=\"error\">" + mesg + "</span>";
																							});
																						} else {
																							if (text == "PORTAL NOT AVAILABLE") {
																								Hide("zoho_upd_portal_busy");
																								i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.portnotavail", null,
																								function(mesg) {
																									document.getElementById("portal_update_status").innerHTML = "<span class=\"error\">" + mesg + "</span>";
																								});
																							} else {
																								if (text == "ONLY FOR PAID PORTALS") {
																									if (document.getElementById("zoho_upd_portal_busy") != null) {
																										Hide("zoho_upd_portal_busy");
																										document.changePortalForm.reset();
																									} else {
																										if (document.getElementById("zoho_upd_owner_busy") != null) {
																											Hide("zoho_upd_owner_busy");
																											document.portalOwnerForm.reset();
																										}
																									}
																								} else {
																									if (text == "OWNER CHANGED") {
																										EmptyDivContent("comp_portal_settings");
																										EmptyDivContent("comp_owner_settings");
																										ShowCompanySettings("prof");
																										var _97f = document.getElementById("portallink").parentNode;
																										var _980 = document.getElementById("ownerlink").parentNode;
																										var _981 = document.getElementById("billingtab");
																										_97f.parentNode.removeChild(_97f);
																										_980.parentNode.removeChild(_980);
																										if (_981) {
																											_981.parentNode.removeChild(_981);
																										}
																									} else {
																										if (text.indexOf("ZSCKEYVALUE|") == 0) {
																											var _982 = text.split("|");
																											document.getElementById("zsckeyvalue").innerHTML = _982[1];
																											document.portalZSCKeyForm.oldzsckey.value = _982[1];
																										} else {
																											if (text.indexOf("http") == 0) {
																												eval("window.location =\"" + text + "\";");
																											} else {
																												_964.innerHTML = text;
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															} else {
																if (url.indexOf("/addclientuser.do") != -1) {
																	var doc = document;
																	var _984 = _957.name.replace("addClientUser_", "");
																	var stid = doc.getElementById("client_add_status_" + _984);
																	if (text == "User already added to this organization") {
																		Hide("zoho_addclient_user_busy_" + _984);
																		i18n.getJSAlertValue(Utils.zuid, "zp.newuser.useralreadyadded", null,
																		function(mesg) {
																			stid.innerHTML = "<span class=\"error\">" + mesg + "</span>";
																		});
																	} else {
																		if (text == "User already added as client to this organization") {
																			Hide("zoho_addclient_user_busy_" + _984);
																			i18n.getJSAlertValue(Utils.zuid, "zp.newuser.useraddasclient", null,
																			function(mesg) {
																				stid.innerHTML = "<span class=\"error\">" + mesg + "</span>";
																			});
																		} else {
																			if (text.indexOf("digest=") == 0) {
																				Hide("zoho_addclient_user_busy_" + _984);
																				var _987 = text.split("digest=")[1];
																				i18n.getJSAlertValue(Utils.zuid, "zp.user.wrongcaptcha", null,
																				function(mesg) {
																					stid.innerHTML = "<span class=\"error\">" + mesg + "</span>";
																				});
																				_957.digest.value = _987;
																				_957.captcha.value = "";
																				_957.captcha.focus();
																				var src = doc.getElementById("captchaimage_" + _984).src;
																				doc.getElementById("captchaimage_" + _984).src = src.split("=")[0] + "=" + _987;
																			} else {
																				_964.innerHTML = text;
																				i18n.getJSAlertValue(Utils.zuid, "zp.user.addsuccess", null,
																				function(mesg) {
																					displayFadeMsg(mesg);
																				});
																			}
																		}
																	}
																} else {
																	if (url.indexOf("loginsearch.na") != -1) {
																		if (text.indexOf("zzzloginurl||") != -1) {
																			ShowHideBlock("signindiv", "lsearchdiv");
																			var a = text.split("||");
																			location.href = a[1];
																		} else {
																			_964.innerHTML = text;
																		}
																	} else {
																		if (url.indexOf("addprojectclientuser.do") != -1) {
																			var dId = document.getElementById("projclientalert");
																			if (text == "zzzCompany Userszzz ||") {
																				Hide("zoho_upd_proj_cuser_busy");
																				i18n.getJSAlertValue(Utils.zuid, "zp.clview.ualexists", null,
																				function(mesg) {
																					dId.innerHTML = "<span class=\"error\">" + mesg + "</span>";
																				});
																			} else {
																				if (text == "zzzClient Userszzz ||") {
																					Hide("zoho_upd_proj_cuser_busy");
																					i18n.getJSAlertValue(Utils.zuid, "zp.clview.clalexists", null,
																					function(mesg) {
																						dId.innerHTML = "<span class=\"error\">" + mesg + "</span>";
																					});

																				} else {
																					_964.innerHTML = text;
																				}
																			}
																		} else {
																			if (endsWith(url, "addmilestone.do") || endsWith(url, "addcalendarmilestone.do")) {
																				_964.innerHTML = text;
																				if (document.getElementById("addmilestone")) {
																					Hide("addmilestone");
																				}
																			} else {
																				if (url.indexOf("updatetodolist.do") != -1) {
																					if (text.indexOf("miledisp_div_") != -1) {
																						_964 = document.getElementById("todolist_div");
																						_958 = "todolist_div";
																					}
																					_964.innerHTML = text;
																					jQuery("#addtaskdiv").hide();
																					var a = _958.split("_");
																					if (document.getElementById("ul_ttitle_" + a[1])) {
																						new Effect.ScrollTo("ul_ttitle_" + a[1], {
																							duration: 0.1
																						});
																						document.getElementById("ul_ttitle_" + a[1]).style.backgroundColor = "#ffffcc";
																						setTimeout(function() {
																							document.getElementById("ul_ttitle_" + a[1]).style.backgroundColor = "#FFFFFF";
																						},
																						3000);
																					}
																					if (document.getElementById("gTasklistId") && document.getElementById("csrftoken")) {
																						var _98e = document.getElementById("gTasklistId").innerHTML;
																						var _98f = document.getElementById("csrftoken").innerHTML;
																						if (_98e != null) {
																							var _990 = _98e.split("||");
																							var _991 = _990[1].split("#");
																							ajaxSendRequest(Utils.contPath + "/gtaskaction.do?tasklistid=" + _991[0] + "&taskaction=" + _991[1] + "&" + _98f);
																						}
																					}
																				} else {
																					if (url.indexOf("copytodotask.do") != -1) {
																						if (is_ie) {
																							jQuery("#" + _958).replaceWith("<tbody id=\"" + _958 + "\">" + text + "</tbody>");
																							bindMoT(_958);
																						} else {
																							_964.innerHTML = text;
																						}
																						jQuery("#copyid").hide();
																						var a = _958.split("_");
																						if (document.getElementById("ul_ttitle_" + a[1])) {
																							new Effect.ScrollTo("ul_ttitle_" + a[1], {
																								duration: 0.1
																							});
																							document.getElementById("ul_ttitle_" + a[1]).style.backgroundColor = "#ffffcc";
																							setTimeout(function() {
																								document.getElementById("ul_ttitle_" + a[1]).style.backgroundColor = "#FFFFFF";
																							},
																							3000);
																						}
																					} else {
																						if (endsWith(url, "addtoptodolist.do")) {
																							if (text.indexOf("&mstype=upcoming") != -1) {
																								_964 = document.getElementById("upcoming_todos");
																							} else {
																								if (text.indexOf("&mstype=delayed") != -1) {
																									_964 = document.getElementById("overdue_todos");
																								} else {
																									if (text.indexOf("&mstype=miscellaneous") != -1) {
																										_964 = document.getElementById("miscellaneous_todos");
																									}
																								}
																							}
																							_964.innerHTML = text;
																							Hide("addtoptodolist");
																							Hide("zoho_add_tlist_busy");
																							if (url.indexOf("addtoptodolist.do") != -1) {
																								if (text.indexOf("zzztoptodolistzzz||") != -1) {
																									var a = text.split("||");
																									new Effect.ScrollTo("todoList" + a[1], {
																										duration: 0.1
																									});
																									var hid = "disptlist_" + a[1];
																									document.getElementById(hid).style.backgroundColor = "#ffffcc";
																									setTimeout(function() {
																										document.getElementById(hid).style.backgroundColor = "#FFFFFF";
																									},
																									3000);
																								}
																							}
																						} else {
																							if (url.indexOf("updatetodotask.do") != -1) {
																								if (url.indexOf("updatetodotask.do") != -1) {
																									var _993 = _957.name;
																									if (text.indexOf("Cannot set reminder") != -1) {
																										if (document.getElementById("ttaskId")) {
																											var _994 = document.getElementById("ttaskId").value;
																											var stid = document.getElementById("ttask_update_status_" + _994);
																											var _967 = text.split("||");
																											stid.innerHTML = "<span class=\"error\">" + _967[1] + "</span>";
																											Hide("zoho_update_ttask_busy_" + _994);
																											eval("document." + _993 + ".edittasksubmit.disabled=false");
																											return false;
																										} else {
																											var stid = document.getElementById("ttask_update_status");
																											var _967 = text.split("||");
																											stid.innerHTML = "<span class=\"error\">" + _967[1] + "</span>";
																											Hide("zoho_update_ttask_busy");
																											eval("document." + _993 + ".edittasksubmit.disabled=false");
																											return false;
																										}
																									}
																									if (text.indexOf("set valid Date") != -1) {
																										if (document.getElementById("ttaskId")) {
																											var _994 = document.getElementById("ttaskId").value;
																											var stid = document.getElementById("ttask_update_status_" + _994);
																											var a = text.split("||");
																											Hide("zoho_update_ttask_busy_" + _994);
																											stid.innerHTML = "<span class=\"error\">" + a[1] + "</span>";
																										} else {
																											var stid = document.getElementById("ttask_update_status");
																											var a = text.split("||");
																											Hide("zoho_update_ttask_busy");
																											stid.innerHTML = "<span class=\"error\">" + a[1] + "</span>";
																										}
																									} else {
																										if (text.indexOf("Invalid move ||") != -1) {
																											if (document.getElementById("ttaskId")) {
																												var _994 = document.getElementById("ttaskId").value;
																												var stid = document.getElementById("ttask_update_status_" + _994);
																												var a = text.split("||");
																												var tid = a[1];
																												Hide("zoho_update_ttask_busy_" + _994);
																												stid.innerHTML = "<span class=\"error\">" + a[1] + "</span>";
																												return false;
																											} else {
																												var stid = document.getElementById("ttask_update_status");
																												var a = text.split("||");
																												var tid = a[1];
																												Hide("zoho_update_ttask_busy");
																												stid.innerHTML = "<span class=\"error\">" + a[1] + "</span>";
																												return false;
																											}
																										} else {
																											var _996 = eval("document." + _993 + ".frompage.value");
																											if ("taskdetails" != _996) {
																												var _997 = null;
																												if (text.indexOf("zzzedittaskzzz||") != -1) {
																													var id = text.split("||");
																													var _96c = "task_" + id[1];
																													jQuery("#taskaction_" + id[1]).remove();
																													if (url.indexOf("pcomplete=100") != -1) {
																														replacePrevTask(id[1]);
																													}
																													jQuery("#" + _96c).replaceWith("<tr>" + text + "</tr>");
																													jQuery("#updatetaskdiv").hide();
																													_997 = _96c;
																												} else {
																													if (text.indexOf("zzzbulkedittaskzzz||") != -1) {
																														if (is_ie) {
																															jQuery("#" + _958).replaceWith("<tbody id=\"" + _958 + "\">" + text + "</tbody>");
																															bindMoT(_958);
																														} else {
																															_964.innerHTML = text;
																														}
																														pmevalScript(_964);
																														jQuery("#updatetaskdiv").hide();
																														new Effect.ScrollTo(_958, {
																															duration: 0.1
																														});
																													} else {
																														if (text.indexOf("zzzrecurrencetaskzzz||") != -1) {
																															var id = text.split("||");
																															var _998 = "task_" + id[1];
																															var _999 = "task_" + id[2];
																															jQuery("#" + _998).remove();
																															jQuery("#" + _958).append("<tr>" + text + "</tr>");
																															bindMoT(_999);
																															jQuery("#updatetaskdiv").hide();
																															_997 = _999;
																														}
																													}
																												}
																												if (_997) {
																													if (document.getElementById(_997)) {
																														document.getElementById(_997).style.backgroundColor = "#ffffcc";
																														setTimeout(function() {
																															document.getElementById(_997).style.backgroundColor = "#FFFFFF";
																														},
																														3000);
																														new Effect.ScrollTo(_997, {
																															duration: 0.1
																														});
																													}
																												}
																												i18n.getJSAlertValue(Utils.zuid, "zp.general.taskupdatedsuccessfully", null,
																												function(mesg) {
																													displayFadeMsg(mesg);
																												});
																												return false;
																											} else {
																												_964.innerHTML = text;
																											}
																											jQuery("#updatetaskdiv").hide();
																											new Effect.ScrollTo(_958, {
																												duration: 0.1
																											});
																											if (document.getElementById("gTaskId") && document.getElementById("csrftoken")) {
																												var _98e = document.getElementById("gTaskId").innerHTML;
																												var _98f = document.getElementById("csrftoken").innerHTML;
																												if (_98e != null) {
																													var _99b = _98e.split("||");
																													var _99c = _99b[1].split("#");
																													if (_99c.indexOf("completed") != -1) {
																														ajaxSendRequest(Utils.contPath + "/gtaskaction.do?taskidlist=" + _99c[0] + "&taskaction=" + _99c[1] + "&tlistid=" + _99c[2] + "&" + _98f);
																													} else {
																														ajaxSendRequest(Utils.contPath + "/gtaskaction.do?taskid=" + _99c[0] + "&taskaction=" + _99c[1] + "&" + _98f);
																													}
																												}
																											}
																										}
																									}
																								} else {
																									if (text.indexOf("miledisp_div_") != -1) {
																										_964 = document.getElementById("todolist_div");
																										_964.innerHTML = text;
																									} else {
																										_964.innerHTML = text;
																									}
																								}
																							} else {
																								if (url.indexOf("adddependency.do") != -1) {
																									if (text.indexOf("set valid Date") != -1) {
																										var _994 = document.getElementById("ttaskId").value;
																										var stid = document.getElementById("ttask_depend_status_" + _994);
																										var a = text.split("||");
																										Hide("zoho_depend_ttask_busy_" + _994);
																										stid.innerHTML = "<span class=\"error\">" + a[1] + "</span>";
																									} else {
																										_964.innerHTML = text;
																									}
																								} else {
																									if (url.indexOf("addcalendartask.do") != -1) {
																										var _993 = _957.name;
																										if (text.indexOf("Cannot set reminder") != -1) {
																											var _966 = document.getElementById("ttask_add_status");
																											var _967 = text.split("||");
																											_966.innerHTML = "<span class=\"error\">" + _967[1] + "</span>";
																											Hide("zoho_add_ttask_busy");
																											eval("document." + _993 + ".addtoptasksubmit.disabled=false");
																											return false;
																										} else {
																											jQuery("#addtodo").hide();
																										}
																										var _99d = eval("document." + _993 + ".frompage.value");
																										if (_99d == "addtodo") {
																											jQuery("#" + _958).append("<tr>" + text + "</tr>");
																										} else {
																											_964.innerHTML = text;
																										}
																										if (text.indexOf("zzztoptodozzz||") != -1) {
																											var a = text.split("||");
																											if (_99d != "addtodo") {
																												new Effect.ScrollTo("task_" + a[1], {
																													duration: 0.1
																												});
																											}
																											var hid = "task_" + a[1];
																											if (document.getElementById(hid)) {
																												document.getElementById(hid).style.backgroundColor = "#ffffcc";
																												setTimeout(function() {
																													document.getElementById(hid).style.backgroundColor = "#FFFFFF";
																												},
																												3000);
																											}
																										}
																										if (document.getElementById("gTaskId") && document.getElementById("csrftoken")) {
																											var _98e = document.getElementById("gTaskId").innerHTML;
																											var _98f = document.getElementById("csrftoken").innerHTML;
																											if (_98e != null) {
																												var _99b = _98e.split("||");
																												var _99e = _99b[1].split("#");
																												ajaxSendRequest(Utils.contPath + "/gtaskaction.do?taskid=" + _99e[0] + "&taskaction=" + _99e[1] + "&" + _98f);
																											}
																										}
																										if (text.indexOf("resourceDiv") != -1) {
																											pmevalScript(_964);
																											resourceLoader();
																										}
																									} else {
																										if (url.indexOf("dependstasklist.do") != -1) {
																											Hide("zoho_depend_mile_busy");
																											_964.innerHTML = text;
																										} else {
																											if (url.indexOf("dependstask.do") != -1) {
																												Hide("zoho_depend_tlist_busy");
																												_964.innerHTML = text;
																											} else {
																												if (url.indexOf("meetfolderdocuments.do") != -1) {
																													_964 = document.getElementById("documentid_" + _958);
																													var fdId = document.getElementById("documentid_" + _958);
																													ShowGenInline("documentid_" + _958);
																													Hide("zoho_taskdoc_busy_" + _958);
																													fdId.innerHTML = text;
																												} else {
																													if (url.indexOf("folderdocuments.do") != -1) {
																														var doid = "documentid_" + _958;
																														_964 = document.getElementById("documentid_" + _958);
																														var fdId = document.getElementById("documentid_" + _958);
																														Hide("zoho_taskdoc_busy_" + _958);
																														var _970 = _957.name;
																														if (is_ie && (eval("document." + _970 + ".frompage") && eval("document." + _970 + ".frompage.value") == "task")) {
																															jQuery("#" + doid).replaceWith("<tbody id=\"" + doid + "\">" + text + "</tbody>");
																														} else {
																															_964.innerHTML = text;
																														}
																														scrollInViewPart("assodoc_" + _958);
																													} else {
																														if (url.indexOf("/categoryforums.do") != -1) {
																															_964 = document.getElementById("forumid_" + _958);
																															var cfid = "forumid_" + _958;
																															Hide("zoho_taskforum_busy_" + _958);
																															var _970 = _957.name;
																															if (is_ie && (eval("document." + _970 + ".frompage") && eval("document." + _970 + ".frompage.value") == "task")) {
																																jQuery("#" + cfid).replaceWith("<tbody id=\"" + cfid + "\">" + text + "</tbody>");
																															} else {
																																_964.innerHTML = text;
																															}
																															scrollInViewPart("assoforum_" + _958);
																														} else {
																															if (url.indexOf("/addMeetDocuments.do") != -1) {
																																_964.innerHTML = text;
																															} else {
																																if (url.indexOf("/addTaskDocuments.do") != -1) {
																																	_964.innerHTML = text;
																																} else {
																																	if (url.indexOf("/addTaskForums.do") != -1) {
																																		_964.innerHTML = text;
																																	} else {
																																		if (endsWith(url, "addtemptodolist.do")) {
																																			_964.innerHTML = text;
																																			if (url.indexOf("addtemptodolist.do") != -1) {
																																				if (text.indexOf("zzztemptodolistzzz||") != -1) {
																																					var a = text.split("||");
																																					var hid = "disptlist_" + a[1];
																																					if (document.getElementById(hid) != null) {
																																						document.getElementById(hid).style.backgroundColor = "#ffffcc";
																																						setTimeout(function() {
																																							document.getElementById(hid).style.backgroundColor = "#FFFFFF";
																																						},
																																						3000);
																																					}
																																				}
																																			}
																																		} else {
																																			if (endsWith(url, "addtemptodotask.do")) {
																																				_964.innerHTML = text;
																																				if (url.indexOf("addtemptodotask.do") != -1) {
																																					if (text.indexOf("zzztemptodotaskzzz||") != -1) {
																																						var a = text.split("||");
																																						var hid = "disptemptask_" + a[1];
																																						if (document.getElementById(hid) != null) {
																																							document.getElementById(hid).style.backgroundColor = "#ffffcc";
																																							setTimeout(function() {
																																								document.getElementById(hid).style.backgroundColor = "#FFFFFF";
																																							},
																																							3000);
																																						}
																																					}
																																				}
																																			} else {
																																				if (endsWith(url, "addproject.do")) {
																																					if (text.indexOf("zzzprojectadderrorzzz||") != -1) {
																																						var a = text.split("||");
																																						var _9a2 = a[1];
																																						var _9a3 = document.getElementById("proj_add_status");
																																						_9a3.innerHTML = _9a2;
																																						Hide("zoho_add_project_busy");
																																					} else {
																																						_964.innerHTML = text;
																																					}
																																				} else {
																																					if (endsWith(url, "updateproject.do")) {
																																						if (document.getElementById("editprojdiv") && document.getElementById("activeprojdiv")) {
																																							ShowHideInline("activeprojdiv", "editprojdiv");
																																						}
																																						if (text.indexOf("zzzprojectupderrorzzz||") != -1) {
																																							var a = text.split("||");
																																							var _9a2 = a[1];
																																							var _9a4 = document.getElementById("settings_update_status");
																																							_9a4.innerHTML = _9a2;
																																							Hide("zoho_proj_settings_busy");
																																						} else {
																																							_964.innerHTML = text;
																																						}
																																					} else {
																																						if (url.indexOf("updateprojannouncement.do") != -1) {
																																							_964.innerHTML = text;
																																						} else {
																																							if (url.indexOf("adddayloghours.do") != -1) {
																																								_964.innerHTML = text;
																																							} else {
																																								if (url.indexOf("addloghours.do") != -1) {
																																									document.addLogHours.logsubmit.disabled = false;
																																									if (text.indexOf("zzzinvalidlogtimezzz||") != -1) {
																																										var a = text.split("||");
																																										i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.fromtoval", null,
																																										function(mesg) {
																																											alert(mesg);
																																										});
																																										Hide("zoho_add_loghours_busy");
																																										return false;
																																									} else {
																																										if (text.indexOf("zzzinvalidusertaskzzz||") != -1) {
																																											i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.timetaskerror", null,
																																											function(mesg) {
																																												alert(mesg);
																																											});
																																											Hide("zoho_add_loghours_busy");
																																											return false;
																																										} else {
																																											if (text.indexOf("zzzinvaliduserbugzzz||") != -1) {
																																												i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.timebugerror", null,
																																												function(mesg) {
																																													mesg = mesg.replace("{0}", Utils.bugReplaceValue);
																																													alert(mesg);
																																												});
																																												Hide("zoho_add_loghours_busy");
																																											} else {
																																												if (document.addLogHours.frompage.value == "oldlistview") {
																																													_964.innerHTML = "<div class=\"fl w100per\">" + text + "</div>" + _964.innerHTML;
																																													Hide("zoho_add_loghours_busy");
																																													ShowGenInline("rec_log_entries");
																																													document.addLogHours.reset();
																																													document.addLogHours.tsheettype.value = _960;
																																													document.addLogHours.addhourstype.value = _961;
																																													document.addLogHours.logdate.value = _962;
																																													document.addLogHours.logtask.value = "";
																																													document.addLogHours.logissue.value = "";
																																												} else {
																																													if ((document.addLogHours.frompage.value) == "logcalendar") {
																																														if (text.indexOf("###") != -1) {
																																															var _9a8 = text.split("###")[0];
																																															var lh = parseInt(_9a8.split(":")[0], 10);
																																															var lm = parseInt(_9a8.split(":")[1], 10);
																																															var _9ab = text.split("###")[1];
																																															if (document.getElementById("caldiv_" + _9ab) && document.getElementById("caldiv_" + _9ab).style.display == "none") {
																																																jQuery("#caldiv_" + _9ab).show();
																																																jQuery("#logcaldiv_" + _9ab).html(_9a8);
																																															} else {
																																																if (document.getElementById("logcaldiv_" + _9ab)) {
																																																	var _9ac = document.getElementById("logcaldiv_" + _9ab).innerHTML;
																																																	document.getElementById("logcaldiv_" + _9ab).innerHTML = calcNewLogHours(_9ac, lh, lm);
																																																}
																																															}
																																															if (document.getElementById("rownum_" + _9ab)) {
																																																var _9ad = document.getElementById("rownum_" + _9ab).value;
																																																if (document.getElementById("weektotal_" + _9ad)) {
																																																	var _9ae = document.getElementById("weektotal_" + _9ad).innerHTML;
																																																	document.getElementById("weektotal_" + _9ad).innerHTML = calcNewLogHours(_9ae, lh, lm);
																																																}
																																																if (document.getElementById("totalloghours")) {
																																																	var _9af = document.getElementById("totalloghours").innerHTML;
																																																	document.getElementById("totalloghours").innerHTML = calcNewLogHours(_9af, lh, lm);
																																																}
																																															}
																																														}
																																														jQuery("#addtasklog").hide();
																																														return false;
																																													} else {
																																														if ((document.addLogHours.frompage.value) != "taskdetails" && (document.addLogHours.frompage.value) != "issuedetails") {
																																															jQuery("#dispDate").remove();
																																															var _9b0 = text.split("##monthformat##")[1];
																																															if (document.getElementById("recentTr_" + _9b0)) {
																																																var _9b1 = document.getElementById("recentTbody_" + _9b0).innerHTML;
																																																_9b1 = _9b1 + text;
																																																jQuery("#recentTbody_" + _9b0).html(_9b1);
																																															} else {
																																																if (document.getElementById("emptypage")) {
																																																	jQuery("#emptypage").remove();
																																																	jQuery("#parentTbody").append("<tr>" + text + "</tr>");
																																																} else {
																																																	var _9b1 = "<tr id=\"recentTr_" + _9b0 + "\"><td  style=\"border-bottom: 0px none;\" width=\"20\" align=\"left\"></td><td id=\"recentTd_" + _9b0 + "\" class=\"pl3 hrsminmax bdrbtm milecompletetitle2 pb3\" bgcolor=\"#f5f5f5\" valign=\"bottom\" colspan=\"4\" height=\"25\">" + _9b0 + "</td></tr>";
																																																	jQuery("#recentlogtitle").show();
																																																	jQuery("#recentlogtitle").after("<tbody id=\"recentTbody_" + _9b0 + "\">" + _9b1 + text + "</tbody>");
																																																	document.getElementById("recentTd_" + _9b0).innerHTML = document.getElementById("dispDate").value;
																																																	document.getElementById("recentTd_" + _9b0).colSpan = document.getElementById("logcolspan").value;
																																																}
																																															}
																																															document.addLogHours.reset();
																																															document.addLogHours.tsheettype.value = _960;
																																															document.addLogHours.addhourstype.value = _961;
																																															document.addLogHours.logdate.value = _962;
																																															document.addLogHours.logtask.value = "";
																																															document.addLogHours.logissue.value = "";
																																															if (document.addLogHours.taskval) {
																																																document.addLogHours.taskval.value = "Select";
																																															}
																																															if (document.addLogHours.issueval) {
																																																document.addLogHours.issueval.value = "Select";
																																															}
																																															jQuery("#zoho_add_loghours_busy").hide();
																																															return false;
																																														} else {
																																															_964.innerHTML = text;
																																															Hide("zoho_add_loghours_busy");
																																															document.addLogHours.reset();
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								} else {
																																									if (url.indexOf("deptaskupdate.do") != -1) {
																																										if (text.indexOf("Task with child task edited from gantt view ||") != -1) {
																																											if (document.getElementById("mainTaskPanel")) {
																																												chartLeft = document.getElementById("mainTaskPanel").scrollLeft;
																																												chartTop = document.getElementById("mainTaskPanel").scrollTop;
																																											}
																																											jQuery("#taskganttchart").empty();
																																											ganttChartLoader(chartLeft, chartTop);
																																											return false;
																																										} else {
																																											if (text.indexOf("Task edited from gantt view ||") != -1) {
																																												Hide("ajax_load_tabforGantt");
																																												return false;
																																											}
																																										}
																																										Hide("ajax_load_tab");
																																										Hide("depview_actionbusy");
																																										if (text.indexOf("update task title ||") != -1) {
																																											var b = text.split("||");
																																											var tid = b[1];
																																											var task = b[2];
																																											ShowHideInline("taskdisp_" + tid, "deptaskbox_" + tid);
																																											ShowGenInline("elipsis_" + tid);
																																											document.getElementById("deptaskdisp_" + tid).value = task;
																																											i18n.getJSAlertValue(Utils.zuid, "zp.general.taskupdatedsuccessfully", null,
																																											function(mesg) {
																																												displayFadeMsg(mesg);
																																											});
																																											depFormHide("taskdisp_", "deptitlediv", "taskname_", "taskid_", "pr5 pL3 dpczpbdrbr", "pr5 pL3 dark");
																																											return false;
																																										} else {
																																											if (text.indexOf("set valid Date ||") != -1) {
																																												var a = text.split("||");
																																												var tid = a[1];
																																												i18n.getJSAlertValue(Utils.zuid, "zp.ajaxfunjs.setvalstdate", null,
																																												function(mesg) {
																																													alert(mesg);
																																												});
																																												ShowHideInline("depstdisp_" + tid, "depstbox_" + tid);
																																												depFormHide("depstdisp_", "depstartdatediv", "stdate_", "stdateid_", "pt3 pL3 pointer taskdeptxtSmall dpczpbdrbr", "pt3 pL3 pointer taskdeptxtSmall dark");
																																												return false;
																																											} else {
																																												if (text.indexOf("Invalid move ||") != -1) {
																																													var a = text.split("||");
																																													var tid = a[1];
																																													var _9b6 = a[2];
																																													document.getElementById("depviewrow_" + tid).style.backgroundColor = "#FFD1D1";
																																													i18n.getJSAlertValue(Utils.zuid, "zp.ajaxfunjs.preponealert", null,
																																													function(mesg) {
																																														alert(mesg);
																																													});
																																													return false;
																																												} else {
																																													if (text.indexOf("set valid dependency ||") != -1) {
																																														var a = text.split("||");
																																														var tid = a[1];
																																														i18n.getJSAlertValue(Utils.zuid, "zp.ajaxfunjs.setvaldep", null,
																																														function(mesg) {
																																															alert(mesg);
																																														});
																																														return false;
																																													} else {
																																														if (text.indexOf("update task dependency ||") != -1) {
																																															var b = text.split("||");
																																															var tid = b[1];
																																															var dep = b[2];
																																															if ((trim(dep)) == "") {
																																																dep = "-";
																																															}
																																															ShowHideInline("depsafdisp_" + tid, "depsafbox_" + tid);
																																															document.getElementById("depsafdisp_" + tid).innerHTML = dep;
																																															i18n.getJSAlertValue(Utils.zuid, "zp.general.taskupdatedsuccessfully", null,
																																															function(mesg) {
																																																displayFadeMsg(mesg);
																																															});
																																															depFormHide("depsafdisp_", "deppreddiv", "pred_", "predid_", "pt3 pL3 pointer txtSmallBlack dpczpbdrbr", "pt3 pL3 pointer txtSmallBlack dark");
																																															return false;
																																														} else {
																																															if (text.indexOf("update task resource ||") != -1) {
																																																var b = text.split("||");
																																																var tid = b[1];
																																																var _9bb = b[2];
																																																ShowHideInline("epresdisp_" + tid, "depresbox_" + tid);
																																																document.getElementById("epresdisp_" + tid).innerHTML = _9bb;
																																																return false;
																																															} else {
																																																document.getElementById("projectcontent").innerHTML = text;
																																																i18n.getJSAlertValue(Utils.zuid, "zp.general.taskupdatedsuccessfully", null,
																																																function(mesg) {
																																																	displayFadeMsg(mesg);
																																																});
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									} else {
																																										if (url.indexOf("/updatechattopic.do") != -1) {
																																											var a = text.split("||");
																																											ShowHideBlock("dispctitle_" + a[1], "editctitle_" + a[1]);
																																											document.getElementById("chattitle_" + a[1]).innerHTML = a[2];
																																										} else {
																																											if (url.indexOf("/inviteparticipants.do") != -1) {
																																												_964.innerHTML = text;
																																											} else {
																																												if (url.indexOf("/changeprojemail.do") != -1) {
																																													if (text == "success") {
																																														var elem = document.getElementsByName("pemail");
																																														var _9be = document.changemailform.value.value;
																																														for (var i = 0,
																																														j, l = elem.length; i < l; i++) {
																																															j = elem[i].href.replace(/_[0-9a-z]+@/, "_" + _9be + "@");
																																															elem[i].href = j;
																																															elem[i].innerHTML = j.replace("mailto:", "");
																																														}
																																													}
																																													ShowHideBlock("view", "edit");
																																												} else {
																																													if (url.indexOf("/vaidatecrmiscticket.do") != -1) {
																																														if (text.indexOf("Invalid Ticket Id||") != -1) {
																																															var _9c2 = document.getElementById("ticket_status");
																																															if (_9c2) {
																																																i18n.getJSAlertValue(Utils.zuid, "zp.newuser.invalidticket", null,
																																																function(mesg) {
																																																	_9c2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
																																																});
																																															}
																																															return false;
																																														} else {
																																															_964.innerHTML = text;
																																														}
																																													} else {
																																														if (url.indexOf("/updatemilestonedate.do") != -1) {
																																															Hide("ajax_load_tabforGantt");
																																															return false;
																																														} else {
																																															if (url.indexOf("/addweeklyloghours.do") != -1) {
																																																if (text.indexOf("zzzinvalidusertaskzzz||") != -1) {
																																																	alert(text.split("||")[1]);
																																																	return false;
																																																} else {
																																																	if (text.indexOf("zzzinvaliduserbugzzz||") != -1) {
																																																		alert(text.split("||")[1]);
																																																		return false;
																																																	} else {
																																																		var pId = document.getElementById("pId").innerHTML;
																																																		var _9c5 = document.getElementById("loginName").innerHTML;
																																																		var csrf = document.getElementById("csrf").innerHTML;
																																																		var _9c7 = document.addLogHours.topage.value;
																																																		if ("addnew" != _9c7) {
																																																			if (pId == "0") {
																																																				ajaxShowCalImgPage(Utils.contPath + "/mylogtodos.do?" + csrf, "projectcontent");
																																																			} else {
																																																				ajaxShowTab(Utils.contPath + "/showproject.do?toview=timesheet&username=" + _9c5 + "&projId=" + pId + "&" + csrf, "projectcontent");
																																																			}
																																																		} else {
																																																			var _9c8 = _964.innerHTML;
																																																			jQuery("#projectcontent").html(text);
																																																			ShowGenInline("rec_log_entries");
																																																			jQuery("#" + _958).append(_9c8);
																																																			var _9c9 = document.getElementById("weeklogtr_0").innerHTML;
																																																			for (var i = 1; i <= 5; i++) {
																																																				AddWeeklyLogRow(_9c9, i);
																																																			}
																																																			jQuery("[class='weeklogtr']")[0].style.display = "none";
																																																		}
																																																	}
																																																}
																																															} else {
																																																_964.innerHTML = text;
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				pmevalScript(_964);
				if (endsWith(url, "taskfilter.do")) {
					Hide("zohobusy_filter_todo");
				}
				if (endsWith(url, "projectfolders.do")) {
					Hide("zp_folfetch_busy");
				}
				if (url.indexOf("movefile.do") != -1) {
					i18n.getJSAlertValue(Utils.zuid, "zp.doc.movefile", null,
					function(mesg) {
						displayFadeMsg(mesg);
					});
				}
				if (url.indexOf("forgotpasswd") != -1) {
					Hide("zoho_fpass_busy");
					ShowGenBlock("fpserver_resp");
					setTimeout(function() {
						Hide("fpserver_resp");
					},
					3000);
					ShowHideBlock("displink", "showfpasswd");
				}
				if (url.indexOf("addcomment.do") != -1) {
					document.commentForm.commbody.focus();
				}
				if (url.indexOf("addbulkuser.do") != -1) {
					Hide("zohoadduser");
					changeTabStyle("peopleview");
				}
				if (url.indexOf("addcrmclient.do") != -1) {
					changeTabStyle("peopleview");
				}
				if (url.indexOf("addgoogletask.do") != -1) {
					if (document.getElementById("resDiv")) {
						ShowGenInline("resDiv");
					}
					if (document.getElementById("errorDiv")) {
						ShowGenInline("errorDiv");
					}
				}
				if (url.indexOf("/addchattopic.do") != -1) {
					setChatStyle("footer");
					setChatStyle("header");
					setChatStyle("tabheader");
				}
				ini = false;
				initLightbox();
			}
		};
		http.send(_95c.args);
	}
	Hide("ajax_load_tab");
	return true;
}
function ajaxPostNote(url, _9cc, _9cd) {
	var _9ce = url;
	if (_9cc != "") {
		var args = getFormValues(_9cc);
		_9ce = url;
	} else {
		_9ce = url;
	}
	_9ce = fixForCachingInIe(_9ce);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _9ce, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _9d2 = document.getElementById(_9cd);
				_9d2.innerHTML = text;
				pmevalScript(_9d2);
				ini = false;
				Hide("ajax_load_tab");
			}
		};
		http.send(args);
	}
	return true;
}
function ajaxSubmitUserPage(url, _9d4, _9d5, _9d6) {
	var _9d7 = url;
	if (_9d4 != "") {
		var args = "?" + getFormValues(_9d4);
		_9d7 = url + args;
	} else {
		_9d7 = url;
	}
	var _9d9 = getURLArgs(_9d7);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _9d9.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				if (url.indexOf("adduser.do") != -1) {
					var _9dc = document.getElementById("newuser_status");
					if (text == "User already added to this organization") {
						Hide("zoho_adduser_busy");
						i18n.getJSAlertValue(Utils.zuid, "zp.newuser.useralreadyadded", null,
						function(mesg) {
							_9dc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
					} else {
						if (text == "User already added as client to this organization") {
							Hide("zoho_adduser_busy");
							i18n.getJSAlertValue(Utils.zuid, "zp.newuser.useraddasclient", null,
							function(mesg) {
								_9dc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
							});
						} else {
							if (text == "User already added to this project") {
								Hide("zoho_adduser_busy");
								i18n.getJSAlertValue(Utils.zuid, "zp.user.alreadyadd", null,
								function(mesg) {
									_9dc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
								});
							} else {
								if (text.indexOf("digest=") == 0) {
									Hide("zoho_adduser_busy");
									var _9e0 = text.split("digest=")[1];
									i18n.getJSAlertValue(Utils.zuid, "zp.user.wrongcaptcha", null,
									function(mesg) {
										_9dc.innerHTML = "<span class=\"error\">" + mesg + "</span>";
									});
									_9d4.digest.value = _9e0;
									_9d4.captcha.value = "";
									_9d4.captcha.focus();
									var src = document.getElementById("captchaimage").src;
									document.getElementById("captchaimage").src = src.split("=")[0] + "=" + _9e0;
								} else {
									i18n.getJSAlertValue(Utils.zuid, "zp.user.addsuccess", null,
									function(mesg) {
										displayFadeMsg(mesg);
									});
									var _9e4 = document.getElementById(_9d5);
									_9e4.innerHTML = text;
									pmevalScript(_9e4);
									if (_9d6) {
										changeTabStyle("peopleview");
									}
								}
							}
						}
					}
				}
			}
			ini = false;
		};
		http.send(_9d9.args);
	}
	return true;
}
function ajaxSubmitPageId(url, _9e6, _9e7, id) {
	var _9e9 = url;
	if (_9e6 != "") {
		var args = "?" + getFormValues(_9e6);
		_9e9 = url + args;
		var _9eb = _9e6.name;
	} else {
		_9e9 = url;
	}
	_9e9 = fixForCachingInIe(_9e9);
	var _9ec = getURLArgs(_9e9);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _9ec.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _9ef = document.getElementById(_9e7);
				if (endsWith(url, "updatemilestone.do")) {
					if (text.indexOf("Elapsed end date cannot be set") != -1 || text.indexOf("Elapsed start date cannot be set") != -1 || text.indexOf("End date cannot be before start date") != -1) {
						var _9f0 = "zoho_update_ms_busy" + id;
						var alId = "mstone_upd_status_" + id;
						Hide(_9f0);
						var stid = document.getElementById(alId);
						stid.innerHTML = text;
					} else {
						Hide("msedit_" + id);
						Hide("delmilefeat_" + id);
						_9ef.innerHTML = text;
					}
				} else {
					if (endsWith(url, "updatemile.do")) {
						_9ef.innerHTML = text;
						var val = "delmilefeat_" + id;
						if (document.getElementById(val)) {
							new Effect.ScrollTo(val, {
								duration: 0.3
							});
							document.getElementById(val).style.background = "#FAFABB";
							setTimeout(function() {
								document.getElementById(val).style.background = "";
							},
							3000);
						}
					} else {
						_9ef.innerHTML = text;
					}
				}
				pmevalScript(_9ef);
			}
		};
		http.send(_9ec.args);
	}
	return true;
}
function ajaxMeetPage(url, _9f5, _9f6, aid) {
	var _9f8 = url;
	var args = "?" + getFormValues(_9f5);
	_9f8 = fixForCachingInIe(url + args);
	var _9fa = getURLArgs(_9f8);
	var _9fb = _9f5.name;
	var http = getHTTPObject();
	var _9fd = "";
	var _9fe = "";
	if (aid != null) {
		_9fd = "zoho_update_meeting_busy";
		_9fe = "meet_upd_status";
	} else {
		_9fd = "zoho_add_meeting_busy";
		_9fe = "meet_add_status";
	}
	if (http) {
		http.open("POST", _9fa.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _a00 = document.getElementById(_9f6);
				var _a01 = document.getElementById(_9fe);
				if (url.indexOf("/updatemeeting.do") != -1) {
					changeSubLink("uplink", "upImg");
				}
				if (text.indexOf("invalidmeet||") != -1) {
					var _a02 = text.substring(text.indexOf("invalidmeet||") + 1, text.length);
					Hide(_9fd);
					i18n.getJSAlertValue(Utils.zuid, "zp.projcal.meetwarn", null,
					function(mesg) {
						_a01.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
				} else {
					_a00.innerHTML = text;
					ini = false;
					pmevalScript(_a00);
				}
			}
		};
		http.send(_9fa.args);
	}
	return true;
}
function ajaxSubmitAnchorPage(url, _a05, _a06, _a07) {
	var _a08 = "";
	if (_a05 != "") {
		var args = "?" + getFormValues(_a05);
		var _a0a = url + args;
		_a08 = _a05.name;
	} else {
		var _a0a = url;
	}
	_a0a = fixForCachingInIe(_a0a);
	var _a0b = getURLArgs(_a0a);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _a0b.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _a0e = document.getElementById(_a06);
				_a0e.innerHTML = text;
				ini = false;
				if (url.indexOf("addtodolist.do") != -1) {
					new Effect.ScrollTo(_a07, {
						duration: 0.1
					});
				}
				if (url.indexOf("addtasklist.do") != -1) {
					if (text.indexOf("zzztoptodolistzzz||") != -1) {
						var a = text.split("||");
						new Effect.ScrollTo("ul_ttitle_" + a[1], {
							duration: 0.1
						});
						var hid = "ul_ttitle_" + a[1];
						document.getElementById(hid).style.backgroundColor = "#ffffcc";
						setTimeout(function() {
							document.getElementById(hid).style.backgroundColor = "#FFFFFF";
						},
						3000);
					}
				}
				pmevalScript(_a0e);
				if (url.indexOf("addtodotask.do") != -1) {
					ShowGenInline("tasklist_div_" + _a06.split("_")[1]);
					ShowGenInline("collapseList_" + _a06.split("_")[1]);
					if (text.indexOf("zzztodolistzzz||") != -1) {
						var b = text.split("||");
						if (eval("document.addTodoTask_" + b[1])) {
							eval("document.addTodoTask_" + b[1] + ".todotask.focus()");
						}
					} else {
						var a = _a07.split("_");
						if (eval("document.addTodoTask_" + a[3])) {

							eval("document.addTodoTask_" + a[3] + ".todotask.focus()");
						}
					}
				}
				if (url.indexOf("movetodotask.do") != -1) {
					var _a12 = "upcoming_todos";
					if (text.indexOf("&mstype=upcoming") != -1) {
						_a12 = "upcoming_todos";
					} else {
						if (text.indexOf("&mstype=delayed") != -1) {
							_a12 = "overdue_todos";
						} else {
							if (text.indexOf("&mstype=miscellaneous") != -1) {
								_a12 = "miscellaneous_todos";
							}
						}
					}
					_a0e = document.getElementById(_a12);
					_a0e.innerHTML = text;
					var a = _a07.split("todoListItem");
					var _a13 = "taction_" + a[1];
					document.getElementById(_a13).style.backgroundColor = "#FFFFCC";
					setTimeout(function() {
						document.getElementById(_a13).style.backgroundColor = "#FFFFFF";
					},
					3000);
					if (document.getElementById("gTaskId") && document.getElementById("csrftoken")) {
						var _a14 = document.getElementById("gTaskId").innerHTML;
						var _a15 = document.getElementById("csrftoken").innerHTML;
						if (_a14 != null) {
							var a = _a14.split("||");
							var b = a[1].split("#");
							ajaxSendRequest(Utils.contPath + "/gtaskaction.do?taskid=" + b[0] + "&taskaction=" + b[1] + "&" + _a15);
						}
					}
				}
				if (url.indexOf("movetemptodotask.do") != -1) {
					var a = _a07.split("todoListItem");
					var _a13 = "disptemptask_" + a[1];
					if (document.getElementById(_a13) != null) {
						document.getElementById(_a13).style.backgroundColor = "#FFFFCC";
						setTimeout(function() {
							document.getElementById(_a13).style.backgroundColor = "#FFFFFF";
						},
						3000);
					}
				}
				Hide("ajax_load_tab");
			}
		};
		http.send(_a0b.args);
	}
	return true;
}
function ajaxShowAnchorPage(url, _a17, _a18) {
	var _a19 = fixForCachingInIe(url);
	var _a1a = getURLArgs(_a19);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _a1a.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _a1d = document.getElementById(_a17);
				_a1d.innerHTML = text;
				ini = false;
				new Effect.ScrollTo(_a18, {
					duration: 0.1
				});
				if (url.indexOf("projcal.do") != -1) {
					document.getElementById(_a18).style.backgroundColor = "#EDF5F8";
					setTimeout(function() {
						document.getElementById(_a18).style.backgroundColor = "#FFFFFF";
					},
					1000);
				}
				pmevalScript(_a1d);
				Hide("ajax_load_tab");
			}
		};
		http.send(_a1a.args);
	}
}
function ajaxShowAnchorPageHighlight(url, _a1f, _a20, hid) {
	var _a22 = fixForCachingInIe(url);
	var _a23 = getURLArgs(_a22);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _a23.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _a26 = document.getElementById(_a1f);
				_a26.innerHTML = text;
				ini = false;
				location.href = "#" + _a20;
				document.getElementById(hid).style.backgroundColor = "#ffffcc";
				setTimeout(function() {
					document.getElementById(hid).style.backgroundColor = "#FFFFFF";
				},
				3000);
				pmevalScript(_a26);
				Hide("ajax_load_tab");
			}
		};
		http.send(_a23.args);
	}
}
function getFormValues(fobj) {
	var str = "";
	var _a29 = null;
	var val = "";
	var cmd = "";
	var _a2c = "";
	var _a2d = fobj.elements.length;
	for (var i = 0; i < _a2d; i++) {
		var _a2f = fobj.elements[i].type;
		var _a30 = fobj.elements[i].name;
		if (_a2f == "text" || _a2f == "textarea" || _a2f == "hidden") {
			_a2c = fobj.elements[i].value;
			str += _a30 + "=" + encodeURIComponent(_a2c);
			if (i != _a2d) {
				str += "&";
			}
		} else {
			if (_a2f == "select-one") {
				if (_a30 == "picklistvalues") {
					var _a31 = fobj.elements[i];
					var slen = _a31.length;
					for (var j = 0; j < slen; j++) {
						var _a34 = _a31.options[j].value;
						str += _a30 + "=" + encodeURIComponent(_a34);
						if (j != (slen - 1)) {
							str += "&";
						}
					}
				} else {
					_a2c = fobj.elements[i].options[fobj.elements[i].selectedIndex].value;
					str += _a30 + "=" + encodeURIComponent(_a2c);
				}
				if (i != _a2d) {
					str += "&";
				}
			} else {
				if (_a2f == "select-multiple" && (_a30 == "workprojects" || _a30 == "projectusers" || _a30 == "participants" || _a30 == "chatparticipants" || _a30 == "addparticipants" || _a30 == "toaddr" || _a30 == "personresponsible" || _a30 == "taskdoc" || _a30 == "taskforum" || _a30 == "newprojectusers" || _a30 == "newprojclientid" || _a30 == "projUsers" || _a30 == "clients" || _a30 == "customfields")) {
					var _a35 = fobj.elements[i];
					var _a36 = _a35.length;
					for (var j = 0; j < _a36; j++) {
						if (_a30 != "addparticipants" && _a30 != "toaddr" && _a30 != "taskdoc" && _a30 != "taskforum" && _a30 != "personresponsible" && _a30 != "newprojectusers" && _a30 != "newprojclientid" && _a30 != "participants" && _a30 != "workprojects" && _a30 != "projUsers" && _a30 != "clients" && _a30 != "customfields") {
							var _a34 = _a35.options[j].value;
							str += _a30 + "=" + encodeURIComponent(_a34);
						} else {
							if (_a30 == "workprojects") {
								_a2c = _a35.options[j].value;
								str += "&" + _a30 + "=" + encodeURIComponent(_a2c);
							} else {
								if (_a30 == "customfields") {
									_a2c = _a35.options[j].value;
									str += "&" + _a30 + "=" + encodeURIComponent(_a2c);
								} else {
									if (_a35.options[j].selected) {
										_a2c = _a35.options[j].value;
										str += "&" + _a30 + "=" + encodeURIComponent(_a2c);
									}
								}
							}
						}
						if (_a30 != "addparticipants") {
							if (j != _a36) {
								str += "&";
							}
						}
					}
					if (i != _a2d) {
						str += "&";
					}
				} else {
					if (_a2f == "checkbox") {
						if (fobj.elements[i].checked) {
							_a2c = fobj.elements[i].value;
							str += _a30 + "=" + encodeURIComponent(_a2c);
							if (i != _a2d) {
								str += "&";
							}
						}
					} else {
						if (_a2f == "radio") {
							if (fobj.elements[i].checked) {
								_a2c = fobj.elements[i].value;
								str += _a30 + "=" + encodeURIComponent(_a2c);
								if (i != _a2d) {
									str += "&";
								}
							}
						} else {
							if (_a2f == "password") {
								_a2c = fobj.elements[i].value;
								str += _a30 + "=" + encodeURIComponent(_a2c);
								if (i != _a2d) {
									str += "&";
								}
							} else {}
						}
					}
				}
			}
		}
	}
	str = str.substr(0, (str.length - 1));
	return str;
}
function showCompanyProfile(_a37) {
	var _a38 = _a37.document.getElementById("myphoto");
	if (!_a38) {
		return;
	}
	var text = _a38.innerHTML;
	var _a3a = document.getElementById("clogo_upload_status");
	Hide("zoho_upd_clogo_busy");
	if (text.indexOf("UPLOAD IMAGE") != -1) {
		i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.notimage", null,
		function(mesg) {
			_a3a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
	} else {
		if (text.indexOf("SIZE GREATER") != -1) {
			i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.logosize", null,
			function(mesg) {
				_a3a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
		} else {
			if (text.indexOf("ZERO SIZE") != -1) {
				i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.filesizezero", null,
				function(mesg) {
					_a3a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
			} else {
				if (text == "PHOTO UPLOADED") {
					var _a3e = document.getElementById("logohiddenimg").src + "&nocache=" + new Date().getTime();
					document.getElementById("mainCompLogo").src = _a3e;
					document.getElementById("mainCompLogo").className = "";
					document.getElementById("companyLogo").src = _a3e;
					document.getElementById("companyLogo").className = "";
					ShowGenInline("dellogolink");
					ShowHideBlock("viewlogo", "editlogo");
					ShowGenInline("zoho_upd_logo_message");
					setTimeout(function() {
						Hide("zoho_upd_logo_message");
					},
					2000);
				} else {
					document.getElementById("projectcontent").innerHTML = text;
				}
			}
		}
	}
}
function showCompanyPowerProfile(_a3f) {
	var _a40 = _a3f.document.getElementById("myphoto");
	if (!_a40) {
		return;
	}
	var text = _a40.innerHTML;
	var _a42 = document.getElementById("plogo_upload_status");
	Hide("zoho_upd_plogo_busy");
	if (text.indexOf("UPLOAD IMAGE") != -1) {
		i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.notimage", null,
		function(mesg) {
			_a42.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
	} else {
		if (text.indexOf("SIZE GREATER") != -1) {
			i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.logosize", null,
			function(mesg) {
				_a42.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
		} else {
			if (text.indexOf("ZERO SIZE") != -1) {
				i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.filesizezero", null,
				function(mesg) {
					_a42.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
			} else {
				if (text.indexOf("/viewAttachment/") == 0) {
					var _a46 = Utils.contPath + text + "?dispcat=powerlogo&fdate=" + new Date().getTime();
					document.getElementById("powerLogoImg").src = _a46;
					document.getElementById("footerLogo").src = _a46;
					ShowGenInline("delplogolink");
					ShowHideBlock("viewpowerlogo", "editpowerlogo");
					ShowGenInline("zoho_upd_plogo_message");
					setTimeout(function() {
						Hide("zoho_upd_plogo_message");
					},
					2000);
				} else {
					document.getElementById("projectcontent").innerHTML = text;
				}
			}
		}
	}
}
function showMyProfile(_a47) {
	var _a48 = _a47.document.getElementById("myphoto");
	if (!_a48) {
		return;
	}
	var text = _a48.innerHTML;
	var _a4a = document.getElementById("photo_upload_status");
	Hide("zoho_upd_photo_busy");
	if (text.indexOf("UPLOAD IMAGE") != -1) {
		i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.notimage", null,
		function(mesg) {
			_a4a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
	} else {
		if (text.indexOf("SIZE GREATER") != -1) {
			i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.logosize", null,
			function(mesg) {
				_a4a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
		} else {
			if (text.indexOf("ZERO SIZE") != -1) {
				i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.filesizezero", null,
				function(mesg) {
					_a4a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
			} else {
				if (text == "PHOTO UPLOADED") {
					document.getElementById("userphotoimg").src = document.getElementById("userhiddenimg").src + "&nocache=" + new Date().getTime();
					ShowGenInline("delphotolink");
					ShowHideBlock("viewprofile", "editprofile");
					ShowGenInline("zoho_upd_photo_message");
					setTimeout(function() {
						Hide("zoho_upd_photo_message");
					},
					2000);
				} else {
					document.getElementById("projectcontent").innerHTML = text;
				}
			}
		}
	}
}
function zpPlanChange(_a4e, _a4f, str, str1, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	if (!_a4e.responseFromStore) {
		return;
	}
	var _a53 = _a4e.responseFromStore;
	if (_a53.indexOf("PROFILEID=" + _a4f) != -1 && _a53.indexOf("RESULT=" + str) != -1 && _a53.indexOf("RESPMSG=" + str1) != -1) {
		if (_a53.indexOf("zpplanchange||") != -1) {
			var a = _a53.split("||");
			var zpl = a[1];
			var zpo = a[2];
			ajaxShowPage(Utils.contPath + "/changeplan.do?plan=" + zpl + "&planopt=" + zpo + "&" + csrf, "projectcontent");
		}
	} else {
		i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.probupdatecredit", null,
		function(mesg) {
			displayCCMsg(mesg);
		});
		i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.probupdatecredit", null,
		function(mesg) {
			alert(mesg);
		});
		Hide("ajax_load_tab");
	}
}
function zpPlanCancel(_a59, _a5a, str, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	if (!_a59.responseFromStore) {
		return;
	}
	var _a5d = _a59.responseFromStore;
	if (_a5d.indexOf("PROFILEID=" + _a5a) != -1 && _a5d.indexOf("RESULT=" + str) != -1) {
		if (_a5d.indexOf("zpplancancel||") != -1) {
			var a = _a5d.split("||");
			var zpl = a[1];
			var zpo = a[2];
			ajaxShowPage(Utils.contPath + "/changeplan.do?plan=" + zpl + "&planopt=" + zpo + "&" + csrf, "projectcontent");
		}
	} else {
		i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.probupdatecredit", null,
		function(mesg) {
			displayCCMsg(mesg);
		});
		i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.probupdatecredit", null,
		function(mesg) {
			alert(mesg);
		});
	}
}
function zpSwitchMode(_a63, _a64, str, _a66) {
	_a66 = getCSRFEncode(_a66);
	if (!_a63.responseFromStore) {
		return;
	}
	var _a67 = _a63.responseFromStore;
	if (_a67.indexOf("||") != -1) {
		var a = _a67.split("||");
		var _a69 = a[1];
		displayCCMsg("Subscription Mode changed to " + _a69);
		ajaxShowPage(Utils.contPath + "/ccinfoaccount.do?" + _a66, "projectcontent");
	}
}
function zpAddOnUpgrade(_a6a, _a6b, str, _a6d) {
	_a6d = getCSRFEncode(_a6d);
	if (!_a6a.responseFromStore) {
		return;
	}
	Hide("ajax_load_tab");
	var _a6e = _a6a.responseFromStore;
	if (_a6e.indexOf("||") != -1) {
		var a = _a6e.split("||");
		var _a70 = a[1];
		var _a71 = _a70.split("_")[1];
		var _a72 = a[2];
		var _a73 = _a72.split("_")[1];
		if (_a71 == "true") {
			i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.wikiupgraded", null,
			function(mesg) {
				upgChatWikiCCMsg(mesg);
			});
		}
		if (_a73 == "true") {
			i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.chatupgraded", null,
			function(mesg) {
				upgChatWikiCCMsg(mesg);
			});
		}
		ajaxShowPage(Utils.contPath + "/ccinfoaccount.do?" + _a6d, "projectcontent");
	}
}
function zpCCVal(_a76, str, str1, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	if (!_a76.responseFromStore) {
		return;
	}
	var _a7a = _a76.responseFromStore;
	if (_a7a.indexOf("RESULT=" + str) != -1 && _a7a.indexOf("RESPMSG=" + str1) != -1) {
		if (_a7a.indexOf("zpplanchange||") != -1) {
			var a = _a7a.split("||");
			var zpl = a[1];
			var zpo = a[2];
			if (zpo != "update") {
				ajaxShowPage(Utils.contPath + "/changeplan.do?plan=" + zpl + "&planopt=" + zpo + "&" + csrf, "projectcontent");
			} else {
				ajaxShowTab(Utils.contPath + "/ccinfoaccount.do?" + csrf, "projectcontent");
			}
		}
	} else {
		i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.probupdatecredit", null,
		function(mesg) {
			displayCCMsg(mesg);
		});
		i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.probupdatecredit", null,
		function(mesg) {
			alert(mesg);
		});
		enableButton("cc_submit_button");
	}
	Hide("customloadingdiv");
}
function showUserProfile(_a80) {
	var mid = _a80.document.getElementById("userprof");
	if (!mid) {
		return;
	}
	var _a82 = document.getElementById("projectcontent");
	_a82.innerHTML = mid.innerHTML;
}
function showMulUploadDoc(_a83, msg) {
	var _a85 = _a83.document;
	var _a86 = _a85.getElementById("myupload");
	var _a87 = _a85.getElementById("projectdocs");
	var text = "";
	if (_a86) {
		text = _a86.innerHTML;
	} else {
		if (_a87) {
			text = _a87.innerHTML;
		} else {
			return;
		}
	}
	var doc = document;
	var _a8a = doc.getElementById("muldoc_upload_status");
	if (text.indexOf("Allocated Disc Space Full") != -1) {
		Hide("zoho_add_docs_busy");
		i18n.getJSAlertValue(Utils.zuid, "zp.newpost.diskfull", null,
		function(mesg) {
			_a8a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
	} else {
		if (text.indexOf("File with zero size cannot be uploaded") != -1) {
			Hide("zoho_add_docs_busy");
			i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.filesizezero", null,
			function(mesg) {
				_a8a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
		} else {
			if (text.indexOf("FREE PLAN SIZE GREATER") != -1) {
				Hide("zoho_add_docs_busy");
				i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.freeplangreatersize", null,
				function(mesg) {
					_a8a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
			} else {
				if (text.indexOf("SIZE GREATER") != -1) {
					Hide("zoho_add_docs_busy");
					i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.greatersize", null,
					function(mesg) {
						_a8a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
				} else {
					var _a8f = doc.getElementById("projectdocs");
					_a8f.innerHTML = text;
					pmevalScript(_a8f);
					bindMoT("doclist");
				}
			}
		}
	}
}
function showForumAttach(_a90) {
	var _a91 = _a90.document;
	var _a92 = _a91.getElementById("myupload");
	var _a93 = _a91.getElementById("forumdiv");
	var text = "";
	if (_a92) {
		text = _a92.innerHTML;
	} else {
		if (_a93) {
			text = _a93.innerHTML;
		} else {
			return;
		}
	}
	var doc = document;
	var _a96 = doc.getElementById("forum_post_add_status");
	if (text.indexOf("Allocated Disc Space Full") != -1) {
		Hide("zoho_add_post_busy");
		i18n.getJSAlertValue(Utils.zuid, "zp.newpost.diskfull", null,
		function(mesg) {
			_a96.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
	} else {
		if (text.indexOf("No Forum Category To Post") != -1) {
			Hide("zoho_add_post_busy");
			i18n.getJSAlertValue(Utils.zuid, "zp.forums.adforcatopost", null,
			function(mesg) {
				_a96.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
		} else {
			if (text.indexOf("SIZE GREATER") != -1) {
				Hide("zoho_add_post_busy");
				i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.greatersize", null,
				function(mesg) {
					_a96.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
			} else {
				var _a9a = doc.getElementById("forumdiv");
				_a9a.innerHTML = text;
				delete Utils.editorObj["editordiv"];
				initLightbox();
			}
		}
	}
}
function showUpdForumAttach(_a9b, id) {
	var _a9d = _a9b.document;
	var _a9e = _a9d.getElementById("myupload");
	var _a9f = _a9d.getElementById("forumdiv");
	var text = "";
	if (_a9e) {
		text = _a9e.innerHTML;
	} else {
		if (_a9f) {
			text = _a9f.innerHTML;
		} else {
			return;
		}
	}
	var doc = document;
	var _aa2 = doc.getElementById("forum_post_upd_status_" + id);
	if (text.indexOf("Allocated Disc Space Full") != -1) {
		Hide("zoho_update_post_busy_" + id);
		i18n.getJSAlertValue(Utils.zuid, "zp.newpost.diskfull", null,
		function(mesg) {
			_aa2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
	} else {
		if (text.indexOf("SIZE GREATER") != -1) {
			Hide("zoho_update_post_busy_" + id);
			i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.greatersize", null,
			function(mesg) {
				_aa2.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
		} else {
			var _aa5 = doc.getElementById("forumdiv");
			_aa5.innerHTML = text;
			delete Utils.editorObj["editordiv_" + id];
			initLightbox();
		}
	}
}
function showUpdChatAttach(_aa6, id) {
	var _aa8 = _aa6.document;
	var _aa9 = _aa8.getElementById("myupload");
	var _aaa = _aa8.getElementById("chatattachdiv");
	var text = "";
	if (_aa9) {
		text = _aa9.innerHTML;
	} else {
		if (_aaa) {
			text = _aaa.innerHTML;
		} else {
			return;
		}
	}
	Hide("zoho_upload_chatfile_busy_" + id);
	if (text.indexOf("Allocated Disc Space Full") != -1) {
		i18n.getJSAlertValue(Utils.zuid, "zp.newpost.diskfull", null,
		function(mesg) {
			alert(mesg);
		});
	} else {
		if (text.indexOf("SIZE GREATER") != -1) {
			i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.greatersize", null,
			function(mesg) {
				alert(mesg);
			});
		}
	}
	document.uploadChatFileForm.reset();
	ShowHideInline("uplfilelink", "uplchatfile");
	initLightbox();
}
function showForumComAttach(_aae) {
	var _aaf = _aae.document;
	var _ab0 = _aaf.getElementById("myupload");
	var _ab1 = _aaf.getElementById("forumcomdiv");
	var text = "";
	if (_ab0) {
		text = _ab0.innerHTML;
	} else {
		if (_ab1) {
			text = _ab1.innerHTML;
		} else {
			return;
		}
	}
	var doc = document;
	var _ab4 = doc.getElementById("fcomment_add_status");
	if (text.indexOf("Allocated Disc Space Full") != -1) {
		Hide("zoho_add_comment_busy");
		i18n.getJSAlertValue(Utils.zuid, "zp.newpost.diskfull", null,
		function(mesg) {
			_ab4.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
	} else {
		if (text.indexOf("SIZE GREATER") != -1) {
			Hide("zoho_add_comment_busy");
			i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.greatersize", null,
			function(mesg) {
				_ab4.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
		} else {
			var _ab7 = doc.getElementById("forumcomdiv");
			_ab7.innerHTML = text;
			delete Utils.editorObj["comeditordiv"];
			loadZEditor("comeditordiv", "");
			initLightbox();
		}
	}
}
function showUpdForumComAttach(_ab8, id) {
	var _aba = _ab8.document;
	var _abb = _aba.getElementById("myupload");
	var _abc = _aba.getElementById("forumcomdiv");
	var text = "";
	if (_abb) {
		text = _abb.innerHTML;
	} else {
		if (_abc) {
			text = _abc.innerHTML;
		} else {
			return;
		}
	}
	var doc = document;
	var _abf = doc.getElementById("fcomment_upd_status_" + id);
	if (text.indexOf("Allocated Disc Space Full") != -1) {
		Hide("zoho_upd_comment_busy_" + id);
		i18n.getJSAlertValue(Utils.zuid, "zp.newpost.diskfull", null,
		function(mesg) {
			_abf.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
	} else {
		if (text.indexOf("SIZE GREATER") != -1) {
			Hide("zoho_upd_comment_busy_" + id);
			i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.greatersize", null,
			function(mesg) {
				_abf.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
		} else {
			var _ac2 = doc.getElementById("forumcomdiv");
			_ac2.innerHTML = text;
			delete Utils.editorObj["comeditordiv_" + id];
			loadZEditor("comeditordiv", "");
			initLightbox();
		}
	}
}
function showUploadNextDoc(_ac3) {
	var _ac4 = _ac3.document;
	var _ac5 = _ac4.getElementById("myupload");
	var _ac6 = _ac4.getElementById("docversionupdate");
	var text = "";
	if (_ac5) {
		text = _ac5.innerHTML;
	} else {
		if (_ac6) {
			text = _ac6.innerHTML;
		} else {
			return;
		}
	}
	var doc = document;
	var _ac9 = doc.getElementById("upload_next_version");
	if (text.indexOf("Allocated Disc Space Full") != -1) {
		Hide("zoho_update_doc_busy");
		i18n.getJSAlertValue(Utils.zuid, "zp.newpost.diskfull", null,
		function(mesg) {
			_ac9.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
	} else {
		if (text.indexOf("Filename Mismatch") != -1) {
			Hide("zoho_update_doc_busy");
			i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.fnamemismatch", null,
			function(mesg) {
				_ac9.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
		} else {
			if (text.indexOf("Content Type Mismatch") != -1) {
				Hide("zoho_update_doc_busy");
				i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.contypemismatch", null,
				function(mesg) {
					_ac9.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
			} else {
				if (text.indexOf("File with zero size cannot be uploaded") != -1) {
					Hide("zoho_update_doc_busy");
					i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.filesizezero", null,
					function(mesg) {
						_ac9.innerHTML = "<span class=\"error\">" + mesg + "</span>";
					});
				} else {
					if (text.indexOf("SIZE GREATER") != -1) {
						Hide("zoho_update_doc_busy");
						i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.greatersize", null,
						function(mesg) {
							_ac9.innerHTML = "<span class=\"error\">" + mesg + "</span>";
						});
					} else {
						var _acf = doc.getElementById("docversionupdate");
						_acf.innerHTML = text;
					}
				}
			}
		}
	}
}
function ajaxValidateSignUp(url, _ad1, _ad2) {
	var _ad3 = url;
	var eval = document.getElementById(_ad1).value;
	eval = encodeURIComponent(eval);
	if (_ad1 == "adminname" || _ad1 == "email") {
		_ad3 = url + "?vcrit=email&vval=" + eval;
	} else {
		if (_ad1 == "portal") {
			_ad3 = url + "?vcrit=porturl&vval=" + eval;
		} else {
			if (_ad1 == "newuseremail") {
				_ad3 = url + "&vcrit=newacc&vval=" + eval;
			}
		}
	}
	_ad3 = fixForCachingInIe(_ad3);
	var _ad5 = getURLArgs(_ad3);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _ad5.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _ad8 = document.getElementById(_ad2);
				_ad8.innerHTML = text;
				ShowGenBlock(_ad2);
			}
		};
		http.send(_ad5.args);
	}
}
function uploadChatFile(_ad9) {
	_ad9.conversid.value = curCompId;
	_ad9.chatsessid.value = _CSESSID;
}
function ajaxShowReport(url, _adb) {
	var _adc = document.getElementById(_adb);
	var text = "<img src=\"" + url + "&fdate=" + (new Date()).getTime() + " width=40px\">";
	_adc.innerHTML = text;
}
function importCSS(doc, _adf) {
	if (_adf == "") {
		return;
	}
	if (typeof(doc.createStyleSheet) == "undefined") {
		var elm = doc.createElement("link");
		elm.rel = "stylesheet";
		elm.href = _adf;
		if ((headArr = doc.getElementsByTagName("head")) != null && headArr.length > 0) {
			headArr[0].appendChild(elm);
		}
	} else {
		var _ae1 = doc.createStyleSheet(_adf);
	}
}
function removeCSS(doc, _ae3) {
	if (_ae3 == "") {
		return;
	}
	var i, a, main;
	for (i = 0; (a = document.getElementsByTagName("link")[i]); i++) {
		if (a.href == _ae3) {
			if ((headArr = doc.getElementsByTagName("head")) != null && headArr.length > 0) {
				headArr[0].removeChild(a);
			}
		}
	}
}
function changeImageSkins(_ae7, _ae8) {
	var imgs = document.getElementsByTagName("img");
	for (i = 0; i < imgs.length; i++) {
		var src = imgs[i].src;
		if (src.indexOf("images/" + _ae7 + "/") >= 0) {
			src = src.replace("images/" + _ae7 + "/", "images/" + _ae8 + "/");
		}
		imgs[i].src = src;
	}
}
function applyTheme() {
	if (Utils.currentThemeSelect) {
		var _aeb = Utils.previousTheme;
		if (_aeb == "undefined") {
			_aeb = "lightblue";
		}
		removeCSS(document, "styles/" + _aeb + "/general.css");
		importCSS(document, "styles/" + Utils.currentThemeSelect + "/general.css");
		changeImageSkins(_aeb, Utils.currentThemeSelect);
		Utils.previousTheme = Utils.currentThemeSelect;
	}
}
function ajaxShowTZPage(csrf, _aed) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var _aee = getHTTPObject();
	var _aef = fixForCachingInIe(Utils.contPath + "/usersettings.do?" + csrf);
	var _af0 = getURLArgs(_aef);
	if (_aee) {
		_aee.open("POST", _af0.url, true);
		_aee.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		_aee.onreadystatechange = function() {
			if (_aee.readyState == 4) {
				var text = _aee.responseText;
				var _af2 = document.getElementById(_aed);
				_af2.innerHTML = text;
				pmevalScript(_af2);
				Hide("ajax_load_tab");
				setTimeout(function() {
					i18n.getJSAlertValue(Utils.zuid, "zp.settings.companyprof", null,
					function(mesg) {
						setBrowserTitle(mesg);
					});
					changeSubLink("compLink", "compImg");
					ajaxShowPage(Utils.contPath + "/companyprofile.do?todisp=editprof&" + csrf, "projectcontent");
				},
				300);
			}
		};
		_aee.send(_af0.args);
	}
}
function ajaxCompanyTZ(csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	ajaxShowTZPage(csrf, "projectpagediv");
}
function pmevalScript(_af5) {
	var _af6 = _af5.getElementsByTagName("SCRIPT");
	var zlen = _af6.length;
	for (var jsat = 0; jsat < zlen; jsat++) {
		var _af9 = _af6[jsat].innerHTML;
		eval(_af9);
	}
}
function validateRespMessage(text) {
	if (text.indexOf("zzzPstat||deactive||") != -1) {
		i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.admindwngradewarn", null,
		function(mesg) {
			alert(mesg);
		});
		window.location.reload();
	} else {
		if (text.indexOf("zzzPstat||nopermission||") != -1) {
			i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.accessdenied", null,
			function(mesg) {
				alert(mesg);
			});
			window.location.reload();
		} else {
			if (text.indexOf("zzzPstat||archived||") != -1) {
				i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.archivedprojwarn", null,
				function(mesg) {
					alert(mesg);
				});
				window.location.reload();
			} else {
				return true;
			}
		}
	}
}
function ajaxSaveAlpProjectOrder(url, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var _b00 = fixForCachingInIe(url);
	var _b01 = getURLArgs(_b00);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _b01.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				if (url.indexOf("saveprojorder.do") != -1) {
					ajaxShowPage(Utils.contPath + "/reorderprojpage.do?" + csrf, "projectcontent");
				}
			}
		};
		http.send(_b01.args);
	}
}
function takeToUpgradePage(_b03) {
	_b03 = getCSRFEncode(_b03);
	var _b04 = fixForCachingInIe(Utils.contPath + "/myhome.do?" + _b03);
	var _b05 = "projectpagediv";
	var _b06 = getURLArgs(_b04);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _b06.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _b09 = document.getElementById(_b05);
				_b09.innerHTML = text;
				ini = false;
				pmevalScript(_b09);
				setTimeout(function() {
					ajaxShowPage(Utils.contPath + "/planaccount.do?" + _b03, "projectcontent");
					changeTabStyle("billing");
				},
				300);
			}
		};
		http.send(_b06.args);
	}
}
function ajaxMoveSubmitAnchorPage(url, _b0b, _b0c, _b0d) {
	if (_b0b != "") {
		var args = "?" + getFormValues(_b0b);
		var _b0f = url + args;
		var _b10 = _b0b.name;
	} else {
		var _b0f = url;
	}
	_b0f = fixForCachingInIe(_b0f);
	var _b11 = getURLArgs(_b0f);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _b11.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				if (url.indexOf("movetodotask.do") != -1 || url.indexOf("movebulktask.do") != -1) {
					if (text.indexOf("zzzmovedtozzz||") != -1) {
						var a = text.split("||");
						var tid = a[2].split(",");
						for (z = 0; z < tid.length; z++) {
							replacePrevTask(tid[z]);
							jQuery("#task_" + tid[z]).remove();
							jQuery("#taskaction_" + tid[z]).remove();
							jQuery("[task_" + tid[z] + "=" + tid[z] + "]").remove();
						}
						var _b16 = document.getElementById("ul_ttask_" + a[1]);
						if (_b16) {
							if (is_ie) {
								jQuery("#ul_ttask_" + a[1]).replaceWith("<tbody id=\"#ul_ttask_" + a[1] + "\">" + text + "</tbody>");
							} else {
								_b16.innerHTML = text;
							}
						}
						i18n.getJSAlertValue(Utils.zuid, "zp.tasks.movesuc", null,
						function(mesg) {
							displayFadeMsg(mesg);
						});
						if (url.indexOf("movetodotask.do") != -1) {
							var hid = "task_" + a[2];
							if (hid) {
								new Effect.ScrollTo("task_" + tid[z], {
									duration: 0.1
								});
								document.getElementById(hid).style.backgroundColor = "#ffffcc";
								setTimeout(function() {
									document.getElementById(hid).style.backgroundColor = "#FFFFFF";
								},
								3000);
							}
						}
					}
					if (document.getElementById("gTaskId") && document.getElementById("csrftoken")) {
						var _b19 = document.getElementById("gTaskId").innerHTML;
						var _b1a = document.getElementById("csrftoken").innerHTML;
						if (_b19 != null) {
							var _b1b = _b19.split("||");
							var _b1c = _b1b[1].split("#");
							ajaxSendRequest(Utils.contPath + "/gtaskaction.do?taskid=" + _b1c[0] + "&taskaction=" + _b1c[1] + "&oldtlistId=" + _b1c[2] + "&" + _b1a);
						}
					}
					var tlid = _b0c.split("_")[2];
					if (url.indexOf("movebulktask.do") != -1) {
						selTaskCheck = jQuery.grep(selTaskCheck,
						function(_b1e) {
							return _b1e != tlid;
						});
						Effect.SlideUp("todoactionslide_" + tlid);
						jQuery("#movetaskid").hide();
					}
				}
			}
		};
		http.send(_b11.args);
	}
	return true;
}
function showOpenForumComAttach(_b1f) {
	var mid = _b1f.document.getElementById("forumcomdiv");
	if (!mid) {
		return;
	}
	var text = mid.innerHTML;
	if (text.indexOf("Allocated Disc Space Full") != -1) {
		Hide("zoho_add_comment_busy");
		var _b22 = document.getElementById("fcomment_add_status");
		_b22.innerHTML = "<span class=\"error\">" + text + "</span>";
	} else {
		var _b23 = document.getElementById("projectcontent");
		_b23.innerHTML = text;
		ini = false;
	}
}
function showOpenUpdForumComAttach(_b24, id) {
	var mid = _b24.document.getElementById("forumcomdiv");
	if (!mid) {
		return;
	}
	var text = mid.innerHTML;
	if (text.indexOf("Allocated Disc Space Full") != -1) {
		Hide("zoho_upd_comment_busy_" + id);
		var _b28 = document.getElementById("fcomment_upd_status_" + id);
		_b28.innerHTML = "<span class=\"error\">" + text + "</span>";
	} else {
		var _b29 = document.getElementById("projectcontent");
		_b29.innerHTML = text;
		ini = false;
	}
}
function ajaxAddMppTasks(_b2a, _b2b, ct, _b2d, _b2e, _b2f, csrf, _b31) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var _b32 = getHTTPObject();
	var act = Utils.contPath + "/mpptadd.do";
	var _b34 = fixForCachingInIe(act + "?ctime=" + ct + "&tskass=" + _b2a + "&projId=" + _b2b + "&mppname=" + _b2f + "&" + csrf + "&blockId=" + _b31);
	var _b35 = getURLArgs(_b34);
	if (_b32) {
		_b32.open("POST", _b35.url, true);
		_b32.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		_b32.onreadystatechange = function() {
			if (_b32.readyState == 4) {
				setTimeout(function() {
					var tgt1 = Utils.contPath + "/tasklistview.do?projId=" + _b2b + "&projstat=active&istabenabled=yes&" + csrf;
					ajaxShowPage(tgt1, "projectpagediv");
				},
				300);
			}
		};
		_b32.send(_b35.args);
	}
}
function ajaxShowActivityPage(url, _b38, prid, _b3a, _b3b, _b3c, _b3d, _b3e, _b3f, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	if (_b38 == "status" || _b38 == "status comment") {
		if (_b3e == "homepage") {
			ajaxShowTab(Utils.contPath + "/showproject.do?projId=" + prid + "&toview=overview&istabenabled=yes" + "&projstat=" + _b3b + "&activity=" + _b38 + "&actid=" + _b3a + "&homepageact=yes&" + csrf, "projectpagediv", csrf);
			return;
		} else {
			ShowHideBlock("userStat", "projAct");
			if (_b38 == "status comment") {
				_b3a = "sts_cmt_" + _b3a;
			}
			ajaxShowSearchPage(csrf, Utils.contPath + "/fetchuserstatus.do?projId=" + prid + "&sindex=1&" + csrf, "userstatus", _b3a);
			return;
		}
	} else {
		if (_b38 == "announcement") {
			if (_b3e == "homepage") {
				ajaxShowTab(Utils.contPath + "/showproject.do?projId=" + prid + "&toview=overview&istabenabled=yes" + "&projstat=" + _b3b + "&activity=" + _b38 + "&actid=" + _b3a + "&homepageact=yes&" + csrf, "projectpagediv", csrf);
			} else {
				ShowGenInline("zoho_activity_busy");
				ajaxShowPage(Utils.contPath + "/fetchprojannouncement.do?projId=" + prid + "&sindex=1&" + csrf, "projannouncement:" + _b3a);
			}
			return;
		} else {
			if (_b38 == "wiki") {
				if (_b3e == "homepage") {
					ShowGenBlock("ajax_load_tab");
					ajaxShowTab(Utils.contPath + "/showproject.do?projId=" + prid + "&toview=wiki&istabenabled=yes&" + csrf, "projectpagediv");
					return;
				} else {
					ShowGenBlock("ajax_load_tab");
					changeProjTabStyle("wiki");
					ajaxShowTab(Utils.contPath + "/showproject.do?projId=" + prid + "&toview=wiki&" + csrf, "projectcontent");
					return;
				}
			} else {
				if (_b38 == "project") {
					Hide("ajax_load_tab");
					return;
				}
			}
		}
	}
	if (_b38 == "issue" || _b38 == "issueattach" || _b38 == "issueresolution" || _b38 == "issuecomment" || _b38 == "issueStatus" || _b38 == "issueSeverity" || _b38 == "issueType" || _b38 == "issueModule" || _b38 == "issuemilestone" || _b38 == "issueOwner" || _b38 == "issueFlag" || _b38 == "issueDue" || _b38 == "issuePriority" || _b38 == "issueDescription" || _b38 == "issueSubject" || _b38.indexOf("issueUDF_") != -1) {
		ShowGenBlock("ajax_load_tab");
		changeProjTabStyle("issue");
		if (_b3e == "homepage") {
			showIssuesTab(Utils.contPath + "/showissue.do?projId=" + prid + "&istabenabled=yes&issueId=" + _b3a + "&" + csrf, "", "projectpagediv");
		} else {
			showIssuesTab(Utils.contPath + "/showissue.do?projId=" + prid + "&issueId=" + _b3a + "&" + csrf, "", "projectcontent");
		}
		return;
	} else {
		if (_b38 == "milestonebugs") {
			listView_MileFilter[prid] = "" + _b3a;
			if (_b3e == "homepage") {
				showIssuesTab(Utils.contPath + "/showIssuesTab.do?projId=" + prid + "&istabenabled=yes&" + csrf, "", "projectpagediv", prid);
			} else {
				showIssuesTab(Utils.contPath + "/showIssuesTab.do?projId=" + prid + "&" + csrf, "", "projectcontent", prid);
			}
			return;
		}
	}
	if (_b38 == "changeset") {
		var _b41 = Utils.contPath + "/showchangeset.do";
		_b41 += "?projId=" + prid + "&chsetId=" + _b3a + "&istabenabled=yes&" + csrf;
		ShowGenInline("ajax_load_tab");
		modifyURL("changesets/" + prid + "/" + _b3a);
		ajaxShowTab(_b41, "projectpagediv");
		return;
	}
	if (_b38 != "project") {
		var _b42 = fixForCachingInIe(url);
		var _b43 = getURLArgs(_b42);
		var http = getHTTPObject();
		if (http) {
			http.open("POST", _b43.url, true);
			http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			http.onreadystatechange = function() {
				if (http.readyState == 4) {
					var text = http.responseText;
					var res = text.split(":");
					if (text.indexOf("status") != -1) {
						var _b47 = res[1];
						if (_b47 == "delete") {
							i18n.getJSAlertValue(Utils.zuid, "zp.dashboard.datadeleted", null,
							function(mesg) {
								alert(mesg);
							});
							Hide("ajax_load_tab");
							return;
						}
					}
					var _b49 = res[1];
					if (_b38 == "milestone") {
						getSearchMiles(csrf, prid, _b3d, _b3b, _b3a, "delmilefeat_" + _b3a);
					} else {
						if (_b38 == "tasklist") {
							getSearchTaskList(csrf, prid, _b3d, _b3b, _b49, "ul_ttitle_" + _b3a, _b3a);
						} else {
							if (_b38 == "task" || _b38 == "subtask") {
								var tlid = res[3];
								getSearchTaskList(csrf, prid, _b3d, _b3b, _b49, "task_" + _b3a, tlid);
							} else {
								if (_b38 == "meeting") {
									getSearchMeets(csrf, prid, _b3b, "dispmeet_" + _b3a, _b49);
								} else {
									if (_b38 == "meeting notes") {
										var _b4b = res[3];
										getSearchMeets(csrf, prid, _b3b, "dispmeet_" + _b4b, _b49, "&ismeetnotes=yes");
									} else {
										if (_b38 == "forum") {
											getSearchMesgs(csrf, prid, _b3a, _b49, _b3c);
										} else {
											if (_b38 == "comment") {
												var _b4c = res[3];
												getSearchMesgs(csrf, prid, _b4c, _b49, _b3c, _b3a);
											} else {
												if (_b38 == "forumcategory") {
													changeProjTabStyle("forum");
													if (_b3e == "homepage") {
														modifyURL("categories/" + prid + "/" + _b3a);
														ajaxSubmitPage(Utils.contPath + "/fetchmessage.do?projId=" + prid + "&catid=" + _b3a + "&sindex=1&istabenabled=yes&" + csrf, "", "projectpagediv");
													} else {
														modifyURL("categories/" + prid + "/" + _b3a);
														ajaxSubmitPage(Utils.contPath + "/fetchmessage.do?projId=" + prid + "&catid=" + _b3a + "&sindex=1&" + csrf, "", "projectcontent");
													}
												} else {
													if (_b38 == "file") {
														if (_b3e == "homepage") {
															ShowGenBlock("ajax_load_tab");
															modifyURL("documents/" + prid + "/" + _b3a);
															ajaxShowPage(Utils.contPath + "/fetchdocdetails.do?projId=" + prid + "&folderid=" + _b49 + "&docid=" + _b3a + "&istabenabled=yes&" + csrf, "projectpagediv");
															changeProjTabStyle("doc");
														} else {
															ShowGenBlock("ajax_load_tab");
															changeProjTabStyle("doc");
															modifyURL("documents/" + prid + "/" + _b3a);
															ajaxShowPage(Utils.contPath + "/fetchdocdetails.do?projId=" + prid + "&folderid=" + _b49 + "&docid=" + _b3a + "&" + csrf, "projectcontent");
														}
													} else {
														if (_b38 == "doccomments") {
															var _b4c = res[3];
															var val = "shownotes_mn_" + _b3a;
															if (_b3e == "homepage") {
																ShowGenBlock("ajax_load_tab");
																changeProjTabStyle("doc");
																modifyURL("documents/" + prid + "/" + _b49);
																ajaxShowPageWithParam(Utils.contPath + "/fetchdocdetails.do?projId=" + prid + "&folderid=" + _b4c + "&docid=" + _b49 + "&istabenabled=yes&" + csrf, "projectpagediv", csrf + ":" + prid + ":" + _b49 + ":" + val);
															} else {
																ShowGenBlock("ajax_load_tab");
																changeProjTabStyle("doc");
																modifyURL("documents/" + prid + "/" + _b49);
																ajaxShowPageWithParam(Utils.contPath + "/fetchdocdetails.do?projId=" + prid + "&folderid=" + _b4c + "&docid=" + _b49 + "&" + csrf, "projectcontent", csrf + ":" + prid + ":" + _b49 + ":" + val);
															}
														} else {
															if (_b38 == "folder") {
																if (_b3e == "homepage") {
																	modifyURL("folders/" + prid + "/" + _b3a);
																	ShowGenBlock("ajax_load_tab");
																	changeProjTabStyle("doc");
																	ajaxShowPage(Utils.contPath + "/fetchdocuments.do?projId=" + prid + "&folderid=" + _b3a + "&sindex=1&sortby=addedtime&order=false&istabenabled=yes&" + csrf, "projectpagediv");
																} else {
																	modifyURL("folders/" + prid + "/" + _b3a);
																	ShowGenBlock("ajax_load_tab");
																	changeProjTabStyle("doc");
																	ajaxShowPage(Utils.contPath + "/fetchdocuments.do?projId=" + prid + "&folderid=" + _b3a + "&sindex=1&sortby=addedtime&order=false&" + csrf, "projectcontent"); + _b49;
																}
															} else {
																if (_b38 == "notes") {
																	var tid = res[3];
																	var tlid = res[5];
																	getSearchMiles(csrf, prid, _b3d, _b3b, _b49, "todolitem_" + tid, tlid, "&isnotes=yes&actid=" + _b3a);
																} else {
																	if (_b38 == "wikipage") {
																		var url = _b49 + ":" + res[2];
																		_b3f = "/" + _b3f + ".html";
																		if (_b3e == "homepage") {
																			ajaxShowWikiPage(Utils.contPath + "/showproject.do?projId=" + prid + "&toview=wiki&istabenabled=yes&" + csrf, "projectpagediv", url, _b3f);
																		} else {
																			ShowGenBlock("ajax_load_tab");
																			changeProjTabStyle("wiki");
																			ajaxShowWikiPage(Utils.contPath + "/showproject.do?projId=" + prid + "&toview=wiki&" + csrf, "projectcontent", url, _b3f);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			};
			http.send(_b43.args);
		}
	}
}
function ajaxShowWikiPage(url, _b51, page, _b53) {
	var _b54 = fixForCachingInIe(url);
	var _b55 = getURLArgs(_b54);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _b55.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _b58 = document.getElementById(_b51);
				Hide("ajax_load_tab");
				_b58.innerHTML = text;
				var _b59 = document.getElementById("wikiembed");
				_b59.src = page + _b53;
			}
		};
		http.send(_b55.args);
	}
}
function ajaxPurchasePage(url, _b5b, _b5c, _b5d, plan) {
	var _b5f = getHTTPObject();
	var _b60 = fixForCachingInIe(url);
	var _b61 = getURLArgs(_b60);
	if (_b5f) {
		_b5f.open("POST", _b61.url, true);
		_b5f.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		_b5f.onreadystatechange = function() {
			if (_b5f.readyState == 4) {
				var text = _b5f.responseText;
				var _b63 = document.getElementById(_b5b);
				_b63.innerHTML = text;
				ini = false;
				pmevalScript(_b63);
				changeTabStyle("billing");
				ajaxShowPage(Utils.contPath + "/ccinfo.do?purchase=true&plan=" + plan + "&frompage=upgrade&profileid=null&" + _b5c + "=" + encodeURIComponent(_b5d), "accttxn");
				initLightbox();
			}
		};
		_b5f.send(_b61.args);
	}
}
function showBumpImport(_b64) {
	var _b65 = _b64.document;
	var text = _b65.getElementById("bdivid").innerHTML;
	var _b67 = document.getElementById("bump_status");
	if (text) {
		var msg = text.split("#")[1];
		_b67.innerHTML = "<span class=\"error\">" + msg + "</span>";
		Hide("zoho_bump_busy");
	}
}
function ajaxShowPageWithParam(url, _b6a, _b6b) {
	var _b6c = getHTTPObject();
	var _b6d = fixForCachingInIe(url);
	var _b6e = getURLArgs(_b6d);
	if (_b6c) {
		_b6c.open("POST", _b6e.url, true);
		_b6c.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		_b6c.onreadystatechange = function() {
			if (_b6c.readyState == 4) {
				var text = _b6c.responseText;
				var _b70 = document.getElementById(_b6a);
				_b70.innerHTML = text;
				ini = false;
				pmevalScript(_b70);
				if (url.indexOf("fetchdocdetails.do") != -1) {
					if (_b6b.indexOf(":") != -1) {
						var _b71 = _b6b.split(":");
						var csrf = _b71[0];
						if (typeof(csrf) == "undefined") {} else {
							csrf = getCSRFEncode(csrf);
						}
						var prid = _b71[1];
						var _b74 = _b71[2];
						var val = _b71[3];
						getSearchDocs(csrf, prid, _b74, val);
					}
				}
				Hide("ajax_load_tab");
			}
		};
		_b6c.send(_b6e.args);
	}
}
function ajaxAutoFill(url) {
	var _b77 = getHTTPObject(),
	_b78 = fixForCachingInIe(url),
	_b79 = getURLArgs(_b78);
	if (_b77) {
		_b77.open("POST", _b79.url, true);
		_b77.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		_b77.onreadystatechange = function() {
			if (_b77.readyState == 4) {
				var text = _b77.responseText;
				Utils._newArray = eval(text);
				Utils._newArray.sort();
			}
		};
		_b77.send(_b79.args);
	}
}
function matchSample(str) {
	Utils._newArray.sort();
	var temp = new Array();
	var _b7d = 10;
	for (z = 0; z < Utils._newArray.length; z++) {
		tmp = Utils._newArray[z];
		var re = new RegExp("^" + str + ".*?", "gi");
		var m = re.exec(tmp);
		if (m && m.index == 0) {
			temp[temp.length] = tmp;
			if (_b7d && temp.length == _b7d) {
				break;
			}
		}
	}
	return temp;
}
function showUpdMesgForm(_b80, id) {
	var _b82 = _b80.document;
	var _b83 = _b82.getElementById("myupload");
	var _b84 = _b82.getElementById("forumcomdiv");
	var text = "";
	if (_b83) {
		text = _b83.innerHTML;
	} else {
		if (_b84) {
			text = _b84.innerHTML;
		} else {
			return;
		}
	}
	var doc = document;
	var _b87 = doc.getElementById("forum_post_upd_status_" + id);
	if (text.indexOf("Allocated Disc Space Full") != -1) {
		Hide("zoho_update_post_busy_" + id);
		i18n.getJSAlertValue(Utils.zuid, "zp.newpost.diskfull", null,
		function(mesg) {
			_b87.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
	} else {
		if (text.indexOf("SIZE GREATER") != -1) {
			Hide("zoho_update_post_busy_" + id);
			i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.greatersize", null,
			function(mesg) {
				_b87.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
		} else {
			var _b8a = doc.getElementById("forumcomdiv");
			_b8a.innerHTML = text;
			delete Utils.editorObj["editordiv_" + id];
			loadZEditor("comeditordiv", "");
			initLightbox();
		}
	}
}
function processCalendar(data, _b8c) {
	var _b8d = data.objString;
	var _b8e = document.getElementById("stDate").value;
	var _b8f = document.getElementById("edDate").value;
	var _b90 = document.getElementById("flagDisp").value;
	if (_b8d.length != 0) {
		var _b91 = "12px";
		var _b92 = 12;
		for (var i = 0; i < _b8d.length; i++) {
			var ms = _b8d[i];
			var sdte = ms.SD;
			var edte = ms.ED;
			var lgap = ms.LG;
			var mid = ms.MID;
			var dom = ms.DOM;
			var _b9a = ms.MTITLE;
			var _b9b = ms.MFLAG;
			var osd = ms.ORIGSD;
			var oed = ms.ORIGED;
			var _b9e = ms.BHEX;
			var _b9f = ms.BCOLOR;
			var pid = ms.PROJID;
			var _ba1 = ms.STYLENAME;
			var _ba2 = ms.PROJSTAT;
			var _ba3 = ms.CSRFPARAM;
			var _ba4 = ms.CSRFTOKEN;
			var _ba5 = "milestone_" + mid;
			var _ba6 = sdte.split("/");
			var _ba7 = _ba6[0];
			var _ba8 = _ba6[1];
			var _ba9 = _ba6[2];
			var _baa = osd.split("/");
			var _bab = _baa[1];
			var _bac = _baa[2];
			var _bad = _baa[1] + "-" + _baa[0] + "-" + _baa[2];
			var _bae = edte.split("/");
			var _baf = _bae[0];
			var _bb0 = _bae[1];
			var _bb1 = _bae[2];
			var _bb2 = oed.split("/");
			var _bb3 = _bb2[1];
			var _bb4 = _bb2[2];
			var _bb5 = _bb2[1] + "-" + _bb2[0] + "-" + _bb2[2];
			var _bb6 = (parseInt(_ba7, 10) + parseInt(lgap, 10)) % 7;
			var _bb7 = parseInt(_baf, 10) - parseInt(_ba7, 10);
			var _bb8 = document.getElementById(sdte);
			var _bb9 = document.getElementById(edte);
			var j = i + 1;
			var ofht;
			if (i == 0) {
				ofht = 4 + 20;
			} else {
				ofht = ofht + 2 + _b92;
			}
			var is = parseInt(_ba7, 10);
			var es = parseInt(_baf, 10);
			var _bbe = parseInt((parseInt(_ba7, 10) + parseInt(lgap, 10), 10) / 7);
			var _bbf = parseInt((parseInt(_baf, 10) + parseInt(lgap, 10), 10) / 7);
			var _bc0;
			var _bc1;
			var _bc2;
			var len = _b9a.length;
			var end = 15;
			var _bc5 = 1;
			var _bc6 = _b9a;
			var _bc7 = _b9a;
			if (len > end) {
				_bc6 = _b9a.substr(0, end) + " ...";
			}
			if (len > _bc5) {
				_bc7 = _b9a.substr(0, _bc5) + " ...";
			}
			if (_b9f == "red" || _b9f == "darkgreen" || _b9f == "navyblue" || _b9f == "darkblue" || _b9f == "pink" || _b9f == "olivegreen") {
				_bc0 = "<span class=\"barWhite\">&nbsp;&nbsp;" + _bc6 + "&nbsp;</span>";
				_bc1 = "<span class=\"barWhite\">" + _bc7 + "</span>";
				_bc2 = "<span class=\"barWhite\">&nbsp;</span>";
			} else {
				_bc0 = "<span class=\"barBlack\">&nbsp;&nbsp;" + _bc6 + "&nbsp;</span>";
				_bc1 = "<span class=\"barBlack\">" + _bc7 + "</span>";
				_bc2 = "<span class=\"barBlack\">&nbsp;</span>";
			}
			if (_ba9 == _bb1) {
				if (_ba8 == _bb0) {
					var _bc8 = 1;
					for (k = is; k <= es; k++) {
						var z = k;
						if (k <= 9) {
							z = "0" + z;
						}
						var zdiv = z + "/" + _ba8 + "/" + _ba9;
						var el = document.getElementById(zdiv);
						var r = getCalPos(el);
						var _bcd = document.createElement("div");
						_bcd.setAttribute("id", "bardivid");
						_bcd.setAttribute("name", "bardivname");
						var pcid = document.getElementById("eventcalendar");
						pcid.appendChild(_bcd);
						var _bcf = (parseInt(k, 10) + parseInt(lgap, 10)) % 7;
						_bcd.title = "cssbody=[setbdywowidth] cssheader=[setheaderwowidth] header=[" + _b9a + "] body=[ <b>" + _b8e + "</b> : " + _bad + "<br> <b>" + _b8f + "</b> : " + _bb5 + "<br> <b>" + _b90 + "</b> : " + _b9b + "] fade=[on] fadespeed=[0.04]";
						_bcd.style.position = "absolute";
						_bcd.style.left = r.offsetLeft + "px";
						_bcd.style.height = _b91;
						_bcd.style.cursor = "pointer";
						divOnClick(_bcd, pid, _ba5, _b9b, mid, _ba2, _ba3, encodeURIComponent(_ba4));
						_bcd.style.top = r.offsetTop + ofht + "px";
						_bcd.style.display = "block";
						_bcd.style.backgroundColor = _b9e;
						if (is == es) {
							_bcd.style.backgroundImage = "url(\"/images/bar/" + _b9f + "-left.gif\")";
							_bcd.style.backgroundRepeat = "no-repeat";
							_bcd.style.textAlign = "right";
							var _bd0 = document.createElement("img");
							_bd0.src = "/images/bar/" + _b9f + "-right.gif";
							_bd0.border = "0";
							_bcd.appendChild(_bd0);
							_bcd.style.width = el.offsetWidth + "px";
						} else {
							if (_bcf == 0) {
								_bcd.style.width = el.offsetWidth + "px";
							} else {
								if (k == es) {
									_bcd.style.width = el.offsetWidth + "px";
								} else {
									_bcd.style.width = (el.offsetWidth + 6) + "px";
								}
							}
							if (_bc8 == 1 && _ba8 == _bab && _ba9 == _bac) {
								_bcd.style.backgroundImage = "url(\"/images/bar/" + _b9f + "-left.gif\")";
								_bcd.style.backgroundRepeat = "no-repeat";
								_bcd.innerHTML = _bc0;
							}
							if (k == es && _bb0 == _bb3 && _bb1 == _bb4) {
								_bcd.style.textAlign = "right";
								var _bd0 = document.createElement("img");
								_bd0.src = "/images/bar/" + _b9f + "-right.gif";
								_bd0.border = "0";
								_bcd.appendChild(_bd0);
							}
							if ((_bcf == 1 && k != es) || _bc8 == 1) {
								_bcd.innerHTML = _bc0;
							} else {
								if (k != es && _bc8 != 1) {
									_bcd.innerHTML = _bc2;
								}
							}
							_bc8++;
						}
					}
				} else {
					var _bd1 = 1;
					for (k = is; k <= dom; k++) {
						var z = k;
						if (k <= 9) {
							z = "0" + z;
						}
						var zdiv = z + "/" + _ba8 + "/" + _ba9;
						var el = document.getElementById(zdiv);
						if (el) {
							var r = getCalPos(el);
							var _bcd = document.createElement("div");
							_bcd.setAttribute("id", "bardivid");
							_bcd.setAttribute("name", "bardivname");
							var pcid = document.getElementById("eventcalendar");
							pcid.appendChild(_bcd);
							var _bcf = (parseInt(k, 10) + parseInt(lgap, 10)) % 7;
							_bcd.title = "cssbody=[setbdywowidth] cssheader=[setheaderwowidth] header=[" + _b9a + "] body=[ <b>" + _b8e + "</b> : " + _bad + "<br> <b>" + _b8f + "</b> : " + _bb5 + "<br> <b>" + _b90 + "</b> : " + _b9b + "] fade=[on] fadespeed=[0.04]";
							_bcd.style.position = "absolute";
							_bcd.style.left = r.offsetLeft + "px";
							_bcd.style.height = _b91;
							_bcd.style.top = r.offsetTop + ofht + "px";
							_bcd.style.cursor = "pointer";
							divOnClick(_bcd, pid, _ba5, _b9b, mid, _ba2, _ba3, encodeURIComponent(_ba4));
							if (_bcf == 0) {
								_bcd.style.width = el.offsetWidth + "px";
							} else {
								if (k == dom) {
									_bcd.style.width = el.offsetWidth + "px";
								} else {
									_bcd.style.width = (el.offsetWidth + 6) + "px";
								}
							}
							_bcd.style.display = "block";
							_bcd.style.backgroundColor = _b9e;
							if (_bd1 == 1) {
								_bcd.style.backgroundImage = "url(\"/images/bar/" + _b9f + "-left.gif\")";
								_bcd.style.backgroundRepeat = "no-repeat";
								_bcd.innerHTML = _bc0;
							}
							if (_bcf == 1) {
								_bcd.innerHTML = _bc0;
							} else {
								if (_bd1 != 1) {
									_bcd.innerHTML = _bc2;
								}
							}
							_bd1++;
						}
					}
					var _bd2 = 1;
					for (q = 1; q <= es; q++) {
						var z = q;
						if (q <= 9) {
							z = "0" + z;
						}
						var zdiv = z + "/" + _bb0 + "/" + _ba9;
						var el = document.getElementById(zdiv);
						if (el) {
							var r = getCalPos(el);
							var _bcd = document.createElement("div");
							_bcd.setAttribute("id", "bardivid");
							_bcd.setAttribute("name", "bardivname");
							var pcid = document.getElementById("eventcalendar");
							pcid.appendChild(_bcd);
							var _bcf = (parseInt(q, 10) + parseInt(lgap, 10)) % 7;
							_bcd.title = "cssbody=[setbdywowidth] cssheader=[setheaderwowidth] header=[" + _b9a + "] body=[ <b>" + _b8e + "</b> : " + _bad + "<br> <b>" + _b8f + "</b> : " + _bb5 + "<br> <b>" + _b90 + "</b> : " + _b9b + "] fade=[on] fadespeed=[0.04]";
							_bcd.style.position = "absolute";
							_bcd.style.left = r.offsetLeft + "px";
							_bcd.style.height = _b91;
							_bcd.style.top = r.offsetTop + ofht + "px";
							_bcd.style.cursor = "pointer";
							divOnClick(_bcd, pid, _ba5, _b9b, mid, _ba2, _ba3, encodeURIComponent(_ba4));
							if (_bcf == 0) {
								_bcd.style.width = el.offsetWidth + "px";
							} else {
								if (q == es) {
									_bcd.style.width = el.offsetWidth + "px";
								} else {
									_bcd.style.width = (el.offsetWidth + 6) + "px";
								}
							}
							_bcd.style.display = "block";
							_bcd.style.backgroundColor = _b9e;
							if (q == es) {
								_bcd.style.textAlign = "right";
								var _bd0 = document.createElement("img");
								_bd0.src = "/images/bar/" + _b9f + "-right.gif";
								_bd0.border = "0";
								_bcd.appendChild(_bd0);
							}
							if ((_bcf == 1 && q != es) || _bd2 == 1) {
								_bcd.innerHTML = _bc0;
							} else {
								if (q != es) {
									_bcd.innerHTML = _bc2;
								}
							}
							_bd2++;
						}
					}
				}
			} else {
				var _bd3 = 1;
				for (k = is; k <= dom; k++) {
					var z = k;
					if (k <= 9) {
						z = "0" + z;
					}
					var zdiv = z + "/" + _ba8 + "/" + _ba9;
					var el = document.getElementById(zdiv);
					if (el) {
						var r = getCalPos(el);
						var _bcd = document.createElement("div");
						_bcd.setAttribute("id", "bardivid");
						_bcd.setAttribute("name", "bardivname");
						var pcid = document.getElementById("eventcalendar");
						pcid.appendChild(_bcd);
						var _bcf = (parseInt(k, 10) + parseInt(lgap, 10)) % 7;
						_bcd.title = "cssbody=[setbdywowidth] cssheader=[setheaderwowidth] header=[" + _b9a + "] body=[ <b>" + _b8e + "</b> : " + _bad + "<br> <b>" + _b8f + "</b> : " + _bb5 + "<br> <b>" + _b90 + "</b> : " + _b9b + "] fade=[on] fadespeed=[0.04]";
						_bcd.style.position = "absolute";
						_bcd.style.left = r.offsetLeft + "px";
						_bcd.style.height = _b91;
						_bcd.style.cursor = "pointer";
						divOnClick(_bcd, pid, _ba5, _b9b, mid, _ba2, _ba3, encodeURIComponent(_ba4));
						_bcd.style.top = r.offsetTop + ofht + "px";
						if (_bcf == 0) {
							_bcd.style.width = el.offsetWidth + "px";
						} else {
							if (k == dom) {
								_bcd.style.width = el.offsetWidth + "px";
							} else {
								_bcd.style.width = (el.offsetWidth + 6) + "px";
							}
						}
						_bcd.style.display = "block";
						_bcd.style.backgroundColor = _b9e;
						if (_bd3 == 1) {
							_bcd.style.backgroundImage = "url(\"/images/bar/" + _b9f + "-left.gif\")";
							_bcd.style.backgroundRepeat = "no-repeat";
							_bcd.innerHTML = _bc0;
						}
						if (_bcf == 1) {
							_bcd.innerHTML = _bc0;
						} else {
							if (_bd3 != 1) {
								_bcd.innerHTML = _bc2;
							}
						}
						_bd3++;
					}
				}
				var _bd3 = 1;
				for (q = 1; q <= es; q++) {
					var z = q;
					if (q <= 9) {
						z = "0" + z;
					}
					var zdiv = z + "/" + _bb0 + "/" + _bb1;
					var el = document.getElementById(zdiv);
					if (el) {
						var r = getCalPos(el);
						var _bcd = document.createElement("div");
						_bcd.setAttribute("id", "bardivid");
						_bcd.setAttribute("name", "bardivname");
						var pcid = document.getElementById("eventcalendar");
						pcid.appendChild(_bcd);
						var _bcf = (parseInt(q, 10) + parseInt(lgap, 10)) % 7;
						_bcd.title = "cssbody=[setbdywowidth] cssheader=[setheaderwowidth] header=[" + _b9a + "] body=[ <b>" + _b8e + "</b> : " + _bad + "<br> <b>" + _b8f + "</b> : " + _bb5 + "<br> <b>" + _b90 + "</b> : " + _b9b + "] fade=[on] fadespeed=[0.04]";
						_bcd.style.position = "absolute";
						_bcd.style.left = r.offsetLeft + "px";
						_bcd.style.height = _b91;
						_bcd.style.cursor = "pointer";
						divOnClick(_bcd, pid, _ba5, _b9b, mid, _ba2, _ba3, encodeURIComponent(_ba4));
						_bcd.style.top = r.offsetTop + ofht + "px";
						if (_bcf == 0) {
							_bcd.style.width = el.offsetWidth + "px";
						} else {
							if (q == es) {
								_bcd.style.width = el.offsetWidth + "px";
							} else {
								_bcd.style.width = (el.offsetWidth + 6) + "px";
							}
						}
						_bcd.style.display = "block";
						_bcd.style.backgroundColor = _b9e;
						if (q == es) {
							_bcd.style.textAlign = "right";
							var _bd0 = document.createElement("img");
							_bd0.src = "/images/bar/" + _b9f + "-right.gif";
							_bd0.border = "0";
							_bcd.appendChild(_bd0);
						}
						if (_bcf == 1 && q != es) {
							_bcd.innerHTML = _bc0;
						} else {
							if (q != es) {
								_bcd.innerHTML = _bc2;
							}
						}
						_bd3++;
					}
				}
			}
		}
	}
}
function roundNum(_bd4, X) {
	return Math.round(_bd4 * Math.pow(10, X)) / Math.pow(10, X);
}
function mathDataUpdate(_bd6, _bd7, _bd8, _bd9, _bda) {
	var _bdb = document.getElementById(_bd7);
	newResult = 0;
	if (_bd6 == "sum") {
		for (i = 0; i < _bda; i++) {
			var cel = document.getElementById("mathData_t" + _bd8 + "l" + i + "c" + _bd9);
			if (isNaN(parseFloat(cel.value))) {
				alert("\"" + cel.value + "\"" + " is not a number");
				return false;
			}
			newResult = parseFloat(newResult) + parseFloat(cel.value);
		}
	} else {
		if (_bd6 == "max") {
			for (i = 0; i < _bda; i++) {
				var cel = document.getElementById("mathData_t" + _bd8 + "l" + i + "c" + _bd9);
				if (isNaN(parseFloat(cel.value))) {
					alert("\"" + cel.value + "\"" + " is not a number");
					return false;
				}
				if (i == 0) {
					newResult = cel.value;
				}
				if (newResult < cel.value) {
					newResult = cel.value;
				}
			}
		} else {
			if (_bd6 == "min") {
				for (i = 0; i < _bda; i++) {
					var cel = document.getElementById("mathData_t" + _bd8 + "l" + i + "c" + _bd9);
					if (isNaN(parseFloat(cel.value))) {
						alert("\"" + cel.value + "\"" + " is not a number");
						return false;
					}
					if (i == 0) {
						newResult = cel.value;
					}
					if (newResult > cel.value) {
						newResult = cel.value;
					}
				}
			} else {
				alert("Sorry !!. \n\"" + _bd6 + "\" operation is not supported yet. ");
			}
		}
	}
	_bdb.innerHTML = roundNum(newResult, 2);
}
function checkValue(_bdd, _bde, type, _be0) {
	if (document.images[_bde + "required"] != null) {
		if (_bdd.value != "") {
			document.images[_bde + "required"].src = imgsrc + "clearpixel.gif";
			if (type == "NUMBER" && !isNumber(_bdd.value)) {
				document.images[_bde + "required"].src = imgsrc + "ast.gif";
			}
			if (type == "DATE" && !isDate(_bdd.value)) {
				document.images[_bde + "required"].src = imgsrc + "ast.gif";
			}
			if (type == "EMAIL" && !isEmail(_bdd.value)) {
				document.images[_bde + "required"].src = imgsrc + "ast.gif";
			}
		} else {
			if (_be0) {
				document.images[_bde + "required"].src = imgsrc + "ast.gif";
			}
		}
	}
}
function isEmail(_be1) {
	invalidChars = " /:,;";
	if (_be1 == "") {
		return false;
	}
	for (i = 0; i < invalidChars.length; i++) {
		badChar = invalidChars.charAt(i);
		if (_be1.indexOf(badChar, 0) != -1) {
			return false;
		}
	}
	atPos = _be1.indexOf("@", 1);
	if (atPos == -1) {
		return false;
	}
	if (_be1.indexOf("@", atPos + 1) != -1) {
		return false;
	}
	periodPos = _be1.indexOf(".", atPos);
	if (periodPos == -1) {
		return false;
	}
	if (periodPos + 3 > _be1.length) {
		return false;
	}
	return true;
}
function isNumber(_be2) {
	if (_be2 == "") {
		return false;
	}
	var d = parseInt(_be2);
	if (!isNaN(d)) {
		return true;
	} else {
		return false;
	}
}
function isDate(_be4) {
	if (_be4 == "") {
		return false;
	}
	var pos = _be4.indexOf("/");
	if (pos == -1) {
		return false;
	}
	var d = parseInt(_be4.substring(0, pos));
	_be4 = _be4.substring(pos + 1, 999);
	pos = _be4.indexOf("/");
	if (pos == -1) {
		return false;
	}
	var m = parseInt(_be4.substring(0, pos));
	_be4 = _be4.substring(pos + 1, 999);
	var y = parseInt(_be4);
	if (isNaN(d)) {
		return false;
	}
	if (isNaN(m)) {
		return false;
	}
	if (isNaN(y)) {
		return false;
	}
	var type = navigator.appName;
	if (type == "Netscape") {
		var lang = navigator.language;
	} else {
		var lang = navigator.userLanguage;
	}
	lang = lang.substr(0, 2);
	if (lang == "fr") {
		var date = new Date(y, m - 1, d);
	} else {
		var date = new Date(d, m - 1, y);
	}
	if (isNaN(date)) {
		return false;
	}
	return true;
}
function initMenu(menu) {
	if (getMenuCookie(menu) == "hide") {
		document.getElementById(menu).style.display = "none";
	} else {
		document.getElementById(menu).style.display = "";
	}
}
function changeMenu(menu) {
	if (document.getElementById(menu).style.display == "none") {
		document.getElementById(menu).style.display = "";
		element = document.getElementById(menu + "b");
		if (element != null) {
			document.getElementById(element).style.display = "none";
		}
		setMenuCookie(menu, "show");
	} else {
		document.getElementById(menu).style.display = "none";
		element = document.getElementById(menu + "b");
		if (element != null) {
			var _bee = document.getElementById(menu).offsetWidth;
			if (navigator.vendor == ("Netscape6") || navigator.product == ("Gecko")) {
				document.getElementById(menu + "b").style.width = _bee;
			} else {
				document.getElementById(menu + "b").width = _bee;
			}
			document.getElementById(menu + "b").style.display = "";
		}
		setMenuCookie(menu, "hide");
	}
	return false;
}
function changeDisplayState(_bef, _bf0, _bf1, _bf2) {
	var _bf3;
	if (document.getElementById(_bef).style.display == "none") {
		document.getElementById(_bef).style.display = "";
		_bf3 = true;
	} else {
		document.getElementById(_bef).style.display = "none";
		_bf3 = false;
	}
	if (_bf0) {
		document.getElementById(_bf0).src = imgsrc + (_bf3 ? _bf1: _bf2);
	}
}
function setMenuCookie(name, _bf5) {
	if (name.indexOf("treeView") != -1) {
		if (_bf5 == "show") {
			var _bf6 = getMenuCookie("treeView", "");
			if (_bf6 == "???") {
				_bf6 = "_";
			}
			_bf6 = _bf6 + name + "_";
			document.cookie = "treeView=" + escape(_bf6);
		} else {
			var _bf6 = getMenuCookie("treeView", "");
			var _bf7 = _bf6.indexOf("_" + name + "_");
			if (_bf6.length > _bf7 + name.length + 2) {
				_bf6 = _bf6.substring(0, _bf7 + 1) + _bf6.substring(_bf7 + 2 + name.length);
			} else {
				_bf6 = _bf6.substring(0, _bf7 + 1);
			}
			document.cookie = "treeView=" + escape(_bf6);
		}
		return;
	}
	if (name.indexOf("selectedTab") != -1) {
		document.cookie = "selectedTab=" + escape(_bf5) + getCookieContextPath();
	} else {
		var _bf6 = name + "STRUTSMENU=" + escape(_bf5);
		document.cookie = _bf6;
	}
}
function getCookieContextPath() {
	if (window.contextPath) {
		return "; path=" + window.contextPath;
	} else {
		return "";
	}
}
function setTabCookie(name, _bf9) {
	var _bfa = getMenuCookie("selectedTab", "");
	var _bfb;
	var end;
	if (_bfa == "undefined") {
		_bfa = "";
	}
	if (_bfa == null) {
		_bfa = "";
	}
	if (_bfa == "???") {
		_bfa = "";
	}
	_bfb = _bfa.indexOf(name + "=");
	if (_bfb == -1) {
		_bfa = _bfa + name + "=" + _bf9 + ";";
	} else {
		end = _bfa.substring(_bfb).indexOf(";");
		_bfa = _bfa.substring(0, _bfb) + name + "=" + _bf9 + _bfa.substring(_bfb + end);
	}
	setMenuCookie("selectedTab", _bfa);
}
function getMenuCookie(name, _bfe) {
	if (_bfe == null) {
		_bfe = "STRUTSMENU";
	}
	var _bff = name + _bfe + "=";
	var _c00 = document.cookie.indexOf(_bff);
	if (_c00 == -1) {
		return "???";
	}
	var _c01 = document.cookie.indexOf(";", _c00 + _bff.length);
	if (_c01 == -1) {
		_c01 = document.cookie.length;
	}
	return unescape(document.cookie.substring(_c00 + _bff.length, _c01));
}
function arrayCompare(e1, e2) {
	return e1[0] < e2[0] ? -1 : (e1[0] == e2[0] ? 0 : 1);
}
var tables = new Array();
function arraySort(_c04, _c05, _c06, _c07) {
	var _c08 = tables[_c04];
	var _c09;
	var _c0a;
	var _c0b = 0;
	if (_c08) {
		_c0a = _c08[0];
		_c09 = new Array(_c06);
		for (i = 0; i < _c06; i++) {
			_c09[i] = new Array(2);
			_c09[i][0] = _c0a[i][_c05];
			_c09[i][1] = i;
		}
		_c0b = 1 - _c08[1];
		_c08[1] = _c0b;
	} else {
		_c0a = new Array(_c06);
		_c09 = new Array(_c06);
		for (i = 0; i < _c06; i++) {
			_c0a[i] = new Array(_c07);
			for (j = 0; j < _c07; j++) {
				obj = document.getElementById("t" + _c04 + "l" + (i + 1) + "c" + j);
				_c0a[i][j] = obj.innerHTML;
			}
			_c0a[i][_c07] = obj.parentNode.parentNode.onmouseover;
			_c0a[i][_c07 + 1] = obj.parentNode.parentNode.onmouseout;
			_c09[i] = new Array(2);
			_c09[i][0] = _c0a[i][_c05];
			_c09[i][1] = i;
			_c08 = new Array(2);
			_c08[0] = _c0a;
			_c08[1] = 0;
			tables[_c04] = _c08;
		}
	}
	_c09.sort(arrayCompare);
	if (_c0b) {
		_c09.reverse();
	}
	for (i = 0; i < _c06; i++) {
		goodLine = _c09[i][1];
		for (j = 0; j < _c07; j++) {
			document.getElementById("t" + _c04 + "l" + (i + 1) + "c" + j).innerHTML = _c0a[goodLine][j];
		}
		document.getElementById("t" + _c04 + "l" + (i + 1) + "c" + 0).parentNode.parentNode.onmouseover = _c0a[goodLine][_c07];
		document.getElementById("t" + _c04 + "l" + (i + 1) + "c" + 0).parentNode.parentNode.onmouseout = _c0a[goodLine][_c07 + 1];
	}
}
var calformname;
var calformelement;
var calpattern;
var calweekstart;
function printCalendar(day1, day2, day3, day4, day5, day6, day7, _c13, _c14, _c15, _c16, _c17, _c18, _c19, _c1a, _c1b, _c1c, _c1d, _c1e, _c1f, day, _c21, year) {
	document.write("<div id=\"caltitre\" style=\"z-index:10;\">");
	document.write("<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"267\">");
	document.write("<tr><td colspan=\"15\" class=\"CALENDARBORDER\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td></tr>");
	document.write("<tr>");
	document.write("\t<td class=\"CALENDARBORDER\" width=\"1\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=20></td>");
	document.write("\t<td class=\"CALENDARTITLE\" colspan=\"3\" align=\"right\"><img src=\"" + imgsrc + "previous.gif\" onclick=\"cal_before(" + day + ");\"></td>");
	document.write("\t<td colspan=7 align=\"center\" class=\"CALENDARTITLE\" nowrap>");
	document.write("<select id=\"calmois\" name=\"calmois\" onchange=\"cal_chg(" + day + ");\"><option value=0>...</option>");
	calweekstart = _c13;
	caldays = new Array(7);
	caldays[0] = day1;
	caldays[1] = day2;
	caldays[2] = day3;
	caldays[3] = day4;
	caldays[4] = day5;
	caldays[5] = day6;
	caldays[6] = day7;
	computedcaldays = new Array(7);
	for (i = 0; i < 7; i++) {
		computedcaldays[(i + 1 - calweekstart + 7) % 7] = caldays[i];
	}
	for (i = 1; i <= 12; i++) {
		var str = "<option value=" + i + ">";
		monthIndex = i - 1;
		switch (monthIndex) {
		case 0:
			str += _c14;
			break;
		case 1:
			str += _c15;
			break;
		case 2:
			str += _c16;
			break;
		case 3:
			str += _c17;
			break;
		case 4:
			str += _c18;
			break;
		case 5:
			str += _c19;
			break;
		case 6:
			str += _c1a;
			break;
		case 7:
			str += _c1b;
			break;
		case 8:
			str += _c1c;
			break;
		case 9:
			str += _c1d;
			break;
		case 10:
			str += _c1e;
			break;
		case 11:
			str += _c1f;
			break;
		}
		document.write(str);
	}
	document.write("</select>");
	document.write("<select id=\"calyear\" name=\"calyear\" onchange=\"cal_chg(" + day + ");\">");
	document.write("</select>");
	document.write("\t</td>");
	document.write("\t<td class=\"CALENDARTITLE\" align=\"left\" colspan=\"3\"><img src=\"" + imgsrc + "next.gif\" onclick=\"cal_after(" + day + ");\">&nbsp;&nbsp;<img src=\"" + imgsrc + "close.gif\" onclick=\"hideCalendar()\"></td>");
	document.write("\t<td class=\"CALENDARBORDER\" width=1><img src=\"" + imgsrc + "shim.gif\" width=\"1\" height=\"1\"></td>");
	document.write("</tr>");
	document.write("<tr><td colspan=15 class=\"CALENDARBORDER\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td></tr>");
	document.write("<tr>");
	document.write("\t<td class=\"CALENDARBORDER\" width=\"1\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"38\">" + computedcaldays[0] + "</td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"1\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"38\">" + computedcaldays[1] + "</td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"1\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"38\">" + computedcaldays[2] + "</td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"1\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"38\">" + computedcaldays[3] + "</td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"1\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"38\">" + computedcaldays[4] + "</td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"1\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"38\">" + computedcaldays[5] + "</td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"1\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td>");
	document.write("\t<td class=\"CALENDRIER\" width=\"38\">" + computedcaldays[6] + "</td>");
	document.write("\t<td class=\"CALENDARBORDER\" width=\"1\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td>");
	document.write("</tr>");
	document.write("<tr><td colspan=15 class=\"CALENDARBORDER\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td></tr>");
	document.write("</table>");
	document.write("</div>");
	document.write("<div id=\"caljour\" style=\"z-index:10;\"></div>");
}
function showCalendar(year, _c25, day, _c27, _c28, _c29, _c2a, _c2b, _c2c) {
	if (document.forms[_c28].elements[_c29].disabled) {
		return;
	}
	if (_c2b != null) {
		var _c2d = document.getElementById("calyear");
		for (i = _c2b; i <= _c2c; i++) {
			_c2d.options[i - _c2b] = new Option(i, i);
		}
		_c2d.options.length = _c2c - _c2b + 1;
	}
	if (document.layers) {
		document.slcalcod.document.caltitre.document.forms[0].calmois.selectedIndex = _c25;
	} else {
		if (document.all) {
			document.all.calmois.selectedIndex = _c25;
		} else {
			document.getElementById("calmois").selectedIndex = _c25;
		}
	}
	if (document.forms[_c28].elements[_c29].stlayout) {
		var _c2e = document.forms[_c28].elements[_c29].stlayout.day;
		var _c2f = document.forms[_c28].elements[_c29].stlayout.month;
		var _c30 = parseInt(document.forms[_c28].elements[_c29].stlayout.year);
		cal_chg(_c2e, _c2f, _c30);
	} else {
		cal_chg(day, _c25, year);
	}
	if (document.all) {
		var _c31 = cal_place(_c2a);
		document.all.slcalcod.style.left = _c31[0];
		document.all.slcalcod.style.top = _c31[1];
		document.all.slcalcod.style.visibility = "visible";
	} else {
		if (document.layers) {
			document.slcalcod.left = e.pageX + 10;
			document.slcalcod.top = e.pageY + 10;
			document.slcalcod.visibility = "visible";
		} else {
			var _c32 = document.getElementById("slcalcod");
			var _c31 = cal_place(_c2a);
			_c32.style.left = _c31[0];
			_c32.style.top = _c31[1];
			_c32.style.visibility = "visible";
		}
	}
	if (document.all) {
		hideElement("SELECT");
	}
	calformname = _c28;
	calformelement = _c29;
	calpattern = _c27;
}
function cal_window_size() {
	var _c33 = 0,
	_c34 = 0;
	if (typeof(window.innerWidth) == "number") {
		_c33 = window.innerWidth;
		_c34 = window.innerHeight;
	} else {
		if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
			_c33 = document.documentElement.clientWidth;
			_c34 = document.documentElement.clientHeight;
		} else {
			if (document.body && (document.body.clientWidth || document.body.clientHeight)) {
				_c33 = document.body.clientWidth;
				_c34 = document.body.clientHeight;
			}
		}
	}
	return [_c33, _c34];
}
function cal_place(_c35) {
	var _c36 = document.getElementById("slcalcod");
	var ofy = document.body.scrollTop;
	var ofx = document.body.scrollLeft;
	var size = cal_window_size();
	var endX = _c36.clientWidth + _c35.clientX + ofx + 10;
	var endY = _c36.clientHeight + _c35.clientY + ofy + 10;
	var calX;
	var calY;
	if (endX > size[0]) {
		calX = _c35.clientX + ofx - 10 - _c36.clientWidth;
	} else {
		calX = _c35.clientX + ofx + 10;
	}
	if (endY > size[1]) {
		calY = _c35.clientY + ofy - 10 - _c36.clientHeight;
	} else {
		calY = _c35.clientY + ofy + 10;
	}
	return [calX, calY];
}
function cal_chg(day, _c3f, year) {
	var str = "",
	j;
	champMonth = document.getElementById("calmois");
	if (_c3f == null) {
		_c3f = champMonth.options[champMonth.selectedIndex].value;
	} else {
		champMonth.selectedIndex = _c3f;
	}
	champYear = document.getElementById("calyear");
	if (year == null) {
		year = champYear.options[champYear.selectedIndex].value;
	} else {
		index = year - champYear.options[0].value;
		if (index >= 0 && index < champYear.options.length) {
			champYear.selectedIndex = index;
		} else {
			year = champYear.options[0].value;
		}
	}
	if (_c3f > 0) {
		j = 1;
		weekEnd1Pos = (1 - calweekstart + 7) % 7;
		weekEnd2Pos = (7 - calweekstart + 7) % 7;
		str += "<table cellpadding=0 cellspacing=0 border=0 width=267>\n";
		for (u = 0; u < 6; u++) {
			str += "\t<tr>\n";
			for (i = 0; i < 7; i++) {
				ldt = new Date(year, _c3f - 1, j);
				str += "\t\t<td class=\"CALENDARBORDER\" width=1><img src=\"" + imgsrc + "shim.gif\" width=1 height=20></td>\n";
				str += "\t\t<td class=\"CALENDAR";
				if ((ldt.getDay() + 1 - calweekstart + 7) % 7 == i && ldt.getDate() == j && j == day) {
					str += "SELECTED";
				} else {
					if (i == weekEnd1Pos || i == weekEnd2Pos) {
						str += "WEEKEND";
					} else {
						str += "WEEK";
					}
				}
				str += "\" width=\"38\" align=\"center\">";
				if ((ldt.getDay() + 1 - calweekstart + 7) % 7 == i && ldt.getDate() == j) {
					str += "<a class=\"CALENDRIER\" href=\"javascript://\" class=\"CALENDRIER\" onmousedown=\"dtemaj('" + j + "','" + _c3f + "','" + year + "');\">" + j + "</a>";
					j++;
				} else {
					str += "&nbsp;";
				}
				str += "</td>\n";
			}
			str += "\t\t<td class=\"CALENDARBORDER\" width=1><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td>\n";
			str += "\t</tr>\n";
			str += "\t<tr><td colspan=15 class=\"CALENDARBORDER\"><img src=\"" + imgsrc + "shim.gif\" width=1 height=1></td></tr>\n";
		}
		str += "</table>\n";
	}
	if (document.all) {
		document.all.caljour.innerHTML = str;
	}
	if (document.layers) {
		obj = document.calendrier.document.caljour;
		obj.top = 48;
		obj.document.write(str);
		obj.document.close();
	}
	if (!document.all && document.getElementById) {
		document.getElementById("caljour").innerHTML = str;
	}
}
function cal_before(day, _c44, year) {
	var _c46, _c47;
	_c46 = document.getElementById("calmois");
	_c47 = document.getElementById("calyear");
	if (_c46.selectedIndex > 1) {
		_c46.selectedIndex--;
	} else {
		if (_c47.selectedIndex > 0) {
			_c47.selectedIndex--;
			_c46.selectedIndex = _c46.options.length - 1;
		}
	}
	cal_chg(day, _c46.options[_c46.selectedIndex].value, _c47.options[_c47.selectedIndex].value);
}
function cal_after(day, _c49, year) {
	var _c4b, _c4c;
	_c4b = document.getElementById("calmois");
	_c4c = document.getElementById("calyear");
	if (_c4b.selectedIndex < _c4b.options.length - 1) {
		_c4b.selectedIndex++;
	} else {
		if (_c4c.selectedIndex < _c4c.options.length - 1) {
			_c4c.selectedIndex++;
			_c4b.selectedIndex = 1;
		}
	}
	cal_chg(day, _c4b.options[_c4b.selectedIndex].value, _c4c.options[_c4c.selectedIndex].value);
}
function dtemaj(jour, mois, _c4f) {
	document.forms[calformname].elements[calformelement].value = formatDate(jour, mois, _c4f);
	document.forms[calformname].elements[calformelement].stlayout = new Object();
	document.forms[calformname].elements[calformelement].stlayout.day = jour;
	document.forms[calformname].elements[calformelement].stlayout.month = mois;
	document.forms[calformname].elements[calformelement].stlayout.year = _c4f;
	hideCalendar();
}
function formatDate(day, _c51, year) {
	var date = "";
	var pos = 0;
	var _c55;
	var _c56;
	var _c57 = 0;
	if (calpattern != null && calpattern.length > 0) {
		_c56 = calpattern.charAt(0);
		while (pos <= calpattern.length) {
			if (pos < calpattern.length) {
				_c55 = calpattern.charAt(pos);
			} else {
				_c55 = "";
			}
			if (_c55 != _c56) {
				switch (_c56) {
				case "y":
					date += padYear(year, _c57);
					break;
				case "M":
					date += padNumber(_c51, _c57);
					break;
				case "d":
					date += padNumber(day, _c57);
					break;
				case "'":
					break;
				default:
					date += _c56;
				}
				_c56 = _c55;
				_c57 = 0;
			}
			_c57++;
			pos++;
		}
	}
	return date;
}
function padYear(year, _c59) {
	if (_c59 == 2 && year.length == 4) {
		return year.substring(2);
	} else {
		return year;
	}
}
function padNumber(_c5a, _c5b) {
	var str = "" + _c5a;
	while (str.length < _c5b) {
		str = "0" + str;
	}
	return str;
}
function hideCalendar() {
	if (document.all) {
		document.all.slcalcod.style.visibility = "hidden";
		showElement("SELECT");
	} else {
		if (document.layers) {
			document.slcalcod.visibility = "hidden";
		} else {
			var _c5d = document.getElementById("slcalcod");
			_c5d.style.visibility = "hidden";
		}
	}
}
function hideElement(_c5e) {
	if (!document.all) {
		return;
	}
	x = parseInt(document.all.slcalcod.style.left);
	y = parseInt(document.all.slcalcod.style.top);
	var node = event.srcElement;
	while (node.tagName != "DIV") {
		node = node.parentNode;
		if (node.tagName == "HTML") {
			break;
		}
	}
	if (node.tagName == "DIV") {
		x += node.scrollLeft;
		y += node.scrollTop;
	}
	xxx = document.all.slcalcod.offsetWidth;
	yyy = document.all.slcalcod.offsetHeight;
	for (i = 0; i < document.all.tags(_c5e).length; i++) {
		obj = document.all.tags(_c5e)[i];
		if (!obj || !obj.offsetParent || obj.id == "calmois" || obj.id == "calyear") {
			continue;
		}
		objLeft = obj.offsetLeft;
		objTop = obj.offsetTop;
		objParent = obj.offsetParent;
		if (obj.style.visibility != "hidden") {
			while (objParent.tagName.toUpperCase() != "BODY") {
				objLeft += objParent.offsetLeft;
				objTop += objParent.offsetTop;
				objParent = objParent.offsetParent;
			}
		}
		obj.statusVisibility = obj.style.visibility;
		if (x > (objLeft + obj.offsetWidth) || objLeft > (x + xxx)) {} else {
			if (objTop > y + yyy) {} else {
				if (y > (objTop + obj.offsetHeight)) {} else {
					if (obj.statusVisibility != "hidden") {
						obj.style.visibility = "hidden";
					}
				}
			}
		}
	}
}
function showElement(_c60) {
	if (!document.all) {
		return;
	}
	for (i = 0; i < document.all.tags(_c60).length; i++) {
		obj = document.all.tags(_c60)[i];
		if (!obj || !obj.offsetParent) {
			continue;
		}
		if (obj.statusVisibility != "hidden") {
			obj.style.visibility = "";
		}
	}
}
function selectTab(_c61, _c62, _c63, _c64, _c65, _c66, _c67, _c68) {
	for (i = 0; i < _c62; i++) {
		element = document.getElementById("tabs" + _c61 + "head" + i);
		if (element.classNameErrorStdLayout) {
			element.className = _c66;
			element.style.color = "";
		} else {
			if (element.className == _c64) {
				element.className = _c65;
				element.style.color = "";
			} else {
				if (element.className == _c66) {}
			}
		}
		document.getElementById("tabs" + _c61 + "tab" + i).style.display = "none";
	}
	if (document.getElementById("tabs" + _c61 + "head" + _c63).className == _c66) {
		document.getElementById("tabs" + _c61 + "head" + _c63).classNameErrorStdLayout = new Object();
	}
	document.getElementById("tabs" + _c61 + "head" + _c63).className = _c64;
	document.getElementById("tabs" + _c61 + "head" + _c63).style.cursor = "default";
	document.getElementById("tabs" + _c61 + "tab" + _c63).style.display = "";
	if (_c67 != null) {
		setTabCookie(_c67, _c68);
	}
}
function onTabHeaderOver(_c69, _c6a, _c6b) {
	element = document.getElementById("tabs" + _c69 + "head" + _c6a);
	if (element.className == _c6b) {
		element.style.cursor = "default";
	} else {
		element.style.cursor = "hand";
	}
}
function loadTree(url, tree) {
	element = document.getElementById("treeView" + url);
	element.innerHTML = tree;
	element.style.display = "";
	element = document.getElementById("treeViewNode" + url);
	element.href = "javascript://";
	setMenuCookie("treeView" + url, "show");
}
function changeTree(tree, _c6f, _c70) {
	if (!isTreeviewLocked(tree)) {
		var _c71 = document.getElementById("treeViewImage" + tree);
		if (_c71.src.indexOf(_c6f) != -1) {
			_c71.src = _c70;
		} else {
			_c71.src = _c6f;
		}
		if (document.getElementById("treeView" + tree).innerHTML == "") {
			return true;
		} else {
			changeMenu("treeView" + tree);
			return false;
		}
	} else {
		return false;
	}
}
function changeTreeAndSubtrees(tree) {
	var _c73 = document.getElementById("treeViewImage" + tree);
	var link = _c73.parentNode;
	if (link.href.indexOf("javascript://") == -1) {
		return false;
	}
	if (_c73.src.indexOf("Close") != -1) {
		reg = new RegExp("Close", "g");
		_c73.src = _c73.src.replace(reg, "Open");
	} else {
		reg = new RegExp("Open", "g");
		_c73.src = _c73.src.replace(reg, "Close");
	}
	if (document.getElementById("treeView" + tree).innerHTML == "") {
		return true;
	} else {
		menu = "treeView" + tree;
		changeMenu(menu);
		toShow = true;
		if (document.getElementById(menu).style.display == "none") {
			toShow = false;
		}
		list = document.getElementsByTagName("td");
		for (i = 0; i < list.length; i++) {
			currentElement = list[i];
			if (currentElement.id.indexOf(menu) != -1 && currentElement.id != menu) {
				subTreeName = currentElement.id.substring(8);
				if (currentElement.style.display == "none" && toShow || currentElement.style.display == "" && !toShow) {
					_c73 = document.getElementById("treeViewImage" + subTreeName);
					link = _c73.parentNode;
					if (link.href.indexOf("javascript://") != -1) {
						if (_c73.src.indexOf("Close") != -1) {
							reg = new RegExp("Close", "g");
							_c73.src = _c73.src.replace(reg, "Open");
						} else {
							reg = new RegExp("Open", "g");
							_c73.src = _c73.src.replace(reg, "Close");
						}
						if (document.getElementById("treeView" + subTreeName).innerHTML == "") {} else {
							changeMenu("treeView" + subTreeName);
						}
					}
				}
			}
		}
		return false;
	}
}
function expandFirstLevels(_c75, _c76) {
	menuId = "treeView" + _c75;
	list = document.getElementsByTagName("td");
	for (i = 0; i < list.length; i++) {
		currentElement = list[i];
		if (currentElement.id.indexOf(menuId) != -1 && currentElement.id != menuId) {
			idSuffix = currentElement.id.substring(menuId.length);
			if (countStringOccurence(idSuffix, "*") <= _c76) {
				subTreeName = currentElement.id.substring(8);
				image = document.getElementById("treeViewImage" + subTreeName);
				link = image.parentNode;
				if (link.href.indexOf("javascript://") != -1) {
					if (image.src.indexOf("Close") != -1) {
						reg = new RegExp("Close", "g");
						image.src = image.src.replace(reg, "Open");
					} else {
						reg = new RegExp("Open", "g");
						image.src = image.src.replace(reg, "Close");
					}
					if (document.getElementById("treeView" + subTreeName).innerHTML == "") {} else {
						changeMenu("treeView" + subTreeName);
					}
				}
			}
		}
	}
}
function openAll(_c77, _c78) {
	if (!isTreeviewLocked(_c77)) {
		menuId = "treeView" + _c77;
		var _c79;
		list = document.getElementsByTagName("td");
		for (i = 0; i < list.length; i++) {
			currentElement = list[i];
			if (currentElement.id.indexOf(menuId) != -1 && currentElement.id != menuId) {
				idSuffix = currentElement.id.substring(menuId.length);
				if (countStringOccurence(idSuffix, "*") <= _c78) {
					subTreeName = currentElement.id.substring(8);
					image = document.getElementById("treeViewImage" + subTreeName);
					link = image.parentNode;
					if (link.href.indexOf("javascript://") != -1) {
						if (image.src.indexOf("Close") != -1) {
							reg = new RegExp("Close", "g");
							image.src = image.src.replace(reg, "Open");
							_c79 = true;
						} else {
							_c79 = false;
						}
						if (document.getElementById("treeView" + subTreeName).innerHTML == "") {} else {
							if (_c79) {
								changeMenu("treeView" + subTreeName);
							}
						}
					}
				}
			}
		}
	}
}
function closeAll(_c7a, _c7b) {
	if (!isTreeviewLocked(_c7a)) {
		menuId = "treeView" + _c7a;
		var _c7c;
		list = document.getElementsByTagName("td");
		for (i = 0; i < list.length; i++) {
			currentElement = list[i];
			if (currentElement.id.indexOf(menuId) != -1 && currentElement.id != menuId) {
				idSuffix = currentElement.id.substring(menuId.length);
				if (countStringOccurence(idSuffix, "*") <= _c7b) {
					subTreeName = currentElement.id.substring(8);
					image = document.getElementById("treeViewImage" + subTreeName);
					link = image.parentNode;
					if (link.href.indexOf("javascript://") != -1) {
						if (image.src.indexOf("Close") != -1) {
							_c7c = false;
						} else {
							reg = new RegExp("Open", "g");
							image.src = image.src.replace(reg, "Close");
							_c7c = true;
						}
						if (document.getElementById("treeView" + subTreeName).innerHTML == "") {} else {
							if (_c7c) {
								changeMenu("treeView" + subTreeName);
							}
						}
					}
				}
			}
		}
	}
}
function countStringOccurence(_c7d, _c7e) {
	index = _c7d.indexOf(_c7e);
	if (_c7d.indexOf(_c7e) != -1) {
		return 1 + countStringOccurence(_c7d.substring(index + _c7e.length), _c7e);
	} else {
		return 0;
	}
}
var lockedTrees = new Array();
function lockTreeview(_c7f) {
	lockedTrees[_c7f] = "locked";
	var item = document.getElementById("treeView" + _c7f);
	var _c81 = item.getElementsByTagName("a");
	var link;
	item.style.cursor = "wait";
	for (i = 0; i < _c81.length; i++) {
		link = _c81.item(i);
		link.style.cursor = "wait";
	}
}
function unlockTreeview(_c83) {
	lockedTrees[_c83] = null;
	var item = document.getElementById("treeView" + _c83);
	var _c85 = item.getElementsByTagName("a");
	var link;
	item.style.cursor = "default";
	for (i = 0; i < _c85.length; i++) {
		link = _c85.item(i);
		link.style.cursor = "default";
	}
}
function isTreeviewLocked(_c87) {
	var pos = _c87.indexOf("*");
	var name = pos == -1 ? _c87: _c87.substring(0, pos);
	var _c8a = lockedTrees[name];
	return "locked" == _c8a;
}
function openpopup(form, _c8c, _c8d, _c8e, e) {
	var xx, yy;
	xx = e.screenX;
	yy = e.screenY;
	window.open("about:blank", "popup", "directories=0, location=0, menubar=0, status=0, toolbar=0, width=" + _c8d + ", height=" + _c8e + ", top=" + yy + ", left=" + xx);
	var _c92 = form.action;
	var _c93 = form.target;
	if (_c8c == null || _c8c == "") {
		_c8c = _c92;
	}
	form.target = "popup";
	form.action = _c8c;
	form.submit();
	form.target = _c93;
	form.action = _c92;
	return false;
}
function closepopup(form, _c95, _c96) {
	var _c97 = form[_c96];
	var _c98;
	if (_c97.options) {
		_c98 = _c97.options[form[_c96].selectedIndex].value;
	} else {
		if (_c97.type == "file") {
			_c98 = _c97.value;
		} else {
			for (i = 0; i < form.elements.length; i++) {
				var _c99 = form.elements[i];
				if (_c99.name == _c96 && _c99.checked) {
					_c98 = _c99.value;
					break;
				}
			}
		}
	}
	window.opener.document.forms[0][_c95].value = _c98;
	window.close();
}
function checkFormChange(link, text) {
	var ok = true;
	for (var form = 0; form < document.forms.length; form++) {
		what = document.forms[form];
		for (var i = 0,
		j = what.elements.length; i < j; i++) {
			if (what.elements[i].type == "checkbox" || what.elements[i].type == "radio") {
				if (what.elements[i].checked != what.elements[i].defaultChecked) {
					ok = false;
					break;
				}
			} else {
				if (what.elements[i].type == "text" || what.elements[i].type == "hidden" || what.elements[i].type == "password" || what.elements[i].type == "textarea") {
					if (what.elements[i].value != what.elements[i].defaultValue) {
						ok = false;
						break;
					}
				} else {
					if (what.elements[i].type == "select-one" || what.elements[i].type == "select-multiple") {
						var _ca0 = false;
						for (var k = 0,
						l = what.elements[i].options.length; k < l; k++) {
							if (what.elements[i].options[k].defaultSelected) {
								_ca0 = true;
							}
						}
						for (var k = 0,
						l = what.elements[i].options.length; k < l; k++) {
							if (what.elements[i].options[k].selected != what.elements[i].options[k].defaultSelected && (_ca0 || k != 0)) {
								ok = false;
								break;
							}
						}
					} else {
						if (what.elements[i].type == "submit") {
							continue;
						} else {
							if (what.elements[i].type == "button") {
								continue;
							} else {
								if (what.elements[i].type == "file") {
									if (what.elements[i].value != null && what.elements[i].value != "") {
										ok = false;
										break;
									}
								} else {
									alert(what.elements[i].type);
								}
							}
						}
					}
				}
			}
		}
	}
	if (ok) {
		window.location.href = link;
		return;
	}
	if (confirm(text == null ? "Data will be lost. Continue ?": text)) {
		window.location.href = link;
		return;
	}
}
function showDetail(id, line) {
	var _ca5 = id[line];
	var _ca6 = document.getElementsByTagName("input");
	var _ca7;
	var _ca8;
	for (i in _ca5) {
		_ca7 = null;
		for (j in _ca6) {
			if (_ca6[j].type == "text" && _ca6[j].name == i) {
				_ca7 = _ca6[j];
				break;
			}
		}
		if (_ca7) {
			_ca8 = _ca5[i];
			_ca7.value = _ca8;
		}
	}
}
function clearDetail(id) {
	var _caa = id[0];
	var _cab = document.getElementsByTagName("input");
	var _cac;
	var _cad;
	for (i in _caa) {
		_cac = null;
		for (j in _cab) {
			if (_cab[j].type == "text" && _cab[j].name == i) {
				_cac = _cab[j];
				break;
			}
		}
		if (_cac) {
			_cad = _caa[i];
			_cac.value = "";
		}
	}
}
function initDependentComboHandler(_cae, _caf, _cb0, _cb1, _cb2) {
	var _cb3 = findCombo(_cae);
	var _cb4 = new Function("updateCombo('" + _cae + "', '" + _caf + "', " + _cb0 + ", '" + _cb1 + "');");
	_cb3.onchange = _cb4;
	_cb3.onchange();
	var _cb5 = findCombo(_caf);
	for (i = 0; i < _cb5.options.length; i++) {
		if (_cb5.options[i].value == _cb2) {
			_cb5.selectedIndex = i;
			break;
		}
	}
}
function findCombo(_cb6) {
	var _cb7 = document.getElementsByTagName("SELECT");
	var _cb8;
	for (i in _cb7) {
		if (_cb7[i].name == _cb6) {
			_cb8 = _cb7[i];
		}
	}
	return _cb8;
}
function updateCombo(_cb9, _cba, _cbb, _cbc) {
	var _cbd = findCombo(_cb9);
	var _cbe = findCombo(_cba);
	var _cbf = _cbd.options[_cbd.selectedIndex].value;
	var _cc0 = _cbd.options[_cbd.selectedIndex].value;
	var _cc1;
	for (i = 0; i < _cbb.length; i++) {
		if (_cbb[i].value == _cc0) {
			_cc1 = _cbb[i];
			break;
		}
	}
	while (_cbe.options.length != 0) {
		_cbe.remove(0);
	}
	if (_cc1 != null) {
		for (i = 0; i < _cc1[_cbc].length; i++) {
			var _cc2 = new Option(_cc1[_cbc][i].label, _cc1[_cbc][i].value);
			if (document.all) {
				_cbe.add(_cc2);
			} else {
				_cbe.add(_cc2, null);
			}
		}
	}
}
function getKeyCode(e) {
	var code;
	if (!e) {
		var e = window.event;
	}
	if (e.keyCode) {
		code = e.keyCode;
	} else {
		if (e.which) {
			code = e.which;
		}
	}
	return code;
}
function pagerGoto(_cc5, e, url, _cc8, max) {
	if (getKeyCode(e) != 13) {
		return;
	}
	var _cca = _cc5.value;
	var _ccb = url;
	if (isNaN(parseInt(_cca, 10))) {
		return;
	}
	if (_cca <= 0) {
		return;
	}
	if (_cca > max) {
		return;
	}
	if (url.indexOf("?") == -1) {
		_ccb += "?";
	} else {
		_ccb += "&";
	}
	_ccb += _cc8 + "=" + (_cca - 1);
	document.location = _ccb;
}
function showRootMenu(td) {
	td.id = "css_hover";
	var _ccd;
	var i;
	var _ccf = td.childNodes.length;
	for (i = 0; i < _ccf; i++) {
		_ccd = td.childNodes[i];
		if (_ccd.nodeName == "UL") {
			_ccd.style.display = "block";
		}
	}
}
function hideRootMenu(td) {
	td.id = null;
	var _cd1;
	var i;
	var _cd3 = td.childNodes.length;
	for (i = 0; i < _cd3; i++) {
		_cd1 = td.childNodes[i];
		if (_cd1.nodeName == "UL") {
			_cd1.style.display = "none";
		}
	}
}
function selectAllCollectionItems(_cd4, n, v, j) {
	var f = _cd4.form;
	var chk = (v == null ? true: v);
	for (i = 0; i < j; i++) {
		var name = n + "[" + i + "]";
		if (f[name].type == "checkbox") {
			f[name].checked = _cd4.checked;
		}
	}
}
var layerXOffset = 20;
var layerYOffset = 0;
function showLayoutLayer(ID, _cdc) {
	var _cdd = document.getElementById(ID);
	objLeft = 0;
	objTop = 0;
	objParent = _cdd.offsetParent;
	while (objParent.tagName.toUpperCase() != "BODY") {
		objLeft += objParent.offsetLeft;
		objTop += objParent.offsetTop;
		objParent = objParent.offsetParent;
	}
	var _cde = _cdc.clientY - objTop;
	var _cdf = _cdc.clientX - objLeft;
	var _ce0 = _cdf - layerXOffset;
	if (_ce0 < 0) {
		_ce0 = 0;
	}
	document.getElementById(ID).style.top = _cde + layerYOffset;
	document.getElementById(ID).style.left = _ce0;
	document.getElementById(ID).style.visibility = "visible";
}
function hideLayoutLayer(ID) {
	document.getElementById(ID).style.visibility = "hidden";
}
var MONTH_NAMES = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
var DAY_NAMES = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
function LZ(x) {
	return (x < 0 || x > 9 ? "": "0") + x;
}
function isDate(val, _ce4) {
	var date = getDateFromFormat(val, _ce4);
	if (date == 0) {
		return false;
	}
	return true;
}
function compareDates(_ce6, _ce7, _ce8, _ce9) {
	var d1 = getDateFromFormat(_ce6, _ce7);
	var d2 = getDateFromFormat(_ce8, _ce9);
	if (d1 == 0 || d2 == 0) {
		return - 1;
	} else {
		if (d1 > d2) {
			return 1;
		}
	}
	return 0;
}
function formatDate(date, _ced) {
	_ced = _ced + "";
	var _cee = "";
	var _cef = 0;
	var c = "";
	var _cf1 = "";
	var y = date.getYear() + "";
	var M = date.getMonth() + 1;
	var d = date.getDate();
	var E = date.getDay();
	var H = date.getHours();
	var m = date.getMinutes();
	var s = date.getSeconds();
	var yyyy, yy, MMM, MM, dd, hh, h, mm, ss, ampm, HH, H, KK, K, kk, k;
	var _d08 = new Object();
	if (y.length < 4) {
		y = "" + (y - 0 + 1900);
	}
	_d08["y"] = "" + y;
	_d08["yyyy"] = y;
	_d08["yy"] = y.substring(2, 4);
	_d08["M"] = M;
	_d08["MM"] = LZ(M);
	_d08["MMM"] = MONTH_NAMES[M - 1];
	_d08["NNN"] = MONTH_NAMES[M + 11];
	_d08["d"] = d;
	_d08["dd"] = LZ(d);
	_d08["E"] = DAY_NAMES[E + 7];
	_d08["EE"] = DAY_NAMES[E];
	_d08["H"] = H;
	_d08["HH"] = LZ(H);
	if (H == 0) {
		_d08["h"] = 12;
	} else {
		if (H > 12) {
			_d08["h"] = H - 12;
		} else {
			_d08["h"] = H;
		}
	}
	_d08["hh"] = LZ(_d08["h"]);
	if (H > 11) {
		_d08["K"] = H - 12;
	} else {
		_d08["K"] = H;
	}
	_d08["k"] = H + 1;
	_d08["KK"] = LZ(_d08["K"]);
	_d08["kk"] = LZ(_d08["k"]);
	if (H > 11) {
		_d08["a"] = "PM";
	} else {
		_d08["a"] = "AM";
	}
	_d08["m"] = m;
	_d08["mm"] = LZ(m);
	_d08["s"] = s;
	_d08["ss"] = LZ(s);
	while (_cef < _ced.length) {
		c = _ced.charAt(_cef);
		_cf1 = "";
		while ((_ced.charAt(_cef) == c) && (_cef < _ced.length)) {
			_cf1 += _ced.charAt(_cef++);
		}
		if (_d08[_cf1] != null) {
			_cee = _cee + _d08[_cf1];
		} else {
			_cee = _cee + _cf1;
		}
	}
	return _cee;
}
function _isInteger(val) {
	var _d0a = "1234567890";
	for (var i = 0; i < val.length; i++) {
		if (_d0a.indexOf(val.charAt(i)) == -1) {
			return false;
		}
	}
	return true;
}
function _getInt(str, i, _d0e, _d0f) {
	for (var x = _d0f; x >= _d0e; x--) {
		var _d11 = str.substring(i, i + x);
		if (_d11.length < _d0e) {
			return null;
		}
		if (_isInteger(_d11)) {
			return _d11;
		}
	}
	return null;
}
function getDateFromFormat(val, _d13) {
	val = val + "";
	_d13 = _d13 + "";
	var _d14 = 0;
	var _d15 = 0;
	var c = "";
	var _d17 = "";
	var _d18 = "";
	var x, y;
	var now = new Date();
	var year = now.getYear();
	var _d1d = now.getMonth() + 1;
	var date = 1;
	var hh = now.getHours();
	var mm = now.getMinutes();
	var ss = now.getSeconds();
	var ampm = "";
	while (_d15 < _d13.length) {
		c = _d13.charAt(_d15);
		_d17 = "";
		while ((_d13.charAt(_d15) == c) && (_d15 < _d13.length)) {
			_d17 += _d13.charAt(_d15++);
		}
		if (_d17 == "yyyy" || _d17 == "yy" || _d17 == "y") {
			if (_d17 == "yyyy") {
				x = 4;
				y = 4;
			}
			if (_d17 == "yy") {
				x = 2;
				y = 2;
			}
			if (_d17 == "y") {
				x = 2;
				y = 4;
			}
			year = _getInt(val, _d14, x, y);
			if (year == null) {
				return 0;
			}
			_d14 += year.length;
			if (year.length == 2) {
				if (year > 70) {
					year = 1900 + (year - 0);
				} else {
					year = 2000 + (year - 0);
				}
			}
		} else {
			if (_d17 == "MMM" || _d17 == "NNN") {
				_d1d = 0;
				for (var i = 0; i < MONTH_NAMES.length; i++) {
					var _d24 = MONTH_NAMES[i];
					if (val.substring(_d14, _d14 + _d24.length).toLowerCase() == _d24.toLowerCase()) {
						if (_d17 == "MMM" || (_d17 == "NNN" && i > 11)) {
							_d1d = i + 1;
							if (_d1d > 12) {
								_d1d -= 12;
							}
							_d14 += _d24.length;
							break;
						}
					}
				}
				if ((_d1d < 1) || (_d1d > 12)) {
					return 0;
				}
			} else {
				if (_d17 == "EE" || _d17 == "E") {
					for (var i = 0; i < DAY_NAMES.length; i++) {
						var _d25 = DAY_NAMES[i];
						if (val.substring(_d14, _d14 + _d25.length).toLowerCase() == _d25.toLowerCase()) {
							_d14 += _d25.length;
							break;
						}
					}
				} else {
					if (_d17 == "MM" || _d17 == "M") {
						_d1d = _getInt(val, _d14, _d17.length, 2);
						if (_d1d == null || (_d1d < 1) || (_d1d > 12)) {
							return 0;
						}
						_d14 += _d1d.length;
					} else {
						if (_d17 == "dd" || _d17 == "d") {
							date = _getInt(val, _d14, _d17.length, 2);
							if (date == null || (date < 1) || (date > 31)) {
								return 0;
							}
							_d14 += date.length;
						} else {
							if (_d17 == "hh" || _d17 == "h") {
								hh = _getInt(val, _d14, _d17.length, 2);
								if (hh == null || (hh < 1) || (hh > 12)) {
									return 0;
								}
								_d14 += hh.length;
							} else {
								if (_d17 == "HH" || _d17 == "H") {
									hh = _getInt(val, _d14, _d17.length, 2);
									if (hh == null || (hh < 0) || (hh > 23)) {
										return 0;
									}
									_d14 += hh.length;
								} else {
									if (_d17 == "KK" || _d17 == "K") {
										hh = _getInt(val, _d14, _d17.length, 2);
										if (hh == null || (hh < 0) || (hh > 11)) {
											return 0;
										}
										_d14 += hh.length;
									} else {
										if (_d17 == "kk" || _d17 == "k") {
											hh = _getInt(val, _d14, _d17.length, 2);
											if (hh == null || (hh < 1) || (hh > 24)) {
												return 0;
											}
											_d14 += hh.length;
											hh--;
										} else {
											if (_d17 == "mm" || _d17 == "m") {
												mm = _getInt(val, _d14, _d17.length, 2);
												if (mm == null || (mm < 0) || (mm > 59)) {
													return 0;
												}
												_d14 += mm.length;
											} else {
												if (_d17 == "ss" || _d17 == "s") {
													ss = _getInt(val, _d14, _d17.length, 2);
													if (ss == null || (ss < 0) || (ss > 59)) {
														return 0;
													}
													_d14 += ss.length;
												} else {
													if (_d17 == "a") {
														if (val.substring(_d14, _d14 + 2).toLowerCase() == "am") {
															ampm = "AM";
														} else {
															if (val.substring(_d14, _d14 + 2).toLowerCase() == "pm") {
																ampm = "PM";
															} else {
																return 0;
															}
														}
														_d14 += 2;
													} else {
														if (val.substring(_d14, _d14 + _d17.length) != _d17) {
															return 0;
														} else {
															_d14 += _d17.length;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	if (_d14 != val.length) {
		return 0;
	}
	if (_d1d == 2) {
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
			if (date > 29) {
				return 0;
			}
		} else {
			if (date > 28) {
				return 0;
			}
		}
	}
	if ((_d1d == 4) || (_d1d == 6) || (_d1d == 9) || (_d1d == 11)) {
		if (date > 30) {
			return 0;
		}
	}
	if (hh < 12 && ampm == "PM") {
		hh = hh - 0 + 12;
	} else {
		if (hh > 11 && ampm == "AM") {
			hh -= 12;
		}
	}
	var _d26 = new Date(year, _d1d - 1, date, hh, mm, ss);
	return _d26.getTime();
}
function parseDate(val) {
	var _d28 = (arguments.length == 2) ? arguments[1] : false;
	generalFormats = new Array("y-M-d", "MMM d,  y", "MMM d, y", "y-MMM-d", "d-MMM-y", "MMM d");
	monthFirst = new Array("M/d/y", "M-d-y", "M.d.y", "MMM-d", "M/d", "M-d");
	dateFirst = new Array("d/M/y", "d-M-y", "d.M.y", "d-MMM", "d/M", "d-M");
	var _d29 = new Array("generalFormats", _d28 ? "dateFirst": "monthFirst", _d28 ? "monthFirst": "dateFirst");
	var d = null;
	for (var i = 0; i < _d29.length; i++) {
		var l = window[_d29[i]];
		for (var j = 0; j < l.length; j++) {
			d = getDateFromFormat(val, l[j]);
			if (d != 0) {
				return new Date(d);
			}
		}
	}
	return null;
}
function ZE_Init() {}
ZE_Init.init = function() {
	ZE_Init.staticVersion = "v26";
	ZE_Init.theme = "gray";
	ZE_Init.language = layUtil.browserLang;
	ZE_Init.needplaintext = false;
	ZE_Init.editorCSS = true;
	ZE_Init.inlineQuotes = true;
	ZE_Init.modeChange = undefined;
	ZE_Init.spellcheckURL = "lt.zoho.com";
	ZE_Init.domain = "zoho.com";
	ZE_Init.tabKeyHandling = false;
	ZE_Init.needEditorFocus = false;
	ZE_Init.needEditorBorder = true;
	ZE_Init.needResizeImage = false;
	ZE_Init.removeInsertOptions = false;
	ZE_Init.removeFontFamily = false;
	ZE_Init.removeFontSize = false;
	ZE_Init.defaultFontSize = "12px";
	ZE_Init.defaultFontFamily = "Verdana,arial,Helvetica,sans-serif";
	ZE_Init.outGoingFontFamily = "";
	ZE_Init.outGoingFontSize = "";
	ZE_Init.outGoingColor = "";
	var _d2e = window.location.protocol;
	ZE_Init.cssPath = ZE_Init.imgPath = _d2e + "//css.zohostatic.com/ze/" + ZE_Init.staticVersion;
	ZE_Init.jsPath = _d2e + "//js.zohostatic.com/ze/" + ZE_Init.staticVersion;
	ZE_Init.toolbarOrder = [[["bold", "Bold (Ctrl+B)", "ze_tb"], ["italic", "Italic (Ctrl+I)", "ze_ti"], ["underline", "Underline (Ctrl+U)", "ze_tu"], ["strikethrough", "Strikethrough", "ze_ts"], ["subscript", "Subscript", "ze_tsu"], ["superscript", "Superscript", "ze_sup"]], [["fontcolor", "Font Color", "ze_f"], ["bgcolor", "Background Color", "ze_tbg"], ["fontfamily", "Font", "ze_tf"], ["fontsize", "Font Size", "ze_tfo"]], [["justifyleft", "Align Text Left (Ctrl+L)", "ze_tjl"], ["justifyright", "Align Text Right (Ctrl+R)", "ze_tjr"], ["justifyfull", "Justify (Ctrl+J)", "ze_tjf"], ["justifycenter", "Center (Ctrl+E)", "ze_tjc"]], [["insertunorderedlist", "Bullets", "ze_tul"], ["insertorderedlist", "Numbering", "ze_tol"], ["outdent", "Decrease Indent", "ze_tou"], ["indent", "Increase Indent", "ze_tin"]], [["image", "Insert Image", "ze_tim"], ["smiley", "Insert smiley", "ze_tis"], ["link", "Insert Link", "ze_til"], ["unlink", "Remove Link", "ze_tuli"], ["insertoptions", "Insert Options", "ze_spo"]], [["edithtml", "Edit HTML", "ze_teh"]]];
	ZE_Init.insertOptions = [["inserthorizontalrule", "Insert Horizontal Rule", "ze_hr"], ["object", "Insert HTML", "ze_obj"], ["code", "Insert Code", "ze_icode"], ["quote", "Insert Quote", "ze_quote"]];
	ZE_Init.imgAction = "/viewinlineattachment.do";
	ZE_Init.imgParameters = "?zpid=" + ZP_ZOID;
	ZE_Init.setContentProcessed = false;
	var _d2f = document,
	agt = navigator.userAgent.toLowerCase(),
	_d31 = _d2f.createElement("img");
	ZE_Init.is_ie = (agt.indexOf("ie") !== -1);
	ZE_Init.is_safari = (agt.indexOf("safari") !== -1);
	ZE_Init.is_opera = (agt.indexOf("opera") !== -1);
	ZE_Init.is_mac = (agt.indexOf("mac") !== -1);
	ZE_Init.theme = ZE_Init.ElementInArray(["blue", "green", "gray", "brown", "lavender", "pink", "stars", "heart", "wood", "leaf"], ZE_Init.theme) || "gray";
	ZE_Init.language = ZE_Init.ElementInArray(["en", "zh", "da", "nl", "fr", "de", "hu", "it", "ja", "pl", "pt", "ru", "es", "sv", "tr", "uk"], ZE_Init.language) || "en";
	ZE_Init.loadURL(ZE_Init.cssPath + "/css/" + ZE_Init.theme + "/ze.min.css", "css");
	if (ZE_Init.is_ie) {
		ZE_Init.loadURL(ZE_Init.jsPath + "/js/i18n/" + ZE_Init.language + "/ze_ie.min.js", "js");
	} else {
		if (ZE_Init.is_safari) {
			ZE_Init.loadURL(ZE_Init.jsPath + "/js/i18n/" + ZE_Init.language + "/ze_sa.min.js", "js");
		} else {
			if (ZE_Init.is_opera) {
				ZE_Init.loadURL(ZE_Init.jsPath + "/js/i18n/" + ZE_Init.language + "/ze_op.min.js", "js");
				ZE_Init.toolbarOrder.pop();
			} else {
				ZE_Init.loadURL(ZE_Init.jsPath + "/js/i18n/" + ZE_Init.language + "/ze.min.js", "js");
			}
		}
	}
	_d31.src = ZE_Init.imgPath + "/images/ze_toolbar_" + ZE_Init.theme + ".png";
	ZE_Init.loading = true;
};
ZE_Init.loadURL = function(URL, type) {
	var css, _d35, _d36 = document;
	if (type === "css") {
		css = _d36.createElement("link");
		css.type = "text/css";
		css.rel = "stylesheet";
		css.href = URL;
		_d36.getElementsByTagName("head")[0].appendChild(css);
	} else {
		if (type === "js") {
			_d35 = _d36.createElement("script");
			_d35.type = "text/javascript";
			_d35.src = URL;
			_d36.getElementsByTagName("head")[0].appendChild(_d35);
		}
	}
};
ZE_Init.ElementInArray = function(_d37, _d38) {
	var _d39;
	while (_d39 = _d37.shift()) {
		if (_d39 === _d38) {
			return _d39;
		}
	}
};
ZE_Init.applyTheme = function(_d3a) {
	if (ZE_Init.theme === _d3a) {
		return;
	}
	if (!ZE_Init.ElementInArray(["blue", "green", "gray", "brown", "lavender", "pink", "stars", "heart", "wood", "leaf"], _d3a)) {
		return;
	}
	var _d3b = document,
	link = _d3b.getElementsByTagName("link"),
	_d3d = link.length,
	_d3e,
	_d3f,
	_d40;
	while (_d3d--) {
		_d3e = link[_d3d];
		_d3f = _d3e.href;
		if (_d3f.match("ze.min.css")) {
			ZE_Init.theme = _d3a;
			_d3e.href = ZE_Init.cssPath + "/css/" + _d3a + "/ze.min.css";
			_d40 = _d3b.createElement("img");
			_d40.src = ZE_Init.imgPath + "/images/ze_toolbar_" + _d3a + ".png";
		} else {
			if (_d3f.match("ze1.min.css")) {
				ZE_Init.theme = _d3a;
				_d3e.href = ZE_Init.cssPath + "/css/" + _d3a + "/ze1.min.css";
			}
		}
	}
};
ZE_Init.toolbarOrderMapping = {
	"bold": ["bold", "Bold (Ctrl+B)", "ze_tb"],
	"italic": ["italic", "Italic (Ctrl+I)", "ze_ti"],
	"underline": ["underline", "Underline (Ctrl+U)", "ze_tu"],
	"strikethrough": ["strikethrough", "Strikethrough", "ze_ts"],
	"subscript": ["subscript", "Subscript", "ze_tsu"],
	"superscript": ["superscript", "Superscript", "ze_sup"],
	"fontcolor": ["fontcolor", "Font Color", "ze_f"],
	"bgcolor": ["bgcolor", "Background Color", "ze_tbg"],
	"fontfamily": ["fontfamily", "Font", "ze_tf"],
	"fontsize": ["fontsize", "Font Size", "ze_tfo"],
	"justifyleft": ["justifyleft", "Align Text Left (Ctrl+L)", "ze_tjl"],
	"justifyright": ["justifyright", "Align Text Right (Ctrl+R)", "ze_tjr"],
	"justifyfull": ["justifyfull", "Justify (Ctrl+J)", "ze_tjf"],
	"justifycenter": ["justifycenter", "Center (Ctrl+E)", "ze_tjc"],
	"insertunorderedlist": ["insertunorderedlist", "Bullets", "ze_tul"],
	"insertorderedlist": ["insertorderedlist", "Numbering", "ze_tol"],
	"outdent": ["outdent", "Decrease Indent", "ze_tou"],
	"indent": ["indent", "Increase Indent", "ze_tin"],
	"removeformat": ["removeformat", "Remove Formatting", "ze_trf"],
	"image": ["image", "Insert Image", "ze_tim"],
	"smiley": ["smiley", "Insert smiley", "ze_tis"],
	"link": ["link", "Insert Link", "ze_til"],
	"unlink": ["unlink", "Remove Link", "ze_tuli"],
	"insertoptions": ["insertoptions", "Insert Options", "ze_spo"],
	"edithtml": ["edithtml", "Edit HTML", "ze_teh"],
	"spellcheck": ["spellcheck", "Check Spelling", "ze_sp"],
	"spellcheckoptions": ["spellcheckoptions", "Spell Check Languages", "ze_spo"],
	"table": ["table", "Insert Table", "ze_tbl"],
	"inserthorizontalrule": ["inserthorizontalrule", "Insert Horizontal Rule", "ze_hr"],
	"object": ["object", "Insert HTML", "ze_obj"],
	"code": ["code", "Insert Code", "ze_icode"],
	"quote": ["quote", "Insert Quote", "ze_quote"]
};
ZE_Init.toolbarOrderGenerate = function(obj) {
	var _d42 = [],
	_d43,
	_d44,
	key,
	_d46;
	for (key in obj) {
		if (obj.hasOwnProperty(key)) {
			_d44 = obj[key];
			_d43 = [];
			for (_d46 in _d44) {
				if (_d44.hasOwnProperty(_d46)) {
					_d43.push(ZE_Init.toolbarOrderMapping[_d44[_d46]]);
				}
			}
			_d42.push(_d43);
		}
	}
	return _d42;
};
ZE_Init.insertOptionsGenerate = function(_d47) {
	var _d48 = [],
	_d49 = _d47.length,
	i = 0;
	for (; i < _d49; i++) {
		_d48[i] = ZE_Init.toolbarOrderMapping[_d47[i]];
	}
	return _d48;
};
ZE_Init.init();
var tooltip = false;
var tooltipShadow = false;
var shadowSize = 4;
var tooltipMaxWidth = 200;
var tooltipMinWidth = 100;
var iframe = false;
var tooltip_is_msie = (navigator.userAgent.indexOf("MSIE") >= 0 && navigator.userAgent.indexOf("opera") == -1 && document.all) ? true: false;
var timerArray = new Array();
var timerObject = new Array();
var taskdiv_id = "";
var taskdiv_operation = "";
var taskdiv_id_bulk = "";
var taskdiv_operation_bulk = "";
var taskdiv_link_bulk = "";
var taskdiv_par_id = "";
var taskbulk;
var bulk_task_id = new Array();
var addtaskowner;
var action_id = "";
var action_type = "";
action_par_id = "";
var selTaskCheck = new Array();
var depaction_id = "";
var deptextboxid = "";
var taskid_dep = "";
var tlid_dep = "";
function showTooltip(e, _d4c) {
	var _d4d = Math.max(document.body.clientWidth, document.documentElement.clientWidth) - 20;
	if (!tooltip) {
		tooltip = document.createElement("DIV");
		tooltip.id = "tooltip";
		tooltipShadow = document.createElement("DIV");
		tooltipShadow.id = "tooltipShadow";
		document.body.appendChild(tooltip);
		document.body.appendChild(tooltipShadow);
		if (tooltip_is_msie) {
			iframe = document.createElement("IFRAME");
			iframe.frameborder = "5";
			iframe.style.backgroundColor = "#FFFFFF";
			iframe.src = "#";
			iframe.style.zIndex = 100;
			iframe.style.position = "absolute";
			document.body.appendChild(iframe);
		}
	}
	tooltip.style.display = "block";
	tooltipShadow.style.display = "block";
	if (tooltip_is_msie) {
		iframe.style.display = "block";
	}
	var st = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
	if (navigator.userAgent.toLowerCase().indexOf("safari") >= 0) {
		st = 0;
	}
	var _d4f = e.clientX + 10;
	tooltip.style.width = null;
	tooltip.innerHTML = _d4c;
	tooltip.style.left = _d4f + "px";
	tooltip.style.top = e.clientY + 10 + st + "px";
	tooltipShadow.style.left = _d4f + shadowSize + "px";
	tooltipShadow.style.top = e.clientY + 10 + st + shadowSize + "px";
	if (tooltip.offsetWidth > tooltipMaxWidth) {
		tooltip.style.width = tooltipMaxWidth + "px";
	}
	var _d50 = tooltip.offsetWidth;
	if (_d50 < tooltipMinWidth) {
		_d50 = tooltipMinWidth;
	}
	tooltip.style.width = _d50 + "px";
	tooltipShadow.style.width = tooltip.offsetWidth + "px";
	tooltipShadow.style.height = tooltip.offsetHeight + "px";
	if ((_d4f + _d50) > _d4d) {
		tooltip.style.left = (tooltipShadow.style.left.replace("px", "") - ((_d4f + _d50) - _d4d)) + "px";
		tooltipShadow.style.left = (tooltipShadow.style.left.replace("px", "") - ((_d4f + _d50) - _d4d) + shadowSize) + "px";
	}
	if (tooltip_is_msie) {
		iframe.style.left = tooltip.style.left;
		iframe.style.top = tooltip.style.top;
		iframe.style.width = tooltip.offsetWidth + "px";
		iframe.style.height = tooltip.offsetHeight + "px";
	}
}
function hideTooltip() {
	tooltip.style.display = "none";
	tooltipShadow.style.display = "none";
	if (tooltip_is_msie) {
		iframe.style.display = "none";
	}
}
function showDocDiv() {
	var _d51 = document.getElementById("docDiv").style.display;
	if (_d51 == "none") {
		document.getElementById("docDiv").style.display = "block";
	}
	if (_d51 == "block") {
		document.getElementById("docDiv").style.display = "none";
	}
}
function actionsDiv(_d52) {
	var _d53 = document.getElementById("actions_" + _d52).style.display;
	if (_d53 == "none") {
		document.getElementById("actions_" + _d52).style.display = "block";
	}
	if (_d53 == "block") {
		document.getElementById("actions_" + _d52).style.display = "none";
	}
}
function selectOrDeselectAll() {
	var _d54 = document.getElementsByName("doccheckbox");
	if (!document.getElementById("selectAll").checked) {
		for (i = 0; i < _d54.length; i++) {
			_d54[i].checked = false;
		}
	} else {
		for (i = 0; i < _d54.length; i++) {
			_d54[i].checked = true;
		}
	}
}
function selectedAllOrNot() {
	var _d55 = document.getElementsByName("doccheckbox");
	var j = 0;
	for (i = 0; i < _d55.length; i++) {
		if (_d55[i].checked) {
			j++;
		}
	}
	if (j == _d55.length) {
		document.getElementById("selectAll").checked = true;
	} else {
		document.getElementById("selectAll").checked = false;
	}
}
function checkedOrNot(url, mesg, _d59) {
	var _d5a = document.getElementsByName("doccheckbox");
	var j = 0;
	for (i = 0; i < _d5a.length; i++) {
		if (_d5a[i].checked) {
			url = url + "&fileid=" + _d5a[i].value;
			j++;
		}
	}
	if (j > 0) {
		if (askOnDelete(mesg)) {
			ajaxShowPage(url, _d59);
		}
	} else {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.nofilesselected", null,
		function(mesg) {
			alert(mesg);
		});
	}
}
function showLoadingDiv() {
	var sts = document.getElementById("loadingdiv");
	var doc = findDocDim();
	var _d5f = sts.offsetWidth;
	var _d60 = sts.offsetHeight;
	var left = (doc.width / 2) + document.body.scrollLeft - 50;
	var top = (doc.height / 2) + (document.body.scrollTop / 2) - (_d60 / 2);
	sts.style.left = parseInt(left) + "px";
	sts.style.top = parseInt(top) + "px";
	sts.style.display = "block";
}
function validateTSExportForm(_d63) {
	var _d64 = document.getElementById("timesheet_export_status");
	var _d65 = eval("document." + _d63 + ".startdate.value");
	var _d66 = eval("document." + _d63 + ".enddate.value");
	Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
	var _d67 = compareDates(_d65, Utils.dateformat, _d66, Utils.dateformat);
	if (trim(_d65).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.stdateemp", null,
		function(mesg) {
			_d64.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return false;
	} else {
		if (trim(_d66).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.enddateemp", null,
			function(mesg) {
				_d64.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return false;
		} else {
			if (_d65 != "" && _d66 != "" && _d67 != 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
				function(mesg) {
					_d64.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _d63 + ".etsenddate.value=\"\"");
				return false;
			} else {
				return true;
			}
		}
	}
}
function SwapRowClass(id1, id2, id3, id4) {
	document.getElementById(id1).className = "hide";
	document.getElementById(id2).className = "hide";
	document.getElementById(id3).className = "hide";
	document.getElementById(id4).className = "";
}
function setAsVisible(id) {
	document.getElementById(id).style.visibility = "visible";
}
function setAsHidden(id) {
	document.getElementById(id).style.visibility = "hidden";
}
function showDropDownMenu(_d71, _d72) {
	var ev = (is_ie) ? window.event: _d71;
	var _d74 = (is_ie) ? ev.srcElement: ev.target;
	var r = getPos(_d74);
	var x = r.offsetLeft;
	var y = r.offsetTop + _d74.offsetHeight;
	id = document.getElementById(_d72);
	id.style.left = x + "px";
	id.style.top = y + "px";
	id.style.display = "block";
}
function showDocActionMenu(_d78, _d79) {
	var ev = (is_ie) ? window.event: _d78;
	var _d7b = (is_ie) ? ev.srcElement: ev.target;
	var r = getPos(_d7b);
	var x = r.offsetLeft - 20;
	var y = r.offsetTop - _d7b.offsetHeight - 110;
	id = document.getElementById(_d79);
	id.style.left = x + "px";
	id.style.top = y + "px";
	id.style.display = "block";
}
function changeHomeTabStyle(elem) {
	var tcid = document.getElementById(elem + "tab");
	document.getElementById("hometab").className = "tabUnselected";
	if (document.getElementById("workcaltab")) {
		document.getElementById("workcaltab").className = "tabUnselected";
	}
	if (document.getElementById("logcaltab")) {
		document.getElementById("logcaltab").className = "tabUnselected";
	}
	if (document.getElementById("progresstab")) {
		document.getElementById("progresstab").className = "tabUnselected";
	}
	if (document.getElementById("searchtab")) {
		document.getElementById("searchtab").className = "tabUnselected";
	}
	tcid.className = "tabSelected";
}
function highLightTab(elem) {
	if (elem.className == "tabUnSelected") {
		elem.className = "tabUnSelectedHover";
	}
}
function dehighLightTab(elem) {
	if (elem.className == "tabUnSelectedHover") {
		elem.className = "tabUnSelected";
	}
}
function ShowInPopUpInCenter(id) {
	var _d84 = 0;
	var _d85 = 0;
	var sts = document.getElementById(id);
	if (document.all) {
		_d84 = document.body.offsetWidth + document.body.scrollLeft;
		_d85 = document.body.offsetHeight + document.body.scrollTop;
	} else {
		_d84 = window.innerWidth + document.body.scrollLeft;
		_d85 = window.innerHeight + document.body.scrollTop;
	}
	var _d87 = sts.offsetWidth;
	var _d88 = sts.offsetHeight;
	var left = (_d84 / 2) + document.body.scrollLeft - (_d87 / 2);
	var top = (_d85 / 2) + (document.body.scrollTop / 2) - (_d88 / 2);
	sts.style.left = parseInt(left) + "px";
	sts.style.top = parseInt(top) + "px";
	sts.style.display = "block";
}
function validateForumComment(stat) {
	if (stat == "add") {
		var _d8c = document.getElementById("fcomment_add_status");
		if (trim(document.getElementById("commentbody").value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.entercomment", null,
			function(mesg) {
				_d8c.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			document.commentForm.commbody.focus();
			return false;
		} else {
			if (findScriptTags(trim(document.getElementById("commentbody").value))) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.validcontent", null,
				function(mesg) {
					_d8c.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				document.commentForm.commbody.focus();
				return false;
			} else {
				ShowGenInline("zoho_add_comment_busy");
				return true;
			}
		}
	}
}
function resizeFrame(f) {
	var _d90 = f.contentWindow.document.body.scrollHeight + 10;
	f.style.height = _d90 + "px";
}
function ChangeClass(Id1, _d92) {
	var id = document.getElementById(Id1);
	id.className = _d92;
}
function hideElements(_d94) {
	if (is_ie) {
		showHideElements_iefix("div", _d94, "hide");
	} else {
		var els = document.getElementsByName(_d94);
		for (var i = 0,
		l = els.length; i < l; i++) {
			els[i].className = "hide";
		}
	}
}
function showElements(_d98) {
	if (is_ie) {
		showHideElements_iefix("div", _d98, "block");
	} else {
		var els = document.getElementsByName(_d98);
		for (var i = 0,
		l = els.length; i < l; i++) {
			els[i].className = "block";
		}
	}
}
function showHideElements_iefix(tag, name, _d9e) {
	var elem = document.getElementsByTagName(tag);
	for (i = 0; i < elem.length; i++) {
		att = elem[i].getAttribute("name");
		if (att == name) {
			elem[i].className = _d9e;
		}
	}
}
function expAllChange() {
	var _da0 = document.getElementById("exptaskusername");
	var i;
	var _da2 = 0;
	for (i = 0; i < _da0.options.length; i++) {
		if (_da0.options[i].selected) {
			if (_da0.options[i].value == "all") {
				Hide("expfmtid");
				ShowHideBlock("exportall", "exportbyuser");
			} else {
				ShowGenBlock("expfmtid");
				ShowHideBlock("exportbyuser", "exportall");
			}
		}
	}
}
function ShowGeneralSettings(_da3) {
	Hide("disp_skin_settings");
	changeElementClass("skinlink", "tslink");
	Hide("disp_passwd_settings");
	changeElementClass("passwdlink", "tslink");
	Hide("disp_photo_settings");
	changeElementClass("photolink", "tslink");
	Hide("disp_profile_settings");
	changeElementClass("profilelink", "tslink");
	Hide("disp_locale_settings");
	changeElementClass("localelink", "tslink");
	Hide("disp_layout_settings");
	changeElementClass("layoutlink", "tslink");
	Hide("disp_schedule_settings");
	changeElementClass("schedulelink", "tslink");
	ShowGenBlock("disp_" + _da3 + "_settings");
	changeElementClass("unassignedlink", "tslink");
	changeElementClass(_da3 + "link", "forumCatSel");
	if ("schedule" == _da3) {
		callCal("startfrom", "remtrigger");
	}
}
function ShowCompanySettings(_da4) {
	Hide("comp_prof_settings");
	changeElementClass("proflink", "tslink");
	Hide("comp_dformat_settings");
	changeElementClass("dformatlink", "tslink");
	Hide("comp_logo_settings");
	changeElementClass("logolink", "tslink");
	Hide("comp_plogo_settings");
	changeElementClass("plogolink", "tslink");
	Hide("comp_portal_settings");
	changeElementClass("portallink", "tslink");
	Hide("comp_owner_settings");
	changeElementClass("ownerlink", "tslink");
	Hide("comp_databkp_settings");
	changeElementClass("databkplink", "tslink");
	Hide("comp_zsckey_settings");
	changeElementClass("zsckeylink", "tslink");
	ShowGenBlock("comp_" + _da4 + "_settings");
	changeElementClass(_da4 + "link", "forumCatSel");
}
function hideAllDiv(_da5) {
	if ("generaldisp" != _da5) {
		var _da6 = document.getElementById("generaldisp");
		_da6.className = "msTitleGeneral";
		if (_da5 == "overdue_todos") {
			setShowHideMile(_da5);
			if (document.getElementById("upcoming_todos")) {
				Hide("upcoming_todos");
				ShowHideInline("upcoming_todos_plus", "upcoming_todos_minus");
			}
			if (document.getElementById("archms")) {
				Hide("archms");
				ShowHideInline("archms_plus", "archms_minus");
			}
			if (document.getElementById("completedms")) {
				Hide("completedms");
				ShowHideInline("completedms_plus", "completedms_minus");
			}
		}
		if (_da5 == "upcoming_todos") {
			setShowHideMile(_da5);
			if (document.getElementById("overdue_todos")) {
				Hide("overdue_todos");
				ShowHideInline("overdue_todos_plus", "overdue_todos_minus");
			}
			if (document.getElementById("archms")) {
				Hide("archms");
				ShowHideInline("archms_plus", "archms_minus");
			}
			if (document.getElementById("completedms")) {
				Hide("completedms");
				ShowHideInline("completedms_plus", "completedms_minus");
			}
		}
		if (_da5 == "archms") {
			setShowHideMile(_da5);
			if (document.getElementById("completedms")) {
				Hide("completedms");
				ShowHideInline("completedms_plus", "completedms_minus");
			}
		}
		if (_da5 == "completedms") {
			setShowHideMile(_da5);
			if (document.getElementById("archms")) {
				Hide("archms");
				ShowHideInline("archms_plus", "archms_minus");
			}
		}
	} else {
		if ("generaldisp" == _da5) {
			var _da6 = document.getElementById("generaldisp");
			_da6.className = "msTitleSelGeneral";
			if (document.getElementById("overdue_todos")) {
				Hide("overdue_todos");
			}
			if (document.getElementById("upcoming_todos")) {
				Hide("upcoming_todos");
			}
			if (document.getElementById("archms")) {
				Hide("archms");
			}
			if (document.getElementById("completedms")) {
				Hide("completedms");
			}
		}
	}
}
function changeClassName(_da7, _da8, _da9, _daa) {
	if (is_ie) {
		showHideElements_iefix("a", _da7, _daa);
	} else {
		var els = document.getElementsByName(_da7);
		for (var i = 0,
		l = els.length; i < l; i++) {
			els[i].className = _daa;
		}
	}
	var _dae = document.getElementById(_da8);
	_dae.className = _da9;
}
function appendSelectedClass(_daf) {
	var _db0 = document.getElementsByName("calendarcell");
	for (var i = 0; i < _db0.length; i++) {
		_db0[i].className = _db0[i].className.replace(" selectedtd", "");
	}
	_daf.className = _daf.className + " selectedtd";
}
function ShowCalRightMenu(_db2) {
	Hide("selectedevents");
	Hide("addtodotask");
	Hide("addmilestone");
	Hide("addmeeting");
	Hide("caladdbug");
	ShowGenBlock(_db2);
}
function eventNotOnIcons(id, ev) {
	var el = is_ie ? ev.srcElement: ev.target;
	if (el.id != "tdicon_" + id && el.id != "msicon_" + id && el.id != "meicon_" + id && el.id != "nooftasks" && el.id != "noofmilestones" && el.id != "noofmeetings" && el.id != "noofissues" && el.id != "idicon_" + id && el.id.indexOf("onissue_") < 0) {
		return true;
	} else {
		return false;
	}
}
function validateNewUserForm(url) {
	var doc = document;
	var _db8 = doc.getElementById("newuser_status");
	var _db9 = /^[a-z0-9]([a-z0-9_\-\.\+]*)@([a-z0-9_\-\.]*)(\.[a-z]{2,4}(\.[a-z]{2}){0,2})$/i;
	var _dba = doc.getElementById("newuseremail");
	if (trim(_dba.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyemailid", null,
		function(mesg) {
			_db8.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		_dba.focus();
		return false;
	} else {
		if (!_db9.test(_dba.value)) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.valemailid", null,
			function(mesg) {
				_db8.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			_dba.focus();
			return false;
		}
	}
	url = url + "&email=" + encodeURIComponent(trim(_dba.value)) + "&urole=" + encodeURIComponent(doc.getElementById("newurole").value);
	ajaxConstructPage(url, "userdetails");
	return true;
}
function validateNewClientForm(url) {
	var _dbe = document.getElementById("newclient_add_status");
	if (trim(document.getElementById("newclientname").value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifycliname", null,
		function(mesg) {
			_dbe.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return false;
	} else {
		if (findScriptTags(trim(document.getElementById("newclientname").value))) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.specifyvalcliname", null,
			function(mesg) {
				_dbe.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return false;
		} else {
			url = url + "&clientname=" + document.getElementById("newclientname").value;
			ajaxShowPage(url, "clientdetails");
			return true;
		}
	}
}
function setThemeValues(_dc1, _dc2, _dc3, _dc4, _dc5) {
	document.getElementById("samplemenubg").style.backgroundColor = "#" + _dc1;
	document.getElementById("samplecurrenttab").style.backgroundColor = "#" + _dc2;
	document.getElementById("samplecurrenttab").style.color = "#" + _dc3;
	document.getElementById("sampleothertab").style.backgroundColor = "#" + _dc4;
	document.getElementById("sampleothertab").style.color = "#" + _dc5;
	document.themeUpdateForm.menubgcolor.value = _dc1;
	document.getElementById("menubgcolordiv").style.backgroundColor = "#" + _dc1;
	document.themeUpdateForm.tabselfontcolor.value = _dc3;
	document.getElementById("tabselfontcolordiv").style.backgroundColor = "#" + _dc3;
	document.themeUpdateForm.tabunselbgcolor.value = _dc4;
	document.getElementById("tabunselbgcolordiv").style.backgroundColor = "#" + _dc4;
	document.themeUpdateForm.tabunselfontcolor.value = _dc5;
	document.getElementById("tabunselfontcolordiv").style.backgroundColor = "#" + _dc5;
	document.getElementById("testcolour").innerHTML = "";
	enableButton("zoho_upd_theme_submit");
}
function openColorPicker(_dc6, _dc7, _dc8, type) {
	document.getElementById(_dc6).innerHTML = "";
	var _dca = new Array(_dc7, "value", _dc7 + "div", "bgcolor", _dc8, type);
	var rgb = document.getElementById(_dc7 + "div").style.backgroundColor;
	if (rgb.indexOf("#") > -1) {
		ColourPicker(document.getElementById(_dc6), "/images/colourpicker/", new RGBColour(parseInt(rgb.substring(1, 3), 16), parseInt(rgb.substring(3, 5), 16), parseInt(rgb.substring(5, 7), 16)), _dca);
	} else {
		var _dcc = rgb.substring(rgb.indexOf("(") + 1, rgb.indexOf(")"));
		var _dcd = _dcc.split(",");
		ColourPicker(document.getElementById(_dc6), "/images/colourpicker/", new RGBColour(_dcd[0], _dcd[1], _dcd[2]), _dca);
	}
	document.getElementById("customradio").checked = true;
	enableButton("zoho_upd_theme_submit");
}
function projectSearch(_dce, _dcf, _dd0) {
	var st = document.sform1.st.value;
	st = st.replace(/(^\s*)|(\s*$)/gi, "");
	var _dd2 = document.sform1.scope.value;
	if (st == "") {
		i18n.getJSAlertValue(Utils.zuid, "zp.search.empsearch", null,
		function(mesg) {
			alert(mesg);
		});
		Hide("ajax_load_tab");
		return false;
	}
	if ((new String(st)).length == 1) {
		i18n.getJSAlertValue(Utils.zuid, "zp.search.plzenterwords", null,
		function(mesg) {
			alert(mesg);
		});
		Hide("ajax_load_tab");
		return false;
	}
	if (document.getElementById("searchStr")) {
		document.getElementById("searchStr").value = document.sform1.st.value;
	}
	ajaxShowPage(Utils.contPath + "/projectsearch.do?scope=" + _dd2 + "&st=" + encodeURIComponent(document.sform1.st.value) + "&" + _dcf + "=" + encodeURIComponent(_dd0) + "&proj=" + document.sform1.proj.value, "projectcontent");
}
function projSearch(_dd5, _dd6, _dd7) {
	var st = document.sform1.st.value;
	if (st == "") {
		i18n.getJSAlertValue(Utils.zuid, "zp.search.empsearch", null,
		function(mesg) {
			alert(mesg);
		});
		ShowHideInline("projectcontent", "searchdiv");
		return false;
	}
	if ((new String(st)).length == 1) {
		i18n.getJSAlertValue(Utils.zuid, "zp.search.plzenterwords", null,
		function(mesg) {
			alert(mesg);
		});
		ShowHideInline("projectcontent", "searchdiv");
		Hide("ajax_load_tab");
		return false;
	}
	ajaxShowPage(Utils.contPath + "/projectsearch.do?scope=" + scope + "&st=" + encodeURIComponent(document.sform1.st.value) + "&" + _dd6 + "=" + encodeURIComponent(_dd7) + "&proj=" + document.sform1.proj.value, "searchdiv");
}
function projsearchissues() {
	document.sform1.scope.value = "issue";
	checkifinline();
	document.getElementById("issuelbl").style.display = "inline";
	document.getElementById("issueach").style.display = "none";
	Hide("tablist");
}
function projsearchmile() {
	document.sform1.scope.value = "milestones";
	checkifinline();
	document.getElementById("milelbl").style.display = "inline";
	document.getElementById("mileach").style.display = "none";
	Hide("tablist");
}
function projsearchtodo() {
	document.sform1.scope.value = "todos";
	checkifinline();
	document.getElementById("todolbl").style.display = "inline";
	document.getElementById("todoach").style.display = "none";
	Hide("tablist");
}
function projsearchmesg() {
	document.sform1.scope.value = "messages";
	checkifinline();
	document.getElementById("mesglbl").style.display = "inline";
	document.getElementById("mesgach").style.display = "none";
	Hide("tablist");
}
function projsearchcom() {
	document.sform1.scope.value = "comments";
	checkifinline();
	document.getElementById("comlbl").style.display = "inline";
	document.getElementById("comach").style.display = "none";
	Hide("tablist");
}
function projsearchfile() {
	document.sform1.scope.value = "docs";
	checkifinline();
	document.getElementById("filelbl").style.display = "inline";
	document.getElementById("fileach").style.display = "none";
	Hide("tablist");
}
function projsearchall() {
	document.sform1.scope.value = "all";
	checkifinline();
	document.getElementById("alllbl").style.display = "inline";
	document.getElementById("allach").style.display = "none";
	Hide("tablist");
}
function projsearchmeet() {
	document.sform1.scope.value = "meeting";
	checkifinline();
	document.getElementById("meetlbl").style.display = "inline";
	document.getElementById("meetach").style.display = "none";
	Hide("tablist");
}
function projsearchwiki() {
	document.sform1.scope.value = "wiki";
	checkifinline();
	document.getElementById("wikilbl").style.display = "inline";
	document.getElementById("wikiach").style.display = "none";
	Hide("tablist");
}
function gc(cn) {
	var _ddc = cn + "=";
	var ca = document.cookie.split(";");
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == " ") {
			c = c.substring(1, c.length);
		}
		if (c.indexOf(_ddc) == 0) {
			return c.substring(_ddc.length, c.length);
		}
	}
	return null;
}
function wikiResizeIframe(_de0) {
	var _de1 = document.getElementById("wikiembed");
	var _de2 = gc("zwHt");
	var _de3 = gc("zwWd");
	if (_de2) {
		_de1.style.height = _de2 + "px";
	} else {
		_de1.style.height = "950px";
	}
	if (_de3) {
		_de1.style.width = _de3 + "px";
	}
}
function validateMyStatus() {
	if (trim(document.getElementById("mystatus").value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.projpage.stsmsg", null,
		function(mesg) {
			alert(mesg);
		});
		document.getElementById("mystatus").focus();
		return false;
	} else {
		if (trim(document.getElementById("mystatus").value).length > 140) {
			i18n.getJSAlertValue(Utils.zuid, "zp.projpage.stsmsgcnt", null,
			function(mesg) {
				alert(mesg);
			});
			document.getElementById("mystatus").focus();
			return false;
		}
	}
	return true;
}
function onFocusCheck(_de6, _de7) {
	if (document.getElementById(_de7).value == _de6) {
		document.getElementById(_de7).value = "";
	}
}
function validateMyComment(_de8) {
	if (trim(document.getElementById(_de8).value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.projpage.stscmt", null,
		function(mesg) {
			alert(mesg);
		});
		document.getElementById(_de8).focus();
		return false;
	} else {
		if (trim(document.getElementById(_de8).value).length > 140) {
			i18n.getJSAlertValue(Utils.zuid, "zp.projpage.stsmsgcmt", null,
			function(mesg) {
				alert(mesg);
			});
			document.getElementById(_de8).focus();
			return false;
		}
	}
	return true;
}
function check(_deb, val) {
	if (document.getElementById(_deb)) {
		if (trim(document.getElementById(_deb).value).length == 0) {
			document.getElementById(_deb).value = val;
		}
	}
}
function showImportMPP(_ded) {
	var _dee = _ded.document.getElementById("mapuserid");
	if (!_dee) {
		return;
	}
	var text = _dee.innerHTML;
	Hide("zoho_mpp_busy");
	ShowHideInline("importmppwiz", "mppimport");
	var _df0 = document.getElementById("importmppwiz");
	_df0.innerHTML = text;
	ini = false;
}
function setAmount(mode, plan, _df3, _df4) {
	var a = new Object();
	a["exm"] = {
		"proj": "20",
		"wiki": "10",
		"chat": "10",
		"bug": "40"
	};
	a["exy"] = {
		"proj": "199",
		"wiki": "149",
		"chat": "149",
		"bug": "199"
	};
	a["pm"] = {
		"proj": "35",
		"wiki": "20",
		"chat": "20",
		"bug": "60"
	};
	a["py"] = {
		"proj": "299",
		"wiki": "149",
		"chat": "149",
		"bug": "299"
	};
	a["enm"] = {
		"proj": "80",
		"wiki": "40",
		"chat": "40",
		"bug": "80"
	};
	a["eny"] = {
		"proj": "599",
		"wiki": "149",
		"chat": "149",
		"bug": "599"
	};
	var m = new Object();
	m["exp"] = {
		"m": "exm",
		"q": "exq",
		"h": "exh",
		"y": "exy"
	};
	m["pre"] = {
		"m": "pm",
		"q": "pq",
		"h": "ph",
		"y": "py"
	};
	m["ent"] = {
		"m": "enm",
		"q": "enq",
		"h": "enh",
		"y": "eny"
	};
	var p = ["exp", "pre", "ent"];
	for (i = 0; i < p.length; i++) {
		document.getElementById(p[i] + "_plan").innerHTML = "<strong>$" + a[m[p[i]][mode]]["proj"] + "</strong>";
		document.getElementById(p[i] + "_chat").innerHTML = "<strong>$" + a[m[p[i]][mode]]["chat"] + "</strong>";
		document.getElementById(p[i] + "_bug").innerHTML = "<strong>$" + a[m[p[i]][mode]]["bug"] + "</strong>";
		if (mode == "y") {
			document.getElementById(p[i] + "_add").innerHTML = "";
			document.getElementById(p[i] + "_mode").innerHTML = "year";
			document.getElementById(p[i] + "_bugmode").innerHTML = "year";
			document.getElementById(p[i] + "_chatmode").innerHTML = "year";
		} else {
			if (mode == "m") {
				document.getElementById(p[i] + "_add").innerHTML = "/addon";
				document.getElementById(p[i] + "_mode").innerHTML = "month";
				document.getElementById(p[i] + "_bugmode").innerHTML = "month";
				document.getElementById(p[i] + "_chatmode").innerHTML = "month";
			}
		}
	}
	var _df8 = document.getElementById("upgButEnterprise");
	var _df9 = document.getElementById("upgButPremium");
	var _dfa = document.getElementById("upgButExpress");
	if (mode == "y") {
		document.getElementById("wikichatdiv").innerHTML = "Wiki + Chat";
		var ppc = document.getElementById("pre_projcnt");
		if (ppc) {
			ppc.innerHTML = "<em>Unlimited</em>";
		}
		var epc = document.getElementById("exp_projcnt");
		if (epc) {
			epc.innerHTML = "<em>Unlimited</em>";
		}
		Hide("exp_unl");
		Hide("pre_unl");
		Hide("discountDiv");
		if (plan != "Free") {
			Hide("ent_button");
			Hide("ent_chat_button");
			Hide("ent_wiki_button");
			Hide("ent_bug_button");
			Hide("pre_button");
			Hide("pre_chat_button");
			Hide("pre_wiki_button");
			Hide("pre_bug_button");
			Hide("exp_button");
			Hide("exp_chat_button");
			Hide("exp_wiki_button");
			Hide("exp_bug_button");
		}
		Hide("exp_chat_msg");
		ShowGenInline("exp_wc_button");
		Hide("pre_chat_msg");
		ShowGenInline("pre_wc_button");
		Hide("ent_chat_msg");
		ShowGenInline("ent_wc_button");
		document.getElementById("ent_wiki_msg").innerHTML = "Unlimited Wikis & Chats";
		document.getElementById("pre_wiki_msg").innerHTML = "Unlimited Wikis & Chats";
		document.getElementById("exp_wiki_msg").innerHTML = "Unlimited Wikis & Chats";
		if (_df8) {
			var _dfd = _df8.innerHTML;
			_df8.innerHTML = _dfd.replace("?mode=" + _df4, "?mode=" + _df3);
		}
		if (_df9) {
			var _dfe = _df9.innerHTML;
			_df9.innerHTML = _dfe.replace("?mode=" + _df4, "?mode=" + _df3);
		}
		if (_dfa) {
			var _dff = _dfa.innerHTML;
			_dfa.innerHTML = _dff.replace("?mode=" + _df4, "?mode=" + _df3);
		}
	} else {
		if (mode == "m") {
			document.getElementById("wikichatdiv").innerHTML = "Wiki,Chat";
			var ppc = document.getElementById("pre_projcnt");
			if (ppc) {
				ppc.innerHTML = "<em>50<em>";
			}
			var epc = document.getElementById("exp_projcnt");
			if (epc) {
				epc.innerHTML = "<em>20</em>";
			}
			ShowGenInline("exp_unl");
			ShowGenInline("pre_unl");
			ShowGenInline("discountDiv");
			if (plan != "Free") {
				ShowGenInline("ent_button");
				ShowGenInline("ent_chat_button");
				ShowGenInline("ent_wiki_button");
				ShowGenInline("ent_bug_button");
				ShowGenInline("pre_button");
				ShowGenInline("pre_chat_button");
				ShowGenInline("pre_wiki_button");
				ShowGenInline("pre_bug_button");
				ShowGenInline("exp_button");
				ShowGenInline("exp_chat_button");
				ShowGenInline("exp_wiki_button");
				ShowGenInline("exp_bug_button");
			}
			ShowGenInline("exp_chat_msg");
			Hide("exp_wc_button");
			ShowGenInline("pre_chat_msg");
			Hide("pre_wc_button");
			ShowGenInline("ent_chat_msg");
			Hide("ent_wc_button");
			document.getElementById("ent_wiki_msg").innerHTML = "Unlimited Wikis";
			document.getElementById("pre_wiki_msg").innerHTML = "50 Wikis";
			document.getElementById("exp_wiki_msg").innerHTML = "20 Wikis";
			if (_df8) {
				var _dfd = _df8.innerHTML;
				_df8.innerHTML = _dfd.replace("?mode=" + _df3, "?mode=" + _df4);
			}
			if (_df9) {
				var _dfe = _df9.innerHTML;
				_df9.innerHTML = _dfe.replace("?mode=" + _df3, "?mode=" + _df4);
			}
			if (_dfa) {
				var _dff = _dfa.innerHTML;
				_dfa.innerHTML = _dff.replace("?mode=" + _df3, "?mode=" + _df4);
			}
		}
	}
}
function hideFilter(ev) {
	var qobj = document.getElementById("fdiv");
	if (!qobj) {
		return;
	}
	if (is_ie) {
		var eobj = window.event.srcElement;
	} else {
		var eobj = ev.target;
	}
	if (eobj.id != "fname" && eobj.id != "fdiv") {
		var robj = document.getElementById("fname");
		if (qobj.className == "inline") {
			qobj.className = "hide";
			robj.className = "inline";
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
function attachFromDocs(_e04, _e05) {
	var _e06 = window.location.protocol;
	document.getElementById("zohodocs").innerHTML = "<iframe id=\"zohodocsframe\" height=\"500\" frameborder=\"0\" width=\"600\" src=\"" + _e06 + "//" + _e05 + "/index.do?service=projects&domain=" + _e04 + "\"></iframe>";
}
function attachFromGoogle(Url, _e08) {
	var _e09 = window.location.protocol;
	document.getElementById("googledocs").innerHTML = "<iframe id=\"gdocsframe\" height=\"510\" frameborder=\"0\" width=\"635\" src=\"https://" + Url + "/gdocs?zservice=zprojects&serURL=" + encodeURIComponent(_e09 + "//" + _e08 + "/jsp/gdocs.jsp") + "\"></iframe>";
}
function attachGDocs(_e0a, _e0b) {
	var _e0c = "";
	for (var i = 0,
	j = _e0a.length; i < j; i++) {
		if (document.getElementById("muluploaddisptable").rows.length < 10) {
			var _e0f = _e0a[i];
			var _e10 = _e0f.split(":");
			var _e11 = _e10[0];
			var _e12 = _e10[1];
			var _e13 = _e0f.replace(_e11 + ":" + _e12 + ":", "");
			if (document.getElementById("gdocid" + _e11) == null) {
				var _e14 = document.createElement("Input");
				_e14.setAttribute("type", "hidden");
				_e14.setAttribute("id", "gdocid" + _e11);
				_e14.setAttribute("name", "fromgdocs");
				_e14.setAttribute("value", _e0f);
				document.addDocuments.appendChild(_e14);
				var _e15 = document.createElement("tr");
				_e15.setAttribute("id", "gd" + _e11);
				var _e16 = document.createElement("td");
				_e16.setAttribute("class", "p3");
				_e15.appendChild(_e16);
				_e16.innerHTML = "<span class=\"txtSmallBlack11 pt5\">" + _e13 + "</span>&nbsp;<img align=\"top\" onclick=\"javascript:deleteGDocFile(this.parentNode.parentNode.id);\" class=\"zptrash cpointer\" src=\"/images/spacer.gif\"/>";
				document.getElementById("fileview").appendChild(_e15);
				showOrHideFilesList();
			} else {
				_e0c = "true";
			}
		} else {
			i18n.getJSAlertValue(Utils.zuid, "zp.documents.notuploadmorethanten", null,
			function(mesg) {
				alert(mesg);
			});
			break;
		}
	}
	if (_e0c == "true") {
		i18n.getJSAlertValue(Utils.zuid, "zp.documents.duplicatesremoved", null,
		function(mesg) {
			alert(mesg);
		});
	}
}
function Attachfiles(_e19, _e1a, _e1b) {
	var _e1c = _e19.split(",");
	var _e1d = _e1a.split(",");
	var _e1e = _e1b.split(",");
	var _e1f = "";
	if (_e1c.length == _e1d.length && _e1d.length == _e1e.length) {
		for (var i = 0; i < _e1c.length; i++) {
			if (document.getElementById("muluploaddisptable").rows.length < 10) {
				if (document.getElementById("docid" + _e1c[i]) == null) {
					var _e21 = document.createElement("Input");
					_e21.setAttribute("type", "hidden");
					_e21.setAttribute("id", "docid" + _e1c[i]);
					_e21.setAttribute("name", "fromdocs");
					_e21.setAttribute("value", _e1c[i] + "_" + _e1d[i] + "_" + _e1e[i]);
					document.addDocuments.appendChild(_e21);
					var _e22 = document.createElement("tr");
					_e22.setAttribute("id", "dr" + _e1c[i]);
					var _e23 = document.createElement("td");
					_e23.setAttribute("class", "p3");
					_e22.appendChild(_e23);
					_e23.innerHTML = "<span class=\"txtSmallBlack11 pt5\">" + _e1e[i] + "</span>&nbsp;<img align=\"top\" onclick=\"javascript:deleteDocFile(this.parentNode.parentNode.id);\" class=\"zptrash cpointer\" src=\"/images/spacer.gif\"/>";
					document.getElementById("fileview").appendChild(_e22);
					showOrHideFilesList();
				} else {
					_e1f = "true";
				}
			} else {
				i18n.getJSAlertValue(Utils.zuid, "zp.documents.notuploadmorethanten", null,
				function(mesg) {
					alert(mesg);
				});
				break;
			}
		}
		if (_e1f == "true") {
			i18n.getJSAlertValue(Utils.zuid, "zp.documents.duplicatesremoved", null,
			function(mesg) {
				alert(mesg);
			});
		}
	} else {
		i18n.getJSAlertValue(Utils.zuid, "zp.document.errorinstoringattach", null,
		function(mesg) {
			alert(mesg);
		});
	}
}
function deleteGDocFile(id) {
	var _e28 = document.getElementById(id);
	if (_e28) {
		_e28.parentNode.removeChild(_e28);
		var _e29 = document.getElementById(id.replace(/gd/, "gdocid"));
		_e29.parentNode.removeChild(_e29);
		showOrHideFilesList();
	}
}
function deleteDocFile(id) {
	var _e2b = document.getElementById(id);
	if (_e2b) {
		_e2b.parentNode.removeChild(_e2b);
		var _e2c = document.getElementById(id.replace(/dr/, "docid"));
		_e2c.parentNode.removeChild(_e2c);
		showOrHideFilesList();
	}
}
function changeAllClassName(_e2d, _e2e, _e2f) {
	if (is_ie) {
		showSelElements_iefix("a", _e2d);
	} else {
		var els = document.getElementsByName(_e2d);
		for (var i = 0,
		l = els.length; i < l; i++) {
			var _e33 = els[i].className;
			if (_e33.indexOf("Sel") != -1) {
				els[i].className = _e33.replace("Sel", "");
			}
		}
	}
	var _e34 = document.getElementById(_e2e);
	_e34.className = _e2f;
}
function showSelElements_iefix(tag, name) {
	var elem = document.getElementsByTagName(tag);
	for (i = 0; i < elem.length; i++) {
		att = elem[i].getAttribute("name");
		if (att == name) {
			var _e38 = elem[i].className;
			if (_e38.indexOf("Sel") != -1) {
				elem[i].className = _e38.replace("Sel", "");
			}
		}
	}
}
function hideOtherDiv(_e39) {
	if (_e39 == "upcoming_todos") {
		if (document.getElementById("overdue_todos")) {
			Hide("overdue_todos");
		}
		if (document.getElementById("archms")) {
			Hide("archms");
		}
		if (document.getElementById("completedms")) {
			Hide("completedms");
		}
	}
}
function changeElementClass(id, _e3b) {
	var _e3c = document.getElementById(id);
	if (_e3c != null) {
		_e3c.className = _e3b;
	}
}
function enableButton(id) {
	var _e3e = document.getElementById(id);
	_e3e.className = "buttonNew";
	_e3e.disabled = false;
}
function disableButton(id) {
	var _e40 = document.getElementById(id);
	_e40.className = "buttonCancel";
	_e40.disabled = true;
}
function setShowHideMile(_e41) {
	var _e42 = document.getElementById(_e41).className;
	var _e43 = _e41 + "_minus";
	var _e44 = _e41 + "_plus";
	if (_e42 == "hide") {
		ShowGenBlock(_e41);
		ShowHideInline(_e43, _e44);
	} else {
		Hide(_e41);
		ShowHideInline(_e44, _e43);
	}
}
function hideMoreMenu(ev, _e46, _e47) {
	var qobj = document.getElementById(_e46);
	if (!qobj) {
		return;
	}
	if (is_ie) {
		var eobj = window.event.srcElement;
	} else {
		var eobj = ev.target;
	}
	if (eobj.id != _e47 && eobj.id != _e46) {
		var robj = document.getElementById(_e47);
		if (qobj.className == "inline") {
			qobj.className = "hide";
			robj.className = "inline";
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
function cancelListOrder(_e4b, mid) {
	ShowHideInline("reorder_span_" + mid, "saveorder_span_" + mid);
	var hide = document.getElementsByTagName("div");
	for (var i = 0; i < hide.length; i++) {
		if (hide[i].id == "hideListItems_" + mid) {
			hide[i].style.display = "block";
		}
	}
	document.getElementById(_e4b).className = "ulstyle";
	Sortable.destroy(_e4b);
}
function swapTwoStyles(id, _e50, _e51) {
	swapElemClass(document.getElementById(id), _e50, _e51);
}
function swapElemClass(elem, _e53, _e54) {
	if (elem) {
		elem.className = (elem.className == _e53) ? _e54: _e53;
	}
}
function getEnterNotes(ev, form, url, id, stat, _e5a) {
	if (!ev) {
		ev = window.event;
	}
	if (ev.keyCode == "13") {
		if (url.indexOf("meetingnotes") != -1) {
			if (validateMeetNoteForm(stat, id)) {
				ajaxPostNote(url, form, _e5a);
			}
		} else {
			if (validateTaskNoteForm(stat, id)) {
				ajaxPostNote(url, form, _e5a);
			}
		}
		return false;
	}
	return false;
}
function loadCustomDiv(str) {
	var _e5c = document.getElementById("customloadingdiv");
	_e5c.innerHTML = "<span class=\"pop tabLoading\"><b>" + str + "&nbsp;<blink>...</blink></b></span>";
	ShowGenBlock("customloadingdiv");
}
function TaskNoteMailSendStatus(Id, cobj) {
	var id = document.getElementById(Id);
	if (cobj.checked) {
		Utils.isTaskNoteMailOpt = true;
		id.value = "yes";
	} else {
		Utils.isTaskNoteMailOpt = false;
		id.value = "no";
	}
}
function UpdateTaskNoteMailSendStatus(Id, cobj) {
	var id = document.getElementById(Id);
	if (cobj.checked) {
		Utils.isEditTaskNoteMailOpt = true;
		id.value = "yes";
	} else {
		Utils.isEditTaskNoteMailOpt = false;
		id.value = "no";
	}
}
function expandCollapseDiv(_e63, _e64, ele) {
	if (is_ie) {
		showHideElements_iefix(ele, _e63, _e64);
	} else {
		var els = document.getElementsByName(_e63);
		for (var i = 0,
		l = els.length; i < l; i++) {
			els[i].className = _e64;
		}
	}
}
function cancelTaskOrder(_e69) {
	var _e6a = "reorder_separator_" + _e69;
	var _e6b = "additem_separator_" + _e69;
	var _e6c = "ul_ttask_" + _e69;
	ShowHideInline(_e6b, _e6a);
	document.getElementById(_e6c).className = "ulstyle pm0";
	Sortable.destroy(_e6c);
}
function changeFocus() {
	var doc = document;
	var _e6e = doc.addLogHours;
	var _e6f = _e6e.logdate;
	var _e70 = _e6e.loghours;
	var _e71 = _e6e.lognotes;
	_e6f.className = "textFieldHighLight w75per";
	_e70.className = "textFieldHighLight w75per";
	_e71.className = "textFieldHighLight w75per h100";
	setTimeout(function() {
		_e6f.className = "textField w75per";
		_e70.className = "textField w75per";
		_e71.className = "textField w75per h100";
	},
	2000);
}
function showTaskMulUploadDoc(_e72, _e73) {
	var _e74 = _e72.document;
	var _e75 = _e74.getElementById("myupload");
	var _e76 = _e74.getElementById("projectdocs");
	var text = "";
	if (_e75) {
		text = _e75.innerHTML;
	} else {
		if (_e76) {
			text = _e76.innerHTML;
		} else {
			return;
		}
	}
	var doc = document;
	var _e79 = doc.getElementById("muldoc_upload_status");
	if (text.indexOf("Allocated Disc Space Full") != -1) {
		Hide("zoho_add_docs_busy");
		i18n.getJSAlertValue(Utils.zuid, "zp.newpost.diskfull", null,
		function(mesg) {
			_e79.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
	} else {
		if (text.indexOf("File with zero size cannot be uploaded") != -1) {
			Hide("zoho_add_docs_busy");
			i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.filesizezero", null,
			function(mesg) {
				_e79.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
		} else {
			if (text.indexOf("SIZE GREATER") != -1) {
				Hide("zoho_add_docs_busy");
				i18n.getJSAlertValue(Utils.zuid, "zp.docdetails.greatersize", null,
				function(mesg) {
					_e79.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
			} else {
				var _e7d = doc.getElementById("projectdocs");
				ShowHideInline("taskdetailsdiv", "newdocdiv");
				_e7d.innerHTML = text;
				initLightbox();
			}
		}
	}
}
function showHideCommonList(_e7e, _e7f, _e80) {
	if (_e80) {
		jQuery("#" + _e7f).attr("class", "actionmenu");
		jQuery("#" + _e7e).show();
	} else {
		jQuery("#" + _e7f).attr("class", "tsactionmenunor1");
		jQuery("#" + _e7e).hide();
	}
}
function showSwitchTo() {
	setShowHide("switchto");
	var dig = document.getElementById("switchto");
	dig.className = "userWidgetBorder";
	divLeft = findPosX(document.getElementById("switch")) - 1;
	dig.style.cssText = "z-index: 999;position: absolute;left:" + divLeft + "px";
}
function updMeetMailURL(_e82, sNo) {
	var _e84 = document.getElementById("mailurl_" + _e82);
	if (_e84) {
		var _e85 = document.getElementById("mailurl_" + _e82 + "_" + sNo);
		_e85.innerHTML = _e84.innerHTML;
		_e85.href = "mailto:" + _e84.innerHTML;
	}
	if (document.getElementById("mailreset_" + _e82)) {
		ShowGenInline("mailreset_" + _e82 + "_" + sNo);
	}
}
function ChangeDisplay(_e86, _e87, _e88) {
	document.getElementById("ReportViewBy").innerHTML = _e86;
	document.getElementById("ReportViewTitle").innerHTML = _e87;
	if (_e88 == 0) {
		Hide("ReportCreatedBy");
	} else {
		ShowGenInline("ReportCreatedBy");
	}
}
function validateDateReport() {
	var _e89 = "ReportForm";
	var _e8a = document.getElementById("datereport_status");
	var _e8b = eval("document." + _e89 + ".repstdate.value");
	var _e8c = eval("document." + _e89 + ".rependdate.value");
	Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
	var _e8d = compareDates(_e8b, Utils.dateformat, _e8c, Utils.dateformat);
	if (trim(document.ReportForm.repstdate.value).length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.stdateemp", null,
		function(mesg) {
			_e8a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
		});
		return (false);
	} else {
		if (trim(document.ReportForm.rependdate.value).length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.enddateemp", null,
			function(mesg) {
				_e8a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
			});
			return (false);
		} else {
			if (_e8b != "" && _e8c != "" && _e8d != 0) {
				i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
				function(mesg) {
					_e8a.innerHTML = "<span class=\"error\">" + mesg + "</span>";
				});
				eval("document." + _e89 + ".rependdate.value=\"\"");
				return false;
			} else {
				return true;
			}
		}
	}
}
isFlashAvailable = function() {
	var _e91 = 0;
	var _e92 = 0;
	var _e93 = false;
	var isMS = false;
	if (navigator.plugins && navigator.plugins.length) {
		x = navigator.plugins["Shockwave Flash"];
		if (x) {
			_e91 = 2;
			if (x.description) {
				y = x.description;
				_e92 = y.match(/\d{1,2}/);
			}
		} else {
			_e91 = 1;
		}
		if (navigator.plugins["Shockwave Flash 2.0"]) {
			_e91 = 2;
			_e92 = 2;
		}
	} else {
		if (navigator.mimeTypes && navigator.mimeTypes.length) {
			x = navigator.mimeTypes["application/x-shockwave-flash"];
			if (x && x.enabledPlugin) {
				_e91 = 2;
			} else {
				_e91 = 1;
			}
		} else {
			isMS = true;
		}
	}
	if (isMS) {
		var pid = new Array("ShockwaveFlash.ShockwaveFlash.9", "ShockwaveFlash.ShockwaveFlash.8.5", "ShockwaveFlash.ShockwaveFlash.8", "ShockwaveFlash.ShockwaveFlash.7");
		for (var p = 0; p < pid.length; p++) {
			try {
				var aobj = new ActiveXObject(pid[p]);
				if (aobj != null) {
					_e93 = true;
					break;
				}
			} catch(e) {}
		}
	} else {
		if (_e91 == 2 && _e92 > 7) {
			_e93 = true;
		}
	}
	return _e93;
};
function getSearchTimeSheet(_e98, _e99, _e9a, _e9b, csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	changeProjTabStyle("tsheet");
	ShowGenInline("ajax_load_tab");
	ajaxShowPage(Utils.contPath + "/logcalendar.do?projId=" + _e98 + "&toview=timesheet&username=" + _e99 + "&" + csrf + "&edate=1&eyear=" + _e9a + "&isaddtime=yes&emonth=" + _e9b, "projectcontent");
	changeSubLink("cvLink", "cvImg");
}
function exportingReports(_e9d, _e9e) {
	var _e9f = new FusionChartsExportObject(_e9d, "/flash/FCExporter.swf");
	_e9f.componentAttributes.width = "150";
	_e9f.componentAttributes.btnWidth = "100";
	_e9f.componentAttributes.btnHeight = "20";
	i18n.getJSAlertValue(Utils.zuid, "zp.reports.savethechart", null,
	function(mesg) {
		_e9f.componentAttributes.btnsavetitle = mesg;
	});
	i18n.getJSAlertValue(Utils.zuid, "zp.reports.waitingforexport", null,
	function(mesg) {
		_e9f.componentAttributes.btndisabledtitle = mesg;
	});
	_e9f.Render(_e9e);
}
function timer(_ea2) {
	if (document.getElementById(_ea2)) {
		var _ea3 = document.getElementById(_ea2).value;
		var sec = (_ea3.split(":")[2]).trim();
		var min = parseInt(trim(_ea3.split(":")[1]), 10);
		var hour = parseInt(trim(_ea3.split(":")[0]), 10);
		sec++;
		if (sec == 60) {
			sec = 0;
			min = ++min;
		}
		if (min == 60) {
			min = 0;
			hour = ++hour;
		}
		if (sec < 10) {
			sec = "0" + sec;
		}
		if (min < 10) {
			min = "0" + min;
		}
		if (hour < 10) {
			hour = "0" + hour;
		}
		document.getElementById(_ea2).value = hour + " : " + min + " : " + sec;
		this.timerKey = setTimeout(function() {
			setTimer(_ea2);
		},
		1000);
	}
}
timer.prototype.stop = function() {
	clearTimeout(this.timerKey);
};
function startTimer(_ea7) {
	timerObject[timerObject.length] = _ea7;
	setTimer(_ea7);
}
function setTimer(_ea8) {
	var _ea9 = new timer(_ea8);
	timerArray[_ea8] = _ea9;
}
function stopTimer(_eaa) {
	var _eab = timerArray[_eaa];
	if (_eab) {
		_eab.stop();
	}
}
function clearTimer() {
	for (var i = 0; i < timerObject.length; i++) {
		if (timerObject[i]) {
			stopTimer(timerObject[i]);
		}
	}
	timerObject = new Array();
}
jQuery.fn.extend({
	hasChildren: function() {
		return this.children().length > 0;
	},
	className: function(_ead) {
		return this.attr("class", _ead);
	}
});
function showSwithToDiv(e, t) {
	stopEvents(e);
	var _eb0 = "#switchToDiv";
	if (!jQuery(_eb0).hasChildren()) {
		jQuery(_eb0).load("/switchto", {
			dropDownClass: "zpzappsdnarrow"
		},
		function() {
			jQuery(_eb0).show();
		});
	} else {
		jQuery(_eb0).show();
	}
	var dig = document.getElementById("switchToDiv");
	var _eb2 = findPosX(t) - 161;
	var _eb3 = findPosY(t) - 3;
	dig.style.cssText = "z-index:20001;position: absolute;width:400px;top:" + _eb3 + "px;left:" + _eb2 + "px";
	document.onclick = function() {
		jQuery(_eb0).hide();
	};
}
function isFilesSizeGreater(name, _eb5) {
	if (window.File) {
		var _eb6 = document.getElementsByName(name);
		var _eb7 = 0;
		for (var j = 0,
		k = _eb6.length; j < k; j++) {
			var _eba = _eb6[j].files;
			for (var i = 0,
			l = _eba.length; i < l; i++) {
				if (_eba[i].size) {
					_eb7 += _eba[i].size;
				} else {
					_eb7 += _eba[i].fileSize;
				}
				if (_eb7 > _eb5 * 1024 * 1024) {
					return true;
				}
			}
		}
	}
	return false;
}
function loadZEditor(id, _ebe) {
	Utils.editorObj[id] = ZE.create({
		id: id,
		content: _ebe
	});
}
function updateProjEmail() {
	var _ebf = document.changemailform;
	var val = _ebf.value.value;
	if (val.length == 0) {
		i18n.getJSAlertValue(Utils.zuid, "zp.projpage.emptystring", null,
		function(mesg) {
			alert(mesg);
		});
		return false;
	} else {
		if (val.length > 30) {
			i18n.getJSAlertValue(Utils.zuid, "zp.projpage.morelength", null,
			function(mesg) {
				alert(mesg);
			});
			return false;
		} else {
			if (!/^[a-z0-9]+$/.test(val)) {
				i18n.getJSAlertValue(Utils.zuid, "zp.projpage.notallowed", null,
				function(mesg) {
					alert(mesg);
				});
				return false;
			}
		}
	}
	ajaxSubmitPage(Utils.contPath + "/changeprojemail.do", _ebf, "edit");
}
function subStr(str, len) {
	if (str.length > len + 3) {
		return str.substring(0, len) + "...";
	}
	return str;
}
function selectOrDeselectAllGUser() {
	var _ec6 = document.getElementsByName("gusercheckbox");
	if (!document.getElementById("selectAll").checked) {
		for (i = 0; i < _ec6.length; i++) {
			_ec6[i].checked = false;
		}
	} else {
		for (i = 0; i < _ec6.length; i++) {
			_ec6[i].checked = true;
		}
	}
}
function selectedAllOrNotGAppUser() {
	var _ec7 = document.getElementsByName("gusercheckbox");
	var j = 0;
	for (i = 0; i < _ec7.length; i++) {
		if (_ec7[i].checked) {
			j++;
		}
	}
	if (j == _ec7.length) {
		document.getElementById("selectAll").checked = true;
	} else {
		document.getElementById("selectAll").checked = false;
	}
}
function SelectedOrNot(url, _eca) {
	var _ecb;
	var _ecc = document.getElementsByName("gusercheckbox");
	var _ecd = document.gappsusers.projId.value;
	var j = false;
	for (i = 0; i < _ecc.length; i++) {
		if (_ecc[i].checked) {
			_ecb = document.gappsusers["role" + i];
			url = url + "&adduser=" + encodeURIComponent(_ecb[0].checked ? _ecb[0].value: _ecb[1].value);
			j = true;
		}
	}
	if (j) {
		ajaxShowPage(url + "&frompage=wizard2&projId=" + _ecd, _eca);
		Hide("zoho_gapps");
	} else {
		i18n.getJSAlertValue(Utils.zuid, "zp.gapps.nouserselect", null,
		function(mesg) {
			alert(mesg);
		});
	}
}
function saveTabOrder(csrf, _ed1, _ed2) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var _ed3 = document.tabreorderform;
	var tabs = _ed3.tab;
	var _ed5 = {};
	for (var i = 0,
	l = tabs.length; i < l; i++) {
		_ed5[tabs[i].value] = [i, tabs[i].checked];
	}
	var seq = "",
	ena = "";
	for (var i = 0,
	l = tabs.length; i < l; i++) {
		seq += _ed5[i][0].toString(16);
		ena += _ed5[i][1] ? "1": "0";
	}
	var url = Utils.contPath + "/savetaborder.do?order=" + seq + "&projId=" + _ed3.projId.value + "&" + csrf + "&toview=" + _ed2;
	url += (_ed1 ? ("&enable=" + ena + "&type=sequence") : "&type=order");
	if (_ed3.all.checked) {
		url += "&all=true";
	}
	ajaxSendRequest(url, csrf, _ed3.projId.value);
}
function showSwithToHelp(e, t) {
	setShowHide("tp_helpdiv");
	var _edd = document.getElementById("tp_helpdiv");
	var pos = jQuery("#listhelp").offset();
	var _edf = pos.left - 46;
	var _ee0 = pos.top - 5;
	_edd.style.cssText = "z-index: 999;position: absolute;top:" + _ee0 + "px;left:" + _edf + "px";
}
function showSwithToSetting(e, t) {
	setShowHide("tp_settingdiv");
	var _ee3 = document.getElementById("tp_settingdiv");
	var _ee4 = jQuery("#listsetting").offset();
	var _ee5 = _ee4.left - 38;
	var _ee6 = _ee4.top - 5;
	_ee3.style.cssText = "z-index: 800;position: absolute;top:" + _ee6 + "px;left:" + _ee5 + "px";
}
function checkifinline() {
	var lbl = document.getElementsByName("serscopelbl");
	var hid = document.getElementsByName("serscopear");
	for (var i = 0; i < lbl.length; i++) {
		if (lbl[i].style.display == "inline") {
			lbl[i].style.display = "none";
			hid[i].style.display = "inline";
			hid[i].style.color = "#666";
		}
	}
}
function uncheckallproj(tid, sid, _eec) {
	if (document.getElementById(tid).checked == true) {
		document.getElementById(tid).checked = false;
	}
	var _eed = 0;
	var _eee = eval(document.getElementsByName(_eec));
	for (var i = 0; i < _eee.length; i++) {
		if (_eee[i].checked) {
			_eed++;
		}
	}
	if (_eed == 0) {
		Hide(sid);
	} else {
		ShowGenInline(sid);
	}
}
function uncheckallcli(tid, sid, _ef2) {
	if (document.getElementById(tid).checked == true) {
		document.getElementById(tid).checked = false;
	}
	var _ef3 = 0;
	var _ef4 = eval(document.getElementsByName(_ef2));
	for (var i = 0; i < _ef4.length; i++) {
		if (_ef4[i].checked) {
			_ef3++;
		}
	}
	if (_ef3 == 0) {
		Hide(sid);
	} else {
		ShowGenInline(sid);
	}
}
function checkalldefault(cbid, sid) {
	if (document.getElementById(cbid).checked) {
		ShowGenInline(sid);
	} else {
		Hide(sid);
	}
}
function validateBulkAddUserCrm() {
	var cnt = document.addCrmUser.count.value;
	var _ef9 = document.addCrmUser.seluserroles.value;
	var _efa = document.addCrmUser.seluseremail.value;
	counter = 0;
	var j = false;
	for (i = 0; i < cnt; i++) {
		var _efc = "usercheckbox" + i;
		var _efd = "userrole" + i;
		var _efe = "useremailid" + i;
		if (eval("document.addCrmUser." + _efd) && eval("document.addCrmUser." + _efe) && eval("document.addCrmUser." + _efc)) {
			var _eff = eval("document.addCrmUser." + _efd + ".value");
			var _f00 = eval("document.addCrmUser." + _efe + ".value");
			var _f01 = eval("document.addCrmUser." + _efc + ".checked");
			if (_f01) {
				if (counter == 0) {
					_ef9 = _eff;
					_efa = _f00;
				} else {
					_ef9 = _ef9 + "," + _eff;
					_efa = _efa + "," + _f00;
				}
				j = true;
				counter = counter + 1;
			}
		}
	}
	document.addCrmUser.seluserroles.value = _ef9;
	document.addCrmUser.seluseremail.value = _efa;
	if (j) {
		return true;
	} else {
		i18n.getJSAlertValue(Utils.zuid, "zp.gapps.nouserselect", null,
		function(mesg) {
			alert(mesg);
		});
		return false;
	}
}
function crmuserselect() {
	if (document.getElementById("selectAll").checked) {
		jQuery("#checkboxtd [id='crmusercheckbox']").attr("checked", "checked");
	} else {
		jQuery("#checkboxtd [id='crmusercheckbox']").attr("checked", null);
	}
}
function crmclientselect() {
	var cnt = document.addClientCrm.count.value;
	var _f04 = document.addClientCrm.countemail.value;
	var _f05;
	var _f06;
	for (i = 1; i <= cnt; i++) {
		var _f07 = "ccompId_" + i;
		var _f08 = eval("document.addClientCrm." + _f07 + ".value");
		_f05 = "accname_" + i + "_" + _f08;
		_f06 = eval("document.addClientCrm." + _f05 + ".checked");
		if (eval("document.addClientCrm." + _f05)) {
			if (_f06) {
				for (j = 1; j <= _f04; j++) {
					var _f09 = "accId_" + j;
					if (eval("document.addClientCrm." + _f09)) {
						var _f0a = eval("document.addClientCrm." + _f09 + ".value");
						var _f0b = "emailid_" + j + "_" + _f0a;
						if (_f08 == _f0a) {
							jQuery("#checkboxtd [name=" + _f0b + "]").attr("checked", "checked");
						}
					}
				}
			}
		}
	}
}
function crmclientuserselect() {
	var cnt = document.addClientCrm.count.value;
	var _f0d = document.addClientCrm.countemail.value;
	var _f0e;
	var _f0f;
	for (j = 1; j <= _f0d; j++) {
		var _f10 = "accId_" + j;
		if (eval("document.addClientCrm." + _f10)) {
			var _f11 = eval("document.addClientCrm." + _f10 + ".value");
			var _f12 = "emailid_" + j + "_" + _f11;
			var _f13 = eval("document.addClientCrm." + _f12 + ".checked");
			if (_f13) {
				for (i = 1; i <= cnt; i++) {
					var _f14 = "ccompId_" + i;
					if (eval("document.addClientCrm." + _f14)) {
						var _f15 = eval("document.addClientCrm." + _f14 + ".value");
						var _f0e = "accname_" + i + "_" + _f15;
						if (_f15 == _f11) {
							jQuery("#checkboxacctd [name=" + _f0e + "]").attr("checked", "checked");
						}
					}
				}
			}
		}
	}
}
function validateaddclientCrm(url, _f17, _f18) {
	var cnt = document.addClientCrm.count.value;
	var _f1a = document.addClientCrm.selcompany.value;
	var _f1b = document.addClientCrm.selemailid.value;
	counter = 0;
	var k = false;
	for (var i = 0; i < cnt; i++) {
		var _f1e = "accId_" + i;
		var _f1f = eval("document.addClientCrm." + _f1e + ".value");
		var _f20 = eval("document.addClientCrm.accname_" + _f1f + ".value");
		var _f21 = "emailid_" + _f1f + "_" + i;
		var _f22 = "cemailid_" + _f1f + "_" + i;
		if (eval("document.addClientCrm." + _f21) && eval("document.addClientCrm." + _f22) && eval("document.addClientCrm." + _f1e)) {
			var _f23 = eval("document.addClientCrm." + _f21 + ".checked");
			var _f24 = eval("document.addClientCrm." + _f22 + ".value");
			if (_f23) {
				if (counter == 0) {
					_f1a = _f20;
					_f1b = _f24;
				} else {
					_f1a = _f1a + "#,#" + _f20;
					_f1b = _f1b + "#,#" + _f24;
				}
				counter = counter + 1;
				k = true;
			}
		}
		document.addClientCrm.selcompany.value = _f1a;
		document.addClientCrm.selemailid.value = _f1b;
	}
	url += "&selcompany=" + _f1a + "&selemailid=" + _f1b;
	if (_f17 == "0") {
		var _f25 = document.forms.addClientCrm;
		var x = 0;
		for (x = 0; x < _f25.workprojects.length; x++) {
			url += "&workprojects=" + _f25.workprojects[x].value;
		}
	} else {
		url += "&workprojects=" + document.addClientCrm.workprojects.value;
	}
	if (k) {
		ajaxShowPage(url, _f18);
	} else {
		i18n.getJSAlertValue(Utils.zuid, "No Client Company are selected to add", null,
		function(mesg) {
			alert(mesg);
		});
		return false;
	}
}
function validateAddOrgUser() {
	var cnt = document.addOrgUser.count.value;
	var _f29 = document.addOrgUser.seluserroles.value;
	var _f2a = document.addOrgUser.seluseremail.value;
	counter = 0;
	var j = false;
	for (i = 0; i < cnt; i++) {
		var _f2c = "usercheckbox" + i;
		var _f2d = "userrole" + i;
		var _f2e = "useremailid" + i;
		if (eval("document.addOrgUser." + _f2d) && eval("document.addOrgUser." + _f2e) && eval("document.addOrgUser." + _f2c)) {
			var _f2f = eval("document.addOrgUser." + _f2d + ".value");
			var _f30 = eval("document.addOrgUser." + _f2e + ".value");
			var _f31 = eval("document.addOrgUser." + _f2c + ".checked");
			if (_f31) {
				if (counter == 0) {
					_f29 = _f2f;
					_f2a = _f30;
				} else {
					_f29 = _f29 + "," + _f2f;
					_f2a = _f2a + "," + _f30;
				}
				j = true;
				counter = counter + 1;
			}
		}
	}
	document.addOrgUser.seluserroles.value = _f29;
	document.addOrgUser.seluseremail.value = _f2a;
	if (j) {
		return true;
	} else {
		i18n.getJSAlertValue(Utils.zuid, "zp.gapps.nouserselect", null,
		function(mesg) {
			alert(mesg);
		});
		return false;
	}
}
function OrgUserselect() {
	if (document.getElementById("selectAllUser").checked) {
		jQuery("#checkboxtd [id='orgusercheckbox']").attr("checked", "checked");
	} else {
		jQuery("#checkboxtd [id='orgusercheckbox']").attr("checked", null);
	}
}
function checkAllTasks(tlid, _f34, _f35) {
	if (document.getElementById(_f35).checked) {
		jQuery("#" + tlid + " [name='" + _f34 + "']").attr("checked", "checked");
	} else {
		jQuery("#" + tlid + " [name='" + _f34 + "']").attr("checked", null);
	}
}
function openOptions(id, _f37, _f38, _f39, _f3a, inp1, inp2) {
	taskbulk = "";
	taskdiv_id = id;
	taskdiv_operation = _f37;
	taskdiv_par_id = _f3a;
	var pos = jQuery("#" + _f39 + id).offset();
	var _f3e = Math.round(eval(pos.top) - 4);
	var _f3f = Math.round(eval(pos.left));
	if (inp1 && inp2) {
		document.getElementById("durtext").value = inp1;
		if (Utils.taskinhr == "true") {
			if ("days" == inp2) {
				jQuery("input:radio[name=popdurtype]")[0].checked = true;
			} else {
				jQuery("input:radio[name=popdurtype]")[1].checked = true;
			}
		}
	}
	jQuery("#" + _f38).css({
		"top": (_f3e + 20) + "px",
		"left": (_f3f - 25) + "px"
	});
	jQuery("#" + _f38).show();
	scrollInViewPart(_f38);
}
function openOwnerOptions(id, _f41, _f42, _f43, _f44, _f45) {
	taskbulk = "";
	taskdiv_id = id;
	taskdiv_operation = _f41;
	taskdiv_par_id = _f44;
	var pos = jQuery("#" + _f43 + id).offset();
	var _f47 = Math.round(eval(pos.top) + 20);
	var _f48 = Math.round(eval(pos.left));
	jQuery("#" + _f42).css({
		"top": _f47 + "px",
		"left": _f48 + "px"
	});
	var _f49 = _f45.split("_");
	jQuery("#townerdiv").find("input[type=checkbox]:checked").removeAttr("checked");
	for (var i = 0; i < _f49.length; i++) {
		if (document.getElementById("btaskowner_" + _f49[i])) {
			document.getElementById("btaskowner_" + _f49[i]).checked = true;
		}
	}
	jQuery("#" + _f42).show();
	scrollInViewPart(_f42);
}
function openBulkOptions(id, _f4c, _f4d, _f4e, _f4f) {
	taskbulk = "bulk";
	taskdiv_id_bulk = id;
	taskdiv_operation_bulk = _f4c;
	taskdiv_par_id = _f4f;
	taskdiv_link_bulk = _f4e + id;
	var pos = jQuery("#" + _f4e + id).offset();
	var _f51 = Math.round(eval(pos.top) - 4);
	var _f52 = Math.round(eval(pos.left));
	_f51 = _f51 + 20;
	_f52 = _f52 - 45;
	jQuery("#" + _f4d).css({
		"top": _f51 + "px",
		"left": _f52 + "px"
	});
	jQuery("#" + _f4d).show();
	scrollInViewPart(_f4d);
}
function bulkOptionEntries(id, _f54, _f55) {
	taskbulk = "bulk";
	taskdiv_id_bulk = id;
	taskdiv_operation_bulk = _f54;
	taskdiv_par_id = _f55;
}
function updateValues(url, from, _f58) {
	var _f59 = "";
	var id = new Array();
	if (taskbulk == "bulk") {
		_f59 = taskdiv_operation_bulk;
		var _f5b = document.getElementsByName("selTask_" + taskdiv_id_bulk);
		for (var i = 0,
		j = 0; i < _f5b.length; i++) {
			if (_f5b[i].checked) {
				checked = true;
				var _f5e = new String(_f5b[i].id);
				var dd = _f5e.split("_");
				id[j++] = dd[1];
			}
		}
		if (id.length > 0) {
			url = url + "&operation=" + _f59 + "&ttaskid=" + id;
			if ("percomp" == _f58) {
				url = url + "&listId=" + (taskdiv_par_id.split("_")[2]);
			}
		} else {
			i18n.getJSAlertValue(Utils.zuid, "zp.tasks.selecttasks", null,
			function(mesg) {
				alert(mesg);
			});
			return false;
		}
		if ("btaskowner" == _f58) {
			var _f61 = new Array();
			var _f62 = document.getElementsByName(_f58);
			var _f63 = 0;
			if (_f62.length) {
				for (var i = 0; i < _f62.length; i++) {
					if (_f62[i].checked) {
						var _f64 = (_f62[i].id).split("_")[1];
						_f61[_f63] = _f64;
						var uid = new String(_f64);
						url = url + "&personresponsible=" + uid;
						_f63++;
						_f62[i].checked = false;
					}
				}
			} else {
				if (_f62.checked) {
					_f61[0] = (_f62.id).split("_")[1];
					var uid = new String((_f62.id).split("_")[1]);
					url = url + "&personresponsible=" + uid;
					_f63++;
					_f62.checked = false;
				}
			}
			if (_f61.length == 0) {
				url = url + "&personresponsible=AnyUser";
			}
			hideCommonList("townerdiv");
		}
		selTaskCheck = jQuery.grep(selTaskCheck,
		function(_f66) {
			return _f66 != taskdiv_id_bulk;
		});
		Effect.SlideUp("todoactionslide_" + taskdiv_id_bulk);
	} else {
		_f59 = taskdiv_operation;
		id = taskdiv_id;
		url = url + "&ttaskid=" + id + "&operation=" + _f59;
		if ("percomp" == _f58) {
			url = url + "&listId=" + (taskdiv_par_id.split("_")[2]);
		}
		if ("btaskowner" == _f58) {
			var _f61 = new Array();
			var _f62 = document.getElementsByName(_f58);
			var _f63 = 0;
			if (_f62.length) {
				for (var i = 0; i < _f62.length; i++) {
					if (_f62[i].checked) {
						var _f64 = (_f62[i].id).split("_")[1];
						_f61[_f63] = _f64;
						var uid = new String(_f64);
						url = url + "&personresponsible=" + uid;
						_f63++;
						_f62[i].checked = false;
					}
				}
			} else {
				if (_f62.checked) {
					var _f64 = (_f62.id).split("_")[1];
					_f61[0] = _f64;
					var uid = new String(_f64);
					url = url + "&personresponsible=" + uid;
					_f63++;
					_f62.checked = false;
				}
			}
			if (_f61.length == 0) {
				url = url + "&personresponsible=AnyUser";
			}
			hideCommonList("townerdiv");
		}
		if ("duration" == _f58) {
			var dt = "days";
			var d = document.getElementById("durtext").value;
			if (Utils.taskinhr == "true") {
				dt = jQuery("input:radio[name=popdurtype]:checked").val();
			}
			if (!isNumeric(d) && (dt == "days")) {
				i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.tododurmustbe", null,
				function(mesg) {
					alert(mesg);
				});
				return false;
			} else {
				if (d != "" && !isValDur(d) && dt == "hrs") {
					i18n.getJSAlertValue(Utils.zuid, "zp.task.invdur", null,
					function(mesg) {
						alert(mesg);
					});
					return false;
				}
			}
			url = url + "&duration=" + d + "&durtype=" + dt;
			hideCommonList("durdiv");
		}
	}
	if ("percomp" == _f58 && taskbulk != "bulk") {
		ajaxShowTab(url, "ul_ttask_" + taskdiv_par_id.split("_")[2]);
	} else {
		ajaxShowTab(url, taskdiv_par_id);
	}
}
function showHideTaskPropertyList(_f6b, _f6c) {
	if (_f6c) {
		jQuery("#" + _f6b).show();
	} else {
		jQuery("#" + _f6b).hide();
	}
}
function tasksSelected(_f6d) {
	var _f6e = new Boolean();
	_f6e = false;
	var _f6f = new Array();
	var _f70 = document.getElementsByName(_f6d);
	for (var i = 0,
	j = 0; i < _f70.length; i++) {
		if (_f70[i].checked) {
			_f6e = true;
			var _f73 = new String(_f70[i].id);
			var dd = _f73.split("_");
			_f6f[j++] = dd[1];
		}
	}
	bulk_task_id = _f6f;
	return _f6e;
}
function addValues(_f75) {
	var _f76 = new Array();
	var _f77 = document.getElementsByName(_f75);
	var _f78 = 0;
	var _f79 = "";
	var _f7a = "Unassigned";
	if (_f77.length) {
		for (var i = 0; i < _f77.length; i++) {
			if (_f77[i].checked) {
				_f76[_f78] = _f77[i].id;
				var uid = new String(_f77[i].id);
				_f79 = _f79 + "&personresponsible=" + uid;
				if (_f7a != "" && _f7a != "Unassigned") {
					_f7a = _f7a + ", " + new String(_f77[i].value);
				} else {
					_f7a = new String(_f77[i].value);
				}
				_f78++;
				_f77[i].checked = false;
			}
		}
	} else {
		if (_f77.checked) {
			_f76[0] = _f77.id;
			var uid = new String(_f77.id);
			_f79 = _f79 + "&personresponsible=" + uid;
			_f7a = new String(_f77.value);
			_f78++;
			_f77.checked = false;
		}
	}
	addtaskowner = _f79;
	if ("Unassigned" == _f7a) {
		addtaskowner = "&personresponsible=AnyUser";
	}
	if (_f7a != "") {
		document.getElementById("personres_" + taskdiv_id).innerHTML = _f7a;
	}
	if (is_ie) {
		changeStyleByName("addownerdiv", "div", "none");
	}
}
function addNewTask(url, csrf, Id) {
	var _f80 = document.getElementById("todotask_" + Id);
	var _f81 = document.getElementById("priority_" + Id);
	var _f82 = document.getElementById("taskenddate_" + Id).value;
	var _f83 = document.getElementById("ttaskdate_" + Id).value;
	var _f84 = document.getElementById("notifyuser_" + Id).value;
	Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
	if (getDateFromFormat(_f83, Utils.dateformat) == "0") {
		_f83 = "";
	}
	if (getDateFromFormat(_f82, Utils.dateformat) == "0") {
		_f82 = "";
	}
	var _f85 = addtaskowner;
	if (!_f85 || _f85 == "") {
		_f85 = "&personresponsible=AnyUser";
	}
	url = url + "&todotask=" + encodeURIComponent(_f80.value) + "&priority=" + _f81.value + "&taskdate=" + _f83 + "&notifyuser=" + _f84 + "&taskenddate=" + _f82 + _f85;
	url = url + "&" + csrf;
	if (document.getElementById("gcalevnt")) {
		if (document.getElementById("gcalevnt").checked) {
			url = url + "&gcalevnt=true";
		} else {
			url = url + "&gcalevnt=false";
		}
	}
	if (document.getElementById("gtask")) {
		if (document.getElementById("gtask").checked) {
			url = url + "&gtask=true";
		} else {
			url = url + "&gtask=false";
		}
	}
	ajaxShowTab(url, ("ul_ttask_" + Id));
	document.getElementById("taskenddate_" + Id).value = "";
	document.getElementById("ttaskdate_" + Id).value = "";
	if (document.getElementById("trid_" + Id).className == "hide") {
		document.getElementById("trid_" + Id).className = "";
	}
}
function openActions(id, _f87, _f88, _f89, _f8a, _f8b) {
	action_id = id;
	action_type = _f87;
	action_par_id = _f8a;
	var pos = jQuery("#" + _f89 + id).offset();
	var _f8d = Math.round(eval(pos.top) - 4) + 5;
	var _f8e = Math.round(eval(pos.left));
	if (0 == _f8b) {
		if (document.getElementById("copytltask")) {
			Hide("copytltask");
		}
		if (document.getElementById("makeastemp")) {
			Hide("makeastemp");
		}
	} else {
		if (document.getElementById("copytltask")) {
			ShowGenBlock("copytltask");
		}
		if (document.getElementById("makeastemp")) {
			ShowGenBlock("makeastemp");
		}
	}
	document.getElementById(_f88).style.cssText = "top:" + _f8d + "px;left:" + _f8e + "px";
	jQuery("#" + _f88).show();
}
function openImgAction(id, _f90, _f91, _f92, _f93) {
	action_id = id;
	action_type = _f90;
	action_par_id = _f93;
	var pos = jQuery("#" + _f92 + id).offset();
	var _f95 = Math.round(eval(pos.top) - 4) + 5;
	var _f96 = Math.round(eval(pos.left));
	document.getElementById(_f91).style.cssText = "top:" + _f95 + "px;left:" + _f96 + "px";
	jQuery("#" + _f91).show();
}
function actionsSubmit(url, _f98, _f99, _f9a, act, _f9c) {
	if (act == "depDropDown") {
		var _f9d = jQuery("#tddropicon_" + taskid_dep).position().left;
		document.getElementById(_f99).style.display = "inline";
		jQuery("#" + _f99).css("left", _f9d);
		jQuery("#" + _f99).appendTo("#tddropicon_" + taskid_dep);
		url = url + "&" + _f98 + "=" + taskid_dep;
		if (_f9c != null) {
			url = url + "&" + _f9c + "=" + tlid_dep;
		}
	} else {
		url = url + "&" + _f98 + "=" + action_id;
	}
	if ("delete" == act) {
		Hide("ul_ttitle_" + action_id);
		Hide("ul_ttask_" + action_id);
		Hide("ul_tfoot_" + action_id);
	}
	if (_f99 == "") {
		_f99 = action_id;
	}
	if (_f99 != "_") {
		eval(_f9a + "('" + url + "','" + _f99 + "')");
	} else {
		eval(_f9a + "('" + url + "')");
	}
}
function openTaskActions(id, _f9f, _fa0, _fa1, _fa2) {
	var pos = jQuery("#" + _fa1 + id).offset();
	var _fa4 = Math.round(eval(pos.top) - 4) + 5;
	var _fa5 = Math.round(eval(pos.left));
	document.getElementById(_fa0).style.cssText = "top:" + _fa4 + "px;left:" + _fa5 + "px";
	jQuery("#" + _fa0).show();
}
function changeActionEvent(_fa6, _fa7, _fa8) {
	if (is_ie) {
		var elem = document.getElementsByTagName("tr");
		for (i = 0; i < elem.length; i++) {
			att = elem[i].getAttribute("name");
			if (att == _fa6) {
				if (_fa7 == "" && _fa8 == "") {
					elem[i].className = "";
				} else {
					elem[i].className = _fa7;
				}
			}
		}
	} else {
		var elem = document.getElementsByName(_fa6);
		for (var i = 0,
		l = elem.length; i < l; i++) {
			if (_fa7 == "" && _fa8 == "") {
				elem[i].className = "";
			} else {
				elem[i].className = _fa7;
			}
		}
	}
}
function showTaskOptionsSubTab(_fac) {
	changeElementClass("tasknotes", " ");
	changeElementClass("taskloghrs", " ");
	changeElementClass("taskdepend", " ");
	changeElementClass("taskactivity", " ");
	changeElementClass("tasknotescontent", "hide");
	changeElementClass("taskloghrscontent", "hide");
	changeElementClass("taskactivitycontent", "hide");
	changeElementClass("taskdependcontent", "hide");
	changeElementClass("tasknotesrhslink", "tslink");
	changeElementClass("taskloghrsrhslink", "tslink");
	changeElementClass("taskdependrhslink", "tslink");
	changeElementClass("taskactivityrhslink", "tslink");
	if (!_fac) {
		changeElementClass(issueDetailViewSubTab, "curr");
		changeElementClass(issueDetailViewSubTab + "content", "inline");
		changeElementClass(issueDetailViewSubTab + "rhslink", "bugsetlink");
	} else {
		changeElementClass(_fac, "curr");
		changeElementClass(_fac + "content", "inline");
		changeElementClass(_fac + "rhslink", "bugsetlink");
		jQuery("html,body").animate({
			scrollTop: (jQuery("#issuesubtabs").offset().top) - 5
		},
		400);
		issueDetailViewSubTab = _fac;
	}
}
function HideDepBox() {
	var _fad = document.getElementsByName("depbox");
	for (var i = 0; i < _fad.length; i++) {
		_fad[i].className = "hide";
	}
}
function setChatStyle(str) {
	if (str == "footer") {
		var _fb0 = document.getElementById("zBottomFooterId");
		if (_fb0) {
			_fb0.style.position = "fixed";
			_fb0.style.bottom = "0px";
			_fb0.style.width = "100%";
		}
	} else {
		if (str == "header") {
			var _fb1 = document.getElementById("zTopHeaderId");
			if (_fb1) {
				_fb1.style.position = "fixed";
				_fb1.style.top = "0px";
				_fb1.style.right = "0px";
				_fb1.style.left = "5px";
				_fb1.style.width = "100%";
				_fb1.style.backgroundColor = "#ffffff";
			}
		} else {
			if (str == "tabheader") {
				var _fb2 = document.getElementById("zTopTabHeaderId");
				if (_fb2) {
					_fb2.style.position = "fixed";
					_fb2.style.top = "60px";
					_fb2.style.width = "100%";
					_fb2.style.backgroundColor = "#ffffff";
				}
			}
		}
	}
}
function unSetChatStyle() {
	window.scrollTo(0, 0);
	var _fb3 = document.getElementById("zBottomFooterId");
	var _fb4 = document.getElementById("zTopHeaderId");
	var _fb5 = document.getElementById("zTopTabHeaderId");
	if (_fb3) {
		_fb3.style.position = "";
	}
	if (_fb4) {
		_fb4.style.position = "";
	}
	if (_fb5) {
		_fb5.style.position = "";
	}
}
function selectTasks(tlId) {
	var _fb7 = document.getElementById("taskSelected_" + tlId);
	var _fb8 = document.getElementById("tlcheck_" + tlId);
	var _fb9 = 0;
	var _fba = new Array();
	var btn = "selTask_" + tlId;
	var _fbc = document.getElementsByName("selTask_" + tlId);
	var _fbd = _fbc.length;
	for (var i = 0,
	j = 0; i < _fbd; i++) {
		if (_fbc[i].checked) {
			if (jQuery.inArray(tlId, selTaskCheck) == -1) {
				Effect.SlideDown("todoactionslide_" + tlId);
				selTaskCheck[selTaskCheck.length] = tlId;
			}
			var _fc0 = new String(_fbc[i].id);
			var dd = _fc0.split("_");
			_fba[j++] = dd[1];
		}
	}
	bulk_issue_id = _fba;
	_fb9 = _fba.length;
	_fb7.innerHTML = _fb9;
	if (_fb9 < _fbd) {
		_fb8.checked = false;
	}
	if (_fb9 == _fbd) {
		_fb8.checked = true;
	}
	if (_fb9 == 0) {
		selTaskCheck = jQuery.grep(selTaskCheck,
		function(_fc2) {
			return _fc2 != tlId;
		});
		Effect.SlideUp("todoactionslide_" + tlId);
	}
}
function selectAllTasks(tlId) {
	var _fc4 = document.getElementsByName("selTask_" + tlId);
	var _fc5 = document.getElementById("tlcheck_" + tlId);
	var _fc6 = document.getElementById("taskSelected_" + tlId);
	var _fc7 = 0;
	if (_fc5.checked == true) {
		for (var i = 0; i < _fc4.length; i++) {
			var div = document.getElementById(_fc4[i].id);
			if (!div.disabled) {
				div.checked = true;
				_fc7 = _fc7 + 1;
			}
		}
		selTaskCheck[selTaskCheck.length] = tlId;
		_fc6.innerHTML = _fc7;
	} else {
		for (var i = 0; i < _fc4.length; i++) {
			var div = document.getElementById(_fc4[i].id);
			div.checked = false;
		}
		selTaskCheck = jQuery.grep(selTaskCheck,
		function(_fca) {
			return _fca != tlId;
		});
		_fc6.innerHTML = _fc7;
		Effect.SlideUp("todoactionslide_" + tlId);
	}
}
function bindMoT(rId) {
	var obj = document.getElementById(rId);
	if (obj) {
		obj.onmouseover = lvTredFn;
		obj.onmouseout = lvTredFn;
	}
}
function lvTredFn(e) {
	var evt = e || window.event;
	var _fcf = evt.target || evt.srcElement;
	while (_fcf.nodeName.toLowerCase() !== "tr") {
		_fcf = _fcf.parentNode;
	}
	switch (evt.type) {
	case "mouseover":
		dropRofn(_fcf, _fcf.id);
		break;
	case "mouseout":
		dropRoutfn(_fcf, _fcf.id);
		break;
	}
}
function dropRofn(rEle, nId) {
	rEle.className = "tdover";
	var _fd2 = "taskaction-link_" + (nId.split("_")[1]);
	if (nId.indexOf("tl_") != -1) {
		_fd2 = "tlaction-link_" + (nId.split("_")[1]);
	}
	if (nId.indexOf("bug_") != -1) {
		_fd2 = "bugactdiv_" + (nId.split("_")[1]);
	}
	if (nId.indexOf("delmilefeat_") != -1) {
		_fd2 = "milediv_" + (nId.split("_")[1]);
	}
	if (nId.indexOf("clpId_") != -1) {
		_fd2 = "cldiv_" + (nId.split("_")[1]);
	}
	if (nId.indexOf("proj_") != -1) {
		_fd2 = "projdiv_" + (nId.split("_")[1]);
	}
	if (nId.indexOf("tasklogid_") != -1) {
		_fd2 = "timesheetaction_" + (nId.split("_")[1]);
	}
	if (nId.indexOf("docrow_") != -1) {
		_fd2 = "docdiv_" + (nId.split("_")[1]);
	}
	if (document.getElementById(_fd2)) {
		document.getElementById(_fd2).style.visibility = "visible";
	}
}
function dropRoutfn(rEle, nId) {
	rEle.className = "tdout";
	var _fd5 = "taskaction-link_" + (nId.split("_")[1]);
	if (nId.indexOf("tl_") != -1) {
		_fd5 = "tlaction-link_" + (nId.split("_")[1]);
	}
	if (nId.indexOf("bug_") != -1) {
		_fd5 = "bugactdiv_" + (nId.split("_")[1]);
	}
	if (nId.indexOf("delmilefeat_") != -1) {
		_fd5 = "milediv_" + (nId.split("_")[1]);
	}
	if (nId.indexOf("clpId_") != -1) {
		_fd5 = "cldiv_" + (nId.split("_")[1]);
	}
	if (nId.indexOf("proj_") != -1) {
		_fd5 = "projdiv_" + (nId.split("_")[1]);
	}
	if (nId.indexOf("docrow_") != -1) {
		_fd5 = "docdiv_" + (nId.split("_")[1]);
	}
	if (nId.indexOf("tasklogid_") != -1) {
		_fd5 = "timesheetaction_" + (nId.split("_")[1]);
	}
	if (document.getElementById(_fd5)) {
		document.getElementById(_fd5).style.visibility = "hidden";
	}
}
function bindMoTDep(rId) {
	var obj = document.getElementById(rId);
	if (obj) {
		obj.onmouseover = lvTredFnDep;
		obj.onmouseout = lvTredFnDep;
	}
}
function lvTredFnDep(e) {
	var evt = e || window.event;
	var _fda = evt.target || evt.srcElement;
	while (_fda.nodeName.toLowerCase() !== "tr") {
		_fda = _fda.parentNode;
	}
	switch (evt.type) {
	case "mouseover":
		dropRofnDep(_fda, _fda.id);
		break;
	case "mouseout":
		dropRoutfnDep(_fda, _fda.id);
		break;
	}
}
function dropRofnDep(rEle, nId) {
	if (rEle.getAttribute("name") == "deprow") {
		rEle.className = "tdover";
	}
	var _fdd = "dropicon_" + (nId.split("_")[1]);
	if (document.getElementById(_fdd)) {
		document.getElementById(_fdd).style.visibility = "visible";
	}
}
function dropRoutfnDep(rEle, nId) {
	if (rEle.getAttribute("name") == "deprow") {
		rEle.className = "tdout";
	}
	var _fe0 = "dropicon_" + (nId.split("_")[1]);
	if (document.getElementById(_fe0)) {
		if (document.getElementById(_fe0).className != "indIconC") {
			document.getElementById(_fe0).style.visibility = "hidden";
		}
	}
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
function ShowGenTabRow(_fe7) {
	var id = document.getElementById(_fe7);
	if (id) {
		id.className = id.className + "tablerowgroup";
	}
}
function changeRowsStyle(id, id1, id2, stat) {
	if (stat == "inline") {
		ShowGenTabRow(id);
		ShowGenTabRow(id1);
		ShowGenTabRow(id2);
	}
	if (stat == "hide") {
		Hide(id);
		Hide(id1);
		Hide(id2);
	}
}
function validateBulkAddUserGapps() {
	var cnt = document.addGappsUser.count.value;
	var _fee = document.addGappsUser.seluserroles.value;
	var _fef = document.addGappsUser.seluseremail.value;
	counter = 0;
	var j = false;
	for (i = 0; i < cnt; i++) {
		var _ff1 = "usercheckbox" + i;
		var _ff2 = "userrole" + i;
		var _ff3 = "useremailid" + i;
		if (eval("document.addGappsUser." + _ff2) && eval("document.addGappsUser." + _ff3) && eval("document.addGappsUser." + _ff1)) {
			var _ff4 = eval("document.addGappsUser." + _ff2 + ".value");
			var _ff5 = eval("document.addGappsUser." + _ff3 + ".value");
			var _ff6 = eval("document.addGappsUser." + _ff1 + ".checked");
			if (_ff6) {
				if (counter == 0) {
					_fee = _ff4;
					_fef = _ff5;
				} else {
					_fee = _fee + "," + _ff4;
					_fef = _fef + "," + _ff5;
				}
				j = true;
				counter = counter + 1;
			}
		}
	}
	document.addGappsUser.seluserroles.value = _fee;
	document.addGappsUser.seluseremail.value = _fef;
	if (j) {
		return true;
	} else {
		i18n.getJSAlertValue(Utils.zuid, "zp.gapps.nouserselect", null,
		function(mesg) {
			alert(mesg);
		});
		return false;
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
function disableCheckBox() {
	var _1006 = document.getElementsByName("enabledcheckbox");
	for (var i = 0; i < _1006.length; i++) {
		_1006[i].setAttribute("disabled", true);
	}
}
function enableCheckBox(_1008) {
	var _1009 = document.getElementsByName("enabledcheckbox");
	for (var i = 0; i < _1009.length; i++) {
		_1009[i].removeAttribute("disabled");
	}
}
function recCheckBox(divId, _100c) {
	var flag = document.getElementById(divId).checked;
	if (divId == _100c && flag) {
		ShowGenInline("rem_rec");
	} else {
		if (divId == _100c && !flag) {
			Hide("rem_rec");
		}
	}
}
function checkGTasks(tlid, _100f, _1010) {
	if (document.getElementById(_1010).checked) {
		jQuery("#" + tlid + " [id='" + _100f + "']").attr("checked", "checked");
	} else {
		jQuery("#" + tlid + " [id='" + _100f + "']").attr("checked", null);
	}
}
function ganttChartLoader(_1011, _1012) {
	var _1013 = jQuery("#taskganttchart").offset().top;
	if (is_ie) {
		document.getElementById("taskganttchart").style.height = document.documentElement.clientHeight - _1013 - 35 - 10 + "px";
	} else {
		document.getElementById("taskganttchart").style.height = window.innerHeight - _1013 - 35 - 10 + "px";
	}
	var _1014 = document.getElementById("encryptedProjId").innerHTML;
	var _1015 = document.getElementById("encryptedZoid").innerHTML;
	var _1016 = document.getElementById("encryptedcoid").innerHTML;
	var _1017 = document.getElementById("projIdfromGanttView").innerHTML;
	var _1018 = document.getElementById("viewtype").innerHTML;
	var _1019 = document.getElementById("username").innerHTML;
	if (_1019 == "0") {
		_1019 = "all";
	}
	var _101a = document.getElementById("plottype").innerHTML;
	var _101b = document.getElementById("plotperiod").innerHTML;
	var _101c = document.getElementById("filename").innerHTML;
	var _101d = 0;
	var _101e = "by1month";
	if (document.getElementById("chartscale")) {
		_101d = parseInt(document.getElementById("chartscale").innerHTML);
		_101e = "morethan1month";
	}
	var _101f = document.getElementById("lastenddate").innerHTML.split("-");
	var gantt = new GanttChart(0, _101e, new Date(_101f[2], _101f[0] - 1, _101f[1]));
	gantt.setImagePath(Utils.domainappender + "images/");
	if (document.getElementById("isEditable").innerHTML == "true") {
		gantt.setEditable(true);
	} else {
		gantt.setEditable(false);
	}
	gantt.create("taskganttchart");
	var pid = _1017;
	var _1022 = "genreport.do";
	if (_1017 == "-1") {
		_1022 = "mytasksreport.do";
		pid = "AllProjects";
		gantt.showDescTask(true, "pn");
	} else {
		gantt.showDescTask(true, "o");
	}
	gantt.loadData(Utils.domainappender + "jsp/project/mainGanttLoader.jsp?projid=" + _1014 + "&zoid=" + _1015 + "&coid=" + _1016 + "&username=" + _1019 + "&statustype=" + _101a + "&filename=" + _101c + "&viewtype=" + _1018, true, true);
	if (_1011 != null) {
		gantt.oData.scrollLeft = _1011;
		Utils.ganttscrolltype = "onEdit";
		gantt.oData.scrollTop = _1012;
	}
	if (gantt.arrProjects[0].EndDate > new Date()) {
		var _1023 = parseInt((new Date() - gantt.startDate) / (24 * 60 * 60000)) * 24 + 12;
		var _1024 = document.getElementById("taskpanel").style.height;
		var _1025 = document.createElement("div");
		_1025.style.cssText = "background-image: url('" + Utils.domainappender + "images/arr.gif'); height: " + _1024 + "; width: 1px;margin-left:" + _1023 + "px;position:absolute;";
		document.getElementById("taskpanel").appendChild(_1025);
	}
	document.getElementById(pid + "_projbar").style.display = "none";
	document.getElementById(pid + "_projname").style.display = "none";
	var _1026 = "click";
	gantt.attachEvent("onTaskClick",
	function(task) {
		if (_1026 != "drag") {
			var csrf = document.getElementById("csrfparam").innerHTML;
			var _1029 = task.getId();
			var tId = _1029;
			if (_1029.indexOf("actualclosedbar") != -1 || _1029.indexOf("barbeyondrange") != -1) {
				_1029 = _1029.split("_")[0];
			}
			if (_1017 == "-1" && tId.indexOf("actualclosedbar") == -1) {
				_1017 = task.TaskInfo.projectid;
			} else {
				if (_1017 == "-1" && tId.indexOf("actualclosedbar") != -1) {
					_1017 = gantt.arrProjects[0].getTaskById(_1029).TaskInfo.projectid;
				}
			}
			if (_1018 == "milereports") {
				getSearchMiles(csrf, _1017, new Date().getTime(), "active", _1029);
			} else {
				var tlId = task.TaskInfo.taskListId;
				getSearchTaskList(csrf, _1017, new Date().getTime(), "active", "", "task_" + _1029, tlId);
			}
		}
		_1026 = "click";
	});
	var _102c = 0;
	var _102d = 0;
	if (is_ie) {
		var _102c = document.documentElement.clientWidth;
		var _102d = document.documentElement.clientHeight;
	} else {
		var _102c = window.innerWidth;
		var _102d = window.innerHeight;
	}
	window.onresize = function(event) {
		var _102f = 0;
		var _1030 = 0;
		if (is_ie) {
			_102f = document.documentElement.clientWidth;
			_1030 = document.documentElement.clientHeight;
		} else {
			_102f = window.innerWidth;
			_1030 = window.innerHeight;
		}
		if (_102f > _102c) {
			var diff = _102f - _102c;
			_102c = _102f;
			document.getElementById("mainTaskPanel").style.width = parseInt(document.getElementById("mainTaskPanel").style.width) + diff + "px";
			if (parseInt(document.getElementById("taskpanel").style.width) < parseInt(document.getElementById("mainTaskPanel").style.width)) {
				document.getElementById("taskpanel").style.width = document.getElementById("mainTaskPanel").style.width;
			}
			document.getElementById("timePanel").style.width = document.getElementById("mainTaskPanel").style.width;
		}
		if (_1030 > _102d) {
			var diff = _1030 - _102d;
			_102d = _1030;
			document.getElementById("mainTaskPanel").style.height = parseInt(document.getElementById("mainTaskPanel").style.height) + diff + "px";
			gantt.panelNames.style.height = parseInt(document.getElementById("mainTaskPanel").style.height) - 16 + "px";
		}
	};
	if (document.getElementById("taskpanel").offsetHeight < document.getElementById("mainTaskPanel").offsetHeight) {
		document.getElementById("taskpanel").style.height = document.getElementById("mainTaskPanel").offsetHeight + "px";
	}
	gantt.attachEvent("onTaskEndResize",
	function(task) {
		ShowGenInline("ajax_load_tabforGantt");
		var _1033 = task.getDuration();
		var _1034 = task.getFinishDate();
		var _1035 = _1033 / 24;
		var _1036 = task.getId();
		var _1037 = _1017;
		if (_1017 == "-1") {
			_1037 = task.TaskInfo.projectid;
		}
		var _1038 = task.getEST();
		var csrf = document.getElementById("csrfparam").innerHTML;
		var day = _1034.getDate();
		var _103b = document.getElementById("chartstart").innerHTML;
		var _103c = document.getElementById("chartend").innerHTML;
		var _103d = appendzero(_1034.getMonth() + 1) + "-" + appendzero(day) + "-" + _1034.getFullYear();
		var url = "";
		var _103f = "";
		if (task.TaskInfo.actualstartdate != null) {
			var _1040 = task.TaskInfo.actualstartdate;
			_103f = _1040.split(".")[1] + "-" + _1040.split(".")[0] + "-" + _1040.split(".")[2];
		} else {
			_103f = appendzero(_1038.getMonth() + 1) + "-" + appendzero(_1038.getDate()) + "-" + _1038.getFullYear();
		}
		if (_1018 == "milereports") {
			url = Utils.contPath + "/updatemilestonedate.do?projId=" + _1037 + "&mid=" + _1036 + "&milestonedate=" + _103d + "&mstartdate=" + _103f + "&" + csrf;
		} else {
			url = Utils.contPath + "/deptaskupdate.do?projId=" + _1037 + "&pId=" + _1017 + "&taskid=" + _1036 + "&enddate=" + _103d + "&projenddate=" + _103c + "&projectstartdate=" + _103b + "&username=" + _1019 + "&status=" + _101a + "&plotperiod=" + _101b + "&toupdate=enddate&edittype=ganttview&" + csrf;
		}
		ajaxSubmitPage(url, "", "");
	});
	gantt.attachEvent("onTaskEndDrag",
	function(task) {
		_1026 = "drag";
		ShowGenInline("ajax_load_tabforGantt");
		var _1042 = task.getDuration();
		var _1043 = task.getFinishDate();
		var _1044 = _1042 / 24;
		var _1045 = task.getId();
		var _1046 = _1017;
		if (_1017 == "-1") {
			_1046 = task.TaskInfo.projectid;
		}
		var _1047 = task.getEST();
		var csrf = document.getElementById("csrfparam").innerHTML;
		var day = _1047.getDate();
		var _104a = document.getElementById("chartstart").innerHTML;
		var _104b = document.getElementById("chartend").innerHTML;
		var _104c = "";
		if (task.TaskInfo.actualstartdate != null) {
			var _104d = task.TaskInfo.actualstartdate;
			_104c = _104d.split(".")[1] + "-" + _104d.split(".")[0] + "-" + _104d.split(".")[2];
		} else {
			_104c = appendzero(_1047.getMonth() + 1) + "-" + appendzero(day) + "-" + _1047.getFullYear();
		}
		var _104e = "";
		if (task.TaskInfo.actualstartdate == null && task.TaskInfo.actualenddate == null && _1042 % 24 == 0) {
			_104e = "&duration=" + _1044;
		}
		if (_1018 == "milereports") {
			var _104f = appendzero(_1043.getMonth() + 1) + "-" + appendzero(_1043.getDate()) + "-" + _1043.getFullYear();
			url = Utils.contPath + "/updatemilestonedate.do?projId=" + _1046 + "&mid=" + _1045 + "&milestonedate=" + _104f + "&mstartdate=" + _104c + "&" + csrf;
		} else {
			var url = Utils.contPath + "/deptaskupdate.do?projId=" + _1046 + "&pId=" + _1017 + "&taskid=" + _1045 + "&startdate=" + _104c + "&projenddate=" + _104b + "&projectstartdate=" + _104a + "&username=" + _1019 + "&status=" + _101a + "&plotperiod=" + _101b + "&toupdate=startdate&edittype=ganttview&" + csrf + _104e;
		}
		ajaxSubmitPage(url, "", "");
	});
	var _1051 = 0;
	if (document.getElementById("totaltasks")) {
		_1051 = parseInt(document.getElementById("totaltasks").innerHTML);
	}
	if (document.getElementById("compressedview") && document.getElementById("mainTaskPanel").offsetWidth > document.getElementById("taskpanel").offsetWidth) {
		document.getElementById("compressedview").style.display = "none";
	}
	if (_1011 != null) {
		jQuery("#PRINTER").unbind();
		jQuery("#PDFGEN").unbind();
	}
	var _1052 = function(event) {
		ShowGenInline("ajax_load_tab");
		var _1054 = document.createElement("div");
		_1054.align = "center";
		_1054.style.width = "99.8%";
		_1054.setAttribute("id", "divforchartprint");
		var _1055 = document.getElementById("printdivid");
		_1055.appendChild(_1054);
		var _1056 = 0;
		if (document.getElementById("monthspan")) {
			_1056 = document.getElementById("monthspan").innerHTML;
			Utils.projbarwidth = (parseInt(_1056) * _101d * 24) + 24;
			if (Utils.projbarwidth < (31 * 24) + 24) {
				Utils.projbarwidth = (31 * 24) + 24;
			}
		} else {
			Utils.projbarwidth = (31 * 24) + 24;
		}
		document.getElementById("divforchartprint").style.height = document.getElementById("taskganttchart").style.height;
		var _1057 = null;
		var _1058 = 0;
		if (_101a == "open") {
			_1058 = 25;
		}
		if (_101a == "closed") {
			_1058 = 12;
			_1057 = gantt;
		}
		if (_101a == "all") {
			_1058 = 15;
		}
		var _1059 = new GanttChart(_101d, _101e, new Date(_101f[2], _101f[0] - 1, _101f[1]), _1057);
		_1059.chartfor = "print";
		_1059.setImagePath(Utils.domainappender + "images/");
		_1059.setEditable(false);
		_1059.create("divforchartprint");
		if (_1017 == "-1") {
			_1059.showDescTask(true, "pn");
		} else {
			_1059.showDescTask(true, "o");
		}
		var csrf = document.getElementById("csrfparam").innerHTML;
		var _105b = document.getElementById("printdetails").innerHTML;
		var j = 1;
		var _105d = parseInt(_1051 / _1058);
		if (_1051 % _1058 > 0) {
			_105d = _105d + 1;
		}
		if (_101b == "selmonth") {
			var _105e = document.getElementById("chartduration").innerHTML;
			var _105f = document.getElementById("repmonth").innerHTML;
			var _1060 = document.getElementById("repyear").innerHTML;
			ajaxShowImgPage(Utils.contPath + "/" + _1022 + "?projId=" + _1017 + "&plottype=" + _101b + "&toview=" + _1018 + "&username=" + _1019 + "&plot=" + _101a + "&repmonth=" + _105f + "&repyear=" + _1060 + "&duration=" + _105e + "&chartfor=print&" + csrf, null, "", _105d, _1059, _1014, _1015, _1016, _1019, _101a, _101c, _1018, event.data.mode);
		} else {
			ajaxShowImgPage(Utils.contPath + "/" + _1022 + "?projId=" + _1017 + "&plottype=" + _101b + "&toview=" + _1018 + "&username=" + _1019 + "&plot=" + _101a + "&chartfor=print&" + csrf, null, "", _105d, _1059, _1014, _1015, _1016, _1019, _101a, _101c, _1018, event.data.mode);
		}
	};
	jQuery("#PRINTER").bind("click", {
		mode: "print"
	},
	_1052);
	jQuery("#PDFGEN").bind("click", {
		mode: "pdf"
	},
	_1052);
	if (_1011 == null) {
		var _1061 = function(event) {
			var cvid = document.getElementById("compressedview");
			if (event.data.action == "loading") {
				ShowGenInline("ajax_load_tab");
				var _1064 = 0;
				if (document.getElementById("monthspan")) {
					_1064 = document.getElementById("monthspan").innerHTML;
				}
				var _1065 = _101d;
				if (((_101d + 1) * _1064 * 24) + 24 < document.getElementById("mainTaskPanel").offsetWidth && _101d != 0) {
					_1065 = _101d + 1;
				}
				Hide("taskganttchart");
				document.getElementById("compressviewid").style.height = document.getElementById("taskganttchart").style.height;
				var _1066 = new GanttChart(_1065, _101e, new Date(_101f[2], _101f[0] - 1, _101f[1]));
				_1066.setImagePath(Utils.domainappender + "images/");
				_1066.setEditable(false);
				_1066.create("compressviewid");
				if (_1017 == "-1") {
					_1066.showDescTask(true, "pn");
				} else {
					_1066.showDescTask(true, "o");
				}
				_1066.loadData(Utils.domainappender + "jsp/project/mainGanttLoader.jsp?projid=" + _1014 + "&zoid=" + _1015 + "&coid=" + _1016 + "&username=" + _1019 + "&statustype=" + _101a + "&filename=" + _101c + "&viewtype=" + _1018, true, true);
				_1066.attachEvent("onTaskClick",
				function(task) {
					var csrf = document.getElementById("csrfparam").innerHTML;
					var _1069 = task.getId();
					var tId = _1069;
					if (_1069.indexOf("actualclosedbar") != -1) {
						_1069 = _1069.split("_")[0];
					}
					if (_1017 == "-1" && tId.indexOf("actualclosedbar") == -1) {
						_1017 = task.TaskInfo.projectid;
					} else {
						if (_1017 == "-1" && tId.indexOf("actualclosedbar") != -1) {
							_1017 = gantt.arrProjects[0].getTaskById(_1069).TaskInfo.projectid;
						}
					}
					if (_1018 == "milereports") {
						getSearchMiles(csrf, _1017, new Date().getTime(), "active", _1069);
					} else {
						var tlId = task.TaskInfo.taskListId;
						getSearchTaskList(csrf, _1017, new Date().getTime(), "active", "", "task_" + _1069, tlId);
					}
				});
				_1066.arrProjects[0].projectItem[0].style.display = "none";
				_1066.arrProjects[0].projectNameItem.style.display = "none";
				if (_1066.oData.firstChild.offsetHeight < _1066.oData.offsetHeight) {
					_1066.oData.firstChild.style.height = _1066.oData.offsetHeight + "px";
				}
				jQuery("#compressedview").unbind();
				jQuery("#compressedview").bind("click", {
					action: "showhide"
				},
				_1061);
				if (_101a == "open" || _101a == "all") {
					i18n.getJSAlertValue(Utils.zuid, "zp.newgantt.editableview", null,
					function(mesg) {
						cvid.innerHTML = mesg;
					});
				}
				if (_101a == "closed") {
					i18n.getJSAlertValue(Utils.zuid, "zp.newgantt.defaultview", null,
					function(mesg) {
						cvid.innerHTML = mesg;
					});
				}
				Hide("ajax_load_tab");
			} else {
				if (event.data.action == "showhide") {
					if (document.getElementById("taskganttchart").className == "hide") {
						i18n.getJSAlertValue(Utils.zuid, "zp.newgantt.fittowidth", null,
						function(mesg) {
							cvid.innerHTML = mesg;
						});
						ShowGenBlock("taskganttchart");
						Hide("compressviewid");
					} else {
						if (document.getElementById("compressviewid").className = "hide") {
							if (_101a == "open" || _101a == "all") {
								i18n.getJSAlertValue(Utils.zuid, "zp.newgantt.editableview", null,
								function(mesg) {
									cvid.innerHTML = mesg;
								});
							}
							if (_101a == "closed") {
								i18n.getJSAlertValue(Utils.zuid, "zp.newgantt.defaultview", null,
								function(mesg) {
									cvid.innerHTML = mesg;
								});
							}
							ShowGenInline("compressviewid");
							Hide("taskganttchart");
						}
					}
				}
			}
		};
		jQuery("#compressedview").bind("click", {
			action: "loading"
		},
		_1061);
	}
	Hide("ajax_load_tabforGantt");
}
function sendActionPdf(url) {
	document.pdfformname.action = url;
	document.pdfformname.submit();
}
function appendzero(n) {
	return n < 10 ? "0" + n: n;
}
function synctaskDetails() {
	var _1073 = document.getElementById("newtask");
	var _1074 = document.getElementById("updatetask");
	var _1075 = document.getElementById("completedtask");
	var _1076 = document.getElementById("reopentask");
	if (_1073) {
		ShowGenInline("newtask");
	}
	if (_1074) {
		ShowGenInline("updatetask");
	}
	if (_1075) {
		ShowGenInline("completedtask");
	}
	if (_1076) {
		ShowGenInline("reopentask");
	}
	Hide("moredetails");
	ShowGenInline("lessdetails");
}
function synctaskHideDetails() {
	var _1077 = document.getElementById("newtask");
	var _1078 = document.getElementById("updatetask");
	var _1079 = document.getElementById("completedtask");
	var _107a = document.getElementById("reopentask");
	if (_1077) {
		Hide("newtask");
	}
	if (_1078) {
		Hide("updatetask");
	}
	if (_1079) {
		Hide("completedtask");
	}
	if (_107a) {
		Hide("reopentask");
	}
	ShowGenInline("moredetails");
	Hide("lessdetails");
}
function showDepForm(divId, _107c, _107d, _107e, _107f, _1080, _1081, _1082, _1083, _1084, _1085, _1086, _1087, _1088) {
	if (_107f != "depdurform") {
		ShowHideInline(_107d, divId);
		jQuery("#" + _107d).appendTo("#" + _107c);
	}
	eval("document." + _107f + ".projId").value = _1082;
	eval("document." + _107f + ".taskid").value = _107e;
	if (_1083 == "duration" || _1083 == "enddate") {
		eval("document." + _107f + ".startdate").value = _1085;
		eval("document." + _107f + ".taskcount").value = _1087;
	}
	if (_1083 == "duration" || _1083 == "startdate") {
		eval("document." + _107f + ".enddate").value = _1086;
	}
	if (_1083 == "startdate") {
		eval("document." + _107f + ".duration").value = _1084;
		eval("document." + _107f + ".taskcount").value = _1087;
	}
	if (_1083 == "dependency") {
		eval("document." + _107f + ".taskcount").value = _1087;
	}
	if (_1081 == "&nbsp;") {
		_1081 = "";
	}
	eval("document." + _107f + "." + _1080).value = _1081;
	eval("document." + _107f + "." + _1080 + ".focus()");
	depaction_id = _107e;
	if (_107f != "depdurform") {
		deptextboxid = _1088;
	}
}
function depFormHide(_1089, _108a, _108b, tdid, _108d, _108e) {
	ShowGenInline(_1089 + depaction_id);
	Hide(_108a);
	changeClassName(_108b + depaction_id, tdid + depaction_id, _108d, _108e);
}
function showDepActionList(_108f, _1090, _1091, _1092) {
	document.getElementById("quickActionListView").style.display = "block";
	var _1093 = jQuery("#" + _1090).position().left;
	var _1094 = jQuery("#" + _1090).position().top;
	jQuery("#quickActionListView").css("left", _1093);
	jQuery("#quickActionListView").css("top", _1094);
	taskid_dep = _1091;
	tlid_dep = _1092;
}
function checkDepDiv(_1095, _1096) {
	var _1097 = jQuery(_1095).parents("div");
	for (var i = 0; i < _1097.length; i++) {
		if (_1097[i].className == "calendar") {
			_1096 = false;
		}
	}
	return _1096;
}
function isScrollBottom() {
	var _1099 = jQuery(document).height();
	var _109a = jQuery(window).height() + jQuery(window).scrollTop();
	var _109b = _1099 - _109a;
	if (_109b < 20 && _109b > 0) {
		return true;
	} else {
		return false;
	}
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
function fieldToggle(_109d, pid, csrf) {
	jQuery("#common_table tbody tr td:nth-child(" + _109d + ")").toggle();
	var _10a0 = document.fieldsvisibility;
	var _10a1 = _10a0.fields;
	var _10a2 = {};
	fields = "11";
	lastField = "";
	for (var i = 0,
	l = _10a1.length; i < l; i++) {
		if (i != 3 && i != 7) {
			fields = fields + ((_10a1[i].checked) ? 1 : 0);
		} else {
			var _10a5 = (_10a1[i].checked) ? 1 : 0;
			lastField = lastField + _10a5;
		}
	}
	fields = fields + lastField;
	var url = Utils.contPath + "/updatetaskfield.do?projId=" + pid + "&" + csrf + "&fields=" + fields;
	ajaxSendRequest(url, csrf, pid);
}
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
function setTRShowHide(divId) {
	var style = document.getElementById(divId).className;
	var _10af = "addtodo";
	var arId = "larid";
	if (divId.indexOf("recurform_") != -1) {
		_10af = "updatetaskdiv";
		arId = "qcarrowb";
	}
	var elHgt = jQuery("#" + _10af).height();
	if (style == "hide") {
		ShowGenBlock(divId);
	} else {
		Hide(divId);
	}
	var _10b2 = jQuery("#" + _10af).height();
	var _10b3 = (jQuery("#" + arId)[0].style.top).split("px")[0];
	var _10b4 = _10b3 - (_10b2 - elHgt);
	jQuery("#" + arId).css({
		"top": _10b4 + "px"
	});
}
function focusDiv(divId) {
	if (document.getElementById(divId)) {
		document.getElementById(divId).focus();
	}
}
function setPosDiv(divId, _10b7) {
	var pos = jQuery("#" + divId).offset();
	var _10b9 = pos.top;
	if ("customcolumn" == _10b7) {
		var _10ba = pos.left - 134;
		jQuery("#" + _10b7).css({
			"top": _10b9 + "px",
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
function updateDocaction(url, divId) {
	var id = new Array();
	var check = document.getElementsByName("doccheckbox");
	for (var i = 0,
	j = 0; i < check.length; i++) {
		if (check[i].checked) {
			checked = true;
			var _10c1 = new String(check[i].id);
			var dd = _10c1.split("_");
			id[j++] = dd[1];
		}
	}
	if (id.length > 0) {
		var _10c3 = document.getElementById("folderid").value;
		url = url + "&folderid=" + _10c3 + "&docids=" + id;
		ajaxShowPage(url, "projectcontent");
	} else {
		return false;
	}
}
function logOptionSelect(count, title, _10c6, id) {
	document.getElementById("taskval_" + count).value = title;
	document.getElementById("tsheettype_" + count).value = _10c6;
	if (id) {
		document.getElementById("optionid_" + count).value = id;
	}
	jQuery("#logtaskbug").remove();
}
function AddWeeklyLogRow(trObj, i) {
	trObj = "<tr id=\"weeklogtr_" + i + "\" class=\"weeklogtr\">" + trObj + "</tr>";
	jQuery("#weeklogbody").append(trObj);
	jQuery("[class='selectlogitem']")[i].setAttribute("id", "selectlogitem_" + i);
	document.getElementsByName("taskval")[i].setAttribute("id", "taskval_" + i);
	document.getElementsByName("tsheettype")[i].setAttribute("id", "tsheettype_" + i);
	document.getElementsByName("optionid")[i].setAttribute("id", "optionid_" + i);
	jQuery("[class='addlogtask']")[i].setAttribute("id", "addlogtask_" + i);
	document.getElementsByName("othertask")[i].setAttribute("id", "othertask_" + i);
	document.getElementsByName("selecttask")[i].setAttribute("id", "selecttask_" + i);
	document.getElementsByName("loggentask")[i].setAttribute("id", "loggentask_" + i);
	document.getElementsByName("projId")[i].setAttribute("id", "logproject_" + i);
	jQuery("[class='UsernameClass']")[i].setAttribute("id", "username_" + i);
	jQuery("[class='slno']")[i].innerHTML = i;
}
function calcTotalLogHours(_10ca) {
	var _10cb = /^\d*[\:\.]{1}\d+$/;
	var _10cc = /^\d+$/;
	var lh = document.getElementsByName("loghours");
	var _10ce = 0;
	var _10cf = jQuery("[class='WeekTotal']");
	for (var i = 7; i < lh.length; i++) {
		var _10d1 = 0;
		var total = 0;
		if (lh[i] == _10ca) {
			var cnt = 7 * parseInt(i / 7);
			for (var j = cnt; j <= cnt + 6; j++) {
				var _10d5 = document.getElementsByName("loghours")[j].value;
				if (trim(_10d5).length != 0 && (_10cb.test(_10d5) || _10cc.test(_10d5))) {
					var mins = 0;
					var hrs = 0;
					if (_10cc.test(_10d5)) {
						hrs = parseFloat(_10d5);
						mins = 0;
					} else {
						var _10d8 = _10d5;
						_10d5 = _10d5.replace(":", ".");
						var min = "0";
						if (_10d5.indexOf(".") != -1) {
							min = _10d5.substring(_10d5.lastIndexOf(".") + 1, _10d5.length);
							if (min == "") {
								min = "0";
							}
						}
						var hours = parseFloat(_10d5);
						_10d5 = hours.toString();
						var hr = _10d5;
						if (_10d5.indexOf(".") != -1) {
							hr = _10d5.substring(0, _10d5.lastIndexOf("."));
						}
						_10d5 = hr + "." + min;
						hrs = parseFloat(_10d5.substring(0, _10d5.lastIndexOf(".")));
						mins = parseFloat(_10d5.substring(_10d5.lastIndexOf(".") + 1, _10d5.length));
						if (_10d8.indexOf(":") == -1) {
							var _10dc = mins.toString().length;
							mins = (mins * Math.pow(10, 2 - _10dc) * 60) / 100;
							mins = Math.round(mins);
						}
					}
					_10d1 = hrs * 60 + mins + _10d1;
				}
			}
			var _10dd = parseInt(_10d1 / 60) + "";
			var _10de = parseInt(_10d1 % 60) + "";
			if (parseInt(_10d1 % 60) < 10) {
				_10de = "0" + _10de;
			}
			_10cf[parseInt(i / 7)].innerHTML = _10dd + ":" + _10de;
			var _10df = /^\d+\:[0-5][0-9]$/;
			var _10e0 = _10d1;
			for (var k = 1; k < _10cf.length; k++) {
				var _10e2 = trim(_10cf[k].innerHTML);
				if (_10df.test(_10e2) && k != parseInt(i / 7)) {
					var hrs = parseFloat(_10e2.substring(0, _10e2.lastIndexOf(":")));
					var mins = parseFloat(_10e2.substring(_10e2.lastIndexOf(":") + 1, _10e2.length));
					_10e0 = parseInt(hrs) * 60 + parseInt(mins) + _10e0;
				}
			}
			_10dd = parseInt(_10e0 / 60) + "";
			_10de = parseInt(_10e0 % 60) + "";
			if (parseInt(_10e0 % 60) < 10) {
				_10de = "0" + _10de;
			}
			document.getElementById("totalhours").innerHTML = _10dd + ":" + _10de;
			return false;
		}
	}
}
function passCalendarValues() {
	if (document.addLogHours && document.getElementById("userfilter") && document.getElementById("statusfilter")) {
		var _10e3 = document.getElementById("userfilter").value;
		var _10e4 = document.addLogHours.username.value;
		var _10e5 = document.getElementById("statusfilter").value;
		if (_10e5 == "NonBillable") {
			_10e5 = "Non Billable";
		}
		var _10e6 = document.addLogHours.billsts.value;
		var _10e7 = "0";
		if (document.getElementById("showbyfilter") && document.addLogHours.showby) {
			_10e7 = document.getElementById("showbyfilter").value;
		}
		if ((_10e3 == "all" || _10e3 == _10e4 || _10e7 != "0") && (_10e5 == "All" || _10e5 == _10e6)) {
			if (document.getElementById("tmonth") && document.getElementById("tyear")) {
				document.getElementById("calmonth").value = document.getElementById("tmonth").value;
				document.getElementById("calyear").value = document.getElementById("tyear").value;
			}
			document.addLogHours.showby.value = _10e7;
			document.addLogHours.CompId.value = _10e3;
		}
	}
}
function calcNewLogHours(_10e8, lh, lm) {
	var ch = parseInt(_10e8.split(":")[0], 10);
	var cm = parseInt(_10e8.split(":")[1], 10);
	var _10ed = parseInt((ch * 60 + cm + lh * 60 + lm) / 60, 10);
	var _10ee = parseInt((ch * 60 + cm + lh * 60 + lm) % 60, 10);
	newmins = "" + _10ee;
	if (_10ee < 10) {
		newmins = "0" + newmins;
	}
	return _10ed + ":" + newmins;
}
function showLogSelectBox(_10ef) {
	if (document.getElementById("loggentask" + _10ef)) {
		document.getElementById("loggentask" + _10ef).value = "";
	}
	dispBlock("othertask" + _10ef);
	dispBlock("selectlogitem" + _10ef);
	dispNone("selecttask" + _10ef);
	dispNone("addlogtask" + _10ef);
}
function hideLogSelectBox(_10f0) {
	if (document.getElementById("logtask")) {
		document.getElementById("logtask").value = "";
	}
	if (document.getElementById("logissue")) {
		document.getElementById("logissue").value = "";
	}
	if (document.getElementById("issueval")) {
		i18n.getJSAlertValue(Utils.zuid, "zp.bugoptions.select", null,
		function(mesg) {
			document.getElementById("issueval").value = mesg;
		});
	}
	if (document.getElementById("taskval" + _10f0)) {
		i18n.getJSAlertValue(Utils.zuid, "zp.bugoptions.select", null,
		function(mesg) {
			document.getElementById("taskval" + _10f0).value = mesg;
		});
	}
	if (document.getElementById("optionid" + _10f0)) {
		document.getElementById("optionid" + _10f0).value = "";
	}
	dispBlock("selecttask" + _10f0);
	dispBlock("addlogtask" + _10f0);
	dispNone("othertask" + _10f0);
	dispNone("selectlogitem" + _10f0);
	document.getElementById("tsheettype" + _10f0).value = "general";
	document.getElementById("loggentask" + _10f0).focus();
}
function hideAllSubTask(_10f3) {
	jQuery("table tr[task_" + _10f3 + "=" + _10f3 + "][id*=task_]").hide();
	jQuery("div[hideIcon_" + _10f3 + "=" + _10f3 + "]").hide();
	jQuery("div[showIcon_" + _10f3 + "=" + _10f3 + "]").hide();
	jQuery("#hidesubtask_" + _10f3).hide();
	jQuery("[id=showsubtask_" + _10f3 + "][isparent=true]").show();
}
function showAllSubTask(_10f4) {
	jQuery("table tr[task_" + _10f4 + "=" + _10f4 + "][id*=task_]").show();
	jQuery("div[hideIcon_" + _10f4 + "=" + _10f4 + "][isparent=true]").show();
	jQuery("div[showIcon_" + _10f4 + "=" + _10f4 + "]").hide();
	jQuery("[id=hidesubtask_" + _10f4 + "][isparent=true]").show();
	jQuery("#showsubtask_" + _10f4).hide();
}
function removeAllSubTask(_10f5) {
	jQuery("table tr[task_" + _10f5 + "=" + _10f5 + "]").remove();
}
function showAlertMsg(_10f6, msg) {
	jQuery("#errorMsgDiv").show();
	if (document.getElementById("errorMsgDiv")) {
		i18n.getJSAlertValue(Utils.zuid, _10f6, null,
		function(mesg) {
			document.getElementById("msgHeader").innerHTML = mesg;
		});
		document.getElementById("msgContent").innerHTML = msg;
	}
}
function dependentAlertMsg(msg) {
	showAlertMsg("zp.dependent.updfailed", msg);
}
function subTaskIndent(_10fa, _10fb, tlid) {
	var tlObj = jQuery("#ul_ttask_" + tlid);
	var _10fe = tlObj.find("[task_" + _10fa + "=" + _10fa + "],tr[id=task_" + _10fa + "],td[id=tasktd_" + _10fa + "],tr[id=taskaction_" + _10fa + "]");
	var _10ff = _10fe.filter("tr[id=task_" + _10fa + "]");
	var _1100 = jQuery(_10ff.children()[3]);
	var _1101 = parseInt(jQuery(_1100).css("padding-left"), 10);
	if (_1101 == 30 && _10fb == -30) {
		_1101 = 35;
	}
	if (_1101 == 5 && _10fb == 30) {
		_1101 = 0;
	}
	jQuery(_1100).css("padding-left", _1101 + _10fb);
	var depth = parseInt(jQuery(_10ff).attr("depth"), 10);
	var _1103 = 0;
	var _1104 = jQuery(_10ff).attr("parents");
	var _1105 = "-1";
	var _1106 = jQuery(_10ff).position().top;
	var _1107 = _1106;
	var sctop = jQuery(window).scrollTop();
	var winht = jQuery(window).height();
	var _110a = _10fe.filter("td[task_" + _10fa + "=" + _10fa + "],td[id=tasktd_" + _10fa + "]");
	var _110b = _110a.find("div[hideIcon_" + _10fa + "=" + _10fa + "],div[id=hidesubtask_" + _10fa + "]");
	var _110c = _110a.find("div[showIcon_" + _10fa + "=" + _10fa + "],div[id=showsubtask_" + _10fa + "]");
	if (_10fb == 30) {
		var _110d = jQuery(_10ff).attr("previoustask");
		var _110e = tlObj.find("tr[previoustask=" + _10fa + "][depth=" + depth + "]");
		var _110f = depth + 1;
		var _1110 = tlObj.find("tr[task_" + _110d + "=" + _110d + "][depth=" + _110f + "][status=open]");
		var _1111 = jQuery(_10fe).filter("tr[id=task_" + _10fa + "],td[id=tasktd_" + _10fa + "],tr[id=taskaction_" + _10fa + "]");
		_1111.attr("depth", depth + 1);
		_1111.attr("task_" + _110d, _110d);
		_110b.attr("hideIcon_" + _110d, _110d);
		_110c.attr("showIcon_" + _110d, _110d);
		if (depth == 0) {
			var _1112 = _10fe.filter("tr[id=task_" + _10fa + "],tr[id=taskaction_" + _10fa + "]");
			jQuery(_1112).removeAttr("tlid_" + tlid);
			jQuery(_10ff).attr("task_" + tlid, tlid);
			jQuery(_1100).attr("task_" + tlid, tlid);
			Hide("rem_" + _10fa);
			Hide("rec_" + _10fa);
			jQuery("#remrec_" + _10fa).hide();
		}
		_1103 = 0;
		for (var i = depth + 1; _1103 == 0; i++) {
			var _1114 = _10fe.filter("table tr[task_" + _10fa + "=" + _10fa + "][depth=" + i + "][task_" + _110d + "!=" + _110d + "]");
			var _1115 = _1114.filter("table tr[id*=task_][status=open]");
			var _1116 = _10fe.filter("table td[task_" + _10fa + "=" + _10fa + "][depth=" + i + "][task_" + _110d + "!=" + _110d + "]");
			if (_1114 != null && _1114.length > 0 && _1116 != null && _1116.length > 0) {
				while (_1115 != null && _1115.length > 0) {
					var _1117 = jQuery(_1115[0]).attr("parents");
					var _1118 = _1115.filter("[parents=" + _1117 + "]");
					_1117 = _1117.replace(_10fa, _110d + "," + _10fa);
					jQuery(_1118).attr("parents", _1117);
					_1115 = _1115.filter("[parents!=" + _1117 + "]");
				}
				var _1101 = i * 30;
				jQuery(_1116).css("padding-left", _1101 + _10fb);
				jQuery(_1114).attr("task_" + _110d, _110d);
				jQuery(_1116).attr("task_" + _110d, _110d);
				jQuery(_1114).attr("depth", i + 1);
				jQuery(_1116).attr("depth", i + 1);
			} else {
				_1103 = 1;
			}
		}
		if (_1104 == "0" || _1104 == "") {
			_1104 = _110d;
		} else {
			_1104 = _1104 + "," + _110d;
		}
		jQuery(_10ff).attr("parents", _1104);
		var _1119 = _10fe.filter("tr[id=task_" + _10fa + "],tr[id=taskaction_" + _10fa + "],tr[task_" + _10fa + "=" + _10fa + "]");
		if (_1110 != null && _1110.length > 0) {
			var _111a = jQuery(_1110[_1110.length - 1]).attr("id").split("_")[1];
			jQuery(_10ff).attr("previoustask", _111a);
			if (jQuery(_1110[_1110.length - 1]).attr("depth") == jQuery(_10ff).attr("depth")) {
				jQuery("#right_" + _10fa).show();
			}
			var _111b = tlObj.find("tr[id=taskaction_" + _111a + "],tr[task_" + _111a + "=" + _111a + "]");
			var _111c = jQuery(_111b[_111b.length - 1]).position().top;
			if (_111b != null && _111b.length > 0 && Math.abs(_1106 - _111c) > 2) {
				jQuery(_111b[_111b.length - 1]).after(_1119);
			}
			_1107 = jQuery(_10ff).position().top;
		} else {
			var _111c = jQuery("#taskaction_" + _110d).position().top;
			if (Math.abs(_1106 - _111c) > 2) {
				jQuery("#taskaction_" + _110d).after(_1119);
			}
			jQuery("#right_" + _10fa).hide();
			_1107 = jQuery(_10ff).position().top;
		}
		if (_1107 < sctop) {
			scrollTo(0, _1107 - winht / 2);
		}
		if (_1106 != _1107) {
			document.getElementById("task_" + _10fa).style.background = "#FAFABB";
			setTimeout(function() {
				document.getElementById("task_" + _10fa).style.background = "";
			},
			3000);
		}
		if (_110e != null && _110e.length == 1) {
			jQuery(_110e).attr("previoustask", _110d);
		}
		var _111d = jQuery("#hidesubtask_" + _110d);
		if (!jQuery("#showsubtask_" + _110d).is(":visible")) {
			_111d.show();
		} else {
			_10fe.filter("tr[id=task_" + _10fa + "],tr[task_" + _10fa + "=" + _10fa + "]").hide();
		}
		_111d.attr("isparent", "true");
		jQuery("#showsubtask_" + _110d).attr("isparent", "true");
	} else {
		if (_10fb == -30) {
			var _110d = _1104.split(",")[depth - 1];
			var _1105 = depth - 1;
			var _111e = tlObj.find("tr[previoustask=" + _110d + "][depth=" + _1105 + "]");
			var _110e = tlObj.find("tr[previoustask=" + _10fa + "][depth=" + depth + "]");
			var _1111 = jQuery(_10fe).filter("tr[id=task_" + _10fa + "],td[id=tasktd_" + _10fa + "],tr[id=taskaction_" + _10fa + "]");
			_1111.attr("depth", depth - 1);
			_1111.removeAttr("task_" + _110d, _110d);
			_110b.removeAttr("hideIcon_" + _110d);
			_110c.removeAttr("showIcon_" + _110d);
			_1103 = 0;
			for (var i = depth + 1; _1103 == 0; i++) {
				var _1114 = _10fe.filter("table tr[task_" + _10fa + "=" + _10fa + "][depth=" + i + "][task_" + _110d + "=" + _110d + "]");
				var _1115 = _1114.filter("table tr[id*=task_][status=open]");
				var _1116 = _10fe.filter("table td[task_" + _10fa + "=" + _10fa + "][depth=" + i + "][task_" + _110d + "=" + _110d + "]");
				if (_1114 != null && _1114.length > 0 && _1116 != null && _1116.length > 0) {
					while (_1115 != null && _1115.length > 0) {
						var _1117 = jQuery(_1115[0]).attr("parents");
						var _1118 = _1115.filter("[parents=" + _1117 + "]");
						_1117 = _1117.replace(_110d + ",", "");
						jQuery(_1118).attr("parents", _1117);
						_1115 = _1115.filter("[parents!=" + _1117 + "]");
					}
					var _1101 = i * 30;
					jQuery(_1116).css("padding-left", _1101 + _10fb);
					jQuery(_1114).removeAttr("task_" + _110d);
					jQuery(_1116).removeAttr("task_" + _110d);
					jQuery(_1114).attr("depth", i - 1);
					jQuery(_1116).attr("depth", i - 1);
				} else {
					_1103 = 1;
				}
			}
			var _111f = _1104.split(",")[_1104.split(",").length - 1];
			var _1120 = jQuery(_10ff).attr("previoustask");
			if (_1104.indexOf("," + _111f) != -1) {
				_1104 = _1104.replace("," + _111f, "");
			} else {
				if (_1104.indexOf(_111f) != -1) {
					_1104 = _1104.replace(_111f, "0");
				}
			}
			jQuery(_10ff).attr("parents", _1104);
			if (_1105 == jQuery(_10ff).attr("depth")) {
				jQuery("#right_" + _10fa).show();
			}
			var _1121 = tlObj.find("tr[task_" + _110d + "=" + _110d + "][depth=" + depth + "]");
			if (_1121 == null || _1121.length <= 0) {
				var _111d = jQuery("#hidesubtask_" + _110d);
				_111d.hide();
				_111d.attr("isparent", "false");
				jQuery("#showsubtask_" + _110d).attr("isparent", "false");
			}
			var _1119 = _10fe.filter("tr[id=task_" + _10fa + "],tr[id=taskaction_" + _10fa + "],tr[task_" + _10fa + "=" + _10fa + "]");
			if (_1105 > 0) {
				var _1122 = _1104.split(",")[_1104.split(",").length - 1];
				var _111b = tlObj.find("tr[task_" + _1122 + "=" + _1122 + "][task_" + _10fa + "!=" + _10fa + "][id!=task_" + _10fa + "][id!=taskaction_" + _10fa + "]");
				var _1123 = jQuery(_111b).filter("[depth=" + _1105 + "][status=open]");
				var _111c = jQuery(_111b[_111b.length - 1]).position().top;
				if (_1123 != null && _1123.length > 0) {
					if (Math.abs(_1106 - _111c) > 2) {
						jQuery(_111b[_111b.length - 1]).after(jQuery(_1119));
					}
					var _1124 = jQuery(_1123[_1123.length - 1]).attr("id").split("_")[1];
					jQuery(_10ff).attr("previoustask", _1124);
					_1107 = jQuery(_10ff).position().top;
				}
			} else {
				if (_1105 == 0) {
					tlObj.append(_1119);
					var _1123 = tlObj.find("tr[depth=" + _1105 + "][tlid_" + tlid + "=" + tlid + "][id!=task_" + _10fa + "]");
					var _1124 = jQuery(_1123[_1123.length - 1]).attr("id").split("_")[1];
					jQuery(_10ff).attr("previoustask", _1124);
					var _1112 = _10fe.filter("[id=task_" + _10fa + "],[id=taskaction_" + _10fa + "]");
					jQuery(_1112).attr("tlid_" + tlid, tlid);
					jQuery(_10ff).removeAttr("task_" + tlid);
					jQuery(_1100).removeAttr("task_" + tlid);
					_1107 = jQuery(_10ff).position().top;
				}
			}
			if (_1107 > winht + sctop) {
				scrollTo(0, _1107 - winht / 2);
			}
			if (_1106 != _1107) {
				document.getElementById("task_" + _10fa).style.background = "#FAFABB";
				setTimeout(function() {
					document.getElementById("task_" + _10fa).style.background = "";
				},
				3000);
			}
			if (_110e != null && _110e.length == 1) {
				if (_111f != _1120) {
					jQuery(_110e).attr("previoustask", _1120);
				} else {
					jQuery(_110e).attr("previoustask", _111f);
					var _1125 = jQuery(_110e).attr("id").split("_")[1];
					jQuery("#right_" + _1125).hide();
				}
			}
		}
	}
	if (jQuery(_10ff).attr("depth") <= 0) {
		jQuery("#left_" + _10fa).hide();
	} else {
		jQuery("#left_" + _10fa).show();
	}
}
function replacePrevTask(_1126) {
	var depth = jQuery("#task_" + _1126).attr("depth");
	var _1128 = jQuery("#task_" + _1126).attr("previoustask");
	var _1129 = jQuery("table tr[previoustask=" + _1126 + "][depth=" + depth + "]");
	if (_1129 != null && _1129.length == 1) {
		jQuery(_1129).attr("previoustask", _1128);
		var _112a = jQuery("#task_" + _1128).attr("depth");
		if (_112a != depth) {
			var _112b = jQuery(_1129[0]).attr("id").split("_")[1];
			jQuery("#right_" + _112b).hide();
		}
	}
}
function moveBulkTask(tlid, url, divid) {
	taskdiv_id_bulk = tlid;
	var id = new Array();
	var check = document.getElementsByName("selTask_" + taskdiv_id_bulk);
	for (var i = 0,
	j = 0; i < check.length; i++) {
		if (check[i].checked) {
			checked = true;
			var _1133 = new String(check[i].id);
			var dd = _1133.split("_");
			id[j++] = dd[1];
		}
	}
	url = url + "&ttaskId=" + id;
	ajaxConstructPage(url, divid);
}
function taskActionRemove(tlid) {
	var tlObj = jQuery("#ul_ttask_" + tlid);
	jQuery(tlObj.find("div[id*=indent_]")).hide();
	jQuery(tlObj.find("div[hideIcon_" + tlid + "=" + tlid + "]")).hide();
	jQuery(tlObj.find("div[showIcon_" + tlid + "=" + tlid + "]")).hide();
	jQuery(tlObj.find("tr[tlid_" + tlid + "=" + tlid + "][id*=taskaction_],tr[task_" + tlid + "=" + tlid + "]")).remove();
	new Effect.ScrollTo("tl_" + tlid, {
		duration: 0.1
	});
}
function valDateCheck(sdate, edate, mfor) {
	var SDate = document.getElementById(sdate).value;
	var EDate = document.getElementById(edate).value;
	Utils.timeformat = Utils.timeformat.replace("aaa", "a");
	Utils.dateformat = Utils.dateformat.replace("MMMM", "MMM");
	var orgDF = Utils.dateformat;
	if (Utils.taskinhr == "true" || !(isDIHrs(SDate))) {
		orgDF = Utils.dateformat + " " + Utils.timeformat;
	}
	var _113d = compareDates(SDate, orgDF, EDate, orgDF);
	if (SDate == "" || SDate == " " || SDate == "-") {
		i18n.getJSAlertValue(Utils.zuid, "zp.functionjs.stdateemp", null,
		function(mesg) {
			alert(mesg);
		});
		return false;
	} else {
		if (SDate != "" && EDate != "" && _113d != 0) {
			if ("comp" == mfor) {
				i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.duedatecantst", null,
				function(mesg) {
					alert(mesg);
				});
				return false;
			} else {
				i18n.getJSAlertValue(Utils.zuid, "zp.ajaxjs.enddatecantst", null,
				function(mesg) {
					alert(mesg);
				});
				return false;
			}
		} else {
			return true;
		}
	}
}
function displayExpandIcon(_1141) {
	if (jQuery("#showsubtask_" + _1141).is(":visible")) {
		setTimeout(function() {
			jQuery("#showsubtask_" + _1141).show();
			jQuery("#hidesubtask_" + _1141).hide();
		},
		500);
	}
}
var spcal = function(but, input, but1, _1145, _1146) {
	var dTime = false;
	var dFmt = Utils.ifformat;
	if (Utils.taskinhr == "true" || ((!(isDIHrs(document.getElementById(input).value)) || !(isDIHrs(document.getElementById(_1145).value))) && "edittask" == _1146)) {
		dTime = "12";
		dFmt = Utils.ifformat + " %I:%M %p";
		if ((Utils.timeformat).indexOf("HH") != -1) {
			dTime = true;
			dFmt = Utils.ifformat + " %H:%M";
		}
	}
	var cal = Calendar.setup({
		animation: false,
		fdow: parseInt(Utils.weekFirstDay),
		showTime: dTime,
		onSelect: function(cal) {
			iscalvisible = true;
			cal.hide();
		},
		onTimeChange: function(cal) {
			cal.date.setHours(parseInt(cal.time / 100));
			cal.date.setMinutes(cal.time % 100);
			cal.inputField.value = Calendar.printDate(cal.date, cal.dateFormat);
		}
	});
	cal.manageFields(but, input, dFmt);
	cal.manageFields(but1, _1145, dFmt);
};
function updateFields(cal, inid, dfmt) {
	var date = cal.selection.get();
	if (date) {
		date = Calendar.intToDate(date);
	}
	document.getElementById(inid).value = Calendar.printDate(date, dfmt) + " " + cal.getHours() + ":" + cal.getMinutes();
}
function showSpan(id, value) {
	if (id != null) {
		var text = "";
		if (value == "daily") {
			text = "zp.span.daily";
		} else {
			if (value == "weekly") {
				text = "zp.span.weekly";
			} else {
				if (value == "monthly") {
					text = "zp.span.monthly";
				} else {
					if (value == "yearly") {
						text = "zp.span.yearly";
					}
				}
			}
		}
		i18n.getJSAlertValue(Utils.zuid, text, null,
		function(mesg) {
			document.getElementById(id).innerHTML = mesg;
		});
	}
}
function getTimeString(hours, _1155) {
	var _1156 = "AM";
	if (hours >= 12) {
		_1156 = "PM";
		if (hours > 12) {
			hours = hours - 12;
		}
	}
	if (hours == 0) {
		hours = 12;
	}
	var hrs = "" + hours;
	var mins = "" + _1155;
	if (hours < 10) {
		hrs = "0" + hrs;
	}
	if (_1155 < 10) {
		mins = "0" + mins;
	}
	var time = hrs + ":" + mins + " " + _1156;
	return time;
}
function addLogHours(l1, l2) {
	var h1 = parseInt(l1.split(":")[0]);
	var m1 = parseInt(l1.split(":")[1]);
	var h2 = parseInt(l2.split(":")[0]);
	var m2 = parseInt(l2.split(":")[1]);
	thrs = h1 + h2;
	tmins = m1 + m2;
	if (tmins > 59) {
		tmins = tmins - 60;
		thrs = thrs + 1;
	}
	var tlog = "";
	if (tmins < 10) {
		tlog = thrs + ":0" + tmins;
	} else {
		tlog = thrs + ":" + tmins;
	}
	return tlog;
}
function enableiphoneAddon(_1161) {
	var _1162 = jQuery("span[iphoneusers=iphoneusers]");
	if (_1161 == 0) {
		alert("Upgrade license for additional users.");
		return false;
	}
	var _1163 = "";
	var _1164 = 0;
	var _1165 = "";
	var _1166 = 0;
	var _1167 = "";
	var _1168 = 0;
	var _1169 = "";
	var _116a = 0;
	for (var i = 0; i < _1162.length; i++) {
		var _116c = ((_1162[i].id).split("_"))[2];
		var _116d = ((_1162[i].id).split("_"))[0];
		if (_1162[i].style.display == "block") {
			if (_1162[i].className == "click-yes") {
				if (_116d == "user") {
					if (_1164 == 0) {
						_1163 = _116c;
					} else {
						_1163 = _1163 + "," + _116c;
					}
					_1164++;
				} else {
					if (_1166 == 0) {
						_1165 = _116c;
					} else {
						_1165 = _1165 + "," + _116c;
					}
					_1166++;
				}
				if (_1161 < (_1164 + _1166)) {
					alert(" Maximum allowed iphone addon user count is " + _1161 + " \n Upgrade license for additional users.");
					return false;
				}
			} else {
				if (_1162[i].className == "click-no") {
					if (_116d == "user") {
						if (_1168 == 0) {
							_1167 = _116c;
						} else {
							_1167 = _1167 + "," + _116c;
						}
						_1168++;
					} else {
						if (_116a == 0) {
							_1169 = _116c;
						} else {
							_1169 = _1169 + "," + _116c;
						}
						_116a++;
					}
				}
			}
		}
	}
	jQuery("#selUser").html(_1163);
	jQuery("#unSelUser").html(_1167);
	jQuery("#selClient").html(_1165);
	jQuery("#unselClient").html(_1169);
	return true;
}
function iphoneUserAddon(_116e, _116f) {
	var cnt = document.getElementById("count");
	if (cnt.value == "-1") {
		alert("Please Select Valid No. of Users");
		return false;
	}
	if (jQuery("input:radio[name=iphonestat]:checked").val() == "downgrade") {
		var _1171 = eval(_116f) - eval(cnt.value);
		if (_1171 < _116e) {
			alert("Disable addon users before downgrading the license");
			return false;
		}
	}
	return true;
}
function changeAddonAmount(total, _1173, mode, stat) {
	var _1176 = "3";
	if (mode == "year") {
		_1176 = "30";
	}
	var _1177 = total;
	if (_1173 != "-1") {
		_1177 = parseInt(_1176, 10) * parseInt(_1173, 10);
	}
	var _1178 = total;
	if (stat == "upgrade") {
		_1178 = eval(total) + eval(_1177);
	} else {
		if (stat == "downgrade") {
			_1178 = eval(total) - eval(_1177);
			if (_1178 < 0) {
				_1178 = 0;
			}
		}
	}
	var _1179 = "$" + _1177;
	if (total != "0" && _1173 != "-1") {
		if (stat == "upgrade") {
			_1179 = "$" + total + " + $" + _1177 + " = $" + _1178;
		} else {
			if (stat == "downgrade") {
				_1179 = "$" + total + " - $" + _1177 + " = $" + _1178;
			}
		}
	}
	document.getElementById("iphoneamt").innerHTML = _1179;
}
function ShowInlineNone(divId, Id) {
	var id = document.getElementById(divId);
	if (id) {
		id.style.display = "block";
	}
	var id1 = document.getElementById(Id);
	if (id1) {
		id1.style.display = "none";
	}
}
function validateDropBox(_117e) {
	if (_117e == "config") {
		var _117f = document.getElementById("dbfdispdiv").innerHTML;
		var _1180 = document.getElementById("zfdispdiv").innerHTML;
		if (_117f != null && _117f == "") {
			var divId = document.getElementById("dropbox_config_status");
			divId.innerHTML = "<span class=\"error\">" + "Select the Dropbox folder" + "</span>";
			return false;
		}
		if (_1180 != null && (_1180 == "" || _1180 == "-")) {
			var divId = document.getElementById("dropbox_config_status");
			divId.innerHTML = "<span class=\"error\">" + "Select the Project folder" + "</span>";
			return false;
		}
	}
	return true;
}
function NiceURL() {}
NiceURL.URL = window.location.href;
function checkURL(csrf) {
	if (NiceURL.URL != window.location.href) {
		NiceURL.URL = window.location.href;
		loadDataFromURL(csrf);
	}
	setTimeout(function() {
		checkURL(csrf);
	},
	100);
}
function loadDataFromURL(csrf) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	ShowGenBlock("ajax_load_tab");
	var urls = NiceURL.URL.split("#");
	if (urls.length > 1) {
		var _1185 = urls[1].split("/");
		var _1186 = {
			"homepage": "allprojects.do?istabenabled=yes&",
			"allprojects": "allprojects.do?istabenabled=yes&",
			"globalreports": "globalreport.do?&istabenabled=yes&",
			"myworkcalendar": "mytodos.do?istabenabled=yes&",
			"mytimesheet": "mylogtodos.do?istabenabled=yes&",
			"mytasks": "mytasklistview.do?istabenabled=yes&pagefor=home&",
			"mymeetings": "showproject.do?projId=0&toview=meetings&dispMeetType=open&pagefor=home&istabenabled=yes&",
			"search": "getprojs.do?istabenabled=yes&",
			"mybugs": "mybugs.do?range=25&istabenabled=yes&type=reported&",
			"settings": "usersettings.do?istabenabled=yes&toshow=profile&",
			"people": "people.do?projId=0&istabenabled=yes&",
			"mymilestones": "mileview.do?projId=0&pagefor=home&istabenabled=yes&mstype=upcoming&"
		} [_1185[0]];
		if (!_1186 && _1185.length > 1 && _1185[1].match(new RegExp("^[0-9]{13,}$"))) {
			_1186 = {
				"dashboard": "showproject.do?toview=projecthome&projSel=yes",
				"todomilestones": (_1185.length > 2 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "taskdetails.do?taskid=" + _1185[2] : "tasklistview.do?projSel=yes",
				"milestones": (_1185.length > 2 && _1185[2] == "bugs") ? "mileview.do?username=all&modtype=" + _1185[2] : "mileview.do?username=all",
				"milestone": (_1185.length > 3 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "tasklistview.do?mileId=" + _1185[2] + "&username=" + _1185[3] : (_1185.length == 3 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "tasklistview.do?mileId=" + _1185[2] : "myhome.do?",
				"tasklist": (_1185.length == 3 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "fetchtasklist.do?tlistid=" + _1185[2] + "&username=all&frompage=arctlist&from=niceurl": (_1185.length > 7 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "fetchtasklist.do?&tlistid=" + _1185[2] + "&username=" + _1185[3] + "&viewBy=" + _1185[4] + "&status=" + _1185[5] + "&priority=" + _1185[6] + "&mileId=" + _1185[7] + "&frompage=arctlist&from=niceurl": "myhome.do?",
				"dependency": "dependview.do?toview=todomilestones&dispType=dependview",
				"ganttview": "genreport.do?&toview=reports&username=0&plot=open&plottype=curmonth",
				"resourceutilization": "manageresource.do?projSel=yes&",
				"bugs": (_1185.length > 2 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "showissue.do?issueId=" + _1185[2] : "showIssuesTab.do?fromIndex=1",
				"bugsettings": (_1185.length > 2) ? ((_1185[2] == "notificationpatterns") ? "notification.do?action=showtab": (_1185[2] == "bugsimport" ? "issueimport.do?type=uploadpage": "bugsettings.do?action=" + _1185[2])) : "bugsettings.do?",
				"bugreports": "bugreports.do?action=bugreports&filtby=open",
				"projectcalendar": "projcal.do?edisp=all&username=0",
				"meetings": "showproject.do?toview=meetings&projSel=yes",
				"folders": (_1185.length > 2 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "fetchdocuments.do?sindex=1&folderid=" + _1185[2] : "showproject.do?toview=attachments&projSel=yes",
				"documents": (_1185.length > 2 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "fetchdocdetails.do?docid=" + _1185[2] : "showproject.do?toview=attachments&projSel=yes",
				"timesheet": (_1185[2] == "listview") ? "showproject.do?toview=timesheet&projSel=yes": "logcalendar.do?projSel=yes&isaddtime=yes",
				"reports": "combinedreport.do?",
				"categories": (_1185.length > 2 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "fetchmessage.do?sindex=1&catid=" + _1185[2] : "showproject.do?toview=forum&projSel=yes",
				"forums": (_1185.length > 2 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "fetchcomment.do?mesgid=" + _1185[2] : "showproject.do?toview=forum&projSel=yes",
				"wiki": "showproject.do?toview=wiki&projSel=yes",
				"chat": (_1185.length > 2 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "showproject.do?pmchatid=" + _1185[2] + "&sid=" + WebMessanger.getSid() + "&toview=chat": "showproject.do?toview=chat&projSel=yes&sid=" + WebMessanger.getSid(),
				"chatrooms": (_1185.length > 2 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "showproject.do?toview=chatrooms": "showproject.do?toview=chatrooms&projSel=yes",
				"users": "showproject.do?toview=users&projSel=yes",
				"bugviews": "bugcustomview.do?action=zbcviewpage&custid=-1",
				"changesets": (_1185.length > 2 && _1185[2].match(new RegExp("^[0-9]{13,}$"))) ? "showchangeset.do?chsetId=" + _1185[2] : "listchangesets.do?"
			} [_1185[0]];
			if (_1186) {
				if (_1185[0] == "tasklist") {
					_1186 = _1186 + "&istabenabled=yes&projid=" + _1185[1] + "&";
				} else {
					_1186 = _1186 + "&istabenabled=yes&projId=" + _1185[1] + "&";
				}
			}
			if (_1185[0] == "chat") {
				ajaxChatNiceURL(_1186, csrf, _1185[1]);
				return;
			}
		}
		if (_1185[0] == "ganttview") {
			document.getElementById("mainPageTd").setAttribute("height", "100%");
			ajaxShowImgPage(Utils.contPath + "/" + _1186 + csrf, "projectpagediv");
			return;
		}
	}
	if (!_1186) {
		_1186 = "myhome.do?";
	}
	ajaxShowPage(Utils.contPath + "/" + _1186 + csrf, "projectpagediv");
}
function ajaxChatNiceURL(_1187, csrf, _1189) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var _118a = fixForCachingInIe(Utils.contPath + "/getchatid.do?projId=" + _1189 + "&" + csrf);
	var _118b = getURLArgs(_118a);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _118b.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				if (text == "-2" || text.match(new RegExp("^[0-9]{13,}$"))) {
					Utils.chatId = text;
					Utils.isChatPopOut = true;
					ini = false;
					ajaxShowPage(Utils.contPath + "/" + _1187 + csrf, "projectpagediv");
				}
			}
		};
		http.send(_118b.args);
	}
}
function loadOrganizeTabs(csrf, _118f) {
	if (typeof(csrf) == "undefined") {} else {
		csrf = getCSRFEncode(csrf);
	}
	var _1190 = fixForCachingInIe(Utils.contPath + "/showproject.do?toview=projecthome&istabenabled=yes&projSel=yes&projId=" + _118f + "&" + csrf);
	var _1191 = getURLArgs(_1190);
	var http = getHTTPObject();
	if (http) {
		http.open("POST", _1191.url, true);
		http.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		http.onreadystatechange = function() {
			if (http.readyState == 4) {
				var text = http.responseText;
				var _1194 = document.getElementById("projectpagediv");
				_1194.innerHTML = text;
				modifyURL("dashboard/" + _118f);
				ajaxShowPage(Utils.contPath + "/organizetabs.do?projId=" + _118f + "&" + csrf, "projectcontent");
				ini = false;
			}
		};
		http.send(_1191.args);
	}
}
function modifyURL(_1195) {
	var urls = NiceURL.URL.split("#");
	if (urls.length <= 1 || _1195 != urls[1]) {
		NiceURL.URL = urls[0] + "#" + _1195;
		window.location.href = NiceURL.URL;
		addToHistory(_1195);
	}
	if (_1195 != "") {
		var _1197 = _1195.split("/");
		if (_1197[0] == "ganttview" || _1197[0] == "resourceutilization" || _1197[0] == "globalreports") {
			document.getElementById("footerTr").style.display = "none";
			document.getElementById("mainPageTd").setAttribute("height", "100%");
		} else {
			document.getElementById("footerTr").style.display = "";
			document.getElementById("mainPageTd").setAttribute("height", "800");
		}
		if (_1197[0] != "globalreports" || _1197[0] != "resourceutilization") {
			jQuery(window).unbind();
		}
		if (_1197[0] == "chat") {
			setChatStyle("footer");
			setChatStyle("header");
			setChatStyle("tabheader");
		} else {
			unSetChatStyle();
		}
	}
}
function addToHistory(_1198) {
	if (jQuery.browser.msie || jQuery.browser.opera) {
		jQuery.history.add("#" + _1198);
	}
}
function isDefined(val) {
	if (val && val != "") {
		return true;
	}
	return false;
}
function viewProjChangesets(evt) {
	var _119b = evt.data;
	if (isDefined(evt.customData)) {
		_119b = jQuery.extend({},
		evt.data, evt.customData);
	}
	var url = Utils.contPath + "/listchangesets.do";
	url += "?projId=" + _119b.projId + "&" + _119b.csrf;
	var pg = _119b.pg;
	var range = _119b.range;
	var rId = _119b.rId;
	url += (isDefined(range)) ? "&range=" + range: "";
	url += (isDefined(pg)) ? "&pg=" + pg: "";
	url += (isDefined(rId)) ? "&rId=" + rId: "";
	changeSubLink("projChangesets", "projChangesetsviewImg");
	modifyURL("changesets/" + _119b.projId);
	ShowGenInline("ajax_load_tab");
	ajaxShowPage(url, "projectcontent");
}
function viewIssueChangeSets(evt) {
	var url = Utils.contPath + "/viewissuechangesets.do";
	url += "?projId=" + evt.data.projId + "&issueId=" + evt.data.issueId + "&" + evt.data.csrf;
	ShowGenInline("ajax_load_tab");
	showIssueOptionsSubTab("changeset");
	ajaxShowTab(url, "changesetcontent");
}
function viewChangeset(evt) {
	var url = Utils.contPath + "/showchangeset.do";
	url += "?projId=" + evt.data.projId + "&chsetId=" + evt.data.commitId + "&" + evt.data.csrf;
	url += (isDefined(evt.data.issueId)) ? "&issueId=" + evt.data.issueId: "";
	url += (isDefined(evt.data.pg)) ? "&pg=" + evt.data.pg: "";
	url += (isDefined(evt.data.range)) ? "&range=" + evt.data.range: "";
	url += (isDefined(evt.data.rId)) ? "&rId=" + evt.data.rId: "";
	ShowGenInline("ajax_load_tab");
	modifyURL("changesets/" + evt.data.projId + "/" + evt.data.commitId);
	ajaxShowTab(url, "projectcontent");
}
function backToChangesets(evt) {
	if (isDefined(evt.data.issueId)) {
		var url = Utils.contPath + "/showissue.do";
		url += "?projId=" + evt.data.projId + "&issueId=" + evt.data.issueId + "&" + evt.data.csrf;
		ShowGenInline("ajax_load_tab");
		ajaxShowTab(url, "projectcontent");
	} else {
		var _11a6 = {};
		if (isDefined(evt.data.pg)) {
			_11a6.pg = evt.data.pg;
		}
		if (isDefined(evt.data.range)) {
			_11a6.range = evt.data.range;
		}
		if (isDefined(evt.data.rId)) {
			_11a6.rId = evt.data.rId;
		}
		var _11a7 = jQuery.Event("click");
		_11a7.customData = _11a6;
		jQuery("#projChangesets").trigger(_11a7);
	}
}
function registerChangesetEvents(pg, rId) {
	var _11aa = {
		"range": jQuery("select[name=range]").val(),
		"rId": rId
	};
	var _11ab = jQuery.Event("click");
	jQuery("select[name=range]").bind("change",
	function() {
		_11ab.customData = jQuery.extend({},
		_11aa, {
			"range": jQuery(this).val()
		});
		jQuery("#projChangesets").trigger(_11ab);
	});
	jQuery("a#next, a#previous").bind("click",
	function(evt) {
		var pgno = (jQuery(evt.target).hasClass("chsetnext")) ? ++pg: --pg;
		_11ab.customData = jQuery.extend({},
		_11aa, {
			"pg": pgno
		});
		jQuery("#projChangesets").trigger(_11ab);
	});
	jQuery("span.repchangesets").bind("click",
	function() {
		_11ab.customData = new Object();
		_11ab.customData.rId = jQuery(this).attr("id");
		jQuery("#projChangesets").trigger(_11ab);
	});
}
function getChangesetCount(pId, rId, csrf) {
	var url = Utils.contPath + "/listchangesets.do";
	url += "?projId=" + pId + "&" + csrf;
	url += (isDefined(rId)) ? "&rId=" + rId: "";
	url += "&actn=cscount";
	ShowGenInline("ajax_load_tab");
	ajaxShowPage(url, "zbcount");
}
function changeSetMouseOverEvents() {
	jQuery("tr.changesetrow").mouseover(function() {
		jQuery(this).removeClass("commitRow").addClass("commitRowHover");
	});
	jQuery("tr.changesetrow").mouseout(function() {
		jQuery(this).removeClass("commitRowHover").addClass("commitRow");
	});
}
function showHideIssueChangesets(_11b2) {
	if (_11b2 == "more") {
		jQuery("#moreChangesets").hide();
		jQuery("#lessChangesets").show();
		jQuery("tr.changesetmorerow").show();
	} else {
		jQuery("#moreChangesets").show();
		jQuery("#lessChangesets").hide();
		jQuery("tr.changesetmorerow").hide();
	}
}
function resourceLoader() {
	var _11b3 = jQuery("#resourceDiv").offset().top;
	if (is_ie) {
		document.getElementById("resourceDiv").style.height = document.documentElement.clientHeight - _11b3 - 35 - 10 + "px";
	} else {
		document.getElementById("resourceDiv").style.height = window.innerHeight - _11b3 - 35 - 10 + "px";
	}
	var _11b4 = document.getElementById("encryptedProjId");
	var _11b5 = document.getElementById("encryptedZoid");
	var _11b6 = document.getElementById("projId");
	var _11b7 = 0;
	if (_11b6) {
		_11b7 = _11b6.value;
	}
	var _11b8 = _11b4.innerHTML;
	var _11b9 = _11b5.innerHTML;
	var scoid = document.getElementById("scoid");
	var _11bb;
	if (scoid) {
		_11bb = scoid.innerHTML;
	}
	var _11bc = null;
	if (document.getElementById("chartenddate")) {
		_11bc = document.getElementById("chartenddate").innerHTML.split("-");
	}
	var gantt = new GanttChart(null, null, new Date(_11bc[2], _11bc[0] - 1, _11bc[1]));
	gantt.setImagePath(Utils.domainappender + "images/");
	gantt.setEditable(false);
	gantt.create("resourceDiv");
	ShowGenInline("ajax_load_tab");
	if (_11b7 == "0") {
		gantt.loadData(Utils.domainappender + "jsp/project/resourceChartLoader.jsp?projid=" + _11b8 + "&coid=" + _11bb + "&zoid=" + _11b9, true, true);
	} else {
		var _11be = "";
		if (document.getElementById("rescharttype")) {
			if (document.getElementById("rescharttype").innerHTML == "weekly") {
				_11be = "resourceweekly";
			}
			if (document.getElementById("rescharttype").innerHTML == "monthly") {
				_11be = "resourcemonthly";
			}
		}
		gantt.loadData(Utils.domainappender + "jsp/project/resourceChartLoader.jsp?projid=" + _11b8 + "&zoid=" + _11b9, true, true);
		var _11bf = true;
		if (Utils.taskinhr == "false" && _11be == "resourceweekly") {
			_11bf = false;
		}
		if (_11bf) {
			gantt.attachEvent("onUserClick",
			function(user, _11c1, _11c2, imgTr) {
				var _11c4 = jQuery(imgTr).offset().left;
				var _11c5 = _11c1 - (_11c4 - 24);
				var _11c6 = user.getDateOnPosition(_11c5, _11be);
				var _11c7 = document.getElementById("csrf").value;
				var _11c8 = "";
				var _11c9 = "";
				var _11ca = _11c6.getDate();
				if (_11be == "resourceweekly") {
					_11c8 = " " + _11c6.getHours() + ":00";
					_11c9 = " " + parseInt(_11c6.getHours() + 1) + ":00";
				}
				if (_11be == "resourcemonthly" && Utils.taskinhr == "true") {
					_11c8 = " 00:00";
					_11c9 = " 00:00";
					_11ca = _11ca + 1;
				}
				var _11cb = appendzero(_11c6.getMonth() + 1) + "-" + appendzero(_11c6.getDate()) + "-" + _11c6.getFullYear() + _11c8;
				var _11cc = appendzero(_11c6.getMonth() + 1) + "-" + appendzero(_11ca) + "-" + _11c6.getFullYear() + _11c9;
				var _11cd = _11c1 + "_" + _11c5 + "_" + _11c2;
				var _11ce = user.getId();
				var _11cf = "";
				if (document.getElementById("sdateval")) {
					_11cf = "&weekstart=" + document.getElementById("sdateval").value;
				}
				ShowGenInline("ajax_load_tab");
				ajaxConstructPage(Utils.contPath + "/conaddtask.do?frompage=resourcetask&resourcetype=" + _11be + "&projid=" + _11b7 + "&username=all&seluser=" + _11ce + "&" + _11c7 + "&cdate=" + _11cb + "&cenddate=" + _11cc + _11cf, _11cd);
			});
		}
	}
	var _11d0 = document.getElementById("mainTaskPanel").scrollHeight;
	if (jQuery("[id=timedivider]").length > 0) {
		jQuery("[id=timedivider]").css("height", _11d0);
	}
	gantt.attachEvent("onTaskClick",
	function(task) {
		var _11d2 = task.getId();
		var csrf = document.getElementById("csrf").value;
		var _11d4 = _11d2.split("_")[0];
		var tlId = _11d2.split("_")[1];
		var _11d6 = _11d2.split("_")[2];
		changeSubLink("taskviewlink", "taskviewImg");
		getSearchTask(csrf, _11d6, "open", tlId, _11d4);
	});
	Hide("ajax_load_tab");
}
_TZOFFSET = ((new Date()).getTimezoneOffset()) * -1;
function populateUsers(chid, list, _11d9) {
	var html = "";
	var _11db = "";
	var lobj = document.getElementById("listbody_" + chid);
	if (lobj) {
		_11db = lobj.innerHTML;
		if (list.length == 0) {
			i18n.getJSAlertValue(Utils.zuid, "zp.chat.noparti", null,
			function(mesg) {
				lobj.innerHTML = "<br /><br /><div class=\"txtSmall\">" + mesg + "</div>";
			});
		}
	}
	for (var i = 0; i < list.length; i++) {
		var _11df = list[i];
		var dname = _11df["dname"];
		var uname = _11df["uname"];
		var scode = _11df["scode"];
		var smsg = _11df["statusmsg"];
		var _11e4 = _11df["status"];
		var zuid = _11df["zuid"];
		var _11e6 = "chatimgbdr5 fl";
		var _11e7 = "";
		if (Utils.origChatOwner != "-1" && Utils.origChatOwner == zuid) {
			_11e7 = "[chat owner]";
		}
		var _11e8 = "<div class=\"" + _11e6 + "\" id=\"ch_" + chid + "_parti_" + zuid + "\">";
		var _11e9 = "<div>";
		var _11ea = "</div>";
		var _11eb = "</div>";
		var _11ec = "<div class=\"chatimghover\">";
		var _11ed = "<img src=\"" + _IAMURL + "file?ID=" + zuid + "&t=user&height=32&width=32\" height=32 width=32 title=\"" + dname + " " + _11e7 + "\" onClick=\"WebMessanger.chat('" + zuid + "');\" onmouseover=\"this.className='pointer';\"></div>";
		if (_11e4 == "0") {
			_11ec = "<div onmouseover=\"this.className='chatimghover'\" onmouseout=\"this.className='chatimg'\" class=\"chatimg\">";
		}
		var _11ee = "<div>";
		var _11ef = "offlinechat3";
		var _11f0 = "onlinechat3";
		if (Utils.isChatOwner) {
			_11ee = _11ee + "<div id=\"outerdiv\" style=\"width:10px;height:10px; position:relative; top:-11px;\" onmouseover=\"ShowHideBlock('chatuserrem_" + zuid + "','chatstatdisp_" + zuid + "');\"  onmouseout=\"ShowHideBlock('chatstatdisp_" + zuid + "','chatuserrem_" + zuid + "');\"><div id=\"chatuserrem_" + zuid + "\" class=\"hide\"><div class=\"chatimgclose\" onClick=\"ajaxSendRequest('" + Utils.contPath + "/deleteparticipant.do?chid=" + chid + "&participant=" + zuid + "');Hide('ch_" + chid + "_parti_" + zuid + "');\"><div class=\"pointer zpchatrmuser\"></div></div></div></div>";
			_11ef = "offlinechat2";
			_11f0 = "onlinechat2";
		}
		if (_11e4 == "0") {
			_11ee = _11ee + "<div id='chatstatdisp_" + zuid + "' class='block'><div id='userstat_" + zuid + "' class='" + _11ef + "'></div></div></div>";
		} else {
			if (_11e4 == "1") {
				_11ee = _11ee + "<div id='chatstatdisp_" + zuid + "' class='block'><div id='userstat_" + zuid + "' class='" + _11f0 + "'></div></div></div>";
			}
		}
		var _11f1 = _11e8 + _11e9 + _11ec + _11ed + _11ea + _11ee + _11eb;
		if (html.indexOf("ch_" + chid + "_parti_" + zuid) == -1 && _11db.indexOf("ch_" + chid + "_parti_" + zuid) == -1) {
			html = html + _11f1;
		}
	}
	if (lobj) {
		if (_11d9) {
			lobj.innerHTML = _11db + html;
		} else {
			lobj.innerHTML = html;
		}
	}
}
function deleteUser(chid, list) {
	for (var i = 0; i < list.length; i++) {
		var _11f5 = list[i];
		var zuid = _11f5["zuid"];
		Hide("ch_" + chid + "_parti_" + zuid);
		if (chid == Utils.chatId && zuid == Utils.zuid) {}
		if (zuid == Utils.zuid) {
			WindowHandler.removeWinById(chid);
		}
	}
}
function updateUserChatStatus(czuid, _11f8) {
	var usObj = document.getElementById("userstat_" + czuid);
	if (_11f8 == "0" && usObj) {
		usObj.className = "offlinechat3";
	} else {
		if (_11f8 == "1" && usObj) {
			usObj.className = "onlinechat3";
		}
	}
}
function populateMsgContent(chid, czuid, uname, dname, msg) {
	var html = document.getElementById("msgdisp_" + chid).innerHTML;
	var chrow = document.getElementById("chrow_" + chid);
	if (msg.indexOf("||zzzfileattachedzzz||") == -1) {
		msg = identifyLink(msg);
		msg = Smiley.replace(msg);
	}
	if (msg == "zzzdeletechatzzz") {
		if (chrow) {
			Hide("chrow_" + chid);
			if (chid == Utils.chatId && czuid == Utils.zuid) {}
		}
		if (czuid == Utils.zuid) {
			WindowHandler.removeWinById(chid);
		}
	} else {
		if (msg.indexOf("||zzzfileattachedzzz||") != -1) {
			var a = msg.split("||");
			var aurl = a[2];
			var _1203 = a[3];
			var _1204 = a[4];
			var _1205 = a[5];
			var otfca = document.getElementById("otfcattachdiv");
			if (otfca) {
				otfca.innerHTML = otfca.innerHTML + "<div class=\"h25\"><div class=\"fl pr3\"><img class=\"zpattachment\" border=\"0\" align=\"absmiddle\" vspace=\"4\" src=\"/images/spacer.gif\"/></div><div class=\"fl\"><a href=\"" + aurl + "\" class=\"tslink\">" + _1203 + "</a></div></div>";
			}
			if (_1204.indexOf("image") != -1) {
				initLightbox();
			}
		}
		var _1207;
		if (uname == Message.PREV_SENDER["ch" + chid]) {
			_1207 = "<dd class=\"wmstxt1\"><div class=\"msgtxt\">" + msg + "</div></dd>";
		} else {
			_1207 = getSenderBlock(uname, dname, getTimeFormat(new Date().getTime()), 0, chid);
			_1207 = _1207 + "<dd class=\"wmstxt1\"><div class=\"msgtxt\">" + msg + "</div></dd>";
		}
		if (czuid != Utils.zuid) {
			playSound("/sound/buzz.mp3");
		}
		Message.PREV_SENDER["ch" + chid] = uname;
		html = html + _1207;
		var _1208 = document.getElementById("msgdisp_" + chid);
		_1208.innerHTML = html;
		var tlen = html.length;
		if (tlen > 2250) {
			var h = (document.compatMode && document.compatMode != "BackCompat") ? document.documentElement: document.body;
			var sh = h.scrollHeight;
			window.scrollTo(0, sh);
		}
	}
}
function zpFormatMsg() {
	var cont = "padding-left:2px;font : " + Utils.fontBold + " " + Utils.fontItalic + " " + Utils.fontSize + "px  " + Utils.fontName + "; color : " + Utils.fontColor + "; text-decoration : " + Utils.fontUnderlined;
	return cont;
}
function updateEditorStyle() {
	var eObj = document.getElementById("editor");
	eObj.style.fontFamily = Utils.fontName;
	eObj.style.fontSize = Utils.fontSize + "px";
	if (Utils.fontBold == "bold") {
		eObj.style.fontWeight = Utils.fontBold;
	} else {
		eObj.style.fontWeight = "";
	}
	if (Utils.fontItalic == "italic") {
		eObj.style.fontStyle = Utils.fontItalic;
	} else {
		eObj.style.fontStyle = "";
	}
	eObj.style.color = Utils.fontColor;
	if (Utils.fontUnderlined == "underline") {
		eObj.style.textDecoration = Utils.fontUnderlined;
	} else {
		eObj.style.textDecoration = "none";
	}
}
function blinkChatLink(chid, czuid, uname, dname, msg) {
	var _1213 = "<img src=\"/images/msg_star.gif\" border=0 title=\"New Chat Message\" align=\"absmiddle\">";
	var _1214 = document.getElementById("newmsgpop_" + chid);
	if (_1214) {
		_1214.innerHTML = _1213;
	}
}
function playSound(_1215) {
	if (Utils.isChatSoundEnabled) {
		Sound.setTune(_1215);
		Sound.play();
	}
}
function updateChatTitleNotify(chid, title) {
	var _1218 = document.getElementById("ctitle_" + chid);
	if (_1218) {
		_1218.innerHTML = title;
	}
}
function ZPSendMessage(chid, msg) {
	if (trim(msg).length != 0) {
		var props = zpFormatMsg();
		WebMessanger.sendMessage(chid, msg, props);
	}
	editorFocus();
}
function editorFocus() {
	var eObj = document.getElementById("editor");
	if (eObj) {
		eObj.value = "";
		eObj.focus();
	}
}
function onlyEditorFocus() {
	updateEditorStyle();
	var eObj = document.getElementById("editor");
	if (eObj) {
		eObj.focus();
	}
}
function toggleFontStyle(comp) {
	if (comp == "bold") {
		if (Utils.fontBold == "bold") {
			Utils.fontBold = "";
			document.getElementById("boldIcon").src = "/images/chatwin/cwBtnBold.gif";
		} else {
			Utils.fontBold = "bold";
			document.getElementById("boldIcon").src = "/images/chatwin/cwBtnBold-h.gif";
		}
	}
	if (comp == "italic") {
		if (Utils.fontItalic == "italic") {
			Utils.fontItalic = "";
			document.getElementById("italicIcon").src = "/images/chatwin/cwBtnItalics.gif";
		} else {
			Utils.fontItalic = "italic";
			document.getElementById("italicIcon").src = "/images/chatwin/cwBtnItalics-h.gif";
		}
	}
	if (comp == "underline") {
		if (Utils.fontUnderlined == "underline") {
			Utils.fontUnderlined = "none";
			document.getElementById("udlineIcon").src = "/images/chatwin/cwBtnUnderline.gif";
		} else {
			Utils.fontUnderlined = "underline";
			document.getElementById("udlineIcon").src = "/images/chatwin/cwBtnUnderline-h.gif";
		}
	}
	if (comp == "fontsizedecrease") {
		if (parseInt(Utils.fontSize, 10) > 12) {
			Utils.fontSize = parseInt((parseInt(Utils.fontSize, 10)) - 1);
		}
	}
	if (comp == "fontsizeincrease") {
		if (parseInt(Utils.fontSize, 10) < 16) {
			Utils.fontSize = parseInt((parseInt(Utils.fontSize, 10)) + 1);
		}
	}
	if (Utils.fontSize == "11" || Utils.fontSize == "12" || Utils.fontSize == "13") {
		document.getElementById("smallFont").src = "/images/chatwin/cwBtnFontSmall-h.gif";
		document.getElementById("largeFont").src = "/images/chatwin/cwBtnFontBig.gif";
	}
	if (Utils.fontSize == "14" || Utils.fontSize == "15" || Utils.fontSize == "16") {
		document.getElementById("largeFont").src = "/images/chatwin/cwBtnFontBig-h.gif";
		document.getElementById("smallFont").src = "/images/chatwin/cwBtnFontSmall.gif";
	}
	onlyEditorFocus();
}
function changeFontStyle(comp, _1220) {
	if (comp == "fontcolor") {
		Utils.fontColor = _1220;
		document.getElementById("colorpicker").style.backgroundColor = _1220;
	}
	if (comp == "fontname") {
		Utils.fontName = _1220;
		document.getElementById("fontname").innerHTML = _1220;
		document.getElementById("fontname").style.fontFamily = _1220;
	}
	onlyEditorFocus();
}
function Combo() {}
Combo.showOptions = function(_1221, cObj) {
	var x = findPosX(cObj);
	var y = findPosY(cObj) + 16;
	var id = document.getElementById(_1221);
	if (id != null) {
		id.style.left = x + "px";
		id.style.top = (y + 2) + "px";
		if (id.style.display == "none") {
			Combo.show(_1221);
		} else {
			Combo.hide(_1221);
		}
	}
};
Combo.show = function(divId) {
	var id = document.getElementById(divId);
	if (id != null) {
		id.style.display = "block";
	}
};
Combo.hide = function(divId) {
	var id = document.getElementById(divId);
	if (id != null) {
		id.style.display = "none";
	}
};
function Option() {}
Option.changeBg = function(obj) {
	obj.style.background = "#888888";
	obj.style.color = "#FFFFFF";
};
Option.recoverBg = function(obj) {
	obj.style.background = "#EEEEEE";
	obj.style.color = "#000000";
};
function captureEnterEvent(ev, chid, msg) {
	if (ev.keyCode == "13") {
		ZPSendMessage(chid, msg);
	}
	return false;
}
function removeNewLine(e, obj) {
	if (e.keyCode == "13" || e.keyCode == "999") {
		if (lineTrim(obj.value) == "") {
			obj.value = "";
		}
	}
	return false;
}
function lineTrim(msg) {
	try {
		msg = msg.replace(/<p>/gi, "");
		msg = msg.replace(/<\/p>/gi, "");
		msg = msg.replace(/<a/gi, "<a target='blank'");
		msg = msg.replace(/\n/gi, "");
		msg = msg.replace(/^\s*$/gi, "");
	} catch(exp) {}
	return msg;
}
function appendSmileyToMsg(_1232) {
	document.getElementById("editor").value = document.getElementById("editor").value + " " + _1232;
	Combo.hide("smileypalette");

	onlyEditorFocus();
}
function identifyLink(msg) {
	try {
		var _1234 = /([a-z0-9]([a-z0-9_\-\.]*)@([a-z0-9_\-\.]*)(\.[a-z]{2,3}(\.[a-z]{2}){0,2}))/gi;
		var _1235 = /(((http)|(https):\/\/)([a-z0-9](([a-z0-9_\-\.]*)(\.[a-z]{2,3}(\.[a-z]{2}){0,2}))))/gi;
		msg = msg.replace(_1234, "<a href='mailto:$1' target='_blank'>$1</a>");
		msg = msg.replace(_1235, "<a href='$1' target='_blank'>$1</a>");
	} catch(exp) {}
	return msg;
}
function getTimeFormat(time) {
	var _1237 = "<div class=\"wmsemtydttm\">Time</div>";
	var time = "&nbsp;" + MyDate.formatTime(time);
	_1237 = _1237.replace(/Time/g, time);
	return _1237;
}
function getTemplate(_1238) {
	var _1239 = "<div id=\"senderformat\"><div class=\"sender0\">\t\t\t<div class=\"rounded-box\">\t\t\t\t<div class=\"top-left-corner\">\t\t\t\t\t<div class=\"top-left-inside\">&#8226;</div>\t\t\t\t</div>\t\t\t\t<div class=\"top-right-corner\">\t\t\t\t\t<div class=\"top-right-inside\">&#8226;</div>\t\t\t\t</div>\t\t\t\t<div class=\"middle\">\t\t\t\t\t<div class=\"senderclass\"> Username </div>";
	if (!_1238) {
		_1239 += "<div class=\"dttm\">Time</div>";
	}
	_1239 += "</div>\t\t\t</div>\t\t\t<div class=\"chatarrow_cont\">\t\t\t\t<div class=\"chatarrow\"><b class=\"rtop\"><b class=\"r1\"></b><b class=\"r2\"></b><b class=\"r3\"></b><b class=\"r4\"></b></b></div>\t\t\t</div>\t\t</div>\t      </div>";
	return _1239;
}
function getSenderBlock(_123a, nname, time, mod, chid, _123f) {
	var _123f = "<dt class=\"senderclass\"><span class=\"wms-ctime\">" + time + "</span>" + nname + "</dt>";
	var _1240 = "1";
	var time = "&nbsp;" + MyDate.formatTime(time);
	if (_123a == Message.PREV_SENDER["ch" + chid]) {
		_123a = "";
		_123f = _123f.replace(/dttm/g, "wmsemtydttm");
		_123f = _123f.replace(/middle/g, "wmsemtymiddle");
		_123f = _123f.replace(/senderclass/g, "wmsemtysender");
	} else {
		_123a = "<b>" + WebMessanger.getContactName(_123a, nname) + "</b> <span class='wmssays'>" + WmsResource.getRealValue("says") + "</span>";
		_123f = _123f.replace(/senderclass/g, "wmssender1");
	}
	_123f = _123f.replace(/Username/g, _123a);
	_123f = _123f.replace(/Time/g, time);
	return _123f;
}
function ChatStatus() {
	var list;
}
var slider = new slide();
slider.setObjName("slider");
ChatStatus.list = new Array("Offline", "Online", "Invisible", "Busy", "Idle");
var statusQueue = new Array();
var stnotifiertimer;
ChatStatus.checkStatusque = function(uname) {
	for (var i in statusQueue) {
		if (statusQueue[i]["uname"] == uname) {
			return true;
		}
	}
	return false;
};
ChatStatus.notify = function(uname, _1245, dname, smsg, czuid, pname, _124a) {
	if (_1245 < 0 || _1245 == 2 || _1245 > 4) {
		return;
	}
	var _124b = document.getElementById("statusnotifier");
	if (_124b.style.display != "none" && !ChatStatus.checkStatusque(uname)) {
		var obj = new Object();
		obj["uname"] = uname;
		obj["status"] = _1245;
		obj["smsg"] = smsg;
		obj["dname"] = dname;
		obj["zuid"] = czuid;
		obj["pname"] = pname;
		obj["msgfor"] = _124a;
		statusQueue.push(obj);
		return;
	}
	_124b.style.display = "block";
	try {
		_124b.style.zIndex = WindowHandler.zoom + 5;
	} catch(e) {
		_124b.style.zIndex = 50;
	}
	ChatStatus.construct(_124b, uname, dname, _1245, smsg, czuid, pname, _124a);
	var _124d = 90;
	try {
		if (_124b.clientHeight > 88) {
			_124d = _124b.clientHeight + 2;
		}
	} catch(e) {}
	slider.setHeightParams(0, _124d, 0, 0);
	slider.down("statusnotifier");
	stnotifiertimer = setTimeout("ChatStatus.close()", 5000);
};
ChatStatus.close = function() {
	slider.finishShrinkWith("ChatStatus.hide()");
	slider.up("statusnotifier");
};
ChatStatus.clear = function() {
	if (stnotifiertimer != null && stnotifiertimer != undefined) {
		clearTimeout(stnotifiertimer);
		ChatStatus.close();
	}
};
ChatStatus.hide = function() {
	var _124e = document.getElementById("statusnotifier");
	_124e.style.height = "";
	_124e.style.display = "none";
	if (statusQueue.length > 0) {
		var obj = statusQueue.splice(0, 1);
		var uname = obj[0]["uname"];
		var _1251 = obj[0]["status"];
		var smsg = obj[0]["smsg"];
		var dname = obj[0]["dname"];
		var czuid = obj[0]["zuid"];
		var pname = obj[0]["pname"];
		var _1256 = obj[0]["msgfor"];
		ChatStatus.notify(uname, _1251, dname, smsg, czuid, pname, _1256);
	}
};
ChatStatus.construct = function(_1257, uname, dname, _125a, smsg, czuid, pname, _125e) {
	var _125f = "";
	if (_125a >= 0 && _125a != 2) {
		if (_125e == "Dropbox") {
			var _1260 = pname.split("||");
			var _1261 = _1260[0];
			var _1262 = _1260[1];
			_125f = "<div class=\"dbstatuspop\"> <div class=\"dbstatuspopdiv\"> <div class=\"dbstatuspopdivinner\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"> <tbody> <tr> <td width=\"50\" valign=\"middle\"> <img border=\"0\" align=\"absmiddle\" src=\"/images/db-icon.png\"> </td> <td width=\"207\" valign=\"top\" class=\"tasklistTitle pl10 pt3\"> " + smsg + " <div class=\"bugown3 pt5 lh20\"> <span class=\"txtSmallBlack\"><i>Dropbox Account:</i></span>&nbsp;<span>" + dname + "</span><br /> <span class=\"txtSmallBlack\"><i>Project:</i></span>&nbsp;<span>" + _1262 + "</span><br /> <span class=\"txtSmallBlack\"><i>Folder:</i></span>&nbsp;</span>&nbsp;<span>" + _1261 + "</span> </div> </td> </tr> </tbody> </table> </div> </div> </div>";
		} else {
			_125f = "<div class=\"statuspop\"><div class=\"statuspopdiv\"><div class=\"statuspopdivinner\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td valign=\"top\" width=\"50\"><img width=\"50\" height=\"50\" border=\"0\" align=\"absmiddle\" src=\"" + _IAMURL + "file?ID=" + czuid + "&t=user&height=50&width=50\"></td><td valign=\"top\" class=\"tasklistTitle pl5\" width=\"222\">" + dname + "<div class=\"txtDisabled pt2\">" + pname + "</div></td></tr><tr><td colspan=\"2\" class=\"bugown3 pt3 lh18\"><span class=\"txtSmallBlack\"><i>" + _125e + "</i> :</span> " + smsg + "</td></tr></table></div></div></div>";
		}
	}
	if (ie) {
		_1257.onclick = function() {
			WebMessanger.imchat(uname, dname);
		};
	} else {
		_1257.setAttribute("onclick", "WebMessanger.imchat('" + uname + "','" + dname + "')");
	}
	_1257.innerHTML = _125f;
};
ChatStatus.setTitle = function(msg) {
	var _1264 = document.getElementsByTagName("head")[0].getElementsByTagName("title")[0];
	if (ie) {
		document.title = msg;
	} else {
		_1264.text = msg;
	}
};
ChatStatus.setDefaultTitle = function() {
	var title = Resource.getRealValue("Zoho_Chat") + " - " + Resource.getRealValue("make_group_decisions_faster");
	ChatStatus.setTitle(title);
};
function Collaboration() {}
Collaboration.handleUserList = function(chid, list) {};
Collaboration.handleMessage = function(chid, uname, dname, msg) {};
Collaboration.handlePresence = function(chid, uname, dname, _126f) {};
Collaboration.join = function(chid) {
	WebMessanger.joinCollab(chid);
};
Collaboration.quit = function(chid) {
	WebMessanger.quit(chid);
};
Collaboration.serverup = function() {
	if (Utils.chatId != "-1" && Utils.chatId != "-2") {
		WebMessanger.join(Utils.chatId);
	}
};
Collaboration.serverdown = function() {};
Collaboration.handleZohoMessage = function(prd, msg, url, _1275, _1276, _1277, att) {
	return false;
};
Collaboration.handleCustomMessage = function(msg) {
	var _127a = msg["CMESGFOR"];
	var _127b = msg["DTITLE"];
	var czuid = msg["ZUID"];
	var pid = msg["PID"];
	if (_127a == "Dropbox") {
		var pName = msg["PNAME"];
		var uname = msg["UNAME"];
		var dname = msg["DNAME"];
		ChatStatus.notify(uname, "3", dname, _127b, czuid, pName, _127a);
	}
	if (_127a == "newtitle") {
		var chid = msg["CHID"];
		var coid = msg["COID"];
		var pmcid = msg["PMCID"];
		var chobj = document.getElementById("ctitle_" + chid);
		if (chobj == null) {
			var _1285 = "<table border=\"0\" cellpadding=\"2\" cellspacing=\"0\" width=\"100%\"><tr id=\"chrow_\"" + chid + "><td style=\"padding-bottom:5px;\"><div id=\"dispTitle\"" + chid + "><a href=\"javascript:;\" onclick=\"ajaxShowTab('" + Utils.contPath + "/showproject.do?projId=" + pid + "&toview=chat&coid=" + coid + "&pmchatid=" + pmcid + "','projectcontent');\" class=\"navLink\"><span id=\"ctitle_" + chid + "\">" + _127b + "</span></a>&nbsp;</div></td><td width=\"18px\"><span id=\"newmsgpop_\"" + chid + ">&nbsp;<span></td></tr></table>";
			var _1286 = document.getElementById("otherchatdiv");
			if (_1286 != null) {
				var _1287 = _1286.innerHTML;
			}
		}
	} else {
		if (_127a == "Status" || _127a == "Forum") {
			var pName = msg["PNAME"];
			var uname = msg["UNAME"];
			var dname = msg["DNAME"];
			if (czuid != Utils.zuid) {
				ChatStatus.notify(uname, "3", dname, _127b, czuid, pName, _127a);
			}
		} else {
			if (_127a == "versionchange" || _127a == "newversion") {
				var vpid = msg["PID"];
				if (czuid == Utils.zuid) {
					var _1289 = msg["URLTOREFRESH"];
					var affo = document.getElementById("allfilesfolderopen");
					if (affo) {
						window.location.reload();
					}
				}
			}
		}
	}
};
Collaboration.handleCrossProductMessage = function(prd, msg) {};
Collaboration.onConnect = function() {};
Collaboration.openURL = function(url, _128e, _128f, prd) {
	return true;
};
Collaboration.handleCollabAdd = function(uname, dname, time, chid) {};
Collaboration.handleCollabDelete = function(uname, dname, time, chid) {};
Collaboration.handleUserAdd = function(chid, _129a) {};
Collaboration.handleUserDeleted = function(chid, _129c) {};
Collaboration.handleIsOnline = function(_129d) {};
Collaboration.handleLogout = function(_129e) {};
Collaboration.goOffline = function() {};
Collaboration.handleChatNotifyMessage = function(chid, _12a0, _12a1) {};
function CustomChat() {}
CustomChat.handleUserList = function(chid, _12a3) {
	try {
		CustomChat.transcript(chid, _TZOFFSET);
		populateUsers(chid, _12a3["users"], true);
		var mobj = document.getElementById("msgdisp_" + chid);
		var pmobj = document.getElementById("msgdisp_" + Utils.prevchatId);
		if (Utils.chatId == chid || Utils.chatId == "-2") {
			if (mobj) {} else {
				if (pmobj) {} else {
					if (Utils.isChatPopOut) {} else {
						setTimeout(function() {
							ajaxSendRequest(Utils.contPath + "/fetchchatdetails.do?chatid=" + chid);
						},
						10000);
					}
				}
			}
		} else {
			if (chid != Utils.chatId && Utils.chatId != "-2") {
				setTimeout(function() {
					ajaxSendRequest(Utils.contPath + "/fetchchatdetails.do?chatid=" + chid);
				},
				10000);
			}
		}
	} catch(e) {}
};
CustomChat.handleMessage = function(_12a6) {
	try {
		var chid = _12a6["chid"];
		var czuid = _12a6["zuid"];
		var uname = _12a6["sender"];
		var dname = WebMessanger.getContactName(_12a6["sender"], _12a6["dname"]);
		var msg = _12a6["msg"];
		var _12ac = _12a6["addinfo"];
		if (chid == Utils.chatId) {
			populateMsgContent(chid, czuid, uname, dname, msg);
		} else {
			blinkChatLink(chid, czuid, uname, dname, msg);
		}
	} catch(e) {}
};
CustomChat.handlePresence = function(chid, uname, czuid, dname, _12b1) {
	updateUserChatStatus(czuid, _12b1);
};
CustomChat.handleUserAdded = function(chid, list) {
	populateUsers(chid, list, true);
};
CustomChat.handleUserDeleted = function(chid, list) {
	deleteUser(chid, list);
};
CustomChat.handleTranscript = function(chid, _12b7) {
	if (Utils.chatId != chid) {
		return;
	}
	var _12b8 = document.getElementById("msgdisp_" + chid);
	if (_12b8) {
		_12b8.innerHTML = _12b7;
	}
	var tlen = _12b7.length;
	if (tlen > 2250) {
		var h = (document.compatMode && document.compatMode != "BackCompat") ? document.documentElement: document.body;
		var sh = h.scrollHeight;
		window.scrollTo(0, sh);
	}
	initLightbox();
};
CustomChat.join = function(chid) {
	WebMessanger.join(chid);
};
CustomChat.transcript = function(chid, _12be) {
	WebMessanger.setTimeZoneOffset(_12be);
	WebMessanger.customtranscript(chid, true);
};
CustomChat.quit = function(chid) {
	WebMessanger.quit(chid);
};
CustomChat.handleAttachmentMessage = function(chid, _12c1) {};
CustomChat.handleTitleUpdated = function(chid, title) {
	updateChatTitleNotify(chid, title);
};
CustomChat.isActive = function(chid, _12c5) {
	var mobj = document.getElementById("msgdisp_" + chid);
	var pmobj = document.getElementById("msgdisp_" + Utils.prevchatId);
	if (Utils.chatId == chid || Utils.chatId == "-2") {
		if (mobj) {
			return true;
		} else {
			if (pmobj) {
				return true;
			} else {
				if (Utils.isChatPopOut) {
					return true;
				} else {
					return false;
				}
			}
		}
	}
	return false;
};
function slide() {
	this.freq = 1;
	this.speed = 4;
	this.up = up;
	this.down = down;
	this.expand = expand;
	this.shrink = shrink;
	this.finish = finish;
	this.finishExpandWith = finishExpandWith;
	this.finishShrinkWith = finishShrinkWith;
	this.setSpeed = setSpeed;
	this.setHeightParams = setHeightParams;
	this.maxHeight = "50",
	this.minHeight = "0",
	this.exHeight = this.minHeight,
	this.shrinkHeight = this.maxHeight,
	this.expandTimer = -1,
	this.shrinkTimer = -1;
	this.showObj,
	this.hideObj;
	this.finishExp = "",
	this.finishShrink = "";
	this.objName;
	this.setObjName = setObjName;
	function down(id) {
		if (document.getElementById(id) != null) {
			this.showObj = document.getElementById(id);
		} else {
			return;
		}
		this.exHeight = "0";
		this.showObj.style.display = "block";
		this.showObj.style.height = "0px";
		if (this.shrinkTimer != -1) {
			if (this.showObj.id == this.hideObj.id) {
				clearTimeout(this.shrinkTimer);
				this.exHeight = this.shrinkHeight - this.speed;
			}
		}
		this.expand();
	}
	function up(id) {
		if (document.getElementById(id) != null) {
			this.hideObj = document.getElementById(id);
		} else {
			return;
		}
		this.shrinkHeight = this.hideObj.offsetHeight;
		this.shrink();
		if (this.expandTimer != -1) {
			if (this.showObj.id == this.hideObj.id) {
				clearTimeout(this.expandTimer);
				this.shrinkHeight = this.exHeight - this.speed;
			}
		}
	}
	function expand() {
		this.exHeight = parseInt(this.exHeight) + this.speed;
		if (this.showObj == null) {
			return;
		}
		this.showObj.style.height = this.exHeight + "px";
		if (parseInt(this.exHeight) >= this.maxHeight) {
			clearTimeout(this.expandTimer);
			this.expandTimer = "-1";
			this.exHeight = this.minHeight;
			this.finish("expand");
			return;
		}
		this.expandTimer = setTimeout(this.objName + ".expand()", this.freq);
	}
	function shrink() {
		this.shrinkHeight = parseInt(this.shrinkHeight) - this.speed;
		if (this.hideObj == null) {
			return;
		}
		if (parseInt(this.shrinkHeight) <= this.minHeight) {
			clearTimeout(this.shrinkTimer);
			this.shrinkTimer = "-1";
			this.shrinkHeight = this.maxHeight;
			this.hideObj.style.height = this.minHeight + "px";
			this.finish("shrink");
			return;
		}
		this.hideObj.style.height = this.shrinkHeight + "px";
		this.shrinkTimer = setTimeout(this.objName + ".shrink()", this.freq);
	}
	function setHeightParams(min, max, _12cc, _12cd) {
		if (min != undefined) {
			this.minHeight = min;
		}
		if (max != undefined) {
			this.maxHeight = max;
		}
		if (_12cc != undefined) {
			this.exHeight = _12cc;
		}
		if (_12cd != undefined) {
			this.shrinkHeight = _12cd;
		}
	}
	function finishExpandWith(_12ce) {
		this.finishExp = _12ce;
	}
	function finishShrinkWith(_12cf) {
		this.finishShrink = "" + _12cf;
	}
	function finish(_12d0) {
		if (_12d0 == "expand") {
			if (this.finishExp != "") {
				setTimeout(this.finishExp + "", 0);
				this.finishExp = "";
			}
		} else {
			if (this.finishShrink != "") {
				setTimeout(this.finishShrink + "", 0);
				this.finishShrink = "";
			}
		}
	}
	function setSpeed(val) {
		this.speed = val;
	}
	function setObjName(_12d2) {
		this.objName = _12d2;
	}
}
function instantiateSlider(_12d3) {
	var _12d4 = new slide();
	_12d4.setObjName(_12d3);
	return _12d4;
}
var isOpera = navigator.userAgent.indexOf("Opera") > -1;
var isIE = navigator.userAgent.indexOf("MSIE") > 1 && !isOpera;
var isMoz = navigator.userAgent.indexOf("Mozilla/5.") == 0 && !isOpera;
var popupon = false;
var totalmatch = 0;
var matchlist = "";
var matcharray;
var position = -1;
var id_array = new Array();
String.prototype.trim = function() {
	var x = this;
	x = x.replace(/^\s*(.*)/, "$1");
	x = x.replace(/(.*?)\s*$/, "$1");
	return x;
};
String.prototype.lastIndexOf = function(comp) {
	var x = this;
	var laind = -1;
	var index = x.indexOf(comp);
	while (index != -1) {
		laind = index;
		index = x.indexOf(comp, index + comp.length);
	}
	return laind;
};
function getElementHeight(obj) {
	return obj.clientHeight;
}
function getElementWidth(obj) {
	return obj.clientWidth;
}
function findPosX(obj) {
	var _12dd = 0;
	if (obj.offsetParent) {
		while (obj.offsetParent) {
			_12dd += obj.offsetLeft;
			obj = obj.offsetParent;
		}
	} else {
		if (obj.x) {
			_12dd += obj.x;
		}
	}
	return _12dd;
}
function findPosY(obj) {
	var _12df = 0;
	if (obj.offsetParent) {
		while (obj.offsetParent) {
			_12df += obj.offsetTop;
			obj = obj.offsetParent;
		}
	} else {
		if (obj.y) {
			_12df += obj.y;
		}
	}
	return _12df;
}
function setIds(area, ch) {
	var _12e2 = findPosY(area);
	var _12e3 = getElementHeight(area);
	var _12e4 = getElementWidth(area);
	var _12e5 = parseInt(_12e2) + parseInt(_12e3) + 2;
	var _12e6 = findPosX(area);
	var _12e7 = document.getElementById(ch);
	var body = "";
	for (i = 0; i < totalmatch; i++) {
		var oc = "onmousedown='appendPos(\"" + area.id + "\",\"" + ch + "\")' onmouseover='switchClassOn(this);' onmouseout='switchClassOff(this);'";
		if (i == position) {
			body = body + "<p id='pos" + i + "' " + oc + " class='dropdown-on'>" + matcharray[i] + "</p>";
		} else {
			body = body + "<p id='pos" + i + "' " + oc + " class='dropdown-off'>" + matcharray[i] + "</p>";
		}
	}
	_12e7.innerHTML = body;
	popupon = true;
	_12e7.style.top = _12e5;
	_12e7.style.left = _12e6;
	_12e7.style.width = _12e4;
	_12e7.style.visibility = "Visible";
}
function appendPos(n1, ch1) {
	fillarea(document.getElementById(n1), ch1);
}
function switchClassOff(el) {
	el.className = "dropdown-off";
}
function switchClassOn(el) {
	var par = el.parentNode;
	var prev = document.getElementById("pos" + position);
	switchClassOff(prev);
	el.className = "dropdown-on";
	var _i = el.id;
	var pos = _i.substring(3, _i.length);
	position = parseInt(pos);
}
function adjustpopup(n, ch) {
	var _12f4 = document.getElementById(ch);
	var ids = _12f4.getElementsByTagName("p");
	for (i = 0; i < ids.length; i++) {
		ids[i].className = (i == position) ? "dropdown-on": "dropdown-off";
	}
	var l = n.value.length;
	if (isIE) {
		var r = n.createTextRange();
		r.moveEnd("character", l);
		r.moveStart("character", l);
		r.select();
	}
	if (isMoz) {
		n.setSelectionRange(l, l);
	}
	if (isOpera) {
		n.setSelection(l, l);
	}
}
function hidepopup(ch) {
	var _12f9 = document.getElementById(ch);
	_12f9.innerHTML = "";
	popupon = false;
	_12f9.style.visibility = "Hidden";
}
function fillarea(area, ch) {
	var _12fc = document.getElementById(ch);
	var ids = _12fc.getElementsByTagName("p");
	var id = ids[position].innerHTML;
	if (area.value == "") {
		area.value = id;
	} else {
		var _12ff = area.value;
		var _1300 = _12ff.lastIndexOf(",");
		if (_1300 == -1) {
			area.value = id;
		}
	}
	hidepopup(ch);
}
function autolist(n, e, ch, _1304, arr) {
	if (n.value == "") {
		hidepopup(ch);
		matcharray = new Array();
		return 0;
	}
	if (e.type == "keydown") {
		if (popupon == true) {
			if (e.keyCode == 9) {
				n.backspace = true;
				fillarea(n, ch);
			}
			return 0;
		}
	}
	if (e.type == "blur") {
		if (popupon == true) {
			hidepopup(ch);
		}
	}
	if (e.type == "keyup") {
		if (popupon) {
			if (e.keyCode == 27) {
				hidepopup(ch);
				return 0;
			}
			if (e.keyCode == 13) {
				n.backspace = true;
				fillarea(n, ch);
				return 0;
			}
			if (e.keyCode == 33) {}
			if (e.keyCode == 34) {}
			if (e.keyCode == 36) {}
			if (e.keyCode == 35) {}
			if (e.keyCode == 38) {
				if (position > -1) {
					if (position != 0) {
						position--;
					}
				}
				adjustpopup(n, ch);
				return 0;
			}
			if (e.keyCode == 40) {
				if (position < totalmatch - 1) {
					position++;
				}
				adjustpopup(n, ch);
				return 0;
			}
			if (e.keyCode == 37) {}
			if (e.keyCode == 39) {}
		}
		var _1306 = n.value;
		var _1307 = _1306.lastIndexOf(",");
		if (_1307 > -1) {
			_1306 = _1306.substring(_1307 + 1, _1306.length);
		}
		_1306 = _1306.trim();
		if (_1306 == "" || _1306 == "\n") {
			return 0;
		}
		matcharray = new Array();
		totalmatch = 0;
		try { (_1304) ? (matcharray = eval(_1304 + "('" + _1306 + "')")) : void(0);
		} catch(e) {
			hidepopup(ch);
		}
		totalmatch = matcharray.length;
		if (matcharray && matcharray.length > 0) {
			position = 0;
			setIds(n, ch);
		} else {
			hidepopup(ch);
		}
	}
	return 0;
}
tabularfilter = function(_1308, _1309) {
	var _130a = jQuery("table#" + _1309);
	jQuery("#filter").keyup(function() {
		jQuery.uiTableFilter(_130a, this.value);
		var elem = jQuery("form[name=" + _1308 + "] table[id=" + _1309 + "] tr:visible");
		var len = elem.length;
	}).click(function() {
		jQuery("#filter").val("");
	});
	jQuery("#filter-form").submit(function() {
		_130a.find("tbody > tr:visible > td:eq(1)").mousedown();
		return false;
	}).focus();
	jQuery("#clearsearch").click(function() {
		jQuery("#filter").val("");
		jQuery("#filter").focus();
		jQuery.uiTableFilter(_130a, "");
		var elem = jQuery("form[name=" + _1308 + "] table[id=" + _1309 + "] tr:visible");
		var len = elem.length;
	});
};
function Sound() {}
var loop = 1;
var isSoundActive = false;
var IS_FLASH_EXIST = false;
var tune = "sound/notify.mp3";
Sound.defaulttune = "sound/notify.mp3";
Sound.Mapping = new Object();
Sound.Mapping = {
	"buzz": "sound/notify.mp3",
	"newchat": "sound/notify.mp3",
	"cal": "sound/notify.mp3",
	"feeds": "sound/notify.mp3",
	"chatmsg": "sound/notify.mp3",
	"email": "sound/notify.mp3",
	"online": "sound/online.mp3",
	"offline": "sound/offline.mp3",
	"support": "sound/notifysupport.mp3"
};
Sound.init = function() {
	var _130f = 0;
	var _1310 = 0;
	var _1311 = false;
	var isMS = false;
	if (navigator.plugins && navigator.plugins.length) {
		x = navigator.plugins["Shockwave Flash"];
		if (x) {
			_130f = 2;
			if (x.description) {
				y = x.description;
				_1310 = y.match(/\d{1,2}/);
			}
		} else {
			_130f = 1;
		}
		if (navigator.plugins["Shockwave Flash 2.0"]) {
			_130f = 2;
			_1310 = 2;
		}
	} else {
		if (navigator.mimeTypes && navigator.mimeTypes.length) {
			x = navigator.mimeTypes["application/x-shockwave-flash"];
			if (x && x.enabledPlugin) {
				_130f = 2;
			} else {
				_130f = 1;
			}
		} else {
			isMS = true;
		}
	}
	if (isMS) {
		var pid = new Array("ShockwaveFlash.ShockwaveFlash.9", "ShockwaveFlash.ShockwaveFlash.8.5", "ShockwaveFlash.ShockwaveFlash.8", "ShockwaveFlash.ShockwaveFlash.7");
		for (var p = 0; p < pid.length; p++) {
			try {
				var aobj = new ActiveXObject(pid[p]);
				if (aobj != null) {
					_1311 = true;
					break;
				}
			} catch(e) {}
		}
	} else {
		if (_130f == 2 && _1310 > 7) {
			_1311 = true;
		}
	}
	IS_FLASH_EXIST = _1311;
	if (IS_FLASH_EXIST) {
		document.getElementById("soundplayer").style.display = "block";
	}
};
Sound.getPlayer = function() {
	if (!IS_FLASH_EXIST) {
		return;
	}
	if (window.document["projectsplayer"]) {
		return window.document["projectsplayer"];
	}
	if (navigator.appName.indexOf("Microsoft Internet") == -1) {
		if (document.embeds && document.embeds["projectsplayer"]) {
			return document.embeds["projectsplayer"];
		}
	} else {
		return document.getElementById("projectsplayer");
	}
};
Sound.play = function() {
	try {
		if (!IS_FLASH_EXIST) {
			return;
		}
		var _1316 = Sound.getPlayer();
		_1316.SetVariable("_root.loops", loop);
		_1316.TCallLabel("_root.$listener", "setloops");
		_1316.SetVariable("_root.$listener.fpath", tune);
		_1316.TCallLabel("_root.$listener", "playfile");
		Sound.setTune("sound/notify.mp3");
	} catch(e) {}
};
Sound.playCCall = function() {
	try {
		if (!IS_FLASH_EXIST) {
			return;
		}
		var _1317 = Sound.getPlayer();
		_1317.SetVariable("_root.loops", 10);
		_1317.TCallLabel("_root.$listener", "setloops");
		_1317.SetVariable("_root.$listener.fpath", "sound/notifysupport.mp3");
		_1317.TCallLabel("_root.$listener", "playfile");
		Sound.setTune("sound/notify.mp3");
	} catch(e) {}
};
Sound.playTune = function(type) {
	try {
		if (Sound.Mapping[type] != undefined) {
			Sound.setTune(Sound.Mapping[type]);
		} else {
			Sound.setTune(Sound.defaulttune);
		}
		Sound.play();
	} catch(e) {}
};
Sound.stop = function() {
	try {
		if (!IS_FLASH_EXIST) {
			return;
		}
		var _1319 = Sound.getPlayer();
		_1319.TCallLabel("_root.$listener", "stopSound");
	} catch(e) {}
};
Sound.setTune = function(_131a) {
	tune = _131a;
};
Sound.active = function() {
	isSoundActive = true;
};
Sound.inactive = function() {
	isSoundActive = false;
};
Sound.status = function() {
	return isSoundActive;
};
function winonfocus() {
	try {
		if (INIT) {
			Sound.inactive();
		}
	} catch(e) {}
}
function winonblur() {
	try {
		Sound.active();
	} catch(e) {}
}
document.onblur = winonblur;
window.onfocus = winonfocus;