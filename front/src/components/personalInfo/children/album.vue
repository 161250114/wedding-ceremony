<template>
  <div class="info_div">
    <el-container>
      <el-header style="border-bottom: 1px solid #d3dce6;"
        ><h3>
          <label
            >当前相册最大容量：{{
              album.maxNumber
            }}&nbsp;&nbsp;&nbsp;已使用容量：{{ album.currentNumber }}</label
          >
        </h3>
      </el-header>
      <el-main>
        <el-carousel
          :initial-index="0"
          type="card"
          height="350px"
          :autoplay="false"
          indicator-position="outside"
          @change="
            (currentIndex) => {
              getPhotoIndex(currentIndex);
            }
          "
          :loop="false"
        >
          <el-carousel-item v-for="item in album.photos.length" :key="item">
            <img
              :src="album.photos[item - 1].url"
              style="width: 100%; height: 100%;"
            />
          </el-carousel-item>
        </el-carousel>
      </el-main>
      <el-footer>
        <el-alert
          title="相册的第一张图片会自动设为头像哦~~"
          type="success"
          :closable="false"
        >
        </el-alert>
        <p></p>
        <el-form label-width="100px">
          <el-row>
            <el-col :span="5">
              <el-form-item label="当前照片位序:">
                <el-input v-model="currentIndex" :disabled="true"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="3" v-if="editMode == false">
              <el-button type="primary" @click="changeMode(true)"
                >更改</el-button
              >
            </el-col>
            <div v-if="editMode == true">
              <el-col :span="5">
                <el-form-item label="新位序:">
                  <el-input v-model="newIndex"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-button type="primary" @click="saveEdit">保存</el-button>
                <el-button type="primary" @click="changeMode(false)"
                  >取消</el-button
                >
              </el-col>
            </div>
            <el-tooltip
              class="item"
              effect="dark"
              content="删除本张照片"
              placement="bottom-start"
            >
              <el-button
                type="danger"
                icon="el-icon-delete"
                circle
                @click="deletePhoto"
              ></el-button>
            </el-tooltip>
            <div style="float: right;">
              <router-link to="/personalInfo/addAlbumMax"
                ><el-button type="primary" size="medium"
                  >购买相册容量</el-button
                ></router-link
              >
              <label>&nbsp;</label>
              <router-link to="/personalInfo/uploadPhoto"
                ><el-button type="primary" size="medium"
                  >上传照片</el-button
                ></router-link
              >
            </div>
          </el-row>
        </el-form>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  data() {
    return {
      album: { photos: [] },
      currentIndex: 1,
      newIndex: "",
      editMode: false,
    };
  },
  methods: {
    getPhotoIndex(currentIndex) {
      let app = this;
      app.currentIndex = currentIndex + 1;
    },
    changeMode(newMode) {
      let app = this;
      app.editMode = newMode;
    },
    saveEdit() {
      let app = this;
      let temp = app.album.photos[app.currentIndex - 1];
      Axios.post("/photo/changePhotoOrder", {
        id: app.album.photos[app.currentIndex - 1].id,
        order_number: app.newIndex,
      })
        .then(function (res) {
          if (res.data.result) {
            app.$message({
              message: res.data.message,
              type: "success",
            });
          }
          app.$emit("updateInfo");
        })
        .catch(function (error) {});
      app.album.photos.splice(
        app.currentIndex - 1,
        1,
        app.album.photos[app.newIndex - 1]
      );
      app.album.photos.splice(app.newIndex - 1, 1, temp);
      app.newIndex = "";
      app.changeMode(false);
    },
    deletePhoto() {
      let app = this;
      Axios.post("/photo/deletePhoto", app.album.photos[app.currentIndex - 1])
        .then(function (res) {
          if (res.data.result) {
            app.album.photos.splice(app.currentIndex - 1, 1);
            app.album.currentNumber--;
            app.$message({
              message:
                "已删除第" +
                app.currentIndex +
                "张照片，当前相册剩余" +
                app.album.photos.length +
                "张！",
              type: "success",
            });
            app.$emit("updateInfo");
          }
        })
        .catch(function (error) {});
    },
  },
  created() {
    let app = this;
    Axios.get("/photo/getAlbum").then(function (res) {
      console.log(res.data);
      if (res.data.result) {
        app.album = JSON.parse(JSON.stringify(res.data.message));
        for (let i = 0; i < app.album.currentNumber; i++) {
          app.album.photos[i].url = "/api" + app.album.photos[i].url;
        }
      }
    });
    app.$emit("getIndex", "/personalInfo/album");
  },
};
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.info_div {
  margin-top: 30px;
  margin-left: 100px;
  margin-right: 100px;
}
</style>
