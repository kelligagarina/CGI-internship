<template>
  <div class = "container">
          <div class = "pealkiri">
            <h1>Soovitused vaatamisajaloo järgi:</h1>
          </div>
          <div class = "kontent">
          <div class = "soovitused">
              <div class = "film" v-for = "film in soovitatudFilmid" v-bind:key = "film.id">
                  <div class="film-info">
                  <h4> Meeldib Teile tõenäosusega {{film.soovitus}}%</h4>

                  <h3 class = "filmiPealkiri">{{ film.pealkiri }}</h3>
                  <p class = "kellaeg">{{ film.kellaeg }}</p>

                  <p class = "üksikasjad">
                  <span class = "info"><b>Žanr: </b>{{ film.žanr }}</span>
                  <span class = "info"><b>Formaat: </b>{{ film.formaat }}</span>
                  <span class = "info"><b>Keel: </b>{{ film.keel }}</span>
                  <span class = "info"><b>Subtiitrid: </b>{{ film.subtiitrid }}</span>
                  <span class = "info"><b>Vanusepiirang: </b>{{ film.vanusepiirang }}+</span>

                  </p>
                  <button @click="toggleSaal" class="vali-btn">Vali film</button>
              </div>
              </div>
               </div>
                  </div>
          </div>
</template>

<script>
import SoovitusService from '../services/SoovitusService'

export default {
  data() {
    return {
      name: 'soovitatudFilmid',
      soovitatudFilmid: []
    };
  },
  methods: {
    getSoovitused(){
        return SoovitusService.getSoovitused().then((response) =>{
            this.soovitatudFilmid = response.data;
             });
           },
    toggleSaal() {
       this.$emit('toggle-istekohad');
            }

  },
  mounted() {
      this.getSoovitused();
    }
}
</script>

<style>
.kontent{
    margin: auto;
}
.soovitused{
    margin: auto;
    width: 100%;
    padding-top: 5%;
}


</style>