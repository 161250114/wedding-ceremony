<template>
  <div>
    
  </div>
</template>

<script>
export default {created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/charge");
  }};
</script>

<style scoped></style>
