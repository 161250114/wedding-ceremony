<template>
  <div></div>
</template>

<script>
export default {
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/beVIP");
  }
};
</script>

<style scoped></style>
