<template>
  <div>
    <el-row>
      <el-col :span="1">&nbsp;</el-col>
    </el-row>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="18">
        <el-container style="height: 700px; border: 1px solid #eee">
          <el-aside width="250px" style="background-color: rgb(238, 241, 246)">
            <div>
              <el-avatar
                :size="100"
                src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
                style="margin-top:10px"
              ></el-avatar>
              <p>
                <b>{{ userinfo.username }}&nbsp;</b
                ><b id="vip_paid" v-if="userinfo.usertype == 1"
                  >&nbsp;VIP&nbsp;</b
                ><b id="vip_free" v-else>&nbsp;VIP&nbsp;</b>
              </p>
              <p>
                <b>账户余额：{{ userinfo.balance }}&nbsp;¥</b>
              </p>
            </div>
            <el-menu router>
              <el-submenu index="1">
                <template slot="title"
                  ><i class="el-icon-message"></i>个人资料</template
                >
                <el-menu-item index="/personalInfo/baseInfo"
                  >基本资料管理</el-menu-item
                >
                <el-menu-item index="/personalInfo/detailInfo"
                  >详细资料管理</el-menu-item
                >
                <el-menu-item index="/personalInfo/introduction"
                  >个人介绍管理</el-menu-item
                >
              </el-submenu>
              <el-submenu index="2">
                <template slot="title"
                  ><i class="el-icon-picture-outline"></i>相册管理</template
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
              </el-submenu>
              <el-submenu index="4">
                <template slot="title"
                  ><i class="el-icon-menu"></i>记录查询</template
                >
                <el-menu-item index="/personalInfo/consumption">消费记录查询</el-menu-item>
                <el-menu-item index="/personalInfo/dateRecord">约会记录查询</el-menu-item>
              </el-submenu>
              <el-submenu index="5">
                <template slot="title"
                  ><i class="el-icon-star-off"></i>服务购买</template
                >
                <el-menu-item index="/personalInfo/beVIP">成为付费会员</el-menu-item>
                <el-menu-item index="/personalInfo/addAlbumMax">相册容量购买</el-menu-item>
              </el-submenu>
              <el-submenu index="6">
                <template slot="title"
                  ><i class="el-icon-setting"></i>系统设置</template
                >
                <el-menu-item index="/personalInfo/limits">权限设置</el-menu-item>
                <el-menu-item index="/personalInfo/changePassword">修改密码</el-menu-item>
              </el-submenu>
            </el-menu>
          </el-aside>

          <el-container>
            <el-header style="text-align: right; font-size: 12px">
              <el-dropdown>
                <i class="el-icon-setting" style="margin-right: 15px"></i>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>查看</el-dropdown-item>
                  <el-dropdown-item>新增</el-dropdown-item>
                  <el-dropdown-item>删除</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
              <span>王小虎</span>
            </el-header>

            <el-main>
              <router-view></router-view>
            </el-main>
          </el-container>
        </el-container>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    const item = {
      date: "2016-05-02",
      name: "王小虎",
      address: "上海市普陀区金沙江路 1518 弄"
    };
    return {
      tableData: Array(20).fill(item),
      currentIndex: "1-1",
      userinfo: {
        username: "pikaqiu",
        usertype: 0,
        balance: 50
      }
    };
  },
  methods: {
    changeIndex() {
      let app = this;
      console.log(app.currentIndex);
    }
  }
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
