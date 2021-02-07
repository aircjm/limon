import axios from 'axios'
import { getToken } from 'src/utils/project'
import { Notify } from 'quasar'
import { logout } from 'src/api/login'

export default async ({ app, router, store, Vue }) => {
  // something to do
  axios.defaults.headers.post['Content-Type'] = 'application/json'

  // request拦截器
  axios.interceptors.request.use(config => {
    // Do something before request is sent
    // console.log(config)
    // 是否需要设置 token
    const isToken = (config.headers || {}).isToken === false
    // console.log(getToken())
    if (getToken() && !isToken) {
      config.headers.Authorization = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config
  }, error => {
    console.log(error)
    // Do something with request error
    Promise.reject(error)
  })

  // 响应拦截器
  axios.interceptors.response.use(res => {
    // 未设置状态码则默认成功状态
    const code = res.data.code || 200
    // 获取错误信息
    const message = res.data.msg || '系统错误'
    if (code === 401) {
      Notify.create({
        message: '登录状态已过期，您可以继续留在该页面，或者重新登录',
        type: 'warning',
        position: 'top',
        timeout: 3000
      })
      logout().then(() => {
        router.push('/login')
      })
    } else if (code === 500) {
      Notify.create({
        message: message,
        type: 'negative',
        position: 'top',
        timeout: 3000
      })
      return Promise.reject(new Error(message))
    } else if (code !== 200) {
      Notify.create({
        position: 'top',
        type: 'negative',
        title: message
      })
      return Promise.reject('error')
    } else {
      return res.data
    }
  },
  error => {
    console.log('err' + error)
    Notify.create({
      message: error.message,
      type: 'negative',
      icon: 'report_problem',
      position: 'top',
      timeout: 3000
    })
    return Promise.reject(error)
  }
  )

  Vue.prototype.$axios = axios
  Vue.prototype.$axios.defaults.baseURL = '/api'
  Vue.prototype.$axios.defaults.timeout = 150000
}
