<template>
<div class="hsp">
  <div  v-for="(h,index) in list" class="hp":style="'height:' + (500+(h.pnumber/3) * 200) + 'px'">
    <div class="wa">
      <p align="left">fjsdfdsfhsdfhd</p>
    </div>
    <div class="pa">
      <!--el-row>
        <el-col :span="8" v-for="(o, index) in 4" :key="o" :offset=0>
          <el-card :body-style="{ padding: '0px' }">
            <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
          </el-card>
        </el-col>
      </--el-row-->
    </div>
    <div class="ca">
      <div><span>{{commentlist[index]["comment"]}}</span></div>
    </div>
    <div class="ia">
      <el-input v-model="input" placeholder="请输入内容" class="send_comment"></el-input>
      <el-button type="primary" class="talk_sub" @click="send()">发送</el-button>
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
      name: "happiness",
      data(){
          return{
            list:[
              {
                pnumber:2
              }, {
              pnumber:5
              },
            ],
            photolist:[{}],
            commentlist:[{comment:"123"},{comment:"456"}],
          }
      }
    }
</script>

<style scoped>
  .send_comment{
    width:620px;
    height:50px;
    padding:0px;
    float:left;
    margin-left:10px;
    outline:none;
    text-indent:10px;
  }
  .ca{
    width: 100%;
    height: 100px;
    background: lightskyblue;
  }
  .hsp{
    width: 100%;
    height: 500px;
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
    border-bottom:20px;
    border-right:20px;
  }
</style>
