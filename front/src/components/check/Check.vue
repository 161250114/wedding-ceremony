<template>
  <div>
    <el-backtop :bottom="250" :right="20">
      <div style="{height: 100%;width: 100%;background-color: #f2f5f6;box-shadow: 0 0 6px rgba(0,0,0, .12);text-align: center;
            line-height: 40px;color: #1989fa;}">
        <i class="el-icon-upload2"></i>
      </div>
    </el-backtop>

    <div style="margin-top: 20px; height: 350px">
      <el-card class="simpleInfo">
        <div style="height: 100%; width: 30%; float: left">
          <figure style="height: 200px; margin-bottom: 20px">
            <img :src="headAddress"/>
          </figure>

          <div style="height: 100px;float:bottom;">
            <el-button type="primary" plain @click="handleSayHi()" class="sayHi" style="margin-left: 40px">打招呼
            </el-button>
            <el-button type="primary" plain @click="handleChat()" class="sayHi" style="margin-left: 10px">发消息
            </el-button>
          </div>
        </div>

        <div style="height: 100%; width: 70%; float: left">
          <div style="width: 80%">
            <el-carousel height="120px" type="card" autoplay="false">
              <el-carousel-item v-for="(item,index) in album" :key="index" style="width: 200px">
                <img style="width: 100%;height: 120px" :src="item"/>
              </el-carousel-item>
            </el-carousel>
          </div>

          <div>
            <p style="line-height: 25px">用户名：<span style="font-size: 30px">{{records.username}}</span></p>
            <p style="line-height: 25px">用户编号：<span>{{records.id}}</span></p>
            <p style="line-height: 25px">诚信等级：
              <el-rate style="display: inline-block" v-model="truth" disabled show-score text-color="#ff9900"
                       :colors="colors"></el-rate>
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
                  style="font-size:15px; height: 35px; margin-left: 25px;vertical-align: middle;text-align: center;color: black;border-radius: 15px"
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
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'Check',
    data () {
      return {
        headAddress: '', //头像
        album: [], //相册
        truth: 0,
        colors: ['#99A9BF', '#F7BA2A', '#FF9900'],  // 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
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
          salary: '10000-20000元',
          education: '本科',
          marriage: '未婚',
          address: ''
        },
        hobby: {
          food: '未填写',
          celebrity: '未填写',
          song: '未填写',
          book: '未填写',
          exercise: '未填写',
          game: '未填写'
        },
        chatHistory: {
          userId: 0,
          chattedUserId: 0,
          isagree: 0,
          userAisle: '',
          chattedUserAisle: ''
        }
      }
    },
    created () {
      this.id = this.$route.params.id
      this.getData()
      this.getAlbumPhotos()
      this.getDateStandard()
      this.getHobby()
      this.getUserLabel()
    },
    methods: {
      getData () {
        let url_getCurrentUser = '/getCurrentUser'
        axios.get(url_getCurrentUser).then((res) => {
          console.log(res.data.message.userid)
          this.userId = res.data.message.userid
          // this.userId=res.data
          // this.addCheckHistory()
        })

        let url = `/user/get/${this.id}`
        axios.get(url).then((res) => {
          this.records = res.data
          this.truth = this.records.trueness * 1.0 / 20
          let nowDate = new Date()
          let currentYear = nowDate.getFullYear()
          this.age = currentYear - parseInt(this.records.birthday.substr(0, 4))
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
        let url_album = '/userInfo/getStatusInfo'
        this.$axios.post(url_album, this.id).then((res) => {
          console.log(res.data.message.headPhotoUrl)
          this.headAddress = res.data.message.headPhotoUrl
          // console.log('album')
        })
        let url = `/album/select/${this.id}`
        axios.get(url).then((res) => {
          this.album = res.data
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
      handleChat () {
        this.chatHistory.userId = this.userId
        this.chatHistory.chattedUserId = this.id
        this.chatHistory.isagree = 0

        let url_add = '/chatHistory/add'
        this.$axios.post(url_add, this.chatHistory).then((res) => {
          console.log(res)
        })

      },
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
