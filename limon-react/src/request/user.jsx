/*
* @file User.jsx
* @author jansora
* @date 2020/2/5
*/


import React, {useEffect, useState} from "react";
import {stringify} from "qs";
import {client} from "./index";
import {message} from "antd";


import {defaultValue} from "../store/global";


export const GetCode = (setCodeUrl, setUuid) => {
    client.get("/captchaImage")
        .then(response => {
            const {data} = response;
            if (data.code === 200) {
                let code =  "data:image/gif;base64," + data.img
                setCodeUrl(code)
                setUuid(data.uuid)
            }
        })

};


export const QueryUser = (id) => {
    const [user, setUser] = useState(null);
    useEffect(() => {
        if (id) {
            client.get(`user/queryUser/${id}`)
                .then(response => {
                    const {data} = response;
                    if (data.status) {
                        setUser(data.data)
                    }
                })
        }
    }, [id])

    return [user];
};

export const QueryCurrentUser = (setUser) => {
    client.get("user/queryCurrentUser")
        .then(response => {
            const {data} = response;
            if (data.status == 200) {
                setUser(data.data)
            } else {
                setUser(defaultValue.user)
            }
        })
    return null;
};


export const UserRegister = (data, setLoading, setOpen, setUser) => {

    setLoading(true);
    client.post('user/register', stringify(data))
        .then(response => {
            const {data} = response;
            if (data.status) {
                message.success(`注册成功~ ${data.data.alias}`)
                setOpen(false);
                setLoading(false);
                setUser(data.data)
            } else {
                message.error(data.message)
            }

        }).catch(e => {
    }).finally(() => {
        setLoading(false);
    })


    return null;
};

export const UserLogin = (request, setLoading, setOpenLoginPane, setUser) => {
    setLoading(true);
    client.post('/login', request)
        .then(response => {
            debugger
            const {data} = response;
            message.success(`欢迎回来~ ${request.username}`);
            setOpenLoginPane(false);
            setLoading(false);
            window.localStorage.token = data.token;
            window.location.href = '/';
            let user = {};
            user.username = request.username;
            setUser(user)
        }).catch(e => {
    }).finally(() => {
        setLoading(false);
    })


    return null;
};


export const UserLogout = (setUser) => {
    client.post("user/logout")
        .then(response => {
            const {data} = response;
            if (data.status) {
                setUser()
                message.warning("你已经退出登录")
            }
        })
    return null;
};

export const UpdateUser = (data, setOpen, setUser) => {
    client.post("user/update", stringify(data))
        .then(response => {
            const {data} = response;
            if (data.status) {
                setOpen(false)
                setUser(data.data)
                message.success("更新成功")
            }
        })
};
