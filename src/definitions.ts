type WorkflowResponse = {
  json: Record<string, any>;
  signature?: string;
  proof?: string;
};

export enum OpacityEnvironment {
  Test = 0,
  Local = 1,
  Staging = 2,
  Production = 3,
}

export interface OpacityPlugin {
  initialize(options: {
    apiKey: string;
    dryRun?: boolean;
    environment: OpacityEnvironment;
    shouldShowErrorsInWebView?: boolean;
  }): Promise<void>;
  get(options: { name: string; params?: Record<string, any> }): Promise<WorkflowResponse>;
}
