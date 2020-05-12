<template>
  <div class="info_div">
    <el-form label-width="100px">
      <el-row>
        <el-col :span="18">
          <el-form-item label="标签:">
            <div>
              <el-tag v-for="tag in newUserInfo.tagList" :key="tag">{{
                tag
              }}</el-tag>
              <el-divider></el-divider>
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-button @click="tagDialog = true">选择标签</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="18">
          <el-form-item label="兴趣爱好:">
            <label>{{ questionMessage }}</label>
            <el-divider></el-divider>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-button @click="questionDialog = true">添加兴趣爱好</el-button>
        </el-col>
      </el-row>
      <el-form-item label="自我介绍：">
        <el-input
          type="textarea"
          v-model="newUserInfo.introduction"
          :autosize="{ minRows: 10, maxRows: 10 }"
        ></el-input>
      </el-form-item>
    </el-form>
    <el-alert
      title="设置最合适的标签，有助于更快的找到心仪的另一半哦~~~"
      type="success"
      :closable="false"
    >
    </el-alert>
    <p></p>
    <div>
      <el-button type="primary" @click="changeIntroduction">保存</el-button>
      <el-button type="primary" @click="redo">重置</el-button>
    </div>
    <el-dialog
      title="性格选择"
      :visible.sync="tagDialog"
      width="50%"
      :before-close="handleClose"
    >
      <div>
        <div>
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="float: left;">你的性格：</span>
              <el-tag
                v-for="tag in tagChosedList"
                :key="tag.label"
                :type="tag.color"
                closable
                @close="deleteTag(tag)"
                >{{ tag.label }}</el-tag
              >
              <el-button
                style="float: right; padding: 3px 0;"
                type="text"
                @click="changeTagList"
                >换一批</el-button
              >
            </div>
            <div class="text item">
              <el-row>
                <el-col :span="3">&nbsp;</el-col>
                <el-col :span="18"
                  ><el-tag
                    :type="tag.color"
                    v-for="tag in tagList"
                    :key="tag.label"
                    @click="chooseTag(tag)"
                    >{{ tag.label }}</el-tag
                  >
                </el-col>
                <el-col :span="3">&nbsp;</el-col>
              </el-row>
            </div>
          </el-card>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button style="margin-top: 12px;" @click="tagDialog = false"
          >取消</el-button
        >
        <el-button style="margin-top: 12px;" @click="saveTagList"
          >保存</el-button
        >
      </span>
    </el-dialog>
    <el-dialog
      title="兴趣爱好选择"
      :visible.sync="questionDialog"
      width="50%"
      :before-close="handleClose"
    >
      <div>
        <div>
          <el-row>
            <el-col :span="3">&nbsp;</el-col>
            <el-col :span="18">
              <el-form label-width="100px" :inline="true">
                <div v-for="index in questionNumber" :key="index">
                  <el-form-item>
                    <label>问题{{ index }}</label>
                    <el-select v-model="questionList[index - 1]" placeholder="">
                      <el-option
                        v-for="question in questionAllList"
                        :key="question"
                        :value="question"
                        :disabled="questionList.indexOf(question) != -1"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item>
                    <el-input
                      placeholder="此处输入答案"
                      v-model="answerList[index - 1]"
                    ></el-input>
                  </el-form-item>
                </div>
                <el-button
                  type="primary"
                  icon="el-icon-plus"
                  @click="addQuestion(1)"
                  circle
                ></el-button>
                <el-button
                  type="primary"
                  icon="el-icon-minus"
                  @click="addQuestion(-1)"
                  circle
                ></el-button>
              </el-form>
            </el-col>
          </el-row>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button style="margin-top: 12px;" @click="questionDialog = false"
          >取消</el-button
        >
        <el-button style="margin-top: 12px;" @click="saveQuestionList"
          >保存</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Axios from "axios";

export default {
  data() {
    return {
      userInfo: {
        tagList: ["牛逼", "啊", "马飞"],
        questionList: [],
        introduction: "我是一只皮皮！",
      },
      newUserInfo: {
        tagList: ["牛逼", "啊", "马飞"],
        questionList: [],
        introduction: "我是一只皮皮！",
      },
      tagDialog: false,
      questionDialog: false,
      tagAllList: [
        "开朗",
        "大方",
        "主动",
        "外向",
        "俏皮",
        "敏捷",
        "乐观",
        "调皮",
        "爽脆",
        "爽朗",
        "豪爽",
        "正直",
        "直率",
        "直爽",
        "干脆",
        "直言",
        "爽直",
        "刚直",
        "憨直",
        "率直",
        "耿直",
        "公正",
        "公道",
        "公平",
        "公允",
        "正派",
        "爽快",
        "简捷",
        "开阔",
        "豁达",
        "明朗",
        "率真",
        "真诚",
        "热诚",
        "至诚",
        "赤诚",
        "诚挚",
        "恳切",
        "纯真",
        "率直",
        "坦率",
        "笃实",
        "热忱",
        "热诚",
        "热心",
        "好客",
        "客气",
        "殷勤",
        "和气",
        "和蔼",
        "和善",
        "和婉",
        "和悦",
        "和易",
        "亲切",
        "过谦",
        "谦卑",
        "谦恭",
        "谦和",
        "谦让",
        "谦虚",
        "谦逊",
        "虚心",
        "自谦",
        "强硬",
        "强悍",
        "强劲",
        "坚决",
        "坚信",
        "坚定",
        "坚韧",
        "坚实",
        "坚毅",
        "坚贞",
        "中坚",
        "毅力",
        "骠悍",
        "勇敢",
        "勇猛",
        "刚毅",
        "骠悍",
        "决断",
        "果敢",
        "果决",
        "坚毅",
        "坚强",
        "坚忍",
        "决然",
        "毅然",
        "定弦",
        "断然",
        "泼辣",
        "断腕",
        "断行",
        "决意",
        "决计",
        "主意",
        "作意",
        "锐意",
        "发誓",
        "干脆",
        "爽快",
        "果断",
      ],
      tagColor: ["", "success", "warning", "danger"],
      tagList: [],
      tagChosedList: [],
      questionAllList: [
        "1、最喜欢的游戏是",
        "2、最喜欢的运动是",
        "3、最喜欢的明星是",
        "4、最喜欢的食物是",
        "5、最喜欢的歌曲是",
        "6、最喜欢的书是",
      ],
      questionList: ["", "", "", "", "", ""],
      questionNumber: 1,
      answerList: ["", "", "", "", "", ""],
      questionMessage: "",
    };
  },
  methods: {
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    changeTagList() {
      let app = this;
      app.tagList = [];
      while (app.tagList.length < 52) {
        let tempIndex = Math.floor(Math.random() * app.tagAllList.length);
        let hasExisted = false;
        for (let i = 0; i < app.tagList.length; i++) {
          if (app.tagAllList[tempIndex] == app.tagList[i].label) {
            hasExisted = true;
            break;
          }
        }
        if (!hasExisted) {
          app.tagList.push({
            label: app.tagAllList[tempIndex],
            color: app.tagColor[Math.floor(Math.random() * 4)],
          });
        }
      }
    },
    chooseTag(tag) {
      let app = this;
      let hasExisted = false;
      if (app.tagChosedList.length == 10) {
        app.$message({
          message: "标签的数量不能多于10个！",
          type: "warning",
        });
        return;
      }
      for (let i = 0; i < app.tagChosedList.length; i++) {
        if (app.tagChosedList[i].label == tag.label) {
          app.$message({
            message: "该标签已存在！请勿重复选择！",
            type: "warning",
          });
          hasExisted = true;
        }
      }
      if (!hasExisted) app.tagChosedList.push(tag);
    },
    deleteTag(tag) {
      let app = this;
      for (let i = 0; i < app.tagChosedList.length; i++) {
        if (app.tagChosedList[i].label == tag.label) {
          app.tagChosedList.splice(i, 1);
        }
      }
      console.log(app.tagChosedList);
    },
    addQuestion(num) {
      let app = this;
      if (app.questionNumber == 6 && num == 1) {
        app.$message({
          message: "最多只能设置六个问题哦",
          type: "warning",
        });
        return;
      }
      if (app.questionNumber == 1 && num == -1) {
        app.$message({
          message: "至少设置一个问题哦",
          type: "warning",
        });
        return;
      }
      if (num == -1) {
        app.questionList.splice(app.questionNumber - 1, 1, "");
        console.log(app.questionList);
      }
      app.questionNumber += num;
    },
    saveTagList() {
      let app = this;
      app.newUserInfo.tagList.splice(0, app.newUserInfo.tagList.length);
      for (let i = 0; i < app.tagChosedList.length; i++) {
        app.newUserInfo.tagList.push(app.tagChosedList[i].label);
      }
      app.tagDialog = false;
    },
    saveQuestionList() {
      let app = this;
      app.newUserInfo.questionList.splice(
        0,
        app.newUserInfo.questionList.length
      );
      for (let i = 0; i < app.questionList.length; i++) {
        if (app.questionList[i] == "") {
          break;
        } else {
          for (let j = 0; j < app.questionAllList.length; j++) {
            if (app.questionAllList[j] == app.questionList[i]) {
              app.newUserInfo.questionList.push({
                questionid: j,
                answer: app.answerList[i],
              });
            }
          }
        }
      }
      app.questionDialog = false;
      app.questionMessage = "当前已选问题";
      for (let i = 0; i < app.newUserInfo.questionList.length; i++) {
        app.questionMessage +=
          app.newUserInfo.questionList[i].questionid + 1 + "  ";
      }
      console.log(app.questionMessage);
    },
    redo() {
      let app = this;
      Axios.get("/userInfo/getIntroduction")
        .then(function (res) {
          console.log(res);
          if (res.data.result) {
            app.newUserInfo = JSON.parse(JSON.stringify(res.data.message));
            app.userInfo = JSON.parse(JSON.stringify(res.data.message));
            app.questionNumber = res.data.message.questionList.length;
            for (let i = 0; i < app.userInfo.tagList.length; i++) {
              app.tagChosedList.push({
                label: app.userInfo.tagList[i],
                color: "",
              });
            }
            for (let i = 0; i < res.data.message.questionList.length; i++) {
              app.questionList[i] =
                app.questionAllList[app.newUserInfo.questionList[i].questionid];
              app.answerList[i] = app.newUserInfo.questionList[i].answer;
            }
            console.log(app.questionList, app.answerList);
            app.saveQuestionList();
          }
        })
        .catch(function (error) {});
    },
    changeIntroduction() {
      let app = this;
      Axios.post("/userInfo/changeIntroduction", app.newUserInfo)
        .then(function (res) {
          if (res.data.result) {
            app.$message({
              message: "修改成功！",
              type: "success",
            });
          }
        })
        .catch(function (error) {});
    },
  },
  created() {
    let app = this;
    Axios.get("../../../static/infoList.json").then(function (res) {
      app.tagAllList = res.data.tagList;
      app.questionAllList = res.data.questionList;
      app.changeTagList();
    });
    app.redo();
    app.$emit("getIndex", "/personalInfo/introduction");
  },
};
</script>

<style scoped>
.info_div {
  margin-top: 30px;
  width: 500px;
  margin-left: 100px;
}
</style>
