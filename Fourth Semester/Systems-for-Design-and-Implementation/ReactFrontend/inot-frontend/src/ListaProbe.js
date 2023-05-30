import React, { Component } from "react";
import { Button, ButtonGroup, Container, Table } from "reactstrap";
import AppNavbar from "./AppNavbar";
import { Link } from "react-router-dom";

class ListaProbe extends Component {
  constructor(props) {
    super(props);
    this.state = { probe: [] };
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    fetch("/api/proba")
      .then((response) => response.json())
      .then((data) => this.setState({ probe: data }));
  }
  async remove(id) {
    await fetch(`/api/proba/${id}`, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    }).then(() => {
      let updatedProbe = [...this.state.probe].filter((i) => i.id !== id);
      this.setState({ probe: updatedProbe });
    });
  }

  render() {
    const { probe, isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const probeList = probe.map((proba) => {
      return (
        <tr key={proba.id}>
          <td style={{ whiteSpace: "nowrap" }}>{proba.stil}</td>
          <td>{proba.distanta}</td>
          <td>
            <ButtonGroup>
              <Button
                size="sm"
                color="primary"
                onClick={() => {
                  window.location.href = "/api/proba/" + proba.id;
                }}
                tag={Link}
                to={"/api/proba/" + proba.id}
              >
                Edit
              </Button>
              <Button
                size="sm"
                color="danger"
                onClick={() => this.remove(proba.id)}
              >
                Delete
              </Button>
            </ButtonGroup>
          </td>
        </tr>
      );
    });

    return (
      <div>
        <AppNavbar />
        <Container fluid>
          <div className="float-right">
            <Button
              color="success"
              onClick={() => {
                window.location.href = "/api/proba/new";
              }}
              tag={Link}
              to="/api/proba/new"
            >
              Add Proba
            </Button>
          </div>
          <h3>Probe</h3>
          <Table className="mt-4">
            <thead>
              <tr>
                <th width="30%">Stil</th>
                <th width="30%">Distanta</th>
                <th width="40%">Actions</th>
              </tr>
            </thead>
            <tbody>{probeList}</tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default ListaProbe;
