import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Menu from './Menu';
import {cookieFunctions} from '../cookieFunctions';
import axios from 'axios';
import UserInfo from './UserInfo';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

class UserAccount extends Component{

    constructor(props){
        super(props);
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
        console.log(this.props.match.params.id);
        return(
            <div>
                <Menu />
                <UserInfo login={this.props.match.params.id} />
            </div>
        )
    }
}

export default UserAccount;
