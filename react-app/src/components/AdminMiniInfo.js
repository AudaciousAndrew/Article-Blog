import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import '../css/UserInfo.css';

const apiPath='http://localhost:8080/kursa4_war_exploded/rest';

class AdminMiniInfo extends Component{

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
                    <div className="miniMainInfo">
                        <Link to={`/user/${this.props.login}`}
                              userlogin={this.props.login}>{this.props.login}</Link>
                    </div>
                </div>
            </div>
        )
    }
}

export default AdminMiniInfo;
