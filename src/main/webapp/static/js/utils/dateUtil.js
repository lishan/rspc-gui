/**
 * 日期格式化
 * @param fmt
 * @return
 */
Date.prototype.format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
/**
 * 判断闰年
 * @return
 */
Date.prototype.isLeapYear = function() {
	return (0 == this.getYear() % 4 && ((this.getYear() % 100 != 0) || (this
			.getYear() % 400 == 0)));
}
/**
 * 转换成Date
 * @param dateStr 
 * @param fmt
 * @return
 * eg:"2015-05-06".toDate("YYYY-MM-dd")
 */
String.prototype.toDate=function(fmt){
	var self=this;
	if(fmt){
		var o={};
		fmt.replace(/([ymdhs])\1/gi,function(a,b,c,d){
			if(o[b]){
				o[b]+=self.slice(c,c+2);
			}else{
				o[b]=self.slice(c,c+2);
			}
		});
		return new Date(o.Y|o.y,o.M,o.d,o.H|o.h|0,o.m|0,o.s|0);
	}
	return null;
}