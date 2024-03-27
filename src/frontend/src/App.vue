<template>
   <div id = "app">
    <Kinokava  v-if="!showRecommendations" @toggle-recommendations="toggleSoovitus" />
    <SooviTused v-if="showRecommendations" />
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
  },
   data() {
      return {
        showRecommendations: false,
        showKinokava: true
      };
    },
    methods: {
      toggleSoovitus() {
        this.showRecommendations = !this.showRecommendations;
        this.showKinokava = !this.showKinokava;
      }
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
