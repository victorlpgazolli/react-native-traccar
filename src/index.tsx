import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-traccar' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';
const Module = Platform.select({
  ios: NativeModules.TraccarModule,
  android: NativeModules.Traccar,
})
const Traccar = Module
  ? Module
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );


function setupTrackingService(url: string, deviceId: string, interval: number): void {

  return Traccar.setupTrackingService(url, deviceId, interval);
}
function startTrackingService(): void {
  return Traccar.startTrackingService();
}

function stopTrackingService(): void {
  return Traccar.stopTrackingService();
}

export default {
  startTrackingService,
  stopTrackingService,
  setupTrackingService
};