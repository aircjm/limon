import axios from 'axios';
import {message} from "antd";

// import {useHistory} from "react-router-dom";


export const client = axios.create(
    {
        baseURL: "/v2/",
        timeout: 100000,
        headers: {
            "Content-Type": "application/json"
        }
    }
);


// const history = useHistory();


// 添加请求入参
client.interceptors.request.use(function (config) {
    let token = window.localStorage.token;
    if (token) {
        config.headers.Authorization = `token ${token}`
    }
    return config
}, function (error) {
    return Promise.reject(error);
});


// 回参处理
client.interceptors.response.use(response => {
        //对响应数据做操作
        if (response.data.code === 200) {
            return response;
        }
        if (response.data.code === '401' || response.data.code === 401) {
            console.log('已过期重新登陆', response.data.code);
            // history.push('login')
            // window.location.href = '/login';
            return Promise.reject(response);
        } else {
            console.log('请求失败', response.data.code);
            message.warn(response.data.msg);
            return Promise.reject(response);
        }
    },
    error => {
        //对响应数据错误做操作
        console.log('请求error', error.message);
        return Promise.reject(error);
    });

export const format = (data) => Object.keys(data).map(i => encodeURIComponent(i) + '=' + encodeURIComponent(data[i])).join('&');
