import axios from "axios";
import { authConfig, getLogger, withLogs } from "../../core";
import { ExerciseProps } from "./ExerciseProps";
import { Plugins } from "@capacitor/core";

const log = getLogger("itemApi");
const { Storage } = Plugins;

const url = "localhost:3000";
const baseUrl = `http://${url}/api/items`;

interface MessageData {
  type: string;
  payload: ExerciseProps;
}

const different = (exercise1: ExerciseProps, exercise2: ExerciseProps) => {
  // Updated function signature
  if (
    exercise1.nameOfExercise === exercise2.nameOfExercise &&
    exercise1.repetitions === exercise2.repetitions &&
    exercise1.dropset === exercise2.dropset &&
    exercise1.load === exercise2.load &&
    exercise1.unit === exercise2.unit &&
    exercise1.latitude === exercise2.latitude &&
    exercise1.longitude === exercise2.longitude &&
    exercise1.webViewPath === exercise2.webViewPath
  )
    return false;
  return true;
};

export const syncData: (token: string) => Promise<ExerciseProps[]> = async (
  token
) => {
  try {
    const { keys } = await Storage.keys();
    var result = axios.get(`${baseUrl}/exercises`, authConfig(token)); // Updated endpoint
    result
      .then(async (result) => {
        keys.forEach(async (i) => {
          if (i !== "token") {
            const exerciseOnServer = result.data.find(
              (each: { _id: string }) => each._id === i
            );
            const exerciseLocal = await Storage.get({ key: i });

            if (
              exerciseOnServer !== undefined &&
              different(exerciseOnServer, JSON.parse(exerciseLocal.value!))
            ) {
              axios.put(
                `${baseUrl}/exercise/${i}`, // Updated endpoint
                JSON.parse(exerciseLocal.value!),
                authConfig(token)
              );
            } else if (exerciseOnServer === undefined) {
              axios.post(
                `${baseUrl}/exercise`, // Updated endpoint
                JSON.parse(exerciseLocal.value!),
                authConfig(token)
              );
            }
          }
        });
      })
      .catch((err) => {
        if (err.response) {
          console.log("client received an error response (5xx, 4xx)");
        } else if (err.request) {
          console.log(
            "client never received a response, or request never left"
          );
        } else {
          console.log("anything else");
        }
      });
    return withLogs(result, "syncItems");
  } catch (error) {
    throw error;
  }
};

export const getItems: (token: string) => Promise<ExerciseProps[]> = (
  token
) => {
  try {
    var result = axios.get(`${baseUrl}/exercises`, authConfig(token)); // Updated endpoint
    result
      .then(async (result) => {
        for (const each of result.data) {
          await Storage.set({
            key: each._id!,
            value: JSON.stringify({
              _id: each._id,
              nameOfExercise: each.nameOfExercise,
              repetitions: each.repetitions,
              dropset: each.dropset,
              load: each.load,
              unit: each.unit,
              latitude: each.latitude,
              longitude: each.longitude,
              webViewPath: each.webViewPath,
            }),
          });
        }
      })
      .catch((err) => {
        if (err.response) {
          console.log("client received an error response (5xx, 4xx)");
        } else if (err.request) {
          console.log(
            "client never received a response, or request never left"
          );
        } else {
          console.log("anything else");
        }
      });
    return withLogs(result, "getItems");
  } catch (error) {
    throw error;
  }
};

export const createItem: (
  token: string,
  exercise: ExerciseProps // Updated parameter name
) => Promise<ExerciseProps[]> = (token, exercise) => {
  var result = axios.post(`${baseUrl}/exercise`, exercise, authConfig(token)); // Updated endpoint
  result
    .then(async (result) => {
      var one = result.data;
      await Storage.set({
        key: one._id!,
        value: JSON.stringify({
          _id: one._id,
          nameOfExercise: one.nameOfExercise,
          repetitions: one.repetitions,
          dropset: one.dropset,
          load: one.load,
          unit: one.unit,
          latitude: one.latitude,
          longitude: one.longitude,
          webViewPath: one.webViewPath,
        }),
      });
    })
    .catch((err) => {
      if (err.response) {
        console.log("client received an error response (5xx, 4xx)");
      } else if (err.request) {
        //alert('client never received a response, or request never left');
      } else {
        console.log("anything else");
      }
    });
  return withLogs(result, "createItem");
};

export const editItem: (
  token: string,
  exercise: ExerciseProps // Updated parameter name
) => Promise<ExerciseProps[]> = (token, exercise) => {
  var result = axios.put(
    `${baseUrl}/exercise/${exercise._id}`, // Updated endpoint
    exercise,
    authConfig(token)
  );
  result.then(async (result) => {
    var one = result.data;
    await Storage.set({
      key: one._id!,
      value: JSON.stringify({
        _id: one._id,
        nameOfExercise: one.nameOfExercise,
        repetitions: one.repetitions,
        dropset: one.dropset,
        load: one.load,
        unit: one.unit,
        latitude: one.latitude,
        longitude: one.longitude,
        webViewPath: one.webViewPath,
      }),
    }).catch((err) => {
      if (err.response) {
        //alert('client received an error response (5xx, 4xx)');
      } else if (err.request) {
        //alert('client never received a response, or request never left');
      } else {
        //alert('anything else');
      }
    });
  });
  return withLogs(result, "updateItem");
};

export const createWebSocket = (
  token: string,
  onMessage: (data: MessageData) => void
) => {
  const ws = new WebSocket(`ws://${url}`);
  ws.onopen = () => {
    log("web socket onopen");
    ws.send(JSON.stringify({ type: "authorization", payload: { token } }));
  };
  ws.onclose = function (event) {
    console.log(event);
    log("web socket onclose");
  };
  ws.onerror = (error) => {
    log("web socket onerror", error);
    ws.close();
  };
  ws.onmessage = (messageEvent) => {
    log("web socket onmessage");
    onMessage(JSON.parse(messageEvent.data));
  };
  return () => {
    ws.close();
  };
};
