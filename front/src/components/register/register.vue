<template>
  <div>
    <el-row style="height: 60px;">
      <el-col ::span="1">&nbsp;</el-col>
    </el-row>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="18">
        <el-steps :active="active" finish-status="success">
          <el-step title="基本信息填写"></el-step>
          <el-step title="个人资料填写"></el-step>
          <el-step title="自我介绍填写"></el-step>
          <el-step title="完成注册"></el-step>
        </el-steps>
      </el-col>
    </el-row>
    <el-row style="height: 60px;">
      <el-col :span="1">&nbsp;</el-col>
    </el-row>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="8">
        <div class="block">
          <el-carousel height="400px">
            <el-carousel-item v-for="item in 4" :key="item">
              <img
                :src="picUrls[item - 1]"
                style="width: 100%; height: 100%;"
              />
            </el-carousel-item>
          </el-carousel>
        </div>
      </el-col>
      <el-col :span="2">&nbsp;</el-col>
      <el-col :span="9">
        <el-form
          label-position="left"
          label-width="80px"
          v-if="this.active == 0"
          :rules="rules1"
          :model="userInfo"
          ref="ruleForm1"
        >
          <el-row>
            <el-col :span="15"
              ><el-form-item label="用户名:" prop="username">
                <el-input
                  prefix-icon="el-icon-user"
                  placeholder="不超过15个字符"
                  v-model="userInfo.username"
                ></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="密码:" prop="password">
                <el-input
                  show-password
                  prefix-icon="el-icon-postcard"
                  placeholder="不超过15个字符"
                  v-model="userInfo.password"
                ></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="确认密码:" prop="password2">
                <el-input
                  show-password
                  prefix-icon="el-icon-postcard"
                  placeholder="重复密码"
                  v-model="userInfo.password2"
                ></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="手机号:" prop="phone">
                <el-input
                  prefix-icon="el-icon-phone"
                  placeholder="请输入手机号"
                  v-model="userInfo.phone"
                ></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="10"
              ><el-form-item label="验证码:" prop="validateNumber">
                <el-input
                  prefix-icon="el-icon-message"
                  placeholder="请输入验证码"
                  v-model="validateNumber"
                ></el-input> </el-form-item
            ></el-col>
            <el-col :span="1">
              <el-button
                type="primary"
                @click="sendKey"
                :disabled="!validateButton.active"
                >{{ validateButton.message }}</el-button
              >
            </el-col>
          </el-row>
        </el-form>
        <el-form
          label-position="left"
          label-width="80px"
          v-else-if="this.active == 1"
        >
          <el-row>
            <el-col :span="15"
              ><el-form-item label="姓名:">
                <el-input
                  v-model="userInfo.fullName"
                  placeholder="请输入姓名"
                  prefix-icon="el-icon-user-solid"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="性别:">
                <el-radio v-model="userInfo.sex" label="1">男</el-radio>
                <el-radio v-model="userInfo.sex" label="2">女</el-radio>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="生日:">
                <div class="block">
                  <el-date-picker
                    v-model="userInfo.birthday"
                    type="date"
                    placeholder="选择日期"
                    style="width: 100%;"
                    value-format="yyyy-MM-dd"
                  >
                  </el-date-picker>
                </div>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="10"
              ><el-form-item label="身高:">
                <el-input
                  v-model="userInfo.height"
                  placeholder="以厘米(cm)为单位的整数"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="1"
              ><el-form-item label-width="0px"
                ><label style="font-size: 20px;">CM</label></el-form-item
              >
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="住址:">
                <el-cascader
                  ref="cityCascader"
                  :options="cities"
                  style="width: 100%;"
                  @change="addressChange"
                ></el-cascader>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="学历:">
                <el-select
                  v-model="userInfo.education"
                  placeholder="请选择"
                  style="width: 100%;"
                >
                  <el-option
                    v-for="item in educationList"
                    :key="item"
                    :label="item"
                    :value="item"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="婚姻状况:">
                <el-radio v-model="userInfo.marriage" label="0">未婚</el-radio>
                <el-radio v-model="userInfo.marriage" label="1">离婚</el-radio>
                <el-radio v-model="userInfo.marriage" label="2">丧偶</el-radio>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="职业:">
                <el-select
                  v-model="userInfo.profession"
                  placeholder="请选择"
                  style="width: 100%;"
                >
                  <el-option
                    v-for="item in professionList"
                    :key="item"
                    :label="item"
                    :value="item"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="月薪:">
                <el-select
                  v-model="userInfo.salary"
                  placeholder="请选择"
                  style="width: 100%;"
                >
                  <el-option
                    v-for="item in salaryList"
                    :key="item"
                    :label="item"
                    :value="item"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-form
          label-position="left"
          label-width="80px"
          v-else-if="this.active == 2"
        >
          <el-row>
            <el-col :span="15">
              <el-form-item label="标签:">
                <label v-if="userInfo.tagList.length == 0">尚未选择标签</label>
                <div v-if="userInfo.tagList.length != 0">
                  <el-tag v-for="tag in userInfo.tagList" :key="tag">{{
                    tag
                  }}</el-tag>
                </div>
              </el-form-item>
              <el-divider></el-divider>
            </el-col>
            <el-col :span="4">
              <el-button @click="tagDialog = true">选择标签</el-button>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15">
              <el-form-item label="兴趣爱好:">
                <label v-if="userInfo.questionList.length==0">尚未选择问题</label>
                <label v-else>{{questionMessage}}</label>
              </el-form-item>
              <el-divider></el-divider>
            </el-col>
            <el-col :span="4">
              <el-button @click="questionDialog = true">添加兴趣爱好</el-button>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="自我介绍:">
                <el-input
                  type="textarea"
                  v-model="userInfo.introduction"
                  :autosize="{ minRows: 10, maxRows: 10 }"
                  placeholder="请自由发挥吧！"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div v-else>
          <el-row style="margin-top: 10%;">
            <el-col :span="16">
              <el-alert
                type="success"
                title="注册成功"
                show-icon
                :closable="false"
                >恭喜您注册成功，页面将在5秒内跳转！</el-alert
              >
            </el-col>
          </el-row>
        </div>
        <el-row>
          <el-col :span="15">
            <el-button
              style="margin-top: 12px;"
              @click="changeStep(-1)"
              v-if="this.active != 3"
              >上一步</el-button
            >
            <el-button
              style="margin-top: 12px;"
              @click="changeStep(1)"
              v-show="this.active == 0 || this.active == 1"
              >下一步</el-button
            >
            <el-button
              style="margin-top: 12px;"
              @click="submit"
              v-show="this.active == 2"
              >提交</el-button
            >
            <router-link to="/login">
              <el-button
                style="margin-top: 20%;"
                v-show="this.active == 3"
                type="primary"
                >返回登录页面</el-button
              ></router-link
            >
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-dialog
      title="性格选择"
      :visible.sync="tagDialog"
      width="50%"
      :before-close="handleClose"
    >
      <div>
        <div>
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="float: left;">你的性格：</span>
              <el-tag
                v-for="tag in tagChosedList"
                :key="tag.label"
                :type="tag.color"
                closable
                @close="deleteTag(tag)"
                >{{ tag.label }}</el-tag
              >
              <el-button
                style="float: right; padding: 3px 0;"
                type="text"
                @click="changeTagList"
                >换一批</el-button
              >
            </div>
            <div class="text item">
              <el-row>
                <el-col :span="3">&nbsp;</el-col>
                <el-col :span="18"
                  ><el-tag
                    :type="tag.color"
                    v-for="tag in tagList"
                    :key="tag.label"
                    @click="chooseTag(tag)"
                    >{{ tag.label }}</el-tag
                  >
                </el-col>
                <el-col :span="3">&nbsp;</el-col>
              </el-row>
            </div>
          </el-card>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button style="margin-top: 12px;" @click="tagDialog = false"
          >取消</el-button
        >
        <el-button style="margin-top: 12px;" @click="saveTagList"
          >保存</el-button
        >
      </span>
    </el-dialog>
    <el-dialog
      title="兴趣爱好选择"
      :visible.sync="questionDialog"
      width="50%"
      :before-close="handleClose"
    >
      <div>
        <div>
          <el-row>
            <el-col :span="3">&nbsp;</el-col>
            <el-col :span="18">
              <el-form label-width="100px" :inline="true">
                <div v-for="index in questionNumber" :key="index">
                  <el-form-item>
                    <label>问题{{ index }}</label>
                    <el-select v-model="questionList[index - 1]" placeholder="">
                      <el-option
                        v-for="question in questionAllList"
                        :key="question"
                        :value="question"
                        :disabled="questionList.indexOf(question) != -1"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item>
                    <el-input
                      placeholder="此处输入答案"
                      v-model="answerList[index - 1]"
                    ></el-input>
                  </el-form-item>
                </div>
                <el-button
                  type="primary"
                  icon="el-icon-plus"
                  @click="addQuestion(1)"
                  circle
                ></el-button>
                <el-button
                  type="primary"
                  icon="el-icon-minus"
                  @click="addQuestion(-1)"
                  circle
                ></el-button>
              </el-form>
            </el-col>
          </el-row>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button style="margin-top: 12px;" @click="questionDialog = false"
          >取消</el-button
        >
        <el-button style="margin-top: 12px;" @click="saveQuestionList"
          >保存</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { regionData } from "element-china-area-data";
import Axios from "axios";
export default {
  data() {
    var checkUsername = (rule, value, callback) => {
      if (value == "") {
        return callback(new Error("名称不能为空"));
      }
      setTimeout(() => {
        if (value.length > 15) {
          callback(new Error("长度超出范围"));
        } else {
          callback();
        }
      }, 1000);
    };
    var checkUsername = (rule, value, callback) => {
      if (value == "") {
        return callback(new Error("名称不能为空"));
      }
      setTimeout(() => {
        if (value.length > 15) {
          callback(new Error("长度超出范围"));
        } else {
          callback();
        }
      }, 1000);
    };
    var checkPassword = (rule, value, callback) => {
      if (value == "") {
        return callback(new Error("密码不能为空"));
      }
      setTimeout(() => {
        if (value.length > 15) {
          callback(new Error("长度超出范围"));
        } else {
          callback();
        }
      }, 1000);
    };
    var checkPassword2 = (rule, value, callback) => {
      if (value == "") {
        return callback(new Error("确认密码不能为空"));
      }
      setTimeout(() => {
        if (value != this.userInfo.password) {
          callback(new Error("两次密码输入不一致"));
        } else {
          callback();
        }
      }, 1000);
    };
    var checkPhone = (rule, value, callback) => {
      if (value == "") {
        return callback(new Error("手机不能为空"));
      }
      setTimeout(() => {
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(value)) {
          callback(new Error("请输入正确手机号码"));
        } else if (value.length != 11) {
          callback(new Error("手机号长度错误"));
        } else {
          callback();
        }
      }, 1000);
    };
    var checkValidateNumber = (rule, value, callback) => {
      if (value == "") {
        return callback(new Error("验证码不能为空"));
      }
      setTimeout(() => {
        callback();
      }, 1000);
    };
    return {
      active: 0,
      validateButton: {
        active: true,
        message: "发送验证码",
        leftSeconds: 0,
        maxSeconds: 10,
      },
      validateNumber: "",
      validateTimer: null,
      picUrls: [
        "../../../static/registerPic1.png",
        "../../../static/registerPic2.jpeg",
        "../../../static/registerPic3.png",
        "../../../static/registerPic4.jpeg",
      ],
      userInfo: {
        username: "",
        password: "",
        phone: "",
        fullName: "",
        sex: 0,
        birthday: "",
        height: "",
        introduction: "",
        address: "",
        education: "",
        marriage: "",
        salary: "",
        profession: "",
        password2: "",
        tagList: [],
        questionList: [],
      },
      cities: "",
      educationList: [
        "高中中专及以下",
        "大专",
        "本科",
        "双学士",
        "硕士",
        "博士",
      ],
      professionList: [
        "销售",
        "客服",
        "人事/行政/后勤",
        "餐饮",
        "旅游",
        "酒店",
        "超市/百货/零售",
        "美容/美发",
        "保健按摩",
        "运动健身",
        "普工/技工",
        "生产管理/研发",
        "汽车制造/服务",
        "建筑",
        "物业管理",
        "房产中介",
        "家政保洁/安保",
        "司机/交通服务",
        "贸易/采购",
        "物流/仓储",
        "淘宝职位",
        "美术/设计/创意",
        "市场/媒介/公关",
        "广告/会展/咨询",
        "影视/娱乐/休闲",
        "教育培训",
        "财务/审计/统计",
        "法律",
        "翻译",
        "编辑/出版/印刷",
        "计算机/互联网/通信",
        "电子/电气",
        "机械/仪器仪表",
        "金融/银行/证券/投资",
        "保险",
        "医院/医疗/护理",
        "制药/生物工程",
        "服装/纺织/食品",
        "环保/能源",
        "质控/安防",
        "高级管理",
        "农/林/牧/渔业",
        "其他职业",
      ],
      salaryList: [
        "2000元以下",
        "2000-5000元",
        "5000-10000元",
        "10000-20000元",
        "20000-50000元",
        "50000元以上",
      ],
      introductionPane: "first",
      rules1: {
        username: [{ validator: checkUsername, trigger: "blur" }],
        password: [{ validator: checkPassword, trigger: "blur" }],
        password2: [{ validator: checkPassword2, trigger: "blur" }],
        phone: [{ validator: checkPhone, trigger: "blur" }],
        validateNumber: [{ validator: checkValidateNumber, trigger: "blur" }],
      },
      tagDialog: false,
      questionDialog: false,
      tagAllList: [
        "开朗",
        "大方",
        "主动",
        "外向",
        "俏皮",
        "敏捷",
        "乐观",
        "调皮",
        "爽朗",
        "豪爽",
        "正直",
        "直率",
        "直爽",
        "耿直",
        "公正",
        "正派",
        "爽快",
        "率真",
        "真诚",
        "热诚",
        "纯真",
        "坦率",
        "笃实",
        "热忱",
        "热心",
        "好客",
        "和气",
        "和蔼",
        "和善",
        "亲切",
        "谦让",
        "谦虚",
        "谦逊",
        "虚心",
        "自谦",
        "强硬",
        "毅力",
        "勇敢",
        "果敢",
        "坚强",
      ],
      tagColor: ["", "success", "warning", "danger"],
      tagList: [],
      tagChosedList: [],
      questionAllList: [
        "1、最喜欢的游戏是",
        "2、最喜欢的运动是",
        "3、最喜欢的明星是",
        "4、最喜欢的食物是",
        "5、最喜欢的歌曲是",
        "6、最喜欢的书是",
      ],
      questionList: ["", "", "", "", "", ""],
      questionNumber: 1,
      answerList: ["", "", "", "", "", ""],
      questionMessage:""
    };
  },
  methods: {
    changeStep(index) {
      let app = this;
      if (app.active == 0 && index == 1) {
        this.$refs["ruleForm1"].validate((valid) => {
          if (valid) {
            app.active = app.active + index;
          } else {
            app.$message({
              message: "请正确填写！",
              type: "warning",
            });
            return false;
          }
        });
      }
      if (app.active == 0 && index == -1) {
        app.$message({
          message: "已回到第一步！",
          type: "warning",
        });
      }
      if (app.active == 1 && index == 1) {
        app.active++;
      }
    },
    chooseTag(tag) {
      let app = this;
      let hasExisted = false;
      if (app.tagChosedList.length == 10) {
        app.$message({
          message: "标签的数量不能多于10个！",
          type: "warning",
        });
        return;
      }
      for (let i = 0; i < app.tagChosedList.length; i++) {
        if (app.tagChosedList[i].label == tag.label) {
          app.$message({
            message: "该标签已存在！请勿重复选择！",
            type: "warning",
          });
          hasExisted = true;
        }
      }
      if (!hasExisted) app.tagChosedList.push(tag);
    },
    addQuestion(num) {
      let app = this;
      if (app.questionNumber == 6 && num == 1) {
        app.$message({
          message: "最多只能设置六个问题哦",
          type: "warning",
        });
        return;
      }
      if (app.questionNumber == 1 && num == -1) {
        app.$message({
          message: "至少设置一个问题哦",
          type: "warning",
        });
        return;
      }
      if (num == -1) {
        app.questionList.splice(app.questionNumber - 1, 1, "");
        console.log(app.questionList);
      }
      app.questionNumber += num;
    },
    deleteTag(tag) {
      let app = this;
      for (let i = 0; i < app.tagChosedList.length; i++) {
        if (app.tagChosedList[i].label == tag.label) {
          app.tagChosedList.splice(i, 1);
        }
      }
      console.log(app.tagChosedList);
    },
    changeTagList() {
      let app = this;
      app.tagList = [];
      while (app.tagList.length < 52) {
        let tempIndex = Math.floor(Math.random() * app.tagAllList.length);
        let hasExisted = false;
        for (let i = 0; i < app.tagList.length; i++) {
          if (app.tagAllList[tempIndex] == app.tagList[i].label) {
            hasExisted = true;
            break;
          }
        }
        if (!hasExisted) {
          app.tagList.push({
            label: app.tagAllList[tempIndex],
            color: app.tagColor[Math.floor(Math.random() * 4)],
          });
        }
      }
    },
    saveTagList() {
      let app = this;
      app.userInfo.tagList.splice(0, app.userInfo.tagList.length);
      for (let i = 0; i < app.tagChosedList.length; i++) {
        app.userInfo.tagList.push(app.tagChosedList[i].label);
      }
      app.tagDialog = false;
    },
    saveQuestionList() {
      let app = this;
      app.userInfo.questionList.splice(0, app.userInfo.questionList.length);
      for (let i = 0; i < app.questionList.length; i++) {
        if (app.questionList[i] == "") {
          break;
        } else {
          for (let j = 0; j < app.questionAllList.length; j++) {
            if (app.questionAllList[j] == app.questionList[i]) {
              app.userInfo.questionList.push({
                questionid: j,
                answer: app.answerList[i],
              });
            }
          }
        }
      }
      app.questionDialog = false;
      app.questionMessage="当前已选问题"
      for(let i=0;i<app.userInfo.questionList.length;i++){
        app.questionMessage+=(app.userInfo.questionList[i].questionid+1)+"  "
      }
      console.log(app.userInfo.questionList);
    },
    sendKey() {
      let app = this;
      Axios.post("register/sendValidateNumber", app.userInfo.phone)
        .then(function (res) {})
        .catch(function (error) {});
      app.validateButton.active = false;
      app.validateButton.leftSeconds = app.validateButton.maxSeconds;
      app.validateTimer = setInterval(function () {
        app.validateButton.message =
          "已发送(" + app.validateButton.leftSeconds + ")";
        app.validateButton.leftSeconds--;
        if (app.validateButton.leftSeconds === -1) {
          clearInterval(app.validateTimer);
          app.validateButton.active = true;
          app.validateButton.message = "发送验证码";
        }
      }, 1000);
    },
    addressChange() {
      let app = this;
      app.userInfo.address = "";
      let temp = app.$refs.cityCascader.getCheckedNodes()[0].pathLabels;
      for (let i = 0; i < temp.length; i++) {
        app.userInfo.address = app.userInfo.address + temp[i];
        if (i != temp.length - 1) {
          app.userInfo.address += "/";
        }
      }
      console.log(app.userInfo.address);
    },
    submit() {
      let app = this;
      Axios.post("register", app.userInfo)
        .then(function (res) {
          console.log(res);
          app.active = 3;
        })
        .catch(function (error) {});
    },
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
  },
  created() {
    this.cities = regionData;
    this.changeTagList();
  },
};
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
