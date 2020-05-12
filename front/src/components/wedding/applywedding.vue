<template>
  <div class="whole">
    <el-row style="height:60px">
      <el-col :span="1">&nbsp;</el-col>
    </el-row>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="18">
        <el-steps :active="active" finish-status="success">
          <el-step title="个人信息填写"></el-step>
          <el-step title="婚宴要求填写"></el-step>
          <el-step title="完成申请"></el-step>
        </el-steps>
      </el-col>
    </el-row>
    <el-row style="height:60px">
      <el-col :span="1">&nbsp;</el-col>
    </el-row>
    <el-row>
      <el-col :span="3">&nbsp;</el-col>
      <el-col :span="8">
        <div class="block">
          <el-carousel height="400px">
            <el-carousel-item v-for="item in 4" :key="item">
              <img :src="picUrls[item - 1]" style="width:100%;height:100%" />
            </el-carousel-item>
          </el-carousel>
        </div>
      </el-col>
      <el-col :span="2">&nbsp;</el-col>
      <el-col :span="9">
        <el-form
          label-position="left"
          label-width="80px"
          v-if="this.active == 0"
          :model="wedding"
          :rules="rules1"
          ref="ruleForm1"
        >
          <el-row>
            <el-col :span="15"
            ><el-form-item label="姓名:" prop="name">
              <el-input prefix-icon="el-icon-user"  v-model="wedding.name"></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
            ><el-form-item label="电话:" prop="phone">
              <el-input
                prefix-icon="el-icon-phone"
                v-model="wedding.phone"
              ></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
            ><el-form-item label="邮箱:" prop="email">
              <el-input
                prefix-icon="el-icon-postcard"
                v-model="wedding.email"
              ></el-input> </el-form-item
            ></el-col>
          </el-row>
        </el-form>
        <el-form
          label-position="left"
          label-width="80px"
          v-else-if="this.active == 1"
          refs="ruleForm2"
          :rules="rules1"
        >
          <el-row>
            <el-col :span="15"
            ><el-form-item label="总人数:" prop="total">
              <el-input
                v-model="wedding.total"
              ></el-input>
            </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
            ><el-form-item label="时间范围:">
              <el-input
                type="date"
                placeholder="开始时间"
                suffix-icon="el-icon-date"
                v-model="wedding.start">
              </el-input>
              <el-input
                type="date"
                placeholder="结束时间"
                suffix-icon="el-icon-date"
                v-model="wedding.end">
              </el-input>
            </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
            ><el-form-item label="位置要求:" prop="location">
              <el-input
                type="textarea"
                placeholder="请输入内容"
                v-model="wedding.location"
                maxlength="50"
                show-word-limit
              >
              </el-input>
            </el-form-item>
            </el-col>
          </el-row>

        </el-form>
        <el-form
          label-position="left"
          label-width="80px"
          v-else-if="this.active == 2"
          refs="ruleForm3"
          :rules="rules1"
        >
          <el-row>
            <el-col :span="15"
            ><el-form-item label="细节要求:" prop="detail">
              <el-input
                type="textarea"
                placeholder="请输入内容"
                v-model="wedding.detail"
                maxlength="250"
                show-word-limit
              >
              </el-input>
            </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-row>
          <el-col :span="15">
            <el-button v-if="this.active!=0" style="margin-top: 12px;" @click="changeStep(-1)"
            >上一步</el-button
            >
            <el-button v-if="this.active!=2" style="margin-top: 12px;" @click="changeStep(1)"
            >下一步</el-button
            >
            <el-button v-if="this.active==2" style="margin-top: 12px;" @click="submit()"
            >提交</el-button
            >
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import axios from 'axios'
    export default {
      name: "applywedding",
      data(){
        var checkPhone = (rule, value, callback) => {
          if (value == "") {
            return callback(new Error("不能为空"));
          }
          setTimeout(() => {
            var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
            if (!myreg.test(value)) {
              callback(new Error("请输入正确手机号码"));
            } else if (value.length != 11) {
              callback(new Error("手机号长度错误"));
            } else {
              callback();
            }
          }, 1000);
        };
        var checkEmail = (rule, value, callback) => {
          if (value == "") {
            return callback(new Error("邮箱不能为空"));
          }
          setTimeout(() => {
            var myreg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
            if (!myreg.test(value)) {
              callback(new Error("请输入正确邮箱"));
            } else {
              callback();
            }
          }, 1000);
        };
        var checkNotNull = (rule, value, callback) => {
          if (value == "") {
            return callback(new Error("不能为空"));
          }
          callback();
        };
        var checkTotal = (rule, value, callback) => {
          if (value == ""||value==" ") {
            return callback(new Error("人数不能为空"));
          }
          callback();
        };
        return {
          active: 0,
          validateButton: {
            active: true,
            message: "发送验证码",
            leftSeconds: 0,
            maxSeconds: 10
          },
          validateTimer: null,
          rules1: {
            phone: [{ validator: checkPhone, trigger: "blur" }],
            location: [{ validator: checkNotNull, trigger: "blur" }],
            detail:[{ validator: checkNotNull, trigger: "blur" }],
            name:[{ validator: checkNotNull, trigger: "blur" }],
            email:[{ validator: checkEmail, trigger: "blur" }],
            total:[{ validator: checkTotal, trigger: "blur" }],
          },
          picUrls: [
            "../../../static/registerPic1.png",
            "../../../static/registerPic2.jpeg",
            "../../../static/registerPic3.png",
            "../../../static/registerPic4.jpeg"
          ],
          wedding: {
            id:-1,
            applicantId:1,
            name:"",
            phone:"",
            email:"",
            total:"",
            start:"",
            end:"",
            location:"",
            detail:"",
            state:0
          },
        }
      },
      created(){
        let app=this;
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
      },
      methods:{

        changeStep(index) {
          let app = this;
          if (app.active == 0 && index == 1) {
            this.$refs["ruleForm1"].validate((valid) => {
              if (valid) {
                app.active = app.active + index;
              } else {
                app.$message({
                  message: "请正确填写！",
                  type: "warning",
                });
              }
            });
          }
          else if (app.active == 0 && index == -1) {
            app.$message({
              message: "已回到第一步！",
              type: "warning",
            });
          }
          else{
            app.active+=index
          }

        },
        submit(){
          let app=this
          axios.get("/getCurrentUser")
            .then(function(res){
              let message=res.data.message
              let id=message.userid;
              app.wedding.applicantId=id
              axios.post("/wedding/add",app.wedding)
                .then(successResponse => {
                  app.$alert("申请成功", '提示', {
                    confirmButtonText: '确定',
                  });
                })
                .catch(failResponse => {
                  app.$alert("操作失败，请刷新页面重试", '提示', {
                    confirmButtonText: '确定',
                  });
                }); //失败后的操作
            })
            .catch(function(err){
              console.log(err);
            });

        }
      }
    }
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }
</style>
