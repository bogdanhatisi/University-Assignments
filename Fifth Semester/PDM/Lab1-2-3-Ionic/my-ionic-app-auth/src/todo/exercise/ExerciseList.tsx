import {
  createAnimation,
  CreateAnimation,
  IonChip,
  IonContent,
  IonFab,
  IonFabButton,
  IonHeader,
  IonIcon,
  IonInfiniteScroll,
  IonInfiniteScrollContent,
  IonItem,
  IonLabel,
  IonList,
  IonLoading,
  IonPage,
  IonSearchbar,
  IonSelect,
  IonSelectOption,
  IonToast,
  IonToolbar,
} from "@ionic/react";
import React, { useContext, useEffect, useState } from "react";
import { RouteComponentProps } from "react-router";
import { ExerciseContext } from "./ExerciseProvider";
import Exercise from "./Exercise";
import { add } from "ionicons/icons";
import { AuthContext } from "../auth";
import { ExerciseProps } from "./ExerciseProps";
import { getLogger } from "../../core";
import { Network } from "@capacitor/core";
import "./Exercise.css";

const log = getLogger("ExerciseList");

const offset = 15;

const ExerciseList: React.FC<RouteComponentProps> = ({ history }) => {
  const { logout } = useContext(AuthContext);
  const { items, fetching, fetchingError } = useContext(ExerciseContext);
  const [disableInfiniteScroll, setDisabledInfiniteScroll] =
    useState<boolean>(false);
  const [visibleItems, setVisibleItems] = useState<ExerciseProps[] | undefined>(
    []
  );
  const [page, setPage] = useState(offset);
  const [filter, setFilter] = useState<string | undefined>(undefined);
  const [search, setSearch] = useState<string>("");
  const [status, setStatus] = useState<boolean>(true);

  Network.getStatus().then((status) => setStatus(status.connected));

  Network.addListener("networkStatusChange", (status) => {
    setStatus(status.connected);
  });

  const isDropset = ["true", "false", "all"];

  useEffect(() => {
    if (items?.length && items?.length > 0) {
      setPage(offset);
      fetchData();
      console.log(items);
    }
  }, [items]);

  useEffect(() => {
    if (items && filter) {
      if (filter === "all") setVisibleItems(items);
      else
        setVisibleItems(
          items.filter((each) => each.dropset.toString() === filter)
        );
    }
  }, [filter]);

  useEffect(() => {
    if (search === "") {
      setVisibleItems(items);
    }
    if (items && search !== "") {
      setVisibleItems(
        items.filter((each) =>
          each.nameOfExercise.toLowerCase().includes(search.toLowerCase())
        )
      );
    }
  }, [search]);

  function fetchData() {
    setVisibleItems(items?.slice(0, page));
    setPage(page + offset);
    if (items && page > items?.length) {
      setDisabledInfiniteScroll(true);
      setPage(items.length);
    } else {
      setDisabledInfiniteScroll(false);
    }
  }

  async function searchNext($event: CustomEvent<void>) {
    fetchData();
    ($event.target as HTMLIonInfiniteScrollElement).complete();
  }

  // animations

  const title = document.querySelector(".title");
  if (title) {
    const titleAnimation = createAnimation()
      .addElement(title)
      .duration(2000)
      .direction("alternate")
      .iterations(Infinity)
      .keyframes([
        { offset: 0, opacity: "0.2" },
        { offset: 0.5, opacity: "1" },
        { offset: 1, opacity: "0.2" },
      ]);
    titleAnimation.play();
  }

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonItem>
            <IonSelect
              style={{ width: "40%" }}
              value={filter}
              placeholder="Dropset:"
              onIonChange={(e) => setFilter(e.detail.value)}
            >
              {isDropset.map((each) => (
                <IonSelectOption key={each} value={each}>
                  {each}
                </IonSelectOption>
              ))}
            </IonSelect>
            <IonSearchbar
              style={{ width: "50%" }}
              placeholder="Search by name:"
              value={search}
              debounce={200}
              onIonChange={(e) => {
                setSearch(e.detail.value!);
              }}
            ></IonSearchbar>
            <CreateAnimation
              duration={2000}
              iterations={Infinity}
              keyframes={[
                { offset: 0, transform: "scale(1)", opacity: "0.5" },
                { offset: 0.5, transform: "scale(0.8)", opacity: "1" },
                { offset: 1, transform: "scale(1)", opacity: "0.5" },
              ]}
            ></CreateAnimation>
            <IonChip>
              <IonLabel color={status ? "success" : "danger"}>
                {status ? "Online" : "Offline"}
              </IonLabel>
            </IonChip>
          </IonItem>
        </IonToolbar>
      </IonHeader>

      <IonContent fullscreen>
        <br />
        <span className={`title`}>Exercises</span>
        <IonLoading isOpen={fetching} message="Loading..." />

        {visibleItems && (
          <IonList>
            {Array.from(visibleItems)
              .filter((each) => {
                if (filter !== undefined && filter !== "all")
                  return (
                    each.dropset.toString() === filter && each._id !== undefined
                  );
                return each._id !== undefined;
              })
              .map(
                ({
                  _id,
                  nameOfExercise,
                  repetitions,
                  dropset,
                  load,
                  unit,
                  latitude,
                  longitude,
                  webViewPath,
                }) => (
                  <Exercise
                    key={_id}
                    _id={_id}
                    nameOfExercise={nameOfExercise}
                    repetitions={repetitions}
                    dropset={dropset || false}
                    load={load}
                    unit={unit}
                    latitude={latitude}
                    longitude={longitude}
                    webViewPath={webViewPath}
                    onEdit={(_id) => history.push(`/api/items/exercise/${_id}`)}
                  />
                )
              )}
          </IonList>
        )}

        <IonInfiniteScroll
          threshold="100px"
          disabled={disableInfiniteScroll}
          onIonInfinite={(e: CustomEvent<void>) => searchNext(e)}
        >
          <IonInfiniteScrollContent loadingText="Loading..."></IonInfiniteScrollContent>
        </IonInfiniteScroll>

        {fetchingError && (
          <div>{fetchingError.message || "Failed to fetch items"}</div>
        )}

        <IonFab vertical="bottom" horizontal="end" slot="fixed">
          <IonFabButton onClick={() => history.push("/api/items/exercise")}>
            <IonIcon icon={add} />
          </IonFabButton>
        </IonFab>

        <IonFab vertical="bottom" horizontal="start" slot="fixed">
          <IonFabButton onClick={handleLogout}>Log out</IonFabButton>
        </IonFab>
      </IonContent>
    </IonPage>
  );

  function handleLogout() {
    log("logout");
    logout?.();
  }
};

export default ExerciseList;
