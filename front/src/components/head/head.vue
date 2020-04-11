<template>
  <div class="head">
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">首页</el-menu-item>
      <el-menu-item index="2">团购</el-menu-item>
      <el-menu-item index="3">管理</el-menu-item>
    </el-menu>
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
        activeIndex: '1'
      }
    },
    created(){
      this.activeIndex = this.index
    },
    methods: {
      handleSelect(key) {
        switch(key){
          case  '1':
            this.$router.push("/")
            break;
          case  '3':
            this.$router.push("/login")
            break;

        }
      }
    }
  }
</script>

<style scoped>

</style>
