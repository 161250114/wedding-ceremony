<template>
<div id="sendpanel" class="sp">
  <div id="wordpanel">
    <el-input
      type="textarea"
      :rows="2"
      placeholder="说点什么吧"
      v-model="words">
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
    export default {
      name: "happiness",
      data(){
        return{
          pnumber:0,
          dnumber:0,
          photolist:[],
          words:"",
          dialogImageUrl: '',
          dialogVisible: false
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
        handleSuccess(file){
          console.log(file)
          this.photolist.push(file.url)
          console.log(this.photolist)
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
