export interface ExerciseProps {
  _id?: string;
  nameOfExercise: string;
  repetitions: number;
  dropset: boolean;
  load: number;
  unit: string;
  latitude?: number;
  longitude?: number;
  webViewPath: string;
}
