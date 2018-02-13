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
            user: cookieFunctions.getCookie('user'),
            message: ''
        };
        this.loadFromServer = this.loadFromServer.bind(this);
        this.checkVote = this.checkVote.bind(this);
        this.loadVote = this.loadVote.bind(this);
        this.checkVote();
        this.loadFromServer();
    }

    loadFromServer(){
      //  return new Promise((resolve, reject) => {
            axios({
                method:'post',
                url: apiPath+'/article/name',
                data: {
                    name: this.props.match.params.id
                }
            })
                .then((response) => {
                    console.log(response.data);
                    this.setState({article: response.data})
                    //resolve();
                }).catch(error => {
                console.log(error.message);
            });
      //  });
    }

    checkVote(){
        if(this.state.user === '')return false;
        var instance = axios.create({
            baseURL: apiPath
        });
        instance.defaults.headers.common['Authorization'] = 'Basic ' + cookieFunctions.getCookie('userToken');
        instance.post('/secured/vote/check/' + this.state.user,
            {
                name: this.props.match.params.id
            }
        )
            .then((response) => {
                console.log(response.data);
                if(response.data.vote === "true")
                this.setState({vote: "plus"}); else
                if(response.data.vote === "false")
                this.setState({vote: "minus"}); else
                this.setState({vote: ""});

            }).catch(error => {
            console.log(error.message);
        });

    }

    loadVote(){
            this.setState({message: ''});

            if(this.state.user === ''){
                this.setState({message: 'Голосовать могут только авторизованные пользователи'});
                return false
            }
            var instance = axios.create({
                baseURL: apiPath
            });
            instance.defaults.headers.common['Authorization'] = 'Basic ' + cookieFunctions.getCookie('userToken');
            instance.post('/secured/vote/'+this.state.vote+ '/' + cookieFunctions.getCookie('user'),
                {
                    name: this.state.article.articleName
                }
            )
                .then((response) => {
                    this.setState({checkResponse: response.data});
                    console.log(response.data);

                }).catch(error => {
                console.log(error.message);
            });

    }

    vote1(){
        this.setState({vote: "plus"}, () => {
            this.loadVote();
        })
    }

    vote2(){
        this.setState({vote: "minus"}, () => {
            this.loadVote();
        })
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
                    <div className="rating">
                        <label>Рейтинг: </label>
                        {this.state.article.rating}
                    </div>
                    <div className="vote">
                        <label>Вам понравилась эта статья? </label>
                        <div className="choice">
                            <div className="var">
                                <RadioButton id="rb1" checked={this.state.vote === "plus"}
                                       onChange={this.vote1.bind(this)}/>
                                <label htmlFor="rb1">Да</label>
                            </div>
                            <div className="var">
                                <RadioButton id="rb2" checked={this.state.vote === "minus"}
                                       onChange={this.vote2.bind(this)}/>
                                <label htmlFor="rb1">Нет</label>
                            </div>
                        </div>
                        <div className="mes">{this.state.message}</div>
                    </div>
                </div>
                    <Tops />
                </div>
            </div>
        )
    }
}

//  <div dangerouslySetInnerHTML={{__html: this.state.text}} />
