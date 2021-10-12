export interface SecondaryFloatingActionButton {
  description?: string;
  title?: string;
  iconUri: string;
  buttonId: string;
}

export interface FabLayoutParams {
  description?: string;
  title?: string;
  iconUri: string;
  secondaryFabs?: SecondaryFloatingActionButton[];
}
