import axios, {
    CanceledError,
} from 'axios'
import axiosRetry from 'axios-retry'


export class Api {
    axiosInstance
    retryDefaultSettings = {}

    constructor(config) {
        this.retryDefaultSettings = {
            retries: config.retryTimes || 3,
            retryDelay: axiosRetry.exponentialDelay,
            shouldResetTimeout: true,
        }
        this.axiosInstance = axios.create({
            baseURL: config.url,
            timeout: config.timeout || 30000,
            headers: config.headers ? config.headers : null,
        })

        // Add a request interceptor
        this.axiosInstance.interceptors.request.use((config) => {
            return config;
        })

        axiosRetry(this.axiosInstance, { retries: 3, shouldResetTimeout: true })
        this.axiosInstance.interceptors.response.use((response) => {
            return response
        }, Api.commonResponseInterceptor)
    }

    static commonResponseInterceptor(
        error,
    ) {
        let message = 'Unhandled error in HTTP request.'

        if (error instanceof CanceledError) {
            return Promise.reject(error)
        }

        if (
            error.response &&
            error.response.data &&
            error.response.data.errors &&
            error.response.data.errors.length > 0
        ) {
            message =
                'HTTP error ' + error.response.status + ': ' + error.response.data.errors[0].detail
        }

        console.error(message)

        return Promise.reject(error)
    }

    appendParamsFromObject(
        params,
        query,
    ) {
        if (query && typeof query === 'object') {
            for (const [key, value] of Object.entries(query)) {
                if (value && typeof value === 'string') {
                    params.append(key, value)
                } else if (value && Array.isArray(value)) {
                    for (const el of value) {
                        if (el && typeof el === 'string') {
                            params.append(key, el.trim())
                        }
                    }
                } else {
                    params.append(key, String(value))
                }
            }
        }
        return params
    }
}
