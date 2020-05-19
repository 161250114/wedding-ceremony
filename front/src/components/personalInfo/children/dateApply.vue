<template>
  <div>
    <el-tabs v-model="activePane">
      <el-tab-pane label="已发出申请" name="first">
        <el-table
          :data="sendApplyList"
          style="width: 100%;"
          :default-sort="{ prop: 'applyDate', order: 'descending' }"
        >
          <el-table-column
            prop="applyDate"
            label="申请日期"
            align="center"
            sortable
          >
          </el-table-column>
          <el-table-column label="被申请人ID" width="150" align="center">
            <template slot-scope="scope">
              <el-tag type="warning">{{ scope.row.userid2 }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="申请信息" width="250" align="center">
            <template slot-scope="scope">
              <el-tag>{{ scope.row.applyInfo }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="回复信息" width="250" align="center">
            <template slot-scope="scope">
              <el-tag>{{ scope.row.replyInfo }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="状态"
            align="center"
            :filters="[
              { text: '等待', value: '等待' },
              { text: '通过', value: '通过' },
              { text: '拒绝', value: '拒绝' },
            ]"
            :filter-method="filterHandler"
          >
            <template slot-scope="scope">
              <el-tag type="info" v-if="scope.row.result == '等待'">{{
                scope.row.result
              }}</el-tag>
              <el-tag
                :type="scope.row.result == '通过' ? 'success' : 'danger'"
                v-else
                >{{ scope.row.result }}</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="danger" v-if="scope.row.result == '等待'"
                >撤销申请</el-button
              >
              <el-button
                type="primary"
                v-if="
                  scope.row.result == '拒绝' || scope.row.result == '已撤销'
                "
                >重新申请</el-button
              >
            </template>
          </el-table-column>
        </el-table></el-tab-pane
      >
      <el-tab-pane label="未处理申请" name="second">
        <el-row>
          <el-col :span="3">&nbsp;</el-col>
          <el-col :span="18">
            <el-table :data="unhandledApplyList" style="width: 100%;">
              <el-table-column label="申请人ID" width="180" align="center">
                <template slot-scope="scope">
                  <el-tag type="warning">{{ scope.row.userid1 }}</el-tag>
                </template>
              </el-table-column>
              <el-table-column label="申请信息" width="280" align="center">
                <template slot-scope="scope">
                  <el-tag>{{ scope.row.applyInfo }}</el-tag>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                  <el-button type="text">查看用户资料</el-button>
                  <el-button
                    type="text"
                    @click="
                      currentEditRow = scope.row;
                      handleApply.result = '通过';
                      handleApplyDialog = true;
                    "
                    >通过</el-button
                  >
                  <el-button
                    type="danger"
                    @click="
                      currentEditRow = scope.row;
                      handleApply.result = '拒绝';
                      handleApplyDialog = true;
                    "
                    >拒绝</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row></el-tab-pane
      >
      <el-tab-pane label="已处理申请" name="third">
        <el-table
          :data="handledApplyList"
          style="width: 100%;"
          :default-sort="{ prop: 'applyDate', order: 'descending' }"
        >
          <el-table-column
            prop="applyDate"
            label="申请日期"
            align="center"
            sortable
          >
          </el-table-column>
          <el-table-column label="申请人ID" width="150" align="center">
            <template slot-scope="scope">
              <el-tag type="warning">{{ scope.row.userid1 }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="申请信息" width="250" align="center">
            <template slot-scope="scope">
              <el-tag>{{ scope.row.applyInfo }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="回复信息" width="250" align="center">
            <template slot-scope="scope">
              <el-tag>{{ scope.row.replyInfo }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="状态"
            align="center"
            :filters="[
              { text: '通过', value: '通过' },
              { text: '拒绝', value: '拒绝' },
            ]"
            :filter-method="filterHandler"
          >
            <template slot-scope="scope">
              <el-tag type="info" v-if="scope.row.result == '等待'">{{
                scope.row.result
              }}</el-tag>
              <el-tag
                :type="scope.row.result == '通过' ? 'success' : 'danger'"
                v-else
                >{{ scope.row.result }}</el-tag
              >
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
    <el-dialog
      title="处理约会申请"
      :visible.sync="handleApplyDialog"
      width="20%"
      :before-close="handleApplyClose"
    >
      <div>
        <el-form label-position="left" label-width="80px">
          <el-form-item label="回复信息:">
            <el-input
              type="textarea"
              placeholder="请输入回复信息"
              :autosize="{ minRows: 5, maxRows: 5 }"
              v-model="handleApply.replyInfo"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button style="margin-top: 12px;" @click="handleApplyDialog = false"
          >取消</el-button
        >
        <el-button style="margin-top: 12px;" @click="handleDateApply"
          >确定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>
<script>
import Axios from "axios";
export default {
  data() {
    return {
      sendApplyList: [],
      activePane: "first",
      unhandledApplyList: [],
      handledApplyList: [],
      handleApplyDialog: false,
      currentEditRow: {},
      handleApply: {
        result: "",
        replyInfo: "",
      },
    };
  },
  methods: {
    handleApplyClose() {
      let app = this;
      app.handleApplyDialog = false;
    },
    handleDateApply() {
      let app = this;
      app.handleApplyDialog = false;
      Axios.post("/friend/handleDateApply", {
        id: app.currentEditRow.id,
        result: app.handleApply.result,
        replyInfo: app.handleApply.replyInfo,
      })
        .then(function (res) {
          app.$message({
            message: res.data.message,
            type: "success",
          });
          app.$emit("updateInfo");
          app.getReceiveApplyList();
        })
        .catch(function (error) {});
    },
    filterHandler(value, row) {
      return row.result === value;
    },
    getSendDateApplyList() {
      let app = this;
      app.sendApplyList.splice(0, app.sendApplyList.length);
      Axios.get("/friend/getSendDateApplyList")
        .then(function (res) {
          app.sendApplyList = res.data.message;
        })
        .catch(function (error) {});
    },
    getReceiveApplyList() {
      let app = this;
      app.unhandledApplyList.splice(0, app.unhandledApplyList.length);
      app.handledApplyList.splice(0, app.handledApplyList.length);
      Axios.get("/friend/getReceiveDateApplyList").then(function (res) {
        for (let i = 0; i < res.data.message.length; i++) {
          console.log(res.data);
          if (res.data.message[i].result == "等待") {
            app.unhandledApplyList.push(res.data.message[i]);
          } else {
            app.handledApplyList.push(res.data.message[i]);
          }
        }
      });
    },
  },
  created() {
    let app = this;
    app.getSendDateApplyList();
    app.getReceiveApplyList();
    app.$emit("getIndex", "/personalInfo/dateApply");
  },
};
</script>

<style scoped></style>
