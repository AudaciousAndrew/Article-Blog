import React, { Component } from 'react';
import '../css/Search.css';
import axios from "axios/index";

const apiPath='http://localhost:8080/kursa4_war_exploded/rest/';

export default class UserSearch extends Component {

    search(){
        let login = document.getElementById('search').value;
        axios.get(apiPath + '/user/' + login)
            .then((response) => {
                console.log(response.data);
                //  resolve();
            })
            .catch((error) => {
                console.log(error);
            });
    }

    render(){
        return(
            <div>
                <form className="userSearch">
                    <input type="search" name="" id="search" placeholder="Поиск по пользователям" className="input" />
                    <input type="submit" name="" value="" className="submit" onClick={this.search.bind(this)}/>
                </form>
            </div>
        )
    }
}
