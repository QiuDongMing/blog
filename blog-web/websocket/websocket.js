/**
 * Created by dell on 2018/6/29.
 */
var websocket = null;
var getMessageTimes = 0;
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    var sig = generateMixed(10);
    console.log("sig:" + sig);
    websocket = new WebSocket("ws://localhost:8585/websocket/" + sig);
}
else {
    alert('当前浏览器 Not support websocket')
}
//连接发生错误的回调方法
websocket.onerror = function () {
    setMessageInnerHTML("WebSocket连接发生错误");
};
//连接成功建立的回调方法
websocket.onopen = function () {
    setMessageInnerHTML("WebSocket连接成功");
}
//接收到消息的回调方法
websocket.onmessage = function (event) {
    getMessageTimes++;
    setMessageInnerHTML(event.data);
}
//连接关闭的回调方法
websocket.onclose = function () {
    setMessageInnerHTML("WebSocket连接关闭");
}
//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    closeWebSocket();
}
//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    //document.getElementById('message').innerHTML += innerHTML + '<br/>';
    document.getElementById('message').innerHTML = innerHTML + ',count=' + getMessageTimes + '<br/>';

}
//关闭WebSocket连接
function closeWebSocket() {
    websocket.close();
}
//发送消息
function send() {
    var message = document.getElementById('text').value;
    websocket.send(message);
}


function generateMixed(n) {
    var res = "";
    var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
        'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
    for (var i = 0; i < n; i++) {
        var id = Math.ceil(Math.random() * 35);
        res += chars[id];
    }
    return res;
}


