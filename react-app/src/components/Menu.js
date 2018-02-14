import React, { Component } from 'react';
import '../css/Menu.css';
import { Link } from 'react-router-dom';
import Dropdown from './Dropdown';
import AccountDropdown from './AccountDropdown';
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
    let role = cookieFunctions.getCookie('role');
      let usrLinks = [
          {
              path: "/myAccount",
              desc: "Мой аккаунт",
          },
          {
              path: "/settings",
              desc: "Настройки",
              user: cookieFunctions.getCookie('user'),
              userToken: cookieFunctions.getCookie('userToken')
          },

          {
              path: "/add",
              desc: "Добавить статью",
              user: cookieFunctions.getCookie('user'),
              userToken: cookieFunctions.getCookie('userToken')
          },
          {
              path: "/myArticles",
              desc: "Мои статьи",
              user: cookieFunctions.getCookie('user'),
              userToken: cookieFunctions.getCookie('userToken')
          }

      ];
      console.log(role);
      if(role === '2' || role === '3'){
          usrLinks = usrLinks.concat({
              path: "/moderation",
              desc: "Модерация статей",
              user: cookieFunctions.getCookie('user'),
              userToken: cookieFunctions.getCookie('userToken')
          });
      }

      if(role === '3'){
          usrLinks = usrLinks.concat({
              path: "/administration",
              desc: "Администрирование",
              user: cookieFunctions.getCookie('user'),
              userToken: cookieFunctions.getCookie('userToken')
          });
      }
    if(cookieFunctions.getCookie('userToken') === '')
     btn = <Link to="/signin" className="usrLink" style={{width: '5em'}}>Вход</Link>;
    else btn = <AccountDropdown classname="dropdown2" id="dd2" name="Профиль" links={usrLinks}/>;
    return(
      <div className="navbar">
        <div className="links">
          <Link to="/">Главная</Link>
          <Dropdown classname="dropdown" id="dd1" name="Статьи" links={Links}/>
          <Link to="/users" >Пользователи</Link>
        </div>
      {btn}
      </div>

    )
  }

}

export default Menu;
