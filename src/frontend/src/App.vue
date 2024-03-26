<template>
   <div id = "app">
    <Kinokava />
    <SooviTused />
   </div>
</template>

<script>

import Kinokava from './components/Kinokava.vue'
import SooviTused from './components/SooviTused.vue'

export default {
  name: 'App',
  components: {
    Kinokava,
    SooviTused
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
