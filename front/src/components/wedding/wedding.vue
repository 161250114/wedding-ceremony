<template>
<div>
  <div style="width: 100%;height: 100px"><el-input style="width: 200px;margin:20px"v-model="input" placeholder="按姓名或编号查询"></el-input><el-button type="primary" @click="search(input)">搜索</el-button></div>
  <el-table
    :data="tableData"
    height="400"
    border
    style="width: 100%">
    <el-table-column
      prop="id"
      label="编号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="phone"
      label="电话">
    </el-table-column>
    <el-table-column
      prop="email"
      label="邮箱">
    </el-table-column>
    <el-table-column
      prop="total"
      label="总人数">
    </el-table-column>
    <el-table-column
      prop="state"
      label="状态">
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
        </template>
      </el-table-column>
    </el-table-column>

  </el-table>
</div>
</template>

<script>
    export default {
      name: "wedding",
      data() {
        return {
          input:"",
          tableData: [{
            id: '2016-05-03',
            name: '王小虎',
            phone: '上海市普陀区金沙江路 1518 弄'
          }, {
            id: '2016-05-02',
            name: '王小虎',
            phone: '上海市普陀区金沙江路 1518 弄'
          }, {
            id: '2016-05-04',
            name: '王小虎',
            phone: '上海市普陀区金沙江路 1518 弄'
          }, {
            id: '2016-05-01',
            name: '王小虎',
            phone: '上海市普陀区金沙江路 1518 弄'
          }, {
            id: '2016-05-08',
            name: '卢本伟',
            phone: '上海市普陀区金沙江路 1518 弄'
          }, {
            id: '2016-05-06',
            name: '王小虎',
            phone: '上海市普陀区金沙江路 1518 弄'
          }, {
            id: '2016-05-07',
            name: '王小虎',
            phone: '上海市普陀区金沙江路 1518 弄'
          }]
        }
      },
      methods: {
      handleClick(row){
        console.log(row.id)
      },
      search(input){
        let app=this
        let table=this.tableData
        let result=new Array();
        for(let i=0;i<table.length;i++){
          let data=table[i];
          if(data["id"].indexOf(input)>=0||data["name"].indexOf(input)>=0){
            result.push(data);
          }
        }
        console.log(result)
      },

    }

    }
</script>

<style scoped>

</style>
