<template>
  <div>
    <div class="info_div">
      <el-form label-position="left" label-width="100px">
        <el-form-item label="用户ID:"
          ><el-input disabled v-model="userInfo.userid"></el-input
        ></el-form-item>
        <el-form-item label="用户名:"
          ><el-input disabled v-model="userInfo.username"></el-input
        ></el-form-item>
        <el-form-item label="账户余额:"
          ><el-input disabled v-model="userInfo.balance"></el-input
        ></el-form-item>
        <el-form-item label="充值金额:">
          <el-radio-group v-model="chargeMoney">
            <el-radio-button label="10">10¥</el-radio-button>
            <el-radio-button label="50">50¥</el-radio-button>
            <el-radio-button label="100">100¥</el-radio-button>
            <el-radio-button label="200">200¥</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-alert type="success" :closable="false"
          >充值的金额可以用来购买付费会员，相册容量等服务哦~</el-alert
        >
        <p></p>
      </el-form>
      <el-button type="primary" @click="charge">充值</el-button>
    </div>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  data() {
    return {
      userInfo: {
        userid: 0,
        username: "pikaqiu",
        balance: 50,
      },
      chargeMoney: 10,
      chargeType: "1",
    };
  },
  methods: {
    charge() {
      let app = this;
      Axios.post("/buy/chargeMoney", app.chargeMoney).then(function (res) {
        if (res.data.result) {
          app.$message({
            message: "修改成功！",
            type: "success",
          });
          window.location.href = "/personalInfo/baseInfo";
        }
      });
    },
  },
  created() {
    let app = this;
    Axios.get("/userInfo/getStatusInfo")
      .then(function (res) {
        console.log(res);
        if (res.data.result) {
          app.userInfo = res.data.message;
        }
      })
      .catch(function (error) {});
    app.$emit("getIndex", "/personalInfo/charge");
  },
};
</script>
<style scoped>
.info_div {
  margin-top: 30px;
  width: 400px;
  margin-left: 100px;
}
</style>
