import React, { Component } from 'react';
import Menu from '../Menu';
import axios from 'axios';
import { Link } from 'react-router-dom';
import {SelectButton} from 'primereact/components/selectbutton/SelectButton';


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

            }
        };
        this.loadFromServer = this.loadFromServer.bind(this);
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

    vote(){
        
    }

    render(){
        return(
            <div>
                <Menu />
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
                        <SelectButton value={this.state.vote} options={vote} onChange={this.vote.bind(this)}/>
                    </div>
                </div>
            </div>
        )
    }
}

//  <div dangerouslySetInnerHTML={{__html: this.state.text}} />
