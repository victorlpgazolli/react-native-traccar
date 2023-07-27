//
//  TraccarModule.swift
//  TraccarClient
//
//  Created by Victor Gazolli on 27/07/23.
//  Copyright © 2023 Traccar. All rights reserved.
//

import Foundation
@objc(GetDetails)
class TraccarModule: NSObject {

    var trackingController: TrackingController?

    @objc(startTrackingService)
    func startTrackingService() {
        // userDefaults.setValue(true, forKey: "service_status_preference")
        // StatusViewController.addMessage(NSLocalizedString("Service created", comment: ""))
        trackingController = TrackingController()
        trackingController?.start()
        // showToast(message: NSLocalizedString("Service created", comment: ""))
    }
    
    @objc(stopTrackingService)
    func stopTrackingService() {
        // userDefaults.setValue(false, forKey: "service_status_preference")
        // StatusViewController.addMessage(NSLocalizedString("Service destroyed", comment: ""))
        trackingController?.stop()
        trackingController = nil
        // showToast(message: NSLocalizedString("Service destroyed", comment: ""))
    }
 
}
