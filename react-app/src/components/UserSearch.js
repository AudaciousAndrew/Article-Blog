import React, { Component } from 'react';
import '../css/Search.css';


export default class UserSearch extends Component {

    render(){
        return(
            <div>
                <form className="userSearch">
                    <input type="search" name="" placeholder="Поиск по пользователям" className="input" />
                    <input type="submit" name="" value="" className="submit" />
                </form>
            </div>
        )
    }
}
