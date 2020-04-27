<template>
  <div>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="18">
        <el-table :data="dateRecords" style="width: 100%">
          <el-table-column label="状态" align="center">
            <template slot-scope="scope">
              <el-tag :type="scope.row.status==='在进行'?'success':'danger'">{{ scope.row.status }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="用户名" align="center">
            <template slot-scope="scope">
              <el-tag type="warning">{{ scope.row.username}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="开始日期" align="center">
            <template slot-scope="scope">
              <el-tag>{{ scope.row.startDate }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="结束日期" align="center">
            <template slot-scope="scope">
              <el-tag>{{ scope.row.endDate }}</el-tag>
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
      dateRecords: [
        {
          status:"在进行",
          username: "161250117",
          startDate: "2020-06-01",
          endDate: "待续"
        },
        {
          status:"有缘无分",
          username: "161250117",
          startDate: "2020-04-01",
          endDate: "2020-05-30"
        }
      ]
    };
  },
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/dateRecord");
  }
};
</script>

<style scoped></style>
