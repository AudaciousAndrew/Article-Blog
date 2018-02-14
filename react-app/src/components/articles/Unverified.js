import React, { Component } from 'react';
import Menu from '../Menu';
import axios from 'axios';
import { Link } from 'react-router-dom';
import {cookieFunctions} from '../../cookieFunctions';
import {RadioButton} from 'primereact/components/radiobutton/RadioButton';
import Tops from '../Tops';

const vote = [
    {label: 'Да', value: 'plus'},
    {label: 'Нет', value: 'minus'}
];

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

export default class Article extends Component{


    constructor(props){
        super(props);
        this.state = {
            article: {

            },
            userToken: cookieFunctions.getCookie('userToken'),
            message: ''
        };
        this.loadFromServer = this.loadFromServer.bind(this);
        this.loadFromServer();
    }

    loadFromServer(){
        let arName = this.props.match.params.id;
        return new Promise((resolve, reject) => {

            var instance = axios.create({
                baseURL: apiPath
            });
            instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
            instance.post('/secured/article/name/unverified', {
                name: arName
            })
                .then((response) => {
                    console.log(response.data);
                    this.setState({article: response.data});
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
                <div className="flex-container">
                    <div className="articleInfo">
                        <div className="articleName">
                            {this.state.article.articleName}
                        </div>
                        <div className="author">
                            <label>Автор: </label>
                            <Link to={'/user/' + this.state.article.author}>
                                {this.state.article.author}
                            </Link>
                        </div>
                        <div className="article">
                            <div dangerouslySetInnerHTML={{__html: this.state.article.articleDesc}} />
                        </div>
                    </div>
                    <Tops />
                </div>
            </div>
        )
    }
}

//  <div dangerouslySetInnerHTML={{__html: this.state.text}} />
