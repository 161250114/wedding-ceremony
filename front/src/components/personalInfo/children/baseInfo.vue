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
    <div
      v-if="activeIndex == '1'"
      style="margin-top:30px;width:300px;"
      class="info_div"
    >
      <el-form label-position="left" label-width="100px">
        <el-form-item label="姓名:">
          <el-input
            v-model="userInfo.fullName"
            :disabled="changeMode"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别:">
          <el-radio v-model="userInfo.sex" label="1" :disabled="changeMode"
            >男</el-radio
          >
          <el-radio v-model="userInfo.sex" label="2" :disabled="changeMode"
            >女</el-radio
          >
        </el-form-item>
        <el-form-item label="生日:">
          <div class="block">
            <el-date-picker
              v-model="userInfo.birthday"
              type="date"
              style="width:100%"
              value-format="yyyy-MM-dd"
              :disabled="changeMode"
            >
            </el-date-picker>
          </div>
        </el-form-item>
      </el-form>
    </div>
    <div v-if="activeIndex == '2'" class="info_div">
      <el-form label-position="left" label-width="100px">
        <el-form-item label="用户名:">
          <el-input
            prefix-icon="el-icon-user"
            placeholder="不超过15个字符"
            v-model="userInfo.username"
          ></el-input>
        </el-form-item>
        <el-form-item label="手机号:">
          <el-input
            prefix-icon="el-icon-phone"
            v-model="userInfo.phone"
          ></el-input>
        </el-form-item>
        <el-form-item label="身高:">
          <el-input
            v-model="userInfo.height"
          ></el-input>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: "1",
      userInfo: {
        fullName: "沈*",
        sex: "1",
        birthday: "1998-06-30",
        username: "pikaqiu",
        phone: "13218051808",
        height:"172cm"
      },
      changeMode: true
    };
  },
  methods: {
    handleSelect(key) {
      let app = this;
      app.activeIndex = key;
    }
  }
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
  width: 300px;
  margin-left: 100px;
}
</style>
