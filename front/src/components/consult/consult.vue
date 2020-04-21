<template>
  <div>
    <div class="title" style="text-align: center"><p style="font-size: 20px">正在与管理员聊天</p></div>
    <div class="talk_con">
      <div class="talk_show" id="words">
        <div  v-for="mess in list"  :class="{'atalk':mess.sender_id==1,'btalk':mess.receiver_id==1}"><span>{{mess.content}}</span></div>
      </div>
      <div class="talk_input">
        <el-input v-model="input" placeholder="请输入内容" class="talk_word"></el-input>
        <el-button type="primary" class="talk_sub" @click="send()">发送</el-button>
      </div>
    </div>
  </div>
</template>

<script>
    import axios from 'axios'
    export default {
      name: "consult",
      data(){
          return {
            input:"",
            isMy:true,
            list:[],
      }
      },
      created(){
        this.load();
      },
      mounted: function () {
        if(this.timer){
          clearInterval(this.timer);
        } else{
          this.timer = setInterval(() => {
            this.load();//再次加载数据,自己定义的方法
            console.log("成功了");
          }, 1000);//每隔十秒刷新一次
        }
      },
      methods:{
        load(){
          let app=this
          axios.get('/systemmessage/getAll')
            .then(function(res){
              app.list=res.data
            })
            .catch(function(err){
              console.log(err);
            });
        },
        send(word){
          let app=this;
          var data = {};
          data["id"] = 0;
          data["sender_id"]=1;
          data["receiver_id"]=0;
          data["content"]=app.input;
          data["state"]=0;
          axios.post("/systemmessage/add",data)
            .then(successResponse => {alert("lbw")})
            .catch(failResponse => {}); //失败后的操作
        },
        addA(word){

        }
      }
    }
</script>

<style type="text/css">
  .talk_con{
    width:800px;
    height:500px;
    border:1px solid #666;
    margin:0px auto 0;
    background:#f9f9f9;
  }
  .talk_show{
    width:780px;
    height:420px;
    border:1px solid #666;
    background:#fff;
    margin:10px auto 0;
    overflow-y:auto;
  }
  .talk_input{
    width:780px;
    margin:10px auto 0;
  }
  .talk_word{
    width:620px;
    height:50px;
    padding:0px;
    float:left;
    margin-left:10px;
    outline:none;
    text-indent:10px;
  }
  .talk_sub{
    width:100px;
    height:50px;
    float:left;
    margin-left:30px;
    text-align: center;
  }
  .atalk{
    margin-top:20px;
    text-align:left;
    word-wrap: break-word;
    overflow:hidden;
    white-space:normal;
    width: 780px;
  }
  .atalk span{
    display:inline-block;
    background:#0181cc;
    border-radius:10px;
    color:#fff;
    padding:5px 10px;
    max-width: 350px;
    word-wrap:break-word ;
  }
  .btalk{
    margin-top:20px;
    text-align:right;
    word-wrap: break-word;
    overflow:hidden;
    white-space:normal;
    width: 780px;
  }
  .btalk span{
    display:inline-block;
    background:#ef8201;
    border-radius:10px;
    color:#fff;
    padding:5px 10px;
    max-width: 350px;
    word-wrap:break-word ;
  }
</style>
