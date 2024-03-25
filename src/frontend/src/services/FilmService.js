import axios from 'axios'

const FILM_API_BASE_URL = 'http://localhost:8080/api/kinokava'
class FilmService{
    getFilmid(){
        return axios.get(FILM_API_BASE_URL);
    }
}

export default new FilmService()
