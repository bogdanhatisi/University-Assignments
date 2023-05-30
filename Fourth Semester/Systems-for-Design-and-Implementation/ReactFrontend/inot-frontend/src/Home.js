import React, { Component } from "react";
import "./App.css";
import AppNavbar from "./AppNavbar";
import { Link } from "react-router-dom";
import { Button, Container } from "reactstrap";
import { withRouter } from "react-router-dom/cjs/react-router-dom.min";

class Home extends Component {
  render() {
    return (
      <div>
        <AppNavbar />
        <Container fluid>
          <Button color="link">
            <Link
              onClick={() => {
                window.location.href = "/api/proba";
              }}
              to="/api/proba"
            >
              Probe
            </Link>
          </Button>
        </Container>
      </div>
    );
  }
}
export default withRouter(Home);
