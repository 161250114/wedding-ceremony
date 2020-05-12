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
        <el-form-item label="绑定手机号:"
          ><el-input disabled v-model="userInfo.phone"></el-input
        ></el-form-item>
      </el-form>
      <el-tabs>
        <el-tab-pane label="修改密码">
          <el-form label-position="left" label-width="100px">
            <el-form-item label="旧密码:">
              <el-input
                placeholder="输入旧密码"
                type="password"
                v-model="oldPassword"
              ></el-input>
            </el-form-item>
            <el-form-item label="新密码">
              <el-input
                placeholder="输入新密码"
                type="password"
                v-model="newPassword"
              ></el-input>
            </el-form-item>
            <el-form-item label="再次输入:">
              <el-input
                placeholder="再次输入新密码"
                type="password"
                v-model="newPassword2"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="changePassword"
                >修改密码</el-button
              >
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="修改手机号"
          ><el-form label-position="left" label-width="100px">
            <el-form-item label="验证码:">
              <el-row>
                <el-col :span="14"
                  ><el-input
                    placeholder="请输入验证码"
                    v-model="validateKey"
                  ></el-input
                ></el-col>
                <el-col :span="10"
                  ><el-button
                    type="primary"
                    @click="sendKey"
                    :disabled="!validateButton.active"
                    >{{ validateButton.message }}</el-button
                  ></el-col
                >
              </el-row>
            </el-form-item>
            <el-form-item label="新手机:">
              <el-input
                placeholder="请输入新手机号"
                v-model="newPhone"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="changePhone">修改手机号</el-button>
            </el-form-item>
          </el-form></el-tab-pane
        >
      </el-tabs>
    </div>
  </div>
</template>

<script>
import Axios from "axios";

export default {
  data() {
    return {
      userInfo: {
        userid: "1000001",
        username: "pikaqiu",
        phone: "132****1808",
      },
      oldPassword: "",
      newPassword: "",
      newPassword2: "",
      validateKey: "",
      newPhone: "",
      validateButton: {
        active: true,
        message: "发送验证码",
        leftSeconds: 0,
        maxSeconds: 10,
      },
    };
  },
  methods: {
    changePassword() {
      let app = this;
      Axios.post("/userInfo/changePassword", {
        oldPassword: app.oldPassword,
        newPassword: app.newPassword,
      }).then(function (res) {
        app.$message({
          message: res.data.message,
          type: "success",
        });
      });
    },
    sendKey() {
      let app = this;
      Axios.post("/sendValidateNumber",app.userInfo.phone)
        .then(function (res) {})
        .catch(function (error) {});
      app.validateButton.active = false;
      app.validateButton.leftSeconds = app.validateButton.maxSeconds;
      app.validateTimer = setInterval(function () {
        app.validateButton.message =
          "已发送(" + app.validateButton.leftSeconds + ")";
        app.validateButton.leftSeconds--;
        if (app.validateButton.leftSeconds === -1) {
          clearInterval(app.validateTimer);
          app.validateButton.active = true;
          app.validateButton.message = "发送验证码";
        }
      }, 1000);
    },
    changePhone(){
      let app=this;
      Axios.post("/userInfo/changePhone", {
        validateKey: app.validateKey,
        newPhone: app.newPhone,
      }).then(function (res) {
        app.$message({
          message: res.data.message,
          type: "success",
        });
      });
    }
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
    app.$emit("getIndex", "/personalInfo/changePassword");
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
