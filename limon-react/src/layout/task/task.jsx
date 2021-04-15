import React from "react";
import NewTask from "./NewTask";
import {Route, Switch} from 'react-router-dom'

const Task = (props) => {


    return <React.Fragment>
        <Switch>
            <Route path="/task/new" component={NewTask} exact={true}/>
            {/*<Route path="/notes/:id/edit" component={ModifyNote} exact={true} />*/}

            {/*<Route path="/notes/:id" component={Note} exact={true} />*/}

        </Switch>
    </React.Fragment>;
}

export default Task;