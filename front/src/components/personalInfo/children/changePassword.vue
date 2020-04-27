<template>
  <div>
    <div class="info_div">
      <el-form label-position="left" label-width="100px">
        <el-form-item label="用户ID:"
          ><el-input disabled v-model="userInfo.id"></el-input
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
              <el-button type="primary">修改密码</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="修改手机号"
          ><el-form label-position="left" label-width="100px">
            <el-form-item label="验证码:">
              <el-row>
                <el-col span="14"
                  ><el-input
                    placeholder="请输入验证码"
                    v-model="validatekey"
                  ></el-input
                ></el-col>
                <el-col span="10"><el-button type="primary">发送验证码</el-button></el-col>
              </el-row>
            </el-form-item>
            <el-form-item label="新手机:">
              <el-input
                placeholder="请输入新手机号"
                v-model="newPhone"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary">修改手机号</el-button>
            </el-form-item>
          </el-form></el-tab-pane
        >
      </el-tabs>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userInfo: {
        id: "1000001",
        username: "pikaqiu",
        phone: "132****1808"
      },
      oldPassword: "",
      newPassword: "",
      newPassword2: "",
      validateKey: "",
      newPhone: ""
    };
  },
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/changePassword");
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
