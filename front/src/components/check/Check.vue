<template>
  <div>
    <el-backtop :bottom="250" :right="20">
      <div style="{height: 100%;width: 100%;background-color: #f2f5f6;box-shadow: 0 0 6px rgba(0,0,0, .12);text-align: center;
            line-height: 40px;color: #1989fa;}">
        <i class="el-icon-upload2"></i>
      </div>
    </el-backtop>

    <!--    <el-dialog style="" title="聊天室" :close-on-click-modal="false" :lock-scroll="false" :visible.sync="chatRoomVisible"-->
    <!--               width="40%" :before-close="handleClose">-->
    <!--      <div style="text-align: center">-->
    <!--        &lt;!&ndash;        <br>欢迎使用<strong>VueTest</strong>极简聊天室：<br/><br/>&ndash;&gt;-->
    <!--        <textarea id="content" v-model="content" style="width: 100%; height: 300px" readonly="readonly"></textarea>-->
    <!--        <div style="width: 100%; margin: 0 auto">-->
    <!--          <el-input type="textarea" style="width: 100%; height: 50px; border: none" placeholder="请输入内容" v-model="message"></el-input>-->
    <!--&lt;!&ndash;          <input style="width: 600px; height: 50px; border: none" type="text" v-model="message">&ndash;&gt;-->
    <!--          <button type="button" @click="sendMsg()" style="float: right;margin-top: 5px">发送消息</button>-->
    <!--          &lt;!&ndash;        <button @click="joinRoom()">加入群聊</button>&ndash;&gt;-->
    <!--          &lt;!&ndash;        <button style="vertical-align: middle" @click="exitRoom()">结束聊天</button>&ndash;&gt;-->
    <!--        </div>-->
    <!--      </div>-->
    <!--    </el-dialog>-->

    <div style="margin-top: 20px; height: 350px">
      <el-card class="simpleInfo">
        <div style="height: 100%; width: 30%; float: left">
          <figure style="height: 70%; margin-bottom: 20px">
            <img src="../recommend/girl2.jpg"/>
            <!--            <img v-bind:src="album.get(0).address">-->
          </figure>

          <div style="height: 25%;float:bottom;">
            <el-button type="primary" plain @click="handleSayHi()" class="sayHi" style="margin-left: 40px">打招呼
            </el-button>
            <el-button type="primary" plain @click="handleChat()" class="sayHi" style="margin-left: 10px"
                       :disabled="userState">发消息
            </el-button>
          </div>
        </div>

        <div style="height: 100%; width: 70%; float: left">
          <div style="width: 80%">
            <el-carousel height="120px" type="card" autoplay="false">
              <el-carousel-item v-for="(item,index) in 4" :key="index" style="width: 200px">
                <!--                <el-carousel-item v-for="(item,index) in album" :key="index" style="width: 200px">-->
                <img src="../recommend/girl.png"/>
                <!--                <img style="width: 100%;height: 120px" v-bind:src="item.address"/>-->
                <!--                </el-carousel-item>-->
              </el-carousel-item>
            </el-carousel>
          </div>

          <div>
            <p style="line-height: 25px">用户名：<span style="font-size: 30px">{{records.username}}</span></p>
            <p style="line-height: 25px">用户编号：<span>{{records.id}}</span></p>
            <p style="line-height: 25px">诚信等级：
              <el-rate style="display: inline-block" v-model="truth" disabled show-score text-color="#ff9900" :colors="colors"></el-rate>
            </p>
          </div>
        </div>
      </el-card>
    </div>

    <div class="show_detail" style="margin-top: 20px">
      <el-card style="margin-bottom: 20px" shadow="false">
        <li style="line-height: 10px; margin-bottom: 20px"><strong
          style="font-size: 20px; font-weight: bolder">个人标签</strong></li>
        <div>
          <el-tag v-for="(item,index) in this.form" :key="index"
                  style="font-size:15px; height: 35px; width: 80px;margin-left: 30px;vertical-align: middle;text-align: center;color: black;border-radius: 20px"
                  effect="plain">
            {{item.label}}
          </el-tag>
        </div>
      </el-card>
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
          <p style="width: 50%;float: left">年龄：<span>{{age}}岁</span></p>
          <p style="width: 50%;float: right">月薪：<span>{{records.salary}}</span></p>
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
          <p style="width: 50%;float: right">学历：<span>{{selectRequire.education}}</span></p>
          <p style="width: 50%;float: left">月收入：<span>{{selectRequire.salary}}</span></p>
        </div>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">婚姻状况：<span>{{selectRequire.marriage}}</span></p>
          <p style="width: 50%;float: right">所在地：<span>{{selectRequire.address}}</span></p>
        </div>
      </el-card>
      <el-card style="margin-bottom: 20px" shadow="false">
        <li style="line-height: 10px; margin-bottom: 20px"><strong
          style="font-size: 20px; font-weight: bolder">兴趣爱好</strong>
        </li>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">最喜欢的游戏是：<span>{{hobby.game}}</span></p>
          <p style="width: 50%;float: right">最喜欢的运动是：<span>{{hobby.exercise}}</span></p>
        </div>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">最喜欢的明星是：<span>{{hobby.celebrity}}</span></p>
          <p style="width: 50%;float: right">最喜欢的食物是：<span>{{hobby.food}}</span></p>
        </div>
        <div style="line-height: 4px">
          <p style="width: 50%;float: left">最喜欢的歌曲是：<span>{{hobby.song}}</span></p>
          <p style="width: 50%;float: right">最喜欢的书是：<span>{{hobby.book}}</span></p>
        </div>
      </el-card>
    </div>

    <div style="margin-top: 100px; width: 100%">
      <el-card shadow="false" style="width: 1600px;margin: 0 auto; border: none">
        <p style="text-align: center">关于我们|联系我们|加入我们|合作伙伴|意见反馈|安全中心|网站地图|帮助中心|精英会员|个人信息保护政策</p>
        <p style="text-align: center">品牌：10年专业婚恋服务 专业：庞大的资深红娘队伍</p>
        <p style="text-align: center">客服热线：4001-520-520（周一至周日：9:00-21:00）客服信箱：1234567890@jingying.com</p>
        <p style="text-align: center">违法和不良信息举报 4001-520-520 举报信箱：1234567891@jingying.com</p>
        <p style="text-align: center">Copyright © 2010-2020 版权所有：西虹市精英婚庆网信息技术有限公司</p>
      </el-card>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'Check',
    data () {
      return {
        // url: 'ws://' + window.location.host + '/chatRoom/',
        // ws: null,
        // message: '',
        // content: '',
        album: {},
        truth: 0,
        colors: ['#99A9BF', '#F7BA2A', '#FF9900'],  // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
        userState: false,
        chatRoomVisible: false,
        id: 0,//查看对象的id
        userId: 0, //当前用户
        records: {},
        age: 0,
        /*form->record how much times that these tags are checked,
        and use the time to represent heat
         */
        form: {},
        checkHistoryform: {
          userId: 0,
          checkedUserId: 0,
        },
        selectRequire: {
          leastAge: 25,
          oldestAge: 30,
          shortestHeight: 175,
          tallestHeight: 185,
          salary: '10000 ~ 20000元',
          education: '本科',
          marriage: '未婚',
          address: '北京朝阳'
        },
        hobby: {
          food: '未填写',
          celebrity: '未填写',
          song: '未填写',
          book: '未填写',
          exercise: '未填写',
          game: '未填写'
        },
        // lifeStyle: {
        //   smoke: '不吸烟',
        //   drink: '不喝酒',
        //   exercise: '偶尔锻炼',
        //   eating: '都可以',
        //   time: '不规律',
        //   religion: '无宗教信仰'
        // }
      }
    },
    created () {
      this.id = this.$route.params.id
      this.getData()
      this.getDateStandard()
      this.getHobby()
      this.getUserLabel()
    },
    methods: {
      getData () {
        let url_getCurrentUser = '/getCurrentUser'
        axios.get(url_getCurrentUser).then((res) => {
          // console.log(res.data.message)
          this.userId=res.data.message.userid
          // this.userId=res.data
          this.addCheckHistory()
        })
        let url = `/user/get/${this.id}`
        axios.get(url).then((res) => {
          this.records = res.data
          this.truth = this.records.trueness * 1.0 / 20
          let nowDate = new Date()
          let currentYear = nowDate.getFullYear()
          this.age = currentYear - parseInt(this.records.birthday.substr(0, 4))
          this.getAlbumPhotos()
        })
      },
      getDateStandard () {
        let url_1 = `/date_standard/select/${this.id}`
        axios.get(url_1).then((res) => {
          // console.log(res)
          this.selectRequire.leastAge = res.data.agemin
          this.selectRequire.oldestAge = res.data.agemax
          this.selectRequire.shortestHeight = res.data.heightmin
          this.selectRequire.tallestHeight = res.data.heightmax
          this.selectRequire.salary = res.data.salary
          this.selectRequire.education = res.data.education
          this.selectRequire.address = res.data.address
          if (res.data.marrige === 0) {
            this.selectRequire.marriage = '未婚'
          } else if (res.data.marrige === 3) {
            this.selectRequire.marriage = '不限'
          } else if (res.data.marrige === 1) {
            this.selectRequire.marriage = '离异'
          } else if (res.data.marrige === 2) {
            this.selectRequire.marriage = '丧偶'
          }
        })
      },
      getAlbumPhotos () {
        let url_album = `/album/select/${this.records.albumid}`
        axios.get(url_album).then((res) => {
          this.album = res.data
          console.log('album')
          console.log(this.album)
        })
      },
      getHobby () {
        let url_hobby = `/userQuestion/select/${this.id}`
        axios.get(url_hobby).then((res) => {
          for (let index = 0; index < res.data.length; index++) {
            if (res.data[index].questionid === 0) {//game
              this.hobby.game = res.data[index].answer
            } else if (res.data[index].questionid === 1) {//exercise
              this.hobby.exercise = res.data[index].answer
            } else if (res.data[index].questionid === 2) {//celebrity
              this.hobby.celebrity = res.data[index].answer
            } else if (res.data[index].questionid === 3) {//food
              this.hobby.food = res.data[index].answer
            } else if (res.data[index].questionid === 4) {//song
              this.hobby.song = res.data[index].answer
            } else if (res.data[index].questionid === 5) {//book
              this.hobby.book = res.data[index].answer
            }
          }
        })
      },
      getUserLabel () {
        let url = `/userLabel/select/${this.id}`
        axios.get(url).then((res) => {
          this.form = res.data
          console.log(this.form)
          this.addLabelHeat()
        })
      },
      addLabelHeat () {
        let url = '/labelHeat/addHeat'
        for (let index = 0; index < this.form.length; index++) {
          console.log(this.form[index])
          this.$axios.post(url, this.form[index]).then((res) => {
            console.log(res.data)
          })
        }
      },
      addCheckHistory () {
        let url = '/checkHistory/add'
        this.checkHistoryform.checkedUserId = this.id
        this.checkHistoryform.userId = this.userId
        this.$axios.post(url, this.checkHistoryform)
          .then((res) => {
            console.log(res.data)
          })
      },
      handleSayHi () {
        this.$message({
          message: '成功打招呼',
          type: 'success',
          center: true
        })
      },
      handleChange (val) {
        console.log(val)
      },
      // eslint-disable-next-line no-unused-vars
      // handleClose (done) {
      //   this.chatRoomVisible = false
      //   this.exitRoom()
      // },
      handleChat () {
        let url = `/user/get/${this.userId}`
        let username
        axios.get(url).then((res) => {
          username = res.data.username
          let routeData = this.$router.resolve({
            path: '/chatRoom',
            query: {
              name: username,
            }
          })
          window.open(routeData.href, '_blank')
        })
        //open the chatRoom and join the room
        // this.chatRoomVisible = true
        // this.joinRoom()
      },
      //methods for chatRoom
      // async joinRoom () {
      //   // let username
      //   // let url = `/user/get/${this.userId}`
      //   // axios.get(url).then((res) => {
      //   //   username = res.data.username
      //   // })
      //   // console.log(this.records.username)
      //   this.ws = new WebSocket(`ws://localhost:8080/chatRoom/${this.records.username}`)  // 后端的启动端口
      //   this.ws.onopen = this.webscoketonopen
      //   this.ws.onmessage = this.webscoketonmessage
      //   //正常关闭触发
      //   this.ws.onclose = function () {
      //     console.log('连接关闭')
      //   }
      // },
      // webscoketonopen () {
      //   console.log('与服务器成功建立连接')
      // },
      // webscoketonmessage (value) {
      //   // console.log(value)
      //   this.content += (value.data + '\r\n')
      // },
      // exitRoom () {
      //   this.closeWebSocket()
      // },
      // sendMsg () {
      //   if (!this.ws) {
      //     alert('你已经掉线，请重新加入')
      //     return
      //   }
      //   if (this.ws.readyState === 1) {
      //     this.ws.send(this.message)
      //     this.message = ''
      //   } else {
      //     alert('发送失败')
      //   }
      // },
      // closeWebSocket () {
      //   if (this.ws) {
      //     this.ws.close()
      //     this.ws = null
      //   }
      // }
    }
  }
</script>

<style scoped>

  .simpleInfo {
    width: 1200px;
    height: 350px;
    margin: 0 auto;
  }

  .show_detail {
    width: 1200px;
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
    height: 280px;
    float: left;
    border: none;
  }

  figure img {
    max-width: 100%;
    max-height: 100%
  }

  p {
    font-size: 18px;
  }

</style>
