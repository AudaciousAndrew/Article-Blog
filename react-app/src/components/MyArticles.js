import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Menu from './Menu';
import {cookieFunctions} from '../cookieFunctions';
import axios from 'axios';
import '../css/UserSettings.css';
import MyArticlesList from "./MyArticlesList";

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

class UserSettings extends Component{

    constructor(props){
        super(props);
        this.state = {
            login: cookieFunctions.getCookie('user'),
            userToken: cookieFunctions.getCookie('userToken'),
            user: {
            },
            articlesVerified: [],
            articlesUnverified: []
        };
        this.loadFromServer = this.loadFromServer.bind(this);
        this.loadFromServer2 = this.loadFromServer2.bind(this);
        this.loadFromServer();
        this.loadFromServer2();
    }

    loadFromServer() {
        axios.get(apiPath + '/article/' + this.state.login + '/all')
            .then((response) => {
                console.log(response.data);
                this.setState({articlesVerified: response.data});
                //  resolve();
            })
            .catch((error) => {
                console.log(error);
            });
    }

    loadFromServer2(){
        return new Promise((resolve, reject) => {

            var instance = axios.create({
                baseURL: apiPath
            });
            instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
            instance.post('/secured/article/all/'+this.state.login+'/unverified')
                .then((response) => {
                    console.log(response.data);
                    this.setState({articlesUnverified: response.data});
                    resolve();

                }).catch(error => {
                console.log(error.message);
            });
        });
    }



    render(){
        return(
            <div>
                <Menu />
                <div className="verified">
                    <h2>Опубликованные</h2>
                    <MyArticlesList articles={this.state.articlesVerified}/>
                </div>
                <div className="unverified">
                    <h2>В очереди на модерацию</h2>
                    <MyArticlesList articles={this.state.articlesUnverified}/>
                </div>
            </div>
        )

    }
}

export default UserSettings;
