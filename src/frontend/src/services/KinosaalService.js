import axios from 'axios'

const FILM_SAAL_API_BASE_URL = 'http://localhost:8080/api/kinosaal'
class KinosaalService{
    getKinosaal(){
        return axios.get(FILM_SAAL_API_BASE_URL);
    }
}

export default new KinosaalService()
