import React, {useContext, useEffect, useState} from 'react'
import {useHistory} from 'react-router-dom';
import {UserLogin} from "../../request/user";
import {GlobalStore} from "../../store/global";
import styled from 'styled-components';
import {client} from "../../request/request";
import {Button, Form, FormField, Input} from "semantic-ui-react";


const Img = styled.img`
`

const Login = () => {

    const [loading, setLoading] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [code, setCode] = useState('');

    const [uuid, setUuid] = useState('')

    const [codeUrl, setCodeUrl] = useState('')

    const resetCode = () => {
        getCode()
    };
    const getCode = () => {
        client.get("/captchaImage").then(
            function (data) {
                if (data.code === 200) {
                    console.log("get code success")
                    let code = "data:image/gif;base64," + data.img
                    setCodeUrl(code)
                    setUuid(data.uuid)
                }
                if (!codeUrl) {
                    console.log("获取验证码失败")
                }
            })
    }


    useEffect(() => {
        getCode()
    }, [])


    const {dispatch} = useContext(GlobalStore);
    const history = useHistory();

    const updateUser = user => {
        dispatch({type: 'user', payload: user});
        history.push(`/about/user/current`);
    };

    const signIn = () => {
        const data = {username, password, code, uuid};
        const userInfo = UserLogin(data, setLoading, updateUser);
        updateUser(userInfo)
    }

    return (
        <React.Fragment>
            <div className={"md:m-auto justify-center md:w-2/5 sm:w-auto"}>
                <Form>
                    <FormField>
                        <Input
                            variant="outlined"
                            margin="normal"
                            required
                            fullWidth
                            name="username"
                            label="username"
                            type='text'
                            id="username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            autoComplete="current-username"
                        />
                    </FormField>
                    <FormField>
                        <Input
                            variant="outlined"
                            margin="normal"
                            required
                            fullWidth
                            name="password"
                            label="Password"
                            type="password"
                            id="password"
                            onChange={(e) =>setPassword(e.target.value)}
                            autoComplete="current-password"
                        />
                    </FormField>

                    <FormField>
                        <div className="flex flex-row">
                            <div>
                                <Input
                                    variant="outlined"
                                    margin="normal"
                                    required
                                    name="imgcode"
                                    label="验证码"
                                    type="text"
                                    id="imgcode"
                                    onChange={(e) =>  setCode(e.target.value)}
                                />
                            </div>
                            <div className={"m-auto justify-center w-auto"}>
                                <Img src={codeUrl} onClick={resetCode}/>
                            </div>
                        </div>
                    </FormField>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        size="large"
                        color="primary"
                        className={"text-red"}
                        onClick={signIn}
                    >
                        Login
                    </Button>
                </Form>
            </div>
        </React.Fragment>
    )


}


export default Login
