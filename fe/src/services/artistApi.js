import { Api } from './api'

class ArtistService extends Api {
    constructor() {
        super({
            url: process.env.REACT_APP_API_URL,
        })
    }

    list = (query) => {
        const params = new URLSearchParams()
        this.appendParamsFromObject(params, query)
        return this.axiosInstance.get('artist?' + params.toString()).then((res) => {
            return res.data
        })
    }
}

const artistApi = new ArtistService()
export default artistApi
