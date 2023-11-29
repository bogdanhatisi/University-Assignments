import {
  createAnimation,
  CreateAnimation,
  IonButton,
  IonButtons,
  IonContent,
  IonDatetime,
  IonHeader,
  IonInput,
  IonItem,
  IonLabel,
  IonLoading,
  IonPage,
  IonTitle,
  IonToggle,
  IonToolbar,
} from "@ionic/react";
import React, { useEffect } from "react";
import { useContext, useState } from "react";
import { RouteComponentProps } from "react-router";
import { ExerciseContext } from "./ExerciseProvider";
import { ExerciseProps } from "./ExerciseProps";
import { MyMap } from "../../core/MyMap";
import { UseMyLocation } from "../../core/UseMyLocation";
import { UsePhotoGallery } from "../../core/UsePhotoGallery";
import moment from "moment";

interface ExerciseEditProps
  extends RouteComponentProps<{
    id?: string;
  }> {}

const ExerciseEdit: React.FC<ExerciseEditProps> = ({ history, match }) => {
  const { items, saving, savingError, saveItem } = useContext(ExerciseContext);
  const [nameOfExercise, setNameOfExercise] = useState("");
  const [repetitions, setRepetitions] = useState<number | undefined>(undefined);
  const [dropset, setDropset] = useState(false);
  const [load, setLoad] = useState<number | undefined>(undefined);
  const [unit, setUnit] = useState("");
  const [item, setItem] = useState<ExerciseProps>();

  const [latitude, setLatitude] = useState<number | undefined>(undefined);
  const [longitude, setLongitude] = useState<number | undefined>(undefined);
  const [currentLatitude, setCurrentLatitude] = useState<number | undefined>(
    undefined
  );
  const [currentLongitude, setCurrentLongitude] = useState<number | undefined>(
    undefined
  );
  const [webViewPath, setWebViewPath] = useState("");
  const location = UseMyLocation();
  const { latitude: lat, longitude: lng } = location.position?.coords || {};
  const { takePhoto } = UsePhotoGallery();

  useEffect(() => {
    log("useEffect");
    const routeId = match.params.id || "";
    const item = items?.find((it) => it._id === routeId);
    setItem(item);
    if (item) {
      setNameOfExercise(nameOfExercise);
      setRepetitions(repetitions);
      setDropset(dropset);
      setLoad(load);
      setUnit(unit);
      setLatitude(item.latitude);
      setLongitude(item.longitude);
      setWebViewPath(item.webViewPath);
    }
  }, [match.params.id, items]);

  useEffect(() => {
    if (latitude === undefined && longitude === undefined) {
      setCurrentLatitude(lat);
      setCurrentLongitude(lng);
    } else {
      setCurrentLatitude(latitude);
      setCurrentLongitude(longitude);
    }
  }, [lat, lng, longitude, latitude]);

  const handleSave = () => {
    log("entered handleSave");
    const editedItem = item
      ? {
          ...item,
          nameOfExercise,
          repetitions,
          dropset,
          load,
          unit,
          latitude,
          longitude,
          webViewPath,
        }
      : {
          nameOfExercise,
          repetitions,
          dropset,
          load,
          unit,
          latitude,
          longitude,
          webViewPath,
        };
    console.log(editedItem);
    saveItem &&
      saveItem(editedItem).then(() => {
        history.goBack();
      });
  };

  async function handlePhotoChange() {
    const image = await takePhoto();
    if (!image) {
      setWebViewPath("");
    } else {
      setWebViewPath(image);
    }
  }

  function setLocation() {
    setLatitude(currentLatitude);
    setLongitude(currentLongitude);
  }

  // animations

  const positionElement = document.querySelector(".position");
  if (positionElement) {
    const positionFabAnimation = createAnimation()
      .addElement(positionElement)
      .duration(1000)
      .fromTo("transform", "translateX(100px)", "translateX(0px)");
    (async () => {
      await positionFabAnimation.play();
    })();
  }

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Edit exercise</IonTitle>
          <IonButtons slot="end">
            <CreateAnimation
              duration={2000}
              iterations={Infinity}
              keyframes={[
                { offset: 0, transform: "scale(2)" },
                { offset: 0.5, transform: "scale(2.5)" },
                { offset: 1, transform: "scale(2)" },
              ]}
            >
              <IonButton onClick={handleSave}>Save</IonButton>
            </CreateAnimation>{" "}
          </IonButtons>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <IonItem>
          <IonLabel>Name Of Exercise: </IonLabel>
          <IonInput
            placeholder="name of exercise"
            value={nameOfExercise}
            onIonChange={(e) => setNameOfExercise(e.detail.value || "")}
          />
        </IonItem>
        <IonItem>
          <IonLabel>Repetitions: </IonLabel>
          <IonInput
            placeholder="repetitions"
            value={repetitions}
            onIonChange={(e) => setRepetitions(Number(e.detail.value) || 0)}
          />
        </IonItem>
        <IonItem>
          <IonLabel>Is the exercise a dropset? </IonLabel>
          <IonToggle
            checked={dropset}
            onIonChange={(e) => setDropset(e.detail.checked)}
          />
        </IonItem>
        <IonItem>
          <IonLabel>Load: </IonLabel>
          <IonInput
            placeholder="load"
            value={load}
            onIonChange={(e) => setLoad(Number(e.detail.value) || 0)}
          />
        </IonItem>
        <IonItem>
          <IonLabel>Unit: </IonLabel>
          <IonInput
            placeholder="unit"
            value={unit}
            onIonChange={(e) => setUnit(e.detail.value || "")}
          />
        </IonItem>
        <IonItem>
          <IonLabel>Where did you do the exercise?</IonLabel>
          <IonButton onClick={setLocation}>Set location</IonButton>
        </IonItem>
        <div className={"camera"}>
          {webViewPath && (
            <img
              onClick={handlePhotoChange}
              src={webViewPath}
              width={"160px"}
              height={"256px"}
            />
          )}
          {!webViewPath && (
            <img
              onClick={handlePhotoChange}
              src={
                "https://upload.wikimedia.org/wikipedia/commons/6/65/No-Image-Placeholder.svg"
              }
              width={"100px"}
              height={"100px"}
            />
          )}
        </div>
        <div className={"position"}>
          {lat && lng && (
            <MyMap
              lat={currentLatitude}
              lng={currentLongitude}
              onMapClick={log("onMap")}
              onMarkerClick={log("onMarker")}
            />
          )}
        </div>
        <IonLoading isOpen={saving} />
        {savingError && (
          <div>{savingError?.message || "Failed to save exercise"}</div>
        )}
      </IonContent>
    </IonPage>
  );

  function log(source: string) {
    return (e: any) => {
      setCurrentLatitude(e.latLng.lat());
      setCurrentLongitude(e.latLng.lng());
      console.log(source, e.latLng.lat(), e.latLng.lng());
    };
  }
};

export default ExerciseEdit;
