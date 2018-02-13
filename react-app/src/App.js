import React, { Component } from 'react';
import { connect } from 'react-redux';
import Menu from './components/Menu';
import Tops from './components/Tops';

class App extends Component{
  addTrack(){
    console.log('addTrack');
    this.props.onAddTrack(this.trackInput.value);
  }

  render(){
    return (
      <div>
        <Menu />
        <Tops />
      </div>
    )
  }

}

export default connect(
  state => ({
    testStore: state
  }),
  dispatch => ({
    onAddTrack: (trackName) =>
      dispatch({ type: 'ADD_TRACK', payload: trackName})
  })
)(App);
