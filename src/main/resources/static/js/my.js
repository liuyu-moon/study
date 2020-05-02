
//动态点赞
function addlikeMoment( momentId) {

    console.log(momentId);
    var  formData=new  FormData;
    formData.append("id",momentId);
    // Ajax 异步提交文件
    $.ajax({
            type: "post",
            url: "/addlikemoment",
            data:formData,//上一步的FormData
            cache: false,//不缓存数据
            processData: false,//不转换数据
            contentType: false,
            dataType: "json",
            success: function (data) {

                if (data.code == 200) {
                    // 页面的刷新
                    // setInterval(function () {
                    //     location.replace(location.href);
                    // }, 1500);
                    alert(data.msg)
                }
            }
        }
    )
}


// 评论点赞
function addlikeComment( commentId) {

    console.log(commentId);
    var  formData=new  FormData;
    formData.append("id",commentId);
    // Ajax 异步提交文件
    $.ajax({
            type: "post",
            url: "/addlikecomment",
            data:formData,//上一步的FormData
            cache: false,//不缓存数据
            processData: false,//不转换数据
            contentType: false,
            dataType: "json",
            success: function (data) {

                if (data.code == 200) {
                    // 页面的刷新
                    // setInterval(function () {
                    //     location.replace(location.href);
                    // }, 1500);
                    alert(data.msg);
                }
            }
        }
    )
}


function searchFriend(user_name) {
    console.log(user_name);

    window.location.href='/searchFriend/'+user_name;
}


function  websocketUtil() {

    let websocket = null;
    let userId = document.getElementById('userId').value;

    console.log("UUUU"+userId);

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://127.0.0.1:8080/replywebsocket/" + userId);
        alert('当前浏览器支持websocket哦！')
    }
    else {
        alert('当前浏览器不支持websocket哦！')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        console.log('连接失败');
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
        console.log('连接成功');

    }

    //接收到消息的回调方法,居右显示
    websocket.onmessage = function (event) {
        setReply(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        console.log('服务器断开连接');
    }

    // 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }


    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }


}
function  setReply(sendMessage) {


    let date = new Date()
    let y = date.getFullYear();<!--年-->
    let m = date.getMonth()+1;<!--月，这里的月份必须要+1才是当前月份-->
    let d = date.getDate(); <!--日，getDay是获得当前星期几（0-6），getDate是获得当前日期-->

    let content=sendMessage.split(".")[0];
    let momentId=sendMessage.split(".")[1];
    console.log(momentId);

    let replyCount=$("#replyCount").attr("value");
    console.log(replyCount);
    document.getElementById("replyCount").innerText = parseInt(replyCount)+1;
    $("#replyCount").attr("value",parseInt(replyCount)+1);
    $("#reply").append(`
                            <a class="dropdown-item d-flex align-items-center" href="/godetail/${momentId}">
                        <div class="mr-3">
                            <div class="icon-circle bg-success">
                                <i class="feather-edit text-white"></i>
                            </div>
                        </div>
                        <div>
                            <div class="small text-gray-500">${y}-${m}-${d}</div>
                           ${content}
                        </div>
                    </a>

        `)
}

function deletemoment(id) {
    let formData=new  FormData();

    formData.append("id",id);


    // Ajax 异步提交文件
    $.ajax({
            type: "post",
            url: "/deletemoment",
            data:formData ,//上一步的FormData
            cache: false,//不缓存数据
            processData: false,//不转换数据
            contentType: false,
            dataType: "json",
            success: function (data) {
                alert(data.msg);

            }
        }
    )
}



function searchMoment(tag) {
    console.log(tag);

    window.location.href='/searchMoment/'+tag;
}

function addF(uid,fid) {


    let formData=new  FormData();

    formData.append("user_id",uid);
    formData.append("friend_id",fid);
    // formData.append("multipartFile",file);

    // Ajax 异步提交文件
    $.ajax({
            type: "post",
            url: "/addFriend",
            data:formData ,//上一步的FormData
            cache: false,//不缓存数据
            processData: false,//不转换数据
            contentType: false,
            dataType: "json",
            success: function (data) {
                alert("666");
                alert(data.msg);

            }
        }
    )

}