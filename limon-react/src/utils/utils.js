export const IsNumber = s => /^\d+$/.test(s);


export function getToken() {
    return localStorage.getItem("token")
}

export function setToken(token) {
    if (token) {
        localStorage.setItem("token", token)
    }
}