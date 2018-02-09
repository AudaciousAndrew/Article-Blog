import React, { Component } from 'react';
import axios from 'axios';
import '../css/UserInfo.css';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

class MyAccount extends Component{

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
                <div className="infoDiv">
                    <div className="mainDiv">
                        <div className="imgDiv">
                            <img src={this.props.avatar} alt="no avatar" />
                        </div>
                        <div className="mainInfo">
                            {this.props.login}
                            {this.props.rating}
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default MyAccount;
