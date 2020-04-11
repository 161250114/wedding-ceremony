<template>
  <div>
    <el-header class="header">
      <el-row>
        <el-col :span="2">
          &nbsp;
        </el-col>
        <a href="./">
          <el-col :span="2">
            <el-avatar shape="square" :size="55" fit="fill" src="../../../static/timg.jpg"></el-avatar>
          </el-col>
          <el-col :span="3">
            <img src="../../../static/title.png" class="title-img">
          </el-col>
        </a>
        <el-col :span="13">
          &nbsp;
        </el-col>
          <el-col :span="3" v-show="!isLogin">
            <el-menu :default-active="activeIndex" class="header" mode="horizontal" @select="handleSelect">
              <el-menu-item index="1">登录</el-menu-item>
              <el-menu-item index="2">注册</el-menu-item>
            </el-menu>
          </el-col>
          <el-col :span="3" v-show="isLogin"> 
            <h3>欢迎您，{{username}}&nbsp;
              <el-dropdown @command="handleCommand">
                <span class="el-dropdown-link">
                  <i class="el-icon-user-solid"></i>
                </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="1">功能1</el-dropdown-item>
                    <el-dropdown-item command="2">功能2</el-dropdown-item>
                    <el-dropdown-item command="3">功能3</el-dropdown-item>
                    <el-dropdown-item command="4">功能4</el-dropdown-item>
                    <el-dropdown-item divided command="quit">退出登录</el-dropdown-item>
                  </el-dropdown-menu>
              </el-dropdown>
            </h3>
            
          </el-col>
      </el-row>
    </el-header>
  </div>
</template>

<script>
  export default {
    name: "head",
    props:{
      index: {
        type: String
      }
    },
    data(){
      return{
        activeIndex:3,
        isLogin:false,
        username:"pikaqiu"
      }
    },
    created(){

    },
    methods: {
      handleSelect(key) {
        switch(key){
          case  '1':
            let app=this;
            app.isLogin=!app.isLogin;
            // this.$router.push("/login")
            break;
          case  '2':
            // this.$router.push("/register")
            break;

        }
      },
      handleCommand(command){
        let app=this;
        switch(command){
          case 'quit':
            app.isLogin=!app.isLogin;
            app.activeIndex=3;
            break;
        }
      }
    }
  }
</script>

<style scoped>
  .header{
    background: #f6f6f6;
  }
  .title-img{
    height: 55px;
  }
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
</style>
