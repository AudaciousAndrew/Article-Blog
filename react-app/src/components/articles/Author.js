import React, { Component } from 'react';
import Menu from '../Menu';
import axios from 'axios';
import ArticlesList from './ArticlesList';
import Tops from '../Tops';
import '../../css/Article.css';
import '../../css/Search.css';
import {InputText} from 'primereact/components/inputtext/InputText';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

export default class Anime extends Component{


    constructor(props){
        super(props);
        this.state = {
            articles: [],
            text: '',
            pageNumber: 1,
            totalPages: 1,
            founded: []
        }
        this.loadFromServer = this.loadFromServer.bind(this);
        this.loadFromServer();
    }

    loadFromServer(){
        return new Promise((resolve, reject) => {
            axios({
                method:'get',
                url: apiPath+'/article/' + this.props.match.params.id +'/all',
            })
                .then((response) => {
                    console.log(response.data);
                    this.setState({articles: response.data});
                    resolve();
                }).catch(error => {
                console.log(error.message);
            });
        });
    }


    onChange(event){
        let c=event.target.value;
        if(c > 0 &&  c <= this.state.totalPages)
            this.setState({pageNumber: event.target.value},() => {
                this.loadFromServer();
            });
    }

    search(){
        let val = document.getElementById('search').value;

        return new Promise((resolve, reject) => {
            axios({
                method:'post',
                url: apiPath+'/article/search/name',
                data: {
                    name: val
                }
            })
                .then((response) => {
                    console.log(response.data);
                    let pgs = Math.floor(response.data.length / 10);
                    if(response.data.length % 10 !== 0) ++pgs;
                    this.setState({founded: response.data});
                    resolve();
                }).catch(error => {
                console.log(error.message);
            });
        });
    }

    checkSearch(){
        let val = document.getElementById('search').value;
        if(val.length === 0){
            this.setState({founded: null});
        }
    }


    checkSearch(){
        let val = document.getElementById('search').value;
        if(val.length === 0){
            this.setState({founded: null});
        }
    }

    render(){
        let list = [];
        if(this.state.totalPages !== 0){
            list = this.state.articles;
        }
        if(this.state.founded !== null && this.state.founded.length !== 0){
            list=this.state.founded;
        }
        return(
            <div>
                <Menu />
                <div>
                    <div className="articleSearch">
                        <input type="search" name="" id="search"
                               placeholder="Поиск по статьям" className="input" onChange={this.checkSearch.bind(this)} />
                        <input type="submit" name="" value="" className="submit" onClick={this.search.bind(this)}/>
                    </div>
                </div>

                <div className="flex-container">
                    <div className="articlesList">
                        <ArticlesList articles={list} />
                    </div>
                    <Tops />
                </div>
            </div>
        )
    }
}

//  <div dangerouslySetInnerHTML={{__html: this.state.text}} />
