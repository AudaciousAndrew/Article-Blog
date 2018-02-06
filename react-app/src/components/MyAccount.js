import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Menu from './Menu';
import {cookieFunctions} from '../cookieFunctions';
import axios from 'axios';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';


class MyAccount extends Component{

  constructor(props){
    super(props);
    this.state = {
      login: cookieFunctions.getCookie('user'),
      userToken: cookieFunctions.getCookie('userToken'),
      user: null
    };
    this.loadFromServer = this.loadFromServer.bind(this);
    this.loadFromServer();
  }


  loadFromServer() {
    return new Promise((resolve, reject) => {
      var instance = axios.create({
        baseURL: apiPath
      });
      instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
      instance.get('/secured/test', {
      })
      .then(function (response) {
        console.log(response);
        console.log(response.data.firstName);
      })
      .catch(function (error) {
        console.log(error);
      });
    });
  }

  // loadFromServer() {
  //   return new Promise((resolve, reject) => {
  //     axios.get(apiPath + '/user/' + this.state.login , {
  //       params: {
  //         login: this.state.login
  //       }
  //     })
  //     .then((response) => {
  //       console.log(response);
  //       console.log(response.data.login);
  //       this.setState({user: response.data});
  //       console.log(this.state.user);
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  //   });
  // }


  render(){
    return(
      <div>
        <Menu />
        <div className="successDiv">
        </div>
      </div>
    )
  }
}

export default MyAccount;
