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
   <!--  <div class = "saaliplaan2">
         <div class = "rida2" v-for = "rida2 in soovitused" v-bind:key = "rida2" >
            <div class = "koht2" v-for = "koht2 in rida2" v-bind:key = "koht2">
               <p>{{koht2}}</p>
            </div>
         </div>
      </div>--> 
      <div class="pileteid">
         <h2>Mitu piletit soovid osta?</h2>
         <form>
            <label for="arv">Piletite arv:</label>
            <input v-model="piletiteArv" type="number" id="vanus" min="0" placeholder="Sisesta piletite arv." @input="getKinosaalSoovitused(piletiteArv)">
         </form>
      </div>
   </div>
</div>
</template>

<script>

import KinosaalService from '../services/KinosaalService'

export default {
  data() {
    return {

      isteKohad: [],
      piletiteArv : 2,
      soovitused: []

    };
  },
  methods: {
    getKinosaal(){
        return KinosaalService.getKinosaal().then((response) =>{
            this.isteKohad = response.data;
             });
          },
          //getKinosaalSoovitused(piletiteArv){
        //return KinosaalService.getKinosaalSoovitused(piletiteArv).then((response) =>{
         //           this.soovitused = response.data;
         //            });
    //}



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
.pileteid {
     height: 20%;
     float: left;
     padding: 20px;
     border: 1px solid #ccc;
     border-radius: 20px;
             box-shadow:10px 10px 2px lightblue;
 }


</style>