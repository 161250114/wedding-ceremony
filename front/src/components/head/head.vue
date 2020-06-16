<template>
  <div>
    <el-header class="header">
      <el-row>
        <el-col :span="2">
          &nbsp;
        </el-col>
        <router-link to="/recommend">
          <el-col :span="2">
            <el-avatar
              shape="square"
              :size="55"
              fit="fill"
              src="../../../static/timg.jpg"
            ></el-avatar>
          </el-col>
          <el-col :span="3">
            <img src="../../../static/title.png" class="title-img" />
          </el-col>
        </router-link>
        <el-col :xs="7" :sm="10" :md="13" :lg="12" :xl="12">
          &nbsp;
        </el-col>
        <el-col :span="4" v-show="!isLogin">
          <router-link to="/login">
            <el-button type="text" style="margin-top: 10px;"
              >登录</el-button
            > </router-link
          >&nbsp;&nbsp;&nbsp;
          <router-link to="/register">
            <el-button type="text">注册</el-button>
          </router-link>
        </el-col>
        <el-col :span="5" v-show="isLogin">
          <p style="margin-top: 15px;">
            欢迎您，{{ userinfo.uname_phone }}&nbsp;
            <el-dropdown>
              <el-button
                type="info"
                icon="el-icon-user-solid"
                circle
                size="medium"
              ></el-button>
              <el-dropdown-menu slot="dropdown">
                <router-link to="/personalInfo" v-if="userinfo.usertype != 2">
                  <el-dropdown-item>个人中心</el-dropdown-item>
                </router-link>
                <router-link to="/ApplyWedding" v-if="userinfo.usertype != 2">
                  <el-dropdown-item>申请婚喜宴</el-dropdown-item>
                </router-link>
                <router-link to="/Wedding">
                  <el-dropdown-item>查看婚喜宴</el-dropdown-item>
                </router-link>
                <router-link to="/Happiness" v-if="userinfo.usertype != 2">
                  <el-dropdown-item>查看好友动态</el-dropdown-item>
                </router-link>
                <router-link to="/weddingrecord" v-if="userinfo.usertype == 2">
                  <el-dropdown-item>查看申请结果</el-dropdown-item>
                </router-link>
                <router-link to="/consult" v-if="userinfo.usertype != 2">
                  <el-dropdown-item>在线咨询</el-dropdown-item>
                </router-link>
                <router-link to="/consultlist" v-if="userinfo.usertype == 2">
                  <el-dropdown-item>在线咨询</el-dropdown-item>
                </router-link>
                <el-dropdown-item divided>
                  <label @click="quit()">退出登录</label>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>

            <el-badge :value="chatNotice.length" :max="99">
              <!--              <el-button size="small">{{cNoticeNum}}</el-button>-->
              <el-dropdown>
                <el-button type="info" icon="el-icon-chat-round" circle size="medium"></el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item v-for="(item, index) in chatNotice" :key="index" @click.native="agreeNotice(item)">
                    {{ item.userId }}希望进一步了解您
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-badge>

            <el-badge :value="requestNotice.length" :max="99">
              <el-dropdown>
                <el-button type="info" icon="el-icon-bell" circle size="medium"></el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item v-for="(item, index) in requestNotice" :key="index" @click.native="joinRoom(item)">
                    {{ item.chattedUserId }}同意与您深入交谈
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-badge>

            <el-badge :value="systemMessages" :max="99">
              <el-dropdown>
                <el-button
                  type="info"
                  icon="el-icon-s-check"
                  circle
                  size="medium"
                  @click.native="gotoconsult()"
                ></el-button>
              </el-dropdown>
            </el-badge>
          </p>
        </el-col>
      </el-row>
    </el-header>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "head",
  props: {
    index: {
      type: String,
    },
  },
  data() {
    return {
      userinfo: {
        id: "",
        uname_phone: "",
        usertype: "",
      },
      isLogin: false,
      chatNotice: {},
      requestNotice: {},
      chatUsername: "",
      chattedUsername: "",
      systemMessages: "",
    };
  },
  methods: {
    quit() {
      let app = this;
      console.log("ok");
      axios.get("/quitLogin").then(function (res) {
        console.log(res);
        if (res.data.result) {
          app.isLogin = false;
          window.location.href = "/login";
        }
      });
    },
    getNotice() {
      let url_getCurrentUser = "/getCurrentUser";
      axios.get(url_getCurrentUser).then((res) => {
        // console.log(res.data.message)
        if (res.data.message !== null) {
          let id = res.data.message.userid;
          let url_chatNotice = `/chatHistory/request/${id}`;
          axios.get(url_chatNotice).then((res) => {
            this.chatNotice = res.data;
            // console.log(this.requestNotice)
          });

          let url_request = `/chatHistory/select/${id}`;
          axios.get(url_request).then((res) => {
            this.requestNotice = res.data;
            // console.log(this.requestNotice)
          });
        }
      });
    },
    agreeNotice(item) {
      //接受者
      item.isagree = 1;
      // that.showInfo(object.people, object.aisle)
      let url_update = "/chatHistory/update";
      this.$axios.post(url_update, item).then((res) => {
        console.log(res);
      });

      this.getChatName(item.id);

      let url = `/user/get/${item.chattedUserId}`;
      axios.get(url).then((res) => {
        console.log(this.chatUsername);
        let name = res.data.username;
        let routeData = this.$router.resolve({
          path: "/chatRoom",
          query: {
            username: this.chatUsername,
            chattedUsername: this.chattedUsername,
            name: name,
            aisle: "",
            chatHistoryId: item.id,
          },
        });
        window.open(routeData.href, "_blank");
      });
    },
    joinRoom(item) {
      //聊天发起者
      item.isagree = 2;
      // that.showInfo(object.people, object.aisle)
      let url_update = "/chatHistory/update";
      this.$axios.post(url_update, item).then((res) => {
        console.log(res);
      });

      this.getChatName(item.id);

      let url = `/user/get/${item.userId}`;
      axios.get(url).then((res) => {
        console.log(this.chatUsername);
        let name = res.data.username;
        let routeData = this.$router.resolve({
          path: "/chatRoom",
          query: {
            username: this.chatUsername,
            chattedUsername: this.chattedUsername,
            name: name,
            aisle: item.chattedUserAisle,
            chatHistoryId: item.id,
          },
        });
        window.open(routeData.href, "_blank");
      });
    },
    getChatName(id) {
      let url_chatHistory = `/chatHistory/get/${id}`;
      axios.get(url_chatHistory).then((res) => {
        let item = res.data;
        console.log(item);
        let url_user = `/user/get/${item.userId}`;
        axios.get(url_user).then((res) => {
          // console.log(res.data.username)
          this.chatUsername = res.data.username;
          // console.log(this.username)
        });
        let url_chattedUser = `/user/get/${item.chattedUserId}`;
        axios.get(url_chattedUser).then((res) => {
          // console.log(res.data.username)
          this.chattedUsername = res.data.username;
          // console.log(this.chattedUsername)
        });
      });
    },
    getSystemMessages() {
      let app = this;
      let id = app.id;
      axios
        .post("/systemmessage/getMyState", id)
        .then(function (res) {
          app.systemMessages = res.data;
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    gotoconsult() {
      if(this.id==0){
        this.$router.push("./consultlist");
      }else{

        this.$router.push("./consult");
      }
    },
  },
  created() {
    let app = this;
    axios
      .get("/getCurrentUser")
      .then(function (res) {
        console.log("head", res);
        app.id = res.data.message.userid;
        if (res.data.result) {
          app.userinfo = res.data.message;
          app.isLogin = true;
        }
        this.getNotice();
      })
      .catch(function (error) {});
  },
  mounted() {
    let app = this;
    this.timer = setInterval(() => {
      setTimeout(() => {
        this.getNotice();
        app.getSystemMessages();
      }, 0);
    }, 5000);
  },
};
</script>

<style scoped>
.header {
  background: #f6f6f6;
}

.title-img {
  height: 55px;
}

.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}

.router-link-active {
  text-decoration: none;
}

a {
  text-decoration: none;
}
</style>
