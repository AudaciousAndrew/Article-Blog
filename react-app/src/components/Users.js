import React, { Component } from 'react';
import '../css/Menu.css';
import Menu from "./Menu";
import {connect} from 'react-redux';
import axios from "axios/index";
import UserMiniInfo from './UserMiniInfo';

const defaultAvatar = require('../resources/user.png');
const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

class Users extends Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [
                {
                    avatarpath: ''
                }
            ]
        };
        this.loadFromServer = this.loadFromServer.bind(this);
        this.loadFromServer();
    }

    loadFromServer() {
        axios.get(apiPath + '/user/top100')
            .then((response) => {
                console.log(response.data);
                this.setState({users: response.data});
                //  resolve();
            })
            .catch((error) => {
                console.log(error);
            });
    }



    render(){
        return(
            <div>
                <Menu />
                {this.state.users.map((el, index) => {
                    let path;
                    if(typeof el.avatarpath !== 'undefined')
                        path="http://localhost:8080/"+el.login;
                    else path=defaultAvatar;
                    return <UserMiniInfo key={index} login={el.login}
                                         rating={el.rating} avatar={path} />
                })}
            </div>
        )
    }
}

export default Users;

// export default connect(
//     state => ({
//         testStore: state
//     }),
//     dispatch => ({})
// )(Users);
