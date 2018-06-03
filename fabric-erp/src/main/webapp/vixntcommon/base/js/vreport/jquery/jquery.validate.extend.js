jQuery.validator.addMethod("isIdCardNo", function(value, element) {   
	  return this.optional(element) || isIdCardNo(value);   
	}, ""); 
jQuery.validator.addMethod("isMobile", function(value, element) {       
    var length = value.length;   
    var mobile = /^(((1[0-9]{2})|(1[0-9]{2}))+\d{8})$/;   
    return this.optional(element) || (length == 11 && mobile.test(value));       
}, "");
jQuery.validator.addMethod("isTel", function(value, element) {       
    var tel = /^\d{3,4}-?\d{7,9}$/;    //电话号码格式010-12345678   
    return this.optional(element) || (tel.test(value));       
}, ""); 
//增加身份证验证
function isIdCardNo(num) {
	var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4,
			2, 1);
	var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3",
			"2");
	var varArray = new Array();
	var intValue;
	var lngProduct = 0;
	var intCheckDigit;
	var intStrLen = num.length;
	var idNumber = num;
	// initialize
	if ((intStrLen != 15) && (intStrLen != 18)) {
		return false;
	}
	// check and set value
	for (i = 0; i < intStrLen; i++) {
		varArray[i] = idNumber.charAt(i);
		if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
			return false;
		} else if (i < 17) {
			varArray[i] = varArray[i] * factorArr[i];
		}
	}
	if (intStrLen == 18) {
		//check date
		var date8 = idNumber.substring(6, 14);
		if (isDate8(date8) == false) {
			return false;
		}
		// calculate the sum of the products
		for (i = 0; i < 17; i++) {
			lngProduct = lngProduct + varArray[i];
		}
		// calculate the check digit
		intCheckDigit = parityBit[lngProduct % 11];
		// check last digit
		if (varArray[17] != intCheckDigit) {
			return false;
		}
	} else { //length is 15
		//check date
		var date6 = idNumber.substring(6, 12);
		if (isDate6(date6) == false) {
			return false;
		}
	}
	return true;
}
function isDate6(sDate) {
	if (!/^[0-9]{6}$/.test(sDate)) {
		return false;
	}
	var year, month, day;
	year = sDate.substring(0, 4);
	month = sDate.substring(4, 6);
	if (year < 1700 || year > 2500)
		return false
	if (month < 1 || month > 12)
		return false
	return true
}

function isDate8(sDate) {
	if (!/^[0-9]{8}$/.test(sDate)) {
		return false;
	}
	var year, month, day;
	year = sDate.substring(0, 4);
	month = sDate.substring(4, 6);
	day = sDate.substring(6, 8);
	var iaMonthDays = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ]
	if (year < 1700 || year > 2500)
		return false
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
		iaMonthDays[1] = 29;
	if (month < 1 || month > 12)
		return false
	if (day < 1 || day > iaMonthDays[month - 1])
		return false
	return true
}


$.validator.messages = {
    required: "<label class='error'>字段不能为空</label>",
    remote: "请输入这些项",
    email: "<label class='error'>请输入一个有效的email地址</label>",
    url: "请输入一个有效的Url地址",
    date: "请输入一个有效的日期",
    dateISO: "Please enter a valid date (ISO).",
    dateDE: "Bitte geben Sie ein gültiges Datum ein.",
    number: "<label class='error'>请输入一个有效的数字</label>",
    numberDE: "Bitte geben Sie eine Nummer ein.",
    digits: "<label class='error'>只能输入数字</label>",
    creditcard: "请输入有效信用卡号码",
    equalTo: "请再次输入相同的值",
    accept: "请输入一个有效的扩展名.",
    maxlength: $.format("<label class='error'>最多能输入{0}个字符</label>"),
    minlength: $.format("<label class='error'>请输入至少 {0}个字符</label>"),
    rangelength: $.format("请输入从{0} 到 {1} 字符长度"),
    range: $.format("请输入从 {0} 到 {1}."),
    max: $.format("请输入一个值小于等于 {0}."),
    min: $.format("请输入一个值大于等于 {0}."),
    isIdCardNo:"<label class='error'>身份证号码不合法</label>"
};