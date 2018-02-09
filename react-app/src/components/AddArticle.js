import React, { Component } from 'react';
import '../css/Menu.css';
import Menu from "./Menu";
import axios from "axios";
import {Editor} from 'primereact/components/editor/Editor';
import {cookieFunctions} from "../cookieFunctions";


const apiPath='http://localhost:8080/kursa4_war_exploded/rest/';

class Users extends Component {

    constructor(props) {
        super(props);
        this.state = {
            type: '',
            topic: '',
            text2: '',
            userToken: cookieFunctions.getCookie('userToken')
        };
        this.loadOnServer = this.loadOnServer.bind(this);
    }

    loadOnServer() {
        return new Promise((resolve, reject) => {

            var instance = axios.create({
                baseURL: apiPath
            });
            instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
            instance.post('/secured/article/add',
                {
                    articleName: 'loool',
                    articleType: 'anime',
                    articleDesc: this.state.text2,
                    author: cookieFunctions.getCookie('user')
                }
            )
                .then((response) => {
                    this.setState({checkResponse: response.data});
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
                <div className="editor" style={{width: '500px'}} >
                    <Editor value={this.state.text2} style={{height:'320px'}}onTextChange={(e)=>this.setState({text2:e.htmlValue})}/>
                    <input type="submit" onClick={this.loadOnServer} />
                </div>
            </div>
        )
    }
}

export default Users;


