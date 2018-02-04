import React, { Component } from 'react';
import Menu from './Menu';
import '../css/SignUp.css';
import  { cookieFunctions } from '../cookieFunctions';

class LoginForm extends Component{
  constructor(props){
    super(props);
    this.state = {
      email: '',
      login: '',
      password: '',
      error: ''
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleLoginChange = this.handleLoginChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
  }

  handleSubmit(event){
    event.preventDefault();
    console.log('submitted');
    cookieFunctions.setCookie('user', this.state.login, 1);
    alert(document.cookie);
    this.props.history.push("/");
  }

  handleLoginChange(event){
    this.setState({ login: event.target.value});
  }

  handlePasswordChange(event){
    this.setState({ password: event.target.value});
  }

  render(){
    return (
      <div>
        <Menu />
        <div className="mainContainer">
          <form className="mainForm" onSubmit={this.handleSubmit}>
          <h2>Войти в аккаунт</h2>
          <div className="container">
            <input
              type="text"
              placeholder="Логин"
              value={this.state.login}
              onChange={this.handleLoginChange}
              className="floatLabel"
            />
            <input
              type="password"
              placeholder="Пароль"
              value={this.state.password}
              onChange={this.handlePasswordChange}
              className="floatLabel"
            />
            <div className="error"> <h3>{this.state.error}</h3> </div>
        		<input type="submit" value="Войти в аккаунт" id="submit" onClick={this.handleSubmit}/>
            <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
          </div>
          </form>
        </div>
      </div>
    )
  }

}

export default LoginForm;
