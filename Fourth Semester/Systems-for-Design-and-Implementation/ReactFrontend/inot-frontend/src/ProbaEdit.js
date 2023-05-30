import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import { Button, Container, Form, FormGroup, Input, Label } from "reactstrap";
import AppNavbar from "./AppNavbar";

class ProbaEdit extends Component {
  emptyItem = {
    stil: "",
    distanta: "",
  };

  constructor(props) {
    super(props);
    this.state = {
      item: this.emptyItem,
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    if (this.props.match.params.id !== "new") {
      const proba = await (
        await fetch(`/api/proba/${this.props.match.params.id}`)
      ).json();
      this.setState({ item: proba });
    }
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let item = { ...this.state.item };
    item[name] = value;
    this.setState({ item });
  }

  async handleSubmit(event) {
    event.preventDefault();
    const { item } = this.state;

    await fetch("/api/proba" + (item.id ? "/" + item.id : ""), {
      method: item.id ? "PUT" : "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(item),
    });
    this.props.history.push("/api/proba");
  }

  render() {
    const { item } = this.state;
    const title = <h2>{item.id ? "Edit Proba" : "Add Proba"}</h2>;

    return (
      <div>
        <AppNavbar />
        <Container>
          {title}
          <Form onSubmit={this.handleSubmit}>
            <FormGroup>
              <Label for="stil">Stil</Label>
              <Input
                type="text"
                name="stil"
                id="stil"
                value={item.stil || ""}
                onChange={this.handleChange}
                autoComplete="stil"
              />
            </FormGroup>
            <FormGroup>
              <Label for="distanta">Distanta</Label>
              <Input
                type="text"
                name="distanta"
                id="distanta"
                value={item.distanta || ""}
                onChange={this.handleChange}
                autoComplete="distanta"
              />
            </FormGroup>
            <FormGroup>
              <Button
                onClick={() => {
                  window.location.href = "/api/proba";
                }}
                color="primary"
                type="submit"
              >
                Save
              </Button>{" "}
              <Button
                color="secondary"
                onClick={() => {
                  window.location.href = "/api/proba";
                }}
                tag={Link}
                to="/api/proba"
              >
                Cancel
              </Button>
            </FormGroup>
          </Form>
        </Container>
      </div>
    );
  }
}
export default withRouter(ProbaEdit);
