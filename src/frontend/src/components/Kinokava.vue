<template>
  <div class = "container">
   <div class = "pealkiri">
      <h1>Nädala kinokava</h1>
      <p>01.04.2024-07.04.2024</p>
   </div>
   <div class = "kontent">
      <div class = "kinokava">
         <div class = "film" v-for = "film in filteredFilmid" v-bind:key = "film.id">
            <div class="film-info">
                <div class = "film-detailid">
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
                <div class="nupp">
                <button @click="LiiguSaali" class="vali-btn">Vali film</button>
                </div>
            </div>
         </div>
      </div>
      <div class="filter">
         <h2>Filtreeri</h2>
         <form>
            <label for="žanr">Žanr:</label>
            <select v-model="valitudŽanr" id="žanr" >
               <option value="">Kõik</option>
               <option v-for="žanr in unikaalsedŽanrid" v-bind:key="žanr" v-bind:value="žanr">
                  {{ žanr }}
               </option>
            </select>

            <label for="keel">Keel:</label>
            <select v-model="valitudKeel" id="keel">
               <option value="">Kõik</option>
               <option v-for="keel in unikaalsedKeeled" v-bind:key="keel">
                  {{ keel }}
               </option>
            </select>

            <label for="subtiitrid">Subtiitrid:</label>
            <select v-model="valitudSubtiitrid" id="subtiitrid">
               <option value="">Kõik</option>
               <option values="inglise">inglise</option>
               <option values="vene">vene</option>
               <option values="eesti">eesti</option>
               <option values="puuduvad">puuduvad</option>

            </select>
            <label for="formaat">Formaat:</label>
            <select v-model="valitudFormaat" id="formaat">
               <option value="">Kõik</option>
               <option values="2D">2D</option>
               <option values="3D">3D</option>
            </select>

            <label for="vanus">Vaataja vanus:</label>
            <input v-model="valitudVanus" type="number" id="vanus" min="0" placeholder="Sisesta vanus.">
            <div v-if="valitudVanus < 0" style="color: grey;">Palun sisestage positiivne arv.</div>
         </form>
         <button @click="LiiguSoovitus" class="soovitus-btn">Soovita filme vaatamisajaloo põhjal</button>
      </div>
   </div>
</div>
</template>

<script>
import FilmService from '../services/FilmService'

    export default{
        name: 'KinoKava',
        data(){
            return {
                filmid : [],
                unikaalsedŽanrid : [],//Otsustasin jätta ainult need žanrid, mis päriselt kinokavas olemas on, kuna žanre võib päriselus olla liiga palju, et neid kõike kajastada.
                unikaalsedKeeled : [],//Sama otsuse tegin ka keelte puhul
                valitudŽanr: '',
                valitudKeel:'',
                valitudSubtiitrid:'',
                valitudFormaat:'',
                valitudVanus: '',
                kellaeg: '',
            }
        },
        methods: {
         getFilmid(){
                return FilmService.getFilmid().then((response) =>{
                    this.filmid = response.data;
                });
          }, 
          LiiguSaali(){
            this.$router.push("/api/kinosaal");
          },
          LiiguSoovitus() {
            this.$router.push("/api/soovitaFilme");
          },
          paremKellaeg(kellaeg){
                return kellaeg.split("T")[1].substring(0,5);
          },
          paremKuupäev(kellaeg){
                const aastaOsad = kellaeg.split("T")[0].split("-");
                return aastaOsad[2] + "." + aastaOsad[1] + "." + aastaOsad[0];
          },
        
        },
     
        created() {//Kasutasin siin ChatGPT abi unikaalsete žanride ja keelte otsimiseks.
            this.getFilmid().then(() => {
            this.unikaalsedŽanrid = [...new Set(this.filmid.map(film => film.žanr))];
            this.unikaalsedKeeled = [...new Set(this.filmid.map(film => film.keel))];
                });
             },
        computed: {
            filteredFilmid() {//Antud meetod filtreerib filme.
                return this.filmid.filter(film => {
                    const žanriJärgi = !this.valitudŽanr || film.žanr === this.valitudŽanr;//Näitab filmi faid siis, kui selle väli on sama, mis filtris või filtri väli on 'default' seisundis.
                    const keeleJärgi = !this.valitudKeel || film.keel === this.valitudKeel;
                    const subtiitrideJärgi =  !this.valitudSubtiitrid || film.subtiitrid === this.valitudSubtiitrid;
                    const formaadiJärgi = !this.valitudFormaat || film.formaat === this.valitudFormaat;
                    const vanuseJärgi = !this.valitudVanus || film.vanusepiirang <= this.valitudVanus || this.valitudVanus < 0;
                        return žanriJärgi && keeleJärgi && subtiitrideJärgi && formaadiJärgi && vanuseJärgi;
                        });
                    }
            
                }
            }

</script>

<style>
.container {
    display: flex;
    justify-content:space;
    align-items: flex-start;
    flex-direction: column;
}
.pealkiri{
    margin: auto;
    text-align: center;
    padding: 3%
 }

.kellaeg{
    text-align: center;
    float: left;
}

.kontent{
    display:flex;
    margin: auto;
    
}
.kinokava{
    margin-right: 5%;
    width: 100%;
}

.film {
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 20px;
    border-radius: 20px;
    box-shadow:5px 5px 2px lightblue;

}

.film-info {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.film-detailid {
    flex-grow: 1;
}

.üksikasjad {
    margin-top: 5px;
    font-size: 18px;
    float: center;
}
.info{
    padding: 10px;
}
.nupp {
    margin-left: 2%;
}
.vali-btn,
.soovitus-btn{
  background: purple;
  border: 0;
  border-radius: 12px;
  color: #FFFFFF;
  cursor: pointer;
  display: inline-block;
  font-family: -apple-system,system-ui,"Segoe UI",Roboto,Helvetica,Arial,sans-serif;
  font-size: 20px;
  font-weight: 500;
  outline: transparent;
  padding: 12px 24px;
  text-align: center;
  transition: box-shadow .2s ease-in-out;
}

.vali-btn:not([disabled]):hover,
.soovitus-btn:not([disabled]):hover {
  box-shadow: 0 0 .25rem rgba(0, 0, 0, 0.5), -.125rem -.125rem 1rem plum;
}

.filter {
    width:30%;
    margin-left: auto;
    padding: 20px;
    flex-wrap: wrap;
    box-sizing: border-box;
    height: 20%;
    border: 1px solid #ccc;
    border-radius: 20px;
    box-shadow:10px 10px 2px lightblue;

}

.filter h2 {
    margin-top: 0;
}

form {
    margin-top: 10px;
   
}

label {
    display: block;
    margin-bottom: 5px;
}

select, input {
    width: 100%;
    margin-bottom: 10px;
    padding: 5px;
}
.soovitus-btn{
    margin-top: 20px;
    padding: 12px 24px;
}


</style>