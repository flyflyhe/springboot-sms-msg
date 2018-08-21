###遇到问题HttpServletRequest 获取不到form对象
```
    原因没有添加multipart/form-data解析器<br>
    commons-fileupload commons-io 配置multipartResolver解决<br>
    或者使用application/x-www-form-urlencoded
```

###webSocket
```
    var socket = new WebSocket("ws://127.0.0.1:8080/smsHandle");<br>
    socket.onopen = function() {
        console.log("open")
        socket.send("hi");
    }<br>
    socket.onmessage = function(msg) {
        console.log(msg)
    };  
```
    
###@RequestBody与@ModelAttribute区别
```
@RequestBody 接收post put body体中的data eg:json xml  
@ModelAttribute 接收key&value键值对  
```

###MyBatis连接不手动关闭 会有性能问题
