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
      :on-remove="handleRemove">
      <i class="el-icon-plus"></i>
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
          photolist:{},
          words:"",
          dialogImageUrl: '',
          dialogVisible: false
        }
      },
      methods:{
        handleRemove(file, fileList) {
          console.log(file, fileList);
        }
      ,
        handlePictureCardPreview(file) {
          this.dialogImageUrl = file.url;
          this.dialogVisible = true;
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
