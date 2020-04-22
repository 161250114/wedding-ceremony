<template>
  <div class="info_div">
    <el-form label-width="100px">
      <el-form-item label="自我介绍：">
        <el-input
          type="textarea"
          v-model="introduction"
          :autosize="{ minRows: 10, maxRows: 10 }"
        ></el-input>
      </el-form-item>
    </el-form>
    <el-alert
      title="如果不知道写些什么，可以点击下方”使用模板“哦~~"
      type="success"
      :closable="false"
    >
    </el-alert>
    <p></p>
    <div>
      <el-button type="primary">保存</el-button>
      <el-button type="primary">重置</el-button>
      <el-button type="warning" @click="dialogVisible = true"
        >使用模板</el-button
      >
    </div>
    <el-dialog
      title="模板创作"
      :visible.sync="dialogVisible"
      width="50%"
      :before-close="handleClose"
    >
      <div>
        <el-steps :active="active" finish-status="success">
          <el-step title="性格"></el-step>
          <el-step title="兴趣爱好"></el-step>
          <el-step title="对TA想说"></el-step>
          <el-step title="成品展示"></el-step>
        </el-steps>
        <div v-if="active == 0">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span style="float:left">你的性格：</span>
              <el-tag
                v-for="tag in tagChosedList"
                :key="tag.label"
                :type="tag.color"
                closable
                @close="deleteTag(tag)"
                >{{ tag.label }}</el-tag
              >
              <el-button
                style="float: right; padding: 3px 0"
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
        <div v-if="active == 1">
          <p></p>
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
        <div v-if="active == 2">
          <el-row>
            <el-col :span="3">&nbsp;</el-col>
            <el-col :span="18">
              <el-form>
                <el-form-item>
                  <el-input
                    type="textarea"
                    v-model="someWords"
                    :autosize="{ minRows: 10, maxRows: 10 }"
                    placeholder="在这里写下对TA想说的话吧！！"
                  ></el-input>
                </el-form-item> </el-form
            ></el-col>
          </el-row>
        </div>
        <div v-if="active == 3">
          <el-row>
            <el-col :span="3">&nbsp;</el-col>
            <el-col :span="18">
              <el-form label-width="80px">
                <el-form-item label="成品：">
                  <el-input
                    type="textarea"
                    v-model="newIntroduction"
                    :autosize="{ minRows: 10, maxRows: 10 }"
                  ></el-input>
                </el-form-item> </el-form
            ></el-col>
          </el-row>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button style="margin-top: 12px;" @click="changeStep(-1)"
          >上一步</el-button
        >
        <el-button
          style="margin-top: 12px;"
          @click="changeStep(1)"
          v-show="this.active == 0 || this.active == 1"
          >下一步</el-button
        >
        <el-button
          style="margin-top: 12px;"
          v-show="active == 2"
          @click="changeStep(1)"
          >完成</el-button
        >
        <el-button style="margin-top: 12px;" v-show="active == 3" @click="save"
          >保存</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      introduction: "我是一只皮皮！",
      dialogVisible: false,
      active: 0,
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
        "果断"
      ],
      tagColor: ["", "success", "warning", "danger"],
      tagList: [],
      tagChosedList: [],
      questionAllList: [
        "最喜欢的游戏是",
        "最喜欢的运动是",
        "最喜欢的明星是",
        "最喜欢的食物是",
        "最喜欢的歌曲是",
        "最喜欢的书是"
      ],
      questionList: ["", "", "", "", ""],
      questionNumber: 1,
      answerList: ["", "", "", "", ""],
      someWords: "",
      newIntroduction: ""
    };
  },
  methods: {
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    changeStep(index) {
      let app = this;
      if (app.active == 0 && index == -1) {
        app.$message({
          message: "已回到第一步！",
          type: "warning"
        });
        return;
      }
      if (app.active == 0 && index == 1 && !app.step1_examine()) {
        return;
      }
      if (app.active == 1 && index == 1 && !app.step2_examine()) {
        return;
      }
      if (app.active == 2 && index == 1) {
        app.getNewIntroduction();
      }
      app.active = app.active + index;
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
            color: app.tagColor[Math.floor(Math.random() * 4)]
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
          type: "warning"
        });
        return;
      }
      for (let i = 0; i < app.tagChosedList.length; i++) {
        if (app.tagChosedList[i].label == tag.label) {
          app.$message({
            message: "该标签已存在！请勿重复选择！",
            type: "warning"
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
      if (app.questionNumber == 5 && num == 1) {
        app.$message({
          message: "最多只能设置五个问题哦",
          type: "warning"
        });
        return;
      }
      if (app.questionNumber == 1 && num == -1) {
        app.$message({
          message: "至少设置一个问题哦",
          type: "warning"
        });
        return;
      }
      if (num == -1) {
        app.questionList.splice(app.questionNumber - 1, 1, "");
        console.log(app.questionList);
      }
      app.questionNumber += num;
    },
    step1_examine() {
      let app = this;
      if (app.tagChosedList.length < 3) {
        app.$message({
          message: "请至少选择3个标签",
          type: "warning"
        });
        return false;
      }
      return true;
    },
    step2_examine() {
      let app = this;
      for (let i = 0; i < app.questionNumber; i++) {
        if (app.questionList[i] == "") {
          app.$message({
            message: "问题" + (i + 1).toString() + "尚未选择！",
            type: "warning"
          });
          return false;
        }
        if (app.answerList[i] == "") {
          app.$message({
            message: "问题" + (i + 1).toString() + "尚未填写答案！",
            type: "warning"
          });
          return false;
        }
      }
      return true;
    },
    getNewIntroduction() {
      let app = this;
      app.newIntroduction = "";
      app.newIntroduction += "我是一个";
      for (let i = 0; i < app.tagChosedList.length; i++) {
        app.newIntroduction += app.tagChosedList[i].label;
        if (i != app.tagChosedList.length - 1) {
          app.newIntroduction += ",";
        }
      }
      app.newIntroduction += "的人。\r\n\r\n";
      for (let i = 0; i < app.questionNumber; i++) {
        app.newIntroduction +=
          "我" + app.questionList[i] + " " + app.answerList[i] + "。\r\n";
      }
      app.newIntroduction += "\r\n";
      app.newIntroduction += "我想对未来的TA说：\r\n" + app.someWords;
    },
    save() {
      let app = this;
      app.introduction = app.newIntroduction;
      app.dialogVisible = false;
      app.$message({
        message: "保存成功",
        type: "success"
      });
    }
  },
  created() {
    let app = this;
    app.changeTagList();
  }
};
</script>

<style scoped>
.info_div {
  margin-top: 30px;
  width: 500px;
  margin-left: 100px;
}
</style>
