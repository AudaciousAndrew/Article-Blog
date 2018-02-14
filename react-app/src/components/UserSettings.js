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
      jabber: '',
      imgMsg: '',
      imgStatus: '',
      msgStatus: '',
      errStatus: ''
    };
    this.loadFromServer = this.loadFromServer.bind(this);
    this.handleImgSubmit = this.handleImgSubmit.bind(this);
    this.handlePasswordSubmit = this.handlePasswordSubmit.bind(this);
    this.handleUserSubmit = this.handleUserSubmit.bind(this);
    this.login = this.login.bind(this);
    this.checkPassword = this.checkPassword.bind(this);
    this.loadFromServer();
  }

    componentDidMount(){
        if(this.state.userToken === '')
            this.props.history.push('/accessdenied');
    }

  loadFromServer() {
      axios.get(apiPath + '/user/' + this.state.login , {
        params: {
          login: this.props.login
        }
      })
      .then((response) => {
        this.user = response.data;
          let {firstname, lastname, email, description, jabber} = this.user;
          this.setState({email: email});
          if(typeof firstname !== 'undefined')this.setState({firstname: firstname});
          if(typeof lastname !== 'undefined')this.setState({lastname: lastname});
          if(typeof description !== 'undefined')this.setState({desc: description});
          if(typeof jabber !== 'undefined')this.setState({jabber: jabber});
          console.log(document.cookie);
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
                login: this.state.login,
                password: this.state.confirm
            }
        })
            .then((response) => {
                if(response.data.authorization === 0)
                    this.setState({error: "Неправильный логин или пароль"});
                else {
                    cookieFunctions.setCookie('user', response.data.login, 1);
                    cookieFunctions.setCookie('userToken', response.data.token, 1);
                    window.location.reload();
                }
            }).catch(error => {
            console.log(error.message);
        });
    }

  checkPassword(){
      return new Promise((resolve, reject) => {

          var instance = axios.create({
              baseURL: apiPath
          });
          instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
          instance.post('/secured/user/changepass',
              {
                  login: this.user.login,
                  password: this.state.old
              }
          )
              .then((response) => {
                  this.setState({checkResponse: response.data});
                  console.log(response.data);
                  resolve();

              }).catch(error => {
              console.log(error.message);
          });
      });
  }

  handleImgSubmit(event){
    var xxx = new FormData();
    if(typeof document.getElementById('Image').files[0] === 'undefined'){
        this.setState({imgMsg: "Выберите изображение", imgStatus: "false"});
        return;
    };
    setTimeout(()=> {
        xxx.append('file', document.getElementById('Image').files[0]);
        axios({
            method: 'post',
            headers: {'content-type': 'multipart/form-data'},
            url: 'http://localhost:8080/kursa4_war_exploded/rest/user/load/' + this.state.login,
            data: xxx
        })
            .then((response) => {
                if (response.data === 1) this.setState({imgMsg: "Аватар успешно изменён", imgStatus: "true"});
                else this.setState({imgMsg: "Доступные форматы изображений: .jpg, .jpeg, .png", imgStatus: "false"});
            })
            .catch((error) => {
                console.log(error);
            });
    }, 500);
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
    this.setState({msg: '', msgStatus: ''});
    this.checkPassword();
    setTimeout(()=>{

      console.log(this.state.checkResponse);
    if(this.state.checkResponse === 0){
        this.setState({msg: 'Неверный пароль', msgStatus: 'false'});
        return false;
    }
    if(this.state.newp !== this.state.confirm) {
            this.setState({msg: 'Пароли не совпадают', msgStatus: 'false'});
            return false;
    }
    if(this.state.newp.length < 3 || this.state.newp.length > 20){
         this.setState({msg: 'Длина пароля должна быть от 3 до 20 символов', msgStatus: 'false'});
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
                  this.setState({password : this.state.confirm, msg:'Пароль успешно изменён', msgStatus: 'true'});
                  this.login();
                  resolve();
              }).catch(error => {
              console.log(error.message);
          });
      });
    }, 500);
  }

  emailCheck(){
    var res = false;
    let cnt = 0;
    for(var i=0; i < this.state.email.length; i++){
        if(this.state.email[i] === '@')cnt++;
    }
    if(cnt === 1)res = true;
    if (/[^0-9a-zA-z\-_@.а-яА-я]/.test(this.state.email)) res = false;
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
    this.setState({error: '', errStatus: ''});
    if(!this.emailCheck()){
        this.setState({error: 'Неверный формат email', errStatus: 'false'});
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
                  email: this.state.email
              }
          )
              .then((response) => {
                  if(response.data === 'email exists')
                    this.setState({error: 'Пользователь с таким email уже существует', errStatus: 'false'});
                  else this.setState({error: 'Email успешно изменён', errStatus: 'true'});

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
                 if(response.data === 'updated')
                    this.setState({mes: 'Изменения успешно сохранены'});
              }).catch(error => {
              console.log(error.message);
          });
      });
  }


  previewFile() {
       this.setState({imgMsg: ''});
       var preview1 = document.getElementById('uploadPreview1');
       var preview2 = document.getElementById('uploadPreview2');
       var preview3 = document.getElementById('uploadPreview3');

       var file = document.querySelector('input[type=file]').files[0];
       var reader  = new FileReader();
       reader.onloadend = function () {
          preview1.src = reader.result;
          preview2.src = reader.result;
          preview3.src = reader.result;
       }

       if (file) {
           reader.readAsDataURL(file);
       } else {
           preview1.src = "";
           preview2.src = "";
           preview3.src = "";
       }
  }

  render(){
    return(
      <div>
        <Menu />
        <div className="settings">
          <div className="avatarUpload">
            <h3>Изменить аватар</h3>
              <div className="previews">
                <img id="uploadPreview1" className="preview" style={{width: '150px', height: '150px'}} />
                <img id="uploadPreview2" className="preview" style={{width: '100px', height: '100px'}} />
                <img id="uploadPreview3" className="preview" style={{width: '50px', height: '50px'}} />

              </div>
              <div className="loadImg">
                  <input type="file" name="file" id="Image"  onChange={this.previewFile.bind(this)} />
                  <input type="submit" className="submitBtn" value="Сохранить" onClick={this.handleImgSubmit}/>
                  <div className="imgMsg" id={this.state.imgStatus}>{this.state.imgMsg}</div>
              </div>
          </div>
          <div className="passwordChange">
            <h3>Изменить пароль</h3>
            <div className="pass">
                Старый пароль:
                <input type="password" onChange={this.handleOldChange.bind(this)} />
            </div>
            <div className="pass">
                Новый пароль:
                <input type="password" onChange={this.handleNewChange.bind(this)} />
            </div>
            <div className="pass">
                Повторите пароль:
                <input type="password" onChange={this.handleConfChange.bind(this)} />
            </div>
            <input type="submit" value="Изменить пароль" className="submitBtn" onClick={this.handlePasswordSubmit} />
            <p id={this.state.msgStatus}>{this.state.msg}</p>
          </div>
          <div className="emailChange">
            <h3>Изменить адрес электронной почты</h3>
            <div className="email">
                Новый email:
                <input type="text" placeholder="Email" value={this.state.email} maxLength="50" onChange={this.handleEmailChange.bind(this)} />
            </div>
            <input type="submit" value="Изменить email" className="submitBtn" onClick={this.handleEmailSubmit.bind(this)} />
            <p id={this.state.errStatus}>{this.state.error}</p>
          </div>
          <div className="infoChange">
            <h3> Изменить личные данные </h3>
            <div className="box-1">
                Имя:
              <input type="text" id="fname" value={this.state.firstname} maxLength="50" onChange={this.fname.bind(this)} />
            </div>
            <div className="box-2">
                Фамилия:
              <input type="text" id="lname" value={this.state.lastname} maxLength="50" onChange={this.lname.bind(this)} />
            </div>
            <div className="box-4">
                Логин jabber.ru:
                <input type="text" id="jabber" value={this.state.jabber} maxLength="50" onChange={this.jabber.bind(this)} />
            </div>
            <div className="box-3">
              <label>О себе:</label>
              <textarea id="desc" value={this.state.desc}
                        maxLength="200" style={{resize: 'none'}} onChange={this.desc.bind(this)} />
            </div>
              <input type="submit" value="Сохранить" className="submitBtn" onClick={this.handleUserSubmit}/>
            <p style={{color: 'green'}}>{this.state.mes}</p>
          </div>
        </div>
      </div>
    )
  }
}

export default UserSettings;
