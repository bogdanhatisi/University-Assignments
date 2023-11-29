import dataStore from "nedb-promise";

export class ExerciseStore {
  constructor({ filename, autoload }) {
    this.store = dataStore({ filename, autoload });
  }

  async find(props) {
    return this.store.find(props);
  }

  async findOne(props) {
    return this.store.findOne(props);
  }

  async insert(exercise) {
    return this.store.insert(exercise);
  }

  async update(props, exercise) {
    return this.store.update(props, exercise);
  }

  async remove(props) {
    return this.store.remove(props);
  }
}

export default new ExerciseStore({
  filename: "./db/exercises.json",
  autoload: true,
});
