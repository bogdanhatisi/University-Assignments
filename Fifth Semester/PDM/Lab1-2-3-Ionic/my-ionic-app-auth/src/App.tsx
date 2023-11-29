import React from "react";
import { Redirect, Route } from "react-router-dom";
import { IonApp, IonRouterOutlet } from "@ionic/react";
import { IonReactRouter } from "@ionic/react-router";

/* Core CSS required for Ionic components to work properly */
import "@ionic/react/css/core.css";

/* Basic CSS for apps built with Ionic */
import "@ionic/react/css/normalize.css";
import "@ionic/react/css/structure.css";
import "@ionic/react/css/typography.css";

/* Optional CSS utils that can be commented out */
import "@ionic/react/css/padding.css";
import "@ionic/react/css/float-elements.css";
import "@ionic/react/css/text-alignment.css";
import "@ionic/react/css/text-transformation.css";
import "@ionic/react/css/flex-utils.css";
import "@ionic/react/css/display.css";

/* Theme variables */
import "./theme/variables.css";
import ExerciseList from "./todo/exercise/ExerciseList";
import { ExerciseProvider } from "./todo/exercise/ExerciseProvider";
import ExerciseEdit from "./todo/exercise/ExerciseEdit";
import { AuthProvider, Login, PrivateRoute } from "./todo/auth";

const App: React.FC = () => (
  <IonApp>
    <IonReactRouter>
      <IonRouterOutlet>
        <AuthProvider>
          <Route path="/login" component={Login} exact={true} />
          <ExerciseProvider>
            <PrivateRoute
              path="/api/items/exercises"
              component={ExerciseList}
              exact={true}
            />
            <PrivateRoute
              path="/api/items/exercise"
              component={ExerciseEdit}
              exact={true}
            />
            <PrivateRoute
              path="/api/items/exercise/:id"
              component={ExerciseEdit}
              exact={true}
            />
          </ExerciseProvider>
          <Route
            exact
            path="/"
            render={() => <Redirect to="/api/items/exercises" />}
          />
        </AuthProvider>
      </IonRouterOutlet>
    </IonReactRouter>
  </IonApp>
);

export default App;
