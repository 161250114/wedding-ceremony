<template>
<div style="text-align: center;">
  <div style="width: 100%;height: 100px">
    <el-input style="width: 200px;margin:20px"v-model="input" placeholder="按姓名或编号查询"></el-input>
    <el-button type="primary" @click="search(input)">搜索</el-button>
    <el-button type="primary" @click="showAll()">显示全部</el-button>
  </div>
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
          <el-button v-if="getState(scope.row)=='申请中'&&isMana" @click="pass(scope.row)" type="text" size="small">通过</el-button>
          <el-button v-if="getState(scope.row)=='申请中'" @click="cancel(scope.row)" type="text" size="small">取消</el-button>
        </template>
      </el-table-column>
  </el-table>
</div>
</template>

<script>
  import axios from 'axios'
    export default {
      name: "wedding",
      data() {
        return {
          id:-1,
          isMana:false,
          input:"",
          storage:[],
          tableData: []
        }
      },
      created(){
        this.load();
      },
      methods: {
        showAll(){
          this.tableData=JSON.parse(JSON.stringify(this.storage))
        },
        handleClick(row){
        console.log(row.id)
        },
        load(){
          let app = this
          axios.get("/getCurrentUser")
            .then(function(res) {
              let message = res.data.message
              let id = message.userid;
              axios.post('/wedding/get',id)
                .then(function (res) {
                  app.tableData = res.data
                  app.storage = JSON.parse(JSON.stringify(res.data))
                  if(id==0){
                    app.isMana=true
                  }
                })
                .catch(function (err) {
                  console.log(err);
                });
            });
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
        find(id){
          let app=this;
          for(let i=0;i<app.storage.length;i++){
            if(app.storage[i].id==id){
              return app.storage[i];
            }
          }
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
        if(input==""){
          this.$alert("请输入搜索关键字", '提示', {
            confirmButtonText: '确定',
          });
          return;
        }
        let app=this
        let table=this.tableData
        let result=new Array();
        for(let i=0;i<table.length;i++){
          let data=table[i];
          if((data["id"]+"").indexOf(input)>=0||data["name"].indexOf(input)>=0){
            result.push(data);
          }
        }
        this.tableData=result
      },
        pass(row){
          let app=this
          row.state="通过"
          let data=JSON.parse(JSON.stringify(row))
          data.state=1
          axios.post("/wedding/update",data)
            .then(successResponse => {
              let record=new Object();
              record["id"]=0;
              record["weddingId"]=row.id;
              record["approverId"]=1;
              record["time"]=new Date();
              record["result"]=1;
              axios.post("/weddingrecord/add",record)
                .then(successResponse=>{
                  this.$alert("操作成功", '提示', {
                    confirmButtonText: '确定',
                  });
                })
                .catch(failResponse=>{
                  this.$alert("操作失败，请刷新页面重试", '提示', {
                    confirmButtonText: '确定',
                  });
                })
            })
            .catch(failResponse => {
              this.$alert("操作失败，请刷新页面重试", '提示', {
                confirmButtonText: '确定',
              });
            }); //失败后的操作
        },
        cancel(row){
          row.state="取消"
          let data=JSON.parse(JSON.stringify(this.find(row.id)))
          console.log(data)
          data.state="取消"
          axios.post("/wedding/update",data)
            .then(successResponse => {
              let record=new Object();
              record["id"]=0;
              record["weddingId"]=row.id;
              record["approverId"]=data.applicant_id
              record["time"]=new Date();
              record["result"]=2;
              axios.post("/weddingrecord/add",record)
                .then(successResponse=>{
                  this.$alert("操作成功", '提示', {
                    confirmButtonText: '确定',
                  });
                })
                .catch(failResponse=>{
                  this.$alert("操作失败，请刷新页面重试", '提示', {
                    confirmButtonText: '确定',
                  });
                })
        })
            .catch(failResponse => {
              this.$alert("操作失败，请刷新页面重试", '提示', {
                confirmButtonText: '确定',
              });
            }); //失败后的操作

        }

    }

    }
</script>

<style scoped>

</style>
