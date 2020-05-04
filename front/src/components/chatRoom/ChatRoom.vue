<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="12" :offset="6">
        <div class="main">
          <el-row>
            <el-input
              placeholder="请输入自己的昵称"
              prefix-icon="el-icon-user-solid"
              v-model="name"
              style="width:50%"
            ></el-input>
            <el-button type="primary" @click="connectWebSocket()">建立连接</el-button>
            <el-button type="danger">断开连接</el-button>
          </el-row>
          <el-row>
            <el-input
              placeholder="请输入对方频道号"
              prefix-icon="el-icon-phone"
              v-model="aisle"
              style="width:40%"
            ></el-input>
          </el-row>
          <el-row>
            <el-input
              placeholder="请输入要发送的消息"
              prefix-icon="el-icon-s-promotion"
              v-model="messageValue"
              style="width:50%"
            ></el-input>
            <el-button type="primary" @click="sendMessage()">发送</el-button>
          </el-row>
          <div class="message">
            <div v-for="(value,key,index) in messageList" :key="index">
              <el-tag v-if="value.name==name" type="success" style="float:right">我：{{value.msg}}</el-tag>
              <br />
              <el-tag v-if="value.name!=name" style="float:left">{{value.name}}：{{value.msg}}</el-tag>
              <br />
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        name: "", // 昵称
        websocket: null, // WebSocket对象
        aisle: "", // 对方频道号
        messageList: [], // 消息列表
        messageValue: "" // 消息内容
      };
    },
    created () {
      this.name = this.$route.query.name
    },
    methods: {
      connectWebSocket() {
        console.log("建立连接");
        if (this.name === "") {
          this.$alert("请输入自己的昵称", "提示", {
            confirmButtonText: "确定",
            callback: action => {}
          });
        } else {
          //判断当前浏览器是否支持WebSocket
          if ("WebSocket" in window) {
            this.websocket = new WebSocket(
              `ws://localhost:8080/websocket/${this.name}`
            );
          } else {
            alert("不支持建立socket连接");
          }
          //连接发生错误的回调方法
          this.websocket.onerror = function() {};
          //连接成功建立的回调方法
          this.websocket.onopen = function(event) {};
          //接收到消息的回调方法
          let that = this;
          this.websocket.onmessage = function(event) {
            let object = eval("(" + event.data + ")");
            console.log(object);
            if (object.type === 0) {
              // 提示连接成功
              console.log("连接成功");
              that.showInfo(object.people, object.aisle);
            }
            if (object.type === 1) {
              //显示消息
              console.log("接受消息");
              that.messageList.push(object);
            }
          };
          //连接关闭的回调方法
          this.websocket.onclose = function() {};
          //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
          window.onbeforeunload = function() {
            this.websocket.close();
          };
        }
      },
      // 发送消息
      sendMessage () {
        let socketMsg = {
          msg: this.messageValue,
          toUser: this.aisle
        };
        if (this.aisle === "") {
          //群聊.
          socketMsg.type = 0;
        } else {
          //单聊.
          socketMsg.type = 1;
        }
        this.websocket.send(JSON.stringify(socketMsg));
      },
      showInfo: function(people, aisle) {
        this.$notify({
          title: "当前在线人数：" + people,
          message: "您的频道号：" + aisle,
        });
      }
    }
  };
</script>

<style scoped>
  .main {
    position: relative;
    top: 20px;
  }
  .message {
    position: relative;
    overflow:auto;
    top: 20px;
    width: 100%;
    height: 40%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
    padding: 5px;
  }
</style>
