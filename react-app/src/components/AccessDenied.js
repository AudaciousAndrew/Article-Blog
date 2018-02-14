import React, { Component } from 'react';
import '../css/Menu.css';
import Menu from './Menu';

export default class AccessDenied extends Component {

    render(){
        return(
            <div>
                <Menu />
                <div style={{marginLeft: '8%', marginTop: '2em'}}>
                    <h2>У вас недостаточно прав для просмотра этой страницы</h2>
                </div>
            </div>
        )
    }
}
