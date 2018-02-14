import React, { Component } from 'react';
import Menu from './components/Menu';
import axios from 'axios';
import ArticlesList from './components/articles/ArticlesList';
import './css/Article.css';
import './css/Search.css'
import Tops from './components/Tops';
import {connect} from 'react-redux';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';
var total;

class App extends Component{


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
                method:'get',
                url: apiPath+'/article/all/' + this.state.pageNumber,
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
                method:'get',
                url: apiPath+'/article/all/amount/',
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
                <h2 style={{marginLeft: '8%', marginTop: '1rem'}}>Новые публикации</h2>
                <div className="flex-container">
                    <div className="articlesList" style={{marginTop: '2rem'}}>
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


export default connect(
  state => ({
    testStore: state
  }),
  dispatch => ({
    onAddTrack: (trackName) =>
      dispatch({ type: 'ADD_TRACK', payload: trackName})
  })
)(App);
