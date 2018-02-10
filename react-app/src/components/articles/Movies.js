import React, { Component } from 'react';
import Menu from '../Menu';
import axios from 'axios';


const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

export default class Movies extends Component{


    constructor(props){
        super(props);
        this.state = {
            text: ''
        };
        this.loadFromServer = this.loadFromServer.bind(this);
        this.loadFromServer();
    }

    loadFromServer(){
        return new Promise((resolve, reject) => {
            axios({
                method:'post',
                url: apiPath+'/article/type/movies',
            })
                .then((response) => {
                    console.log(response.data);
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
                here will be some Anime reviews
                <div dangerouslySetInnerHTML={{__html: this.state.text}} />


            </div>
        )
    }
}
