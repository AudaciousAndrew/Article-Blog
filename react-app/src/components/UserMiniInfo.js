import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import '../css/UserInfo.css';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

class UserMiniInfo extends Component{

    constructor(props){
        super(props);
        this.state = {
            user: {
                //login: ''
            }
        };
    }

    render(){
        return(
            <div>
                <div className="miniInfo">
                    <div className="miniMainDiv">
                            <img src={this.props.avatar}
                                 style={{height: '60px', width: '60px'}} alt="noAvatar" />                    </div>
                        <div className="miniMainInfo">
                            <Link to={`/user/${this.props.login}`}
                                userlogin={this.props.login}>{this.props.login}</Link>
                            <p>Email: {this.props.email}</p>
                            <p>Рейтинг: {this.props.rating}</p>
                        </div>
                </div>
            </div>
        )
    }
}

export default UserMiniInfo;
