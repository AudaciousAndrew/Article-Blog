import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import { createStore } from 'redux';
import App from './App';
import RegistrationForm from './components/RegistrationForm';
import LoginForm from './components/LoginForm';
import RegistrationSuccessful from './components/RegistrationSuccessful';
import Footer from './components/Footer';
import MyAccount from './components/MyAccount';
import Games from './components/articles/Games';
import Movies from './components/articles/Movies';
import TVSeries from './components/articles/TVSeries';
import Anime from './components/articles/Anime';
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
        <Route path="/signup" component={RegistrationForm} />
        <Route path="/signin" component={LoginForm} />
        <Route path="/regsuccess" component={RegistrationSuccessful} />
        <Route path="/myAccount" component={MyAccount} />
        <Route path="/games" component={Games} />
        <Route path="/movies" component={Movies} />
        <Route path="/tvshows" component={TVSeries} />
        <Route path="/anime" component={Anime} />
      </div>
    </BrowserRouter>
    </Provider>,
document.getElementById('root'));

ReactDOM.render(
  <Footer />,
document.getElementById('footer'));
