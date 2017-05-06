$(function() {
    $('#confirm').click(function() {
        update()
    })
});

function update() {
    jQuery.ajax({
        url: 'ad/reset',
        type: 'post',
        dataType: 'json',
        data: $('form').serialize(),
        success: function(data) {
            if (data.result == true) {
                $('#oldpassword').val("");
                $('#newpassword').val("");
                swal("成功", "个人信息修改成功!", "success")
            } else {
                swal("失败", data.reason, "error")
            }
            init()
        },
        error: function(data) {
            swal("OMG", "服务器错误,请稍后重试!", "error")
        }
    })
}