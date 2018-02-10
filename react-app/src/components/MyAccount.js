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
  }

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
