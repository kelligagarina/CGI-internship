import axios from 'axios'

const FILM_SAAL_API_BASE_URL = 'http://localhost:8080/api/kinosaal'
//const FILM_SAAL_SOOV_BASE_URL = 'http://localhost:8080/api/istekohaSoovitus'
class KinosaalService{
    getKinosaal(){
        return axios.get(FILM_SAAL_API_BASE_URL);
    }
 //   getKinosaalSoovitused(piletiteArv){
 //       return axios.post(FILM_SAAL_SOOV_BASE_URL, piletiteArv);
 //   }
}

export default new KinosaalService()
