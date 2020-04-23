<template>
  <div></div>
</template>

<script>
export default {
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/addAlbumMax");
  }
};
</script>

<style scoped></style>
