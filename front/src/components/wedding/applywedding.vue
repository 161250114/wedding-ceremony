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
        >
          <el-row>
            <el-col :span="15"
            ><el-form-item label="姓名:" v-model="wedding.name">
              <el-input prefix-icon="el-icon-user"></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
            ><el-form-item label="电话:" v-model="wedding.phone">
              <el-input
                prefix-icon="el-icon-phone"
              ></el-input> </el-form-item
            ></el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
            ><el-form-item label="邮箱:" v-model="wedding.email">
              <el-input
                prefix-icon="el-icon-postcard"
              ></el-input> </el-form-item
            ></el-col>
          </el-row>
        </el-form>
        <el-form
          label-position="left"
          label-width="80px"
          v-else-if="this.active == 1"
        >
          <el-row>
            <el-col :span="15"
            ><el-form-item label="总人数:">
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
                placeholder="开始时间"
                suffix-icon="el-icon-date"
                v-model="wedding.start">
              </el-input>
              <el-input
                placeholder="结束时间"
                suffix-icon="el-icon-date"
                v-model="wedding.end">
              </el-input>
            </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="15"
            ><el-form-item label="位置要求:">
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
        >
          <el-row>
            <el-col :span="15"
            ><el-form-item label="细节要求:">
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
            <el-button style="margin-top: 12px;" @click="changeStep(-1)"
            >上一步</el-button
            >
            <el-button style="margin-top: 12px;" @click="changeStep(1)"
            >下一步</el-button
            >
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    export default {
      name: "applywedding",
      data(){
        return {
          active: 0,
          validateButton: {
            active: true,
            message: "发送验证码",
            leftSeconds: 0,
            maxSeconds: 10
          },
          validateTimer: null,
          picUrls: [
            "../../../static/registerPic1.png",
            "../../../static/registerPic2.jpeg",
            "../../../static/registerPic3.png",
            "../../../static/registerPic4.jpeg"
          ],
          wedding: {
            name:"",
            phone:"",
            email:"",
            total:"",
            start:"",
            end:"",
            location:"",
            detail:""
          },
        }
      },
    methods:{
      changeStep(index) {
        let app = this;
        if (app.active == 0 && index == -1) {
          app.$message({
            message: "已回到第一步！",
            type: "warning"
          });
        } else {
          this.active = this.active + index;
        }
      },
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
