<template>
  <div>
    <el-backtop :bottom="250" :right="20">
      <div style="{height: 100%;width: 100%;background-color: #f2f5f6;box-shadow: 0 0 6px rgba(0,0,0, .12);text-align: center;
            line-height: 40px;color: #1989fa;}">
        <i class="el-icon-upload2"></i>
      </div>
    </el-backtop>

    <div class="recommend">
      <el-carousel height="300px" class="el-carousel">
        <el-carousel-item v-for="(item,index) in carouselList" :key="index">
          <img style="width: 100%;height: 300px;min-width: 1250px;" v-bind:src="item.img_url"/>
        </el-carousel-item>
      </el-carousel>

      <el-tabs type="border-card" @tab-click="handleClick" v-model="activeTab">
        <el-tab-pane label="猜你喜欢" name="猜你喜欢">
          <span slot="label"><i class="el-icon-magic-stick"></i> 猜你喜欢</span>
          <figure v-for="(item,index) in preferList" :key="index" @click="handleCheck(item.id)">
            <img :src="preferListAddress[index]"/>
            <h2>{{item.username}}</h2>
          </figure>
        </el-tab-pane>

        <el-tab-pane v-for="(item,index) in hottestLabel" :key="index" :label=item.label :name=item.label>
          <figure v-for="(user,i) in userList" :key="i" @click="handleCheck(user.id)">
            <img :src="userListAddress[i]"/>
            <h2>{{user.username}}</h2>
          </figure>
        </el-tab-pane>
      </el-tabs>
    </div>

    <div style="margin-top: 40px">
      <el-card style="width: 1250px; margin: 0 auto; border: none" shadow="false">
        <el-button class="searchButton" plain type="text" @click="handleClicktoSearch">前往搜索页面
        </el-button>
      </el-card>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import head_img1 from './head1.jpg'
  import head_img2 from './title.png'
  import head_img3 from './timg.jpg'

  export default {
    name: 'Recommend',
    data () {
      return {
        userId: 0,
        activeIndex: '2',
        activeTab: '猜你喜欢',
        preferList: {},
        preferListAddress: [],
        hottestLabel: {},
        userList: {},
        userListAddress: [],
        carouselList: [{
          img_name: 'love',
          img_url: head_img1
        }, {
          img_name: 'title',
          img_url: head_img2
        }, {
          img_name: 'scene',
          img_url: head_img3
        }]
      }
    },
    created () {
      this.getData()
      this.getHottestLabel()
    },
    methods: {
      getData () {
        this.preferListAddress = []
        let url_getCurrentUser = '/getCurrentUser'
        axios.get(url_getCurrentUser).then((res) => {
          console.log(res.data.message)
          if (res.data.message === null) {//没登录的用户，猜你喜欢显示会员
            let url = '/user/memberList'
            axios.get(url).then((res) => {
              // console.log(res)
              this.preferList = res.data
              for(let item in this.preferList) {
                console.log(this.preferList[item])
                let url_album = '/userInfo/getStatusInfo'
                this.$axios.post(url_album, this.preferList[item].id).then((res) => {
                  console.log(res.data.message.headPhotoUrl)
                  let photoAddress = res.data.message.headPhotoUrl
                  // console.log('album')
                  this.preferListAddress.push(photoAddress)
                })
              }
              // console.log(url)
            })
          }
          else {
            this.userId = res.data.message.userid
            console.log(this.userId)
            // this.userId=res.data
            let url = `/user/preferList/${this.userId}`
            axios.get(url).then((res) => {
              console.log(res)
              this.preferList = res.data
              for(let item in this.preferList) {
                console.log(this.preferList[item])
                let url_album = '/userInfo/getStatusInfo'
                this.$axios.post(url_album, this.preferList[item].id).then((res) => {
                  console.log(res.data.message.headPhotoUrl)
                  let photoAddress = res.data.message.headPhotoUrl
                  // console.log('album')
                  this.preferListAddress.push(photoAddress)
                })
              }
              // console.log(url)
            })
          }
        })
      },
      getHottestLabel () {
        let url = '/labelHeat/heat_list'
        axios.get(url).then((res) => {
          console.log(res)
          this.hottestLabel = res.data
          console.log(url)
        })
      },
      handleClick (tab, event) {
        this.userListAddress = []
        let url_getCurrentUser = '/getCurrentUser'
        axios.get(url_getCurrentUser).then((res) => {
          // console.log(res.data)
          if (res.data.message === null) {//没登录的用户，无法查看
            this.$alert('请您登陆后查看', '信息提醒', {
              confirmButtonText: '确定',
              callback: action => {
                this.$router.go(0);
              }
            })
          }
          else {//已经登录状态下
            console.log(event.target.getAttribute('id'))
            if (event.target.getAttribute('id') === 'tab-猜你喜欢') {
              //猜你喜欢标签，在登录状态下，加载页面的时候已经有数据，直接沿用
              console.log("111")
              // console.log(this.preferListAddress)
            }
            else {
              let label = event.target.getAttribute('id')
              if(label !==null){
                label=label.substr(4)
              }
              let url = `/user/label_search/${label}&${this.userId}`
              axios.get(url).then((res) => {
                // console.log(res)
                this.userList = res.data
                for(let item in this.userList) {
                  console.log(this.userList[item])
                  let url_album = '/userInfo/getStatusInfo'
                  this.$axios.post(url_album, this.userList[item].id).then((res) => {
                    console.log(res.data.message.headPhotoUrl)
                    let photoAddress = res.data.message.headPhotoUrl
                    // console.log('album')
                    this.userListAddress.push(photoAddress)
                  })
                  // let url_album = `/album/select/${this.userList[item].albumid}`
                  // axios.get(url_album).then((res) => {
                  // })
                }
                // console.log(url)
              })
            }
          }
        })
      },
      handleClicktoSearch () {
        this.$router.push({
          path: '/search'
        })
      },
      handleCheck (id) {
        let url_getCurrentUser = '/getCurrentUser'
        axios.get(url_getCurrentUser).then((res) => {
          // console.log(res.data)
          if (res.data.message === null) {//没登录的用户，无法查看
            this.$alert('请您登陆后查看', '信息提醒', {
              confirmButtonText: '确定'
            })
          } else {
            this.$router.push({
              path: `/check/${id}`
            })
          }
        })
      },
    }
  }
</script>

<style scoped>
  .el-carousel {
    margin-bottom: 20px;
    margin-top: 20px;
  }

  .recommend {
    width: 1250px;
    text-align: center;
    margin: 0 auto;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

  figure {
    width: 212px;
    float: left;
    padding: 2px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }

  figure img {
    width: 212px;
    height: 200px;
  }

  figure h2 {
    text-align: center;
    font-size: 22px;
    color: #666;
    white-space: nowrap; /* 不换行*/
    overflow: hidden; /* 超出不显示*/
    text-overflow: ellipsis; /* 加省略号显示*/
  }

  .searchButton {
    background-color: mediumpurple;
    width: 300px;
    height: 45px;
    color: white;
    font-size: 16px;
    margin-left: 40%
  }
</style>
