export interface OpacityPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
