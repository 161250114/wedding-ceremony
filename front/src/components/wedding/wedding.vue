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
    </el-table-column>
      <el-table-column
        prop="start"
        label="开始时间">
      </el-table-column>
      <el-table-column
        prop="end"
        label="结束时间">
      </el-table-column>
      <el-table-column
        label="位置信息">
        <template slot-scope="scope">
          <el-button @click="checkLoc(scope.row)" type="text" size="small">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column
        label="细节">
        <template slot-scope="scope">
          <el-button @click="checkDet(scope.row)" type="text" size="small">查看</el-button>
        </template>
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button v-if="getState(scope.row)==0" @click="handleClick(scope.row)" type="text" size="small">通过</el-button>
          <el-button v-if="getState(scope.row)==0" @click="handleClick(scope.row)" type="text" size="small">取消</el-button>
        </template>
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
            id:1,
            name:"卢本伟",
            phone:"",
            email:"",
            total:100,
            start:"",
            end:"",
            location:"777777",
            detail:"2155",
            state:0
          }, {
            id:2,
            name:"五五开",
            phone:"",
            email:"",
            total:120,
            start:"",
            end:"",
            location:"35325",
            detail:"2532",
            state:1
          }, {
            id:3,
            name:"pdd",
            phone:"",
            email:"",
            total:200,
            start:"",
            end:"",
            location:"2314",
            detail:"144345315",
            state:0
          }]
        }
      },
      methods: {
        handleClick(row){
        console.log(row.id)
      },
        getState(row){
          let app=this
          let table=this.tableData
          let info=""
          for(let i=0;i<table.length;i++){
            if(table[i]["id"]==row.id){
              return table[i]["state"]
            }
          }
        },
        checkLoc(row){
          let app=this
          let table=this.tableData
          let info=""
          for(let i=0;i<table.length;i++){
            if(table[i]["id"]==row.id){
              info=table[i]["location"];
            }
          }
          this.$alert(info, '位置信息', {
            confirmButtonText: '确定',
          });
        },
        checkDet(row){
          let app=this
          let table=this.tableData
          let info=""
          for(let i=0;i<table.length;i++){
            if(table[i]["id"]==row.id){
              info=table[i]["detail"];
            }
          }
          this.$alert(info, '细节信息', {
            confirmButtonText: '确定',
          });
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
        this.tableData=result
      },
    }

    }
</script>

<style scoped>

</style>
