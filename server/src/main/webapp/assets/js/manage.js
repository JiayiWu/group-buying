/**
 * Created by StevenWu on 17/5/3.
 */

$(function(){
    init();
});

function init(){
    initAD("/advertisement/list");

    $('#add').click(function(){
        $('#myModal').modal('toggle');
        $("#myModal").modal().css({
            "margin-top": function () {
                return ($(this).height() / 5);
            }
        });
        $('#confirmB').unbind('click');
        $('#confirmB').click(
            function(){
                addAD();
            });
    });

    $('#deleteC').click(function(){
        deleteAD();
    })

}

function addAD(){
    jQuery.ajax({
        url: '/advertisement/add',
        type: 'post',
        dataType: 'json',
        data:{
            imgURL:$('#picurl').val(),
            contents:$('#content').val(),
            targetURL:$('#target').val()
        },
        success: function (data) {
            if (data.result == true) {
                swal("成功", "添加成功", "success");
                $('#picurl').val("");
                $('#content').val("");
                $('#target').val("");
                $('#tablepool').bootstrapTable('refresh');

            } else {
                swal("失败","出错啦!联系下管理员吧", "error");
            }

        },
        error:function(data){
            swal("OMG", "服务器错误,联系下管理员吧!", "error");
        }
    });


}

function deleteAD(){
    var ids = new Array();
    var objects = $('#tablepool').bootstrapTable('getSelections');
    for (var i = 0; i < objects.length; i++) {
        ids.push(objects[i].id);
    }
    jQuery.ajax({
        url: '/advertisement/delete',
        type: 'post',
        dataType: 'json',
        traditional: true,
        data: {
            'id': ids
        },
        success: function (data) {
            if (data.result == true) {
                swal("成功", "你所选的广告已经删除", "success");

            } else {
                swal("删除失败", "出错啦!联系下管理员吧", "error");
            }
            $('#tablepool').bootstrapTable('refresh');
        },
        error: function (data) {
            swal("OMG", "服务器错误,联系下管理员吧!", "error");
        }
    });
}


(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
    m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
ga('create', 'UA-36708951-1', 'wenzhixin.net.cn');
ga('send', 'pageview');

function initAD(url){
    $('#tablepool').bootstrapTable({
        url: url
    });
}