import React, { Component } from 'react';
import { Link } from 'react-router-dom';


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
                            <img src={this.props.avatar}
                                 style={{height: '75px', width: '75px'}} alt="noAvatar" />                    </div>
                        <div className="mainInfo">
                            <Link to={`/user/${this.props.login}`}
                                userlogin={this.props.login}>{this.props.login}</Link>
                            {this.props.rating}
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default MyAccount;
