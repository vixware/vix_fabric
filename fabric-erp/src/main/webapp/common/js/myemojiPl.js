;
(function($, window, document, undefined) {
	'use strict';
	$.fn.myEmoji = function(options) {
		var defaults = {
			emojiconfig: {
				tieba: {
					path: "../assets/js/arclist/",
					maxNum: 69,
					file: ".gif",
					placeholder: ":{title}:",
					title: {
						1: "呵呵",
						2: "哈哈",
						3: "吐舌",
						4: "啊",
						5: "酷",
						6: "怒",
						7: "开心",
						8: "汗",
						9: "泪",
						10: "黑线",
						11: "鄙视",
						12: "不高兴",
						13: "真棒",
						14: "钱",
						15: "疑问",
						16: "阴脸",
						17: "吐",
						18: "咦",
						19: "委屈",
						20: "花心",
						21: "呼~",
						22: "笑脸",
						23: "冷",
						24: "太开心",
						25: "滑稽",
						26: "勉强",
						27: "狂汗",
						28: "乖",
						29: "睡觉",
						30: "惊哭",
						31: "生气",
						32: "惊讶",
						33: "喷",
						34: "爱心",
						35: "心碎",
						36: "玫瑰",
						37: "礼物",
						38: "彩虹",
						39: "星星月亮",
						40: "太阳",
						41: "钱币",
						42: "灯泡",
						43: "茶杯",
						44: "蛋糕",
						45: "音乐",
						46: "haha",
						47: "胜利",
						48: "大拇指",
						49: "弱",
						50: "OK",
						51: "生气",
						52: "惊讶",
						53: "喷",
						54: "爱心",
						55: "心碎",
						56: "玫瑰",
						57: "礼物",
						58: "彩虹",
						59: "星星月亮",
						60: "太阳",
						61: "钱币",
						62: "灯泡",
						63: "茶杯",
						64: "蛋糕",
						65: "音乐",
						66: "haha",
						67: "胜利",
						68: "大拇指",
						69: "弱",
						
					}
				}
				//, AcFun: {
				// 	name: "AcFun表情",
				// 	path: "img/AcFun/",
				// 	maxNum: 54,
				// 	file: ".png"
				// }
			},
			postFunction: function() {
				alert(InputText.html());
				console.log(InputText.html());
			}
		};
		var opts = $.extend(defaults, options);
		var emojiconfig = opts.emojiconfig;
		var plBox = $(this);
		var InputBox = plBox.find('.Input_Box');
		var faceDiv = plBox.find('.faceDiv');
		var InputText = InputBox.find('.Input_text');
		var InputFoot = InputBox.find('.Input_Foot');
		var imgBtn = InputFoot.find('.imgBtn');

		imgBtn.click(
			function() {
				var emojiContainer = faceDiv.find('.emoji_container');
				if (emojiContainer.children().length <= 0) {
					faceDiv.css({
						width: emojiContainer.attr("em_width") + 'px',
						display: 'block'
					});
					for (var emojilist in emojiconfig) {
						emojiContainer.append('<section class="for_' + emojilist + '"></section>');
						//faceDiv.find('.emoji_tab').append('<a href="#!" data-target="for_' + emojilist + '">' + emojiconfig[emojilist].name + '</a>');
						for (var i = 0; i <= emojiconfig[emojilist].maxNum; i++) {
							if (emojiContainer.find('.for_' + emojilist) !== undefined) {
								emojiContainer.find('.for_' + emojilist).append('<a href="#!" class="_img"><img src="' + emojiconfig[emojilist].path + i + emojiconfig[emojilist].file + '" title="' + (emojiconfig[emojilist].title == undefined ? '' : emojiconfig[emojilist].title[i]) + '" /></a>');
							}
						}
					}
					faceDiv.find('.emoji_container section')[0].style.display = 'block';
					//faceDiv.find('.emoji_tab a')[0].className += 'active';
					faceDiv.find('.emoji_container ._img').on('click', function() {
						if (InputText[0].nodeName === 'DIV') {
							InputText.append(this.innerHTML);
							changePinglun(emojiContainer.attr("len"));
						} else {
							InputText.append('[' + $(this).find('img').attr('title')+']');
						}

					});
					/*faceDiv.find('.emoji_tab a').on('click', function() {
						$(this).parent().prev().find('section').hide();
						faceDiv.find('.emoji_container .' + $(this).attr('data-target')).show();
						faceDiv.find('.emoji_tab a').removeClass('active');
						this.className += ' active';
					});*/
				} else {
					faceDiv.toggle();
				}
			}
		);

		InputFoot.find('.postBtn').on('click', opts.postFunction);
		$(document).on('click', function(e) {
			if ((faceDiv.find($(e.target)).length) <= 0 && (InputBox.find($(e.target)).length <= 0)) {
				faceDiv.hide();
			}
		});
	};
})(jQuery, window, document);