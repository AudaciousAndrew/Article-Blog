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

class Menu extends Component{

  constructor(props){
    super(props);
    this.state = { user : document.cookie};
  }

  render(){
    let btn;
    if(cookieFunctions.getCookie('user') === '')
     btn = <Link to="/signin" className="usrLink">Войти в аккаунт</Link>;
    else btn = <Link to="/signin" className="usrLink">аккаунт</Link>;
    return(
      <div className="navbar">
        <div className="links">
          <Link to="/">Главная</Link>
          <Link to="/news">Новости</Link>
          <Dropdown id="dd1" name="Статьи" link={"/arcticles"} links={Links}/>
          <Link to="/about">О проекте</Link>
        </div>
      {btn}
      <Search />
      </div>

    )
  }

}

export default Menu;
