
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