// JavaScript Document
// 下拉框

$(function() {
	$(".select_box").click(function(event) {
		event.stopPropagation();
		$(this).find(".option").toggle();
		$(this).parent().siblings().find(".option").hide();
	});
	$(document).click(function(event) {
		var eo = $(event.target);
		if ($(".select_box").is(":visible") && eo.attr("class") != "option" && !eo.parent(".option").length)
			$('.option').hide();
	});
	/* 赋值给文本框 */
	$(".option a").click(function() {
		var value = $(this).text();
		$(this).parent().siblings(".select_txt").text(value);
		$("#select_value").val(value)
	});
});

$(function() {
	$('.header_l').click(function() {
		if ($(this).hasClass('vis')) {
			$(this).removeClass('vis');
			$('.side_navigation').animate({
				left : '-100%'
			})
			$('html,body').css({
				overflow : 'visible'
			})
		} else {
			$(this).addClass('vis');
			$('.side_navigation').animate({
				left : 0
			})
			$('html,body').css({
				overflow : 'hidden'
			})
		}
	})
})

$(function() {
	$('.side_navigation li').click(function() {
		$(this).addClass('cur').siblings('li').removeClass('cur');
	})
})

$(function() {
	$('.chat_icon').click(function() {
		if ($('.chat_more').css('display') == 'none') {
			$('.chat_more').show();
		} else {
			$(this).find('p').hide().removeClass('cur_indMenu');
			$('.chat_more').hide();
		}
	});

	var facTuc_tag = $('.facTuc_tag');
	var anoTuc_exp = $('.anoTuc_exp');
	var chat_bot = $('.chat_bot dl');
	facTuc_tag.click(function() {
		if ($(this).hasClass('vis')) {
			$(this).removeClass('vis');
			anoTuc_exp.removeClass('vis');
			chat_bot.removeClass('vis');
		} else {
			$(this).addClass('vis');
			anoTuc_exp.addClass('vis');
			chat_bot.addClass('vis');
		}
	});

	$('.close_exp').click(function() {
		facTuc_tag.removeClass('vis');
		anoTuc_exp.removeClass('vis');
		chat_bot.removeClass('vis');
	});

})

// 城市选择，右边字母导航
$(function() {
	$('.chaCity_list2_r a').click(function() {
		var val = $(this).text().toUpperCase().trim();
		$(this).addClass('vis').siblings('a').removeClass('vis');
		$('h1').each(function() {
			if ($(this).text().toUpperCase().trim() == val) {
				$('html,body').animate({
					scrollTop : $(this).offset().top
				})
			}
		})
	})
	$(window).scroll(function() {
		if ($(window).scrollTop() >= 60) {
			$('.chaCity_list2_r').css({
				top : 0
			})
		}
		if ($(window).scrollTop() < 60) {
			// alert($(this).scrollTop())
			$('.chaCity_list2_r').css({
				top : 130
			})
		}
	})
})

$(function() {
	$('.conRoom dl dt').click(function() {
		$(this).addClass('cur').parent().siblings('dl').find('dt').removeClass('cur');
	});
	$('.workLog h3 span,.subJob dt span').click(function() {
		if ($(this).hasClass('cur')) {
			$(this).removeClass('cur');
		} else {
			$(this).addClass('cur');
		}

	})

});

$(function() {
	var tab_menu_li = $('.tab_menu li');
	$('.tab_con .common:gt(0)').hide();

	tab_menu_li.click(function() {
		var index = $(this).index() + 1;
		$(this).addClass('selected').siblings().removeClass('selected');

		var tab_menu_li_index = tab_menu_li.index(this);
		$('.tab_con .common').eq(tab_menu_li_index).show().siblings().hide();
	}).click(function() {
		$(this).addClass('hover');
	}, function() {
		$(this).removeClass('hover');
	});

});

$(function() {
	$('.shear_Click').click(function() {
		if ($('.share').css('display') == 'none') {
			$('.share').show(0);
		} else {
			$('.share').hide(0);
		}
		return false;
	})
	$(window).click(function() {
		$('.share').hide(0);
	})

	$('.share ul li').click(function() {
		$(this).addClass('cur').siblings('li').removeClass('cur');
	})
})

// dynList
$(function() {
	var doc = $(document).height();
	var body = window.innerHeight;

	if (doc > body) {
		$('.leftSlider').show(0);
	}
})

// index leftNav
function getsessionId() {
	alert('fdafdsafdsafsafdasfa');
}
$(function() {
	var header_l = $('.header_l');
	var side = $('.side_navigation');
	var index_box = $('.index_box');
	var cw = $(window).width();
})
