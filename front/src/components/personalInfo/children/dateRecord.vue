<template>
  <div>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="18">
        <el-table :data="dateRecords" style="width: 100%;">
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
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="danger" v-if="scope.row.status === '在进行'"
              @click="stopDating(scope.row)"
                >结束约会</el-button
              >
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
    };
  },
  methods: {
    getDateRecords() {
      let app = this;
      Axios.get("/record/getDateRecords").then(function (res) {
        if (res.data.result) {
          console.log(res.data.message);
          app.dateRecords = res.data.message;
        }
      });
    },
    stopDating(row){
      let app=this;
      Axios.post("/record/stopDating",row).then(function(res){
        app.$message({
            message: res.data.message,
            type: "success",
          });
      })
    }
  },
  created() {
    let app = this;
    app.getDateRecords();
    app.$emit("getIndex", "/personalInfo/dateRecord");
  },
};
</script>

<style scoped></style>
