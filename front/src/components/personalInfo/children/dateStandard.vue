<template>
  <div>
    <div class="info_div">
      <el-form label-position="left" label-width="80px">
        <el-form-item label="年龄：">
          <el-select
            v-model="standard.ageMin"
            placeholder="请选择"
            class="select"
          >
            <el-option key="不限" label="不限" value="不限"></el-option>
            <el-option
              v-for="count in 82"
              :key="count + 17"
              :label="count + 17"
              :value="count + 17"
            >
            </el-option>
          </el-select>
          <span>至</span>
          <el-select
            v-model="standard.ageMax"
            placeholder="请选择"
            class="select"
          >
            <el-option key="不限" label="不限" value="不限"></el-option>
            <el-option
              v-for="count in 82"
              :key="count + 17"
              :label="count + 17"
              :value="count + 17"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="身高：">
          <el-select
            v-model="standard.heightMin"
            placeholder="请选择"
            class="select"
          >
            <el-option key="不限" label="不限" value="不限"></el-option>
            <el-option
              v-for="count in 82"
              :key="count + 128"
              :label="count + 128"
              :value="count + 128"
            >
            </el-option>
          </el-select>
          <span>至</span>
          <el-select
            v-model="standard.heightMax"
            placeholder="请选择"
            class="select"
          >
            <el-option key="不限" label="不限" value="不限"></el-option>
            <el-option
              v-for="count in 82"
              :key="count + 128"
              :label="count + 128"
              :value="count + 128"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="月收入：">
          <el-select
            v-model="standard.salary"
            placeholder="请选择"
            class="single_select"
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
        <el-form-item label="学历:">
          <el-select
            v-model="standard.education"
            placeholder="请选择"
            class="single_select"
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
        <el-form-item label="住址:">
          <el-cascader
            ref="cityCascader"
            :options="cities"
            @change="addressChange"
            v-model="addressCode"
            class="single_select"
          ></el-cascader>
        </el-form-item>
        <el-form-item label="婚姻状况:">
          <el-radio v-model="standard.marriage" label="1">未婚</el-radio>
          <el-radio v-model="standard.marriage" label="2">离婚</el-radio>
          <el-radio v-model="standard.marriage" label="3">丧偶</el-radio>
          <el-radio v-model="standard.marriage" label="4">不限</el-radio>
        </el-form-item>
      </el-form>
      <el-alert
        title="这些选项会影响系统为您推荐的用户列表~~~"
        type="success"
        :closable="false"
      >
      </el-alert>
      <p></p>
      <el-button type="primary">保存</el-button>
      <el-button type="primary">重置</el-button>
    </div>
  </div>
</template>

<script>
import { regionData } from "element-china-area-data";
export default {
  data() {
    return {
      standard: {
        ageMin: "19",
        ageMax: "25",
        heightMin: "不限",
        heightMax: "不限",
        salary: "不限",
        education: "不限",
        address: "江苏省/宿迁市/泗阳县",
        marriage: "4"
      },
      cities: "",
      addressCode: [],
      educationList: [
        "不限",
        "高中中专及以下",
        "大专",
        "本科",
        "双学士",
        "硕士",
        "博士"
      ],
      salaryList: [
        "不限",
        "2000元以下",
        "2000-5000元",
        "5000-10000元",
        "10000-20000元",
        "20000-50000元",
        "50000元以上"
      ]
    };
  },
  methods: {
    addressChange() {
      let app = this;
      app.standard.address = "";
      let temp = app.$refs.cityCascader.getCheckedNodes()[0].pathLabels;
      for (let i = 0; i < temp.length; i++) {
        app.standard.address = app.standard.address + temp[i];
        if (i != temp.length - 1) {
          app.standard.address += "/";
        }
      }
      console.log(app.standard.address);
    }
  },
  created() {
    let app = this;
    app.cities = JSON.parse(JSON.stringify(regionData));
    let tempAddress = { label: "不限", value: "不限" };
    app.cities.splice(0, 0, tempAddress);
    for (let i = 0; i < app.cities.length; i++) {
      if (app.cities[i].children != null) {
        app.cities[i].children.splice(0, 0, tempAddress);
        for (let j = 0; j < app.cities[i].children.length; j++) {
          if (app.cities[i].children[j].children != null) {
            app.cities[i].children[j].children.splice(0, 0, tempAddress);
          }
        }
      }
    }
    let temp = app.cities;
    let cityArray = app.standard.address.split("/");
    for (let i = 0; i < cityArray.length; i++) {
      for (let j = 0; j < temp.length; j++) {
        if (cityArray[i] == temp[j].label) {
          app.addressCode.push(temp[j].value);
          temp = temp[j].children;
          break;
        }
      }
    }
  },
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/addAlbumMax");
  }
};
</script>

<style scoped>
.info_div {
  margin-top: 30px;
  width: 500px;
  margin-left: 100px;
}

.select {
  width: 150px;
}

.single_select {
  width: 300px;
}
</style>
