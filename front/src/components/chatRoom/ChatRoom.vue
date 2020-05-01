<template>
    <div style="text-align: center">
<!--        <br>欢迎使用<strong>VueTest</strong>极简聊天室：<br/><br/>-->
        <textarea id="content" v-model="content" cols="60" rows="30" readonly="readonly"></textarea><br>
        <input type="text" v-model="message">
        <button type="button" @click="sendMsg()">发送消息</button>
        <br/><br/>
        用户：<input type="text" v-model="user">
        <button @click="joinRoom()">加入群聊</button>
        <button @click="exitRoom()">退出群聊</button>
    </div>
</template>

<script>
    export default {
        name: "ChatRoom",
        data() {
            return {
                url: 'ws://'+ window.location.host + '/chatRoom/',
                ws: null,
                user: '',
                message: '',
                content: '',
            }
        },
        created(){
        },
        methods: {
            async joinRoom(){
                if(this.ws) {
                    alert("你已经再聊天室");
                    return;
                }
                // eslint-disable-next-line no-unused-vars
                let url = this.url;
                let username = this.user;
                console.log(username)
                this.ws = new WebSocket(`ws://localhost:8999/wedding/chatRoom/${username}`);  // 后端的启动端口
                this.ws.onopen = this.webscoketonopen;
                this.ws.onmessage =  this.webscoketonmessage;
                //发生错误触发
                this.ws.onerror = function () {
                    console.log("连接错误")
                };
                //正常关闭触发
                this.ws.onclose = function () {
                    console.log("连接关闭");
                };
            },
            webscoketonopen(){
                console.log("与服务器成功建立连接");
            },
            webscoketonmessage(value){
                console.log(value);
                this.content += (value.data + '\r\n') ;
            },
            exitRoom(){
                this.closeWebSocket();
            },
            sendMsg(){
                if(!this.ws) {
                    alert('你已经掉线，请重新加入');
                    return;
                }
                if(this.ws.readyState === 1){
                    this.ws.send(this.message);
                    this.message = '';
                } else {
                    alert('发送失败');
                }
            },
            closeWebSocket(){
                if(this.ws) {
                    this.ws.close();
                    this.ws = null;
                }
            },
            talking(content) {
                console.log(content);
            }
        }
    }
</script>

<style scoped>
</style>
