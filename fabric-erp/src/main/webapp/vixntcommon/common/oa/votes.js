Date.prototype.format = function(format) {
	var o = {
	"M+" : this.getMonth() + 1, //month 
	"d+" : this.getDate(), //day 
	"h+" : this.getHours(), //hour 
	"m+" : this.getMinutes(), //minute 
	"s+" : this.getSeconds(), //second 
	"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter 
	"S" : this.getMilliseconds()
	//millisecond 
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
$(document)
		.ready(function() {

			pageSetUp();

			"use strict";

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

			function listFilter(list) {// header is any element, list is an unordered list
				// create and add the filter form to the header

				$.filter_input.change(function() {
					var filter = $(this).val();
					if (filter) {
						// this finds all links in a list that contain the input,
						// and hide the ones not containing the input while showing the ones that do
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

			$.chat_body.animate({
				scrollTop : $.chat_body[0].scrollHeight
			}, 500);

			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();

			var hdr = {
			left : 'title',
			center : 'month,agendaWeek,agendaDay',
			right : 'prev,today,next'
			};

			var initDrag = function(e) {
				// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
				// it doesn't need to have a start or end

				var eventObject = {
				title : $.trim(e.children().text()), // use the element's text as the event title
				description : $.trim(e.children('span').attr('data-description')),
				icon : $.trim(e.children('span').attr('data-icon')),
				className : $.trim(e.children('span').attr('class'))
				// use the element's children as the event class
				};
				// store the Event Object in the DOM element so we can get to it later
				e.data('eventObject', eventObject);

				// make the event draggable using jQuery UI
				e.draggable({
				zIndex : 999,
				revert : true, // will cause the event to go back to its
				revertDuration : 0
				//  original position after the drag
				});
			};

			var addEvent = function(title, priority, description, icon) {
				title = title.length === 0 ? "Untitled Event" : title;
				description = description.length === 0 ? "No Description" : description;
				icon = icon.length === 0 ? " " : icon;
				priority = priority.length === 0 ? "label label-default" : priority;

				var html = $('<li><span class="' + priority + '" data-description="' + description + '" data-icon="' +
		            icon + '">' + title + '</span></li>')
						.prependTo('ul#external-events').hide().fadeIn();

				$("#event-container").effect("highlight", 800);

				initDrag(html);
			};

			/* initialize the external events
			 -----------------------------------------------------------------*/

			$('#external-events > li').each(function() {
				initDrag($(this));
			});

			$('#add-event')
					.click(function() {
						var title = $('#title').val(), priority = $('input:radio[name=priority]:checked').val(), description = $('#description')
								.val(), icon = $('input:radio[name=iconselect]:checked').val();

						addEvent(title, priority, description, icon);
					});

			/* initialize the calendar
			 -----------------------------------------------------------------*/

			$('#calendar').fullCalendar({

			header : hdr,
			buttonText : {
			prev : '<i class="fa fa-chevron-left"></i>',
			next : '<i class="fa fa-chevron-right"></i>'
			},

			editable : true,
			droppable : true, // this allows things to be dropped onto the calendar !!!

			drop : function(date, allDay) { // this function is called when something is dropped

				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');

				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);

				// assign it the date that was reported
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;

				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}

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
					element.find('.fc-event-title').append("<i class='air air-top-right fa " + event.icon +
		                    " '></i>");
				}
			},

			windowResize : function(event, ui) {
				$('#calendar').fullCalendar('render');
			}
			});

			/* hide default buttons */
			$('.fc-header-right, .fc-header-center').hide();

			$('#calendar-buttons #btn-prev').click(function() {
				$('.fc-button-prev').click();
				return false;
			});

			$('#calendar-buttons #btn-next').click(function() {
				$('.fc-button-next').click();
				return false;
			});

			$('#calendar-buttons #btn-today').click(function() {
				$('.fc-button-today').click();
				return false;
			});

			$('#mt').click(function() {
				$('#calendar').fullCalendar('changeView', 'month');
			});

			$('#ag').click(function() {
				$('#calendar').fullCalendar('changeView', 'agendaWeek');
			});

			$('#td').click(function() {
				$('#calendar').fullCalendar('changeView', 'agendaDay');
			});

			$('#sign').on('click', function() {
				var date = new Date();
				$.SmartMessageBox({
				"title" : date.format('hh:mm:ss'),
				"content" : date.format('yyyy年MM月dd日'),
				"buttons" : "[签离][签到]"
				}, function(a) {
					if (a == '签到') {

					} else {

					}
				})
			});
			$('#invitation').on('click', function() {
				$.SmartMessageBox({
				"title" : '请输入同事Email',
				"content" : '<input type="text" style="color:#000">',
				"buttons" : "[邀请]"
				}, function(a) {
				});
			});

		})