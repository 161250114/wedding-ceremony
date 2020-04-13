<template>
  <div class="page">
    <el-row>
      <el-col :span="1">
        <h1>&nbsp;</h1>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="2">
        &nbsp;
      </el-col>
      <el-col :span="3">
        &nbsp;
      </el-col>
      <el-col :span="5">
        <div class="wrap" style="margin-top:50px">
          <el-form>
            <el-form-item label="用户名/手机号：">
              <el-input></el-input>
            </el-form-item>
            <el-form-item label="密码：">
              <el-input></el-input>
              <el-button type="text" style="float:right;" size="small">忘记密码</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="primary">登录</el-button>
              <router-link to="/register"><el-button>注册</el-button></router-link>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default{
  data(){
      return{
        picUrls:["../../../static/loginPic1.png"]
      }
    }
}

</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.page {
  background-image:url("../../../static/loginBack.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
  height: 600px;
}
</style>
