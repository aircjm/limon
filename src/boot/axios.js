import axios from 'axios'
import { Notify } from 'quasar'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'

const axiosInstance = axios.create({
  baseURL: '/api',
  // 超时
  timeout: 10000
})

axiosInstance.interceptors.response.use((response) => {
  // 未设置状态码则默认成功状态
  const code = response.data.code || 200
  // 获取错误信息
  const msg = response.data.msg
  if (code === 401) {
    Notify.create({
      type: 'warn',
      message: '登录状态已过期'
    }).then(() => {
      // 刷新页面
      // store.dispatch('LogOut').then(() => {
      //   location.reload() // 为了重新实例化vue-router对象 避免bug
      // })
    })
  } else if (code === 500) {
    Notify.create({
      type: 'warn',
      message: msg
    })
    return Promise.reject(new Error(msg))
  } else if (code !== 200 || code !== 0) {
    Notification.error({
      title: msg
    })
    return Promise.reject('error')
  } else {
    return response.data
  }
}, (error) => {
  console.log('err' + error)
  Notify.create({
    type: 'warn',
    message: 'errors.network' + error.message
  })
  return Promise.reject(error)
})

export default ({ Vue }) => {
  Vue.prototype.$axios = axiosInstance
  Vue.axios = Vue.prototype.$axios
}

export { axiosInstance }
