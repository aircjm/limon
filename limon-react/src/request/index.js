import axios from 'axios';

export const client = axios.create(
    {
        baseURL: "/v2/",
        timeout: 100000,
        headers: {
            "Content-Type": "application/json"
        }
    }
);

// 添加请求入参
axios.interceptors.request.use(function (config) {
    let token = window.localStorage.token;
    if (token) {
        config.headers.Authorization = `token ${token}`
    }
    return config
}, function (error) {
    return Promise.reject(error);
});


// 回参处理
axios.interceptors.response.use(response => {
        //对响应数据做操作
        if (response.data.code === '200') {
            console.log('请求成功');
            return response
        }
        if (response.data.code === '2009001' || response.data.code === 2009001) {
            console.log('已过期重新登陆', response.data.code);
            window.location.href = '/login';
            return Promise.reject(response);
        } else {
            console.log('请求失败', response.data.code);
            alert(response.data.message);
            return Promise.reject(response);
        }
    },
    error => {
        //对响应数据错误做操作
        console.log('请求error', error.message);
        return Promise.reject(error);
    });

export const format = (data) => Object.keys(data).map(i => encodeURIComponent(i) + '=' + encodeURIComponent(data[i])).join('&');
