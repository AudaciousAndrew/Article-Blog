import React, { Component } from 'react';
import '../css/Search.css';


export default class Search extends Component {

  render(){
    return(
      <div>
        <form className="search">
          <input type="search" name="" placeholder="поиск" className="input" />
          <input type="submit" name="" value="" className="submit" />
        </form>
      </div>
    )
  }
}
