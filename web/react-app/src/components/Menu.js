import React, { Component } from 'react';
import '../css/Menu.css';
import { Link } from 'react-router-dom';
import Dropdown from './Dropdown';

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
    }

  render(){
    return(
      <div className="navbar">
        <Link to="/">Главная</Link>
        <Link to="/news">Новости</Link>
        <Dropdown id="dd1" name="Статьи" link={"/arcticles"} links={Links}/>
        <Link to="/about">О проекте</Link>
        <Link to="/signup">Регистрация</Link>  
      </div>

    )
  }

}

export default Menu;
