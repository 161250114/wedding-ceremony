<template>
  <div class="info_div">
    <el-form label-position="left" label-width="120px">
      <el-form-item label="用户ID:"
        ><el-input disabled v-model="userInfo.id"></el-input
      ></el-form-item>
      <el-form-item label="用户名:"
        ><el-input disabled v-model="userInfo.username"></el-input
      ></el-form-item>
      <el-form-item label="账户余额:"
        ><el-input disabled v-model="userInfo.balance"></el-input
      ></el-form-item>
      <el-form-item label="当前相册容量:"
        ><el-input disabled v-model="userInfo.albumMax"></el-input
      ></el-form-item>
      <el-form-item label="增加相册容量:">
        <el-radio-group v-model="chargeAlbumMax">
          <el-radio-button label="5">5张</el-radio-button>
          <el-radio-button label="10">10张</el-radio-button>
          <el-radio-button label="20">20张</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="需付金额:">
        <b v-if="chargeAlbumMax == '5'">5¥</b>
        <b v-if="chargeAlbumMax == '10'">10¥</b>
        <b v-if="chargeAlbumMax == '20'">15¥</b>
        <label
          v-if="chargeAlbumMax == '20'"
          style="text-decoration:line-through;font-style: italic"
          >20¥</label
        >
      </el-form-item>
      <el-alert type="warning" :closable="false">相册容量购买后即为永久扩充，无有效期</el-alert>
      <p></p>
    </el-form>
    <el-button type="primary">购买</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userInfo: {
        id: "1000001",
        username: "pikaqiu",
        balance: 50,
        albumMax: 5
      },
      chargeAlbumMax: "5"
    };
  },
  methods: {},
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/addAlbumMax");
  }
};
</script>

<style scoped>
.info_div {
  margin-top: 30px;
  width: 400px;
  margin-left: 100px;
}
</style>
