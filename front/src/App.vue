<template>
  <div
    style="display:flex;flex-direction:column;justify-content:space-between;height:100%;"
  >
    <div>
      <Myhead v-bind:isLogin="isLogin" @quit="changeLogin"></Myhead>
    </div>
    <div id="app">
      <router-view @login="changeLogin" />
    </div>
    <div>
      <Myfooter></Myfooter>
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import Myhead from './components/head/head'
import Myfooter from './components/footer/footer'
import axios from 'axios'

Vue.prototype.$axios = axios
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8'
axios.defaults.baseURL = '/api'

export default {
  name: 'App',
  components: {
    Myhead,
    Myfooter
  },
  data () {
    return {
      isLogin: false
    }
  },
  methods: {
    changeLogin (isLogin) {
      this.isLogin = isLogin
      console.log(this.isLogin)
    }
  },
  created () {
    console.log('OK')
  }
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  height:100%;
}

html, body {
  height: 95%;
  margin: 0;
  padding: 0;
}
</style>
