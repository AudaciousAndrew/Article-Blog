import React, { Component } from 'react';
import '../css/Menu.css';
import { Link } from 'react-router-dom';

export default class Dropdown extends Component {

  constructor(props){
    super(props);
    this.state = {popupVisible : false};
    this.mouseOver = this.mouseOver.bind(this);
    this.mouseOut = this.mouseOut.bind(this);
  }

  mouseOver(e) {
      document.getElementById(this.props.id).classList.toggle("show");
  }

  mouseOut(e) {
     document.getElementById(this.props.id).classList.remove("show");
  }


  render(){
    return(
      <div className="dropdown" onMouseOver={this.mouseOver} onMouseOut={this.mouseOut}>
        <button className="dropbtn">
          <Link to={this.props.link} style={{padding: '0'}}>{this.props.name}</Link>
        </button>
        <div className="dropdown-content" id={this.props.id}>
          {this.props.links.map((el, index) => {
            return <Link to={el.path} key={index}>{el.desc}</Link>
          })}
        </div>
      </div>
    )
  }
}
