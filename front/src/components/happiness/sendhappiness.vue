<template>
<div id="sendpanel" class="sp">
  <div id="wordpanel">
    <el-input
      type="textarea"
      :rows="2"
      maxlength="250"
      placeholder="说点什么吧"
      v-model="happiness.content"
      show-word-limit>
    </el-input>
  </div>
  <div id="photopanel" class="pp">
    <el-upload
      action="https://jsonplaceholder.typicode.com/posts/"
      list-type="picture-card"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess"
      :before-upload="addP"
    >
      <i class="el-icon-plus"
         v-if="this.pnumber-this.dnumber<10"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
  <div id="buttonpanel" class="bp">
    <el-button type="primary" class="talk_sub" @click="send()">发布动态</el-button>
    <el-button type="primary" class="talk_sub" @click="save()">存为草稿</el-button>
  </div>
</div>
</template>

<script>
  import axios from 'axios'
    export default {
      name: "happiness",
      data(){
        return{
          pnumber:0,
          dnumber:0,
          photolist:[],
          dialogImageUrl: '',
          dialogVisible: false,
          happiness:{
            id:0,
            senderId:1,
            time:new Date(),
            content:"",
            likes:0,
            state:0
          },
          hplist:[],
          id:0
        }
      },
      methods:{
        handleRemove(file,filelist) {
          this.dnumber++;
        }
      ,
        handlePictureCardPreview(file) {
          this.dialogImageUrl = file.url;
          this.dialogVisible = true;
        },
        handleSuccess(response, file, fileList){
          this.getBase64(file.raw).then(res => {
            this.photolist.push(res)
          });
        },
        addP(file){
          this.pnumber++;
          if(this.pnumber-this.dnumber==10){
            this.$alert("至多只能上传九张照片！", '提示', {
              confirmButtonText: '确定',
            });
            return false;
          }
          return true;
        },
        getBase64(file) {
          return new Promise(function(resolve, reject) {
            let reader = new FileReader();
            let imgResult = "";
            reader.readAsDataURL(file);
            reader.onload = function() {
              imgResult = reader.result;
            };
            reader.onerror = function(error) {
              reject(error);
            };
            reader.onloadend = function() {
              resolve(imgResult);
            };
          });
        },
        send(){
          let app=this
          console.log(app.photolist)
          axios.get('/happiness/getId')
            .then(function(res){
              console.log(res)
              app.id=parseInt(res.data)
              for(let i in app.photolist){
                let hp=new Object();
                hp.id=0
                hp.happinessId=app.id
                hp.photo=app.photolist[i].toString()
                app.hplist.push(hp)
              }
              axios.post("/happiness/add",app.happiness)
                .then(successResponse => {})
                .catch(failResponse => {
                  this.$alert("操作失败，请刷新页面重试", '提示', {
                    confirmButtonText: '确定',
                  });
                }); //失败后的操作
              axios.post("/happinessphoto/addlist",app.hplist)
                .then(successResponse => {
                  app.$alert("发布成功", '提示', {
                  confirmButtonText: '确定',
                });})
                .catch(failResponse => {
                  this.$alert("操作失败，请刷新页面重试", '提示', {
                    confirmButtonText: '确定',
                  });
                }); //失败后的操作
            })
            .catch(function(err){
              console.log(err);
            });

        },
        save(){
          let app=this
          app.happiness.state=1
          console.log(app.happiness.state)
          axios.get('/happiness/getId')
            .then(function(res){
              console.log(res)
              app.id=parseInt(res.data)
              for(let i in app.photolist){
                let hp=new Object();
                hp.id=0
                hp.happinessId=app.id
                hp.photo=app.photolist[i].toString()
                app.hplist.push(hp)
              }
              axios.post("/happiness/add",app.happiness)
                .then(successResponse => {})
                .catch(failResponse => {
                  this.$alert("操作失败，请刷新页面重试", '提示', {
                  confirmButtonText: '确定',
                });}); //失败后的操作
              axios.post("/happinessphoto/addlist",app.hplist)
                .then(successResponse => {
                  app.$alert("发布成功", '提示', {
                  confirmButtonText: '确定',
                });})
                .catch(failResponse => {
                  this.$alert("操作失败，请刷新页面重试", '提示', {
                  confirmButtonText: '确定',
                });}); //失败后的操作
            })
            .catch(function(err){
              console.log(err);
            });

        }
      }
    }
</script>

<style scoped>
  .sp{
    width:800px;
    height:200px;
    margin:100px 200px;
  }
  .pp{
    width: 800px;
    height:300px;
    margin: 20px;
  }
  .bp{
    width: 800px;
    height: 200px;
    margin: 20px;
  }
</style>
