import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import { createStore } from 'redux';
import App from './App';
import News from './components/News';
import RegistrationForm from './components/RegistrationForm';
import LoginForm from './components/LoginForm';
import RegistrationSuccessful from './components/RegistrationSuccessful';

import { BrowserRouter, Route } from 'react-router-dom';


const initialState = [
  'Disowned',
  'Seen it all'
];

function playlist(state = initialState, action){
  if(action.type === 'ADD_TRACK'){
    return [
      ...state,
      action.payload
    ];
  }
  return state;
}

const store = createStore(playlist, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());

ReactDOM.render(
  <Provider store={store}>
    <BrowserRouter>
      <div>
        <Route exact path="/" component={App} />
        <Route path="/news" component={News} />
        <Route path="/signup" component={RegistrationForm} />
        <Route path="/signin" component={LoginForm} />
        <Route path="/regsuccess" component={RegistrationSuccessful} />
      </div>
    </BrowserRouter>
    </Provider>,
  document.getElementById('root'));
