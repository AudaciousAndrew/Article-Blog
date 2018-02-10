import React, { Component } from 'react';
import Menu from '../Menu';
import axios from 'axios';
import ArticlesList from './ArticlesList';
import {Pager} from 'react-bootstrap';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

export default class Anime extends Component{


  constructor(props){
      super(props);
      this.state = {
          articles: [],
          text: '',
          pageNumber: 1
      };
      this.loadFromServer = this.loadFromServer.bind(this);
      this.loadFromServer();
  }

  loadFromServer(){
      return new Promise((resolve, reject) => {
          axios({
              method:'post',
              url: apiPath+'/article/type/anime/' + this.state.pageNumber,
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

  decPage(){
      if(this.state.page > 1)this.setState({pageNumber: this.state.pageNumber - 1});
  }

  incPage(){
      if(true)this.setState({pageNumber: this.state.pageNumber + 1});
  }

    render(){
    return(
      <div>
        <Menu />
        <div>
            <ArticlesList articles={this.state.articles} />
            <div className="pagination">
                <div className="pager">
                  <button onClick={this.decPage.bind(this)}>&#8592;Сюда</button>
                  <button onClick={this.incPage.bind(this)}>Туда&#8594;</button>
                </div>
            </div>
        </div>
      </div>
    )
  }
}

//  <div dangerouslySetInnerHTML={{__html: this.state.text}} />
