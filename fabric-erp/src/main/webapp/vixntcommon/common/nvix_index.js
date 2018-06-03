/*function loadContent(url) {
	$.ajax({
	url : url,
	cache : false,
	success : function(html) {
		$("#mainContent").html(html);
	}
	});
}*/
$(document).ready(function() {

	/*loadContent('mid_index', '${nvix}/nvixnt/redirectAction!goListContent.action');*/

	$('#left-panel a').click(function() {
		$('#left-panel li.active').removeClass('active');
		$(this).parent().addClass('active');
	});
});

$(function() {
	// DO NOT REMOVE : GLOBAL FUNCTIONS!
	pageSetUp();

	// 加载首页面
	// loadOcMenu('mid_index','${vix}/oc/u/user/ocUserLoginAction!goContent.action');
	/*
	 * PAGE RELATED SCRIPTS
	 */

	$(".js-status-update a").click(function() {
		var selText = $(this).text();
		var $this = $(this);
		$this.parents('.btn-group').find('.dropdown-toggle').html(selText + ' <span class="caret"></span>');
		$this.parents('.dropdown-menu').find('li').removeClass('active');
		$this.parent().addClass('active');
	});

	// check and uncheck
	$('.todo .checkbox > input[type="checkbox"]').click(function() {
		var $this = $(this).parent().parent().parent();

		if ($(this).prop('checked')) {
			$this.addClass("complete");

			// remove this if you want to undo a check list once checked
			// $(this).attr("disabled", true);
			$(this).parent().hide();

			// once clicked - add class, copy to memory then remove and add to
			// sortable3
			$this.slideUp(500, function() {
				$this.clone().prependTo("#sortable3").effect("highlight", {}, 800);
				$this.remove();
				countTasks();
			});
		} else {
			// insert undo code here...
		}

	})
	// count tasks
	function countTasks() {

		$('.todo-group-title').each(function() {
			var $this = $(this);
			$this.find(".num-of-tasks").text($this.next().find("li").size());
		});

	}

	/*
	 * RUN PAGE GRAPHS
	 */

	/* TAB 1: UPDATING CHART */
	// For the demo we use generated data, but normally it would be coming from
	// the server
	var data = [], totalPoints = 200, $UpdatingChartColors = $("#updating-chart").css('color');

	function getRandomData() {
		if (data.length > 0)
			data = data.slice(1);

		// do a random walk
		while (data.length < totalPoints) {
			var prev = data.length > 0 ? data[data.length - 1] : 50;
			var y = prev + Math.random() * 10 - 5;
			if (y < 0)
				y = 0;
			if (y > 100)
				y = 100;
			data.push(y);
		}

		// zip the generated y values with the x values
		var res = [];
		for (var i = 0; i < data.length; ++i)
			res.push([ i, data[i] ])
		return res;
	}

	// setup control widget
	var updateInterval = 1500;
	$("#updating-chart").val(updateInterval).change(function() {

		var v = $(this).val();
		if (v && !isNaN(+v)) {
			updateInterval = +v;
			$(this).val("" + updateInterval);
		}

	});

	// setup plot
	var options = {
	yaxis : {
	min : 0,
	max : 100
	},
	xaxis : {
	min : 0,
	max : 100
	},
	colors : [ $UpdatingChartColors ],
	series : {
		lines : {
		lineWidth : 1,
		fill : true,
		fillColor : {
			colors : [ {
				opacity : 0.4
			}, {
				opacity : 0
			} ]
		},
		steps : false

		}
	}
	};

	data_array = {
	"US" : 4977,
	"AU" : 4873,
	"IN" : 3671,
	"BR" : 2476,
	"TR" : 1476,
	"CN" : 146,
	"CA" : 134,
	"BD" : 100
	};

	/*
	 * FULL CALENDAR JS
	 */

	if ($("#calendar").length) {
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();

		var calendar = $('#calendar').fullCalendar({

		editable : true,
		draggable : true,
		selectable : false,
		selectHelper : true,
		unselectAuto : false,
		disableResizing : false,

		header : {
		left : 'title', // ,today
		center : 'prev, next, today',
		right : 'month, agendaWeek, agenDay' // month, agendaDay,
		},

		select : function(start, end, allDay) {
			var title = prompt('Event Title:');
			if (title) {
				calendar.fullCalendar('renderEvent', {
				title : title,
				start : start,
				end : end,
				allDay : allDay
				}, true // make the event "stick"
				);
			}
			calendar.fullCalendar('unselect');
		},

		events : [ {
		title : 'All Day Event',
		start : new Date(y, m, 1),
		description : 'long description',
		className : [ "event", "bg-color-greenLight" ],
		icon : 'fa-check'
		}, {
		title : 'Long Event',
		start : new Date(y, m, d - 5),
		end : new Date(y, m, d - 2),
		className : [ "event", "bg-color-red" ],
		icon : 'fa-lock'
		}, {
		id : 999,
		title : 'Repeating Event',
		start : new Date(y, m, d - 3, 16, 0),
		allDay : false,
		className : [ "event", "bg-color-blue" ],
		icon : 'fa-clock-o'
		}, {
		id : 999,
		title : 'Repeating Event',
		start : new Date(y, m, d + 4, 16, 0),
		allDay : false,
		className : [ "event", "bg-color-blue" ],
		icon : 'fa-clock-o'
		}, {
		title : 'Meeting',
		start : new Date(y, m, d, 10, 30),
		allDay : false,
		className : [ "event", "bg-color-darken" ]
		}, {
		title : 'Lunch',
		start : new Date(y, m, d, 12, 0),
		end : new Date(y, m, d, 14, 0),
		allDay : false,
		className : [ "event", "bg-color-darken" ]
		}, {
		title : 'Birthday Party',
		start : new Date(y, m, d + 1, 19, 0),
		end : new Date(y, m, d + 1, 22, 30),
		allDay : false,
		className : [ "event", "bg-color-darken" ]
		}, {
		title : 'Smartadmin Open Day',
		start : new Date(y, m, 28),
		end : new Date(y, m, 29),
		className : [ "event", "bg-color-darken" ]
		} ],

		eventRender : function(event, element, icon) {
			if (!event.description == "") {
				element.find('.fc-event-title').append("<br/><span class='ultra-light'>" + event.description + "</span>");
			}
			if (!event.icon == "") {
				element.find('.fc-event-title').append("<i class='air air-top-right fa " + event.icon + " '></i>");
			}
		}
		});

	}
	;

	/* hide default buttons */
	$('.fc-header-right, .fc-header-center').hide();

	// calendar prev
	$('#calendar-buttons #btn-prev').click(function() {
		$('.fc-button-prev').click();
		return false;
	});

	// calendar next
	$('#calendar-buttons #btn-next').click(function() {
		$('.fc-button-next').click();
		return false;
	});

	// calendar today
	$('#calendar-buttons #btn-today').click(function() {
		$('.fc-button-today').click();
		return false;
	});

	// calendar month
	$('#mt').click(function() {
		$('#calendar').fullCalendar('changeView', 'month');
	});

	// calendar agenda week
	$('#ag').click(function() {
		$('#calendar').fullCalendar('changeView', 'agendaWeek');
	});

	// calendar agenda day
	$('#td').click(function() {
		$('#calendar').fullCalendar('changeView', 'agendaDay');
	});

	/*
	 * CHAT
	 */

	$.filter_input = $('#filter-chat-list');
	$.chat_users_container = $('#chat-container > .chat-list-body')
	$.chat_users = $('#chat-users')
	$.chat_list_btn = $('#chat-container > .chat-list-open-close');
	$.chat_body = $('#chat-body');

	/*
	 * LIST FILTER (CHAT)
	 */

	// custom css expression for a case-insensitive contains()
	jQuery.expr[':'].Contains = function(a, i, m) {
		return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
	};

	function listFilter(list) {// header is any element, list is an unordered
		// list
		// create and add the filter form to the header

		$.filter_input.change(function() {
			var filter = $(this).val();
			if (filter) {
				// this finds all links in a list that contain the input,
				// and hide the ones not containing the input while showing the
				// ones that do
				$.chat_users.find("a:not(:Contains(" + filter + "))").parent().slideUp();
				$.chat_users.find("a:Contains(" + filter + ")").parent().slideDown();
			} else {
				$.chat_users.find("li").slideDown();
			}
			return false;
		}).keyup(function() {
			// fire the above change event after every letter
			$(this).change();

		});

	}

	// on dom ready
	listFilter($.chat_users);

	// open chat list
	$.chat_list_btn.click(function() {
		$(this).parent('#chat-container').toggleClass('open');
	})

	/*
	 * $.chat_body.animate({ scrollTop : $.chat_body[0].scrollHeight }, 500);
	 */

});
