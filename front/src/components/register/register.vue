<template>
  <div>
    <el-row style="height:60px">
      <el-col :span="1">&nbsp;</el-col>
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
    <el-row style="height:60px">
      <el-col :span="1">&nbsp;</el-col>
    </el-row>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="8">
        <div class="block">
          <el-carousel height="400px">
            <el-carousel-item v-for="item in 4" :key="item">
              <img :src="picUrls[item - 1]" style="width:100%;height:100%" />
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
              ><el-form-item label="密码:">
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
              ><el-form-item label="确认密码:">
                <el-input
                  show-password
                  prefix-icon="el-icon-postcard"
                  placeholder="重复密码"
                  v-model="password2"
                ></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="手机号:">
                <el-input
                  prefix-icon="el-icon-phone"
                  placeholder="请输入手机号"
                  v-model="userInfo.phone"
                ></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="10"
              ><el-form-item label="验证码:">
                <el-input
                  prefix-icon="el-icon-message"
                  placeholder="请输入验证码"
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
                    style="width:100%"
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
                ><label style="font-size:20px">CM</label></el-form-item
              >
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="住址:">
                <el-cascader
                  ref="cityCascader"
                  :options="cities"
                  style="width:100%"
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
                  style="width:100%"
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
                <el-radio v-model="userInfo.marriage" label="1">未婚</el-radio>
                <el-radio v-model="userInfo.marriage" label="2">离婚</el-radio>
                <el-radio v-model="userInfo.marriage" label="3">丧偶</el-radio>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
              ><el-form-item label="职业:">
                <el-select
                  v-model="userInfo.profession"
                  placeholder="请选择"
                  style="width:100%"
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
                  style="width:100%"
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
            <el-col :span="15"
              ><el-form-item label="自我介绍:">
                <el-input
                  type="textarea"
                  v-model="userInfo.introduction"
                  :autosize="{ minRows: 10, maxRows: 10 }"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <div v-else>
          <el-row style="margin-top:10%">
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
    return {
      active: 0,
      validateButton: {
        active: true,
        message: "发送验证码",
        leftSeconds: 0,
        maxSeconds: 10
      },
      validateTimer: null,
      picUrls: [
        "../../../static/registerPic1.png",
        "../../../static/registerPic2.jpeg",
        "../../../static/registerPic3.png",
        "../../../static/registerPic4.jpeg"
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
        profession: ""
      },
      password2: "",
      cities: "",
      educationList: [
        "高中中专及以下",
        "大专",
        "本科",
        "双学士",
        "硕士",
        "博士"
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
        "其他职业"
      ],
      salaryList: [
        "2000元以下",
        "2000-5000元",
        "5000-10000元",
        "10000-20000元",
        "20000-50000元",
        "50000元以上"
      ],
      introductionPane: "first",
      rules1: {
        username: [{ validator: checkUsername, trigger: "blur" }]
      }
    };
  },
  methods: {
    changeStep(index) {
      let app = this;
      if (app.active == 0 && index == 1) {
        this.$refs["ruleForm1"].validate(valid => {
          if (valid) {
            app.active = app.active + index;
          } else {
            app.$message({
              message: "请正确填写！",
              type: "warning"
            });
            return false;
          }
        });
      }
      if (app.active == 0 && index == -1) {
        app.$message({
          message: "已回到第一步！",
          type: "warning"
        });
      }
    },
    sendKey() {
      let app = this;
      app.validateButton.active = false;
      app.validateButton.leftSeconds = app.validateButton.maxSeconds;
      app.validateTimer = setInterval(function() {
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
        .then(function(res) {
          console.log(res);
          app.active = 3;
        })
        .catch(function(error) {});
    }
  },
  created() {
    this.cities = regionData;
  }
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
