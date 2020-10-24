var COURSE_RECORD_ID = 1;
var DEFAULT_TEACHER_ID = 1;
var QUESTION_ID = 1;
$(function () {
    initStyle();
    initEvent();
    initData();
});

function initStyle() {
    $("#foot-pos").css("margin-top", ($(window).height() - 200).toString() + "px");
    $("#tabContent > div").eq(0).show().siblings().hide();
    $("#tabItems > li").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var index = $(this).index();
        $("#tabContent > div").eq(index).show().siblings().hide();
    });
}

function initData() {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    var teacherTable = new TeacherTableInit();
    teacherTable.Init();

    var questionTableInit = new QuestionTableInit();
    questionTableInit.Init();

    var answerTableInit = new AnswerTableInit();
    answerTableInit.Init();

    var workTableInit = new WorkTableInit();
    workTableInit.Init();

    var resourceTableInit = new ResourceTableInit();
    resourceTableInit.Init();


}


function initEvent() {


    $.ajax({
        type: "GET",
        url: ctxPath + '/learning_record/getChart?student_id=' + student_id, //请求后台的URL（*）
        success: function (msg) {
            createChart(msg);
        }
    });
//
    $("#ask_question_btn").click(function () {
        $.ajax({
            url: ctxPath + "/question_record/add",
            type: "POST",
            data: {
                question: $("#ask_question").val(),
                student_id: student_id,
                teacher_id: DEFAULT_TEACHER_ID,

            },
            success: function (result) {
                $('#question_table').bootstrapTable('refresh');
                alert(result.extend.msg);
            }
        });
        return false;
    });

    //
    $("#reply_question_btn").click(function () {
        $.ajax({
            url: ctxPath + "/answer_record/add",
            type: "POST",
            data: {
                answer: $("#reply_question").val(),
                student_id: student_id,
                teacher_id: DEFAULT_TEACHER_ID,
                question_id: QUESTION_ID,
            },
            success: function (result) {
                $('#answer_table').bootstrapTable('refresh');
                alert(result.extend.msg);
            }
        });
        return false;
    });


}


function createChart(msg) {//添加新文件后，target文件没有更新
    var ctx = document.getElementById('scoreChart').getContext('2d');
    var chart = new Chart(ctx, {
        // The type of chart we want to create
        type: 'bar',

        // The data for our dataset
        data: {
            labels: msg.extend.courses,
            datasets: [{
                label: '学生成绩',
                backgroundColor: 'rgb(255, 99, 132)',
                borderColor: 'rgb(255, 99, 132)',
                data: msg.extend.scores
            }]
        },

        // Configuration options go here
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
}


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#course_table').bootstrapTable({


            ajax: function (request) {
                $.ajax({
                    type: "GET",
                    url: ctxPath + '/learning_record/getData?student_id=' + student_id, //请求后台的URL（*）
                    success: function (msg) {
                        request.success({
                            row: msg.extend.coursePages
                        });
                        $('#course_table').bootstrapTable('load', msg.extend.coursePages);
                    },
                    error: function () {
                        alert("错误");
                    }
                });
            },


            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
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
                field: 'course_name',
                title: '名称'

            }, {
                field: 'course_category',
                title: '科目'

            }, {
                field: 'course_school',
                title: '院校'

            }, {
                field: 'course_point_system',
                title: '分制'

            }, {
                field: 'teacher_name',
                title: '教师'

            }, {
                field: 'score',
                title: '成绩'

            }, {
                field: 'learning_time',
                title: '学时'

            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            }

            ],

            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/edit",
                    data: {
                        strJson: JSON.stringify(row)
                    },
                    success: function (data, status) {
                        if (status == "success") {
                            alert("编辑成功");
                        }
                    },
                    error: function () {
                        alert("Error");
                    }
                });
            }
        });
    };

    return oTableInit;
};


function operateFormatter(value, row, index) {
    return [

        '<a class="work btn btn-primary" data-toggle="modal" data-target="#workModal" href="javascript:void(0)" title="work">',
        '<i class="glyphicon glyphicon-tasks"></i>',
        '&nbsp;&nbsp;作业</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="resource btn btn-primary" data-toggle="modal" data-target="#resourceModal" href="javascript:void(0)" title="resource">',
        '<i class="glyphicon glyphicon-save-file"></i>',
        '&nbsp;&nbsp;资料</a>'

    ].join('');
}

window.operateEvents = {

    'click .work': function (e, value, row, index) {
        COURSE_RECORD_ID = row.course_record_id;
        $('#work_table').bootstrapTable('refresh');


    },
    //
    'click .resource': function (e, value, row, index) {
        COURSE_RECORD_ID = row.course_record_id;
        $('#resource_table').bootstrapTable('refresh');


    }
};


function responseHandler(res) {
    $.each(res.rows, function (i, row) {
        row.state = $.inArray(row.id, selections) !== -1;
    });
    return res;
}


var TeacherTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#teacher_table').bootstrapTable({


            ajax: function (request) {
                $.ajax({
                    type: "GET",
                    url: ctxPath + '/learning_record/getData?student_id=' + student_id, //请求后台的URL（*）
                    success: function (msg) {
                        request.success({
                            row: msg.extend.coursePages
                        });
                        $('#teacher_table').bootstrapTable('load', msg.extend.coursePages);
                    },
                    error: function () {
                        alert("错误");
                    }
                });
            },


            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
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
                field: 'teacher_name',
                title: '姓名'

            }, {
                field: 'teacher_gender',
                title: '性别'

            }, {
                field: 'course_school',
                title: '院校'

            }, {
                field: 'course_name',
                title: '课程名称'

            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents_teacher,
                formatter: operateFormatter_teacher
            }

            ],

            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/edit",
                    data: {
                        strJson: JSON.stringify(row)
                    },
                    success: function (data, status) {
                        if (status == "success") {
                            alert("编辑成功");
                        }
                    },
                    error: function () {
                        alert("Error");
                    }
                });
            }
        });
    };

    return oTableInit;
};


function operateFormatter_teacher(value, row, index) {
    return [

        '<a class="question btn btn-primary" data-toggle="modal" data-target="#askModal" href="javascript:void(0)" title="question">',
        '<i class="glyphicon glyphicon-question-sign"></i>',
        '&nbsp;&nbsp;提问</a>'
    ].join('');
}

window.operateEvents_teacher = {

    'click .question': function (e, value, row, index) {
        DEFAULT_TEACHER_ID = row.teacher_id;

    }
};


//

var AnswerTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#answer_table').bootstrapTable({


            ajax: function (request) {
                $.ajax({
                    type: "GET",
                    url: ctxPath + '/answer_record/getData?student_id=' + student_id, //请求后台的URL（*）
                    success: function (msg) {
                        request.success({
                            row: msg.extend.answerPages
                        });
                        $('#answer_table').bootstrapTable('load', msg.extend.answerPages);
                    },
                    error: function () {
                        alert("错误");
                    }
                });
            },


            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
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
                field: 'teacher_name',
                title: '教师姓名'

            }, {
                field: 'question',
                title: '问题'

            }, {
                field: 'questionDate',
                title: '提问日期'

            }, {
                field: 'answer',
                title: '答案'

            },
                {
                    field: 'answerDate',
                    title: '回答日期'

                }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    events: operateEvents_answer,
                    formatter: operateFormatter_answer
                }

            ],

            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/edit",
                    data: {
                        strJson: JSON.stringify(row)
                    },
                    success: function (data, status) {
                        if (status == "success") {
                            alert("编辑成功");
                        }
                    },
                    error: function () {
                        alert("Error");
                    }
                });
            }
        });
    };

    return oTableInit;
};


function operateFormatter_answer(value, row, index) {
    return [

        '<a class="question btn btn-primary" data-toggle="modal" data-target="#replyModal" href="javascript:void(0)" title="question">',
        '<i class="glyphicon glyphicon-question-sign"></i>',
        '&nbsp;&nbsp;回答</a>'
    ].join('');
}

window.operateEvents_answer = {

    'click .question': function (e, value, row, index) {
        QUESTION_ID = row.question_id;
        DEFAULT_TEACHER_ID = row.teacherId;
    }
};


var QuestionTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#question_table').bootstrapTable({


            ajax: function (request) {
                $.ajax({
                    type: "GET",
                    url: ctxPath + '/question_record/getData?student_id=' + student_id, //请求后台的URL（*）
                    success: function (msg) {
                        request.success({
                            row: msg.extend.answerPages
                        });
                        $('#question_table').bootstrapTable('load', msg.extend.answerPages);
                    },
                    error: function () {
                        alert("错误");
                    }
                });
            },


            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
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
                field: 'teacher_name',
                title: '教师姓名'

            }, {
                field: 'question',
                title: '问题'

            }, {
                field: 'questionDate',
                title: '问题日期'

            }, {
                field: 'answer',
                title: '回答'

            }, {
                field: 'answerDate',
                title: '回答日期'

            }

            ],

            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/edit",
                    data: {
                        strJson: JSON.stringify(row)
                    },
                    success: function (data, status) {
                        if (status == "success") {
                            alert("编辑成功");
                        }
                    },
                    error: function () {
                        alert("Error");
                    }
                });
            }
        });
    };

    return oTableInit;
};

//

var WorkTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#work_table').bootstrapTable({


            ajax: function (request) {
                $.ajax({
                    type: "GET",
                    url: ctxPath + '/work/getDataByCourseRecordAndStudent', //请求后台的URL（*）
                    data: {
                        course_record_id: COURSE_RECORD_ID,
                        student_id: student_id,
                    },
                    success: function (msg) {
                        request.success({
                            row: msg.extend.datas
                        });
                        $('#work_table').bootstrapTable('load', msg.extend.datas);
                    },
                    error: function () {
                        alert("错误");
                    }
                });
            },


            toolbar: '#work_toolbar', //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
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
                field: 'work.name',
                title: '作业名称'

            }, {
                field: 'work.startDate',
                title: '开始日期'

            }, {
                field: 'work.deadDate',
                title: '截至日期'

            }, {
                field: 'course.name',
                title: '课程名称'

            }, {
                field: 'teacher.name',
                title: '教师'

            }, {
                field: 'workRecord.score',
                title: '成绩'

            }, {
                field: 'workRecord.status',
                title: '状态'

            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents_work,
                formatter: operateFormatter_work
            }

            ],

            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/edit",
                    data: {
                        strJson: JSON.stringify(row)
                    },
                    success: function (data, status) {
                        if (status == "success") {
                            alert("编辑成功");
                        }
                    },
                    error: function () {
                        alert("Error");
                    }
                });
            }
        });
    };

    return oTableInit;
};


function operateFormatter_work(value, row, index) {
    return [

        '<a class="download" href="javascript:void(0)" title="download">',
        '<i class="glyphicon glyphicon-download-alt"></i>',
        '</a>'

    ].join('');
}

window.operateEvents_work = {

    'click .download': function (e, value, row, index) {
        var fileName=row.work.info;
        var category = "文档";
        if(fileName.lastIndexOf("/")!=-1){
            fileName = fileName.substr(fileName.lastIndexOf("/") + 1);
        }
        $.ajax({
            type: "get",
            url: ctxPath+"/file/download",
            data: {
                fileName: fileName,
                category: category,

            },
            success: function(data, status) {
                if(status == "success") {
                    const link = document.createElement('a');
                    link.href = ctxPath+"/file/download?fileName="+fileName+"&category="+category;
                    link.click();
                }
            },
            error: function() {
                alert("Error");
            },
            complete: function() {

            }

        });
        return false;

    }
};

var ResourceTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#resource_table').bootstrapTable({


            ajax: function (request) {
                $.ajax({
                    type: "GET",
                    url: ctxPath + '/resource/getDataByCourseRecordAndStudent', //请求后台的URL（*）
                    data: {
                        course_record_id: COURSE_RECORD_ID,
                        student_id: student_id,
                    },
                    success: function (msg) {
                        request.success({
                            row: msg.extend.datas
                        });
                        $('#resource_table').bootstrapTable('load', msg.extend.datas);
                    },
                    error: function () {
                        alert("错误");
                    }
                });
            },


            toolbar: '#resource_toolbar', //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
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
                field: 'resource.name',
                title: '资料名称'

            }, {
                field: 'resource.category',
                title: '资料类型'

            }, {
                field: 'resource.resDate',
                title: '上传日期'

            }, {
                field: 'course.name',
                title: '课程名称'

            }, {
                field: 'teacher.name',
                title: '教师'

            }, {
                field: 'resourceRecord.status',
                title: '状态'

            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents_resource,
                formatter: operateFormatter_resource
            }

            ],

            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/edit",
                    data: {
                        strJson: JSON.stringify(row)
                    },
                    success: function (data, status) {
                        if (status == "success") {
                            alert("编辑成功");
                        }
                    },
                    error: function () {
                        alert("Error");
                    }
                });
            }
        });
    };

    return oTableInit;
};


function operateFormatter_resource(value, row, index) {
    return [

        '<a class="download" href="javascript:void(0)" title="download">',
        '<i class="glyphicon glyphicon-download-alt"></i>',
        '</a>'

    ].join('');
}

window.operateEvents_resource = {

    'click .download': function (e, value, row, index) {
        var category = row.resource.category;
        var fileName=row.resource.content;
        if(fileName.lastIndexOf("/")!=-1){
            fileName = fileName.substr(fileName.lastIndexOf("/") + 1);
        }

        $.ajax({
            type: "get",
            url: ctxPath+"/file/download",
            data: {
                fileName: fileName,
                category: category,

            },
            success: function(data, status) {
                if(status == "success") {
                    const link = document.createElement('a');
                    link.href = ctxPath+"/file/download?fileName="+fileName+"&category="+category;
                    link.click();
                }
            },
            error: function() {
                alert("Error");
            },
            complete: function() {

            }

        });

    }
};
//
