import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
// import './components/styled/semantic-ui-css/semantic.css'
import App from './App';
import reportWebVitals from './reportWebVitals';
import GlobalStoreProvider from "./store/global";
import {BrowserRouter} from 'react-router-dom';

ReactDOM.render(
  <React.Fragment>
    <GlobalStoreProvider>
      <BrowserRouter>
        <App/>
      </BrowserRouter>
    </GlobalStoreProvider>
  </React.Fragment>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
