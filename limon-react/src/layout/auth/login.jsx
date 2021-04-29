import React, {useContext, useState} from 'react'
import {useHistory} from 'react-router-dom';
import {UserLogin} from "../../request/user";
import {GlobalStore} from "../../store/global";
import TextField from "@material-ui/core/TextField";
import {Button} from "@material-ui/core";

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
            <form className="w-80 m-auto"
                  noValidate
                  onSubmit={signIn}>
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
                ></TextField>

                <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    size="large"
                    color="primary"
                >
                    Login
                </Button>
            </form>
        </React.Fragment>
    )


}


export default Login