# react-native-traccar

package for traccar

## Installation

```sh
npm install react-native-traccar
```

## Usage

```ts
import { 
    startTrackingService,
    stopTrackingService,
    setupTrackingService
 } from 'react-native-traccar';


interface SetupOptions {
    url: string
    deviceId: string
    distanceFilter?: number // set 0 to disable, defaults to 0
    locationUpdatesIntervalInSeconds?: number // defaults to 600s (10 minutes)
}

setupTrackingService(options: SetupOptions): void


startTrackingService(): void

// ...


stopTrackingService(): void

```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
