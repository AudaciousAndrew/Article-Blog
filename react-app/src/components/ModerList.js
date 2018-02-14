import React, { Component } from 'react';
import '../css/Article.css';
import AuthorMiniInfo from './articles/AuthorMiniInfo';
import axios from "axios/index";
import {cookieFunctions} from "../cookieFunctions";

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

export default class ModerList extends Component{

    constructor(props){
        super(props);
        this.state = {
            login: cookieFunctions.getCookie('user'),
            userToken: cookieFunctions.getCookie('userToken'),
        }
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleSubmit2 = this.handleSubmit2.bind(this);
    }

    handleSubmit(e){
        let arName = e.target.id;
        return new Promise((resolve, reject) => {

            var instance = axios.create({
                baseURL: apiPath
            });
            instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
            instance.post('/secured/article/moderator/delete', {
                name: arName
            })
                .then((response) => {
                    console.log(response.data);
                    this.setState({articlesUnverified: response.data});
                    window.location.reload();
                    resolve();

                }).catch(error => {
                console.log(error.message);
            });
        });
    }

    handleSubmit2(e){
        let arName = e.target.id;
        return new Promise((resolve, reject) => {

            var instance = axios.create({
                baseURL: apiPath
            });
            instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
            instance.post('/secured/article/approve', {
                name: arName
            })
                .then((response) => {
                    console.log(response.data);
                    this.setState({articlesUnverified: response.data});
                    window.location.reload();
                    resolve();

                }).catch(error => {
                console.log(error.message);
            });
        });
    }

    render(){
        let list = this.props.articles.map((el, index) => {
            return <div className="myListItem" key={index}>
                <AuthorMiniInfo  article={el} />
                <input type="submit" value="Опубликовать" id={el.articleName}
                       className="submitBtn" onClick={this.handleSubmit2} />
                <input type="submit" value="Удалить" id={el.articleName}
                       className="submitBtn" onClick={this.handleSubmit} />
            </div>
        });
        return(
            <div>
                {list}
            </div>
        )
    }
}

//          <div dangerouslySetInnerHTML={{__html: this.state.text}} />
