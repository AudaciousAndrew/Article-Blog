import React, { Component } from 'react';
import Menu from '../Menu';
import axios from 'axios';
import ArticlesList from './ArticlesList';
import '../../css/Article.css';
import {InputText} from 'primereact/components/inputtext/InputText';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

export default class Anime extends Component{


    constructor(props){
        super(props);
        this.state = {
            articles: [],
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
                method:'get',
                url: apiPath+'/article/' + this.props.match.params.id +'/all',
            })
                .then((response) => {
                    console.log(response.data);
                    //this.setState({articles: response.data});
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
                url: apiPath+'/article/number/anime/',
            })
                .then((response) => {
                    console.log(response.data);
                    let pages = Math.floor(response.data / 10);
                    if(response.data % 10 !== 0) ++pages;
                    this.setState({totalPages: pages});
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
                url: apiPath+'/article/name',
                data: {
                    name: 'В'
                }
            })
                .then((response) => {
                    console.log(response.data);
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

    render(){
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

                <div>
                    <ArticlesList articles={this.state.articles} />
                    <div className="pagination">
                        <div className="pager">
                            <button onClick={this.decPage.bind(this)}>&#8592;Сюда</button>
                            <label> Страница {this.state.pageNumber} из {this.state.totalPages}</label>
                            <button onClick={this.incPage.bind(this)}>Туда&#8594;</button>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

//  <div dangerouslySetInnerHTML={{__html: this.state.text}} />
