$(function () {
    // 初始化轮播
    initCarousel();
    recommendCourse();
    recommendTeacher();
    recommendSchool();
    bindRegisterEvent();
    login_student();
});

function initCarousel() {

    $('#myCarousel').carousel({
        interval: 2000
    })

    $('#myCarousel').carousel('cycle')
}

function createThumbnail() {
    var rowDiv = $("<div></div>").addClass("row");
    for (var i = 0; i < 4; i++) {
        var colDiv = $("<div></div>").addClass("col-md-3");
        var imgDiv = $("<a></a>").addClass("thumbnail").attr("href", "#").append($("<img />").addClass("img-responsive").attr("src", ctxPath + "/static/img/computer_ds.jpg"));
        var courseName = $("<div></div>").addClass("h5").text("课程名称");
        var detailDiv = $("<div></div>").addClass("row").append($("<div></div>").addClass("col-md-6 text-muted").text("院校"))
            .append($("<div></div>").addClass("col-md-6 text-muted").text("人数"));
        colDiv.append(imgDiv).append(courseName).append(detailDiv);
        rowDiv.append(colDiv);
    }
    return rowDiv;
}

function recommendCourse() {
    var rowDiv = $("<div></div>").addClass("row");
    $.ajax({
        url: ctxPath + "/course_record/find",
        type: "get",
        data: {
            count: 4,
        },
        success: function (result) {
            if (result.code == 100) {
                var courses = result.extend.courses;
                var courseRecords = result.extend.courseRecords;
                for (var i = 0; i < 4; i++) {
                    var colDiv = $("<div></div>").addClass("col-md-3");
                    var imgDiv = $("<a></a>").addClass("thumbnail").attr("href", ctxPath + "/course_record/info?course_id=" + courseRecords[i].courseId + "&teacher_id=" + courseRecords[i].teacherId + "&course_record_id=" + courseRecords[i].id).append($("<img />").css("height", "240px").attr("src", ctxPath + courses[i].img));
                    var courseName = $("<div></div>").addClass("h5").text(courses[i].name);
                    var detailDiv = $("<div></div>").addClass("row").append($("<div></div>").addClass("col-md-6 text-muted").text(courses[i].school))
                        .append($("<div></div>").addClass("col-md-6 text-muted").text(""));
                    colDiv.append(imgDiv).append(courseName).append(detailDiv);
                    rowDiv.append(colDiv);
                }
            }
        }
    });
    $("#index-course").append(rowDiv);
}

function recommendTeacher() {

    $("#index-teacher").append(createThumbnail());
}

function recommendSchool() {
    $("#index-school").append(createThumbnail());
}

function bindRegisterEvent() {
    var register_s = $("#register_s");
    var register_t = $("#register_t");
    var register_u = $("#register_u");
    register_s.click(function () {
        if (!register_s.hasClass("active")) {
            $("#register_t").removeClass("active");
            $("#register_u").removeClass("active");
            $("#register_s").addClass("active"); //增加类
        }
        //显示对应表单
        $("#register_s_form").css("display", "block");
        $("#register_t_form").css("display", "none");
        $("#register_u_form").css("display", "none");
    });
    register_t.click(function () {
        if (!register_t.hasClass("active")) {
            $("#register_s").removeClass("active");
            $("#register_u").removeClass("active");
            $("#register_t").addClass("active"); //增加类
        }
        //显示对应表单
        $("#register_t_form").css("display", "block");
        $("#register_s_form").css("display", "none");
        $("#register_u_form").css("display", "none");
    });
    register_u.click(function () {
        if (!register_u.hasClass("active")) {
            $("#register_s").removeClass("active");
            $("#register_t").removeClass("active");
            $("#register_u").addClass("active"); //增加类
        }
        //显示对应表单
        $("#register_u_form").css("display", "block");
        $("#register_s_form").css("display", "none");
        $("#register_t_form").css("display", "none");
    });

    $("#student_reg_btn").click(function () {
        var data = form2JsonString("#register_s_form");
        $.ajax({
            url: ctxPath + "/student/register",
            dataType: "json",
            type: "POST",
            contentType: "application/json",//上传内容格式为json结构
            data: data,
            success: function (result) {
                if (result.code == 100) {
                    alert("注册成功。");
                    $('#registerModal').modal('hide');
                } else {
                    alert(result.extend.cell);
                }
            }
        });
        return false;//防止form表单再次提交。
    });

    //
    $("#teacher_reg_btn").click(function () {
        var data = form2JsonString("#register_t_form");
        $.ajax({
            url: ctxPath + "/teacher/register",
            dataType: "json",
            type: "POST",
            contentType: "application/json",//上传内容格式为json结构
            data: data,
            success: function (result) {
                if (result.code == 100) {
                    alert("注册成功。");
                    $('#registerModal').modal('hide');
                } else {
                    alert(result.extend.cell);
                }
            }
        });
        return false;//防止form表单再次提交。
    });

    //
    $("#school_reg_btn").click(function () {
        var data = form2JsonString("#register_u_form");
        $.ajax({
            url: ctxPath + "/school/register",
            dataType: "json",
            type: "POST",
            contentType: "application/json",//上传内容格式为json结构
            data: data,
            success: function (result) {
                if (result.code == 100) {
                    alert("注册成功。");
                    $('#registerModal').modal('hide');
                } else {
                    alert(result.extend.cell);
                }
            }
        });
        return false;//防止form表单再次提交。
    });
}

function login_student() {
    var login_s = $("#login_s");
    var login_t = $("#login_t");
    var login_u = $("#login_u");
    login_s.click(function () {
        if (!login_s.hasClass("active")) {
            $("#login_t").removeClass("active");
            $("#login_u").removeClass("active");
            $("#login_s").addClass("active"); //增加类
        }
        //显示对应表单
        $("#login_s_form").css("display", "block");
        $("#login_t_form").css("display", "none");
        $("#login_u_form").css("display", "none");
    });
    login_t.click(function () {
        if (!login_t.hasClass("active")) {
            $("#login_s").removeClass("active");
            $("#login_u").removeClass("active");
            $("#login_t").addClass("active"); //增加类
        }
        //显示对应表单
        $("#login_t_form").css("display", "block");
        $("#login_s_form").css("display", "none");
        $("#login_u_form").css("display", "none");
    });
    login_u.click(function () {
        if (!login_u.hasClass("active")) {
            $("#login_s").removeClass("active");
            $("#login_t").removeClass("active");
            $("#login_u").addClass("active"); //增加类
        }
        //显示对应表单
        $("#login_u_form").css("display", "block");
        $("#login_s_form").css("display", "none");
        $("#login_t_form").css("display", "none");
    });

    //***************


    $("#student_login_btn").click(function () {
        $.ajax({
            url: ctxPath + "/student/login",
            type: "POST",
            data: $('#login_s_form').serialize(),
            success: function (result) {
                $("#nav-login-btn").addClass("div-none");
                $("#nav-register-btn").addClass("div-none");
                $("#nav-info-btn").removeClass("div-none");
                $("#nav-info-btn a").attr("href", ctxPath + "/student/info");
                $('#loginModal').modal('hide');
                window.location.reload(true);
            }
        });
        return false;
    });
    $("#teacher_login_btn").click(function () {
        $.ajax({
            url: ctxPath + "/teacher/login",
            type: "POST",
            data: $('#login_t_form').serialize(),
            success: function (result) {
                $("#nav-login-btn").addClass("div-none");
                $("#nav-register-btn").addClass("div-none");
                $("#nav-info-btn").removeClass("div-none");
                $("#nav-info-btn a").attr("href", ctxPath + "/teacher/info");
                $('#loginModal').modal('hide');
                window.location.reload(true);
            }
        });
        return false;
    });
    $("#school_login_btn").click(function () {
        $.ajax({
            url: ctxPath + "/school/login",
            type: "POST",
            data: $('#login_u_form').serialize(),
            success: function (result) {
                $("#nav-login-btn").addClass("div-none");
                $("#nav-register-btn").addClass("div-none");
                $("#nav-info-btn").removeClass("div-none");
                $("#nav-info-btn a").attr("href", ctxPath + "/school/info");
                $('#loginModal').modal('hide');
                window.location.reload(true);
            }
        });
        return false;
    });
}

function form2JsonString(formId) {
    var paramArray = $(formId).serializeArray();
    /*请求参数转json对象*/
    var jsonObj = {};
    $(paramArray).each(function () {
        jsonObj[this.name] = this.value;
    });
    // json对象再转换成json字符串
    return JSON.stringify(jsonObj);
}


