<template>
  <div class="info_div">
    <el-form label-width="100px">
      <el-form-item label="自我介绍：">
        <el-input
          type="textarea"
          v-model="introduction"
          :autosize="{ minRows: 10, maxRows: 10 }"
        ></el-input>
      </el-form-item>
    </el-form>
    <el-alert
      title="如果不知道写些什么，可以点击下方”使用模板“哦~~"
      type="success"
      :closable="false"
    >
    </el-alert>
    <p></p>
    <div>
      <el-button type="primary">保存</el-button>
      <el-button type="primary">重置</el-button>
      <el-button type="warning" @click="dialogVisible = true"
        >使用模板</el-button
      >
    </div>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose"
    >
      <div>
        <el-steps :active="active" finish-status="success">
          <el-step title="性格"></el-step>
          <el-step title="兴趣爱好"></el-step>
          <el-step title="大致经历"></el-step>
          <el-step title="成品展示"></el-step>
        </el-steps>
        <div>
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="float:left">你的性格：</span>
              <el-button style="float: right; padding: 3px 0" type="text"
                >换一批</el-button
              >
            </div>
            <div class="text item">
              <el-row>
                <el-col :span="3">&nbsp;</el-col>
                <el-col :span="18"
                  ><el-tag type="success">标签二</el-tag>
                  <el-tag type="info">标签三</el-tag>
                  <el-tag type="warning">标签四</el-tag>
                  <el-tag type="danger">标签五</el-tag
                  ><el-tag type="success">标签二</el-tag>
                  <el-tag type="info">标签三</el-tag>
                  <el-tag type="warning">标签四</el-tag>
                  <el-tag type="danger">标签五</el-tag
                  ><el-tag type="success">标签二</el-tag>
                  <el-tag type="info">标签三</el-tag>
                  <el-tag type="warning">标签四</el-tag>
                  <el-tag type="danger">标签五</el-tag
                  ><el-tag type="success">标签二</el-tag>
                  <el-tag type="info">标签三</el-tag>
                  <el-tag type="warning">标签四</el-tag>
                  <el-tag type="danger">标签五</el-tag
                  ><el-tag type="success">标签二</el-tag>
                  <el-tag type="info">标签三</el-tag>
                  <el-tag type="warning">标签四</el-tag>
                  <el-tag type="danger">标签五</el-tag
                  ><el-tag type="success">标签二</el-tag>
                  <el-tag type="info">标签三</el-tag>
                  <el-tag type="warning">标签四</el-tag>
                  <el-tag type="danger">标签五</el-tag></el-col
                >
                <el-col :span="3">&nbsp;</el-col>
              </el-row>
            </div>
          </el-card>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button style="margin-top: 12px;" @click="changeStep(-1)"
          >上一步</el-button
        >
        <el-button
          style="margin-top: 12px;"
          @click="changeStep(1)"
          v-show="this.active == 0 || this.active == 1"
          >下一步</el-button
        >
        <el-button style="margin-top: 12px;" v-show="this.active == 2"
          >完成</el-button
        >
        <el-button style="margin-top: 12px;" v-show="this.active == 3"
          >保存</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      introduction: "我是一只皮皮！",
      dialogVisible: false,
      active: 0
    };
  },
  methods: {
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    changeStep(index) {
      let app = this;
      if (app.active == 0 && index == -1) {
        app.$message({
          message: "已回到第一步！",
          type: "warning"
        });
      } else {
        app.active = app.active + index;
      }
      console.log(app.active);
    }
  }
};
</script>

<style scoped>
.info_div {
  margin-top: 30px;
  width: 400px;
  margin-left: 100px;
}
</style>
