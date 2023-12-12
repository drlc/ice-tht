import { Api } from './api'

class AlbumService extends Api {
    constructor() {
        super({
            url: process.env.REACT_APP_API_URL,
        })
    }

    list = (query) => {
        const params = new URLSearchParams()
        this.appendParamsFromObject(params, query)
        return this.axiosInstance.get('album?' + params.toString()).then((res) => {
            return res.data
        })
    }
}

const albumApi = new AlbumService()
export default albumApi
