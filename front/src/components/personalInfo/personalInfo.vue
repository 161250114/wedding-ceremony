<template>
  <div style="text-align: center;">
    <el-row>
      <el-col :span="1">&nbsp;</el-col>
    </el-row>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="18">
        <el-container style="height: 750px; border: 1px solid #eee;">
          <el-aside width="250px" style="background-color: rgb(238, 241, 246);">
            <div>
              <el-avatar
                :size="100"
                :src="userinfo.headPhotoUrl"
                style="margin-top: 10px;"
                :key="userinfo.headPhotoUrl"
              ></el-avatar>
              <p>
                <b>{{ userinfo.username }}&nbsp;</b>
                <el-tooltip
                  :content="
                    userinfo.usertype === 0
                      ? '当前不是VIP'
                      : '会员有效期至：' + userinfo.vipEnddate
                  "
                  placement="bottom-start"
                  class="item"
                  effect="dark"
                  ><b id="vip_paid" v-if="userinfo.usertype == 1"
                    >&nbsp;VIP&nbsp;</b
                  >
                  <b id="vip_free" v-else>&nbsp;VIP&nbsp;</b>
                </el-tooltip>
                <el-tooltip
                  class="item"
                  effect="dark"
                  :content="
                    userinfo.dateStatus === 0
                      ? '当前没有约会对象'
                      : '当前约会对象ID：' + userinfo.dateFriendId
                  "
                  placement="bottom-start"
                >
                  <img
                    :src="
                      userinfo.dateStatus === 0
                        ? '../../../static/single.png'
                        : '../../../static/couple.png'
                    "
                    style="height: 22px; vertical-align: text-top;"
                  />
                </el-tooltip>
              </p>
              <p>
                <b>账户余额：{{ userinfo.balance }}&nbsp;¥</b>
              </p>
            </div>
            <el-menu router unique-opened :default-active="currentIndex">
              <el-submenu index="1">
                <template slot="title"
                  ><i class="el-icon-message"></i>个人资料</template
                >
                <el-menu-item index="/personalInfo/baseInfo"
                  >基本资料管理</el-menu-item
                >
                <el-menu-item index="/personalInfo/dateStandard"
                  >择偶标准管理</el-menu-item
                >
                <el-menu-item index="/personalInfo/introduction"
                  >个人介绍管理</el-menu-item
                >
              </el-submenu>
              <el-submenu index="2">
                <template slot="title"
                  ><i class="el-icon-picture-outline"></i>相册管理</template
                >
                <el-menu-item index="/personalInfo/uploadPhoto"
                  >上传照片</el-menu-item
                >
                <el-menu-item index="/personalInfo/album"
                  >相册管理</el-menu-item
                >
              </el-submenu>
              <el-submenu index="3">
                <template slot="title"
                  ><i class="el-icon-user"></i>好友管理</template
                >
                <el-menu-item index="/personalInfo/friends"
                  >好友管理</el-menu-item
                >
                <el-badge
                  :value="
                    userinfo.newFapplyNum === 0 ? '' : userinfo.newFapplyNum
                  "
                  class="item"
                >
                  <el-menu-item index="/personalInfo/friendApply"
                    >好友申请</el-menu-item
                  >
                </el-badge>
                <el-badge
                  :value="
                    userinfo.newDapplyNum === 0 ? '' : userinfo.newDapplyNum
                  "
                  class="item"
                >
                  <el-menu-item index="/personalInfo/dateApply"
                    >约会申请</el-menu-item
                  >
                </el-badge>
              </el-submenu>
              <el-submenu index="4">
                <template slot="title"
                  ><i class="el-icon-menu"></i>记录查询</template
                >
                <el-menu-item index="/personalInfo/billList"
                  >消费记录查询</el-menu-item
                >
                <el-menu-item index="/personalInfo/dateRecord"
                  >约会记录查询</el-menu-item
                >
              </el-submenu>
              <el-submenu index="5">
                <template slot="title"
                  ><i class="el-icon-star-off"></i>服务购买</template
                >
                <el-menu-item index="/personalInfo/charge">充值</el-menu-item>
                <el-menu-item index="/personalInfo/beVIP"
                  >付费会员</el-menu-item
                >
                <el-menu-item index="/personalInfo/addAlbumMax"
                  >相册容量购买</el-menu-item
                >
              </el-submenu>
              <el-submenu index="6">
                <template slot="title"
                  ><i class="el-icon-setting"></i>系统设置</template
                >
                <el-menu-item index="/personalInfo/limits"
                  >权限设置</el-menu-item
                >
                <el-menu-item index="/personalInfo/changePassword"
                  >修改密码</el-menu-item
                >
              </el-submenu>
            </el-menu>
          </el-aside>

          <el-container>
            <el-header style="text-align: right; font-size: 12px;">
              <span
                ><b>{{ userinfo.username }}</b></span
              >
            </el-header>

            <el-main>
              <router-view
                @getIndex="getCurrentIndex"
                @updateInfo="updateInfo"
              ></router-view>
            </el-main>
          </el-container>
        </el-container>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  data() {
    return {
      currentIndex: "/personalInfo/baseInfo",
      userinfo: {
        username: "hahaha",
        usertype: 0,
        balance: 50,
        dateStatus: 0,
        headPhotoUrl: "",
        newFapplyNum: 0,
        newDapplyNum: 0,
      },
    };
  },
  methods: {
    changeIndex() {
      let app = this;
      console.log(app.currentIndex);
      console.log(app.dateStatus === 1);
    },
    getCurrentIndex(newIndex) {
      let app = this;
      app.currentIndex = newIndex;
    },
    updateInfo() {
      let app = this;
      Axios.get("/getCurrentUser").then(function (res) {
        console.log(res.data.message)
        Axios.post("/userInfo/getStatusInfo",res.data.message.userid)
          .then(function (res) {
            if (res.data.result) {
              app.userinfo = JSON.parse(JSON.stringify(res.data.message));
              app.userinfo.headPhotoUrl = app.userinfo.headPhotoUrl;
            }
          })
          .catch(function (error) {});
      });
    },
  },
  created() {
    this.updateInfo();
  },
};
</script>

<style scoped>
.el-header {
  background-color: #f6f6f6;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

#vip_paid {
  color: white;
  background-color: tomato;
}

#vip_free {
  color: white;
  background-color: rgb(83, 74, 72);
}
</style>
