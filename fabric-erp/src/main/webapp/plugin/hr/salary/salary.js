		
	/**
	 * 转义
	 * @param str
	 * @returns {String}
	 */
	function html_encode(str)  {   
		var s = ""; 
	    if (str.length == 0) return ""; 
	    s = str.replace(/&/g, "&amp;"); 
	    s = s.replace(/</g, "&lt;"); 
	    s = s.replace(/>/g, "&gt;"); 
	    s = s.replace(/ /g, "&nbsp;"); 
	    s = s.replace(/\'/g, "&#39;"); 
	    s = s.replace(/\"/g, "&quot;"); 
	        s = s.replace(/\n/g, "<br/>"); 
	    return s; 
	}   
	
	/**
	 * 解码
	 * @param str
	 * @returns {String}
	 */
	function html_decode(str) {   
		var s = ""; 
	    if (str.length == 0) return ""; 
	    s = str.replace(/&amp;/g, "&"); 
	    s = s.replace(/&lt;/g, "<"); 
	    s = s.replace(/&gt;/g, ">"); 
	    s = s.replace(/&nbsp;/g, " "); 
	    s = s.replace(/&#39;/g, "\'"); 
	    s = s.replace(/&quot;/g, "\""); 
	    s = s.replace(/<br\/>/g, "\n"); 
	    return s;   
	}   

	/**
	 * 回调使用
	 */
	function salaryProjectItemSelect(){
		/* var selectCode = $("#selectIdCode").val();
		if(selectCode!=''){
			return selectCode;
		}
		return ""; */
		var resArray = new Array();
		var itemType = $("#selectIdItemType").val();
		if(itemType!=''){
			//选择的不是根节点的类型
			var selectItemName = $("#selectIdName").val();
			var selectItemCode = $("#selectIdCode").val();
			//return itemType+"-"+selectItemCode+"-"+selectItemName;
			resArray.push(itemType);
			resArray.push(selectItemCode);
			resArray.push(selectItemName);
		}
		return resArray;
	}
	
	/**
	 * 点击工资项选择事件
	 */
	function selectSalaryProjectItem(){
		var resArray = salaryProjectItemSelect();
		if(resArray.length>0){
			var itemType =  resArray[0];
			var itemCode =  resArray[1];
			var itemName =  resArray[2];
			//alert("123a3aa");
			//var inserHtml = $("<span dataType='");//var inserHtml
			//var inserHtml = "<span class='salaryItemSelect' dataType='" + itemType +"' dataCode='" + itemCode +"'>"+ itemName +"</span>";
			//$('#gs').append('<span class="text" itemCode="'+$(this).val()+'">'+ $(this).text() + '</span>');
			//self.insertHtml($(inserHtml).html());
			var inserHtml = "<span class='text' dataType='" + itemType +"' dataCode='" + itemCode +"'>"+ itemName +"</span>";
			$('#gs').append(inserHtml);
		}
	}
	
	/**
	 * 一般符号事件
	 */
	function clickSalarySymbol(){
		//$('#gs').append('<span class="text" itemCode="'+$(this).val()+'">'+ $(this).text() + '</span>');
		//$('#gs').append('<span class="text" itemCode="'+$(this).val()+'">'+ $(this).text() + '</span>');
		//var inserHtml = "<span class='text' dataType='normalSymbol'>"+ $(this).text() +"</span>";
		//var inserHtml = $(this).text();
		var insertHtml = "<span class='text' dataType='" + $(this).attr("class") + "'>"+ $(this).text() +"</span>";
		$('#gs').append(insertHtml);
	}