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
                <label for="vanus">Vanus:</label>
                <input v-model="valitudVanus" type="number" id="vanus" min="0" placeholder="Sisesta vanus.">
                <div v-if="valitudVanus < 0" style="color: grey;">Palun sisestage positiivne arv.</div>

            </form>
            <button @click="toggleSoovitus" class="soovitus-btn">Soovita filme vaatamisajaloo põhjal</button>
            </div>
        </div>
    </div>
</template>

<script>
import FilmService from '../services/FilmService'

    export default{
        name: 'FilmidList',
        data(){
            return {
                filmid : [],
                unikaalsedŽanrid : [],
                unikaalsedKeeled : [],
                valitudŽanr: '',
                valitudKeel:'',
                valitudSubtiitrid:'',
                valitudFormaat:'',
                valitudVanus: '',
            }
        },
        methods: {
            getFilmid(){
                return FilmService.getFilmid().then((response) =>{
                    this.filmid = response.data;
                });
            },
            applyFilters(){
            },

            toggleSoovitus() {
                 this.$emit('toggle-recommendations');
                  },
            toggleSaal() {
                 this.$emit('toggle-istekohad');

            }
        },
        created() {
                     this.getFilmid().then(() => {
                         this.unikaalsedŽanrid = [...new Set(this.filmid.map(film => film.žanr))];
                         this.unikaalsedKeeled = [...new Set(this.filmid.map(film => film.keel))];
                     });},
        computed: {
                filteredFilmid() {
                    return this.filmid.filter(film => {
                        const žanriJärgi = !this.valitudŽanr || film.žanr === this.valitudŽanr;
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
    align-items: flex-start; /* Align items at the top */
    flex-direction: column;
}
.pealkiri{
    margin: auto;
    text-align: center;
    padding: 3%
 }
.kontent{
    display: flex;
}
.kinokava{
    margin:auto;
    padding-right: 20px;
    width: 100%;
}

.film {
    display: flex;
    flex-direction: column;
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 20px;
    border-radius: 20px;
    box-shadow:5px 5px 2px lightblue;

}

.film-info {
    flex-grow: 1;
    margin-right: 20px;
}
.kellaeg{
    
    
}
.pealkiri {
    margin-top: 0;
    font-size: 15px;
}

.üksikasjad {
    margin-top: 5px;
    font-size: 20px;
    float: left;
}
.info{
    padding: 10px;
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
    float: right;
    padding: 20px;
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