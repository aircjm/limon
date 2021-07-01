import React from "react";
import NewTask from "./NewTask";
import {Route, Switch} from "react-router-dom";

const Task = (props: any) => {
    return (
        <React.Fragment>
            <Switch>
                <Route path="/task/new" component={NewTask} exact={true}/>
            </Switch>
        </React.Fragment>
    );
}

export default Task;