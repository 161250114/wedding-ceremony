<template>
  <div
    style="display:flex;flex-direction:column;justify-content:space-between;min-height:100%;border:1px solid;position:absolute;width:100%"
  >
    <div id="app">
      <Myhead></Myhead>
      <router-view/>
    </div>
    <div id="footer">
      <Myfooter></Myfooter>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import Myhead from "./components/head/head";
import Myfooter from "./components/footer/footer";
import axios from "axios";

Vue.prototype.$axios = axios;
axios.defaults.headers.post["Content-Type"] = "application/json;charset=utf-8";
axios.defaults.baseURL = "/api";

export default {
  name: "App",
  components: {
    Myhead,
    Myfooter
  },
  data() {
    return {
    };
  },
  methods: {

  },
  created() {
    console.log("OK");
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
}

html,
body {
  height: 100%;
  margin: 0;
  padding: 0;
}

</style>
