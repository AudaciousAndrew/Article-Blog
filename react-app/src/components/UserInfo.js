import React, { Component } from 'react';
import axios from 'axios';
import '../css/UserInfo.css';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

class MyAccount extends Component{

  constructor(props){
    super(props);
    this.state = {
      user: {
        //login: ''
      }
    };
    this.loadFromServer = this.loadFromServer.bind(this);
    this.getInfo = this.getInfo.bind(this);
    this.loadFromServer();
  }

  loadFromServer() {
      axios.get(apiPath + '/user/' + this.props.login , {
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
  
  getInfo(){
    this.login = <p>Никнейм: {this.state.user.login}</p>;
    this.userName = <h2>{this.state.user.firstname} {this.state.user.lastname}</h2>
    this.email = <p>E-mail: {this.state.user.email}</p>
    this.rating = <p>Рейтинг: {this.state.user.rating}</p>;
    this.jabber = <p>Jabber: {this.state.user.jabber}</p>
    this.desc = <p>{this.state.user.description}</p>;
    console.log(`${this.state.user.avatarpath}`);
    const path = "http://localhost:8080/kursa4_war_exploded/rest/user/load/"+this.state.user.login;
    console.log(typeof this.state.user.avatarpath === 'undefined');
    //const img = require(path);
    if(typeof this.state.user.avatarpath !== 'undefined')  this.avatar = <img src={"http://localhost:8080/"+this.state.user.login} alt=""  width={'150px'} height={'150px'} />;
    else this.avatar = <img src={require('../resources/user.png')} alt="" height={'150px'}  width={'150px'} />;
  }

  render(){
    this.getInfo();
    return(
      <div>
        <div className="infoDiv">
          <div className="mainDiv">
            <div className="imgDiv">
              {this.avatar}
            </div>
            <div className="mainInfo">
                {this.userName}
                {this.login}
                {this.email}
                {this.jabber}
                {this.rating}
            </div>
          </div>
          <div className="moreInfo">
            <h3>О себе</h3>
            <hr />
            {this.desc}
          </div>
        </div>
      </div>
    )
  }
}

export default MyAccount;
