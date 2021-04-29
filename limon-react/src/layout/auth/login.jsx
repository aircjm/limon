import React, {useContext, useState} from 'react'
import {useHistory} from 'react-router-dom';
import {GetCode, UserLogin} from "../../request/user";
import {GlobalStore} from "../../store/global";
import TextField from "@material-ui/core/TextField";
import {Button, FormGroup} from "@material-ui/core";
import styled from 'styled-components';

const Img = styled.img`
`

const Login = () => {

    const [loading, setLoading] = useState(false);
    const [Name, setName] = useState('');
    const [Token, setToken] = useState('');

    const [codeUrl, setCodeUrl] = useState('')
    const [uuid, setUuid] = useState('')

    const resetCode = () => {
        GetCode(setCodeUrl, setUuid)
    };


    useState(() => {
        GetCode(setCodeUrl, setUuid)
    });


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
            <button className="py-2 px-4 font-semibold rounded-lg shadow-md text-white bg-green-500 hover:bg-green-700">
                Click me
            </button>
            <form className="w-80 m-auto"
                  noValidate
                  onSubmit={signIn}>
                <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    name="username"
                    label="username"
                    type='text'
                    id="username"
                    autoComplete="current-username"
                />
                <TextField
                    variant="outlined"
                    margin="normal"
                    required
                    fullWidth
                    name="password"
                    label="Password"
                    type="password"
                    id="password"
                    autoComplete="current-password"
                />
                <div>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        name="imgcode"
                        label="验证码"
                        type="text"
                        id="imgcode"
                    />
                    <Img src={codeUrl} onClick={resetCode}/>
                </div>
                <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    size="large"
                    color="primary"
                    className={"text-red"}
                >
                    Login
                </Button>
            </form>
        </React.Fragment>
    )


}


export default Login