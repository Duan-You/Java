$(function () {


    initTeachedCourse();
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

});

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_grade').bootstrapTable({
            url: '/student_grade_system/grade/search_grade',         //请求后台的URL（*）
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
            pageSize: 5,                       //每页的记录行数（*）
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
            }, {
                field: 'grade',
                title: '成绩'
            }, {
                field: 'operate',
                title: '录入与修改',
                events: "operateEvents",
                formatter: function () {
                    return '<input type="text" class="enter_grade form-control" placeholder="成绩">'
                }
            },]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            courseName: $("#txt_search_coursename option:selected").text(),
            term: termJoin(),
        };
        return temp;
    };
    return oTableInit;
};


window.operateEvents = {
    'change .enter_grade': function (e, value, row, index) {
        var val = $(e.target).val();
        var reg =  /^(0|\d{1,2}|100)(\.\d)?$/;//   /^1?[1-9]?\d([.]\d)?$/
        var con = reg.test(val);
        if (!con) {
            alert("成绩输入有误。");
            return;
        }
        row.grade = val;
        row.credit = (val / 100 * row.course.credit).toFixed(2);
        row.gpa = (val / 100 * row.course.gpa).toFixed(2);
        $('#tb_grade').bootstrapTable('updateRow', {
            index: index,
            row: row
        });
    }
};

var ButtonInit = function () {
    var oInit = new Object();

    oInit.Init = function () {
        var gradeTable = $('#tb_grade');
        //初始化页面上面的按钮事件

        $('#btn_edit').click(function () {
            updateGrades();
        });

    };
    return oInit;
};

function updateGrades() {
    var selected = confirm("录入的成绩是否全部被选中?");
    if (!selected) return;
    var datas = $('#tb_grade').bootstrapTable('getSelections');
    if (datas.length == 0) return;
    //获取id,credit,gpa,grade
    var update_ids = "";
    var update_grades = "";
    var update_credits = "";
    var update_gpas = "";
    for (var i = 0; i < datas.length; i++) {
        update_ids += (datas[i].id + ",");
        update_credits += (datas[i].credit + ",");
        update_gpas += (datas[i].gpa + ",");
        update_grades += (datas[i].grade + ",");
    }
    update_ids = update_ids.substring(0, update_ids.length - 1);
    update_credits = update_credits.substring(0, update_credits.length - 1);
    update_gpas = update_gpas.substring(0, update_gpas.length - 1);
    update_grades = update_grades.substring(0, update_grades.length - 1);

    $.ajax({
        url: '/student_grade_system/grade/update_grades',
        type: "post",
        data: {
            update_ids: update_ids,
            update_credits: update_credits,
            update_gpas: update_gpas,
            update_grades: update_grades,
        },
        success: function () {
            alert("操作成功。");
        }
    });

}


$('#btn_query').click(function () {
    $.ajax({
        url: '/student_grade_system/grade/search_grade',
        type: "post",
        data: {
            courseName: $("#txt_search_coursename option:selected").text(),
            term: termJoin(),
        },
        dataType: "json",
        success: function (msg) {
            $('#tb_grade').bootstrapTable('load', msg);
        }
    });
});

$("#treeNavControl").click(function () {
    Tool.sidebar('toggle-sidebar');
    this.blur();
});

function termJoin() {
    var year = $("#txt_search_term_year option:selected").text();
    var term = $("#txt_search_term option:selected").text();
    return year + term;
}

function initTeachedCourse() {
    $.ajax({
        url: '/student_grade_system/course/find_teachedCourse',
        type: "post",
        data: {
            user_id: $("#loginUserId").text()
        },
        dataType: "json",
        success: function (msg) {
            for (var i = 0; i < msg.length; i++) {
                $("#txt_search_coursename").append("<option>" + msg[i].name + "</option>");
            }
        }
    });
}

$('#logout').click(function () {
    $.ajax({
        url: '/student_grade_system/user/logout',
        type: "post",
        success: function () {
            window.location.href = "/student_grade_system/login.jsp";
        }
    });
});
