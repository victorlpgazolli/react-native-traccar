#import <Foundation/Foundation.h>

#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(Traccar, NSObject)

RCT_EXTERN_METHOD(startTrackingService, NSObject);
RCT_EXTERN_METHOD(stopTrackingService, NSObject);


@end
