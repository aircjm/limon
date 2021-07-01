import React, { useState } from "react";
import { useForm } from "react-hook-form";
import {
    Button,
    TextField,
    Dialog,
    DialogContent,
    DialogTitle,
    Grow, Alert,
} from "@material-ui/core";
import { useDispatch, useSelector } from "react-redux";
import { login, clearErrors } from "./AuthSlice";
import { RootState } from "store";
import styled from "styled-components";
import Close from "../../components/Close";

const FormActions = styled.div`
  margin-top: 1rem;
  text-align: right;
`;




interface FormData {
    username: string;
    password: string;
}

const LoginDialog = () => {
    const dispatch = useDispatch();
    const [open, setOpen] = useState(false);
    const apiErrors = useSelector((state: RootState) => state.auth.loginErrors);
    const loading = useSelector((state: RootState) => state.auth.loginLoading);
    const { register, handleSubmit, formState: { errors }} = useForm<FormData>();

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
        dispatch(clearErrors());
    };

    const onSubmit = handleSubmit(({ username,  password}) => {
        dispatch(login({ username, password }));
    });

    return (
        <>
            <Button
                variant="contained"
                color="primary"
                onClick={handleOpen}
                data-testid="open-login-btn"
            >
                Login
            </Button>
            <Dialog
                open={open}
                onClose={handleClose}
                keepMounted={false}
                aria-labelledby="login-dialog-title"
                maxWidth="xs"
                fullWidth
            >
                <Close onClose={handleClose} />
                <DialogTitle id="login-dialog-title">Login</DialogTitle>
                <form onSubmit={onSubmit}>
                    <DialogContent>
                        {apiErrors?.non_field_errors && (
                            <Grow in timeout={100}>
                                <Alert
                                    severity="error"
                                >
                                    {apiErrors.non_field_errors?.map((errorMsg: any) => (
                                        <div key={errorMsg}>{errorMsg}</div>
                                    ))}
                                </Alert>
                            </Grow>
                        )}
                        <TextField
                            autoFocus
                            margin="dense"
                            id="username"
                            label="Username"
                            variant="outlined"
                            {...register("username", {
                                required: "this is required",
                                minLength: {
                                    value: 2,
                                    message: "Min length is 2"
                                }
                            })}
                            helperText={errors.username?.message}
                            error={Boolean(errors.username)}
                            fullWidth
                        />
                        <TextField
                            margin="dense"
                            id="password"
                            label="Password"
                            variant="outlined"
                            type="password"
                            {...register("password", {
                                required: "this is required"
                            })}
                            fullWidth
                        />
                        <FormActions>
                            <Button
                                variant="contained"
                                color="primary"
                                type="submit"
                                disabled={loading}
                                data-testid="submit-login-btn"
                            >
                                Login
                            </Button>
                        </FormActions>
                    </DialogContent>
                </form>
            </Dialog>
        </>
    );
};

export default LoginDialog;