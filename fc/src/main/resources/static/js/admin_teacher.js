var COURSE_RECORD_ID = 1;
var DEFAULT_TEACHER_ID = 1;
var QUESTION_ID = 1;
var LEARNING_RECORD = 1;
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

    var studentTable = new StudentTableInit();
    studentTable.Init();

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


    $("#add_work_info_btn").click(function () {

        $.ajax({
            url: ctxPath + "/work/add",
            type: "POST",
            data: $('#add_work_info_form').serialize(),
            success: function (result) {
                $('#addWorkModal').modal('hide');
                $('#work_table').bootstrapTable('refresh');
                alert(result.extend.msg);
            }
        });
        return false;
    });
    //
    $("#add_resource_info_btn").click(function () {
        $.ajax({
            url: ctxPath + "/resource/add",
            type: "POST",
            data: $('#add_resource_info_form').serialize(),
            success: function (result) {
                $('#addResourceModal').modal('hide');
                $('#resource_table').bootstrapTable('refresh');
                alert(result.extend.msg);
            }
        });
        return false;
    });
    $("#add_course_info_btn").click(function () {

        $.ajax({
            url: ctxPath + "/course/add",
            type: "POST",
            data: $('#add_course_info_form').serialize(),
            success: function (result) {
                $('#addCourseModal').modal('hide');
                $('#course_table').bootstrapTable('refresh');
                alert(result.extend.msg);
            }
        });
        return false;
    });

    $.ajax({
        url: ctxPath + "/course_record/getByTeacher",
        type: "get",
        data: {
            teacher_id: teacher_id,
        },
        success: function (result) {
            var datas = result.extend.datas;
            $.each(datas, function (index, value) {
                var option = $("<option></option>").val(value.courseRecord.id).text(value.course.name);
                $("#teacher_class").append(option);
            });
        }
    });

    $("#teacher_class").change(function () {
        COURSE_RECORD_ID = $(this).children('option:selected').val();
        $('#student_table').bootstrapTable('refresh');
    });

    $("#score_btn").click(function () {
        $.ajax({
            url: ctxPath + "/learning_record/update",
            type: "get",
            data: {
                learning_record_id: LEARNING_RECORD,
                score: $("#score_input").val(),
            },
            success: function (result) {
                $('#scoreModal').modal('hide');
                $('#student_table').bootstrapTable('refresh');
            }
        });
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
                    url: ctxPath + '/course_record/getData?teacher_id=' + teacher_id, //请求后台的URL（*）
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


            toolbar: '#course_toolbar', //工具按钮用哪个容器
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
                field: 'course_date',
                title: '创建时间'

            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            }],

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
        '&nbsp;&nbsp;资料</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="mychart btn btn-primary" data-toggle="modal" data-target="#chartModal" href="javascript:void(0)" title="chart">',
        '<i class="glyphicon glyphicon-send"></i>',
        '&nbsp;&nbsp;成绩</a>'

    ].join('');
}

window.operateEvents = {

    'click .work': function (e, value, row, index) {
        COURSE_RECORD_ID = row.course_record_id;

        $("#input_course_record_id").val(COURSE_RECORD_ID);
        $('#work_table').bootstrapTable('refresh');
    },
    //
    'click .resource': function (e, value, row, index) {
        COURSE_RECORD_ID = row.course_record_id;
        $("#input_course_record_id_resource").val(COURSE_RECORD_ID);
        $('#resource_table').bootstrapTable('refresh');
    },
    //
    'click .mychart': function (e, value, row, index) {
        COURSE_RECORD_ID = row.course_record_id;
        $.ajax({
            type: "GET",
            url: ctxPath + '/learning_record/getLineChart', //请求后台的URL（*）
            data: {
                course_record_id: COURSE_RECORD_ID,
            },
            success: function (msg) {
                var ctx = document.getElementById('scoreChart').getContext('2d');
                var chart = new Chart(ctx, {
                    // The type of chart we want to create
                    type: 'line',

                    // The data for our dataset
                    data: {
                        labels: msg.extend.labels,
                        datasets: [{
                            label: '统计信息',
                            backgroundColor: 'rgba(54, 162, 235)',
                            data: msg.extend.datas,
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
        });


    }
};


function responseHandler(res) {
    $.each(res.rows, function (i, row) {
        row.state = $.inArray(row.id, selections) !== -1;
    });
    return res;
}


var StudentTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#student_table').bootstrapTable({


            ajax: function (request) {
                $.ajax({
                    type: "GET",
                    url: ctxPath + '/learning_record/getStudents', //请求后台的URL（*）
                    data: {
                        course_record_id: COURSE_RECORD_ID,
                    },
                    success: function (msg) {
                        request.success({
                            row: msg.extend.datas
                        });
                        $('#student_table').bootstrapTable('load', msg.extend.datas);
                    },
                    error: function () {
                        alert("错误");
                    }
                });
            },


            toolbar: '#student_toolbar', //工具按钮用哪个容器
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
                field: 'student.id',
                title: '学号'

            }, {
                field: 'student.name',
                title: '姓名'

            }, {
                field: 'student.gender',
                title: '性别'

            }, {
                field: 'course.name',
                title: '课程名称'

            }, {
                field: 'learningRecord.score',
                title: '成绩'

            }, {
                field: 'learningRecord.learningTime',
                title: '学时'

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

        '<a class="score btn btn-primary" data-toggle="modal" data-target="#scoreModal" href="javascript:void(0)" title="score">',
        '<i class="glyphicon glyphicon-check"></i>',
        '&nbsp;&nbsp;打分</a>'
    ].join('');
}

window.operateEvents_teacher = {

    'click .score': function (e, value, row, index) {

        LEARNING_RECORD = row.learningRecord.id;

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
                    url: ctxPath + '/answer_record/getTeacherData?teacher_id=' + teacher_id, //请求后台的URL（*）
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


            toolbar: '#teacher_toolbar', //工具按钮用哪个容器
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
                field: 'studentId',
                title: '学号'

            }, {
                field: 'student_name',
                title: '学生姓名'

            }, {
                field: 'question',
                title: '问题'

            }, {
                field: 'questionDate',
                title: '提问日期'

            }, {
                field: 'answer',
                title: '答案'

            }, {
                field: 'answerDate',
                title: '回答日期'

            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents_answer,
                formatter: operateFormatter_answer
            }],

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

        '<a class="question btn btn-primary" href="javascript:void(0)" title="question">',
        '<i class="glyphicon glyphicon-question-sign"></i>',
        '&nbsp;&nbsp;回答</a>'
    ].join('');
}

window.operateEvents_answer = {

    'click .question': function (e, value, row, index) {
        var fileName = row.content.substr(row.content.lastIndexOf("/") + 1);

        $.ajax({
            type: "get",
            url: ctxPath + "/file/download",
            data: {
                fileName: fileName,
                category: row.category,

            },
            success: function (data, status) {
                if (status == "success") {
                    const link = document.createElement('a');
                    link.href = ctxPath + "/file/download?fileName=" + fileName + "&category=" + row.category;
                    link.click();
                }
            },
            error: function () {
                alert("Error");
            }

        });


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
                    url: ctxPath + '/question_record/getTeacherData?teacher_id=' + teacher_id, //请求后台的URL（*）
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
                field: 'studentId',
                title: '学号'

            }, {
                field: 'student_name',
                title: '学生姓名'

            }, {
                field: 'question',
                title: '问题'

            }, {
                field: 'questionDate',
                title: '提问日期'

            }, {
                field: 'answer',
                title: '答案'

            }, {
                field: 'answerDate',
                title: '回答日期'

            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents_question,
                formatter: operateFormatter_question
            }],

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

function operateFormatter_question(value, row, index) {
    return [

        '<a class="question btn btn-primary" href="javascript:void(0)" title="question">',
        '<i class="glyphicon glyphicon-question-sign"></i>',
        '&nbsp;&nbsp;修改</a>'
    ].join('');
}

window.operateEvents_question = {

    'click .question': function (e, value, row, index) {
        var fileName = row.content.substr(row.content.lastIndexOf("/") + 1);

        $.ajax({
            type: "get",
            url: ctxPath + "/file/download",
            data: {
                fileName: fileName,
                category: row.category,

            },
            success: function (data, status) {
                if (status == "success") {
                    const link = document.createElement('a');
                    link.href = ctxPath + "/file/download?fileName=" + fileName + "&category=" + row.category;
                    link.click();
                }
            },
            error: function () {
                alert("Error");
            }

        });


    }
};


var WorkTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#work_table').bootstrapTable({


            ajax: function (request) {
                $.ajax({
                    type: "GET",
                    url: ctxPath + '/work/getDataByCourseRecord', //请求后台的URL（*）
                    data: {
                        course_record_id: COURSE_RECORD_ID,
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
                field: 'work.info',
                title: '文件'

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

        '<a class="modify" href="javascript:void(0)" title="modify">',
        '<i class="glyphicon glyphicon-edit"></i>',
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="upload" data-toggle="modal" data-target="#fileModal" href="javascript:void(0)" title="upload">',
        '<i class="glyphicon glyphicon-upload"></i>',
        '</a>'

    ].join('');
}

window.operateEvents_work = {

    'click .modify': function (e, value, row, index) {


    },
    'click .upload': function (e, value, row, index) {
        $("#upload_file_btn").click(function () {
            var formData = new FormData();
            formData.append("file", $("#uploadFile")[0].files[0]);
            formData.append("file_id", row.work.id);//也可以传递其他字段
            formData.append("type", "work");//也可以传递其他字段
            $.ajax({
                type: "post",
                url: ctxPath + "/file/upload",
                data: formData,
                contentType: false,
                processData: false,
                dataType: "json",
                success: function (res) {
                    $('#fileModal').modal('hide');
                    $('#work_table').bootstrapTable('refresh');
                    alert(res.extend.msg);
                },
                error: function (msg) {
                    alert(res.extend.msg);
                }
            });
            return false;
        });


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
                    url: ctxPath + '/resource/getDataByCourseRecord', //请求后台的URL（*）
                    data: {
                        course_record_id: COURSE_RECORD_ID,
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
                field: 'resource.content',
                title: '文件'

            }, {
                field: 'resource.resDate',
                title: '上传日期'

            }, {
                field: 'course.name',
                title: '课程名称'

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

        '<a class="modify" href="javascript:void(0)" title="modify">',
        '<i class="glyphicon glyphicon-edit"></i>',
        '</a>&nbsp;&nbsp;&nbsp;&nbsp;',
        '<a class="upload" data-toggle="modal" data-target="#fileModal" href="javascript:void(0)" title="upload">',
        '<i class="glyphicon glyphicon-upload"></i>',
        '</a>'

    ].join('');
}

window.operateEvents_resource = {

    'click .upload': function (e, value, row, index) {
        $("#upload_file_btn").click(function () {
            var formData = new FormData();
            formData.append("file", $("#uploadFile")[0].files[0]);
            formData.append("file_id", row.resource.id);//也可以传递其他字段
            formData.append("type", "资料&" + row.resource.category);//也可以传递其他字段
            $.ajax({
                type: "post",
                url: ctxPath + "/file/upload",
                data: formData,
                contentType: false,
                processData: false,
                dataType: "json",
                success: function (res) {
                    $('#fileModal').modal('hide');
                    $('#resource_table').bootstrapTable('refresh');
                    alert(res.extend.msg);
                },
                error: function (msg) {
                    alert(res.extend.msg);
                }
            });
            return false;
        });

    }
};