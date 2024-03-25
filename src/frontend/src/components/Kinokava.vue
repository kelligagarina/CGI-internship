<template>
    <div class = "container">
        <div class = "kinokava">
            <h1>Nädala kinokava</h1>
            <p>01.04.2024-07.04.2024</p>
            <div class = "film" v-for = "film in filteredFilmid" v-bind:key = "film.id">
                <div class="film-info">
                <h1 class = "pealkiri">{{ film.pealkiri }}</h1>
                <p class = "kellaeg">{{ film.kellaeg }}</p>

                <p class = "üksikasjad">
                <span class = "info"><b>Žanr: </b>{{ film.žanr }}</span>
                <span class = "info"><b>Formaat: </b>{{ film.formaat }}</span>
                <span class = "info"><b>Keel: </b>{{ film.keel }}</span>
                <span class = "info"><b>Subtiitrid: </b>{{ film.subtiitrid }}</span>
                <span class = "info"><b>Vanusepiirang: </b>{{ film.vanusepiirang }}+</span>

                </p>
                <button class="vali-btn">Vali film</button>

            </div>

            </div>
        </div>
        <div class="filter">
            <h2>Filtreeri</h2>
            <form @submit.prevent="applyFilters">
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
                <input type="number" id="vanus" min="0">


              <!--  <button type="submit">Apply</button>-->
            </form>
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
                valitudFormaat:''
            }
        },
        methods: {
            getFilmid(){
                return FilmService.getFilmid().then((response) =>{
                    this.filmid = response.data;
                });
            },
            applyFilters(){
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
                            return žanriJärgi && keeleJärgi && subtiitrideJärgi && formaadiJärgi;
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
}

.kinokava{
    margin:auto;
    padding-right: 20px;
    width: 80%;
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



.vali-btn {
  background: purple;
  border: 0;
  border-radius: 12px;
  color: #FFFFFF;
  cursor: pointer;
  display: inline-block;
  font-family: -apple-system,system-ui,"Segoe UI",Roboto,Helvetica,Arial,sans-serif;
  font-size: 20px;
  font-weight: 500;
  line-height: 2.5;
  outline: transparent;
  padding: 0 1rem;
  text-align: center;
  transition: box-shadow .2s ease-in-out;
}

.vali-btn:not([disabled]):hover {
  box-shadow: 0 0 .25rem rgba(0, 0, 0, 0.5), -.125rem -.125rem 1rem plum;
}
.filter {
    margin-top: 93px;
    width:20%;
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


</style>