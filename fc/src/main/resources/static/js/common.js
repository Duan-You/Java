selections = [];

$(function () {
    initEvent();
    recommendCourse();
    recommendTeacher();
    recommendSchool();
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
});

function initEvent() {


    $("#foot-pos").css("margin-top", ($(window).height() - $(document.body).height()).toString() + "px");
    $("#add_learning_record_btn").click(function () {
        $.ajax({
            url: ctxPath + "/learning_record/add",
            type: "get",
            data: {
                course_record_id: course_record_id,
            },
            success: function (result) {
                alert(result.extend.msg);
            },
        });
    });
}

function createThumbnail() {
    var rowDiv = $("<div></div>").addClass("row");
    for (var i = 0; i < 4; i++) {
        var colDiv = $("<div></div>").addClass("col-md-3");
        var imgDiv = $("<a></a>").addClass("thumbnail").attr("href", "#").append($("<img />").addClass("img-responsive").attr("src", "img/history_jianqiao.jpg"));
        var courseName = $("<div></div>").addClass("h5").text("课程名称");
        var detailDiv = $("<div></div>").addClass("row").append($("<div></div>").addClass("col-md-6 text-muted").text("院校"))
            .append($("<div></div>").addClass("col-md-6 text-muted").text("人数"));
        colDiv.append(imgDiv).append(courseName).append(detailDiv);
        rowDiv.append(colDiv);
    }
    return rowDiv;
}

function recommendCourse() {
    $("#index-course").append(createThumbnail());
}

function recommendTeacher() {

    $("#index-teacher").append(createThumbnail());
}

function recommendSchool() {
    $("#index-school").append(createThumbnail());
}


var TableInit = function() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function() {
        $('#resource_table').bootstrapTable({
            ajax : function (request) {
                $.ajax({
                    type : "GET",
                    url: ctxPath+'/resource/getData?course_record_id='+course_record_id, //请求后台的URL（*）
                    success : function (msg) {
                        request.success({
                            row : msg.extend.data
                        });
                        $('#resource_table').bootstrapTable('load', msg.extend.data);
                    },
                    error:function(){
                        alert("错误");
                    }
                });
            },



            toolbar: '#toolbar', //工具按钮用哪个容器
            striped: true, //是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, //是否显示分页（*）
            sortable: false, //是否启用排序
            sortOrder: "asc", //排序方式
            queryParams: oTableInit.queryParams, //传递参数（*）
            sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1, //初始化加载第一页，默认第一页
            pageSize: 7, //每页的记录行数（*）
            pageList: [10], //可供选择的每页的行数（*）
            search: true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true, //是否显示所有的列
            showRefresh: true, //是否显示刷新按钮
            minimumCountColumns: 2, //最少允许的列数
            clickToSelect: true, //是否启用点击选中行
            height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id", //每一行的唯一标识，一般为主键列
            showToggle: true, //是否显示详细视图和列表视图的切换按钮
            cardView: false, //是否显示详细视图
            detailView: false, //是否显示父子表onEditableSave
            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: '编号'

            }, {
                field: 'name',
                title: '名称'

            }, {
                field: 'category',
                title: '类型'

            }, {
                field: 'resDate',
                title: '创建时间'

            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            }

            ],

            onEditableSave: function(field, row, oldValue, $el) {
                $.ajax({
                    type: "post",
                    url: "/edit",
                    data: {
                        strJson: JSON.stringify(row)
                    },
                    success: function(data, status) {
                        if(status == "success") {
                            alert("编辑成功");
                        }
                    },
                    error: function() {
                        alert("Error");
                    },
                    complete: function() {

                    }

                });
            }
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function(params) {
        var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit, //页面大小
            offset: params.offset, //页码
            search: params.search
        };
        return temp;
    };
    return oTableInit;
};

var ButtonInit = function() {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function() {
        //初始化页面上面的按钮事件
    };

    return oInit;
};

function btn_add() {
    $("#myModalLabel").text("添加客户");
    $('#myModal').modal();

}

function operateFormatter(value, row, index) {
    return [

        '<a class="download" href="javascript:void(0)" title="download">',
        '<i class="glyphicon glyphicon-download-alt"></i>',
        '</a>'
    ].join('');
}

window.operateEvents = {

    'click .download': function(e, value, row, index) {
        var fileName = row.content.substr(row.content. lastIndexOf("/")+1);

        $.ajax({
            type: "get",
            url: ctxPath+"/file/download",
            data: {
                fileName: fileName,
                category:row.category,

            },
            success: function(data, status) {
                if(status == "success") {
                    const link = document.createElement('a');
                    link.href = ctxPath+"/file/download?fileName="+fileName+"&category="+row.category;
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


function getIdSelections() {
    return $.map($('#tb_customer').bootstrapTable('getSelections'), function(row) {
        return row.id
    });
}

function responseHandler(res) {
    $.each(res.rows, function(i, row) {
        row.state = $.inArray(row.id, selections) !== -1;
    });
    return res;
}