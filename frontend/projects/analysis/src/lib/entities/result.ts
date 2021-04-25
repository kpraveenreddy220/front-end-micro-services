export type ResultStatus = 'STARTING' | 'RUNNING' | 'STOPPING' | 'COMPLETED' | 'CANCELED' | 'FAILED';
export type ResultType = 'RUN' | 'DEBUG' | 'HAR';

export interface Result {
  id: string;
  startDate: number;
  endDate: number;
  status: ResultStatus;
  description: string;
  type: ResultType;
}
