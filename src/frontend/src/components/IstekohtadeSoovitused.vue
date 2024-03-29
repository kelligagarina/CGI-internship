<template>
<div class = "container">
   <div class = "pealkiri">
      <h1>Istekohtade soovitamine</h1>
   </div>
   <div class = "kontent">
      <div class = "saaliplaan">
         <div class = "rida" v-for = "rida in soovitused" v-bind:key = "rida" >
               <div class = "koht" v-for = "koht in rida" v-bind:key = "koht" :style="{'background-color': koht === 1 ? 'red' : koht === 0 ?  'green' : koht === 2 ? 'yellow': 'initial' }">
         </div>
      </div>
    </div>
      <div class="pileteid">
         <h2>Mitu piletit soovid osta?</h2>
         <form @submit.prevent="reserveTickets">
            <label for="arv">Piletite arv:</label>
            <input v-model="piletiteArv" type="number" min="0" placeholder="Vajuta 'Soovita', et näha saali plaani." >
            <button class = 'vali-btn' type="submit">Soovita</button>
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
      piletiteArv : '',
      soovitused: []

    };
  },
  methods: {
    getKinosaal(){
        return KinosaalService.getKinosaal().then((response) =>{
            this.isteKohad = response.data;
             });
          },
   //Antud meetod soovitab kasutajale kohti tema sisetatud piletite arvu järgi.
    reserveTickets() {//Antud meetodi koostamisel kasutasin ChatGPT abi, kuna kogemus JavaScriptiga ei olnud piisav.
      const numTickets = parseInt(this.piletiteArv); 
      this.soovitused = [];

      // Kopeerime algse kinosaali soovituste hulka
      this.soovitused = this.isteKohad.map(row => [...row]);

      const parimRida = parseInt(this.isteKohad.length / 2);
      const poolRidu = Math.ceil(parimRida / 2);//Aitab läbida pool ridu keskelt eelisjärjekorras läbitavat
      const parimKoht = parseInt(this.isteKohad[0].length / 2); // Ümardame alla, kuna tegu on indeksiga
      const esimeneKoht = Math.max(0, parimKoht - parseInt(numTickets / 2)); // Et mitte minna väljaspool rida
      const poolKohti  = Math.ceil(parimKoht / 2);

      for (let i = poolRidu; i < this.soovitused.length - poolRidu; i++) {//Läbime eelkõge parimaid ridu.
         const row = this.soovitused[i]; 
         for (let j = esimeneKoht; j <= parimKoht; j++) {
            let validSeats = true;
            for (let k = j; k < j + numTickets; k++) {// Kontroll, kas valitud istekohad on vabad
            if (row[k] === 1) {//Kui pole vaba, antud lõik ei sobi.
               validSeats = false;
               break;
            }
            }
            if (validSeats) {//Kui oleme leidnud sobiva lõiku, märkime ära istmeid arvuga 2.
               for (let k = j; k < j + numTickets && k < row.length; k++) {
                  this.soovitused[i][k] = 2;
               }
            return;
            }
         }
         for (let j = esimeneKoht-1; j <= parimKoht+1; j++) {//Seejärel otsitakse rea laiemas lõigus.
            let validSeats = true;
            for (let k = j; k < j + numTickets; k++) {
            if (row[k] === 1) {
               validSeats = false;
               break;
            }
            }
            if (validSeats) {
            for (let k = j; k < j + numTickets && k < row.length; k++) {
               this.soovitused[i][k] = 2;
            }
            return;
            }
         }
         for (let j = poolKohti; j <= row.length-poolKohti; j++) {//Läbime veel laiema lõigu.
            let validSeats = true;
            for (let k = j; k < j + numTickets; k++) {
               if (row[k] === 1) {
                  validSeats = false;
                  break;
            }
            }
            if (validSeats) {
            for (let k = j; k < j + numTickets && k < row.length; k++) {
               this.soovitused[i][k] = 2;
            }
            return;
            }
         }
      }
      for (let i = 0; i < this.soovitused.length; i++) {//Kui siiamaani pole midagi leitud, otsime sobivaid kohti tervest saalist.
         const row = this.soovitused[i];
         for (let j = 0; j < row.length-numTickets; j++) {
            let validSeats = true;
            for (let k = j; k < j + numTickets; k++) {
               if (row[k] === 1) {
                  validSeats = false;
                  break;
               }
            }
            if (validSeats) {
               for (let k = j; k < j + numTickets && k < row.length; k++) {
                  this.soovitused[i][k] = 2;
               }
            return;
            }
         }
      }
   }
},
  mounted() {
      this.getKinosaal();
    }
   
}
</script>

<style>
input {
    margin-bottom: 10px;
    padding: 5px;
}
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

.vali-btn{
   margin-top: 5%;
}
</style>