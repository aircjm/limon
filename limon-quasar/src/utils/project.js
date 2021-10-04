import { LocalStorage } from 'quasar'
const TokenKey = 'token'

export function getToken () {
  return LocalStorage.getItem(TokenKey)
}

export function setToken (token) {
  return LocalStorage.set(TokenKey, token)
}

export function setLoginUserInfo (user) {
   LocalStorage.set(TokenKey, user.token)
   LocalStorage.set('username', user.username)
   LocalStorage.set('avatar', user.avatar)
}

export function removeToken () {
  return LocalStorage.remove(TokenKey)
}


const items = [
  "/captchaImage"
]

export function noToken(url) {
 return  items.includes(url)
}
