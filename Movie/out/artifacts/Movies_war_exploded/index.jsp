<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>购票系统</title>
  <meta name="keywords" content="电影，购票">
  <meta name="description" content="">
  <link rel="shortcut icon" href="favicon.ico">
  <link href="./css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
  <link href="./css/font-awesome.css?v=4.4.0" rel="stylesheet">
  <link href="./css/animate.css" rel="stylesheet">
  <link href="./css/style.css?v=4.1.0" rel="stylesheet">
  <!--[if lt IE 9]>
  <meta http-equiv="refresh" content="0;ie.html" />
  <![endif]-->
  <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">
<a href="./html/admin_login.html" style="float: right">管理员入口</a>

<div class="middle-box text-center loginscreen  animated fadeInDown "; style="margin:auto";  >
  <div  >
    <div>

      <h2 class="logo-name" style="">SCU</h2>

    </div>
    <br>
    <h3>欢迎使用电影购票系统</h3>

    <form class="m-t" role="form" >
      <div class="form-group">
        <input type="text" id="phone" class="form-control" placeholder="手机号" required="">
      </div>
      <div class="form-group">
        <input type="password" id="password" class="form-control" placeholder="密码" required="">
      </div>
      <button type="button" id="loginBtn" class="btn btn-primary block full-width m-b">登 录</button>
      <p class="text-muted text-center">
        <a href="html/passwordBack.html">忘记密码了？</a> | <a href="html/user_register.html">注册一个新账号</a>
      </p>

    </form>
  </div>
</div>

<!-- 全局js -->
<script src="./js/jquery.min.js?v=2.1.4"></script>
<script src="./js/bootstrap.min.js?v=3.3.6"></script>
<script src="./js/plugins/validate/jquery.validate.min.js" type="text/javascript"></script>
<script src="./js/login.js"></script>
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<!--统计代码，可删除-->

</body>
</html>

