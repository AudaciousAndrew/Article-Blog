import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Menu from './Menu';
import {cookieFunctions} from "../cookieFunctions";

class RegistrationSuccessful extends Component{

    componentDidMount(){
        let role = cookieFunctions.getCookie('role');
        if(typeof role !== 'undefined') this.props.history.push('/home');
    }

  render(){
    return(
      <div>
        <Menu />
        <div className="successDiv">
          <h1>Ваш аккаунт успешно зарегистрирован</h1>
          <Link to='/signin'> Войти в аккаунт </Link>
        </div>
      </div>
    )
  }
}

export default RegistrationSuccessful;
