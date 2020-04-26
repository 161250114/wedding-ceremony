<template>
  <div>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="18">
        <el-table
          :data="billList"
          style="width: 100%"
          :default-sort="{ prop: 'time', order: 'descending' }"
        >
          <el-table-column
            label="消费类型"
            width="100"
            align="center"
            :filters="[
              { text: '购买', value: '购买' },
              { text: '充值', value: '充值' }
            ]"
            :filter-method="filterHandler"
          >
            <template slot-scope="scope">
              <el-tag type="warning">{{ scope.row.type }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="消费金额" width="100" align="center">
            <template slot-scope="scope">
              <el-tag>{{ scope.row.money }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="账户余额" width="100" align="center">
            <template slot-scope="scope">
              <el-tag type="danger">{{ scope.row.balance }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="time"
            label="消费时间"
            width="250"
            align="center"
            sortable
          >
          </el-table-column>
          <el-table-column label="备注" align="center">
            <template slot-scope="scope">
              <el-tag type="success">{{ scope.row.remark }}</el-tag>
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
      billList: [
        {
          type: "充值",
          money: "50",
          balance:"50",
          time: "2020-04-18 14:36:57",
          remark: "充值了50元钱"
        },
        {
          type: "购买",
          money: "10",
          balance:"40",
          time: "2020-04-19 15:26:34",
          remark: "续费会员1个月"
        },
        {
          type: "购买",
          money: "10",
          balance:"30",
          time: "2020-04-23 14:37:58",
          remark: "购买相册容量5张"
        }
      ]
    };
  },
  methods: {
    filterHandler(value, row) {
      return row.type === value;
    }
  },
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/billList");
  }
};
</script>

<style scoped></style>
