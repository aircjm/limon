import React from 'react';
import {Head, Section} from "../../../components/styled/frameworks";
import {Route, Switch} from "react-router-dom";
import Current from "./Current";

const UserInfo = (props) => {
    return <React.Fragment>
        <Head>
            <h3> 用户信息 </h3>
        </Head>
        <Section style={{padding: "100px 20%"}}>
            <Switch>
                <Route path="/about/user/current" component={() => <Current/>} exact={false}/>
            </Switch>
        </Section>
    </React.Fragment>
}

export default UserInfo;
