<template>
  <div class = "container">
   <div class = "pealkiri">
      <h1>Soovitused vaatamisajaloo järgi:</h1>
   </div>
   <div class = "kontent">
      <div class = "soovitused">
         <div class = "film" v-for = "film in soovitatudFilmid" v-bind:key = "film.id">
            <div class="film-info">
               <div class = "film-detailid">
               <h4> Meeldib Teile tõenäosusega {{film.soovitus}}%</h4>
               <h3 class = "kellaeg">{{ paremKellaeg(film.kellaeg) }}</h3>
               <h3 class = "filmiPealkiri">{{ film.pealkiri }}</h3>
               <p class = "kellaeg">{{ paremKuupäev(film.kellaeg) }}</p>
               <p class = "üksikasjad">
                  <span class = "info"><b>Žanr: </b>{{ film.žanr }}</span>
                  <span class = "info"><b>Formaat: </b>{{ film.formaat }}</span>
                  <span class = "info"><b>Keel: </b>{{ film.keel }}</span>
                  <span class = "info"><b>Subtiitrid: </b>{{ film.subtiitrid }}</span>
                  <span class = "info"><b>Vanusepiirang: </b>{{ film.vanusepiirang }}+</span>
               </p>
               </div>
               <button @click="LiiguSaali" class="vali-btn">Vali film</button>
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
    LiiguSaali() {
      this.$router.push("/api/kinosaal");
      },
    paremKellaeg(kellaeg){
                return kellaeg.split("T")[1].substring(0,5);
      },
    paremKuupäev(kellaeg){
                const aastaOsad = kellaeg.split("T")[0].split("-");
                return aastaOsad[2] + "." + aastaOsad[1] + "." + aastaOsad[0];
      },
        

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