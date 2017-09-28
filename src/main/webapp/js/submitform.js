$(document).ready(function () {
    var hour = new Date().getHours();
    var sayhello = hour < 5 ? "晚上好！" : hour < 9 ? "早上好！" : hour < 12 ? "上午好！" : hour < 18 ? "下午好！" : "晚上好！"
    $('#sayhello').html(sayhello);
})

var refresh = false;//默认不刷新，异步写入数据

function logout() {
    //标识跳转页面的方式为刷新
    refresh = true;
    //导航栏用户名置空
    $("#navbar-username").remove();
    //将login版面标为active
    $('a',$('#pannel-header li')[0]).click();
    //页脚添加取消按钮
    $('.modal-footer button').removeClass("col-sm-offset-5").addClass('col-sm-offset-4');
    var cancelButton = "<button type='button' class='btn btn-default col-sm-offset-2 col-sm-2'data-dismiss='modal'>关闭</button>";
    $('.modal-footer').append(cancelButton);
    //点击页面空白处关闭模板框
    $('#loginModal').removeAttr('data-backdrop');

    $.ajax({
        url: "logout.do",
        type: 'post', // 数据发送方式
    })
}

function user() {
    var form = $('form', $($('#pannel-header li.active a').attr("href")));
    var input = $(':input', form);
    var actionURL = form.closest('.tab-pane').attr('id') + ".do";
    // alert(refresh);
    var inputData = {};
    $.each(input.serializeArray(), function (i, item) {
        inputData[item.name] = item.value;
        $(input[i]).val("");//置空表单
    })
    $.ajax({
        url: actionURL,
        type: 'post', // 数据发送方式
        data: inputData,
        dataType: 'json', // 接受数据格式
        error: function (message) {
            alert(message);
        },
        async: true,// 异步加载
        success: function (account) {
            if (refresh) {
                window.location.reload();
            } else {
                var balance = '账户余额：';
                balance = balance + account;
                var pattern = /^\d+\.\d+$/;
                if (!pattern.test(balance)) {
                    balance = balance + '.0'
                }
                $('#balance').html(balance);
                $('#navbar-username').html(inputData.name);
                $('#welcome-username').html(inputData.name);
                $('#loginModal').modal('hide');
            }
        }
    })
    //TODO 收起表单
}

function account(button) {
    var form = $(button).closest('form');
    var input = $(':input', form);
    var actionURL = form.closest('.tab-pane').attr('id') + ".do";
    // alert(action);
    var inputData = {};
    $.each(input.serializeArray(), function (i, item) {
        inputData[item.name] = item.value;
        $(input[i]).val("");//置空表单
    })
    $.ajax({
        url: actionURL,
        type: 'post', // 数据发送方式
        data: inputData,
        dataType: 'json', // 接受数据格式
        error: function (message) {
            alert(message);
        },
        async: true,// 异步加载
        success: function (account) {
            var balance = '账户余额：';
            balance = balance + account;
            var pattern = /^\d+\.\d+$/;
            if (!pattern.test(balance)) {
                balance = balance + '.0'
            }
            $('#balance').html(balance);
        }
    })
    //TODO 收起表单
}
