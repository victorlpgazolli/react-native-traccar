import * as React from 'react';

import { StyleSheet, View, NativeModules, TouchableOpacity, Text, PermissionsAndroid, Platform } from 'react-native';
import Traccar from 'react-native-traccar';

export default function App() {

  const startTrackingService = async () => {
    // await requestMultiple([
    //   Platform.select({
    //     android: PERMISSIONS.ANDROID.ACCESS_FINE_LOCATION,
    //     ios: PERMISSIONS.IOS.LOCATION_ALWAYS,
    //   })
    // ]);
    Traccar.startTrackingService()
    // PermissionsAndroid.request(
    //   PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
    //   {
    //     title: 'Location Access Required',
    //     message: 'This App needs to Access your location',
    //   },
    // ).then(granted => {
    //   if (granted === PermissionsAndroid.RESULTS.GRANTED) {
    //     NativeModules?.Traccar.startTrackingService()
    //   } else {
    //     alert('Location permission denied');
    //   }
    // });
  }
  const stopTrackingService = () => {
    Traccar.stopTrackingService()
  }

  return (
    <View style={styles.container}>
      {/* <Text>Result: {result}</Text> */}
      <TouchableOpacity onPress={startTrackingService}>
        <Text>start</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={stopTrackingService}>
        <Text>stop</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
