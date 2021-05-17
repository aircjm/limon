import client, {doPost} from "src/utils/axios";

// 登录方法

export function login (data) {
  return doPost('/login', data)
}

// 获取用户详细信息
export function getInfo () {
  return client({
    url: '/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout () {
  return client({
    url: '/logout',
    method: 'post'
  })
}

// 获取验证码
export function getCodeImg () {
  return client({
    url: '/captchaImage',
    method: 'get'
  })
}

