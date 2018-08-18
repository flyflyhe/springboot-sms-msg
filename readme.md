###遇到问题HttpServletRequest 获取不到form对象<br>
    #####原因没有添加multipart/form-data解析器
    #####commons-fileupload commons-io 配置multipartResolver解决
    #####或者使用application/x-www-form-urlencoded

###webSocket<br>
    var socket = new WebSocket("ws://127.0.0.1:8080/smsHandle");
    socket.onopen = function() {
        console.log("open")
        socket.send("hi");
    }
    socket.onmessage = function(msg) {
        console.log(msg)
    };
