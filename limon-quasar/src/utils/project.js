import { LocalStorage } from 'quasar'
const TokenKey = 'token'

export function getToken () {
  return LocalStorage.getItem(TokenKey)
}

export function setToken (token) {
  return LocalStorage.set(TokenKey, token)
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
