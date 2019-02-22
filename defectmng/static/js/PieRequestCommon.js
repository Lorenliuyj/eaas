
var pieAjaxRequest = function (object,url,axios,callback){
    if(null == url || "" == url){
        console.log("未设置查询路径");
        return;
    }
    axios.post(url,object)
        .then(
        response =>{
            if(typeof callback == "function"){
                callback(response);
            }
            return response;
        },
        function(response) {
            // 这里是处理错误的回调
            console.log(response);
        }
    );
}

export {
    pieAjaxRequest
}