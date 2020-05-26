<template>
  <div>
    <div class="title" style="width:800px;margin:0px auto 0;text-align: center"><p style="font-size: 20px">{{notice}}</p></div>
    <div class="talk_con">
      <div class="talk_show" id="words">
        <div  v-for="mess in list"  :class="{'atalk':mess.senderId==id,'btalk':mess.receiverId==id}"><span>{{mess.content}}</span></div>
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
            toid:-1,
            id:-1,
            input:"",
            isMy:true,
            list:[],
            notice:"正在与管理员聊天"
      }
      },
      created(){
        let app=this
        let toid=app.$route.query.toid
        let id=app.$route.query.id
        if(toid===undefined){
          app.toid=0
        }
        else{
          app.toid=toid;
        }
        if(id==0){
          app.id=0;
          app.notice="正在与"+app.$route.query.username+"聊天"
          app.load();
        }
        else{
          axios.get("/getCurrentUser")
            .then(function(res) {
              if(res.data.result==false){
                app.$router.push({
                  path: './login',
                })
              }
              app.id=res.data.message.userid
              app.load();
            })
            .catch(function (err) {
              console.log(err);
            })
        }
      },
      destroyed: function () {
        let app=this;
        if(app.id==-1){
          return;
        }
        else{
          let from=app.toid;
          let to=app.id;
          let str=from+"_"+to;
          axios.post('/systemmessage/read',str)
            .then(function(res){

            })
            .catch(function(err){
              console.log(err);
            });
        }
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
          let number=app.toid+app.id;
          axios.post('/systemmessage/get',number)
            .then(function(res){
              app.list=res.data
              console.log(app.list)
            })
            .catch(function(err){
              console.log(err);
            });
        },
        send(word){
          let app=this;
          var data = {};
          data["id"] = 0;
          data["senderId"]=app.id;
          data["receiverId"]=app.toid;
          data["content"]=app.input;
          data["state"]=0;
          axios.post("/systemmessage/add",data)
            .then(successResponse => {
              app.input=""
            })
            .catch(failResponse => {
              this.$alert("操作失败，请重试", '提示', {
                confirmButtonText: '确定',
              });
            }); //失败后的操作
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
