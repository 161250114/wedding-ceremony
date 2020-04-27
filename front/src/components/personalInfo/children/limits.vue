<template>
  <div>
    <div class="info_div">
      <el-form label-position="left" label-width="120px">
        <el-form-item label="资料浏览权限：">
          <el-radio-group v-model="limit_browse" :disabled="!edit_mode">
            <el-radio label="1">所有人可见</el-radio>
            <el-radio label="2">仅符合标准可见</el-radio>
            <el-radio label="3">所有人不可见</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="短信送达权限：">
          <el-radio-group v-model="limit_message" :disabled="!edit_mode">
            <el-radio label="1">接收系统短信</el-radio>
            <el-radio label="2">不接收系统短信</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="好友申请权限：">
          <el-radio-group v-model="limit_fapply" :disabled="!edit_mode">
            <el-radio label="1">所有人可申请</el-radio>
            <el-radio label="2">拒绝申请</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="约会申请权限：">
          <el-radio-group v-model="limit_dapply" :disabled="!edit_mode">
            <el-radio label="1">所有人可申请</el-radio>
            <el-radio label="2">拒绝申请</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="!edit_mode">
          <el-button type="primary" @click="edit_mode = true">修改</el-button>
        </el-form-item>
        <el-form-item v-if="edit_mode">
          <el-button type="primary" @click="edit_mode = false">取消</el-button>
          <el-button type="primary">确定</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      limit_browse: "1",
      limit_message: "1",
      limit_fapply: "1",
      limit_dapply: "1",
      edit_mode: false
    };
  },
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/limits");
  }
};
</script>

<style scoped>
.info_div {
  margin-top: 30px;
  width: 600px;
  margin-left: 100px;
}
</style>
