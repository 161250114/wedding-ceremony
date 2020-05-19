<template>
  <div>
    <el-container>
      <el-header style="border-bottom: 1px solid #d3dce6;"
        ><h3>
          <label
            >当前相册最大容量：{{ maxNum }}&nbsp;&nbsp;&nbsp;已使用容量：{{
              currentNum
            }}&nbsp;&nbsp;&nbsp;剩余可上传照片数量：{{
              maxNum - currentNum
            }}</label
          >
        </h3></el-header
      >
      <el-main style="height: 350px; border: 1px solid #d3dce6;">
        <el-row
          ><el-col :span="1"
            ><div
              style="
                height: 280px;
                width: 20px;
                text-align: center;
                color: #f5989d;
              "
            >
              <h4>此处上传照片</h4>
            </div>
          </el-col>
          <el-col :span="1"
            ><div
              style="
                height: 280px;
                width: 20px;
                text-align: center;
                color: #f5989d;
              "
            >
              <h4>请选择清晰美观的个人近照</h4>
            </div></el-col
          >
          <el-col :span="1"
            ><div
              style="
                height: 280px;
                width: 20px;
                text-align: center;
                color: #f5989d;
              "
            >
              <h4>作为你的相册照片</h4>
            </div></el-col
          >
          <el-col :span="18" style="border: 1px dashed;">
            <div>
              <el-upload
                ref="photoUploader"
                action="/api/photo/upload"
                list-type="picture-card"
                :on-preview="handlePictureCardPreview"
                :on-remove="handleRemove"
                style="float: left;"
                :file-list="pictureList"
                :auto-upload="false"
                :on-success="uploadSuccess"
                multiple
              >
                <i class="el-icon-plus"></i>
              </el-upload></div
          ></el-col>
          <el-col :span="1"
            ><div
              style="
                height: 280px;
                width: 20px;
                text-align: center;
                float: right;
                color: #f5989d;
              "
            >
              <h4>作为你的相册照片</h4>
            </div></el-col
          ><el-col :span="1"
            ><div
              style="
                height: 280px;
                width: 20px;
                text-align: center;
                float: right;
                color: #f5989d;
              "
            >
              <h4>请选择清晰美观的个人近照</h4>
            </div></el-col
          ><el-col :span="1"
            ><div
              style="
                height: 280px;
                width: 20px;
                text-align: center;
                float: right;
                color: #f5989d;
              "
            >
              <h4>此处上传照片</h4>
            </div>
          </el-col>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" /> </el-dialog
        ></el-row>
      </el-main>
      <el-footer></el-footer
      ><el-row>
        <el-button size="small" type="primary" @click="removeAll"
          >清空</el-button
        >
        <el-button
          style="margin-left: 10px;"
          size="small"
          type="success"
          @click="submitUpload"
          >上传到相册</el-button
        >
      </el-row>
    </el-container>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  data() {
    return {
      maxNum: 5,
      currentNum: 4,
      dialogImageUrl: "",
      dialogVisible: false,
      pictureList: [],
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    removeAll() {
      let app = this;
      app.pictureList.splice(0, app.pictureList.length);
    },
    submitUpload(file, fileList) {
      let app = this;
      console.log(fileList);
      if (
        app.$refs.photoUploader.uploadFiles.length >
        app.maxNum - app.currentNum
      ) {
        app.$message({
          message: "相册容量不足！",
          type: "warning",
        });
      } else {
        app.$refs.photoUploader.submit();
      }
    },
    uploadSuccess() {
      let app = this;
      app.currentNum += app.$refs.photoUploader.uploadFiles.length;
      app.removeAll();
      app.$message({
        message: "上传成功！",
        type: "success",
      });
      console.log("ok");
      app.$emit("updateInfo");
    },
    getAlbumInfo() {
      let app = this;
      Axios.get("/photo/getAlbum").then(function (res) {
        console.log(res.data);
        if (res.data.result) {
          app.maxNum = res.data.message.maxNumber;
          app.currentNum = res.data.message.currentNumber;
        }
      });
    },
  },
  created() {
    this.getAlbumInfo();
    app.$emit("getIndex", "/personalInfo/uploadPhoto");
  },
};
</script>
<style scoped></style>
