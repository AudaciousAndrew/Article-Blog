import React, { Component } from 'react';
import '../css/Menu.css';
import { Redirect } from 'react-router-dom';
import  { cookieFunctions } from '../cookieFunctions';

export default class Signout extends Component {

    static signOut(){
        cookieFunctions.setCookie('user', '', 0);
        cookieFunctions.setCookie('userToken', '', 0);
        console.log(document.cookie);
    }

    render(){
        Signout.signOut();
        return(
            <Redirect to="/" />
        )
    }
}
