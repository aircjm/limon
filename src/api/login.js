import axios from 'axios'

// 登录方法

export function login (data) {
  return axios({
    url: '/login',
    method: 'post',
    data: data
  })
}

// 获取用户详细信息
export function getInfo () {
  return axios({
    url: '/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout () {
  return axios({
    url: '/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg () {
  return axios({
    url: '/captchaImage',
    method: 'get'
  })
}
