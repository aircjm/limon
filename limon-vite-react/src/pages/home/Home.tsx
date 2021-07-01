import React from "react";
import {Route, Switch} from "react-router-dom";
import NewTask from "../task/NewTask";
import Login from "../auth/login";

export const Home = () => {


    return (
        <React.Fragment>
            <Switch>
                <Route path="/login" component={Login} exact={true}/>
            </Switch>
        </React.Fragment>
    );


}