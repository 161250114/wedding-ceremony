<template>
  <div>
    <el-tabs v-model="activePane">
      <el-tab-pane label="添加好友" name="first">
        <el-row>
          <el-col :span="5">&nbsp;</el-col>
          <el-col :span="14">
            <el-input placeholder="请输入内容" v-model="searchString">
              <el-select
                v-model="searchMode"
                slot="prepend"
                placeholder="请选择"
                style="width: 130px;"
              >
                <el-option label="按用户名搜索" :value="1"></el-option>
                <el-option label="按用户ID搜索" :value="2"></el-option>
              </el-select>
              <el-button
                slot="append"
                icon="el-icon-search"
                @click="search"
              ></el-button>
            </el-input>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="3">&nbsp;</el-col>
          <el-col :span="18">
            <el-table :data="searchFriendList" style="width: 100%;">
              <el-table-column
                prop="username"
                label="用户ID"
                width="180"
                align="center"
              >
                <template slot-scope="scope">
                  <el-tag type="warning">{{ scope.row.userid }}</el-tag>
                </template>
              </el-table-column>
              <el-table-column
                prop="remark"
                label="用户名"
                width="180"
                align="center"
              >
                <template slot-scope="scope">
                  <el-tag type="success">{{ scope.row.username }}</el-tag>
                </template> </el-table-column
              >x
              <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                  <el-button
                    @click="
                      remarkDialog = true;
                      currentEditRow = scope.row;
                    "
                    type="text"
                    >查看用户资料</el-button
                  >
                  <el-button
                    type="text"
                    @click="
                      sendApplyDialog = true;
                      currentEditRow = scope.row;
                    "
                    >发送好友申请</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
      </el-tab-pane>
      <el-tab-pane label="已发出申请" name="second">
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
              <el-button
                type="danger"
                v-if="scope.row.result == '等待'"
                @click="
                  currentEditRow = scope.row;
                  undoApply();
                "
                >撤销申请</el-button
              >
              <el-button
                type="primary"
                v-if="
                  scope.row.result == '拒绝' || scope.row.result == '已撤销'
                "
                @click="
                  sendApplyDialog = true;
                  currentEditRow = { userid: scope.row.userid2 };
                "
                >重新申请</el-button
              >
            </template>
          </el-table-column>
        </el-table></el-tab-pane
      >
      <el-tab-pane label="未处理申请" name="third">
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
                      handleApplyDialog = true;
                      currentEditRow = scope.row;
                      handleApplyInfo.result = '通过';
                    "
                    >通过</el-button
                  >
                  <el-button
                    type="danger"
                    @click="
                      handleApplyDialog = true;
                      currentEditRow = scope.row;
                      handleApplyInfo.result = '拒绝';
                    "
                    >拒绝</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row></el-tab-pane
      >
      <el-tab-pane label="已处理申请" name="forth">
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
      title="发送好友申请"
      :visible.sync="sendApplyDialog"
      width="20%"
      :before-close="sendApplyClose"
    >
      <div>
        <el-form label-position="left" label-width="80px">
          <el-form-item label="申请信息:">
            <el-input
              type="textarea"
              placeholder="请输入申请信息"
              :autosize="{ minRows: 5, maxRows: 5 }"
              v-model="applyInfo"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button style="margin-top: 12px;" @click="sendApplyDialog = false"
          >取消</el-button
        >
        <el-button style="margin-top: 12px;" @click="sendFriendApply"
          >发送</el-button
        >
      </span>
    </el-dialog>
    <el-dialog
      title="处理好友申请"
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
              v-model="handleApplyInfo.replyInfo"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button style="margin-top: 12px;" @click="handleApplyDialog = false"
          >取消</el-button
        >
        <el-button style="margin-top: 12px;" @click="handleApply"
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
      searchMode: 1,
      sendApplyDialog: false,
      activePane: "first",
      searchString: "",
      searchFriendList: [],
      currentEditRow: {},
      applyInfo: "",
      sendApplyList: [],
      unhandledApplyList: [],
      handledApplyList: [],
      handleApplyDialog: false,
      handleApplyInfo: {
        replyInfo: "",
        result: "",
      },
    };
  },
  methods: {
    handleApply() {
      console.log("OK");
      let app = this;
      app.handleApplyDialog = false;
      Axios.post("/friend/handleFriendApply", {
        id: app.currentEditRow.id,
        result: app.handleApplyInfo.result,
        replyInfo: app.handleApplyInfo.replyInfo,
      })
        .then(function (res) {
          app.$message({
            message: res.data.message,
            type: "success",
          });
          app.getReceiveApplyList();
          app.$emit("updateInfo");
        })
        .catch(function (error) {});
    },
    sendApplyClose() {
      this.sendApplyDialog = false;
    },
    handleApplyClose() {
      this.handleApplyDialog = false;
    },
    filterHandler(value, row) {
      return row.result === value;
    },
    search() {
      let app = this;
      if (app.searchMode == 1) {
        Axios.post("/friend/fuzzySearchUserByUsername", app.searchString).then(
          function (res) {
            app.searchFriendList = res.data.message;
          }
        );
      } else if (app.searchMode == 2) {
        Axios.post("/friend/searchUserByUserid", app.searchString).then(
          function (res) {
            app.searchFriendList = res.data.message;
          }
        );
      }
    },
    sendFriendApply() {
      let app = this;
      app.sendApplyDialog = false;
      Axios.post("/friend/sendFriendApply", {
        userid2: app.currentEditRow.userid,
        applyInfo: app.applyInfo,
      })
        .then(function (res) {
          app.$message({
            message: res.data.message,
            type: "success",
          });
          app.getSendApplyList();
        })
        .catch(function (error) {});
    },
    getSendApplyList() {
      let app = this;
      app.sendApplyList.splice(0,app.sendApplyList.length)
      Axios.get("/friend/getSendFriendApplyList")
        .then(function (res) {
          app.sendApplyList = res.data.message;
        })
        .catch(function (error) {});
    },
    getReceiveApplyList() {
      let app = this;
      app.unhandledApplyList.splice(0,app.unhandledApplyList.length)
      app.handledApplyList.splice(0,app.handledApplyList.length)
      Axios.get("/friend/getReceiveFriendApplyList").then(function (res) {
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
    undoApply() {
      let app = this;
      Axios.post("/friend/handleFriendApply", {
        id: app.currentEditRow.id,
        result: "已撤销",
        replyInfo: "暂无",
      })
        .then(function (res) {
          app.$message({
            message: res.data.message,
            type: "success",
          });
        })
        .catch(function (error) {});
    },
  },
  created() {
    let app = this;
    app.getSendApplyList();
    app.getReceiveApplyList();
    app.$emit("getIndex", "/personalInfo/friendApply");
  },
};
</script>

<style scoped></style>
