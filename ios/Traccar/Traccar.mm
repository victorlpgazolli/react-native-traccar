#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(Traccar, NSObject)

RCT_EXTERN_METHOD(startTrackingService, NSObject)
RCT_EXTERN_METHOD(stopTrackingService, NSObject)

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end
