<template>
  <div style="text-align:center">
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
                src="../../../static/photo1.jpg"
                style="margin-top: 10px;"
              ></el-avatar>
              <p>
                <b>{{ userinfo.username }}&nbsp;</b
                ><b id="vip_paid" v-if="userinfo.usertype == 1"
                  >&nbsp;VIP&nbsp;</b
                ><b id="vip_free" v-else>&nbsp;VIP&nbsp;</b>
                <el-tooltip
                  class="item"
                  effect="dark"
                  content="当前没有约会对象"
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
                <el-badge :value="3" class="item">
                  <el-menu-item index="/personalInfo/friendApply"
                    >好友申请</el-menu-item
                  >
                </el-badge>
                <el-badge value="新" class="item">
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
              <router-view @getIndex="getCurrentIndex"></router-view>
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
  },
  created() {
    let app = this;
    Axios.get("/userInfo/getStatusInfo")
      .then(function (res) {
        console.log(res)
        if (res.data.result) {
          app.userinfo = res.data.message;
        }
      })
      .catch(function (error) {});
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
