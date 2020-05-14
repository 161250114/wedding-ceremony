<template>
  <div style="text-align: center">
    <div style="width: 100%;height: 100px">
      <el-input style="width: 200px;margin:20px"v-model="input1" placeholder="按id查询"></el-input>
      <el-input style="width: 200px;margin:20px"v-model="input2" placeholder="按婚宴id查询"></el-input>
      <el-input style="width: 200px;margin:20px"v-model="input3" placeholder="按审批人id查询"></el-input>
      <el-button type="primary" @click="search(input1,input2,input3)">搜索</el-button>
      <el-button type="primary" @click="showAll()">显示全部</el-button>
      </div>
    <el-table
      :data="tableData"
      height="400"
      border
      style="width: 100%">
      <el-table-column
        prop="id"
        label="id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="weddingId"
        label="婚宴id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="approverId"
        label="审批人id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="time"
        label="时间">
      </el-table-column>
      <el-table-column
        prop="result"
        label="具体结果">
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-input v-model="resultinput[scope.row.id]"></el-input>
          <el-button @click="updateResult(scope.row)" type="text" size="small">更新结果</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: "weddingrecord",
    data() {
      return {
        resultinput:[],
        input1:"",
        input2:"",
        input3:"",
        id:-1,
        storage:[],
        tableData: [],
      }
    },
    created(){
      let app=this
      /*axios.get("/getCurrentUser")
        .then(function(res) {
          if(res.data.result==false){
            app.$router.push({
              path: './login',
            })
          }
          app.id=res.data.message.userid
          app.load();
        })
        .catch(function (err) {
          console.log(err);
        })
        */
      app.load()
    },
    methods: {
      showAll(){
        this.tableData=JSON.parse(JSON.stringify(this.storage))
      },
      handleClick(row){
        console.log(row.id)
      },
      load(){
        let app=this
        let id=app.id
        axios.get('/weddingrecord/getAll')
          .then(function(res){
            app.tableData=res.data
            app.storage=JSON.parse(JSON.stringify(res.data))
            for(let i=0;i<res.data.length;i++){
              app.resultinput.splice(i,0,"");
            }
          })
          .catch(function(err){
            console.log(err);
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
      search(input1,input2,input3){
        let app=this
        let table=this.storage
        let result=new Array();
        for(let i=0;i<table.length;i++){
          let data=table[i];
          let hasId=input1==""?true:(data.id+"").indexOf(input1)>=0;
          let hasWeddingId=input2==""?true:(data.weddingId+"").indexOf(input2)>=0;
          let hasApproverId=input3==""?true:(data.approverId+"").indexOf(input3)>=0;
          if(hasId&&hasWeddingId&&hasApproverId){
            result.push(data);
          }
        }
        this.tableData=result
      },
      updateResult(row){
        let app=this;
        let newObject=JSON.parse(JSON.stringify(app.storage[row.id]));
        newObject.result=app.resultinput[row.id];
        console.log(newObject)
        app.tableData.splice(row.id,1,newObject)
        axios.post("/weddingrecord/update",newObject)
          .then(successResponse=>{
            app.$alert("操作成功", '提示', {
              confirmButtonText: '确定',
            });
          })
          .catch(failResponse=>{
            app.$alert("操作失败，请刷新页面重试", '提示', {
              confirmButtonText: '确定',
            });
          })
      }
    }

  }
</script>

<style scoped>

</style>
