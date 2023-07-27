//
//  TraccarModule.m
//  TraccarClient
//
//  Created by Victor Gazolli on 27/07/23.
//  Copyright © 2023 Traccar. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import "TraccarModule.h"

//@interface RCT_EXTERN_MODULE(TraccarModule, NSObject);


@implementation TraccarModule


RCT_EXPORT_MODULE();

+(BOOL)requiresMainQueueSetup
{
    return YES;
}
// method to export
RCT_EXTERN_METHOD(startTrackingService);
RCT_EXTERN_METHOD(stopTrackingService);


@end
