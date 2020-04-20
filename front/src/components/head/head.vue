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
            ><el-button type="text" style="margin-top:10px;"
              >登录</el-button
            ></router-link
          >&nbsp;&nbsp;&nbsp;
          <router-link to="/register"
            ><el-button type="text">注册</el-button></router-link
          >
        </el-col>
        <el-col :span="3" v-show="isLogin">
          <p style="margin-top:15px">
            欢迎您，{{ username }}&nbsp;
            <el-dropdown>
              <span class="el-dropdown-link">
                <i class="el-icon-user-solid"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <router-link to="/personalInfo">
                  <el-dropdown-item>个人中心</el-dropdown-item></router-link
                >
                <el-dropdown-item>功能2</el-dropdown-item>
                <el-dropdown-item>功能3</el-dropdown-item>
                <el-dropdown-item>功能4</el-dropdown-item>
                <el-dropdown-item divided @click="quit"
                  >退出登录</el-dropdown-item
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
export default {
  name: "head",
  props: {
    index: {
      type: String
    },
    isLogin: Boolean
  },
  data() {
    return {
      username: "pikaqiu"
    };
  },
  created() {},
  methods: {
    quit() {
      let app = this;
      app.$emit("quit", false);
    }
  }
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
