/*
* @file login.jsx
* @author jansora
*/
import React, {useContext, useState} from 'react';

import {Button, Form, Grid, Header, Image, Message, Segment} from "semantic-ui-react";
import {GlobalStore} from "../../store/global";

import {GetCode, UserLogin} from "../../request/user";


const Login = () => {

    const [loading, setLoading] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [code, setCode] = useState('');
    const [uuid, setUuid] = useState('');
    const [codeUrl, setCodeUrl] = useState('');
    const [keepLogin, setKeepLogin] = useState(false);
    const {theme, dispatch} = useContext(GlobalStore);

    const [openLoginPane, setOpenLoginPane] = useState(false);
    const [openRegisterPane, setOpenRegisterPane] = useState(false);

    const setUser = user => dispatch({type: 'user', payload: user});


    const login = () => {
        const data = {username, password, code, keepLogin, uuid};
        setLoading(true);
        UserLogin(data, setLoading, setUser);
    };

    const resetCode = () => {
        GetCode(setCodeUrl, setUuid)
    };


    useState(() => {
        GetCode(setCodeUrl, setUuid)
    });


    return (
        <Grid textAlign='center' style={{height: '100vh'}} verticalAlign='middle'>
            <Grid.Column style={{maxWidth: 450}}>
                <Header as='h2' color='teal' textAlign='center'>
                    <Image src='/logo.png'/> Log-in to your account
                </Header>
                <Form size='large'>
                    <Segment>
                        <Form.Input fluid icon='user' iconPosition='left' placeholder='E-mail address Or username'
                                    value={username} onChange={(e) => setUsername(e.target.value)}/>
                        <Form.Input fluid icon='lock' iconPosition='left' placeholder='Password' type='password'
                                    value={password} onChange={(e) => setPassword(e.target.value)}/>
                        <Form.Input fluid placeholder='验证码'
                                    value={code} onChange={(e) => setCode(e.target.value)}/>
                        <Image src={codeUrl} size='small' onClick={resetCode}/>
                        <Form.Checkbox toggle onChange={() => setKeepLogin(!keepLogin)} checked={keepLogin}
                                       label='30天内保持登录状态'/>
                        <Button color='teal' fluid size='large' onClick={login}>
                            Login
                        </Button>
                    </Segment>
                </Form>
                <Message>
                    New to us? <a href='#'>Sign Up</a>
                </Message>
            </Grid.Column>
        </Grid>
    )
};
export default Login;
