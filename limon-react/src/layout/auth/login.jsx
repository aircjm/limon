import React, {useContext, useState} from 'react'
import {useHistory} from 'react-router-dom';
import {UserLogin} from "../../request/user";
import {GlobalStore} from "../../store/global";

const Login = () => {

    const [loading, setLoading] = useState(false);
    const [Name, setName] = useState('');
    const [Token, setToken] = useState('');


    const {theme, dispatch} = useContext(GlobalStore);
    const history = useHistory();

    const setUser = user => {
        dispatch({type: 'user', payload: user});
        history.push(`/about/user/current`);

    };

    const signIn = () => {

        const data = {Name, Token};
        setLoading(true);
        UserLogin(data, setLoading, setUser);
    }
    return (
        <React.Fragment>

        </React.Fragment>
    )


}


export default Login