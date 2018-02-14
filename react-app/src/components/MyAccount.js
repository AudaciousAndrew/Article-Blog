import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Menu from './Menu';
import {cookieFunctions} from '../cookieFunctions';
import axios from 'axios';
import UserInfo from './UserInfo';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

class MyAccount extends Component{

  constructor(props){
    super(props);
    this.state = {
        role: cookieFunctions.getCookie('role')
    }
  }

  componentDidMount(){
      if(typeof this.state.role === 'undefined')
          this.props.history.push('/accessdenied');
  }

  //
  // loadFromServer() {
  //   return new Promise((resolve, reject) => {
  //     var instance = axios.create({
  //       baseURL: apiPath
  //     });
  //     instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
  //     instance.get('/secured/test', {
  //     })
  //     .then(function (response) {
  //       console.log(response);
  //       console.log(response.data.firstName);
  //     })
  //     .catch(function (error) {
  //       console.log(error);
  //     });
  //   });
  // }

  render(){
    return(
      <div>
        <Menu />
        <UserInfo login={cookieFunctions.getCookie('user')} />
      </div>
    )
  }
}

export default MyAccount;
