import { Api } from './api'

class GenreService extends Api {
    constructor() {
        super({
            url: process.env.REACT_APP_API_URL,
        })
    }

    list = (query) => {
        const params = new URLSearchParams()
        this.appendParamsFromObject(params, query)
        return this.axiosInstance.get('genre?' + params.toString()).then((res) => {
            return res.data
        })
    }
}

const genreApi = new GenreService()
export default genreApi
