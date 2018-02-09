import React, { Component } from 'react';
import Menu from '../Menu';
import axios from 'axios';


const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

export default class Anime extends Component{


  constructor(props){
      super(props);
      this.state = {
          text: ''
      };
      this.loadFromServer = this.loadFromServer.bind(this);
      this.loadFromServer();
  }

  loadFromServer(){
      axios.post(apiPath + '/article/name', {

              name: 'loool'

      })
          .then((response) => {
              console.log(response.data);
              this.setState({text: response.data.articleDesc});
              //  resolve();
          })
          .catch((error) => {
              console.log(error);
          });
  }

    createMarkup() {
        return {__html: 'First &middot; Second'};
    }

    render(){
    return(
      <div>
        <Menu />
        here will be some Anime reviews
          <div dangerouslySetInnerHTML={{__html: this.state.text}} />


      </div>
    )
  }
}
