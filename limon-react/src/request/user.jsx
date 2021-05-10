import {client} from "./request";
import {defaultValue} from "../store/global";


export const FetchCurrentUser = (setUser) => {
    client.get("fetchCurrentUser")
        .then(setUser)
};


export const GetCode = (setCodeUrl, setUuid) => {
    client.get("/captchaImage")
        .then(response => {
            debugger;
            const {data} = response;
            if (data.code === 200) {
                console.log("get code success")
                let code = "data:image/gif;base64," + data.img
                setCodeUrl(code)
                setUuid(data.uuid)
            }
        })
};

export const UserLogin = (data, setLoading, callback) => {
    client.post('/login', data)
        .then(response => {
            debugger
            callback(response)
        }).catch(e => {
    }).finally(() => {
        setLoading(false);
    })
};


export const UserLogout = (callback) => {
    client.post("logout")
        .then( response => {
          callback(defaultValue)
        })
    return null;
};
