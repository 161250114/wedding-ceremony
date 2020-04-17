<template>
  <div class="whole">
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
        >
          <el-row>
            <el-col :span="15"
            ><el-form-item label="用户名:" v-model="userInfo.username">
              <el-input prefix-icon="el-icon-user"></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
            ><el-form-item label="密码:" v-model="userInfo.password">
              <el-input
                prefix-icon="el-icon-postcard"
              ></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
            ><el-form-item label="确认密码:">
              <el-input
                prefix-icon="el-icon-postcard"
              ></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
            ><el-form-item label="手机号:" v-model="userInfo.phone">
              <el-input prefix-icon="el-icon-phone"></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="10"
            ><el-form-item label="验证码:">
              <el-input
                prefix-icon="el-icon-message"
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
                >
                </el-date-picker>
              </div>
            </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
            ><el-form-item label="身高:">
              <el-input
                v-model="userInfo.height"
                placeholder="以厘米(cm)为单位"
              ></el-input>
            </el-form-item>
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
        <el-row>
          <el-col :span="15">
            <el-button style="margin-top: 12px;" @click="changeStep(-1)"
            >上一步</el-button
            >
            <el-button style="margin-top: 12px;" @click="changeStep(1)"
            >下一步</el-button
            >
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    export default {
      name: "applywedding",
      data(){
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
        }
      }
    }
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }
</style>
