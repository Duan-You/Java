$(function () {
    initStyle();
    initEvent();
    initData();
});

function initStyle() {
    //取消table的overflow属性
    $(".table-responsive").css("overflow", "hidden");
    $("#page-content").children().each(function (index) {
        if (index != 0) {
            $(this).hide();
        } else {
            $(this).show();
        }

    });
}

function initEvent() {
    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
    var userButtonInit = new UserButtonInit();
    userButtonInit.Init();
    var courseButtonInit = new CourseButtonInit();
    courseButtonInit.Init();
    //sideNavControl
    var sideNavControl = 0;
    var current = [];
    $("#page-content").children().each(function (index, domEle) {
        current[index] = $(this);
    });

    $("#grade_manage").click(function () {
        if (sideNavControl == 0) {
            return;
        }
        current[sideNavControl].hide("slow");
        current[0].show("slow");
        sideNavControl = 0;
    });
    $("#user_manage").click(function () {
        if (sideNavControl == 1) {
            return;
        }
        current[sideNavControl].hide("slow");
        current[1].show("slow");
        sideNavControl = 1;
    });
    $("#course_manage").click(function () {
        if (sideNavControl == 2) {
            return;
        }
        current[sideNavControl].hide("slow");
        current[2].show("slow");
        sideNavControl = 2;
    });

    $("#txt_search_coursename").off().on('keypress', function (event) {
        event.preventDefault();
        if (event.keyCode == "13") {
            $("#btn_query").click();
        }
    });
    $("#txt_search_username").off().on('keypress', function (event) {
        event.preventDefault();
        if (event.keyCode == "13") {
            $("#btn_query").click();
        }
    });
    $("#txt_search_course_name").off().on('keypress', function (event) {
        event.preventDefault();
        if (event.keyCode == "13") {
            $("#btn_query").click();
        }
    });
}

function initData() {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
    var userTable = new UserTableInit();
    userTable.Init();
    var courseTable = new CourseTableInit();
    courseTable.Init();


}

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_grade').bootstrapTable({
            url: '/student_grade_system/grade/search_grade', //请求后台的URL（*）
            method: 'get', //请求方式（*）
            toolbar: '#toolbar', //工具按钮用哪个容器
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
                events: "gradeOperateEvents",
                formatter: function () {
                    return '<input type="text" class="enter_grade form-control" placeholder="成绩">'
                }
            },]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            courseName: $("#txt_search_coursename").val(),
            term: termJoin(),
        };
        return temp;
    };
    return oTableInit;
};

window.gradeOperateEvents = {
    'change .enter_grade': function (e, value, row, index) {
        var val = $(e.target).val();
        var reg = /^(0|\d{1,2}|100)(\.\d)?$/;
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

        $('#btn_submit').click(function () {
            updateGrades();
        });

        $('#btn_delete').click(function () {
            var selections = gradeTable.bootstrapTable('getSelections');
            if (selections == null || selections.length == 0) {
                alert("未选中要删除的数据。");
            } else {
                var ids = [];
                var idsStr = "";
                for (var i = 0; i < selections.length; i++) {
                    ids[i] = selections[i].id;
                    idsStr = idsStr + ids[i] + ",";
                }
                idsStr = idsStr.substring(0, idsStr.length - 1);
                gradeTable.bootstrapTable('remove', {
                    field: 'id',
                    values: ids
                });
                $.ajax({
                    url: '/student_grade_system/grade_delete',
                    type: "post",
                    data: {
                        delete_ids: idsStr
                    },
                    dataType: "json"
                });
            }
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
            courseName: $("#txt_search_coursename").val(),
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

$('#logout').click(function () {

    $.ajax({
        url: '/student_grade_system/user/logout',
        type: "post",
        success: function () {
            window.location.href = "/student_grade_system/login.jsp";
        }
    });
});

//用户表
var UserTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_user').bootstrapTable({
            url: '/student_grade_system/user/search_user', //请求后台的URL（*）
            method: 'get', //请求方式（*）
            toolbar: '#user_toolbar', //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            cardView: false,                    //是否显示详细视图
            showColumns: true,                  //是否显示所有的列
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: '用户编号'
            }, {
                field: 'userId',
                title: '学号/工号'
            }, {
                field: 'name',
                title: '姓名'
            }, {
                field: 'class_',
                title: '班级'
            }, {
                field: 'gender',
                title: '性别'
            }, {
                field: 'major',
                title: '专业'
            }, {
                field: 'userClass',
                title: '用户类型'
            }, {
                field: 'institute',
                title: '学院'
            }, {
                field: 'nation',
                title: '民族'
            },]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            username: $("#txt_search_username").val(),
            user_class: $("#txt_search_user_class option:selected").text()
        };
        return temp;
    };
    return oTableInit;
};

//课程表
var CourseTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_course').bootstrapTable({
            url: '/student_grade_system/course/search_course', //请求后台的URL（*）
            method: 'get', //请求方式（*）
            toolbar: '#course_toolbar', //工具按钮用哪个容器
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
                field: 'id',
                title: '课程编号'
            }, {
                field: 'name',
                title: '课程名称'
            }, {
                field: 'class_',
                title: '课程类型'
            }, {
                field: 'credit',
                title: '学分'
            }, {
                field: 'gpa',
                title: '绩点'
            }, {
                field: 'user.name',
                title: '教师姓名'
            }, {
                field: 'user.id',
                title: '教师编号'
            }, {
                field: 'operate_teacher_id',
                title: '安排教师',
                events: "courseOperateEvents",
                formatter: function () {
                    return '<input type="text" class="set_teacher_id input_teacher_id form-control" placeholder="教师编号">'
                }
            },]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            course_name: $("#txt_search_course_name").val(),
            course_class: $("#txt_search_course_class").val(),
        };
        return temp;
    };
    return oTableInit;
};
window.courseOperateEvents = {
    'change .set_teacher_id': function (e, value, row) {
        var val = $(e.target).val();//获取输入框val
        row.teacher_id = val;//row数据增加
    },
};
var UserButtonInit = function () {
    var oInit = new Object();
    oInit.Init = function () {
        var table = $('#tb_user');
        //初始化页面上面的按钮事件
        $('#btn_query_user').click(function () {
            $.ajax({
                url: '/student_grade_system/user/search_user',
                type: "get",
                data: {
                    username: $("#txt_search_username").val(),
                    user_class: $("#txt_search_user_class option:selected").text()
                },
                dataType: "json",
                success: function (msg) {
                    $('#tb_user').bootstrapTable('load', msg);
                }
            });
        });
        $('#btn_add_user').click(function () {
            $("#UserModal button:contains('提交')").unbind().click(function () {
                $("#UserModal button:contains('×')").click();
                $.ajax({
                    url: '/student_grade_system/user/add',
                    type: 'post',
                    data: $("#user_form").serialize(),
                    dataType: 'json',
                    success: function (msg) {
                        table.bootstrapTable('refresh');
                        alert(msg);
                    }
                });
            });
        });
        $('#btn_edit_user').click(function () {
            var selections = table.bootstrapTable('getSelections');
            if (selections == null || selections.length == 0) {
                alert("未选中数据。");
                $("#UserModal button:contains('×')").click();
                return;
            }
            //表单赋值
            $("#user_form input[name='user.id']").val(selections[0].id);
            $("#user_form input[name='user.date']").val(selections[0].date);
            $("#user_form input[name='user.userId']").val(selections[0].userId);
            $("#user_form input[name='user.name']").val(selections[0].name);
            $("#user_form input[name='user.userClass']").val(selections[0].userClass);
            $("#user_form input[name='user.class_']").val(selections[0].class_);
            $("#user_form input[name='user.gender']").val(selections[0].gender);
            $("#user_form input[name='user.nation']").val(selections[0].nation);
            $("#user_form input[name='user.institute']").val(selections[0].institute);
            $("#user_form input[name='user.major']").val(selections[0].major);
            $("#user_form input[name='user.password']").val(selections[0].password);

            $("#UserModal button:contains('提交')").unbind().click(function () {
                $("#UserModal button:contains('×')").click();
                $.ajax({
                    url: '/student_grade_system/user/update',
                    type: 'post',
                    data: $("#user_form").serialize(),
                    dataType: 'json',
                    success: function (msg) {
                        table.bootstrapTable('refresh');
                        alert(msg);
                    }
                });
            });
        });

        $('#btn_delete_user').click(function () {
            var selections = table.bootstrapTable('getSelections');
            if (selections == null || selections.length == 0) {
                alert("未选中要删除的数据。");
                return;
            } else {
                var ids = [];
                var idsStr = "";
                for (var i = 0; i < selections.length; i++) {
                    ids[i] = selections[i].id;
                    idsStr = idsStr + ids[i] + ",";
                }
                idsStr = idsStr.substring(0, idsStr.length - 1);
                table.bootstrapTable('remove', {
                    field: 'id',
                    values: ids
                });
                $.ajax({
                    url: '/student_grade_system/user/delete',
                    type: "post",
                    data: {
                        delete_ids: idsStr
                    },
                    dataType: "json"
                });
            }
        });

    };
    return oInit;
};

var CourseButtonInit = function () {
    var oInit = new Object();
    oInit.Init = function () {
        var table = $('#tb_course');
        //初始化页面上面的按钮事件
        $('#btn_arrange_course').click(function () {
            var selections = table.bootstrapTable('getSelections');
            if (selections.length == 0 || selections == null) {
                alert("未选中数据。");
                return;
            } else {
                var teacher_ids = "";
                var course_ids = "";
                for (var i = 0; i < selections.length; i++) {
                    teacher_ids = teacher_ids + selections[i].teacher_id + ",";
                    course_ids = course_ids + selections[i].id + ",";
                }
                teacher_ids = teacher_ids.substring(0, teacher_ids.length - 1);
                course_ids = course_ids.substring(0, course_ids.length - 1);
                $.ajax({
                    url: '/student_grade_system/course/arrange_course',
                    type: "get",
                    data: {
                        teacher_ids: teacher_ids,
                        course_ids: course_ids,
                    },
                    dataType: "json",
                    success: function (msg) {
                        table.bootstrapTable('refresh');
                        alert(msg);
                    }
                });
            }
        });
        $('#btn_query_course').click(function () {
            $.ajax({
                url: '/student_grade_system/course/search_course',
                type: "get",
                data: {
                    course_name: $("#txt_search_course_name").val(),
                    course_class: $("#txt_search_course_class").val(),
                },
                dataType: "json",
                success: function (msg) {
                    $('#tb_course').bootstrapTable('load', msg);
                }
            });
        });
        $('#btn_add_course').click(function () {
            $("#CourseModal button:contains('提交')").unbind().click(function () {
                $("#CourseModal button:contains('×')").click();
                $.ajax({
                    url: '/student_grade_system/course/add',
                    type: 'post',
                    data: $("#course_form").serialize(),
                    dataType: 'json',
                    success: function (msg) {
                        table.bootstrapTable('refresh');
                        alert(msg);
                    }
                });
            });
        });
        $('#btn_edit_course').click(function () {
            var selections = table.bootstrapTable('getSelections');
            if (selections == null || selections.length == 0) {
                alert("未选中数据。");
                $("#CourseModal button:contains('×')").click();
                return;
            }
            //表单赋值
            $("#course_form input[name='course.id']").val(selections[0].id);
            $("#course_form input[name='course.name']").val(selections[0].name);
            $("#course_form input[name='course.class_']").val(selections[0].class_);
            $("#course_form input[name='course.credit']").val(selections[0].credit);
            $("#course_form input[name='course.gpa']").val(selections[0].gpa);


            $("#CourseModal button:contains('提交')").unbind().click(function () {
                $("#CourseModal button:contains('×')").click();
                $.ajax({
                    url: '/student_grade_system/course/update',
                    type: 'post',
                    data: $("#course_form").serialize(),
                    dataType: 'json',
                    success: function (msg) {
                        table.bootstrapTable('refresh');
                        alert(msg);
                    }
                });
            });
        });

        $('#btn_delete_course').click(function () {
            var selections = table.bootstrapTable('getSelections');
            if (selections == null || selections.length == 0) {
                alert("未选中要删除的数据。");
                return;
            } else {
                var ids = [];
                var idsStr = "";
                for (var i = 0; i < selections.length; i++) {
                    ids[i] = selections[i].id;
                    idsStr = idsStr + ids[i] + ",";
                }
                idsStr = idsStr.substring(0, idsStr.length - 1);
                table.bootstrapTable('remove', {
                    field: 'id',
                    values: ids
                });
                $.ajax({
                    url: '/student_grade_system/course/delete',
                    type: "post",
                    data: {
                        delete_ids: idsStr
                    },
                    dataType: "json"
                });
            }
        });

    };
    return oInit;
};
$("#addGradeModal button:contains('提交')").click(function () {
    $("#addGradeModal button:contains('×')").click();
    $.ajax({
        url: '/student_grade_system/grade/add',
        type: 'post',
        data: $("#add_grade_form").serialize(),
        dataType: 'json',
        success: function (msg) {
            alert(msg);
        }
    });
});


