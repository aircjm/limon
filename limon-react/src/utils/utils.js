export const IsNumber = s => /^\d+$/.test(s);


export function getToken() {
    return localStorage.getItem("token")
}