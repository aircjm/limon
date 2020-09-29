import React from 'react';
import Layout from "./layout/layout";
import {Redirect, Route, Switch, useLocation} from 'react-router-dom'
import Header from "./layout/header";

const App = () => {
    const location = useLocation();
    const bootstrap = location.pathname.indexOf("bootstrap") > 0;
    return (
        <React.Fragment>
            <Header bootstrap={bootstrap}/>
            <Switch>
                <Redirect from="/" to="/home" exact={true}/>
                <Redirect from="/post" to="/post" exact={true}/>
                <Redirect from="/bootstrap/:production/redirect" to="/:production" exact={true}/>
                <Route path="/:production" component={Layout} exact={false}/>
            </Switch>
        </React.Fragment>
    );
};

export default App;
