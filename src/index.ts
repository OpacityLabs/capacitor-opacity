import { registerPlugin } from '@capacitor/core';

import type { OpacityPlugin } from './definitions';

const Opacity = registerPlugin<OpacityPlugin>('Opacity', {
  web: () => import('./web').then((m) => new m.OpacityWeb()),
});

export * from './definitions';
export { Opacity };
