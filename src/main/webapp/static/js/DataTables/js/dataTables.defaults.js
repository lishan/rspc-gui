$.extend( $.fn.dataTable.defaults, {
	oLanguage:{
		"oAria": {
		    "sSortAscending": ": 以升序排列此列",
		    "sSortDescending": ": 以降序排列此列"
		},
		"sLengthMenu": "每页显示 _MENU_条",
		"sZeroRecords": "没有找到符合条件的数据",
		"sProcessing": "&lt;img src=’./loading.gif’ /&gt;",
		"sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
		"sInfoEmpty": "暂无记录",
		"sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
		"sSearch": "搜索：",
		"sEmptyTable": "",//暂无数据
		"sInfoPostFix": "",
		"sDecimal": "",
		"sThousands": ",",
		"sLoadingRecords": "Loading...",
		"sUrl": "",
		"oPaginate": {
				"sFirst": "首页",
				"sPrevious": "前一页",
				"sNext": "后一页",
				"sLast": "尾页"
			}
		},
		iDisplayLength:10,
		bSort:false,
		bServerSide:true,
		//l- 每页显示数量
		//f - 过滤输入
		//t - 表单Table
		//i - 信息
		//p - 翻页
		//r - pRocessing
		//< and > - div elements
		//<"class" and > - div with a class
		//Examples: <"wrapper"flipt>, <lf<t>ip>
		"dom": "<'row-fluid'<'pull-left'l><'pull-right'>>rt<'row-fluid'<'col-sm-3'i><'col-sm-9'p>>",
		sServerMethod:"POST"
		
});
