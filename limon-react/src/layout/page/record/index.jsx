

import React from "react";

import {Route, Switch} from "react-router-dom";
import RecordList from "./list";
import RecordEdit from "./RecordEdit";


const Record = () => {

    return (
        <Switch>
            <Route path="/record" component={RecordList} exact={true}/>
            <Route path="/record/edit" component={RecordEdit} exact={true}/>
        </Switch>
    )

};

export default Record;
