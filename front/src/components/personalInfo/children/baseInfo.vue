<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
    >
      <el-menu-item index="1">固定资料</el-menu-item>
      <el-menu-item index="2">基本资料</el-menu-item>
    </el-menu>
    <div v-if="activeIndex == '1'" class="info_div">
      <el-form label-position="left" label-width="100px">
        <el-form-item label="姓名:">
          <el-input
            v-model="newUserInfo.fullname"
            :disabled="editMode"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别:">
          <el-radio v-model="newUserInfo.sex" :label="1" :disabled="editMode"
            >男</el-radio
          >
          <el-radio v-model="newUserInfo.sex" :label="2" :disabled="editMode"
            >女</el-radio
          >
        </el-form-item>
        <el-form-item label="生日:">
          <div class="block">
            <el-date-picker
              v-model="newUserInfo.birthday"
              type="date"
              style="width: 100%;"
              value-format="yyyy-MM-dd"
              :disabled="editMode"
            >
            </el-date-picker>
          </div>
        </el-form-item>
        <el-form-item label="身高（cm）:">
          <el-input
            v-model="newUserInfo.height"
            :disabled="editMode"
          ></el-input>
        </el-form-item>
      </el-form>
      <div>
        <el-alert
          title="固定资料一经注册就无法修改！！！"
          type="warning"
          :closable="false"
          show-icon
        >
        </el-alert>
      </div>
      <p></p>
      <!-- <el-button
        type="danger"
        @click="changeMode(false)"
        v-if="editMode == true"
        ><i class="el-icon-warning"></i>强制修改</el-button
      > -->
      <div v-if="editMode == false">
        <div>
          <el-button type="primary">保存</el-button>
          <el-button type="primary">重置</el-button>
        </div>
      </div>
    </div>
    <div v-if="activeIndex == '2'" class="info_div">
      <el-form label-position="left" label-width="100px">
        <el-form-item label="用户名:">
          <el-input
            prefix-icon="el-icon-user"
            placeholder="不超过15个字符"
            v-model="newUserInfo.username"
          ></el-input>
        </el-form-item>
        <el-form-item label="手机号:">
          <el-input
            prefix-icon="el-icon-phone"
            v-model="newUserInfo.phone"
          ></el-input>
        </el-form-item>
        <el-form-item label="住址:">
          <el-cascader
            ref="cityCascader"
            :options="cities"
            style="width: 100%;"
            @change="addressChange"
            v-model="addressCode"
          ></el-cascader>
        </el-form-item>
        <el-form-item label="学历:">
          <el-select
            v-model="newUserInfo.education"
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
        <el-form-item label="婚姻状况:">
          <el-radio v-model="newUserInfo.marriage" :label="0">未婚</el-radio>
          <el-radio v-model="newUserInfo.marriage" :label="1">离婚</el-radio>
          <el-radio v-model="newUserInfo.marriage" :label="2">丧偶</el-radio>
        </el-form-item>
        <el-form-item label="职业:">
          <el-select
            v-model="newUserInfo.profession"
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
        <el-form-item label="月薪:">
          <el-select
            v-model="newUserInfo.salary"
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
      </el-form>
      <el-button type="primary" @click="changeBaseInfo">保存</el-button>
      <el-button type="primary" @click="redo">重置</el-button>
    </div>
  </div>
</template>

<script>
import { regionData } from "element-china-area-data";
import Axios from "axios";

export default {
  data() {
    return {
      activeIndex: "1",
      userInfo: {
        fullname: "沈*",
        sex: 1,
        birthday: "1998-06-30",
        username: "pikaqiu",
        phone: "13218051808",
        height: "172",
        address: "江苏省/宿迁市/泗阳县",
        education: "本科",
        marriage: 1,
        profession: "计算机/互联网/通信",
        salary: "5000-10000元",
      },
      newUserInfo: {
        fullname: "沈*",
        sex: 1,
        birthday: "1998-06-30",
        username: "pikaqiu",
        phone: "13218051808",
        height: "172",
        address: "江苏省/宿迁市/泗阳县",
        education: "本科",
        marriage: 1,
        profession: "计算机/互联网/通信",
        salary: "5000-10000元",
      },
      educationList: [],
      salaryList: [],
      professionList: [],
      addressCode: [],
      cities: "",
      editMode: true,
      index: 0,
    };
  },
  methods: {
    handleSelect(key) {
      let app = this;
      app.activeIndex = key;
    },
    changeMode(newMode) {
      let app = this;
      app.editMode = newMode;
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
    redo() {
      let app = this;
      app.newUserInfo = JSON.parse(JSON.stringify(app.userInfo));
    },
    changeBaseInfo() {
      let app = this;
      Axios.post("/userInfo/changeBaseInfo", app.newUserInfo)
        .then(function (res) {
          if (res.data.result) {
            app.$message({
              message: "修改成功！",
              type: "success",
            });
            window.location.href = "/personalInfo/baseInfo";
          }
        })
        .catch(function (error) {});
    },
  },
  created() {
    let app = this;
    app.cities = regionData;
    let temp = app.cities;
    Axios.get("../../../static/infoList.json").then(function(res){
      app.salaryList=res.data.salaryList
      app.educationList=res.data.educationList
      app.professionList=res.data.professionList
    })
    Axios.get("/userInfo/getBaseInfo")
      .then(function (res) {
        if (res.data.result) {
          console.log(res.data.message);
          app.userInfo = JSON.parse(JSON.stringify(res.data.message));
          app.newUserInfo = JSON.parse(JSON.stringify(res.data.message));
          let cityArray = app.userInfo.address.split("/");
          for (let i = 0; i < cityArray.length; i++) {
            for (let j = 0; j < temp.length; j++) {
              if (cityArray[i] == temp[j].label) {
                app.addressCode.push(temp[j].value);
                temp = temp[j].children;
                break;
              }
            }
          }
        }
      })
      .catch(function (error) {});
    app.$emit("getIndex", "/personalInfo/baseInfo");
  },
};
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
}

.info_div {
  margin-top: 30px;
  width: 400px;
  margin-left: 100px;
}
</style>
