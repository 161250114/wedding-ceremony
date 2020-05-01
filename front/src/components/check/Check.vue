<template>
  <div>
    <el-backtop :bottom="250" :right="20">
      <div style="{height: 100%;width: 100%;background-color: #f2f5f6;box-shadow: 0 0 6px rgba(0,0,0, .12);text-align: center;
            line-height: 40px;color: #1989fa;}">
        <i class="el-icon-upload2"></i>
      </div>
    </el-backtop>

    <!--        <Head></Head>-->

    <el-dialog style="" title="聊天室" :close-on-click-modal="false" :lock-scroll="false" :visible.sync="chatRoomVisible"
               width="40%" :before-close="handleClose">
      <ChatRoom></ChatRoom>
    </el-dialog>

    <div style="margin-top: 20px">
      <el-card class="simpleInfo">
        <div style="height: 210px; width: 30%;display: inline">
          <figure>
            <img src="../recommend/girl2.jpg"/>
          </figure>
        </div>
        <div style="height: 210px">
          <h2 style="font-size: 30px">{{records.username}}</h2>
          <p>用户编号：<span>{{records.id}}</span></p>
          <p>会员身份：<span>{{userType}}</span></p>
          <p>诚信等级：<span>{{records.trueness}}</span></p>
        </div>
        <div style="margin-top: 20px">
          <el-button type="primary" plain @click="handleDisplay()" class="sayHi" style="margin-left: 40px">打招呼
          </el-button>
          <el-button type="primary" plain @click="handleChat()" class="sayHi" style="margin-left: 10px"
                     :disabled="userState">发消息
          </el-button>
        </div>
      </el-card>
    </div>

    <div class="show_detail" style="margin-top: 20px">
      <el-card style="margin-bottom: 20px" shadow="false">
        <li style="line-height: 10px; margin-bottom: 20px"><strong
          style="font-size: 20px; font-weight: bolder">内心独白</strong></li>
        <p style="font-size: 18px; line-height: 8px">{{records.introduction}}</p>
      </el-card>
      <el-card style="margin-bottom: 20px" shadow="false">
        <li style="line-height: 10px; margin-bottom: 20px"><strong
          style="font-size: 20px; font-weight: bolder">个人资料</strong>
        </li>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">学历：<span>{{records.education}}</span></p>
          <p style="width: 50%;float: right">身高：<span>{{records.height}}cm</span></p>
        </div>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">年龄：<span>{{records.age}}岁</span></p>
          <p style="width: 50%;float: right">月薪：<span>{{records.salary}}元</span></p>
        </div>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">职业：<span>{{records.profession}}</span></p>
          <p style="width: 50%;float: right">所在地：<span>{{records.address}}</span></p>
        </div>
      </el-card>
      <el-card style="margin-bottom: 20px" shadow="false">
        <li style="line-height: 10px; margin-bottom: 20px"><strong
          style="font-size: 20px; font-weight: bolder">择偶要求</strong>
        </li>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">年龄：<span>{{selectRequire.leastAge}}~{{selectRequire.oldestAge}}岁</span></p>
          <p style="width: 50%;float: right">
            身高：<span>{{selectRequire.shortestHeight}}~{{selectRequire.tallestHeight}}cm</span></p>
        </div>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">民族：<span>{{selectRequire.nation}}</span></p>
          <p style="width: 50%;float: right">学历：<span>{{selectRequire.education}}</span></p>
        </div>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">婚姻状况：<span>{{selectRequire.marriage}}</span></p>
          <p style="width: 50%;float: right">所在地：<span>{{records.address}}</span></p>
        </div>
      </el-card>
      <el-card style="margin-bottom: 20px" shadow="false">
        <li style="line-height: 10px; margin-bottom: 20px"><strong
          style="font-size: 20px; font-weight: bolder">兴趣爱好</strong>
        </li>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">喜欢的一道菜：<span>{{hobby.food}}</span></p>
          <p style="width: 50%;float: right">欣赏的一个名人：<span>{{hobby.celebrity}}</span></p>
        </div>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">喜欢的一首歌：<span>{{hobby.song}}</span></p>
          <p style="width: 50%;float: right">喜欢的一本书：<span>{{hobby.book}}</span></p>
        </div>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">喜欢做的事：<span>{{hobby.thing}}</span></p>
          <p style="width: 50%;float: right">喜欢的一部电影：<span>{{hobby.movie}}</span></p>
        </div>
      </el-card>
      <el-card style="margin-bottom: 20px" shadow="false">
        <li style="line-height: 10px; margin-bottom: 20px"><strong
          style="font-size: 20px; font-weight: bolder">生活方式</strong>
        </li>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">吸烟：<span>{{lifeStyle.smoke}}</span></p>
          <p style="width: 50%;float: right">饮酒：<span>{{lifeStyle.drink}}</span></p>
        </div>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">锻炼习惯：<span>{{lifeStyle.exercise}}</span></p>
          <p style="width: 50%;float: right">饮食习惯：<span>{{lifeStyle.eating}}</span></p>
        </div>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">作息时间：<span>{{lifeStyle.time}}</span></p>
          <p style="width: 50%;float: right">宗教信仰：<span>{{lifeStyle.religion}}</span></p>
        </div>
      </el-card>
    </div>

    <div style="margin-top: 100px">
      <el-card shadow="false" style="width: 1600px;margin: 0 auto; border: none">
        <p style="text-align: center">关于我们|联系我们|加入我们|合作伙伴| 意见反馈|安全中心|网站地图 | 帮助中心|精英会员|个人信息保护政策</p>
        <p style="text-align: center">品牌：10年专业婚恋服务 专业：庞大的资深红娘队伍</p>
        <p style="text-align: center">客服热线：4001-520-520（周一至周日：9:00-21:00）客服信箱：1234567890@jingying.com</p>
        <p style="text-align: center">违法和不良信息举报 4001-520-520 举报信箱：1234567891@jingying.com</p>
        <p style="text-align: center">Copyright © 2010-2020 版权所有：西虹市精英婚庆网信息技术有限公司</p>
      </el-card>
    </div>
  </div>
</template>

<script>
  import Head from '../head/Head'
  import axios from 'axios'
  import ChatRoom from '../chatRoom/ChatRoom'

  export default {
    name: 'Check',
    data () {
      return {
        userState: true,
        chatRoomVisible: false,
        id: '',
        user: 666, //当前用户
        activeNames: ['1'],
        userType: '普通会员',
        records: {},
        /*form->record how much times that these tags are checked,
        and use the time to represent heat
         */
        form: {
          id: 0,
          user_id: 0,
          label1: '',
          label2: '',
          label3: '',
        },
        checkHistoryform: {
          userId: 111,
          checkedUserId: 921,
        },
        selectRequire: {
          leastAge: 25,
          oldestAge: 30,
          shortestHeight: 175,
          tallestHeight: 185,
          nation: '汉族',
          education: '本科',
          marriage: '未婚',
          address: '北京朝阳'
        },
        hobby: {
          food: '未填写',
          celebrity: '未填写',
          song: '未填写',
          book: '未填写',
          thing: '未填写',
          movie: '未填写'
        },
        lifeStyle: {
          smoke: '不吸烟',
          drink: '不喝酒',
          exercise: '偶尔锻炼',
          eating: '都可以',
          time: '不规律',
          religion: '无宗教信仰'
        }
      }
    },
    components: {
      ChatRoom,
      Head
    },
    created () {
      this.id = this.$route.params.id
      this.getData()
      this.getUserLabel()
      this.addCheckHistory()
    },
    methods: {
      getData () {
        let url = `http://localhost:8999/wedding/user/get/${this.id}`
        axios.get(url).then((res) => {
          // console.log(res)
          this.records = res.data
          // console.log(url)
          if (this.records.usertype === 0) {
            this.userType = '普通会员'
          } else if (this.records.usertype === 1) {
            this.userType = '高级会员'
          }
        })
      },
      getUserLabel () {
        let url = `http://localhost:8999/wedding/userLabel/select/${this.id}`
        axios.get(url).then((res) => {
          // console.log(res),
          this.form = res.data
          // console.log(this.form.label1 + " " + this.form.label2 + " " + this.form.label3)
          this.addLabelHeat()
        })
      },
      addLabelHeat () {
        let url = 'http://localhost:8999/wedding/labelHeat/addHeat'
        this.$axios.post(url, this.form)
          .then((res) => {
            console.log(res.data)
          })
      },
      addCheckHistory () {
        let url = 'http://localhost:8999/wedding/checkHistory/add'
        this.checkHistoryform.checkedUserId = this.id
        this.$axios.post(url, this.checkHistoryform)
          .then((res) => {
            console.log(res.data)
          })
      },
      handleDisplay () {
        this.$message('成功打招呼')
      },
      handleChange (val) {
        console.log(val)
      },
      // eslint-disable-next-line no-unused-vars
      handleClose (done) {
        this.chatRoomVisible = false
      },
      // handleCloseRoom(){
      //     this.chatRoomVisible = false;
      // },
      handleChat () {
        //设置菜单权限
        this.chatRoomVisible = true
      },
    }
  }
</script>

<style scoped>

  .simpleInfo {
    width: 1250px;
    margin: 0 auto;
  }

  .show_detail {
    width: 1250px;
    margin: 0 auto;
  }

  .sayHi {
    width: 100px;
  }

  figure {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 210px;
    height: 210px;
    float: left;
    border: 1px solid #ccc;
  }

  figure img {
    max-width: 100%;
    max-height: 100%
  }

  p {
    font-size: 18px;
  }

</style>
