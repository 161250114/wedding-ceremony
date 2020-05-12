<template>
  <div>
    <div style="width: 100%;height: 100px;text-align: center"><el-input style="width: 200px;margin:20px"v-model="input" placeholder="按姓名或编号查询"></el-input><el-button type="primary" @click="search(input)">搜索</el-button></div>
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
        prop="username"
        label="用户名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="state"
        label="状态">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="jump(scope.row)" type="text" size="small">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import axios from 'axios'
    export default {
      name: "consultlist",
      data() {
        return {
          input: "",
          tableData:[]
        }
      },
      created(){
        this.load()
      },
      methods:{
        load(){
          let app=this
          axios.get("/getCurrentUser")
            .then(function(res) {
              if(res.data.result==false){
                app.$router.push({
                  path: './login',
                })
              }
              app.id=res.data.message.userid
            })
            .catch(function (err) {
              console.log(err);
            })
          axios.get("/systemmessage/getState")
            .then(function(res) {
              app.tableData=res.data
            })
            .catch(function (err) {
              console.log(err);
            })
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
        jump(row){
          this.$router.push({
            path: './consult',
            query: {
              id: row.toid
            }
          })
        }
      }
    }
</script>

<style scoped>

</style>
