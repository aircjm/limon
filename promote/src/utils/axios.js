import axios from 'axios'
import {getToken} from 'src/utils/project'
import {Notify} from 'quasar'
import {logout} from 'src/api/login'


axios.defaults.headers['Content-Type'] = 'application/json;'
// 创建axios实例
const client = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: '/api',
  // 超时
  timeout: 150000
})
// client.defaults.withCredentials = true


// request拦截器
client.interceptors.request.use(config => {
  // Do something before request is sent
  // console.log(config)
  // 是否需要设置 token
  if (getToken()) {
    config.headers.Authorization = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
  }
  return config
}, error => {
  console.log(error)
  // Do something with request error
  Promise.reject(error)
})

// 响应拦截器
client.interceptors.response.use(res => {
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

export function doGet(url, data) {
  return client({
    url: url,
    method: 'get',
    params: data
  })
}


// 列表搜索
export function doPost(url, data) {
  return client({
    url: url,
    method: 'post',
    data: data
  })
}

export default client
