import {createContext, useReducer} from 'react';


export const defaultValue = {
    // 用户信息
    user: {
        username: "",
        alias: "",
        token: ""
    },
    breadcrumb: [],
    title: '主页'
};

interface DefaultContext {
    user: User;
    title: string;
}

interface User {
    username: string;
    token: string;
    alias: string;
}



export const GlobalStore = createContext<DefaultContext>(defaultValue);

const reducer = (state: any, action: any) => {
    switch(action.type) {
        case 'user':
            return {...state, user: action.payload};
        case 'title':
            return {...state, title: action.payload};
        default:
            return {...state, ...action.payload}
    }
}
