import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Menu from './Menu';

class RegistrationSuccessful extends Component{

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
