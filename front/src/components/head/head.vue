<template>
  <div>
    <el-header class="header">
      <el-row>
        <el-col :span="2">
          &nbsp;
        </el-col>
        <router-link to="/home">
          <el-col :span="2">
            <el-avatar
              shape="square"
              :size="55"
              fit="fill"
              src="../../../static/timg.jpg"
            ></el-avatar>
          </el-col>
          <el-col :span="3">
            <img src="../../../static/title.png" class="title-img" />
          </el-col>
        </router-link>
        <el-col :xs="8" :sm="11" :md="14" :lg="13" :xl="13">
          &nbsp;
        </el-col>
        <el-col :span="3" v-show="!isLogin">
          <router-link to="/login"
            ><el-button type="text" style="margin-top: 10px;"
              >登录</el-button
            ></router-link
          >&nbsp;&nbsp;&nbsp;
          <router-link to="/register"
            ><el-button type="text">注册</el-button></router-link
          >
        </el-col>
        <el-col :span="3" v-show="isLogin">
          <p style="margin-top: 15px;">
            欢迎您，{{ username }}&nbsp;
            <el-dropdown>
              <span class="el-dropdown-link">
                <i class="el-icon-user-solid"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <router-link to="/personalInfo">
                  <el-dropdown-item>个人中心</el-dropdown-item></router-link
                >
                <router-link to="/ApplyWedding">
                  <el-dropdown-item>申请婚喜宴</el-dropdown-item>
                </router-link>
                <router-link to="/Wedding">
                  <el-dropdown-item>查看婚喜宴</el-dropdown-item>
                </router-link>
                <router-link to="/Happiness">
                  <el-dropdown-item>查看好友动态</el-dropdown-item>
                </router-link>
                <router-link to="/consult">
                  <el-dropdown-item>在线咨询</el-dropdown-item>
                </router-link>
                <el-dropdown-item divided
                  ><label @click="quit()">退出登录</label></el-dropdown-item
                >
              </el-dropdown-menu>
            </el-dropdown>
          </p>
        </el-col>
      </el-row>
    </el-header>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  name: "head",
  props: {
    index: {
      type: String,
    },
  },
  data() {
    return {
      username: "",
      isLogin: false,
    };
  },
  methods: {
    quit() {
      let app = this;
      console.log("ok");
      Axios.get("/quitLogin").then(function (res) {
        console.log(res);
        if (res.data.result) {
          app.isLogin = false;
        }
      });
    },
  },
  created() {
    let app = this;
    Axios.get("/getCurrentUser")
      .then(function (res) {
        console.log("head", res);
        if (res.data.result) {
          app.username = res.data.message.uname_phone;
          app.isLogin = true;
        }
      })
      .catch(function (error) {});
  },
};
</script>

<style scoped>
.header {
  background: #f6f6f6;
}
.title-img {
  height: 55px;
}
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.router-link-active {
  text-decoration: none;
}
a {
  text-decoration: none;
}
</style>
