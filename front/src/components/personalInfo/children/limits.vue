<template>
  <div>
    <div class="info_div">
      <el-form label-position="left" label-width="120px">
        <el-form-item label="资料浏览权限：">
          <el-radio-group
            v-model="userLimits.limitBrowse"
            :disabled="!edit_mode"
          >
            <el-radio :label="0">所有人可见</el-radio>
            <el-radio :label="1">仅符合标准可见</el-radio>
            <el-radio :label="2">所有人不可见</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="短信送达权限：">
          <el-radio-group
            v-model="userLimits.limitMessage"
            :disabled="!edit_mode"
          >
            <el-radio :label="0">接收系统短信</el-radio>
            <el-radio :label="1">不接收系统短信</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="好友申请权限：">
          <el-radio-group
            v-model="userLimits.limitFapply"
            :disabled="!edit_mode"
          >
            <el-radio :label="0">所有人可申请</el-radio>
            <el-radio :label="1">拒绝申请</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="约会申请权限：">
          <el-radio-group
            v-model="userLimits.limitDapply"
            :disabled="!edit_mode"
          >
            <el-radio :label="0">所有人可申请</el-radio>
            <el-radio :label="1">拒绝申请</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="!edit_mode">
          <el-button type="primary" @click="edit_mode = true">修改</el-button>
        </el-form-item>
        <el-form-item v-if="edit_mode">
          <el-button type="primary" @click="edit_mode = false">取消</el-button>
          <el-button type="primary" @click="submit">确定</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  data() {
    return {
      userLimits: {
        limitBrowse: 1,
        limitMessage: 1,
        limitFapply: 1,
        limitDapply: 1,
      },
      edit_mode: false,
    };
  },
  methods: {
    submit() {
      let app = this;
      app.edit_mode = false;
      Axios.post("/limit/changeUserLimits", app.userLimits).then(function (
        res
      ) {
        app.$message({
          message: "修改成功！",
          type: "success",
        });
      });
    },
  },
  created() {
    let app = this;
    Axios.get("/limit/getUserLimits")
      .then(function (res) {
        console.log(res);
        if (res.data.result) {
          app.userLimits = res.data.message;
        }
      })
      .catch(function (error) {});
    app.$emit("getIndex", "/personalInfo/limits");
  },
};
</script>

<style scoped>
.info_div {
  margin-top: 30px;
  width: 600px;
  margin-left: 100px;
}
</style>
