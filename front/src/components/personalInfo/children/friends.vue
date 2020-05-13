<template>
  <div>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="18"
        ><el-input placeholder="请输入内容" v-model="searchString">
          <el-select
            v-model="searchMode"
            slot="prepend"
            placeholder="请选择"
            style="width: 130px;"
          >
            <el-option label="按用户名搜索" value="1"></el-option>
            <el-option label="按备注搜索" value="2"></el-option>
          </el-select>
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="search"
          ></el-button> </el-input
        ><el-table :data="tableList" style="width: 100%;">
          <el-table-column
            prop="username"
            label="用户ID"
            width="100"
            align="center"
          >
            <template slot-scope="scope">
              <el-tag type="danger">{{ scope.row.userid }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="username"
            label="用户名"
            width="180"
            align="center"
          >
            <template slot-scope="scope">
              <el-tag type="warning">{{ scope.row.username }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="remark"
            label="备注"
            width="180"
            align="center"
          >
            <template slot-scope="scope">
              <el-tag type="success">{{ scope.row.remark }}</el-tag>
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
                >修改好友备注</el-button
              >
              <el-button
                type="text"
                @click="
                  sendApplyDialog = true;
                  currentEditRow = scope.row;
                "
                >发起约会</el-button
              >
              <el-button type="danger" @click="deleteFriend(scope.row)"
                >删除好友</el-button
              >
            </template>
          </el-table-column>
        </el-table></el-col
      >
    </el-row>
    <el-dialog title="修改备注" :visible.sync="remarkDialog" width="30%">
      <span
        ><label>新备注：</label
        ><el-input
          placeholder="请输入新备注"
          style="width: 50%;"
          v-model="newRemark"
        ></el-input
      ></span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="remarkDialog = false">取 消</el-button>
        <el-button type="primary" @click="saveNewRemark">保存</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="发送约会申请"
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
        <el-button style="margin-top: 12px;" @click="sendDateApply"
          >发送</el-button
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
      friendsList: [
        { friendid: "1000001", username: "dapi", remark: "大皮" },
        { friendid: "1000002", username: "161250113", remark: "韦神" },
      ],
      searchString: "",
      searchMode: "1",
      tableList: [],
      remarkDialog: false,
      sendApplyDialog: false,
      newRemark: "",
      currentEditRow: "",
      applyInfo: "",
    };
  },
  methods: {
    search() {
      let app = this;
      app.tableList.splice(0, app.tableList.length);
      if (app.searchMode == 1) {
        for (let i = 0; i < app.friendsList.length; i++) {
          if (app.friendsList[i].username.indexOf(app.searchString) != -1) {
            app.tableList.push(app.friendsList[i]);
          }
        }
      } else if (app.searchMode == 2) {
        for (let i = 0; i < app.friendsList.length; i++) {
          if (app.friendsList[i].remark.indexOf(app.searchString) != -1) {
            app.tableList.push(app.friendsList[i]);
          }
        }
      }
    },
    saveNewRemark() {
      let app = this;
      Axios.post("/friend/changeRemark", {
        userid: app.currentEditRow.userid,
        remark: app.newRemark,
      }).then(function (res) {
        if (res.data.result) {
          app.currentEditRow.remark = app.newRemark;
          app.friendsList = JSON.parse(JSON.stringify(app.tableList));
          app.$message({
            message: res.data.message,
            type: "success",
          });
        }
        app.remarkDialog = false;
      });
    },
    deleteFriend(row) {
      let app = this;
      Axios.post("/friend/deleteFriend", { userid: row.userid }).then(function (
        res
      ) {
        if (res.data.result) {
          for (let i = 0; i < app.tableList.length; i++) {
            if (app.tableList[i].friendid == row.friendid) {
              app.tableList.splice(i, 1);
              break;
            }
          }
          app.friendsList = JSON.parse(JSON.stringify(app.tableList));
          app.$message({
            message: res.data.message,
            type: "success",
          });
        }
      });
    },
    sendDateApply() {
      let app = this;
      app.sendApplyDialog = false;
      Axios.post("/friend/sendDateApply", {
        userid2: app.currentEditRow.userid,
        applyInfo: app.applyInfo,
      })
        .then(function (res) {
          app.$message({
            message: res.data.message,
            type: "success",
          });
        })
        .catch(function (error) {});
    },
    sendApplyClose() {
      let app = this;
      app.sendApplyDialog = false;
    },
  },
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/friends");
    Axios.get("/friend/getFriendList").then(function (res) {
      app.friendsList = res.data.message;
      app.tableList = JSON.parse(JSON.stringify(app.friendsList));
    });
  },
};
</script>

<style scoped></style>
