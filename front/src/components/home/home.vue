<template>
  <div>
    <div class="head">
      <Head :index="activeIndex"></Head>
    </div>
    <div class="wrap">
      <el-form label-width="80px">
        <el-form-item label="用户名">
          <el-input></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input></el-input >
        </el-form-item>
        <el-form-item>
          <el-button type="primary">登录</el-button>
          <el-button>注册</el-button>
        </el-form-item>
      </el-form>
      <el-card class="box-card">
        <div>
          <span class="title">1000$</span>
        </div>
      </el-card>
    </div>
  </div>

</template>

<script>
  import Head from '../head/head'
  export default {
    name: "home",
    components: {
      Head
    }
  }
</script>

<style scoped>
  .wrap {
    width: 500px;
    height: 1000px;
    margin: 10px auto;
  }
</style>
