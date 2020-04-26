<template>
  <div>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="18"
        ><el-table :data="applyList" style="width: 100%">
          <el-table-column label="用户名" width="180" align="center">
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
              <el-button type="text" @click="handleApply(true,scope.row)">通过</el-button>
              <el-button type="danger" @click="handleApply(false,scope.row)"
                >拒绝</el-button
              >
            </template>
          </el-table-column>
        </el-table></el-col
      >
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      applyList: [
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
      ]
    };
  },
  methods:{
    handleApply(result,row){

    }
  },
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/friendApply");
  }
};
</script>

<style scoped></style>
