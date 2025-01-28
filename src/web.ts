import { WebPlugin } from '@capacitor/core';

import type { OpacityPlugin } from './definitions';

export class OpacityWeb extends WebPlugin implements OpacityPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
