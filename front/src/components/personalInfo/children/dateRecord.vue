<template>
  <div>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="18">
        <el-table
          :data="dateRecords"
          style="width: 100%;"
          :default-sort="{ prop: 'startDate', order: 'descending' }"
        >
          <el-table-column label="状态" align="center">
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.status === '在进行' ? 'success' : 'danger'"
                >{{ scope.row.status }}</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column label="用户ID" align="center">
            <template slot-scope="scope">
              <el-tag type="warning">{{ scope.row.userid2 }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="开始日期"
            align="center"
            prop="startDate"
            sortable
          >
            <template slot-scope="scope">
              <el-tag>{{ scope.row.startDate }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="结束日期" align="center">
            <template slot-scope="scope">
              <el-tag>{{ scope.row.endDate }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button
                type="danger"
                v-if="scope.row.status === '在进行'"
                @click="stopDating(scope.row)"
                >结束约会</el-button
              >
              <el-button
                type="primary"
                v-if="
                  scope.row.isCommented === 0 && scope.row.status === '有缘无分'
                "
                @click="commentDialog = true"
                >进行评价</el-button
              >
              <el-button
                type="primary"
                v-if="scope.row.isCommented === 1"
                disabled
                >已评价</el-button
              >
              <el-dialog
                title="信息真实度评价"
                :visible.sync="commentDialog"
                width="50%"
                :before-close="handleClose"
              >
                <div>
                  <div class="block">
                    <el-row>
                      <span class="demonstration"
                        >基础信息真实度(年龄，身高，性别)</span
                      >
                    </el-row>
                    <el-row>
                      <el-col :span="5">&nbsp;</el-col>
                      <el-col :span="17">
                        <el-slider
                          v-model="basiccomment"
                          show-input
                        ></el-slider>
                      </el-col>
                      <el-col :span="2">&nbsp;</el-col>
                    </el-row>
                    <el-row>
                      <span class="demonstration"
                        >其他信息真实度(职业，月薪，住址等)</span
                      >
                    </el-row>
                    <el-row>
                      <el-col :span="5">&nbsp;</el-col>
                      <el-col :span="17">
                        <el-slider
                          v-model="extracomment"
                          show-input
                        ></el-slider>
                      </el-col>
                      <el-col :span="2">&nbsp;</el-col>
                    </el-row>
                    <el-row>
                      <span class="demonstration">性格兴趣真实度</span>
                    </el-row>
                    <el-row>
                      <el-col :span="5">&nbsp;</el-col>
                      <el-col :span="17">
                        <el-slider
                          v-model="labelcomment"
                          show-input
                        ></el-slider>
                      </el-col>
                      <el-col :span="2">&nbsp;</el-col>
                    </el-row>
                  </div>
                </div>
                <span slot="footer" class="dialog-footer">
                  <el-button
                    style="margin-top: 12px;"
                    @click="commentDialog = false"
                    >取消</el-button
                  >
                  <el-button
                    style="margin-top: 12px;"
                    @click="
                      scope.row.isCommented=1;
                      sendCreditComment();
                    "
                    >完成评价</el-button
                  >
                </span>
              </el-dialog>
            </template>
          </el-table-column>
        </el-table></el-col
      >
    </el-row>
  </div>
</template>

<script>
import Axios from "axios";
export default {
  data() {
    return {
      dateRecords: [],
      commentDialog: false,
      basiccomment: 60,
      extracomment: 60,
      labelcomment: 60,
    };
  },
  methods: {
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    sendCreditComment() {
      let app = this;
      app.$message({
        message: "提交成功",
        type: "success",
      });
      app.commentDialog = false;
    },
    getDateRecords() {
      let app = this;
      Axios.get("/record/getDateRecords").then(function (res) {
        if (res.data.result) {
          console.log(res.data.message);
          app.dateRecords = res.data.message;
        }
      });
    },
    stopDating(row) {
      let app = this;
      Axios.post("/record/stopDating", row).then(function (res) {
        app.$message({
          message: res.data.message,
          type: "success",
        });
        app.getDateRecords();
      });
    },
  },
  created() {
    let app = this;
    app.getDateRecords();
    app.$emit("getIndex", "/personalInfo/dateRecord");
  },
};
</script>

<style scoped></style>
