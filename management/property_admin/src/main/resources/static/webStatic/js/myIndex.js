$(function() {
    layui.use(['layer'], function() {
        var layer = layui.layer;

        /**
         * 登录按钮
         */
        $("#toLogin").click(function() {
            // 点击登录弹出登录窗
            layer.open({
                type: 2,
                title: false,
                scrollbar: false,
                shadeClose: true,
                closeBtn: false,
                shade: 0.5,
                area: ['800px', '500px'],
                content: "webPage/loginPage",
            });
        })

        /**
         * 查看投诉信息
         */
        $("#toComplaint").click(function() {
            var userId = $("#userId").val();
            if(!userId) {
                layer.confirm("您还未登录！", {
                    btn: ['确定'] //按钮
                    , icon: 5
                    , anim: 6
                    , title: '错误信息'
                }, function (index) {
                    layer.close(index)
                })
                return;
            }
            // 点击登录弹出登录窗
            layer.open({
                type: 2,
                title: "投诉信息",
                shadeClose: true,
                shade: 0.5,
                area: ['1000px', '500px'],
                content: "webPage/toComplaintPage?userId=" + userId,
            });
        })

        /**
         * 查看报修信息
         */
        $("#toRepair").click(function() {
            var userId = $("#userId").val();
            if(!userId) {
                layer.confirm("您还未登录！", {
                    btn: ['确定'] //按钮
                    , icon: 5
                    , anim: 6
                    , title: '错误信息'
                }, function (index) {
                    layer.close(index)
                })
                return;
            }
            // 点击登录弹出登录窗
            layer.open({
                type: 2,
                title: "报修信息",
                shadeClose: true,
                shade: 0.5,
                area: ['1000px', '500px'],
                content: "webPage/toRepairPage?userId=" + userId,
            });
        })

        /**
         * 退出按钮
         */
        $("#webOut").click(function() {
            window.location.href = getPath() + "/webPage/webLoginOut";
        })

        /**
         * 报修按钮
         */
        $("#repairBtn").click(function() {
            var userId = $("#userId").val();
            if(!userId) {
                layer.confirm("您还未登录！", {
                    btn: ['确定'] //按钮
                    , icon: 5
                    , anim: 6
                    , title: '错误信息'
                }, function (index) {
                    layer.close(index)
                })
                return;
            }
            // 判断是否为空
            var userName = $("input[name='repairUserName']").val()
            var phone = $("input[name='repairPhone']").val()
            var repairInfo = $("textarea[name='repairInfo']").val()
            if(!userName || !phone || !repairInfo) {
                layer.confirm("请填写完整报修信息！", {
                    btn: ['确定'] //按钮
                    , icon: 5
                    , anim: 6
                    , title: '错误信息'
                }, function (index) {
                    layer.close(index)
                })
                return;
            }
            // 新增报修信息
            var jsonObj = {
                "userId" : userId,
                "userName" : userName,
                "phone" : phone,
                "repairInfo" : repairInfo
            }
            console.log(jsonObj)
            $.ajax({
                url: "userRepair/insertInfo",
                type: 'POST',
                async: false,
                contentType : 'application/json;charset=utf-8', //设置请求头信息
                data: JSON.stringify(jsonObj),
                success: function(res) {
                    if (res.code == "0") {
                        layer.confirm(res.msg, {
                            btn: ['确定']  //按钮
                            , icon: 6
                        }, function () {
                            window.parent.location.reload();    //刷新父页面
                        });
                    } else {
                        layer.confirm(res.msg, {
                            btn: ['确定']  //按钮
                            , icon: 5
                            , anim: 6
                        }, function (index) {
                            layer.close(index);
                        });
                    }
                },
                error: function(res) {
                    layer.confirm('啊哦！访问出问题了！快找开发狗算账！', {
                        btn: ['确定']  //按钮
                        , icon: 5
                        , anim: 6
                    }, function (index) {
                        layer.close(index);
                    });
                }
            })
        })

        /**
         * 投诉按钮
         */
        $("#complaintBtn").click(function() {
            var userId = $("#userId").val();
            if(!userId) {
                layer.confirm("您还未登录！", {
                    btn: ['确定'] //按钮
                    , icon: 5
                    , anim: 6
                    , title: '错误信息'
                }, function (index) {
                    layer.close(index)
                })
                return;
            }
            var userName = $("input[name='complaintUserName']").val();
            var phone = $("input[name='complaintPhone']").val();
            var textInfo = $("textarea[name='complaintInfo']").val();
            if(!userName || !phone || !textInfo) {
                layer.confirm("请填写完整投诉信息！", {
                    btn: ['确定'] //按钮
                    , icon: 5
                    , anim: 6
                    , title: '错误信息'
                }, function (index) {
                    layer.close(index)
                })
                return;
            }
            // 新增投诉信息
            var jsonObj = {
                "userId" : userId,
                "userName" : userName,
                "phone" : phone,
                "complaintInfo" : textInfo
            }
            $.ajax({
                url: "userComplaint/insertInfo",
                type: 'POST',
                async: false,
                contentType : 'application/json;charset=utf-8', //设置请求头信息
                data: JSON.stringify(jsonObj),
                success: function(res) {
                    if (res.code == "0") {
                        layer.confirm(res.msg, {
                            btn: ['确定']  //按钮
                            , icon: 6
                        }, function () {
                            window.parent.location.reload();    //刷新父页面
                        });
                    } else {
                        layer.confirm(res.msg, {
                            btn: ['确定']  //按钮
                            , icon: 5
                            , anim: 6
                        }, function (index) {
                            layer.close(index);
                        });
                    }
                },
                error: function(res) {
                    layer.confirm('啊哦！访问出问题了！快找开发狗算账！', {
                        btn: ['确定']  //按钮
                        , icon: 5
                        , anim: 6
                    }, function (index) {
                        layer.close(index);
                    });
                }
            })
        })

        function getPath() {
            //获取项目路径
            var curRequestPath = window.document.location.href;
            //获取项目请求路径
            var pathName = window.document.location.pathname;
            var ipAndPort = curRequestPath.indexOf(pathName);
            var localhostPath = curRequestPath.substring(0,ipAndPort);
            var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
            basePath = localhostPath + projectName;
            return basePath;
        }
    })
})