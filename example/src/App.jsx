import * as React from 'react';

import { StyleSheet, View, TouchableOpacity, Text } from 'react-native';
import Traccar from 'react-native-traccar';

export default function App() {

  const startTrackingService = async () => {
    Traccar.setupTrackingService("http://demoX.traccar.org:5055", "DEVICE_ID_HERE", 5)

    Traccar.startTrackingService()

  }
  React.useEffect(() => {
    startTrackingService()
  }, [])
  const stopTrackingService = () => {
    Traccar.stopTrackingService()
  }

  return (
    <View style={styles.container}>
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
