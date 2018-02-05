import React, { Component } from 'react';
import '../css/Menu.css';
import { Link } from 'react-router-dom';
import Dropdown from './Dropdown';
import Search from './Search';
import { cookieFunctions } from '../cookieFunctions.js';
const Links = [
  {
    path: "/games",
    desc: "Игры"
  },
  {
    path: "/movies",
    desc: "Фильмы"
  },
  {
    path: "/tvshows",
    desc: "Сериалы"
  },
  {
    path: "/anime",
    desc: "Аниме"
  }
];

const usrLinks = [
  {
    path: "/myAccount",
    desc: "Мой аккаунт"
  },
  {
    path: "/settings",
    desc: "Настройки"
  },
  {
    path: "/",
    desc: "Выйти"
  }
];

class Menu extends Component{

  constructor(props){
    super(props);
    this.state = { user : document.cookie};
  }

  render(){
    let btn;
    if(cookieFunctions.getCookie('userToken') === '')
     btn = <Link to="/signin" className="usrLink">Вход</Link>;
    else btn = <Dropdown classname="dropdown2" id="dd2" name="Профиль" links={usrLinks}/>;
    return(
      <div className="navbar">
        <div className="links">
          <Link to="/">Главная</Link>
          <Dropdown classname="dropdown" id="dd1" name="Статьи" links={Links}/>
          <Link to="/about">О проекте</Link>
        </div>
      {btn}
      <Search />
      </div>

    )
  }

}

export default Menu;
