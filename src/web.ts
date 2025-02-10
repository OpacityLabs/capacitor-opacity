/* eslint-disable @typescript-eslint/no-unused-vars */
import { WebPlugin } from '@capacitor/core';

import type { OpacityEnvironment, OpacityPlugin } from './definitions';

export class OpacityWeb extends WebPlugin implements OpacityPlugin {
  initialize(_options: {
    apiKey: string;
    dryRun?: boolean;
    environment: OpacityEnvironment;
    shouldShowErrorsInWebView?: boolean;
  }): Promise<void> {
    throw new Error('Not supported on web.');
  }
  get(_options: {
    name: string;
    params?: Record<string, any>;
  }): Promise<{ json: Record<string, any>; signature?: string; proof?: string }> {
    throw new Error('Not supported on web.');
  }
}
