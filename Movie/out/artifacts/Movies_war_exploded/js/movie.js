(function ($) {

    var movie = {};

    function getById() {
        var id = document.getElementById("movieId").value;
        // ajax请求后台
        $.ajax({
            url: "../movie/getMovieById.do?id=" + id,
            type: "post",
            data: {},
            async: false,
            success: function (data) {
                console.log(data);
                var moviedata  =JSON.parse(data);
                if (moviedata.flag == false) {
                    $('#movieInfo').val("没有查询到编号是" + id + "的电影啊");
                    return;
                }
                if (moviedata.flag == true) {
                    movie =moviedata.movie;
                    var info = "编号：" + moviedata.movie.id + "\n"
                        + "电影名：" + moviedata.movie.name + "\n"
                        + "电影名：" + moviedata.movie.type + "\n"
                        + "电影名：" + moviedata.movie.star + "\n"
                        + "时长：" + moviedata.movie.duration + "\n"
                        + "简介：" + moviedata.movie.description;
                    // $('#userInfo').val(data.user.name);
                    $('#movieInfo').val(info);
                    $('#movieName').val(movie.name);
                    return;
                }
            },
            error: function (data) {
                     alert("电影不存在！")

            }
        });
    }

    //查找所有电影
    function getAll() {

        // ajax请求后台
        $.ajax({
            url: "../movie/getAllMovie.do",
            type: "post",
            data: {},
            success: function (data) {
                // console.log(data.length);
                // console.log(data[0].name);
                console.log(data);
                alert("获取成功！");
                data = JSON.parse(data);

                if(data.flag==true) {
                    alert("进入")
                    var content = "";
                    moviedata = data.allMovie;
                        for (var i = 0; i < moviedata.length; i++) {
                            content += moviedata[i].id + ",";
                            content += moviedata[i].name + ",";
                            content += moviedata[i].type + ";  ";
                            content += moviedata[i].star + ",";
                            content += moviedata[i].duration + ",";
                            content += moviedata[i].description + ";  ";
                        }
                        $('#allInfo').val(content);
                        return;

                }
                if (data.flag == false) {
                    $('#allInfo').val("没有查询到任何电影");
                    return;
                }
            },
            error: function (data) {
                alert("无电影存在！！")

            }
        });
    }

//更新学生信息
    function updateMovie() {
        //trim() ：去掉前后空格
        var id = document.getElementById("movieId").value;
        var name = $.trim($('#name').val());
            // data = {};
            // newMovie = {};
            // data.name = name;
            // data.duration=199;
            // data.description = "更新电影测试";
            // data.star = "徐宏博，徐璐";
            // data.type = "爱情";
            // newMovie.id = id;
            // newMovie.data = data;
        newMovie = {};
        newMovie.name = name;
        newMovie.duration=999;
        newMovie.description = "更新电影测试";
        newMovie.star = "两个人儿";
        newMovie.type = "爱情";
        newMovie.id = id;
            console.log(newMovie);
            // ajax请求后台
            $.ajax({
                url: "../movie/updateMovie.do",
                type: "post",
                data: newMovie,
                success: function (data) {
                    alert("进入")
                    console.log(data);
                    data = JSON.parse(data);
                    console.log(data);
                    if (data.flag == true) {
                        alert( " 更新成功！");
                    }
                    else if (data.flag == false) {
                        alert( " 更新失败！");
                    }
                    return;
                },
                error: function (data) {
                    alert("更新失败！")

                }
            });


    }

    //更新学生信息
    function addMovie() {
        //trim() ：去掉前后空格

        newMovie = {};
        newMovie.name = "新电影";
        newMovie.duration = 120;
        newMovie.type = "温情";
        newMovie.star = "娜塔莉";
        newMovie.description = "经典电影";
        // ajax请求后台
        $.ajax({
            url: "../movie/addMovie.do",
            type: "post",
            data: newMovie,
            success: function (data) {
                alert("进入")
                console.log(data);
                data = JSON.parse(data);
                console.log(data);
                if (data.flag == true) {
                    alert( " 添加成功！");
                }
                else if (data.flag == false) {
                    alert( " 添加失败！");
                }
                return;
            },
            error: function (data) {
                alert("失败！")

            }
        });


    }

    /** 页面onload事件 */
    function init() {
        $('#getById').click(function () {
            getById();
        });
        $('#getAll').click(function () {
            getAll();
        });
        $('#update').click(function () {
            updateMovie();
        });
        $('#add').click(function () {
            addMovie();
        });
    }

    $(init);
})(jQuery);
