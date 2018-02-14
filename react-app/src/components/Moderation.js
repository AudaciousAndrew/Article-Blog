import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Menu from './Menu';
import {cookieFunctions} from '../cookieFunctions';
import axios from 'axios';
import '../css/UserSettings.css';
import ModerList from './ModerList';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

class Moderation extends Component{

    constructor(props){
        super(props);
        this.state = {
            login: cookieFunctions.getCookie('user'),
            userToken: cookieFunctions.getCookie('userToken'),
            role: cookieFunctions.getCookie('role'),
            user: {
            },
            articles: []
        };
        this.loadFromServer = this.loadFromServer.bind(this);
        this.loadFromServer();
    }

    componentDidMount(){
        if(typeof this.state.role === 'undefined' || this.state.role === '1')
            this.props.history.push('/accessdenied');
    }

    loadFromServer() {
        var instance = axios.create({
            baseURL: apiPath
        });
        instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
        instance.get('/secured/article/all/unverified')
            .then((response) => {
                console.log(response.data);
                this.setState({articles: response.data});
                //  resolve();
            })
            .catch((error) => {
                console.log(error);
            });
    }


    render(){
        return(
            <div>
                <Menu />
                <div className="unverified">
                    <h2>В очереди на модерацию</h2>
                    <ModerList articles={this.state.articles}/>
                </div>
            </div>
        )

    }
}

export default Moderation;
