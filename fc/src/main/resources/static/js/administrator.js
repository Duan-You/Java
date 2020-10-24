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

function initEvent() {

    $.ajax({
        type: "GET",
        url: ctxPath + '/student/getChart', //请求后台的URL（*）
        data: {
            school_id: school_id,
        },
        success: function (msg) {
            var ctx = document.getElementById('userChart').getContext('2d');
            var datas = msg.extend.datas;
            var chart = new Chart(ctx, {
                // The type of chart we want to create
                type: 'pie',

                // The data for our dataset
                data: {
                    labels: msg.extend.labels,
                    datasets: [{
                        label: '统计信息',
                        data: msg.extend.datas,
                        backgroundColor: [
                            'rgba(255, 99, 132)',
                            'rgba(54, 162, 235)',
                        ]
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

    //
    $.ajax({
        type: "GET",
        url: ctxPath + '/course/getChart', //请求后台的URL（*）
        data: {
            school_id: school_id,
        },
        success: function (msg) {
            var ctx = document.getElementById('courseChart').getContext('2d');
            var chart = new Chart(ctx, {
                // The type of chart we want to create
                type: 'bar',

                // The data for our dataset
                data: {
                    labels: msg.extend.labels,
                    datasets: [{
                        label: '统计信息',
                        backgroundColor: [
                            'rgba(255, 99, 132)',
                            'rgba(54, 162, 235)',
                            'rgba(255, 206, 86)',
                            'rgba(75, 192, 192)',
                            'rgba(153, 102, 255)',
                            'rgba(255, 159, 64)',
                            'rgba(255, 99, 132)',
                            'rgba(54, 162, 235)',
                            'rgba(255, 206, 86)',
                            'rgba(75, 192, 192)',
                            'rgba(153, 102, 255)',
                            'rgba(255, 159, 64)'
                        ],
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

    //
    $.ajax({
        type: "GET",
        url: ctxPath + '/resource/getChart', //请求后台的URL（*）
        data: {
            school_id: school_id,
        },
        success: function (msg) {
            var ctx = document.getElementById('resourceChart').getContext('2d');
            var chart = new Chart(ctx, {
                // The type of chart we want to create
                type: 'bar',

                // The data for our dataset
                data: {
                    labels: msg.extend.labels,
                    datasets: [{
                        label: '统计信息',
                        backgroundColor: [
                            'rgba(255, 99, 132)',
                            'rgba(54, 162, 235)',
                            'rgba(255, 206, 86)',
                            'rgba(75, 192, 192)',
                            'rgba(153, 102, 255)',
                            'rgba(255, 159, 64)',
                            'rgba(255, 99, 132)',
                            'rgba(54, 162, 235)',
                            'rgba(255, 206, 86)',
                            'rgba(75, 192, 192)',
                            'rgba(153, 102, 255)',
                            'rgba(255, 159, 64)'
                        ],
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

function initData() {
    var teacherTable = new TeacherTableInit();
    teacherTable.Init();


}


var TeacherTableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#teacher_table').bootstrapTable({


            ajax: function (request) {
                $.ajax({
                    type: "GET",
                    url: ctxPath + '/teacher/getDataBySchool', //请求后台的URL（*）
                    data: {
                        school_id: school_id,
                    },
                    success: function (msg) {
                        request.success({
                            row: msg.extend.datas
                        });
                        $('#teacher_table').bootstrapTable('load', msg.extend.datas);
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
                field: 'teacher.name',
                title: '姓名'

            }, {
                field: 'teacher.gender',
                title: '性别'

            }, {
                field: 'school.name',
                title: '院校'

            }, {
                field: 'teacher.title',
                title: '职称'

            },

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