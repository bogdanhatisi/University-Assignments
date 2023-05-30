import React, { Component } from "react";
import "./App.css";
import Home from "./Home";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ListaProbe from "./ListaProbe";
import ProbaEdit from "./ProbaEdit";

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path="/" exact={true} component={Home} />
          <Route path="/api/proba" exact={true} component={ListaProbe} />
          <Route path="/api/proba/:id" component={ProbaEdit} />
        </Switch>
      </Router>
    );
  }
}

export default App;
