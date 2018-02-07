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
      msg: ''
    };
    this.loadFromServer = this.loadFromServer.bind(this);
    this.handleImgSubmit = this.handleImgSubmit.bind(this);
    this.handlePasswordSubmit = this.handlePasswordSubmit.bind(this);
    this.handleUserSubmit = this.handleUserSubmit.bind(this);
    this.loadFromServer();
  }

  loadFromServer() {
      axios.get(apiPath + '/user/' + this.state.login , {
        params: {
          login: this.props.login
        }
      })
      .then((response) => {
        this.setState({user: response.data});
        console.log(this.state.user);
      //  resolve();
      })
      .catch((error) => {
        console.log(error);
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

  handlePasswordSubmit(){
    this.setState({msg: ''});
    if(this.state.old !== this.state.user.password)
      this.setState({msg: 'Неверный пароль'});
    if(this.state.newp !== this.state.confirm)
      this.setState({msg: 'Пароли не совпадают'});

    return new Promise((resolve, reject) => {
        var instance = axios.create({
            baseURL: apiPath
        });
        instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
            instance.post('/secured/user/update',
              {
                  login: this.state.login,
                  password: this.state.confirm,
                  email: this.state.user.email,
                  firstname: this.state.user.firstname,
                  lastname: this.state.user.lastname,
                  rating: this.state.user.rating,
                  description: this.state.user.description,
                  avatarpath: this.state.user.avatarpath
              }
          )
              .then((response) => {
                  console.log(response.data);
                  resolve();
              }).catch(error => {
              console.log(error.message);
          });
      });
  }

  handleUserSubmit(){

  }

  render(){
    let path = 'http://localhost:8080/kursa4_war_exploded/rest/user/load/' + this.state.login;
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
        </div>
      </div>
    )
  }
}

export default UserSettings;
