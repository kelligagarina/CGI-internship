<template>
  <div class = "container">
          <div class = "pealkiri">
            <h1>Istekohtade valimine:</h1>
          </div>
          <div class = "kontent">
          <div class = "saaliplaan">
              <div class = "rida" v-for = "rida in isteKohad" v-bind:key = "rida" >
                <div class = "koht" v-for = "koht in rida" v-bind:key = "koht" :style="{'background-color': koht === 1 ? 'red' : koht === 0 ? 'green' : 'initial' }">
                  </div>
              </div>
              </div>
               </div>
          </div>
</template>

<script>
import KinosaalService from '../services/KinosaalService'

export default {
  data() {
    return {
      name: 'isteKohad',
      isteKohad: []
    };
  },
  methods: {
    getKinosaal(){
        return KinosaalService.getKinosaal().then((response) =>{
            this.isteKohad = response.data;
             });
           },

  },
  mounted() {
      this.getKinosaal();
    }
}
</script>

<style>
.koht{
    padding: 20px;
    padding-bottom: 35px;
    display: inline-block;
    margin: 10px;
    border-radius: 5px;

}
.istekoht{
    font-size: 25px;
}

</style>