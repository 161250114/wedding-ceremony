<template>
<div class="hsp">
  <div class="hp">
    <div class="wa">
      <p align="left">fjsdfdsfhsdfhd</p>
    </div>
    <div class="pa">
      <el-row>
        <el-col :span="8" v-for="(o, index) in 4" :key="o" :offset=0>
          <el-card :body-style="{ padding: '0px' }">
            <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div class="la">
      <p class="like">&#10084;</p>
    </div>
  </div>
  <div class="hp">
    <div class="wa">
      <p align="left">fjsdfdsfhsdfhd</p>
    </div>
    <div class="pa">
    </div>
    <div class="la">
    </div>
  </div>
</div>
</template>

<script>
    export default {
        name: "happiness"
    }
</script>

<style scoped>
  .hsp{
    width: 100%;
    height: 500px;
    margin:100px;
    background:gainsboro;
    text-align: center;
    overflow:auto;
  }
  .hp{
    width: 800px;
    height: 500px;
    margin:20px 400px;
    text-align: center;
    background: white;
  }
  .wa{
    width:600px;
    height: 80px;
  }
  .like{
    font-size:33px;
    color:#ccc;
    cursor:pointer;
    margin:20px 700px;
  }
</style>
