import React, { Component } from 'react';
import '../css/Menu.css';
import Menu from "./Menu";
import {connect} from 'react-redux';
import axios from "axios/index";
import UserMiniInfo from './UserMiniInfo';
import UserSearch from './UserSearch';
import '../css/Search.css';
import AdminMiniInfo from './AdminMiniInfo';
import {cookieFunctions} from "../cookieFunctions";
const defaultAvatar = require('../resources/user.png');
const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

var users =[];
var role = [];
var omg = [1, 2, 3, 4];

class Administration extends Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [
            ],
            userToken: cookieFunctions.getCookie('userToken'),
            founded: null
        };
        this.loadFromServer = this.loadFromServer.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.loadFromServer();
        this.props.onLoadUsers();
    }

    loadFromServer() {
        axios.get(apiPath + '/user/top/1000')
            .then((response) => {
                console.log(response.data);
                this.setState({users: response.data}, ()=>{
                });
                console.log(role[0]);
                //  resolve();
            })
            .catch((error) => {
                console.log(error);
            });
    }

    search(){
        let login = document.getElementById('search').value;
        if(login.length === 0)return;
        console.log(login);
        axios.get(apiPath + '/user/' + login)
            .then((response) => {
                this.setState({founded: response.data});
                //  resolve();
            })
            .catch((error) => {
                console.log(error);
            });
    }

    checkSearch(){
        let val = document.getElementById('search').value;
        if(val.length === 0){
            this.setState({founded: null});
        }
    }

    handleSubmit(e){
        alert("allo")
        let val = e.target.id;
        return new Promise((resolve, reject) => {

            var instance = axios.create({
                baseURL: apiPath
            });
            instance.defaults.headers.common['Authorization'] = 'Basic ' + this.state.userToken;
            instance.post('/secured/user/create/moderator/'+val)
                .then((response) => {
                    console.log(response.data);
                    //window.location.reload();
                    resolve();

                }).catch(error => {
                console.log(error.message);
            });
        });
    }


    render(){
        console.log(role);
        var userslist = <div><h3>По вашему запросу ничего не найдено.</h3></div> ;
        if(this.state.founded !== null && this.state.founded !==''){
            let path, el = this.state.founded;
            if(typeof el.avatarpath !== 'undefined')
                path="http://localhost:8080/"+el.login;
            else path=defaultAvatar;
            userslist = <div className="administration">
                <AdminMiniInfo login={el.login}/>
                <input type="submit" value="Сделать модератором" id={el.login}
                       className="submitBtn" onClick={this.handleSubmit} />
            </div>
        } else if(this.state.founded !== '')
            userslist = this.state.users.map((el, index) => {
                let path;
                if(typeof el.avatarpath !== 'undefined')
                    path="http://localhost:8080/"+el.login;
                else path=defaultAvatar;

                return <div key={index} className="administration">
                    <AdminMiniInfo  login={el.login}/>
                    <input type="submit" value="Сделать модератором" id={el.login}
                           className="submitBtn" onClick={this.handleSubmit} />
                </div>
            });
        return(
            <div>
                <Menu />
                <div>
                    <div className="userSearch">
                        <input type="search" name="" id="search"
                               placeholder="Поиск по пользователям" className="input" onChange={this.checkSearch.bind(this)} />
                        <input type="submit" name="" value="" className="submit" onClick={this.search.bind(this)}/>
                    </div>
                </div>
                <div className="flex-container">
                    <div className="usersList">
                        {userslist}
                    </div>
                </div>
            </div>
        )
    }
}


export default connect(
    state => ({
        uStore: state
    }),
    dispatch => ({
        onLoadUsers: () => {
            dispatch({type: 'LOAD_USERS', payload: users})
        }
    })
)(Administration);
