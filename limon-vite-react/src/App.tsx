import React, { useState } from 'react'
import './App.css'
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import Task from "./pages/task/task";

function App() {

  return (
      <React.Fragment>
          <BrowserRouter>
              <Switch>
                  <Redirect from="/" to="/home" exact={true} />
                  <Route path={"/home"} component={Home} exact={false}/>
                  <Route path="/task" component={Task} exact={false}/>
                  {/*<Route path="/login" component={Login} exact={false}/>*/}
              </Switch>
          </BrowserRouter>
      </React.Fragment>
  )
}

export default App
