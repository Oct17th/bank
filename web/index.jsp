<!DOCTYPE html>
<html>
<head>
    <%@page contentType="text/html; UTF-8" %>
    <%@page pageEncoding="UTF-8" %>
    <title>银行</title>

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--<script src="js/jquerysession.js"></script>--%>
    <script src="js/jrj6out.js"></script>
    <script>try {
        Typekit.load({async: false});
    } catch (e) {
    }</script>

    <style>
        body {
            position: relative;
            margin: 0;
            overflow: hidden;
        }

        .intro-container {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            color: white;
            text-align: center;
            margin: 0 auto;
            right: 0;
            left: 0;
        }

        h1 {
            font-family: 'brandon-grotesque', sans-serif;
            font-weight: bold;
            margin-top: 0px;
            margin-bottom: 0;
            font-size: 20px;
        }

        @media screen and (min-width: 860px) {
            h1 {
                font-size: 40px;
                line-height: 52px;
            }
        }

        .fancy-text {
            font-family: "adobe-garamond-pro", sans-serif;
            font-style: italic;
            letter-spacing: 1px;
            margin-bottom: 17px;
        }

        .button {
            position: relative;
            cursor: pointer;
            display: inline-block;
            font-family: 'brandon-grotesque', sans-serif;
            text-transform: uppercase;
            min-width: 200px;
            margin-top: 30px;
        }

        .button:hover .border {
            box-shadow: 0px 0px 10px 0px white;
        }

        .button:hover .border .left-plane, .button:hover .border .right-plane {
            transform: translateX(0%);
        }

        .button:hover .text {
            color: #121212;
        }

        .button .border {
            border: 1px solid white;
            transform: skewX(-20deg);
            height: 32px;
            border-radius: 3px;
            overflow: hidden;
            position: relative;
            transition: .10s ease-out;
        }

        .button .border .left-plane, .button .border .right-plane {
            position: absolute;
            background: white;
            height: 32px;
            width: 100px;
            transition: .15s ease-out;
        }

        .button .border .left-plane {
            left: 0;
            transform: translateX(-100%);
        }

        .button .border .right-plane {
            right: 0;
            transform: translateX(100%);
        }

        .button .text {
            position: absolute;
            left: 0;
            right: 0;
            top: 50%;
            transform: translateY(-50%);
            transition: .15s ease-out;
        }

        .x-mark {
            right: 10px;
            top: 10px;
            position: absolute;
            cursor: pointer;
            opacity: 0;
        }

        .x-mark:hover .right {
            transform: rotate(-45deg) scaleY(1.2);
        }

        .x-mark:hover .left {
            transform: rotate(45deg) scaleY(1.2);
        }

        .x-mark .container {
            position: relative;
            width: 20px;
            height: 20px;
        }

        .x-mark .left, .x-mark .right {
            width: 2px;
            height: 20px;
            background: white;
            position: absolute;
            border-radius: 3px;
            transition: .15s ease-out;
            margin: 0 auto;
            left: 0;
            right: 0;
        }

        .x-mark .right {
            transform: rotate(-45deg);
        }

        .x-mark .left {
            transform: rotate(45deg);
        }

        .sky-container {
            position: absolute;
            color: white;
            text-transform: uppercase;
            margin: 0 auto;
            right: 0;
            left: 0;
            top: 2%;
            text-align: center;
            opacity: 0;
        }

        @media screen and (min-width: 860px) {
            .sky-container {
                top: 18%;
                right: 12%;
                left: auto;
            }
        }

        .sky-container__left, .sky-container__right {
            display: inline-block;
            vertical-align: top;
            font-weight: bold;
        }

        .sky-container__left h2, .sky-container__right h2 {
            font-family: 'brandon-grotesque', sans-serif;
            font-size: 26px;
            line-height: 26px;
            margin: 0;
        }

        @media screen and (min-width: 860px) {
            .sky-container__left h2, .sky-container__right h2 {
                font-size: 72px;
                line-height: 68px;
            }
        }

        .sky-container__left {
            margin-right: 5px;
        }

        .sky-container .thirty-one {
            letter-spacing: 4px;
        }

        .text-right {
            text-align: right;
        }

        .text-left {
            text-align: left;
        }

        .twitter:hover a {
            transform: rotate(-45deg) scale(1.05);
        }

        .twitter:hover i {
            color: #21c2ff;
        }

        .twitter a {
            bottom: -40px;
            right: -75px;
            transform: rotate(-45deg);
        }

        .twitter i {
            bottom: 7px;
            right: 7px;
            color: #00ACED;
        }

        .social-icon a {
            position: absolute;
            background: white;
            color: white;
            box-shadow: -1px -1px 20px 0px rgba(0, 0, 0, 0.3);
            display: inline-block;
            width: 150px;
            height: 80px;
            transform-origin: 50% 50%;
            transition: .15s ease-out;
        }

        .social-icon i {
            position: absolute;
            pointer-events: none;
            z-index: 1000;
            transition: .15s ease-out;
        }

        .youtube:hover a {
            transform: rotate(45deg) scale(1.05);
        }

        .youtube:hover i {
            color: #ec4c44;
        }

        .youtube a {
            bottom: -40px;
            left: -75px;
            transform: rotate(45deg);
        }

        .youtube i {
            bottom: 7px;
            left: 7px;
            color: #E62117;
        }

    </style>

    <script src="js/prefixfree.min.js"></script>
    <script type="application/javascript">
        $(document).ready(
            function () {
                //user为空时才会往下走
                var user =<%=session.getAttribute("user")%>;
                if (user==null) {
                    document.getElementById('show-login').click();
                }
            })

    </script>
</head>

<body>
<div class="x-mark">
    <div class="container">
        <div class="left"></div>
        <div class="right"></div>
    </div>
</div>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container container-fluid center-block">
        <div class="navbar-left">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li class="active">
                    ${sessionScope.user.name}
                </li>
            </ol>
        </div>
        <form class="navbar-form navbar-right" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
            <div class="btn btn-default shift-camera-button">
                <div class="text">退出</div>
            </div>
        </form>
    </div>
</nav>


<div class="intro-container">
    <!--决定表单内容两端是否为空-->
    <div class="container" id="intro">
        <div class="jumbotron">
            <div class="container center-block">
                <div class="row">
                    <h1 class="pull-left">&nbsp;<font style="color: #63c4e6;">
                        ${sessionScope.user.name}
                    </font>&nbsp;
                        <small>早上好！</small>
                    </h1>
                </div>

                <!--分割线-->
                <div class="row">
                    <ul class="nav nav-list">
                        <li class="divider">&nbsp;&nbsp;</li>
                        <li class="divider">&nbsp;&nbsp;</li>
                    </ul>
                </div>
                <div class="row well">
                    <div class="col-sm-6">
                        <h4 style="color: black">账户余额：${requestScope.account}</h4>
                    </div>
                    <div class="col-sm-2">
                        <a href="#deposit" data-toggle="tab" class="btn btn-info btn-block">
                            存款
                        </a>
                    </div>
                    <div class="col-sm-2">
                        <a href="#withdrawals" data-toggle="tab" class="btn btn-info btn-block">
                            取款
                        </a>
                    </div>
                    <div class="col-sm-2">
                        <a href="#transfer" data-toggle="tab" class="btn btn-info btn-block">
                            转账
                        </a>
                    </div>
                </div>

                <!--&lt;!&ndash; 操作表单 &ndash;&gt;-->
                <div class="tab-content">
                    <div class="tab-pane fade " id="deposit">
                        <div class="row well">
                            <form class="form-horizontal" role="form" action="deposit.do" method="post">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" style="color: black">金额</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" type="text" id="focusedInput" placeholder="文本输入"
                                               name="account">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-5 col-sm-2">
                                        <div class="button" onclick="document.getElementById('deposit-submit').click()">
                                            <div class="border" style="background-color:#63c4e6;">
                                                <div class="left-plane"></div>
                                                <div class="right-plane"></div>
                                            </div>
                                            <div class="text">确认存款</div>
                                        </div>
                                        <button type="submit" id="deposit-submit" style="display: none"></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="withdrawals">
                        <div class="row well">
                            <form class="form-horizontal" role="form" action="withdrawals.do" method="post">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" style="color: black">金额</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" type="text" placeholder="文本输入" name="account">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-5 col-sm-2">
                                        <div class="button"
                                             onclick="document.getElementById('withdrawals-submit').click()">
                                            <div class="border" style="background-color:#63c4e6;">
                                                <div class="left-plane"></div>
                                                <div class="right-plane"></div>
                                            </div>
                                            <div class="text">确认取款</div>
                                        </div>
                                        <button type="submit" id="withdrawals-submit" style="display: none"></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="transfer">
                        <div class="row well">
                            <form class="form-horizontal" role="form" action="transfer.do" method="post">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" style="color: black">转账人</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" type="text" placeholder="文本输入" name="transferUser">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" style="color: black">金额</label>
                                    <div class="col-sm-8">
                                        <input class="form-control" type="text" placeholder="文本输入" name="account">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-5 col-sm-2">
                                        <div class="button"
                                             onclick="document.getElementById('transfer-submit').click()">
                                            <div class="border" style="background-color:#63c4e6;">
                                                <div class="left-plane"></div>
                                                <div class="right-plane"></div>
                                            </div>
                                            <div class="text">确认转账</div>
                                        </div>
                                        <button type="submit" id="transfer-submit" style="display: none"></button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="sky-container">
    <div class="text-right sky-container__left">
        <!--<div class="row">-->
        <h2 class="resurrection">
            Thanks for you using!
        </h2>
        <!--</div>-->
    </div>
    <div class="row">
        <div class="button">
            <div class="border">
                <div class="left-plane"></div>
                <div class="right-plane"></div>
            </div>
            -
            <div class="text" data-toggle="modal" data-target="#loginModal" id="show-login">login</div>
        </div>
    </div>
</div>
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="container">
                    <div class="panel-heading">
                        <div class="row col-sm-6">
                            <ul class="nav nav-tabs" id="pannel-header">
                                <li class="active col-sm-6">
                                    <a href="#login" data-toggle="tab" value="login">
                                        <h4 style="text-align: center"><strong>登&nbsp;&nbsp;&nbsp;录</strong></h4>
                                    </a>
                                </li>
                                <li class="col-sm-6">
                                    <a href="#register" data-toggle="tab" value="register">
                                        <h4 style="text-align: center"><strong>注&nbsp;&nbsp;&nbsp;册</strong></h4>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="row">&nbsp;</div>
                    <div class="panel-body">
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="login">
                                <form class="form-horizontal" role="form" action="login.do" method="post">
                                    <div class="form-group">&nbsp;</div>
                                    <div class="form-group">
                                        <label class="col-sm-1 control-label" style="color: black">用户名</label>
                                        <div class="col-sm-4">
                                            <input class="form-control" type="text" placeholder="文本输入" name="name">
                                        </div>
                                    </div>
                                    <div class="form-group"></div>
                                    <div class="form-group">
                                        <label class="col-sm-1 control-label" style="color: black">密码</label>
                                        <div class="col-sm-4">
                                            <input class="form-control" type="text" placeholder="文本输入" name="password">
                                        </div>
                                    </div>
                                    <button type="submit" id="login-submit" style="display: none"></button>
                                </form>
                            </div>
                            <div class="tab-pane fade" id="register">
                                <form class="form-horizontal" role="form" action="register.do" method="post">
                                    <div class="form-group">&nbsp;</div>
                                    <div class="form-group">
                                        <label class="col-sm-1 control-label" style="color: black">用户名</label>
                                        <div class="col-sm-4">
                                            <input class="form-control" type="text" placeholder="文本输入" name="name">
                                        </div>
                                    </div>
                                    <div class="form-group"></div>
                                    <div class="form-group">
                                        <label class="col-sm-1 control-label" style="color: black">密码</label>
                                        <div class="col-sm-4">
                                            <input class="form-control" type="text" placeholder="文本输入" name="password">
                                        </div>
                                    </div>
                                    <div class="form-group"></div>
                                    <div class="form-group">
                                        <label class="col-sm-1 control-label" style="color: black">确认密码</label>
                                        <div class="col-sm-4">
                                            <input class="form-control" type="text" placeholder="文本输入">
                                        </div>
                                    </div>
                                </form>
                                <button type="submit" id="register-submit" style="display: none"></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info col-sm-offset-3 col-sm-2" onclick="a();

//                function c() {
//                    alert($('#pannel-header').activeElement);

//                    var buttonID = window.location.host;
//                    buttonID = buttonID+'-submit';
//                  document.getElementById(buttonID).click()
//                }
">
                    确定
                </button>
                <button type="button" class="btn btn-default col-sm-offset-2 col-sm-2"
                        data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script>
    $(function show() {
        $('#loginModal').on('shown.bs.modal', function (e) {
            // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
            $(this).css('display', 'block');
            var modalHeight = $(window).height() / 2 - $('#loginModalContent').height() / 2;
            $(this).find('.modal-dialog').css({
                'margin-top': modalHeight
            });
        });
//        $('#loginModal').modal({
//            keyboard: true
//        })
    });
    function a() {
        var href = $('#pannel-header li.active a').attr("href") + '-submit';
        $(href).click();

    }

</script>
<script src='js/jquery.min.js'></script>
<script src='js/TweenMax.min.js'></script>
<script src='js/three.min.js'></script>
<script src="js/index.js"></script>
<script type="application/javascript">
</script>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
    <p>2017.08&nbsp;&nbsp;实训银行项目&nbsp;&nbsp;<strong>@YiJie</strong></p>
</div>
</body>
</html>
