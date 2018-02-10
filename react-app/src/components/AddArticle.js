import React, { Component } from 'react';
import '../css/Menu.css';
import Menu from "./Menu";
import axios from "axios";
import {Editor} from 'primereact/components/editor/Editor';
import {cookieFunctions} from "../cookieFunctions";
import {SelectButton} from 'primereact/components/selectbutton/SelectButton';
import 'primereact/resources/primereact.min.css';
import 'primereact/resources/themes/omega/theme.css';
import '../css/AddArticle.css';
import {InputText} from 'primereact/components/inputtext/InputText';


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
            type: null,
            articleName: '',
            text2: '',
            userToken: cookieFunctions.getCookie('userToken')
        };
        this.loadOnServer = this.loadOnServer.bind(this);
    }

    loadOnServer() {
        this.setState({checkResponse: "", check: ''});
        if(this.state.type === null){
            this.setState({checkResponse: "Выберите тип статьи", check: 'false'});
            return false;
        }
        let name = document.getElementById('aN').value;
        if(name === ''){
            this.setState({checkResponse: "Введите имя статьи", check: 'false'});
            return false;
        }
        let desc = document.getElementById('desc').value;
        if(desc === '' || desc === "" || desc === null){
            this.setState({checkResponse: "Введите краткое описание", check: 'false'});
            return false;
        }
        if(this.state.text2 === null || this.state.text2 === ''){
            this.setState({checkResponse: "Вы забыли написать статью", check: 'false'});
            return false;
        }
        return new Promise((resolve, reject) => {

            var instance = axios.create({
                baseURL: apiPath
            });
            instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
            instance.post('/secured/article/add',
                {
                    articleName: name,
                    articleType: this.state.type,
                    articleDesc: this.state.text2,
                    small_desc: desc,
                    author: cookieFunctions.getCookie('user')
                }
            )
                .then((response) => {
                    if(response.data ===true)
                    this.setState({checkResponse: "Статья успешно добавлена в очередь на модерацию", check: 'true'});
                    else this.setState({checkResponse: "Статья с таким именем уже существует", check: 'false'});
                    resolve();

                }).catch(error => {
                console.log(error.message);
            });
        });
    }


    desc(e){
        this.setState({desc: e.target.value});
    }

    render(){
        return(
            <div>
                <Menu />
                <div className="add">
                    <h3>Добавить статью</h3>
                    <div className="articleInfo">
                        <div className="articleName">
                            <label>Название статьи:</label>
                            <input type="text" id="aN" maxLength={150} />
                        </div>
                        <div className="articleType">
                            <label>Тип статьи:</label>
                            <SelectButton id="sB" value={this.state.type} options={types} onChange={(e) => this.setState({type: e.value})}/>
                        </div>
                        <div className="briefDesc">
                            <label>Краткое описание:</label>
                            <textarea id="desc"
                                      maxLength="400" style={{resize: 'none'}}  />
                        </div>
                    </div>


                    <div className="editor" >
                        <Editor value={this.state.text2} style={{height:'320px'}}onTextChange={(e)=>this.setState({text2:e.htmlValue})}/>
                    </div>
                    <input type="submit" value="Отправить статью на модерацию"
                           className="submitBtn" onClick={this.loadOnServer} />
                    <p id={this.state.check}>{this.state.checkResponse}</p>
                </div>
            </div>
        )
    }
}

export default Users;


