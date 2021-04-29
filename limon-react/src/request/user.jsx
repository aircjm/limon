import {client} from "./request";
import {defaultValue} from "../store/global";


export const FetchCurrentUser = (setUser) => {
    client.get("fetchCurrentUser")
        .then(setUser)
};



export const UserLogin = (data, setLoading, callback) => {
    setLoading(true);
    client.post('login', data)
        .then(response =>  {
          callback(response)
        }).catch( e => {

    }).finally(()=> {
        setLoading(false);
    })
    return null;
};


export const UserLogout = (callback) => {
    client.post("logout")
        .then( response => {
          callback(defaultValue)
        })
    return null;
};
