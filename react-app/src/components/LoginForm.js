import React, { Component } from 'react';
import Menu from './Menu';
import '../css/SignUp.css';
import  { cookieFunctions } from '../cookieFunctions';
import { Link } from 'react-router-dom';
import axios from 'axios';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest/user/login';

class LoginForm extends Component{
  constructor(props){
    super(props);
    this.state = {
      email: '',
      login: '',
      password: '',
      error: '',
        role: cookieFunctions.getCookie('role')
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleLoginChange = this.handleLoginChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
    this.getRole = this.getRole.bind(this);
    this.login = this.login.bind(this);
  }

    componentDidMount(){
        if(typeof this.state.role !== 'undefined')
            this.props.history.push('/home');
    }

  login(){
    axios({
      method:'post',
      url: apiPath,
      data: {
        login: this.state.login,
        password: this.state.password
      }
    })
    .then((response) => {
      console.log(response);
      if(response.data.authorization === 0)
        this.setState({error: "Неправильный логин или пароль"});
      else {
        cookieFunctions.setCookie('user', response.data.login, 1);
        cookieFunctions.setCookie('userToken', response.data.token, 1);
        this.getRole();
        setTimeout(()=>{this.props.history.push("/")}, 500);
      }
    }).catch(error => {
      console.log(error.message);
    });
  }

  getRole(){
      let login = cookieFunctions.getCookie('user');

      axios({
          method:'post',
          url: 'http://localhost:8080/kursa4_war_exploded/rest/user/roles/' + login
      })
          .then((response) => {
              console.log(response);
              cookieFunctions.setCookie('role', response.data, 1);

          }).catch(error => {
          console.log(error.message);
      });
  }

  handleSubmit(event){
    event.preventDefault();
    console.log('submitted');
    this.login();
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
          <h2>Авторизация</h2>
          <div className="container">
            <input
              type="text"
              placeholder="Логин"
              value={this.state.login}
              maxLength="50"
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
            <Link to='/signup'>Зарегистрировать новый аккаунт</Link>
            <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
          </div>
          </form>
        </div>
      </div>
    )
  }

}

export default LoginForm;
