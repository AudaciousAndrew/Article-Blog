import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Menu from './Menu';
import {cookieFunctions} from '../cookieFunctions';
import axios from 'axios';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';


class MyAccount extends Component{

  constructor(props){
    super(props);
    this.state = {userToken: cookieFunctions.getCookie('userToken')};
    this.loadFromServer = this.loadFromServer.bind(this);
  }

  componentDidMount(){
    this.loadFromServer();
  }

  loadFromServer() {
    return new Promise((resolve, reject) => {
      var instance = axios.create({
        baseURL: apiPath
      });
      instance.defaults.headers.common['Authorization'] = this.state.userToken;
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


  render(){
    return(
      <div>
        <Menu />
        <div className="successDiv">
          my account
        </div>
      </div>
    )
  }
}

export default MyAccount;
