<template>
   <div id = "app">
    <Kinokava  v-if="!showRecommendations && !showKinosaal" @toggle-recommendations="toggleSoovitus" @toggle-istekohad="toggleSaal" />
    <SooviTused v-if="showRecommendations"  @toggle-istekohad="toggleSaal"/>
    <IstekohtadeSoovitused v-if="showKinosaal"/>
   </div>
</template>

<script>

import Kinokava from './components/Kinokava.vue'
import SooviTused from './components/SooviTused.vue'
import IstekohtadeSoovitused from './components/IstekohtadeSoovitused.vue'

export default {
  name: 'App',
  components: {
    Kinokava,
    SooviTused,
    IstekohtadeSoovitused
  },
   data() {
      return {
        showRecommendations: false,
        showKinokava: true,
        showKinosaal: false
      };
    },
    methods: {
      toggleSoovitus() {
        this.showRecommendations = !this.showRecommendations;
        this.showKinokava = !this.showKinokava;
      },
      toggleSaal() {
          this.showKinosaal = !this.showKinosaal;
          this.showKinokava = false;
          this.showRecommendations = false;
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
