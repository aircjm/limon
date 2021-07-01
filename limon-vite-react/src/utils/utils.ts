export function getToken() {
    return localStorage.getItem("token")
}

export function setToken(token : string) {
    if (token) {
        localStorage.setItem("token", token)
    }
}