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

export const UserLogin = async (data, setLoading, callback) => {
    let response = {}
   await client.post('/login', data)
        .then(data => {
            response = data
        }).catch(e => {
    }).finally(() => {
        setLoading(false);
    })
    return response
};


export const UserLogout = (callback) => {
    client.post("logout")
        .then( response => {
          callback(defaultValue)
        })
    return null;
};
