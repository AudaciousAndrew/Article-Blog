import React, { Component } from 'react';
import '../css/Menu.css';
import axios from "axios/index";
import '../css/Search.css';
import {Link} from 'react-router-dom';
import '../css/Tops.css';

const defaultAvatar = require('../resources/user.png');

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';


class Tops extends Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [],
            articles: []
        }
        this.loadUsers = this.loadUsers.bind(this);
        this.loadArticles = this.loadArticles.bind(this);
        this.loadUsers();
        this.loadArticles();
    }

    loadUsers() {
        axios.get(apiPath + '/user/top/10')
            .then((response) => {
                console.log(response.data);
                this.setState({users: response.data});
            })
            .catch((error) => {
                console.log(error);
            });
    }

    loadArticles() {
        axios.get(apiPath + '/article/top10')
            .then((response) => {
                console.log(response.data);
                this.setState({articles: response.data});
            })
            .catch((error) => {
                console.log(error);
            });
    }

    render(){
        let usersList = this.state.users.map((el, index) => {
            return <div key={index} className="topEntry">
                <label>{index + 1 + '.'}</label>
                <Link to={"/user/"+el.login}>{el.login}</Link>
            </div>;
        });

        let articlesList = this.state.articles.map((el, index) => {
            return <div key={index} className="topEntry">
                <label>{index + 1 + '.'}</label>
                <a href={"/article/"+el.articleName}>{el.articleName}</a>
            </div>;
        });


        return(
            <div className="tops">
                <div className="top10" id="t1">
                    <h3>Топ-10 пользователей</h3>
                    {usersList}
                </div>
                <div className="top10" id="t2">
                    <h3>Топ-10 статей</h3>
                    {articlesList}
                </div>
            </div>
        )
    }
}


export default Tops;