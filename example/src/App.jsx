import * as React from 'react';

import { StyleSheet, View, NativeModules, TouchableOpacity, Text, PermissionsAndroid, Platform } from 'react-native';
import Traccar from 'react-native-traccar';
import { request, PERMISSIONS, RESULTS } from 'react-native-permissions';
export default function App() {


  const startTrackingService = async () => {
    const granted = await request(
      Platform.select({
        android: PERMISSIONS.ANDROID.ACCESS_COARSE_LOCATION,
        ios: PERMISSIONS.IOS.LOCATION_WHEN_IN_USE,
      }),
      {
        title: 'DemoApp',
        message: 'DemoApp would like access to your location ',
      },
    );

    if (granted === RESULTS.GRANTED) return NativeModules?.Traccar.startTrackingService();
    alert('Location permission denied');

  }
  const stopTrackingService = () => {
    NativeModules?.Traccar.stopTrackingService()
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
