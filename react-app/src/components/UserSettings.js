import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Menu from './Menu';
import {cookieFunctions} from '../cookieFunctions';
import axios from 'axios';
import '../css/UserSettings.css';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

class UserSettings extends Component{

  constructor(props){
    super(props);
    this.state = {
      login: cookieFunctions.getCookie('user'),
      userToken: cookieFunctions.getCookie('userToken'),
      user: {
      },
      old: '',
      newp: '',
      confirm: '',
      msg: '',
      firstname: '',
      lastname: '',
      desc: '',
      email: '',
      error: '',
      jabber: ''
    };
    this.loadFromServer = this.loadFromServer.bind(this);
    this.handleImgSubmit = this.handleImgSubmit.bind(this);
    this.handlePasswordSubmit = this.handlePasswordSubmit.bind(this);
    this.handleUserSubmit = this.handleUserSubmit.bind(this);
    this.login = this.login.bind(this);
    this.loadFromServer();
  }

  loadFromServer() {
      axios.get(apiPath + '/user/' + this.state.login , {
        params: {
          login: this.props.login
        }
      })
      .then((response) => {
        this.user = response.data;
        console.log(this.user);
          let {firstname, lastname, email, description, jabber} = this.user;
          this.setState({email: email});
          if(typeof firstname !== 'undefined')this.setState({firstname: firstname});
          if(typeof lastname !== 'undefined')this.setState({lastname: lastname});
          if(typeof description !== 'undefined')this.setState({desc: description});
          if(typeof jabber !== 'undefined')this.setState({jabber: jabber});
          //  resolve();
      })
      .catch((error) => {
        console.log(error);
      });
  }

    login(){
        const apiPath='http://localhost:8080/kursa4_war_exploded/rest/user/login';
        axios({
            method:'post',
            url: apiPath,
            data: {
                login: this.user.login,
                password: this.state.confirm
            }
        })
            .then((response) => {
                console.log(response);
                if(response.data.authorization === 0)
                    this.setState({error: "Неправильный логин или пароль"});
                else {
                    cookieFunctions.setCookie('user', response.data.login, 1);
                    cookieFunctions.setCookie('userToken', response.data.token, 1);
                    console.log(document.cookie);
                    window.location.reload();
                }
            }).catch(error => {
            console.log(error.message);
        });
    }

  handleImgSubmit(){
    var xxx = new FormData();
    xxx.append('file', document.getElementById('Image').files[0]);
    axios({
      method:'post',
      headers: { 'content-type': 'multipart/form-data'},
      url: 'http://localhost:8080/kursa4_war_exploded/rest/user/load/' + this.state.login,
      data: xxx
    })
    .then((response) => {
      console.log(response);
      window.location.reload();
    })
    .catch((error) => {
      console.log(error);
    });
  }

  handleOldChange(event){
    this.setState({old: event.target.value});
  }

  handleNewChange(event){
    this.setState({newp: event.target.value});
  }

  handleConfChange(event){
    this.setState({confirm: event.target.value});
  }


  handleEmailChange(event){
    this.setState({email: event.target.value});
  }

  handlePasswordSubmit(){
    this.setState({msg: ''});
    if(this.state.old !== this.user.password){
        this.setState({msg: 'Неверный пароль'});
        return false;
    }
    if(this.state.newp !== this.state.confirm) {
            this.setState({msg: 'Пароли не совпадают'});
            return false;
    }
    if(this.state.newp.length < 3 || this.state.newp.length > 20){
         this.setState({msg: 'Длина пароля должна быть от 3 до 20 символов'});
         return false;
    }

    return new Promise((resolve, reject) => {
        var instance = axios.create({
            baseURL: apiPath
        });
        instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
            instance.post('/secured/user/update',
              {
                  login: this.user.login,
                  password: this.state.confirm
              }
          )
              .then((response) => {
                  console.log(response.data);
                  this.login();
                  resolve();
              }).catch(error => {
              console.log(error.message);
          });
      });
  }

  emailCheck(){
    var res = false;
    let cnt = 0;
    for(var i=0; i < this.state.email.length; i++){
        if(this.state.email[i] === '@')cnt++;
    }
    if(cnt === 1)res = true;
    if (/[^0-9a-zA-z\-_@.]/.test(this.state.email)) res = false;
    return res;
  }

  fname(e){
    this.setState({firstname: e.target.value, mes: ''});
  }

  lname(e){
    this.setState({lastname: e.target.value, mes: ''});
  }

  desc(e){
    this.setState({desc: e.target.value, mes: ''});
  }

  jabber(e){
    this.setState({jabber: e.target.value, mes: ''});
  }

  handleEmailSubmit(){
    this.setState({error: ''});
    if(!this.emailCheck())this.setState({error: 'Неверный формат email'});
      return new Promise((resolve, reject) => {
          var instance = axios.create({
              baseURL: apiPath
          });
          instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
          instance.post('/secured/user/update',
              {
                  login: this.user.login,
                  email: this.state.email
              }
          )
              .then((response) => {
                  console.log(response.data);
                  if(response.data === 'email exists')
                    this.setState({error: 'Пользователь с таким email уже существует'});
                  resolve();
              }).catch(error => {
              console.log(error.message);
          });
      });
  }

  handleUserSubmit(){
    this.setState({mes: ''});
      return new Promise((resolve, reject) => {
          var instance = axios.create({
              baseURL: apiPath
          });
          instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
          instance.post('/secured/user/update',
              {
                  login: this.user.login,
                  firstname: this.state.firstname,
                  lastname: this.state.lastname,
                  description: this.state.desc,
                  jabber: this.state.jabber
              }
          )
              .then((response) => {
                  console.log(response.data);
                  if(response.data === 'updated')
                    this.setState({mes: 'Изменения успешно сохранены'});
              }).catch(error => {
              console.log(error.message);
          });
      });
  }

  render(){
    return(
      <div>
        <Menu />
        <div className="settings">
          <div className="avatarUpload">
            <h3>Load img on server</h3>
            <input type="file" name="file" id="Image" />
            <input type="submit" value="upload" onClick={this.handleImgSubmit}/>
          </div>
          <div className="passwordChange">
            <h3>passwordChange</h3>
            <input type="password" onChange={this.handleOldChange.bind(this)} />
            <input type="password" onChange={this.handleNewChange.bind(this)} />
            <input type="password" onChange={this.handleConfChange.bind(this)} />
            <input type="submit" onClick={this.handlePasswordSubmit} />
            <p>{this.state.msg}</p>
          </div>
          <div className="emailChange">
            <h3>email</h3>
            <input type="text" value={this.state.email} maxLength="50" onChange={this.handleEmailChange.bind(this)} />
            <input type="submit" onClick={this.handleEmailSubmit.bind(this)} />
            <p>{this.state.error}</p>
          </div>
          <div className="infoChange">
            <div className="box-1">
              <h3>имя мужик</h3>
              <input type="text" id="fname" value={this.state.firstname} maxLength="50" onChange={this.fname.bind(this)} />
            </div>
            <div className="box-2">
              <h3>фамилия таджик</h3>
              <input type="text" id="lname" value={this.state.lastname} maxLength="50" onChange={this.lname.bind(this)} />
            </div>
            <div className="box-3">
              <h3>хуй стоячком</h3>
              <textarea id="desc" value={this.state.desc} cols="40" rows="5"
                        maxLength="200" style={{resize: 'none'}} onChange={this.desc.bind(this)} />
            </div>
            <div className="box-4">
              <h3>жизнь ништячком</h3>
              <input type="text" id="jabber" value={this.state.jabber} maxLength="50" onChange={this.jabber.bind(this)} />
            </div>
              <input type="submit" value="Сохранить" onClick={this.handleUserSubmit}/>
            <p>{this.state.mes}</p>
          </div>
        </div>
      </div>
    )
  }
}

export default UserSettings;
