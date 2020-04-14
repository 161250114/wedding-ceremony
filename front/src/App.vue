<template>
  <div>
    <Myhead></Myhead>
    <div id="app">
      <router-view/>
    </div>
    <Myfooter></Myfooter>
  </div>
</template>

<script>
  import Vue from 'vue'
import Myhead from "./components/head/head"
import Myfooter from "./components/footer/footer"
import axios from 'axios'
Vue.prototype.$axios = axios
axios.defaults.baseURL = '/api'

export default {
  name: 'App',
  components:{
    Myhead,Myfooter
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

</style>
