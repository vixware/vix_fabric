jQuery.fn.extend({
	getE6Value : function() {
		var view_ = this.attr("view_");
		if(view_=="textfield")
			return this.val();
		if(view_=="numberfield")
			return this.val();
		if(view_=="textareafield")
			return this.val();
		if(view_=="htmlfield")
			return this.val();
		if(view_=="datefield")
			return this.val();
		if(view_=="comboxfield")
			return this.val();
		if(view_=="checkboxfield"){
			var value = "";
			$(this).children("input[type='checkbox']").each(function(){
				if(this.checked){
					value = value + this.value + ","
				}
			})
			if(value.length>0)
				value = value.substring(0, value.length-1);
			return value;
		}
		if(view_=="radiofield"){
			var value = "";
			$(this).children("input[type='radio']").each(function(){
				if(this.checked){
					value = value + this.value + ","
				}
			})
			if(value.length>0)
				value = value.substring(0, value.length-1);
			return value;
		}
		if(view_=="e6_exttree"){
			var name = $(this).attr("name");
			var value = $("input[name='"+name+"']").val();
			return value;
		}
		if(view_=="e6_extgrid"){
			var name = $(this).attr("name");
			var value = $("input[name='"+name+"']").val();
			return value;
		}
		if(view_=="e6_exticon"){
			var name = $(this).attr("name");
			var value = $("input[name='"+name+"']").val();
			return value;
		}
	},
	//电商调用
	validForm:function(obj){
		obj = obj||{};
		if(obj.tip==true){
			var tipTile = obj.tipTile||{};
			for(var i in tipTile){
				$('#'+i).attr("tiptitle_",tipTile[i]);
			}
			$("input[tiptitle_]").each(function(){
				$(this).blur(function(){
					$(this).valid();
				})
				if($(this).attr("tiptitle_")==""||typeof($(this).attr("tiptitle_"))=="undefined")
					return;
				$(this).focus(function(){
					var tipHtml = '<div class="tip_normal"><div class="text">'+$(this).attr("tiptitle_")+'</div></div>';
					$(this).after(tipHtml);
					$(this.form).find("div[for='" + this.id + "']").hide();
				});
				$(this).blur(function(){
					$(this).parent().children(".tip_normal").remove();
					$(this.form).find("div[for='" + this.id + "']").show();
				})
			});
		}
		this.validate({
			errorClass:'tip_error',
			success:'tip_success',
			highlight:function(element, errorClass, validClass){
				$(element).removeClass(errorClass);
				$(element.form).find("div[for='" + element.id + "']").addClass(errorClass).removeClass('tip_success');
			},
			unhighlight:function(element, errorClass, validClass){
				$(element).removeClass(validClass);
				$(element.form).find("div[for='" + element.id + "']").addClass('tip_success');
			},
			rules:obj.rules||{},
			messages:obj.messages||{},
			errorElement:'div',
			onkeyup:false
		});
	},
	//运营调用
	initValid:function(obj){
		this.validate({
	   		errorClass:'tip_error',
	  		success:'tip_success',
	  		ignore:'',
	  		highlight:function(element, errorClass, validClass){
	  			$(element).removeClass(errorClass);
	  			$(element.form).find("label[for=" + element.name + "]").addClass(errorClass).removeClass('tip_success');
	  		},
	  		unhighlight:function(element, errorClass, validClass){
	  			$(element).removeClass(validClass);
	  			$(element.form).find("label[for=" + element.name + "]").addClass('tip_success');
	  		},
	   		onkeyup:false,
			onfocusout:function(item){
	   			$(item).valid();
	   		}
		});
	}
});

jQuery.extend({
　　renderE6:function(obj,fangwei){
		fangwei = fangwei||window.document;
		if(typeof(obj)=="string"){
			
		}else{
			$("input[view_='datefield']",fangwei).each(function(){
				/*
				$(this).focus(function(){
					var mindate_ =$(this).attr("mindate_");
					var param = {};
					if(typeof(mindate_)=='string'&&mindate_!=''){
						param.minDate = mindate_;
					}
					param.onpicked = $(this).get(0).onpicked||function(){};
					WdatePicker(param);
					$(this).blur();
				});
				*/
			});
			$("div[view_='checkboxfield']",fangwei).each(function(){
				var checkbox = new Eform.checkbox(this);
				if(checkbox.isLoadUrl()){
					checkbox.loadData();
				}else{
					checkbox.setValue();
				}
				checkbox = null;
			});
			$("div[view_='radiofield']",fangwei).each(function(){
				var radio = new Eform.radio(this);
				if(radio.isLoadUrl()){
					radio.loadData();
				}else{
					radio.setValue();
				}
				radio = null;
			});
			$("div[view_='e6_uploadfield']",fangwei).each(function(){
				var id = $(this).attr("id");
				var readonly_ = $(this).attr("readonly_");
				new Plc.form.mulUploadItem({
					name:$(this).attr("name"),
					id:id+"_upload",
					disabled:readonly_=='true',
					value:$(this).attr("value_"),
					renderTo:id
				});
			});
			$("div[view_='e6_uploadresource']",fangwei).each(function(){
				var id = $(this).attr("id");
				var readonly_ = $(this).attr("readonly_");
				new Plc.form.uploadImg({
					id:id+"_upload",
					name:$(this).attr("name"),
					value:$(this).attr("value_"),
					disabled:readonly_=='true',
					renderTo:id,
					height:50
				});
				var required_ = $(this).attr("required_");
				if(typeof(required_)!="undefined"&&required_!=null){
					$("#"+id+"_upload_input").addClass("required")
				}
			});
			$("div[view_='e6_exttree']",fangwei).each(function(){
				var id = $(this).attr("id");
				var singleCheck = $(this).attr("singleCheck_");
				var rootCheck_ = $(this).attr("rootCheck_");
				var name = $(this).attr("name");
				var value_ =  $(this).attr("value_");
				var rawtext_ = $(this).attr("rawtext_");
				var readonly_ = $(this).attr("readonly_");
				var rootText_ = $(this).attr("rootText_");
				if(value_=='-1'){
					rawtext_ = '根节点';
					if(typeof(rootText_)=="string"&&rootText_.length>0)
						rawtext_ = rootText_;
				}
				new Plc.form.extendItem({
					renderTo:id,
					etype:17,
					value:value_,
					text:rawtext_,
					rootText:rootText_,
					name:name,
					disabled:readonly_=='true',
					singleCheck:singleCheck=='1',
					rootCheck:rootCheck_=='1',
					url:$(this).attr("dataurl_")
				})
			});
			$("div[view_='e6_extgrid']",fangwei).each(function(){
				var id = $(this).attr("id");
				var name = $(this).attr("name");
				var value_ =  $(this).attr("value_");
				var rawtext_ = $(this).attr("rawtext_");
				var singleCheck = $(this).attr("singleCheck_");
				var readonly_ = $(this).attr("readonly_");
				var width_ = $(this).attr("width_")||"400";
				var required =  $(this).attr("required")||'';
				
				new Plc.form.extendItem({
					id:id,
					renderTo:id,
					etype:singleCheck=='1'?18:19,
					value:value_,
					text:rawtext_,
					style:{
						width:parseInt(width_)
					},
					required:required,
					name:name,
					disabled:readonly_,
					url:$(this).attr("dataurl_")
				});
			});
			$("div[view_='e6_exticon']",fangwei).each(function(){
				var id = $(this).attr("id");
				var name = $(this).attr("name");
				var value_ =  $(this).attr("value_");
				var rawtext_ = $(this).attr("rawtext_");
				var singleCheck = $(this).attr("singleCheck_");
				var readonly_ = $(this).attr("readonly_");
				var width_ = $(this).attr("width_")||"400";
				var required =  $(this).attr("required")||'';
				new Plc.form.extendItem({
					id:id,
					renderTo:id,
					etype:20,
					value:value_,
					text:rawtext_,
					style:{
						width:parseInt(width_)
					},
					required:required,
					name:name,
					disabled:readonly_
				});
			});
			$("select[view_='comboxfield']",fangwei).each(function(){
				var combox = new Eform.combox(this);
				if(combox.isLoadUrl()){
					combox.loadData();
				}else{
					combox.setValue();
				}
				combox = null;
			});
		}
　　},
	showDateField:function(item){
		var mindate_ =$(item).attr("mindate_");
		var maxdate_ =$(item).attr("maxdate_");
		var startDate_ = $(item).attr("startDate_");
		var param = {};
		if(typeof(mindate_)=='string'&&mindate_!=''){
			param.minDate = mindate_;
		}
		if(typeof(maxdate_)=='string'&&maxdate_!=''){
			param.maxDate = maxdate_;
		}
		if(typeof(startDate_)=='string'&&startDate_!=''){
			param.startDate = startDate_;
		}
		param.onpicked = $(item).get(0).onpicked||function(){};
		WdatePicker(param);
		$(item).blur();
	},
	error:function(obj){
		var o = obj||{};
		o.title = o.title||"提示";
		o.text =  o.text||"失败";
		var win = $(".jquery_error").get(0);
		$(".title",win).html(o.title);
		$(".content",win).html(o.text);
		$(".alert_img",win).attr("src",getJsPath()+"/resources/ydcaib-portal/images/e_ico4.gif");
		$(win).floatdiv("middle").show();
	},
	info:function(obj){
		var o = obj||{};
		o.title = o.title||"提示";
		o.text =  o.text||"成功";
		var win = $(".jquery_error").get(0);
		$(".title",win).html(o.title);
		$(".content",win).html(o.text);
		$(".alert_img",win).attr("src",getJsPath()+"/resources/ydcaib-portal/images/e_ico1.gif");
		$(win).floatdiv("middle").show();
	},
	confirm:function(obj){
		var o = obj||{};
		o.title = o.title||"提示";
		o.text =  o.text||"确认提交";
		var win = $(".jquery_confirm").get(0);
		o.success = o.success||function(win){};
		$(".title",win).html(o.title);
		$(".content",win).html(o.text);
		$(win).floatdiv("middle").show();
		$(".success",win).get(0).onclick = function(){
			o.success(win);
		};
		$(".failure",win).get(0).onclick = function(){
			$(win).hide();
			$("#popup_background").remove();
		};
	},
	hideConfirm:function(){
		$(".jquery_confirm").hide();
		$("#popup_background").remove();
	},
	hideByClass:function(className){
		$("."+className).hide();
		$("#popup_background").remove();
	},
	reloadCode:function(item){
		var src = $(item).attr("src");
		$(item).attr("src",src);
	},
	changeRegion:function(srcItem,tarItem,regionData){
		$("#"+tarItem).html("");
		$("#"+tarItem).append("<option value=''>请选择</option>")
		for(var i=0;i<E6_region[regionData][$("#"+srcItem).val()].length;i++){
			var region = E6_region[regionData][$("#"+srcItem).val()][i];
			$("#"+tarItem).append("<option value='"+region.id+"'>"+region.name+"</option>")
		}
	},
	getAllRegionName:function(value){
		if(value!=null&&value!=''){
			var region2Text = "";
			var region2Value = "";
			var region1Value = "";
			var region3Text = "";
			for(var i in E6_region['region3']){
				for(var j=0;j<E6_region['region3'][i].length;j++){
					var t3 = E6_region['region3'][i][j];
					if(t3.id==value){
						region3Text = t3.name;
						region2Value = i;
						break;
					}
				}
				if(region2Value!='')
					break;
			}
			for(var i in E6_region['region2']){
				for(var j=0;j<E6_region['region2'][i].length;j++){
					var t2 = E6_region['region2'][i][j];
					if(t2.id==region2Value){
						region2Text = t2.name;
						region1Value = i;
						break;
					}
				}
				if(region1Value!='')
					break;
			}
			return $.getRegionName(region1Value)+"-"+region2Text+"-"+region3Text;
		}else
			return "";
	},
	getRegionName:function(value){
		for(var i=0;i<E6_region['region1'].length;i++){
			var region = E6_region['region1'][i];
			if(region.id==value)
				return region.name;
		}
	},
	initRegion1:function(value,retionId){
		$("#"+retionId).append("<option value=''>请选择</option>")
		for(var i=0;i<E6_region['region1'].length;i++){
			var region = E6_region['region1'][i];
			$("#"+retionId).append("<option value='"+region.id+"'>"+region.name+"</option>")
		}
		$("#"+retionId).val(value);
	},
	initRegion:function(value,region1,region2,region3){
		$("#"+region1).append("<option value=''>请选择</option>")
		for(var i=0;i<E6_region['region1'].length;i++){
			var region = E6_region['region1'][i];
			$("#"+region1).append("<option value='"+region.id+"'>"+region.name+"</option>")
		}
		if(value!=null&&value!=''){
			var region2Value = "";
			var region1Value = "";
			for(var i in E6_region['region3']){
				for(var j=0;j<E6_region['region3'][i].length;j++){
					var t3 = E6_region['region3'][i][j];
					if(t3.id==value){
						region2Value = i;
						break;
					}
				}
				if(region2Value!='')
					break;
			}
			for(var i in E6_region['region2']){
				for(var j=0;j<E6_region['region2'][i].length;j++){
					var t2 = E6_region['region2'][i][j];
					if(t2.id==region2Value){
						region1Value = i;
						break;
					}
				}
				if(region1Value!='')
					break;
			}
			$("#"+region1).val(region1Value);
			$.changeRegion(region1,region2,'region2');
			$("#"+region2).val(region2Value);
			$("#"+region2).val(region2Value);
			$.changeRegion(region2,region3,'region3');
			$("#"+region3).val(value);
		}
	}
});


