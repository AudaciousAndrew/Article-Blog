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
import UserSettings from './components/UserSettings';
import Games from './components/articles/Games';
import Movies from './components/articles/Movies';
import TVSeries from './components/articles/TVSeries';
import Anime from './components/articles/Anime';
import Users from './components/Users';
import AddArticle from './components/AddArticle';
import UserAccount from './components/UserAccount'
import Article from './components/articles/Article';
import reducer from './reducers';
import Author from './components/articles/Author';
import Signout from './components/Signout';
import NotFound from './components/NotFound';
import MyArticles from './components/MyArticles';
import Administration from './components/Administration';
import Moderation from './components/Moderation';
import Unverified from './components/articles/Unverified';
import AccessDenied from './components/AccessDenied';
import { BrowserRouter, Route, Switch } from 'react-router-dom';


const store = createStore(reducer, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());

ReactDOM.render(
  <Provider store={store}>
    <BrowserRouter baseName="">
      <div>
          <Switch>
          <Route exact path="/" component={App} />
          <Route exact path="/home" component={App} />
          <Route exact path="/signup" component={RegistrationForm} />
          <Route exact path="/signin" component={LoginForm} />
          <Route exact path="/regsuccess" component={RegistrationSuccessful} />
          <Route exact path="/myAccount" component={MyAccount} />
          <Route exact path="/settings" component={UserSettings} />
          <Route exact path="/games" component={Games} />
          <Route exact path="/movies" component={Movies} />
          <Route exact path="/tvshows" component={TVSeries} />
          <Route exact path="/anime" component={Anime} />
          <Route exact path="/users" component={Users} />
          <Route exact path="/add" component={AddArticle} />
          <Route exact path="/user/:id" component={UserAccount}/>
          <Route exact path="/author/:id" component={Author} />
          <Route exact path="/article/:id" component={Article}/>
          <Route exact path="/unverified/:id" component={Unverified}/>
          <Route exact path="/signout" component={Signout} />
          <Route exact path="/myArticles" component={MyArticles} />
          <Route exact path="/moderation" component={Moderation} />
          <Route exact path="/administration" component={Administration} />
          <Route exact path="/accessdenied" component={AccessDenied} />
          <Route component={NotFound} />
          </Switch>
      </div>
    </BrowserRouter>
    </Provider>,
document.getElementById('root'));

ReactDOM.render(
  <Footer />,
    document.getElementById('footer'));
