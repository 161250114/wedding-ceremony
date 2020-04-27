<template>
  <div>
    <el-tabs v-model="activePane">
      <el-tab-pane label="已发出申请" name="first">
        <el-table
          :data="sendApplyList"
          style="width: 100%"
          :default-sort="{ prop: 'applyDate', order: 'descending' }"
        >
          <el-table-column
            prop="applyDate"
            label="申请日期"
            align="center"
            sortable
          >
          </el-table-column>
          <el-table-column label="被申请人" width="150" align="center">
            <template slot-scope="scope">
              <el-tag type="warning">{{ scope.row.username }}</el-tag>
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
              { text: '拒绝', value: '拒绝' }
            ]"
            :filter-method="filterHandler"
          >
            <template slot-scope="scope">
              <el-tag type="info" v-if="scope.row.status == '等待'">{{
                scope.row.status
              }}</el-tag>
              <el-tag
                :type="scope.row.status == '通过' ? 'success' : 'danger'"
                v-else
                >{{ scope.row.status }}</el-tag
              >
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="danger" v-if="scope.row.status == '等待'"
                >撤销申请</el-button
              >
              <el-button type="primary" v-if="scope.row.status == '拒绝'"
                >重新申请</el-button
              >
            </template>
          </el-table-column>
        </el-table></el-tab-pane
      >
      <el-tab-pane label="收到的申请" name="second">
        <el-row>
          <el-col :span="3">&nbsp;</el-col>
          <el-col :span="18">
            <el-table :data="receiveApplyList" style="width: 100%">
              <el-table-column label="申请人" width="180" align="center">
                <template slot-scope="scope">
                  <el-tag type="warning">{{ scope.row.username }}</el-tag>
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
                  <el-button type="text" @click="handleApply(true, scope.row)"
                    >通过</el-button
                  >
                  <el-button
                    type="danger"
                    @click="handleApply(false, scope.row)"
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
          style="width: 100%"
          :default-sort="{ prop: 'applyDate', order: 'descending' }"
        >
          <el-table-column
            prop="applyDate"
            label="申请日期"
            align="center"
            sortable
          >
          </el-table-column>
          <el-table-column label="申请人" width="150" align="center">
            <template slot-scope="scope">
              <el-tag type="warning">{{ scope.row.username }}</el-tag>
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
              { text: '拒绝', value: '拒绝' }
            ]"
            :filter-method="filterHandler"
          >
            <template slot-scope="scope">
              <el-tag type="info" v-if="scope.row.status == '等待'">{{
                scope.row.status
              }}</el-tag>
              <el-tag
                :type="scope.row.status == '通过' ? 'success' : 'danger'"
                v-else
                >{{ scope.row.status }}</el-tag
              >
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  data() {
    return {
      receiveApplyList: [
        {
          userid: "1000003",
          username: "161250112",
          applyInfo: "请加我为好友吧！"
        },
        {
          userid: "1000004",
          username: "shawandshaw",
          applyInfo: "处对象嘛小姐姐"
        },
        {
          userid: "1000005",
          username: "发条的橙",
          applyInfo: "小姐姐找我我超帅！"
        }
      ],
      activePane: "first",
      sendApplyList: [
        {
          applyDate: "2020-04-20",
          userid: "1000010",
          username: "gjd",
          applyInfo: "老师加一下好友呗",
          replyInfo: "暂无",
          status: "等待"
        },
        {
          applyDate: "2020-04-19",
          userid: "1000011",
          username: "testApplyUser1",
          applyInfo: "小姐姐来玩呀~",
          replyInfo: "滚!",
          status: "拒绝"
        },
        {
          applyDate: "2020-04-18",
          userid: "1000012",
          username: "testApplyUser2",
          applyInfo: "小姐姐来玩呀~",
          replyInfo: "好呀好呀！",
          status: "通过"
        }
      ],
      handledApplyList: [
        {
          applyDate: "2020-04-20",
          userid: "1000010",
          username: "testApplyUser3",
          applyInfo: "老师加一下好友呗",
          replyInfo: "暂无",
          status: "通过"
        },
        {
          applyDate: "2020-04-19",
          userid: "1000011",
          username: "testApplyUser4",
          applyInfo: "小姐姐来玩呀~",
          replyInfo: "滚!",
          status: "拒绝"
        }
      ]
    };
  },
  methods: {
    handleApply(result, row) {},
    filterHandler(value, row) {
      return row.status === value;
    }
  },
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/friendApply");
  }
};
</script>

<style scoped></style>
