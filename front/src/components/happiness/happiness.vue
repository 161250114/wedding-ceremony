<template>
<div class="hsp">
  <div style="height: 100px;width: 800px;background-color: white;margin-left: auto;margin-right: auto;">
    <div style="float: left" @click="myroom">
      <el-avatar :size="80" src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png">
      </el-avatar>
    </div>
      <el-button size="200" style="float:right;top:auto" type="primary" icon="el-icon-edit" @click="write">
      </el-button>
  </div>
  <div  v-for="(h,index) in list" class="hp" :key="index">
    <div>
      <p @click="myroom" style="margin-left:-700px;margin-top: 50px"><el-avatar :size="30" src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" ></el-avatar>
      卢本伟</p>
    </div>
    <div class="wa">
      <div style="width: 760px;
      text-align: left;
      overflow: hidden;
      text-overflow: ellipsis;">{{list[index].content}}</div>
    </div>
    <div class="pa">
      <el-row>
        <el-col :span="8" :offset=0  v-for="photo in photolist[index]" :key="photo">
          <el-card :body-style="{ padding: '0px' }" >
            <img :src="photo" style="width: 250px;height: 250px" class="image">
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div class="ica">
      <span style="position: relative;;left:-60px;">
          <el-button v-if="!islike[index]" icon="el-icon-thumb" circle @click="like(index)"></el-button>
          <el-button v-if="islike[index]" type="danger" icon="el-icon-thumb" circle @click="like(index)"></el-button>
          {{likes[index]}}
      </span>
      <span style="position: relative;left:-30px;" v-if="list[index].senderId==id">
          <el-button icon="el-icon-delete" circle @click="del(index)"></el-button>
      </span>
    </div>
    <div class="ca">
      <div  v-for="comm in commentlist[index]" :key="comm"><p style="margin-left:-700px;margin-top: 50px"><el-avatar :size="30" src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" ></el-avatar>
        {{comm.content}}</p></div>
    </div>
    <div class="ia">
      <el-input v-model="input[index]" placeholder="评论" class="send_comment"></el-input>
      <el-button type="primary" class="talk_sub" @click="send(index)">发送</el-button>
    </div>
  </div>
</div>
</template>

<script>
  import axios from 'axios'
  import qs from 'qs'
    export default {
      name: "happiness",
      data(){
          return{
            id:-1,
            list:[],
            friendlist:[10,3],
            happinesslist:[],
            islike:[],
            likes:[],
            input:[],
            photolist:[],
            commentlist:[],
          }
      },
      computed:{

      },
      created(){
        this.load();
      },
      methods:{
        load(){
          let app=this
          axios.get("/getCurrentUser")
            .then(function(res) {
              if(res.data.result==false){
                app.$router.push({
                  path: './login',
                })
              }
              app.id=res.data.message.userid
              let ids=app.friendlist
              axios.post('/happiness/get',ids)
                .then(function(res){
                  app.list=res.data;
                  for(let i=0;i<app.list.length;i++){
                    app.happinesslist.push(app.list[i].id)
                  }
                  axios.post('/happiness/getPhotoList',app.happinesslist)
                    .then(function(res){
                      app.photolist=res.data
                    })
                    .catch(function(err){
                      console.log(err);
                    });
                  axios.post('/happiness/getCommentList',app.happinesslist)
                    .then(function(res){
                      console.log(app.happinesslist)
                      app.commentlist=res.data
                      console.log(res.data)
                    })
                    .catch(function(err){
                      console.log(err);
                    });
                  axios.post('/happiness/getLikes',app.happinesslist)
                    .then(function(res){
                      app.likes=res.data
                    })
                    .catch(function(err){
                      console.log(err);
                    });
                  axios.post('/happiness/getMyLikes',app.happinesslist)
                    .then(function(res){
                      app.islike=res.data
                    })
                    .catch(function(err){
                      console.log(err);
                    });
                })
                .catch(function (err) {
                  console.log(err);
                })
                })
                .catch(function(err){
                  console.log(err);
                });



        },
        write(){
          this.$router.push({
            path: './sendhappiness',
          })
        },
        myroom(){
          this.$router.push({
            path: './happiness',
          })
        },
        send(index){
          let word=this.input[index];
          let raw_commentlist=JSON.parse(JSON.stringify(this.commentlist[index]))
          let comment=new Object();
          comment.id=0;
          comment.senderId=this.id;
          comment.happinessId=this.list[index].id;
          comment.content=word
          comment.state=0;
          raw_commentlist.push(comment)
          this.commentlist.splice(index,1,raw_commentlist)
          axios.post('/comment/add',comment)
            .then(function(res) {

            })
            .catch(function (err) {
              console.log(err);
            })
        },
        like(index){
          let happinessId=this.happinesslist[index]
          axios.post('/happiness/updatel',happinessId)
            .then(function(res) {

            })
            .catch(function (err) {
              console.log(err);
            })
          let num=this.likes[index];
          if(this.islike[index]){
            num--;
          }
          else{
            num++
          }
          this.likes.splice(index,1,num)
          this.islike.splice(index,1,!this.islike[index])
        },
        del(index){
          let happinessId=this.happinesslist[index]
          this.list.splice(index,1)
          this.islike.splice(index,1)
          this.photolist.splice(index,1)
          this.likes.splice(index,1)
          this.happinesslist.splice(index,1)
          this.commentlist.splice(index,1)
          axios.post('/happiness/del',happinessId)
            .then(function(res) {
            })
            .catch(function (err) {
              console.log(err);
            })
        }
      }
    }
</script>

<style scoped>
  .ia{
    margin-top:40px;
    padding-bottom: 20px;
  }
  .ica{
    height: 40px;
    text-align: right;
  }
  .send_comment{
    width:620px;
    height:50px;
    padding:0px;
    float:left;
    margin-left:10px;
    outline:none;
    text-indent:10px;
  }
  .ca{
    width: 100%;
  }
  .hsp{
    width: 100%;
    background:gainsboro;
    text-align: center;
    overflow:auto;
  }
  .hp{
    width: 800px;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    background: white;
  }
  .wa{
    width:800px;
    height: 150px;
    display: flex;
    justify-content: center;
    align-items: center;

  }
</style>
