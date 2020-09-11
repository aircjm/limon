/*
* @file index.jsx
* @author jansora
* @date 2020/2/5
*/


import React from "react";

import {Route, Switch} from "react-router-dom";
import RecordList from "./list";


const Record = () => {

    return (
        <Switch>
            <Route path="/record" component={RecordList} exact={true}/>
            {/*<Route path="/post/new" component={EditPost} exact={true}/>*/}
            {/*<Route path="/post/:resource/edit" component={EditPost} exact={true}/>*/}
        </Switch>
    )

};

export default Record;
