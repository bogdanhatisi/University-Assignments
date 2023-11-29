import {
  createAnimation,
  IonCard,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonModal,
} from "@ionic/react";
import React, { useEffect, useState } from "react";
import { ExerciseProps } from "./ExerciseProps";

interface ExercisePropsExtended extends ExerciseProps {
  onEdit: (_id?: string) => void;
}

const Exercise: React.FC<ExercisePropsExtended> = ({
  _id,
  nameOfExercise,
  repetitions,
  dropset,
  load,
  unit,
  latitude,
  longitude,
  webViewPath,
  onEdit,
}) => {
  useEffect(() => {
    if (document.getElementById("item") !== null) {
      // @ts-ignore
      document.getElementById("item")!.addEventListener("click", () => {
        onEdit(_id);
      });
    }
  }, [document.getElementById("item")]);

  return (
    <IonCard onClick={() => onEdit(_id)}>
      <IonCardHeader>
        <IonCardTitle>{nameOfExercise}</IonCardTitle>
        <IonCardSubtitle>{repetitions}</IonCardSubtitle>
        <IonCardSubtitle>
          Is this exercise {dropset ? "dropset" : "no"}.
        </IonCardSubtitle>
        <IonCardSubtitle>{unit}</IonCardSubtitle>
        <IonCardSubtitle>{load}</IonCardSubtitle>
        <IonCardSubtitle>Latitude: {latitude}</IonCardSubtitle>
        <IonCardSubtitle>Longitude: {longitude}</IonCardSubtitle>
        {webViewPath && (
          <img id="image" src={webViewPath} width={"160px"} height={"250px"} />
        )}
        {!webViewPath && (
          <img
            src={
              "https://us.123rf.com/450wm/koblizeek/koblizeek2208/koblizeek220800128/190320173-no-image-vector-symbol-missing-available-icon-no-gallery-for-this-moment-placeholder.jpg"
            }
            width={"200px"}
            height={"200px"}
          />
        )}
      </IonCardHeader>
    </IonCard>
  );
};

export default Exercise;
