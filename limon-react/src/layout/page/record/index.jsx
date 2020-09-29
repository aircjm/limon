/*
* @file index.jsx
* @author jansora
* @date 2020/2/5
*/


import React from "react";

import {Route, Switch} from "react-router-dom";
import RecordList from "./list";
import RecordEdit from "./edit";


const Record = () => {

    return (
        <Switch>
            <Route path="/record" component={RecordList} exact={true}/>
            <Route path="/record/edit" component={RecordEdit} exact={true}/>
        </Switch>
    )

};

export default Record;
