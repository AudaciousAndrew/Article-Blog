import React, { Component } from 'react';
import '../css/Menu.css';
import Menu from "./Menu";
import axios from "axios";
import {Editor} from 'primereact/components/editor/Editor';
import {cookieFunctions} from "../cookieFunctions";
import {SelectButton} from 'primereact/components/selectbutton/SelectButton';


const apiPath='http://localhost:8080/kursa4_war_exploded/rest/';


const types = [
    {label: 'Игры', value: 'games'},
    {label: 'Фильмы', value: 'movies'},
    {label: 'Сериалы', value: 'tvshows'},
    {label: 'Аниме', value: 'anime'}
];

class Users extends Component {

    constructor(props) {
        super(props);
        this.state = {
            type: '',
            name: '',
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
                    articleName: this.state.name,
                    articleType: this.state.type,
                    articleDesc: this.state.text2,
                    author: cookieFunctions.getCookie('user')
                }
            )
                .then((response) => {
                    if(response.data ===true)
                    this.setState({checkResponse: "Статья успешно добавлена в очередь на модерацию"});
                    else this.setState({checkResponse: "Статья с таким именем уже существует"});
                    console.log(response.data);
                    resolve();

                }).catch(error => {
                console.log(error.message);
            });
        });
    }

    template(option){

    }


    render(){
        console.log(this.state.type);
        return(
            <div>
                <Menu />
                <div className="Article">
                    <div className="articleInfo">
                        <div className="articleName">
                            Название Статьи:
                            <input type="text" onChange={(e)=>this.setState({name:e.target.value})} />
                        </div>
                        <div className="articleType">
                            <SelectButton value={this.state.type} options={types} onChange={(e) => this.setState({type: e.value})}/>
                        </div>
                    </div>



                    <div className="editor" style={{width: '500px'}} >
                        <Editor value={this.state.text2} style={{height:'320px'}}onTextChange={(e)=>this.setState({text2:e.htmlValue})}/>
                        <input type="submit" onClick={this.loadOnServer} />
                    </div>
                    {this.state.checkResponse}
                </div>
            </div>
        )
    }
}

export default Users;


