<template>
  <div class="info_div">
    <el-form label-position="left" label-width="100px">
      <el-form-item label="用户ID:"
        ><el-input disabled v-model="userInfo.id"></el-input
      ></el-form-item>
      <el-form-item label="用户名:"
        ><el-input disabled v-model="userInfo.username"></el-input
      ></el-form-item>
      <el-form-item label="账户余额:"
        ><el-input disabled v-model="userInfo.balance"></el-input
      ></el-form-item>
      <el-form-item label="会员有效期至:"
        ><el-input disabled v-model="userInfo.vip_enddate"></el-input
      ></el-form-item>
      <el-form-item label="购买会员天数:">
        <el-radio-group v-model="chargeVipDays">
          <el-radio-button label="30">30天</el-radio-button>
          <el-radio-button label="90">90天</el-radio-button>
          <el-radio-button label="365">365天</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="需付金额:">
        <b v-if="chargeVipDays == '30'">10¥</b>
        <b v-if="chargeVipDays == '90'">30¥</b>
        <b v-if="chargeVipDays == '365'">108¥</b>
        <label
          v-if="chargeVipDays == '365'"
          style="text-decoration:line-through;font-style: italic"
          >120¥</label
        >
      </el-form-item>
      <el-alert type="warning" :closable="false" title="付费会员特权："
        ><p>1、可以查看详细的用户资料</p>
        <p>2、付费会员享有尊贵标识&nbsp;&nbsp;&nbsp;</p><p>3、更多豪华功能正在开发中</p></el-alert
      >
      <p></p>
    </el-form>
    <el-button type="primary">购买</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userInfo: {
        id: "1000001",
        username: "pikaqiu",
        balance: 50,
        vip_enddate: "当前不是会员"
      },
      chargeVipDays: "30"
    };
  },
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/beVIP");
  }
};
</script>

<style scoped>
.info_div {
  margin-top: 30px;
  width: 400px;
  margin-left: 100px;
}
</style>
