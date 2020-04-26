<template>
<div class="hsp">
  <div  v-for="(h,index) in list" class="hp">
    <div>
      <p style="margin-left:-700px;margin-top: 50px"><el-avatar :size="30" src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" ></el-avatar>
      卢本伟</p>
    </div>
    <div class="wa">
      <div style="width: 760px;
      text-align: left;
      overflow: hidden;
      text-overflow: ellipsis;">虽然阿根廷累计确诊数在南美洲并不靠前，但在全球经济衰退的背景下，国际资本大幅流出，阿根廷金融市场及比索汇率雪上加霜，外汇储备持续缩水，多种风险相互叠加最终将阿根廷再次推向破产边缘。</div>

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
    <div class="ica">
      <span style="position: relative;;left:-60px;">
          <el-button v-if="!islike[index]" icon="el-icon-thumb" circle @click="like(index)"></el-button>
          <el-button v-if="islike[index]" type="danger" icon="el-icon-thumb" circle @click="like(index)"></el-button>
      </span>
      <span style="position: relative;left:-30px;">
          <el-button icon="el-icon-delete"circle @click="del(index)"></el-button>
      </span>
    </div>
    <div class="ca">
      <div  v-for="comm in commentlist[index]"><p style="margin-left:-700px;margin-top: 50px"><el-avatar :size="30" src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" ></el-avatar>
        {{comm["comment"]}}</p></div>
    </div>
    <div class="ia">
      <el-input v-model="input[index]" placeholder="评论" class="send_comment"></el-input>
      <el-button type="primary" class="talk_sub" @click="send(index)">发送</el-button>
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
            islike:[true,false],
            input:[],
            photolist:[{}],
            commentlist:[[{comment:"123"},{comment:"456"}],[]],
          }
      },
      computed:{

      },
      methods:{
        send(index){
          let word=this.input[index];
          let commentObject=new Object();
          commentObject["comment"]=word;
          this.commentlist[index].push(commentObject)
          console.log(this.commentlist)
        },
        like(index){
          this.islike.splice(index,1,!this.islike[index])
        },
        del(index){
        }
      }
    }
</script>

<style scoped>
  .ia{
    margin-top:40px;
    padding-bottom: 20px;
  }
  .ica{
    height: 40px;
    text-align: right;
  }
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
    margin:20px 400px;
    text-align: center;
    background: white;
  }
  .wa{
    width:800px;
    height: 150px;
    display: flex;
    justify-content: center;
    align-items: center;

  }
</style>
