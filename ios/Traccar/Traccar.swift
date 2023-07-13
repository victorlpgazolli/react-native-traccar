import Foundation
@objc(Traccar)
class Traccar: NSObject{
    
    @objc
    func startTrackingService(){
        print("startTrackingService") 
    }
    
    @objc
    func stopTrackingService(){
        print("stopTrackingService") 
    }
}
