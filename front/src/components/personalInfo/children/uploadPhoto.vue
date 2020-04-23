<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="单张上传" name="first">单张上传</el-tab-pane>
      <el-tab-pane label="批量上传" name="second">批量上传</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {};
</script>
<style scoped></style>
