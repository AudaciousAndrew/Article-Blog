import React, { Component } from 'react';
import Menu from '../Menu';
import axios from 'axios';
import ArticlesList from './ArticlesList';
import '../../css/Article.css';
import '../../css/Search.css'
import {InputText} from 'primereact/components/inputtext/InputText';
import Tops from '../Tops';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';
var total;

export default class Games extends Component{


    constructor(props){
        super(props);
        this.state = {
            articles: [],
            founded: [],
            text: '',
            pageNumber: 1,
            totalPages: 1
        };
        this.loadFromServer = this.loadFromServer.bind(this);
        this.getPages = this.getPages.bind(this);
        this.getPages();
        this.loadFromServer();
    }

    loadFromServer(){
        return new Promise((resolve, reject) => {
            axios({
                method:'post',
                url: apiPath+'/article/type/games/' + this.state.pageNumber,
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

    getPages(){
        return new Promise((resolve, reject) => {
            axios({
                method:'post',
                url: apiPath+'/article/number/games/',
            })
                .then((response) => {
                    console.log(response.data);
                    let pages = Math.floor(response.data / 10);
                    if(response.data % 10 !== 0) ++pages;
                    this.setState({totalPages: pages});
                    total = pages;
                    resolve();
                }).catch(error => {
                console.log(error.message);
            });
        });
    }

    decPage(){
        if(this.state.pageNumber > 1)this.setState({pageNumber: this.state.pageNumber - 1},() => {
            this.loadFromServer();
        });
    }

    incPage(){
        if(this.state.pageNumber < this.state.totalPages)
            this.setState({pageNumber: this.state.pageNumber + 1},() => {
                this.loadFromServer();
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
                    this.setState({totalPages: pgs});
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
            this.setState({founded: null, totalPages: total});
        }
    }

    render(){
        let list = [],
            pagination = <div className="pagination"><h3>Нет статей :(</h3></div> ;
        if(this.state.totalPages !== 0){
            list = this.state.articles;
            pagination =  <div className="pagination">
                <div className="pager">
                    <button onClick={this.decPage.bind(this)}>&#8592;Сюда</button>
                    <label> Страница {this.state.pageNumber} из {this.state.totalPages} </label>
                    <button onClick={this.incPage.bind(this)}>Туда&#8594;</button>
                </div>
            </div>;

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
                        {pagination}
                    </div>
                    <Tops />
                </div>
            </div>
        )
    }
}

//  <div dangerouslySetInnerHTML={{__html: this.state.text}} />
