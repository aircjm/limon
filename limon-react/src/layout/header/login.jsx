import React, {useContext, useState} from 'react';

import {Button, Form, Grid, Header, Image, Message, Segment} from "semantic-ui-react";
import {GlobalStore} from "../../store/global";

import {GetCode, UserLogin} from "../../request/user";
import {Link} from "react-router-dom";


const Login = () => {

    const [loading, setLoading] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [code, setCode] = useState('');
    const [uuid, setUuid] = useState('');
    const [codeUrl, setCodeUrl] = useState('');
    const [keepLogin, setKeepLogin] = useState(false);
    const {theme, dispatch} = useContext(GlobalStore);


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
        <Grid textAlign='center' style={{height: '100vh'}} verticalAlign='top'>
            <Grid.Column style={{maxWidth: 450}}>
                <Header as='h4' color='teal' textAlign='center'>
                    <Image src='/logo.png'/> Log-in to your account
                </Header>
                <Form size='big'>
                    <Segment>
                        <Form.Input fluid icon='user' iconPosition='left' placeholder='E-mail address Or username'
                                    value={username} onChange={(e) => setUsername(e.target.value)}/>
                        <Form.Input fluid icon='lock' iconPosition='left' placeholder='Password' type='password'
                                    value={password} onChange={(e) => setPassword(e.target.value)}/>
                        <Form.Group>
                            <Form.Field>
                                <Form.Input placeholder='验证码'
                                            value={code} onChange={(e) => setCode(e.target.value)}/>
                            </Form.Field>
                            <Form.Field>
                                <Image src={codeUrl} size='small' onClick={resetCode}/>
                            </Form.Field>
                        </Form.Group>

                        <Form.Checkbox toggle onChange={() => setKeepLogin(!keepLogin)} checked={keepLogin}
                                       label='30天内保持登录状态'/>
                        <Button fluid size='large' onClick={login} color={"green"}>
                            Login
                        </Button>
                    </Segment>
                </Form>
                <Message>
                    New to us? <Button as={Link} to="/signUp" color={"blue"} >Sign Up</Button>
                </Message>
            </Grid.Column>
        </Grid>
    )
};
export default Login;
