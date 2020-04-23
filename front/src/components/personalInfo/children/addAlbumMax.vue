<template>
  <div></div>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {},
  created() {
    let app = this;
    app.$emit("getIndex", "/personalInfo/addAlbumMax");
  }
};
</script>

<style scoped></style>
