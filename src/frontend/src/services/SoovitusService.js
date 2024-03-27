import axios from 'axios'

const FILM_SUG_API_BASE_URL = 'http://localhost:8080/api/soovita'
class SoovitusService{
    getSoovitused(){
        return axios.get(FILM_SUG_API_BASE_URL);
    }
}

export default new SoovitusService()
