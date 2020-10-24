$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
});

$("#txt_search_coursename").off().on('keypress', function (event) {
    event.preventDefault();
    if (event.keyCode == "13") {
        $("#btn_query").click();
    }
});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_grade').bootstrapTable({
            url: '/student_grade_system/grade/user_grades',         //?user_id='+$("#loginUserId").text()请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            cardView: false,                    //是否显示详细视图
            showColumns: true,                  //是否显示所有的列
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'user.userId',
                title: '学号'
            }, {
                field: 'user.name',
                title: '姓名'
            }, {
                field: 'course.name',
                title: '课程'
            }, {
                field: 'course.class_',
                title: '类别'
            }, {
                field: 'way',
                title: '考核方式'
            }, {
                field: 'property',
                title: '读修性质'
            }, {
                field: 'grade',
                title: '成绩'
            }, {
                field: 'course.credit',
                title: '学分'
            }, {
                field: 'credit',
                title: '取得学分'
            }, {
                field: 'course.gpa',
                title: '绩点'
            }, {
                field: 'gpa',
                title: '取得绩点'
            },]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            user_id: $("#loginUserId").text()
        };
        return temp;
    };
    return oTableInit;
};

$("#treeNavControl").click(function () {
    Tool.sidebar('toggle-sidebar');
    this.blur();
});

$('#logout').click(function () {
    $.ajax({
        url: '/student_grade_system/user/logout',
        type: "post",
        success: function () {
            window.location.href = "/student_grade_system/login.jsp";
        }
    });
});

$('#btn_query').click(function () {
    $.ajax({
        url: '/student_grade_system/grade/user_search_grade',
        type: "post",
        data: {
            courseName: $("#txt_search_coursename").val(),
            term: termJoin(),
            user_id: $("#loginUserId").text()
        },
        dataType: "json",
        success: function (msg) {
            $('#tb_grade').bootstrapTable('load', msg);
        }
    });
});

function termJoin() {
    var year = $("#txt_search_term_year option:selected").text();
    var term = $("#txt_search_term option:selected").text();
    return year + term;
}