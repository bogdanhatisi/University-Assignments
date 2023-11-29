import Router from "koa-router";
import exerciseStore from "./store";
import { broadcast } from "../utils/wss.js";

export const router = new Router();

router.get("/exercises", async (ctx) => {
  const response = ctx.response;
  const userId = ctx.state.user._id;
  response.body = await exerciseStore.find({ userId });
  response.status = 200; // ok
});

router.get("/exercise/:id", async (ctx) => {
  const userId = ctx.state.user._id;
  const exercise = await exerciseStore.findOne({ _id: ctx.params.id });
  const response = ctx.response;
  if (exercise) {
    if (exercise.userId === userId) {
      response.body = exercise;
      response.status = 200; // ok
    } else {
      response.status = 403; // forbidden
    }
  } else {
    response.status = 404; // not found
  }
});

const createExercise = async (ctx, exercise, response) => {
  console.log("SE CREEAZA UN EXERCITIU");
  try {
    const userId = ctx.state.user._id;
    exercise.userId = userId;
    response.body = await exerciseStore.insert(exercise);
    response.status = 201; // created
    broadcast(userId, { type: "created", payload: response.body });
  } catch (err) {
    response.body = { message: err.message };
    response.status = 400; // bad request
  }
};

router.post(
  "/exercise",
  async (ctx) => await createExercise(ctx, ctx.request.body, ctx.response)
);

router.put("/exercise/:id", async (ctx) => {
  console.log("SE EDITEAZA UN EXERCITIU");
  const exercise = ctx.request.body;
  const id = ctx.params.id;
  const exerciseId = exercise._id;
  const response = ctx.response;
  if (exerciseId && exerciseId !== id) {
    response.body = { message: "Param id and body _id should be the same" };
    response.status = 400; // bad request
    return;
  }
  const userId = ctx.state.user._id;
  exercise.userId = userId;
  const updatedCount = await exerciseStore.update({ _id: id }, exercise);
  if (updatedCount === 1) {
    response.body = exercise;
    response.status = 200; // ok
    broadcast(userId, { type: "updated", payload: exercise });
  } else {
    response.body = { message: "Resource no longer exists" };
    response.status = 405; // method not allowed
  }
});

router.del("/exercise/:id", async (ctx) => {
  const userId = ctx.state.user._id;
  const exercise = await exerciseStore.findOne({ _id: ctx.params.id });
  if (exercise && userId !== exercise.userId) {
    ctx.response.status = 403; // forbidden
  } else {
    await exerciseStore.remove({ _id: ctx.params.id });
    ctx.response.status = 204; // no content
  }
});
