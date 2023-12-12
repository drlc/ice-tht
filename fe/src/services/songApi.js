import { Api } from './api'

class SongService extends Api {
    constructor() {
        super({
            url: process.env.REACT_APP_API_SONG_URL,
        })
    }

    list = (query) => {
        const params = new URLSearchParams()
        this.appendParamsFromObject(params, query)
        return this.axiosInstance.get('song?' + params.toString()).then((res) => {
            return res.data
        })
    }
}

const songApi = new SongService()
export default songApi
