import React, {useState} from 'react'
import './App.css'
import {Route, Switch} from "react-router-dom";
import GetPath from "./hooks/GetPath";
import CssBaseline from "@material-ui/core/CssBaseline";
import Link from '@material-ui/core/Link';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import Task from "./layout/task/task";

function App() {
  const [activeNav, setActiveNav] = useState(false);
  const path = GetPath();

  const setActiveNavHandle = (value) => {
    if (path === "/notes/new" || (path.startsWith("/notes") && path.endsWith("edit"))) {
      return
    }
    setActiveNav(value)
  }


  const [open, setOpen] = React.useState(false);

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  return (
      <React.Fragment>
        <div className="App">
          <CssBaseline/>
          <AppBar position="relative">
            <Toolbar>
              <IconButton edge="start" color="inherit" aria-label="menu">
                <MenuIcon/>
              </IconButton>
              <Typography variant="h6">
                News
              </Typography>
              <Button color="inherit">Login</Button>
            </Toolbar>
          </AppBar>
          <Switch>
            {/*<Route path="/notes" component={Notes} exact={false}/>*/}
            <Route path="/task" component={Task} exact={false}/>
            {/*<Route path="/about" component={About} exact={false}/>*/}
            {/*<Route path="/login" component={Login} exact={false}/>*/}
          </Switch>
          {/*          <NavBar activeNav={activeNav} setActiveNav={setActiveNavHandle}/>
          <LayoutWrapper activeNav={activeNav}>

          </LayoutWrapper>*/}
          <Copyright/>
          {/*<Hooks/>*/}
        </div>
      </React.Fragment>
  )
}


function Copyright(props) {
  return (
      <Typography variant="body2" color="text.secondary" align="center" {...props}>
        {'Copyright © '}
        <Link color="inherit" href="https://material-ui.com/">
          Your Website
        </Link>{' '}
        {new Date().getFullYear()}
        {'.'}
      </Typography>
  );
}

export default App
