import React, { Component } from 'react';
import '../css/Menu.css';
import Menu from './Menu';

export default class NotFound extends Component {

    render(){
        return(
            <div>
                <Menu />
                <div style={{marginLeft: '8%', marginTop: '2em'}}>
                    <h2>Такой страницы не существует</h2>
                </div>
            </div>
        )
    }
}
