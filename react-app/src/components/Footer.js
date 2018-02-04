import React, { Component } from 'react';
import { Link } from 'react-router-dom';

export default class Footer extends Component {

  render(){
    return(
      <footer style={{height: '100%'}}>
        <p>Курсовая работа по дисциплине "Программирование интернет-приложений"</p>
        <p>&copy; Семенов Павел, Мельник Андрей, вариант 1002, гр. P3210</p>
      </footer>
    )
  }
}
