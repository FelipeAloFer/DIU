import React, { Component } from "react";

export default class InputComponent extends Component {
    
  render() {
    return (
        <div>
            <input value={this.props.name} onChange={this.props.cambiarNombre}/>
        </div>
    );
  }
}