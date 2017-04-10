/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
jQuery.extend(jQuery.validator.messages, {
        required: "必选字段",
		remote: "名称已存在",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
		minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
		rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
		min: jQuery.validator.format("请输入一个最小为 {0} 的值")
});

jQuery.extend(jQuery.validator.defaults, {
    errorElement: "span",
    errorPlacement: function(error, element) { //指定错误信息位置
		if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
		var eid = element.attr('name'); //获取元素的name属性
		error.appendTo(element.parent()); //将错误信息添加当前元素的父结点后面
		} else {
		error.insertAfter(element);
		} 
	}
    
});
jQuery.validator.addMethod("account",function(value, element, param){
	if(param){
		return /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){5,24}$/g.test(value);
	}
	return true;
},"账号格式有误。必须以字母开头，可包含字母数字及 . _的6至25个字符");

jQuery.validator.addMethod("phone",function(value, element, param){
	if(param){
		//((((13[0-9])|(14[57])|(15[^4,\D])|(18[0-9]))\d{8})|(0\d{2,3}-\d{7,8}))
		return /^[\d-]{5,20}$/g.test(value);
	}
	return true;
},"电话格式有误");
jQuery.validator.addMethod("cellphone",function(value, element, param){
	if(param){
		return /^(((13[0-9])|(14[57])|(15[^4,\D])|(18[0-9]))\d{8})$$/g.test(value);
	}
	return true;
},"请输入正确手机号");

jQuery.validator.addMethod("zzs",function(value, element, param){
	if(param){
		return /^[0-9]*[1-9][0-9]*$/g.test(value);
	}
	return true;
},"请输入正整数");

jQuery.validator.addMethod("character",function(value, element, param){
	if(param){
		return /^[A-Za-z]+$/g.test(value);
	}
	return true;
},"只能输入字母");
jQuery.validator.addMethod("passwordFlg",function(value, element, param){
	if(param){
		return /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/g.test(value);
	}
	return true;
},"密码必须由字母和数字组成的6-20位字符");

jQuery.validator.addMethod("tel",function(value, element, param){
	if(param){
		return /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/g.test(value);
	}
	return true;
},"请输入正确的座机号码，如029-88888888");
    


jQuery.validator.addMethod("ip",function(value, element, param){
	if(param){
		return /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/g.test(value);
	}
	return true;
},"请输入正确的IP地址");


jQuery.validator.addMethod("lnglat_CN",function(value, element, param){
	if(param){
		var lnglat =(value+'').split(',');
		if(lnglat.length==2){
			var lng = parseFloat(lnglat[0]);
			var lat = parseFloat(lnglat[1]);
			return lng>73&&lng<136.5&&lat>3&&lat<54;
		}else{
			return false;
		}
	}
	return true;
},"经纬度格式有误");
//精确度
jQuery.validator.addMethod("accuracy",function(value, element, param){
	if(value&&param&&param>0){
		var reg = new RegExp("^\\d+(.\\d{0,"+param+"})?$");
		return reg.test(value);
	}
	return true;
},"请输入最多保留{0}位小数的数字");


$(":input").live("keyup",function(){
	var context = $(this).val();
	context = context.replace(/[<>]/,function(a,b,c){
		switch (a) {
		case '<':
			return '&lt;';
		case '>':
			return '&gt;';
		default:
			break;
		}
	});
	$(this).val(context);
	if('TEXTAREA'==this.nodeName){
		if(context.length>0){
			$("span.textarea_preset").hide();
		}else{
			$("span.textarea_preset").show();	
		}
	}
});