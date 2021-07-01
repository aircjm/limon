import React, {useContext, useEffect, useState} from 'react'
import {useHistory} from 'react-router-dom';
import {GlobalStore} from "../../store/global";
import {Button, Input} from "@material-ui/core";
import client from "../../api/request";


interface CaptchaImage {
    uuid : string;
    img : string;
    code : number;
}


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
        client.get<CaptchaImage>("/captchaImage").then((data) => {
            if (data.data.code === 200) {
                console.log("get code success")
                let code = "data:image/gif;base64," + data.data.img
                setCodeUrl(code)
                setUuid(data.data.uuid)
            }
            if (!codeUrl) {
                console.log("获取验证码失败")
            }
        })
    }


    useEffect(() => {
        getCode()
    }, [])


    const history = useHistory();

    // const updateUser = user => {
    //     dispatch({type: 'user', payload: user});
    //     history.push(`/about/user/current`);
    // };

    const changeUserName = (event: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(event.target.value);
    };

    const signIn = () => {
        const data = {username, password, code, uuid};
        // const userInfo = UserLogin(data, setLoading, updateUser);
        // updateUser(userInfo)
    }

    return (
        <React.Fragment>
            <Input
                required
                fullWidth
                name="username"
                type='text'
                id="username"
                value={username}
                onChange={changeUserName}
                autoComplete="current-username"
            />
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
        </React.Fragment>
    )


}


export default Login
