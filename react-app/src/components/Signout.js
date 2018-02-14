import React, { Component } from 'react';
import '../css/Menu.css';
import { Redirect } from 'react-router-dom';
import  { cookieFunctions } from '../cookieFunctions';

export default class Signout extends Component {

    static signOut(){
        cookieFunctions.setCookie('user', '', -1);
        cookieFunctions.setCookie('userToken', '', -1);
        cookieFunctions.setCookie('role', '', -1);

        console.log(document.cookie);
    }

    render(){
        Signout.signOut();
        return(
            <Redirect to="/home" />
        )
    }
}
